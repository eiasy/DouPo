package mmo.common.module.clazz.thread.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mmo.common.module.sdk.token.AClientData;
import mmo.common.module.sdk.token.TokenManager;
import mmo.tools.thread.runnable.IHttpRequest;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

public class ValidateItoolsRun implements IHttpRequest {
	private static final String STATUS_SUCCESS = "success";
	private static final String KEY_STATUS     = "status";
	private static final String ITOOLS_URL     = "https://pay.slooti.com/?r=auth/verify&appid=688&sessionid=";
	private static final String STR_SUB        = "appid=688&sessionid=";
	private AClientData         clientData;

	public ValidateItoolsRun() {
		super();
	}

	public void setClientData(AClientData clientData) {
		this.clientData = clientData;
	}

	@Override
	public void run() {
		URL url = null;
		HttpURLConnection conn = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(ITOOLS_URL).append(clientData.getToken()).append("&sign=").append(MD5.getHashString(STR_SUB + clientData.getToken()));
			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User_Agent", "ITOOLS");
			conn.setRequestProperty("Connection", "close");

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
			String status = jsonObject.getString(KEY_STATUS);
			if (STATUS_SUCCESS.equalsIgnoreCase(status)) {
				clientData.setUsername(clientData.getUserid());
				TokenManager.getInstance().addToken(clientData.getToken(), clientData.getUserid(), clientData.getUsername(),
				        clientData.getChannelSub());
				clientData.userChannelLogin();
			} else {
				clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, AClientData.MSG_4_VALIDATE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
