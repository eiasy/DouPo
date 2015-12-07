package mmo.common.module.clazz.thread.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import mmo.common.module.sdk.token.AClientData;
import mmo.common.module.sdk.token.TokenManager;
import mmo.tools.thread.runnable.IHttpRequest;
import net.sf.json.JSONObject;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class Validate91Run implements IHttpRequest {
	private final static String APP_ID         = "116051";
	private final static String ACT            = "4";
	private final static String APP_KEY        = "9af85a8f0a38f248d0c4a513025b37993750b0f917d07660";

	private static final String STATUS_SUCCESS = "1";
	private static final String KEY_ErrorCode  = "ErrorCode";
	private static final String VALIDATE_URL   = "http://service.sj.91.com/usercenter/AP.aspx?AppId=116051&Act=4&Uin=";
	private static final String KEY_SIGN       = "&Sign=";
	private static final String KEY_SESSION_ID = "&SessionID=";
	private AClientData         clientData;

	public Validate91Run() {
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
			sb.append(APP_ID).append(ACT).append(clientData.getUserid()).append(clientData.getToken()).append(APP_KEY);
			String sign = md5(sb.toString());
			sb.setLength(0);
			sb.append(VALIDATE_URL).append(clientData.getUserid());
			sb.append(KEY_SIGN).append(sign);
			sb.append(KEY_SESSION_ID).append(clientData.getToken());
			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User_Agent", "91");
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
			String status = jsonObject.getString(KEY_ErrorCode);
			if (STATUS_SUCCESS.equalsIgnoreCase(status)) {
				clientData.setUsername(clientData.getUserid());
				TokenManager.getInstance().addToken(clientData.getToken(), clientData.getUserid(), clientData.getUsername(),
				        clientData.getChannelSub());
				clientData.userChannelLogin();
			} else {
				clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, AClientData.MSG_4_VALIDATE_FAIL + "#" + status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String md5(String sourceStr) {
		String signStr = "";
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if (md5Byte != null) {
				signStr = HexBin.encode(md5Byte);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signStr;
	}
}
