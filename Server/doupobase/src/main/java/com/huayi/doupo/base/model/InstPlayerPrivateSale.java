package com.huayi.doupo.base.model;

import java.io.*;

/**
	特卖会实例表
*/
@SuppressWarnings("serial")
public class InstPlayerPrivateSale implements Serializable
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
		特卖会字典Id
	*/
	private int privateSaleId;
	public int getPrivateSaleId(){
		return privateSaleId;
	}
	public void setPrivateSaleId(int privateSaleId) {
		this.privateSaleId = privateSaleId;
		index = 3;
		result += index + "*int*" + privateSaleId + "#";
	}

	public void setPrivateSaleId(int privateSaleId, int bs) {
		this.privateSaleId = privateSaleId;
	}

	/**
		购买次数
	*/
	private int count;
	public int getCount(){
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		index = 4;
		result += index + "*int*" + count + "#";
	}

	public void setCount(int count, int bs) {
		this.count = count;
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
		index = 5;
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

	public String getResult(){
		return result;
	}

	public InstPlayerPrivateSale clone(){
		InstPlayerPrivateSale extend=new InstPlayerPrivateSale();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setPrivateSaleId(this.privateSaleId);
		extend.setCount(this.count);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
