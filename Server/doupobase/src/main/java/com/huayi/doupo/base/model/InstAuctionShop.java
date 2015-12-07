package com.huayi.doupo.base.model;

import java.io.*;

/**
	拍卖行实例表
*/
@SuppressWarnings("serial")
public class InstAuctionShop implements Serializable
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
		玩家实例Id
	*/
	private int instPlayerId;
	public int getInstPlayerId(){
		return instPlayerId;
	}
	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
		index = 2;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
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
		出售类型 1-元宝 2-精魂
	*/
	private int sellType;
	public int getSellType(){
		return sellType;
	}
	public void setSellType(int sellType) {
		this.sellType = sellType;
		index = 6;
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
		index = 7;
		result += index + "*int*" + sellValue + "#";
	}

	public void setSellValue(int sellValue, int bs) {
		this.sellValue = sellValue;
	}

	/**
		是否售完 0-未售完 1-售完
	*/
	private int sellOut;
	public int getSellOut(){
		return sellOut;
	}
	public void setSellOut(int sellOut) {
		this.sellOut = sellOut;
		index = 8;
		result += index + "*int*" + sellOut + "#";
	}

	public void setSellOut(int sellOut, int bs) {
		this.sellOut = sellOut;
	}

	/**
		版本号
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

	/**
		添加时间
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 10;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
	}

	/**
		更新时间
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 11;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstAuctionShop clone(){
		InstAuctionShop extend=new InstAuctionShop();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setTableTypeId(this.tableTypeId);
		extend.setTableFieldId(this.tableFieldId);
		extend.setValue(this.value);
		extend.setSellType(this.sellType);
		extend.setSellValue(this.sellValue);
		extend.setSellOut(this.sellOut);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
