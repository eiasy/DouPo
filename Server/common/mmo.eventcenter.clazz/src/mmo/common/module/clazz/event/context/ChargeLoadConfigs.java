package mmo.common.module.clazz.event.context;

import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.FileUtil;

public class ChargeLoadConfigs {

	private final String ERR_SUCC = "success";

	public ChargeLoadConfigs() {
	}

	public HttpResponseMessage callback(HttpRequestMessage request) {
		try {
			ProjectCofigs.init(FileUtil.ROOT_DIR + FileUtil.FILE_SEPARATOR + "config" + FileUtil.FILE_SEPARATOR + "configs.xml");
			LoggerError.messageLog.warn("ChargeLoadConfigs");
			return sendToClient(ERR_SUCC);
		} catch (Exception e) {
			LoggerError.error("ChargeLoadConfigs", e);
			return sendToClient("err:parameter");
		}
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content) {
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain;charset=utf-8");
		response.appendBody(content);
		return response;
	}
}
