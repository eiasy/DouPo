package mmo.common.module.service.charge.thread.run;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.module.service.charge.service.ServiceCharge;
import mmo.common.module.service.charge.thread.IChargeDatabase;

public class UpdateCharge2DBRun implements IChargeDatabase {
	private ChargeRecord cr;

	public UpdateCharge2DBRun(ChargeRecord cr) {
		this.cr = cr;
	}

	@Override
	public void run() {
		if (cr != null) {
			ServiceCharge.getInstance().updateChargeRecord(cr);
			cr = null;
		}
	}
}
