package com.huayi.doupo.logic.http;

import java.util.HashMap;
import java.util.Map;

import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;

import org.apache.mina.core.session.IoSession;

public class LoginSDKCallback implements ILoginSDKCallback {
	/** 验证触控账号登录 */
	private static final String C_30000 = "30000";
	private static final String C_30001 = "30001";
	private Map<String, Class>  allClass    = new HashMap<String, Class>();
	private AContextHandle      loadServer  = null;
	private AContextHandle      gmLoadServer  = null;

	public LoginSDKCallback(){
		reloadClasses();
	}
	public void reloadClasses() {
		initChannenSDK();
	}

	public Class getClass(String fullName) {
		return allClass.get(fullName);
	}

	public void loadClasses() {
		initChannenSDK();
	}

	private void initChannenSDK() {
		try {
			loadServer = new ContextReloadServerList();
			gmLoadServer = new GmContextReload();
		} catch (Exception e) {
		}
	}

	public HttpResponseMessage callback(IoSession session, String context, HttpRequestMessage request) {
		try {
			if (context != null) {
				switch (context) {
					case C_30000: {
						return loadServer.callback(session, request);
					}
					case C_30001: {
						return gmLoadServer.callback(session, request);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain");
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
		return response;
	}

}
