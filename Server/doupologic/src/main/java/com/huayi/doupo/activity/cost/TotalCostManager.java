package com.huayi.doupo.activity.cost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstTotalCost;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class TotalCostManager {
	private final static TotalCostManager instance = new TotalCostManager();

	public final static TotalCostManager getInstance() {
		return instance;
	}

	private Map<Integer, InstTotalCost> playerCost = new ConcurrentHashMap<Integer, InstTotalCost>();

	public InstTotalCost loadPlayerActivities(int instPlayerId) {
		try {
			List<InstTotalCost> costList = DALFactory.getInstTotalCostDAL().getList(" playerId=" + instPlayerId, instPlayerId);
			if (costList != null && costList.size() > 0) {
				if (costList.size() > 1) {
					Map<Integer, Integer> states = new HashMap<Integer, Integer>();
					int totalCount = 0;
					for (int ti = 0; ti < costList.size(); ti++) {
						if (DateUtil.isSameDay(DateUtil.string2MillSecond(costList.get(ti).getCostReset()), System.currentTimeMillis())) {
							int[] array = StringUtil.string2IntArray(costList.get(ti).getAmountState(), '_');
							for (int a = 0; a < array.length / 2; a++) {
								states.put(array[0 + a * 2], array[1 + a * 2]);
							}
							totalCount += costList.get(ti).getCostCount();
						}
						DALFactory.getInstTotalCostDAL().deleteById(costList.get(ti).getId(), instPlayerId);
					}
					InstTotalCost cost = new InstTotalCost();
					cost.setPlayerId(instPlayerId);
					cost.setCostCount(totalCount);
					cost.setCostReset(DateUtil.getCurrTime());
					Set<Integer> keys = states.keySet();
					int index = 0;
					StringBuilder sb = new StringBuilder();
					for (int k : keys) {
						if (index++ > 0) {
							sb.append("_");
						}
						sb.append(k).append("_").append(states.get(k));
					}
					cost.setAmountState(sb.toString());
					DALFactory.getInstTotalCostDAL().add(cost, instPlayerId);
					playerCost.put(instPlayerId, cost);
					return cost;
				} else {
					playerCost.put(instPlayerId, costList.get(0));
					return costList.get(0);
				}
			} else {
				InstTotalCost cost = new InstTotalCost();
				cost.setPlayerId(instPlayerId);
				cost.setCostCount(0);
				cost.setCostReset(DateUtil.getCurrTime());
				DALFactory.getInstTotalCostDAL().add(cost, instPlayerId);
				playerCost.put(instPlayerId, cost);
				return cost;
			}
		} catch (Exception e) {
			LogUtil.error("插入累计消耗异常", e);
		}
		return null;
	}

	public void removePlayerData(int instPlayerId) {
		playerCost.remove(instPlayerId);
	}

	public void checkTotalCost(int instPlayerId) {
		costYuanbao(instPlayerId, 0);
	}

	public void costYuanbao(int instPlayerId, int count) {
		try {
			SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.SaveConsume + "");
			if (dictActivity == null) {
				return;
			}
			long curr = System.currentTimeMillis();
			if (curr < DateUtil.string2MillSecond(dictActivity.getStartTime()) || curr > DateUtil.string2MillSecond(dictActivity.getEndTime())) {
				return;
			}
			InstTotalCost cost = playerCost.get(instPlayerId);
			if (cost == null) {
				cost = loadPlayerActivities(instPlayerId);
			}
			boolean isSave = false;
			if (!DateUtil.isSameDay(System.currentTimeMillis(), DateUtil.string2MillSecond(cost.getCostReset()))) {
				cost.setCostReset(DateUtil.getCurrTime());
				cost.setCostCount(0);
				cost.setAmountState("");
				isSave = true;
			}
			if (count > 0) {
				cost.setCostCount(cost.getCostCount() + count);
				isSave = true;
			}
			if (isSave) {
				DALFactory.getInstTotalCostDAL().update(cost, instPlayerId);
			}
		} catch (Exception e) {
			LogUtil.error("更新累计消耗异常", e);
		}
	}

	public InstTotalCost getInstTotalCost(int instPlayerId) {
		InstTotalCost cost = playerCost.get(instPlayerId);
		if (cost == null) {
			cost = loadPlayerActivities(instPlayerId);
		}
		if (!DateUtil.isSameDay(System.currentTimeMillis(), DateUtil.string2MillSecond(cost.getCostReset()))) {
			cost.setCostReset(DateUtil.getCurrTime());
			cost.setCostCount(0);
			try {
				DALFactory.getInstTotalCostDAL().update(cost, instPlayerId);
			} catch (Exception e) {
				LogUtil.error("更新累计消耗异常", e);
			}
		}
		return cost;
	}
}
