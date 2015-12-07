package com.huayi.doupo.base.model;

import java.io.*;

/**
	世界Boss实例表
*/
@SuppressWarnings("serial")
public class InstWorldBoss implements Serializable
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
		本次世界Boss的字典Id
	*/
	private int currBossId;
	public int getCurrBossId(){
		return currBossId;
	}
	public void setCurrBossId(int currBossId) {
		this.currBossId = currBossId;
		index = 2;
		result += index + "*int*" + currBossId + "#";
	}

	public void setCurrBossId(int currBossId, int bs) {
		this.currBossId = currBossId;
	}

	/**
		本次世界Boss血量
	*/
	private int currBossBlood;
	public int getCurrBossBlood(){
		return currBossBlood;
	}
	public void setCurrBossBlood(int currBossBlood) {
		this.currBossBlood = currBossBlood;
		index = 3;
		result += index + "*int*" + currBossBlood + "#";
	}

	public void setCurrBossBlood(int currBossBlood, int bs) {
		this.currBossBlood = currBossBlood;
	}

	/**
		本次打死世界Boss所用秒数 未打死为0
	*/
	private int currHitBossSecs;
	public int getCurrHitBossSecs(){
		return currHitBossSecs;
	}
	public void setCurrHitBossSecs(int currHitBossSecs) {
		this.currHitBossSecs = currHitBossSecs;
		index = 4;
		result += index + "*int*" + currHitBossSecs + "#";
	}

	public void setCurrHitBossSecs(int currHitBossSecs, int bs) {
		this.currHitBossSecs = currHitBossSecs;
	}

	/**
		当前世界Boss血量系数
	*/
	private float currBossBloodPer;
	public float getCurrBossBloodPer(){
		return currBossBloodPer;
	}
	public void setCurrBossBloodPer(float currBossBloodPer) {
		this.currBossBloodPer = currBossBloodPer;
		index = 5;
		result += index + "*float*" + currBossBloodPer + "#";
	}

	public void setCurrBossBloodPer(float currBossBloodPer, int bs) {
		this.currBossBloodPer = currBossBloodPer;
	}

	/**
		上一次世界Boss的字典Id
	*/
	private int lastBossId;
	public int getLastBossId(){
		return lastBossId;
	}
	public void setLastBossId(int lastBossId) {
		this.lastBossId = lastBossId;
		index = 6;
		result += index + "*int*" + lastBossId + "#";
	}

	public void setLastBossId(int lastBossId, int bs) {
		this.lastBossId = lastBossId;
	}

	/**
		上一次世界boss的血量
	*/
	private int lastBossBlood;
	public int getLastBossBlood(){
		return lastBossBlood;
	}
	public void setLastBossBlood(int lastBossBlood) {
		this.lastBossBlood = lastBossBlood;
		index = 7;
		result += index + "*int*" + lastBossBlood + "#";
	}

	public void setLastBossBlood(int lastBossBlood, int bs) {
		this.lastBossBlood = lastBossBlood;
	}

	/**
		上一次打死世界Boss所用秒数 未打死为0
	*/
	private int lastHitBossSecs;
	public int getLastHitBossSecs(){
		return lastHitBossSecs;
	}
	public void setLastHitBossSecs(int lastHitBossSecs) {
		this.lastHitBossSecs = lastHitBossSecs;
		index = 8;
		result += index + "*int*" + lastHitBossSecs + "#";
	}

	public void setLastHitBossSecs(int lastHitBossSecs, int bs) {
		this.lastHitBossSecs = lastHitBossSecs;
	}

	/**
		上一次世界Boss血量系数
	*/
	private float lastBossBloodPer;
	public float getLastBossBloodPer(){
		return lastBossBloodPer;
	}
	public void setLastBossBloodPer(float lastBossBloodPer) {
		this.lastBossBloodPer = lastBossBloodPer;
		index = 9;
		result += index + "*float*" + lastBossBloodPer + "#";
	}

	public void setLastBossBloodPer(float lastBossBloodPer, int bs) {
		this.lastBossBloodPer = lastBossBloodPer;
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

	public InstWorldBoss clone(){
		InstWorldBoss extend=new InstWorldBoss();
		extend.setId(this.id);
		extend.setCurrBossId(this.currBossId);
		extend.setCurrBossBlood(this.currBossBlood);
		extend.setCurrHitBossSecs(this.currHitBossSecs);
		extend.setCurrBossBloodPer(this.currBossBloodPer);
		extend.setLastBossId(this.lastBossId);
		extend.setLastBossBlood(this.lastBossBlood);
		extend.setLastHitBossSecs(this.lastHitBossSecs);
		extend.setLastBossBloodPer(this.lastBossBloodPer);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
