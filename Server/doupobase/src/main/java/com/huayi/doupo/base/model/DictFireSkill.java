package com.huayi.doupo.base.model;

import java.io.*;

/**
	异火技能字典表
*/
@SuppressWarnings("serial")
public class DictFireSkill implements Serializable
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
		小图标
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 2;
		result += index + "*int*" + smallUiId + "#";
	}

	public void setSmallUiId(int smallUiId, int bs) {
		this.smallUiId = smallUiId;
	}

	/**
		大图标
	*/
	private int bigUiId;
	public int getBigUiId(){
		return bigUiId;
	}
	public void setBigUiId(int bigUiId) {
		this.bigUiId = bigUiId;
		index = 3;
		result += index + "*int*" + bigUiId + "#";
	}

	public void setBigUiId(int bigUiId, int bs) {
		this.bigUiId = bigUiId;
	}

	/**
		名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 4;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		异火技能品质Id
	*/
	private int fireSkillQualityId;
	public int getFireSkillQualityId(){
		return fireSkillQualityId;
	}
	public void setFireSkillQualityId(int fireSkillQualityId) {
		this.fireSkillQualityId = fireSkillQualityId;
		index = 5;
		result += index + "*int*" + fireSkillQualityId + "#";
	}

	public void setFireSkillQualityId(int fireSkillQualityId, int bs) {
		this.fireSkillQualityId = fireSkillQualityId;
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
		战斗属性值
	*/
	private int fightPropValue;
	public int getFightPropValue(){
		return fightPropValue;
	}
	public void setFightPropValue(int fightPropValue) {
		this.fightPropValue = fightPropValue;
		index = 7;
		result += index + "*int*" + fightPropValue + "#";
	}

	public void setFightPropValue(int fightPropValue, int bs) {
		this.fightPropValue = fightPropValue;
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

	public String getResult(){
		return result;
	}

	public DictFireSkill clone(){
		DictFireSkill extend=new DictFireSkill();
		extend.setId(this.id);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setName(this.name);
		extend.setFireSkillQualityId(this.fireSkillQualityId);
		extend.setFightPropId(this.fightPropId);
		extend.setFightPropValue(this.fightPropValue);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
