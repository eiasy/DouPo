package mmo.common.module.clazz.account.doupo.context;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.ActiveCodeManager;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class A20028_resetActiveCode extends AContextHandle {

	public A20028_resetActiveCode() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		ActiveCodeManager.getInstance().resetActiveCode();
		loginResult.put(HttpCData.Receipt.message, "重置指令已经发出");
		return sendToClient(loginResult.toString());
	}

}
