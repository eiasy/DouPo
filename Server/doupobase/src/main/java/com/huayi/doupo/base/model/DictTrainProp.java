package com.huayi.doupo.base.model;

import java.io.*;

/**
	卡牌修炼属性字典表
*/
@SuppressWarnings("serial")
public class DictTrainProp implements Serializable
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
		修炼类型 1-普通修炼 2-天幕修炼
	*/
	private int trainType;
	public int getTrainType(){
		return trainType;
	}
	public void setTrainType(int trainType) {
		this.trainType = trainType;
		index = 2;
		result += index + "*int*" + trainType + "#";
	}

	public void setTrainType(int trainType, int bs) {
		this.trainType = trainType;
	}

	/**
		潜力点消耗上限
	*/
	private int trainUpLimit;
	public int getTrainUpLimit(){
		return trainUpLimit;
	}
	public void setTrainUpLimit(int trainUpLimit) {
		this.trainUpLimit = trainUpLimit;
		index = 3;
		result += index + "*int*" + trainUpLimit + "#";
	}

	public void setTrainUpLimit(int trainUpLimit, int bs) {
		this.trainUpLimit = trainUpLimit;
	}

	/**
		潜力点消耗下限
	*/
	private int trainDownLimit;
	public int getTrainDownLimit(){
		return trainDownLimit;
	}
	public void setTrainDownLimit(int trainDownLimit) {
		this.trainDownLimit = trainDownLimit;
		index = 4;
		result += index + "*int*" + trainDownLimit + "#";
	}

	public void setTrainDownLimit(int trainDownLimit, int bs) {
		this.trainDownLimit = trainDownLimit;
	}

	/**
		属性值变动上限
	*/
	private int changeUpLimit;
	public int getChangeUpLimit(){
		return changeUpLimit;
	}
	public void setChangeUpLimit(int changeUpLimit) {
		this.changeUpLimit = changeUpLimit;
		index = 5;
		result += index + "*int*" + changeUpLimit + "#";
	}

	public void setChangeUpLimit(int changeUpLimit, int bs) {
		this.changeUpLimit = changeUpLimit;
	}

	/**
		属性值变动下限
	*/
	private int changeDownLimit;
	public int getChangeDownLimit(){
		return changeDownLimit;
	}
	public void setChangeDownLimit(int changeDownLimit) {
		this.changeDownLimit = changeDownLimit;
		index = 6;
		result += index + "*int*" + changeDownLimit + "#";
	}

	public void setChangeDownLimit(int changeDownLimit, int bs) {
		this.changeDownLimit = changeDownLimit;
	}

	/**
		属性变动个数
	*/
	private int propertyNum;
	public int getPropertyNum(){
		return propertyNum;
	}
	public void setPropertyNum(int propertyNum) {
		this.propertyNum = propertyNum;
		index = 7;
		result += index + "*int*" + propertyNum + "#";
	}

	public void setPropertyNum(int propertyNum, int bs) {
		this.propertyNum = propertyNum;
	}

	/**
		所需元宝
	*/
	private int gold;
	public int getGold(){
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
		index = 8;
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
		index = 9;
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
		index = 10;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictTrainProp clone(){
		DictTrainProp extend=new DictTrainProp();
		extend.setId(this.id);
		extend.setTrainType(this.trainType);
		extend.setTrainUpLimit(this.trainUpLimit);
		extend.setTrainDownLimit(this.trainDownLimit);
		extend.setChangeUpLimit(this.changeUpLimit);
		extend.setChangeDownLimit(this.changeDownLimit);
		extend.setPropertyNum(this.propertyNum);
		extend.setGold(this.gold);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
