package com.huayi.doupo.base.model;

import java.io.*;

/**
	系统兑换码类型字典表
*/
@SuppressWarnings("serial")
public class SysCdKeyType implements Serializable
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
		失效时间
	*/
	private String endTime;
	public String getEndTime(){
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
		index = 3;
		result += index + "*String*" + endTime + "#";
	}

	public void setEndTime(String endTime, int bs) {
		this.endTime = endTime;
	}

	/**
		奖励物品礼包字典Id
	*/
	private String thingId;
	public String getThingId(){
		return thingId;
	}
	public void setThingId(String thingId) {
		this.thingId = thingId;
		index = 4;
		result += index + "*String*" + thingId + "#";
	}

	public void setThingId(String thingId, int bs) {
		this.thingId = thingId;
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
		index = 5;
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

	public SysCdKeyType clone(){
		SysCdKeyType extend=new SysCdKeyType();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setEndTime(this.endTime);
		extend.setThingId(this.thingId);
		extend.setSname(this.sname);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
