package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class ChapterEnt extends BaseHandler{
	
	
	/**
	 * 副本——普通战斗
	 * @author hzw
	 * @date 2014-8-20上午10:59:54
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void commonWar(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().commonWar(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 一键战斗
	 * @author hzw
	 * @date 2014-8-21下午4:21:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void aKeyCommonWar(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().aKeyCommonWar(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 星数开箱
	 * @author hzw
	 * @date 2014-8-21下午4:21:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void chapterOpenBox(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().chapterOpenBox(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 清除连战冷却时间
	 * @author hzw
	 * @date 2014-8-22下午2:25:42
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void clearColdTime(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().clearColdTime(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 普通战斗重置战斗次数
	 * @author hzw
	 * @date 2014-8-25上午11:26:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void resetFightNum(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().resetFightNum(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 精英战斗
	 * @author hzw
	 * @date 2014-8-23上午10:43:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void eliteWar(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().eliteWar(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 精英战斗重置次数
	 * @author hzw
	 * @date 2014-8-25下午3:18:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void buyEliteFightNum(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().buyEliteFightNum(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 活动战斗
	 * @author hzw
	 * @date 2014-8-26下午2:33:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void activityWar(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().activityWar(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 活动战斗购买次数
	 * @author hzw
	 * @date 2014-8-26下午3:35:07
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void buyActivityFightNum(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().buyActivityFightNum(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 战斗失败-普通/精英/活动
	 * @author hzw
	 * @date 2014-10-2下午3:30:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void warFailed(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().warFailed(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error(e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 元宝购买体力/耐力
	 * @author hzw
	 * @date 2014-10-2下午3:34:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void goldEnergyOrVigor(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().goldEnergyOrVigor(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error(e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 普通副本开福利箱
	 * @author hzw
	 * @date 2015-3-13上午11:08:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void openWelfareBox(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().openWelfareBox(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error(e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 下发魂魄活动副本魂魄
	 * @author mp
	 * @date 2015-10-14 下午3:04:20
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void sendSoulActivityChapterSoul(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getChapterHandler().sendSoulActivityChapterSoul(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error(e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
}
