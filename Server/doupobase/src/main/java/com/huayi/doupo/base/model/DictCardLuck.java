package com.huayi.doupo.base.model;

import java.io.*;

/**
	卡牌缘分字典表
*/
@SuppressWarnings("serial")
public class DictCardLuck implements Serializable
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
		卡牌Id
	*/
	private int cardId;
	public int getCardId(){
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
		index = 3;
		result += index + "*int*" + cardId + "#";
	}

	public void setCardId(int cardId, int bs) {
		this.cardId = cardId;
	}

	/**
		缘分条件值 tableTypeId_tableFieldId;
	*/
	private String lucks;
	public String getLucks(){
		return lucks;
	}
	public void setLucks(String lucks) {
		this.lucks = lucks;
		index = 4;
		result += index + "*String*" + lucks + "#";
	}

	public void setLucks(String lucks, int bs) {
		this.lucks = lucks;
	}

	/**
		缘分增加战斗属性值 fightPropId_value;
	*/
	private String fightValues;
	public String getFightValues(){
		return fightValues;
	}
	public void setFightValues(String fightValues) {
		this.fightValues = fightValues;
		index = 5;
		result += index + "*String*" + fightValues + "#";
	}

	public void setFightValues(String fightValues, int bs) {
		this.fightValues = fightValues;
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

	public DictCardLuck clone(){
		DictCardLuck extend=new DictCardLuck();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setCardId(this.cardId);
		extend.setLucks(this.lucks);
		extend.setFightValues(this.fightValues);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
