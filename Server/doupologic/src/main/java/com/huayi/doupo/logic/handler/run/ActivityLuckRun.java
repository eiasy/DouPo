package com.huayi.doupo.logic.handler.run;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.activity.luck.ActivityLuckManager;
import com.huayi.doupo.activity.luck.LuckItem;
import com.huayi.doupo.activity.run.IActRunnable;
import com.huayi.doupo.base.model.InstLuckRank;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.custom.CustomMarqueeType;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.marquee.MarqueeUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

public class ActivityLuckRun implements IActRunnable {
	private Map<String, Object> msgMap;
	private String channelId;

	public ActivityLuckRun(Map<String, Object> msgMap, String channelId) {
		super();
		this.msgMap = msgMap;
		this.channelId = channelId;
	}

	@Override
	public void run() {
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.lucky + "");
		if (dictActivity == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}

		if (DateUtil.string2MillSecond(dictActivity.getStartTime()) > System.currentTimeMillis()) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		if (DateUtil.string2MillSecond(dictActivity.getEndTime()) < System.currentTimeMillis()) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		int type = Integer.parseInt(msgMap.get("type").toString());
		switch (type) {
			case 1: {// 打开活动界面
				openUI(type);
				break;
			}
			case 2: {// 刷新
				try {
					refresh(type);
				} catch (Exception e) {
					LogUtil.error("刷新幸运轮盘数据异常", e);
				}
				break;
			}
			case 3: {// 3启动一次
				try {
					startOne(type);
				} catch (Exception e) {
					LogUtil.error("启动一次异常", e);
				}
				break;
			}
			case 4: {// 启动十次
				try {
					startTen(type);
				} catch (Exception e) {
					LogUtil.error("启动十次异常", e);
				}
				break;
			}
			default: {

			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void startTen(int type) throws Exception {
		int instPlayerId = BaseHandler.getInstPlayerId(channelId);
		InstPlayer instPlayer = BaseHandler.getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstLuckRank rl = ActivityLuckManager.getInstance().getRankLuck(instPlayerId);
		// if (rl.getStartRemain() > 0) {
		// rl.setStartRemain(rl.getStartRemain() - 1);
		// } else {
		if (instPlayer.getGold() < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.tenOpenGold)) {
			MessageUtil.sendFailMsg(channelId, msgMap, "元宝数量不足");
			return;
		}
		PlayerUtil.goldStatics(instPlayer, 0, DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenOpenGold), msgMap);
		BaseHandler.getInstPlayerDAL().update(instPlayer, instPlayerId);
		MessageData syncMsgData = new MessageData();
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		// }

		MessageData retMsgData = new MessageData();
		int index = 0;
		StringBuilder sb = new StringBuilder();

		// 定义一个map
		Map<String, String> thingMap = new HashMap<String, String>();
		int count = 10;
		Object[] data = ActivityLuckManager.getInstance().getAwardLuckItem(instPlayerId, rl, count);
		ActivityLuckManager.getInstance().refresh(rl, true);
		index = (int) data[0];
		for (int i = 0; i < count; i++) {
			LuckItem item = (LuckItem) data[i * 2 + 1];

			// 组装map
			ThingUtil.groupThingMap(thingMap, item.getType(), item.getItemId(), item.getCount());
			if(i>0){
				sb.append(";");
			}
			sb.append(item.getType()).append("_" + item.getItemId()).append("_" + item.getCount()).append("_").append(data[i * 2 + 2]);
			if (item.isLimit()) {
				rl.setGetLimitCount(rl.getGetLimitCount() + 1);
				rl.setLastLimitValue(rl.getRankValue());
				MarqueeUtil.putMsgToMarquee(channelId, ThingUtil.getThingName(item.getType(), item.getItemId()), CustomMarqueeType.MARQUEE_TYPE_OTHER, CustomMarqueeType.MARQUEE_SOURCE_ZHUANPAN);
			}
		}
		rl.setPayStartCount(rl.getPayStartCount() + 10);
		// 发送物品
		ThingUtil.groupThingMap(instPlayer, thingMap, retMsgData, msgMap);

		ActivityLuckManager.getInstance().addRankValue(rl, DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenOpenIntegral));
		MessageUtil.sendSyncMsg(channelId, retMsgData);
		// ActivityLuckManager.getInstance().reorderData();
		openUI(type, index, sb.toString(), retMsgData);
		LogUtil.info("幸运轮盘|启动|10|" + sb.toString());
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void startOne(int type) throws Exception {
		int instPlayerId = BaseHandler.getInstPlayerId(channelId);
		InstPlayer instPlayer = BaseHandler.getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstLuckRank rl = ActivityLuckManager.getInstance().getRankLuck(instPlayerId);
		boolean free = false;
		if (rl.getStartRemain() > 0) {
			rl.setStartRemain(rl.getStartRemain() - 1);
			free = true;
		} else {
			if (instPlayer.getGold() < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.oneOpenGold)) {
				MessageUtil.sendFailMsg(channelId, msgMap, "元宝数量不足");
				return;
			}
			PlayerUtil.goldStatics(instPlayer, 0, DictMapUtil.getSysConfigIntValue(StaticSysConfig.oneOpenGold), msgMap);
			BaseHandler.getInstPlayerDAL().update(instPlayer, instPlayerId);
			MessageData syncMsgData = new MessageData();
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
			rl.setPayStartCount(rl.getPayStartCount() + 1);
		}
		Object[] data = ActivityLuckManager.getInstance().getAwardLuckItem(instPlayerId, free, rl, 1);
		LuckItem item = (LuckItem) data[1];
		MessageData retMsgData = new MessageData();
		ThingUtil.groupThing(instPlayer, item.getType(), item.getItemId(), item.getCount(), retMsgData, msgMap);
		ActivityLuckManager.getInstance().refresh(rl, true);
		MessageUtil.sendSyncMsg(channelId, retMsgData);
		if (item.isLimit()) {
			rl.setGetLimitCount(rl.getGetLimitCount() + 1);
			rl.setLastLimitValue(rl.getRankValue());
			MarqueeUtil.putMsgToMarquee(channelId, ThingUtil.getThingName(item.getType(), item.getItemId()), CustomMarqueeType.MARQUEE_TYPE_OTHER, CustomMarqueeType.MARQUEE_SOURCE_ZHUANPAN);
		}
		ActivityLuckManager.getInstance().addRankValue(rl, DictMapUtil.getSysConfigIntValue(StaticSysConfig.oneOpenIntegral));
		// ActivityLuckManager.getInstance().reorderData();
		openUI(type, (int) data[0], item.getType() + "_" + item.getItemId() + "_" + item.getCount() + "_" + data[2], retMsgData);
		LogUtil.info("幸运轮盘|启动|1|" + item.getType() + "_" + item.getItemId() + "_" + item.getCount() + "_" + data[2]);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void refresh(int type) throws Exception {
		int instPlayerId = BaseHandler.getInstPlayerId(channelId);
		InstLuckRank rl = ActivityLuckManager.getInstance().getRankLuck(instPlayerId);
		if (rl.getRefreshRemain() > 0) {
			rl.setRefreshRemain(rl.getRefreshRemain() - 1);
		} else {
			InstPlayer instPlayer = BaseHandler.getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			if (instPlayer.getGold() < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.refreshGoldNum)) {
				MessageUtil.sendFailMsg(channelId, msgMap, "元宝数量不足");
				return;
			}
			rl.setPayRefreshCount(rl.getPayRefreshCount() + 1);
			PlayerUtil.goldStatics(instPlayer, 0, DictMapUtil.getSysConfigIntValue(StaticSysConfig.refreshGoldNum), msgMap);
			BaseHandler.getInstPlayerDAL().update(instPlayer, instPlayerId);
			MessageData syncMsgData = new MessageData();
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
		}

		ActivityLuckManager.getInstance().refresh(rl, false);
		openUI(type);
		LogUtil.warn("幸运轮盘|刷新|1");
	}

	private void openUI(int type) {
		openUI(type, 0, "", null);
	}

	/**
	 * 打开活动界面
	 * 
	 * @author 李天喜
	 * @date 2015-8-20 上午13:40:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	private void openUI(int type, int index, String awards, MessageData retMsgData) {
		int instPlayerId = BaseHandler.getInstPlayerId(channelId);
		InstLuckRank rl = ActivityLuckManager.getInstance().getRankLuck(instPlayerId);
		if (retMsgData == null) {
			retMsgData = new MessageData();
		}
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.lucky + "");
		retMsgData.putIntItem("type", type);
		retMsgData.putStringItem("common", ActivityLuckManager.getInstance().getInfoForClient(instPlayerId));
		retMsgData.putStringItem("limit", ActivityLuckManager.getInstance().getLimitInfo());
		retMsgData.putStringItem("rank", ActivityLuckManager.getInstance().getRank50Info());// 前50名
		retMsgData.putIntItem("point", rl.getRankValue());
		retMsgData.putIntItem("order", rl.getOrderIndex() + 1);
		retMsgData.putLongItem("start", DateUtil.string2MillSecond(dictActivity.getStartTime()));
		retMsgData.putLongItem("stop", DateUtil.string2MillSecond(dictActivity.getEndTime()));
		retMsgData.putIntItem("oneCost", DictMapUtil.getSysConfigIntValue(StaticSysConfig.oneOpenGold));
		retMsgData.putIntItem("tenCost", DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenOpenGold));
		retMsgData.putIntItem("refreshCost", DictMapUtil.getSysConfigIntValue(StaticSysConfig.refreshGoldNum));
		retMsgData.putIntItem("refreshRemain", rl.getRefreshRemain());
		retMsgData.putIntItem("startRemain", rl.getStartRemain());
		retMsgData.putStringItem("awards", awards);
		retMsgData.putIntItem("awardIndex", index);
		retMsgData.putStringItem("rankAwards", ActivityLuckManager.getInstance().getRankAwards());
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

}
