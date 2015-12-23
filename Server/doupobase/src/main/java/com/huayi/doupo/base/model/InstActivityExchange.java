package com.huayi.doupo.base.model;

import java.io.*;

/**
	超值兑换活动实例表
*/
@SuppressWarnings("serial")
public class InstActivityExchange implements Serializable
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
		兑换字典表Id
	*/
	private int exchageId;
	public int getExchageId(){
		return exchageId;
	}
	public void setExchageId(int exchageId) {
		this.exchageId = exchageId;
		index = 3;
		result += index + "*int*" + exchageId + "#";
	}

	public void setExchageId(int exchageId, int bs) {
		this.exchageId = exchageId;
	}

	/**
		兑换次数
	*/
	private int exchangeTimes;
	public int getExchangeTimes(){
		return exchangeTimes;
	}
	public void setExchangeTimes(int exchangeTimes) {
		this.exchangeTimes = exchangeTimes;
		index = 4;
		result += index + "*int*" + exchangeTimes + "#";
	}

	public void setExchangeTimes(int exchangeTimes, int bs) {
		this.exchangeTimes = exchangeTimes;
	}

	/**
		兑换时间
	*/
	private String exchangeTime;
	public String getExchangeTime(){
		return exchangeTime;
	}
	public void setExchangeTime(String exchangeTime) {
		this.exchangeTime = exchangeTime;
		index = 5;
		result += index + "*String*" + exchangeTime + "#";
	}

	public void setExchangeTime(String exchangeTime, int bs) {
		this.exchangeTime = exchangeTime;
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
		index = 6;
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
		index = 7;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstActivityExchange clone(){
		InstActivityExchange extend=new InstActivityExchange();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setExchageId(this.exchageId);
		extend.setExchangeTimes(this.exchangeTimes);
		extend.setExchangeTime(this.exchangeTime);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
