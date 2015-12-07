package mmo.common.module.clazz.charge.callback.run;

import mmo.common.bean.role.QQChargeRecord;
import mmo.common.module.service.charge.service.Service;
import mmo.common.module.service.charge.thread.IChargeDatabase;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.tools.log.LoggerError;

public class AddQQCharge2DBRun implements IChargeDatabase {
	private QQChargeRecord cr;

	public AddQQCharge2DBRun(QQChargeRecord cr) {
		this.cr = cr;
	}

	@Override
	public void run() {
		if (cr != null) {
			try {
				if (Service.getInstance().addQQCharge(cr) && cr.getStatus() == QQChargeRecord.STATUS_0_NEW) {
					ThreadManager.checkChargeRecord(cr);
				}
			} catch (Exception e) {
				LoggerError.error("存储QQ充值记录报错", e);
			}
		}
		cr = null;
	}

	public void setChargeRecord(QQChargeRecord cr) {
		this.cr = cr;
	}
}
