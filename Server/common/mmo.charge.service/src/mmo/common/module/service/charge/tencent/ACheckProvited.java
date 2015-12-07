package mmo.common.module.service.charge.tencent;

import mmo.common.bean.role.QQChargeRecord;
import mmo.tools.thread.runnable.IHttpRequest;

abstract public class ACheckProvited implements IHttpRequest {

	protected QQChargeRecord cr;

	public ACheckProvited() {

	}

	public QQChargeRecord getCr() {
		return cr;
	}

	public void setCr(QQChargeRecord cr) {
		this.cr = cr;
	}


}
