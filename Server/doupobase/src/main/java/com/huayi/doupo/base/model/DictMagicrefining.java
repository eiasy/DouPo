package com.huayi.doupo.base.model;

import java.io.*;

/**
	功法法宝精炼
*/
@SuppressWarnings("serial")
public class DictMagicrefining implements Serializable
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
		宝物d
	*/
	private int MagicId;
	public int getMagicId(){
		return MagicId;
	}
	public void setMagicId(int MagicId) {
		this.MagicId = MagicId;
		index = 2;
		result += index + "*int*" + MagicId + "#";
	}

	public void setMagicId(int MagicId, int bs) {
		this.MagicId = MagicId;
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
		战斗属性Id
	*/
	private int fightPropId;
	public int getFightPropId(){
		return fightPropId;
	}
	public void setFightPropId(int fightPropId) {
		this.fightPropId = fightPropId;
		index = 4;
		result += index + "*int*" + fightPropId + "#";
	}

	public void setFightPropId(int fightPropId, int bs) {
		this.fightPropId = fightPropId;
	}

	/**
		战斗值
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
		强化上限
	*/
	private int maxStrengthen;
	public int getMaxStrengthen(){
		return maxStrengthen;
	}
	public void setMaxStrengthen(int maxStrengthen) {
		this.maxStrengthen = maxStrengthen;
		index = 6;
		result += index + "*int*" + maxStrengthen + "#";
	}

	public void setMaxStrengthen(int maxStrengthen, int bs) {
		this.maxStrengthen = maxStrengthen;
	}

	/**
		条件物品 tableTypeId_tableFieldId_tableValue;
	*/
	private String contions;
	public String getContions(){
		return contions;
	}
	public void setContions(String contions) {
		this.contions = contions;
		index = 7;
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

	public DictMagicrefining clone(){
		DictMagicrefining extend=new DictMagicrefining();
		extend.setId(this.id);
		extend.setMagicId(this.MagicId);
		extend.setStarLevel(this.starLevel);
		extend.setFightPropId(this.fightPropId);
		extend.setValue(this.value);
		extend.setMaxStrengthen(this.maxStrengthen);
		extend.setContions(this.contions);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
