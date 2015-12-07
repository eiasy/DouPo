package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 斗魂处理类入口
 * @author mp
 * @date 2015-10-17 上午10:29:52
 */
public class FightSoulEnt extends BaseHandler{
	
	/**
	 * 猎魂
	 * @author mp
	 * @date 2015-10-17 上午10:30:05
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void huntFightSoul(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().huntFightSoul(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 猎十次
	 * @author mp
	 * @date 2015-10-17 下午5:23:45
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void huntTenTimes(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().huntTenTimes(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 点亮
	 * @author mp
	 * @date 2015-10-17 下午2:50:51
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void fightSoulLight(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().fightSoulLight(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 一键出售斗魂
	 * @author mp
	 * @date 2015-10-17 下午3:10:44
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void oneKeySell(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().oneKeySell(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 出售斗魂
	 * @author mp
	 * @date 2015-10-17 下午3:27:54
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void sellFightSoul(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().sellFightSoul(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 锁定斗魂
	 * @author mp
	 * @date 2015-10-19 下午4:43:21
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void lockFightSoul(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().lockFightSoul(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 解锁斗魂
	 * @author mp
	 * @date 2015-10-19 下午4:43:35
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void unLockFightSoul(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().unLockFightSoul(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 附魂
	 * @author mp
	 * @date 2015-10-19 下午4:43:47
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void stickFightSoul(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().stickFightSoul(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 将斗魂从卡牌身上卸下
	 * @author mp
	 * @date 2015-10-19 下午5:58:11
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void dropFightSoul(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().dropFightSoul(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 斗魂吞噬（升级）
	 * @author mp
	 * @date 2015-10-20 上午10:44:57
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void fightSoulUpgrade(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().fightSoulUpgrade(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 斗魂一键吞噬（升级）
	 * @author mp
	 * @date 2015-10-20 上午10:45:11
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void fightSoulOneKeyUpgrade(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().fightSoulOneKeyUpgrade(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 猎魂一键吞噬时下发要升级的斗魂字典Id
	 * @author mp
	 * @date 2015-10-20 下午4:09:07
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void upgradeFightSoulId(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().upgradeFightSoulId(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 一键附魂
	 * @author mp
	 * @date 2015-10-21 下午1:41:06
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void oneKeyStick(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().oneKeyStick(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 一键卸下
	 * @author mp
	 * @date 2015-10-21 下午1:41:26
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void oneKeyDrop(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().oneKeyDrop(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 购买银币
	 * @author mp
	 * @date 2015-11-3 下午5:27:03
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void fightSoulBuySilver(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().fightSoulBuySilver(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 购买银币/票个数
	 * @author mp
	 * @date 2015-11-6 下午4:38:06
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getFightSoulBuySilverTimes(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFightSoulHandler().getFightSoulBuySilverTimes(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
}
