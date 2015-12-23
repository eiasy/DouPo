package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class DictUnionWarInfo implements Serializable
{
	private int index;
	public String result = "";
	/**
		id
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
		队伍数[8,4,2]8强4强2强
	*/
	private int teamCount;
	public int getTeamCount(){
		return teamCount;
	}
	public void setTeamCount(int teamCount) {
		this.teamCount = teamCount;
		index = 2;
		result += index + "*int*" + teamCount + "#";
	}

	public void setTeamCount(int teamCount, int bs) {
		this.teamCount = teamCount;
	}

	/**
		每次战斗的衰减系数
	*/
	private float reductionPer;
	public float getReductionPer(){
		return reductionPer;
	}
	public void setReductionPer(float reductionPer) {
		this.reductionPer = reductionPer;
		index = 3;
		result += index + "*float*" + reductionPer + "#";
	}

	public void setReductionPer(float reductionPer, int bs) {
		this.reductionPer = reductionPer;
	}

	/**
		衰减的上限
	*/
	private float reductionMax;
	public float getReductionMax(){
		return reductionMax;
	}
	public void setReductionMax(float reductionMax) {
		this.reductionMax = reductionMax;
		index = 4;
		result += index + "*float*" + reductionMax + "#";
	}

	public void setReductionMax(float reductionMax, int bs) {
		this.reductionMax = reductionMax;
	}

	/**
		参与奖励
	*/
	private String rewardLose;
	public String getRewardLose(){
		return rewardLose;
	}
	public void setRewardLose(String rewardLose) {
		this.rewardLose = rewardLose;
		index = 5;
		result += index + "*String*" + rewardLose + "#";
	}

	public void setRewardLose(String rewardLose, int bs) {
		this.rewardLose = rewardLose;
	}

	/**
		胜利方奖励
	*/
	private String rewardWin;
	public String getRewardWin(){
		return rewardWin;
	}
	public void setRewardWin(String rewardWin) {
		this.rewardWin = rewardWin;
		index = 6;
		result += index + "*String*" + rewardWin + "#";
	}

	public void setRewardWin(String rewardWin, int bs) {
		this.rewardWin = rewardWin;
	}

	/**
		盟主、副盟主奖励
	*/
	private String rewardLeader;
	public String getRewardLeader(){
		return rewardLeader;
	}
	public void setRewardLeader(String rewardLeader) {
		this.rewardLeader = rewardLeader;
		index = 7;
		result += index + "*String*" + rewardLeader + "#";
	}

	public void setRewardLeader(String rewardLeader, int bs) {
		this.rewardLeader = rewardLeader;
	}

	/**
		胜利方MVP奖励
	*/
	private String rewardMvp;
	public String getRewardMvp(){
		return rewardMvp;
	}
	public void setRewardMvp(String rewardMvp) {
		this.rewardMvp = rewardMvp;
		index = 8;
		result += index + "*String*" + rewardMvp + "#";
	}

	public void setRewardMvp(String rewardMvp, int bs) {
		this.rewardMvp = rewardMvp;
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
		index = 9;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		version
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

	public String getResult(){
		return result;
	}

	public DictUnionWarInfo clone(){
		DictUnionWarInfo extend=new DictUnionWarInfo();
		extend.setId(this.id);
		extend.setTeamCount(this.teamCount);
		extend.setReductionPer(this.reductionPer);
		extend.setReductionMax(this.reductionMax);
		extend.setRewardLose(this.rewardLose);
		extend.setRewardWin(this.rewardWin);
		extend.setRewardLeader(this.rewardLeader);
		extend.setRewardMvp(this.rewardMvp);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
