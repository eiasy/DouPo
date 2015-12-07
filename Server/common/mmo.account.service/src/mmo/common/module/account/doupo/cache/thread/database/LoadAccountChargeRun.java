package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.charge.AccountChargeManager;
import mmo.tools.thread.runnable.IDatabaseRunnable;

public class LoadAccountChargeRun implements IDatabaseRunnable {

	public LoadAccountChargeRun() {
		super();
	}

	@Override
	public void run() {
		AccountChargeManager.getInstance().loadAccountCharge();
	}

}
