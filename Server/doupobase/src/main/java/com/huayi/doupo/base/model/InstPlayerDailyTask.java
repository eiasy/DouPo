package com.huayi.doupo.base.model;

import java.io.*;

/**
	每日任务实例表
*/
@SuppressWarnings("serial")
public class InstPlayerDailyTask implements Serializable
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
		每日任务字典Id
	*/
	private int dailyTashkId;
	public int getDailyTashkId(){
		return dailyTashkId;
	}
	public void setDailyTashkId(int dailyTashkId) {
		this.dailyTashkId = dailyTashkId;
		index = 3;
		result += index + "*int*" + dailyTashkId + "#";
	}

	public void setDailyTashkId(int dailyTashkId, int bs) {
		this.dailyTashkId = dailyTashkId;
	}

	/**
		当前完成次数
	*/
	private int times;
	public int getTimes(){
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
		index = 4;
		result += index + "*int*" + times + "#";
	}

	public void setTimes(int times, int bs) {
		this.times = times;
	}

	/**
		奖励状态 0-未领取  1-已领取
	*/
	private int rewardState;
	public int getRewardState(){
		return rewardState;
	}
	public void setRewardState(int rewardState) {
		this.rewardState = rewardState;
		index = 5;
		result += index + "*int*" + rewardState + "#";
	}

	public void setRewardState(int rewardState, int bs) {
		this.rewardState = rewardState;
	}

	/**
		
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 6;
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
		index = 7;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerDailyTask clone(){
		InstPlayerDailyTask extend=new InstPlayerDailyTask();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setDailyTashkId(this.dailyTashkId);
		extend.setTimes(this.times);
		extend.setRewardState(this.rewardState);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
