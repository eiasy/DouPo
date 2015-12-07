package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家手动技能实例表
*/
@SuppressWarnings("serial")
public class InstPlayerManualSkill implements Serializable
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
		类别 1-攻 2-辅 3-控 4-毒
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 3;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		手动技能Id
	*/
	private int manualSkillId;
	public int getManualSkillId(){
		return manualSkillId;
	}
	public void setManualSkillId(int manualSkillId) {
		this.manualSkillId = manualSkillId;
		index = 4;
		result += index + "*int*" + manualSkillId + "#";
	}

	public void setManualSkillId(int manualSkillId, int bs) {
		this.manualSkillId = manualSkillId;
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
		index = 5;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		经验 当前经验值
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 6;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		0-未使用 >0-已使用
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
		操作时间
	*/
	private String operTime;
	public String getOperTime(){
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
		index = 8;
		result += index + "*String*" + operTime + "#";
	}

	public void setOperTime(String operTime, int bs) {
		this.operTime = operTime;
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

	public InstPlayerManualSkill clone(){
		InstPlayerManualSkill extend=new InstPlayerManualSkill();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setType(this.type);
		extend.setManualSkillId(this.manualSkillId);
		extend.setLevel(this.level);
		extend.setExp(this.exp);
		extend.setIsUse(this.isUse);
		extend.setOperTime(this.operTime);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
