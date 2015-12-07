package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 卡牌处理入口
 * @author hzw
 * @date 2014-6-23上午10:23:06
 */
public class CardEnt extends BaseHandler{

	/**
	 * 吃卡
	 * @author hzw
	 * @date 2014-6-26下午2:01:42
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void deleteCard(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().deleteCard(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 卡牌魂魄召唤
	 * @author mp
	 * @date 2014-7-3 下午5:04:29
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void cardSoulCall(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().cardSoulCall(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 卡牌修炼
	 * @author hzw
	 * @date 2014-7-8上午11:22:03
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void train(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().train(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 接受卡牌修炼
	 * @author hzw
	 * @date 2014-7-10下午2:50:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void trainAccept(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().trainAccept(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	/*
	*//**
	 * 突破
	 * @author hzw
	 * @date 2014-7-11下午4:11:20
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 *//*
	public void breakThrough(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().breakThrough(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}*/
	
	/**
	 * 获取招募信息
	 * @author mp
	 * @date 2014-8-14 上午11:27:23
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getRecruitInfo(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().getRecruitInfo(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 卡牌招募
	 * @author mp
	 * @date 2014-8-14 上午11:25:58
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void cardRecruit(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().cardRecruit(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 卡牌进阶
	 * @author mp
	 * @date 2014-9-1 下午5:11:27
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void cardAdvance(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().cardAdvance(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 出售卡牌
	 * @author hzw
	 * @date 2014-9-2上午10:18:27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void sellCards(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().sellCards(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 吃丹
	 * @author hzw
	 * @date 2014-9-8下午4:35:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void eatPill(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().eatPill(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 锁卡
	 * @author hzw
	 * @date 2014-9-22下午3:05:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void lockCard(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().lockCard(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 轮回预览
	 * @author hzw
	 * @date 2014-9-22下午3:07:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void restoreCardView(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().restoreCardView(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 轮回
	 * @author hzw
	 * @date 2014-9-22下午3:07:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void restoreCard(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().restoreCard(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 境界
	 * @author hzw
	 * @date 2015-7-1下午3:15:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void realm(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getCardHandler().realm(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	

}
