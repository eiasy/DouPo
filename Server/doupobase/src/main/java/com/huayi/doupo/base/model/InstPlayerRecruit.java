package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家招募实例表
*/
@SuppressWarnings("serial")
public class InstPlayerRecruit implements Serializable
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
		招募类型Id
	*/
	private int recruitTypeId;
	public int getRecruitTypeId(){
		return recruitTypeId;
	}
	public void setRecruitTypeId(int recruitTypeId) {
		this.recruitTypeId = recruitTypeId;
		index = 3;
		result += index + "*int*" + recruitTypeId + "#";
	}

	public void setRecruitTypeId(int recruitTypeId, int bs) {
		this.recruitTypeId = recruitTypeId;
	}

	/**
		招募总次数
	*/
	private int recruitSumTimes;
	public int getRecruitSumTimes(){
		return recruitSumTimes;
	}
	public void setRecruitSumTimes(int recruitSumTimes) {
		this.recruitSumTimes = recruitSumTimes;
		index = 4;
		result += index + "*int*" + recruitSumTimes + "#";
	}

	public void setRecruitSumTimes(int recruitSumTimes, int bs) {
		this.recruitSumTimes = recruitSumTimes;
	}

	/**
		费用招募次数
	*/
	private int feeRecruitTimes;
	public int getFeeRecruitTimes(){
		return feeRecruitTimes;
	}
	public void setFeeRecruitTimes(int feeRecruitTimes) {
		this.feeRecruitTimes = feeRecruitTimes;
		index = 5;
		result += index + "*int*" + feeRecruitTimes + "#";
	}

	public void setFeeRecruitTimes(int feeRecruitTimes, int bs) {
		this.feeRecruitTimes = feeRecruitTimes;
	}

	/**
		最后一次招募时间
	*/
	private String lastRecruitTime;
	public String getLastRecruitTime(){
		return lastRecruitTime;
	}
	public void setLastRecruitTime(String lastRecruitTime) {
		this.lastRecruitTime = lastRecruitTime;
		index = 6;
		result += index + "*String*" + lastRecruitTime + "#";
	}

	public void setLastRecruitTime(String lastRecruitTime, int bs) {
		this.lastRecruitTime = lastRecruitTime;
	}

	/**
		最后一次免费招募时间
	*/
	private String lastFreeRecruitTime;
	public String getLastFreeRecruitTime(){
		return lastFreeRecruitTime;
	}
	public void setLastFreeRecruitTime(String lastFreeRecruitTime) {
		this.lastFreeRecruitTime = lastFreeRecruitTime;
		index = 7;
		result += index + "*String*" + lastFreeRecruitTime + "#";
	}

	public void setLastFreeRecruitTime(String lastFreeRecruitTime, int bs) {
		this.lastFreeRecruitTime = lastFreeRecruitTime;
	}

	/**
		下一次免费招募时间
	*/
	private String nextFreeRecruitTime;
	public String getNextFreeRecruitTime(){
		return nextFreeRecruitTime;
	}
	public void setNextFreeRecruitTime(String nextFreeRecruitTime) {
		this.nextFreeRecruitTime = nextFreeRecruitTime;
		index = 8;
		result += index + "*String*" + nextFreeRecruitTime + "#";
	}

	public void setNextFreeRecruitTime(String nextFreeRecruitTime, int bs) {
		this.nextFreeRecruitTime = nextFreeRecruitTime;
	}

	/**
		白银招募中每日剩余招募次数
	*/
	private int remainRecruitTimes;
	public int getRemainRecruitTimes(){
		return remainRecruitTimes;
	}
	public void setRemainRecruitTimes(int remainRecruitTimes) {
		this.remainRecruitTimes = remainRecruitTimes;
		index = 9;
		result += index + "*int*" + remainRecruitTimes + "#";
	}

	public void setRemainRecruitTimes(int remainRecruitTimes, int bs) {
		this.remainRecruitTimes = remainRecruitTimes;
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
		index = 10;
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

	public InstPlayerRecruit clone(){
		InstPlayerRecruit extend=new InstPlayerRecruit();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setRecruitTypeId(this.recruitTypeId);
		extend.setRecruitSumTimes(this.recruitSumTimes);
		extend.setFeeRecruitTimes(this.feeRecruitTimes);
		extend.setLastRecruitTime(this.lastRecruitTime);
		extend.setLastFreeRecruitTime(this.lastFreeRecruitTime);
		extend.setNextFreeRecruitTime(this.nextFreeRecruitTime);
		extend.setRemainRecruitTimes(this.remainRecruitTimes);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
