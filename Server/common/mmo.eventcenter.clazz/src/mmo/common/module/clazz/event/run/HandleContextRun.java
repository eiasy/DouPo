package mmo.common.module.clazz.event.run;

import mmo.common.module.clazz.event.context.AEventContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.tools.thread.runnable.CRunnable;

import org.apache.mina.core.session.IoSession;

public class HandleContextRun implements CRunnable {
	private IoSession session;
	private HttpRequestMessage request;
	private AEventContextHandle handler;

	public HandleContextRun(AEventContextHandle handler, IoSession session, HttpRequestMessage request) {
		super();
		this.handler = handler;
		this.session = session;
		this.request = request;
	}

	@Override
	public void run() {
		handler.callback(session, request);
	}

}
