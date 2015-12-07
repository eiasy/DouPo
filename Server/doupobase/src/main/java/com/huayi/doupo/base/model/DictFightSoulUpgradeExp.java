package com.huayi.doupo.base.model;

import java.io.*;

/**
	斗魂升级经验字典表
*/
@SuppressWarnings("serial")
public class DictFightSoulUpgradeExp implements Serializable
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
		斗魂品质Id
	*/
	private int fightSoulQualityId;
	public int getFightSoulQualityId(){
		return fightSoulQualityId;
	}
	public void setFightSoulQualityId(int fightSoulQualityId) {
		this.fightSoulQualityId = fightSoulQualityId;
		index = 2;
		result += index + "*int*" + fightSoulQualityId + "#";
	}

	public void setFightSoulQualityId(int fightSoulQualityId, int bs) {
		this.fightSoulQualityId = fightSoulQualityId;
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
		当前等级达到多少经验能升到下一级
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 4;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
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
		index = 5;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictFightSoulUpgradeExp clone(){
		DictFightSoulUpgradeExp extend=new DictFightSoulUpgradeExp();
		extend.setId(this.id);
		extend.setFightSoulQualityId(this.fightSoulQualityId);
		extend.setLevel(this.level);
		extend.setExp(this.exp);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
