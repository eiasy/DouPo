package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家装备实例表
*/
@SuppressWarnings("serial")
public class InstPlayerEquip implements Serializable
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
		装备类型Id
	*/
	private int equipTypeId;
	public int getEquipTypeId(){
		return equipTypeId;
	}
	public void setEquipTypeId(int equipTypeId) {
		this.equipTypeId = equipTypeId;
		index = 3;
		result += index + "*int*" + equipTypeId + "#";
	}

	public void setEquipTypeId(int equipTypeId, int bs) {
		this.equipTypeId = equipTypeId;
	}

	/**
		装备Id
	*/
	private int equipId;
	public int getEquipId(){
		return equipId;
	}
	public void setEquipId(int equipId) {
		this.equipId = equipId;
		index = 4;
		result += index + "*int*" + equipId + "#";
	}

	public void setEquipId(int equipId, int bs) {
		this.equipId = equipId;
	}

	/**
		装备等级
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
		卡牌Id  此装备被装备在哪个卡牌身上.未装备为0
	*/
	private int instCardId;
	public int getInstCardId(){
		return instCardId;
	}
	public void setInstCardId(int instCardId) {
		this.instCardId = instCardId;
		index = 6;
		result += index + "*int*" + instCardId + "#";
	}

	public void setInstCardId(int instCardId, int bs) {
		this.instCardId = instCardId;
	}

	/**
		是否镶嵌 0-未镶嵌 1-已镶嵌
	*/
	private int isInlay;
	public int getIsInlay(){
		return isInlay;
	}
	public void setIsInlay(int isInlay) {
		this.isInlay = isInlay;
		index = 7;
		result += index + "*int*" + isInlay + "#";
	}

	public void setIsInlay(int isInlay, int bs) {
		this.isInlay = isInlay;
	}

	/**
		装备进阶字典Id 当前进行的进阶
	*/
	private int equipAdvanceId;
	public int getEquipAdvanceId(){
		return equipAdvanceId;
	}
	public void setEquipAdvanceId(int equipAdvanceId) {
		this.equipAdvanceId = equipAdvanceId;
		index = 8;
		result += index + "*int*" + equipAdvanceId + "#";
	}

	public void setEquipAdvanceId(int equipAdvanceId, int bs) {
		this.equipAdvanceId = equipAdvanceId;
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

	public String getResult(){
		return result;
	}

	public InstPlayerEquip clone(){
		InstPlayerEquip extend=new InstPlayerEquip();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setEquipTypeId(this.equipTypeId);
		extend.setEquipId(this.equipId);
		extend.setLevel(this.level);
		extend.setInstCardId(this.instCardId);
		extend.setIsInlay(this.isInlay);
		extend.setEquipAdvanceId(this.equipAdvanceId);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
