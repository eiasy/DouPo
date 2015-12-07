package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家成就实例表
*/
@SuppressWarnings("serial")
public class InstPlayerAchievement implements Serializable
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
		成就类型
	*/
	private int achievementTypeId;
	public int getAchievementTypeId(){
		return achievementTypeId;
	}
	public void setAchievementTypeId(int achievementTypeId) {
		this.achievementTypeId = achievementTypeId;
		index = 3;
		result += index + "*int*" + achievementTypeId + "#";
	}

	public void setAchievementTypeId(int achievementTypeId, int bs) {
		this.achievementTypeId = achievementTypeId;
	}

	/**
		当前成就字典Id 分号隔开
	*/
	private int achievementId;
	public int getAchievementId(){
		return achievementId;
	}
	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
		index = 4;
		result += index + "*int*" + achievementId + "#";
	}

	public void setAchievementId(int achievementId, int bs) {
		this.achievementId = achievementId;
	}

	/**
		完成成就字典Id 分号隔开
	*/
	private int canAchievementId;
	public int getCanAchievementId(){
		return canAchievementId;
	}
	public void setCanAchievementId(int canAchievementId) {
		this.canAchievementId = canAchievementId;
		index = 5;
		result += index + "*int*" + canAchievementId + "#";
	}

	public void setCanAchievementId(int canAchievementId, int bs) {
		this.canAchievementId = canAchievementId;
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

	public InstPlayerAchievement clone(){
		InstPlayerAchievement extend=new InstPlayerAchievement();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setAchievementTypeId(this.achievementTypeId);
		extend.setAchievementId(this.achievementId);
		extend.setCanAchievementId(this.canAchievementId);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
