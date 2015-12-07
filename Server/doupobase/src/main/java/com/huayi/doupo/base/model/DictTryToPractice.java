package com.huayi.doupo.base.model;

import java.io.*;

/**
	试练日字典表
*/
@SuppressWarnings("serial")
public class DictTryToPractice implements Serializable
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
		试练日天数分组Id
	*/
	private int tryToPracticeTypeId;
	public int getTryToPracticeTypeId(){
		return tryToPracticeTypeId;
	}
	public void setTryToPracticeTypeId(int tryToPracticeTypeId) {
		this.tryToPracticeTypeId = tryToPracticeTypeId;
		index = 3;
		result += index + "*int*" + tryToPracticeTypeId + "#";
	}

	public void setTryToPracticeTypeId(int tryToPracticeTypeId, int bs) {
		this.tryToPracticeTypeId = tryToPracticeTypeId;
	}

	/**
		条件 礼包的原件
	*/
	private String conditions;
	public String getConditions(){
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
		index = 4;
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
		index = 5;
		result += index + "*String*" + rewards + "#";
	}

	public void setRewards(String rewards, int bs) {
		this.rewards = rewards;
	}

	/**
		进度 礼包的现价
	*/
	private int progress;
	public int getProgress(){
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
		index = 6;
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
		index = 7;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictTryToPractice clone(){
		DictTryToPractice extend=new DictTryToPractice();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setTryToPracticeTypeId(this.tryToPracticeTypeId);
		extend.setConditions(this.conditions);
		extend.setRewards(this.rewards);
		extend.setProgress(this.progress);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
