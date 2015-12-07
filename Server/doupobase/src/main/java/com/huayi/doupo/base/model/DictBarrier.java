package com.huayi.doupo.base.model;

import java.io.*;

/**
	副本关卡字典表
*/
@SuppressWarnings("serial")
public class DictBarrier implements Serializable
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
		名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 2;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
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
		index = 3;
		result += index + "*int*" + chapterId + "#";
	}

	public void setChapterId(int chapterId, int bs) {
		this.chapterId = chapterId;
	}

	/**
		开启副本关卡Id
	*/
	private int barrierId;
	public int getBarrierId(){
		return barrierId;
	}
	public void setBarrierId(int barrierId) {
		this.barrierId = barrierId;
		index = 4;
		result += index + "*int*" + barrierId + "#";
	}

	public void setBarrierId(int barrierId, int bs) {
		this.barrierId = barrierId;
	}

	/**
		关卡类型 1-喽啰 2-精英 3-boss
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 5;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		x坐标
	*/
	private int x;
	public int getX(){
		return x;
	}
	public void setX(int x) {
		this.x = x;
		index = 6;
		result += index + "*int*" + x + "#";
	}

	public void setX(int x, int bs) {
		this.x = x;
	}

	/**
		y坐标
	*/
	private int y;
	public int getY(){
		return y;
	}
	public void setY(int y) {
		this.y = y;
		index = 7;
		result += index + "*int*" + y + "#";
	}

	public void setY(int y, int bs) {
		this.y = y;
	}

	/**
		关卡星数
	*/
	private int starNum;
	public int getStarNum(){
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
		index = 8;
		result += index + "*int*" + starNum + "#";
	}

	public void setStarNum(int starNum, int bs) {
		this.starNum = starNum;
	}

	/**
		显示的卡牌头像Id
	*/
	private int cardId;
	public int getCardId(){
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
		index = 9;
		result += index + "*int*" + cardId + "#";
	}

	public void setCardId(int cardId, int bs) {
		this.cardId = cardId;
	}

	/**
		消耗体力
	*/
	private int energy;
	public int getEnergy(){
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
		index = 10;
		result += index + "*int*" + energy + "#";
	}

	public void setEnergy(int energy, int bs) {
		this.energy = energy;
	}

	/**
		每日战斗次数
	*/
	private int fightNum;
	public int getFightNum(){
		return fightNum;
	}
	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
		index = 11;
		result += index + "*int*" + fightNum + "#";
	}

	public void setFightNum(int fightNum, int bs) {
		this.fightNum = fightNum;
	}

	/**
		重置次数
	*/
	private int resetNum;
	public int getResetNum(){
		return resetNum;
	}
	public void setResetNum(int resetNum) {
		this.resetNum = resetNum;
		index = 12;
		result += index + "*int*" + resetNum + "#";
	}

	public void setResetNum(int resetNum, int bs) {
		this.resetNum = resetNum;
	}

	/**
		掉落物品
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 13;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
	}

	/**
		开启等级  （用于活动副本）
	*/
	private int openLevel;
	public int getOpenLevel(){
		return openLevel;
	}
	public void setOpenLevel(int openLevel) {
		this.openLevel = openLevel;
		index = 14;
		result += index + "*int*" + openLevel + "#";
	}

	public void setOpenLevel(int openLevel, int bs) {
		this.openLevel = openLevel;
	}

	/**
		福利箱 只用于普通副本
	*/
	private String welfareBox;
	public String getWelfareBox(){
		return welfareBox;
	}
	public void setWelfareBox(String welfareBox) {
		this.welfareBox = welfareBox;
		index = 15;
		result += index + "*String*" + welfareBox + "#";
	}

	public void setWelfareBox(String welfareBox, int bs) {
		this.welfareBox = welfareBox;
	}

	/**
		福利箱x坐标
	*/
	private int boxX;
	public int getBoxX(){
		return boxX;
	}
	public void setBoxX(int boxX) {
		this.boxX = boxX;
		index = 16;
		result += index + "*int*" + boxX + "#";
	}

	public void setBoxX(int boxX, int bs) {
		this.boxX = boxX;
	}

	/**
		福利箱y坐标
	*/
	private int boxY;
	public int getBoxY(){
		return boxY;
	}
	public void setBoxY(int boxY) {
		this.boxY = boxY;
		index = 17;
		result += index + "*int*" + boxY + "#";
	}

	public void setBoxY(int boxY, int bs) {
		this.boxY = boxY;
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
		index = 18;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 19;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictBarrier clone(){
		DictBarrier extend=new DictBarrier();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setChapterId(this.chapterId);
		extend.setBarrierId(this.barrierId);
		extend.setType(this.type);
		extend.setX(this.x);
		extend.setY(this.y);
		extend.setStarNum(this.starNum);
		extend.setCardId(this.cardId);
		extend.setEnergy(this.energy);
		extend.setFightNum(this.fightNum);
		extend.setResetNum(this.resetNum);
		extend.setThings(this.things);
		extend.setOpenLevel(this.openLevel);
		extend.setWelfareBox(this.welfareBox);
		extend.setBoxX(this.boxX);
		extend.setBoxY(this.boxY);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
