package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class ManualSkillEnt extends BaseHandler{
	
	/**
	 * 手动技能上阵使用
	 * @author hzw
	 * @date 2014-9-11上午9:47:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void use(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getManualSkillHandler().use(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 更换/卸下
	 * @author hzw
	 * @date 2014-9-11上午11:26:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void convertManualSkill(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getManualSkillHandler().convertManualSkill(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	
	/**
	 * 吃技能/升级
	 * @author hzw
	 * @date 2014-9-11下午2:27:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void eatManualSkill(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getManualSkillHandler().eatManualSkill(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	
	/**
	 * 出售
	 * @author hzw
	 * @date 2014-9-15上午10:16:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public void manualSkillSell(HashMap<String, Object> msgMap, String channelId){
		try {
			HandlerFactory.getManualSkillHandler().manualSkillSell(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	

}
