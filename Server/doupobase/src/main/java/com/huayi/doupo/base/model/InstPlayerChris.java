package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家战斗阵容实例表
*/
@SuppressWarnings("serial")
public class InstPlayerChris implements Serializable
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
		阵型Id
	*/
	private String firstTime;
	public String getFirstTime(){
		return firstTime;
	}
	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
		index = 3;
		result += index + "*String*" + firstTime + "#";
	}

	public void setFirstTime(String firstTime, int bs) {
		this.firstTime = firstTime;
	}

	/**
		装备类型Id
	*/
	private int firstCount;
	public int getFirstCount(){
		return firstCount;
	}
	public void setFirstCount(int firstCount) {
		this.firstCount = firstCount;
		index = 4;
		result += index + "*int*" + firstCount + "#";
	}

	public void setFirstCount(int firstCount, int bs) {
		this.firstCount = firstCount;
	}

	/**
		玩家装备Id
	*/
	private String secondTime;
	public String getSecondTime(){
		return secondTime;
	}
	public void setSecondTime(String secondTime) {
		this.secondTime = secondTime;
		index = 5;
		result += index + "*String*" + secondTime + "#";
	}

	public void setSecondTime(String secondTime, int bs) {
		this.secondTime = secondTime;
	}

	/**
		
	*/
	private int secondCount;
	public int getSecondCount(){
		return secondCount;
	}
	public void setSecondCount(int secondCount) {
		this.secondCount = secondCount;
		index = 6;
		result += index + "*int*" + secondCount + "#";
	}

	public void setSecondCount(int secondCount, int bs) {
		this.secondCount = secondCount;
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

	/**
		添加时间
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 8;
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
		index = 9;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerChris clone(){
		InstPlayerChris extend=new InstPlayerChris();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setFirstTime(this.firstTime);
		extend.setFirstCount(this.firstCount);
		extend.setSecondTime(this.secondTime);
		extend.setSecondCount(this.secondCount);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
