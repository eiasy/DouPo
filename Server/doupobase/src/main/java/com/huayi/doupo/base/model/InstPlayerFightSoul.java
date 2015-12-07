package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家斗魂实例表
*/
@SuppressWarnings("serial")
public class InstPlayerFightSoul implements Serializable
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
		装备Id
	*/
	private int fightSoulId;
	public int getFightSoulId(){
		return fightSoulId;
	}
	public void setFightSoulId(int fightSoulId) {
		this.fightSoulId = fightSoulId;
		index = 3;
		result += index + "*int*" + fightSoulId + "#";
	}

	public void setFightSoulId(int fightSoulId, int bs) {
		this.fightSoulId = fightSoulId;
	}

	/**
		斗魂品质Id
	*/
	private int fightSoulQualityId;
	public int getFightSoulQualityId(){
		return fightSoulQualityId;
	}
	public void setFightSoulQualityId(int fightSoulQualityId) {
		this.fightSoulQualityId = fightSoulQualityId;
		index = 4;
		result += index + "*int*" + fightSoulQualityId + "#";
	}

	public void setFightSoulQualityId(int fightSoulQualityId, int bs) {
		this.fightSoulQualityId = fightSoulQualityId;
	}

	/**
		斗魂等级
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 5;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		锁定状态 0-未锁定 1-锁定
	*/
	private int lockState;
	public int getLockState(){
		return lockState;
	}
	public void setLockState(int lockState) {
		this.lockState = lockState;
		index = 6;
		result += index + "*int*" + lockState + "#";
	}

	public void setLockState(int lockState, int bs) {
		this.lockState = lockState;
	}

	/**
		卡牌Id  此斗魂被附着在哪个卡牌身上.未附着为0
	*/
	private int instCardId;
	public int getInstCardId(){
		return instCardId;
	}
	public void setInstCardId(int instCardId) {
		this.instCardId = instCardId;
		index = 7;
		result += index + "*int*" + instCardId + "#";
	}

	public void setInstCardId(int instCardId, int bs) {
		this.instCardId = instCardId;
	}

	/**
		附着卡牌斗魂槽的位置  默认为0=未附着
	*/
	private int position;
	public int getPosition(){
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
		index = 8;
		result += index + "*int*" + position + "#";
	}

	public void setPosition(int position, int bs) {
		this.position = position;
	}

	/**
		当前经验
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 9;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
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
		index = 10;
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
		index = 11;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
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

	public String getResult(){
		return result;
	}

	public InstPlayerFightSoul clone(){
		InstPlayerFightSoul extend=new InstPlayerFightSoul();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setFightSoulId(this.fightSoulId);
		extend.setFightSoulQualityId(this.fightSoulQualityId);
		extend.setLevel(this.level);
		extend.setLockState(this.lockState);
		extend.setInstCardId(this.instCardId);
		extend.setPosition(this.position);
		extend.setExp(this.exp);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
