package mmo.common.module.clazz.callback;

import mmo.common.account.HttpCData;
import mmo.common.module.sdk.http.ClassloaderRun;
import mmo.common.module.sdk.token.TokenHeartbeat;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class ContextReloadCalss {

	public ContextReloadCalss() {
		super();
	}

	public final HttpResponseMessage handle(IoSession session, HttpRequestMessage request) {
		TokenHeartbeat.getInstance().execute(new ClassloaderRun());
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(HttpCData.A20001.result, 1);
		jsonResult.put(HttpCData.A20001.message, "加载指令已经发出");
		return sendToClient(jsonResult.toString());
	}

	public final HttpResponseMessage sendToClient(String content) {
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain;charset=utf-8");
		response.appendBody(content);
		return response;
	}
}
