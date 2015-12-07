package mmo.module.rank.bean.arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.bean.role.Role;
import mmo.common.config.role.RoleProfession;
import mmo.module.rank.RankManager;
import mmo.module.rank.bean.RankData;
import mmo.tools.util.MathUtil;

public class ArenaManager {
	/** 实例 */
	private static ArenaManager               instance   = new ArenaManager();
	/** 职业与等级对应的AI */
	private Map<ProfessionLevelType, ArenaAi> arenaAis   = new HashMap<ArenaManager.ProfessionLevelType, ArenaAi>(16);
	/** 职业和等级对应的协战AI */
	private Map<ProfessionLevelType, ArenaAi> helpWarAis = new HashMap<ArenaManager.ProfessionLevelType, ArenaAi>(16);

	private ArenaManager() {

	}

	/**
	 * 获取实例对象
	 */
	public final static ArenaManager getInstance() {
		if (instance == null) {
			instance = new ArenaManager();
		}
		return instance;
	}

	/**
	 * 添加一个职业与等级对应AI的映射
	 * 
	 * @param profession
	 * @param level
	 * @param arenaAi
	 */
	public void addArenaAi(byte profession, short level, ArenaAi arenaAi) {
		if (arenaAi == null) {
			arenaAis = new HashMap<ArenaManager.ProfessionLevelType, ArenaAi>(16);
		}
		arenaAis.put(getType(profession, level), arenaAi);
	}

	/**
	 * 通过职业和等级获取竞技场AI
	 * 
	 * @param profession
	 * @param level
	 * @return
	 */
	public ArenaAi getArenaAi(byte profession, short level) {
		ArenaAi ai = arenaAis.get(getType(profession, level));
		if (ai == null) {
			ai = new ArenaAi(profession);
		}
		return ai;
	}

	/**
	 * 添加一个职业与等级对应协战AI的映射
	 * 
	 * @param profession
	 * @param level
	 * @param arenaAi
	 */
	public void addHelpWarAi(byte profession, short level, ArenaAi arenaAi) {
		if (arenaAi == null) {
			helpWarAis = new HashMap<ArenaManager.ProfessionLevelType, ArenaAi>(16);
		}
		helpWarAis.put(getType(profession, level), arenaAi);
	}

	/**
	 * 通过职业和等级获取协战AI
	 * 
	 * @param profession
	 * @param level
	 * @return
	 */
	public ArenaAi getHelpWarAi(byte profession, short level) {
		ArenaAi ai = helpWarAis.get(getType(profession, level));
		if (ai == null) {
			ai = new ArenaAi(profession);
		}
		return ai;
	}

	// /**
	// * 挑战成功名次改变
	// *
	// * @param defier
	// * 挑战者
	// * @param target
	// * 挑战目标
	// */
	// public void changeRank(Role role, int target, String message) {
	// RankManager.getInstance().enterRank(UserProtocol.RankType.ARENA_SORT, role);
	// }

	/**
	 * 根据名次随机获取可被挑战的竞技场角色
	 * 
	 * @param index
	 *            名次
	 * @return result[0][] 名次， result[1][] 角色ID
	 */
	public List<RankData> getRandomArenaRole(Role role) {
		// 　　排名里可显示的最低名次是5000名，玩家低于这个名次都属于第5001名，并不进行排名。
		// 　　LV=玩家当前排名
		// 　　挑选5位对手需要分阶段：
		// 　　3000以下排名：(LV,LV+250] 里随机选择5位名次；当 3000<LV<=3004时，取[LV+1,LV+5]
		// 　　1001 - 3000排名：(LV,LV+200)里随机选择5位名次；当 1000<LV<=1004时，取[LV+1,LV+5]
		// 　　11-1000排名：(LV,LV+100)里随机选择5位名次；当 10<LV<=14时，取[LV+1,LV+5]
		// 　　0 - 10排名：[1,10]里随机选择5位名次；排除自己名次
		int roleScore = role.getMaxFighting();// 角色的积分
		List<RankData> rankList = RankManager.getInstance().getArenaRank().getRankList();
		int rankSize = rankList.size();

		if (rankSize <= 5) {
			return getRandomElement(rankList, 0, true, 10, true, 5, roleScore);
		} else {
			if (rankSize <= 10) {
				return getRandomElement(rankList, 0, true, 10, true, 5, roleScore);
			} else if (roleScore <= 14) {
				return getRandomElement(rankList, roleScore + 1, true, roleScore + 5, true, 5, -1);
			} else if (roleScore <= 1000) {
				return getRandomElement(rankList, roleScore, false, roleScore + 100, true, 5, -1);
			} else if (roleScore <= 1004) {
				return getRandomElement(rankList, roleScore + 1, true, roleScore + 5, true, 5, -1);
			} else if (roleScore <= 3000) {
				return getRandomElement(rankList, roleScore, false, roleScore + 200, true, 5, -1);
			} else if (roleScore <= 3004) {
				return getRandomElement(rankList, roleScore, true, roleScore + 5, true, 5, -1);
			} else {
				return getRandomElement(rankList, roleScore, false, roleScore + 250, true, 5, -1);
			}
		}
	}

	/**
	 * 从列表中获取部分元素
	 * 
	 * @param rankList
	 * @param start
	 * @param containStart
	 * @param end
	 * @param containEnd
	 * @param count
	 * @param exceptIndex
	 * @return
	 */
	private List<RankData> getRandomElement(List<RankData> rankList, int start0, boolean containStart, int end0, boolean containEnd, int count,
	        int exceptIndex) {
		int start = start0;
		int end = end0;
		int tmp = rankList.size();
		int switchCount = 0;
		if (start > end) {
			switchCount = end;
			end = start;
			start = switchCount;
		}
		if (end > tmp - 1) {
			end = tmp - 1;
			start = end - count;
		}
		if (start < 0) {
			start = 0;
		}
		List<RankData> resultList = new ArrayList<RankData>();
		switchCount = end - start + 1;
		int[] indexs = new int[switchCount];
		for (int si = 0; si < switchCount; si++) {
			indexs[si] = si;
		}
		int i1 = 0;
		int i2 = 0;
		for (int si = 0; si < switchCount; si++) {
			i1 = MathUtil.getRandom(0, switchCount - 1);
			i2 = MathUtil.getRandom(0, switchCount - 1);
			tmp = indexs[i1];
			indexs[i1] = indexs[i2];
			indexs[i2] = tmp;
		}
		for (int ri = 0; ri < switchCount; ri++) {
			tmp = start + indexs[ri];
			if (!containEnd && tmp == end0) {
				continue;
			}
			if (!containStart && tmp == start0) {
				continue;
			}
			if (tmp == exceptIndex) {
				continue;
			}
			boolean notAdd = true;
			for (int rti = 0; rti < resultList.size(); rti++) {
				if (rankList.get(tmp).getCurrRankPosition() < resultList.get(rti).getCurrRankPosition()) {
					resultList.add(rti, rankList.get(tmp));
					notAdd = false;
					break;
				}
			}
			if (notAdd) {
				resultList.add(rankList.get(tmp));
			}
			if (resultList.size() == count) {
				break;
			}
		}
		return resultList;
	}

	/**
	 * 获取当前排名
	 * 
	 * @param roleId
	 * @return 若无排名 返回-1,排名5000以后的返回5001
	 */
	public int getNowArenaRank(int roleId) {
		RankData rad = RankManager.getInstance().getArenaRank().getRankData(roleId);
		if (rad == null) {
			return -1;
		}
		return rad.getCurrRankPosition();
	}

	/**
	 * 获取上一次排名
	 * 
	 * @param roleId
	 * @return 若无排名 返回-1,排名5000以后的返回5001
	 */
	public int getOldArenaRank(int roleId) {
		RankData rad = RankManager.getInstance().getArenaRank().getRankData(roleId);
		if (rad == null) {
			return -1;
		}
		return rad.getPreRankPosition();
	}

	/**
	 * 排名变化量
	 * 
	 * @param roleId
	 * @return
	 */
	public int getChangeRankCount(int roleId) {
		RankData rad = RankManager.getInstance().getArenaRank().getRankData(roleId);
		if (rad == null) {
			return -1;
		}
		return rad.getPreRankPosition() - rad.getCurrRankPosition();
	}

	// /**
	// * 增加一名竞技场角色
	// *
	// * @param role
	// * @return index 排名
	// */
	// public int addArenaRole(Role role) {
	// RankList rankList = RankManager.getInstance().getArenaRank();
	// RankManager.getInstance().enterRank(UserProtocol.RankType.ARENA_SORT, role);
	// return rankList.getRankList().size();
	// }

	/**
	 * 通过职业和等级获取一种类型
	 * 
	 * @param profession
	 * @param level
	 * @return
	 */
	public ProfessionLevelType getType(byte profession, short level) {
		switch (profession) {
			case RoleProfession.XiuLuo_2: {
				if (level <= 19) {
					return ProfessionLevelType.XiuLuo0_19;
				} else if (level <= 49) {
					return ProfessionLevelType.XiuLuo20_49;
				} else if (level <= 59) {
					return ProfessionLevelType.XiuLuo50_59;
				} else {
					return ProfessionLevelType.XiuLuo60_69;
				}
			}
			case RoleProfession.XuanXian_1: {
				if (level <= 19) {
					return ProfessionLevelType.XuanXian0_19;
				} else if (level <= 49) {
					return ProfessionLevelType.XuanXian20_49;
				} else if (level <= 59) {
					return ProfessionLevelType.XuanXian50_59;
				} else {
					return ProfessionLevelType.XuanXian60_69;
				}
			}
			case RoleProfession.YuLing_16: {
				if (level <= 19) {
					return ProfessionLevelType.YuLing0_19;
				} else if (level <= 49) {
					return ProfessionLevelType.YuLing20_49;
				} else if (level <= 59) {
					return ProfessionLevelType.YuLing50_59;
				} else {
					return ProfessionLevelType.YuLing60_69;
				}
			}
			default: {
				return ProfessionLevelType.DEFAULT;
			}
		}
	}

	/**
	 * 职业与等级确定一种类型
	 * 
	 * @author 肖琼
	 * 
	 */
	public enum ProfessionLevelType {
		DEFAULT, XiuLuo0_19, XiuLuo20_49, XiuLuo50_59, XiuLuo60_69, XuanXian0_19, XuanXian20_49, XuanXian50_59, XuanXian60_69, YuLing0_19, YuLing20_49, YuLing50_59, YuLing60_69
	}
}
