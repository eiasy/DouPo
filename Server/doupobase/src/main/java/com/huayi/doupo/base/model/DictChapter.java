package com.huayi.doupo.base.model;

import java.io.*;

/**
	副本章节字典表
*/
@SuppressWarnings("serial")
public class DictChapter implements Serializable
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
		章节类型 1-普通 2-精英 3-活动
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 3;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		开启时间 1;3;5
	*/
	private String openTime;
	public String getOpenTime(){
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
		index = 4;
		result += index + "*String*" + openTime + "#";
	}

	public void setOpenTime(String openTime, int bs) {
		this.openTime = openTime;
	}

	/**
		开启等级 玩家等级
	*/
	private int openLeve;
	public int getOpenLeve(){
		return openLeve;
	}
	public void setOpenLeve(int openLeve) {
		this.openLeve = openLeve;
		index = 5;
		result += index + "*int*" + openLeve + "#";
	}

	public void setOpenLeve(int openLeve, int bs) {
		this.openLeve = openLeve;
	}

	/**
		开启副本章节Id
	*/
	private int chapterId;
	public int getChapterId(){
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
		index = 6;
		result += index + "*int*" + chapterId + "#";
	}

	public void setChapterId(int chapterId, int bs) {
		this.chapterId = chapterId;
	}

	/**
		总星数
	*/
	private int starNum;
	public int getStarNum(){
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
		index = 7;
		result += index + "*int*" + starNum + "#";
	}

	public void setStarNum(int starNum, int bs) {
		this.starNum = starNum;
	}

	/**
		一阶段星数
	*/
	private int starOne;
	public int getStarOne(){
		return starOne;
	}
	public void setStarOne(int starOne) {
		this.starOne = starOne;
		index = 8;
		result += index + "*int*" + starOne + "#";
	}

	public void setStarOne(int starOne, int bs) {
		this.starOne = starOne;
	}

	/**
		一阶段获得物品 tableTypeId_tableFieldId_tableValue
	*/
	private String thingsOne;
	public String getThingsOne(){
		return thingsOne;
	}
	public void setThingsOne(String thingsOne) {
		this.thingsOne = thingsOne;
		index = 9;
		result += index + "*String*" + thingsOne + "#";
	}

	public void setThingsOne(String thingsOne, int bs) {
		this.thingsOne = thingsOne;
	}

	/**
		二阶段星数
	*/
	private int starTwo;
	public int getStarTwo(){
		return starTwo;
	}
	public void setStarTwo(int starTwo) {
		this.starTwo = starTwo;
		index = 10;
		result += index + "*int*" + starTwo + "#";
	}

	public void setStarTwo(int starTwo, int bs) {
		this.starTwo = starTwo;
	}

	/**
		二阶段获得物品 tableTypeId_tableFieldId_tableValue
	*/
	private String thingsTwo;
	public String getThingsTwo(){
		return thingsTwo;
	}
	public void setThingsTwo(String thingsTwo) {
		this.thingsTwo = thingsTwo;
		index = 11;
		result += index + "*String*" + thingsTwo + "#";
	}

	public void setThingsTwo(String thingsTwo, int bs) {
		this.thingsTwo = thingsTwo;
	}

	/**
		三阶段星数
	*/
	private int starThree;
	public int getStarThree(){
		return starThree;
	}
	public void setStarThree(int starThree) {
		this.starThree = starThree;
		index = 12;
		result += index + "*int*" + starThree + "#";
	}

	public void setStarThree(int starThree, int bs) {
		this.starThree = starThree;
	}

	/**
		三阶段获得物品 tableTypeId_tableFieldId_tableValue
	*/
	private String thingsThree;
	public String getThingsThree(){
		return thingsThree;
	}
	public void setThingsThree(String thingsThree) {
		this.thingsThree = thingsThree;
		index = 13;
		result += index + "*String*" + thingsThree + "#";
	}

	public void setThingsThree(String thingsThree, int bs) {
		this.thingsThree = thingsThree;
	}

	/**
		是否包含节点
	*/
	private int isBarrier;
	public int getIsBarrier(){
		return isBarrier;
	}
	public void setIsBarrier(int isBarrier) {
		this.isBarrier = isBarrier;
		index = 14;
		result += index + "*int*" + isBarrier + "#";
	}

	public void setIsBarrier(int isBarrier, int bs) {
		this.isBarrier = isBarrier;
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
		index = 15;
		result += index + "*int*" + fightNum + "#";
	}

	public void setFightNum(int fightNum, int bs) {
		this.fightNum = fightNum;
	}

	/**
		背景小图
	*/
	private String backGroundPictureS;
	public String getBackGroundPictureS(){
		return backGroundPictureS;
	}
	public void setBackGroundPictureS(String backGroundPictureS) {
		this.backGroundPictureS = backGroundPictureS;
		index = 16;
		result += index + "*String*" + backGroundPictureS + "#";
	}

	public void setBackGroundPictureS(String backGroundPictureS, int bs) {
		this.backGroundPictureS = backGroundPictureS;
	}

	/**
		背景大图
	*/
	private String backGroundPictureD;
	public String getBackGroundPictureD(){
		return backGroundPictureD;
	}
	public void setBackGroundPictureD(String backGroundPictureD) {
		this.backGroundPictureD = backGroundPictureD;
		index = 17;
		result += index + "*String*" + backGroundPictureD + "#";
	}

	public void setBackGroundPictureD(String backGroundPictureD, int bs) {
		this.backGroundPictureD = backGroundPictureD;
	}

	/**
		战斗背景
	*/
	private String backGroundWar;
	public String getBackGroundWar(){
		return backGroundWar;
	}
	public void setBackGroundWar(String backGroundWar) {
		this.backGroundWar = backGroundWar;
		index = 18;
		result += index + "*String*" + backGroundWar + "#";
	}

	public void setBackGroundWar(String backGroundWar, int bs) {
		this.backGroundWar = backGroundWar;
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
		index = 19;
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
		index = 20;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictChapter clone(){
		DictChapter extend=new DictChapter();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setType(this.type);
		extend.setOpenTime(this.openTime);
		extend.setOpenLeve(this.openLeve);
		extend.setChapterId(this.chapterId);
		extend.setStarNum(this.starNum);
		extend.setStarOne(this.starOne);
		extend.setThingsOne(this.thingsOne);
		extend.setStarTwo(this.starTwo);
		extend.setThingsTwo(this.thingsTwo);
		extend.setStarThree(this.starThree);
		extend.setThingsThree(this.thingsThree);
		extend.setIsBarrier(this.isBarrier);
		extend.setFightNum(this.fightNum);
		extend.setBackGroundPictureS(this.backGroundPictureS);
		extend.setBackGroundPictureD(this.backGroundPictureD);
		extend.setBackGroundWar(this.backGroundWar);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
