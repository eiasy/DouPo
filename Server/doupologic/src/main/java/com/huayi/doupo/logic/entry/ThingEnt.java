package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 物品处理类
 * @author mp
 * @date 2014-8-4 上午9:53:05
 */
public class ThingEnt extends BaseHandler{
	
	/**
	 * 分解-预览分解结果
	 * @author mp
	 * @date 2014-7-28 上午11:25:27
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void preViewResolve(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().preViewResolve(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 确认分解
	 * @author mp
	 * @date 2014-7-28 上午11:26:40
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void makeSureResolve(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().makeSureResolve(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 物品出售
	 * @author mp
	 * @date 2014-8-4 上午10:04:05
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void sell(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().sell(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 获取商城数据
	 * @author mp
	 * @date 2014-8-5 上午10:51:38
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void getStoreData(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().getStoreData(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 商城购买
	 * @author mp
	 * @date 2014-8-4 上午10:06:13
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void buy(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().buy(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 背包扩容
	 * @author mp
	 * @date 2014-8-6 下午2:14:03
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void bagExpand(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().bagExpand(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 装备-背包中宝石升级
	 * @author mp
	 * @date 2014-7-2 上午10:50:41
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void packGemUpgrade (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().packGemUpgrade(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 装备-装备身上宝石升级
	 * @author mp
	 * @date 2014-7-2 下午5:06:21
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void equipGemUpgrade (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().equipGemUpgrade(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 魔核转换
	 * @author mp
	 * @date 2014-8-6 下午4:58:17
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void coreConvert (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().coreConvert(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 物品使用
	 * @author mp
	 * @date 2014-8-7 上午9:41:21
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void thingUse (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().thingUse(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 开宝箱
	 * @author mp
	 * @date 2015-1-24 下午2:33:18
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void openBox (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getThingHandler().openBox(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
}
