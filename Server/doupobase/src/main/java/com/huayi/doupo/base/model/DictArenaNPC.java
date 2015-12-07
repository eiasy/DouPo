package com.huayi.doupo.base.model;

import java.io.*;

/**
	竞技场NPC生成字典表
*/
@SuppressWarnings("serial")
public class DictArenaNPC implements Serializable
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
		排名下限
	*/
	private int downRank;
	public int getDownRank(){
		return downRank;
	}
	public void setDownRank(int downRank) {
		this.downRank = downRank;
		index = 2;
		result += index + "*int*" + downRank + "#";
	}

	public void setDownRank(int downRank, int bs) {
		this.downRank = downRank;
	}

	/**
		排名上限
	*/
	private int upRank;
	public int getUpRank(){
		return upRank;
	}
	public void setUpRank(int upRank) {
		this.upRank = upRank;
		index = 3;
		result += index + "*int*" + upRank + "#";
	}

	public void setUpRank(int upRank, int bs) {
		this.upRank = upRank;
	}

	/**
		等级
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 4;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		绿卡个数
	*/
	private int green;
	public int getGreen(){
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
		index = 5;
		result += index + "*int*" + green + "#";
	}

	public void setGreen(int green, int bs) {
		this.green = green;
	}

	/**
		蓝卡个数
	*/
	private int blue;
	public int getBlue(){
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
		index = 6;
		result += index + "*int*" + blue + "#";
	}

	public void setBlue(int blue, int bs) {
		this.blue = blue;
	}

	/**
		紫卡个数
	*/
	private int purple;
	public int getPurple(){
		return purple;
	}
	public void setPurple(int purple) {
		this.purple = purple;
		index = 7;
		result += index + "*int*" + purple + "#";
	}

	public void setPurple(int purple, int bs) {
		this.purple = purple;
	}

	/**
		装备品质id
	*/
	private int equipQualityId;
	public int getEquipQualityId(){
		return equipQualityId;
	}
	public void setEquipQualityId(int equipQualityId) {
		this.equipQualityId = equipQualityId;
		index = 8;
		result += index + "*int*" + equipQualityId + "#";
	}

	public void setEquipQualityId(int equipQualityId, int bs) {
		this.equipQualityId = equipQualityId;
	}

	/**
		法宝功法品质id
	*/
	private int magicQualityId;
	public int getMagicQualityId(){
		return magicQualityId;
	}
	public void setMagicQualityId(int magicQualityId) {
		this.magicQualityId = magicQualityId;
		index = 9;
		result += index + "*int*" + magicQualityId + "#";
	}

	public void setMagicQualityId(int magicQualityId, int bs) {
		this.magicQualityId = magicQualityId;
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

	public DictArenaNPC clone(){
		DictArenaNPC extend=new DictArenaNPC();
		extend.setId(this.id);
		extend.setDownRank(this.downRank);
		extend.setUpRank(this.upRank);
		extend.setLevel(this.level);
		extend.setGreen(this.green);
		extend.setBlue(this.blue);
		extend.setPurple(this.purple);
		extend.setEquipQualityId(this.equipQualityId);
		extend.setMagicQualityId(this.magicQualityId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
