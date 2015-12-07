package mmo.common.module.clazz.account.doupo.context;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.database.ReloadActiveCodeRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class A20027_reloadActiveCode extends AContextHandle {

	public A20027_reloadActiveCode() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		ThreadManager.accessDatabase(new ReloadActiveCodeRun());
		loginResult.put(HttpCData.Receipt.message, "加载激活码指令已经发出");
		return sendToClient(loginResult.toString());
	}

}
