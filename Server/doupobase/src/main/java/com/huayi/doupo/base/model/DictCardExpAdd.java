package com.huayi.doupo.base.model;

import java.io.*;

/**
	吃卡牌经验增长字典表
*/
@SuppressWarnings("serial")
public class DictCardExpAdd implements Serializable
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
		卡牌等级范围初始值
	*/
	private int levelStart;
	public int getLevelStart(){
		return levelStart;
	}
	public void setLevelStart(int levelStart) {
		this.levelStart = levelStart;
		index = 2;
		result += index + "*int*" + levelStart + "#";
	}

	public void setLevelStart(int levelStart, int bs) {
		this.levelStart = levelStart;
	}

	/**
		卡牌等级范围结束值
	*/
	private int levelEnd;
	public int getLevelEnd(){
		return levelEnd;
	}
	public void setLevelEnd(int levelEnd) {
		this.levelEnd = levelEnd;
		index = 3;
		result += index + "*int*" + levelEnd + "#";
	}

	public void setLevelEnd(int levelEnd, int bs) {
		this.levelEnd = levelEnd;
	}

	/**
		经验值
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 4;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
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

	public DictCardExpAdd clone(){
		DictCardExpAdd extend=new DictCardExpAdd();
		extend.setId(this.id);
		extend.setLevelStart(this.levelStart);
		extend.setLevelEnd(this.levelEnd);
		extend.setExp(this.exp);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
