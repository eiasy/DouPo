package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 占星逻辑处理入口
 * @author mp
 * @date 2015-12-3 下午7:27:05
 */
public class HoldStarEnt extends BaseHandler{
	
	/**
	 * 进入占星
	 * @author mp
	 * @date 2015-12-4 上午9:56:23
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void intoHoldStar (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getHoldStarHandler().intoHoldStar(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 选择占星等级档
	 * @author mp
	 * @date 2015-12-4 上午9:56:47
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void selectHoldStarGrade (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getHoldStarHandler().selectHoldStarGrade(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 刷新星座
	 * @author mp
	 * @date 2015-12-4 上午9:57:08
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void refreshStarZodiac (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getHoldStarHandler().refreshStarZodiac(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 刷新占星奖励
	 * @author mp
	 * @date 2015-12-4 上午9:57:54
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void refreshStarReward (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getHoldStarHandler().refreshStarReward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 占星
	 * @author mp
	 * @date 2015-12-7 下午3:25:18
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void holdStar (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getHoldStarHandler().holdStar(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 一键领取
	 * @author mp
	 * @date 2015-12-7 下午6:41:57
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void oneKeyGet (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getHoldStarHandler().oneKeyGet(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
	/**
	 * 占星进入奖励界面
	 * @author mp
	 * @date 2015-12-18 下午6:06:17
	 * @param msgMap
	 * @param channelId
	 * @Description
	 */
	public void intoHoldStarReward (HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getHoldStarHandler().intoHoldStarReward(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}
	
}
