package mmo.common.module.account.doupo.cache.thread.database;

import mmo.common.module.account.doupo.cache.account.service.ServiceServerLastEnter;
import mmo.tools.thread.runnable.IDatabaseRunnable;

public class ServerLastDBRun implements IDatabaseRunnable {
	private int    accountId;
	private String servers;

	public ServerLastDBRun(int accountId, String servers) {
		super();
		this.accountId = accountId;
		this.servers = servers;
	}

	@Override
	public void run() {
		ServiceServerLastEnter.getInstance().addOrUpdate(accountId, servers);
		servers = null;
	}

}
