package com.huayi.doupo.base.model;

import java.io.*;

/**
	在线奖励活动字典表
*/
@SuppressWarnings("serial")
public class DictActivityOnlineRewards implements Serializable
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
		在线时间 分钟
	*/
	private int onlineTime;
	public int getOnlineTime(){
		return onlineTime;
	}
	public void setOnlineTime(int onlineTime) {
		this.onlineTime = onlineTime;
		index = 2;
		result += index + "*int*" + onlineTime + "#";
	}

	public void setOnlineTime(int onlineTime, int bs) {
		this.onlineTime = onlineTime;
	}

	/**
		奖励物品 tableTypeId_tableFieldId_tableValue
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 3;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
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

	public DictActivityOnlineRewards clone(){
		DictActivityOnlineRewards extend=new DictActivityOnlineRewards();
		extend.setId(this.id);
		extend.setOnlineTime(this.onlineTime);
		extend.setThings(this.things);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
