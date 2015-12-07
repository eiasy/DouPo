package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家充值实例表
*/
@SuppressWarnings("serial")
public class InstPlayerRecharge implements Serializable
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
		玩家实例Id
	*/
	private int instPlayerId;
	public int getInstPlayerId(){
		return instPlayerId;
	}
	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
		index = 2;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
	}

	/**
		账号
	*/
	private String openId;
	public String getOpenId(){
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
		index = 3;
		result += index + "*String*" + openId + "#";
	}

	public void setOpenId(String openId, int bs) {
		this.openId = openId;
	}

	/**
		玩家名字
	*/
	private String playerName;
	public String getPlayerName(){
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		index = 4;
		result += index + "*String*" + playerName + "#";
	}

	public void setPlayerName(String playerName, int bs) {
		this.playerName = playerName;
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
		index = 5;
		result += index + "*String*" + channel + "#";
	}

	public void setChannel(String channel, int bs) {
		this.channel = channel;
	}

	/**
		本次充值人民币
	*/
	private int thisrmb;
	public int getThisrmb(){
		return thisrmb;
	}
	public void setThisrmb(int thisrmb) {
		this.thisrmb = thisrmb;
		index = 6;
		result += index + "*int*" + thisrmb + "#";
	}

	public void setThisrmb(int thisrmb, int bs) {
		this.thisrmb = thisrmb;
	}

	/**
		本次充值游戏币金额
	*/
	private int thisamt;
	public int getThisamt(){
		return thisamt;
	}
	public void setThisamt(int thisamt) {
		this.thisamt = thisamt;
		index = 7;
		result += index + "*int*" + thisamt + "#";
	}

	public void setThisamt(int thisamt, int bs) {
		this.thisamt = thisamt;
	}

	/**
		累计充值游戏币金额
	*/
	private int saveamt;
	public int getSaveamt(){
		return saveamt;
	}
	public void setSaveamt(int saveamt) {
		this.saveamt = saveamt;
		index = 8;
		result += index + "*int*" + saveamt + "#";
	}

	public void setSaveamt(int saveamt, int bs) {
		this.saveamt = saveamt;
	}

	/**
		支付渠道 0=个帐渠道,1=财付通,2=银行卡快捷支付,3=没介绍,4=Q卡渠道,5=手机充值卡渠道,6=话费渠道,7=元宝渠道,8=微信支付渠道,
	*/
	private int payChannel;
	public int getPayChannel(){
		return payChannel;
	}
	public void setPayChannel(int payChannel) {
		this.payChannel = payChannel;
		index = 9;
		result += index + "*int*" + payChannel + "#";
	}

	public void setPayChannel(int payChannel, int bs) {
		this.payChannel = payChannel;
	}

	/**
		冗余字段-腾讯返回的：游戏币个数（包含了赠送游戏币）
	*/
	private int balance;
	public int getBalance(){
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
		index = 10;
		result += index + "*int*" + balance + "#";
	}

	public void setBalance(int balance, int bs) {
		this.balance = balance;
	}

	/**
		冗余字段-腾讯返回的：赠送游戏币个数
	*/
	private int genbalance;
	public int getGenbalance(){
		return genbalance;
	}
	public void setGenbalance(int genbalance) {
		this.genbalance = genbalance;
		index = 11;
		result += index + "*int*" + genbalance + "#";
	}

	public void setGenbalance(int genbalance, int bs) {
		this.genbalance = genbalance;
	}

	/**
		冗余字段-回调接口返回的,下单成功时购买的数量(游戏币)
	*/
	private int saveNum;
	public int getSaveNum(){
		return saveNum;
	}
	public void setSaveNum(int saveNum) {
		this.saveNum = saveNum;
		index = 12;
		result += index + "*int*" + saveNum + "#";
	}

	public void setSaveNum(int saveNum, int bs) {
		this.saveNum = saveNum;
	}

	/**
		来源
	*/
	private String source;
	public String getSource(){
		return source;
	}
	public void setSource(String source) {
		this.source = source;
		index = 13;
		result += index + "*String*" + source + "#";
	}

	public void setSource(String source, int bs) {
		this.source = source;
	}

	/**
		充值服务器生成的订单号
	*/
	private String orderId;
	public String getOrderId(){
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
		index = 14;
		result += index + "*String*" + orderId + "#";
	}

	public void setOrderId(String orderId, int bs) {
		this.orderId = orderId;
	}

	/**
		服务器编号
	*/
	private int serverId;
	public int getServerId(){
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
		index = 15;
		result += index + "*int*" + serverId + "#";
	}

	public void setServerId(int serverId, int bs) {
		this.serverId = serverId;
	}

	/**
		账号服务器生成的id
	*/
	private String accountId;
	public String getAccountId(){
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
		index = 16;
		result += index + "*String*" + accountId + "#";
	}

	public void setAccountId(String accountId, int bs) {
		this.accountId = accountId;
	}

	/**
		渠道生成的订单号
	*/
	private String orderform;
	public String getOrderform(){
		return orderform;
	}
	public void setOrderform(String orderform) {
		this.orderform = orderform;
		index = 17;
		result += index + "*String*" + orderform + "#";
	}

	public void setOrderform(String orderform, int bs) {
		this.orderform = orderform;
	}

	/**
		角色等级
	*/
	private int roleLevel;
	public int getRoleLevel(){
		return roleLevel;
	}
	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
		index = 18;
		result += index + "*int*" + roleLevel + "#";
	}

	public void setRoleLevel(int roleLevel, int bs) {
		this.roleLevel = roleLevel;
	}

	/**
		道具编号-充值id
	*/
	private int goodsId;
	public int getGoodsId(){
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
		index = 19;
		result += index + "*int*" + goodsId + "#";
	}

	public void setGoodsId(int goodsId, int bs) {
		this.goodsId = goodsId;
	}

	/**
		充值类型（1：成功，2：GM补点，3：充值失败
	*/
	private int ctype;
	public int getCtype(){
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
		index = 20;
		result += index + "*int*" + ctype + "#";
	}

	public void setCtype(int ctype, int bs) {
		this.ctype = ctype;
	}

	/**
		充值服务器返回的本次充值金额
	*/
	private int money;
	public int getMoney(){
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
		index = 21;
		result += index + "*int*" + money + "#";
	}

	public void setMoney(int money, int bs) {
		this.money = money;
	}

	/**
		充值记录编号
	*/
	private String rechargeRecordId;
	public String getRechargeRecordId(){
		return rechargeRecordId;
	}
	public void setRechargeRecordId(String rechargeRecordId) {
		this.rechargeRecordId = rechargeRecordId;
		index = 22;
		result += index + "*String*" + rechargeRecordId + "#";
	}

	public void setRechargeRecordId(String rechargeRecordId, int bs) {
		this.rechargeRecordId = rechargeRecordId;
	}

	/**
		操作日期
	*/
	private String operateDate;
	public String getOperateDate(){
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
		index = 23;
		result += index + "*String*" + operateDate + "#";
	}

	public void setOperateDate(String operateDate, int bs) {
		this.operateDate = operateDate;
	}

	/**
		操作时间
	*/
	private String operateTime;
	public String getOperateTime(){
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
		index = 24;
		result += index + "*String*" + operateTime + "#";
	}

	public void setOperateTime(String operateTime, int bs) {
		this.operateTime = operateTime;
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
		index = 25;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerRecharge clone(){
		InstPlayerRecharge extend=new InstPlayerRecharge();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setOpenId(this.openId);
		extend.setPlayerName(this.playerName);
		extend.setChannel(this.channel);
		extend.setThisrmb(this.thisrmb);
		extend.setThisamt(this.thisamt);
		extend.setSaveamt(this.saveamt);
		extend.setPayChannel(this.payChannel);
		extend.setBalance(this.balance);
		extend.setGenbalance(this.genbalance);
		extend.setSaveNum(this.saveNum);
		extend.setSource(this.source);
		extend.setOrderId(this.orderId);
		extend.setServerId(this.serverId);
		extend.setAccountId(this.accountId);
		extend.setOrderform(this.orderform);
		extend.setRoleLevel(this.roleLevel);
		extend.setGoodsId(this.goodsId);
		extend.setCtype(this.ctype);
		extend.setMoney(this.money);
		extend.setRechargeRecordId(this.rechargeRecordId);
		extend.setOperateDate(this.operateDate);
		extend.setOperateTime(this.operateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
