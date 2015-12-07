package com.huayi.doupo.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstActivity;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;

public class PlayerActivityData {
	private InstActivity activity;
	private JSONObject   activityDatas = null;

	public PlayerActivityData(InstActivity activity) {
		super();
		this.activity = activity;
		try {
			activityDatas = JSONObject.fromObject(activity.getActivityTime());
		} catch (Exception e) {
			try {
				activityDatas = JSONObject.fromObject("{}");
			} catch (Exception e1) {
			}
			LogUtil.error("加载限时兑换活动数据异常#" + activity.getActivityTime(), e);
		}
	}

	public JSONObject getActivityDatas() {
		try {
			checkData();
		} catch (Exception e) {
			LogUtil.error("checkDataAndUpdate", e);
		}
		return activityDatas;
	}

	public InstActivity getActivity() {
		return activity;
	}

	public void checkDataAndUpdate() throws Exception {
		switch (activity.getActivityId()) {
			case StaticActivity.LimitTimeExchange: {
				List<String> remove = new ArrayList<String>();
				Set<String> keys = activityDatas.keySet();
				long curr = System.currentTimeMillis();
				for (String k : keys) {
					try {
						if (curr > DateUtil.string2MillSecond(activityDatas.getJSONObject(k).getString("stop"))) {
							remove.add(k);
						}
					} catch (Exception e) {
						LogUtil.error("校验时间异常#" + k, e);
					}
				}
				for (int li = 0; li < remove.size(); li++) {
					activityDatas.remove(remove.get(li));
				}
				activity.setActivityTime(activityDatas.toString());
				DALFactory.getInstActivityDAL().update(activity, activity.getInstPlayerId());
				break;
			}
			case StaticActivity.SaveConsume: {
				if (!DateUtil.isSameDay(System.currentTimeMillis(), activityDatas.getLong("date"))) {
					JSONObject json = new JSONObject();
					json.put("cost", 0);
					json.put("date", DateUtil.getCurrMill());
					activityDatas = json;
				}
				activity.setActivityTime(activityDatas.toString());
				DALFactory.getInstActivityDAL().update(activity, activity.getInstPlayerId());
				break;
			}
		}
	}

	public void checkData() throws Exception {
		switch (activity.getActivityId()) {
			case StaticActivity.LimitTimeExchange: {
				List<String> remove = new ArrayList<String>();
				Set<String> keys = activityDatas.keySet();
				long curr = System.currentTimeMillis();
				for (String k : keys) {
					try {
						if (curr > DateUtil.string2MillSecond(activityDatas.getJSONObject(k).getString("stop"))) {
							remove.add(k);
						}
					} catch (Exception e) {
						LogUtil.error("校验时间异常#" + k, e);
					}
				}
				if (remove.size() > 0) {
					for (int li = 0; li < remove.size(); li++) {
						activityDatas.remove(remove.get(li));
					}
					activity.setActivityTime(activityDatas.toString());
					DALFactory.getInstActivityDAL().update(activity, activity.getInstPlayerId());
				}
				break;
			}
			case StaticActivity.SaveConsume: {
				if (!DateUtil.isSameDay(System.currentTimeMillis(), activityDatas.getLong("date"))) {
					JSONObject json = new JSONObject();
					json.put("cost", 0);
					json.put("date", DateUtil.getCurrMill());
					activityDatas = json;
					activity.setActivityTime(json.toString());
					DALFactory.getInstActivityDAL().update(activity, activity.getInstPlayerId());
				}
				break;
			}
		}
	}
}
