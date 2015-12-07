package com.huayi.doupo.base.util.sdk.uc.session;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存信息管理,模拟实现WEB容器Session会话管理的工具类
 * 
 */
public class SDKSessionMgr {
    private static SDKSessionMgr instance = new SDKSessionMgr();

    private Logger log = LoggerFactory.getLogger(SDKSessionMgr.class.getName());

    private ConcurrentHashMap<String, ModelSession> sdkSessionMap = new ConcurrentHashMap<String, ModelSession>();

    private Timer timer;

    private ClearSessionTask clearSessionTask = new ClearSessionTask();

    private long overTime = 1000 * 60 * 5;//缓存固定为5分钟

    private SDKSessionMgr() {
        this.timer = new Timer(SDKSessionMgr.class.getName());
        this.timer.scheduleAtFixedRate(this.clearSessionTask, new Date(), this.overTime);
    }

    /**
     * 取得wapSessionMgr实例
     * 
     * @return
     */
    public static SDKSessionMgr getInstance() {
        return SDKSessionMgr.instance;
    }

    /**
     * 创建session
     * 
     * @return
     */
    public String createSession() {
        ModelSession ws = new ModelSession();
        ws.setCreateTime(System.currentTimeMillis());
        ws.setLastUseTime(ws.getCreateTime());
        String sid = buildSessionId();

        sdkSessionMap.put(sid, ws);
        return sid;
    }
    
    /**
     * 指定常量key创建session
     * 
     * @return
     */
    public ModelSession createSession(String cacheConstKey) {
        ModelSession ws = new ModelSession();
        ws.setCreateTime(System.currentTimeMillis());
        ws.setLastUseTime(ws.getCreateTime());

        sdkSessionMap.put(cacheConstKey, ws);
        return ws;
    }

    /**
     * 取得sdkSession对象
     * 
     * @param sessionId
     * @return
     */
    public ModelSession getSession(String sessionId) {
        if (sessionId == null || sessionId.length() == 0) {
            return null;
        }

        ModelSession ws = this.sdkSessionMap.get(sessionId);

        if (ws != null) {
            ws.setLastUseTime(System.currentTimeMillis());
        }

        return ws;
    }

    private String buildSessionId() {
        String id = UUID.randomUUID().toString();
        return id;
    }

    public void clearSession() {
        Iterator<Map.Entry<String, ModelSession>> iter = sdkSessionMap.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<String, ModelSession> entry = iter.next();
            ModelSession value = entry.getValue();
            if (value != null
                    && System.currentTimeMillis() - value.getLastUseTime() > this.overTime) {
                iter.remove();
            }
        }
    }

    public void destorySessionBySid(String sid) {
        this.sdkSessionMap.remove(sid);
    }

    public static void main(String[] args) throws Exception {
        String sessionId = SDKSessionMgr.getInstance().createSession();
        System.out.println(sessionId);
        ModelSession ms = SDKSessionMgr.getInstance().getSession(sessionId);
        System.out.println(ms.getLastUseTime());
        ms.setParameter("abcd", "qwertyuiop");
        Thread.sleep(2000l);
        ms = SDKSessionMgr.getInstance().getSession(sessionId);
        System.out.println(ms.getLastUseTime());
        System.out.println(ms.getParameter("abcd"));
        
        //SDKSessionMgr.getInstance().stopService();
        System.exit(0);
    }

    private class ClearSessionTask extends TimerTask {
        public void run() {
            SDKSessionMgr.getInstance().clearSession();
        }
    }
    
    public void stopService() {
        if(log.isDebugEnabled()){
            log.debug("会话管理已退出");
        }
            
        sdkSessionMap.clear();
        timer.cancel();
    }
    
    /**
     * 钩子程序，程序被关闭时调用关闭服务方法
     */
    static {
        Runtime runtime = Runtime.getRuntime();
        Class c = runtime.getClass();
        try {
            Method m = c.getMethod("addShutdownHook", new Class[] { Thread.class });

            m.invoke(runtime, new Object[] { new ShutdownThread() });
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shuts down the current service provider. It should be called when the VM is exiting so that any necessary cleanup can be done.
     */
    private static class ShutdownThread extends Thread {
        public void run() {
            SDKSessionMgr.getInstance().stopService();
        }
    }
}
