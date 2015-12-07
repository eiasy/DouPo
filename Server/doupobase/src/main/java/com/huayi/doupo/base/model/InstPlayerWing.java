package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家翅膀实例表
*/
@SuppressWarnings("serial")
public class InstPlayerWing implements Serializable
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
		翅膀Id
	*/
	private int wingId;
	public int getWingId(){
		return wingId;
	}
	public void setWingId(int wingId) {
		this.wingId = wingId;
		index = 3;
		result += index + "*int*" + wingId + "#";
	}

	public void setWingId(int wingId, int bs) {
		this.wingId = wingId;
	}

	/**
		强化等级
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
		进阶星数
	*/
	private int starNum;
	public int getStarNum(){
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
		index = 5;
		result += index + "*int*" + starNum + "#";
	}

	public void setStarNum(int starNum, int bs) {
		this.starNum = starNum;
	}

	/**
		卡牌Id 此翅膀被装备在哪个卡牌身上.未装备为0
	*/
	private int instCardId;
	public int getInstCardId(){
		return instCardId;
	}
	public void setInstCardId(int instCardId) {
		this.instCardId = instCardId;
		index = 6;
		result += index + "*int*" + instCardId + "#";
	}

	public void setInstCardId(int instCardId, int bs) {
		this.instCardId = instCardId;
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

	public InstPlayerWing clone(){
		InstPlayerWing extend=new InstPlayerWing();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setWingId(this.wingId);
		extend.setLevel(this.level);
		extend.setStarNum(this.starNum);
		extend.setInstCardId(this.instCardId);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
