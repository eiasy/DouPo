package mmo.common.module.clazz.callback.chukong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.module.clazz.thread.http.A20031_TokenDataRun;
import mmo.common.module.sdk.server.ThreadManager;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import net.sf.json.JSONObject;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class ChukongManager {

	private final static String F_STATUS = "status";
	private final static String userAgent = "px v1.0";
	private final static int connectTimeOut = 30 * 1000;
	private final static int timeOut = 30 * 1000;

	public final String getSignForAnyValid(HttpRequestMessage request) {
		List<String> requestParams = request.getParameterNames();// 获得所有的参数名
		List<String> params = new ArrayList<String>(requestParams);
		sortParamNames(params);
		String paramValues = "";
		for (String param : params) {// 拼接参数值
			if (param.equals("sign")) {
				continue;
			}
			String paramValue = request.getParameter(param);
			if (paramValue != null) {
				paramValues += paramValue;
			}
		}
		String md5Values = MD5Encode(paramValues);
		md5Values = MD5Encode(md5Values.toLowerCase() + ProjectCofigs.getParameter("chukong_private_key")).toLowerCase();
		return md5Values;
	}

	// MD5编码
	public String MD5Encode(String sourceStr) {
		String signStr = null;
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if (md5Byte != null) {
				signStr = HexBin.encode(md5Byte);
			}
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return signStr;

	}

	private void sortParamNames(List<String> paramNames) {
		Collections.sort(paramNames, new Comparator<String>() {
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
	}

	public final void callback(IoSession session, HttpRequestMessage request) {
		JSONObject jsonResult = new JSONObject();
		try {
			List<String> requestParams = request.getParameterNames();// 获得所有的参数名
			HashMap<String, String> params = new HashMap<String, String>();
			String value = null;
			for (String key : requestParams) {
				value = request.getParameter(key);
				if (value != null && value.length() > 0) {
					params.put(key, request.getParameter(key));
				}
			}
			// 检测必要参数
			if (parametersIsset(params)) {
				LoggerError.messageLog.warn("CHUKONG#parameter not complete");
				sendToClient(session, "parameter not complete");
				return;
			}

			String queryString = HttpsUtil.httpBuildQuery(params);
			LoggerError.warn(("url = " + ProjectCofigs.getParameter("chukong_login_url")));
			URL url = new URL(ProjectCofigs.getParameter("chukong_login_url"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", userAgent);
			conn.setReadTimeout(timeOut);
			conn.setConnectTimeout(connectTimeOut);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(queryString);
			writer.flush();
			tryClose(writer);
			tryClose(os);
			conn.connect();

			InputStream is = conn.getInputStream();
			String result = stream2String(is);
			LoggerError.warn("result = " + result);

			JSONObject jsonObj = JSONObject.fromObject(result);
			if (jsonObj.containsKey(F_STATUS)) {
				if ("ok".equalsIgnoreCase(jsonObj.getString(F_STATUS))) {
					long time = System.currentTimeMillis();
					String type = "anysdk";
					Map<String, String> message = new HashMap<String, String>();
					message.put("data", result);
					message.put("time", time + "");
					message.put("type", type);
					ThreadManager.requestHttp("account", new A20031_TokenDataRun(session, message));
					return;
				} else {
					jsonResult.put("state", "fail");
					jsonResult.put("message", "验证失败[1]");
					LoggerError.messageLog.warn("CHUKONG#STATUS=" + jsonObj.getString(F_STATUS));
				}
			} else {
				jsonResult.put("state", "fail");
				jsonResult.put("state", "验证失败[2]");
				LoggerError.messageLog.warn("CHUKONG#NO STATUS");
			}
			sendToClient(session, result);
			return;
		} catch (Exception e) {
			jsonResult.put("state", "fail");
			jsonResult.put("message", "验证失败[3]");
			LoggerError.messageLog.error("CHUKONG#ERROR", e);
		}
		sendToClient(session, jsonResult.toString());
	}

	/**
	 * check needed parameters isset 检查必须的参数 channel uapi_key：渠道提供给应用的app_id或app_key（标识应用的id） uapi_secret：渠道提供给应用的app_key或app_secret（支付签名使用的密钥）
	 * 
	 * @param params
	 * @return boolean
	 */
	private final boolean parametersIsset(Map<String, String> params) {
		return !(params.containsKey("channel") && params.containsKey("uapi_key") && params.containsKey("uapi_secret"));
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
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final void sendToClient(IoSession session, String content) {
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain;charset=utf-8");
		response.appendBody(content);
		session.write(response).addListener(IoFutureListener.CLOSE);
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
}
