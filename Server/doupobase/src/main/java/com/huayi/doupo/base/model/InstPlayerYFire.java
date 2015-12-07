package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class InstPlayerYFire implements Serializable
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
		玩家ID
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
		异火ID
	*/
	private int fireId;
	public int getFireId(){
		return fireId;
	}
	public void setFireId(int fireId) {
		this.fireId = fireId;
		index = 3;
		result += index + "*int*" + fireId + "#";
	}

	public void setFireId(int fireId, int bs) {
		this.fireId = fireId;
	}

	/**
		异火状态
	*/
	private int state;
	public int getState(){
		return state;
	}
	public void setState(int state) {
		this.state = state;
		index = 4;
		result += index + "*int*" + state + "#";
	}

	public void setState(int state, int bs) {
		this.state = state;
	}

	/**
		异火更新时间
	*/
	private String fireTime;
	public String getFireTime(){
		return fireTime;
	}
	public void setFireTime(String fireTime) {
		this.fireTime = fireTime;
		index = 5;
		result += index + "*String*" + fireTime + "#";
	}

	public void setFireTime(String fireTime, int bs) {
		this.fireTime = fireTime;
	}

	/**
		异火能量
	*/
	private int hp;
	public int getHp(){
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
		index = 6;
		result += index + "*int*" + hp + "#";
	}

	public void setHp(int hp, int bs) {
		this.hp = hp;
	}

	/**
		狂暴状态描述
	*/
	private int speed;
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
		index = 7;
		result += index + "*int*" + speed + "#";
	}

	public void setSpeed(int speed, int bs) {
		this.speed = speed;
	}

	/**
		异火被哪些人装备
	*/
	private String owner;
	public String getOwner(){
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
		index = 8;
		result += index + "*String*" + owner + "#";
	}

	public void setOwner(String owner, int bs) {
		this.owner = owner;
	}

	/**
		收集的碎片数量
	*/
	private int chipCount;
	public int getChipCount(){
		return chipCount;
	}
	public void setChipCount(int chipCount) {
		this.chipCount = chipCount;
		index = 9;
		result += index + "*int*" + chipCount + "#";
	}

	public void setChipCount(int chipCount, int bs) {
		this.chipCount = chipCount;
	}

	/**
		插入更新时间
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
		更新时间
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

	/**
		版本号
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 12;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerYFire clone(){
		InstPlayerYFire extend=new InstPlayerYFire();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setFireId(this.fireId);
		extend.setState(this.state);
		extend.setFireTime(this.fireTime);
		extend.setHp(this.hp);
		extend.setSpeed(this.speed);
		extend.setOwner(this.owner);
		extend.setChipCount(this.chipCount);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
