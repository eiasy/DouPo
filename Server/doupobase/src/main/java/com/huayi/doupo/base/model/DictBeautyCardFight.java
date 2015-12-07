package com.huayi.doupo.base.model;

import java.io.*;

/**
	美人等级属性字典表
*/
@SuppressWarnings("serial")
public class DictBeautyCardFight implements Serializable
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
		美人Id
	*/
	private int beautyCardId;
	public int getBeautyCardId(){
		return beautyCardId;
	}
	public void setBeautyCardId(int beautyCardId) {
		this.beautyCardId = beautyCardId;
		index = 2;
		result += index + "*int*" + beautyCardId + "#";
	}

	public void setBeautyCardId(int beautyCardId, int bs) {
		this.beautyCardId = beautyCardId;
	}

	/**
		美人经验Id
	*/
	private int beautyCardExpId;
	public int getBeautyCardExpId(){
		return beautyCardExpId;
	}
	public void setBeautyCardExpId(int beautyCardExpId) {
		this.beautyCardExpId = beautyCardExpId;
		index = 3;
		result += index + "*int*" + beautyCardExpId + "#";
	}

	public void setBeautyCardExpId(int beautyCardExpId, int bs) {
		this.beautyCardExpId = beautyCardExpId;
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
		index = 4;
		result += index + "*int*" + fightPropId + "#";
	}

	public void setFightPropId(int fightPropId, int bs) {
		this.fightPropId = fightPropId;
	}

	/**
		战斗属性值
	*/
	private int value;
	public int getValue(){
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		index = 5;
		result += index + "*int*" + value + "#";
	}

	public void setValue(int value, int bs) {
		this.value = value;
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
		index = 6;
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
		index = 7;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictBeautyCardFight clone(){
		DictBeautyCardFight extend=new DictBeautyCardFight();
		extend.setId(this.id);
		extend.setBeautyCardId(this.beautyCardId);
		extend.setBeautyCardExpId(this.beautyCardExpId);
		extend.setFightPropId(this.fightPropId);
		extend.setValue(this.value);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
