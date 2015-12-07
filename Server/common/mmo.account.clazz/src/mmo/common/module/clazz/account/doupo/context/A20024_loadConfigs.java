package mmo.common.module.clazz.account.doupo.context;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.FileUtil;
import net.sf.json.JSONObject;

public class A20024_loadConfigs extends AContextHandle {
	private String MSG_1_OK = "OK";
	private String MSG_ERR  = "操作失败";

	public A20024_loadConfigs() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			ProjectCofigs.init(FileUtil.ROOT_DIR + FileUtil.FILE_SEPARATOR + "config" + FileUtil.FILE_SEPARATOR + "configs.xml");
			AccountCache.getInstance().resetPermitChannels(ProjectCofigs.getParameter("white_channels"));
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
			loginResult.put(HttpCData.A20001.message, MSG_1_OK);
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20024_loadConfigs", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
