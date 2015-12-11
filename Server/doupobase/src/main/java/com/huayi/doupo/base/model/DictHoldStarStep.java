package com.huayi.doupo.base.model;

import java.io.*;

/**
	占星-阶段字典表
*/
@SuppressWarnings("serial")
public class DictHoldStarStep implements Serializable
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
		星座档次Id
	*/
	private int holdStarGradeId;
	public int getHoldStarGradeId(){
		return holdStarGradeId;
	}
	public void setHoldStarGradeId(int holdStarGradeId) {
		this.holdStarGradeId = holdStarGradeId;
		index = 2;
		result += index + "*int*" + holdStarGradeId + "#";
	}

	public void setHoldStarGradeId(int holdStarGradeId, int bs) {
		this.holdStarGradeId = holdStarGradeId;
	}

	/**
		阶段
	*/
	private int step;
	public int getStep(){
		return step;
	}
	public void setStep(int step) {
		this.step = step;
		index = 3;
		result += index + "*int*" + step + "#";
	}

	public void setStep(int step, int bs) {
		this.step = step;
	}

	/**
		本阶段完成后额外奖励星数
	*/
	private int rewardStarNum;
	public int getRewardStarNum(){
		return rewardStarNum;
	}
	public void setRewardStarNum(int rewardStarNum) {
		this.rewardStarNum = rewardStarNum;
		index = 4;
		result += index + "*int*" + rewardStarNum + "#";
	}

	public void setRewardStarNum(int rewardStarNum, int bs) {
		this.rewardStarNum = rewardStarNum;
	}

	/**
		本阶段命中概率
	*/
	private float hitPer;
	public float getHitPer(){
		return hitPer;
	}
	public void setHitPer(float hitPer) {
		this.hitPer = hitPer;
		index = 5;
		result += index + "*float*" + hitPer + "#";
	}

	public void setHitPer(float hitPer, int bs) {
		this.hitPer = hitPer;
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
		index = 6;
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
		index = 7;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictHoldStarStep clone(){
		DictHoldStarStep extend=new DictHoldStarStep();
		extend.setId(this.id);
		extend.setHoldStarGradeId(this.holdStarGradeId);
		extend.setStep(this.step);
		extend.setRewardStarNum(this.rewardStarNum);
		extend.setHitPer(this.hitPer);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
