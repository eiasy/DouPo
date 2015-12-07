package com.huayi.doupo.activity.luck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.activity.ThreadManager;
import com.huayi.doupo.activity.run.ClearLuckDataRun;
import com.huayi.doupo.activity.run.UpdateLimitRemainRun;
import com.huayi.doupo.activity.run.UpdateRankDataRun;
import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictActivityLuck;
import com.huayi.doupo.base.model.InstLuckRank;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class ActivityLuckManager {
	private final static ComparatorRank COMPARATOR = new ComparatorRank();
	private final static ActivityLuckManager instance = new ActivityLuckManager();

	public final static ActivityLuckManager getInstance() {
		return instance;
	}

	/** 普通奖励<数据库ID，道具信息> */
	private Map<Integer, LuckItem> commonAward = new HashMap<Integer, LuckItem>();
	/** 限定奖励<数据库ID，道具信息> */
	private List<LuckItem> limitAward = new ArrayList<LuckItem>();
	/** 数据库ID> */
	private int[] commoonIndex = new int[0];
	/** 数据库ID> */
	private int[] limitIndex = new int[0];
	/** 限定道具的剩余数量<玩家ID，排名及当前状态> */
	private Map<Integer, Integer> limitRemain = new HashMap<Integer, Integer>();
	/** 排名<玩家ID，排名及当前状态> */
	private Map<Integer, RankLuck> rankItemMap = new ConcurrentHashMap<Integer, RankLuck>();

	private List<InstLuckRank> luckRankList = new ArrayList<InstLuckRank>();
	private Map<Integer, InstLuckRank> luckRankMap = new ConcurrentHashMap<Integer, InstLuckRank>();

	private String rank50Info = "";
	private Map<Integer, List<LuckItem>> orderAwards = new HashMap<Integer, List<LuckItem>>();
	private String rankAwards = "";

	public Map<Integer, LuckItem> getCommonAward() {
		return commonAward;
	}

	public List<LuckItem> getLimitAward() {
		return limitAward;
	}

	public Map<Integer, RankLuck> getRankItemMap() {
		return rankItemMap;
	}

	public List<InstLuckRank> getLuckRankList() {
		return luckRankList;
	}

	public Map<Integer, InstLuckRank> getLuckRankMap() {
		return luckRankMap;
	}

	public Map<Integer, List<LuckItem>> getOrderAwards() {
		return orderAwards;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void init() {
		try {
			boolean isNew = false;
			List<InstPlayerBigTable> instPlayerBigTableList = DALFactory.getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '" + StaticBigTable.luckRemain + "'", 0);
			if (instPlayerBigTableList.size() > 0) {
				InstPlayerBigTable bt = instPlayerBigTableList.get(0);
				int[] array = StringUtil.string2IntArray(bt.getValue1().replace(';', ','), ',');
				if (array.length == 8) {
					for (int a = 0; a < array.length / 2; a++) {
						limitRemain.put(array[0 + a * 2], array[1 + 2 * a]);
					}
					isNew = false;
				}
			}
			List<InstLuckRank> instActivityList = DALFactory.getInstLuckRankDAL().getList("", 0);
			luckRankMap = new HashMap<Integer, InstLuckRank>();
			if (instActivityList == null || instActivityList.size() == 0) {
				luckRankList = new ArrayList<InstLuckRank>();
				isNew = true;
			} else {
				for (int ri = 0; ri < instActivityList.size(); ri++) {
					luckRankMap.put(instActivityList.get(ri).getPlayerId(), instActivityList.get(ri));
					String[] array = StringUtil.split(instActivityList.get(ri).getItems(), ";");
					if (array != null) {
						List<LuckItem> list = new ArrayList<LuckItem>();
						for (String a : array) {
							int[] array1 = StringUtil.string2IntArray(a, '_');
							if (array1.length == 7) {
								LuckItem item = new LuckItem();
								item.setId(array1[0]);
								item.setType(array1[1]);
								item.setItemId(array1[2]);
								item.setCount(array1[3]);
								item.setRate1(array1[4]);
								item.setRate2(array1[5]);
								item.setLimit(1 == array1[6]);
								list.add(item);
							}
						}
						RankLuck rank = new RankLuck();
						rank.setItems(list);
						rankItemMap.put(instActivityList.get(ri).getPlayerId(), rank);
					}

				}
				this.luckRankList = instActivityList;
				reorderData();
				pack50rank();
			}
			initAwards(isNew);
		} catch (Exception e) {
			LogUtil.error("初始化幸运轮盘数据异常", e);
		}
	}

	public void resetData(SysActivity dictActivity) {
		if (dictActivity != null) {
			try {
				commonAward.clear();
				limitAward.clear();
				limitRemain.clear();
				luckRankList.clear();
				luckRankMap.clear();
				orderAwards.clear();

				initAwards(true);
				Set<Integer> keys = limitRemain.keySet();
				StringBuilder sb = new StringBuilder();
				int index = 0;
				for (int k : keys) {
					if (index++ > 0) {
						sb.append(",");
					}
					sb.append(k).append(",").append(limitRemain.get(k));
				}
				rank50Info = "";
				DALFactory.getInstPlayerBigTableDAL().update("update Inst_Player_BigTable set value1='" + sb.toString() + "' where properties='" + StaticBigTable.luckRemain + "'");
				ThreadManager.accessDatabase(new ClearLuckDataRun());
			} catch (Exception e) {
				LogUtil.error("初始化幸运轮盘数据异常", e);
			}
		}
	}

	public void checkReset() {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.lucky + "");
			if (dictActivity == null) {
				return;
			}
			long curr = System.currentTimeMillis() + 1000 * 60 * 5;
			if (DateUtil.string2MillSecond(dictActivity.getEndTime()) > curr && DateUtil.string2MillSecond(dictActivity.getStartTime()) < curr) {
				ActivityLuckManager.getInstance().resetData(dictActivity);
			}
		} catch (Exception e) {
			LogUtil.error("初始化幸运轮盘数据异常", e);
		}
	}

	public void reorderData() throws Exception {
		Collections.sort(luckRankList, COMPARATOR);
		for (int ri = 0; ri < luckRankList.size(); ri++) {
			luckRankList.get(ri).setOrderIndex(ri);
		}
		pack50rank();
	}

	public void addRankValue(InstLuckRank rank, int value) {
		try {
			rank.setRankValue(rank.getRankValue() + value);
			boolean isPack = false;
			int index = rank.getOrderIndex();
			if (index > 0) {
				InstLuckRank temp = null;
				for (int ii = index - 1; ii > -1; ii--) {
					temp = luckRankList.get(ii);
					if (rank.getRankValue() > temp.getRankValue()) {
						temp.setOrderIndex(ii + 1);
						luckRankList.set(temp.getOrderIndex(), temp);
						ThreadManager.accessDatabase(new UpdateRankDataRun(temp));
						rank.setOrderIndex(ii);
						luckRankList.set(rank.getOrderIndex(), rank);
						if (ii < 51) {
							isPack = true;
						}
					} else {
						rank.setOrderIndex(ii + 1);
						luckRankList.set(rank.getOrderIndex(), rank);
						if (ii < 51) {
							isPack = true;
						}
						break;
					}
				}
			}
			if (index < 51) {
				isPack = true;
			}
			ThreadManager.accessDatabase(new UpdateRankDataRun(rank));
			if (isPack) {
				pack50rank();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initAwards(boolean isNew) {
		if (isNew) {
			commonAward.clear();
			limitAward.clear();
			limitRemain.clear();
			orderAwards.clear();
		}
		List<DictActivityLuck> luckList = DictList.dictActivityLuckList;
		int commonSum = 0;
		int limitSum = 0;
		
		if (luckList != null) {
			Map<Integer,List<DictActivityLuck>> rankAwardMap = new HashMap<Integer,List<DictActivityLuck>>();
			DictActivityLuck luck = null;
			LuckItem luckItem = null;
			for (int li = 0; li < luckList.size(); li++) {
				luck = luckList.get(li);
				if (luck.getMark().equals("1")) {//普通物品
					luckItem = new LuckItem(luck.getData(), false);
					luckItem.setId(luck.getId());
					if (luckItem != null) {
						commonSum += luckItem.getRate1();
						commonAward.put(luck.getId(), luckItem);
					}
				} else if (luck.getMark().equals("2")) {//限定物品
					luckItem = new LuckItem(luck.getData(), true);
					luckItem.setId(luck.getId());
					limitAward.add(luckItem);
					if (isNew) {
						limitSum += luckItem.getRate1();
						limitRemain.put(luck.getId(), luckItem.getCount());
					} else {
						if (getLimitRemain(luck.getId()) > 0) {
							limitSum += luckItem.getRate1();
						}
					}
				} else {//排名奖励
					int[] array = StringUtil.string2IntArray(luck.getMark(), '-');
					if (array != null && array.length == 2) {
						List<DictActivityLuck> dalList = rankAwardMap.get(array[0]);
						if(dalList==null){
							dalList = new ArrayList<DictActivityLuck>();
							rankAwardMap.put(array[0], dalList);
						}
						dalList.add(luck);
						for (int ai = array[0]; ai <= array[1]; ai++) {
							luckItem = new LuckItem(luck.getData(), false);
							luckItem.setId(luck.getId());
							List<LuckItem> list = orderAwards.get(ai);
							if (list == null) {
								list = new ArrayList<LuckItem>();
								orderAwards.put(ai, list);
							}
							list.add(luckItem);
						}
					}
				}
			}
			//初始化排名奖励字符串
			StringBuilder sbAward = new StringBuilder();
			List<Integer> rankIndexList = new ArrayList<Integer>(rankAwardMap.keySet());
			Collections.sort(rankIndexList);
			for(int ri=0;ri<rankIndexList.size();ri++){
				List<DictActivityLuck> dalList = rankAwardMap.get(rankIndexList.get(ri));
				if(dalList!= null){
					for(DictActivityLuck dal:dalList){
						if (sbAward.length() > 0) {
							sbAward.append(";");
						}
						sbAward.append(dal.getMark()).append(",").append(dal.getData());
					}
				}
			}
			rankAwards = sbAward.toString();

			int[] commoonIndex = new int[commonSum];
			Set<Integer> keys = commonAward.keySet();
			int sub = 0;
			int sup = 0;
			for (int k : keys) {
				luckItem = commonAward.get(k);
				sup += luckItem.getRate1();
				for (; sub < sup; sub++) {
					commoonIndex[sub] = k;
				}
			}
			int[] limitIndex = new int[limitSum];
			sub = 0;
			sup = 0;
			for (int li = 0; li < limitAward.size(); li++) {
				luckItem = limitAward.get(li);
				if (getLimitRemain(luckItem.getId()) < 1) {
					continue;
				}
				sup += luckItem.getRate1();
				for (; sub < sup; sub++) {
					limitIndex[sub] = li;
				}
			}
			this.commoonIndex = commoonIndex;
			this.limitIndex = limitIndex;
		}
		if (isNew) {//新活动要初始化限定物品
			Set<Integer> keys = limitRemain.keySet();
			StringBuilder sb = new StringBuilder();
			int index = 0;
			for (int k : keys) {
				if (index++ > 0) {
					sb.append(";");
				}
				sb.append(k).append(",").append(limitRemain.get(k));
			}
			ThreadManager.accessDatabase(new UpdateLimitRemainRun(sb.toString(), false));
		}
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.lucky + "");
		if (dictActivity == null) {
			return;
		}
	}

	public String getRankAwards() {
		return rankAwards;
	}

	protected int getLimitRemain(int id) {
		Integer c = limitRemain.get(id);
		if (c == null) {
			return 0;
		}
		return c;
	}

	protected int reduceLimitRemain(int id) {
		Integer c = limitRemain.get(id);
		if (c == null) {
			return 0;
		}
		c = c - 1;
		limitRemain.put(id, c);
		if (c < 1) {
			int sum = 0;
			LuckItem item = null;
			for (int li = 0; li < limitAward.size(); li++) {
				item = limitAward.get(li);
				if (getLimitRemain(item.getId()) > 0) {
					sum += item.getRate1();
				}
			}
			int[] limtiIndex = new int[sum];
			int sub = 0;
			int sup = 0;
			for (int li = 0; li < limitAward.size(); li++) {
				item = limitAward.get(li);
				if (getLimitRemain(item.getId()) > 0) {
					sup += item.getRate1();
					for (; sub < sup; sub++) {
						limtiIndex[sub] = li;
					}
				}
			}
			this.limitIndex = limtiIndex;
		}
		Set<Integer> keys = limitRemain.keySet();
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (int k : keys) {
			if (index++ > 0) {
				sb.append(",");
			}
			sb.append(k).append(",").append(limitRemain.get(k));
		}
		ThreadManager.accessDatabase(new UpdateLimitRemainRun(sb.toString(), false));
		return c;
	}

	public Map<Integer, Integer> getLimitRemain() {
		return limitRemain;
	}

	public int getRankCount() {
		return luckRankList.size();
	}

	public InstLuckRank getRankLuck(int instPlayerId) {
		InstLuckRank rl = luckRankMap.get(instPlayerId);
		boolean isNew = false;
		if (rl == null) {
			isNew = true;
			rl = new InstLuckRank();
			rl.setPlayerId(instPlayerId);
			rl.setName(ParamConfig.playerNameMap.get(instPlayerId));
			rl.setOrderIndex(luckRankList.size());
			rl.setRankValue(1);

		}
		if (!DateUtil.isSameDay(DateUtil.string2MillSecond(rl.getCountReset()), System.currentTimeMillis())) {
			refresh(rl, true, isNew);
		}
		if (isNew) {
			try {
				rl.setItems(rankItemMap.get(instPlayerId).toForServer());
				DALFactory.getInstLuckRankDAL().add(rl, 0);
				luckRankList.add(rl);
				luckRankMap.put(instPlayerId, rl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rl;
	}

	public InstLuckRank refresh(InstLuckRank rl, boolean auto) {
		return refresh(rl, auto, false);
	}

	private InstLuckRank refresh(InstLuckRank rl, boolean auto, boolean isNew) {
		if (rl != null) {
			if (!auto) {
				ActivityLuckManager.getInstance().addRankValue(rl, DictMapUtil.getSysConfigIntValue(StaticSysConfig.refreshIntegral));
			}
			long curr = System.currentTimeMillis();
			if (!DateUtil.isSameDay(DateUtil.string2MillSecond(rl.getCountReset()), curr)) {
				rl.setRefreshRemain(DictMapUtil.getSysConfigIntValue(StaticSysConfig.refreshNum));
				rl.setStartRemain(DictMapUtil.getSysConfigIntValue(StaticSysConfig.openNum));
			}
			rl.setCountReset(DateUtil.getCurrTime());

			LuckItem limitItem = null;
			// 生成限定物品
			if (limitIndex.length > 0 && StringUtil.getRandom(0, 100) < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.refreshChance) * 100) {
				int index = StringUtil.getRandom(0, limitIndex.length - 1);
				int itemId = limitIndex[index];
				limitItem = limitAward.get(itemId);
				limitItem.setCount(1);
			}
			List<LuckItem> list = new ArrayList<LuckItem>();
			int count = 8;
			if (limitItem != null) {
				count = 7;
			}
			for (int li = 0; li < count; li++) {
				list.add(commonAward.get(commoonIndex[StringUtil.getRandom(0, commoonIndex.length - 1)]));
			}
			if (limitItem != null) {
				int random = StringUtil.getRandom(0, 6);
				list.add(random, limitItem);
			}
			RankLuck rankItems = rankItemMap.get(rl.getPlayerId());
			if (rankItems == null) {
				rankItems = new RankLuck();
				rankItemMap.put(rl.getPlayerId(), rankItems);
			}
			rankItems.setItems(list);
			rl.setItems(rankItems.toForServer());
			if (!isNew) {
				ThreadManager.accessDatabase(new UpdateRankDataRun(rl));
			}
		}
		return rl;
	}

	public String getLimitInfo() {
		StringBuilder sb = new StringBuilder();
		LuckItem item = null;
		for (int li = 0; li < limitAward.size(); li++) {
			item = limitAward.get(li);
			if (li > 0) {
				sb.append(';');
			}
			sb.append(item.getType()).append('_').append(item.getItemId()).append('_').append(getLimitRemain(item.getId()));
		}
		return sb.toString();
	}

	public String getRank50Info() {
		return rank50Info;
	}

	private void pack50rank() {
		int size = luckRankList.size();
		if (size > 50) {
			size = 50;
		}
		InstLuckRank rl = null;
		StringBuilder sb = new StringBuilder();
		for (int ri = 0; ri < size; ri++) {
			rl = luckRankList.get(ri);
			if (ri > 0) {
				sb.append(';');
			}
			sb.append(rl.getPlayerId()).append('|').append(rl.getName()).append('|').append(rl.getOrderIndex() + 1).append('|').append(rl.getRankValue());
		}
		rank50Info = sb.toString();
	}

	public void dispatchAward() {
		InstLuckRank rl = null;
		List<LuckItem> list = null;
		LuckItem item = null;
		StringBuilder sb = new StringBuilder();
		for (int ri = 0; ri < luckRankList.size(); ri++) {
			sb.setLength(0);
			rl = luckRankList.get(ri);
			list = orderAwards.get(rl.getOrderIndex() + 1);
			if (list != null) {
				for (int li = 0; li < list.size(); li++) {
					if (li > 0) {
						sb.append(';');
					}
					item = list.get(li);
					sb.append(item.getType()).append('_').append(item.getItemId()).append('_').append(item.getCount());
				}
				Player player = PlayerMapUtil.getPlayerByPlayerId(rl.getPlayerId());
				try {
					LogUtil.warn("幸运轮盘排名|" + (rl.getOrderIndex() + 1) + "|" + rl.getPlayerId() + "|" + sb.toString());
					// 在线玩家
					if (player != null) {
						if (sb.length() > 0) {
							MessageData otherSyncMsgData = new MessageData();
							ActivityUtil.addInstPlayerAward(rl.getPlayerId(), 3, sb.toString(), DateUtil.getCurrTime(), "您在幸运转盘活动中的积分名次为第 " + (rl.getOrderIndex() + 1) + "名,获得奖励：", otherSyncMsgData);
							MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
						}
					} else {
						ActivityUtil.addInstPlayerAward(rl.getPlayerId(), 3, sb.toString(), DateUtil.getCurrTime(), "您在幸运转盘活动中的积分名次为第 " + (rl.getOrderIndex() + 1) + "名,获得奖励：", new MessageData());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Set<Integer> keysRemain = limitRemain.keySet();
		sb.setLength(0);
		int index = 0;
		for (int k : keysRemain) {
			if (index++ > 0) {
				sb.append(",");
			}
			sb.append(k).append(",").append(0);
		}
		rank50Info = "";
		commonAward.clear();
		limitAward.clear();
		limitRemain.clear();
		luckRankList.clear();
		luckRankMap.clear();
		orderAwards.clear();
		ThreadManager.accessDatabase(new UpdateLimitRemainRun("", false));
		ThreadManager.accessDatabase(new ClearLuckDataRun());
	}

	public Object[] getAwardLuckItem(int instPlayerId, InstLuckRank rl,int count) {
		return getAwardLuckItem(instPlayerId, false, rl, count);
	}

	public Object[] getAwardLuckItem(int instPlayerId, boolean free, InstLuckRank rl,int count) {
		return rankItemMap.get(instPlayerId).getAwardLuckItem(free, rl, count);
	}

	public String getInfoForClient(int instPlayerId) {
		return rankItemMap.get(instPlayerId).getInfoForClient();
	}
}
