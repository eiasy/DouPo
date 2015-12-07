package com.huayi.doupo.base.model;

import java.io.*;

/**
	系统统计表 统计最大最小在线人数
*/
@SuppressWarnings("serial")
public class SysStatics implements Serializable
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
		最大在线人数
	*/
	private int maxOnlineNum;
	public int getMaxOnlineNum(){
		return maxOnlineNum;
	}
	public void setMaxOnlineNum(int maxOnlineNum) {
		this.maxOnlineNum = maxOnlineNum;
		index = 2;
		result += index + "*int*" + maxOnlineNum + "#";
	}

	public void setMaxOnlineNum(int maxOnlineNum, int bs) {
		this.maxOnlineNum = maxOnlineNum;
	}

	/**
		最大在线人数时间点
	*/
	private String maxOnlineTime;
	public String getMaxOnlineTime(){
		return maxOnlineTime;
	}
	public void setMaxOnlineTime(String maxOnlineTime) {
		this.maxOnlineTime = maxOnlineTime;
		index = 3;
		result += index + "*String*" + maxOnlineTime + "#";
	}

	public void setMaxOnlineTime(String maxOnlineTime, int bs) {
		this.maxOnlineTime = maxOnlineTime;
	}

	/**
		最小在线人数
	*/
	private int minOnlineNum;
	public int getMinOnlineNum(){
		return minOnlineNum;
	}
	public void setMinOnlineNum(int minOnlineNum) {
		this.minOnlineNum = minOnlineNum;
		index = 4;
		result += index + "*int*" + minOnlineNum + "#";
	}

	public void setMinOnlineNum(int minOnlineNum, int bs) {
		this.minOnlineNum = minOnlineNum;
	}

	/**
		最小在线人数时间点
	*/
	private String minOnlineTime;
	public String getMinOnlineTime(){
		return minOnlineTime;
	}
	public void setMinOnlineTime(String minOnlineTime) {
		this.minOnlineTime = minOnlineTime;
		index = 5;
		result += index + "*String*" + minOnlineTime + "#";
	}

	public void setMinOnlineTime(String minOnlineTime, int bs) {
		this.minOnlineTime = minOnlineTime;
	}

	/**
		最大打世界Boss的人数
	*/
	private int maxHitBossNum;
	public int getMaxHitBossNum(){
		return maxHitBossNum;
	}
	public void setMaxHitBossNum(int maxHitBossNum) {
		this.maxHitBossNum = maxHitBossNum;
		index = 6;
		result += index + "*int*" + maxHitBossNum + "#";
	}

	public void setMaxHitBossNum(int maxHitBossNum, int bs) {
		this.maxHitBossNum = maxHitBossNum;
	}

	/**
		次日留存
	*/
	private float twoDayBackPer;
	public float getTwoDayBackPer(){
		return twoDayBackPer;
	}
	public void setTwoDayBackPer(float twoDayBackPer) {
		this.twoDayBackPer = twoDayBackPer;
		index = 7;
		result += index + "*float*" + twoDayBackPer + "#";
	}

	public void setTwoDayBackPer(float twoDayBackPer, int bs) {
		this.twoDayBackPer = twoDayBackPer;
	}

	/**
		三日留存
	*/
	private float threeDayBackPer;
	public float getThreeDayBackPer(){
		return threeDayBackPer;
	}
	public void setThreeDayBackPer(float threeDayBackPer) {
		this.threeDayBackPer = threeDayBackPer;
		index = 8;
		result += index + "*float*" + threeDayBackPer + "#";
	}

	public void setThreeDayBackPer(float threeDayBackPer, int bs) {
		this.threeDayBackPer = threeDayBackPer;
	}

	/**
		七日留存
	*/
	private float sevenDayBackPer;
	public float getSevenDayBackPer(){
		return sevenDayBackPer;
	}
	public void setSevenDayBackPer(float sevenDayBackPer) {
		this.sevenDayBackPer = sevenDayBackPer;
		index = 9;
		result += index + "*float*" + sevenDayBackPer + "#";
	}

	public void setSevenDayBackPer(float sevenDayBackPer, int bs) {
		this.sevenDayBackPer = sevenDayBackPer;
	}

	/**
		
	*/
	private float thirtyDayBackPer;
	public float getThirtyDayBackPer(){
		return thirtyDayBackPer;
	}
	public void setThirtyDayBackPer(float thirtyDayBackPer) {
		this.thirtyDayBackPer = thirtyDayBackPer;
		index = 10;
		result += index + "*float*" + thirtyDayBackPer + "#";
	}

	public void setThirtyDayBackPer(float thirtyDayBackPer, int bs) {
		this.thirtyDayBackPer = thirtyDayBackPer;
	}

	/**
		处理时间
	*/
	private String settleTime;
	public String getSettleTime(){
		return settleTime;
	}
	public void setSettleTime(String settleTime) {
		this.settleTime = settleTime;
		index = 11;
		result += index + "*String*" + settleTime + "#";
	}

	public void setSettleTime(String settleTime, int bs) {
		this.settleTime = settleTime;
	}

	/**
		
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

	public SysStatics clone(){
		SysStatics extend=new SysStatics();
		extend.setId(this.id);
		extend.setMaxOnlineNum(this.maxOnlineNum);
		extend.setMaxOnlineTime(this.maxOnlineTime);
		extend.setMinOnlineNum(this.minOnlineNum);
		extend.setMinOnlineTime(this.minOnlineTime);
		extend.setMaxHitBossNum(this.maxHitBossNum);
		extend.setTwoDayBackPer(this.twoDayBackPer);
		extend.setThreeDayBackPer(this.threeDayBackPer);
		extend.setSevenDayBackPer(this.sevenDayBackPer);
		extend.setThirtyDayBackPer(this.thirtyDayBackPer);
		extend.setSettleTime(this.settleTime);
		extend.setVersion(this.version);
		return extend;
	}
}
