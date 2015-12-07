package mmo.common.module.clazz.callback;

import mmo.common.account.HttpCData;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.FileUtil;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class ContextReloadConfig extends AContextHandle {

	public ContextReloadConfig() {
		super();
	}

	public final HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		ProjectCofigs.init(FileUtil.ROOT_DIR + FileUtil.FILE_SEPARATOR + "config" + FileUtil.FILE_SEPARATOR + "configs.xml");
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(HttpCData.A20001.result, 1);
		jsonResult.put(HttpCData.A20001.message, "加载配置项指令已经发出");
		return sendToClient(jsonResult.toString());
	}

}
