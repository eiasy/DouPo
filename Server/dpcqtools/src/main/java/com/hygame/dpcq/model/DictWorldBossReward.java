package com.hygame.dpcq.model;

import java.io.Serializable;

/**
	世界Boss排名奖励字典表
*/
@SuppressWarnings("serial")
public class DictWorldBossReward implements Serializable
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
		奖励银币
	*/
	private int copper;
	public int getCopper(){
		return copper;
	}
	public void setCopper(int copper) {
		this.copper = copper;
		index = 4;
		result += index + "*int*" + copper + "#";
	}

	public void setCopper(int copper, int bs) {
		this.copper = copper;
	}

	/**
		奖励威望
	*/
	private int prestige;
	public int getPrestige(){
		return prestige;
	}
	public void setPrestige(int prestige) {
		this.prestige = prestige;
		index = 5;
		result += index + "*int*" + prestige + "#";
	}

	public void setPrestige(int prestige, int bs) {
		this.prestige = prestige;
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

	public DictWorldBossReward clone(){
		DictWorldBossReward extend=new DictWorldBossReward();
		extend.setId(this.id);
		extend.setDownRank(this.downRank);
		extend.setUpRank(this.upRank);
		extend.setCopper(this.copper);
		extend.setPrestige(this.prestige);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
