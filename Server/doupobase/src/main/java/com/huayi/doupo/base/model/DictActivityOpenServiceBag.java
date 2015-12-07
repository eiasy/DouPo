package com.huayi.doupo.base.model;

import java.io.*;

/**
	开服礼包活动字典表
*/
@SuppressWarnings("serial")
public class DictActivityOpenServiceBag implements Serializable
{
	private int index;
	public String result = "";
	/**
		天数
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
		奖励物品 tableTypeId_tableFieldId_tableValue
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 2;
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
		index = 3;
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
		index = 4;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictActivityOpenServiceBag clone(){
		DictActivityOpenServiceBag extend=new DictActivityOpenServiceBag();
		extend.setId(this.id);
		extend.setThings(this.things);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
