package com.huayi.doupo.base.model;

import java.io.*;

/**
	功法每重加成字典表
*/
@SuppressWarnings("serial")
public class DictKungFuTierAdd implements Serializable
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
		功法Id
	*/
	private int kungFuId;
	public int getKungFuId(){
		return kungFuId;
	}
	public void setKungFuId(int kungFuId) {
		this.kungFuId = kungFuId;
		index = 2;
		result += index + "*int*" + kungFuId + "#";
	}

	public void setKungFuId(int kungFuId, int bs) {
		this.kungFuId = kungFuId;
	}

	/**
		功法重数
	*/
	private int tier;
	public int getTier(){
		return tier;
	}
	public void setTier(int tier) {
		this.tier = tier;
		index = 3;
		result += index + "*int*" + tier + "#";
	}

	public void setTier(int tier, int bs) {
		this.tier = tier;
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

	public DictKungFuTierAdd clone(){
		DictKungFuTierAdd extend=new DictKungFuTierAdd();
		extend.setId(this.id);
		extend.setKungFuId(this.kungFuId);
		extend.setTier(this.tier);
		extend.setFightPropId(this.fightPropId);
		extend.setValue(this.value);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
