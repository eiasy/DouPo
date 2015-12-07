package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.charge.AccountCharge;
import mmo.common.module.account.doupo.cache.account.service.ServiceAccountCharge;
import mmo.tools.thread.runnable.IDatabaseRunnable;

public class UpdateAccountChargeRun implements IDatabaseRunnable {
	private AccountCharge ac;

	public UpdateAccountChargeRun(AccountCharge ac) {
		super();
		this.ac = ac;
	}

	@Override
	public void run() {
		ServiceAccountCharge.getInstance().updateAccountCharge(ac);
		ac = null;
	}

}
