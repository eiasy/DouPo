package com.huayi.doupo.base.model;

import java.io.*;

/**
	丹药字典表
*/
@SuppressWarnings("serial")
public class DictPill implements Serializable
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
		小头像Id
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 3;
		result += index + "*int*" + smallUiId + "#";
	}

	public void setSmallUiId(int smallUiId, int bs) {
		this.smallUiId = smallUiId;
	}

	/**
		大头像Id
	*/
	private int bigUiId;
	public int getBigUiId(){
		return bigUiId;
	}
	public void setBigUiId(int bigUiId) {
		this.bigUiId = bigUiId;
		index = 4;
		result += index + "*int*" + bigUiId + "#";
	}

	public void setBigUiId(int bigUiId, int bs) {
		this.bigUiId = bigUiId;
	}

	/**
		丹药类型Id
	*/
	private int pillTypeId;
	public int getPillTypeId(){
		return pillTypeId;
	}
	public void setPillTypeId(int pillTypeId) {
		this.pillTypeId = pillTypeId;
		index = 5;
		result += index + "*int*" + pillTypeId + "#";
	}

	public void setPillTypeId(int pillTypeId, int bs) {
		this.pillTypeId = pillTypeId;
	}

	/**
		丹药品阶Id
	*/
	private int pillQualityId;
	public int getPillQualityId(){
		return pillQualityId;
	}
	public void setPillQualityId(int pillQualityId) {
		this.pillQualityId = pillQualityId;
		index = 6;
		result += index + "*int*" + pillQualityId + "#";
	}

	public void setPillQualityId(int pillQualityId, int bs) {
		this.pillQualityId = pillQualityId;
	}

	/**
		药方Id
	*/
	private int prescriptId;
	public int getPrescriptId(){
		return prescriptId;
	}
	public void setPrescriptId(int prescriptId) {
		this.prescriptId = prescriptId;
		index = 7;
		result += index + "*int*" + prescriptId + "#";
	}

	public void setPrescriptId(int prescriptId, int bs) {
		this.prescriptId = prescriptId;
	}

	/**
		卡牌等级
	*/
	private int cardlevel;
	public int getCardlevel(){
		return cardlevel;
	}
	public void setCardlevel(int cardlevel) {
		this.cardlevel = cardlevel;
		index = 8;
		result += index + "*int*" + cardlevel + "#";
	}

	public void setCardlevel(int cardlevel, int bs) {
		this.cardlevel = cardlevel;
	}

	/**
		表字段Id
	*/
	private int tableTypeId;
	public int getTableTypeId(){
		return tableTypeId;
	}
	public void setTableTypeId(int tableTypeId) {
		this.tableTypeId = tableTypeId;
		index = 9;
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
		index = 10;
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
		index = 11;
		result += index + "*int*" + value + "#";
	}

	public void setValue(int value, int bs) {
		this.value = value;
	}

	/**
		出售价格
	*/
	private int sellCopper;
	public int getSellCopper(){
		return sellCopper;
	}
	public void setSellCopper(int sellCopper) {
		this.sellCopper = sellCopper;
		index = 12;
		result += index + "*int*" + sellCopper + "#";
	}

	public void setSellCopper(int sellCopper, int bs) {
		this.sellCopper = sellCopper;
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
		index = 13;
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
		index = 14;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictPill clone(){
		DictPill extend=new DictPill();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setPillTypeId(this.pillTypeId);
		extend.setPillQualityId(this.pillQualityId);
		extend.setPrescriptId(this.prescriptId);
		extend.setCardlevel(this.cardlevel);
		extend.setTableTypeId(this.tableTypeId);
		extend.setTableFieldId(this.tableFieldId);
		extend.setValue(this.value);
		extend.setSellCopper(this.sellCopper);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
