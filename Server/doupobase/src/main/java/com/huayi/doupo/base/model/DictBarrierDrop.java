package com.huayi.doupo.base.model;

import java.io.*;

/**
	副本关卡掉落字典表
*/
@SuppressWarnings("serial")
public class DictBarrierDrop implements Serializable
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
		副本关卡Id
	*/
	private int barrierId;
	public int getBarrierId(){
		return barrierId;
	}
	public void setBarrierId(int barrierId) {
		this.barrierId = barrierId;
		index = 2;
		result += index + "*int*" + barrierId + "#";
	}

	public void setBarrierId(int barrierId, int bs) {
		this.barrierId = barrierId;
	}

	/**
		0-普通 1-特殊
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 3;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		掉落类型
	*/
	private int type2;
	public int getType2(){
		return type2;
	}
	public void setType2(int type2) {
		this.type2 = type2;
		index = 4;
		result += index + "*int*" + type2 + "#";
	}

	public void setType2(int type2, int bs) {
		this.type2 = type2;
	}

	/**
		表类型Id
	*/
	private int tableTypeId;
	public int getTableTypeId(){
		return tableTypeId;
	}
	public void setTableTypeId(int tableTypeId) {
		this.tableTypeId = tableTypeId;
		index = 5;
		result += index + "*int*" + tableTypeId + "#";
	}

	public void setTableTypeId(int tableTypeId, int bs) {
		this.tableTypeId = tableTypeId;
	}

	/**
		表字段Id
	*/
	private int tableFieldId;
	public int getTableFieldId(){
		return tableFieldId;
	}
	public void setTableFieldId(int tableFieldId) {
		this.tableFieldId = tableFieldId;
		index = 6;
		result += index + "*int*" + tableFieldId + "#";
	}

	public void setTableFieldId(int tableFieldId, int bs) {
		this.tableFieldId = tableFieldId;
	}

	/**
		值   卡牌-等级
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
		配比
	*/
	private int chance;
	public int getChance(){
		return chance;
	}
	public void setChance(int chance) {
		this.chance = chance;
		index = 8;
		result += index + "*int*" + chance + "#";
	}

	public void setChance(int chance, int bs) {
		this.chance = chance;
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

	public DictBarrierDrop clone(){
		DictBarrierDrop extend=new DictBarrierDrop();
		extend.setId(this.id);
		extend.setBarrierId(this.barrierId);
		extend.setType(this.type);
		extend.setType2(this.type2);
		extend.setTableTypeId(this.tableTypeId);
		extend.setTableFieldId(this.tableFieldId);
		extend.setValue(this.value);
		extend.setChance(this.chance);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
