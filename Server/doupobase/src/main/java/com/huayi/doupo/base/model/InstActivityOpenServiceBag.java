package com.huayi.doupo.base.model;

import java.io.*;

/**
	开服礼包活动实例表
*/
@SuppressWarnings("serial")
public class InstActivityOpenServiceBag implements Serializable
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
		玩家实例Id
	*/
	private int instPlayerId;
	public int getInstPlayerId(){
		return instPlayerId;
	}
	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
		index = 2;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
	}

	/**
		登录天数
	*/
	private int day;
	public int getDay(){
		return day;
	}
	public void setDay(int day) {
		this.day = day;
		index = 3;
		result += index + "*int*" + day + "#";
	}

	public void setDay(int day, int bs) {
		this.day = day;
	}

	/**
		可以领取的开服礼包活动Id 分号隔开
	*/
	private String can;
	public String getCan(){
		return can;
	}
	public void setCan(String can) {
		this.can = can;
		index = 4;
		result += index + "*String*" + can + "#";
	}

	public void setCan(String can, int bs) {
		this.can = can;
	}

	/**
		已经领取的开服礼包活动Id 分号隔开
	*/
	private String end;
	public String getEnd(){
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
		index = 5;
		result += index + "*String*" + end + "#";
	}

	public void setEnd(String end, int bs) {
		this.end = end;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		添加时间
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 7;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
	}

	/**
		更新时间
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 8;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstActivityOpenServiceBag clone(){
		InstActivityOpenServiceBag extend=new InstActivityOpenServiceBag();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setDay(this.day);
		extend.setCan(this.can);
		extend.setEnd(this.end);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
