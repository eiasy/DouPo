package com.huayi.doupo.logic.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.activity.PlayerActivities;
import com.huayi.doupo.activity.PlayerActivityManager;
import com.huayi.doupo.activity.ThreadManager;
import com.huayi.doupo.activity.cost.TotalCostManager;
import com.huayi.doupo.activity.run.DantaHandlerRun;
import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictActivityAddRecharge;
import com.huayi.doupo.base.model.DictActivityAllPeapleWeal;
import com.huayi.doupo.base.model.DictActivityDailyDeals;
import com.huayi.doupo.base.model.DictActivityFlashSale;
import com.huayi.doupo.base.model.DictActivityFund;
import com.huayi.doupo.base.model.DictActivityGrabTheHour;
import com.huayi.doupo.base.model.DictActivityHoliday;
import com.huayi.doupo.base.model.DictActivityLevelBag;
import com.huayi.doupo.base.model.DictActivityLimitShopping;
import com.huayi.doupo.base.model.DictActivityLimitTimeHeroJiFenReward;
import com.huayi.doupo.base.model.DictActivityLimitTimeHeroRankReward;
import com.huayi.doupo.base.model.DictActivityLvStore;
import com.huayi.doupo.base.model.DictActivityMonthCardStore;
import com.huayi.doupo.base.model.DictActivityMonthCardStoreTemp;
import com.huayi.doupo.base.model.DictActivityOpenServiceBag;
import com.huayi.doupo.base.model.DictActivityPrivateSale;
import com.huayi.doupo.base.model.DictActivitySignIn;
import com.huayi.doupo.base.model.DictActivityStarStore;
import com.huayi.doupo.base.model.DictActivityStrogerHero;
import com.huayi.doupo.base.model.DictActivityTreasures;
import com.huayi.doupo.base.model.DictActivityVipStore;
import com.huayi.doupo.base.model.DictRecharge;
import com.huayi.doupo.base.model.DictTryToPractice;
import com.huayi.doupo.base.model.DictTryToPracticeType;
import com.huayi.doupo.base.model.DictVIP;
import com.huayi.doupo.base.model.DictactivityExchange;
import com.huayi.doupo.base.model.DictactivityTotalCost;
import com.huayi.doupo.base.model.InstActivity;
import com.huayi.doupo.base.model.InstActivityLevelBag;
import com.huayi.doupo.base.model.InstActivityOnlineRewards;
import com.huayi.doupo.base.model.InstActivityOpenServiceBag;
import com.huayi.doupo.base.model.InstActivitySignIn;
import com.huayi.doupo.base.model.InstActivityTreasures;
import com.huayi.doupo.base.model.InstAuctionShop;
import com.huayi.doupo.base.model.InstHJYStore;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerAchievementValue;
import com.huayi.doupo.base.model.InstPlayerArenaNPC;
import com.huayi.doupo.base.model.InstPlayerAward;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerChapter;
import com.huayi.doupo.base.model.InstPlayerGrabTheHour;
import com.huayi.doupo.base.model.InstPlayerPrivateSale;
import com.huayi.doupo.base.model.InstPlayerRecharge;
import com.huayi.doupo.base.model.InstPlayerRecruit;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.InstPlayerTryToPractice;
import com.huayi.doupo.base.model.InstTotalCost;
import com.huayi.doupo.base.model.InstUnion;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.InstUser;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticActivityHoliday;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticFunctionOpen;
import com.huayi.doupo.base.model.statics.StaticRecruitType;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticSysConfigStr;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.StaticTryToPracticeType;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.CollectionUtil;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.DateUtil.DateType;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.run.ActivityLuckRun;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.CardUtil;
import com.huayi.doupo.logic.handler.util.GmUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.VerifyUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;


public class ActivityHandler extends BaseHandler {

	/**
	 * 玩家拍卖行/黑角域倒计时时间到
	 * 
	 * @author hzw
	 * @date 2014-9-27下午4:44:50
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateAuctionOrHjy(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);

		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}

		int type = (Integer) msgMap.get("type"); // 1-拍卖行刷新物品 2-黑角域增加刷新次数 3-黑角域刷新物品
		int instActivityId = (Integer) msgMap.get("instActivityId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstActivity instActivity = getInstActivityDAL().getModel(instActivityId, instPlayerId);
		if (instActivity.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if (type == 1) {
			String startTime = instActivity.getActivityTime(); // 开始时间
			String currTime = DateUtil.getCurrTime(); // 当前时间
			int jg = DictMapUtil.getSysConfigIntValue(StaticSysConfig.auctionShopResetTime); // 间隔时间
			long jgm = jg * 60 * 60 * 1000; // 间隔毫秒数
			long startm = DateUtil.getMillSecond(startTime);
			long currm = DateUtil.getMillSecond(currTime);
			long intval = jgm - ((currm - startm) % jgm);
			if (intval >= 0 && (currm - startm) / jgm > 0) {
				ActivityUtil.addInstAuctionShop(instPlayerId, syncMsgData);
				instActivity.setActivityTime(currTime);
				getInstActivityDAL().update(instActivity, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
			} else {
				if (instPlayer.getGold() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.auctionShopResetGold)) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
					return;
				} else {
					// 使用元宝
					PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, DictMapUtil.getSysConfigIntValue(StaticSysConfig.auctionShopResetGold), msgMap);
					getInstPlayerDAL().update(instPlayer, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
					ActivityUtil.addInstAuctionShop(instPlayerId, syncMsgData);
				}
			}
		} else if (type == 2) {
			String startTime = instActivity.getActivityTime(); // 开始时间
			String currTime = DateUtil.getCurrTime(); // 当前时间
			int jg = DictMapUtil.getSysConfigIntValue(StaticSysConfig.hJYStoreResetTime); // 间隔时间
			long jgm = jg * 60 * 60 * 1000; // 间隔毫秒数
			long startm = DateUtil.getMillSecond(startTime);
			long currm = DateUtil.getMillSecond(currTime);
			long intval = jgm - ((currm - startm) % jgm);
			if (intval >= 0 && (currm - startm) / jgm > 0) {
				if (instActivity.getUseNum() > 0) {
					if ((currm - startm) / jgm >= instActivity.getUseNum()) {
						instActivity.setUseNum(0);
					} else {
						instActivity.setUseNum((int) (instActivity.getUseNum() - (currm - startm) / jgm));
					}
					instActivity.setActivityTime(currTime);
					getInstActivityDAL().update(instActivity, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
				}
			}
		} else if (type == 3) {
			DictVIP dictVIP = CardUtil.getVipByLevel(instPlayer.getVipLevel());
			List<InstPlayerThing> things = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.refreshSign, instPlayerId);
			if (instActivity.getUseNum() < dictVIP.getHJYReset()) {
				// 免费刷新
				instActivity.setUseNum(instActivity.getUseNum() + 1);
				if (instActivity.getUseNum() == 1) {
					instActivity.setActivityTime(DateUtil.getCurrTime());
				}
				getInstActivityDAL().update(instActivity, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);

				ActivityUtil.addInstHJYStore(instPlayerId, syncMsgData);
			} else if (things != null && things.size() > 0) {
				// 使用刷新令
				InstPlayerThing instPlayerThing = things.get(0);
				ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, 1, syncMsgData, msgMap);

				ActivityUtil.addInstHJYStore(instPlayerId, syncMsgData);
			} else if (instPlayer.getGold() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.hJYStoreResetGold)) {
				// 使用元宝
				//判断是否跨天，是则重置次数
				String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
				String  oldUpdateTime = instActivity.getSpareTwo();
				if(oldUpdateTime!=null&&oldUpdateTime.length()>0&&!oldUpdateTime.equals("null")){
					if(!DateUtil.isSameDay(updateTime,oldUpdateTime,DateFormat.YMDHMS)){
						instActivity.setSpareOne("0");
					}
				}
				int nowCount;
				if(instActivity.getSpareOne()==null||instActivity.getSpareOne().length()<=0||instActivity.getSpareOne().equals("null")){
					nowCount=0;
				}else{
					try{
						nowCount=Integer.valueOf(instActivity.getSpareOne());
					}catch(Exception e){
						nowCount=0;
					}
				}
				if(nowCount<dictVIP.getHjyFreshCount()){
					PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, DictMapUtil.getSysConfigIntValue(StaticSysConfig.hJYStoreResetGold), msgMap);
					getInstPlayerDAL().update(instPlayer, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
					instActivity.setSpareOne(nowCount+1+"");
					instActivity.setSpareTwo(updateTime);
					getInstActivityDAL().update(instActivity, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
					ActivityUtil.addInstHJYStore(instPlayerId, syncMsgData);
				}else{
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notResetNum);
					return;
				}
			} else {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
				return;
			}

		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 兑换商品
	 * 
	 * @author hzw
	 * @date 2014-9-29上午10:40:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void convertGoods(Map<String, Object> msgMap, String channelId) throws Exception {
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int type = (Integer) msgMap.get("type"); // 1-拍卖行兑换物品 2--黑角域兑换物品
		
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if (type == 1) {
			int instAuctionShopId = (Integer) msgMap.get("instAuctionShopId");
			InstAuctionShop instAuctionShop = getInstAuctionShopDAL().getModel(instAuctionShopId, instPlayerId);
			if (instAuctionShop == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, "无此物品,登录重试");
				return;
			}
			if (instAuctionShop.getInstPlayerId() != instPlayerId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if (instAuctionShop.getSellOut() == 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_sellOut);
				return;
			}
			if (instAuctionShop.getSellType() == 1) { // 1-元宝 2-魂源
				if (instPlayer.getGold() < instAuctionShop.getSellValue()) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
					return;
				} else {
					// 使用元宝
					PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, instAuctionShop.getSellValue(), msgMap);
					getInstPlayerDAL().update(instPlayer, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
				}
			} else {
				List<InstPlayerThing> things = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.soulSource, instPlayerId);
				if (things == null || things.size() <= 0 || (things.size() > 0 && things.get(0).getNum() < instAuctionShop.getSellValue())) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notSoulSourceNum);
					return;
				} else {
					// 使用魂源
					InstPlayerThing instPlayerThing = things.get(0);
					ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, instAuctionShop.getSellValue(), syncMsgData, msgMap);
				}
			}
			// 兑换物品
			ThingUtil.groupThing(instPlayer, instAuctionShop.getTableTypeId(), instAuctionShop.getTableFieldId(), instAuctionShop.getValue(), syncMsgData, msgMap);
			instAuctionShop.setSellOut(1); // 0-未售完 1-售完
			getInstAuctionShopDAL().update(instAuctionShop, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instAuctionShop, instAuctionShop.getId(), instAuctionShop.getResult(), syncMsgData);
		} else if (type == 2) {
			if (instPlayer.getLevel() < DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.hJYStoreLevel + "").getLevel()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
				return;
			}
			int instHJYStoreId = (Integer) msgMap.get("instHJYStoreId");
			InstHJYStore instHJYStore = getInstHJYStoreDAL().getModel(instHJYStoreId, instPlayerId);
			if (instHJYStore == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, "无此物品,登录重试");
				return;
			}
			if (instHJYStore.getInstPlayerId() != instPlayerId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if (instHJYStore.getSellOut() == 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_sellOut);
				return;
			}
			if (instHJYStore.getSellType() == 1) { // 1-元宝 2-魂源
				if (instPlayer.getGold() < instHJYStore.getSellValue()) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
					return;
				} else {
					// 使用元宝
					PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, instHJYStore.getSellValue(), msgMap);
					getInstPlayerDAL().update(instPlayer, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
				}
			} else {
				List<InstPlayerThing> things = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.soulSource, instPlayerId);
				if (things == null || things.size() <= 0 || (things.size() > 0 && things.get(0).getNum() < instHJYStore.getSellValue())) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notSoulSourceNum);
					return;
				} else {
					// 使用魂源
					InstPlayerThing instPlayerThing = things.get(0);
					ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, instHJYStore.getSellValue(), syncMsgData, msgMap);
				}
			}
			// 兑换物品
			ThingUtil.groupThing(instPlayer, instHJYStore.getTableTypeId(), instHJYStore.getTableFieldId(), instHJYStore.getValue(), syncMsgData, msgMap);
			instHJYStore.setSellOut(1); // 0-未售完 1-售完
			getInstHJYStoreDAL().update(instHJYStore, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instHJYStore, instHJYStore.getId(), instHJYStore.getResult(), syncMsgData);

			// 更新玩家成就计数实例数据
			AchievementUtil.updateAchievementValue(instPlayerId, StaticAchievementType.hJYStore, 1, syncMsgData);
		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 拍卖场-白金贵宾
	 * 
	 * @author hzw
	 * @date 2014-9-29上午11:30:07
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void platinumVIP(Map<String, Object> msgMap, String channelId) throws Exception {

		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instActivityId = (Integer) msgMap.get("instActivityId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		// if(instPlayer.getVipLevel() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.auctionShopVip)){
		// MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noUpVip);
		// return;
		// }
		DictVIP dictVIP = DictMap.dictVIPMap.get((instPlayer.getVipLevel() + 1) + "");
		if (dictVIP != null) {
			if (dictVIP.getIsUpSilverVip() == 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noUpVip);
				return;
			}
		}
		if (instPlayer.getLevel() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.auctionShopLevel)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		if (instPlayer.getGold() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.auctionShopGold)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		// 消耗元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, DictMapUtil.getSysConfigIntValue(StaticSysConfig.auctionShopGold), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		if (instActivityId != 0) {
			InstActivity instActivity = getInstActivityDAL().getModel(instActivityId, instPlayerId);
			if (instActivity.getInstPlayerId() != instPlayerId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if (instActivity.getIsForever() == 0 && instActivity.getActivityId() == StaticActivity.auctionShop) {
				// 更新活动为永久
				instActivity.setIsForever(1);
				getInstActivityDAL().update(instActivity, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
			}
		} else if (instActivityId == 0) {
			ActivityUtil.addInstActivity(instPlayerId, StaticActivity.auctionShop, 1, syncMsgData);
		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 在线奖励领奖
	 * 
	 * @author hzw
	 * @date 2014-10-14下午3:21:02
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void onlineRewards(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		int instActivityOnlineRewardsId = (Integer) msgMap.get("instActivityOnlineRewardsId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstActivityOnlineRewards instActivityOnlineRewards = getInstActivityOnlineRewardsDAL().getModel(instActivityOnlineRewardsId, instPlayerId);
		if (instActivityOnlineRewards.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		InstUser instUser = getInstUserDAL().getList("openId='" + instPlayer.getOpenId() + "'", 0).get(0);
		if (instActivityOnlineRewards.getOnlineTime() != 0 && (DateUtil.getMillSecond(DateUtil.getCurrTime()) - DateUtil.getMillSecond(instUser.getLastLoginTime())) >= instActivityOnlineRewards.getOnlineTime()) {
			String things = instActivityOnlineRewards.getThings();
			String thing[] = things.split(";");
			for (String str : thing) {
				ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
			}
			ActivityUtil.updateInstActivityOnlineRewards(instActivityOnlineRewards, syncMsgData);
		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 初始化开服礼包物品
	 * 
	 * @author hzw
	 * @date 2014-10-15下午4:35:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void openServiceBagView(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		MessageData testMsgData = new MessageData();
		List<DictActivityOpenServiceBag> openServiceBagList = DictList.dictActivityOpenServiceBagList;
		for (DictActivityOpenServiceBag obj : openServiceBagList) {
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", obj.getId());// 登录天数
			msgData.putStringItem("3", obj.getThings());// 物品列表
			testMsgData.putMessageItem(obj.getId() + "", msgData.getMsgData());
		}
		retMsgData.putMessageItem("DictActivityContinuousLogin", testMsgData.getMsgData());

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 开服礼包领取
	 * 
	 * @author hzw
	 * @date 2014-10-15下午4:58:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void openServiceBag(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		int instActivityOpenServiceBagId = (int) msgMap.get("instActivityOpenServiceBagId");// 开服礼包实例Id
		int dictActivityOpenServiceBagId = (int) msgMap.get("dictActivityOpenServiceBagId");// 开服礼包字典Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstActivityOpenServiceBag instActivityOpenServiceBag = getInstActivityOpenServiceBagDAL().getModel(instActivityOpenServiceBagId, instPlayerId);

		if (instActivityOpenServiceBag.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if (StringUtil.contains(instActivityOpenServiceBag.getEnd(), dictActivityOpenServiceBagId + "", ";")) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_openServiceBag);
			return;
		}
		if (!StringUtil.contains(instActivityOpenServiceBag.getCan(), dictActivityOpenServiceBagId + "", ";")) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notOpenServiceBag);
			return;
		}
		DictActivityOpenServiceBag dictActivityOpenServiceBag = DictMap.dictActivityOpenServiceBagMap.get(dictActivityOpenServiceBagId + "");
		String things = dictActivityOpenServiceBag.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}
		String can[] = instActivityOpenServiceBag.getCan().split(";");
		String canString = "";
		for (String str : can) {
			if (!str.equals(dictActivityOpenServiceBagId + "")) {
				canString += str + ";";
			}
		}
		if (!canString.equals("")) {
			instActivityOpenServiceBag.setCan(canString.substring(0, canString.length() - 1));
		} else {
			instActivityOpenServiceBag.setCan(canString);
		}
		if (!instActivityOpenServiceBag.getEnd().equals("")) {
			instActivityOpenServiceBag.setEnd(instActivityOpenServiceBag.getEnd() + ";" + dictActivityOpenServiceBagId);
		} else {
			instActivityOpenServiceBag.setEnd(dictActivityOpenServiceBagId + "");
		}
		getInstActivityOpenServiceBagDAL().update(instActivityOpenServiceBag, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivityOpenServiceBag, instActivityOpenServiceBag.getId(), instActivityOpenServiceBag.getResult(), syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 领取等级礼包
	 * 
	 * @author hzw
	 * @date 2014-10-18下午3:50:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void levelBag(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		int dictActivityLevelBagId = (int) msgMap.get("dictActivityLevelBagId");// 等级礼包字典Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		// 处理引导逻辑
		String step = (String) msgMap.get("step");// 等级用'_'区分; 关卡用'b'区分
		if (step != null && !step.equals("")) {
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}

		if (instPlayer.getLevel() < dictActivityLevelBagId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPlayerLevel);
			return;
		}

		List<InstActivityLevelBag> InstActivityLevelBagList = getInstActivityLevelBagDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if (InstActivityLevelBagList != null && InstActivityLevelBagList.size() > 0) {
			InstActivityLevelBag instActivityLevelBag = InstActivityLevelBagList.get(0);
			if (StringUtil.contains(instActivityLevelBag.getEnd(), dictActivityLevelBagId + "", ";")) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_openServiceBag);
				return;
			}

			// 更新等级礼包实例数据
			if (!instActivityLevelBag.getEnd().equals("")) {
				instActivityLevelBag.setEnd(instActivityLevelBag.getEnd() + ";" + dictActivityLevelBagId);
			} else {
				instActivityLevelBag.setEnd(instActivityLevelBag + "");
			}
			getInstActivityLevelBagDAL().update(instActivityLevelBag, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivityLevelBag, instActivityLevelBag.getId(), instActivityLevelBag.getResult(), syncMsgData);

		} else {
			InstActivityLevelBag instActivityLevelBag = new InstActivityLevelBag();
			instActivityLevelBag.setInstPlayerId(instPlayerId);
			instActivityLevelBag.setEnd(dictActivityLevelBagId + "");
			getInstActivityLevelBagDAL().add(instActivityLevelBag, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivityLevelBag, instActivityLevelBag.getId(), instActivityLevelBag.getResult(), syncMsgData);
		}

		// 添加物品
		DictActivityLevelBag dictActivityLevelBag = DictMap.dictActivityLevelBagMap.get(dictActivityLevelBagId + "");
		String things = dictActivityLevelBag.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 签到
	 * 
	 * @author hzw
	 * @date 2014-10-20下午2:39:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void signIn(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		int dictActivitySignInId = (int) msgMap.get("dictActivitySignInId");// 签到字典Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		DictActivitySignIn dictActivitySignIn = DictMap.dictActivitySignInMap.get(dictActivitySignInId + "");
		int type = dictActivitySignIn.getType();
		List<InstActivitySignIn> InstActivitySignInList = getInstActivitySignInDAL().getList(" instPlayerId = " + instPlayerId + " and type = " + type, instPlayerId);
		if (InstActivitySignInList != null && InstActivitySignInList.size() > 0) {
			InstActivitySignIn instActivitySignIn = InstActivitySignInList.get(0);
			if (DateUtil.isSameDay(DateUtil.getCurrTime(), instActivitySignIn.getUpdateTime(), DateFormat.YMDHMS)) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notOpenServiceBag);
				return;
			}
			int day = instActivitySignIn.getDay();
			if (type == 1) {
				if (DateUtil.getTimeInfo(instActivitySignIn.getUpdateTime(), DateType.Month) != DateUtil.getTimeInfo(DateType.Month)) {
					day = 0;
				}
				if (day < ((List<InstActivitySignIn>) DictMapList.dictActivitySignInMap.get(DateUtil.getTimeInfo(DateType.Month))).size()) {
					++day;
				}
			} else if (type == 2) {
				if (day < ((List<InstActivitySignIn>) DictMapList.dictActivitySignInMap.get(0)).size()) { // 0为豪华奖励的月份
					++day;
				} else {
					day = 1; // 周期循环为0
				}
			}
			if (dictActivitySignIn.getDay() != day) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notOpenServiceBag);
				return;
			}
			if (dictActivitySignIn.getType() == 2) {
				List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
				int dayrmb = 0;
				for (InstPlayerRecharge instPlayerRecharge : instPlayerRechargeList) {
					if (DateUtil.isSameDay(DateUtil.getCurrTime(), instPlayerRecharge.getOperateTime(), DateFormat.YMDHMS)) {
						dayrmb += instPlayerRecharge.getThisrmb();
					}
				}
				if (dayrmb < DictMapUtil.getSysConfigIntValue(StaticSysConfig.signInCondition)) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notOpenServiceBag);
					return;
				}
			}
			// 添加物品
			String things = dictActivitySignIn.getThings();
			for (String thing : things.split(";")) {
				int value = ConvertUtil.toInt(thing.split("_")[2]);
				if (dictActivitySignIn.getVip() > 0 && instPlayer.getVipLevel() >= dictActivitySignIn.getVip()) {
					value = value * 2;
				} else {
					instActivitySignIn.setIsTwo(1);
				}
				ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(thing.split("_")[0]), ConvertUtil.toInt(thing.split("_")[1]), value, syncMsgData, msgMap);
			}

			// 更新签到实例数据
			instActivitySignIn.setDay(day);
			instActivitySignIn.setUpdateTime(DateUtil.getCurrTime());
			getInstActivitySignInDAL().update(instActivitySignIn, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivitySignIn, instActivitySignIn.getId(), instActivitySignIn.getResult(), syncMsgData);

		} else {
			if (dictActivitySignIn.getDay() != 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notOpenServiceBag);
				return;
			}
			if (dictActivitySignIn.getType() == 2) {
				List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
				int dayrmb = 0;
				for (InstPlayerRecharge instPlayerRecharge : instPlayerRechargeList) {
					if (DateUtil.isSameDay(DateUtil.getCurrTime(), instPlayerRecharge.getOperateTime(), DateFormat.YMDHMS)) {
						dayrmb += instPlayerRecharge.getThisrmb();
					}
				}
				if (dayrmb < DictMapUtil.getSysConfigIntValue(StaticSysConfig.signInCondition)) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notOpenServiceBag);
					return;
				}
			}
			// 添加物品

			String things = dictActivitySignIn.getThings();
			int isTwo = 0;

			for (String thing : things.split(";")) {
				int value = ConvertUtil.toInt(thing.split("_")[2]);
				if (dictActivitySignIn.getVip() > 0 && instPlayer.getVipLevel() >= dictActivitySignIn.getVip()) {
					value = value * 2;
				} else {
					isTwo = 1;
				}
				ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(thing.split("_")[0]), ConvertUtil.toInt(thing.split("_")[1]), value, syncMsgData, msgMap);
			}

			InstActivitySignIn instActivitySignIn = new InstActivitySignIn();
			instActivitySignIn.setIsTwo(isTwo);

			/*
			 * String things = dictActivitySignIn.getThings(); int value = ConvertUtil.toInt(things.split("_")[2]);
			 * 
			 * InstActivitySignIn instActivitySignIn = new InstActivitySignIn(); instActivitySignIn.setIsTwo(0);
			 * 
			 * if(dictActivitySignIn.getVip() > 0 && instPlayer.getVipLevel() >= dictActivitySignIn.getVip()){ value = value * 2; }else{ instActivitySignIn.setIsTwo(1); } ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(things.split("_")[0]), ConvertUtil.toInt(things.split("_")[1]), value, syncMsgData, msgMap);
			 */

			instActivitySignIn.setInstPlayerId(instPlayerId);
			instActivitySignIn.setType(type);
			instActivitySignIn.setDay(1);
			instActivitySignIn.setUpdateTime(DateUtil.getCurrTime());
			getInstActivitySignInDAL().add(instActivitySignIn, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivitySignIn, instActivitySignIn.getId(), instActivitySignIn.getResult(), syncMsgData);
		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 签到-领取双倍
	 * 
	 * @author hzw
	 * @date 2014-10-23上午11:23:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void twoSignIn(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		List<InstActivitySignIn> instActivitySignInList = getInstActivitySignInDAL().getList(" instPlayerId = " + instPlayerId + " and type = 2", instPlayerId);
		if (instActivitySignInList == null || instActivitySignInList.isEmpty()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notOpenServiceBag);
			return;
		}
		InstActivitySignIn instActivitySignIn = instActivitySignInList.get(0);
		if (instActivitySignIn.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int dictId = (int) msgMap.get("instActivitySignInId");
		DictActivitySignIn dictActivitySignIn = DictMap.dictActivitySignInMap.get(dictId + "");
		if (instActivitySignIn.getIsTwo() == 0 || dictActivitySignIn.getVip() == 0 || instPlayer.getVipLevel() < dictActivitySignIn.getVip()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_openServiceBag);
			return;
		}

		MessageData syncMsgData = new MessageData();
		String things = dictActivitySignIn.getThings();
		for (String thing : things.split(";")) {
			int value = ConvertUtil.toInt(thing.split("_")[2]);
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(thing.split("_")[0]), ConvertUtil.toInt(thing.split("_")[1]), value, syncMsgData, msgMap);
		}

		// String things = dictActivitySignIn.getThings();
		// int value = ConvertUtil.toInt(things.split("_")[2]);
		// ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(things.split("_")[0]), ConvertUtil.toInt(things.split("_")[1]), value, syncMsgData, msgMap);

		instActivitySignIn.setIsTwo(0);
		instActivitySignIn.setUpdateTime(DateUtil.getCurrTime());
		getInstActivitySignInDAL().update(instActivitySignIn, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivitySignIn, instActivitySignIn.getId(), instActivitySignIn.getResult(), syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 领奖中心领奖
	 * 
	 * @author hzw
	 * @date 2014-10-22上午11:29:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void award(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		int instPlayerAwardId = (int) msgMap.get("instPlayerAwardId");// 签到字典Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		InstPlayerAward instPlayerAward = getInstPlayerAwardDAL().getModel(instPlayerAwardId, instPlayerId);

		if (instPlayerAward.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		// 添加物品
		String things = instPlayerAward.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 当是全服发奖时,要记录所发奖励信息,以防止玩家重新领取[因为程序为领完后删除,所以无法判断是否已经领取全服奖励]
		if (instPlayerAward.getName() == 3) {
			GmUtil.addGmReward(instPlayerId, instPlayerAward.getOperTime());
		}

		getInstPlayerAwardDAL().deleteById(instPlayerAward.getId(), instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerAward, instPlayerAward.getId(), "", syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 领奖中心-全部领取
	 * 
	 * @author hzw
	 * @date 2014-10-22上午11:35:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aKeyAward(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		List<InstPlayerAward> instPlayerAwardList = getInstPlayerAwardDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);

		if (instPlayerAwardList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notOpenServiceBag);
			return;
		}

		Map<String, String> thingMap = new HashMap<String, String>(); // 返还的物品Map
		for (InstPlayerAward obj : instPlayerAwardList) {
			String things = obj.getThings();
			String thing[] = things.split(";");
			for (String str : thing) {
				int tableTypeId = ConvertUtil.toInt(str.split("_")[0]);
				int tableFieldId = ConvertUtil.toInt(str.split("_")[1]);
				int value = ConvertUtil.toInt(str.split("_")[2]);
				ThingUtil.groupThingMap(thingMap, tableTypeId, tableFieldId, value);
			}

			// 当是全服发奖时,要记录所发奖励信息,以防止玩家重新领取[因为程序为领完后删除,所以无法判断是否已经领取全服奖励]
			if (obj.getName() == 3) {
				GmUtil.addGmReward(instPlayerId, obj.getOperTime());
			}

			getInstPlayerAwardDAL().deleteById(obj.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
		}
		// 添加物品
		ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 兑换码领奖
	 * 
	 * @author hzw
	 * @date 2015-4-9上午11:04:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void cDKeyAward(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		int instRecordId = 0;

		try {
			String cdk = (String) msgMap.get("cdk");
			cdk = cdk.toLowerCase();

			// 获取渠道号
			Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
			String channel = player.getChannel();
			channel = channel.toLowerCase();
			String serverId = player.getZoneid();

			// channel = "huawei";
			// serverId = "1";

			// openId, cdk, channel, serverId
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("openId", player.getOpenId());
			paramsMap.put("cdk", cdk);
			paramsMap.put("channel", channel);
			paramsMap.put("serverId", serverId);
			String params = HttpClientUtil.httpBuildQuery(paramsMap);
			String cdkResult = HttpClientUtil.postMapSubmit(SysConfigUtil.getValue("cdk.verify.url"), params);
			JSONObject cdkJson = JSONObject.fromObject(cdkResult);

			if (cdkResult == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_webNotGood);
				return;
			} else {
				String code = String.valueOf(cdkJson.get("code"));
				if (!code.equals("0")) {
					MessageUtil.sendFailMsg(channelId, msgMap, String.valueOf(cdkJson.get("message")));
					return;
				}
			}

			// 兑换得到的物品发放到领奖中心
			instRecordId = Integer.valueOf(String.valueOf(cdkJson.get("instCdkRecordId")));
			String things = String.valueOf(cdkJson.get("things"));
			String description = "恭喜您获得" + String.valueOf(cdkJson.get("cdkTypeName")) + "：";
			ActivityUtil.addInstPlayerAward(instPlayerId, 5, things, DateUtil.getCurrTime(), description, syncMsgData);
		} catch (Exception e) {
			if (instRecordId != 0) {
				Map<String, String> paramsMap = new HashMap<String, String>();
				paramsMap.put("instRecordId", instRecordId + "");
				String params = HttpClientUtil.httpBuildQuery(paramsMap);
				HttpClientUtil.postMapSubmit(SysConfigUtil.getValue("cdk.del.url"), params);
			}
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_webNotGood);
			return;
		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 限时抢购购买
	 * 
	 * @author hzw
	 * @date 2015-5-5下午2:00:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void flashSale(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//验证时间-不满足提示活动已过期
		Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
		if (player != null) {
			String openId = player.getOpenId();
			List<InstUser> userList = getInstUserDAL().getList(" openId = '" + openId + "'", 0);
			if (userList.size() > 0) {
				String firstLoginTime = userList.get(0).getFirstLoginTime();
				if (DateUtil.getCurrMill() - DateUtil.getMillSecond(firstLoginTime) > (72 * 60 * 60 * 1000)) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_activityOutDate);
					return;
				}
			}
		}
		
		MessageData syncMsgData = new MessageData();
		int activityFlashSaleId = (int) msgMap.get("activityFlashSaleId");// 限时抢购字典Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		List<InstActivity> activitieList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.flashSale, instPlayerId);
		InstActivity instActivity = activitieList.get(0);

		if (instActivity.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if (!instActivity.getActivityTime().equals("")) {
			if (StringUtil.contains(instActivity.getActivityTime(), activityFlashSaleId + "", ";")) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyRepeat);
				return;
			}
		}
		DictActivityFlashSale dictActivityFlashSale = DictMap.dictActivityFlashSaleMap.get(activityFlashSaleId + "");
		// 验证元宝是否足够
		if (instPlayer.getGold() < dictActivityFlashSale.getBuyGold()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}

		// 添加物品
		String things = dictActivityFlashSale.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 修改购买记录(最后不含分号)
		if (instActivity.getActivityTime().equals("")) {
			instActivity.setActivityTime(activityFlashSaleId + "");
		} else {
			instActivity.setActivityTime(instActivity.getActivityTime() + ";" + activityFlashSaleId);
		}
		getInstActivityDAL().update(instActivity, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);

		// 扣除元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, dictActivityFlashSale.getBuyGold(), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 购买开服基金
	 * 
	 * @author mp
	 * @date 2015-5-4 下午4:40:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buyFund(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		// 验证是否已经购买
		int haveBuy = getInstActivityDAL().getCount("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.fund);
		if (haveBuy > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyRepeat);
			return;
		}

		// 验证vip是否达到
		if (instPlayer.getVipLevel() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.fundVipLevel)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_vipLevel);
			return;
		}

		// 验证元宝是否足够
		if (instPlayer.getGold() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.fundBaseGold)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}

		// 扣除元宝
		MessageData syncMsgData = new MessageData();
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, DictMapUtil.getSysConfigIntValue(StaticSysConfig.fundBaseGold), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 加入活动实例表,并同步给客户端
		ActivityUtil.addInstActivity(instPlayerId, StaticActivity.fund, syncMsgData);

		// 记录购买开服基金的总数
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '" + StaticBigTable.sumBuyFundNum + "'", 0);
		if (instPlayerBigTableList.size() > 0) {
			InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
			instPlayerBigTable.setValue1((Integer.valueOf(instPlayerBigTable.getValue1()) + 1) + "");
			getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
			ParamConfig.sumBuyFundNum = Integer.valueOf(instPlayerBigTable.getValue1());
		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 领取开服基金
	 * 
	 * @author mp
	 * @date 2015-5-4 下午4:40:56
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getFund(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int fundId = (int) msgMap.get("fundId");// 要领取的开服基金字典Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		// 验证是否购买基金
		List<InstActivity> activitieList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.fund, instPlayerId);
		if (activitieList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noFund);
			return;
		}

		// 是否达到领取等级
		DictActivityFund dictActivityFund = DictMap.dictActivityFundMap.get(fundId + "");
		if (instPlayer.getLevel() < dictActivityFund.getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}

		// 验证以前是否领过
		InstActivity instActivity = activitieList.get(0);
		if (!instActivity.getActivityTime().equals("")) {
			if (StringUtil.firstLastAddChar(instActivity.getActivityTime(), ";").contains(StringUtil.firstLastAddChar(fundId + "", ";"))) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notGetStarType);
				return;
			}
		}

		// 领取元宝
		MessageData syncMsgData = new MessageData();
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.add, dictActivityFund.getGoldNum(), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 修改领取记录(最后不含分号)
		if (instActivity.getActivityTime().equals("")) {
			instActivity.setActivityTime(fundId + "");
		} else {
			instActivity.setActivityTime(instActivity.getActivityTime() + ";" + fundId);
		}
		getInstActivityDAL().update(instActivity, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 领取累计充值物品
	 * 
	 * @author mp
	 * @date 2015-5-5 下午2:03:26
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getAddRecargeThings(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int addRechargeId = (int) msgMap.get("addRechargeId");// 要领取的累计充值字典Id
		DictActivityAddRecharge dictActivityAddRecharge = DictMap.dictActivityAddRechargeMap.get(addRechargeId + "");
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.addRecharge + "");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		if (addRechargeId < 100) { // magic id
			int dayOfWeek = DateUtil.getTimeInfo(DateType.DayOfWeek);
			int idMin = dayOfWeek * 10 + 1; // [
			int idMax = dayOfWeek * 10 + 9; // ]
			if (addRechargeId < idMin || idMax < addRechargeId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_addRechargeId_error);
				return;
			}
			int dayRmb = 0;
			List<InstPlayerRecharge> instPlayerRechargeList = getInstPlayerRechargeDAL().getList("instPlayerId = " + instPlayerId, 0);
			for (InstPlayerRecharge instPlayerRecharge : instPlayerRechargeList) {
				if (DateUtil.isSameDay(DateUtil.getCurrTime(), instPlayerRecharge.getOperateTime(), DateFormat.YMDHMS)) {
					dayRmb += instPlayerRecharge.getThisrmb();
				}
			}
			if (dayRmb < dictActivityAddRecharge.getProgress()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_recharge_not_enough);
				return;
			}
			// 是否领取过
			String sqlCondition = "instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.addRecharge;
			if (getInstActivityDAL().getCount(sqlCondition) == 0) {
				ActivityUtil.addInstActivity(instPlayerId, StaticActivity.addRecharge, new MessageData());
			}
			List<InstActivity> activityList = getInstActivityDAL().getList(sqlCondition, instPlayerId);
			InstActivity instActivity = activityList.get(0);
			String spareOne = instActivity.getSpareOne();
			String spareTwo = instActivity.getSpareTwo();
			if (spareOne == null) {
				spareOne = "";
			}
			if (spareTwo == null) {
				spareTwo = "";
			}
			if (!spareOne.isEmpty() && !spareTwo.isEmpty() && DateUtil.isSameDay(spareOne, DateUtil.getYmdDate(), DateFormat.YMD)) {
				String[] arrTwo = spareTwo.split(";");
				String strRechargeId = Integer.toString(addRechargeId);
				for (String str : arrTwo) {
					if (strRechargeId.equals(str)) {
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_award_gotten);
						return;
					}
				}
			}
			// 领取物品
			MessageData syncMsgData = new MessageData();
			for (String thing : dictActivityAddRecharge.getThings().split(";")) {
				int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
				int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
				int value = ConvertUtil.toInt(thing.split("_")[2]);
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
			}
			// 记录本次领取
			if (spareOne.isEmpty() || !DateUtil.isSameDay(spareOne, DateUtil.getYmdDate(), DateFormat.YMD)) {
				instActivity.setSpareOne(DateUtil.getYmdDate());
				instActivity.setSpareTwo(addRechargeId + "");
			} else {
				instActivity.setSpareTwo(spareTwo + ";" + addRechargeId);
			}
			getInstActivityDAL().update(instActivity, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);

			MessageUtil.sendSyncMsg(channelId, syncMsgData);
			MessageUtil.sendSuccMsg(channelId, msgMap);
		} else {
			// 验证当前时间是否在活动期内
			if (DateUtil.getCurrMill() < DateUtil.getMillSecond(dictActivity.getStartTime()) || DateUtil.getCurrMill() > DateUtil.getMillSecond(dictActivity.getEndTime())) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noActivityDate);
				return;
			}

			// 验证是否达到本次充值金额
			int sumrmb = getInstPlayerRechargeDAL().getStatResult("sum", "thisrmb", "instPlayerId = " + instPlayerId + " and operateTime between '" + dictActivity.getStartTime() + "' and '" + dictActivity.getEndTime() + "'");
			if (sumrmb < dictActivityAddRecharge.getProgress()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_recharge);
				return;
			}

			// 验证数据
			List<InstActivity> activitieList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.addRecharge, instPlayerId);
			if (activitieList.size() <= 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_dataError);
				return;
			}

			// 验证本次奖品是否已领过
			InstActivity instActivity = activitieList.get(0);
			if (!instActivity.getActivityTime().equals("")) {
				if (StringUtil.firstLastAddChar(instActivity.getActivityTime(), ";").contains(StringUtil.firstLastAddChar(addRechargeId + "", ";"))) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notGetStarType);
					return;
				}
			}

			// 领取物品
			MessageData syncMsgData = new MessageData();
			for (String thing : dictActivityAddRecharge.getThings().split(";")) {
				int tableTypeId = ConvertUtil.toInt(thing.split("_")[0]);
				int tableFieldId = ConvertUtil.toInt(thing.split("_")[1]);
				int value = ConvertUtil.toInt(thing.split("_")[2]);
				ThingUtil.groupThing(instPlayer, tableTypeId, tableFieldId, value, syncMsgData, msgMap);
			}

			// 记录本次领取
			if (instActivity.getActivityTime().equals("")) {
				instActivity.setActivityTime(addRechargeId + "");
			} else {
				instActivity.setActivityTime(instActivity.getActivityTime() + ";" + addRechargeId);
			}
			getInstActivityDAL().update(instActivity, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);

			MessageUtil.sendSyncMsg(channelId, syncMsgData);
			MessageUtil.sendSuccMsg(channelId, msgMap);
		}
	}

	/**
	 * 月卡每日领取
	 * 
	 * @author hzw
	 * @date 2015-5-7上午10:59:33
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getMonthCard(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instActivityId = (int) msgMap.get("instActivityId");// 活动实例Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstActivity instActivity = getInstActivityDAL().getModel(instActivityId, instPlayerId);

		//验证是否存在
		if (instActivity == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		//验证玩家是否一致
		if (instActivity.getInstPlayerId() != instPlayerId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证是否为月卡
		if (instActivity.getActivityId() != StaticActivity.monthCard) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		// 验证当前时间是否在月卡领取范围内
		String activityTime = instActivity.getActivityTime();
		if (activityTime.equals("") || DateUtil.dayCompare(activityTime, DateUtil.getCurrTime()) < 0 || (instActivity.getSpareOne() != null && !instActivity.getSpareOne().equals("") && DateUtil.dayCompare(instActivity.getSpareOne(), DateUtil.getCurrTime()) == 0)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notWelfareBox);
			return;
		}

		// 领取元宝
		DictRecharge recharge = null;
		if (instActivity.getUseNum() == 1) {
			recharge = DictMap.dictRechargeMap.get("1");
		} else if (instActivity.getUseNum() == 2) {
			recharge = DictMap.dictRechargeMap.get("9");
		}
		MessageData syncMsgData = new MessageData();
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.add, recharge.getNoFirstAmt(), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 更新领取时间
		instActivity.setSpareOne(DateUtil.getCurrTime());
		getInstActivityDAL().update(instActivity, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 签到初始化数据
	 * 
	 * @author hzw
	 * @date 2015-5-11下午2:41:21
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void initSignIn(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		MessageData testMsgData = new MessageData();
		MessageData test2MsgData = new MessageData();
		List<DictActivitySignIn> activitySignInList = (List<DictActivitySignIn>) DictMapList.dictActivitySignInMap.get(DateUtil.getTimeInfo(DateType.Month));
		for (DictActivitySignIn obj : activitySignInList) {
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", obj.getId());
			msgData.putIntItem("3", obj.getDay());// 登录天数
			msgData.putStringItem("5", obj.getThings());// 物品列表
			msgData.putIntItem("6", obj.getVip());// vip限制
			testMsgData.putMessageItem(obj.getId() + "", msgData.getMsgData());
		}

		int whichDay;
		final int daysOfCycle = 30; // 30天一个周期
		activitySignInList = (List<DictActivitySignIn>) DictMapList.dictActivitySignInMap.get(0); // 0为豪华奖励的月份
		List<InstActivitySignIn> instActivitySignInList = getInstActivitySignInDAL().getList(" instPlayerId = " + instPlayerId + " and type = 2", instPlayerId);
		if (instActivitySignInList != null && instActivitySignInList.size() > 0) {
			whichDay = instActivitySignInList.get(0).getDay();
			if (whichDay % daysOfCycle == 0 && !DateUtil.isSameDay(instActivitySignInList.get(0).getUpdateTime(), DateUtil.getCurrTime(), DateFormat.YMDHMS)) {
				if (++whichDay > activitySignInList.size()) { // 大循环
					whichDay = 1;
				}
			}
		} else {
			whichDay = 1;
		}
		int whichCycle = (whichDay - 1) / daysOfCycle;
		int dayMin = daysOfCycle * whichCycle + 1;
		int dayMax = daysOfCycle * whichCycle + daysOfCycle;
		for (DictActivitySignIn obj : activitySignInList) {
			int day = obj.getDay();
			if (dayMin <= day && day <= dayMax) {
				MessageData msgData = new MessageData();
				msgData.putIntItem("1", obj.getId());
				msgData.putIntItem("3", day);// 登录天数
				msgData.putStringItem("5", obj.getThings());// 物品列表
				msgData.putIntItem("6", obj.getVip());// vip限制
				test2MsgData.putMessageItem(obj.getId() + "", msgData.getMsgData());
			}
		}
		retMsgData.putMessageItem("DictActivitySignIn1", testMsgData.getMsgData());
		retMsgData.putMessageItem("DictActivitySignIn2", test2MsgData.getMsgData());

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 排行榜
	 * 
	 * @author mp
	 * @date 2015-5-18 下午4:50:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void ranking(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int type = (int) msgMap.get("type");// 1-等级榜 2-竞技榜
		int index = (int) msgMap.get("pageNum");// 页码-不大于5（每页显示10条数据,最大5页,50条）
		int size = DictMapUtil.getSysConfigIntValue(StaticSysConfig.onePageNum);
		StringBuilder result = new StringBuilder();

		// 判断是否超过规定的最大页数
		if (index > DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxPage)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_maxPage);
			return;
		}

		// 判断页码必须大于0
		if (index <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}

		int id = (index - 1) * DictMapUtil.getSysConfigIntValue(StaticSysConfig.onePageNum) + 1;
		index = (index - 1) * size;
		MessageData retMsgData = new MessageData();// 返回结果格式：序号 玩家id 头像id(卡牌字典表id) 玩家名 等级/...
		if (type == 1) {
			List<Map<String, Object>> retList = getInstPlayerArenaDAL().sqlHelper("select id, headerCardId, name, level, openId from Inst_Player where 1=1 order by level desc , exp desc , id asc limit " + index + ", " + size);
			for (Map<String, Object> retMap : retList) {
				int playerId = (int) retMap.get("id");
				List<InstUnionMember> unionList = getInstUnionMemberDAL().getList("instPlayerId = " + playerId, 0);
				String unionName = "";//玩家所属联盟名字
				if(unionList!=null&&unionList.size()>0){
					List<InstUnion>  list = getInstUnionDAL().getList("id = "+unionList.get(0).getInstUnionId(), 0);
					if(list!=null&&list.size()>0){
						unionName = list.get(0).getName();
					}
				}
				result.append(id + " " + retMap.get("id") + " " + retMap.get("headerCardId") + " " + retMap.get("name") + " " + retMap.get("level") + " " + (ParamConfig.openIds.contains(retMap.get("openId")) ? "1" : "0")+ " "+unionName).append("/");
				id++;
			}
		} else if (type == 2) {
			List<Map<String, Object>> retList = getInstPlayerArenaDAL().sqlHelper("select a.instPlayerId, a.rank as rank from Inst_Player_Arena a order by a.rank limit " + index + ", " + size);
			for (Map<String, Object> retMap : retList) {
				int instPlayerId = (int) retMap.get("instPlayerId");
				int headerId = 0;
				String name = "";
				String openId = "";
				int level = 0;
				if (instPlayerId > 1000000) {
					int[] headers = { 88, 89, 101, 104, 111, 135 };
					int random = RandomUtil.getRangeInt(0, headers.length - 1);
					headerId = headers[random];
					InstPlayerArenaNPC instPlayerArenaNPC = getInstPlayerArenaNPCDAL().getModel(instPlayerId, 0);
					level = instPlayerArenaNPC.getLevel();
					name = instPlayerArenaNPC.getName();
				} else {
					InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, 0);
					headerId = instPlayer.getHeaderCardId();
					level = instPlayer.getLevel();
					name = instPlayer.getName();
					openId = instPlayer.getOpenId();
				}
				List<InstUnionMember> unionList = getInstUnionMemberDAL().getList("instPlayerId = " + instPlayerId, 0);
				String unionName = "";//玩家所属联盟名字
				if(unionList!=null&&unionList.size()>0){
					List<InstUnion>  list = getInstUnionDAL().getList("id = "+unionList.get(0).getInstUnionId(), 0);
					if(list!=null&&list.size()>0){
						unionName = list.get(0).getName();
					}
				}
				result.append(id + " " + instPlayerId + " " + headerId + " " + name + " " + level + " " + (ParamConfig.openIds.contains(openId) ? "1" : "0")+" "+unionName).append("/");
				id++;
			}
		}

		if (result.toString().length() > 0) {
			retMsgData.putStringItem("1", StringUtil.noContainLastString(result.toString()));
		} else {
			retMsgData.putStringItem("1", "");
		}

		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 下发整点抢物品
	 * 
	 * @author hzw
	 * @date 2014-10-15下午4:35:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabTheHour(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		MessageData testMsgData = new MessageData();
		List<DictActivityGrabTheHour> grabTheHourList = (List<DictActivityGrabTheHour>) DictMapList.dictActivityGrabTheHourMap.get(DateUtil.getTimeInfo(DateType.DayOfWeek));
		for (DictActivityGrabTheHour obj : grabTheHourList) {
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", obj.getId());// 字典Id
			msgData.putStringItem("3", obj.getThings());// 物品列表
			msgData.putIntItem("4", obj.getBuyCount());// 总购买次数
			msgData.putIntItem("5", obj.getBuyType());// 购买类型
			msgData.putIntItem("6", obj.getBuyValue());// 购买价格
			msgData.putIntItem("7", obj.getOriginalValue());// 原价
			int count = getInstPlayerGrabTheHourDAL().getCount(" grabTheHourId = " + obj.getId());
			// int h = DateUtil.getTimeInfo(DateType.HOUR_OF_DAY);
			// if(((h >= 0 && h < DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeOne)) || (h >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeThree) && h <= 24)) && obj.getRank() == 3){
			// count = getInstPlayerGrabTheHourDAL().getCount(" grabTheHourId = " + obj.getId());
			// }else if(h >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeOne) && h < DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeTwo) && obj.getRank() == 1){
			// count = getInstPlayerGrabTheHourDAL().getCount(" grabTheHourId = " + obj.getId());
			// }else if(h >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeTwo) && h < DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeThree) && obj.getRank() == 2){
			// count = getInstPlayerGrabTheHourDAL().getCount(" grabTheHourId = " + obj.getId());
			// }
			msgData.putIntItem("8", obj.getRank());// 排序从小到大对应整点时间
			msgData.putIntItem("9", obj.getBuyCount() - count);// 剩余购买次数
			msgData.putStringItem("10", obj.getDescription());// 礼包名称
			testMsgData.putMessageItem(obj.getId() + "", msgData.getMsgData());
		}
		retMsgData.putMessageItem("DictActivityGrabTheHour", testMsgData.getMsgData());

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 整点抢购买物品
	 * 
	 * @author hzw
	 * @date 2015-6-4下午3:26:00
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabTheHourBuy(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		int grabTheHourId = (int) msgMap.get("grabTheHourId");// 整点抢字典Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		// 如果有活动开始结束时间,验证时间是否已过
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.grabTheHour + "");
		if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
			if (DateUtil.getCurrMill() < DateUtil.getMillSecond(dictActivity.getStartTime()) || DateUtil.getCurrMill() > DateUtil.getMillSecond(dictActivity.getEndTime())) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noActivityDate);
				return;
			}
		}

		// 验证是够已经购买过只可购买一次
		List<InstPlayerGrabTheHour> activitieList = getInstPlayerGrabTheHourDAL().getList("instPlayerId = " + instPlayerId + " and grabTheHourId = " + grabTheHourId, instPlayerId);
		if (activitieList.size() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyRepeat);
			return;
		}

		DictActivityGrabTheHour obj = DictMap.dictActivityGrabTheHourMap.get(grabTheHourId + "");

		// 验证抢购时间是否已到
		int h = DateUtil.getTimeInfo(DateType.HOUR_OF_DAY);

		// 时间应该在当天的 [10 10+2] 之间
		if (obj.getRank() == 1) {
			if (h < DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeOne) || h > (DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeOne) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourSustainTimeOne))) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notUpTime);
				return;
			}
			// 时间应该在当天的 [15 15+2] 之间
		} else if (obj.getRank() == 2) {
			if (h < DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeTwo) || h > (DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeTwo) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourSustainTimeTwo))) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notUpTime);
				return;
			}
			// 时间应该在当天的 [20 20+2] 之间
		} else if (obj.getRank() == 3) {
			if (h < DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeThree) || h > (DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourTimeThree) + DictMapUtil.getSysConfigIntValue(StaticSysConfig.grabTheHourSustainTimeThree))) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notUpTime);
				return;
			}
		}

		int count = getInstPlayerGrabTheHourDAL().getCount(" grabTheHourId = " + grabTheHourId);
		if (obj.getBuyCount() - count <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_sellOut);
			return;
		}

		// 验证元宝/银币是否足够
		if (obj.getBuyType() == 1 && instPlayer.getGold() < obj.getBuyValue()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		if (obj.getBuyType() == 2 && ConvertUtil.toInt(instPlayer.getCopper()) < obj.getBuyValue()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		}

		// 添加物品
		String things = obj.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 扣除元宝/银币
		if (obj.getBuyType() == 1) {
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, obj.getBuyValue(), msgMap);
		} else {
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - obj.getBuyValue()) + "");
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 添加购买记录
		InstPlayerGrabTheHour instPlayerGrabTheHour = new InstPlayerGrabTheHour();
		instPlayerGrabTheHour.setInstPlayerId(instPlayerId);
		instPlayerGrabTheHour.setGrabTheHourId(grabTheHourId);
		getInstPlayerGrabTheHourDAL().add(instPlayerGrabTheHour, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerGrabTheHour, instPlayerGrabTheHour.getId(), instPlayerGrabTheHour.getResult(), syncMsgData);

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 下发特卖会物品
	 * 
	 * @author hzw
	 * @date 2015-6-4下午3:25:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void privateSale(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		MessageData testMsgData = new MessageData();
		int dayOfWeek = DateUtil.getTimeInfo(DateType.DayOfWeek);
		int h = DateUtil.getTimeInfo(DateType.HOUR_OF_DAY);
		if (h >= 0 && h < DictMapUtil.getSysConfigIntValue(StaticSysConfig.privateSaleTimeOne)) {
			dayOfWeek = DateUtil.getTimeInfo(DateUtil.getNumDayDate(DateUtil.getCurrTime(), -1), DateType.DayOfWeek);
		}
		List<DictActivityPrivateSale> grabTheHourList = (List<DictActivityPrivateSale>) DictMapList.dictActivityPrivateSaleMap.get(dayOfWeek);

		int i = 6; // 用于控制显示的物品
		int size = grabTheHourList.size();
		if (h >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.privateSaleTimeOne) && h < DictMapUtil.getSysConfigIntValue(StaticSysConfig.privateSaleTimeTwo)) {
			i = 0;
			size = 6;
		}

		for (; i < size; i++) {
			DictActivityPrivateSale obj = grabTheHourList.get(i);
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", obj.getId());// 字典Id
			msgData.putIntItem("2", obj.getSmallUiId());// UI标示Id
			msgData.putStringItem("4", obj.getThings());// 物品列表
			msgData.putIntItem("5", obj.getBuyCount());// 剩余数量
			msgData.putIntItem("6", obj.getBuyType());// 购买类型
			msgData.putIntItem("7", obj.getBuyValue());// 购买价格
			testMsgData.putMessageItem(obj.getId() + "", msgData.getMsgData());
		}
		retMsgData.putMessageItem("DictActivityPrivateSale", testMsgData.getMsgData());

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 特卖会购买物品
	 * 
	 * @author hzw
	 * @date 2015-6-4下午3:37:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void privateSaleBuy(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		int privateSaleId = (int) msgMap.get("privateSaleId");// 整点抢字典Id
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		DictActivityPrivateSale obj = DictMap.dictActivityPrivateSaleMap.get(privateSaleId + "");
		// 验证是够已经购买过只可购买一次
		List<InstPlayerPrivateSale> activitieList = getInstPlayerPrivateSaleDAL().getList("instPlayerId = " + instPlayerId + " and privateSaleId = " + privateSaleId, instPlayerId);
		if (activitieList.size() > 0 && activitieList.get(0).getCount() >= obj.getBuyCount()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyRepeat);
			return;
		}

		// 验证元宝/银币是否足够
		if (obj.getBuyType() == 1 && instPlayer.getGold() < obj.getBuyValue()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		if (obj.getBuyType() == 2 && ConvertUtil.toInt(instPlayer.getCopper()) < obj.getBuyValue()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		}

		// 添加物品
		String things = obj.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 扣除元宝/银币
		if (obj.getBuyType() == 1) {
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, obj.getBuyValue(), msgMap);
		} else {
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - obj.getBuyValue()) + "");
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 添加购买记录
		InstPlayerPrivateSale instPlayerPrivateSale = null;
		if (activitieList.size() > 0) {
			instPlayerPrivateSale = activitieList.get(0);
			instPlayerPrivateSale.setCount(instPlayerPrivateSale.getCount() + 1);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerPrivateSale, instPlayerPrivateSale.getId(), instPlayerPrivateSale.getResult(), syncMsgData);
		} else {
			instPlayerPrivateSale = new InstPlayerPrivateSale();
			instPlayerPrivateSale.setInstPlayerId(instPlayerId);
			instPlayerPrivateSale.setPrivateSaleId(privateSaleId);
			instPlayerPrivateSale.setCount(1);
			getInstPlayerPrivateSaleDAL().add(instPlayerPrivateSale, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerPrivateSale, instPlayerPrivateSale.getId(), instPlayerPrivateSale.getResult(), syncMsgData);
		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 获取每日特惠列表
	 * 
	 * @author mp
	 * @date 2015-6-3 下午5:02:55
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getDailyDeals(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.dailyDeals, instPlayerId);

		// 组织数据,格式为 id thingsName things oldPrice nowPrice buyState(0-未购买, 1-已购买)/...
		StringBuilder sb = new StringBuilder();
//		String[] days = { DateUtil.getYmdDate(), DateUtil.getNumDayDate(1) };
//		for (String day : days) {
//			int dayNum = DateUtil.getTimeInfo(day, DateType.Day);
//			DictActivityDailyDeals activityDailyDeals = ((List<DictActivityDailyDeals>) DictMapList.dictActivityDailyDealsMap.get(dayNum)).get(0);
//
//			// 今日需判断是否已购买,明日不需要
//			if (day.equals(DateUtil.getYmdDate())) {
//				sb.append(activityDailyDeals.getId()).append(" ").append(activityDailyDeals.getThingsName()).append(" ").append(activityDailyDeals.getThings()).append(" ").append(activityDailyDeals.getOldPrice()).append(" ").append(activityDailyDeals.getNowPrice());
//				if (instActivityList.size() <= 0) {
//					sb.append(" ").append("0");
//				} else {
//					InstActivity instActivity = instActivityList.get(0);
//					if (instActivity.getActivityTime().equals(day)) {
//						sb.append(" ").append("1");
//					} else {
//						sb.append(" ").append("0");
//					}
//				}
//				sb.append("/");
//			} else {
//				sb.append(activityDailyDeals.getId()).append(" ").append(activityDailyDeals.getThingsName()).append(" ").append(activityDailyDeals.getThings()).append(" ").append(activityDailyDeals.getOldPrice()).append(" ").append(activityDailyDeals.getNowPrice()).append(" ").append("0");
//			}
//		}
		
		
		// 组织数据,格式为 id thingsName things price(多个用分号间隔) buyState(0-未购买, 1-已购买,不同商品加个分号间隔)
		String days = DateUtil.getYmdDate();
		int dayNum = DateUtil.getTimeInfo(days, DateType.Day);
		DictActivityDailyDeals activityDailyDeals = ((List<DictActivityDailyDeals>) DictMapList.dictActivityDailyDealsMap.get(dayNum)).get(0);
		// 今日需判断是否已购买,明日不需要
		sb.append(activityDailyDeals.getId()).append(" ").append(activityDailyDeals.getThingsName()).append(" ").append(activityDailyDeals.getThings()).append(" ").append(activityDailyDeals.getPrice());
		if (instActivityList.size() <= 0) {
			sb.append(" ").append("0;0;0;0;0;0");
		} else {
			InstActivity instActivity = instActivityList.get(0);
			if (instActivity.getActivityTime().equals(days)) {
				String state = instActivity.getSpareOne();
				if(state==null||state.length()<=0||state.equals("null")){
					sb.append(" ").append("0;0;0;0;0;0");
				}else{
					sb.append(" ").append(state);
				}
			} else {
				sb.append(" ").append("0;0;0;0;0;0");
			}
		}
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", sb.toString());
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 每日特惠
	 * 
	 * @author mp
	 * @date 2015-6-3 下午4:57:19
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dailyDeals(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int id = (int) msgMap.get("id");// 每日特惠的字典Id
		int index=(int)msgMap.get("index");//购买物品Index
		DictActivityDailyDeals activityDailyDeals = DictMap.dictActivityDailyDealsMap.get(id + "");
		String currYmd = DateUtil.getYmdDate();
		int dayNum = DateUtil.getTimeInfo(currYmd, DateType.Day);
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.dailyDeals, instPlayerId);

		// 验证是否为当天所卖
		if (activityDailyDeals.getDay() != dayNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_todayGoods);
			return;
		}
		String state = "";
		// 验证是否已买
		if (instActivityList.size() > 0) {
			InstActivity instActivity = instActivityList.get(0);
			state = instActivity.getSpareOne();
			if(DateUtil.getTimeInfo(instActivity.getActivityTime(), DateType.Day)!=dayNum){
				state="0;0;0;0;0;0";
			}else if(state!= null&&state.length()>0&&state.split(";")[index].equals("1")){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_todayBuy);
				return;
			}
		}

		// 验证元宝是否充足
		int gold = Integer.valueOf(activityDailyDeals.getPrice().split(";")[index]);
		if (gold > instPlayer.getGold()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}

		// 扣款,给东西
		MessageData syncMsgData = new MessageData();
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		String thing = activityDailyDeals.getThings().split(";")[index];
		ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(thing.split("_")[0]), ConvertUtil.toInt(thing.split("_")[1]), ConvertUtil.toInt(thing.split("_")[2]), syncMsgData, msgMap);
		
		// 做购买记录
		if (instActivityList.size() <= 0) {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<6;i++){
				if(i==index){
					sb.append("1");
				}else{
					sb.append("0");
				}
				if(i!=5){
					sb.append(";");
				}
			}
			ActivityUtil.addInstActivity(instPlayerId, StaticActivity.dailyDeals, currYmd,sb.toString());
		} else {
			InstActivity instActivity = instActivityList.get(0);
			instActivity.setActivityTime(currYmd);
			if(state==null||state.length()<=0||state.equals("null")){
				state="0;0;0;0;0;0";
			}
			String[] temp = state.split(";");
			temp[index]="1";
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<temp.length;i++){
				if(i!=temp.length-1){
					sb.append(temp[i]).append(";");
				}else{
					sb.append(temp[i]);
				}
			}
			instActivity.setSpareOne(sb.toString());
			getInstActivityDAL().update(instActivity, instPlayerId);// 只服务器用,不用同步给客户端
		}

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 获取限时抢购列表
	 * 
	 * @author mp
	 * @date 2015-6-3 下午5:03:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getLimitShopping(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.limitShopping, instPlayerId);

		// 组织数据,格式为 id thing oldPrice nowPrice moneyType(1-金币 2-银币) limitNum(0的时候表示卖完) description(礼包名称)/...
		StringBuilder sb = new StringBuilder();
		List<DictActivityLimitShopping> activityLimitShoppingList = DictList.dictActivityLimitShoppingList;
		for (DictActivityLimitShopping activityLimitShopping : activityLimitShoppingList) {
			sb.append(activityLimitShopping.getId()).append(" ").append(activityLimitShopping.getThing()).append(" ").append(activityLimitShopping.getOldPrice()).append(" ").append(activityLimitShopping.getNewPrice()).append(" ").append(activityLimitShopping.getMoneyType()).append(" ");
			if (instActivityList.size() <= 0) {
				sb.append(activityLimitShopping.getLimitNum());
			} else {
				InstActivity instActivity = instActivityList.get(0);
				int value = StringUtil.getSegValue(instActivity.getSpareOne(), ";" + activityLimitShopping.getId() + "_");
				sb.append(activityLimitShopping.getLimitNum() - value);
			}
			sb.append(" ").append(activityLimitShopping.getDescription()).append("/");

		}

		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", StringUtil.noContainLastString(sb.toString()));
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 限时抢购
	 * 
	 * @author mp
	 * @date 2015-6-3 下午4:57:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void limitShopping(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int id = (int) msgMap.get("id");// 限时抢购的字典Id
		DictActivityLimitShopping activityLimitShopping = DictMap.dictActivityLimitShoppingMap.get(id + "");
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.limitShopping, instPlayerId);
		SysActivity activity = DictMap.sysActivityMap.get(StaticActivity.limitShopping + "");

		// 验证是否在活动期内
		if (DateUtil.getCurrMill() < DateUtil.getMillSecond(activity.getStartTime()) || DateUtil.getCurrMill() > DateUtil.getMillSecond(activity.getEndTime())) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noActivityDate);
			return;
		}

		// 是否超过购买的限制次数
		if (instActivityList.size() > 0) {
			InstActivity instActivity = instActivityList.get(0);
			int limitNum = activityLimitShopping.getLimitNum();
			int value = StringUtil.getSegValue(instActivity.getSpareOne(), ";" + activityLimitShopping.getId() + "_");
			if (value >= limitNum) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_upLimitNum);
				return;
			}
		}

		// 金币,银币是否足够 1-金币 2-银币
		if (activityLimitShopping.getMoneyType() == 1) {
			if (instPlayer.getGold() < activityLimitShopping.getNewPrice()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
				return;
			}
		} else if (activityLimitShopping.getMoneyType() == 2) {
			if (ConvertUtil.toInt(instPlayer.getCopper()) < activityLimitShopping.getNewPrice()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
				return;
			}
		}

		// 扣款,给东西
		MessageData syncMsgData = new MessageData();
		if (activityLimitShopping.getMoneyType() == 1) {
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, activityLimitShopping.getNewPrice(), msgMap);
		} else if (activityLimitShopping.getMoneyType() == 2) {
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - activityLimitShopping.getNewPrice()) + "");
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		String things = activityLimitShopping.getThing();
		for (String thing : things.split(";")) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(thing.split("_")[0]), ConvertUtil.toInt(thing.split("_")[1]), ConvertUtil.toInt(thing.split("_")[2]), syncMsgData, msgMap);
		}

		// 做购买记录
		if (instActivityList.size() <= 0) {
			ActivityUtil.addInstActivityLimitShop(instPlayerId, StaticActivity.limitShopping, ";" + id + "_" + 1 + ";");// 格式为 ;1_2;2_10;
		} else {
			InstActivity instActivity = instActivityList.get(0);
			instActivity.setSpareOne(StringUtil.segValueAddOne(instActivity.getSpareOne(), ";" + id + "_"));
			getInstActivityDAL().update(instActivity, instPlayerId);// 只服务器用,不用同步给客户端
		}

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 星星兑换
	 * 
	 * @author hzw
	 * @date 2015-6-11上午9:54:43
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void starStoreBuy(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int starStoreId = (int) msgMap.get("starStoreId");// 星星兑换的字典Id
		DictActivityStarStore activityStarStore = DictMap.dictActivityStarStoreMap.get(starStoreId + "");

		List<InstPlayerChapter> instPlayerChapterList = getInstPlayerChapterDAL().getList(" instPlayerId = " + instPlayerId + " and chapterId = " + activityStarStore.getChapterId(), instPlayerId);
		if (instPlayerChapterList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPagodaStore);
			return;
		} else {
			if (instPlayerChapterList.get(0).getIsPass() == 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPagodaStore);
				return;
			}
		}
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.starStore, instPlayerId);
		InstPlayerAchievementValue instPlayerAchievementValue = getInstPlayerAchievementValueDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.chapter, instPlayerId).get(0);
		int start = instPlayerAchievementValue.getValue(); // 玩家可用星星数
		InstActivity instActivity = null;
		// 验证购买次数以及星星数是否充足
		if (instActivityList.size() > 0) {
			instActivity = instActivityList.get(0);
			int value = StringUtil.getSegValue(instActivity.getActivityTime(), ";" + starStoreId + "_");
			if (activityStarStore.getBuyCount() <= value) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notBuyEliteFightNum);
				return;
			}
			start = start - instActivity.getUseNum();
		}
		if (start < activityStarStore.getStarNum()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notStarNum);
			return;
		}

		// 添加物品
		MessageData syncMsgData = new MessageData();
		String things = activityStarStore.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 做购买记录
		if (instActivityList.size() <= 0) {
			instActivity = new InstActivity();
			instActivity.setUseNum(activityStarStore.getStarNum() + instActivity.getUseNum());
			instActivity.setActivityId(StaticActivity.starStore);
			instActivity.setInstPlayerId(instPlayerId);
			instActivity.setActivityTime(";" + starStoreId + "_" + 1 + ";");
			getInstActivityDAL().add(instActivity, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);

		} else {
			instActivity.setUseNum(activityStarStore.getStarNum() + instActivity.getUseNum());
			instActivity.setActivityTime(StringUtil.segValueAddOne(instActivity.getActivityTime(), ";" + starStoreId + "_"));
			getInstActivityDAL().update(instActivity, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instActivity, instActivity.getId(), instActivity.getResult(), syncMsgData);
		}

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 月卡贵族下发
	 * 
	 * @author hzw
	 * @date 2015-6-11上午10:14:55
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void monthCardStore(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instPlayerId = getInstPlayerId(channelId);
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		MessageData testMsgData = new MessageData();
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCardStore, instPlayerId);
		List<DictActivityMonthCardStoreTemp> monthCardStoreTempList = DictList.dictActivityMonthCardStoreTempList;
		for (DictActivityMonthCardStoreTemp obj : monthCardStoreTempList) {
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", obj.getId());// 字典Id
			msgData.putStringItem("3", obj.getThings());// 物品列表
			int count = obj.getBuyCount();
			if (instActivityList.size() > 0) {
				InstActivity instActivity = instActivityList.get(0);
				int value = StringUtil.getSegValue(instActivity.getActivityTime(), ";" + obj.getId() + "_");
				if (value > 0) {
					count = count - value;
				}
			}
			msgData.putIntItem("4", count);// 总购买次数
			msgData.putIntItem("5", obj.getBuyType());// 购买类型
			msgData.putIntItem("6", obj.getBuyValue());// 购买价格
			msgData.putIntItem("7", obj.getOriginalValue());// 原价
			msgData.putIntItem("8", obj.getVip());// vip限制
			msgData.putStringItem("9", obj.getDescription());// 礼包名称
			testMsgData.putMessageItem(obj.getId() + "", msgData.getMsgData());
		}
		retMsgData.putMessageItem("DictActivityMonthCardStoreTemp", testMsgData.getMsgData());

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 月卡贵族购买
	 * 
	 * @author hzw
	 * @date 2015-6-11上午11:39:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void monthCardStoreBuy(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int monthCardStoreTempId = (int) msgMap.get("monthCardStoreTempId");// 月卡贵族字典Id
		DictActivityMonthCardStoreTemp activityMonthCardStoreTemp = DictMap.dictActivityMonthCardStoreTempMap.get(monthCardStoreTempId + "");
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard, instPlayerId);
		InstActivity instActivity = null;
		// 验证是否月卡
		if (instActivityList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_monthCard);
			return;
		} else {
			instActivity = instActivityList.get(0);
			String activityTime = instActivity.getActivityTime();
			if (activityTime.equals("") || DateUtil.dayCompare(activityTime, DateUtil.getCurrTime()) < 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_monthCard);
				return;
			}
		}

		// 验证是否vip达到
		if (instPlayer.getVipLevel() < activityMonthCardStoreTemp.getVip()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_vipLevel);
			return;
		}

		instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCardStore, instPlayerId);

		// 验证购买次数是否充足
		if (instActivityList.size() > 0) {
			instActivity = instActivityList.get(0);
			int value = StringUtil.getSegValue(instActivity.getActivityTime(), ";" + monthCardStoreTempId + "_");
			if (activityMonthCardStoreTemp.getBuyCount() <= value) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notBuyEliteFightNum);
				return;
			}
		}

		// 验证元宝/银币是否足够
		if (activityMonthCardStoreTemp.getBuyType() == 1 && instPlayer.getGold() < activityMonthCardStoreTemp.getBuyValue()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		if (activityMonthCardStoreTemp.getBuyType() == 2 && ConvertUtil.toInt(instPlayer.getCopper()) < activityMonthCardStoreTemp.getBuyValue()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_copperNotEnough);
			return;
		}

		// 添加物品
		MessageData syncMsgData = new MessageData();
		String things = activityMonthCardStoreTemp.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 扣除元宝/银币
		if (activityMonthCardStoreTemp.getBuyType() == 1) {
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, activityMonthCardStoreTemp.getBuyValue(), msgMap);
		} else {
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - activityMonthCardStoreTemp.getBuyValue()) + "");
		}
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 做购买记录
		if (instActivityList.size() <= 0) {
			instActivity.setActivityId(StaticActivity.monthCardStore);
			instActivity.setInstPlayerId(instPlayerId);
			instActivity.setActivityTime(";" + monthCardStoreTempId + "_" + 1 + ";");
			getInstActivityDAL().add(instActivity, instPlayerId);
		} else {
			DictActivityMonthCardStore dictActivityMonthCardStore = DictMap.dictActivityMonthCardStoreMap.get(instActivity.getActivityTime().substring(1, 2));
			if (dictActivityMonthCardStore.getType() != activityMonthCardStoreTemp.getType()) {
				instActivity.setActivityTime(";" + monthCardStoreTempId + "_" + 1 + ";");
			} else {
				instActivity.setActivityTime(StringUtil.segValueAddOne(instActivity.getActivityTime(), ";" + monthCardStoreTempId + "_"));
			}
			getInstActivityDAL().update(instActivity, instPlayerId);
		}

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 获取lv专卖
	 * 
	 * @author mp
	 * @date 2015-6-10 上午11:18:52
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getLvSell(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.levelSell, instPlayerId);

		// 组织数据,格式为 id level things sellGold limitNum(0的时候显示已购买)/...
		StringBuilder sb = new StringBuilder();
		List<DictActivityLvStore> activityLvStoreList = DictList.dictActivityLvStoreList;
		for (DictActivityLvStore obj : activityLvStoreList) {
			sb.append(obj.getId()).append(" ").append(obj.getLevel()).append(" ").append(obj.getThings()).append(" ").append(obj.getSellGold()).append(" ");
			if (instActivityList.size() <= 0) {
				sb.append(obj.getLimitNum());
			} else {
				InstActivity instActivity = instActivityList.get(0);
				if (instActivity.getActivityTime().equals("")) {
					sb.append(obj.getLimitNum());
				} else {
					int value = StringUtil.getSegValue(instActivity.getActivityTime(), ";" + obj.getId() + "_");
					sb.append(obj.getLimitNum() - value);
				}
			}
			sb.append("/");
		}

		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", StringUtil.noContainLastString(sb.toString()));
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * lv专卖购买
	 * 
	 * @author mp
	 * @date 2015-6-10 上午11:18:56
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void lvSellBuy(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int id = (int) msgMap.get("id");// lv专卖的字典Id
		DictActivityLvStore activityLvStore = DictMap.dictActivityLvStoreMap.get(id + "");
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.levelSell, instPlayerId);

		// 验证是否达到等级
		if (instPlayer.getLevel() < activityLvStore.getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}

		// 验证是否元宝充足
		if (instPlayer.getGold() < activityLvStore.getSellGold()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}

		// 验证限制次数是否足够
		if (instActivityList.size() > 0) {
			InstActivity instActivity = instActivityList.get(0);
			int value = StringUtil.getSegValue(instActivity.getActivityTime(), ";" + activityLvStore.getId() + "_");
			if ((activityLvStore.getLimitNum() - value) <= 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_finishTimes);
				return;
			}
		}

		// 扣钱,给东西
		MessageData syncMsgData = new MessageData();
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, activityLvStore.getSellGold(), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 添加物品
		String things = activityLvStore.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 做购买记录
		if (instActivityList.size() <= 0) {
			ActivityUtil.addInstActivity(instPlayerId, StaticActivity.levelSell, ";" + id + "_" + 1 + ";","");// 格式为 ;1_2;2_10;
		} else {
			InstActivity instActivity = instActivityList.get(0);
			instActivity.setActivityTime(StringUtil.segValueAddOne(instActivity.getActivityTime(), ";" + id + "_"));
			getInstActivityDAL().update(instActivity, instPlayerId);// 只服务器用,不用同步给客户端
		}

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 获取vip专卖
	 * 
	 * @author mp
	 * @date 2015-6-10 上午11:19:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getVipSell(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.vipSell, instPlayerId);

		// 组织数据,格式为 id vipLevel things oldPrice nowPrice limitNum(0的时候显示已购买)/...
		StringBuilder sb = new StringBuilder();
		List<DictActivityVipStore> activityVipStoreList = DictList.dictActivityVipStoreList;
		for (DictActivityVipStore obj : activityVipStoreList) {
			sb.append(obj.getId()).append(" ").append(obj.getVipLevel()).append(" ").append(obj.getThings()).append(" ").append(obj.getOldPrice()).append(" ").append(obj.getNowPrice()).append(" ");
			if (instActivityList.size() <= 0) {
				sb.append(obj.getLimitNum());
			} else {
				InstActivity instActivity = instActivityList.get(0);
				if (instActivity.getActivityTime().equals("")) {
					sb.append(obj.getLimitNum());
				} else {
					int value = StringUtil.getSegValue(instActivity.getActivityTime(), ";" + obj.getId() + "_");
					sb.append(obj.getLimitNum() - value);
				}
			}
			sb.append("/");
		}

		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", StringUtil.noContainLastString(sb.toString()));
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * vip专卖购买
	 * 
	 * @author mp
	 * @date 2015-6-10 上午11:19:04
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void vipSellBuy(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int id = (int) msgMap.get("id");// vip专卖的字典Id
		DictActivityVipStore activityVipStore = DictMap.dictActivityVipStoreMap.get(id + "");
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.vipSell, instPlayerId);

		// 验证是否达到Vip等级
		if (instPlayer.getVipLevel() < activityVipStore.getVipLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noUpVip);
			return;
		}

		// 验证是否元宝充足
		if (instPlayer.getGold() < activityVipStore.getNowPrice()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}

		// 验证限制次数是否足够
		if (instActivityList.size() > 0) {
			InstActivity instActivity = instActivityList.get(0);
			int value = StringUtil.getSegValue(instActivity.getActivityTime(), ";" + activityVipStore.getId() + "_");
			if ((activityVipStore.getLimitNum() - value) <= 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_finishTimes);
				return;
			}
		}

		// 扣钱,给东西
		MessageData syncMsgData = new MessageData();
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, activityVipStore.getNowPrice(), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 添加物品
		String things = activityVipStore.getThings();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 做购买记录
		if (instActivityList.size() <= 0) {
			ActivityUtil.addInstActivity(instPlayerId, StaticActivity.vipSell, ";" + id + "_" + 1 + ";","");// 格式为 ;1_2;2_10;
		} else {
			InstActivity instActivity = instActivityList.get(0);
			instActivity.setActivityTime(StringUtil.segValueAddOne(instActivity.getActivityTime(), ";" + id + "_"));
			getInstActivityDAL().update(instActivity, instPlayerId);// 只服务器用,不用同步给客户端
		}

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 试练日领奖
	 * 
	 * @author hzw
	 * @date 2015-6-17下午4:39:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void tryToPractice(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int id = (int) msgMap.get("id");// 试练日字典Id
		int instPlayerTryToPracticeId = (int) msgMap.get("instPlayerTryToPracticeId");// 试练日实例Id 默认0
		DictTryToPractice tryToPractice = DictMap.dictTryToPracticeMap.get(id + "");
		DictTryToPracticeType tryToPracticeType = DictMap.dictTryToPracticeTypeMap.get(tryToPractice.getTryToPracticeTypeId() + "");
		
		//验证试炼日天数
		int currDay = tryToPracticeType.getDay();//领奖的此物品是第几天试炼日的
		//计算当前是第几天的试炼日
		InstUser instUser = getInstUserDAL().getModel(instPlayerId, 0);
		String regeditDate = instUser.getFirstLoginDate();
		String currDate = DateUtil.getYmdDate();
		int jiange = DateUtil.dayDiff(regeditDate, currDate) + 1;
		System.out.println("此奖品为第几天可以领=" + currDay + "      服务器算出的今天为第几天的试炼日" + jiange);
		
		//如果领奖的此物品是第几天试炼日的   大于了   当前试炼日  ,既是作弊
		if (currDay > jiange) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}

		// 验证是否达到条件
		if (VerifyUtil.vfTryToPractice(channelId, msgMap, instPlayerId, tryToPracticeType.getId(), tryToPractice)) {
			return;
		}
		
		MessageData syncMsgData = new MessageData();
		// 做领奖记录
		InstPlayerTryToPractice instPlayerTryToPractice = null;
		if (instPlayerTryToPracticeId == 0) {
			List<InstPlayerTryToPractice> tryToPracticesList = getInstPlayerTryToPracticeDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if (tryToPracticesList.size() > 0) {
				instPlayerTryToPractice = tryToPracticesList.get(0);
			}
			if (instPlayerTryToPractice == null) {
				instPlayerTryToPractice = new InstPlayerTryToPractice();
				instPlayerTryToPractice.setInstPlayerId(instPlayerId);
				instPlayerTryToPractice.setTryToPracticeIds(id + "");
				instPlayerTryToPractice.setCanTryToPracticeIds("");
				getInstPlayerTryToPracticeDAL().add(instPlayerTryToPractice, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
			} else {
				
				//验证是否已经领过
				if (StringUtil.contains(instPlayerTryToPractice.getTryToPracticeIds(), id + "", ";")) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_award_gotten);
					return;
				}
				
				instPlayerTryToPractice.setTryToPracticeIds(instPlayerTryToPractice.getTryToPracticeIds() + ";" + id);
				getInstPlayerTryToPracticeDAL().update(instPlayerTryToPractice, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
			}
		} else {
			instPlayerTryToPractice = getInstPlayerTryToPracticeDAL().getModel(instPlayerTryToPracticeId, instPlayerId);
			if (instPlayerTryToPractice.getInstPlayerId() != instPlayerId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if (instPlayerTryToPractice.getTryToPracticeIds() == null || instPlayerTryToPractice.getTryToPracticeIds().equals("")) {
				instPlayerTryToPractice.setTryToPracticeIds(id + "");
			} else {
				if (StringUtil.contains(instPlayerTryToPractice.getTryToPracticeIds(), id + "", ";")) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noTryToPractice);
					return;
				} else {
					instPlayerTryToPractice.setTryToPracticeIds(instPlayerTryToPractice.getTryToPracticeIds() + ";" + id);
				}
			}
			getInstPlayerTryToPracticeDAL().update(instPlayerTryToPractice, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
		}

		// 添加物品
		String things = tryToPractice.getRewards();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 试练日购买
	 * 
	 * @author hzw
	 * @date 2015-6-17下午4:39:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void tryToPracticeBuy(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int id = (int) msgMap.get("id");// 试练日字典Id
		int instPlayerTryToPracticeId = (int) msgMap.get("instPlayerTryToPracticeId");// 试练日实例Id 默认0
		
		DictTryToPractice tryToPractice = DictMap.dictTryToPracticeMap.get(id + "");
		// 验证元宝
		if (tryToPractice.getTryToPracticeTypeId() != StaticTryToPracticeType.hotShopping1 && tryToPractice.getTryToPracticeTypeId() != StaticTryToPracticeType.hotShopping2 && tryToPractice.getTryToPracticeTypeId() != StaticTryToPracticeType.hotShopping3 && tryToPractice.getTryToPracticeTypeId() != StaticTryToPracticeType.hotShopping4 && tryToPractice.getTryToPracticeTypeId() != StaticTryToPracticeType.hotShopping5) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_todayGoods);
			return;
		}
		if (instPlayer.getGold() < tryToPractice.getProgress()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}

		MessageData syncMsgData = new MessageData();

		// 做领奖记录
		InstPlayerTryToPractice instPlayerTryToPractice = null;
		if (instPlayerTryToPracticeId == 0) {
			
			List<InstPlayerTryToPractice> tryToPracticesList = getInstPlayerTryToPracticeDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if (tryToPracticesList.size() > 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
				return;
			}
			if (instPlayerTryToPractice == null) {
				instPlayerTryToPractice = new InstPlayerTryToPractice();
				instPlayerTryToPractice.setInstPlayerId(instPlayerId);
				instPlayerTryToPractice.setTryToPracticeIds(id + "");
				instPlayerTryToPractice.setCanTryToPracticeIds("");
				getInstPlayerTryToPracticeDAL().add(instPlayerTryToPractice, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
			}/* else {
				
				//判断是否已经领取过
				if (StringUtil.contains(instPlayerTryToPractice.getTryToPracticeIds(), id + "", ";")) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noTryToPractice);
					return;
				}
				instPlayerTryToPractice.setTryToPracticeIds(instPlayerTryToPractice.getTryToPracticeIds() + ";" + id);
				getInstPlayerTryToPracticeDAL().update(instPlayerTryToPractice, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
			}*/
		} else {
			instPlayerTryToPractice = getInstPlayerTryToPracticeDAL().getModel(instPlayerTryToPracticeId, instPlayerId);
			if (instPlayerTryToPractice.getInstPlayerId() != instPlayerId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if (instPlayerTryToPractice.getTryToPracticeIds() == null || instPlayerTryToPractice.getTryToPracticeIds().equals("")) {
				instPlayerTryToPractice.setTryToPracticeIds(id + "");
			} else {
				if (StringUtil.contains(instPlayerTryToPractice.getTryToPracticeIds(), id + "", ";")) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noTryToPractice);
					return;
				} else {
					instPlayerTryToPractice.setTryToPracticeIds(instPlayerTryToPractice.getTryToPracticeIds() + ";" + id);
				}
			}
			getInstPlayerTryToPracticeDAL().update(instPlayerTryToPractice, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
		}

		// 添加物品
		String things = tryToPractice.getRewards();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 扣除元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, tryToPractice.getProgress(), msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 试练日副本
	 * 
	 * @author hzw
	 * @date 2015-6-17下午4:35:12
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void tryToPracticeBarrier(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int id = (int) msgMap.get("id");// 试练日字典Id
		int instPlayerTryToPracticeId = (int) msgMap.get("instPlayerTryToPracticeId");// 试练日实例Id 默认0
//		String coredata = (String) msgMap.get("coredata");// 1:2_3|3_3;2:2_3|3_3;3:2_3|3_3 1-卡牌 2-装备 3-功法法宝
		DictTryToPractice tryToPractice = DictMap.dictTryToPracticeMap.get(id + "");

		// 用于验证玩家是否利用烧饼等修改器
//		if (VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)) {
//			return;
//		}

		MessageData syncMsgData = new MessageData();

		// 做领奖记录
		InstPlayerTryToPractice instPlayerTryToPractice = null;
		if (instPlayerTryToPracticeId == 0) {
			List<InstPlayerTryToPractice> tryToPracticesList = getInstPlayerTryToPracticeDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if (tryToPracticesList.size() > 0) {
				instPlayerTryToPractice = tryToPracticesList.get(0);
			}
			if (instPlayerTryToPractice == null) {
				instPlayerTryToPractice = new InstPlayerTryToPractice();
				instPlayerTryToPractice.setInstPlayerId(instPlayerId);
				instPlayerTryToPractice.setTryToPracticeIds("");
				instPlayerTryToPractice.setCanTryToPracticeIds(id + "");
				getInstPlayerTryToPracticeDAL().add(instPlayerTryToPractice, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
			} else {
				instPlayerTryToPractice.setCanTryToPracticeIds(instPlayerTryToPractice.getCanTryToPracticeIds() + ";" + id);
				getInstPlayerTryToPracticeDAL().update(instPlayerTryToPractice, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
			}
		} else {
			instPlayerTryToPractice = getInstPlayerTryToPracticeDAL().getModel(instPlayerTryToPracticeId, instPlayerId);
			if (instPlayerTryToPractice.getInstPlayerId() != instPlayerId) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if (instPlayerTryToPractice.getCanTryToPracticeIds() == null || instPlayerTryToPractice.getCanTryToPracticeIds().equals("")) {
				instPlayerTryToPractice.setCanTryToPracticeIds(id + "");
			} else {
				if (StringUtil.contains(instPlayerTryToPractice.getCanTryToPracticeIds(), id + "", ";")) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noTryToPractice);
					return;
				} else {
					instPlayerTryToPractice.setCanTryToPracticeIds(instPlayerTryToPractice.getCanTryToPracticeIds() + ";" + id);
				}
			}
			getInstPlayerTryToPracticeDAL().update(instPlayerTryToPractice, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
		}

		// 添加物品
		String things = tryToPractice.getRewards();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 试练日领大奖
	 * 
	 * @author hzw
	 * @date 2015-8-8下午2:51:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void tryToPracticeAward(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int id = (int) msgMap.get("id");// 试练日天数分组字典表Id
		int instPlayerTryToPracticeId = (int) msgMap.get("instPlayerTryToPracticeId");// 试练日实例Id 默认0
		DictTryToPracticeType tryToPracticeType = DictMap.dictTryToPracticeTypeMap.get(id + "");
		MessageData syncMsgData = new MessageData();

		// 判断试炼日是否已结束

		if (instPlayerTryToPracticeId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noTryToPractice);
			return;
		}
		List<InstPlayerTryToPractice> tryToPracticesList = getInstPlayerTryToPracticeDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if (tryToPracticesList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noTryToPractice);
			return;
		} else {
			InstPlayerTryToPractice instPlayerTryToPractice = tryToPracticesList.get(0);
			String awards = instPlayerTryToPractice.getAwards();
			if (StringUtil.contains(awards, id + "", ";")) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noTryToPractice);
				return;
			}

			// 玩家已领取的试练日字典Ids
			String tryToPractice = instPlayerTryToPractice.getTryToPracticeIds();
			String tryToPractices[] = tryToPractice.split(";");
			int progress = 0;
			for (String tryToPracticeId : tryToPractices) {
				DictTryToPractice dictTryToPractice = DictMap.dictTryToPracticeMap.get(tryToPracticeId + "");
				DictTryToPracticeType dictTryToPracticeType = DictMap.dictTryToPracticeTypeMap.get(dictTryToPractice.getTryToPracticeTypeId() + "");
				if (dictTryToPracticeType.getDay() == tryToPracticeType.getDay() && dictTryToPracticeType.getId() != StaticTryToPracticeType.hotShopping1 && dictTryToPracticeType.getId() != StaticTryToPracticeType.hotShopping2 && dictTryToPracticeType.getId() != StaticTryToPracticeType.hotShopping3 && dictTryToPracticeType.getId() != StaticTryToPracticeType.hotShopping4 && dictTryToPracticeType.getId() != StaticTryToPracticeType.hotShopping5) {
					progress++;
				}
			}
			if (progress < tryToPracticeType.getCount()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noTryToPractice);
				return;
			}
			if (instPlayerTryToPractice.getAwards() == null || instPlayerTryToPractice.getAwards().equals("")) {
				instPlayerTryToPractice.setAwards(tryToPracticeType.getDay() + "");
			} else {
				instPlayerTryToPractice.setAwards(instPlayerTryToPractice.getAwards() + ";" + tryToPracticeType.getDay());
			}

			getInstPlayerTryToPracticeDAL().update(instPlayerTryToPractice, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerTryToPractice, instPlayerTryToPractice.getId(), instPlayerTryToPractice.getResult(), syncMsgData);
		}

		// 添加物品
		String things = tryToPracticeType.getRewards();
		String thing[] = things.split(";");
		for (String str : thing) {
			ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), syncMsgData, msgMap);
		}

		// 发送消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 全民福利领奖
	 * 
	 * @author mp
	 * @date 2015-8-15 下午7:45:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void allPeapleWealReward(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int id = (int) msgMap.get("id");// 全民福利领奖物品Id
		DictActivityAllPeapleWeal activityAllPeapleWeal = DictMap.dictActivityAllPeapleWealMap.get(id + "");

		// 验证是否达到可领取的购买基金人数
		if (ParamConfig.sumBuyFundNum < activityAllPeapleWeal.getBuyerNum()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_allPeapleWeal_NotEnough);
			return;
		}

		// 验证是否已领
		String haveGetRewardList = "";
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '" + StaticBigTable.allPeapleWeal + "'", 0);
		if (instPlayerBigTableList.size() > 0) {
			haveGetRewardList = instPlayerBigTableList.get(0).getValue1();
		}
		if (StringUtil.contains(haveGetRewardList, activityAllPeapleWeal.getId() + "", ";")) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveWash);
			return;
		}

		// 领取物品
		MessageData syncMsgData = new MessageData();
		ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(activityAllPeapleWeal.getThing().split("_")[0]), ConvertUtil.toInt(activityAllPeapleWeal.getThing().split("_")[1]), ConvertUtil.toInt(activityAllPeapleWeal.getThing().split("_")[2]), syncMsgData, msgMap);

		// 记录领取日志
		if (instPlayerBigTableList.size() > 0) {
			InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
			instPlayerBigTable.setValue1(instPlayerBigTable.getValue1() + ";" + activityAllPeapleWeal.getId());
			getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
		} else {
			InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
			instPlayerBigTable.setInstPlayerId(instPlayerId);
			instPlayerBigTable.setProperties(StaticBigTable.allPeapleWeal);
			instPlayerBigTable.setValue1(activityAllPeapleWeal.getId() + "");
			getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
		}

		// 返回消息
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 进入限时英雄界面
	 * 
	 * @author mp
	 * @date 2015-8-17 上午10:56:38
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void intoLimitTimeHero(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
//		if (!ActivityUtil.isInActivity(StaticActivity.LimitTimeHero)) {
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noInActivityDate);
//			return;
//		}
		
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		MessageData retMsgData = new MessageData();

		// 降序排列
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Entry<Integer, Integer> entry : ParamConfig.recruitJifenMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		Map<Integer, Integer> sortedMap = CollectionUtil.sortByValueDown(map);
		// System.out.println("sortedMap= " + sortedMap.size() + "  " + sortedMap);

		// 组织前50名的新map
		Map<Integer, Integer> newSorted20Map = new LinkedHashMap<>();
		int index = 0;
		for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
			index++;
			if (index > DictMapUtil.getSysConfigIntValue(StaticSysConfig.limitTimePeapleRankSumNum)) {
				break;
			}
			newSorted20Map.put(entry.getKey(), entry.getValue());
		}
		// System.out.println("newSorted20MapSize= " + newSorted20Map.size() + " " + newSorted20Map);

		// 组织重复的积分 [积分 玩家id列表]
		Map<Integer, String> orgRepeatJifenMap = new HashMap<>();
		for (Entry<Integer, Integer> entry : newSorted20Map.entrySet()) {
			int instPlayerObjId = entry.getKey();
			int jifen = entry.getValue();
			if (orgRepeatJifenMap.containsKey(jifen)) {
				String playerIdList = orgRepeatJifenMap.get(jifen);
				orgRepeatJifenMap.put(jifen, playerIdList + ";" + instPlayerObjId);
			} else {
				orgRepeatJifenMap.put(jifen, instPlayerObjId + "");
			}
		}
		// System.out.println(orgRepeatJifenMap);

		// 为重复的组织时间
		HashMap<Integer, Map<Integer, Long>> orgRepeatTimeMap = new HashMap<>();
		for (Entry<Integer, String> entry : orgRepeatJifenMap.entrySet()) {
			int jifen = entry.getKey();
			String playerIdList = entry.getValue();
			if (playerIdList.split(";").length > 1) {
				Map<Integer, Long> repeatPidJifenMap = new HashMap<>();
				for (String pId : playerIdList.split(";")) {
					long timeMill = 0;
					List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + ConvertUtil.toInt(pId) + " and properties = '" + StaticBigTable.recruitJiFen + "'", 0);
					if (instPlayerBigTableList.size() > 0) {
						InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
						if (instPlayerBigTable.getUpdateTime() != null && !instPlayerBigTable.getUpdateTime().equals("")) {
							timeMill = DateUtil.getMillSecond(instPlayerBigTable.getUpdateTime());
						}
					}
					repeatPidJifenMap.put(ConvertUtil.toInt(pId), timeMill);
				}
				orgRepeatTimeMap.put(jifen, repeatPidJifenMap);
			}
		}
		// System.out.println(orgRepeatTimeMap);

		// 把组织好的map按时间排序
		HashMap<Integer, Map<Integer, Long>> orgRepeatTimeNewMap = new HashMap<>();
		for (Entry<Integer, Map<Integer, Long>> orgRepeatTimeEntry : orgRepeatTimeMap.entrySet()) {
			int jifen = orgRepeatTimeEntry.getKey();
			Map<Integer, Long> pidTimeMap = orgRepeatTimeEntry.getValue();
			Map<Integer, Long> sortedTimeMap = CollectionUtil.sortByValueUp(pidTimeMap);
			orgRepeatTimeNewMap.put(jifen, sortedTimeMap);
		}
		// System.out.println(orgRepeatTimeNewMap);

		// 重新组织结果map
		String jifenRecord = "";
		Map<Integer, Integer> resultMap = new LinkedHashMap<>();
		for (Entry<Integer, Integer> entry : newSorted20Map.entrySet()) {
			int pId = entry.getKey();
			int jifen = entry.getValue();

			if (!StringUtil.contains(jifenRecord, jifen + "", ";")) {
				if (orgRepeatTimeNewMap.containsKey(jifen)) {
					Map<Integer, Long> timeNewMap = orgRepeatTimeNewMap.get(jifen);
					for (Entry<Integer, Long> entryTimeNewEntry : timeNewMap.entrySet()) {
						resultMap.put(entryTimeNewEntry.getKey(), jifen);
					}
				} else {
					resultMap.put(pId, jifen);
				}
			}
		}

		// System.out.println(resultMap);

		// 1积分排行[发送前50名的] 排名序号|玩家名|积分/
		StringBuilder jifenRankBd = new StringBuilder();
		int jifenRankIndex = 0;
		// for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
		for (Entry<Integer, Integer> entry : resultMap.entrySet()) {
			if (entry.getValue() > 0) {
				jifenRankIndex++;
				if (jifenRankIndex > DictMapUtil.getSysConfigIntValue(StaticSysConfig.limitTimePeapleRankSumNum)) {
					break;
				}
				String playerName = ParamConfig.playerNameMap.get(entry.getKey());
				jifenRankBd.append(jifenRankIndex).append("|").append(playerName == null ? "" : playerName).append("|").append(entry.getValue()).append("/");
			}
		}
		if (jifenRankBd.toString().length() > 0) {
			retMsgData.putStringItem("1", StringUtil.noContainLastString(jifenRankBd.toString()));
		} else {
			retMsgData.putStringItem("1", "");
		}

		// 3排名奖励 id|startRankNum|endRankNum|奖励/
		StringBuilder rankRewardBd = new StringBuilder();
		List<DictActivityLimitTimeHeroRankReward> activityLimitTimeHeroRankRewardList = DictList.dictActivityLimitTimeHeroRankRewardList;
		for (DictActivityLimitTimeHeroRankReward obj : activityLimitTimeHeroRankRewardList) {
			rankRewardBd.append(obj.getId()).append("|").append(obj.getStartRankNum()).append("|").append(obj.getEndRankNum()).append("|").append(obj.getRewards()).append("/");
		}
		String rankReward = StringUtil.noContainLastString(rankRewardBd.toString());
		retMsgData.putStringItem("3", rankReward);

		// 4招募信息 倒计时时间-毫秒数 0表示没有|元宝数|10次招募话费元宝数|再招几次必得
		long backTime = 0;// 倒计时时间-毫秒数
		int goldNum = 0;// 元宝数
		List<InstPlayerRecruit> instPlayerRecruitList = getInstPlayerRecruitDAL().getList("instPlayerId = " + instPlayerId + " and recruitTypeId = " + StaticRecruitType.goldRecruit, 0);
		InstPlayerRecruit instPlayerRecruit = null;
		if (instPlayerRecruitList.size() > 0) {
			instPlayerRecruit = instPlayerRecruitList.get(0);
		}

		List<InstPlayerRecruit> instPlayerRecruitList1 = getInstPlayerRecruitDAL().getList("instPlayerId = " + instPlayerId + " and recruitTypeId = " + StaticRecruitType.diamondRecruit, 0);
		InstPlayerRecruit instPlayerRecruit1 = null;
		if (instPlayerRecruitList1.size() > 0) {
			instPlayerRecruit1 = instPlayerRecruitList1.get(0);
		}

		// 花费元宝数固定,不随次数变化而变化
		goldNum = DictMap.dictRecruitTypeMap.get(StaticRecruitType.diamondRecruit + "").getGoldRecruitFee();

		// 首次招募,没有倒计时[返回0], 元宝数为配置数据
		int currRecruitTimes = 0;
		if (instPlayerRecruit1 != null) {
			currRecruitTimes = instPlayerRecruit1.getRecruitSumTimes();
		}

		if (instPlayerRecruit == null) {
			backTime = 0;
		} else {
			// 计算倒计时
			if (instPlayerRecruit.getNextFreeRecruitTime().equals("")) {
				backTime = 0;
			} else {
				long freeTime = DateUtil.getMillSecond(instPlayerRecruit.getNextFreeRecruitTime());
				backTime = (freeTime - DateUtil.getCurrMill()) < 0 ? 0 : (freeTime - DateUtil.getCurrMill());
				if (backTime != 0) {
					backTime += 2000;
				}
			}
		}
		String recruitStr = backTime + "|" + goldNum + "|" + DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitGoldNum) + "|" + (CardUtil.nextRecruitTimes(currRecruitTimes, 3) - 1);
		retMsgData.putStringItem("4", recruitStr);

		// 5当前积分
		int jifen = 0;
		if (sortedMap.containsKey(instPlayerId)) {
			jifen = sortedMap.get(instPlayerId);
		}
		retMsgData.putIntItem("5", jifen);

		// 6当前积分排名
		if (resultMap.containsKey(instPlayerId)) {
			int ownRank = 0;
			int indexRank = 0;
			int isInRank = 0;
			for (Entry<Integer, Integer> entry : resultMap.entrySet()) {
				indexRank++;
				if (entry.getKey() == instPlayerId) {
					isInRank = 1;
					break;
				}
			}
			if (isInRank == 1) {
				ownRank = indexRank;
			}
			retMsgData.putIntItem("6", ownRank);
		} else {
			int ownRank = 0;
			int indexRank = 0;
			int isInRank = 0;
			for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
				indexRank++;
				if (entry.getKey() == instPlayerId) {
					isInRank = 1;
					break;
				}
			}
			if (isInRank == 1) {
				ownRank = indexRank;
			}
			retMsgData.putIntItem("6", ownRank);
		}

		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 获取限时英雄积分奖励
	 * 
	 * @author mp
	 * @date 2015-8-18 下午2:12:42
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getJifenRewardList(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData retMsgData = new MessageData();
		// 2积分奖励 [字段间用|隔开, 行与行之间用/隔开] id|累计积分数|奖励/
		StringBuilder jifenRewardBd = new StringBuilder();
		List<DictActivityLimitTimeHeroJiFenReward> activityLimitTimeHeroJiFenRewardList = DictList.dictActivityLimitTimeHeroJiFenRewardList;
		for (DictActivityLimitTimeHeroJiFenReward obj : activityLimitTimeHeroJiFenRewardList) {
			jifenRewardBd.append(obj.getId()).append("|").append(obj.getSaveJifenNum()).append("|").append(obj.getRewards()).append("/");
		}
		String jifenReward = "";
		if (jifenRewardBd.toString().length() > 0) {
			jifenReward = StringUtil.noContainLastString(jifenRewardBd.toString());
		}

		retMsgData.putStringItem("1", jifenReward);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 进入最强英雄界面
	 * 
	 * @author mp
	 * @date 2015-8-19 上午10:47:21
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void intoStrogerHero(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		MessageData retMsgData = new MessageData();

		// 1.第一名各时间段奖励, 有时间顺序的[12,21,23] [tableTypeId_tableFiledId_value|]
		StringBuilder rankSb = new StringBuilder();
		List<DictActivityStrogerHero> activityStrogerHeroList = DictList.dictActivityStrogerHeroList;
		for (DictActivityStrogerHero obj : activityStrogerHeroList) {
			if (obj.getRewardTimePoint() == DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime1) && obj.getRank() == 1) {
				rankSb.append(obj.getRewards().split(";")[0]).append("|");
			}
			if (obj.getRewardTimePoint() == DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime2) && obj.getRank() == 1) {
				rankSb.append(obj.getRewards().split(";")[0]).append("|");
			}
			if (obj.getRewardTimePoint() == DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRewardTime3) && obj.getRank() == 1) {
				rankSb.append(obj.getRewards().split(";")[0]).append("|");
			}
		}
		retMsgData.putStringItem("1", rankSb.toString().length() > 0 ? StringUtil.noContainLastString(rankSb.toString()) : rankSb.toString());

		// 对积分进行降序排列
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Entry<Integer, Integer> entry : ParamConfig.strogerHeroJifenMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		Map<Integer, Integer> sortedMap = CollectionUtil.sortByValueDown(map);
		// System.out.println("sortedMapSize== " + sortedMap.size());

		// 组织前20名的新map
		Map<Integer, Integer> newSorted20Map = new LinkedHashMap<>();
		int index = 0;
		for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
			index++;
			if (index > DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRankNum)) {
				break;
			}
			newSorted20Map.put(entry.getKey(), entry.getValue());
		}
		// System.out.println("newSorted20MapSize " + newSorted20Map.size());

		// 组织重复的积分 [积分 玩家id列表]
		Map<Integer, String> orgRepeatJifenMap = new HashMap<>();
		for (Entry<Integer, Integer> entry : newSorted20Map.entrySet()) {
			int instPlayerObjId = entry.getKey();
			int jifen = entry.getValue();
			if (orgRepeatJifenMap.containsKey(jifen)) {
				String playerIdList = orgRepeatJifenMap.get(jifen);
				orgRepeatJifenMap.put(jifen, playerIdList + ";" + instPlayerObjId);
			} else {
				orgRepeatJifenMap.put(jifen, instPlayerObjId + "");
			}
		}
		// System.out.println(orgRepeatJifenMap);

		// 为重复的组织时间
		HashMap<Integer, Map<Integer, Long>> orgRepeatTimeMap = new HashMap<>();
		for (Entry<Integer, String> entry : orgRepeatJifenMap.entrySet()) {
			int jifen = entry.getKey();
			String playerIdList = entry.getValue();
			if (playerIdList.split(";").length > 1) {
				Map<Integer, Long> repeatPidJifenMap = new HashMap<>();
				for (String pId : playerIdList.split(";")) {
					int instPid = Integer.valueOf(pId);
					long timeMill = 0;
					String maxTime = "";
					String sql = "select max(operTime) as maxTime from Inst_Player_GoldStatics where instPlayerId = " + instPid + " and type = 0;";
					List<Map<String, Object>> retList = getInstPlayerGoldStaticsDAL().sqlHelper(sql);
					for (Map<String, Object> retMap : retList) {
						maxTime = String.valueOf(retMap.get("maxTime"));
					}
					try {
						timeMill = DateUtil.getMillSecond(maxTime);
					} catch (Exception e) {
						timeMill = 0;
					}
					repeatPidJifenMap.put(ConvertUtil.toInt(pId), timeMill);
				}
				orgRepeatTimeMap.put(jifen, repeatPidJifenMap);
			}
		}
		// System.out.println(orgRepeatTimeMap);

		// 把组织好的map按时间排序
		HashMap<Integer, Map<Integer, Long>> orgRepeatTimeNewMap = new HashMap<>();
		for (Entry<Integer, Map<Integer, Long>> orgRepeatTimeEntry : orgRepeatTimeMap.entrySet()) {
			int jifen = orgRepeatTimeEntry.getKey();
			Map<Integer, Long> pidTimeMap = orgRepeatTimeEntry.getValue();
			Map<Integer, Long> sortedTimeMap = CollectionUtil.sortByValueUp(pidTimeMap);
			orgRepeatTimeNewMap.put(jifen, sortedTimeMap);
		}
		// System.out.println(orgRepeatTimeNewMap);

		// 重新组织结果map
		String jifenRecord = "";
		Map<Integer, Integer> resultMap = new LinkedHashMap<>();
		for (Entry<Integer, Integer> entry : newSorted20Map.entrySet()) {
			int pId = entry.getKey();
			int jifen = entry.getValue();

			if (!StringUtil.contains(jifenRecord, jifen + "", ";")) {
				if (orgRepeatTimeNewMap.containsKey(jifen)) {
					Map<Integer, Long> timeNewMap = orgRepeatTimeNewMap.get(jifen);
					for (Entry<Integer, Long> entryTimeNewEntry : timeNewMap.entrySet()) {
						resultMap.put(entryTimeNewEntry.getKey(), jifen);
					}
				} else {
					resultMap.put(pId, jifen);
				}
			}
		}

		// System.out.println(resultMap);

		// 2.积分排行[发送前20名的] 排名序号|玩家名|积分/
		StringBuilder jifenRankBd = new StringBuilder();
		int jifenRankIndex = 0;
		// for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
		for (Entry<Integer, Integer> entry : resultMap.entrySet()) {
			jifenRankIndex++;
			if (jifenRankIndex > DictMapUtil.getSysConfigIntValue(StaticSysConfig.strogerHeroRankNum)) {
				break;
			}
			String playerName = ParamConfig.playerNameMap.get(entry.getKey());
			jifenRankBd.append(jifenRankIndex).append("|").append(playerName == null ? "" : playerName).append("|").append(entry.getValue()).append("/");
		}
		if (jifenRankBd.toString().length() > 0) {
			retMsgData.putStringItem("2", StringUtil.noContainLastString(jifenRankBd.toString()));
		} else {
			retMsgData.putStringItem("2", "");
		}

		// 3当前积分
		int jifen = 0;
		if (sortedMap.containsKey(instPlayerId)) {
			jifen = sortedMap.get(instPlayerId);
		}
		retMsgData.putIntItem("3", jifen);

		// 4当前排行
		if (resultMap.containsKey(instPlayerId)) {
			int ownRank = 0;
			int indexRank = 0;
			int isInRank = 0;
			for (Entry<Integer, Integer> entry : resultMap.entrySet()) {
				indexRank++;
				if (entry.getKey() == instPlayerId) {
					isInRank = 1;
					break;
				}
			}
			if (isInRank == 1) {
				ownRank = indexRank;
			}
			retMsgData.putIntItem("4", ownRank);
		} else {
			int ownRank = 0;
			int indexRank = 0;
			int isInRank = 0;
			for (Entry<Integer, Integer> entry : sortedMap.entrySet()) {
				indexRank++;
				if (entry.getKey() == instPlayerId) {
					isInRank = 1;
					break;
				}
			}
			if (isInRank == 1) {
				ownRank = indexRank;
			}
			retMsgData.putIntItem("4", ownRank);
		}
		
		retMsgData.putStringItem("firstNames", ParamConfig.strogherHeroNumOnes);

		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 获取最强英雄排行奖励
	 * 
	 * @author mp
	 * @date 2015-8-19 上午10:47:33
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getStrogerHeroRankReward(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 1积分奖励{Dict_Activity_StrogerHero} [字段间用|隔开, 行与行之间用/隔开] id|rewardTimePoint|rank|rewards/
		MessageData retMsgData = new MessageData();
		StringBuilder rankRewardBd = new StringBuilder();

		List<DictActivityStrogerHero> activityStrogerHeroList = DictList.dictActivityStrogerHeroList;
		for (DictActivityStrogerHero obj : activityStrogerHeroList) {
			rankRewardBd.append(obj.getId()).append("|").append(obj.getRewardTimePoint()).append("|").append(obj.getRank()).append("|").append(obj.getRewards()).append("/");
		}
		String rankReward = "";
		if (rankRewardBd.toString().length() > 0) {
			rankReward = StringUtil.noContainLastString(rankRewardBd.toString());
		}
		retMsgData.putStringItem("1", rankReward);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 刷新限时兑换列表
	 * 
	 * @author 李天喜
	 * @date 2015-8-15 上午09:40:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void refreshExchange(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.LimitTimeExchange + "");
		if (dictActivity == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		long curr = System.currentTimeMillis();
		if (curr < DateUtil.string2MillSecond(dictActivity.getStartTime()) || curr > DateUtil.string2MillSecond(dictActivity.getEndTime())) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		List<DictactivityExchange> activityList = DictList.dictactivityExchangeList;
		PlayerActivities pas = PlayerActivityManager.getInstance().getPlayerActivities(instPlayerId);
		JSONObject json = pas.getPlayerActivityData(StaticActivity.LimitTimeExchange).getActivityDatas();
		StringBuilder sb = new StringBuilder();
		// 组装限时兑换列表数据：1 1/2 开始时间 结束时间 2_3_4;2_4_1 3_2_1|
		if (activityList != null) {
			DictactivityExchange activity = null;
			int index = 0;
			for (int ai = 0; ai < activityList.size(); ai++) {
				activity = activityList.get(ai);
				if (index > 0) {
					sb.append("|");
				}
				index++;
				sb.append(activity.getId());

				sb.append(" ");
				if (json.containsKey(activity.getId() + "")) {
					JSONObject ad = json.getJSONObject(activity.getId() + "");
					sb.append(activity.getCountLimit() - ad.getInt("remain"));
				} else {
					sb.append(activity.getCountLimit());
				}
				sb.append("/").append(activity.getCountLimit());

				sb.append(" ").append(DateUtil.string2MillSecond(dictActivity.getStartTime()));
				sb.append(" ").append(DateUtil.string2MillSecond(dictActivity.getEndTime()));
				sb.append(" ").append(activity.getCostItem());
				sb.append(" ").append(activity.getGetItem());
			}
		}
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", sb.toString());
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 刷新累计消耗列表
	 * 
	 * @author 李天喜
	 * @date 2015-8-15 上午09:40:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void refreshTotalCost(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		TotalCostManager.getInstance().checkTotalCost(checkInstPlayerId);
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.SaveConsume + "");
		if (dictActivity == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		long curr = System.currentTimeMillis();
		if (curr < DateUtil.string2MillSecond(dictActivity.getStartTime()) || curr > DateUtil.string2MillSecond(dictActivity.getEndTime())) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		int instPlayerId = getInstPlayerId(channelId);
		InstTotalCost cost = TotalCostManager.getInstance().getInstTotalCost(instPlayerId);
		if (cost == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		MessageData retMsgData = new MessageData();
		// 获取参数
		List<DictactivityTotalCost> activityList = DictList.dictactivityTotalCostList;

		StringBuilder sb = new StringBuilder();
		// 组装累计消耗列表数据：开始时间 结束时间 100 50 2_3_4;2_4_1 状态|100 2_3_4;2_4_1 3_2_1 状态|
		if (activityList != null) {
			DictactivityTotalCost activity = null;
			// 状态：0不可领，1可领，2已领,3过期
			retMsgData.putStringItem("start", dictActivity.getStartTime());
			retMsgData.putStringItem("stop", dictActivity.getEndTime());
			retMsgData.putStringItem("cost", cost.getCostCount() + "");
			int[] array = StringUtil.string2IntArray(cost.getAmountState(), '_');
			Map<Integer, Integer> states = new HashMap<Integer, Integer>();
			for (int a = 0; a < array.length / 2; a++) {
				states.put(array[0 + a * 2], array[1 + a * 2]);
			}
			for (int ai = 0; ai < activityList.size(); ai++) {
				activity = activityList.get(ai);
				if (ai > 0) {
					sb.append("|");
				}
				sb.append(activity.getId());
				sb.append(" ").append(activity.getYuanbao());
				sb.append(" ").append(activity.getAwards());
				int state = 0;
				Integer stateValue = states.get(activity.getId());
				if (stateValue != null) {
					if (stateValue != 2) {
						if (cost.getCostCount() >= activity.getYuanbao()) {
							state = 1;
						} else {
							state = 0;
						}
					} else {
						state = 2;
					}
				} else {
					if (cost.getCostCount() >= activity.getYuanbao()) {
						state = 1;
					} else {
						state = 0;
					}
				}
				sb.append(" ").append(state);
			}
			// }

			retMsgData.putStringItem("data", sb.toString());
		}
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 兑换道具
	 * 
	 * @author 李天喜
	 * @date 2015-8-15 上午15:40:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void requestExchange(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.LimitTimeExchange + "");
		if (dictActivity == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		long curr = System.currentTimeMillis();
		if (curr < DateUtil.string2MillSecond(dictActivity.getStartTime()) || curr > DateUtil.string2MillSecond(dictActivity.getEndTime())) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		DictactivityExchange activity = DictMap.dictactivityExchangeMap.get(msgMap.get("id") + "");
		if (activity != null) {
			PlayerActivities pas = PlayerActivityManager.getInstance().getPlayerActivities(instPlayerId);
			JSONObject json = pas.getPlayerActivityData(StaticActivity.LimitTimeExchange).getActivityDatas();
			long currTime = System.currentTimeMillis();
			if (DateUtil.string2MillSecond(dictActivity.getStartTime()) > currTime || DateUtil.string2MillSecond(dictActivity.getEndTime()) < currTime) {
				MessageUtil.sendFailMsg(channelId, msgMap, "已过期");
			} else {
				JSONObject ad = null;
				if (json.containsKey(activity.getId() + "")) {
					ad = json.getJSONObject(activity.getId() + "");
				} else {
					ad = new JSONObject();
					ad.put("remain", "0");
					ad.put("stop", dictActivity.getEndTime());
					json.put(activity.getId() + "", ad);
				}
				// 消耗检查并发放奖励
				if (ad.getInt("remain") < activity.getCountLimit()) {
					InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
					if (ThingUtil.costThingOrProperty(msgMap, channelId, instPlayer, StringUtil.split(activity.getCostItem(), ";"))) {
						MessageData retMsgData = new MessageData();
						ad.put("stop", dictActivity.getEndTime());
						ad.put("last", DateUtil.getCurrTime());
						ad.put("remain", ad.getInt("remain") + 1);

						String thing[] = activity.getGetItem().split(";");
						for (String str : thing) {
							ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), retMsgData, msgMap);
						}
						json.put(activity.getId() + "", ad);
						MessageUtil.sendSyncMsg(channelId, retMsgData);
						pas.getPlayerActivityData(StaticActivity.LimitTimeExchange).checkDataAndUpdate();
					}
				} else {
					MessageUtil.sendFailMsg(channelId, msgMap, "兑换次数已经达到上限");
				}
			}
		} else {
			MessageUtil.sendFailMsg(channelId, msgMap, "请稍后再试");
		}

	}

	/**
	 * 获取全民福利列表
	 * 
	 * @author mp
	 * @date 2015-8-15 下午7:45:05
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getAllPeapleWeal(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);

		// 已购买人数 福利物品列表{不同物品用;分开,最后一个下划线后边是本条记录的领取状态[0-未领取 1-已领取]}
		StringBuilder sb = new StringBuilder();
		sb.append(ParamConfig.sumBuyFundNum + "").append(" ");

		// 组织福利物品列表
		String things = "";
		String haveGetRewardList = "";

		// 组织物品和对应的领取状态
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '" + StaticBigTable.allPeapleWeal + "'", 0);
		if (instPlayerBigTableList.size() > 0) {
			haveGetRewardList = instPlayerBigTableList.get(0).getValue1();
		}
		for (DictActivityAllPeapleWeal obj : DictList.dictActivityAllPeapleWealList) {
			String isGet = "0";
			if (StringUtil.contains(haveGetRewardList, obj.getId() + "", ";")) {
				isGet = "1";
			}
			things += obj.getId() + "|" + obj.getThing() + "|" + obj.getBuyerNum() + "|" + isGet + ";";
		}
		things = StringUtil.noContainLastString(things);

		sb.append(things);

		// 返回消息
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", sb.toString());
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 领取累计消耗奖励
	 * 
	 * @author 李天喜
	 * @date 2015-8-15 上午15:40:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getTotalCostAward(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		TotalCostManager.getInstance().checkTotalCost(checkInstPlayerId);
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.SaveConsume + "");
		if (dictActivity == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		// 获取参数
		long currTime = System.currentTimeMillis();
		if (currTime < DateUtil.string2MillSecond(dictActivity.getStartTime())) {
			MessageUtil.sendFailMsg(channelId, msgMap, "未开启");
			return;
		}
		if (currTime > DateUtil.string2MillSecond(dictActivity.getEndTime())) {
			MessageUtil.sendFailMsg(channelId, msgMap, "已过期");
			return;
		}
		int instPlayerId = getInstPlayerId(channelId);
		InstTotalCost cost = TotalCostManager.getInstance().getInstTotalCost(instPlayerId);
		if (cost == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		DictactivityTotalCost activity = DictMap.dictactivityTotalCostMap.get(msgMap.get("id") + "");
		// 检查并发放奖励
		if (activity != null) {
			int[] array = StringUtil.string2IntArray(cost.getAmountState(), '_');
			Map<Integer, Integer> states = new HashMap<Integer, Integer>();
			for (int a = 0; a < array.length / 2; a++) {
				states.put(array[0 + a * 2], array[1 + a * 2]);
			}

			// PlayerActivities pas = PlayerActivityManager.getInstance().getPlayerActivities(instPlayerId);
			// JSONObject json = pas.getPlayerActivityData(StaticActivity.SaveConsume).getActivityDatas();
			if (cost.getCostCount() < activity.getYuanbao()) {
				MessageUtil.sendFailMsg(channelId, msgMap, "累计消耗未达到" + activity.getYuanbao() + "元宝");
				return;
			}
			Integer stateValue = states.get(activity.getId());
			if (stateValue != null) {
				if (stateValue == 2) {
					MessageUtil.sendFailMsg(channelId, msgMap, "每日只能领取一次");
					return;
				}
			}
			MessageData retMsgData = new MessageData();
			String thing[] = activity.getAwards().split(";");
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			for (String str : thing) {
				ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), retMsgData, msgMap);
			}
			states.put(activity.getId(), 2);
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
			MessageUtil.sendSyncMsg(channelId, retMsgData);
			MessageUtil.sendSuccMsg(channelId, msgMap);
			DALFactory.getInstTotalCostDAL().update(cost, instPlayerId);
			// /////////////////////////////////////////////////////////////

			// JSONObject ad = null;
			// if (json.containsKey(activity.getId() + "")) {
			// ad = json.getJSONObject(activity.getId() + "");
			// } else {
			// ad = new JSONObject();
			// ad.put("state", 1);
			// }
			// // 发放奖励
			// if (!ad.containsKey("state") || ad.getInt("state") != 2) {
			// MessageData retMsgData = new MessageData();
			// String thing[] = activity.getAwards().split(";");
			// InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			// for (String str : thing) {
			// ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(str.split("_")[0]), ConvertUtil.toInt(str.split("_")[1]), ConvertUtil.toInt(str.split("_")[2]), retMsgData, msgMap);
			// }
			// ad.put("state", 2);
			// json.put(activity.getId() + "", ad);
			// MessageUtil.sendSyncMsg(channelId, retMsgData);
			// MessageUtil.sendSuccMsg(channelId, msgMap);
			// pas.getPlayerActivityData(StaticActivity.SaveConsume).checkDataAndUpdate();
			// } else {
			// MessageUtil.sendFailMsg(channelId, msgMap, "每日只能领取一次");
			// }
		} else {
			MessageUtil.sendFailMsg(channelId, msgMap, "请稍后再试");
		}
	}

	/**
	 * 幸运转轮
	 * 
	 * @author 李天喜
	 * @date 2015-8-15 上午15:40:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void activityLuck(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		ThreadManager.execute(new ActivityLuckRun(msgMap, channelId));
	}

	/**
	 * 进入月卡界面
	 * 
	 * @author mp
	 * @date 2015-8-23 下午2:10:15
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void intoMonthCard(Map<String, Object> msgMap, String channelId) throws Exception {

		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}

		int instPlayerId = getInstPlayerId(channelId);
		String things = DictMap.dictSysConfigStrMap.get(StaticSysConfigStr.extraMonthCardThing + "").getValue();
		int getState = 1;// 0-未领取 1-已领取
		int isBuyMonthCard = 0; // 0-未购买 , 1-已购买月卡

		// 判断此玩家有月卡，并且在月卡有效期内,如果都满足,才判断是否已领
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard + " and useNum = 1", instPlayerId);
		if (instActivityList.size() > 0) {
			InstActivity instActivity = instActivityList.get(0);
			if (DateUtil.getCurrMill() <= DateUtil.getMillSecond(instActivity.getActivityTime())) {
				// 判断是否已领取
				List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '" + StaticBigTable.monthCard + "'", 0);
				if (instPlayerBigTableList.size() > 0) {
					InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
					if(instPlayerBigTable.getValue1()==null||instPlayerBigTable.getValue1().length()<=0){
						getState = 0;
					}else if (DateUtil.getMillSecond(instPlayerBigTable.getValue1()) > DateUtil.getMillSecond(instActivity.getActivityTime())) {
						getState = 0;
					}
				} else {
					getState = 0;
				}
			}
		}

		if (instActivityList.size() > 0) {
			InstActivity instActivity = instActivityList.get(0);
			if (DateUtil.getCurrMill() <= DateUtil.getMillSecond(instActivity.getActivityTime())) {
				isBuyMonthCard = 1;
			}
		}

		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("1", things);// 赠送的物品
		retMsgData.putIntItem("2", getState);// 0-未领取 1-已领取
		retMsgData.putIntItem("3", isBuyMonthCard);// 0-未购买 , 1-已购买月卡

		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	/**
	 * 领取月卡额外赠送物品
	 * 
	 * @author mp
	 * @date 2015-8-23 下午2:10:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getMonthCardThing(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		// 获取参数
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

		String things = DictMap.dictSysConfigStrMap.get(StaticSysConfigStr.extraMonthCardThing + "").getValue();
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId + " and activityId = " + StaticActivity.monthCard + " and  useNum = 1", instPlayerId);

		// 验证是否购买月卡
		if (instActivityList.size() <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noMonthCard);
			return;
		}

		// 验证月卡是否已过期
		if (instActivityList.size() > 0) {
			InstActivity instActivity = instActivityList.get(0);
			if (DateUtil.getCurrMill() > DateUtil.getMillSecond(instActivity.getActivityTime())) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_noMonthCardPastDule);
				return;
			}
		}

		// 验证是否已领过
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '" + StaticBigTable.monthCard + "'", 0);
		if (instPlayerBigTableList.size() > 0) {
			InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
			if (instPlayerBigTable.getValue1()!=null&&instPlayerBigTable.getValue1().length()>0&&DateUtil.getMillSecond(instPlayerBigTable.getValue1()) > DateUtil.getMillSecond(instActivityList.get(0).getActivityTime())) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_monthCardHaveGetThing);
				return;
			}
		}

		// 给物品
		MessageData syncMsgData = new MessageData();
		ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(things.split("_")[0]), ConvertUtil.toInt(things.split("_")[1]), ConvertUtil.toInt(things.split("_")[2]), syncMsgData, msgMap);

		// 做领取记录
		if (instPlayerBigTableList.size() > 0) {
			InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
			instPlayerBigTable.setValue1(DateUtil.getCurrTime());
			getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
		} else {
			InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
			instPlayerBigTable.setInstPlayerId(instPlayerId);
			instPlayerBigTable.setProperties(StaticBigTable.monthCard);
			instPlayerBigTable.setValue1(DateUtil.getCurrTime());
			getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
		}

		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 招财进宝
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void treasuresFillTheHome(Map<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		//验证活动开放时间
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.MakeMoney + "");
		if (dictActivity == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "");
			return;
		}
		long currTime = System.currentTimeMillis();
		if (currTime < DateUtil.string2MillSecond(dictActivity.getStartTime())) {
			MessageUtil.sendFailMsg(channelId, msgMap, "未开启");
			return;
		}
		if (currTime > DateUtil.string2MillSecond(dictActivity.getEndTime())) {
			MessageUtil.sendFailMsg(channelId, msgMap, "已过期");
			return;
		}
		int type = (int) msgMap.get("type");// 类型，0进入1抽奖
		MessageData retMsgData = new MessageData();
		MessageData syncMsgData = new MessageData();
		InstActivityTreasures instActivityTre=null;
		List<InstActivityTreasures> instActivityTreList = getInstActivityTreasuresDAL().getList("instPlayerId="+instPlayerId, 0);
		if(instActivityTreList!=null&&instActivityTreList.size()>0){
			instActivityTre=instActivityTreList.get(0);
		}
		int id=1;
		int rank=1;
		int cost = 0;
		int max = 0;
		int lastGold = 0;
		List<DictActivityTreasures> tempList = new ArrayList<DictActivityTreasures>();
		if(type==0){//进入界面
			if(instActivityTre!=null){
				id = instActivityTre.getTreasuresId();
				rank = DictMap.dictActivityTreasuresMap.get(id+"").getRank();
//				tempList = getDictActivityTreasuresDAL().getList("rank="+(rank+1), 0);
				
				List<DictActivityTreasures> activityTreasureList = DictList.dictActivityTreasuresList;
				for (DictActivityTreasures obj : activityTreasureList) {
					if (obj.getRank() == (rank+1)) {
						tempList.add(obj);
					}
				}
				
				lastGold = DictMap.dictActivityTreasuresMap.get(id+"").getObtain();
				if(tempList!=null&&tempList.size()>0){
					cost = tempList.get(0).getCost();
					max = tempList.get(tempList.size()-1).getObtain();
				}
			}else{
//				tempList = getDictActivityTreasuresDAL().getList("rank="+rank, 0);
				List<DictActivityTreasures> activityTreasureList = DictList.dictActivityTreasuresList;
				for (DictActivityTreasures obj : activityTreasureList) {
					if (obj.getRank() == rank) {
						tempList.add(obj);
					}
				}
				cost = tempList.get(0).getCost();
				max = tempList.get(tempList.size()-1).getObtain();
			}
			retMsgData.putIntItem("lastGold",lastGold);
			retMsgData.putIntItem("cost", cost);
			retMsgData.putIntItem("max",max);
		}else if(type==1){//招财
			if(instActivityTre!=null){
				rank = DictMap.dictActivityTreasuresMap.get(instActivityTre.getTreasuresId()+"").getRank();
//				tempList = getDictActivityTreasuresDAL().getList("rank="+(rank+1), 0);
				List<DictActivityTreasures> activityTreasureList = DictList.dictActivityTreasuresList;
				for (DictActivityTreasures obj : activityTreasureList) {
					if (obj.getRank() == (rank+1)) {
						tempList.add(obj);
					}
				}
			}else{
//				tempList = getDictActivityTreasuresDAL().getList("rank="+rank, 0);
				List<DictActivityTreasures> activityTreasureList = DictList.dictActivityTreasuresList;
				for (DictActivityTreasures obj : activityTreasureList) {
					if (obj.getRank() == rank) {
						tempList.add(obj);
					}
				}
			}
			//验证是否还有可用次数
			if(tempList==null||tempList.size()<=0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_treasuresMax);
				return;
			}
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			// 验证元宝是否足够
			if (instPlayer.getGold() < tempList.get(0).getCost()) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
				return;
			}
			//根据权重随机获得元宝数
			int weightTotal = 0;
			int obtain = 0;
			Map<Integer,Integer> weightMap=new HashMap<Integer,Integer>();
			Map<Integer,Integer> obtainMap=new HashMap<Integer,Integer>();
			for(int i=0;i<tempList.size();i++){
				if(tempList.get(i).getWeight()==0){
					continue;
				}
				if(i==0){
					weightMap.put(0, tempList.get(i).getWeight());
				}else{
					weightMap.put(weightTotal, weightTotal+tempList.get(i).getWeight());
				}
				obtainMap.put(weightTotal+tempList.get(i).getWeight(),tempList.get(i).getObtain());
				weightTotal+=tempList.get(i).getWeight();
			}
			Random rand = new Random();
			rand.setSeed(new Date().getTime());
			int randomWeight = rand.nextInt(weightTotal);//生成随机权重
			for(Integer key : weightMap.keySet()){
				if(randomWeight>=key&&randomWeight<weightMap.get(key)){
					obtain = obtainMap.get(weightMap.get(key));
				}
			}
			cost = tempList.get(0).getCost();
			if(obtain-cost>0){
				// 领取元宝
				PlayerUtil.goldStatics(instPlayer, GoldStaticsType.add, obtain-cost, msgMap);
				getInstPlayerDAL().update(instPlayer, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
				if(instActivityTre==null){
					instActivityTre = new InstActivityTreasures();
					instActivityTre.setInstPlayerId(instPlayerId);
					
					int tId = 0;
					for (DictActivityTreasures obj : DictList.dictActivityTreasuresList) {
						if (obj.getObtain() == obtain) {
							tId = obj.getId();
							break;
						}
					}
					instActivityTre.setTreasuresId(tId);
					getInstActivityTreasuresDAL().add(instActivityTre, instPlayerId);
				}else{
					int tId = 0;
					for (DictActivityTreasures obj : DictList.dictActivityTreasuresList) {
						if (obj.getObtain() == obtain) {
							tId = obj.getId();
							break;
						}
					}
					instActivityTre.setTreasuresId(tId);
//					instActivityTre.setTreasuresId(getDictActivityTreasuresDAL().getList("obtain="+obtain, 0).get(0).getId());
					getInstActivityTreasuresDAL().update(instActivityTre, instPlayerId);
				}
//				tempList = getDictActivityTreasuresDAL().getList("rank="+(tempList.get(0).getRank()+1), 0);
				
				List<DictActivityTreasures> newTempList = new ArrayList<>();
				List<DictActivityTreasures> activityTreasureList = DictList.dictActivityTreasuresList;
				for (DictActivityTreasures obj : activityTreasureList) {
					if (obj.getRank() == (tempList.get(0).getRank()+1)) {
//						tempList.add(obj);
						newTempList.add(obj);
					}
				}
				if(newTempList!=null&&newTempList.size()>0){
					cost = newTempList.get(0).getCost();
					max = newTempList.get(newTempList.size()-1).getObtain();
				}
				retMsgData.putIntItem("lastGold",obtain);
				retMsgData.putIntItem("cost", cost);
				retMsgData.putIntItem("max",max);
				retMsgData.putIntItem("obtainGold", obtain);
			}else{
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_treasuresError);
				return;
			}
		}
		List<InstActivityTreasures> msgList = getInstActivityTreasuresDAL().getList("1=1 order by updateTime desc limit 4", 0);
		StringBuilder sb = new StringBuilder();
		//"name|gole;name|gole;name|gole;name|gole;"
		for(InstActivityTreasures obj:msgList){
			sb.append(getInstPlayerDAL().getModel(obj.getInstPlayerId(), obj.getInstPlayerId()).getName())
			.append("|")
//			.append(getDictActivityTreasuresDAL().getModel(obj.getTreasuresId(), 0).getObtain())
			.append(DictMap.dictActivityTreasuresMap.get(obj.getTreasuresId() + "").getObtain())
			.append(";");
		}
		retMsgData.putStringItem("message",sb.toString());
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap,retMsgData);
	}
	
	/**
	 * 丹塔处理器
	 * 
	 * @author 李天喜
	 * @date 2015-9-17 上午15:40:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dantaHandler(Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		ThreadManager.execute(new DantaHandlerRun(msgMap, channelId));
	}

	/**
	 * 进入限时活动
	 * @author mp
	 * @date 2015-12-9 上午11:01:26
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void intoLimitActivity (Map<String, Object> msgMap, String channelId) throws Exception {
		int checkInstPlayerId = getInstPlayerId(channelId);// 玩家实例Id
		if (checkInstPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		MessageData retMsgData = new MessageData();
		
		int strogerHeroHoliday = 0;
		if (ActivityUtil.isInHolidayActivity(StaticActivityHoliday.strogerHeroHoliday)) {
			strogerHeroHoliday = 1;
		}
		retMsgData.putIntItem("1", strogerHeroHoliday);////当天是否为巅峰强者节日版  0-不是   1-是
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

}
