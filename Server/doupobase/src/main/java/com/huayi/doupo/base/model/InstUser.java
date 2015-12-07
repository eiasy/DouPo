package com.huayi.doupo.base.model;

import java.io.*;

/**
	用户实例表
*/
@SuppressWarnings("serial")
public class InstUser implements Serializable
{
	private int index;
	public String result = "";
	/**
		编号
	*/
	private int id;
	public int getId(){
		return id;
	}
	public void setId(int id) {
		this.id = id;
		index = 1;
		result += index + "*int*" + id + "#";
	}

	public void setId(int id, int bs) {
		this.id = id;
	}

	/**
		账号Id
	*/
	private String openId;
	public String getOpenId(){
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
		index = 2;
		result += index + "*String*" + openId + "#";
	}

	public void setOpenId(String openId, int bs) {
		this.openId = openId;
	}

	/**
		 状态: 0-离线, 1-在线  2-冻结 3-禁言
	*/
	private int onlineState;
	public int getOnlineState(){
		return onlineState;
	}
	public void setOnlineState(int onlineState) {
		this.onlineState = onlineState;
		index = 3;
		result += index + "*int*" + onlineState + "#";
	}

	public void setOnlineState(int onlineState, int bs) {
		this.onlineState = onlineState;
	}

	/**
		注册日期
	*/
	private String firstLoginDate;
	public String getFirstLoginDate(){
		return firstLoginDate;
	}
	public void setFirstLoginDate(String firstLoginDate) {
		this.firstLoginDate = firstLoginDate;
		index = 4;
		result += index + "*String*" + firstLoginDate + "#";
	}

	public void setFirstLoginDate(String firstLoginDate, int bs) {
		this.firstLoginDate = firstLoginDate;
	}

	/**
		首次登录时间 玩家注册时间
	*/
	private String firstLoginTime;
	public String getFirstLoginTime(){
		return firstLoginTime;
	}
	public void setFirstLoginTime(String firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
		index = 5;
		result += index + "*String*" + firstLoginTime + "#";
	}

	public void setFirstLoginTime(String firstLoginTime, int bs) {
		this.firstLoginTime = firstLoginTime;
	}

	/**
		登录总次数
	*/
	private int loginTotalTimes;
	public int getLoginTotalTimes(){
		return loginTotalTimes;
	}
	public void setLoginTotalTimes(int loginTotalTimes) {
		this.loginTotalTimes = loginTotalTimes;
		index = 6;
		result += index + "*int*" + loginTotalTimes + "#";
	}

	public void setLoginTotalTimes(int loginTotalTimes, int bs) {
		this.loginTotalTimes = loginTotalTimes;
	}

	/**
		在线总时间 单位-毫秒
	*/
	private String onlineTotalTime;
	public String getOnlineTotalTime(){
		return onlineTotalTime;
	}
	public void setOnlineTotalTime(String onlineTotalTime) {
		this.onlineTotalTime = onlineTotalTime;
		index = 7;
		result += index + "*String*" + onlineTotalTime + "#";
	}

	public void setOnlineTotalTime(String onlineTotalTime, int bs) {
		this.onlineTotalTime = onlineTotalTime;
	}

	/**
		最近一次登录日期
	*/
	private String lastLoginDate;
	public String getLastLoginDate(){
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
		index = 8;
		result += index + "*String*" + lastLoginDate + "#";
	}

	public void setLastLoginDate(String lastLoginDate, int bs) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
		最近一次登录时间 存的为用户的登录时间
	*/
	private String lastLoginTime;
	public String getLastLoginTime(){
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		index = 9;
		result += index + "*String*" + lastLoginTime + "#";
	}

	public void setLastLoginTime(String lastLoginTime, int bs) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
		最近一次离开日期
	*/
	private String lastLeaveDate;
	public String getLastLeaveDate(){
		return lastLeaveDate;
	}
	public void setLastLeaveDate(String lastLeaveDate) {
		this.lastLeaveDate = lastLeaveDate;
		index = 10;
		result += index + "*String*" + lastLeaveDate + "#";
	}

	public void setLastLeaveDate(String lastLeaveDate, int bs) {
		this.lastLeaveDate = lastLeaveDate;
	}

	/**
		最近一次离开时间
	*/
	private String lastLeaveTime;
	public String getLastLeaveTime(){
		return lastLeaveTime;
	}
	public void setLastLeaveTime(String lastLeaveTime) {
		this.lastLeaveTime = lastLeaveTime;
		index = 11;
		result += index + "*String*" + lastLeaveTime + "#";
	}

	public void setLastLeaveTime(String lastLeaveTime, int bs) {
		this.lastLeaveTime = lastLeaveTime;
	}

	/**
		渠道
	*/
	private String channel;
	public String getChannel(){
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
		index = 12;
		result += index + "*String*" + channel + "#";
	}

	public void setChannel(String channel, int bs) {
		this.channel = channel;
	}

	/**
		冻结时间
	*/
	private String frozenTime;
	public String getFrozenTime(){
		return frozenTime;
	}
	public void setFrozenTime(String frozenTime) {
		this.frozenTime = frozenTime;
		index = 13;
		result += index + "*String*" + frozenTime + "#";
	}

	public void setFrozenTime(String frozenTime, int bs) {
		this.frozenTime = frozenTime;
	}

	/**
		
	*/
	private int serverId;
	public int getServerId(){
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
		index = 14;
		result += index + "*int*" + serverId + "#";
	}

	public void setServerId(int serverId, int bs) {
		this.serverId = serverId;
	}

	/**
		账号服务器  账号Id
	*/
	private String accountId;
	public String getAccountId(){
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
		index = 15;
		result += index + "*String*" + accountId + "#";
	}

	public void setAccountId(String accountId, int bs) {
		this.accountId = accountId;
	}

	/**
		版本号
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 16;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstUser clone(){
		InstUser extend=new InstUser();
		extend.setId(this.id);
		extend.setOpenId(this.openId);
		extend.setOnlineState(this.onlineState);
		extend.setFirstLoginDate(this.firstLoginDate);
		extend.setFirstLoginTime(this.firstLoginTime);
		extend.setLoginTotalTimes(this.loginTotalTimes);
		extend.setOnlineTotalTime(this.onlineTotalTime);
		extend.setLastLoginDate(this.lastLoginDate);
		extend.setLastLoginTime(this.lastLoginTime);
		extend.setLastLeaveDate(this.lastLeaveDate);
		extend.setLastLeaveTime(this.lastLeaveTime);
		extend.setChannel(this.channel);
		extend.setFrozenTime(this.frozenTime);
		extend.setServerId(this.serverId);
		extend.setAccountId(this.accountId);
		extend.setVersion(this.version);
		return extend;
	}
}
