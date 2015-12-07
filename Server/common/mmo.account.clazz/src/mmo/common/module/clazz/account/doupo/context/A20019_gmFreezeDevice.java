package mmo.common.module.clazz.account.doupo.context;

import java.util.Date;

import mmo.common.account.HttpCData;
import mmo.common.module.account.doupo.cache.account.bean.DeviceFreeze;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.ThreadManager;
import mmo.common.module.account.doupo.cache.thread.database.FreezeDeviceAddDBRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.DateUtil;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class A20019_gmFreezeDevice extends AContextHandle {
	private String MSG_ERR = "操作失败";

	public A20019_gmFreezeDevice() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			String imei =request.getParameter(HttpCData.A20001.imei);
			int day =  getInt(request, HttpCData.A20001.days);
			long freezeTime = System.currentTimeMillis() + day * 24 * 60 * 60 * 1000;
			
			DeviceFreeze df = AccountCache.getInstance().getFreezeDevice(imei);
			if (df == null) {
				if (day < 1) {
					loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
					loginResult.put(HttpCData.A20001.message, "冻结设备IMEI【" + imei + "】操作失败，冻结天数为负值");
				} else {
					df = new DeviceFreeze();
					df.setDeviceImei(imei);
					df.setTimeFreeze(freezeTime);
					df.setTimeOperate(System.currentTimeMillis());
					AccountCache.getInstance().addDeviceFreeze(df);
					ThreadManager.accessDatabase(new FreezeDeviceAddDBRun(df, true));
				}
			} else {
				df.setTimeFreeze(freezeTime);
				df.setTimeOperate(System.currentTimeMillis());
				ThreadManager.accessDatabase(new FreezeDeviceAddDBRun(df, false));
			}
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
			loginResult.put(HttpCData.A20001.message, "设备IMEI【" + imei + "】冻结截止时间为：" + DateUtil.formatDate(new Date(freezeTime)));

			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20019_gmFreezeDevice", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
