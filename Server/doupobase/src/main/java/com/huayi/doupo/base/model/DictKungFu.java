package com.huayi.doupo.base.model;

import java.io.*;

/**
	功法字典表
*/
@SuppressWarnings("serial")
public class DictKungFu implements Serializable
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
		小图标Id
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
		大图标Id
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
		功法类型Id
	*/
	private int kungFuTypeId;
	public int getKungFuTypeId(){
		return kungFuTypeId;
	}
	public void setKungFuTypeId(int kungFuTypeId) {
		this.kungFuTypeId = kungFuTypeId;
		index = 5;
		result += index + "*int*" + kungFuTypeId + "#";
	}

	public void setKungFuTypeId(int kungFuTypeId, int bs) {
		this.kungFuTypeId = kungFuTypeId;
	}

	/**
		功法品质Id
	*/
	private int kungFuQualityId;
	public int getKungFuQualityId(){
		return kungFuQualityId;
	}
	public void setKungFuQualityId(int kungFuQualityId) {
		this.kungFuQualityId = kungFuQualityId;
		index = 6;
		result += index + "*int*" + kungFuQualityId + "#";
	}

	public void setKungFuQualityId(int kungFuQualityId, int bs) {
		this.kungFuQualityId = kungFuQualityId;
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
		index = 7;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		经脉Id
	*/
	private int acupoint;
	public int getAcupoint(){
		return acupoint;
	}
	public void setAcupoint(int acupoint) {
		this.acupoint = acupoint;
		index = 8;
		result += index + "*int*" + acupoint + "#";
	}

	public void setAcupoint(int acupoint, int bs) {
		this.acupoint = acupoint;
	}

	/**
		所需经验值
	*/
	private int expSum;
	public int getExpSum(){
		return expSum;
	}
	public void setExpSum(int expSum) {
		this.expSum = expSum;
		index = 9;
		result += index + "*int*" + expSum + "#";
	}

	public void setExpSum(int expSum, int bs) {
		this.expSum = expSum;
	}

	/**
		下阶功法Id
	*/
	private int nextKungFu;
	public int getNextKungFu(){
		return nextKungFu;
	}
	public void setNextKungFu(int nextKungFu) {
		this.nextKungFu = nextKungFu;
		index = 10;
		result += index + "*int*" + nextKungFu + "#";
	}

	public void setNextKungFu(int nextKungFu, int bs) {
		this.nextKungFu = nextKungFu;
	}

	/**
		功法重数
	*/
	private int tier;
	public int getTier(){
		return tier;
	}
	public void setTier(int tier) {
		this.tier = tier;
		index = 11;
		result += index + "*int*" + tier + "#";
	}

	public void setTier(int tier, int bs) {
		this.tier = tier;
	}

	/**
		经脉节点上限
	*/
	private int maxNode;
	public int getMaxNode(){
		return maxNode;
	}
	public void setMaxNode(int maxNode) {
		this.maxNode = maxNode;
		index = 12;
		result += index + "*int*" + maxNode + "#";
	}

	public void setMaxNode(int maxNode, int bs) {
		this.maxNode = maxNode;
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
		index = 13;
		result += index + "*String*" + chops + "#";
	}

	public void setChops(String chops, int bs) {
		this.chops = chops;
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
		index = 14;
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
		index = 15;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictKungFu clone(){
		DictKungFu extend=new DictKungFu();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setKungFuTypeId(this.kungFuTypeId);
		extend.setKungFuQualityId(this.kungFuQualityId);
		extend.setExp(this.exp);
		extend.setAcupoint(this.acupoint);
		extend.setExpSum(this.expSum);
		extend.setNextKungFu(this.nextKungFu);
		extend.setTier(this.tier);
		extend.setMaxNode(this.maxNode);
		extend.setChops(this.chops);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
