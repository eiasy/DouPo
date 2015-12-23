package com.huayi.doupo.logic.handler;

import java.util.HashMap;
import java.util.List;

import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.unionwar.UnionWarUtil;
import com.huayi.doupo.logic.util.MessageUtil;

public class UnionWarHandler extends BaseHandler {

	/**
	 * 盟战报名
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void join(HashMap<String, Object> msgMap, String channelId) {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = 0;
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		UnionWarUtil.join(msgMap, channelId, instPlayerId, instUnionId, (int) msgMap.get("battlefieldId"));
	}

	/**
	 * 盟战取消报名
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void disjoin(HashMap<String, Object> msgMap, String channelId) {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = 0;
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		UnionWarUtil.disjoin(msgMap, channelId, instPlayerId, instUnionId, (int) msgMap.get("battlefieldId"));
	}

	/**
	 * 盟战排序
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void sort(HashMap<String, Object> msgMap, String channelId) {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = 0;
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		UnionWarUtil.sort(msgMap, channelId, instPlayerId, instUnionId, (int) msgMap.get("battlefieldId"));
	}

	/**
	 * 盟战设置伏兵
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void ambush(HashMap<String, Object> msgMap, String channelId) {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = 0;
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		UnionWarUtil.ambush(msgMap, channelId, instPlayerId, instUnionId);
	}

	/**
	 * 盟战取消设伏
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void unambush(HashMap<String, Object> msgMap, String channelId) {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = 0;
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		UnionWarUtil.unambush(msgMap, channelId, instPlayerId, instUnionId);
	}

	/**
	 * 盟战入阵
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void fight(HashMap<String, Object> msgMap, String channelId) {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = 0;
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		UnionWarUtil.fight(msgMap, channelId, instPlayerId, instUnionId, (int) msgMap.get("battlefieldId"));
	}

	/**
	 * 查看盟战录像
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void unionReplay(HashMap<String, Object> msgMap, String channelId) {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = 0;
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		UnionWarUtil.unionReplay(msgMap, channelId, instPlayerId, instUnionId);
	}

	/**
	 * 盟战贡献
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void unionContribution(HashMap<String, Object> msgMap, String channelId) {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = 0;
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		UnionWarUtil.unionContribution(msgMap, channelId, instPlayerId, instUnionId);
	}

	/**
	 * 盟战赛程
	 * sjl
	 * 
	 * @param msgMap
	 * @param channelId
	 */
	public void unionSchedule(HashMap<String, Object> msgMap, String channelId) {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int instUnionId = 0;
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_unionRefresh);
			return;
		}
		UnionWarUtil.unionSchedule(msgMap, channelId, instPlayerId, instUnionId);
	}

}
