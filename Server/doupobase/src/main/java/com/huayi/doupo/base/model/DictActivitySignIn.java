package com.huayi.doupo.base.model;

import java.io.*;

/**
	签到活动字典表
*/
@SuppressWarnings("serial")
public class DictActivitySignIn implements Serializable
{
	private int index;
	public String result = "";
	/**
		
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
		月
	*/
	private int month;
	public int getMonth(){
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
		index = 2;
		result += index + "*int*" + month + "#";
	}

	public void setMonth(int month, int bs) {
		this.month = month;
	}

	/**
		天数
	*/
	private int day;
	public int getDay(){
		return day;
	}
	public void setDay(int day) {
		this.day = day;
		index = 3;
		result += index + "*int*" + day + "#";
	}

	public void setDay(int day, int bs) {
		this.day = day;
	}

	/**
		类型(1-普通签到 2-豪华签到)
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 4;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
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
		index = 5;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
	}

	/**
		双倍vip等级限制 0-无双倍
	*/
	private int vip;
	public int getVip(){
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
		index = 6;
		result += index + "*int*" + vip + "#";
	}

	public void setVip(int vip, int bs) {
		this.vip = vip;
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

	public DictActivitySignIn clone(){
		DictActivitySignIn extend=new DictActivitySignIn();
		extend.setId(this.id);
		extend.setMonth(this.month);
		extend.setDay(this.day);
		extend.setType(this.type);
		extend.setThings(this.things);
		extend.setVip(this.vip);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
