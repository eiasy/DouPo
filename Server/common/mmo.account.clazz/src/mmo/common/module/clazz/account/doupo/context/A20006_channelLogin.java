package mmo.common.module.clazz.account.doupo.context;

import java.util.HashMap;
import java.util.Map;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.ActiveCodeManager;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.AccountDatabaseHeartbeat;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.database.AccountRegisterRun;
import mmo.common.module.account.doupo.cache.thread.database.UpdateAccountLastTimeDBRun;
import mmo.common.module.account.doupo.cache.thread.database.UpdateActiveCodeRun;
import mmo.common.module.account.doupo.security.SecurityCodeManager;
import mmo.common.module.account.doupo.security.TokenData;
import mmo.extension.application.ApplicationConfig;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.account.LoggerAccount;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class A20006_channelLogin extends AContextHandle {
	private final static String REGISTER_FAIL = "注册失败,请稍后再试";
	private final static String FAIL_DEVICE_FREEZE = "注册失败,设备被冻结";
	private final static String MSG_OK = "OK";
	private final static String MSG_NO = "未知";
	private final Map<String, String> info = new HashMap<String, String>();
	static {

	}

	public A20006_channelLogin() {
		info.put("uc", "激活失败，请检查是否输入正确。想了解更多详情，请查看UC斗破苍穹论坛。");
		info.put("360", "激活失败，请检查是否输入正确。想了解更多详情，请加入官方QQ群：467113831。");
		info.put("baidu", "激活失败，请检查是否输入正确。想了解更多详情，请加入官方QQ群：467113831。");
		info.put("xiaomi", "激活失败，请检查是否输入正确。想了解更多详情，请加入官方QQ群：467113831。");
	}

	public String getInfo(String channel) {
		String msg = info.get(channel);
		if (msg == null) {
			return "激活失败";
		}
		return msg;
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			String channelId = request.getParameter(HttpCData.A20001.channelId);
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
			int screenWidth = 0;
			int screenHeight = 0;
			try {
				screenWidth = Integer.parseInt(request.getParameter(HttpCData.A20001.screenWidth));
				screenHeight = Integer.parseInt(request.getParameter(HttpCData.A20001.screenHeight));
			} catch (Exception e) {

			}
			String deviceUdid = request.getParameter(HttpCData.A20001.deviceUdid);
			String deviceMac = request.getParameter(HttpCData.A20001.deviceMac);
			String deviceUa = request.getParameter(HttpCData.A20001.deviceUa);
			String phoneType = request.getParameter(HttpCData.A20001.phoneType);
			String remoteAddress = request.getParameter(HttpCData.A20001.real_ip);
			if (remoteAddress == null || remoteAddress.length() < 6) {
				remoteAddress = request.getParameter(HttpCData.A20001.remoteAddress);
				if (remoteAddress.contains("/") && remoteAddress.contains(":")) {
					remoteAddress = remoteAddress.substring(remoteAddress.indexOf('/') + 1, remoteAddress.indexOf(':'));
				}
			}
			String clientCode = request.getParameter(HttpCData.A20001.clientCode);
			String phone = request.getParameter(HttpCData.A20001.phone);
			String permit = request.getParameter(HttpCData.A20001.permit);
			byte registerFrom = (byte) getInt(request, HttpCData.A20001.registerFrom);
			String userid = request.getParameter(HttpCData.A20001.userid);
			if ("anysdk".equalsIgnoreCase(channelId)) {
				TokenData tokenData = SecurityCodeManager.getTokenData(request.getParameter("token"));
				if (tokenData == null) {
					loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_1_FAIL);
					loginResult.put(HttpCData.A20001.message, REGISTER_FAIL);
					return sendToClient(loginResult.toString());
				}
				JSONObject json = JSONObject.fromObject(tokenData.getData());
				JSONObject jsonData = json.getJSONObject("common");
				userid = jsonData.getString("uid");
			}
			if (userid == null || "".equals(userid.trim())) {
				loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_1_FAIL);
				loginResult.put(HttpCData.A20001.message, REGISTER_FAIL);
				return sendToClient(loginResult.toString());
			}
			String username = request.getParameter(HttpCData.A20001.username);
			String customData = request.getParameter(HttpCData.A20001.customData);
			String activeCode = request.getParameter(HttpCData.AccountDoupo.active_code);
			if (activeCode != null) {
				activeCode = activeCode.toUpperCase();
			}
			long deviceFreeze = AccountCache.getInstance().getDeviceFreeze(imei);
			loginResult.put(HttpCData.A20001.channelId, channelId);
			loginResult.put(HttpCData.A20001.belongto, belongto);
			loginResult.put(HttpCData.A20001.channelSub, channelSub);

			if (deviceFreeze > System.currentTimeMillis()) {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_4_DEVICE_FREEZE);
				loginResult.put(HttpCData.A20001.message, FAIL_DEVICE_FREEZE);
			} else {
				UserAccount ua = AccountCache.getInstance().validateChannelAccount(channelId, channelSub, userid);
				if (ua == null) {
					int accountId = AccountCache.getInstance().nextAccountId();
					if (accountId > 0) {
						String password = "";
						ua = new UserAccount();
						ua.setCustomData(customData);
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
						if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("white_list")) && !AccountCache.getInstance().filterWhiteList(channelSub, ua.getUserid(), remoteAddress)) {
							loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_1_FAIL);
							loginResult.put(HttpCData.A20001.message, ProjectCofigs.getParameter("white_list_message"));
						} else {
							if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("need_active_code"))) {
								if (ua.isActive()) {
									loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_0_OK);
									loginResult.put(HttpCData.A20001.message, MSG_OK);
								} else {
									if (activeCode == null || activeCode.length() < 1) {
										loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_3_NEED_ACTIVE_CODE);
										loginResult.put(HttpCData.A20001.message, getInfo(channelId));
									} else {
										boolean switchChannel = true;
										switch (channelId) {
											case "uc": {
												if (!activeCode.startsWith("U")) {
													switchChannel = false;
												}
												break;
											}
											case "360": {
												if (!activeCode.startsWith("Q")) {
													switchChannel = false;
												}
												break;
											}
											case "baidu": {
												if (!activeCode.startsWith("B")) {
													switchChannel = false;
												}
												break;
											}
											case "xiaomi": {
												if (!activeCode.startsWith("M")) {
													switchChannel = false;
												}
												break;
											}
											case "oppo": {
												if (!activeCode.startsWith("O")) {
													switchChannel = false;
												}
												break;
											}
											case "yiyou": {
												if (!activeCode.startsWith("Y")) {
													switchChannel = false;
												}
												break;
											}
										}
										if (switchChannel) {
											if (ActiveCodeManager.getInstance().validate(ua, activeCode)) {
												loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_0_OK);
												loginResult.put(HttpCData.A20001.message, MSG_OK);
											} else {
												loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_2_FAIL_ACTIVE_CODE);
												loginResult.put(HttpCData.A20001.message, getInfo(channelId));
											}
										} else {
											loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_2_FAIL_ACTIVE_CODE);
											loginResult.put(HttpCData.A20001.message, getInfo(channelId));
										}
									}
								}
							} else {
								loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_0_OK);
								loginResult.put(HttpCData.A20001.message, MSG_OK);
							}
						}
						AccountDatabaseHeartbeat.getInstance().execute(new AccountRegisterRun(ua, deviceOS, osVersion, deviceUdid, deviceMac, deviceUa, phoneType, screenHeight, screenWidth, clientCode, productId, remoteAddress, registerFrom));

						String securityCode = ApplicationConfig.getInstance().getSecurityCode();
						SecurityCodeManager.generateSecurityCode(securityCode, ua.getAccountId(), channelId, clientVersion, productId, ua.getUserid(), ua.getTimeRegister(), channelSub, belongto, loginServer);
						/*******************************************************/

						loginResult.put(HttpCData.A20001.accountId, ua.getAccountId());
						loginResult.put(HttpCData.A20001.userid, ua.getUserid());
						loginResult.put(HttpCData.A20001.username, ua.getUsername());
						loginResult.put(HttpCData.A20001.sex, ua.getSex());
						// 组织URL和参数
						if (deviceOS.toLowerCase().contains("ios")) {
							loginResult.put(HttpCData.A20001.notifyUri, ProjectCofigs.getParameter(HttpCData.A20001.notifyUri + "_" + channelId + "_ios"));
						} else {
							loginResult.put(HttpCData.A20001.notifyUri, ProjectCofigs.getParameter(HttpCData.A20001.notifyUri + "_" + channelId));
						}
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
						loginResult.put(HttpCData.A20001.newAccount, "true");

						LoggerAccount.accountLogin(productId, feature, loginServer, serverVersion, clientCode, deviceOS, osVersion, screenWidth, screenHeight, imei, deviceUdid, deviceMac, deviceUa, phoneType, remoteAddress, ua.getPermit(), channelId, channelSub, ua.getAccountId(), userid, ua.getUsername(), HttpCData.LOGIN_1_NEW_ACCOUNT);
					} else {
						loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
						loginResult.put(HttpCData.A20001.message, MSG_NO);
					}
				} else {
					ua.setCustomData(customData);
					ua.setLoginCount(ua.getLoginCount() + 1);
					ThreadManager.accessDatabase(new UpdateAccountLastTimeDBRun(ua));
					String securityCode = ApplicationConfig.getInstance().getSecurityCode();
					SecurityCodeManager.generateSecurityCode(securityCode, ua.getAccountId(), channelId, clientVersion, productId, ua.getUserid(), ua.getTimeRegister(), channelSub, belongto, loginServer);
					/*******************************************************/
					if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("white_list")) && !AccountCache.getInstance().filterWhiteList(channelSub, ua.getUserid(), remoteAddress)) {
						loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_1_FAIL);
						loginResult.put(HttpCData.A20001.message, ProjectCofigs.getParameter("white_list_message"));
					} else {
						if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("need_active_code"))) {
							if (ua.isActive()) {
								loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_0_OK);
								loginResult.put(HttpCData.A20001.message, MSG_OK);
							} else {
								if (activeCode == null || activeCode.length() < 1) {
									loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_3_NEED_ACTIVE_CODE);
									loginResult.put(HttpCData.A20001.message, getInfo(channelId));
								} else {
									boolean switchChannel = true;
									switch (channelId) {
										case "uc": {
											if (!activeCode.startsWith("U")) {
												switchChannel = false;
											}
											break;
										}
										case "360": {
											if (!activeCode.startsWith("Q")) {
												switchChannel = false;
											}
											break;
										}
										case "baidu": {
											if (!activeCode.startsWith("B")) {
												switchChannel = false;
											}
											break;
										}
										case "xiaomi": {
											if (!activeCode.startsWith("M")) {
												switchChannel = false;
											}
											break;
										}
										case "oppo": {
											if (!activeCode.startsWith("O")) {
												switchChannel = false;
											}
											break;
										}
										case "yiyou": {
											if (!activeCode.startsWith("Y")) {
												switchChannel = false;
											}
											break;
										}
									}
									if (switchChannel) {
										if (ActiveCodeManager.getInstance().validate(ua, activeCode)) {
											loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_0_OK);
											loginResult.put(HttpCData.A20001.message, MSG_OK);
											AccountDatabaseHeartbeat.getInstance().execute(new UpdateActiveCodeRun(ua));
										} else {
											loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_2_FAIL_ACTIVE_CODE);
											loginResult.put(HttpCData.A20001.message, getInfo(channelId));
										}
									} else {
										loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_2_FAIL_ACTIVE_CODE);
										loginResult.put(HttpCData.A20001.message, getInfo(channelId));
									}
								}
							}
						} else {
							loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_0_OK);
							loginResult.put(HttpCData.A20001.message, MSG_OK);
						}
					}
					loginResult.put(HttpCData.A20001.accountId, ua.getAccountId());
					loginResult.put(HttpCData.A20001.userid, ua.getUserid());
					loginResult.put(HttpCData.A20001.username, ua.getUsername());
					loginResult.put(HttpCData.A20001.sex, ua.getSex());
					// 组织URL和参数
					if (deviceOS.toLowerCase().contains("ios")) {
						loginResult.put(HttpCData.A20001.notifyUri, ProjectCofigs.getParameter(HttpCData.A20001.notifyUri + "_" + channelId + "_ios"));
					} else {
						loginResult.put(HttpCData.A20001.notifyUri, ProjectCofigs.getParameter(HttpCData.A20001.notifyUri + "_" + channelId));
					}
					loginResult.put(HttpCData.A20001.dbstatus, ua.getState());
					loginResult.put(HttpCData.A20001.password, "");
					loginResult.put(HttpCData.A20001.money, ua.getMoney());
					loginResult.put(HttpCData.A20001.accountFreeze, ua.getTimeFreeze());
					loginResult.put(HttpCData.A20001.loginCount, ua.getLoginCount());
					loginResult.put(HttpCData.A20001.pwdStatus, ua.getPwdState());
					loginResult.put(HttpCData.A20001.securityCode, securityCode);
					loginResult.put(HttpCData.A20001.roleCount, AccountCache.getInstance().getServerRoleCount(ua.getAccountId(), productId));
					loginResult.put(HttpCData.A20001.lastEnter, AccountCache.getInstance().getServerLastEnter(ua.getAccountId(), productId));
					loginResult.put(HttpCData.A20001.deviceFreeze, AccountCache.getInstance().getDeviceFreeze(imei));
					loginResult.put(HttpCData.A20001.newAccount, "false");
					LoggerAccount.accountLogin(productId, feature, loginServer, serverVersion, clientCode, deviceOS, osVersion, screenWidth, screenHeight, imei, deviceUdid, deviceMac, deviceUa, phoneType, remoteAddress, ua.getPermit(), channelId, channelSub, ua.getAccountId(), userid, ua.getUsername(), HttpCData.LOGIN_2_OLD_ACCOUNT);
				}
			}
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20006_channelLogin", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.AccountDoupo.RT_1_FAIL);
			loginResult.put(HttpCData.A20001.message, REGISTER_FAIL);
			return sendToClient(loginResult.toString());
		}
	}

}
