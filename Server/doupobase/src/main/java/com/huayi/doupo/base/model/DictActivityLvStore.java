package com.huayi.doupo.base.model;

import java.io.*;

/**
	等级商店字典表
*/
@SuppressWarnings("serial")
public class DictActivityLvStore implements Serializable
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
		等级 填写31天的数据
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
		物品列表 tableTypeId_tableFieldId_value;
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
		出售价格（元宝）
	*/
	private int sellGold;
	public int getSellGold(){
		return sellGold;
	}
	public void setSellGold(int sellGold) {
		this.sellGold = sellGold;
		index = 4;
		result += index + "*int*" + sellGold + "#";
	}

	public void setSellGold(int sellGold, int bs) {
		this.sellGold = sellGold;
	}

	/**
		限购次数
	*/
	private int limitNum;
	public int getLimitNum(){
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
		index = 5;
		result += index + "*int*" + limitNum + "#";
	}

	public void setLimitNum(int limitNum, int bs) {
		this.limitNum = limitNum;
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
		index = 6;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		版本号
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 7;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictActivityLvStore clone(){
		DictActivityLvStore extend=new DictActivityLvStore();
		extend.setId(this.id);
		extend.setLevel(this.level);
		extend.setThings(this.things);
		extend.setSellGold(this.sellGold);
		extend.setLimitNum(this.limitNum);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
