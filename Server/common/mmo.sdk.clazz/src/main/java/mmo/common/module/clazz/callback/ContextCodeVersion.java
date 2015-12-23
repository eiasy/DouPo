package mmo.common.module.clazz.callback;

import mmo.common.account.HttpCData;
import mmo.common.module.sdk.xml.platform.XmlPlatform;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class ContextCodeVersion extends AContextHandle {

	public ContextCodeVersion() {
		super();
	}

	public final HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		XmlPlatform.init(ProjectCofigs.getFile("client_version"));
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(HttpCData.A20001.result, 1);
		jsonResult.put(HttpCData.A20001.message, "加载代码版本号指令已经发出");
		return sendToClient(jsonResult.toString());
	}

}
