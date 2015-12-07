package mmo.common.module.clazz.charge.callback.run;

import org.apache.mina.core.session.IoSession;

import net.sf.json.JSONObject;
import mmo.common.bean.advertise.IdfaEvent;
import mmo.common.module.service.charge.advertise.IdfaManager;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.tools.net.AHandleRequestRun;

public class ValidateIdfaEventRun extends AHandleRequestRun {
	private IdfaEvent event;

	public void setEvent(IdfaEvent event) {
		this.event = event;
	}

	public ValidateIdfaEventRun() {

	}

	public ValidateIdfaEventRun(IoSession session, HttpRequestMessage request, IdfaEvent event) {
		super();
		this.event = event;
		setSession(session);
		setRequest(request);
	}

	@Override
	public void run() {
		boolean firstActive = IdfaManager.getInstance().validateIdfaEvent(event);
		if (IdfaEvent.TYPE_GAME_EVENT.equalsIgnoreCase(event.getEventType())) {
			JSONObject json = new JSONObject();
			if (firstActive) {
				json.put("message", "ok");
				json.put("first_active", true);
			} else {
				json.put("message", "ok");
				json.put("first_active", false);
			}
			sendToClient(json.toString());
		} else {
			switch (event.getChannelTag()) {
				case "shike": {
					sendToClient("200");
					break;
				}
				case "qianka": {
					sendToClient("200");
					break;
				}
				case "zhangyue": {
					sendToClient("200");
					break;
				}
				default: {
					sendToClient("200");
				}
			}
		}
	}

}
