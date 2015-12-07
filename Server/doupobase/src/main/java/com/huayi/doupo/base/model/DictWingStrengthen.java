package com.huayi.doupo.base.model;

import java.io.*;

/**
	翅膀强化字典表
*/
@SuppressWarnings("serial")
public class DictWingStrengthen implements Serializable
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
		翅膀Id
	*/
	private int wingId;
	public int getWingId(){
		return wingId;
	}
	public void setWingId(int wingId) {
		this.wingId = wingId;
		index = 2;
		result += index + "*int*" + wingId + "#";
	}

	public void setWingId(int wingId, int bs) {
		this.wingId = wingId;
	}

	/**
		等级
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 3;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		战斗属性及其数值 fightPropId_value;
	*/
	private String fightPropValueList;
	public String getFightPropValueList(){
		return fightPropValueList;
	}
	public void setFightPropValueList(String fightPropValueList) {
		this.fightPropValueList = fightPropValueList;
		index = 4;
		result += index + "*String*" + fightPropValueList + "#";
	}

	public void setFightPropValueList(String fightPropValueList, int bs) {
		this.fightPropValueList = fightPropValueList;
	}

	/**
		
	*/
	private String nextLevelConds;
	public String getNextLevelConds(){
		return nextLevelConds;
	}
	public void setNextLevelConds(String nextLevelConds) {
		this.nextLevelConds = nextLevelConds;
		index = 5;
		result += index + "*String*" + nextLevelConds + "#";
	}

	public void setNextLevelConds(String nextLevelConds, int bs) {
		this.nextLevelConds = nextLevelConds;
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
		index = 6;
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
		index = 7;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictWingStrengthen clone(){
		DictWingStrengthen extend=new DictWingStrengthen();
		extend.setId(this.id);
		extend.setWingId(this.wingId);
		extend.setLevel(this.level);
		extend.setFightPropValueList(this.fightPropValueList);
		extend.setNextLevelConds(this.nextLevelConds);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
