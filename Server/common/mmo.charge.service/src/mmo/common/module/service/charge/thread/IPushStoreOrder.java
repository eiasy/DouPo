package mmo.common.module.service.charge.thread;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.tools.thread.runnable.IHttpRequest;

public interface IPushStoreOrder extends IHttpRequest {
	public void setReceipt(RoleStoreReceipt receipt);
}
