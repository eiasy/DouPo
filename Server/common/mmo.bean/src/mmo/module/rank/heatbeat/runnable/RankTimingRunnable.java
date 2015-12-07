package mmo.module.rank.heatbeat.runnable;

import java.util.List;

import mmo.common.protocol.game.UserProtocol;
import mmo.module.cache.role.CacheAccountRole;
import mmo.module.rank.RankList;
import mmo.module.rank.RankManager;
import mmo.module.rank.data.DataRank;
import mmo.module.rank.runnable.SaveRankData;
import mmo.tools.thread.pool.ThreadExecutor;

/**
 * 定时排行
 * 
 * @author 李天喜
 * 
 */
public class RankTimingRunnable implements IRankRunnable {

	private List<CacheAccountRole> accountRoles = null;
	private List<RankList>         rankLists    = null;

	public RankTimingRunnable(List<CacheAccountRole> accountRoles, List<RankList> rankLists) {
		this.accountRoles = accountRoles;
		this.rankLists = rankLists;
	}

	public void run() {
		int size = rankLists.size();
		RankList rankList = null;
		for (int i = 0; i < size; i++) {
			if (rankLists.get(i).getRankType() != UserProtocol.RankType.ARENA_SORT) {
				rankList = (RankList) rankLists.get(i).clone();
				rankLists.set(i, rankList);
			}
		}
		for (int ri = 0; ri < size; ri++) {
			rankList = rankLists.get(ri);
			rankList.updateRoleProperties4Rank(accountRoles);
			RankManager.getInstance().replaceRankList(rankList);
			ThreadExecutor.execute(new SaveRankData(rankList, DataRank.dataPacket(rankList)));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
