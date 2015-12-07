package com.huayi.doupo.base.model;

import java.io.*;

/**
	最强英雄排名奖励字典表
*/
@SuppressWarnings("serial")
public class DictActivityStrogerHero implements Serializable
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
		发奖时间点
	*/
	private int rewardTimePoint;
	public int getRewardTimePoint(){
		return rewardTimePoint;
	}
	public void setRewardTimePoint(int rewardTimePoint) {
		this.rewardTimePoint = rewardTimePoint;
		index = 2;
		result += index + "*int*" + rewardTimePoint + "#";
	}

	public void setRewardTimePoint(int rewardTimePoint, int bs) {
		this.rewardTimePoint = rewardTimePoint;
	}

	/**
		名次
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 3;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
	}

	/**
		奖励 tableTypeId_tableFileId_value;
	*/
	private String rewards;
	public String getRewards(){
		return rewards;
	}
	public void setRewards(String rewards) {
		this.rewards = rewards;
		index = 4;
		result += index + "*String*" + rewards + "#";
	}

	public void setRewards(String rewards, int bs) {
		this.rewards = rewards;
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

	public DictActivityStrogerHero clone(){
		DictActivityStrogerHero extend=new DictActivityStrogerHero();
		extend.setId(this.id);
		extend.setRewardTimePoint(this.rewardTimePoint);
		extend.setRank(this.rank);
		extend.setRewards(this.rewards);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
