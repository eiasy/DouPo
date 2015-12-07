package mmo.module.rank.heatbeat.runnable;

import mmo.module.cache.role.CacheRole;
import mmo.module.rank.RankList;

/**
 * 及时排行
 * 
 * @author 李天喜
 * 
 */
public class RankImmediateRunnable implements IRankRunnable {
	private RankList  rank;
	private CacheRole role;

	public RankImmediateRunnable(RankList rank, CacheRole role) {
		this.rank = rank;
		this.role = role;
	}

	@Override
	public void run() {
		if (rank != null && role != null) {
			rank.rankImmediate(role);
		}
		this.rank = null;
		this.role = null;
	}

}
