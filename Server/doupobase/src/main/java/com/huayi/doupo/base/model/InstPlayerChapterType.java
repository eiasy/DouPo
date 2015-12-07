package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家副本章节类型实例表
*/
@SuppressWarnings("serial")
public class InstPlayerChapterType implements Serializable
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
		副本章节字典Id 1-普通 2-精英 3-活动
	*/
	private int chapterType;
	public int getChapterType(){
		return chapterType;
	}
	public void setChapterType(int chapterType) {
		this.chapterType = chapterType;
		index = 3;
		result += index + "*int*" + chapterType + "#";
	}

	public void setChapterType(int chapterType, int bs) {
		this.chapterType = chapterType;
	}

	/**
		已挑战次数（精英副本）
	*/
	private int fightNum;
	public int getFightNum(){
		return fightNum;
	}
	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
		index = 4;
		result += index + "*int*" + fightNum + "#";
	}

	public void setFightNum(int fightNum, int bs) {
		this.fightNum = fightNum;
	}

	/**
		一键战斗时间（普通副本）
	*/
	private String aKeyTime;
	public String getAKeyTime(){
		return aKeyTime;
	}
	public void setAKeyTime(String aKeyTime) {
		this.aKeyTime = aKeyTime;
		index = 5;
		result += index + "*String*" + aKeyTime + "#";
	}

	public void setAKeyTime(String aKeyTime, int bs) {
		this.aKeyTime = aKeyTime;
	}

	/**
		购买次数 用于普通（购买冷却的次数），精英（购买攻击的次数）
	*/
	private int buyNum;
	public int getBuyNum(){
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
		index = 6;
		result += index + "*int*" + buyNum + "#";
	}

	public void setBuyNum(int buyNum, int bs) {
		this.buyNum = buyNum;
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
		index = 7;
		result += index + "*String*" + operTime + "#";
	}

	public void setOperTime(String operTime, int bs) {
		this.operTime = operTime;
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
		index = 8;
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
		index = 9;
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
		index = 10;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerChapterType clone(){
		InstPlayerChapterType extend=new InstPlayerChapterType();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setChapterType(this.chapterType);
		extend.setFightNum(this.fightNum);
		extend.setAKeyTime(this.aKeyTime);
		extend.setBuyNum(this.buyNum);
		extend.setOperTime(this.operTime);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
