package mmo.common.module.clazz.channel;

import java.net.SocketAddress;
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
import mmo.tools.net.HttpsUtil;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

public class ValidateYiyouRun extends HandRequestRun {

	@Override
	public void run() {
		try {
			int sw = 0;
			int sh = 0;
			try {
				sw = Integer.parseInt(request.getParameter(HttpCData.AccountDoupo.screen_width));
			} catch (Exception e) {

			}
			try {
				sh = Integer.parseInt(request.getParameter(HttpCData.AccountDoupo.screen_hight));
			} catch (Exception e) {

			}
			int platform = 2;
			try {
				platform = Integer.parseInt(request.getParameter(HttpCData.AccountDoupo.product_id));
			} catch (Exception e) {
				platform = 2;
			}
			String os = request.getParameter(HttpCData.AccountDoupo.device_os);

			// 获取参数
			String channel = request.getParameter("channel");
			String uid = request.getParameter("uid");
			String sessionid = request.getParameter("sessionid");

			String appid = null;
			String appkey = null;
			String appsecret = null;
			String url = null;
			// 组织URL和参数
			if ("ios".equalsIgnoreCase(os)) {
				appsecret = ProjectCofigs.getParameter("yiyou_appsecret_ios");
				appid = ProjectCofigs.getParameter("yiyou_appId_ios");
				appkey = ProjectCofigs.getParameter("yiyou_appkey_ios");
				url = ProjectCofigs.getParameter("yiyou_url_ios");
			} else {
				appsecret = ProjectCofigs.getParameter("yiyou_appsecret");
				appid = ProjectCofigs.getParameter("yiyou_appId");
				appkey = ProjectCofigs.getParameter("yiyou_appkey");
				url = ProjectCofigs.getParameter("yiyou_url_android");
			}
			StringBuilder paramBuilder = new StringBuilder();
			paramBuilder.append("appid=").append(appid).append("&");
			paramBuilder.append("appkey=").append(appkey).append("&");
			paramBuilder.append("uid=").append(uid).append("&");
			paramBuilder.append("sessionid=").append(sessionid).append("&");
			paramBuilder.append("md5string=").append(MD5.getHashString(appid + appkey + uid + sessionid + appsecret));

			url = url + "?" + paramBuilder.toString();
			String result = HttpsUtil.request(url, "");

			if (result != null && !result.equals("")) {
				JSONObject resultJson = JSONObject.fromObject(result);
				if (resultJson.containsKey("errorcode") && "0".equals(resultJson.getString("errorcode"))) {
					String id = uid;

					Map<String, String> message = new HashMap<String, String>();
					message.put(HttpCData.A20001.channelId, channel);
					message.put(HttpCData.A20001.belongto, "0");
					message.put(HttpCData.A20001.channelSub, request.getParameter(HttpParameter.Account.channel_sub));
					message.put(HttpCData.A20001.clientVersion, "1");
					message.put(HttpCData.A20001.productId, platform + "");
					message.put(HttpCData.AccountDoupo.active_code, request.getParameter(HttpCData.AccountDoupo.active_code));
					message.put(HttpCData.A20001.imei, request.getParameter(HttpCData.AccountDoupo.serial_code));
					message.put(HttpCData.A20001.deviceOS, os);
					message.put(HttpCData.A20001.osVersion, request.getParameter(HttpCData.AccountDoupo.os_version));
					message.put(HttpCData.A20001.deviceUdid, request.getParameter(HttpCData.AccountDoupo.udid));
					message.put(HttpCData.A20001.deviceMac, request.getParameter(HttpCData.AccountDoupo.mac));
					message.put(HttpCData.A20001.deviceUa, request.getParameter(HttpCData.AccountDoupo.ua));
					message.put(HttpCData.A20001.phone, request.getParameter(HttpCData.AccountDoupo.phone_code));
					message.put(HttpCData.A20001.screenWidth, sw + "");
					message.put(HttpCData.A20001.screenHeight, sh + "");
					message.put(HttpCData.A20001.phoneType, request.getParameter(HttpCData.AccountDoupo.phone_type));
					message.put(HttpCData.A20001.clientCode, request.getParameter(HttpParameter.Account.code_version));
					message.put(HttpCData.A20001.permit, channel);
					message.put(HttpCData.A20001.feature, "");
					message.put(HttpCData.A20001.registerFrom, "1");
					message.put(HttpCData.A20001.userid, id);
					message.put(HttpCData.A20001.username, uid);
					message.put(HttpCData.A20001.loginServer, ApplicationConfig.getInstance().getAppId() + "-" + ApplicationConfig.getInstance().getAppName());
					message.put(HttpCData.A20001.serverVersion, ApplicationConfig.getInstance().getCodeVersion());
					String addIp = "";
					SocketAddress sa = session.getRemoteAddress();
					if (sa != null) {
						addIp = sa.toString();
					}
					message.put(HttpCData.A20001.remoteAddress, addIp);
					message.put(HttpCData.A20001.real_ip, request.getHeaderValue(HttpCData.A20001.real_ip));
					message.put(HttpCData.A20001.customData, "");

					ThreadManager.requestHttp("account", new A20006ChannelLoginRun(session, message));
					return;
				}
			}
		} catch (Exception e) {
			LoggerError.error("登录YiYou异常", e);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(HttpCData.A20001.result, 1);
		jsonObj.put(HttpCData.A20001.message, "登录失败");
		sendToClient(jsonObj.toString());
	}
}
