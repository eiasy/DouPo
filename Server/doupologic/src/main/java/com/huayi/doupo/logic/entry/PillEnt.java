package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class PillEnt extends BaseHandler{
	
	/**
	 * 使用丹药
	 * @author hzw
	 * @date 2014-8-5上午11:31:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void usePill(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPillHandler().usePill(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 炼制丹药
	 * @author hzw
	 * @date 2014-8-5上午11:32:20
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void addPill(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPillHandler().addPill(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 炼制/一键炼制
	 * @author hzw
	 * @date 2014-8-5下午3:23:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void addPills(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPillHandler().addPills(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 出售丹药
	 * @author hzw
	 * @date 2014-8-5下午3:23:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void deletePill(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPillHandler().deletePill(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 出售丹药材料
	 * @author hzw
	 * @date 2014-8-16上午10:05:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void deletePillThing(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPillHandler().deletePillThing(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

}
