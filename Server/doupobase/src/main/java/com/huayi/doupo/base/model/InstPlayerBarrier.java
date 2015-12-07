package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家副本关卡实例表
*/
@SuppressWarnings("serial")
public class InstPlayerBarrier implements Serializable
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
		副本关卡字典Id
	*/
	private int barrierId;
	public int getBarrierId(){
		return barrierId;
	}
	public void setBarrierId(int barrierId) {
		this.barrierId = barrierId;
		index = 3;
		result += index + "*int*" + barrierId + "#";
	}

	public void setBarrierId(int barrierId, int bs) {
		this.barrierId = barrierId;
	}

	/**
		已战斗次数
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
		副本章节Id
	*/
	private int chapterId;
	public int getChapterId(){
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
		index = 5;
		result += index + "*int*" + chapterId + "#";
	}

	public void setChapterId(int chapterId, int bs) {
		this.chapterId = chapterId;
	}

	/**
		通过的战斗关卡等级
	*/
	private int barrierLevel;
	public int getBarrierLevel(){
		return barrierLevel;
	}
	public void setBarrierLevel(int barrierLevel) {
		this.barrierLevel = barrierLevel;
		index = 6;
		result += index + "*int*" + barrierLevel + "#";
	}

	public void setBarrierLevel(int barrierLevel, int bs) {
		this.barrierLevel = barrierLevel;
	}

	/**
		重置次数（用于普通战斗购买战斗次数）
	*/
	private int resetNum;
	public int getResetNum(){
		return resetNum;
	}
	public void setResetNum(int resetNum) {
		this.resetNum = resetNum;
		index = 7;
		result += index + "*int*" + resetNum + "#";
	}

	public void setResetNum(int resetNum, int bs) {
		this.resetNum = resetNum;
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
		index = 8;
		result += index + "*String*" + operTime + "#";
	}

	public void setOperTime(String operTime, int bs) {
		this.operTime = operTime;
	}

	/**
		福利箱状态 用于普通副本 1-可 2-已
	*/
	private int welfareBox;
	public int getWelfareBox(){
		return welfareBox;
	}
	public void setWelfareBox(int welfareBox) {
		this.welfareBox = welfareBox;
		index = 9;
		result += index + "*int*" + welfareBox + "#";
	}

	public void setWelfareBox(int welfareBox, int bs) {
		this.welfareBox = welfareBox;
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

	public InstPlayerBarrier clone(){
		InstPlayerBarrier extend=new InstPlayerBarrier();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setBarrierId(this.barrierId);
		extend.setFightNum(this.fightNum);
		extend.setChapterId(this.chapterId);
		extend.setBarrierLevel(this.barrierLevel);
		extend.setResetNum(this.resetNum);
		extend.setOperTime(this.operTime);
		extend.setWelfareBox(this.welfareBox);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
