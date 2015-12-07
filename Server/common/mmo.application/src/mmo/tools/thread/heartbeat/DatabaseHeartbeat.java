package mmo.tools.thread.heartbeat;

import mmo.tools.thread.runnable.IDatabaseRunnable;

public class DatabaseHeartbeat extends AHeartbeat {
	public void execute(IDatabaseRunnable run) {
		super.addEvent(run);
	}

}
