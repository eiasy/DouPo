package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class DictYFire implements Serializable
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
		碎片名字
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
		排名
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 3;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
	}

	/**
		小图标id
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 4;
		result += index + "*int*" + smallUiId + "#";
	}

	public void setSmallUiId(int smallUiId, int bs) {
		this.smallUiId = smallUiId;
	}

	/**
		大图标ID
	*/
	private int bigUiId;
	public int getBigUiId(){
		return bigUiId;
	}
	public void setBigUiId(int bigUiId) {
		this.bigUiId = bigUiId;
		index = 5;
		result += index + "*int*" + bigUiId + "#";
	}

	public void setBigUiId(int bigUiId, int bs) {
		this.bigUiId = bigUiId;
	}

	/**
		旺盛状态描述
	*/
	private String exuberantDesc;
	public String getExuberantDesc(){
		return exuberantDesc;
	}
	public void setExuberantDesc(String exuberantDesc) {
		this.exuberantDesc = exuberantDesc;
		index = 6;
		result += index + "*String*" + exuberantDesc + "#";
	}

	public void setExuberantDesc(String exuberantDesc, int bs) {
		this.exuberantDesc = exuberantDesc;
	}

	/**
		狂暴状态描述
	*/
	private String rageDesc;
	public String getRageDesc(){
		return rageDesc;
	}
	public void setRageDesc(String rageDesc) {
		this.rageDesc = rageDesc;
		index = 7;
		result += index + "*String*" + rageDesc + "#";
	}

	public void setRageDesc(String rageDesc, int bs) {
		this.rageDesc = rageDesc;
	}

	/**
		能量上限
	*/
	private int hpMax;
	public int getHpMax(){
		return hpMax;
	}
	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
		index = 8;
		result += index + "*int*" + hpMax + "#";
	}

	public void setHpMax(int hpMax, int bs) {
		this.hpMax = hpMax;
	}

	/**
		每个火种能量回复量
	*/
	private int cureVar;
	public int getCureVar(){
		return cureVar;
	}
	public void setCureVar(int cureVar) {
		this.cureVar = cureVar;
		index = 9;
		result += index + "*int*" + cureVar + "#";
	}

	public void setCureVar(int cureVar, int bs) {
		this.cureVar = cureVar;
	}

	/**
		每1分钟消耗
	*/
	private int cost;
	public int getCost(){
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
		index = 10;
		result += index + "*int*" + cost + "#";
	}

	public void setCost(int cost, int bs) {
		this.cost = cost;
	}

	/**
		异火碎片ID
	*/
	private int chipId;
	public int getChipId(){
		return chipId;
	}
	public void setChipId(int chipId) {
		this.chipId = chipId;
		index = 11;
		result += index + "*int*" + chipId + "#";
	}

	public void setChipId(int chipId, int bs) {
		this.chipId = chipId;
	}

	/**
		激活异火需要的碎片
	*/
	private int chipMax;
	public int getChipMax(){
		return chipMax;
	}
	public void setChipMax(int chipMax) {
		this.chipMax = chipMax;
		index = 12;
		result += index + "*int*" + chipMax + "#";
	}

	public void setChipMax(int chipMax, int bs) {
		this.chipMax = chipMax;
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
		index = 13;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictYFire clone(){
		DictYFire extend=new DictYFire();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setRank(this.rank);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setExuberantDesc(this.exuberantDesc);
		extend.setRageDesc(this.rageDesc);
		extend.setHpMax(this.hpMax);
		extend.setCureVar(this.cureVar);
		extend.setCost(this.cost);
		extend.setChipId(this.chipId);
		extend.setChipMax(this.chipMax);
		extend.setVersion(this.version);
		return extend;
	}
}
