package mmo.common.module.clazz.account.doupo.context;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class A20010_bindPhoneCheck extends AContextHandle {

	public A20010_bindPhoneCheck() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			String phone = request.getParameter(HttpCData.A20001.phone);
			loginResult.put(HttpCData.A20001.status, AccountCache.getInstance().isPhoneBinded(phone) ? HttpCData.A20001.RT_1_OK
			        : HttpCData.A20001.RT_2_NO);
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20010_bindPhoneCheck", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			return sendToClient(loginResult.toString());
		}
	}

}
