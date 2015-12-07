package mmo.common.module.account.doupo.http;

import mmo.extension.application.ApplicationConfig;
import mmo.http.httpserver.HttpHandler;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.java.AClassLoader;
import mmo.tools.java.MyClassLoader;
import mmo.tools.log.LoggerError;

import org.apache.mina.core.session.IoSession;

public class HttpHandlerLogin implements HttpHandler {
	private static AClassLoader sdkCallback = null;
	static {
		try {
			MyClassLoader cl = new MyClassLoader();
			cl.setClassPath(ApplicationConfig.getClassDir());
			Class callbackClass = cl.loadClass("mmo.common.module.clazz.account.doupo.callback.AccountCallback");
			sdkCallback = (AClassLoader) callbackClass.newInstance();
			sdkCallback.loadClasses();
		} catch (Exception e) {
			LoggerError.error("加载登陆模块异常", e);
		}
	}

	@Override
	public HttpResponseMessage handle(IoSession session, HttpRequestMessage request) {
		String context = request.getContext();
		LoggerError.messageLog.warn("context|" + context);
		try {
			AClassLoader sdkCallback = HttpHandlerLogin.sdkCallback;
			if (sdkCallback != null) {
				return sdkCallback.callback(session, context, request);
			} else {
				HttpResponseMessage response = new HttpResponseMessage();
				response.setContentType("text/plain");
				response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
				return response;
			}
		} catch (Exception e) {
			LoggerError.error("渠道回调异常", e);
			HttpResponseMessage response = new HttpResponseMessage();
			response.setContentType("text/plain");
			response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
			return response;
		}
	}

	public static AClassLoader getSdkCallback() {
		return sdkCallback;
	}

	public static void setSdkCallback(AClassLoader sdkCallback) {
		HttpHandlerLogin.sdkCallback = sdkCallback;
	}
}
