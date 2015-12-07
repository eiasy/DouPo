package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家具体称号字典表
*/
@SuppressWarnings("serial")
public class DictTitleDetail implements Serializable
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
		称号Id
	*/
	private int titleId;
	public int getTitleId(){
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
		index = 3;
		result += index + "*int*" + titleId + "#";
	}

	public void setTitleId(int titleId, int bs) {
		this.titleId = titleId;
	}

	/**
		数值
	*/
	private int value;
	public int getValue(){
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		index = 4;
		result += index + "*int*" + value + "#";
	}

	public void setValue(int value, int bs) {
		this.value = value;
	}

	/**
		消耗境界丹
	*/
	private int useTalentValue;
	public int getUseTalentValue(){
		return useTalentValue;
	}
	public void setUseTalentValue(int useTalentValue) {
		this.useTalentValue = useTalentValue;
		index = 5;
		result += index + "*int*" + useTalentValue + "#";
	}

	public void setUseTalentValue(int useTalentValue, int bs) {
		this.useTalentValue = useTalentValue;
	}

	/**
		效果 fightPropId_value,多个效果用分号分开例如:2_100;3_%50
	*/
	private String effects;
	public String getEffects(){
		return effects;
	}
	public void setEffects(String effects) {
		this.effects = effects;
		index = 6;
		result += index + "*String*" + effects + "#";
	}

	public void setEffects(String effects, int bs) {
		this.effects = effects;
	}

	/**
		称号升级增加潜力值
	*/
	private int conditions;
	public int getConditions(){
		return conditions;
	}
	public void setConditions(int conditions) {
		this.conditions = conditions;
		index = 7;
		result += index + "*int*" + conditions + "#";
	}

	public void setConditions(int conditions, int bs) {
		this.conditions = conditions;
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
		境界消耗描述
	*/
	private String cost;
	public String getCost(){
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
		index = 9;
		result += index + "*String*" + cost + "#";
	}

	public void setCost(String cost, int bs) {
		this.cost = cost;
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

	public DictTitleDetail clone(){
		DictTitleDetail extend=new DictTitleDetail();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setTitleId(this.titleId);
		extend.setValue(this.value);
		extend.setUseTalentValue(this.useTalentValue);
		extend.setEffects(this.effects);
		extend.setConditions(this.conditions);
		extend.setDescription(this.description);
		extend.setCost(this.cost);
		extend.setVersion(this.version);
		return extend;
	}
}
