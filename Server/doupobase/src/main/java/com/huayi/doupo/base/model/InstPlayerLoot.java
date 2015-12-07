package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家抢夺实例表
*/
@SuppressWarnings("serial")
public class InstPlayerLoot implements Serializable
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
		受保护截止时间
	*/
	private String protectTime;
	public String getProtectTime(){
		return protectTime;
	}
	public void setProtectTime(String protectTime) {
		this.protectTime = protectTime;
		index = 3;
		result += index + "*String*" + protectTime + "#";
	}

	public void setProtectTime(String protectTime, int bs) {
		this.protectTime = protectTime;
	}

	/**
		技能列表 技能Id_拼合次数 分号隔开
	*/
	private String skills;
	public String getSkills(){
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
		index = 4;
		result += index + "*String*" + skills + "#";
	}

	public void setSkills(String skills, int bs) {
		this.skills = skills;
	}

	/**
		功法列表 功法Id_拼合次数 分号隔开
	*/
	private String kungFus;
	public String getKungFus(){
		return kungFus;
	}
	public void setKungFus(String kungFus) {
		this.kungFus = kungFus;
		index = 5;
		result += index + "*String*" + kungFus + "#";
	}

	public void setKungFus(String kungFus, int bs) {
		this.kungFus = kungFus;
	}

	/**
		法宝列表
	*/
	private String magics;
	public String getMagics(){
		return magics;
	}
	public void setMagics(String magics) {
		this.magics = magics;
		index = 6;
		result += index + "*String*" + magics + "#";
	}

	public void setMagics(String magics, int bs) {
		this.magics = magics;
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
		index = 7;
		result += index + "*String*" + operTime + "#";
	}

	public void setOperTime(String operTime, int bs) {
		this.operTime = operTime;
	}

	/**
		抢夺次数
	*/
	private int lootNum;
	public int getLootNum(){
		return lootNum;
	}
	public void setLootNum(int lootNum) {
		this.lootNum = lootNum;
		index = 8;
		result += index + "*int*" + lootNum + "#";
	}

	public void setLootNum(int lootNum, int bs) {
		this.lootNum = lootNum;
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

	public InstPlayerLoot clone(){
		InstPlayerLoot extend=new InstPlayerLoot();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setProtectTime(this.protectTime);
		extend.setSkills(this.skills);
		extend.setKungFus(this.kungFus);
		extend.setMagics(this.magics);
		extend.setOperTime(this.operTime);
		extend.setLootNum(this.lootNum);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
