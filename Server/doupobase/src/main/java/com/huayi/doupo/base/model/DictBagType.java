package com.huayi.doupo.base.model;

import java.io.*;

/**
	背包类型字典表
*/
@SuppressWarnings("serial")
public class DictBagType implements Serializable
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
		名字
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
		背包上限
	*/
	private int bagUpLimit;
	public int getBagUpLimit(){
		return bagUpLimit;
	}
	public void setBagUpLimit(int bagUpLimit) {
		this.bagUpLimit = bagUpLimit;
		index = 3;
		result += index + "*int*" + bagUpLimit + "#";
	}

	public void setBagUpLimit(int bagUpLimit, int bs) {
		this.bagUpLimit = bagUpLimit;
	}

	/**
		
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

	public DictBagType clone(){
		DictBagType extend=new DictBagType();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setBagUpLimit(this.bagUpLimit);
		extend.setSname(this.sname);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
