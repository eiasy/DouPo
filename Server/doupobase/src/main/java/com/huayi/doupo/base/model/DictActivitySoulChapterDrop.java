package com.huayi.doupo.base.model;

import java.io.*;

/**
	战斗类型字典表
*/
@SuppressWarnings("serial")
public class DictActivitySoulChapterDrop implements Serializable
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
		周几
	*/
	private int weekDay;
	public int getWeekDay(){
		return weekDay;
	}
	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
		index = 2;
		result += index + "*int*" + weekDay + "#";
	}

	public void setWeekDay(int weekDay, int bs) {
		this.weekDay = weekDay;
	}

	/**
		代表难度的关卡id
	*/
	private int difficultyBarrierId;
	public int getDifficultyBarrierId(){
		return difficultyBarrierId;
	}
	public void setDifficultyBarrierId(int difficultyBarrierId) {
		this.difficultyBarrierId = difficultyBarrierId;
		index = 3;
		result += index + "*int*" + difficultyBarrierId + "#";
	}

	public void setDifficultyBarrierId(int difficultyBarrierId, int bs) {
		this.difficultyBarrierId = difficultyBarrierId;
	}

	/**
		掉落的物品
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 4;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
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

	public DictActivitySoulChapterDrop clone(){
		DictActivitySoulChapterDrop extend=new DictActivitySoulChapterDrop();
		extend.setId(this.id);
		extend.setWeekDay(this.weekDay);
		extend.setDifficultyBarrierId(this.difficultyBarrierId);
		extend.setThings(this.things);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
