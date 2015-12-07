package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家竞技场实例表
*/
@SuppressWarnings("serial")
public class InstPlayerArena implements Serializable
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
		玩家实例Id
	*/
	private int instPlayerId;
	public int getInstPlayerId(){
		return instPlayerId;
	}
	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
		index = 2;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
	}

	/**
		排名
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 3;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
	}

	/**
		历史最高排名  -1:NPC  >=0:PC
	*/
	private int upRank;
	public int getUpRank(){
		return upRank;
	}
	public void setUpRank(int upRank) {
		this.upRank = upRank;
		index = 4;
		result += index + "*int*" + upRank + "#";
	}

	public void setUpRank(int upRank, int bs) {
		this.upRank = upRank;
	}

	/**
		竞技次数
	*/
	private int arenaNum;
	public int getArenaNum(){
		return arenaNum;
	}
	public void setArenaNum(int arenaNum) {
		this.arenaNum = arenaNum;
		index = 5;
		result += index + "*int*" + arenaNum + "#";
	}

	public void setArenaNum(int arenaNum, int bs) {
		this.arenaNum = arenaNum;
	}

	/**
		竞技时间
	*/
	private String arenaTime;
	public String getArenaTime(){
		return arenaTime;
	}
	public void setArenaTime(String arenaTime) {
		this.arenaTime = arenaTime;
		index = 6;
		result += index + "*String*" + arenaTime + "#";
	}

	public void setArenaTime(String arenaTime, int bs) {
		this.arenaTime = arenaTime;
	}

	/**
		威望
	*/
	private int prestige;
	public int getPrestige(){
		return prestige;
	}
	public void setPrestige(int prestige) {
		this.prestige = prestige;
		index = 7;
		result += index + "*int*" + prestige + "#";
	}

	public void setPrestige(int prestige, int bs) {
		this.prestige = prestige;
	}

	/**
		购买次数 购买挑战次数
	*/
	private int buyArenaNum;
	public int getBuyArenaNum(){
		return buyArenaNum;
	}
	public void setBuyArenaNum(int buyArenaNum) {
		this.buyArenaNum = buyArenaNum;
		index = 8;
		result += index + "*int*" + buyArenaNum + "#";
	}

	public void setBuyArenaNum(int buyArenaNum, int bs) {
		this.buyArenaNum = buyArenaNum;
	}

	/**
		领取前进将的排名
	*/
	private int awardRank;
	public int getAwardRank(){
		return awardRank;
	}
	public void setAwardRank(int awardRank) {
		this.awardRank = awardRank;
		index = 9;
		result += index + "*int*" + awardRank + "#";
	}

	public void setAwardRank(int awardRank, int bs) {
		this.awardRank = awardRank;
	}

	/**
		操作时间
	*/
	private String operTime;
	public String getOperTime(){
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
		index = 10;
		result += index + "*String*" + operTime + "#";
	}

	public void setOperTime(String operTime, int bs) {
		this.operTime = operTime;
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
		index = 11;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		版本号
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 12;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		添加时间
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 13;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
	}

	/**
		更新时间
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 14;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerArena clone(){
		InstPlayerArena extend=new InstPlayerArena();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setRank(this.rank);
		extend.setUpRank(this.upRank);
		extend.setArenaNum(this.arenaNum);
		extend.setArenaTime(this.arenaTime);
		extend.setPrestige(this.prestige);
		extend.setBuyArenaNum(this.buyArenaNum);
		extend.setAwardRank(this.awardRank);
		extend.setOperTime(this.operTime);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
