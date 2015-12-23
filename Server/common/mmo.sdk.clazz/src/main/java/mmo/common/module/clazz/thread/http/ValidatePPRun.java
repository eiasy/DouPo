package mmo.common.module.clazz.thread.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import mmo.common.module.sdk.token.AClientData;
import mmo.common.module.sdk.token.TokenManager;
import mmo.tools.thread.runnable.IHttpRequest;
import net.sf.json.JSONObject;

public class ValidatePPRun implements IHttpRequest {
	private AClientData clientData;

	public ValidatePPRun() {
		super();
	}

	public void setClientData(AClientData clientData) {
		this.clientData = clientData;
	}

	@Override
	public void run() {
		byte[] token_key = clientData.getToken().getBytes();
		InputStream ppserver_InputStream = null;
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL("http://passport_i.25pp.com:8080/index?tunnel-command=2852126756");

			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User_Agent", "25PP");
			conn.setRequestProperty("Connection", "close");
			// 长度是实体的二进制长度
			conn.setRequestProperty("Content-Length", String.valueOf(token_key.length));
			conn.getOutputStream().write(token_key);
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
			ppserver_InputStream = conn.getInputStream();
			if (ppserver_InputStream != null) {
				byte ppsbuff[] = readStream(ppserver_InputStream);
				String recvjson = new String(ppsbuff, "UTF-8");
				recvjson = "{" + recvjson + "}";
				JSONObject jsonObject = JSONObject.fromObject(recvjson);

				int status = jsonObject.getInt("status");
				if (status == 0) {
					clientData.setUsername(jsonObject.getString("username"));
					clientData.setUserid(jsonObject.getInt("userid") + "");
					TokenManager.getInstance().addToken(clientData.getToken(), clientData.getUserid(), clientData.getUsername(),
					        clientData.getChannelSub());
					clientData.userChannelLogin();
				} else {
					clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, AClientData.MSG_4_VALIDATE_FAIL);
				}
			} else {
				clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, AClientData.MSG_4_VALIDATE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取流
	 * 
	 * @param inStream
	 * @return 字节数组
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		/* while */if ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		return outSteam.toByteArray();
	}
}
