package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家商店购买实例表.只存储商店中的道具和vip礼包。道具保留今日和昨日数据，礼包永久保存。礼包存储时，bagType设置成0.
*/
@SuppressWarnings("serial")
public class InstPlayerGmReward implements Serializable
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
		发奖时间
	*/
	private String sendRewardTime;
	public String getSendRewardTime(){
		return sendRewardTime;
	}
	public void setSendRewardTime(String sendRewardTime) {
		this.sendRewardTime = sendRewardTime;
		index = 3;
		result += index + "*String*" + sendRewardTime + "#";
	}

	public void setSendRewardTime(String sendRewardTime, int bs) {
		this.sendRewardTime = sendRewardTime;
	}

	/**
		得奖时间
	*/
	private String getRewardTime;
	public String getGetRewardTime(){
		return getRewardTime;
	}
	public void setGetRewardTime(String getRewardTime) {
		this.getRewardTime = getRewardTime;
		index = 4;
		result += index + "*String*" + getRewardTime + "#";
	}

	public void setGetRewardTime(String getRewardTime, int bs) {
		this.getRewardTime = getRewardTime;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 5;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerGmReward clone(){
		InstPlayerGmReward extend=new InstPlayerGmReward();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setSendRewardTime(this.sendRewardTime);
		extend.setGetRewardTime(this.getRewardTime);
		extend.setVersion(this.version);
		return extend;
	}
}
