package mmo.common.module.clazz.thread.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import mmo.common.module.sdk.token.AClientData;
import mmo.common.module.sdk.token.TokenManager;
import mmo.tools.thread.runnable.IHttpRequest;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

public class ValidateTencentRun implements IHttpRequest {
	private final static String accesstoken = "accesstoken";
	private final static String action      = "action";
	private final static String open_id     = "open_id";
	private final static String paytoken    = "paytoken";
	private final static String pf          = "pf";
	private final static String pf_key      = "pf_key";
	/*****************************************************************************************************/
	private final static String timestamp   = "timestamp=";
	private final static String appid       = "&appid=";
	private final static String sig         = "&sig=";
	private final static String openid      = "&openid=";
	private final static String encode      = "&encode=";
	/*****************************************************************************************************/

	private final static int    QQ_APP_ID   = 1104244259;
	private final static String WX_APP_ID   = "wxe6fc33c405f3968f";
	private final static String QQ_APP_KEY  = "X6U6ZZch0ZEXHt8r";
	private final static String WX_APP_KEY  = "652a1e1d7f366fd18da4f95a285c9700";
	private static final String QQ_URL      = "http://msdktest.qq.com/auth/verify_login?";
	private static final String WX_URL      = "http://msdktest.qq.com/auth/check_token?";
	/*****************************************************************************************************/

	private static final String ret         = "ret";
	private AClientData         clientData;

	public ValidateTencentRun() {
		super();
	}

	public void setClientData(AClientData clientData) {
		this.clientData = clientData;
	}

	@Override
	public void run() {
		JSONObject json = JSONObject.fromObject(clientData.getCustomData());
		if (json.getString(action).startsWith("qq")) {
			qqLogin(json);
		} else if (json.getString(action).startsWith("wx")) {
			wxLogin(json);
		} else {
			clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, AClientData.MSG_4_VALIDATE_FAIL + "#ACTION:" + json.getString(action));
		}
	}

	private void qqLogin(JSONObject json) {
		URL url = null;
		HttpURLConnection conn = null;
		try {

			long time = System.currentTimeMillis() / 1000;
			StringBuilder sb = new StringBuilder();
			sb.append(QQ_APP_KEY).append(time);
			String sign = MD5.getHashString(sb.toString());
			sb.setLength(0);
			sb.append(QQ_URL).append(timestamp).append(time);
			sb.append(appid).append(QQ_APP_ID);
			sb.append(sig).append(sign);
			sb.append(openid).append(json.getString(open_id));
			sb.append(encode).append(1);
			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "close");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			JSONObject parameter = new JSONObject();
			parameter.put("appid", QQ_APP_ID);
			parameter.put("openid", json.getString(open_id));
			parameter.put("openkey", json.getString(accesstoken));
			parameter.put("userip", clientData.getIp());
			writer.write(parameter.toString());
			writer.flush();

			String line = null;
			String result = null;
			BufferedReader bufferedReader;
			InputStreamReader streamReader = null;
			sb.setLength(0);
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
					result = sb.toString();
				}
			}
			JSONObject jsonObject = JSONObject.fromObject(result);
			int retValue = jsonObject.getInt(ret);
			if (retValue == 0) {
				clientData.setUsername(json.getString(open_id));
				clientData.setUserid(clientData.getUsername());
				TokenManager.getInstance().addToken(clientData.getToken(), clientData.getUserid(), clientData.getUsername(),
				        clientData.getChannelSub());
				clientData.userChannelLogin();
			} else {
				clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, AClientData.MSG_4_VALIDATE_FAIL + "#" + retValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void wxLogin(JSONObject json) {
		URL url = null;
		HttpURLConnection conn = null;
		try {

			long time = System.currentTimeMillis() / 1000;
			StringBuilder sb = new StringBuilder();
			sb.append(WX_APP_KEY).append(time);
			String sign = MD5.getHashString(sb.toString());
			sb.setLength(0);
			sb.append(WX_URL).append(timestamp).append(time);
			sb.append(appid).append(WX_APP_ID);
			sb.append(sig).append(sign);
			sb.append(openid).append(json.getString(open_id));
			sb.append(encode).append(1);
			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "close");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			JSONObject parameter = new JSONObject();
			parameter.put("openid", json.getString(open_id));
			parameter.put("accessToken", json.getString(accesstoken));
			writer.write(parameter.toString());
			writer.flush();

			String line = null;
			String result = null;
			BufferedReader bufferedReader;
			InputStreamReader streamReader = null;
			sb.setLength(0);
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
					result = sb.toString();
				}
			}
			JSONObject jsonObject = JSONObject.fromObject(result);
			int retValue = jsonObject.getInt(ret);
			if (retValue == 0) {
				clientData.setUsername(json.getString(open_id));
				clientData.setUserid(clientData.getUsername());
				TokenManager.getInstance().addToken(clientData.getToken(), clientData.getUserid(), clientData.getUsername(),
				        clientData.getChannelSub());
				clientData.userChannelLogin();
			} else {
				clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, AClientData.MSG_4_VALIDATE_FAIL + "#" + retValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
