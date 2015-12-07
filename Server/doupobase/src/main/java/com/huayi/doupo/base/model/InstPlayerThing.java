package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家物品实例表
*/
@SuppressWarnings("serial")
public class InstPlayerThing implements Serializable
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
		物品等级 当物品为金宝箱时,此字段表示特殊宝箱的数量, 金宝箱总数量=此字段数量+num字段下的数量
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 4;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		数量
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
		物品类型Id
	*/
	private int thingTypeId;
	public int getThingTypeId(){
		return thingTypeId;
	}
	public void setThingTypeId(int thingTypeId) {
		this.thingTypeId = thingTypeId;
		index = 6;
		result += index + "*int*" + thingTypeId + "#";
	}

	public void setThingTypeId(int thingTypeId, int bs) {
		this.thingTypeId = thingTypeId;
	}

	/**
		
	*/
	private int bagTypeId;
	public int getBagTypeId(){
		return bagTypeId;
	}
	public void setBagTypeId(int bagTypeId) {
		this.bagTypeId = bagTypeId;
		index = 7;
		result += index + "*int*" + bagTypeId + "#";
	}

	public void setBagTypeId(int bagTypeId, int bs) {
		this.bagTypeId = bagTypeId;
	}

	/**
		物品排序
	*/
	private int indexOrder;
	public int getIndexOrder(){
		return indexOrder;
	}
	public void setIndexOrder(int indexOrder) {
		this.indexOrder = indexOrder;
		index = 8;
		result += index + "*int*" + indexOrder + "#";
	}

	public void setIndexOrder(int indexOrder, int bs) {
		this.indexOrder = indexOrder;
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
		index = 9;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 10;
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
		index = 11;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerThing clone(){
		InstPlayerThing extend=new InstPlayerThing();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setThingId(this.thingId);
		extend.setLevel(this.level);
		extend.setNum(this.num);
		extend.setThingTypeId(this.thingTypeId);
		extend.setBagTypeId(this.bagTypeId);
		extend.setIndexOrder(this.indexOrder);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
