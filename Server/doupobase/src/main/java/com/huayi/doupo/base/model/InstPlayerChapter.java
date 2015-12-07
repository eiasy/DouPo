package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家副本章节实例表
*/
@SuppressWarnings("serial")
public class InstPlayerChapter implements Serializable
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
		副本章节字典Id
	*/
	private int chapterId;
	public int getChapterId(){
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
		index = 3;
		result += index + "*int*" + chapterId + "#";
	}

	public void setChapterId(int chapterId, int bs) {
		this.chapterId = chapterId;
	}

	/**
		已挑战次数
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
		获得星数
	*/
	private int starNum;
	public int getStarNum(){
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
		index = 5;
		result += index + "*int*" + starNum + "#";
	}

	public void setStarNum(int starNum, int bs) {
		this.starNum = starNum;
	}

	/**
		是否通过 0-未通过 1-通过
	*/
	private int isPass;
	public int getIsPass(){
		return isPass;
	}
	public void setIsPass(int isPass) {
		this.isPass = isPass;
		index = 6;
		result += index + "*int*" + isPass + "#";
	}

	public void setIsPass(int isPass, int bs) {
		this.isPass = isPass;
	}

	/**
		领取星数奖励 1-10星 2-20星 3-30星
	*/
	private String getStarType;
	public String getGetStarType(){
		return getStarType;
	}
	public void setGetStarType(String getStarType) {
		this.getStarType = getStarType;
		index = 7;
		result += index + "*String*" + getStarType + "#";
	}

	public void setGetStarType(String getStarType, int bs) {
		this.getStarType = getStarType;
	}

	/**
		活动副本购买战斗次数
	*/
	private int buyNum;
	public int getBuyNum(){
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
		index = 8;
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
		index = 9;
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
		index = 10;
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
		index = 11;
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
		index = 12;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerChapter clone(){
		InstPlayerChapter extend=new InstPlayerChapter();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setChapterId(this.chapterId);
		extend.setFightNum(this.fightNum);
		extend.setStarNum(this.starNum);
		extend.setIsPass(this.isPass);
		extend.setGetStarType(this.getStarType);
		extend.setBuyNum(this.buyNum);
		extend.setOperTime(this.operTime);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
