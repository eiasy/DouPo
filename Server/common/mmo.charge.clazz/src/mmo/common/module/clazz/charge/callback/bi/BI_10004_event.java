package mmo.common.module.clazz.charge.callback.bi;

import java.net.SocketAddress;

import mmo.common.bean.advertise.IdfaEvent;
import mmo.common.http.parameter.HttpParameter;
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

public class BI_10004_event extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			String app_id = request.getParameter(HttpParameter.GameEvent.app_id);
			String channel_tag = request.getParameter(HttpParameter.GameEvent.channel_tag);
			String idfa = request.getParameter(HttpParameter.GameEvent.idfa);
			String device_mac = request.getParameter(HttpParameter.GameEvent.device_mac);
			String device_udid = request.getParameter(HttpParameter.GameEvent.device_udid);
			String device_imei = request.getParameter(HttpParameter.GameEvent.device_imei);
			String device_serial = request.getParameter(HttpParameter.GameEvent.device_serial);
			String device_ua = request.getParameter(HttpParameter.GameEvent.device_ua);
			String device_os = request.getParameter(HttpParameter.GameEvent.device_os);
			String device_os_version = request.getParameter(HttpParameter.GameEvent.device_os_version);
			String phone_code = request.getParameter(HttpParameter.GameEvent.phone_code);
			String event_tag = request.getParameter(HttpParameter.GameEvent.event_tag);
			String desc = request.getParameter(HttpParameter.GameEvent.desc);
			String key_1 = request.getParameter(HttpParameter.GameEvent.key_1);
			String value_1 = request.getParameter(HttpParameter.GameEvent.value_1);
			String key_2 = request.getParameter(HttpParameter.GameEvent.key_2);
			String value_2 = request.getParameter(HttpParameter.GameEvent.value_2);
			String key_3 = request.getParameter(HttpParameter.GameEvent.key_3);
			String value_3 = request.getParameter(HttpParameter.GameEvent.value_3);
			String key_4 = request.getParameter(HttpParameter.GameEvent.key_4);
			String value_4 = request.getParameter(HttpParameter.GameEvent.value_4);
			String key_5 = request.getParameter(HttpParameter.GameEvent.key_5);
			String value_5 = request.getParameter(HttpParameter.GameEvent.value_5);
			if (app_id.length() < 1 && device_imei.length() < 1 && device_mac.length() < 1 && idfa.length() < 1) {
				return sendToClient("FAILURE");
			}
			JSONObject json = new JSONObject();
			json.put("json", desc);
			String addIp = "";
			SocketAddress sa = session.getRemoteAddress();
			if (sa != null) {
				addIp = sa.toString();
			}
			String remoteAddress = addIp;
			if (remoteAddress.contains("/") && remoteAddress.contains(":")) {
				remoteAddress = remoteAddress.substring(remoteAddress.indexOf('/') + 1, remoteAddress.indexOf(':'));
			}
			IdfaEvent event = new IdfaEvent(IdfaEvent.TYPE_GAME_EVENT, event_tag, app_id, channel_tag, idfa, device_mac, device_imei, remoteAddress,
			        System.currentTimeMillis(), device_udid, device_serial, device_ua, device_os, device_os_version, phone_code, json.toString());
			event.addCustomValue(key_1, value_1);
			event.addCustomValue(key_2, value_2);
			event.addCustomValue(key_3, value_3);
			event.addCustomValue(key_4, value_4);
			event.addCustomValue(key_5, value_5);
			LoggerCharge.idfaEvent(event);
			ChargeDatabaseHeartbeat.getInstance().execute(new AddIdfaEventDBRun(event));
			IdfaEventHeartbeat.getInstance().execute(new ValidateIdfaEventRun(session,request,event));
			return null;
		} catch (Exception e) {
			LoggerError.error("处理GAME_EVENT异常", e);
		}
		return sendToClient("FAILURE");
	}
}
