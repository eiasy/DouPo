package mmo.common.module.clazz.event.context;

import java.util.HashMap;
import java.util.List;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;

import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;

public abstract class AEventContextHandle extends AContextHandle {

	/**
	 * 验证渠道传过来的参数
	 * 
	 * @param request
	 * @return NULL代表验证通过，否则验证失败，把该消息返回给渠道
	 */
	abstract protected HttpResponseMessage checkParameters(HttpRequestMessage request);

	public void printParameter(HttpRequestMessage request) {
		List<String> names = request.getParameterNames();
		HashMap<String, String> params = new HashMap<String, String>();
		for (String k : names) {
			params.put(k, request.getParameter(k));
			LoggerError.warn("k=" + k + ", v=" + request.getParameter(k));
		}
	}

	/**
	 * 
	 * @param response
	 * @param content
	 */
	public void sendToClient(IoSession session, String content) {
		if (session != null) {
			HttpResponseMessage response = new HttpResponseMessage();
			response.setContentType("text/plain;charset=utf-8");
			response.appendBody(content);
			session.write(response).addListener(IoFutureListener.CLOSE);
		}
	}
}
