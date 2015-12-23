package mmo.common.module.clazz.callback;

import mmo.common.account.HttpCData;
import mmo.common.module.sdk.http.LoadServerListRun;
import mmo.common.module.sdk.token.TokenHeartbeat;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class ContextReloadServerList extends AContextHandle {

	public ContextReloadServerList() {
		super();
	}

	public final HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		TokenHeartbeat.getInstance().execute(new LoadServerListRun());
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(HttpCData.A20001.result, 1);
		jsonResult.put(HttpCData.A20001.message, "加载服务器列表指令已经发出");
		return sendToClient(jsonResult.toString());
	}

}
