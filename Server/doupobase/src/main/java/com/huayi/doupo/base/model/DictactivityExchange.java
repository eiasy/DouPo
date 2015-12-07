package com.huayi.doupo.base.model;

import java.io.*;

/**
	限时兑换
*/
@SuppressWarnings("serial")
public class DictactivityExchange implements Serializable
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
		开始时间[2015-08-01 01:20:00]
	*/
	private String timeStart;
	public String getTimeStart(){
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
		index = 2;
		result += index + "*String*" + timeStart + "#";
	}

	public void setTimeStart(String timeStart, int bs) {
		this.timeStart = timeStart;
	}

	/**
		结束时间[2015-08-01 01:20:00]
	*/
	private String timeStop;
	public String getTimeStop(){
		return timeStop;
	}
	public void setTimeStop(String timeStop) {
		this.timeStop = timeStop;
		index = 3;
		result += index + "*String*" + timeStop + "#";
	}

	public void setTimeStop(String timeStop, int bs) {
		this.timeStop = timeStop;
	}

	/**
		消耗道具[类型_ID_数量;]
	*/
	private String costItem;
	public String getCostItem(){
		return costItem;
	}
	public void setCostItem(String costItem) {
		this.costItem = costItem;
		index = 4;
		result += index + "*String*" + costItem + "#";
	}

	public void setCostItem(String costItem, int bs) {
		this.costItem = costItem;
	}

	/**
		获得的道具[类型_ID_数量]
	*/
	private String getItem;
	public String getGetItem(){
		return getItem;
	}
	public void setGetItem(String getItem) {
		this.getItem = getItem;
		index = 5;
		result += index + "*String*" + getItem + "#";
	}

	public void setGetItem(String getItem, int bs) {
		this.getItem = getItem;
	}

	/**
		最大兑换次数
	*/
	private int countLimit;
	public int getCountLimit(){
		return countLimit;
	}
	public void setCountLimit(int countLimit) {
		this.countLimit = countLimit;
		index = 6;
		result += index + "*int*" + countLimit + "#";
	}

	public void setCountLimit(int countLimit, int bs) {
		this.countLimit = countLimit;
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

	public DictactivityExchange clone(){
		DictactivityExchange extend=new DictactivityExchange();
		extend.setId(this.id);
		extend.setTimeStart(this.timeStart);
		extend.setTimeStop(this.timeStop);
		extend.setCostItem(this.costItem);
		extend.setGetItem(this.getItem);
		extend.setCountLimit(this.countLimit);
		extend.setVersion(this.version);
		return extend;
	}
}
