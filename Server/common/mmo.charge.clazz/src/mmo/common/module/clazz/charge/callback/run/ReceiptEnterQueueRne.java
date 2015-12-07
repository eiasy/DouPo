package mmo.common.module.clazz.charge.callback.run;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.module.service.charge.thread.AppStoreHeartbeat;
import mmo.common.module.service.charge.thread.run.IStoreRunnable;

public class ReceiptEnterQueueRne implements IStoreRunnable {
	private RoleStoreReceipt receipt;

	public ReceiptEnterQueueRne(RoleStoreReceipt receipt) {
		super();
		this.receipt = receipt;
	}

	@Override
	public void run() {
		AppStoreHeartbeat.getInstance().enterQueue(receipt);
	}

}
