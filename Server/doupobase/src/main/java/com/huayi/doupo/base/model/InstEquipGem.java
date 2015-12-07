package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家装备镶嵌宝石实例表
*/
@SuppressWarnings("serial")
public class InstEquipGem implements Serializable
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
		玩家装备实例Id
	*/
	private int instPlayerEquipId;
	public int getInstPlayerEquipId(){
		return instPlayerEquipId;
	}
	public void setInstPlayerEquipId(int instPlayerEquipId) {
		this.instPlayerEquipId = instPlayerEquipId;
		index = 3;
		result += index + "*int*" + instPlayerEquipId + "#";
	}

	public void setInstPlayerEquipId(int instPlayerEquipId, int bs) {
		this.instPlayerEquipId = instPlayerEquipId;
	}

	/**
		物品Id 0表示未镶嵌宝石
	*/
	private int thingId;
	public int getThingId(){
		return thingId;
	}
	public void setThingId(int thingId) {
		this.thingId = thingId;
		index = 4;
		result += index + "*int*" + thingId + "#";
	}

	public void setThingId(int thingId, int bs) {
		this.thingId = thingId;
	}

	/**
		位置
	*/
	private int position;
	public int getPosition(){
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
		index = 5;
		result += index + "*int*" + position + "#";
	}

	public void setPosition(int position, int bs) {
		this.position = position;
	}

	/**
		
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

	public InstEquipGem clone(){
		InstEquipGem extend=new InstEquipGem();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setInstPlayerEquipId(this.instPlayerEquipId);
		extend.setThingId(this.thingId);
		extend.setPosition(this.position);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
