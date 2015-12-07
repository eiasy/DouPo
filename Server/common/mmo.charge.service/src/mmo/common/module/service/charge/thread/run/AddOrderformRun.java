package mmo.common.module.service.charge.thread.run;

import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.bean.IOrderform;

public class AddOrderformRun implements IOrderform {
	private ChargeOrderform orderform;

	public AddOrderformRun(ChargeOrderform orderform) {
		this.orderform = orderform;
	}

	@Override
	public void run() {
		OrderformManager.getInstance().addOrderform(orderform);
	}

}
