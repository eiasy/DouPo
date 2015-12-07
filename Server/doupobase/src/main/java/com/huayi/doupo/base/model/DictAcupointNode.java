package com.huayi.doupo.base.model;

import java.io.*;

/**
	经脉节点字典表
*/
@SuppressWarnings("serial")
public class DictAcupointNode implements Serializable
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
		经脉Id
	*/
	private int acupointId;
	public int getAcupointId(){
		return acupointId;
	}
	public void setAcupointId(int acupointId) {
		this.acupointId = acupointId;
		index = 2;
		result += index + "*int*" + acupointId + "#";
	}

	public void setAcupointId(int acupointId, int bs) {
		this.acupointId = acupointId;
	}

	/**
		经脉重数
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
		节点
	*/
	private int node;
	public int getNode(){
		return node;
	}
	public void setNode(int node) {
		this.node = node;
		index = 4;
		result += index + "*int*" + node + "#";
	}

	public void setNode(int node, int bs) {
		this.node = node;
	}

	/**
		所需修为
	*/
	private int culture;
	public int getCulture(){
		return culture;
	}
	public void setCulture(int culture) {
		this.culture = culture;
		index = 5;
		result += index + "*int*" + culture + "#";
	}

	public void setCulture(int culture, int bs) {
		this.culture = culture;
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
	private int value;
	public int getValue(){
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		index = 7;
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

	public DictAcupointNode clone(){
		DictAcupointNode extend=new DictAcupointNode();
		extend.setId(this.id);
		extend.setAcupointId(this.acupointId);
		extend.setTier(this.tier);
		extend.setNode(this.node);
		extend.setCulture(this.culture);
		extend.setFightPropId(this.fightPropId);
		extend.setValue(this.value);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
