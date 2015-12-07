package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家商店购买实例表.只存储商店中的道具和vip礼包。道具保留今日和昨日数据，礼包永久保存。礼包存储时，bagType设置成0.
*/
@SuppressWarnings("serial")
public class InstPlayerStore implements Serializable
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
		物品Id
	*/
	private int thingId;
	public int getThingId(){
		return thingId;
	}
	public void setThingId(int thingId) {
		this.thingId = thingId;
		index = 3;
		result += index + "*int*" + thingId + "#";
	}

	public void setThingId(int thingId, int bs) {
		this.thingId = thingId;
	}

	/**
		购买类型
	*/
	private int bagType;
	public int getBagType(){
		return bagType;
	}
	public void setBagType(int bagType) {
		this.bagType = bagType;
		index = 4;
		result += index + "*int*" + bagType + "#";
	}

	public void setBagType(int bagType, int bs) {
		this.bagType = bagType;
	}

	/**
		购买个数
	*/
	private int num;
	public int getNum(){
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		index = 5;
		result += index + "*int*" + num + "#";
	}

	public void setNum(int num, int bs) {
		this.num = num;
	}

	/**
		购买时间
	*/
	private String buyTime;
	public String getBuyTime(){
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
		index = 6;
		result += index + "*String*" + buyTime + "#";
	}

	public void setBuyTime(String buyTime, int bs) {
		this.buyTime = buyTime;
	}

	/**
		
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

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 9;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerStore clone(){
		InstPlayerStore extend=new InstPlayerStore();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setThingId(this.thingId);
		extend.setBagType(this.bagType);
		extend.setNum(this.num);
		extend.setBuyTime(this.buyTime);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
