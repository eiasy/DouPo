package mmo.module.rank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import mmo.common.protocol.game.UserProtocol;
import mmo.common.system.GameParameter;
import mmo.module.rank.bean.RankData;
import mmo.module.rank.data.DataRank;
import mmo.module.rank.runnable.SaveRankData;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.pool.ThreadExecutor;
import mmo.tools.thread.runnable.IExecuteEntity;

public class RankManager implements UserProtocol.RankType, IExecuteEntity {
	/** 各种排行榜 */
	private final static Map<Byte, Integer> rankCountMax              = new HashMap<Byte, Integer>();
	/** 排行榜名称与ID */
	private final static Map<Byte, String>  rankNameItem              = new HashMap<Byte, String>();

	private static final int                SAVE_OFFST                = 5 * 60 * 1000;
	/** 周期更新间隔 */
	private static long                     NEXT_UPDATE_TIME_INTERVAL = 5 * 60 * 1000;
	/** 实例 */
	private static RankManager              instance                  = new RankManager();
	/** 每次更新时间 */
	private long                            nextTime                  = System.currentTimeMillis() + NEXT_UPDATE_TIME_INTERVAL;
	/** 通过类别取得排行榜 */
	private Map<Byte, RankList>             rankListById              = new ConcurrentHashMap<Byte, RankList>();

	/** 排行榜服务 */
	private IRankService                    rankService               = null;
	/** 强制触发排行 */
	private boolean                         isRank                    = false;
	private long                            saveRankTime              = System.currentTimeMillis() + SAVE_OFFST;

	static {
		rankNameItem.put(ARENA_SORT, "斗法排行");
		rankNameItem.put(ONE_STAND_SORT, "一战到底排行");
		rankNameItem.put(LEADER_SORT, "世界首领排行");
		rankNameItem.put(FIGHTER_SORT, "战斗力排行");
		rankNameItem.put(LEVEL_SORT, "等级排行");
	}

	public final static RankManager getInstance() {
		if (instance == null) {
			instance = new RankManager();
		}
		return instance;
	}

	@Override
	public void run(long currTime) {
		if (saveRankTime < currTime) {
			saveRankTime = currTime + SAVE_OFFST;
			Collection<RankList> ranks = rankListById.values();
			for (RankList rank : ranks) {
				ThreadExecutor.execute(new SaveRankData(rank, DataRank.dataPacket(rank)));
			}
		}
	}

	public final void enforceSave() {
		saveRankTime = 0;
	}

	/**
	 * 设置排行榜入榜数量
	 * 
	 * @param rankTitle
	 * @param count
	 */
	public static final void setRankCountMax(String rankTitle, int count) {
		if (rankTitle == null) {
			return;
		}
		if (count < 1) {
			count = 100;
		}
		Set<Byte> keys = rankNameItem.keySet();
		for (Byte key : keys) {
			if (rankTitle.equals(rankNameItem.get(key))) {
				rankCountMax.put(key, count);
				RankList rl = instance.getRankList(key);
				if (rl != null) {
					rl.setMaxCount(count);
				}
				return;
			}
		}
	}

	public final static int getRankCountMax(byte type) {
		Integer count = rankCountMax.get(type);
		if (count == null) {
			return 100;
		}
		return count;
	}

	public void toRank() {
		isRank = true;
	}

	private void initRankList(byte type) {
		RankList rankList = rankListById.get(type);
		if (rankList == null) {
			rankList = new RankList(type);
			rankListById.put(type, rankList);
		}
		this.rankService.loadRankTopData(rankList);
		LoggerError.messageLog.warn("finish init 排行：" + type);
	}

	public void init(IRankService rankService) {
		this.rankService = rankService;
		initRankList(RANK_LING_SHI);
		initRankList(RANK_YUAN_BAO);
		initRankList(LEVEL_SORT);
		initRankList(FIGHTER_SORT);
		initRankList(ARENA_SORT);
		initRankList(ONE_STAND_SORT);
		initRankList(LEADER_SORT);
	}

	public RankList addRankList(byte type, int rankId) {
		RankList rankList = new RankList(type);
		rankList.setRankId(rankId);
		rankListById.put(type, rankList);
		return rankList;
	}

	/**
	 * 排行榜数据初始化
	 */
	private RankManager() {
		// 更新时间设置
		NEXT_UPDATE_TIME_INTERVAL = GameParameter.getRankListRefresh();
		nextTime = System.currentTimeMillis() + NEXT_UPDATE_TIME_INTERVAL;

		rankListById.put(RANK_LING_SHI, new RankList(RANK_LING_SHI));
		rankListById.put(RANK_YUAN_BAO, new RankList(RANK_YUAN_BAO));
		rankListById.put(LEVEL_SORT, new RankList(LEVEL_SORT));
		rankListById.put(FIGHTER_SORT, new RankList(FIGHTER_SORT));
		rankListById.put(ARENA_SORT, new RankList(ARENA_SORT));
		rankListById.put(ONE_STAND_SORT, new RankList(ONE_STAND_SORT));
		rankListById.put(LEADER_SORT, new RankList(LEADER_SORT));
	}

	public void resetRankOffset() {
		NEXT_UPDATE_TIME_INTERVAL = GameParameter.getRankListRefresh();
		if (NEXT_UPDATE_TIME_INTERVAL < 5 * 1000 * 60) {
			NEXT_UPDATE_TIME_INTERVAL = 5 * 1000 * 60;
		}
	}

	public boolean isRank(long currTime) {
		if (isRank) {
			isRank = false;
			nextTime = currTime + NEXT_UPDATE_TIME_INTERVAL;
			return true;
		}
		if (currTime > nextTime) {
			nextTime = currTime + NEXT_UPDATE_TIME_INTERVAL;
			return true;
		}
		return false;
	}

	public final static Map<Byte, String> getRankNameItem() {
		return rankNameItem;
	}

	public RankList getRankList(byte rankType) {
		return rankListById.get(rankType);
	}

	public final List<RankList> getAllRankList() {
		return new ArrayList<RankList>(rankListById.values());
	}

	public List<RankData> getLevelRank() {
		return rankListById.get(LEVEL_SORT).getRankList();
	}

	public RankList getArenaRank() {
		return rankListById.get(ARENA_SORT);
	}

	public RankList getOneStandRank() {
		return rankListById.get(ONE_STAND_SORT);
	}

	public RankList getLeaderRank() {
		return rankListById.get(LEADER_SORT);
	}

	/**
	 * 在排行榜中删除无效的角色
	 * 
	 * @param roleid
	 *            角色id
	 */
	public void removeInvaildRole(int roleid) {
		rankListById.get(RANK_YUAN_BAO).removeInvaildRole(roleid);
		rankListById.get(RANK_LING_SHI).removeInvaildRole(roleid);
		rankListById.get(LEVEL_SORT).removeInvaildRole(roleid);
		rankListById.get(FIGHTER_SORT).removeInvaildRole(roleid);
		rankListById.get(ARENA_SORT).removeInvaildRole(roleid);
		rankListById.get(ONE_STAND_SORT).removeInvaildRole(roleid);
		rankListById.get(LEADER_SORT).removeInvaildRole(roleid);
	}

	public void eventRoleLevelUp(int roleId, short level) {
		RankData rd = rankListById.get(RANK_YUAN_BAO).getRankData(roleId);
		if (rd != null) {
			rd.setLevel(level);
		}
		rd = rankListById.get(RANK_LING_SHI).getRankData(roleId);
		if (rd != null) {
			rd.setLevel(level);
		}
		rd = rankListById.get(FIGHTER_SORT).getRankData(roleId);
		if (rd != null) {
			rd.setLevel(level);
		}
		rd = rankListById.get(ARENA_SORT).getRankData(roleId);
		if (rd != null) {
			rd.setLevel(level);
		}
		rd = rankListById.get(ONE_STAND_SORT).getRankData(roleId);
		if (rd != null) {
			rd.setLevel(level);
		}
		rd = rankListById.get(LEADER_SORT).getRankData(roleId);
		if (rd != null) {
			rd.setLevel(level);
		}
	}

	public IRankService getRankService() {
		return rankService;
	}

	public void replaceRankList(RankList rankList) {
		rankListById.put(rankList.getRankType(), rankList);
	}

}