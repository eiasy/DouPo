package mmo.common.module.account.doupo.cache.thread;

import mmo.common.module.account.doupo.security.SecurityCodeManager;
import mmo.tools.thread.heartbeat.AHeartbeat;

public class AccountValidateHeartbeat extends AHeartbeat {
	private final static AccountValidateHeartbeat instance = new AccountValidateHeartbeat();

	public final static AccountValidateHeartbeat getInstance() {
		return instance;
	}

	private AccountValidateHeartbeat() {
		addExecuteEntity(SecurityCodeManager.getInstance());
	}

	public void execute(IAccountValidate run) {
		addEvent(run);
	}
}
