package com.huayi.doupo.base.model;

import java.io.*;

/**
	副本节点等级字典表
*/
@SuppressWarnings("serial")
public class DictBarrierLevel implements Serializable
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
		副本关卡Id
	*/
	private int barrierId;
	public int getBarrierId(){
		return barrierId;
	}
	public void setBarrierId(int barrierId) {
		this.barrierId = barrierId;
		index = 2;
		result += index + "*int*" + barrierId + "#";
	}

	public void setBarrierId(int barrierId, int bs) {
		this.barrierId = barrierId;
	}

	/**
		难度 1-简单 2-普通 3-困难
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 3;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		敌人属性总体+
	*/
	private float fightAdd;
	public float getFightAdd(){
		return fightAdd;
	}
	public void setFightAdd(float fightAdd) {
		this.fightAdd = fightAdd;
		index = 4;
		result += index + "*float*" + fightAdd + "#";
	}

	public void setFightAdd(float fightAdd, int bs) {
		this.fightAdd = fightAdd;
	}

	/**
		敌人波数
	*/
	private int waveNum;
	public int getWaveNum(){
		return waveNum;
	}
	public void setWaveNum(int waveNum) {
		this.waveNum = waveNum;
		index = 5;
		result += index + "*int*" + waveNum + "#";
	}

	public void setWaveNum(int waveNum, int bs) {
		this.waveNum = waveNum;
	}

	/**
		奖励金币
	*/
	private int copper;
	public int getCopper(){
		return copper;
	}
	public void setCopper(int copper) {
		this.copper = copper;
		index = 6;
		result += index + "*int*" + copper + "#";
	}

	public void setCopper(int copper, int bs) {
		this.copper = copper;
	}

	/**
		奖励修为
	*/
	private int culture;
	public int getCulture(){
		return culture;
	}
	public void setCulture(int culture) {
		this.culture = culture;
		index = 7;
		result += index + "*int*" + culture + "#";
	}

	public void setCulture(int culture, int bs) {
		this.culture = culture;
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

	public DictBarrierLevel clone(){
		DictBarrierLevel extend=new DictBarrierLevel();
		extend.setId(this.id);
		extend.setBarrierId(this.barrierId);
		extend.setLevel(this.level);
		extend.setFightAdd(this.fightAdd);
		extend.setWaveNum(this.waveNum);
		extend.setCopper(this.copper);
		extend.setCulture(this.culture);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
