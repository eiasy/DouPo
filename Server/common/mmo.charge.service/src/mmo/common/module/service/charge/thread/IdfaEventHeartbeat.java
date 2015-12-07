package mmo.common.module.service.charge.thread;

import mmo.tools.net.AHandleRequestRun;
import mmo.tools.thread.heartbeat.AHeartbeat;

public class IdfaEventHeartbeat extends AHeartbeat {

	private final static IdfaEventHeartbeat instance = new IdfaEventHeartbeat();

	public final static IdfaEventHeartbeat getInstance() {
		return instance;
	}

	private IdfaEventHeartbeat() {

	}

	public void execute(AHandleRequestRun run) {
		addEvent(run);
	}

	public void callback() {
	}
}
