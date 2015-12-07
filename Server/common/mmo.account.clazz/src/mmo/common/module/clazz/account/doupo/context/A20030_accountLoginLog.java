package mmo.common.module.clazz.account.doupo.context;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.clazz.account.parameter.AccountParameter;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.account.LoggerAccount;
import mmo.tools.log.LoggerError;

import org.apache.mina.core.session.IoSession;

public class A20030_accountLoginLog extends AContextHandle {

	public A20030_accountLoginLog() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			int accountId = getInt(request, AccountParameter.Account.account_d);
			String channelId = request.getParameter(AccountParameter.Account.channel_id);
			int belongto = getIntRelax(request, AccountParameter.Account.belongto);
			String channelSub = request.getParameter(AccountParameter.Account.channel_sub);
			int productId = getIntRelax(request, (AccountParameter.Account.product_id));
			String imei = request.getParameter(AccountParameter.Account.imei);
			String feature = request.getParameter(AccountParameter.Account.feature);
			String loginServer = request.getParameter(AccountParameter.Account.login_server);
			String serverVersion = request.getParameter(AccountParameter.Account.server_version);
			String deviceOS = request.getParameter(AccountParameter.Account.device_os);
			String osVersion = request.getParameter(AccountParameter.Account.os_version);
			int screenWidth = getIntRelax(request, AccountParameter.Account.screen_width);
			int screenHeight = getIntRelax(request, AccountParameter.Account.screen_height);
			String deviceUdid = request.getParameter(AccountParameter.Account.udid);
			String deviceMac = request.getParameter(AccountParameter.Account.mac);
			String deviceUa = request.getParameter(AccountParameter.Account.ua);
			String phoneType = request.getParameter(AccountParameter.Account.phone_type);
			String remoteAddress = request.getParameter(AccountParameter.Account.real_ip);
			String clientCode = request.getParameter(AccountParameter.Account.code_version);
			String phone = request.getParameter(AccountParameter.Account.phone_code);
			String permit = request.getParameter(AccountParameter.Account.permit);
			String userid = request.getParameter(AccountParameter.Account.user_id);
			String username = request.getParameter(AccountParameter.Account.user_name);
			String customData = request.getParameter(AccountParameter.Account.custom_data);
			UserAccount ua = AccountCache.getInstance().getUserAccount(accountId);
			if (ua != null) {
				LoggerAccount.accountLogin(productId, feature, loginServer, serverVersion, clientCode, deviceOS, osVersion, screenWidth,
				        screenHeight, imei, deviceUdid, deviceMac, deviceUa, phoneType, remoteAddress, ua.getPermit(), channelId, channelSub,
				        ua.getAccountId(), userid, ua.getUsername(), HttpCData.LOGIN_2_OLD_ACCOUNT);
			}
			return sendToClient("ok");
		} catch (Exception e) {
			LoggerError.error("A20030_accountLoginLog", e);
			return sendToClient("error");
		}
	}
}
