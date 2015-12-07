package com.huayi.doupo.base.model;

import java.io.*;

/**
	试练日实例表
*/
@SuppressWarnings("serial")
public class InstPlayerTryToPractice implements Serializable
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
		领取过的试练日字典Ids
	*/
	private String tryToPracticeIds;
	public String getTryToPracticeIds(){
		return tryToPracticeIds;
	}
	public void setTryToPracticeIds(String tryToPracticeIds) {
		this.tryToPracticeIds = tryToPracticeIds;
		index = 3;
		result += index + "*String*" + tryToPracticeIds + "#";
	}

	public void setTryToPracticeIds(String tryToPracticeIds, int bs) {
		this.tryToPracticeIds = tryToPracticeIds;
	}

	/**
		可领取的试练日字典Ids
	*/
	private String canTryToPracticeIds;
	public String getCanTryToPracticeIds(){
		return canTryToPracticeIds;
	}
	public void setCanTryToPracticeIds(String canTryToPracticeIds) {
		this.canTryToPracticeIds = canTryToPracticeIds;
		index = 4;
		result += index + "*String*" + canTryToPracticeIds + "#";
	}

	public void setCanTryToPracticeIds(String canTryToPracticeIds, int bs) {
		this.canTryToPracticeIds = canTryToPracticeIds;
	}

	/**
		试练日大奖领取过的天数 ;
	*/
	private String awards;
	public String getAwards(){
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
		index = 5;
		result += index + "*String*" + awards + "#";
	}

	public void setAwards(String awards, int bs) {
		this.awards = awards;
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
		index = 6;
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
		index = 7;
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
		index = 8;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerTryToPractice clone(){
		InstPlayerTryToPractice extend=new InstPlayerTryToPractice();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setTryToPracticeIds(this.tryToPracticeIds);
		extend.setCanTryToPracticeIds(this.canTryToPracticeIds);
		extend.setAwards(this.awards);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
