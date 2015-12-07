package com.huayi.doupo.base.model;

import java.io.*;

/**
	系统配置字典表 如:人物、武功、装备等级上限不超过100,人物传承等级上限=人物等级上限*3
*/
@SuppressWarnings("serial")
public class DictSysConfig implements Serializable
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
		名称
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
		静态字段
	*/
	private String sname;
	public String getSname(){
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
		index = 3;
		result += index + "*String*" + sname + "#";
	}

	public void setSname(String sname, int bs) {
		this.sname = sname;
	}

	/**
		具体值
	*/
	private float value;
	public float getValue(){
		return value;
	}
	public void setValue(float value) {
		this.value = value;
		index = 4;
		result += index + "*float*" + value + "#";
	}

	public void setValue(float value, int bs) {
		this.value = value;
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

	public DictSysConfig clone(){
		DictSysConfig extend=new DictSysConfig();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSname(this.sname);
		extend.setValue(this.value);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
