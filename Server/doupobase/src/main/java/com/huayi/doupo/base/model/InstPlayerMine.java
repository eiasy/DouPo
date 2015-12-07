package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class InstPlayerMine implements Serializable
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
		矿的ID
	*/
	private int mineId;
	public int getMineId(){
		return mineId;
	}
	public void setMineId(int mineId) {
		this.mineId = mineId;
		index = 2;
		result += index + "*int*" + mineId + "#";
	}

	public void setMineId(int mineId, int bs) {
		this.mineId = mineId;
	}

	/**
		矿主的类型 1.铁矿 2.铜矿 3.银矿 4.金矿
	*/
	private int mineType;
	public int getMineType(){
		return mineType;
	}
	public void setMineType(int mineType) {
		this.mineType = mineType;
		index = 3;
		result += index + "*int*" + mineType + "#";
	}

	public void setMineType(int mineType, int bs) {
		this.mineType = mineType;
	}

	/**
		矿主的类型 0.普通矿区 1.高级矿区
	*/
	private int mineZone;
	public int getMineZone(){
		return mineZone;
	}
	public void setMineZone(int mineZone) {
		this.mineZone = mineZone;
		index = 4;
		result += index + "*int*" + mineZone + "#";
	}

	public void setMineZone(int mineZone, int bs) {
		this.mineZone = mineZone;
	}

	/**
		矿区页数
	*/
	private int minePage;
	public int getMinePage(){
		return minePage;
	}
	public void setMinePage(int minePage) {
		this.minePage = minePage;
		index = 5;
		result += index + "*int*" + minePage + "#";
	}

	public void setMinePage(int minePage, int bs) {
		this.minePage = minePage;
	}

	/**
		当前矿区页数里的第几个
	*/
	private int mineIndex;
	public int getMineIndex(){
		return mineIndex;
	}
	public void setMineIndex(int mineIndex) {
		this.mineIndex = mineIndex;
		index = 6;
		result += index + "*int*" + mineIndex + "#";
	}

	public void setMineIndex(int mineIndex, int bs) {
		this.mineIndex = mineIndex;
	}

	/**
		玩家ID
	*/
	private int instPlayerId;
	public int getInstPlayerId(){
		return instPlayerId;
	}
	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
		index = 7;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
	}

	/**
		当前矿被从空矿变成被占领的时间
	*/
	private String initTime;
	public String getInitTime(){
		return initTime;
	}
	public void setInitTime(String initTime) {
		this.initTime = initTime;
		index = 8;
		result += index + "*String*" + initTime + "#";
	}

	public void setInitTime(String initTime, int bs) {
		this.initTime = initTime;
	}

	/**
		当前矿被重新占领的时间
	*/
	private String masterTime;
	public String getMasterTime(){
		return masterTime;
	}
	public void setMasterTime(String masterTime) {
		this.masterTime = masterTime;
		index = 9;
		result += index + "*String*" + masterTime + "#";
	}

	public void setMasterTime(String masterTime, int bs) {
		this.masterTime = masterTime;
	}

	/**
		协助者1的ID
	*/
	private int assistId1;
	public int getAssistId1(){
		return assistId1;
	}
	public void setAssistId1(int assistId1) {
		this.assistId1 = assistId1;
		index = 10;
		result += index + "*int*" + assistId1 + "#";
	}

	public void setAssistId1(int assistId1, int bs) {
		this.assistId1 = assistId1;
	}

	/**
		协助者1开始协助的时间
	*/
	private String aTime1;
	public String getATime1(){
		return aTime1;
	}
	public void setATime1(String aTime1) {
		this.aTime1 = aTime1;
		index = 11;
		result += index + "*String*" + aTime1 + "#";
	}

	public void setATime1(String aTime1, int bs) {
		this.aTime1 = aTime1;
	}

	/**
		协助者2的ID
	*/
	private int assistId2;
	public int getAssistId2(){
		return assistId2;
	}
	public void setAssistId2(int assistId2) {
		this.assistId2 = assistId2;
		index = 12;
		result += index + "*int*" + assistId2 + "#";
	}

	public void setAssistId2(int assistId2, int bs) {
		this.assistId2 = assistId2;
	}

	/**
		协助者2开始协助的时间
	*/
	private String aTime2;
	public String getATime2(){
		return aTime2;
	}
	public void setATime2(String aTime2) {
		this.aTime2 = aTime2;
		index = 13;
		result += index + "*String*" + aTime2 + "#";
	}

	public void setATime2(String aTime2, int bs) {
		this.aTime2 = aTime2;
	}

	/**
		当协助者离开时记录协助的时间长度，秒为单位
	*/
	private int extratime;
	public int getExtratime(){
		return extratime;
	}
	public void setExtratime(int extratime) {
		this.extratime = extratime;
		index = 14;
		result += index + "*int*" + extratime + "#";
	}

	public void setExtratime(int extratime, int bs) {
		this.extratime = extratime;
	}

	/**
		天气变化
	*/
	private int weather;
	public int getWeather(){
		return weather;
	}
	public void setWeather(int weather) {
		this.weather = weather;
		index = 15;
		result += index + "*int*" + weather + "#";
	}

	public void setWeather(int weather, int bs) {
		this.weather = weather;
	}

	/**
		矿主特殊奖励物品
	*/
	private String masterThing;
	public String getMasterThing(){
		return masterThing;
	}
	public void setMasterThing(String masterThing) {
		this.masterThing = masterThing;
		index = 16;
		result += index + "*String*" + masterThing + "#";
	}

	public void setMasterThing(String masterThing, int bs) {
		this.masterThing = masterThing;
	}

	/**
		特殊奖励是否领取 （0.没有领取 1.已领取）
	*/
	private int masterThingState;
	public int getMasterThingState(){
		return masterThingState;
	}
	public void setMasterThingState(int masterThingState) {
		this.masterThingState = masterThingState;
		index = 17;
		result += index + "*int*" + masterThingState + "#";
	}

	public void setMasterThingState(int masterThingState, int bs) {
		this.masterThingState = masterThingState;
	}

	/**
		协助者特殊奖励物品
	*/
	private String assistThing;
	public String getAssistThing(){
		return assistThing;
	}
	public void setAssistThing(String assistThing) {
		this.assistThing = assistThing;
		index = 18;
		result += index + "*String*" + assistThing + "#";
	}

	public void setAssistThing(String assistThing, int bs) {
		this.assistThing = assistThing;
	}

	/**
		协助者特殊奖励状态（0.没有领取 1.已领取）
	*/
	private int assistThingState;
	public int getAssistThingState(){
		return assistThingState;
	}
	public void setAssistThingState(int assistThingState) {
		this.assistThingState = assistThingState;
		index = 19;
		result += index + "*int*" + assistThingState + "#";
	}

	public void setAssistThingState(int assistThingState, int bs) {
		this.assistThingState = assistThingState;
	}

	/**
		数据版本
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

	public InstPlayerMine clone(){
		InstPlayerMine extend=new InstPlayerMine();
		extend.setId(this.id);
		extend.setMineId(this.mineId);
		extend.setMineType(this.mineType);
		extend.setMineZone(this.mineZone);
		extend.setMinePage(this.minePage);
		extend.setMineIndex(this.mineIndex);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setInitTime(this.initTime);
		extend.setMasterTime(this.masterTime);
		extend.setAssistId1(this.assistId1);
		extend.setATime1(this.aTime1);
		extend.setAssistId2(this.assistId2);
		extend.setATime2(this.aTime2);
		extend.setExtratime(this.extratime);
		extend.setWeather(this.weather);
		extend.setMasterThing(this.masterThing);
		extend.setMasterThingState(this.masterThingState);
		extend.setAssistThing(this.assistThing);
		extend.setAssistThingState(this.assistThingState);
		extend.setVersion(this.version);
		return extend;
	}
}
