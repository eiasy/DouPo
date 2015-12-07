package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class ActivityEnt extends BaseHandler{
	
	/**
	 * 玩家拍卖行/黑角域倒计时时间到
	 * @author hzw
	 * @date 2014-9-27下午4:36:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void updateAuctionOrHjy(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().updateAuctionOrHjy(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 兑换商品
	 * @author hzw
	 * @date 2014-9-29上午10:39:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void convertGoods(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().convertGoods(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 拍卖场-白金贵宾
	 * @author hzw
	 * @date 2014-9-29上午11:29:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void platinumVIP(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().platinumVIP(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 在线奖励领奖
	 * @author hzw
	 * @date 2014-10-14下午4:54:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void onlineRewards(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().onlineRewards(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 初始化开服礼包物品
	 * @author hzw
	 * @date 2014-10-16上午10:50:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void openServiceBagView(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().openServiceBagView(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 开服礼包领取
	 * @author hzw
	 * @date 2014-10-16上午10:51:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void openServiceBag(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().openServiceBag(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 领取等级礼包
	 * @author hzw
	 * @date 2014-10-18下午3:52:05
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void levelBag(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().levelBag(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 签到
	 * @author hzw
	 * @date 2014-10-20下午4:28:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void signIn(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().signIn(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 签到-领取双倍
	 * @author hzw
	 * @date 2014-10-23上午11:34:17
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void twoSignIn(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().twoSignIn(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 领奖中心领奖
	 * @author hzw
	 * @date 2014-10-22上午11:33:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void award(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().award(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 领奖中心-全部领取
	 * @author hzw
	 * @date 2014-10-22上午11:35:03
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void aKeyAward(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().aKeyAward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 兑换码领奖
	 * @author hzw
	 * @date 2015-4-9下午3:58:23
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void cDKeyAward(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().cDKeyAward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 限时抢购购买
	 * @author hzw
	 * @date 2015-5-5下午1:57:17
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void flashSale(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().flashSale(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 购买开服基金
	 * @author mp
	 * @date 2015-5-4 下午4:39:42
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void buyFund (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().buyFund(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 领取开服基金
	 * @author mp
	 * @date 2015-5-4 下午4:39:52
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getFund (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getFund(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 领取累计充值物品
	 * @author mp
	 * @date 2015-5-5 下午2:03:32
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getAddRecargeThings (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getAddRecargeThings(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 月卡每日领取
	 * @author hzw
	 * @date 2015-5-7上午11:22:12
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void getMonthCard (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getMonthCard(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 签到初始化数据
	 * @author hzw
	 * @date 2015-5-11下午3:37:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void initSignIn (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().initSignIn(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 排行榜
	 * @author mp
	 * @date 2015-5-18 下午4:50:08
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void ranking (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().ranking(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 下发整点抢物品
	 * @author hzw
	 * @date 2015-6-4下午3:24:58
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void grabTheHour (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().grabTheHour(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 整点抢购买物品
	 * @author hzw
	 * @date 2015-6-4下午3:24:58
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void grabTheHourBuy (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().grabTheHourBuy(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 下发特卖会物品
	 * @author hzw
	 * @date 2015-6-4下午3:24:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void privateSale (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().privateSale(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 特卖会购买物品
	 * @author hzw
	 * @date 2015-6-4下午3:38:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void privateSaleBuy (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().privateSaleBuy(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 获取每日特惠列表
	 * @author mp
	 * @date 2015-6-3 下午5:02:03
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getDailyDeals (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getDailyDeals(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 每日特惠
	 * @author mp
	 * @date 2015-6-3 下午4:55:46
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void dailyDeals (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().dailyDeals(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取限时抢购列表
	 * @author mp
	 * @date 2015-6-3 下午5:02:29
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getLimitShopping (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getLimitShopping(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 限时抢购
	 * @author mp
	 * @date 2015-6-3 下午4:56:02
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void limitShopping (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().limitShopping(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 星星兑换
	 * @author hzw
	 * @date 2015-6-10下午4:42:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void starStoreBuy (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().starStoreBuy(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 月卡贵族下发数据
	 * @author hzw
	 * @date 2015-6-11上午11:39:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void monthCardStore (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().monthCardStore(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 月卡贵族购买
	 * @author hzw
	 * @date 2015-6-11上午11:39:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void monthCardStoreBuy (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().monthCardStoreBuy(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取lv专卖
	 * @author mp
	 * @date 2015-6-10 上午11:18:52
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getLvSell (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getLvSell(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * lv专卖购买
	 * @author mp
	 * @date 2015-6-10 上午11:18:56
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void lvSellBuy (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().lvSellBuy(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取vip专卖
	 * @author mp
	 * @date 2015-6-10 上午11:19:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getVipSell (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getVipSell(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 刷新限时兑换列表
	 * @author 李天喜
	 * @date 2015-8-15 上午09:38:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void refreshExchange (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().refreshExchange(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 刷新累计消耗列表
	 * @author 李天喜
	 * @date 2015-8-15 上午16:50:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void refreshTotalCost (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().refreshTotalCost(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 兑换道具
	 * @author 李天喜
	 * @date 2015-8-15 上午15:38:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void requestExchange (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().requestExchange(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 领取累计消耗奖励
	 * @author 李天喜
	 * @date 2015-8-15 上午15:38:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getTotalCostAward (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getTotalCostAward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 幸运转轮
	 * @author 李天喜
	 * @date 2015-8-20 上午11:31:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void activityLuck (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().activityLuck(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * vip专卖购买
	 * @author mp
	 * @date 2015-6-10 上午11:19:04
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void vipSellBuy (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().vipSellBuy(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 试练日领奖
	 * @author hzw
	 * @date 2015-6-17下午4:35:12
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void tryToPractice (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().tryToPractice(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 试练日购买
	 * @author hzw
	 * @date 2015-8-3下午6:10:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void tryToPracticeBuy (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().tryToPracticeBuy(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 试练日副本
	 * @author hzw
	 * @date 2015-6-17下午4:35:12
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void tryToPracticeBarrier (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().tryToPracticeBarrier(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 试练日领大奖
	 * @author hzw
	 * @date 2015-8-8下午2:51:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void tryToPracticeAward (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().tryToPracticeAward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取全民福利列表
	 * @author mp
	 * @date 2015-8-15 下午7:42:55
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getAllPeapleWeal (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getAllPeapleWeal(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 全民福利领奖
	 * @author mp
	 * @date 2015-8-15 下午7:43:48
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void allPeapleWealReward (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().allPeapleWealReward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 进入限时英雄界面
	 * @author mp
	 * @date 2015-8-17 上午10:28:18
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void intoLimitTimeHero (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().intoLimitTimeHero(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取限时英雄积分奖励
	 * @author mp
	 * @date 2015-8-18 下午2:12:04
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getJifenRewardList (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getJifenRewardList(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 进入最强英雄界面
	 * @author mp
	 * @date 2015-8-19 上午10:46:22
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void intoStrogerHero (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().intoStrogerHero(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取最强英雄排行奖励
	 * @author mp
	 * @date 2015-8-19 上午10:46:29
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getStrogerHeroRankReward (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getStrogerHeroRankReward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 进入月卡界面
	 * @author mp
	 * @date 2015-8-23 下午2:02:10
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void intoMonthCard (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().intoMonthCard(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 领取月卡额外赠送物品
	 * @author mp
	 * @date 2015-8-23 下午2:02:14
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getMonthCardThing (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().getMonthCardThing(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 财源广进
	 * @param msgMap
	 * @param channelId
	 */
	public void treasuresFillTheHome (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().treasuresFillTheHome(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 丹塔处理器
	 * @param msgMap
	 * @param channelId
	 */
	public void dantaHandler (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getActivityHandler().dantaHandler(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
}
