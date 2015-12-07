package mmo.common.module.service.charge.http;

import java.util.Map;

import mmo.common.bean.role.ChargeRecord;
import mmo.tools.thread.runnable.IHttpRequest;

public abstract class AFlushChargeRecordRun implements IHttpRequest {
	protected static final String FLUSH_CHARGE_RECORD = "FLUSH_CHARGE_RECORD";

	protected ChargeRecord        cr;
	protected Map<String, String> parameter;

	public void setCr(ChargeRecord cr) {
		this.cr = cr;
	}

	public void setParameter(Map<String, String> parameter) {
		this.parameter = parameter;
	}

	@Override
	public void run() {

	}

}
