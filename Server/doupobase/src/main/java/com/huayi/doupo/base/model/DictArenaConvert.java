package com.huayi.doupo.base.model;

import java.io.*;

/**
	竞技场兑换字典表
*/
@SuppressWarnings("serial")
public class DictArenaConvert implements Serializable
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
		威望值
	*/
	private int prestige;
	public int getPrestige(){
		return prestige;
	}
	public void setPrestige(int prestige) {
		this.prestige = prestige;
		index = 5;
		result += index + "*int*" + prestige + "#";
	}

	public void setPrestige(int prestige, int bs) {
		this.prestige = prestige;
	}

	/**
		兑换类型 1-总共 2-每天
	*/
	private int convertType;
	public int getConvertType(){
		return convertType;
	}
	public void setConvertType(int convertType) {
		this.convertType = convertType;
		index = 6;
		result += index + "*int*" + convertType + "#";
	}

	public void setConvertType(int convertType, int bs) {
		this.convertType = convertType;
	}

	/**
		兑换次数
	*/
	private int convertNum;
	public int getConvertNum(){
		return convertNum;
	}
	public void setConvertNum(int convertNum) {
		this.convertNum = convertNum;
		index = 7;
		result += index + "*int*" + convertNum + "#";
	}

	public void setConvertNum(int convertNum, int bs) {
		this.convertNum = convertNum;
	}

	/**
		玩家等级
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 8;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		排序
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 9;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
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
		index = 10;
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
		index = 11;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictArenaConvert clone(){
		DictArenaConvert extend=new DictArenaConvert();
		extend.setId(this.id);
		extend.setTableTypeId(this.tableTypeId);
		extend.setTableFieldId(this.tableFieldId);
		extend.setValue(this.value);
		extend.setPrestige(this.prestige);
		extend.setConvertType(this.convertType);
		extend.setConvertNum(this.convertNum);
		extend.setLevel(this.level);
		extend.setRank(this.rank);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
