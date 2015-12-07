package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家卡牌魂魄实例表
*/
@SuppressWarnings("serial")
public class InstPlayerCardSoul implements Serializable
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
		卡牌字典Id
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
		卡牌魂魄字典Id
	*/
	private int cardSoulId;
	public int getCardSoulId(){
		return cardSoulId;
	}
	public void setCardSoulId(int cardSoulId) {
		this.cardSoulId = cardSoulId;
		index = 4;
		result += index + "*int*" + cardSoulId + "#";
	}

	public void setCardSoulId(int cardSoulId, int bs) {
		this.cardSoulId = cardSoulId;
	}

	/**
		数量
	*/
	private int num;
	public int getNum(){
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		index = 5;
		result += index + "*int*" + num + "#";
	}

	public void setNum(int num, int bs) {
		this.num = num;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		插入时间
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 7;
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
		index = 8;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerCardSoul clone(){
		InstPlayerCardSoul extend=new InstPlayerCardSoul();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setCardId(this.cardId);
		extend.setCardSoulId(this.cardSoulId);
		extend.setNum(this.num);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
