package com.huayi.doupo.base.util.sdk.uc.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayi.doupo.base.util.sdk.uc.config.Configuration;
import com.huayi.doupo.base.util.sdk.uc.constant.StateCode;
import com.huayi.doupo.base.util.sdk.uc.http.SDKHttpClient;
import com.huayi.doupo.base.util.sdk.uc.model.DomainInfo;
import com.huayi.doupo.base.util.sdk.uc.model.SDKException;
import com.huayi.doupo.base.util.sdk.uc.protocol.CpRequest;
import com.huayi.doupo.base.util.sdk.uc.protocol.CpRequestHelper;
import com.huayi.doupo.base.util.sdk.uc.protocol.CpResponse;
import com.huayi.doupo.base.util.sdk.uc.util.BeanToMapUtil;
import com.huayi.doupo.base.util.sdk.uc.util.JacksonUtil;


public abstract class AbstractSDKService {
    
    private final static boolean DEBUG = Configuration.getDebug();
    private static Logger log = LoggerFactory.getLogger(AbstractSDKService.class.getName());

    protected static SDKHttpClient client = new SDKHttpClient();

//    //如果希望自己设置HttpClient的各种参数，可以使用下面的构造方法
//    protected static SDKHttpClient client = new SDKHttpClient(int maxConPerHost, int conTimeOutMs, int soTimeOutMs);
    
    /**
     * 调用SDKServer接口并返回实体类
     * @param params
     * @param sidInfo 
     * @param entityClazz
     * @return
     * @throws SDKException
     */
    protected static <T> T getSDKServerResponse (Map<String, Object> params, String prefix, String serviceName, Class<T> entityclazz, String gameId) throws SDKException{
        T entity = null;
        try {
            CpRequest requestObj = CpRequestHelper.assemblyParams(serviceName, params);
            String requestBody = JacksonUtil.encode(requestObj);
            String responseBody = "";
            try {
                //首次请求时，识别天雷缓存中是否有值，若有则代表直接使用天雷的ip
                DomainInfo domainCache = DomainServerService.getDomainByCache();
                if(domainCache != null){
                    log("天雷系统缓存存在，直接使用天雷系统返回的IP"+assemblyUrl("http://" + domainCache.getIpAddress(), prefix , serviceName)+"访问服务端.");
                    responseBody = client.post(assemblyUrl("http://" + domainCache.getIpAddress(), prefix , serviceName), requestBody);
                }
                else{
                    responseBody = client.post(assemblyUrl(Configuration.getSDKServerBaseUrl(), prefix , serviceName), requestBody);
                }
            } catch (SDKException e) {
                if(e.getErrorCode() == -1){//网络无法连接场景
                    //使用天雷客户端
                    DomainInfo domainInfo = DomainServerService.getDomainByServer();
                    if(domainInfo == null){
                        log("请求常规接口"+assemblyUrl(Configuration.getSDKServerBaseUrl(), prefix , serviceName)+"失败，请求天雷也失败.");
                        throw e;
                    }
                    
                    //重新使用天雷客户端返回的ip地址进行请求
                    responseBody = client.post(assemblyUrl("http://" + domainInfo.getIpAddress(), prefix , serviceName), requestBody);
                }
                else{
                    throw e;
                }
            }
            
            if("".equals(responseBody)){
                throw new SDKException("网络响应异常", -1);
            }
            
            CpResponse responseObj = null;
            try {
                responseObj = (CpResponse) JacksonUtil.decode(responseBody, CpResponse.class);
            } catch (Exception e) {
                // 无法正确解析报文，使用天雷客户端
                DomainInfo domainInfo = DomainServerService.getDomainByServer();
                if(domainInfo == null){
                    log("请求常规接口"+assemblyUrl(Configuration.getSDKServerBaseUrl(), prefix , serviceName)+"失败，请求天雷也失败.");
                    throw e;
                }
                
                //重新使用天雷客户端返回的ip地址进行请求
                responseBody = client.post(assemblyUrl("http://" + domainInfo.getIpAddress(), prefix , serviceName), requestBody);
                try {
                    responseObj = (CpResponse) JacksonUtil.decode(responseBody, CpResponse.class);
                } catch (Exception jsondecodeerror) {
                    throw new SDKException("解析报文异常", jsondecodeerror, -1);
                }
            }
            
            if(responseObj == null){
                throw new SDKException("请求接口无响应", -1);
            }
            
            if(responseObj.getState().getCode() != StateCode.SUCCESS){
                //接口返回失败，以异常的形式抛出
                throw new SDKException(responseObj.getState().getMsg(), responseObj.getState().getCode());
            }
            
            //返回的结果集安全解析
            if(responseObj.getData() instanceof Map){
                entity = BeanToMapUtil.convertMap(entityclazz, (Map<String, Object>)responseObj.getData());
            }
            else{
                entity = (T) responseObj.getData();//直接赋值
            }
        } catch (Exception e) {
            if(e instanceof SDKException){
                SDKException sdkException = (SDKException) e;
                throw sdkException;
            }
            throw new SDKException("调用接口出错", e, -1);
        }
        return entity;
    }
    
    /**
     * 组装POST请求url
     * @param sdkServerBaseUrl
     * @param prefix
     * @param serviceName
     * @return
     */
    protected static String assemblyUrl(String sdkServerBaseUrl, String prefix, String serviceName) {
        String postUrl = filtSplictWord(sdkServerBaseUrl, Boolean.FALSE) + "/" + filtSplictWord(prefix, Boolean.TRUE) + filtSplictWord(serviceName, Boolean.TRUE);
        return postUrl;
    }
    
    /**
     * url和method统一去掉“/” OR “\”
     * @param str
     * @param isStartsWith
     * @return
     */
    private static String filtSplictWord(String str, boolean isStartsWith){
        if(str==null ||"".equals(str)){
            return str;
        }
        if(isStartsWith){
            if(str.startsWith("\\") || str.startsWith("/")){
                return str.substring(1, str.length());
            }
        }else{
            if(str.endsWith("\\") || str.endsWith("/")){
                return str.substring(0, str.length()-1);
            }
        }
        return str;
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
