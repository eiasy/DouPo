package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家充值实例表
*/
@SuppressWarnings("serial")
public class InstPlayerRechargeFail implements Serializable
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
	private int money;
	public int getMoney(){
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
		index = 6;
		result += index + "*int*" + money + "#";
	}

	public void setMoney(int money, int bs) {
		this.money = money;
	}

	/**
		充值服务器订单号
	*/
	private String orderId;
	public String getOrderId(){
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
		index = 7;
		result += index + "*String*" + orderId + "#";
	}

	public void setOrderId(String orderId, int bs) {
		this.orderId = orderId;
	}

	/**
		账号服务器账号id
	*/
	private String accountId;
	public String getAccountId(){
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
		index = 8;
		result += index + "*String*" + accountId + "#";
	}

	public void setAccountId(String accountId, int bs) {
		this.accountId = accountId;
	}

	/**
		充值类型（1：成功，2：GM补点，3：充值失败）
	*/
	private int ctype;
	public int getCtype(){
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
		index = 9;
		result += index + "*int*" + ctype + "#";
	}

	public void setCtype(int ctype, int bs) {
		this.ctype = ctype;
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
		index = 10;
		result += index + "*String*" + orderform + "#";
	}

	public void setOrderform(String orderform, int bs) {
		this.orderform = orderform;
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
		index = 11;
		result += index + "*String*" + rechargeRecordId + "#";
	}

	public void setRechargeRecordId(String rechargeRecordId, int bs) {
		this.rechargeRecordId = rechargeRecordId;
	}

	/**
		道具编号-充值id
	*/
	private String goodsId;
	public String getGoodsId(){
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
		index = 12;
		result += index + "*String*" + goodsId + "#";
	}

	public void setGoodsId(String goodsId, int bs) {
		this.goodsId = goodsId;
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
		index = 13;
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
		index = 14;
		result += index + "*String*" + operateTime + "#";
	}

	public void setOperateTime(String operateTime, int bs) {
		this.operateTime = operateTime;
	}

	/**
		
	*/
	private String des;
	public String getDes(){
		return des;
	}
	public void setDes(String des) {
		this.des = des;
		index = 15;
		result += index + "*String*" + des + "#";
	}

	public void setDes(String des, int bs) {
		this.des = des;
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

	public InstPlayerRechargeFail clone(){
		InstPlayerRechargeFail extend=new InstPlayerRechargeFail();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setOpenId(this.openId);
		extend.setPlayerName(this.playerName);
		extend.setChannel(this.channel);
		extend.setMoney(this.money);
		extend.setOrderId(this.orderId);
		extend.setAccountId(this.accountId);
		extend.setCtype(this.ctype);
		extend.setOrderform(this.orderform);
		extend.setRechargeRecordId(this.rechargeRecordId);
		extend.setGoodsId(this.goodsId);
		extend.setOperateDate(this.operateDate);
		extend.setOperateTime(this.operateTime);
		extend.setDes(this.des);
		extend.setVersion(this.version);
		return extend;
	}
}
