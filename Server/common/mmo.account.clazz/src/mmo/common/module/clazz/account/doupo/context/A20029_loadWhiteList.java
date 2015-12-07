package mmo.common.module.clazz.account.doupo.context;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.ParseWhiteList;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class A20029_loadWhiteList extends AContextHandle {

	public A20029_loadWhiteList() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		ParseWhiteList.init(ProjectCofigs.getFile("white_list"));
		loginResult.put(HttpCData.Receipt.message, "重置指令已经发出");
		return sendToClient(loginResult.toString());
	}

}
