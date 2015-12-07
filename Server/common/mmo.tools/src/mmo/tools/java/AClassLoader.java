package mmo.tools.java;

import mmo.http.ICallback;
import mmo.http.httpserver.HttpResponseMessage;

public abstract class AClassLoader implements ICallback, IReloadClasses {

	public void execute() {

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
