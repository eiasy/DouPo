package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class WorldBossEnt extends BaseHandler{
	
	/**
	 * 进入世界Boss主页
	 * @author hzw
	 * @date 2015-1-5下午4:03:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void clickWorldBossBtn(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWorldBossHandler().clickWorldBossBtn(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 参战世界Boss
	 * @author hzw
	 * @date 2015-1-5下午4:04:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void joinWorldBoss(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWorldBossHandler().joinWorldBoss(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 退出世界Boss
	 * @author hzw
	 * @date 2015-1-5下午5:28:18
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void exitWorldBoss(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWorldBossHandler().exitWorldBoss(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 挑战世界Boss
	 * @author hzw
	 * @date 2015-1-6上午10:55:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void fightWorldBoss(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWorldBossHandler().fightWorldBoss(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 重生
	 * @author hzw
	 * @date 2015-1-6下午4:13:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void rebirthWorldBoss(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWorldBossHandler().rebirthWorldBoss(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 鼓舞
	 * @author hzw
	 * @date 2015-1-7下午9:55:34
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void inspireWorldBoss(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWorldBossHandler().inspireWorldBoss(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 下发世界Boss屠魔商店信息
	 * @author mp
	 * @date 2015-11-26 下午4:54:09
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void sendBossShop(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWorldBossHandler().sendBossShop(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 屠魔商店兑换
	 * @author mp
	 * @date 2015-11-26 下午4:54:19
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void exchangeBossShop(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWorldBossHandler().exchangeBossShop(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 世界boss开大宝箱
	 * @author mp
	 * @date 2015-11-30 下午14:08
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void openBossBigBox(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWorldBossHandler().openBossBigBox(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
}
