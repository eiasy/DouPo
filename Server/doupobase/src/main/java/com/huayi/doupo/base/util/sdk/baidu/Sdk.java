/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huayi.doupo.base.util.sdk.baidu;

/**
 *
 * @author Administrator
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Sdk {
	
	//开发者应用APPID
	private String appid = "";  
        public String getAppID(){
            return appid;
        }
	//开发者应用SecretKey
	private String secretkey = "";
        public String getSecretKey(){
            return secretkey;
        }        
       
	/**
	 * 对字符串进行MD5并返回结果
	 * @param sourceStr
	 * @return
	 */
	public static String md5(String sourceStr){
		String signStr = "";
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("MD5"); md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if(md5Byte != null){
			signStr = HexBin.encode(md5Byte); }
			} catch (NoSuchAlgorithmException e) { e.printStackTrace();
			} catch (UnsupportedEncodingException e) { e.printStackTrace();
			}
			return signStr;
	}
	
        /**
        * 向指定 URL 发送POST方法的请求
        * @param goUrl
        *            请求地址
        * @param param
        *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式
        * @return 所代表远程资源的响应结果
        */
        public static String sendPost(String goUrl,String param) {
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            try {
                URL realUrl = new URL(goUrl);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(),"utf-8"));      
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送 POST 请求出现异常！"+e);
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流
            finally{
                try{
                    if(out!=null){
                        out.close();
                    }
                    if(in!=null){
                        in.close();
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            return result;
        }    
         /**
	 * 获取POST流请求参数
	 * @param inStream
	 * @return
	 */
        public HashMap<String, String> getParams(InputStream inStream){
            BufferedReader in =null;
            HashMap<String , String> map = new HashMap<String , String>();
            try {
                in= new BufferedReader(
                        new InputStreamReader(inStream,"utf-8"));
                String postData="";
                String line;
                while ((line = in.readLine()) != null) {
                    postData += line;
                }
                if(postData != null && postData.trim().length() != 0){
                    String connectorParam = "&";
                    String spiltParam="=";
                    if(postData.indexOf(connectorParam)>-1&&postData.indexOf(spiltParam)>-1){
                        for(String keyValue : postData.split(connectorParam)){
                             String[] kv = keyValue.split(spiltParam);
                             if (kv.length > 1 && !map.containsKey(kv[0])){
                                 map.put(kv[0], kv[1]);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("getParams出现异常"+e);
                e.printStackTrace();
            }
            finally{
                try{
                    if(in!=null){
                        in.close();
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            return map;
        }
}
