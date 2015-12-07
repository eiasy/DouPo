package com.huayi.doupo.base.model;

import java.io.*;

/**
	轮回消耗字典表
*/
@SuppressWarnings("serial")
public class DictRestore implements Serializable
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
		品质Id
	*/
	private int qualityId;
	public int getQualityId(){
		return qualityId;
	}
	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
		index = 2;
		result += index + "*int*" + qualityId + "#";
	}

	public void setQualityId(int qualityId, int bs) {
		this.qualityId = qualityId;
	}

	/**
		星级Id
	*/
	private int starLevelId;
	public int getStarLevelId(){
		return starLevelId;
	}
	public void setStarLevelId(int starLevelId) {
		this.starLevelId = starLevelId;
		index = 3;
		result += index + "*int*" + starLevelId + "#";
	}

	public void setStarLevelId(int starLevelId, int bs) {
		this.starLevelId = starLevelId;
	}

	/**
		消耗金币
	*/
	private int gold;
	public int getGold(){
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
		index = 4;
		result += index + "*int*" + gold + "#";
	}

	public void setGold(int gold, int bs) {
		this.gold = gold;
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

	public DictRestore clone(){
		DictRestore extend=new DictRestore();
		extend.setId(this.id);
		extend.setQualityId(this.qualityId);
		extend.setStarLevelId(this.starLevelId);
		extend.setGold(this.gold);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
