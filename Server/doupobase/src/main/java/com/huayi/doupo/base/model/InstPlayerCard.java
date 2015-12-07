package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家卡牌实例表
*/
@SuppressWarnings("serial")
public class InstPlayerCard implements Serializable
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
		卡牌Id
	*/
	private int cardId;
	public int getCardId(){
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
		index = 3;
		result += index + "*int*" + cardId + "#";
	}

	public void setCardId(int cardId, int bs) {
		this.cardId = cardId;
	}

	/**
		品阶Id
	*/
	private int qualityId;
	public int getQualityId(){
		return qualityId;
	}
	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
		index = 4;
		result += index + "*int*" + qualityId + "#";
	}

	public void setQualityId(int qualityId, int bs) {
		this.qualityId = qualityId;
	}

	/**
		星级Id
	*/
	private int starLevelId;
	public int getStarLevelId(){
		return starLevelId;
	}
	public void setStarLevelId(int starLevelId) {
		this.starLevelId = starLevelId;
		index = 5;
		result += index + "*int*" + starLevelId + "#";
	}

	public void setStarLevelId(int starLevelId, int bs) {
		this.starLevelId = starLevelId;
	}

	/**
		具体称号Id
	*/
	private int titleDetailId;
	public int getTitleDetailId(){
		return titleDetailId;
	}
	public void setTitleDetailId(int titleDetailId) {
		this.titleDetailId = titleDetailId;
		index = 6;
		result += index + "*int*" + titleDetailId + "#";
	}

	public void setTitleDetailId(int titleDetailId, int bs) {
		this.titleDetailId = titleDetailId;
	}

	/**
		性别
	*/
	private int sex;
	public int getSex(){
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
		index = 7;
		result += index + "*int*" + sex + "#";
	}

	public void setSex(int sex, int bs) {
		this.sex = sex;
	}

	/**
		经验
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 8;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
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
		index = 9;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		是否在队伍中 0-不在 1-在
	*/
	private int inTeam;
	public int getInTeam(){
		return inTeam;
	}
	public void setInTeam(int inTeam) {
		this.inTeam = inTeam;
		index = 10;
		result += index + "*int*" + inTeam + "#";
	}

	public void setInTeam(int inTeam, int bs) {
		this.inTeam = inTeam;
	}

	/**
		潜力值
	*/
	private int useTalentValue;
	public int getUseTalentValue(){
		return useTalentValue;
	}
	public void setUseTalentValue(int useTalentValue) {
		this.useTalentValue = useTalentValue;
		index = 11;
		result += index + "*int*" + useTalentValue + "#";
	}

	public void setUseTalentValue(int useTalentValue, int bs) {
		this.useTalentValue = useTalentValue;
	}

	/**
		玩家功法实例Id
	*/
	private int instPlayerKungFuId;
	public int getInstPlayerKungFuId(){
		return instPlayerKungFuId;
	}
	public void setInstPlayerKungFuId(int instPlayerKungFuId) {
		this.instPlayerKungFuId = instPlayerKungFuId;
		index = 12;
		result += index + "*int*" + instPlayerKungFuId + "#";
	}

	public void setInstPlayerKungFuId(int instPlayerKungFuId, int bs) {
		this.instPlayerKungFuId = instPlayerKungFuId;
	}

	/**
		玩家命宫实例
	*/
	private String instPlayerConstells;
	public String getInstPlayerConstells(){
		return instPlayerConstells;
	}
	public void setInstPlayerConstells(String instPlayerConstells) {
		this.instPlayerConstells = instPlayerConstells;
		index = 13;
		result += index + "*String*" + instPlayerConstells + "#";
	}

	public void setInstPlayerConstells(String instPlayerConstells, int bs) {
		this.instPlayerConstells = instPlayerConstells;
	}

	/**
		潜力点
	*/
	private int potential;
	public int getPotential(){
		return potential;
	}
	public void setPotential(int potential) {
		this.potential = potential;
		index = 14;
		result += index + "*int*" + potential + "#";
	}

	public void setPotential(int potential, int bs) {
		this.potential = potential;
	}

	/**
		锁  0-不锁 1-锁
	*/
	private int isLock;
	public int getIsLock(){
		return isLock;
	}
	public void setIsLock(int isLock) {
		this.isLock = isLock;
		index = 15;
		result += index + "*int*" + isLock + "#";
	}

	public void setIsLock(int isLock, int bs) {
		this.isLock = isLock;
	}

	/**
		修炼次数
	*/
	private int trainNum;
	public int getTrainNum(){
		return trainNum;
	}
	public void setTrainNum(int trainNum) {
		this.trainNum = trainNum;
		index = 16;
		result += index + "*int*" + trainNum + "#";
	}

	public void setTrainNum(int trainNum, int bs) {
		this.trainNum = trainNum;
	}

	/**
		接受修炼次数
	*/
	private int trainAcceptNum;
	public int getTrainAcceptNum(){
		return trainAcceptNum;
	}
	public void setTrainAcceptNum(int trainAcceptNum) {
		this.trainAcceptNum = trainAcceptNum;
		index = 17;
		result += index + "*int*" + trainAcceptNum + "#";
	}

	public void setTrainAcceptNum(int trainAcceptNum, int bs) {
		this.trainAcceptNum = trainAcceptNum;
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
		index = 18;
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
		index = 19;
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
		index = 20;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerCard clone(){
		InstPlayerCard extend=new InstPlayerCard();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setCardId(this.cardId);
		extend.setQualityId(this.qualityId);
		extend.setStarLevelId(this.starLevelId);
		extend.setTitleDetailId(this.titleDetailId);
		extend.setSex(this.sex);
		extend.setExp(this.exp);
		extend.setLevel(this.level);
		extend.setInTeam(this.inTeam);
		extend.setUseTalentValue(this.useTalentValue);
		extend.setInstPlayerKungFuId(this.instPlayerKungFuId);
		extend.setInstPlayerConstells(this.instPlayerConstells);
		extend.setPotential(this.potential);
		extend.setIsLock(this.isLock);
		extend.setTrainNum(this.trainNum);
		extend.setTrainAcceptNum(this.trainAcceptNum);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
