package mmo.common.module.service.charge.thread.run;

import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.service.OrderformService;
import mmo.common.module.service.charge.thread.IChargeDatabase;

public class UpdateOrderform2DBRun implements IChargeDatabase {
	private ChargeOrderform orderform;

	public UpdateOrderform2DBRun(ChargeOrderform orderform) {
		this.orderform = orderform;
	}

	@Override
	public void run() {
		if (orderform != null) {
			OrderformService.getInstance().updateChargeOrderform(orderform);
			orderform = null;
		}
	}
}
