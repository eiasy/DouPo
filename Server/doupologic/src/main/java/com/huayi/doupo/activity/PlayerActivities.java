package com.huayi.doupo.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstActivity;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class PlayerActivities {
	/** <activityId, 活动数据> */
	private Map<Integer, PlayerActivityData> playerActivities = new HashMap<Integer, PlayerActivityData>();
	private int playerId;

	public PlayerActivities(int playerId) {
		super();
		this.playerId = playerId;
	}

	public void resetData(List<InstActivity> activities) {
		if (activities != null) {
			if (activities.size() == 1) {
				playerActivities.put(activities.get(0).getActivityId(), new PlayerActivityData(activities.get(0)));
			} else if (activities.size() > 1) {
				InstActivity ia = new InstActivity();
				ia.setActivityId(StaticActivity.LimitTimeExchange);
				ia.setInstPlayerId(playerId);
				JSONObject json = new JSONObject();
				long curr = System.currentTimeMillis();
				for (int ai = 0; ai < activities.size(); ai++) {
					try {
						JSONObject source = JSONObject.fromObject(activities.get(ai).getActivityTime());
						Set<String> keys = source.keySet();
						for (String k : keys) {
							JSONObject ei = source.getJSONObject(k);
							if (DateUtil.string2MillSecond(ei.getString("stop")) > curr) {
								if (json.containsKey(k)) {
									JSONObject ni = json.getJSONObject(k);
									if (ni.getInt("remain") > ei.getInt("remain")) {
										json.put(k, ei);
									}
								} else {
									json.put(k, ei);
								}
							}
						}
					} catch (Exception e) {
						LogUtil.error("解析限时兑换数据异常", e);
					}
					try {
						DALFactory.getInstActivityDAL().deleteById(activities.get(ai).getId(), playerId);
					} catch (Exception e) {
						LogUtil.error("add InstActivity#instPlayerId = " + playerId + ", activityId=" + 11, e);
					}
				}
				ia.setActivityTime(json.toString());
				try {
					DALFactory.getInstActivityDAL().add(ia, playerId);
				} catch (Exception e) {
					LogUtil.error("add InstActivity#instPlayerId = " + playerId + ", activityId=" + 11, e);
				}
				playerActivities.put(StaticActivity.LimitTimeExchange, new PlayerActivityData(ia));
			}
		}
	}

	public PlayerActivityData getPlayerActivityData(int activityId) {
		PlayerActivityData data = playerActivities.get(activityId);
		if (data == null) {
			InstActivity activity = new InstActivity();
			activity.setActivityId(activityId);
			switch (activityId) {
				case StaticActivity.LimitTimeExchange: {
					activity.setActivityTime("{}");
					break;
				}
				case StaticActivity.SaveConsume: {
					JSONObject json = new JSONObject();
					json.put("cost", 0);
					json.put("date", DateUtil.getCurrMill());
					activity.setActivityTime(json.toString());
					break;
				}
			}
			activity.setInstPlayerId(playerId);
			data = new PlayerActivityData(activity);
			try {
				DALFactory.getInstActivityDAL().add(activity, playerId);
			} catch (Exception e) {
				LogUtil.error("add InstActivity#instPlayerId = " + playerId + ", activityId=" + activityId, e);
			}
			playerActivities.put(activityId, data);
		}
		return data;
	}
}
