package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.service.ServiceAccount;
import mmo.tools.thread.runnable.IDatabaseRunnable;

public class UserAlertPasswordRun implements IDatabaseRunnable {
	private UserAccount ua;

	public UserAlertPasswordRun(UserAccount ua) {
		super();
		this.ua = ua;
	}

	@Override
	public void run() {
		if (ua != null) {
			ServiceAccount.getInstance().updateUserPassword(ua);
		}
		ua = null;
	}

}
