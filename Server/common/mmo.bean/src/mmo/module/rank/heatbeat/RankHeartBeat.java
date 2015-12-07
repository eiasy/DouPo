package mmo.module.rank.heatbeat;

import mmo.module.rank.RankManager;
import mmo.module.rank.heatbeat.runnable.IRankRunnable;
import mmo.tools.thread.heartbeat.AHeartbeat;

/**
 * 及时排行线程心跳
 * 
 * @author 李天喜
 * 
 */
public class RankHeartBeat extends AHeartbeat {
	private static final RankHeartBeat instance = new RankHeartBeat();

	public static final RankHeartBeat getInstance() {
		return instance;
	}

	private RankHeartBeat() {
		addExecuteEntity(RankManager.getInstance());
	}

	public void execute(IRankRunnable run) {
		super.addEvent(run);
	}

}
