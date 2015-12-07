package mmo.common.module.account.doupo.cache.thread;

import mmo.module.logger.account.LoggerAccount;
import mmo.tools.thread.heartbeat.AHeartbeat;
import mmo.tools.thread.runnable.IDatabaseRunnable;

public class AccountDatabaseHeartbeat extends AHeartbeat {
	private final static AccountDatabaseHeartbeat instance = new AccountDatabaseHeartbeat();

	public final static AccountDatabaseHeartbeat getInstance() {
		return instance;
	}

	private AccountDatabaseHeartbeat() {

	}

	public void execute(IDatabaseRunnable run) {
		addEvent(run);
	}

	public void callback() {
		LoggerAccount.nullLog();
	}
}
