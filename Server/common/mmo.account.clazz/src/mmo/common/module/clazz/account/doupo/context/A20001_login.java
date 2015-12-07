package mmo.common.module.clazz.account.doupo.context;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.security.SecurityCodeManager;
import mmo.extension.application.ApplicationConfig;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.account.LoggerAccount;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class A20001_login extends AContextHandle {

	private String MSG_OK = "OK";
	private String MSG_NO = "未知";

	public A20001_login() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			String channelId = request.getParameter(HttpCData.A20001.channelId);
			int belongto = getInt(request, HttpCData.A20001.belongto);
			String userid = request.getParameter(HttpCData.A20001.userid);
			String password = request.getParameter(HttpCData.A20001.password);
			String channelSub = request.getParameter(HttpCData.A20001.channelSub);
			int clientVersion = getInt(request, HttpCData.A20001.clientVersion);
			int productId = getInt(request, HttpCData.A20001.productId);
			String imei = request.getParameter(HttpCData.A20001.imei);
			String feature = request.getParameter(HttpCData.A20001.feature);
			String loginServer = request.getParameter(HttpCData.A20001.loginServer);
			String serverVersion = request.getParameter(HttpCData.A20001.serverVersion);
			String deviceOS = request.getParameter(HttpCData.A20001.deviceOS);
			String osVersion = request.getParameter(HttpCData.A20001.osVersion);
			int screenWidth = getInt(request, HttpCData.A20001.screenWidth);
			int screenHeight = getInt(request, HttpCData.A20001.screenHeight);
			String deviceUdid = request.getParameter(HttpCData.A20001.deviceUdid);
			String deviceMac = request.getParameter(HttpCData.A20001.deviceMac);
			String deviceUa = request.getParameter(HttpCData.A20001.deviceUa);
			String phoneType = request.getParameter(HttpCData.A20001.phoneType);
			String remoteAddress = request.getParameter(HttpCData.A20001.remoteAddress);
			String clientCode = request.getParameter(HttpCData.A20001.clientCode);

			loginResult.put(HttpCData.A20001.channelId, channelId);
			loginResult.put(HttpCData.A20001.belongto, belongto);
			loginResult.put(HttpCData.A20001.userid, userid);
			loginResult.put(HttpCData.A20001.channelSub, channelSub);

			Object result = AccountCache.getInstance().validateAccount(channelId, channelSub, userid, password);
			if (result == null) {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
				loginResult.put(HttpCData.A20001.message, MSG_NO);
			} else {
				if (result instanceof UserAccount) {
					UserAccount ua = (UserAccount) result;
					ua.setLoginCount(ua.getLoginCount() + 1);
					String securityCode = ApplicationConfig.getInstance().getSecurityCode();
					SecurityCodeManager.generateSecurityCode(securityCode, ua.getAccountId(), channelId, clientVersion, productId, ua.getUserid(),
					        ua.getTimeRegister(), channelSub, belongto, loginServer);
					loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
					loginResult.put(HttpCData.A20001.message, MSG_OK);
					loginResult.put(HttpCData.A20001.accountId, ua.getAccountId());
					loginResult.put(HttpCData.A20001.sex, ua.getSex());
					loginResult.put(HttpCData.A20001.dbstatus, ua.getState());
					loginResult.put(HttpCData.A20001.money, ua.getMoney());
					loginResult.put(HttpCData.A20001.accountFreeze, ua.getTimeFreeze());
					loginResult.put(HttpCData.A20001.loginCount, ua.getLoginCount());
					loginResult.put(HttpCData.A20001.pwdStatus, ua.getPwdState());
					loginResult.put(HttpCData.A20001.securityCode, securityCode);
					loginResult.put(HttpCData.A20001.roleCount, AccountCache.getInstance().getServerRoleCount(ua.getAccountId(), productId));
					loginResult.put(HttpCData.A20001.lastEnter, AccountCache.getInstance().getServerLastEnter(ua.getAccountId(), productId));
					loginResult.put(HttpCData.A20001.deviceFreeze, AccountCache.getInstance().getDeviceFreeze(imei));

					LoggerAccount.accountLogin(productId, feature, loginServer, serverVersion, clientCode, deviceOS, osVersion, screenWidth,
					        screenHeight, imei, deviceUdid, deviceMac, deviceUa, phoneType, remoteAddress, ua.getPermit(), channelId, channelSub,
					        ua.getAccountId(), userid, ua.getUsername(), HttpCData.LOGIN_2_OLD_ACCOUNT);
				} else if (result instanceof String) {
					loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_3_ACCOUNT);
					loginResult.put(HttpCData.A20001.message, result);
				} else {
					loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
					loginResult.put(HttpCData.A20001.message, MSG_NO);
				}
			}
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20001_login", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_NO);
			return sendToClient(loginResult.toString());
		}
	}

}
