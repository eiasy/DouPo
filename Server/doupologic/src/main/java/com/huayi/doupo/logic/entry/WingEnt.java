package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 神羽处理入口
 * @author mp
 * @date 2015-11-11 下午2:07:40
 */
public class WingEnt extends BaseHandler{
	
	/**
	 * 翅膀激活
	 * @author mp
	 * @date 2015-11-12 上午10:04:03
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void wingActivity(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWingHandler().wingActivity(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 翅膀佩戴/更换
	 * @author mp
	 * @date 2015-11-12 上午10:04:19
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void wingPutOnOrExchanger(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWingHandler().wingPutOnOrExchanger(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 翅膀卸下
	 * @author mp
	 * @date 2015-11-12 上午10:04:48
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void wingPutOff(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWingHandler().wingPutOff(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 翅膀转换
	 * @author mp
	 * @date 2015-11-12 上午10:05:32
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void wingConvert(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWingHandler().wingConvert(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 翅膀进阶
	 * @author mp
	 * @date 2015-11-12 上午10:05:50
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void wingAdvance(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWingHandler().wingAdvance(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 翅膀强化
	 * @author mp
	 * @date 2015-11-12 上午10:06:08
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void wingStronger(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWingHandler().wingStronger(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 翅膀出售
	 * @author mp
	 * @date 2015-11-12 上午10:06:35
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void wingSell(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWingHandler().wingSell(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 翅膀一键强化
	 * @author mp
	 * @date 2015-11-17 下午1:59:53
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void wingOneKeyStronger(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getWingHandler().wingOneKeyStronger(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
}
