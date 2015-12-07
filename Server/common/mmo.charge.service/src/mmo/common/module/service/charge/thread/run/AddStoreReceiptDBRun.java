package mmo.common.module.service.charge.thread.run;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.module.service.charge.http.HttpHandlerLogin;
import mmo.common.module.service.charge.service.Service;
import mmo.common.module.service.charge.thread.IChargeDatabase;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.tools.log.LoggerError;

public class AddStoreReceiptDBRun implements IChargeDatabase {
	private RoleStoreReceipt receipt;

	public AddStoreReceiptDBRun(RoleStoreReceipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public void run() {
		if (Service.getInstance().addRoleStoreReceipt(receipt)) {
			try {
				IValidateReceipt run = (IValidateReceipt) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.run.ValidateReceiptRun").newInstance();
				run.setReceipt(receipt);
				ThreadManager.requestHttp("apple",run);
			} catch (Exception e) {
				LoggerError.error("验证AppStore票据出现异常", e);
			}
		}
	}

}
