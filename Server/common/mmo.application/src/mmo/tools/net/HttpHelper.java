package mmo.tools.net;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mmo.tools.log.LoggerError;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

/**
 * Http请求访问公共类
 * 
 */
public class HttpHelper {
	private static HttpHelper instance                = new HttpHelper();
	/** 成功请求状态 */
	private static int        SUCCESS_RESPONSE_STATUS = 200;

	/** 连接超时时长 */
	private static final int  CONNECTION_TIMEOUT      = 1000 * 30;

	/** 请求超时时长 */
	private static final int  METHOD__TIMEOUT         = 1000 * 30;

	/** http协议端口号 */
	private static final int  HTTP_PORT               = 80;

	/** https协议端口号 */
	private static final int  HTTPS_PORT              = 443;

	/** HttpClient对象 */
	private HttpClient        httpClient;

	public static final HttpHelper getInstance() {
		if (instance == null) {
			instance = new HttpHelper();
		}
		return instance;
	}

	/**
	 * 构造函数
	 */
	private HttpHelper() {
		httpClient = getHttpClient();
	}

	/**
	 * 发送Http请求，返回文本形式的请求响应
	 * 
	 * @param url
	 *            url地址
	 * @return
	 */
	public String doGet(String url) {
		HttpGet request = null;
		try {
			request = new HttpGet(url);
			request.setHeader("Accept-Language", "zh-cn,zh");
			request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			HttpResponse stringResponse = getHttpClient().execute(request);// 进行Http请求

			if (!isResponseSuccessStatus(stringResponse)) {// 判断请求是否成功
				return null;
			}
			String result = parseHttpResponse2String(stringResponse);
			return result;
		} catch (Exception e) {
			if (request != null) {
				request.abort();
			}
			httpClient.getConnectionManager().shutdown();
			httpClient = null;
			LoggerError.error("HttpHelper get", e);
			return null;
		}
	}

	/**
	 * 发送Http请求，返回文本形式的请求响应
	 * 
	 * @param url
	 *            url地址
	 * @return
	 */
	// public String doGetSSL(String url) {
	// HttpGet request = null;
	// try {
	// request = new HttpGet(url);
	// request.setHeader("Accept-Language", "zh-cn,zh");
	// request.setHeader("Content-Type", "application/x-www-form-urlencoded");
	// HttpResponse stringResponse = getHttpClient().execute(request);// 进行Http请求
	//
	// if (!isResponseSuccessStatus(stringResponse)) {// 判断请求是否成功
	// return null;
	// }
	// String result = parseHttpResponse2String(stringResponse);
	//
	// SSLSocketFactory ssf = new SSLSocketFactory( SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	// SchemeRegistry registry = new SchemeRegistry();
	// registry.register(new Scheme("https", 443, ssf));
	// ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
	// return new DefaultHttpClient(mgr, base.getParams());
	//
	//
	// return result;
	// } catch (Exception e) {
	// if (request != null) {
	// request.abort();
	// }
	// httpClient.getConnectionManager().shutdown();
	// httpClient = null;
	// LoggerError.loggerError.error("HttpHelper get", e);
	// return null;
	// }
	// }

	/**
	 * 发送Http请求，返回文本形式的请求响应
	 * 
	 * @param url
	 *            url地址
	 * @return
	 */
	public String doGet(String url, String encode) {
		HttpGet request = null;
		try {
			request = new HttpGet(url);
			request.setHeader("Accept-Language", "zh-cn,zh");
			request.setHeader("Accept-Charset", encode);
			request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			HttpResponse stringResponse = getHttpClient().execute(request);// 进行Http请求

			if (!isResponseSuccessStatus(stringResponse)) {// 判断请求是否成功
				return null;
			}
			return parseHttpResponse2String(stringResponse);
		} catch (Exception e) {
			if (request != null) {
				request.abort();
			}
			httpClient.getConnectionManager().shutdown();
			httpClient = null;
			LoggerError.error("HttpHelper get", e);
			return null;
		}
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的内容
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的内容
	 */
	public String doPost(String url, Map<String, Object> param) {
		HttpResponse stringResponse = null;
		HttpPost httppost = null;
		try {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			if (param != null) {
				Set<String> set = param.keySet();
				Iterator<String> iterator = set.iterator();
				while (iterator.hasNext()) {
					Object key = iterator.next();
					Object value = param.get(key);
					formparams.add(new BasicNameValuePair(key.toString(), value.toString()));
				}
			}

			UrlEncodedFormEntity postentity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);

			HttpClient httpclient = HttpHelper.getInstance().getHttpClient();
			httppost = new HttpPost(url);
			httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			httppost.setEntity(postentity);
			stringResponse = httpclient.execute(httppost);

			if (!isResponseSuccessStatus(stringResponse)) {// 判断请求是否成功
				return null;
			}
			return parseHttpResponse2String(stringResponse);
		} catch (Exception e) {
			httppost.abort();
			httpClient.getConnectionManager().shutdown();
			httpClient = null;
			LoggerError.error("HttpHelper get", e);
			return null;
		}
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的内容
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的内容
	 */
	public String doPost(String url, String body) {
		HttpResponse stringResponse = null;
		HttpPost httppost = null;
		try {
			HttpClient httpclient = HttpHelper.getInstance().getHttpClient();
			httppost = new HttpPost(url);
			httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			httppost.setEntity(new ByteArrayEntity(body.getBytes("UTF-8")));
			stringResponse = httpclient.execute(httppost);

			if (!isResponseSuccessStatus(stringResponse)) {// 判断请求是否成功
				return null;
			}
			return parseHttpResponse2String(stringResponse);
		} catch (Exception e) {
			httppost.abort();
			httpClient.getConnectionManager().shutdown();
			httpClient = null;
			LoggerError.error("HttpHelper post", e);
			return null;
		}
	}

	/**
	 * 初始化HttpClient对象
	 * 
	 * @return HttpClient对象
	 */
	public HttpClient getHttpClient() {
		if (httpClient == null) {
			try {
				// ThreadSafeClientConnManager\PoolingClientConnectionManager
				PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(getSchemeRegistry());
				// pccm.setDefaultMaxPerRoute(20);
				httpClient = new DefaultHttpClient(pccm, prepareHttpParams());
			} catch (Exception e) {
				e.printStackTrace();
				httpClient = new DefaultHttpClient();
			}
		}
		return httpClient;
	}

	/**
	 * 设置Http请求参数
	 * 
	 * @return Http请求参数
	 */
	private HttpParams prepareHttpParams() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, METHOD__TIMEOUT);

		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, Consts.UTF_8.name());
		return params;
	}

	/**
	 * 设置Http协议信息
	 * 
	 * @return Http协议对象
	 * @throws Exception
	 */
	private SchemeRegistry getSchemeRegistry() throws Exception {
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("HTTP", HTTP_PORT, PlainSocketFactory.getSocketFactory()));
		registry.register(new Scheme("HTTPS", HTTPS_PORT, SSLSocketFactory.getSocketFactory()));
		return registry;
	}

	/**
	 * 判断Http请求是否成功
	 * 
	 * @param response
	 * @return 成功标识
	 */
	private boolean isResponseSuccessStatus(HttpResponse response) {
		return SUCCESS_RESPONSE_STATUS == response.getStatusLine().getStatusCode();
	}

	/**
	 * 解析Http响应结果，读出Stream流将结果转换为文本
	 * 
	 * @param response
	 * @return InputStream
	 * @throws ParseResponseFailException
	 * @throws IOException
	 */
	private String parseHttpResponse2String(HttpResponse response) throws Exception {
		HttpEntity entity = response.getEntity();
		checkEntity(entity);
		return readInputStream2String(getInputStream(entity), getContentCharSet(entity));
	}

	/**
	 * 检查HttpEntity是否正确
	 * 
	 * @param entity
	 * @throws Exception
	 */
	private void checkEntity(HttpEntity entity) throws Exception {
		if (null == entity || entity.getContentLength() > Integer.MAX_VALUE) {
			throw new Exception("entity is null or the max length exceed Integer.MAX_VALUE");
		}
	}

	/**
	 * 获取页面字符编码
	 * 
	 * @param entity
	 *            网络访问实体
	 * @return 页面字符编码
	 */
	public String getContentCharSet(final HttpEntity entity) {
		// 默认使用UTF-8编码
		String charset = Consts.UTF_8.name();
		if (null != entity.getContentType()) {
			HeaderElement[] values = entity.getContentType().getElements();

			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (null != param) {
					charset = param.getValue();
				} else if (null != entity.getContentEncoding()) {
					values = entity.getContentEncoding().getElements();
					if (null != values && values.length > 0) {
						param = values[0].getParameterByName("charset");
						if (null != param) {
							charset = param.getValue();
						}
					}
				}
			}
		}
		return charset;
	}

	/**
	 * 从Http响应结果出读出inputstream输出流
	 * 
	 * @param entity
	 * @return inputstream输出流
	 * @throws Exception
	 */
	private InputStream getInputStream(HttpEntity entity) throws Exception {
		try {
			BufferedHttpEntity wrappedEntity = new BufferedHttpEntity(entity);
			return wrappedEntity.getContent();
		} catch (Exception e) {
			LoggerError.error("getInputStream exception", e);
			throw new Exception("getInputStream exception");
		}
	}

	/**
	 * 将inputStream流转换为文本信息
	 * 
	 * @param inputStream
	 * @param charset
	 *            字符编码
	 * @return 文本信息
	 * @throws Exception
	 */
	private String readInputStream2String(InputStream inputStream, String charset) throws Exception {
		try {
			return IOUtils.toString(inputStream, charset);
		} catch (IOException e) {
			LoggerError.error("readInputStream2String exception", e);
			throw new Exception("readInputStream2String exception");
		}
	}
}
