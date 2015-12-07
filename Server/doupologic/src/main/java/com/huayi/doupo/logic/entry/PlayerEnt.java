package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import org.junit.Test;

import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class PlayerEnt extends BaseHandler{
	
	public void testGuid(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().testGuid(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	public void testGiveName(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().testGiveName(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	public void testFight(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().testFight(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	public void testOther(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().testOther(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	public void login2(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().login2(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	public void login1(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().login1(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 玩家登录
	 * @author mp
	 * @date 2013-9-24 上午11:57:08
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void login(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().login(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 初始化卡牌
	 * @author hzw
	 * @date 2014-6-19下午2:07:15
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void choiceCard(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().choiceCard(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 卡牌换位
	 * @author hzw
	 * @date 2014-6-24上午9:30:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void convertPosition(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().convertPosition(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 更换卡牌
	 * @author hzw
	 * @date 2014-6-24下午3:23:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void convertCard(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().convertCard(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 卡牌上阵
	 * @author mp
	 * @date 2014-6-24 下午4:07:01
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void cardInTeam(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().cardInTeam(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 小伙伴上阵
	 * @author mp
	 * @date 2014-7-3 上午11:33:23
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void cardInPartner(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().cardInPartner(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 小伙伴下阵
	 * @author mp
	 * @date 2014-7-3 上午11:36:49
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void cardOutPartner(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().cardOutPartner(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 聊天
	 * @author mp
	 * @date 2014-8-28 上午11:46:38
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void chat(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().chat(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 体力恢复
	 * @author mp
	 * @date 2014-9-19 上午10:29:55
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void energyRecover(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().energyRecover(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 耐力恢复
	 * @author mp
	 * @date 2015-2-7 下午7:14:00
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void vigorRecover(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().vigorRecover(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 快速升级（战队）
	 * @author hzw
	 * @date 2014-9-29下午3:11:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void quickUpgrade(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().quickUpgrade(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 引导步骤
	 * @author mp
	 * @date 2014-10-25 下午3:27:47
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void guidStep(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().guidStep(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 断线重连
	 * @author mp
	 * @date 2014-11-5 上午9:30:37
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void relink(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().relink(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 起名字
	 * @author mp
	 * @date 2014-11-7 上午10:10:51
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void giveName(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().giveName(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 洗澡恢复体力
	 * @author mp
	 * @date 2014-12-18 下午2:51:47
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void wash(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().wash(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 每日任务领奖
	 * @author mp
	 * @date 2014-12-27 下午5:26:31
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void dailyTashReward(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().dailyTashReward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 玩家选头像
	 * @author mp
	 * @date 2015-1-15 下午5:22:54
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void selectHeader(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().selectHeader(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 成就领奖
	 * @author hzw
	 * @date 2015-1-21下午2:18:05
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void achievementReward(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().achievementReward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 随机获取名字
	 * @author mp
	 * @date 2015-1-22 下午2:22:58
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void randomName(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().randomName(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 领取vip礼包
	 * @author mp
	 * @date 2015-1-28 下午3:25:31
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getVipGiftBag(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().getVipGiftBag(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 充值回调
	 * @author mp
	 * @date 2015-3-25 下午4:39:38
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void rechargeCallBack(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().rechargeCallBack(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 查看当前累计充值元宝
	 * @author mp
	 * @date 2015-3-26 上午10:23:47
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void lookSaveAmt(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().lookSaveAmt(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 领取首充大礼包
	 * @author mp
	 * @date 2015-3-26 下午2:48:18
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getFirstRechargeGift(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPlayerHandler().getFirstRechargeGift(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取玩家的战力值
	 * @author hzw
	 * @date 2015-5-29上午10:55:42
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void fightValue(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().fightValue(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 用于测试ios充值
	 * @author hzw
	 * @date 2015-6-20下午4:23:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void rechargeTest(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().rechargeTest(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取充值订单（号）
	 * @author mp
	 * @date 2015-7-13 上午9:31:01
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getOrder(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().getOrder(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 发起邮件
	 * @author mp
	 * @date 2015-7-28 上午10:47:25
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void sendMail(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().sendMail(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 打开聊天窗口
	 * @author mp
	 * @date 2015-7-30 下午4:00:45
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void openChatWindow(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().openChatWindow(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 关闭聊天窗口
	 * @author mp
	 * @date 2015-7-30 下午4:00:53
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void closeChatWindow(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().closeChatWindow(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取系统时间
	 * @author mp
	 * @date 2015-8-23 下午10:44:13
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getSysTime(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getPlayerHandler().getSysTime(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	@Test
	public void test(){
		try {
			SpringUtil.getSpringContext();
			DictData.dictData(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
