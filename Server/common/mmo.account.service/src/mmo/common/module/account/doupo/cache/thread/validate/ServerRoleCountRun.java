package mmo.common.module.account.doupo.cache.thread.validate;

import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.IAccountValidate;

public class ServerRoleCountRun implements IAccountValidate {
	private int gameId;
	private int serverId;
	private int accountId;
	private int roleCount;

	public ServerRoleCountRun(int gameId, int serverId, int accountId, int roleCount) {
		super();
		this.gameId = gameId;
		this.serverId = serverId;
		this.accountId = accountId;
		this.roleCount = roleCount;
	}

	@Override
	public void run() {
		AccountCache.getInstance().updateServerRoleCount(gameId, serverId, accountId, roleCount);
	}
}
