package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家法宝功法实例表
*/
@SuppressWarnings("serial")
public class InstPlayerMagic implements Serializable
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
		法宝功法Id
	*/
	private int magicId;
	public int getMagicId(){
		return magicId;
	}
	public void setMagicId(int magicId) {
		this.magicId = magicId;
		index = 3;
		result += index + "*int*" + magicId + "#";
	}

	public void setMagicId(int magicId, int bs) {
		this.magicId = magicId;
	}

	/**
		法宝功法类型 1-法宝 2-功法
	*/
	private int magicType;
	public int getMagicType(){
		return magicType;
	}
	public void setMagicType(int magicType) {
		this.magicType = magicType;
		index = 4;
		result += index + "*int*" + magicType + "#";
	}

	public void setMagicType(int magicType, int bs) {
		this.magicType = magicType;
	}

	/**
		品质 1-天 2-地 3-玄 -黄
	*/
	private int quality;
	public int getQuality(){
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
		index = 5;
		result += index + "*int*" + quality + "#";
	}

	public void setQuality(int quality, int bs) {
		this.quality = quality;
	}

	/**
		法宝功法等级经验字典Id
	*/
	private int magicLevelId;
	public int getMagicLevelId(){
		return magicLevelId;
	}
	public void setMagicLevelId(int magicLevelId) {
		this.magicLevelId = magicLevelId;
		index = 6;
		result += index + "*int*" + magicLevelId + "#";
	}

	public void setMagicLevelId(int magicLevelId, int bs) {
		this.magicLevelId = magicLevelId;
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
		index = 7;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		卡牌Id 此功法/法宝被装备在哪个卡牌身上.未装备为0
	*/
	private int instCardId;
	public int getInstCardId(){
		return instCardId;
	}
	public void setInstCardId(int instCardId) {
		this.instCardId = instCardId;
		index = 8;
		result += index + "*int*" + instCardId + "#";
	}

	public void setInstCardId(int instCardId, int bs) {
		this.instCardId = instCardId;
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
		index = 9;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		
	*/
	private int advanceId;
	public int getAdvanceId(){
		return advanceId;
	}
	public void setAdvanceId(int advanceId) {
		this.advanceId = advanceId;
		index = 10;
		result += index + "*int*" + advanceId + "#";
	}

	public void setAdvanceId(int advanceId, int bs) {
		this.advanceId = advanceId;
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

	public InstPlayerMagic clone(){
		InstPlayerMagic extend=new InstPlayerMagic();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setMagicId(this.magicId);
		extend.setMagicType(this.magicType);
		extend.setQuality(this.quality);
		extend.setMagicLevelId(this.magicLevelId);
		extend.setExp(this.exp);
		extend.setInstCardId(this.instCardId);
		extend.setVersion(this.version);
		extend.setAdvanceId(this.advanceId);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
