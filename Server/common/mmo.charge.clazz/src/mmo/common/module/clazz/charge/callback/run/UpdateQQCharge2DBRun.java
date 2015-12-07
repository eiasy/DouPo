package mmo.common.module.clazz.charge.callback.run;

import mmo.common.bean.role.QQChargeRecord;
import mmo.common.module.service.charge.service.Service;
import mmo.common.module.service.charge.thread.IChargeDatabase;

public class UpdateQQCharge2DBRun implements IChargeDatabase {
	private QQChargeRecord cr;

	public UpdateQQCharge2DBRun(QQChargeRecord cr) {
		this.cr = cr;
	}

	@Override
	public void run() {
		if (cr != null) {
			Service.getInstance().updateQQCharge(cr);
			cr = null;
		}
	}
}

