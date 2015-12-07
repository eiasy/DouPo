package mmo.common.module.clazz.event.context;

import mmo.common.bean.bi.EventDefault;
import mmo.common.service.eventcenter.thread.HeartbeatEventAccount;
import mmo.common.service.eventcenter.thread.HeartbeatEventCharge;
import mmo.common.service.eventcenter.thread.HeartbeatEventDefault;
import mmo.common.service.eventcenter.thread.run.AddEventDBRun;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class Event_Trigger extends AEventContextHandle {
	private static String eventSource = "eventSource";
	private static String eventTag = "eventTag";
	private static String appTag = "appTag";
	private static String platform = "platform";
	private static String serverTag = "serverTag";
	private static String channelTag = "channelTag";
	private static String channelSub = "channelSub";
	private static String accountId = "accountId";
	private static String userId = "userId";
	private static String roleId = "roleId";
	private static String roleName = "roleName";
	private static String roleLevel = "roleLevel";
	private static String vipLevel = "vipLevel";
	private static String value1string = "value1string";
	private static String value2string = "value2string";
	private static String value3string = "value3string";
	private static String value4string = "value4string";
	private static String value5string = "value5string";
	private static String value6string = "value6string";
	private static String value7string = "value7string";
	private static String value8string = "value8string";
	private static String key1int = "key1int";
	private static String value1int = "value1int";
	private static String key2int = "key2int";
	private static String value2int = "value2int";
	private static String key3int = "key3int";
	private static String value3int = "value3int";
	private static String key1long = "key1long";
	private static String value1long = "value1long";
	private static String key1doublue = "key1doublue";
	private static String value1double = "value1double";

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			EventDefault event = new EventDefault();
			event.setEventSource(request.getParameter(eventSource));
			event.setEventTag(request.getParameter(eventTag));
			event.setAppTag(request.getParameter(appTag));
			event.setPlatform(request.getParameter(platform));
			event.setServerTag(request.getParameter(serverTag));
			event.setChannelTag(request.getParameter(channelTag));
			event.setChannelSub(request.getParameter(channelSub));
			event.setAccountId(request.getIntParameter(accountId));
			event.setUserId(request.getParameter(userId));
			event.setRoleId(request.getIntParameter(roleId));
			event.setRoleName(request.getParameter(roleName));
			event.setRoleLevel(request.getIntParameter(roleLevel));
			event.setVipLevel(request.getIntParameter(vipLevel));
			event.setValue1string(request.getParameter(value1string));
			event.setValue2string(request.getParameter(value2string));
			event.setValue3string(request.getParameter(value3string));
			event.setValue4string(request.getParameter(value4string));
			event.setValue5string(request.getParameter(value5string));
			event.setValue6string(request.getParameter(value6string));
			event.setValue7string(request.getParameter(value7string));
			event.setValue8string(request.getParameter(value8string));
			event.setKey1int(request.getParameter(key1int));
			event.setValue1int(request.getIntParameter(value1int));
			event.setKey2int(request.getParameter(key2int));
			event.setValue2int(request.getIntParameter(value2int));
			event.setKey3int(request.getParameter(key3int));
			event.setValue3int(request.getIntParameter(value3int));
			event.setKey1long(request.getParameter(key1long));
			event.setValue1long(request.getLongParameter(value1long));
			event.setKey1double(request.getParameter(key1doublue));
			event.setValue1double(request.getDoubleParameter(value1double));

			JSONObject json = new JSONObject();
			json.put("code", "ok");
			json.put("message", "ok");
			LoggerCharge.biEvent(event);
			switch (event.getEventSource()) {
				case "account": {
					HeartbeatEventAccount.getInstance().execute(new AddEventDBRun(event));
					break;
				}
				case "charge": {
					HeartbeatEventCharge.getInstance().execute(new AddEventDBRun(event));
					break;
				}
				default: {
					HeartbeatEventDefault.getInstance().execute(new AddEventDBRun(event));
					break;
				}
			}
			sendToClient(session, json.toString());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			LoggerError.error("处理GAME_EVENT异常", e);
		}
		JSONObject json = new JSONObject();
		json.put("code", "fail");
		json.put("message", "fail");
		System.out.println("----- "+request.getParameter(eventSource));

		sendToClient(session, json.toString());
		return null;
	}
}
