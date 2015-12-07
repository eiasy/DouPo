package com.huayi.doupo.base.model;

import java.io.*;

/**
	手动技能字典表
*/
@SuppressWarnings("serial")
public class DictManualSkill implements Serializable
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
		产出副本 多个用分号隔开
	*/
	private String outBarrier;
	public String getOutBarrier(){
		return outBarrier;
	}
	public void setOutBarrier(String outBarrier) {
		this.outBarrier = outBarrier;
		index = 5;
		result += index + "*String*" + outBarrier + "#";
	}

	public void setOutBarrier(String outBarrier, int bs) {
		this.outBarrier = outBarrier;
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
		品质 供抢夺用
	*/
	private int quality;
	public int getQuality(){
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
		index = 7;
		result += index + "*int*" + quality + "#";
	}

	public void setQuality(int quality, int bs) {
		this.quality = quality;
	}

	/**
		初始经验
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 8;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		系别 1-攻 2-辅 3-控 4-毒
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 9;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		切割坐标分号隔开
	*/
	private String chops;
	public String getChops(){
		return chops;
	}
	public void setChops(String chops) {
		this.chops = chops;
		index = 10;
		result += index + "*String*" + chops + "#";
	}

	public void setChops(String chops, int bs) {
		this.chops = chops;
	}

	/**
		初始战斗力
	*/
	private int fightValue;
	public int getFightValue(){
		return fightValue;
	}
	public void setFightValue(int fightValue) {
		this.fightValue = fightValue;
		index = 11;
		result += index + "*int*" + fightValue + "#";
	}

	public void setFightValue(int fightValue, int bs) {
		this.fightValue = fightValue;
	}

	/**
		战斗力加成
	*/
	private int fightValueAdd;
	public int getFightValueAdd(){
		return fightValueAdd;
	}
	public void setFightValueAdd(int fightValueAdd) {
		this.fightValueAdd = fightValueAdd;
		index = 12;
		result += index + "*int*" + fightValueAdd + "#";
	}

	public void setFightValueAdd(int fightValueAdd, int bs) {
		this.fightValueAdd = fightValueAdd;
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
		index = 13;
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
		index = 14;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictManualSkill clone(){
		DictManualSkill extend=new DictManualSkill();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setOutBarrier(this.outBarrier);
		extend.setSellCopper(this.sellCopper);
		extend.setQuality(this.quality);
		extend.setExp(this.exp);
		extend.setType(this.type);
		extend.setChops(this.chops);
		extend.setFightValue(this.fightValue);
		extend.setFightValueAdd(this.fightValueAdd);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
