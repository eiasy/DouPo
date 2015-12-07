package mmo.common.module.clazz.account.doupo.context;

import java.util.Collection;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.DeviceFreeze;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class A20020_selectFreezedDevice extends AContextHandle {
	private String MSG_1_OK = "OK";
	private String MSG_ERR  = "操作失败";

	public A20020_selectFreezedDevice() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			Collection<DeviceFreeze> values = AccountCache.getInstance().getDevicesFreeze().values();
			loginResult.put(HttpCData.A20001.cdata, values);

			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
			loginResult.put(HttpCData.A20001.message, MSG_1_OK);
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20020_selectFreezedDevice", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
