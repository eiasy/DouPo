package com.huayi.doupo.base.model;

import java.io.*;

/**
	箱子计数实例表
*/
@SuppressWarnings("serial")
public class InstPlayerBoxCount implements Serializable
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
		开箱子值
	*/
	private float openValue;
	public float getOpenValue(){
		return openValue;
	}
	public void setOpenValue(float openValue) {
		this.openValue = openValue;
		index = 3;
		result += index + "*float*" + openValue + "#";
	}

	public void setOpenValue(float openValue, int bs) {
		this.openValue = openValue;
	}

	/**
		特殊箱子规则Id
	*/
	private int specialBoxRuleId;
	public int getSpecialBoxRuleId(){
		return specialBoxRuleId;
	}
	public void setSpecialBoxRuleId(int specialBoxRuleId) {
		this.specialBoxRuleId = specialBoxRuleId;
		index = 4;
		result += index + "*int*" + specialBoxRuleId + "#";
	}

	public void setSpecialBoxRuleId(int specialBoxRuleId, int bs) {
		this.specialBoxRuleId = specialBoxRuleId;
	}

	/**
		是否命中 0-未命中  1-命中
	*/
	private int isHit;
	public int getIsHit(){
		return isHit;
	}
	public void setIsHit(int isHit) {
		this.isHit = isHit;
		index = 5;
		result += index + "*int*" + isHit + "#";
	}

	public void setIsHit(int isHit, int bs) {
		this.isHit = isHit;
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

	public InstPlayerBoxCount clone(){
		InstPlayerBoxCount extend=new InstPlayerBoxCount();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setOpenValue(this.openValue);
		extend.setSpecialBoxRuleId(this.specialBoxRuleId);
		extend.setIsHit(this.isHit);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
