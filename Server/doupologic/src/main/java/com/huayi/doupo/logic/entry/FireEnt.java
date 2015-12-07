package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 异火处理入口
 * @author hzw
 * @date 2014-7-23下午4:52:27
 */
public class FireEnt extends BaseHandler{
	
	/**
	 * 开启/蜕变异火技能
	 * @author hzw
	 * @date 2014-7-23下午5:04:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void changeFireSkill(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFireHandler().changeFireSkill(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 保留异火斗技
	 * @author hzw
	 * @date 2014-7-24下午3:33:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void trainFireSkill(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFireHandler().trainFireSkill(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 异火抓取
	 * @author hzw
	 * @date 2014-7-25上午11:57:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void fireGain(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFireHandler().fireGain(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 异火升级
	 * @author hzw
	 * @date 2014-7-28上午10:24:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void fireUpgrade(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFireHandler().fireUpgrade(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 异火传承
	 * @author hzw
	 * @date 2014-7-28下午2:54:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void fireInherit(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFireHandler().fireInherit(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 异火上阵
	 * @author hzw
	 * @date 2014-7-28下午5:19:38
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void fireUse(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFireHandler().fireUse(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 出售
	 * @author hzw
	 * @date 2014-7-28下午5:24:35
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void fireSell(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getFireHandler().fireSell(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
}
