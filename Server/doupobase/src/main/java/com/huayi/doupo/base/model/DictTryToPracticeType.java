package com.huayi.doupo.base.model;

import java.io.*;

/**
	试练日天数分组字典表
*/
@SuppressWarnings("serial")
public class DictTryToPracticeType implements Serializable
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
		天数
	*/
	private int day;
	public int getDay(){
		return day;
	}
	public void setDay(int day) {
		this.day = day;
		index = 2;
		result += index + "*int*" + day + "#";
	}

	public void setDay(int day, int bs) {
		this.day = day;
	}

	/**
		名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 3;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		通关奖励,每天的第一条
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
		完成数,只每天的第一条填
	*/
	private int count;
	public int getCount(){
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		index = 5;
		result += index + "*int*" + count + "#";
	}

	public void setCount(int count, int bs) {
		this.count = count;
	}

	/**
		用于限制次数
	*/
	private int num;
	public int getNum(){
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		index = 6;
		result += index + "*int*" + num + "#";
	}

	public void setNum(int num, int bs) {
		this.num = num;
	}

	/**
		
	*/
	private String sname;
	public String getSname(){
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
		index = 7;
		result += index + "*String*" + sname + "#";
	}

	public void setSname(String sname, int bs) {
		this.sname = sname;
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

	public DictTryToPracticeType clone(){
		DictTryToPracticeType extend=new DictTryToPracticeType();
		extend.setId(this.id);
		extend.setDay(this.day);
		extend.setName(this.name);
		extend.setRewards(this.rewards);
		extend.setCount(this.count);
		extend.setNum(this.num);
		extend.setSname(this.sname);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
