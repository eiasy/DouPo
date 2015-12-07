package com.huayi.doupo.logic.handler.util;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstUser;
import com.huayi.doupo.base.model.socket.statics.StaticConfig;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;


/**
 * 用户帮助类
 * @author mp
 * @date 2013-10-11 下午1:29:17
 */
public class UserUtil extends DALFactory{
	
	/**
	 * 初始化用户实例
	 * @author mp
	 * @date 2013-10-11 下午1:29:39
	 * @param openId
	 * @Description
	 */
	public static InstUser initUser(String openId, String channel, String serverId, String accountId) throws Exception{
		InstUser instUser = new InstUser();
		String currTime = DateUtil.getCurrTime();
		String currYmdDay = DateUtil.getYmdDate();
		instUser.setFirstLoginDate(currYmdDay);
		instUser.setFirstLoginTime(currTime);
		instUser.setLastLeaveDate("");
		instUser.setLastLeaveTime("");
		instUser.setLastLoginDate(currYmdDay);
		instUser.setLastLoginTime(currTime);
		instUser.setLoginTotalTimes(1);
		instUser.setOnlineState(StaticConfig.ONLINE);
		instUser.setOnlineTotalTime("0");
		instUser.setOpenId(openId);
		instUser.setChannel(channel);
		if (serverId != null && !serverId.equals("")) {
			instUser.setServerId(Integer.valueOf(serverId));
		}
		instUser.setAccountId(accountId);
		return getInstUserDAL().add(instUser, 0);
	}
	
	/**
	 * 下次登录时更新用户账号信息
	 * @author mp
	 * @date 2013-10-11 下午5:46:29
	 * @param instUser
	 * @throws Exception
	 * @Description
	 */
	public static void updateUserInfo(InstUser instUser) throws Exception{
		instUser.setLastLoginDate(DateUtil.getYmdDate());
		instUser.setLastLoginTime(DateUtil.getCurrTime());
		instUser.setLoginTotalTimes(instUser.getLoginTotalTimes()+1);
		if (instUser.getOnlineState() == StaticConfig.UNLINE) {
			instUser.setOnlineState(StaticConfig.ONLINE);
		}
		getInstUserDAL().update(instUser, 0);
	}
	
	/**
	 * 退出时更新用户数据
	 * @author mp
	 * @date 2013-10-14 上午10:28:09
	 * @param openId
	 * @throws Exception
	 * @Description
	 */
	public static void exitUpdate(String openId) throws Exception{
		InstUser instUser = getInstUserDAL().getList("openId='"+openId+"'", 0).get(0);
		if (instUser.getOnlineState() == StaticConfig.ONLINE) {
			instUser.setOnlineState(StaticConfig.UNLINE);
		}
		instUser.setLastLeaveDate(DateUtil.getYmdDate());
		instUser.setLastLeaveTime(DateUtil.getCurrTime());
		instUser.setOnlineTotalTime((ConvertUtil.toLong(instUser.getOnlineTotalTime()) + DateUtil.getMillSecond(instUser.getLastLeaveTime()) - DateUtil.getMillSecond(instUser.getLastLoginTime()) + ""));
		getInstUserDAL().update(instUser, 0);
	}
}
