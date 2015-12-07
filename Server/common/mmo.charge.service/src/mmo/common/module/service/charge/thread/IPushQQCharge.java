package mmo.common.module.service.charge.thread;

import mmo.common.bean.role.QQChargeRecord;
import mmo.tools.thread.runnable.IHttpRequest;

public interface IPushQQCharge extends IHttpRequest {
	public void setQQChargeRecord(QQChargeRecord cr);
}
