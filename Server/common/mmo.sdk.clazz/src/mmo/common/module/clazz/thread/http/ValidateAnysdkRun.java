package mmo.common.module.clazz.thread.http;

import mmo.common.module.clazz.callback.chukong.ChukongManager;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.runnable.IHttpRequest;

import org.apache.mina.core.session.IoSession;

public class ValidateAnysdkRun implements IHttpRequest {
	private ChukongManager chukong;
	private IoSession session;
	private HttpRequestMessage request;

	public ValidateAnysdkRun(ChukongManager chukong, IoSession session, HttpRequestMessage request) {
		super();
		this.chukong = chukong;
		this.session = session;
		this.request = request;
	}

	@Override
	public void run() {
		try {
			if (chukong != null) {
				chukong.callback(session, request);
			}
		} catch (Exception e) {
			LoggerError.error("验证anysdk登录异常", e);
		}
	}

}
