package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 装备处理入口
 * @author hzw
 * @date 2014-6-30上午10:29:09
 */
public class EquipEnt extends BaseHandler{
	
	/**
	 * 卸装备
	 * @author hzw
	 * @date 2014-6-30下午3:36:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void putOffEquip(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().putOffEquip(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 添加/更换装备
	 * @author hzw
	 * @date 2014-6-30下午3:36:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void addEquipment(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().addEquipment(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 强化装备
	 * @author hzw
	 * @date 2014-7-1上午10:17:17
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void strengthen(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().strengthen(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 一键强化
	 * @author hzw
	 * @date 2014-7-2下午3:29:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void quickStrengthen(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().quickStrengthen(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 洗练
	 * @author hzw
	 * @date 2014-7-2下午4:37:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 *//*
	public void refinement(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().refinement(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}*/
	
	/**
	 * 装备打孔
	 * @author mp
	 * @date 2014-7-2 上午10:49:18
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void openHole (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().openHole(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 装备-镶嵌宝石
	 * @author mp
	 * @date 2014-7-2 上午9:36:21
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void equipInlay (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().equipInlay(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 装备-宝石拆除
	 * @author mp
	 * @date 2014-7-2 上午10:50:01
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void takeOffGem (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().takeOffGem(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 装备拼合
	 * @author hzw
	 * @date 2014-7-4上午11:01:19
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void equipPiece (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().equipPiece(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 装备进阶
	 * @author hzw
	 * @date 2015-7-2上午11:42:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void equipAdvance (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().equipAdvance(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 使用淬炼石
	 * @author cui
	 * @date	2015/12/08
	 * @param msgMap
	 * @param channelId
	 */
	public void useAdvanceThing (HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getEquipHandler().useAdvanceThing(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

}
