package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.service.ServiceAccount;
import mmo.common.module.account.doupo.cache.thread.IAccountDatabase;

public class UpdatePhoneRun implements IAccountDatabase {
	private UserAccount oldUA;
	private UserAccount newUA;

	public UpdatePhoneRun(UserAccount oldUA, UserAccount newUA) {
		super();
		this.oldUA = oldUA;
		this.newUA = newUA;
	}

	@Override
	public void run() {
		if (oldUA != null) {
			ServiceAccount.getInstance().updatePhone(oldUA);
		}
		if (newUA != null) {
			ServiceAccount.getInstance().updatePhone(newUA);
		}
		oldUA = null;
	}

}
