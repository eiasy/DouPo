package com.huayi.doupo.base.model;

import java.io.*;

/**
	占星-星座档次奖励字典表
*/
@SuppressWarnings("serial")
public class DictHoldStarGradeReward implements Serializable
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
		星座档次Id
	*/
	private int holdStarGradeId;
	public int getHoldStarGradeId(){
		return holdStarGradeId;
	}
	public void setHoldStarGradeId(int holdStarGradeId) {
		this.holdStarGradeId = holdStarGradeId;
		index = 2;
		result += index + "*int*" + holdStarGradeId + "#";
	}

	public void setHoldStarGradeId(int holdStarGradeId, int bs) {
		this.holdStarGradeId = holdStarGradeId;
	}

	/**
		星数
	*/
	private int starNum;
	public int getStarNum(){
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
		index = 3;
		result += index + "*int*" + starNum + "#";
	}

	public void setStarNum(int starNum, int bs) {
		this.starNum = starNum;
	}

	/**
		物品
	*/
	private String thing;
	public String getThing(){
		return thing;
	}
	public void setThing(String thing) {
		this.thing = thing;
		index = 4;
		result += index + "*String*" + thing + "#";
	}

	public void setThing(String thing, int bs) {
		this.thing = thing;
	}

	/**
		随机比重
	*/
	private int weight;
	public int getWeight(){
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
		index = 5;
		result += index + "*int*" + weight + "#";
	}

	public void setWeight(int weight, int bs) {
		this.weight = weight;
	}

	/**
		是否系统可以刷新出来 0-刷不出来 1-能刷出来
	*/
	private int isSysRefresh;
	public int getIsSysRefresh(){
		return isSysRefresh;
	}
	public void setIsSysRefresh(int isSysRefresh) {
		this.isSysRefresh = isSysRefresh;
		index = 6;
		result += index + "*int*" + isSysRefresh + "#";
	}

	public void setIsSysRefresh(int isSysRefresh, int bs) {
		this.isSysRefresh = isSysRefresh;
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
		index = 7;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictHoldStarGradeReward clone(){
		DictHoldStarGradeReward extend=new DictHoldStarGradeReward();
		extend.setId(this.id);
		extend.setHoldStarGradeId(this.holdStarGradeId);
		extend.setStarNum(this.starNum);
		extend.setThing(this.thing);
		extend.setWeight(this.weight);
		extend.setIsSysRefresh(this.isSysRefresh);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
