package mmo.common.module.service.charge.thread;

import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.IOrderform;
import mmo.tools.thread.heartbeat.AHeartbeat;

public class OrderformHeartbeat extends AHeartbeat {
	private final static OrderformHeartbeat instance = new OrderformHeartbeat();

	public final static OrderformHeartbeat getInstance() {
		return instance;
	}

	private OrderformHeartbeat() {
		addExecuteEntity(OrderformManager.getInstance());
	}

	public void execute(IOrderform run) {
		addEvent(run);
	}
}
