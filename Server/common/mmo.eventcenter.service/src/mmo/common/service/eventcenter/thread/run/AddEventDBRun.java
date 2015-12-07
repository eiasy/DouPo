package mmo.common.service.eventcenter.thread.run;

import mmo.common.bean.bi.EventDefault;
import mmo.common.service.eventcenter.thread.HeartbeantEventRun;
import mmo.common.service.eventcenter.thread.IChargeDatabase;

public class AddEventDBRun implements IChargeDatabase {
	private EventDefault event;
	private HeartbeantEventRun heartbeat;

	public AddEventDBRun(EventDefault event) {
		this.event = event;
	}

	public void setHeartbeat(HeartbeantEventRun heartbeat) {
		this.heartbeat = heartbeat;
	}

	@Override
	public void run() {
		if (heartbeat != null) {
			heartbeat.append(event.getData());
		}
	}

}
