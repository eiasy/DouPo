package com.huayi.doupo.activity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstActivity;
import com.huayi.doupo.base.model.statics.StaticActivity;

public class PlayerActivityManager {
	private final static PlayerActivityManager instance = new PlayerActivityManager();

	public final static PlayerActivityManager getInstance() {
		return instance;
	}

	/**
	 * 玩家的活动数据<玩家ID，活动集合>
	 */
	private Map<Integer, PlayerActivities> playerActivities = new ConcurrentHashMap<Integer, PlayerActivities>();

	private PlayerActivityManager() {
	}

	public void loadPlayerActivities(int instPlayerId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" instPlayerId = ").append(instPlayerId);
		sb.append(" and activityId=").append(StaticActivity.LimitTimeExchange);
		List<InstActivity> instActivityList = DALFactory.getInstActivityDAL().getList(sb.toString(), instPlayerId);
		PlayerActivities pas = playerActivities.get(instPlayerId);
		if (pas == null) {
			pas = new PlayerActivities(instPlayerId);
			playerActivities.put(instPlayerId, pas);
		}
		pas.resetData(instActivityList);
	}

	public PlayerActivities getPlayerActivities(int playerId) {
		PlayerActivities pa = playerActivities.get(playerId);
		if (pa == null) {
			pa = new PlayerActivities(playerId);
			playerActivities.put(playerId, pa);
		}
		return pa;
	}
	
	public void removePlayerActivities(int instPlayerId){
		playerActivities.remove(instPlayerId);
	}

}
