package mmo.common.module.clazz.account.doupo.context;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.charge.AccountChargeManager;
import mmo.common.module.account.doupo.cache.thread.AccountValidateHeartbeat;
import mmo.common.module.account.doupo.cache.thread.validate.ServerRoleCountRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class A20014_serverRoleCount extends AContextHandle {
	private String MSG_1_OK = "OK";
	private String MSG_ERR  = "操作失败";

	public A20014_serverRoleCount() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			int gameId = getInt(request, HttpCData.A20001.productId);
			int serverId = getInt(request, HttpCData.A20001.serverId);
			int accountId = getInt(request, HttpCData.A20001.accountId);
			int roleCount = getInt(request, HttpCData.A20001.roleCount);
			AccountValidateHeartbeat.getInstance().execute(new ServerRoleCountRun(gameId, serverId, accountId, roleCount));
			int roleid = getIntRelax(request, HttpCData.A20001.roleid);
			if (roleid > 0) {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
				long[] array = AccountChargeManager.getInstance().getAndRemoveAccountCharge(accountId, gameId, roleid, serverId);
				loginResult.put(HttpCData.A20001.money, (int) array[0]);
				loginResult.put(HttpCData.A20001.time, array[1]);
			}

			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
			loginResult.put(HttpCData.A20001.message, MSG_1_OK);
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20014_serverRoleCount", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
