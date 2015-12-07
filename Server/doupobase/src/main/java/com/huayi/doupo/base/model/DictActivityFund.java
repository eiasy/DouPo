package com.huayi.doupo.base.model;

import java.io.*;

/**
	开服基金字典表
*/
@SuppressWarnings("serial")
public class DictActivityFund implements Serializable
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
		等级
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 2;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		元宝数
	*/
	private int goldNum;
	public int getGoldNum(){
		return goldNum;
	}
	public void setGoldNum(int goldNum) {
		this.goldNum = goldNum;
		index = 3;
		result += index + "*int*" + goldNum + "#";
	}

	public void setGoldNum(int goldNum, int bs) {
		this.goldNum = goldNum;
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

	public DictActivityFund clone(){
		DictActivityFund extend=new DictActivityFund();
		extend.setId(this.id);
		extend.setLevel(this.level);
		extend.setGoldNum(this.goldNum);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
