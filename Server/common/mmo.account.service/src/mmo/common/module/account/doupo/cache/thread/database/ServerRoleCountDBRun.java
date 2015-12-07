package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.service.ServiceServerRoleCount;
import mmo.tools.thread.runnable.IDatabaseRunnable;

public class ServerRoleCountDBRun implements IDatabaseRunnable {
	private int    accountId;
	private String serverRoleCount;

	public ServerRoleCountDBRun(int accountId, String serverRoleCount) {
		super();
		this.accountId = accountId;
		this.serverRoleCount = serverRoleCount;
	}

	@Override
	public void run() {
		ServiceServerRoleCount.getInstance().addOrUpdate(accountId, serverRoleCount);
		serverRoleCount = null;
	}

}
