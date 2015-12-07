package mmo.common.module.service.charge.thread;

import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.thread.heartbeat.AHeartbeat;

public class ChargeDatabaseHeartbeat extends AHeartbeat {
	private final static ChargeDatabaseHeartbeat instance = new ChargeDatabaseHeartbeat();

	public final static ChargeDatabaseHeartbeat getInstance() {
		return instance;
	}

	private ChargeDatabaseHeartbeat() {
	}

	public void execute(IChargeDatabase run) {
		addEvent(run);
	}

	public void callback() {
		LoggerCharge.nullLog();
	}
}
