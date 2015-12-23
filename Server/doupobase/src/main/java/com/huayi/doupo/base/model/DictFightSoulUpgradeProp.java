package com.huayi.doupo.base.model;

import java.io.*;

/**
	斗魂升级经验字典表
*/
@SuppressWarnings("serial")
public class DictFightSoulUpgradeProp implements Serializable
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
		斗魂Id
	*/
	private int fightSoulId;
	public int getFightSoulId(){
		return fightSoulId;
	}
	public void setFightSoulId(int fightSoulId) {
		this.fightSoulId = fightSoulId;
		index = 2;
		result += index + "*int*" + fightSoulId + "#";
	}

	public void setFightSoulId(int fightSoulId, int bs) {
		this.fightSoulId = fightSoulId;
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
		index = 3;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		战斗属性值类型 0-整数 1-百分比
	*/
	private int fightPropValueType;
	public int getFightPropValueType(){
		return fightPropValueType;
	}
	public void setFightPropValueType(int fightPropValueType) {
		this.fightPropValueType = fightPropValueType;
		index = 4;
		result += index + "*int*" + fightPropValueType + "#";
	}

	public void setFightPropValueType(int fightPropValueType, int bs) {
		this.fightPropValueType = fightPropValueType;
	}

	/**
		战斗属性值
	*/
	private float fightPropValue;
	public float getFightPropValue(){
		return fightPropValue;
	}
	public void setFightPropValue(float fightPropValue) {
		this.fightPropValue = fightPropValue;
		index = 5;
		result += index + "*float*" + fightPropValue + "#";
	}

	public void setFightPropValue(float fightPropValue, int bs) {
		this.fightPropValue = fightPropValue;
	}

	/**
		战斗属性Id
	*/
	private int fightPropId;
	public int getFightPropId(){
		return fightPropId;
	}
	public void setFightPropId(int fightPropId) {
		this.fightPropId = fightPropId;
		index = 6;
		result += index + "*int*" + fightPropId + "#";
	}

	public void setFightPropId(int fightPropId, int bs) {
		this.fightPropId = fightPropId;
	}

	/**
		增加的战斗力
	*/
	private int fightValue;
	public int getFightValue(){
		return fightValue;
	}
	public void setFightValue(int fightValue) {
		this.fightValue = fightValue;
		index = 7;
		result += index + "*int*" + fightValue + "#";
	}

	public void setFightValue(int fightValue, int bs) {
		this.fightValue = fightValue;
	}

	/**
		出售可获得的银币
	*/
	private int sellSilver;
	public int getSellSilver(){
		return sellSilver;
	}
	public void setSellSilver(int sellSilver) {
		this.sellSilver = sellSilver;
		index = 8;
		result += index + "*int*" + sellSilver + "#";
	}

	public void setSellSilver(int sellSilver, int bs) {
		this.sellSilver = sellSilver;
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
		index = 9;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 10;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictFightSoulUpgradeProp clone(){
		DictFightSoulUpgradeProp extend=new DictFightSoulUpgradeProp();
		extend.setId(this.id);
		extend.setFightSoulId(this.fightSoulId);
		extend.setLevel(this.level);
		extend.setFightPropValueType(this.fightPropValueType);
		extend.setFightPropValue(this.fightPropValue);
		extend.setFightPropId(this.fightPropId);
		extend.setFightValue(this.fightValue);
		extend.setSellSilver(this.sellSilver);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
