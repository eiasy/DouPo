package com.huayi.doupo.base.model;

import java.io.*;

/**
	世界Boss实例表
*/
@SuppressWarnings("serial")
public class InstPlayerHoldStar implements Serializable
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
		星座档次Id
	*/
	private int holdStarGradeId;
	public int getHoldStarGradeId(){
		return holdStarGradeId;
	}
	public void setHoldStarGradeId(int holdStarGradeId) {
		this.holdStarGradeId = holdStarGradeId;
		index = 3;
		result += index + "*int*" + holdStarGradeId + "#";
	}

	public void setHoldStarGradeId(int holdStarGradeId, int bs) {
		this.holdStarGradeId = holdStarGradeId;
	}

	/**
		当前正在进行的第几阶段
	*/
	private int step;
	public int getStep(){
		return step;
	}
	public void setStep(int step) {
		this.step = step;
		index = 4;
		result += index + "*int*" + step + "#";
	}

	public void setStep(int step, int bs) {
		this.step = step;
	}

	/**
		星数
	*/
	private int starNum;
	public int getStarNum(){
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
		index = 5;
		result += index + "*int*" + starNum + "#";
	}

	public void setStarNum(int starNum, int bs) {
		this.starNum = starNum;
	}

	/**
		今日已占星次数
	*/
	private int holdStarTimes;
	public int getHoldStarTimes(){
		return holdStarTimes;
	}
	public void setHoldStarTimes(int holdStarTimes) {
		this.holdStarTimes = holdStarTimes;
		index = 6;
		result += index + "*int*" + holdStarTimes + "#";
	}

	public void setHoldStarTimes(int holdStarTimes, int bs) {
		this.holdStarTimes = holdStarTimes;
	}

	/**
		占星时间
	*/
	private String holdStarTime;
	public String getHoldStarTime(){
		return holdStarTime;
	}
	public void setHoldStarTime(String holdStarTime) {
		this.holdStarTime = holdStarTime;
		index = 7;
		result += index + "*String*" + holdStarTime + "#";
	}

	public void setHoldStarTime(String holdStarTime, int bs) {
		this.holdStarTime = holdStarTime;
	}

	/**
		占星免费已刷新次数
	*/
	private int holdStarFreeRefreshedTimes;
	public int getHoldStarFreeRefreshedTimes(){
		return holdStarFreeRefreshedTimes;
	}
	public void setHoldStarFreeRefreshedTimes(int holdStarFreeRefreshedTimes) {
		this.holdStarFreeRefreshedTimes = holdStarFreeRefreshedTimes;
		index = 8;
		result += index + "*int*" + holdStarFreeRefreshedTimes + "#";
	}

	public void setHoldStarFreeRefreshedTimes(int holdStarFreeRefreshedTimes, int bs) {
		this.holdStarFreeRefreshedTimes = holdStarFreeRefreshedTimes;
	}

	/**
		占星非免费已刷新次数
	*/
	private int holdStarNoFreeRefreshedTimes;
	public int getHoldStarNoFreeRefreshedTimes(){
		return holdStarNoFreeRefreshedTimes;
	}
	public void setHoldStarNoFreeRefreshedTimes(int holdStarNoFreeRefreshedTimes) {
		this.holdStarNoFreeRefreshedTimes = holdStarNoFreeRefreshedTimes;
		index = 9;
		result += index + "*int*" + holdStarNoFreeRefreshedTimes + "#";
	}

	public void setHoldStarNoFreeRefreshedTimes(int holdStarNoFreeRefreshedTimes, int bs) {
		this.holdStarNoFreeRefreshedTimes = holdStarNoFreeRefreshedTimes;
	}

	/**
		占星刷新时间
	*/
	private String holdStarRefreshedTime;
	public String getHoldStarRefreshedTime(){
		return holdStarRefreshedTime;
	}
	public void setHoldStarRefreshedTime(String holdStarRefreshedTime) {
		this.holdStarRefreshedTime = holdStarRefreshedTime;
		index = 10;
		result += index + "*String*" + holdStarRefreshedTime + "#";
	}

	public void setHoldStarRefreshedTime(String holdStarRefreshedTime, int bs) {
		this.holdStarRefreshedTime = holdStarRefreshedTime;
	}

	/**
		奖励已刷新次数
	*/
	private int rewardRefreshedTimes;
	public int getRewardRefreshedTimes(){
		return rewardRefreshedTimes;
	}
	public void setRewardRefreshedTimes(int rewardRefreshedTimes) {
		this.rewardRefreshedTimes = rewardRefreshedTimes;
		index = 11;
		result += index + "*int*" + rewardRefreshedTimes + "#";
	}

	public void setRewardRefreshedTimes(int rewardRefreshedTimes, int bs) {
		this.rewardRefreshedTimes = rewardRefreshedTimes;
	}

	/**
		奖励刷新时间
	*/
	private String rewardRefreshedTime;
	public String getRewardRefreshedTime(){
		return rewardRefreshedTime;
	}
	public void setRewardRefreshedTime(String rewardRefreshedTime) {
		this.rewardRefreshedTime = rewardRefreshedTime;
		index = 12;
		result += index + "*String*" + rewardRefreshedTime + "#";
	}

	public void setRewardRefreshedTime(String rewardRefreshedTime, int bs) {
		this.rewardRefreshedTime = rewardRefreshedTime;
	}

	/**
		上方星星信息 位置_星座id_状态; 状态0-未点亮 1-点亮
	*/
	private String upStars;
	public String getUpStars(){
		return upStars;
	}
	public void setUpStars(String upStars) {
		this.upStars = upStars;
		index = 13;
		result += index + "*String*" + upStars + "#";
	}

	public void setUpStars(String upStars, int bs) {
		this.upStars = upStars;
	}

	/**
		下方星星信息 位置_星座id;
	*/
	private String downStars;
	public String getDownStars(){
		return downStars;
	}
	public void setDownStars(String downStars) {
		this.downStars = downStars;
		index = 14;
		result += index + "*String*" + downStars + "#";
	}

	public void setDownStars(String downStars, int bs) {
		this.downStars = downStars;
	}

	/**
		奖励信息  位置(根据位置找星数)_占星奖励Id(根据id找物品);
	*/
	private String rewards;
	public String getRewards(){
		return rewards;
	}
	public void setRewards(String rewards) {
		this.rewards = rewards;
		index = 15;
		result += index + "*String*" + rewards + "#";
	}

	public void setRewards(String rewards, int bs) {
		this.rewards = rewards;
	}

	/**
		系统刷新时间
	*/
	private String sysRefreshTime;
	public String getSysRefreshTime(){
		return sysRefreshTime;
	}
	public void setSysRefreshTime(String sysRefreshTime) {
		this.sysRefreshTime = sysRefreshTime;
		index = 16;
		result += index + "*String*" + sysRefreshTime + "#";
	}

	public void setSysRefreshTime(String sysRefreshTime, int bs) {
		this.sysRefreshTime = sysRefreshTime;
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
		index = 17;
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
		index = 18;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
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
		index = 19;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerHoldStar clone(){
		InstPlayerHoldStar extend=new InstPlayerHoldStar();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setHoldStarGradeId(this.holdStarGradeId);
		extend.setStep(this.step);
		extend.setStarNum(this.starNum);
		extend.setHoldStarTimes(this.holdStarTimes);
		extend.setHoldStarTime(this.holdStarTime);
		extend.setHoldStarFreeRefreshedTimes(this.holdStarFreeRefreshedTimes);
		extend.setHoldStarNoFreeRefreshedTimes(this.holdStarNoFreeRefreshedTimes);
		extend.setHoldStarRefreshedTime(this.holdStarRefreshedTime);
		extend.setRewardRefreshedTimes(this.rewardRefreshedTimes);
		extend.setRewardRefreshedTime(this.rewardRefreshedTime);
		extend.setUpStars(this.upStars);
		extend.setDownStars(this.downStars);
		extend.setRewards(this.rewards);
		extend.setSysRefreshTime(this.sysRefreshTime);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
