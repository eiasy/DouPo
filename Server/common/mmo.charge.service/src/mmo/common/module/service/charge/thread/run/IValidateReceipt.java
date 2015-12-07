package mmo.common.module.service.charge.thread.run;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.tools.thread.runnable.IHttpRequest;

public interface IValidateReceipt extends IStoreRunnable,IHttpRequest {
	public void setReceipt(RoleStoreReceipt receipt);
}
