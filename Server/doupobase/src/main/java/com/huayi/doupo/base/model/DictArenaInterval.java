package com.huayi.doupo.base.model;

import java.io.*;

/**
	竞技场间隔字典表
*/
@SuppressWarnings("serial")
public class DictArenaInterval implements Serializable
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
		间隔数
	*/
	private int intervalNum;
	public int getIntervalNum(){
		return intervalNum;
	}
	public void setIntervalNum(int intervalNum) {
		this.intervalNum = intervalNum;
		index = 4;
		result += index + "*int*" + intervalNum + "#";
	}

	public void setIntervalNum(int intervalNum, int bs) {
		this.intervalNum = intervalNum;
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
		index = 5;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictArenaInterval clone(){
		DictArenaInterval extend=new DictArenaInterval();
		extend.setId(this.id);
		extend.setDownRank(this.downRank);
		extend.setUpRank(this.upRank);
		extend.setIntervalNum(this.intervalNum);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
