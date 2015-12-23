package mmo.common.module.clazz.channel;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

public class ValidateUCRun extends HandRequestRun {

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
			String channel = request.getParameter("channel");
			String sid = request.getParameter("sid");
			String gameId = request.getParameter("gameId");

			JSONObject jsonMap = new JSONObject();
			String url = "";
			if ("test".equalsIgnoreCase(ProjectCofigs.getParameter("uc_mode"))) {
				url = ProjectCofigs.getParameter("uc_testUrl") + ProjectCofigs.getParameter("uc_prefix") + ProjectCofigs.getParameter("uc_verifySession");
			} else {
				url = ProjectCofigs.getParameter("uc_normalUrl") + ProjectCofigs.getParameter("uc_prefix") + ProjectCofigs.getParameter("uc_verifySession");
			}
			jsonMap.put("id", System.currentTimeMillis() + "");
			Map<String, Object> sidMap = new HashMap<String, Object>();
			sidMap.put("sid", sid);
			jsonMap.put("data", sidMap);
			Map<String, Object> gameMap = new HashMap<String, Object>();
			gameMap.put("gameId", gameId);
			jsonMap.put("game", gameMap);
			jsonMap.put("sign", createMD5Sign(sidMap, "", ProjectCofigs.getParameter("uc_apiKey")));
			String param = jsonMap.toString();
			String result = HttpsUtil.request(url, param);

			if (result != null && !result.equals("")) {
				JSONObject resultJson = JSONObject.fromObject(result);
				JSONObject state = resultJson.getJSONObject("state");
				if (state.getInt("code") == 1) {
					JSONObject data = resultJson.getJSONObject("data");

					Map<String, String> message = new HashMap<String, String>();
					message.put(HttpCData.A20001.channelId, channel);
					message.put(HttpCData.A20001.belongto, "0");
					message.put(HttpCData.A20001.channelSub, request.getParameter(HttpParameter.Account.channel_sub));
					message.put(HttpCData.A20001.clientVersion, "1");
					message.put(HttpCData.A20001.productId, platform + "");
					message.put(HttpCData.AccountDoupo.active_code, request.getParameter(HttpCData.AccountDoupo.active_code));
					message.put(HttpCData.A20001.imei, request.getParameter(HttpCData.AccountDoupo.serial_code));
					message.put(HttpCData.A20001.deviceOS, request.getParameter(HttpCData.AccountDoupo.device_os));
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
					message.put(HttpCData.A20001.userid, data.getString("accountId"));
					if (data.containsKey("nickName")) {
						message.put(HttpCData.A20001.username,data.getString("nickName"));
					}else{
						message.put(HttpCData.A20001.username, data.getString("accountId"));
					}
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
			LoggerError.error("登录UC异常", e);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(HttpCData.A20001.result, 1);
		jsonObj.put(HttpCData.A20001.message, "登录失败");
		sendToClient(jsonObj.toString());
	}

	/**
	 * 按照接口规范生成请求数据的MD5签名
	 * 
	 * @param params
	 *            业务数据
	 * @param caller
	 *            客户端平台
	 * @param secKey
	 *            MD5签名用的密钥
	 * @return MD5签名生成的字符串。如果传入的参数有一个为null，将返回null
	 */
	String createMD5Sign(Map<String, Object> params, String caller, String secKey) {
		if (null == params || null == caller || null == secKey) {
			return null;
		}

		String temp = caller + createSignData(params, null) + secKey;

		return MD5.getHashString(temp);
	}

	/**
	 * 将Map数据组装成待签名字符串
	 * 
	 * @param params
	 *            待签名的参数列表
	 * @param notIn
	 *            不参与签名的参数名列表
	 * @return 待签名字符串。如果参数params为null，将返回null
	 */
	String createSignData(Map<String, Object> params, String[] notIn) {
		if (null == params) {
			return null;
		}

		StringBuilder content = new StringBuilder(200);

		// 按照key排序
		List<String> notInList = null;
		if (null != notIn) {
			notInList = Arrays.asList(notIn);
		}
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);

			if (notIn != null && notInList.contains(key))
				continue;

			String value = params.get(key) == null ? "" : params.get(key).toString();
			content.append(key).append("=").append(value);
		}

		String result = content.toString();

		return result;
	}
}
