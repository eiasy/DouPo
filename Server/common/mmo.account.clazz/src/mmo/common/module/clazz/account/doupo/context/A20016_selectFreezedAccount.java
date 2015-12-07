package mmo.common.module.clazz.account.doupo.context;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.database.SelectFreezeAccountRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class A20016_selectFreezedAccount extends AContextHandle {
	private String MSG_1_OK = "OK";
	private String MSG_ERR  = "操作失败";

	public A20016_selectFreezedAccount() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {

			String callback = request.getParameter(HttpCData.A20001.callback);
			String sessionId = request.getParameter(HttpCData.A20001.sessionId);
			int connectId = getInt(request, HttpCData.A20001.connectId);

			ThreadManager.accessDatabase(new SelectFreezeAccountRun(callback, sessionId, connectId));

			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
			loginResult.put(HttpCData.A20001.message, MSG_1_OK);
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20016_selectFreezedAccount", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
