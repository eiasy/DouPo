package mmo.common.module.clazz.account.doupo.context;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.AccountDatabaseHeartbeat;
import mmo.common.module.account.doupo.cache.thread.database.AccountRegisterRun;
import mmo.common.module.account.doupo.security.SecurityCodeManager;
import mmo.extension.application.ApplicationConfig;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.account.LoggerAccount;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

public class A20005_registerAccount extends AContextHandle {
	private final static String REGISTER_FAIL      = "注册失败,请稍后再试";
	private final static String FAIL_DEVICE_FREEZE = "注册失败,设备被冻结";
	private final static String MSG_OK             = "OK";
	private final static String MSG_NO             = "未知";
	private final static String MSG_4_EXIST        = "账号已经存在";

	public A20005_registerAccount() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			String channelId = request.getParameter( HttpCData.A20001.channelId);
			int belongto = getInt(request, HttpCData.A20001.belongto);
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
			String phone = request.getParameter(HttpCData.A20001.phone);
			String permit = request.getParameter(HttpCData.A20001.permit);
			byte registerFrom = (byte) getInt(request, HttpCData.A20001.registerFrom);
			String userid = request.getParameter(HttpCData.A20001.userid);
			String username = request.getParameter(HttpCData.A20001.username);
			String password = request.getParameter(HttpCData.A20001.password);

			long deviceFreeze = AccountCache.getInstance().getDeviceFreeze(imei);
			loginResult.put(HttpCData.A20001.channelId, channelId);
			loginResult.put(HttpCData.A20001.belongto, belongto);
			loginResult.put(HttpCData.A20001.channelSub, channelSub);

			if (deviceFreeze > System.currentTimeMillis()) {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_4_DEVICE_FREEZE);
				loginResult.put(HttpCData.A20001.message, FAIL_DEVICE_FREEZE);
			} else {
				UserAccount ua = AccountCache.getInstance().validateChannelAccount(channelId, channelSub, userid);
				if (ua != null) {
					loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_5_EXIST);
					loginResult.put(HttpCData.A20001.message, MSG_4_EXIST);
				} else {
					int accountId = AccountCache.getInstance().nextAccountId();
					if (accountId > 0) {
						ua = new UserAccount();
						ua.setAccountId(accountId);
						ua.setBelongto(belongto);
						ua.setChannelId(channelId);
						ua.setChannelSub(channelSub);
						ua.setDeviceImei(imei);
						ua.setEmail("");
						ua.setFreezeDay(0);
						ua.setLoginCount(1);
						ua.setMoney(0);
						ua.setPassword(MD5.getHashString(password));
						ua.setPermit(userid + "@" + permit);
						ua.setPhone(phone);
						ua.setPwdState((byte) 0);
						ua.setReuserid(userid);
						ua.setSex((byte) 0);
						ua.setState((byte) 0);
						ua.setTimeFreeze(0);
						ua.setTimeRegister(System.currentTimeMillis());
						ua.setUserid(userid);
						ua.setUsername(username);
						AccountCache.getInstance().newAccount(ua);
						AccountDatabaseHeartbeat.getInstance().execute(
						        new AccountRegisterRun(ua, deviceOS, osVersion, deviceUdid, deviceMac, deviceUa, phoneType, screenHeight,
						                screenWidth, clientCode, productId, remoteAddress, registerFrom));

						String securityCode = ApplicationConfig.getInstance().getSecurityCode();
						SecurityCodeManager.generateSecurityCode(securityCode, ua.getAccountId(), channelId, clientVersion, productId,
						        ua.getUserid(), ua.getTimeRegister(), channelSub, belongto, loginServer);
						/*******************************************************/

						loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
						loginResult.put(HttpCData.A20001.message, MSG_OK);
						loginResult.put(HttpCData.A20001.accountId, ua.getAccountId());
						loginResult.put(HttpCData.A20001.userid, ua.getUserid());
						loginResult.put(HttpCData.A20001.username, ua.getUsername());
						loginResult.put(HttpCData.A20001.sex, ua.getSex());
						loginResult.put(HttpCData.A20001.dbstatus, ua.getState());
						loginResult.put(HttpCData.A20001.password, password);
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
						        ua.getAccountId(), userid, ua.getUsername(), HttpCData.LOGIN_1_NEW_ACCOUNT);
					} else {
						loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
						loginResult.put(HttpCData.A20001.message, MSG_NO);
					}
				}
			}

			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20005_registerAccount", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_3_ACCOUNT);
			loginResult.put(HttpCData.A20001.message, REGISTER_FAIL);
			return sendToClient(loginResult.toString());
		}
	}

}
