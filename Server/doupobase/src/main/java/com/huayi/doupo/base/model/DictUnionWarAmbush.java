package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class DictUnionWarAmbush implements Serializable
{
	private int index;
	public String result = "";
	/**
		id
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
		等级区间开始（包含）
	*/
	private int levelRangeStart;
	public int getLevelRangeStart(){
		return levelRangeStart;
	}
	public void setLevelRangeStart(int levelRangeStart) {
		this.levelRangeStart = levelRangeStart;
		index = 2;
		result += index + "*int*" + levelRangeStart + "#";
	}

	public void setLevelRangeStart(int levelRangeStart, int bs) {
		this.levelRangeStart = levelRangeStart;
	}

	/**
		等级区间结束（包含）
	*/
	private int levelRangeEnd;
	public int getLevelRangeEnd(){
		return levelRangeEnd;
	}
	public void setLevelRangeEnd(int levelRangeEnd) {
		this.levelRangeEnd = levelRangeEnd;
		index = 3;
		result += index + "*int*" + levelRangeEnd + "#";
	}

	public void setLevelRangeEnd(int levelRangeEnd, int bs) {
		this.levelRangeEnd = levelRangeEnd;
	}

	/**
		伏兵限制数
	*/
	private int count;
	public int getCount(){
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		index = 4;
		result += index + "*int*" + count + "#";
	}

	public void setCount(int count, int bs) {
		this.count = count;
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
		version
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

	public DictUnionWarAmbush clone(){
		DictUnionWarAmbush extend=new DictUnionWarAmbush();
		extend.setId(this.id);
		extend.setLevelRangeStart(this.levelRangeStart);
		extend.setLevelRangeEnd(this.levelRangeEnd);
		extend.setCount(this.count);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
