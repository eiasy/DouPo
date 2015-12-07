package com.huayi.doupo.base.model;

import java.io.*;

/**
	斗魂字典表
*/
@SuppressWarnings("serial")
public class DictFightSoul implements Serializable
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
		名称
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
		资源特效 有多个用;分开
	*/
	private String effects;
	public String getEffects(){
		return effects;
	}
	public void setEffects(String effects) {
		this.effects = effects;
		index = 3;
		result += index + "*String*" + effects + "#";
	}

	public void setEffects(String effects, int bs) {
		this.effects = effects;
	}

	/**
		所属斗魂品质Id
	*/
	private int fightSoulQualityId;
	public int getFightSoulQualityId(){
		return fightSoulQualityId;
	}
	public void setFightSoulQualityId(int fightSoulQualityId) {
		this.fightSoulQualityId = fightSoulQualityId;
		index = 4;
		result += index + "*int*" + fightSoulQualityId + "#";
	}

	public void setFightSoulQualityId(int fightSoulQualityId, int bs) {
		this.fightSoulQualityId = fightSoulQualityId;
	}

	/**
		是否为经验斗魂 0-不是 1-是
	*/
	private int isExpFightSoul;
	public int getIsExpFightSoul(){
		return isExpFightSoul;
	}
	public void setIsExpFightSoul(int isExpFightSoul) {
		this.isExpFightSoul = isExpFightSoul;
		index = 5;
		result += index + "*int*" + isExpFightSoul + "#";
	}

	public void setIsExpFightSoul(int isExpFightSoul, int bs) {
		this.isExpFightSoul = isExpFightSoul;
	}

	/**
		初始经验
	*/
	private int initExp;
	public int getInitExp(){
		return initExp;
	}
	public void setInitExp(int initExp) {
		this.initExp = initExp;
		index = 6;
		result += index + "*int*" + initExp + "#";
	}

	public void setInitExp(int initExp, int bs) {
		this.initExp = initExp;
	}

	/**
		战队达到多少等级能抽到
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 7;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		权重
	*/
	private int weight;
	public int getWeight(){
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
		index = 8;
		result += index + "*int*" + weight + "#";
	}

	public void setWeight(int weight, int bs) {
		this.weight = weight;
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

	public DictFightSoul clone(){
		DictFightSoul extend=new DictFightSoul();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setEffects(this.effects);
		extend.setFightSoulQualityId(this.fightSoulQualityId);
		extend.setIsExpFightSoul(this.isExpFightSoul);
		extend.setInitExp(this.initExp);
		extend.setLevel(this.level);
		extend.setWeight(this.weight);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
