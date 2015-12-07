package com.hygame.dpcq.model;

import java.io.Serializable;

/**
	限时英雄排名奖励字典表
*/
@SuppressWarnings("serial")
public class DictActivityLimitTimeHeroRankReward implements Serializable
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
		开始排名
	*/
	private int startRankNum;
	public int getStartRankNum(){
		return startRankNum;
	}
	public void setStartRankNum(int startRankNum) {
		this.startRankNum = startRankNum;
		index = 2;
		result += index + "*int*" + startRankNum + "#";
	}

	public void setStartRankNum(int startRankNum, int bs) {
		this.startRankNum = startRankNum;
	}

	/**
		结束排名
	*/
	private int endRankNum;
	public int getEndRankNum(){
		return endRankNum;
	}
	public void setEndRankNum(int endRankNum) {
		this.endRankNum = endRankNum;
		index = 3;
		result += index + "*int*" + endRankNum + "#";
	}

	public void setEndRankNum(int endRankNum, int bs) {
		this.endRankNum = endRankNum;
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

	public DictActivityLimitTimeHeroRankReward clone(){
		DictActivityLimitTimeHeroRankReward extend=new DictActivityLimitTimeHeroRankReward();
		extend.setId(this.id);
		extend.setStartRankNum(this.startRankNum);
		extend.setEndRankNum(this.endRankNum);
		extend.setRewards(this.rewards);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
