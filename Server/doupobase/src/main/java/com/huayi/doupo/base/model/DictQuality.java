package com.huayi.doupo.base.model;

import java.io.*;

/**
	品质字典表
*/
@SuppressWarnings("serial")
public class DictQuality implements Serializable
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
		召唤需要的魂魄数
	*/
	private int soulNum;
	public int getSoulNum(){
		return soulNum;
	}
	public void setSoulNum(int soulNum) {
		this.soulNum = soulNum;
		index = 3;
		result += index + "*int*" + soulNum + "#";
	}

	public void setSoulNum(int soulNum, int bs) {
		this.soulNum = soulNum;
	}

	/**
		分解出的魂源数量
	*/
	private int resolveSoulNum;
	public int getResolveSoulNum(){
		return resolveSoulNum;
	}
	public void setResolveSoulNum(int resolveSoulNum) {
		this.resolveSoulNum = resolveSoulNum;
		index = 4;
		result += index + "*int*" + resolveSoulNum + "#";
	}

	public void setResolveSoulNum(int resolveSoulNum, int bs) {
		this.resolveSoulNum = resolveSoulNum;
	}

	/**
		分解所需铜币
	*/
	private int resolveNeedCopper;
	public int getResolveNeedCopper(){
		return resolveNeedCopper;
	}
	public void setResolveNeedCopper(int resolveNeedCopper) {
		this.resolveNeedCopper = resolveNeedCopper;
		index = 5;
		result += index + "*int*" + resolveNeedCopper + "#";
	}

	public void setResolveNeedCopper(int resolveNeedCopper, int bs) {
		this.resolveNeedCopper = resolveNeedCopper;
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
		index = 6;
		result += index + "*int*" + sellCopper + "#";
	}

	public void setSellCopper(int sellCopper, int bs) {
		this.sellCopper = sellCopper;
	}

	/**
		出售价格+
	*/
	private int sellCopperAdd;
	public int getSellCopperAdd(){
		return sellCopperAdd;
	}
	public void setSellCopperAdd(int sellCopperAdd) {
		this.sellCopperAdd = sellCopperAdd;
		index = 7;
		result += index + "*int*" + sellCopperAdd + "#";
	}

	public void setSellCopperAdd(int sellCopperAdd, int bs) {
		this.sellCopperAdd = sellCopperAdd;
	}

	/**
		最大星级
	*/
	private int maxStarLevel;
	public int getMaxStarLevel(){
		return maxStarLevel;
	}
	public void setMaxStarLevel(int maxStarLevel) {
		this.maxStarLevel = maxStarLevel;
		index = 8;
		result += index + "*int*" + maxStarLevel + "#";
	}

	public void setMaxStarLevel(int maxStarLevel, int bs) {
		this.maxStarLevel = maxStarLevel;
	}

	/**
		静态字段
	*/
	private String sname;
	public String getSname(){
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
		index = 9;
		result += index + "*String*" + sname + "#";
	}

	public void setSname(String sname, int bs) {
		this.sname = sname;
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

	public DictQuality clone(){
		DictQuality extend=new DictQuality();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSoulNum(this.soulNum);
		extend.setResolveSoulNum(this.resolveSoulNum);
		extend.setResolveNeedCopper(this.resolveNeedCopper);
		extend.setSellCopper(this.sellCopper);
		extend.setSellCopperAdd(this.sellCopperAdd);
		extend.setMaxStarLevel(this.maxStarLevel);
		extend.setSname(this.sname);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
