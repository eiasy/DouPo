package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.service.ServiceAccount;
import mmo.tools.thread.runnable.IDatabaseRunnable;

public class ReloadActiveCodeRun implements IDatabaseRunnable {

	public ReloadActiveCodeRun() {
		super();
	}

	@Override
	public void run() {
		ServiceAccount.getInstance().loadActiveCode();
	}

}
