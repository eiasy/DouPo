package mmo.common.module.clazz.account.doupo.context;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.thread.AccountValidateHeartbeat;
import mmo.common.module.account.doupo.cache.thread.validate.ServerLastEnterRun;
import mmo.common.module.account.doupo.security.SecurityCode;
import mmo.common.module.account.doupo.security.SecurityCodeManager;
import mmo.extension.application.ApplicationConfig;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class A20002_securityCode extends AContextHandle {

	public A20002_securityCode() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			String receipt = request.getParameter(HttpCData.AccountDoupo.securityCode);
			int productId = getIntRelax(request, HttpCData.A20001.productId);
			if (productId < 1) {
				productId = 2;
			}
			int serverId = getIntRelax(request, HttpCData.AccountDoupo.server_id);
			SecurityCode sc = SecurityCodeManager.validateSecurityCode(receipt);
			if (sc == null) {
				loginResult.put(HttpCData.Receipt.status, HttpCData.Receipt.RT_2_NO);
			} else {
				loginResult.put(HttpCData.Receipt.status, HttpCData.Receipt.RT_1_OK);
				loginResult.put(HttpCData.Receipt.accountCenter, ApplicationConfig.getInstance().getAppId());
				loginResult.put(HttpCData.Receipt.loginServer, sc.getLoginServer());
				loginResult.put(HttpCData.Receipt.accountId, sc.getAccountId());
				loginResult.put(HttpCData.AccountDoupo.userId, sc.getUserId());
				loginResult.put(HttpCData.Receipt.clientVersion, sc.getClientVersion());
				loginResult.put(HttpCData.Receipt.timeRegister, sc.getRegisterTime());
				loginResult.put(HttpCData.AccountDoupo.channel_mark, sc.getChannelId());
				loginResult.put(HttpCData.AccountDoupo.channel_sub, sc.getChannelSub());
				loginResult.put(HttpCData.Receipt.belongto, sc.getBelongto());
				// if (sc.getChannelId() == 670000) {
				// UserAccount ua = AccountCache.getInstance().getUserAccount(sc.getAccountId());
				// loginResult.put(HttpCData.Receipt.customData, ua.getCustomData());
				// }
				AccountValidateHeartbeat.getInstance().execute(new ServerLastEnterRun(productId, serverId, sc.getAccountId()));
			}
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20002_securityCode", e);
			loginResult.put(HttpCData.Receipt.status, HttpCData.Receipt.RT_2_NO);
			return sendToClient(loginResult.toString());
		}
	}

}
