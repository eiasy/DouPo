package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家充值回调实例表
*/
@SuppressWarnings("serial")
public class InstPlayerRechargeCallBack implements Serializable
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
		玩家Id
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
		返回码 -2=说明游戏初始化绑定service不成功,-1=支付流程失败,0=支付流程成功,2=用户取消
	*/
	private int resultCode;
	public int getResultCode(){
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
		index = 5;
		result += index + "*int*" + resultCode + "#";
	}

	public void setResultCode(int resultCode, int bs) {
		this.resultCode = resultCode;
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
		index = 6;
		result += index + "*int*" + payChannel + "#";
	}

	public void setPayChannel(int payChannel, int bs) {
		this.payChannel = payChannel;
	}

	/**
		支付状态 -1=未知 0=支付成功,1=用户取消,2=支付出错
	*/
	private int payState;
	public int getPayState(){
		return payState;
	}
	public void setPayState(int payState) {
		this.payState = payState;
		index = 7;
		result += index + "*int*" + payState + "#";
	}

	public void setPayState(int payState, int bs) {
		this.payState = payState;
	}

	/**
		发货状态 -1=无法知道是否发货成功，如：财付通、手机充值卡渠道,0=发货成功
	*/
	private int provideState;
	public int getProvideState(){
		return provideState;
	}
	public void setProvideState(int provideState) {
		this.provideState = provideState;
		index = 8;
		result += index + "*int*" + provideState + "#";
	}

	public void setProvideState(int provideState, int bs) {
		this.provideState = provideState;
	}

	/**
		下单成功时购买的数量(游戏币)
	*/
	private int saveNum;
	public int getSaveNum(){
		return saveNum;
	}
	public void setSaveNum(int saveNum) {
		this.saveNum = saveNum;
		index = 9;
		result += index + "*int*" + saveNum + "#";
	}

	public void setSaveNum(int saveNum, int bs) {
		this.saveNum = saveNum;
	}

	/**
		返回信息
	*/
	private String resultMsg;
	public String getResultMsg(){
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
		index = 10;
		result += index + "*String*" + resultMsg + "#";
	}

	public void setResultMsg(String resultMsg, int bs) {
		this.resultMsg = resultMsg;
	}

	/**
		扩展信息
	*/
	private String extendInfo;
	public String getExtendInfo(){
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
		index = 11;
		result += index + "*String*" + extendInfo + "#";
	}

	public void setExtendInfo(String extendInfo, int bs) {
		this.extendInfo = extendInfo;
	}

	/**
		操作日期
	*/
	private String operDate;
	public String getOperDate(){
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
		index = 12;
		result += index + "*String*" + operDate + "#";
	}

	public void setOperDate(String operDate, int bs) {
		this.operDate = operDate;
	}

	/**
		操作时间
	*/
	private String operTime;
	public String getOperTime(){
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
		index = 13;
		result += index + "*String*" + operTime + "#";
	}

	public void setOperTime(String operTime, int bs) {
		this.operTime = operTime;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 14;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerRechargeCallBack clone(){
		InstPlayerRechargeCallBack extend=new InstPlayerRechargeCallBack();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setOpenId(this.openId);
		extend.setPlayerName(this.playerName);
		extend.setResultCode(this.resultCode);
		extend.setPayChannel(this.payChannel);
		extend.setPayState(this.payState);
		extend.setProvideState(this.provideState);
		extend.setSaveNum(this.saveNum);
		extend.setResultMsg(this.resultMsg);
		extend.setExtendInfo(this.extendInfo);
		extend.setOperDate(this.operDate);
		extend.setOperTime(this.operTime);
		extend.setVersion(this.version);
		return extend;
	}
}
