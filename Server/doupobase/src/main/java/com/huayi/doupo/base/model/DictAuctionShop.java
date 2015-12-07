package com.huayi.doupo.base.model;

import java.io.*;

/**
	拍卖行字典表
*/
@SuppressWarnings("serial")
public class DictAuctionShop implements Serializable
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
		表类型Id
	*/
	private int tableTypeId;
	public int getTableTypeId(){
		return tableTypeId;
	}
	public void setTableTypeId(int tableTypeId) {
		this.tableTypeId = tableTypeId;
		index = 2;
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
		index = 3;
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
		index = 4;
		result += index + "*int*" + value + "#";
	}

	public void setValue(int value, int bs) {
		this.value = value;
	}

	/**
		出售类型 1-元宝 2-精魂
	*/
	private int sellType;
	public int getSellType(){
		return sellType;
	}
	public void setSellType(int sellType) {
		this.sellType = sellType;
		index = 5;
		result += index + "*int*" + sellType + "#";
	}

	public void setSellType(int sellType, int bs) {
		this.sellType = sellType;
	}

	/**
		出售值
	*/
	private int sellValue;
	public int getSellValue(){
		return sellValue;
	}
	public void setSellValue(int sellValue) {
		this.sellValue = sellValue;
		index = 6;
		result += index + "*int*" + sellValue + "#";
	}

	public void setSellValue(int sellValue, int bs) {
		this.sellValue = sellValue;
	}

	/**
		必出 0-不出 1-必出
	*/
	private int isOut;
	public int getIsOut(){
		return isOut;
	}
	public void setIsOut(int isOut) {
		this.isOut = isOut;
		index = 7;
		result += index + "*int*" + isOut + "#";
	}

	public void setIsOut(int isOut, int bs) {
		this.isOut = isOut;
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

	public DictAuctionShop clone(){
		DictAuctionShop extend=new DictAuctionShop();
		extend.setId(this.id);
		extend.setTableTypeId(this.tableTypeId);
		extend.setTableFieldId(this.tableFieldId);
		extend.setValue(this.value);
		extend.setSellType(this.sellType);
		extend.setSellValue(this.sellValue);
		extend.setIsOut(this.isOut);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
