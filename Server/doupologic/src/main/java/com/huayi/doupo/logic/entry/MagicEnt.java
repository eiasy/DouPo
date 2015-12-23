package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class MagicEnt extends BaseHandler{
	
	/**
	 * 法宝/功法 上阵
	 * @author mp
	 * @date 2014-12-8 上午10:11:35
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void putOn (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getMagicHandler().putOn(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 法宝/功法 下阵
	 * @author mp
	 * @date 2014-12-8 上午10:11:47
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void putOff (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getMagicHandler().putOff(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 强化法宝与功法
	 * @author hzw
	 * @date 2014-7-1上午10:17:17
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void strengthenMagic(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getMagicHandler().strengthenMagic(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 精炼法宝功法
	 * @author cui
	 * @date	2015/12/09
	 * @param msgMap
	 * @param channelId
	 */
	public void magicAdvance(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getMagicHandler().magicAdvance(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	

}
