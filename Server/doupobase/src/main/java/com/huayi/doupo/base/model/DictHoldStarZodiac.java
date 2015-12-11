package com.huayi.doupo.base.model;

import java.io.*;

/**
	占星-星座字典表
*/
@SuppressWarnings("serial")
public class DictHoldStarZodiac implements Serializable
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
		星座图标Id
	*/
	private int uiId;
	public int getUiId(){
		return uiId;
	}
	public void setUiId(int uiId) {
		this.uiId = uiId;
		index = 3;
		result += index + "*int*" + uiId + "#";
	}

	public void setUiId(int uiId, int bs) {
		this.uiId = uiId;
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

	public DictHoldStarZodiac clone(){
		DictHoldStarZodiac extend=new DictHoldStarZodiac();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setUiId(this.uiId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
