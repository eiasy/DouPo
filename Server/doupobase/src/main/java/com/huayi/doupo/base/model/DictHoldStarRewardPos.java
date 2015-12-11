package com.huayi.doupo.base.model;

import java.io.*;

/**
	占星-星座奖励位置字典表
*/
@SuppressWarnings("serial")
public class DictHoldStarRewardPos implements Serializable
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
		此位置代表的星数
	*/
	private int starNum;
	public int getStarNum(){
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
		index = 2;
		result += index + "*int*" + starNum + "#";
	}

	public void setStarNum(int starNum, int bs) {
		this.starNum = starNum;
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
		index = 3;
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
		index = 4;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictHoldStarRewardPos clone(){
		DictHoldStarRewardPos extend=new DictHoldStarRewardPos();
		extend.setId(this.id);
		extend.setStarNum(this.starNum);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
