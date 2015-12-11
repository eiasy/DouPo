package com.huayi.doupo.base.model;

import java.io.*;

/**
	占星-星座奖励刷新次数需要元宝数字典表
*/
@SuppressWarnings("serial")
public class DictHoldStarRewardRefreshTimes implements Serializable
{
	private int index;
	public String result = "";
	/**
		位置
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
		区间开始次数
	*/
	private int starTimes;
	public int getStarTimes(){
		return starTimes;
	}
	public void setStarTimes(int starTimes) {
		this.starTimes = starTimes;
		index = 2;
		result += index + "*int*" + starTimes + "#";
	}

	public void setStarTimes(int starTimes, int bs) {
		this.starTimes = starTimes;
	}

	/**
		区间结束次数
	*/
	private int endTimes;
	public int getEndTimes(){
		return endTimes;
	}
	public void setEndTimes(int endTimes) {
		this.endTimes = endTimes;
		index = 3;
		result += index + "*int*" + endTimes + "#";
	}

	public void setEndTimes(int endTimes, int bs) {
		this.endTimes = endTimes;
	}

	/**
		需要元宝数
	*/
	private int needGold;
	public int getNeedGold(){
		return needGold;
	}
	public void setNeedGold(int needGold) {
		this.needGold = needGold;
		index = 4;
		result += index + "*int*" + needGold + "#";
	}

	public void setNeedGold(int needGold, int bs) {
		this.needGold = needGold;
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

	public DictHoldStarRewardRefreshTimes clone(){
		DictHoldStarRewardRefreshTimes extend=new DictHoldStarRewardRefreshTimes();
		extend.setId(this.id);
		extend.setStarTimes(this.starTimes);
		extend.setEndTimes(this.endTimes);
		extend.setNeedGold(this.needGold);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
