package com.huayi.doupo.base.model;

import java.io.*;

/**
	招募类型字典表
*/
@SuppressWarnings("serial")
public class DictRecruitType implements Serializable
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
		元宝招募费用
	*/
	private int goldRecruitFee;
	public int getGoldRecruitFee(){
		return goldRecruitFee;
	}
	public void setGoldRecruitFee(int goldRecruitFee) {
		this.goldRecruitFee = goldRecruitFee;
		index = 3;
		result += index + "*int*" + goldRecruitFee + "#";
	}

	public void setGoldRecruitFee(int goldRecruitFee, int bs) {
		this.goldRecruitFee = goldRecruitFee;
	}

	/**
		抽白卡所占权重
	*/
	private int whiteWeight;
	public int getWhiteWeight(){
		return whiteWeight;
	}
	public void setWhiteWeight(int whiteWeight) {
		this.whiteWeight = whiteWeight;
		index = 4;
		result += index + "*int*" + whiteWeight + "#";
	}

	public void setWhiteWeight(int whiteWeight, int bs) {
		this.whiteWeight = whiteWeight;
	}

	/**
		抽绿卡所占权重
	*/
	private int greenWeight;
	public int getGreenWeight(){
		return greenWeight;
	}
	public void setGreenWeight(int greenWeight) {
		this.greenWeight = greenWeight;
		index = 5;
		result += index + "*int*" + greenWeight + "#";
	}

	public void setGreenWeight(int greenWeight, int bs) {
		this.greenWeight = greenWeight;
	}

	/**
		抽蓝卡所占权重
	*/
	private int blueWeight;
	public int getBlueWeight(){
		return blueWeight;
	}
	public void setBlueWeight(int blueWeight) {
		this.blueWeight = blueWeight;
		index = 6;
		result += index + "*int*" + blueWeight + "#";
	}

	public void setBlueWeight(int blueWeight, int bs) {
		this.blueWeight = blueWeight;
	}

	/**
		抽紫卡所占权重
	*/
	private int purpleWeight;
	public int getPurpleWeight(){
		return purpleWeight;
	}
	public void setPurpleWeight(int purpleWeight) {
		this.purpleWeight = purpleWeight;
		index = 7;
		result += index + "*int*" + purpleWeight + "#";
	}

	public void setPurpleWeight(int purpleWeight, int bs) {
		this.purpleWeight = purpleWeight;
	}

	/**
		抽金卡所占权重
	*/
	private int goldWeight;
	public int getGoldWeight(){
		return goldWeight;
	}
	public void setGoldWeight(int goldWeight) {
		this.goldWeight = goldWeight;
		index = 8;
		result += index + "*int*" + goldWeight + "#";
	}

	public void setGoldWeight(int goldWeight, int bs) {
		this.goldWeight = goldWeight;
	}

	/**
		冷却时间[分钟] 单位-分钟
	*/
	private int coolTime;
	public int getCoolTime(){
		return coolTime;
	}
	public void setCoolTime(int coolTime) {
		this.coolTime = coolTime;
		index = 9;
		result += index + "*int*" + coolTime + "#";
	}

	public void setCoolTime(int coolTime, int bs) {
		this.coolTime = coolTime;
	}

	/**
		首次招募获得的卡牌列表
	*/
	private String firstRecruitCardList;
	public String getFirstRecruitCardList(){
		return firstRecruitCardList;
	}
	public void setFirstRecruitCardList(String firstRecruitCardList) {
		this.firstRecruitCardList = firstRecruitCardList;
		index = 10;
		result += index + "*String*" + firstRecruitCardList + "#";
	}

	public void setFirstRecruitCardList(String firstRecruitCardList, int bs) {
		this.firstRecruitCardList = firstRecruitCardList;
	}

	/**
		以最后一次为准,重复间隔数
	*/
	private int interval;
	public int getInterval(){
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
		index = 11;
		result += index + "*int*" + interval + "#";
	}

	public void setInterval(int interval, int bs) {
		this.interval = interval;
	}

	/**
		以最后一次为准,间隔多少次到那个库编号下找卡牌
	*/
	private int intervalAreaNo;
	public int getIntervalAreaNo(){
		return intervalAreaNo;
	}
	public void setIntervalAreaNo(int intervalAreaNo) {
		this.intervalAreaNo = intervalAreaNo;
		index = 12;
		result += index + "*int*" + intervalAreaNo + "#";
	}

	public void setIntervalAreaNo(int intervalAreaNo, int bs) {
		this.intervalAreaNo = intervalAreaNo;
	}

	/**
		钻石招募中招募十次去那个库编号下找卡牌
	*/
	private int diamondTenAreaNo;
	public int getDiamondTenAreaNo(){
		return diamondTenAreaNo;
	}
	public void setDiamondTenAreaNo(int diamondTenAreaNo) {
		this.diamondTenAreaNo = diamondTenAreaNo;
		index = 13;
		result += index + "*int*" + diamondTenAreaNo + "#";
	}

	public void setDiamondTenAreaNo(int diamondTenAreaNo, int bs) {
		this.diamondTenAreaNo = diamondTenAreaNo;
	}

	/**
		静态字段
	*/
	private String sname;
	public String getSname(){
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
		index = 14;
		result += index + "*String*" + sname + "#";
	}

	public void setSname(String sname, int bs) {
		this.sname = sname;
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
		index = 15;
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
		index = 16;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictRecruitType clone(){
		DictRecruitType extend=new DictRecruitType();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setGoldRecruitFee(this.goldRecruitFee);
		extend.setWhiteWeight(this.whiteWeight);
		extend.setGreenWeight(this.greenWeight);
		extend.setBlueWeight(this.blueWeight);
		extend.setPurpleWeight(this.purpleWeight);
		extend.setGoldWeight(this.goldWeight);
		extend.setCoolTime(this.coolTime);
		extend.setFirstRecruitCardList(this.firstRecruitCardList);
		extend.setInterval(this.interval);
		extend.setIntervalAreaNo(this.intervalAreaNo);
		extend.setDiamondTenAreaNo(this.diamondTenAreaNo);
		extend.setSname(this.sname);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
