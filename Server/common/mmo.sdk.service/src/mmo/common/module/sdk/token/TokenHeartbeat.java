package mmo.common.module.sdk.token;

import mmo.common.module.sdk.token.run.ITokenRun;
import mmo.tools.thread.heartbeat.AHeartbeat;

public class TokenHeartbeat extends AHeartbeat {
	private final static TokenHeartbeat instance = new TokenHeartbeat();

	public final static TokenHeartbeat getInstance() {
		return instance;
	}

	private TokenHeartbeat() {
		addExecuteEntity(TokenManager.getInstance());
	}

	public void execute(ITokenRun run) {
		addEvent(run);
	}
}
