package mmo.common.module.service.charge.http;

import java.net.SocketAddress;

import mmo.common.module.service.charge.IChargeSDKCallback;
import mmo.extension.application.ApplicationConfig;
import mmo.http.httpserver.HttpHandler;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.java.MyClassLoader;
import mmo.tools.log.LoggerError;

import org.apache.mina.core.session.IoSession;

public class HttpHandlerLogin implements HttpHandler {
	private static IChargeSDKCallback sdkCallback = null;
	static {
		try {
			MyClassLoader cl = new MyClassLoader();
			cl.setClassPath(ApplicationConfig.getClassDir());
			Class callbackClass = cl.loadClass("mmo.common.module.clazz.charge.callback.ChargeSDKCallback");
			sdkCallback = (IChargeSDKCallback) callbackClass.newInstance();
			sdkCallback.loadClasses();
		} catch (Exception e) {
			LoggerError.error("加载登陆模块异常", e);
		}
	}

	@Override
	public HttpResponseMessage handle(IoSession session, HttpRequestMessage request) {
		String context = request.getContext();
		String addIp = "";
		SocketAddress sa = session.getRemoteAddress();
		if (sa != null) {
			addIp = sa.toString();
		}
		LoggerError.messageLog.warn("context|" + context + "|" + addIp);
		try {
			IChargeSDKCallback sdkCallback = HttpHandlerLogin.sdkCallback;
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

	public static IChargeSDKCallback getSdkCallback() {
		return sdkCallback;
	}

	public static void setSdkCallback(IChargeSDKCallback sdkCallback) {
		HttpHandlerLogin.sdkCallback = sdkCallback;
	}
}
