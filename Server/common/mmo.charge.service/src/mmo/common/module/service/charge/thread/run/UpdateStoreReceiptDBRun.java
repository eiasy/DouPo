package mmo.common.module.service.charge.thread.run;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.module.service.charge.service.Service;
import mmo.common.module.service.charge.thread.IChargeDatabase;

public class UpdateStoreReceiptDBRun implements IChargeDatabase {
	private RoleStoreReceipt receipt;

	public UpdateStoreReceiptDBRun(RoleStoreReceipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public void run() {
		Service.getInstance().updateRoleStoreReceipt(receipt);
	}

}
