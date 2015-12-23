package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家团购实例表
*/
@SuppressWarnings("serial")
public class InstPlayerGroup implements Serializable
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
		购买箱子个数
	*/
	private int buyBoxNum;
	public int getBuyBoxNum(){
		return buyBoxNum;
	}
	public void setBuyBoxNum(int buyBoxNum) {
		this.buyBoxNum = buyBoxNum;
		index = 3;
		result += index + "*int*" + buyBoxNum + "#";
	}

	public void setBuyBoxNum(int buyBoxNum, int bs) {
		this.buyBoxNum = buyBoxNum;
	}

	/**
		领奖状态 0-未领奖 1-已领奖
	*/
	private int rewardState;
	public int getRewardState(){
		return rewardState;
	}
	public void setRewardState(int rewardState) {
		this.rewardState = rewardState;
		index = 4;
		result += index + "*int*" + rewardState + "#";
	}

	public void setRewardState(int rewardState, int bs) {
		this.rewardState = rewardState;
	}

	/**
		开团购箱子个数
	*/
	private int openGroupBoxNum;
	public int getOpenGroupBoxNum(){
		return openGroupBoxNum;
	}
	public void setOpenGroupBoxNum(int openGroupBoxNum) {
		this.openGroupBoxNum = openGroupBoxNum;
		index = 5;
		result += index + "*int*" + openGroupBoxNum + "#";
	}

	public void setOpenGroupBoxNum(int openGroupBoxNum, int bs) {
		this.openGroupBoxNum = openGroupBoxNum;
	}

	/**
		给字列表（给字Id）  多个用；隔开
	*/
	private String giveZiList;
	public String getGiveZiList(){
		return giveZiList;
	}
	public void setGiveZiList(String giveZiList) {
		this.giveZiList = giveZiList;
		index = 6;
		result += index + "*String*" + giveZiList + "#";
	}

	public void setGiveZiList(String giveZiList, int bs) {
		this.giveZiList = giveZiList;
	}

	/**
		购买箱子时间
	*/
	private String buyBoxTime;
	public String getBuyBoxTime(){
		return buyBoxTime;
	}
	public void setBuyBoxTime(String buyBoxTime) {
		this.buyBoxTime = buyBoxTime;
		index = 7;
		result += index + "*String*" + buyBoxTime + "#";
	}

	public void setBuyBoxTime(String buyBoxTime, int bs) {
		this.buyBoxTime = buyBoxTime;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		添加时间
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 9;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
	}

	/**
		更新时间
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 10;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerGroup clone(){
		InstPlayerGroup extend=new InstPlayerGroup();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setBuyBoxNum(this.buyBoxNum);
		extend.setRewardState(this.rewardState);
		extend.setOpenGroupBoxNum(this.openGroupBoxNum);
		extend.setGiveZiList(this.giveZiList);
		extend.setBuyBoxTime(this.buyBoxTime);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
