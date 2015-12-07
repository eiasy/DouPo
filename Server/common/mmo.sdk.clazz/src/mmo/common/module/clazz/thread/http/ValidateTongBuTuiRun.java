package mmo.common.module.clazz.thread.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mmo.common.module.sdk.token.AClientData;
import mmo.common.module.sdk.token.TokenManager;
import mmo.tools.thread.runnable.IHttpRequest;

public class ValidateTongBuTuiRun implements IHttpRequest {
	private static final String STATUS_SUCCESS = "1";
	private static final String ITOOLS_URL     = "http://tgi.tongbu.com/check.aspx?k=";
	private AClientData         clientData;

	public ValidateTongBuTuiRun() {
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
			sb.append(ITOOLS_URL).append(clientData.getToken());
			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User_Agent", "TONGBU");
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
			if (STATUS_SUCCESS.equalsIgnoreCase(result)) {
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
