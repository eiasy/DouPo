package com.huayi.doupo.base.model;

import java.io.*;

/**
	世界boss次数奖励字典表
*/
@SuppressWarnings("serial")
public class DictWorldBossTimesReward implements Serializable
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
		阶段开始
	*/
	private int startRank;
	public int getStartRank(){
		return startRank;
	}
	public void setStartRank(int startRank) {
		this.startRank = startRank;
		index = 2;
		result += index + "*int*" + startRank + "#";
	}

	public void setStartRank(int startRank, int bs) {
		this.startRank = startRank;
	}

	/**
		阶段结束
	*/
	private int endRank;
	public int getEndRank(){
		return endRank;
	}
	public void setEndRank(int endRank) {
		this.endRank = endRank;
		index = 3;
		result += index + "*int*" + endRank + "#";
	}

	public void setEndRank(int endRank, int bs) {
		this.endRank = endRank;
	}

	/**
		奖励物品,tableTypeId_tableFieldId_value;
	*/
	private String rewardThings;
	public String getRewardThings(){
		return rewardThings;
	}
	public void setRewardThings(String rewardThings) {
		this.rewardThings = rewardThings;
		index = 4;
		result += index + "*String*" + rewardThings + "#";
	}

	public void setRewardThings(String rewardThings, int bs) {
		this.rewardThings = rewardThings;
	}

	/**
		描述
	*/
	private String description;
	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		index = 5;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		
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

	public String getResult(){
		return result;
	}

	public DictWorldBossTimesReward clone(){
		DictWorldBossTimesReward extend=new DictWorldBossTimesReward();
		extend.setId(this.id);
		extend.setStartRank(this.startRank);
		extend.setEndRank(this.endRank);
		extend.setRewardThings(this.rewardThings);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
