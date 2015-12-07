package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class PagodaEnt extends BaseHandler{
	
	/**
	 * 爬塔——战
	 * @author hzw
	 * @date 2014-8-12下午4:31:43
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void war(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPagodaHandler().war(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 爬塔——重置
	 * @author hzw
	 * @date 2014-8-12下午4:57:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void reset(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPagodaHandler().reset(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 爬塔——扫荡
	 * @author hzw
	 * @date 2014-8-13上午11:09:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void mop(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPagodaHandler().mop(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 爬塔——搜寻
	 * @author hzw
	 * @date 2014-8-13下午2:59:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void search(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPagodaHandler().search(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 爬塔———商店
	 * @author hzw
	 * @date 2015-7-11下午3:48:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void store(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPagodaHandler().store(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 进入丹塔
	 * @author mp
	 * @date 2015-10-27 下午5:09:04
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void intoPagoda(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPagodaHandler().intoPagoda(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 下发强力装备
	 * @author mp
	 * @date 2015-10-27 下午5:09:25
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void sendStrongEquip(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPagodaHandler().sendStrongEquip(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 点击强力装备
	 * @author mp
	 * @date 2015-10-27 下午5:09:40
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void clickStrongEquip(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPagodaHandler().clickStrongEquip(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 购买强力装备
	 * @author mp
	 * @date 2015-10-27 下午5:09:55
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void buyStrongEquip(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getPagodaHandler().buyStrongEquip(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
}
