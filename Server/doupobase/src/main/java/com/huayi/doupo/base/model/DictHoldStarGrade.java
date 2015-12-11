package com.huayi.doupo.base.model;

import java.io.*;

/**
	占星-档次字典表
*/
@SuppressWarnings("serial")
public class DictHoldStarGrade implements Serializable
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
		开启等级
	*/
	private int openLevel;
	public int getOpenLevel(){
		return openLevel;
	}
	public void setOpenLevel(int openLevel) {
		this.openLevel = openLevel;
		index = 3;
		result += index + "*int*" + openLevel + "#";
	}

	public void setOpenLevel(int openLevel, int bs) {
		this.openLevel = openLevel;
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

	public DictHoldStarGrade clone(){
		DictHoldStarGrade extend=new DictHoldStarGrade();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setOpenLevel(this.openLevel);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
