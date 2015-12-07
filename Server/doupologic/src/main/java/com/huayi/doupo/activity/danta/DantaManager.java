package com.huayi.doupo.activity.danta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.activity.ThreadManager;
import com.huayi.doupo.activity.comparator.ComparatorRankDanta;
import com.huayi.doupo.activity.comparator.ComparatorRankDantaDay;
import com.huayi.doupo.activity.run.ClearRankDantaDayRun;
import com.huayi.doupo.activity.run.UpdateRankDantaDayRun;
import com.huayi.doupo.activity.run.UpdateRankDantaRun;
import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictDantaDayAward;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstRankDanta;
import com.huayi.doupo.base.model.InstRankDantaDay;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class DantaManager {
	private final static ComparatorRankDanta COMPARATOR = new ComparatorRankDanta();
	private final static ComparatorRankDantaDay COMPARATOR_DAY = new ComparatorRankDantaDay();
	public final static int PAGE_COUNT = 10;
	private final static DantaManager instance = new DantaManager();
	static{
	}

	public final static DantaManager getInstance() {
		return instance;
	}

	private DantaManager() {
	}

	private List<InstRankDanta> rankDantaList = new ArrayList<InstRankDanta>();
	private Map<Integer, InstRankDanta> rankDantaMap = new ConcurrentHashMap<Integer, InstRankDanta>();

	private List<InstRankDantaDay> rankDantaDayList = new ArrayList<InstRankDantaDay>();
	private Map<Integer, InstRankDantaDay> rankDantaDayMap = new ConcurrentHashMap<Integer, InstRankDantaDay>();

	public void init() {
		try {
			List<InstRankDanta> rankDantaList = DALFactory.getInstRankDantaDAL().getList("", 0);
			if (rankDantaList != null) {
				this.rankDantaList = rankDantaList;
			}
			reorderRankDanta();
			List<InstRankDantaDay> rankDantaDayList = DALFactory.getInstRankDantaDayDAL().getList("", 0);
			if (rankDantaDayList != null && rankDantaDayList.size() > 0) {
				if (rankDantaDayList.get(0).getAddTime().startsWith(DateUtil.getYmdDate())) {
					this.rankDantaDayList = rankDantaDayList;
				} else {
					this.rankDantaDayList = new ArrayList<>();
					this.rankDantaDayMap = new ConcurrentHashMap<Integer, InstRankDantaDay>();
					ThreadManager.accessDatabase(new ClearRankDantaDayRun());
				}
			}
			reorderRankDantaDay(true);
		} catch (Exception e) {
			LogUtil.error("初始化丹塔数据异常", e);
		}
	}

	public void checkReset() {
		try {
			List<DictDantaDayAward> dayAwardList = DictList.dictDantaDayAwardList;
			if (dayAwardList != null) {
				DictDantaDayAward da = null;
				InstRankDantaDay dtDay = null;
				for (int ai = 0; ai < dayAwardList.size(); ai++) {
					da = dayAwardList.get(ai);
					for (int ri = da.getRankSub(); ri <= da.getRankSup(); ri++) {
						if (ri < 1 || ri > rankDantaDayList.size()) {
							continue;
						}
						dtDay = rankDantaDayList.get(ri - 1);
						if (dtDay != null) {
							Player player = PlayerMapUtil.getPlayerByPlayerId(dtDay.getPlayerId());
							try {
								LogUtil.warn("丹塔当日排名|" + (ri) + "|" + dtDay.getPlayerId() + "|" + da.getAwards());
								// 在线玩家
								if (da.getAwards() != null && da.getAwards().length() > 0) {
									if (player != null) {
										MessageData otherSyncMsgData = new MessageData();
										ActivityUtil.addInstPlayerAward(dtDay.getPlayerId(), 3, da.getAwards(), DateUtil.getCurrTime(), "您在丹塔当日排名中的名次为第 " + (ri) + "名,获得奖励：", otherSyncMsgData);
										MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
									} else {
										ActivityUtil.addInstPlayerAward(dtDay.getPlayerId(), 3, da.getAwards(), DateUtil.getCurrTime(), "您在丹塔当日排名中的名次为第 " + (ri) + "名,获得奖励：", new MessageData());
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			this.rankDantaDayList = new ArrayList<>();
			this.rankDantaDayMap = new ConcurrentHashMap<Integer, InstRankDantaDay>();
			ThreadManager.accessDatabase(new ClearRankDantaDayRun());
		} catch (Exception e) {
			LogUtil.error("丹塔当日排行发奖异常", e);
		}
	}

	public InstRankDanta getInstRankDanta(InstPlayer instPlayer) {
		InstRankDanta rd = rankDantaMap.get(instPlayer.getId());
		boolean isNew = false;
		if (rd == null) {
			isNew = true;
			rd = new InstRankDanta();
			rd.setPlayerId(instPlayer.getId());
			rd.setPlayerName(ParamConfig.playerNameMap.get(instPlayer.getId()));
			rd.setRankIndex(rankDantaList.size());
			rd.setMaxLayer(0);
			rd.setAddTime(DateUtil.getCurrTime());
			rd.setUpdateTime(DateUtil.getCurrTime());
			rd.setPlayerLevel(instPlayer.getLevel());
			rd.setStartCount(0);
			rd.setFinishCount(0);
			List<InstUnionMember> instUnionMemberList = DALFactory.getInstUnionMemberDAL().getList("instPlayerId= " + rd.getPlayerId(), 0);
			if (instUnionMemberList != null && instUnionMemberList.size() > 0) {
				rd.setInstUnionId(instUnionMemberList.get(0).getInstUnionId());
			}
			rd.setHeaderCardId(instPlayer.getHeaderCardId());
		}
		if (isNew) {
			try {
				DALFactory.getInstRankDantaDAL().add(rd, 0);
				rankDantaList.add(rd);
				rankDantaMap.put(instPlayer.getId(), rd);
			} catch (Exception e) {
				LogUtil.error("新增丹塔历史排名异常", e);
			}
		}
		return rd;
	}

	public InstRankDantaDay getInstRankDantaDay(InstPlayer instPlayer) {
		InstRankDantaDay rdd = rankDantaDayMap.get(instPlayer.getId());
		boolean isNew = false;
		if (rdd == null) {
			isNew = true;
			rdd = new InstRankDantaDay();
			rdd.setPlayerId(instPlayer.getId());
			rdd.setPlayerName(ParamConfig.playerNameMap.get(instPlayer.getId()));
			rdd.setRankIndex(rankDantaDayList.size());
			rdd.setMaxLayer(0);
			rdd.setAddTime(DateUtil.getCurrTime());
			rdd.setUpdateTime(DateUtil.getCurrTime());
			rdd.setPlayerLevel(instPlayer.getLevel());
			rdd.setMedalCount(0);
			rdd.setStartCount(0);
			rdd.setStartLayer(0);
			rdd.setFinishLayer(0);
			rdd.setLayerInfo("");
			List<InstUnionMember> instUnionMemberList = DALFactory.getInstUnionMemberDAL().getList("instPlayerId= " + rdd.getPlayerId(), 0);
			if (instUnionMemberList != null && instUnionMemberList.size() > 0) {
				rdd.setInstUnionId(instUnionMemberList.get(0).getInstUnionId());
			}
			rdd.setHeaderCardId(instPlayer.getHeaderCardId());
		}
		if (isNew) {
			try {
				DALFactory.getInstRankDantaDayDAL().add(rdd, 0);
				rankDantaDayList.add(rdd);
				rankDantaDayMap.put(instPlayer.getId(), rdd);
			} catch (Exception e) {
				LogUtil.error("新增丹塔单日排名异常", e);
			}
		}
		return rdd;
	}

	public void checkResetRankDantaDay(int instPlayer) {
		try {
			InstRankDantaDay rdd = rankDantaDayMap.get(instPlayer);
			if (rdd == null) {
				return;
			}
			rdd.setStartLayer(0);
			rdd.setFinishLayer(0);
			rdd.setUpdateTime(DateUtil.getCurrTime());
			if (rdd.getId() != 0 && DALFactory.getInstRankDantaDayDAL().isExist(rdd.getId(), null)) {
				BaseHandler.getInstRankDantaDayDAL().update(rdd, 0);
			}
		} catch (Exception e) {
			LogUtil.error("下线重置丹塔副本异常", e);
		}
	}

	public int getMaxLayer(int instPlayerId) {
		InstRankDanta rd = rankDantaMap.get(instPlayerId);
		if (rd == null) {
			return 0;
		}
		return rd.getMaxLayer();
	}

	public int getRemainCount(int instPlayerId) {
		int count = 0;
		InstRankDantaDay rdd = rankDantaDayMap.get(instPlayerId);
		if (rdd != null) {
			count = rdd.getStartCount();
		}
		count = DictMapUtil.getSysConfigIntValue(StaticSysConfig.DanTaNum) - count;
		if (count < 0) {
			count = 0;
		}
		return count;
	}

	public String getRankPage(int page) {
		if (rankDantaList.size() < 1) {
			return "";
		}
		page = page - 1;
		if (page < 0) {
			page = 0;
		}
		int sub = page * PAGE_COUNT;
		if (sub < rankDantaList.size()) {
			int sup = (page + 1) * PAGE_COUNT;
			if (sup > rankDantaList.size()) {
				sup = rankDantaList.size();
			}
			InstRankDanta rd = null;
			StringBuilder sb = new StringBuilder();
			int index = 0;
			for (; sub < sup; sub++) {
				rd = rankDantaList.get(sub);
				if (index++ > 0) {
					sb.append('/');
				}
				sb.append(rd.getRankIndex() + 1).append('|');
				sb.append(rd.getPlayerId()).append('|');
				sb.append(rd.getPlayerName()).append('|');
				sb.append(rd.getMaxLayer()).append('|');
				sb.append(rd.getPlayerLevel()).append('|');
				sb.append(rd.getMedalCount()).append('|');
				if (rd.getInstUnionId() > 0) {
					sb.append(ParamConfig.unionMap.get(rd.getInstUnionId()));
				}
				sb.append('|').append(rd.getHeaderCardId());
			}
			return sb.toString();
		}
		return "";
	}

	public String getRankDayPage(int page) {
		if (rankDantaDayList.size() < 1) {
			return "";
		}
		page = page - 1;
		if (page < 0) {
			page = 0;
		}
		int sub = page * PAGE_COUNT;
		if (sub < rankDantaDayList.size()) {
			int sup = (page + 1) * PAGE_COUNT;
			if (sup > rankDantaDayList.size()) {
				sup = rankDantaDayList.size();
			}
			InstRankDantaDay rd = null;
			StringBuilder sb = new StringBuilder();
			int index = 0;
			for (; sub < sup; sub++) {
				rd = rankDantaDayList.get(sub);
				if (index++ > 0) {
					sb.append('/');
				}
				sb.append(rd.getRankIndex() + 1).append('|');
				sb.append(rd.getPlayerId()).append('|');
				sb.append(rd.getPlayerName()).append('|');
				sb.append(rd.getMaxLayer()).append('|');
				sb.append(rd.getPlayerLevel()).append('|');
				sb.append(rd.getMedalCount()).append('|');
				if (rd.getInstUnionId() > 0) {
					sb.append(ParamConfig.unionMap.get(rd.getInstUnionId()));
				}
				sb.append('|').append(rd.getHeaderCardId());
			}
			return sb.toString();
		}
		return "";
	}

	public void checkRank(InstPlayer instPlayer, int finishLayer, int medalCount) {
		InstRankDantaDay rdd = DantaManager.getInstance().getInstRankDantaDay(instPlayer);
		if (rdd.getMaxLayer() < finishLayer) {
			rdd.setMaxLayer(finishLayer);
			rdd.setMedalCount(medalCount);
			checkRankDantaDay(rdd);
		} else if (rdd.getMaxLayer() == finishLayer) {
			if (rdd.getMedalCount() < medalCount) {
				rdd.setMedalCount(medalCount);
				checkRankDantaDay(rdd);
			}
		}
		InstRankDanta rd = DantaManager.getInstance().getInstRankDanta(instPlayer);
		if (rd.getMaxLayer() < finishLayer) {
			rd.setMaxLayer(finishLayer);
			rd.setMedalCount(medalCount);
			checkRankDantaDay(rd);
		} else if (rd.getMaxLayer() == finishLayer) {
			if (rd.getMedalCount() < medalCount) {
				rd.setMedalCount(medalCount);
				checkRankDantaDay(rd);
			}
		}
	}

	private void checkRankDantaDay(InstRankDanta rank) {
		try {
			int index = rank.getRankIndex();
			if (index > 0) {
				InstRankDanta temp = null;
				for (int ii = index - 1; ii > -1; ii--) {
					temp = rankDantaList.get(ii);
					if (rank.getMaxLayer() > temp.getMaxLayer()) {
						temp.setRankIndex(ii + 1);
						rankDantaList.set(temp.getRankIndex(), temp);
						ThreadManager.accessDatabase(new UpdateRankDantaRun(temp));
						rank.setRankIndex(ii);
						rankDantaList.set(rank.getRankIndex(), rank);
					} else if (rank.getMaxLayer() == temp.getMaxLayer() && rank.getMedalCount() > temp.getMedalCount()) {
						temp.setRankIndex(ii + 1);
						rankDantaList.set(temp.getRankIndex(), temp);
						ThreadManager.accessDatabase(new UpdateRankDantaRun(temp));
						rank.setRankIndex(ii);
						rankDantaList.set(rank.getRankIndex(), rank);
					} else {
						rank.setRankIndex(ii + 1);
						rankDantaList.set(rank.getRankIndex(), rank);
						break;
					}
				}
			}
			ThreadManager.accessDatabase(new UpdateRankDantaRun(rank));
		} catch (Exception e) {
			LogUtil.error("计算丹塔历史排名异常", e);
		}
	}

	public void reorderRankDanta() throws Exception {
		Collections.sort(rankDantaList, COMPARATOR);
		for (int ri = 0; ri < rankDantaList.size(); ri++) {
			rankDantaList.get(ri).setRankIndex(ri);
			rankDantaMap.put(rankDantaList.get(ri).getPlayerId(), rankDantaList.get(ri));
		}
	}

	public void reorderRankDantaDay(boolean reset) throws Exception {
		Collections.sort(rankDantaDayList, COMPARATOR_DAY);
		InstRankDantaDay rdd = null;
		for (int ri = 0; ri < rankDantaDayList.size(); ri++) {
			rdd = rankDantaDayList.get(ri);
			boolean update = false;
			if (reset && rdd.getStartLayer() > 0) {
				update = true;
				rdd.setStartLayer(0);
				rdd.setFinishLayer(0);
			}
			update |= rdd.getRankIndex() != ri;
			rdd.setRankIndex(ri);
			rankDantaDayMap.put(rdd.getPlayerId(), rankDantaDayList.get(ri));
			if (update) {
				ThreadManager.accessDatabase(new UpdateRankDantaDayRun(rdd));
			}
		}
	}

	private void checkRankDantaDay(InstRankDantaDay rank) {
		try {
			int index = rank.getRankIndex();
			if (index > 0) {
				InstRankDantaDay temp = null;
				for (int ii = index - 1; ii > -1; ii--) {
					temp = rankDantaDayList.get(ii);
					if (rank.getMaxLayer() > temp.getMaxLayer()) {
						temp.setRankIndex(ii + 1);
						rankDantaDayList.set(temp.getRankIndex(), temp);
						ThreadManager.accessDatabase(new UpdateRankDantaDayRun(temp));
						rank.setRankIndex(ii);
						rankDantaDayList.set(rank.getRankIndex(), rank);
					} else if (rank.getMaxLayer() == temp.getMaxLayer() && rank.getMedalCount() > temp.getMedalCount()) {
						temp.setRankIndex(ii + 1);
						rankDantaDayList.set(temp.getRankIndex(), temp);
						ThreadManager.accessDatabase(new UpdateRankDantaDayRun(temp));
						rank.setRankIndex(ii);
						rankDantaDayList.set(rank.getRankIndex(), rank);
					} else {
						rank.setRankIndex(ii + 1);
						rankDantaDayList.set(rank.getRankIndex(), rank);
						break;
					}
				}
			}
			ThreadManager.accessDatabase(new UpdateRankDantaDayRun(rank));
		} catch (Exception e) {
			LogUtil.error("计算丹塔当日排名异常", e);
		}
	}

}
