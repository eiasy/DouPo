package com.huayi.doupo.base.model;

import java.io.*;

/**
	装备洗练字典表
*/
@SuppressWarnings("serial")
public class DictEquipWash implements Serializable
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
		名字
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 2;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		装备品质Id
	*/
	private int equipQualityId;
	public int getEquipQualityId(){
		return equipQualityId;
	}
	public void setEquipQualityId(int equipQualityId) {
		this.equipQualityId = equipQualityId;
		index = 3;
		result += index + "*int*" + equipQualityId + "#";
	}

	public void setEquipQualityId(int equipQualityId, int bs) {
		this.equipQualityId = equipQualityId;
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
		战斗值
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
		级别
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 6;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		概率
	*/
	private int chance;
	public int getChance(){
		return chance;
	}
	public void setChance(int chance) {
		this.chance = chance;
		index = 7;
		result += index + "*int*" + chance + "#";
	}

	public void setChance(int chance, int bs) {
		this.chance = chance;
	}

	/**
		
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

	public DictEquipWash clone(){
		DictEquipWash extend=new DictEquipWash();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setEquipQualityId(this.equipQualityId);
		extend.setFightPropId(this.fightPropId);
		extend.setValue(this.value);
		extend.setLevel(this.level);
		extend.setChance(this.chance);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
