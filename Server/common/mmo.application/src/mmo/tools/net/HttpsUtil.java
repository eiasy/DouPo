package mmo.tools.net;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import mmo.tools.log.LoggerError;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpsUtil {
	private final static int connectTimeOut = 30 * 1000;
	private final static int timeOut        = 30 * 1000;

	public final static String request(String address, String parameter) {

		HttpPost httpPost = new HttpPost(address);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			// 参数列表
			StringEntity s = new StringEntity(parameter);
			s.setContentEncoding("UTF-8");
			httpPost.setEntity(s);

			// 发送请求
			HttpResponse response = httpclient.execute(httpPost);

			// 返回实体
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			LoggerError.error("请求Http异常@" + address, e);
			return e.getMessage();
		} finally {
			httpPost.releaseConnection();
			try {
				httpclient.close();
			} catch (IOException e) {
				LoggerError.error("请求Http异常@" + address, e);
			}
		}
	}

	public final static String request(String address, byte[] data) {
		HttpPost httpPost = new HttpPost(address);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			ByteArrayEntity s = new ByteArrayEntity(data);
			httpPost.setEntity(s);

			// 发送请求
			HttpResponse response = httpclient.execute(httpPost);

			// 返回实体
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			LoggerError.error("请求Http异常@" + address, e);
			return e.getMessage();
		} finally {
			httpPost.releaseConnection();
			try {
				httpclient.close();
			} catch (IOException e) {
				LoggerError.error("请求Http异常@" + address, e);
			}
		}
	}

	public final static String requestPost(String address, String parameter) {
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(timeOut);
			conn.setConnectTimeout(connectTimeOut);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(parameter);
			writer.flush();
			tryClose(writer);
			tryClose(os);
			conn.connect();
			InputStream is = conn.getInputStream();
			return stream2String(is);
		} catch (Exception e) {
			LoggerError.error("请求Http异常@" + address, e);
			return e.getMessage();
		}
	}

	public final static String requestPost(String address, byte[] data) {
		BufferedOutputStream urlBufOus = null;
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(timeOut);
			conn.setConnectTimeout(connectTimeOut);
			conn.setRequestMethod("POST");
			conn.setUseCaches(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			urlBufOus = new BufferedOutputStream(os);
			urlBufOus.write(data);
			urlBufOus.flush();
			tryClose(urlBufOus);
			tryClose(os);
			conn.connect();
			InputStream is = conn.getInputStream();
			return stream2String(is);
		} catch (Exception e) {
			LoggerError.error("请求Http异常@" + address, e);
			return e.getMessage();
		}

	}

	private static final String stream2String(InputStream is) {
		try {
			BufferedReader br = null;
			String s;
			br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null)
				sb.append(line);
			s = sb.toString();
			tryClose(br);
			return s;
		} catch (Exception e) {
			LoggerError.error("读取数据错误", e);
			return e.getMessage();
		}
	}

	private static final void tryClose(Reader reader) {
		try {
			if (reader != null) {
				reader.close();
				reader = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final void tryClose(OutputStream os) {
		try {
			if (os != null) {
				os.close();
				os = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final void tryClose(Writer writer) {
		try {
			if (writer != null) {
				writer.close();
				writer = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static String request(String address) {
		HttpGet httpGet = new HttpGet(address);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			// 发送请求
			CloseableHttpResponse response = httpclient.execute(httpGet);
			// 返回实体
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			LoggerError.error("请求Http异常@" + address, e);
			return e.getMessage();
		} finally {
			httpGet.releaseConnection();
			try {
				httpclient.close();
			} catch (IOException e1) {
				LoggerError.error("请求Http异常@" + address, e1);
			}
		}
	}

	/**
	 * 
	 * @param url
	 * @param data
	 * @param ignoreSSL
	 * @return
	 * @throws IOException
	 */
	public static String requestUrl(String url, HashMap<String, String> data, Boolean ignoreSSL) throws IOException {

		if (ignoreSSL) {
			_ignoreSSL();
		}

		HttpURLConnection conn;
		try {
			URL requestUrl = new URL(url);
			conn = (HttpURLConnection) requestUrl.openConnection();
		} catch (MalformedURLException e) {
			return e.getMessage();
		}

		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		conn.setDoInput(true);
		conn.setDoOutput(true);

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
		writer.write(httpBuildQuery(data));
		writer.flush();
		writer.close();

		String line;
		BufferedReader bufferedReader;
		StringBuilder sb = new StringBuilder();
		InputStreamReader streamReader = null;
		try {
			streamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
		} catch (IOException e) {
			streamReader = new InputStreamReader(conn.getErrorStream(), "UTF-8");
		} finally {
			if (streamReader != null) {
				bufferedReader = new BufferedReader(streamReader);
				sb = new StringBuilder();
				while ((line = bufferedReader.readLine()) != null) {
					sb.append(line);
				}
			}
		}
		return sb.toString();
	}

	private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
		                                                       @Override
		                                                       public boolean verify(String s, SSLSession sslsession) {
			                                                       return true;
		                                                       }
	                                                       };

	/**
	 * 蹇界暐SSL
	 */
	private static void _ignoreSSL() {
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
		} catch (KeyManagementException ex) {
			Logger.getLogger(HttpsUtil.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(HttpsUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String httpBuildQuery(Map<String, String> data) {
		return httpBuildQuery(data, true);
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String httpBuildQuery(Map<String, String> data, boolean encode) {
		if (data.size() < 1) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		String k, v;
		Iterator<String> iterator = data.keySet().iterator();
		int index = 0;
		while (iterator.hasNext()) {
			if (index++ > 0) {
				sb.append("&");
			}
			k = iterator.next();
			v = data.get(k);
			if (v == null) {
				v = "";
			}
			try {
				if (encode) {
					sb.append(URLEncoder.encode(k, "utf8")).append("=").append(URLEncoder.encode(v, "utf8"));
				} else {
					sb.append(k).append("=").append(v);
				}
			} catch (UnsupportedEncodingException e) {
				LoggerError.error("编码异常", e);
			}

		}
		return sb.toString();
	}

	/**
	 * 
	 * @param params
	 * @param appSecret
	 * @return
	 */
	public static String getSign(HashMap params, String appSecret) {
		Object[] keys = params.keySet().toArray();
		Arrays.sort(keys);
		String k, v;

		String str = "";
		for (int i = 0; i < keys.length; i++) {
			k = (String) keys[i];
			if (k.equals("sign") || k.equals("sign_return")) {
				continue;
			}
			v = (String) params.get(k);

			if (v.equals("")) {
				continue;
			}

			str += v + "#";
		}
		return HttpsUtil.md5(str + appSecret);
	}

	public static String md5(String str) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(str.getBytes("UTF8"));
			byte bytes[] = m.digest();

			for (int i = 0; i < bytes.length; i++) {
				if ((bytes[i] & 0xff) < 0x10) {
					sb.append("0");
				}
				sb.append(Long.toString(bytes[i] & 0xff, 16));
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}

	public static String postMapSubmit(String url, String param) throws Exception {

		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {

			// 参数列表
			StringEntity s = new StringEntity(param);
			s.setContentEncoding("UTF-8");
			httpPost.setEntity(s);

			// 发送请求
			HttpResponse response = httpclient.execute(httpPost);

			// 返回实体
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

}
