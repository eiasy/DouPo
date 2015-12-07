package com.huayi.doupo.logic.entry;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 抢夺处理入口
 * @author hzw
 * @date 2014-9-3下午5:41:11
 */
public class LootEnt extends BaseHandler{
	
	/**
	 * 下发抢夺玩家/NPC列表
	 * @author hzw
	 * @date 2014-9-4上午11:16:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void lootPlayer(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getLootHandler().lootPlayer(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 和平镇
	 * @author hzw
	 * @date 2014-9-4下午5:45:42
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void lootPeace(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getLootHandler().lootPeace(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 抢夺
	 * @author hzw
	 * @date 2014-9-5上午11:50:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void lootWar(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getLootHandler().lootWar(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 一键抢夺
	 * @author gaolizheng
	 * @param msgMap
	 * @param channelId
	 */
	public void aKeyLootWar(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getLootHandler().aKeyLootWar(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 战斗胜利
	 * @author hzw
	 * @date 2014-9-5下午5:16:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	private int lootWarWinIndex = 0;
	public void lootWarWin(HashMap<String, Object> msgMap, String channelId) throws Exception{
		try {
			HandlerFactory.getLootHandler().lootWarWin(msgMap, channelId);
		} catch (Exception e) {
			lootWarWinIndex++;
			if (lootWarWinIndex >= 5) {
				LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
				e.printStackTrace();
				MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
				return;
			}
			TimeUnit.MILLISECONDS.sleep(5);
			lootWarWin(msgMap, channelId);
		}
	}
	
	/**
	 * 抢夺拼合
	 * @author hzw
	 * @date 2014-9-6下午2:42:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	private int lootPieceIndex = 0;
	public void lootPiece(HashMap<String, Object> msgMap, String channelId) throws Exception{
		try {
			HandlerFactory.getLootHandler().lootPiece(msgMap, channelId);
		} catch (Exception e) {
			lootPieceIndex++;
			if (lootPieceIndex >= 5) {
				LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
				e.printStackTrace();
				MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
				return;
			}
			TimeUnit.MILLISECONDS.sleep(5);
			lootPiece(msgMap, channelId);
		}
	}

}
