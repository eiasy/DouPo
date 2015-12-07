package mmo.common.module.clazz.account.doupo.context;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.DeviceFreeze;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class A20021_checkDeviceFreezed extends AContextHandle {
	private String MSG_1_OK = "OK";
	private String MSG_ERR  = "操作失败";

	public A20021_checkDeviceFreezed() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			String imei = request.getParameter(HttpCData.A20001.imei);
			List<DeviceFreeze> values = new ArrayList<DeviceFreeze>();
			DeviceFreeze df = AccountCache.getInstance().getFreezeDevice(imei);
			if (df != null) {
				values.add(df);
			}
			loginResult.put(HttpCData.A20001.cdata, values);

			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
			loginResult.put(HttpCData.A20001.message, MSG_1_OK);
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20021_checkDeviceFreezed", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
