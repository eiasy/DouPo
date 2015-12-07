package com.hygame.dpcq.model;

import java.io.Serializable;

/**
	限时英雄排名奖励字典表
*/
@SuppressWarnings("serial")
public class DictActivityLimitTimeHeroJiFenReward implements Serializable
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
		累计积分数
	*/
	private int saveJifenNum;
	public int getSaveJifenNum(){
		return saveJifenNum;
	}
	public void setSaveJifenNum(int saveJifenNum) {
		this.saveJifenNum = saveJifenNum;
		index = 2;
		result += index + "*int*" + saveJifenNum + "#";
	}

	public void setSaveJifenNum(int saveJifenNum, int bs) {
		this.saveJifenNum = saveJifenNum;
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
		index = 3;
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
		index = 4;
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
		index = 5;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictActivityLimitTimeHeroJiFenReward clone(){
		DictActivityLimitTimeHeroJiFenReward extend=new DictActivityLimitTimeHeroJiFenReward();
		extend.setId(this.id);
		extend.setSaveJifenNum(this.saveJifenNum);
		extend.setRewards(this.rewards);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
