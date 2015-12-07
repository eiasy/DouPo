package mmo.tools.thread.runnable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;

import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;

abstract public class RequestHttpRun2 implements IHttpRequest {
	private final static int connectTimeOut = 30 * 1000;
	private final static int timeOut        = 30 * 1000;
	protected String         parameter;

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public RequestHttpRun2() {

	}

	public RequestHttpRun2(String parameter) {
		this.parameter = parameter;
	}

	protected String request(String address) {
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
			if (parameter != null) {
				writer.write(parameter);
			}
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

	/**
	 * 获取流中的字符串
	 * 
	 * @param is
	 * @return
	 */
	private final String stream2String(InputStream is) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new java.io.InputStreamReader(is));
			String line = "";
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tryClose(br);
		}
		return "";
	}

	/**
	 * 关闭writer
	 * 
	 * @param writer
	 */
	private final void tryClose(java.io.Writer writer) {
		try {
			if (null != writer) {
				writer.close();
				writer = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭Reader
	 * 
	 * @param reader
	 */
	private final void tryClose(java.io.Reader reader) {
		try {
			if (null != reader) {
				reader.close();
				reader = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭输出流
	 * 
	 * @param os
	 */
	private final void tryClose(OutputStream os) {
		try {
			if (null != os) {
				os.close();
				os = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void sendMessage(IoSession session, String message) {
		if (session != null) {
			HttpResponseMessage response = new HttpResponseMessage();
			response.setContentType("text/plain;charset=utf-8");
			response.appendBody(message.toString());
			session.write(response).addListener(IoFutureListener.CLOSE);
		}
	}
}
