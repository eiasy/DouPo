package com.huayi.doupo.base.model;

import java.io.*;

/**
	签到活动实例表
*/
@SuppressWarnings("serial")
public class InstActivitySignIn implements Serializable
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
		签到类型(1-普通签到 2-豪华签到)
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 3;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		签到天数
	*/
	private int day;
	public int getDay(){
		return day;
	}
	public void setDay(int day) {
		this.day = day;
		index = 4;
		result += index + "*int*" + day + "#";
	}

	public void setDay(int day, int bs) {
		this.day = day;
	}

	/**
		是否领取双倍奖励 0-全部领取 1-单倍领取
	*/
	private int isTwo;
	public int getIsTwo(){
		return isTwo;
	}
	public void setIsTwo(int isTwo) {
		this.isTwo = isTwo;
		index = 5;
		result += index + "*int*" + isTwo + "#";
	}

	public void setIsTwo(int isTwo, int bs) {
		this.isTwo = isTwo;
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

	public InstActivitySignIn clone(){
		InstActivitySignIn extend=new InstActivitySignIn();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setType(this.type);
		extend.setDay(this.day);
		extend.setIsTwo(this.isTwo);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
