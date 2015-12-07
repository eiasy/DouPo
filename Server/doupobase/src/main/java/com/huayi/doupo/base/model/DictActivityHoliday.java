package com.huayi.doupo.base.model;

import java.io.*;

/**
	节假日活动字典表
*/
@SuppressWarnings("serial")
public class DictActivityHoliday implements Serializable
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
		开始时间
	*/
	private String startTime;
	public String getStartTime(){
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
		index = 3;
		result += index + "*String*" + startTime + "#";
	}

	public void setStartTime(String startTime, int bs) {
		this.startTime = startTime;
	}

	/**
		结束时间
	*/
	private String endTime;
	public String getEndTime(){
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
		index = 4;
		result += index + "*String*" + endTime + "#";
	}

	public void setEndTime(String endTime, int bs) {
		this.endTime = endTime;
	}

	/**
		
	*/
	private float multiple;
	public float getMultiple(){
		return multiple;
	}
	public void setMultiple(float multiple) {
		this.multiple = multiple;
		index = 5;
		result += index + "*float*" + multiple + "#";
	}

	public void setMultiple(float multiple, int bs) {
		this.multiple = multiple;
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
		index = 6;
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

	public DictActivityHoliday clone(){
		DictActivityHoliday extend=new DictActivityHoliday();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setStartTime(this.startTime);
		extend.setEndTime(this.endTime);
		extend.setMultiple(this.multiple);
		extend.setSname(this.sname);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
