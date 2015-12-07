package mmo.common.module.clazz.charge.callback.bi;

import mmo.common.bean.advertise.IdfaEvent;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.common.module.clazz.charge.callback.run.ValidateIdfaEventRun;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.IdfaEventHeartbeat;
import mmo.common.module.service.charge.thread.run.AddIdfaEventDBRun;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class BI_10005_shikeClick extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			IdfaEvent event = new IdfaEvent();
			event.setAppId(request.getParameter("appid"));
			event.setIdfa(request.getParameter("IDFA"));
			event.setDeviceMac(request.getParameter("mac"));
			event.setIp(request.getParameter("ip"));
			event.setDeviceOS(request.getParameter("os"));
			JSONObject json = new JSONObject();
			event.setDesc(json.toString());
			event.setCallback(request.getParameter("callback"));
			event.setDeviceUdid(request.getParameter("UDID"));
			event.setEventType(IdfaEvent.TYPE_ADVERTISE);
			event.setEventTag(IdfaEvent.EVENT_CLICK);
			event.setChannelTag("shike");

			LoggerCharge.idfaEvent(event);
			ChargeDatabaseHeartbeat.getInstance().execute(new AddIdfaEventDBRun(event));
			IdfaEventHeartbeat.getInstance().execute(new ValidateIdfaEventRun(session,request,event));
			return null;
		} catch (Exception e) {
			LoggerError.error("处理BI_10005_shikeClick异常", e);
		}
		return sendToClient("400");

	}
}
