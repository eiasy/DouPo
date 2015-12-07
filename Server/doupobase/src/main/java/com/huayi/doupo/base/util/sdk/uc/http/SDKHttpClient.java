package com.huayi.doupo.base.util.sdk.uc.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayi.doupo.base.util.sdk.uc.config.Configuration;
import com.huayi.doupo.base.util.sdk.uc.model.SDKException;


/**
 * 封装HTTPClient 3.1的客户端
 * <br>==========================
 */
public class SDKHttpClient implements java.io.Serializable {

	private static final long serialVersionUID = -176092625883595547L;
	private static final int OK 				   = 200;// OK: Success!
	private static final int NOT_MODIFIED 		   = 304;// Not Modified: There was no new data to return.
	private static final int BAD_REQUEST 		   = 400;// Bad Request: The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.
	private static final int NOT_AUTHORIZED 	   = 401;// Not Authorized: Authentication credentials were missing or incorrect.
	private static final int FORBIDDEN 			   = 403;// Forbidden: The request is understood, but it has been refused.  An accompanying error message will explain why.
	private static final int NOT_FOUND             = 404;// Not Found: The URI requested is invalid or the resource requested, such as a user, does not exists.
	private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server Error: Something is broken.  Please post to the group so the 9game sdk team can investigate.
	private static final int BAD_GATEWAY           = 502;// Bad Gateway: servers is down or being upgraded.
	private static final int SERVICE_UNAVAILABLE   = 503;// Service Unavailable: The servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.

	private String proxyHost = Configuration.getProxyHost();
	private int proxyPort = Configuration.getProxyPort();
	private String proxyAuthUser = Configuration.getProxyUser();
	private String proxyAuthPassword = Configuration.getProxyPassword();

	public String getProxyHost() {
		return proxyHost;
	}

	/**
	 * Sets proxy host. System property
	 * 
	 * @param proxyHost
	 */
	public void setProxyHost(String proxyHost) {
		this.proxyHost = Configuration.getProxyHost(proxyHost);
	}

	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * Sets proxy port. System property
	 * 
	 * @param proxyPort
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = Configuration.getProxyPort(proxyPort);
	}

	public String getProxyAuthUser() {
		return proxyAuthUser;
	}

	/**
	 * Sets proxy authentication user. 
	 * 
	 * @param proxyAuthUser
	 */
	public void setProxyAuthUser(String proxyAuthUser) {
		this.proxyAuthUser = Configuration.getProxyUser(proxyAuthUser);
	}

	public String getProxyAuthPassword() {
		return proxyAuthPassword;
	}

	/**
	 * Sets proxy authentication password. System property
	 * 
	 * @param proxyAuthPassword
	 */
	public void setProxyAuthPassword(String proxyAuthPassword) {
		this.proxyAuthPassword = Configuration.getProxyPassword(proxyAuthPassword);
	}

	private static boolean DEBUG = Configuration.getDebug() ;
	private static Logger log = LoggerFactory.getLogger(SDKHttpClient.class);
	private HttpClient client = null;

	private MultiThreadedHttpConnectionManager connectionManager;

	/**
	 * 默认的客户端配置
	 * @throws SDKException 
	 */
	public SDKHttpClient() {
	    this(Configuration.getDefaultMaxConnectionsPerHost(150),
	            Configuration.getConnectionTimeout(30000),
	            Configuration.getReadTimeout(30000));
	}

	public SDKHttpClient(int maxConPerHost, int conTimeOutMs, int soTimeOutMs) {
	    //默认的连接池太小，此处指定大小的连接池
		connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setDefaultMaxConnectionsPerHost(maxConPerHost);
		params.setConnectionTimeout(conTimeOutMs);// 设置连接的超时时间 
		params.setSoTimeout(soTimeOutMs);// 设置读取数据的超时时间 

		HttpClientParams clientParams = new HttpClientParams();
        // 忽略cookie 避免 Cookie rejected 警告
        clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        client = new HttpClient(clientParams, connectionManager);
		// 支持proxy
		if (proxyHost != null && !proxyHost.equals("")) {
			client.getHostConfiguration().setProxy(proxyHost, proxyPort);
			client.getParams().setAuthenticationPreemptive(true);
			if (proxyAuthUser != null && !proxyAuthUser.equals("")) {
				client.getState().setProxyCredentials(
						AuthScope.ANY,
						new UsernamePasswordCredentials(proxyAuthUser,
								proxyAuthPassword));
				log("Proxy AuthUser: " + proxyAuthUser);
				log("Proxy AuthPassword: " + proxyAuthPassword);
			}
		}
	    
	}

	/**
	 * log调试
	 * 
	 */
	private static void log(String message) {
		if (DEBUG) {
			log.debug(message);
		}
	}

	/**
	 * Get方式访问URL
	 * @param url  链接地址
	 * @return
	 * @throws SDKException
	 */
    public String get(String url) throws SDKException {
        return get(url, "UTF-8");
    }
    
    /**
     * Get方式访问URL
     * @param url   链接地址
     * @param urlEncoding   字符编码
     * @return
     * @throws SDKException
     */
    public String get(String url, String urlEncoding) throws SDKException {
        return get(url, urlEncoding, false);
    }
    
    /**
     * Get方式访问URL
     * @param url   链接地址
     * @param withUcHeader  是否在header中携带UC标签
     * @return
     * @throws SDKException
     */
    public String get(String url, boolean withUcHeader) throws SDKException {
        return get(url, "UTF-8", withUcHeader);
    }
    
    /**
     * Get方式访问URL
     * @param url   链接地址
     * @param urlEncoding   字符编码
     * @param withUcHeader  是否在header中携带UC标签
     * @return
     * @throws SDKException
     */
    public String get(String url, String urlEncoding, boolean withUcHeader) throws SDKException {
        GetMethod getMethod = new GetMethod(url);
        return getResponse(getMethod, urlEncoding, withUcHeader);
    }
 

    /**
     * POST方式发送实体请求URL
     * @param url   链接地址
     * @param body  字符编码
     * @return
     * @throws SDKException
     */
    public String post(String url, String body) throws SDKException {
        return post(url, body, "UTF-8");
    }
    
    /**
     * POST方式发送实体请求URL
     * @param url   链接地址
     * @param body  字符编码
     * @param urlEncoding
     * @return
     * @throws SDKException
     */
    public String post(String url, String body, String urlEncoding) throws SDKException {
        return post(url, body, urlEncoding, false);
    }
    
    /**
     * POST方式发送实体请求URL
     * @param url   链接地址
     * @param body  实体内容
     * @param withUcHeader  是否在header中携带UC标签
     * @return
     * @throws SDKException
     */
    public String post(String url, String body, boolean withUcHeader) throws SDKException {
        return post(url, body, "UTF-8", withUcHeader);
    }
    
    /**
     * POST方式发送实体请求URL
     * @param url   链接地址
     * @param body  实体内容
     * @param urlEncoding   字符编码
     * @param withUcHeader  是否在header中携带UC标签
     * @return
     * @throws SDKException
     */
    public String post(String url, String body, String urlEncoding, boolean withUcHeader) throws SDKException {
        PostMethod postMethod = new PostMethod(url);
        RequestEntity entityBody;
        try {
            entityBody = new StringRequestEntity(body, "application/json", urlEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new SDKException("post body can't be encode.");
        }
        postMethod.setRequestEntity(entityBody);
        return getResponse(postMethod, urlEncoding, withUcHeader);
    }
    

	public String getResponse(HttpMethod method, String urlEncoding, boolean withUcHeader) throws SDKException {
		int responseCode = -1;
		try {
			if (withUcHeader) {
			    method.addRequestHeader("User-Agent", "UCSDK");
			}
			//Header[] reqHeader = method.getRequestHeaders();
			//for (Header header : reqHeader) {
            //    log(header.getName() + ":" + header.getValue());
            //}
			
			//若请求失败，不发起重试
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
			client.executeMethod(method);
			responseCode = method.getStatusCode();
			
			//Header[] resHeader = method.getResponseHeaders();
			//for (Header header : resHeader) {
			//	log(header.getName() + ":" + header.getValue());
			//}
			//读取内容 
			byte[] responseBody = method.getResponseBody();
			String response = new String(responseBody, urlEncoding);
			if(DEBUG){
			    if(method instanceof PostMethod){
			        log("Request:" + method.getURI() + ",BODY:" + (((StringRequestEntity) (((PostMethod) method).getRequestEntity())).getContent()) + ",Response StatusCode:" + String.valueOf(responseCode) +",Response:" + response);
			    }
			    else if(method instanceof GetMethod){
			        log("Request:" + method.getURI() + ",Query:" + method.getQueryString() + ",Response StatusCode:" + String.valueOf(responseCode) +",Response:" + response);
			    }
			}

			if (responseCode != OK){
				throw new SDKException(getCause(responseCode), method.getStatusCode());
			}
			return response;

		} catch (IOException ioe) {
			throw new SDKException(ioe.getMessage(), ioe, responseCode);
		} finally {
		    //important:无论成功、失败 都必须关闭
			method.releaseConnection();
		}

	}

	/*
	 * 对parameters进行encode处理
	 */
	public static String encodeParameters(Map<String, String> postParams) {
	    Iterator<Entry<String, String>> itreator = postParams.entrySet().iterator();
		StringBuilder sb = new StringBuilder();
		boolean markedFirst = false;
		while(itreator.hasNext()){
		    Entry<String, String> entry = itreator.next();
		    if(markedFirst){
		        sb.append("&");
		    }
		    try {
                sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                markedFirst = true;
            } catch (java.io.UnsupportedEncodingException neverHappen) {
            }
		    
		}
		return sb.toString();
	}

	private static String getCause(int statusCode) {
		String cause = null;
		switch (statusCode) {
		case NOT_MODIFIED:
			break;
		case BAD_REQUEST:
			cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
			break;
		case NOT_AUTHORIZED:
			cause = "Authentication credentials were missing or incorrect.";
			break;
		case FORBIDDEN:
			cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
			break;
		case NOT_FOUND:
			cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
			break;
		case INTERNAL_SERVER_ERROR:
			cause = "Something is broken.  Please post to the group so the 9game sdk team can investigate.";
			break;
		case BAD_GATEWAY:
			cause = "The 9game sdk servers is down or being upgraded.";
			break;
		case SERVICE_UNAVAILABLE:
			cause = "Service Unavailable: The 9game sdk servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
			break;
		default:
			cause = "";
		}
		return statusCode + ":" + cause;
	}

	public static void main(String[] args){
	    try {
	        SDKHttpClient sdkClient = new SDKHttpClient();
            String response = sdkClient.get("https://www.baidu.com/404.html", true);
            System.out.println(response);
        } catch (SDKException e) {
            e.printStackTrace();
        }
	}
	
}
