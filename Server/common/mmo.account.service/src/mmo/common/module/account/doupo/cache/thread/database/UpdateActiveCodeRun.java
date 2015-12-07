package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.service.ServiceAccount;
import mmo.common.module.account.doupo.cache.thread.IAccountDatabase;

public class UpdateActiveCodeRun implements IAccountDatabase {
	private UserAccount ua;

	public UpdateActiveCodeRun(UserAccount ua) {
		super();
		this.ua = ua;
	}

	@Override
	public void run() {
		if (ua != null) {
			ServiceAccount.getInstance().updateActiveCode(ua);
		}
		ua = null;
	}

}
