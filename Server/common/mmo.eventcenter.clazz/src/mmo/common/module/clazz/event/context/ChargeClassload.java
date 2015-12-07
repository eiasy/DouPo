package mmo.common.module.clazz.event.context;

import mmo.common.service.eventcenter.thread.ThreadManager;
import mmo.common.service.eventcenter.thread.run.ClassloaderRun;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;

public class ChargeClassload {

	private final String ERR_SUCC = "success";

	public ChargeClassload() {
	}

	public HttpResponseMessage callback(HttpRequestMessage request) {
		try {
			ThreadManager.handleContext(new ClassloaderRun());
			return sendToClient(ERR_SUCC);
		} catch (Exception e) {
			LoggerError.error("LoadServerCharges", e);
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
