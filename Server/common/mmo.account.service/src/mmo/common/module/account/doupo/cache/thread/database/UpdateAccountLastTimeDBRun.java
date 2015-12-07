package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.service.ServiceAccount;
import mmo.tools.thread.runnable.IDatabaseRunnable;

public class UpdateAccountLastTimeDBRun implements IDatabaseRunnable {
	private UserAccount ua;

	public UpdateAccountLastTimeDBRun(UserAccount ua) {
		super();
		this.ua = ua;
	}

	@Override
	public void run() {
		if (ua != null) {
			ServiceAccount.getInstance().updateLastTime(ua);
		}
		ua = null;
	}

}
