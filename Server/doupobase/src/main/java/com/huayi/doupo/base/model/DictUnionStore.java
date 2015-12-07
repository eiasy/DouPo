package com.huayi.doupo.base.model;

import java.io.*;

/**
	联盟商店
*/
@SuppressWarnings("serial")
public class DictUnionStore implements Serializable
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
		类型 1-道具 2-限时 3-奖励
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
		等级限制
	*/
	private int unionLevel;
	public int getUnionLevel(){
		return unionLevel;
	}
	public void setUnionLevel(int unionLevel) {
		this.unionLevel = unionLevel;
		index = 6;
		result += index + "*int*" + unionLevel + "#";
	}

	public void setUnionLevel(int unionLevel, int bs) {
		this.unionLevel = unionLevel;
	}

	/**
		所需贡献值
	*/
	private int buyOffer;
	public int getBuyOffer(){
		return buyOffer;
	}
	public void setBuyOffer(int buyOffer) {
		this.buyOffer = buyOffer;
		index = 7;
		result += index + "*int*" + buyOffer + "#";
	}

	public void setBuyOffer(int buyOffer, int bs) {
		this.buyOffer = buyOffer;
	}

	/**
		所需元宝
	*/
	private int buyGold;
	public int getBuyGold(){
		return buyGold;
	}
	public void setBuyGold(int buyGold) {
		this.buyGold = buyGold;
		index = 8;
		result += index + "*int*" + buyGold + "#";
	}

	public void setBuyGold(int buyGold, int bs) {
		this.buyGold = buyGold;
	}

	/**
		出售类型 1-一次性 2-每天
	*/
	private int sellType;
	public int getSellType(){
		return sellType;
	}
	public void setSellType(int sellType) {
		this.sellType = sellType;
		index = 9;
		result += index + "*int*" + sellType + "#";
	}

	public void setSellType(int sellType, int bs) {
		this.sellType = sellType;
	}

	/**
		出售次数
	*/
	private int sellValue;
	public int getSellValue(){
		return sellValue;
	}
	public void setSellValue(int sellValue) {
		this.sellValue = sellValue;
		index = 10;
		result += index + "*int*" + sellValue + "#";
	}

	public void setSellValue(int sellValue, int bs) {
		this.sellValue = sellValue;
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
		index = 11;
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
		index = 12;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictUnionStore clone(){
		DictUnionStore extend=new DictUnionStore();
		extend.setId(this.id);
		extend.setType(this.type);
		extend.setTableTypeId(this.tableTypeId);
		extend.setTableFieldId(this.tableFieldId);
		extend.setValue(this.value);
		extend.setUnionLevel(this.unionLevel);
		extend.setBuyOffer(this.buyOffer);
		extend.setBuyGold(this.buyGold);
		extend.setSellType(this.sellType);
		extend.setSellValue(this.sellValue);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
