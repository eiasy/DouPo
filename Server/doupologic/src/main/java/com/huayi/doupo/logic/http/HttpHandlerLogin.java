package com.huayi.doupo.logic.http;

import mmo.http.httpserver.HttpHandler;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;

import org.apache.mina.core.session.IoSession;

public class HttpHandlerLogin implements HttpHandler {
	private static ILoginSDKCallback sdkCallback = new LoginSDKCallback();

	@Override
	public HttpResponseMessage handle(IoSession session, HttpRequestMessage request) {
		String context = request.getContext();
		try {
			ILoginSDKCallback sdkCallback = HttpHandlerLogin.sdkCallback;
			if (sdkCallback != null) {
				return sdkCallback.callback(session, context, request);
			} else {
				HttpResponseMessage response = new HttpResponseMessage();
				response.setContentType("text/plain");
				response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
				return response;
			}
		} catch (Exception e) {
			HttpResponseMessage response = new HttpResponseMessage();
			response.setContentType("text/plain");
			response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
			return response;
		}
	}

	public static ILoginSDKCallback getSdkCallback() {
		return sdkCallback;
	}

	public static void setSdkCallback(ILoginSDKCallback sdkCallback) {
		HttpHandlerLogin.sdkCallback = sdkCallback;
	}

}
