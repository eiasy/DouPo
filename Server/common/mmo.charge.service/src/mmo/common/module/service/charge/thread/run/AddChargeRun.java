package mmo.common.module.service.charge.thread.run;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeState;
import mmo.common.module.service.charge.ChargeManager;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.service.ServiceCharge;
import mmo.common.module.service.charge.thread.IChargeDatabase;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.log.LoggerError;

public class AddChargeRun implements IChargeDatabase {
	private ChargeRecord cr;

	public AddChargeRun(ChargeRecord cr) {
		this.cr = cr;
	}

	@Override
	public void run() {
		if (cr != null) {
			try {
				if (ServiceCharge.getInstance().addChargeRecord(cr)) {
					switch (cr.getState()) {
						default: {
							ThreadManager.newCharge(cr);
							OrderformManager.getInstance().addChargeRecord(cr);
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("存储充值记录报错", e);
			}
			LoggerCharge.chargeResult(cr, 0, 0, ";");
		}
		cr = null;
	}

	public void setChargeRecord(ChargeRecord cr) {
		this.cr = cr;
	}
}
