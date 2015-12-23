package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class InstUnionWarContributionRank implements Serializable
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
		联盟实例Id
	*/
	private int instUnionId;
	public int getInstUnionId(){
		return instUnionId;
	}
	public void setInstUnionId(int instUnionId) {
		this.instUnionId = instUnionId;
		index = 2;
		result += index + "*int*" + instUnionId + "#";
	}

	public void setInstUnionId(int instUnionId, int bs) {
		this.instUnionId = instUnionId;
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
		index = 3;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
	}

	/**
		玩家等级
	*/
	private int instPlayerLevel;
	public int getInstPlayerLevel(){
		return instPlayerLevel;
	}
	public void setInstPlayerLevel(int instPlayerLevel) {
		this.instPlayerLevel = instPlayerLevel;
		index = 4;
		result += index + "*int*" + instPlayerLevel + "#";
	}

	public void setInstPlayerLevel(int instPlayerLevel, int bs) {
		this.instPlayerLevel = instPlayerLevel;
	}

	/**
		玩家名字
	*/
	private String instPlayerName;
	public String getInstPlayerName(){
		return instPlayerName;
	}
	public void setInstPlayerName(String instPlayerName) {
		this.instPlayerName = instPlayerName;
		index = 5;
		result += index + "*String*" + instPlayerName + "#";
	}

	public void setInstPlayerName(String instPlayerName, int bs) {
		this.instPlayerName = instPlayerName;
	}

	/**
		玩家头像Id
	*/
	private int instPlayerHead;
	public int getInstPlayerHead(){
		return instPlayerHead;
	}
	public void setInstPlayerHead(int instPlayerHead) {
		this.instPlayerHead = instPlayerHead;
		index = 6;
		result += index + "*int*" + instPlayerHead + "#";
	}

	public void setInstPlayerHead(int instPlayerHead, int bs) {
		this.instPlayerHead = instPlayerHead;
	}

	/**
		职位Id
	*/
	private int gradeId;
	public int getGradeId(){
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
		index = 7;
		result += index + "*int*" + gradeId + "#";
	}

	public void setGradeId(int gradeId, int bs) {
		this.gradeId = gradeId;
	}

	/**
		是否MVP
	*/
	private int isMvp;
	public int getIsMvp(){
		return isMvp;
	}
	public void setIsMvp(int isMvp) {
		this.isMvp = isMvp;
		index = 8;
		result += index + "*int*" + isMvp + "#";
	}

	public void setIsMvp(int isMvp, int bs) {
		this.isMvp = isMvp;
	}

	/**
		是否活着
	*/
	private int isAlive;
	public int getIsAlive(){
		return isAlive;
	}
	public void setIsAlive(int isAlive) {
		this.isAlive = isAlive;
		index = 9;
		result += index + "*int*" + isAlive + "#";
	}

	public void setIsAlive(int isAlive, int bs) {
		this.isAlive = isAlive;
	}

	/**
		取消设伏前的状态，-1未报名  [0-3]4个战场Index
	*/
	private int lastType;
	public int getLastType(){
		return lastType;
	}
	public void setLastType(int lastType) {
		this.lastType = lastType;
		index = 10;
		result += index + "*int*" + lastType + "#";
	}

	public void setLastType(int lastType, int bs) {
		this.lastType = lastType;
	}

	/**
		打架次数
	*/
	private int fightCount;
	public int getFightCount(){
		return fightCount;
	}
	public void setFightCount(int fightCount) {
		this.fightCount = fightCount;
		index = 11;
		result += index + "*int*" + fightCount + "#";
	}

	public void setFightCount(int fightCount, int bs) {
		this.fightCount = fightCount;
	}

	/**
		击杀数
	*/
	private int killCount;
	public int getKillCount(){
		return killCount;
	}
	public void setKillCount(int killCount) {
		this.killCount = killCount;
		index = 12;
		result += index + "*int*" + killCount + "#";
	}

	public void setKillCount(int killCount, int bs) {
		this.killCount = killCount;
	}

	/**
		贡献值
	*/
	private int contribution;
	public int getContribution(){
		return contribution;
	}
	public void setContribution(int contribution) {
		this.contribution = contribution;
		index = 13;
		result += index + "*int*" + contribution + "#";
	}

	public void setContribution(int contribution, int bs) {
		this.contribution = contribution;
	}

	/**
		版本
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 14;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstUnionWarContributionRank clone(){
		InstUnionWarContributionRank extend=new InstUnionWarContributionRank();
		extend.setId(this.id);
		extend.setInstUnionId(this.instUnionId);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setInstPlayerLevel(this.instPlayerLevel);
		extend.setInstPlayerName(this.instPlayerName);
		extend.setInstPlayerHead(this.instPlayerHead);
		extend.setGradeId(this.gradeId);
		extend.setIsMvp(this.isMvp);
		extend.setIsAlive(this.isAlive);
		extend.setLastType(this.lastType);
		extend.setFightCount(this.fightCount);
		extend.setKillCount(this.killCount);
		extend.setContribution(this.contribution);
		extend.setVersion(this.version);
		return extend;
	}
}
