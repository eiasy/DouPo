package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class DictEquipAdvancered implements Serializable
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
		装备Id
	*/
	private int equipId;
	public int getEquipId(){
		return equipId;
	}
	public void setEquipId(int equipId) {
		this.equipId = equipId;
		index = 2;
		result += index + "*int*" + equipId + "#";
	}

	public void setEquipId(int equipId, int bs) {
		this.equipId = equipId;
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
		index = 3;
		result += index + "*int*" + starLevel + "#";
	}

	public void setStarLevel(int starLevel, int bs) {
		this.starLevel = starLevel;
	}

	/**
		淬炼值
	*/
	private String contions;
	public String getContions(){
		return contions;
	}
	public void setContions(String contions) {
		this.contions = contions;
		index = 4;
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
		index = 5;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictEquipAdvancered clone(){
		DictEquipAdvancered extend=new DictEquipAdvancered();
		extend.setId(this.id);
		extend.setEquipId(this.equipId);
		extend.setStarLevel(this.starLevel);
		extend.setContions(this.contions);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
