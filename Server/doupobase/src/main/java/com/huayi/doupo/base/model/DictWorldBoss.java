package com.huayi.doupo.base.model;

import java.io.*;

/**
	世界boss字典表
*/
@SuppressWarnings("serial")
public class DictWorldBoss implements Serializable
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
		名称 -用于策划填数据, 实际界面值放在客户端
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
		Boss血量系数
	*/
	private float bloodPer;
	public float getBloodPer(){
		return bloodPer;
	}
	public void setBloodPer(float bloodPer) {
		this.bloodPer = bloodPer;
		index = 3;
		result += index + "*float*" + bloodPer + "#";
	}

	public void setBloodPer(float bloodPer, int bs) {
		this.bloodPer = bloodPer;
	}

	/**
		更新或更新后开新服是否为第一次出现的世界boss 0-不是 1-是
	*/
	private int isFirstShow;
	public int getIsFirstShow(){
		return isFirstShow;
	}
	public void setIsFirstShow(int isFirstShow) {
		this.isFirstShow = isFirstShow;
		index = 4;
		result += index + "*int*" + isFirstShow + "#";
	}

	public void setIsFirstShow(int isFirstShow, int bs) {
		this.isFirstShow = isFirstShow;
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

	public DictWorldBoss clone(){
		DictWorldBoss extend=new DictWorldBoss();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setBloodPer(this.bloodPer);
		extend.setIsFirstShow(this.isFirstShow);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
