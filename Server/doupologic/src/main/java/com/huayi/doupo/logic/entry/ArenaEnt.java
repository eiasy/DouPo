package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class ArenaEnt extends BaseHandler{
	
	/**
	 * 初始竞技场
	 * @author hzw
	 * @date 2014-10-29下午4:29:21
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void arena(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getArenaHandler().arena(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 竞技场排行（前十名）
	 * @author hzw
	 * @date 2014-10-29下午4:32:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void arenaList(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getArenaHandler().arenaList(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	
	/**
	 * 挑战
	 * @author hzw
	 * @date 2014-10-29下午5:48:27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void arenaWar(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getArenaHandler().arenaWar(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 抢夺胜利/失败
	 * @author hzw
	 * @date 2014-10-28上午11:32:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void arenaWarWin(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getArenaHandler().arenaWarWin(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 清除冷却时间
	 * @author hzw
	 * @date 2014-10-29下午2:23:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void buyArenaTime(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getArenaHandler().buyArenaTime(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 购买挑战次数
	 * @author hzw
	 * @date 2014-10-29下午2:48:21
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void buyArenaNum(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getArenaHandler().buyArenaNum(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 竞技场兑换
	 * @author hzw
	 * @date 2014-10-30上午10:56:07
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void arenaConvert(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getArenaHandler().arenaConvert(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_data);
		}
	}
	
	
	/**
	 * 获取玩家玩家战斗相关数据
	 * @author hzw
	 * @date 2014-11-5下午5:42:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void enemyPlayerInfo(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getArenaHandler().enemyPlayerInfo(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_data);
		}
	}
	

}
