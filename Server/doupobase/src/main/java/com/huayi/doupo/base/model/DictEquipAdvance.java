package com.huayi.doupo.base.model;

import java.io.*;

/**
	装备进阶字典表
*/
@SuppressWarnings("serial")
public class DictEquipAdvance implements Serializable
{
	private int index;
	public String result = "";
	/**
		等级
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
		装备类型Id
	*/
	private int equipTypeId;
	public int getEquipTypeId(){
		return equipTypeId;
	}
	public void setEquipTypeId(int equipTypeId) {
		this.equipTypeId = equipTypeId;
		index = 2;
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
		index = 3;
		result += index + "*int*" + equipQualityId + "#";
	}

	public void setEquipQualityId(int equipQualityId, int bs) {
		this.equipQualityId = equipQualityId;
	}

	/**
		进阶星级
	*/
	private int starLevel;
	public int getStarLevel(){
		return starLevel;
	}
	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
		index = 4;
		result += index + "*int*" + starLevel + "#";
	}

	public void setStarLevel(int starLevel, int bs) {
		this.starLevel = starLevel;
	}

	/**
		装备升级加成的加成
	*/
	private String propAndAdd;
	public String getPropAndAdd(){
		return propAndAdd;
	}
	public void setPropAndAdd(String propAndAdd) {
		this.propAndAdd = propAndAdd;
		index = 5;
		result += index + "*String*" + propAndAdd + "#";
	}

	public void setPropAndAdd(String propAndAdd, int bs) {
		this.propAndAdd = propAndAdd;
	}

	/**
		条件 装备数量_需要的银币;
	*/
	private String contions;
	public String getContions(){
		return contions;
	}
	public void setContions(String contions) {
		this.contions = contions;
		index = 6;
		result += index + "*String*" + contions + "#";
	}

	public void setContions(String contions, int bs) {
		this.contions = contions;
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

	public DictEquipAdvance clone(){
		DictEquipAdvance extend=new DictEquipAdvance();
		extend.setId(this.id);
		extend.setEquipTypeId(this.equipTypeId);
		extend.setEquipQualityId(this.equipQualityId);
		extend.setStarLevel(this.starLevel);
		extend.setPropAndAdd(this.propAndAdd);
		extend.setContions(this.contions);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
