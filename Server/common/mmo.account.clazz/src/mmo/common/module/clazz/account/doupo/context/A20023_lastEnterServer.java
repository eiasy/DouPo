package mmo.common.module.clazz.account.doupo.context;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.thread.AccountValidateHeartbeat;
import mmo.common.module.account.doupo.cache.thread.validate.ServerLastEnterRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class A20023_lastEnterServer extends AContextHandle {

	public A20023_lastEnterServer() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			int productId = getInt(request, HttpCData.Receipt.productId);
			int serverId = getInt(request, HttpCData.Receipt.serverId);
			int accountId = getInt(request, HttpCData.Receipt.accountId);
			AccountValidateHeartbeat.getInstance().execute(new ServerLastEnterRun(productId, serverId, accountId));
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20023_lastEnterServer", e);
			loginResult.put(HttpCData.Receipt.status, HttpCData.Receipt.RT_2_NO);
			return sendToClient(loginResult.toString());
		}
	}

}
