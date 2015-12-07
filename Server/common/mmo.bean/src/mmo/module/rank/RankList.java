package mmo.module.rank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mmo.common.bean.pet.PetArrayInfo;
import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.common.protocol.game.UserProtocol;
import mmo.common.protocol.game.UserProtocol.RankType;
import mmo.common.protocol.ui.main.Main_200_Equip;
import mmo.module.cache.role.CacheAccountRole;
import mmo.module.cache.role.CacheRole;
import mmo.module.rank.bean.RankData;
import mmo.module.rank.bean.RankFighter;
import mmo.module.rank.bean.RankLeaderData;
import mmo.module.rank.bean.RankLevel;
import mmo.module.rank.bean.RankLingshi;
import mmo.module.rank.bean.RankOneStandData;
import mmo.module.rank.bean.RankYuanbao;
import mmo.module.rank.bean.RoleArenaData;
import mmo.module.rank.heatbeat.RankHeartBeat;
import mmo.module.rank.heatbeat.runnable.RankImmediateRunnable;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MathUtil;

public class RankList implements UserProtocol.RankType, Cloneable {
	private final static ComparatorRank COMPARATOR   = new ComparatorRank();

	/** 排行榜上榜人数 */
	private int                         maxCount     = 20;
	/** 数据库中的ID */
	private int                         rankId;
	/** 排行类型 */
	protected byte                      rankType;
	/** 离竞技场排行1000积分最近的位置 */
	protected int                       recentIndex  = 0;

	/** 排行榜 */
	private LinkedList<RankData>        rankDataList = new LinkedList<RankData>();
	/** 角色ID与角色排行数据间的映射 */
	private Map<Integer, RankData>      rankRoles    = new HashMap<Integer, RankData>();

	public RankList(byte type) {
		this.rankType = type;
		maxCount = RankManager.getRankCountMax(rankType);
	}

	public int getRecentIndex() {
		return recentIndex;
	}

	public void setRecentIndex(int recentIndex) {
		this.recentIndex = recentIndex;
	}

	public byte getRankType() {
		return rankType;
	}

	public void setMaxCount(int maxCount) {
		if (maxCount < 20) {
			maxCount = 20;
		}
		this.maxCount = maxCount;
	}

	public void setRankType(byte rankType) {
		this.rankType = rankType;
	}

	public List<RankData> getRankList() {
		return rankDataList;
	}

	private void checkCount() {
		while (rankRoles.size() > maxCount) {
			RankData rd = rankDataList.removeLast();
			if (rd != null) {
				rankRoles.remove(rd.getRoleId());
			}
		}
	}

	public int getMaxCount() {
		return maxCount;
	}

	private RankData convertRole2RankData(CacheRole roleOnLine, byte type) {
		RankData rankData = null;
		switch (type) {
			case RANK_LING_SHI: {
				rankData = new RankLingshi(roleOnLine);
				break;
			}
			case RANK_YUAN_BAO: {
				rankData = new RankYuanbao(roleOnLine);
				break;
			}
			case LEVEL_SORT: {
				rankData = new RankLevel(roleOnLine);
				break;
			}
			case FIGHTER_SORT: {
				rankData = new RankFighter(roleOnLine);
				break;
			}
			case ARENA_SORT: {
				rankData = new RoleArenaData(roleOnLine);
				break;
			}
			case ONE_STAND_SORT: {
				rankData = new RankOneStandData(roleOnLine);
				break;
			}
			case LEADER_SORT: {
				rankData = new RankLeaderData(roleOnLine);
				break;
			}
			default: {
				rankData = new RankData(roleOnLine);
			}
		}
		return rankData;
	}

	/**
	 * 把入榜成员设成删除状态，在重新进行排行时重排行中移除
	 * 
	 * @param roleId
	 *            角色编号
	 */
	public void removeInvaildRole(int roleId) {
		RankData rankData = getRankData(roleId);
		if (rankData != null) {
			rankData.toDeleted();
		}
	}

	public RankData getRankData(int roleId) {
		return rankRoles.get(roleId);
	}

	public RankData getRankData() {
		RankData rankData = null;
		switch (rankType) {
			case RANK_LING_SHI: {
				rankData = new RankLingshi();
				break;
			}
			case RANK_YUAN_BAO: {
				rankData = new RankYuanbao();
				break;
			}
			case LEVEL_SORT: {
				rankData = new RankLevel();
				break;
			}
			case FIGHTER_SORT: {
				rankData = new RankFighter();
				break;
			}
			case ARENA_SORT: {
				rankData = new RoleArenaData();
				break;
			}
			case ONE_STAND_SORT: {
				rankData = new RankOneStandData();
				break;
			}
			case LEADER_SORT: {
				rankData = new RankLeaderData();
				break;
			}
			default: {
				rankData = new RankData();
			}
		}
		return rankData;
	}

	public int getRankId() {
		return rankId;
	}

	public void setRankId(int rankId) {
		this.rankId = rankId;
	}

	public Object clone() {
		RankList rankList = null;
		try {
			rankList = (RankList) super.clone();
			rankList.rankRoles = new HashMap<Integer, RankData>(rankRoles);
			rankList.rankDataList = new LinkedList<RankData>(rankDataList);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return rankList;
	}

	public Map<Integer, RankData> getRankRoles() {
		return rankRoles;
	}

	/**
	 * 获取第一名
	 * 
	 * @return 排行榜第一名
	 */
	public RankData getFirstRole() {
		if (rankDataList.size() < 1) {
			return null;
		}
		return rankDataList.get(0);
	}

	public List<RankData> getChallengeList(Role role) {
		RankData myData = getRankData(role.getId());
		List<RankData> resultList = new ArrayList<RankData>();
		if (myData == null) {
			List<RankData> list = RoleManager.getChallengeroles();
			if (list != null && list.size() > 0) {
				int position = rankDataList.size() + 3;
				for (int ri = 0; ri < 3 && ri < list.size(); ri++) {
					list.get(ri).setCurrRankPosition(position--);
					resultList.add(list.get(ri));
				}
				return resultList;
			}

			List<Integer> robots = RoleManager.getFirstChallengeRobots();
			if (robots != null && robots.size() > 0) {
				RankData rd = null;
				for (int ri = 0; ri < 3 && ri < robots.size(); ri++) {
					rd = getRankData(robots.get(ri));
					if (rd != null && !resultList.contains(rd)) {
						if (resultList.size() < 1) {
							resultList.add(rd);
						} else {
							boolean added = false;
							for (int rt = 0; rt < resultList.size(); rt++) {
								if (rd.getCurrRankPosition() > resultList.get(rt).getCurrRankPosition()) {
									resultList.add(rt, rd);
									added = true;
									break;
								}
							}
							if (!added) {
								resultList.add(rd);
							}
						}
					}
				}
			} else {
				getRankDataRange(resultList, -1, 0, 15);
				getRankDataRange(resultList, -1, 16, 50);
				getRankDataRange(resultList, -1, 51, 70);
			}
		} else {
			int position = myData.getCurrRankPosition() - 1;
			switch (position) {
				case 0: {
					getRankDataRange(resultList, position, 401, 600);
					getRankDataRange(resultList, position, 201, 400);
					getRankDataRange(resultList, position, 50, 200);
					break;
				}
				case 1: {
					getRankDataRange(resultList, position, 101, 200);
					getRankDataRange(resultList, position, 50, 100);
					resultList.add(rankDataList.get(0));
					break;
				}
				case 2: {
					getRankDataRange(resultList, position, 40, 100);
					resultList.add(rankDataList.get(1));
					resultList.add(rankDataList.get(0));
					break;
				}
				default: {
					getRankDataRange(resultList, position, 0, 15);
					getRankDataRange(resultList, position, 16, 50);
					getRankDataRange(resultList, position, 51, 70);
					break;
				}
			}
		}
		return resultList;
	}

	public List<RankData> getChallengeList5(Role role, boolean first) {
		RankData myData = getRankData(role.getId());
		List<RankData> resultList = new ArrayList<RankData>();
		if (first) {
			List<Integer> robots = RoleManager.getFirstChallengeRobots();
			if (robots != null && robots.size() > 0) {
				RankData rd = null;
				for (int ri = 0; ri < 5 && ri < robots.size(); ri++) {
					rd = getRankData(robots.get(ri));
					if (rd != null && !resultList.contains(rd)) {
						if (resultList.size() < 1) {
							resultList.add(rd);
						} else {
							boolean added = false;
							for (int rt = 0; rt < resultList.size(); rt++) {
								if (rd.getCurrRankPosition() > resultList.get(rt).getCurrRankPosition()) {
									resultList.add(rt, rd);
									added = true;
									break;
								}
							}
							if (!added) {
								resultList.add(rd);
							}
						}
					}
				}
			} else {
				getRankDataRange(resultList, -1, 0, 15);
				getRankDataRange(resultList, -1, 16, 30);
				getRankDataRange(resultList, -1, 31, 45);
				getRankDataRange(resultList, -1, 46, 60);
				getRankDataRange(resultList, -1, 61, 70);
			}
			if (resultList.size() > 0) {
				return resultList;
			}
		}
		if (myData == null) {
			getRankDataRange(resultList, -1, 0, 15);
			getRankDataRange(resultList, -1, 16, 30);
			getRankDataRange(resultList, -1, 31, 45);
			getRankDataRange(resultList, -1, 46, 60);
			getRankDataRange(resultList, -1, 61, 70);
		} else {
			int position = myData.getCurrRankPosition() - 1;
			switch (position) {
				case 0: {
					getRankDataRange(resultList, position, 451, 600);
					getRankDataRange(resultList, position, 351, 450);
					getRankDataRange(resultList, position, 251, 350);
					getRankDataRange(resultList, position, 151, 250);
					getRankDataRange(resultList, position, 50, 150);
					break;
				}
				case 1: {
					getRankDataRange(resultList, position, 161, 200);
					getRankDataRange(resultList, position, 121, 160);
					getRankDataRange(resultList, position, 81, 120);
					getRankDataRange(resultList, position, 50, 80);
					resultList.add(rankDataList.get(0));
					break;
				}
				case 2: {
					getRankDataRange(resultList, position, 81, 100);
					getRankDataRange(resultList, position, 61, 80);
					getRankDataRange(resultList, position, 40, 60);
					resultList.add(rankDataList.get(1));
					resultList.add(rankDataList.get(0));
					break;
				}
				default: {
					getRankDataRange(resultList, position, 0, 15);
					getRankDataRange(resultList, position, 16, 25);
					getRankDataRange(resultList, position, 26, 36);
					getRankDataRange(resultList, position, 37, 50);
					getRankDataRange(resultList, position, 51, 70);
					break;
				}
			}
		}
		return resultList;
	}

	public void getRankDataRange(List<RankData> resultList, int position, int percentSub, int percentSup) {
		switch (position) {
			case -1: {
				position = rankDataList.size() - 1;
				percentSub = position - position * percentSub / 100;
				percentSup = position - position * percentSup / 100;
				int newPosition = MathUtil.getRandom(percentSup, percentSub);
				if (newPosition > -1 && newPosition < rankDataList.size()) {
					RankData rd = rankDataList.get(newPosition);
					if (resultList.contains(rd)) {
						if (newPosition > 0) {
							newPosition--;
							rd = rankDataList.get(newPosition);
							if (!resultList.contains(rd)) {
								resultList.add(rd);
							}
						}
					} else {
						resultList.add(rd);
					}
				}
				break;
			}
			case 0: {
				position += 1;
				percentSub = position + position * percentSub / 100;
				percentSup = position * percentSup / 100;
				int newPosition = MathUtil.getRandom(percentSup, percentSub);
				if (newPosition > -1 && newPosition < rankDataList.size()) {
					RankData rd = rankDataList.get(newPosition);
					if (resultList.contains(rd)) {
						if (newPosition > 0) {
							newPosition--;
							rd = rankDataList.get(newPosition);
							if (!resultList.contains(rd)) {
								resultList.add(rd);
							}
						}
					} else {
						resultList.add(rd);
					}
				}
				break;
			}
			case 1: {
				position += 1;
				percentSub = position + position * percentSub / 100;
				percentSup = position * percentSup / 100;
				int newPosition = MathUtil.getRandom(percentSup, percentSub);
				if (newPosition > -1 && newPosition < rankDataList.size()) {
					RankData rd = rankDataList.get(newPosition);
					if (resultList.contains(rd)) {
						if (newPosition > 0) {
							newPosition--;
							rd = rankDataList.get(newPosition);
							if (!resultList.contains(rd)) {
								resultList.add(rd);
							}
						}
					} else {
						resultList.add(rd);
					}
				}
				break;
			}
			case 2: {
				position += 1;
				percentSub = position + position * percentSub / 100;
				percentSup = position * percentSup / 100;
				int newPosition = MathUtil.getRandom(percentSup, percentSub);
				if (newPosition > -1 && newPosition < rankDataList.size()) {
					RankData rd = rankDataList.get(newPosition);
					if (resultList.contains(rd)) {
						if (newPosition > 0) {
							newPosition--;
							rd = rankDataList.get(newPosition);
							if (!resultList.contains(rd)) {
								resultList.add(rd);
							}
						}
					} else {
						resultList.add(rd);
					}
				}
				break;
			}
			default: {
				position -= 1;
				percentSub = position - position * percentSub / 100;
				percentSup = position - position * percentSup / 100;
				int newPosition = MathUtil.getRandom(percentSup, percentSub);
				if (newPosition > -1 && newPosition < rankDataList.size()) {
					RankData rd = rankDataList.get(newPosition);
					if (resultList.contains(rd)) {
						if (newPosition > 0) {
							newPosition--;
							rd = rankDataList.get(newPosition);
							if (!resultList.contains(rd)) {
								resultList.add(rd);
							}
						}
					} else {
						resultList.add(rd);
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return "RankList [maxCount=" + maxCount + ", rankId=" + rankId + ", rankType=" + rankType + ", rankDataList=" + rankDataList + "]";
	}

	public void switchPosition(CacheRole role, int role02) {
		RankData data01 = getRankData(role.getRoleId());
		if (data01 == null) {
			data01 = convertRole2RankData(role, rankType);
			rankRoles.put(data01.getRoleId(), data01);
			rankDataList.add(data01);
			data01.resetPosition(rankDataList.size());
		}
		switchPosition(data01, getRankData(role02));
		checkCount();
	}

	public void switchPosition(RankData data01, RankData data02) {
		if (data01 == null || data02 == null) {
			return;
		}
		rankDataList.set(data01.getCurrRankPosition() - 1, data02);
		rankDataList.set(data02.getCurrRankPosition() - 1, data01);

		int tmp = data02.getCurrRankPosition();
		data02.setCurrRankPosition(data01.getCurrRankPosition());
		data01.setCurrRankPosition(tmp);
	}

	public void enterLast(CacheRole role) {
		RankData data01 = getRankData(role.getRoleId());
		if (data01 == null) {
			data01 = convertRole2RankData(role, rankType);
			rankRoles.put(data01.getRoleId(), data01);
			rankDataList.add(data01);
			data01.resetPosition(rankDataList.size());
		}
		checkCount();
	}

	public void enterLast(Role role) {
		if (rankType == RankType.ARENA_SORT) {
			RankData data01 = getRankData(role.getId());
			if (data01 == null) {
				data01 = new RoleArenaData();

				PetArrayInfo pai = role.getArrayInfo(Main_200_Equip.Main_224_Sub.doufa_1_array);
				if (pai != null) {
					data01.setRankValue(pai.getFightValue());
				} else {
					data01.setRankValue(role.getFighting());
				}
				data01.setRoleId(role.getId()); // 角色ID
				data01.setRoleName(role.getUsername()); // 角色名称
				data01.setSectId(role.getSectId()); // 宗门ID
				data01.setProfession(role.getProfession()); // 角色职业
				data01.setLevelupFirstTime(role.getLevelupFirstTime());// 等级升级时间
				data01.setAccountId(role.getAccountId());// 账号id
				data01.setLevel(role.getLevel());
				data01.setSex(role.getSex());
				rankRoles.put(data01.getRoleId(), data01);
				rankDataList.add(data01);
				data01.resetPosition(rankDataList.size());
			}
			checkCount();
		}
	}

	/********************************************************************************************************************************/
	public void dbInitRank(RankData rankData) {
		rankDataList.add(rankData);
		rankRoles.put(rankData.getRoleId(), rankData);
	}

	public void checkRole(CacheRole role) {
		RankHeartBeat.getInstance().execute(new RankImmediateRunnable(this, role));
	}

	public void rankImmediate(CacheRole role) {
		switch (rankType) {
			case RankType.LEADER_SORT: {
				if (role.getLeaderIntegral() < 1) {
					return;
				}
				break;
			}
		}
		try {
			RankData rankData = rankRoles.get(role.getRoleId());
			if (rankData == null) {// 如果rank值小于1则不入榜
				rankData = convertRole2RankData(role, rankType);
				if (rankData.getRankValue() < 1) {
					return;
				}
				rankRoles.put(role.getRoleId(), rankData);
				rankDataList.add(rankData);
			} else {// 已经在排行榜内
				rankData.reset();
			}
		} catch (Exception e) {
			LoggerError.error("排行异常：" + rankType, e);
		} finally {
			Collections.sort(rankDataList, COMPARATOR);
			resetPosition();
			checkCount();
		}
	}

	public void refreshRank() {
		switch (rankType) {
			case RankType.ARENA_SORT: {
				return;
			}
		}
		try {
			Collections.sort(rankDataList, COMPARATOR);
			resetPosition();
			checkCount();
		} catch (Exception e) {
			LoggerError.error("排行异常：" + rankType, e);
		} finally {

		}
	}

	public void resetPosition() {
		RankData data = null;
		for (int ri = 0; ri < rankDataList.size();) {
			data = rankDataList.get(ri);
			if (data.isDeleted()) {
				rankDataList.remove(ri);
				rankRoles.remove(data.getRoleId());
				continue;
			} else {
				data.resetPosition(ri + 1);
				ri++;
			}
		}
	}

	/**
	 * 将当前在线角色属性更新到排行榜数据中
	 */
	public void updateRoleProperties4Rank(List<CacheAccountRole> accrountRoles) {
		if (rankType == ARENA_SORT) {
			return;
		}
		int roleSize = accrountRoles.size();
		if (roleSize > 0) {
			CacheAccountRole accountRole = null;
			Collection<CacheRole> roles = null;
			try {
				for (int ari = 0; ari < roleSize; ari++) {
					accountRole = accrountRoles.get(ari);
					roles = accountRole.getAllRoles();
					for (CacheRole role : roles) {
						if (rankType == RankType.LEADER_SORT && role.getLeaderIntegral() < 1) {
							continue;
						}
						RankData rankData = rankRoles.get(role.getRoleId());
						if (rankData == null) {// 如果rank值小于1则不入榜
							rankData = convertRole2RankData(role, rankType);
							if (rankData.getRankValue() < 1) {
								continue;
							}
							rankRoles.put(role.getRoleId(), rankData);
							rankDataList.add(rankData);
						} else {// 已经在排行榜内
							rankData.reset();
						}

					}
				}
			} catch (Exception e) {
				LoggerError.error("排行异常：" + rankType, e);
			} finally {
				if (rankDataList.size() > 1) {
					Collections.sort(rankDataList, COMPARATOR);
				}
				resetPosition();
				checkCount();
			}
		}
	}

}
