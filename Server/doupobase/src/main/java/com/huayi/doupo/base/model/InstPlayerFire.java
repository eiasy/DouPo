package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家异火实例表
*/
@SuppressWarnings("serial")
public class InstPlayerFire implements Serializable
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
		玩家Id
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
		异火字典Id
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
		等级
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
		经验
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 5;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		被动技能 位置_异火斗技字典ID;位置_异火斗技字典ID
	*/
	private String bySkillIds;
	public String getBySkillIds(){
		return bySkillIds;
	}
	public void setBySkillIds(String bySkillIds) {
		this.bySkillIds = bySkillIds;
		index = 6;
		result += index + "*String*" + bySkillIds + "#";
	}

	public void setBySkillIds(String bySkillIds, int bs) {
		this.bySkillIds = bySkillIds;
	}

	/**
		是否上阵  0-不在阵 1-在阵
	*/
	private int isUse;
	public int getIsUse(){
		return isUse;
	}
	public void setIsUse(int isUse) {
		this.isUse = isUse;
		index = 7;
		result += index + "*int*" + isUse + "#";
	}

	public void setIsUse(int isUse, int bs) {
		this.isUse = isUse;
	}

	/**
		描述
	*/
	private String description;
	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		index = 8;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
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

	public String getResult(){
		return result;
	}

	public InstPlayerFire clone(){
		InstPlayerFire extend=new InstPlayerFire();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setFireId(this.fireId);
		extend.setLevel(this.level);
		extend.setExp(this.exp);
		extend.setBySkillIds(this.bySkillIds);
		extend.setIsUse(this.isUse);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
