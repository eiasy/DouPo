package com.huayi.doupo.base.util.sdk.uc.protocol;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

import com.huayi.doupo.base.util.sdk.uc.config.Version;
import com.huayi.doupo.base.util.sdk.uc.model.SDKException;
import com.huayi.doupo.base.util.sdk.uc.protocol.CpRequest.Game;


/**
 * CP请求JSON请求数据组装辅助类
 * 
 * <br>==========================
 * <br> 公司：优视科技-游戏中心
 * <br> 开发：NieYong <nieyong@ucweb.com>
 * <br> 创建时间：2012-12-25下午5:33:32
 * <br>==========================
 */
public class CpRequestHelper {


    /**
     * 组装为完成的客户端请求参数
     * @param serviceName
     * @param params
     * @return
     * @throws SDKException 
     */
    public static CpRequest assemblyParams(String serviceName, Map<String, Object> params) throws SDKException {
        CpRequest request = new CpRequest();
        request.setId(CpRequestHelper.createRequestId());
        request.setGame(CpRequestHelper.getCpGame());
        request.setService(serviceName);
        request.setEncrypt("md5");
        request.setSign(CpRequestHelper.createMD5Sign(params));
        request.setClient(createClient());
        request.setData(params);
        
        if(request.getSign() == null || "".equals(request.getSign())){
            throw new SDKException("配置项中的无秘钥项");
        }
        
        return request;
    }
    
    /**
     * 生成请求id
     * 
     * @return 请求id
     */
    private static long createRequestId() {
        long time = System.currentTimeMillis();
        
        return time;
    }
   
    private static Game getCpGame(){
        return null;
    }

    /**
     * 内部调用时，指定的caller,key
     * <br>注意：不使用cpId作为caller
     * @param params
     * @return
     */
    public static String createMD5Sign(Map<String, Object> params){
        return createMD5Sign(params, "", "");
    }
    
    /**
     * 创建客户端(客户端类型，版本号)
     * @return
     */
    public static Map<String, Object> createClient(){
        Map<String, Object> clientMap = new HashMap<String, Object>();
        String ex = "language:" + Version.getLanguage() + "|version:" + Version.getVersion();
        clientMap.put("ex", ex);
        
        return clientMap;
    }
    
    /**
     * 按照接口规范生成请求数据的MD5签名
     * 
     * @param params 业务数据
     * @param caller 客户端平台
     * @param secKey MD5签名用的密钥
     * @return MD5签名生成的字符串。如果传入的参数有一个为null，将返回null
     */
    public static String createMD5Sign(Map<String, Object> params, String caller, String secKey) {
        if (null == params || null == caller || null == secKey) {
            return null;
        }
        
        String temp = caller + createSignData(params, null) + secKey;
        
        return hexMD5(temp);
    }
    
    /**
     * 将Map数据组装成待签名字符串
     * 
     * @param params 待签名的参数列表
     * @param notIn 不参与签名的参数名列表
     * @return 待签名字符串。如果参数params为null，将返回null
     */
    public static String createSignData(Map<String, Object> params, String[] notIn) {
        if (null == params) {
            return null;
        }
        
        StringBuilder content = new StringBuilder(200);

        // 按照key排序
        List<String> notInList = null;
        if (null != notIn) {
            notInList = Arrays.asList(notIn);
        }
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);

            if (notIn != null && notInList.contains(key))
                continue;

            String value = params.get(key)==null?"":params.get(key).toString();
            content.append(key)
                    .append("=")
                    .append(value);
        }

        String result = content.toString();
        
        return result;
    }
    
    /**
     * 对字符串进行MD5签名
     * @param value 待MD5签名的字符串
     * @return 生成的MD5签名字符串。如果传入null，返回null；如果签名过程中抛出异常，将返回null
     */
    public static String hexMD5(String value) {
        if (null == value) {
            return null;
        }
        
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(value.getBytes("utf8"));
            byte[] digest = messageDigest.digest();
            return byteToHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            // ignore
        } catch (UnsupportedEncodingException e) {
            // ignore
        }
        
        return null;
    }
    
    /**
     * 将字节数组转换成十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String byteToHexString(byte[] bytes) {
        if (null == bytes) {
            return null;
        }
        
        return String.valueOf(Hex.encodeHex(bytes));
    }

}
