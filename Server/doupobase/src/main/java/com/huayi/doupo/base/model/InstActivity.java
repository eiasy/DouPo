package com.huayi.doupo.base.model;

import java.io.*;

/**
	活动实例表（目前是涉及到拍卖行跟黑角域）
*/
@SuppressWarnings("serial")
public class InstActivity implements Serializable
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
		活动字典Id
	*/
	private int activityId;
	public int getActivityId(){
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
		index = 3;
		result += index + "*int*" + activityId + "#";
	}

	public void setActivityId(int activityId, int bs) {
		this.activityId = activityId;
	}

	/**
		活动开启时间   在开服基金活动中，此字段记录已领取的基金id并用分号分开  限时抢购表示已经购买的Id分号隔开 月卡的结束时间 星星兑换记录(id_次数)
	*/
	private String activityTime;
	public String getActivityTime(){
		return activityTime;
	}
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
		index = 4;
		result += index + "*String*" + activityTime + "#";
	}

	public void setActivityTime(String activityTime, int bs) {
		this.activityTime = activityTime;
	}

	/**
		是否永久 0-非永久 1-永久
	*/
	private int isForever;
	public int getIsForever(){
		return isForever;
	}
	public void setIsForever(int isForever) {
		this.isForever = isForever;
		index = 5;
		result += index + "*int*" + isForever + "#";
	}

	public void setIsForever(int isForever, int bs) {
		this.isForever = isForever;
	}

	/**
		活动使用次数 hjy-已刷新次数  限时抢购表示结束时间的秒数 月卡的类型 星星兑换消耗的星数
	*/
	private int useNum;
	public int getUseNum(){
		return useNum;
	}
	public void setUseNum(int useNum) {
		this.useNum = useNum;
		index = 6;
		result += index + "*int*" + useNum + "#";
	}

	public void setUseNum(int useNum, int bs) {
		this.useNum = useNum;
	}

	/**
		备用字段1 月卡最近一次的领取时间
	*/
	private String spareOne;
	public String getSpareOne(){
		return spareOne;
	}
	public void setSpareOne(String spareOne) {
		this.spareOne = spareOne;
		index = 7;
		result += index + "*String*" + spareOne + "#";
	}

	public void setSpareOne(String spareOne, int bs) {
		this.spareOne = spareOne;
	}

	/**
		备用字段2
	*/
	private String spareTwo;
	public String getSpareTwo(){
		return spareTwo;
	}
	public void setSpareTwo(String spareTwo) {
		this.spareTwo = spareTwo;
		index = 8;
		result += index + "*String*" + spareTwo + "#";
	}

	public void setSpareTwo(String spareTwo, int bs) {
		this.spareTwo = spareTwo;
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

	public InstActivity clone(){
		InstActivity extend=new InstActivity();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setActivityId(this.activityId);
		extend.setActivityTime(this.activityTime);
		extend.setIsForever(this.isForever);
		extend.setUseNum(this.useNum);
		extend.setSpareOne(this.spareOne);
		extend.setSpareTwo(this.spareTwo);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
