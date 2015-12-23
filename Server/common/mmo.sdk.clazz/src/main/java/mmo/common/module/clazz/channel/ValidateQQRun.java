package mmo.common.module.clazz.channel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import mmo.common.account.HttpCData;
import mmo.common.http.parameter.HttpParameter;
import mmo.common.module.clazz.thread.http.A20006ChannelLoginRun;
import mmo.common.module.sdk.http.HandRequestRun;
import mmo.common.module.sdk.server.ThreadManager;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

public class ValidateQQRun extends HandRequestRun {
	private final static String accesstoken = "accesstoken";
	private final static String action = "action";
	private final static String open_id = "open_id";
	private final static String paytoken = "paytoken";
	private final static String pf = "pf";
	private final static String pf_key = "pf_key";
	/*****************************************************************************************************/
	private final static String timestamp = "timestamp=";
	private final static String appid = "&appid=";
	private final static String sig = "&sig=";
	private final static String openid = "&openid=";
	private final static String encode = "&encode=";
	/*****************************************************************************************************/
	private static final String ret = "ret";

	@Override
	public void run() {
		LoggerError.warn("action = " + request.getParameter("action"));
		try {
			if (request.getParameter("action").startsWith("qq")) {
				qqLogin();
			} else if (request.getParameter("action").startsWith("wx")) {
				wxLogin();
			} else {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(HttpCData.A20001.result, 1);
				jsonObj.put(HttpCData.A20001.message, "登录失败");
				sendToClient(jsonObj.toString());
			}
		} catch (Exception e) {
			LoggerError.error("登录QQ异常", e);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(HttpCData.A20001.result, 1);
			jsonObj.put(HttpCData.A20001.message, "登录失败");
			sendToClient(jsonObj.toString());
		}
	}

	private void qqLogin() {
		URL url = null;
		HttpURLConnection conn = null;
		try {
			String remoteIp = request.getHeaderValue(HttpCData.A20001.real_ip);
			if (remoteIp == null || "".equals(remoteIp)) {
				SocketAddress sa = session.getRemoteAddress();
				if (sa != null) {
					remoteIp = sa.toString();
				}
			}
			if (remoteIp.contains("/") && remoteIp.contains(":")) {
				remoteIp = remoteIp.substring(remoteIp.indexOf('/') + 1, remoteIp.indexOf(':'));
			}

			String urlKey = "qq_url";
			if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("qq_login_test"))) {
				urlKey = "qq_url_test";
			}

			long time = System.currentTimeMillis() / 1000;
			StringBuilder sb = new StringBuilder();
			sb.append(ProjectCofigs.getParameter("qq_Appkey")).append(time);
			String sign = MD5.getHashString(sb.toString());
			sb.setLength(0);
			sb.append(ProjectCofigs.getParameter(urlKey)).append(timestamp).append(time);
			sb.append(appid).append(ProjectCofigs.getParameter("qq_AppId"));
			sb.append(sig).append(sign);
			sb.append(openid).append(request.getParameter(open_id));
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
			parameter.put("appid", ProjectCofigs.getParameter("qq_AppId"));
			parameter.put("openid", request.getParameter(open_id));
			parameter.put("openkey", request.getParameter(accesstoken));
			parameter.put("userip", remoteIp);
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
				Map<String, String> message = new HashMap<String, String>();
				message.put(HttpCData.A20001.channelId, request.getParameter("channel"));
				message.put(HttpCData.A20001.belongto, "0");
				message.put(HttpCData.A20001.channelSub, request.getParameter(HttpParameter.Account.channel_sub));
				message.put(HttpCData.A20001.clientVersion, "1");
				message.put(HttpCData.A20001.productId, request.getParameter("product_id"));
				message.put(HttpCData.AccountDoupo.active_code, request.getParameter(HttpCData.AccountDoupo.active_code));
				message.put(HttpCData.A20001.imei, request.getParameter(HttpCData.AccountDoupo.serial_code));
				message.put(HttpCData.A20001.deviceOS, request.getParameter(HttpCData.AccountDoupo.device_os));
				message.put(HttpCData.A20001.osVersion, request.getParameter(HttpCData.AccountDoupo.os_version));
				message.put(HttpCData.A20001.deviceUdid, request.getParameter(HttpCData.AccountDoupo.udid));
				message.put(HttpCData.A20001.deviceMac, request.getParameter(HttpCData.AccountDoupo.mac));
				message.put(HttpCData.A20001.deviceUa, request.getParameter(HttpCData.AccountDoupo.ua));
				message.put(HttpCData.A20001.phone, request.getParameter(HttpCData.AccountDoupo.phone_code));
				message.put(HttpCData.A20001.screenWidth, request.getParameter(HttpCData.AccountDoupo.screen_width));
				message.put(HttpCData.A20001.screenHeight, request.getParameter(HttpCData.AccountDoupo.screen_hight));
				message.put(HttpCData.A20001.phoneType, request.getParameter(HttpCData.AccountDoupo.phone_type));
				message.put(HttpCData.A20001.clientCode, request.getParameter(HttpParameter.Account.code_version));
				message.put(HttpCData.A20001.permit, request.getParameter("channel"));
				message.put(HttpCData.A20001.feature, "");
				message.put(HttpCData.A20001.registerFrom, "1");
				message.put(HttpCData.A20001.userid, request.getParameter(open_id));
				message.put(HttpCData.A20001.username, "");
				message.put(HttpCData.A20001.loginServer, ApplicationConfig.getInstance().getAppId() + "-" + ApplicationConfig.getInstance().getAppName());
				message.put(HttpCData.A20001.serverVersion, ApplicationConfig.getInstance().getCodeVersion());
				String addIp = "";
				SocketAddress sa = session.getRemoteAddress();
				if (sa != null) {
					addIp = sa.toString();
				}
				message.put(HttpCData.A20001.remoteAddress, addIp);
				message.put(HttpCData.A20001.real_ip, request.getHeaderValue(HttpCData.A20001.real_ip));

				JSONObject json = new JSONObject();
				json.put(accesstoken, request.getParameter(accesstoken));
				json.put(action, request.getParameter(action));
				json.put(open_id, request.getParameter(open_id));
				json.put(paytoken, request.getParameter(paytoken));
				json.put(pf, request.getParameter(pf));
				json.put(pf_key, request.getParameter(pf_key));

				message.put(HttpCData.A20001.customData, json.toString());

				ThreadManager.requestHttp("account", new A20006ChannelLoginRun(session, message));
				return;
			} else {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(HttpCData.A20001.result, 1);
				jsonObj.put(HttpCData.A20001.message, "登录失败");
				sendToClient(jsonObj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void wxLogin() {
		URL url = null;
		HttpURLConnection conn = null;
		try {
			String remoteIp = request.getHeaderValue(HttpCData.A20001.real_ip);
			if (remoteIp == null || "".equals(remoteIp)) {
				SocketAddress sa = session.getRemoteAddress();
				if (sa != null) {
					remoteIp = sa.toString();
				}
			}
			if (remoteIp.contains("/") && remoteIp.contains(":")) {
				remoteIp = remoteIp.substring(remoteIp.indexOf('/') + 1, remoteIp.indexOf(':'));
			}
			long time = System.currentTimeMillis() / 1000;
			StringBuilder sb = new StringBuilder();
			sb.append(ProjectCofigs.getParameter("wx_Appkey")).append(time);
			String sign = MD5.getHashString(sb.toString());
			sb.setLength(0);

			String urlKey = "wx_url";
			if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("wx_login_test"))) {
				urlKey = "wx_url_test";
			}

			sb.append(ProjectCofigs.getParameter(urlKey)).append(timestamp).append(time);
			sb.append(appid).append(ProjectCofigs.getParameter("wx_AppId"));
			sb.append(sig).append(sign);
			sb.append(openid).append(request.getParameter(open_id));
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
			parameter.put("openid", request.getParameter(open_id));
			parameter.put("accessToken", request.getParameter(accesstoken));
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
				Map<String, String> message = new HashMap<String, String>();
				message.put(HttpCData.A20001.channelId, request.getParameter("channel"));
				message.put(HttpCData.A20001.belongto, "0");
				message.put(HttpCData.A20001.channelSub, request.getParameter(HttpParameter.Account.channel_sub));
				message.put(HttpCData.A20001.clientVersion, "1");
				message.put(HttpCData.A20001.productId, request.getParameter("product_id"));
				message.put(HttpCData.AccountDoupo.active_code, request.getParameter(HttpCData.AccountDoupo.active_code));
				message.put(HttpCData.A20001.imei, request.getParameter(HttpCData.AccountDoupo.serial_code));
				message.put(HttpCData.A20001.deviceOS, request.getParameter(HttpCData.AccountDoupo.device_os));
				message.put(HttpCData.A20001.osVersion, request.getParameter(HttpCData.AccountDoupo.os_version));
				message.put(HttpCData.A20001.deviceUdid, request.getParameter(HttpCData.AccountDoupo.udid));
				message.put(HttpCData.A20001.deviceMac, request.getParameter(HttpCData.AccountDoupo.mac));
				message.put(HttpCData.A20001.deviceUa, request.getParameter(HttpCData.AccountDoupo.ua));
				message.put(HttpCData.A20001.phone, request.getParameter(HttpCData.AccountDoupo.phone_code));
				message.put(HttpCData.A20001.screenWidth, request.getParameter(HttpCData.AccountDoupo.screen_width));
				message.put(HttpCData.A20001.screenHeight, request.getParameter(HttpCData.AccountDoupo.screen_hight));
				message.put(HttpCData.A20001.phoneType, request.getParameter(HttpCData.AccountDoupo.phone_type));
				message.put(HttpCData.A20001.clientCode, request.getParameter(HttpParameter.Account.code_version));
				message.put(HttpCData.A20001.permit, request.getParameter("channel"));
				message.put(HttpCData.A20001.feature, "");
				message.put(HttpCData.A20001.registerFrom, "1");
				message.put(HttpCData.A20001.userid, request.getParameter(open_id));
				message.put(HttpCData.A20001.username, "");
				message.put(HttpCData.A20001.loginServer, ApplicationConfig.getInstance().getAppId() + "-" + ApplicationConfig.getInstance().getAppName());
				message.put(HttpCData.A20001.serverVersion, ApplicationConfig.getInstance().getCodeVersion());
				String addIp = "";
				SocketAddress sa = session.getRemoteAddress();
				if (sa != null) {
					addIp = sa.toString();
				}
				message.put(HttpCData.A20001.remoteAddress, addIp);
				message.put(HttpCData.A20001.real_ip, request.getHeaderValue(HttpCData.A20001.real_ip));
				JSONObject json = new JSONObject();
				json.put(accesstoken, request.getParameter(accesstoken));
				json.put(action, request.getParameter(action));
				json.put(open_id, request.getParameter(open_id));
				json.put(paytoken, request.getParameter(paytoken));
				json.put(pf, request.getParameter(pf));
				json.put(pf_key, request.getParameter(pf_key));

				message.put(HttpCData.A20001.customData, json.toString());

				ThreadManager.requestHttp("account", new A20006ChannelLoginRun(session, message));
				return;
			} else {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(HttpCData.A20001.result, 1);
				jsonObj.put(HttpCData.A20001.message, "登录失败");
				sendToClient(jsonObj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
