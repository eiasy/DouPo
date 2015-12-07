package com.huayi.doupo.base.model;

import java.io.*;

/**
	装备字典表
*/
@SuppressWarnings("serial")
public class DictEquipment implements Serializable
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
		装备类型Id
	*/
	private int equipTypeId;
	public int getEquipTypeId(){
		return equipTypeId;
	}
	public void setEquipTypeId(int equipTypeId) {
		this.equipTypeId = equipTypeId;
		index = 5;
		result += index + "*int*" + equipTypeId + "#";
	}

	public void setEquipTypeId(int equipTypeId, int bs) {
		this.equipTypeId = equipTypeId;
	}

	/**
		装备品质Id
	*/
	private int equipQualityId;
	public int getEquipQualityId(){
		return equipQualityId;
	}
	public void setEquipQualityId(int equipQualityId) {
		this.equipQualityId = equipQualityId;
		index = 6;
		result += index + "*int*" + equipQualityId + "#";
	}

	public void setEquipQualityId(int equipQualityId, int bs) {
		this.equipQualityId = equipQualityId;
	}

	/**
		品级（品质的细分）
	*/
	private int qualityLevel;
	public int getQualityLevel(){
		return qualityLevel;
	}
	public void setQualityLevel(int qualityLevel) {
		this.qualityLevel = qualityLevel;
		index = 7;
		result += index + "*int*" + qualityLevel + "#";
	}

	public void setQualityLevel(int qualityLevel, int bs) {
		this.qualityLevel = qualityLevel;
	}

	/**
		消耗洗练石变更为分解得到的火能石（一个碎片的）

	*/
	private int useTalentValue;
	public int getUseTalentValue(){
		return useTalentValue;
	}
	public void setUseTalentValue(int useTalentValue) {
		this.useTalentValue = useTalentValue;
		index = 8;
		result += index + "*int*" + useTalentValue + "#";
	}

	public void setUseTalentValue(int useTalentValue, int bs) {
		this.useTalentValue = useTalentValue;
	}

	/**
		携带的初始属性及升级加成 fightPropId_initValue_addValue;多个用分号分开
	*/
	private String propAndAdd;
	public String getPropAndAdd(){
		return propAndAdd;
	}
	public void setPropAndAdd(String propAndAdd) {
		this.propAndAdd = propAndAdd;
		index = 9;
		result += index + "*String*" + propAndAdd + "#";
	}

	public void setPropAndAdd(String propAndAdd, int bs) {
		this.propAndAdd = propAndAdd;
	}

	/**
		售价
	*/
	private int sellPrice;
	public int getSellPrice(){
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
		index = 10;
		result += index + "*int*" + sellPrice + "#";
	}

	public void setSellPrice(int sellPrice, int bs) {
		this.sellPrice = sellPrice;
	}

	/**
		初始进阶字典Id
	*/
	private int equipAdvanceId;
	public int getEquipAdvanceId(){
		return equipAdvanceId;
	}
	public void setEquipAdvanceId(int equipAdvanceId) {
		this.equipAdvanceId = equipAdvanceId;
		index = 11;
		result += index + "*int*" + equipAdvanceId + "#";
	}

	public void setEquipAdvanceId(int equipAdvanceId, int bs) {
		this.equipAdvanceId = equipAdvanceId;
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
		index = 12;
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
		index = 13;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictEquipment clone(){
		DictEquipment extend=new DictEquipment();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setEquipTypeId(this.equipTypeId);
		extend.setEquipQualityId(this.equipQualityId);
		extend.setQualityLevel(this.qualityLevel);
		extend.setUseTalentValue(this.useTalentValue);
		extend.setPropAndAdd(this.propAndAdd);
		extend.setSellPrice(this.sellPrice);
		extend.setEquipAdvanceId(this.equipAdvanceId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
