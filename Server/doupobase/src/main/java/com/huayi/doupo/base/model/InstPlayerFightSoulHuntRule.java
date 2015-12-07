package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家装备实例表
*/
@SuppressWarnings("serial")
public class InstPlayerFightSoulHuntRule implements Serializable
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
		斗魂猎取Id
	*/
	private int fightSouleHuntRuleId;
	public int getFightSouleHuntRuleId(){
		return fightSouleHuntRuleId;
	}
	public void setFightSouleHuntRuleId(int fightSouleHuntRuleId) {
		this.fightSouleHuntRuleId = fightSouleHuntRuleId;
		index = 3;
		result += index + "*int*" + fightSouleHuntRuleId + "#";
	}

	public void setFightSouleHuntRuleId(int fightSouleHuntRuleId, int bs) {
		this.fightSouleHuntRuleId = fightSouleHuntRuleId;
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
		index = 4;
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
		index = 5;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerFightSoulHuntRule clone(){
		InstPlayerFightSoulHuntRule extend=new InstPlayerFightSoulHuntRule();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setFightSouleHuntRuleId(this.fightSouleHuntRuleId);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
