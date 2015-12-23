package mmo.common.module.sdk.http;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;

import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.thread.runnable.IHttpRequest;

public class HandRequestRun implements IHttpRequest {

	protected IoSession          session;
	protected HttpRequestMessage request;

	public void setSession(IoSession session) {
		this.session = session;
	}

	public void setRequest(HttpRequestMessage request) {
		this.request = request;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param response
	 * @param content
	 */
	public final void sendToClient(String content) {
		if (session != null) {
			HttpResponseMessage response = new HttpResponseMessage();
			response.setContentType("text/plain;charset=utf-8");
			response.appendBody(content);
			session.write(response).addListener(IoFutureListener.CLOSE);
		}
	}
}
