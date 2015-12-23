package mmo.common.module.clazz.thread.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import mmo.common.module.sdk.token.AClientData;
import mmo.common.module.sdk.token.TokenManager;
import mmo.tools.net.HttpsUtil;
import mmo.tools.thread.runnable.IHttpRequest;
import net.sf.json.JSONObject;

public class ValidateXYRun implements IHttpRequest {
	private final static int    connectTimeOut = 30 * 1000;
	private final static int    timeOut        = 30 * 1000;
	private AClientData         clientData;
	private static final String APP_ID         = "100005276";
	private final static String UID            = "uid";
	private final static String APPID          = "appid";
	private final static String TOKEN          = "token";
	private final static String ret            = "ret";

	public ValidateXYRun() {
		super();
	}

	public void setClientData(AClientData clientData) {
		this.clientData = clientData;
	}

	@Override
	public void run() {
		URL url = null;
		try {
			url = new URL("http://passport.xyzs.com/checkLogin.php");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(timeOut);
			conn.setConnectTimeout(connectTimeOut);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			Map<String, String> parameter = new HashMap<String, String>();
			parameter.put(UID, clientData.getUserid());
			parameter.put(APPID, APP_ID);
			parameter.put(TOKEN, clientData.getToken());

			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(HttpsUtil.httpBuildQuery(parameter));
			writer.flush();
			tryClose(writer);
			tryClose(os);
			conn.connect();

			InputStream is = conn.getInputStream();
			String result = stream2String(is);
			if (result != null) {
				JSONObject jsonObject = JSONObject.fromObject(result);
				int status = jsonObject.getInt(ret);
				switch (status) {
					case 0: {
						clientData.setUsername(clientData.getUserid());
						TokenManager.getInstance().addToken(clientData.getToken(), clientData.getUserid(), clientData.getUsername(),
						        clientData.getChannelSub());
						clientData.userChannelLogin();
						break;
					}
					case 2: {
						clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, "uid 不能为空");
						break;
					}
					case 20: {
						clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, " 缺少APPID");
						break;
					}
					case 997: {
						clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, "Token 过期");
						break;
					}
					case 999: {
						clientData.validateFail(AClientData.RT_4_VALIDATE_FAIL, "验证码校验失败");
						break;
					}
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
}
