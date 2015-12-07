package com.huayi.doupo.base.model;

import java.io.*;

/**
	成就字典表
*/
@SuppressWarnings("serial")
public class DictAchievement implements Serializable
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
		名字
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 2;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		成就类型Id
	*/
	private int achievementTypeId;
	public int getAchievementTypeId(){
		return achievementTypeId;
	}
	public void setAchievementTypeId(int achievementTypeId) {
		this.achievementTypeId = achievementTypeId;
		index = 3;
		result += index + "*int*" + achievementTypeId + "#";
	}

	public void setAchievementTypeId(int achievementTypeId, int bs) {
		this.achievementTypeId = achievementTypeId;
	}

	/**
		下一个成就字典Id
	*/
	private int nextId;
	public int getNextId(){
		return nextId;
	}
	public void setNextId(int nextId) {
		this.nextId = nextId;
		index = 4;
		result += index + "*int*" + nextId + "#";
	}

	public void setNextId(int nextId, int bs) {
		this.nextId = nextId;
	}

	/**
		条件
	*/
	private String conditions;
	public String getConditions(){
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
		index = 5;
		result += index + "*String*" + conditions + "#";
	}

	public void setConditions(String conditions, int bs) {
		this.conditions = conditions;
	}

	/**
		奖励 奖励列表 tableType_tableField_TableValue;格式
	*/
	private String rewards;
	public String getRewards(){
		return rewards;
	}
	public void setRewards(String rewards) {
		this.rewards = rewards;
		index = 6;
		result += index + "*String*" + rewards + "#";
	}

	public void setRewards(String rewards, int bs) {
		this.rewards = rewards;
	}

	/**
		进度
	*/
	private int progress;
	public int getProgress(){
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
		index = 7;
		result += index + "*int*" + progress + "#";
	}

	public void setProgress(int progress, int bs) {
		this.progress = progress;
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
		index = 8;
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
		index = 9;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictAchievement clone(){
		DictAchievement extend=new DictAchievement();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setAchievementTypeId(this.achievementTypeId);
		extend.setNextId(this.nextId);
		extend.setConditions(this.conditions);
		extend.setRewards(this.rewards);
		extend.setProgress(this.progress);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
