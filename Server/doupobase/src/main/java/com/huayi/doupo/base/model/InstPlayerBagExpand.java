package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家背包扩充实例表
*/
@SuppressWarnings("serial")
public class InstPlayerBagExpand implements Serializable
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
		背包类型
	*/
	private int bagTypeId;
	public int getBagTypeId(){
		return bagTypeId;
	}
	public void setBagTypeId(int bagTypeId) {
		this.bagTypeId = bagTypeId;
		index = 3;
		result += index + "*int*" + bagTypeId + "#";
	}

	public void setBagTypeId(int bagTypeId, int bs) {
		this.bagTypeId = bagTypeId;
	}

	/**
		扩充得到的总数量
	*/
	private int expandSumNum;
	public int getExpandSumNum(){
		return expandSumNum;
	}
	public void setExpandSumNum(int expandSumNum) {
		this.expandSumNum = expandSumNum;
		index = 4;
		result += index + "*int*" + expandSumNum + "#";
	}

	public void setExpandSumNum(int expandSumNum, int bs) {
		this.expandSumNum = expandSumNum;
	}

	/**
		扩充消耗的总金币数
	*/
	private int expandSumGold;
	public int getExpandSumGold(){
		return expandSumGold;
	}
	public void setExpandSumGold(int expandSumGold) {
		this.expandSumGold = expandSumGold;
		index = 5;
		result += index + "*int*" + expandSumGold + "#";
	}

	public void setExpandSumGold(int expandSumGold, int bs) {
		this.expandSumGold = expandSumGold;
	}

	/**
		扩充总次数
	*/
	private int expandSumTimes;
	public int getExpandSumTimes(){
		return expandSumTimes;
	}
	public void setExpandSumTimes(int expandSumTimes) {
		this.expandSumTimes = expandSumTimes;
		index = 6;
		result += index + "*int*" + expandSumTimes + "#";
	}

	public void setExpandSumTimes(int expandSumTimes, int bs) {
		this.expandSumTimes = expandSumTimes;
	}

	/**
		
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 7;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
	}

	/**
		
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 8;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 9;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerBagExpand clone(){
		InstPlayerBagExpand extend=new InstPlayerBagExpand();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setBagTypeId(this.bagTypeId);
		extend.setExpandSumNum(this.expandSumNum);
		extend.setExpandSumGold(this.expandSumGold);
		extend.setExpandSumTimes(this.expandSumTimes);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
