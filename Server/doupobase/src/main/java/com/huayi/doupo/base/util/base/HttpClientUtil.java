package com.huayi.doupo.base.util.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient工具类
 * Http提交有get/post/put/delete四种方式
 * 外观模式的使用,不用手动管理连接和资源回收
 * @author mp
 * @date 2013-12-26 下午8:28:00
 */
public class HttpClientUtil {
	
	/**
	 * http get方法
	 * @author mp
	 * @date 2014-9-23 下午3:06:34
	 * @param uri
	 * @param timeout 超时,单位:毫秒
	 * @param keepAlive
	 * @param responseHandler
	 * @return
	 * @throws IOException
	 * @Description
	 */
	public final static < T > T httpGet( String uri , int timeout , boolean keepAlive , ResponseHandler< T > responseHandler ) throws IOException{
		return Request.Get( uri ).connectTimeout( timeout ).socketTimeout( timeout ).setHeader( "Connection" , keepAlive ? "Keep-Alive" : "Close" ).execute().handleResponse( responseHandler ) ;
	}
	
	/**
	 * http get方法
	 * @author mp
	 * @date 2014-9-23 下午3:07:07
	 * @param uri
	 * @param timeout
	 * @param keepAlive
	 * @return
	 * @throws IOException
	 * @Description
	 */
	public final static String httpGet( String uri , int timeout , boolean keepAlive ) throws IOException{
		return Request.Get( uri ).connectTimeout( timeout ).socketTimeout( timeout ).setHeader( "Connection" , keepAlive ? "Keep-Alive" : "Close" ).execute().returnContent().asString() ;
	}
	
	/**
	 * http post方法
	 * @author mp
	 * @date 2014-9-23 下午3:07:15
	 * @param uri
	 * @param timeout
	 * @param keepAlive
	 * @return
	 * @throws IOException
	 * @Description
	 */
	public final static String httpPost( String uri , int timeout , boolean keepAlive ) throws IOException{
		return Request.Post( uri ).connectTimeout( timeout ).socketTimeout( timeout ).setHeader( "Connection" , keepAlive ? "Keep-Alive" : "Close" ).execute().returnContent().asString() ;
	}
	
	/**
	 * http post方法
	 * @author mp
	 * @date 2014-9-23 下午3:07:25
	 * @param uri
	 * @param timeout
	 * @param keepAlive
	 * @param responseHandler
	 * @return
	 * @throws IOException
	 * @Description
	 */
	public final static < T > T httpPost( String uri , int timeout , boolean keepAlive , ResponseHandler< T > responseHandler ) throws IOException{
		return Request.Post( uri ).connectTimeout( timeout ).socketTimeout( timeout ).setHeader( "Connection" , keepAlive ? "Keep-Alive" : "Close" ).execute().handleResponse( responseHandler ) ;
	}
	
	/**
	 * http put方法
	 * @author mp
	 * @date 2014-9-23 下午3:07:33
	 * @param uri
	 * @param timeout
	 * @param keepAlive
	 * @return
	 * @throws IOException
	 * @Description
	 */
	public final static String httpPut( String uri , int timeout , boolean keepAlive ) throws IOException{
		return Request.Put( uri ).connectTimeout( timeout ).socketTimeout( timeout ).setHeader( "Connection" , keepAlive ? "Keep-Alive" : "Close" ).execute().returnContent().asString() ;
	}
	
	/**
	 * http put方法
	 * @author mp
	 * @date 2014-9-23 下午3:07:42
	 * @param uri
	 * @param timeout
	 * @param keepAlive
	 * @param responseHandler
	 * @return
	 * @throws IOException
	 * @Description
	 */
	public final static < T > T httpPut( String uri , int timeout , boolean keepAlive , ResponseHandler< T > responseHandler ) throws IOException{
		return Request.Put( uri ).connectTimeout( timeout ).socketTimeout( timeout ).setHeader( "Connection" , keepAlive ? "Keep-Alive" : "Close" ).execute().handleResponse( responseHandler ) ;
	}
	
	/**
	 * http delete方法
	 * @author mp
	 * @date 2014-9-23 下午3:07:50
	 * @param uri
	 * @param timeout
	 * @param keepAlive
	 * @return
	 * @throws IOException
	 * @Description
	 */
	public final static String httpDelete( String uri , int timeout , boolean keepAlive ) throws IOException{
		return Request.Delete( uri ).connectTimeout( timeout ).socketTimeout( timeout ).setHeader( "Connection" , keepAlive ? "Keep-Alive" : "Close" ).execute().returnContent().asString() ;
	}
	
	/**
	 * http delete方法
	 * @author mp
	 * @date 2014-9-23 下午3:08:02
	 * @param uri
	 * @param timeout
	 * @param keepAlive
	 * @param responseHandler
	 * @return
	 * @throws IOException
	 * @Description
	 */
	public final static < T > T httpDelete( String uri , int timeout , boolean keepAlive , ResponseHandler< T > responseHandler ) throws IOException{
		return Request.Delete( uri ).connectTimeout( timeout ).socketTimeout( timeout ).setHeader( "Connection" , keepAlive ? "Keep-Alive" : "Close" ).execute().handleResponse( responseHandler ) ;
	}
	
	public static String postMapSubmit(String url, String param) throws Exception{
		
		HttpPost httpPost = new HttpPost (url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
			
			//参数列表
	        StringEntity s = new StringEntity(param);
	        s.setContentEncoding("UTF-8");
	        httpPost.setEntity(s);
	        
	        //发送请求
			HttpResponse response = httpclient.execute(httpPost);
			
	        //返回实体
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	        	return EntityUtils.toString(entity, "UTF-8");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        httpPost.releaseConnection();
	        httpclient.close();
		}
		return null;
	}
	
	public static String postSubmit(String url) throws Exception{
		
		HttpPost httpPost = new HttpPost (url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
			//参数列表
/*	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        formparams.add(new BasicNameValuePair("name", "miaopengaaaaa"));
	        
	        //参数编码设置
	        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
	        httpPost.setEntity(uefEntity);*/
			
//			StringEntity s = new StringEntity(param);  
//	        s.setContentEncoding("UTF-8");
//	        s.setContentType("application/json");  
//	        httpPost.setEntity(s);
	        
	        //发送请求
			HttpResponse response = httpclient.execute(httpPost);
			
	        //返回实体
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	        	return EntityUtils.toString(entity, "UTF-8");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        httpPost.releaseConnection();
	        httpclient.close();
		}
		return null;
	}
	
	
	/**
	 * 原始Post提交
	 * @author mp
	 * @date 2013-12-26 下午8:55:11
	 * @param url
	 * @throws Exception
	 * @Description
	 */
	public static String postSubmit(String url, String param) throws Exception{
		
		HttpPost httpPost = new HttpPost (url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
			//参数列表
/*	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        formparams.add(new BasicNameValuePair("name", "miaopengaaaaa"));
	        
	        //参数编码设置
	        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
	        httpPost.setEntity(uefEntity);*/
			
			StringEntity s = new StringEntity(param);  
	        s.setContentEncoding("UTF-8");
	        s.setContentType("application/json");  
	        httpPost.setEntity(s);
	        
	        //发送请求
			HttpResponse response = httpclient.execute(httpPost);
			
	        //返回实体
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	        	return EntityUtils.toString(entity, "UTF-8");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        httpPost.releaseConnection();
	        httpclient.close();
		}
		return null;
	}
	
	/**
	 * 原始Get方式提交
	 * 参数放在url后面
	 * @author mp
	 * @date 2013-12-26 下午8:58:20
	 * @param url
	 * @throws Exception
	 * @Description
	 */
	public static String get(String url) throws Exception{
		
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpclient=HttpClients.createDefault();
		
		try {
		    //发送请求
			CloseableHttpResponse response = httpclient.execute(httpGet);
	        //返回实体
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	        	 return EntityUtils.toString(entity, "UTF-8");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
	        httpGet.releaseConnection();
	        httpclient.close();
		}
		return null;
    }
	
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //		 try {
		//			 URL url = new URL(address);
		//			 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//			 conn.setReadTimeout(timeOut);
		//			 conn.setConnectTimeout(connectTimeOut);
		//			 conn.setRequestMethod("POST");
		//			 conn.setDoInput(true);
		//			 conn.setDoOutput(true);
		//			 OutputStream os = conn.getOutputStream();
		//			 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		//			 writer.write(parameter);
		//			 writer.flush();
		//			 tryClose(writer);
		//			 tryClose(os);
		//			 conn.connect();
		//			 InputStream is = conn.getInputStream();
		//			 return stream2String(is);
		//		 } catch (Exception e) {
		//			 LoggerError.error("请求Http异常@" + address, e);
		//			 return e.getMessage();
		//		 }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *  最近碰到一个网站，是使用cookie来传递参数的。
需要访问两次以上才能取得需要的数据。第一次访问获得cookie，第二次获得数据。

   用httpanalyzer发现浏览器的行为和httpclient的不一样

   浏览器第一次访问时服务器会返回两个set-cookie值，第二次浏览器把这两个cookie拼成一个发给服务器
而使用httpclient的如下代码却不行
hc.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
CookiePolicy.BROWSER_COMPATIBILITY换成别的常量也不行。好像是cookie有很多标准，比如rfc，比如Netscape...
默认的httpclient把两个set-cookie值作为两个cookie发给服务器，结果没有数据

Cookie   xxx=yyy; id=**** 这是浏览器发送的cookie

Cookie   xxx=yyy
Cookie   id=****   这两个是httpclient发的cookie

所以只好自己写个方法
                                //第一次get方法获得cookie
                hc.executeMethod(get);    
                                //设置cookie
                    Cookie [] cookies = hc.getState().getCookies();
                    if(cookies !=null && cookies.length>0){

                        String cook=cookies[0].getValue();
                        for (int i = 1; i < cookies.length; i++) {
                            cook += "; " + cookies[i].getName() + "=" + cookies[i].getValue();
                        }

                        cookies[0].setValue(cook);
                        HttpState state = new HttpState();
                        state.addCookie(cookies[0]);
                        hc.setState(state);
                    }                
                //第二次post方法取得数据
                hc.executeMethod(post);
	 * 
	 */
	
	
	/**
	 * 
	 * public static void main(String[] args) {
        HTTPClient curl=new HTTPClient("http://test.com/post.php");
//      HTTPClient curl=new HTTPClient("http://typecho.org/archives/1/comment");
//      System.out.println(curl.doGet());
        RequestHeaders headers =curl.getHeaders();
        headers.setReferer("http://typecho.org/archives/1/");
        headers.setHost("typecho.org");
        headers.setHeader("Cookie", "PHPSESSID=e1724452dbcf2fac7e1102140d0c0fb4");
//      System.out.println(headers.getHeaders().get("Content-Length"));
//      Map<String, String> params = new HashMap<String, String>();
//        params.put("author", "测试");
//        params.put("mail", "lajitest@125.com");
//        params.put("text", "test22");
        curl.setQueryString("author=%E6%B5%8B%E8%AF%95&mail=lajitest@125.com&url=&text=typecho+%E5%BE%88%E5%A5%BD");
        System.out.println(curl.doPost(false));
//      for (int i = 0; i < 200; i++) {
//          curl.doPost(true);
//          try {
//              Thread.sleep(1000);
//          } catch (InterruptedException e) {
//          }
//      }
	 * 
	 */
	public static void main(String[] args) {
		try {
			
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("id", "1");
//			paramMap.put("orderId", "12344555");
//			paramMap.put("gameId", "1");
//			paramMap.put("serverId", "1");
//			paramMap.put("channelId", "1");
//			paramMap.put("accountId", "1");
//			paramMap.put("roleId", "15");
//			paramMap.put("rolename", "1");
//			paramMap.put("money", "10");
//			paramMap.put("ctype", "1");
//			paramMap.put("orderform", "121ccc21");
//			paramMap.put("userid", "1");
//			paramMap.put("proxy", "1");
//			paramMap.put("roleLevel", "12");
//			paramMap.put("goodsId", "1");
//			String params = HttpClientUtil.httpBuildQuery(paramMap);
//			System.out.println(postMapSubmit("http://192.168.1.36:8888/30000", params));
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getWeekActivity");
//			paramMap.put("cdk", "TY65EANSN".toLowerCase());
//			paramMap.put("channel", "uc");
//			paramMap.put("serverId", "1");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String ret = postMapSubmit("http://192.168.1.36:50000/50000", params);
			Map<String, String> activityMap = JsonUtil.fromJson(ret, HashMap.class);
			System.out.println(activityMap);
			System.out.println(activityMap.size());
			
//			System.out.println(postSubmit("http://61.135.185.83:80"));
			
			
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("instRecordId", "1");
//			String params = HttpClientUtil.httpBuildQuery(paramMap);
//			System.out.println(postMapSubmit("http://192.168.1.36:40000/30001", params));
			
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("openId", "1");
//			paramMap.put("serverId", "2");
//			String params = HttpClientUtil.httpBuildQuery(paramMap);
//			System.out.println(postMapSubmit("http://192.168.1.36:40000/30002", params));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * map构建参数
	 * @author mp
	 * @date 2015-7-6 上午11:16:10
	 * @param data
	 * @return
	 * @Description
	 */
	public static String httpBuildQuery(Map<String, String> data) {                
		if (data.size() < 1) {                                                       
			return "";                                                                 
		}                                                                            
		String ret = "";                                                             
		String k, v;                                                                 
		Iterator<String> iterator = data.keySet().iterator();                        
		while (iterator.hasNext()) {                                                 
			k = iterator.next();                                                       
			v = data.get(k);                                                           
			if (v == null) {                                                           
				v = "";                                                                  
			}                                                                          
			try {                                                                      
				ret += URLEncoder.encode(k, "utf8") + "=" + URLEncoder.encode(v, "utf8");
			} catch (UnsupportedEncodingException e) {                                 
			}                                                                          
			ret += "&";                                                                
		}                                                                            
		return ret.substring(0, ret.length() - 1);                                   
	}                                                                              
	
}
