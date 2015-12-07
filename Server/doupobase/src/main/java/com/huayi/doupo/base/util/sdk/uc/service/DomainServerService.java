package com.huayi.doupo.base.util.sdk.uc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayi.doupo.base.util.sdk.uc.config.Configuration;
import com.huayi.doupo.base.util.sdk.uc.constant.ServiceName;
import com.huayi.doupo.base.util.sdk.uc.model.DomainInfo;
import com.huayi.doupo.base.util.sdk.uc.model.SDKException;
import com.huayi.doupo.base.util.sdk.uc.session.ModelSession;
import com.huayi.doupo.base.util.sdk.uc.session.SDKSessionMgr;

/**
 * 天雷服务端通信服务
 * <br>
 * 天雷主要是解决DNS解析域名失败的场景下，通过天雷系统拿取正确的SDKServer服务端ip
 * <br>==========================
 */
public class DomainServerService extends AbstractSDKService{
    
    private static List<String> serverAddressList = new ArrayList<String>();
    
    private final static ReentrantLock lock = new ReentrantLock();
    
    private final static boolean DEBUG = Configuration.getDebug();
    private static Logger log = LoggerFactory.getLogger(DomainServerService.class.getName());
    
    static {
        init();
    }

    public static DomainInfo getDomainByServer() throws SDKException{
        DomainInfo domainInfo = null;
        
        //从缓存中获取，是否有值
        ModelSession ms = SDKSessionMgr.getInstance().getSession(ServiceName.DOMAINSERVER_CACHE_KEY);
        if(ms == null){
            domainInfo = getDomainInfoByLock();
        }
        else{
            domainInfo = (DomainInfo) ms.getAttribute(ServiceName.DOMAINSERVER_ATTR_KEY);
        }
        return domainInfo;
    }
    
    /**
     * 获取缓存中的天雷系统缓存
     * @return
     */
    public static DomainInfo getDomainByCache(){
        DomainInfo domainInfo = null;
        
        //从缓存中获取，是否有值
        ModelSession ms = SDKSessionMgr.getInstance().getSession(ServiceName.DOMAINSERVER_CACHE_KEY);
        if(ms == null){
            return null;
        }
        else{
            //log("从缓存中获取值成功，直接获取获取属性");
            domainInfo = (DomainInfo) ms.getAttribute(ServiceName.DOMAINSERVER_ATTR_KEY);
        }
        return domainInfo;
    }
    
    private static void init() {
        String domainServerWithCm = Configuration.getDomainBaseUrlWithCm();
        String domainServerWithCt = Configuration.getDomainBaseUrlWithCt();
        String domainServerWithUnicom = Configuration.getDomainBaseUrlWithUnicom();
        
        serverAddressList.add(domainServerWithCm);
        serverAddressList.add(domainServerWithCt);
        serverAddressList.add(domainServerWithUnicom);
    }
    
    private static DomainInfo getDomainInfoByLock() throws SDKException{
        // 检查标志位
        ModelSession ms = SDKSessionMgr.getInstance().getSession(ServiceName.DOMAINSERVER_LOCK_KEY);
        if (ms == null) {
            log("检测到无其它线程设置标志位，进入竞争状态。");
            
            // 标志不存在，进入竞争状态
            if (lock.tryLock()) {
                log("竞争锁成功，访问天雷系统获取并设置缓存值");
                // 竞争胜利者
                try {
                    //请求天雷系统
                    DomainInfo domainInfo = getDomainInfoByMutiThread();
                    if(domainInfo != null){
                        //设置标志位,创建key值即可，无需属性值，在该标志位创建前，所有并发进程都进入争抢锁状态。
                        //此标志位的主要用途：稍后于本批线程进入获取天雷返回流程的线程检测到标志位后直接休眠2秒再获取缓存返回。
                        SDKSessionMgr.getInstance().createSession(ServiceName.DOMAINSERVER_LOCK_KEY);
                        log("完成设置竞争状态标志位");
                        
                        
                        ms = SDKSessionMgr.getInstance().createSession(ServiceName.DOMAINSERVER_CACHE_KEY);
                        ms.setAttribute(ServiceName.DOMAINSERVER_ATTR_KEY, domainInfo);
                        log("完成设置域名缓存");
                        
                        return domainInfo;
                    }
                    return null;//请求天雷系统失败
                } finally {
                    lock.unlock();
                }
            } else {
                log("竞争锁失败，进入等待状态，休眠结束后直接返回缓存。");
                //竞争失败，进入等待状态
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    throw new SDKException("获取天雷系统IP信息时，获取竞争锁失败后处于等待状态的线程被中断", e);
                }
            }
        } else {
            log("检测到已有其它线程设置标志位，进入等待状态，休眠结束后直接返回缓存。");
            
            // 标志存在，已有其他线程进入获取状态，本线程等待
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new SDKException("获取天雷系统IP信息，其它线程已设置标志位后处于等待状态的线程被中断", e);
            }
        }
        
        //直接获取缓存并返回
        return getDomainByCache();

    }

    private static DomainInfo getDomainInfoByMutiThread(){
        /** 
         * 内部维护指定大小线程的线程池 
         */  
        ExecutorService exec = Executors.newFixedThreadPool(serverAddressList.size()); 
        
        /** 
         * 容量为指定大小的阻塞队列 
         */  
        final BlockingQueue<Future<String>> queue = new LinkedBlockingDeque<Future<String>>(serverAddressList.size());
        //实例化CompletionService  
        final CompletionService<String> completionService = new ExecutorCompletionService<String>(exec, queue);
        
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        String responseResult = null;//返回的结果集
        /** 
         * 调用天雷服务端的接口 
         */
        try {
            for (String address : serverAddressList){
                futureList.add(completionService.submit(new DomainServerService().new domainServerCallable(address)));
            }
            
            //获取结果集
            for(int i = 0; i < serverAddressList.size(); i++){
                try {
                    //取第一个结果集
                    String result = completionService.take().get();
                    if (result != null) {
                        responseResult = result;
                        break;
                    }
                } catch(ExecutionException ignore) {} 
                catch (InterruptedException ignore) {}
            }
        }
        catch(Exception ignore){
            //忽略并发线程内的异常，比如：请求天雷系统异常
        }
        finally {
            for (Future<String> f : futureList){
                f.cancel(true);
            }
            exec.shutdown();
        }
        
        if(responseResult == null || "".equals(responseResult)){
            return null;
        }
        
        //解析接口返回的结果集:sdk.g.uc.cn_117.135.151.250|
        //String[] mutiServerAddress = responseResult.split("[|]");Pattern.quote("|")
        String[] mutiServerAddress = responseResult.split("\\|");
        if(mutiServerAddress == null || mutiServerAddress.length == 0){
            log("获取到天雷服务端接口返回的IP地址为空。");
            return null;
        }
        
        String[] serverInfo = mutiServerAddress[0].split("_");
        if(serverInfo == null || serverInfo.length == 0 || serverInfo[0] == null || "".equals(serverInfo[0]) || serverInfo[1] == null ||"".equals(serverInfo[1])){
            log("获取到天雷服务端接口返回的IP地址为空。");
            return null;
        }
        
        DomainInfo domainInfo = new DomainInfo();
        domainInfo.setDomain(serverInfo[0]);
        domainInfo.setIpAddress(serverInfo[1]);
        
        return domainInfo;
    }
    
    /**
     * 调用天雷服务端的多线程
     * <br>
     * <br>========================== 
     */
    private class domainServerCallable implements Callable<String>{
        
        private String serverAddress;//服务端的地址
        
        public domainServerCallable(String serverAddress){
            this.serverAddress = serverAddress;
        }

        @Override
        public String call() throws Exception {
            return client.post(serverAddress + ServiceName.DOMAINSERVER_SERVICE, ServiceName.DOMAINSERVER_REQ_BODY, true);
        }
        
    }
    
    /**
     * log调试
     * 
     */
    protected static void log(String message) {
        if (DEBUG) {
            log.debug(message);
        }
    }
}
