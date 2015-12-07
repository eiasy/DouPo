package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.service.ServiceAccount;
import mmo.common.module.account.doupo.cache.thread.IAccountDatabase;

public class UpdatePasswordStateRun implements IAccountDatabase {
	private UserAccount ua;

	public UpdatePasswordStateRun(UserAccount ua) {
		super();
		this.ua = ua;
	}

	@Override
	public void run() {
		if (ua != null) {
			ServiceAccount.getInstance().updatePasswordState(ua);
		}
		ua = null;
	}

}
