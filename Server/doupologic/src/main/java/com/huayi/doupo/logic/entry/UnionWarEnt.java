package com.huayi.doupo.logic.entry;

import java.util.HashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

public class UnionWarEnt extends BaseHandler {

	/**
	 * 盟战报名
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void join(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getUnionWarHandler().join(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 盟战取消报名
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void disjoin(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getUnionWarHandler().disjoin(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 盟战排序
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void sort(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getUnionWarHandler().sort(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 盟战设置伏兵
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void ambush(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getUnionWarHandler().ambush(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 盟战取消设伏
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void unambush(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getUnionWarHandler().unambush(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 盟战入阵
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void fight(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getUnionWarHandler().fight(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 查看盟战录像
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void unionReplay(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getUnionWarHandler().unionReplay(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 盟战贡献
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void unionContribution(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getUnionWarHandler().unionContribution(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

	/**
	 * 盟战赛程
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void unionSchedule(HashMap<String, Object> msgMap, String channelId) {
		try {
			HandlerFactory.getUnionWarHandler().unionSchedule(msgMap, channelId);
		} catch (Exception e) {
			LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
			e.printStackTrace();
			MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
		}
	}

}
