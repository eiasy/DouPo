package mmo.common.module.account.doupo.cache.thread.validate;

import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.common.module.account.doupo.cache.thread.IAccountValidate;

public class ServerLastEnterRun implements IAccountValidate {
	private int gameId;
	private int serverId;
	private int accountId;

	public ServerLastEnterRun(int gameId, int serverId, int accountId) {
		super();
		this.gameId = gameId;
		this.serverId = serverId;
		this.accountId = accountId;
	}

	@Override
	public void run() {
		AccountCache.getInstance().updateServerLastEnter(gameId, serverId, accountId);
	}
}
