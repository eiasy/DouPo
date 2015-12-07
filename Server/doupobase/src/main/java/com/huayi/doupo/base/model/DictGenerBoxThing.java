package com.huayi.doupo.base.model;

import java.io.*;

/**
	普通宝箱物品字典表
*/
@SuppressWarnings("serial")
public class DictGenerBoxThing implements Serializable
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
		类型 1-铜宝箱 2-银宝箱 3-金宝箱 
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 2;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
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
		index = 3;
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
		index = 4;
		result += index + "*int*" + tableFieldId + "#";
	}

	public void setTableFieldId(int tableFieldId, int bs) {
		this.tableFieldId = tableFieldId;
	}

	/**
		数量
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
		配比
	*/
	private int chance;
	public int getChance(){
		return chance;
	}
	public void setChance(int chance) {
		this.chance = chance;
		index = 6;
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
		index = 7;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictGenerBoxThing clone(){
		DictGenerBoxThing extend=new DictGenerBoxThing();
		extend.setId(this.id);
		extend.setType(this.type);
		extend.setTableTypeId(this.tableTypeId);
		extend.setTableFieldId(this.tableFieldId);
		extend.setValue(this.value);
		extend.setChance(this.chance);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
