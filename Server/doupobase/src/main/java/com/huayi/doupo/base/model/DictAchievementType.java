package com.huayi.doupo.base.model;

import java.io.*;

/**
	成就类型字典表
*/
@SuppressWarnings("serial")
public class DictAchievementType implements Serializable
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
		小图标0
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 2;
		result += index + "*int*" + smallUiId + "#";
	}

	public void setSmallUiId(int smallUiId, int bs) {
		this.smallUiId = smallUiId;
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
		index = 3;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		静态字段
	*/
	private String sname;
	public String getSname(){
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
		index = 4;
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
		index = 5;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		功能开放字典Id
	*/
	private int functionOpenId;
	public int getFunctionOpenId(){
		return functionOpenId;
	}
	public void setFunctionOpenId(int functionOpenId) {
		this.functionOpenId = functionOpenId;
		index = 6;
		result += index + "*int*" + functionOpenId + "#";
	}

	public void setFunctionOpenId(int functionOpenId, int bs) {
		this.functionOpenId = functionOpenId;
	}

	/**
		
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

	public DictAchievementType clone(){
		DictAchievementType extend=new DictAchievementType();
		extend.setId(this.id);
		extend.setSmallUiId(this.smallUiId);
		extend.setName(this.name);
		extend.setSname(this.sname);
		extend.setDescription(this.description);
		extend.setFunctionOpenId(this.functionOpenId);
		extend.setVersion(this.version);
		return extend;
	}
}
