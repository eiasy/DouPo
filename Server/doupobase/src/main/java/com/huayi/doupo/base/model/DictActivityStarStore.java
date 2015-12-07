package com.huayi.doupo.base.model;

import java.io.*;

/**
	星星兑换活动字典表
*/
@SuppressWarnings("serial")
public class DictActivityStarStore implements Serializable
{
	private int index;
	public String result = "";
	/**
		
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
		物品 tableTypeId_tableFieldId_tableValue
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 2;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
	}

	/**
		购买次数
	*/
	private int buyCount;
	public int getBuyCount(){
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
		index = 3;
		result += index + "*int*" + buyCount + "#";
	}

	public void setBuyCount(int buyCount, int bs) {
		this.buyCount = buyCount;
	}

	/**
		通关副本Id
	*/
	private int chapterId;
	public int getChapterId(){
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
		index = 4;
		result += index + "*int*" + chapterId + "#";
	}

	public void setChapterId(int chapterId, int bs) {
		this.chapterId = chapterId;
	}

	/**
		需要的星数
	*/
	private int starNum;
	public int getStarNum(){
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
		index = 5;
		result += index + "*int*" + starNum + "#";
	}

	public void setStarNum(int starNum, int bs) {
		this.starNum = starNum;
	}

	/**
		排序
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 6;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
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

	public DictActivityStarStore clone(){
		DictActivityStarStore extend=new DictActivityStarStore();
		extend.setId(this.id);
		extend.setThings(this.things);
		extend.setBuyCount(this.buyCount);
		extend.setChapterId(this.chapterId);
		extend.setStarNum(this.starNum);
		extend.setRank(this.rank);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
