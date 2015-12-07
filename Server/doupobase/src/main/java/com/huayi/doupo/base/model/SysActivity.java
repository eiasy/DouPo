package com.huayi.doupo.base.model;

import java.io.*;

/**
	活动字典表
*/
@SuppressWarnings("serial")
public class SysActivity implements Serializable
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
		小头像Id
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 3;
		result += index + "*int*" + smallUiId + "#";
	}

	public void setSmallUiId(int smallUiId, int bs) {
		this.smallUiId = smallUiId;
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
		index = 4;
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
		index = 5;
		result += index + "*String*" + endTime + "#";
	}

	public void setEndTime(String endTime, int bs) {
		this.endTime = endTime;
	}

	/**
		是否为最新活动 0-不是最新 1-是最新
	*/
	private int isNew;
	public int getIsNew(){
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
		index = 6;
		result += index + "*int*" + isNew + "#";
	}

	public void setIsNew(int isNew, int bs) {
		this.isNew = isNew;
	}

	/**
		是否为永久活动 0-不是 1-是
	*/
	private int isForevery;
	public int getIsForevery(){
		return isForevery;
	}
	public void setIsForevery(int isForevery) {
		this.isForevery = isForevery;
		index = 7;
		result += index + "*int*" + isForevery + "#";
	}

	public void setIsForevery(int isForevery, int bs) {
		this.isForevery = isForevery;
	}

	/**
		是否显示 0-不显示 1-显示
	*/
	private int isView;
	public int getIsView(){
		return isView;
	}
	public void setIsView(int isView) {
		this.isView = isView;
		index = 8;
		result += index + "*int*" + isView + "#";
	}

	public void setIsView(int isView, int bs) {
		this.isView = isView;
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
		index = 9;
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
		index = 10;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		排序
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 11;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
	}

	/**
		是否有活动快捷图标 0-没有 1-有
	*/
	private int isHotKey;
	public int getIsHotKey(){
		return isHotKey;
	}
	public void setIsHotKey(int isHotKey) {
		this.isHotKey = isHotKey;
		index = 12;
		result += index + "*int*" + isHotKey + "#";
	}

	public void setIsHotKey(int isHotKey, int bs) {
		this.isHotKey = isHotKey;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 13;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public SysActivity clone(){
		SysActivity extend=new SysActivity();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSmallUiId(this.smallUiId);
		extend.setStartTime(this.startTime);
		extend.setEndTime(this.endTime);
		extend.setIsNew(this.isNew);
		extend.setIsForevery(this.isForevery);
		extend.setIsView(this.isView);
		extend.setSname(this.sname);
		extend.setDescription(this.description);
		extend.setRank(this.rank);
		extend.setIsHotKey(this.isHotKey);
		extend.setVersion(this.version);
		return extend;
	}
}
