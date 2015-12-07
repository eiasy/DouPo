package com.huayi.doupo.base.model;

import java.io.*;

/**
	塔层商店字典表
*/
@SuppressWarnings("serial")
public class DictPagodaStore implements Serializable
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
		类型 1-紫装 2-橙装 3-奖励
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
		值
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
		所需火能
	*/
	private int culture;
	public int getCulture(){
		return culture;
	}
	public void setCulture(int culture) {
		this.culture = culture;
		index = 6;
		result += index + "*int*" + culture + "#";
	}

	public void setCulture(int culture, int bs) {
		this.culture = culture;
	}

	/**
		层数限制
	*/
	private int pagodaStoreyId;
	public int getPagodaStoreyId(){
		return pagodaStoreyId;
	}
	public void setPagodaStoreyId(int pagodaStoreyId) {
		this.pagodaStoreyId = pagodaStoreyId;
		index = 7;
		result += index + "*int*" + pagodaStoreyId + "#";
	}

	public void setPagodaStoreyId(int pagodaStoreyId, int bs) {
		this.pagodaStoreyId = pagodaStoreyId;
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

	public DictPagodaStore clone(){
		DictPagodaStore extend=new DictPagodaStore();
		extend.setId(this.id);
		extend.setType(this.type);
		extend.setTableTypeId(this.tableTypeId);
		extend.setTableFieldId(this.tableFieldId);
		extend.setValue(this.value);
		extend.setCulture(this.culture);
		extend.setPagodaStoreyId(this.pagodaStoreyId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
