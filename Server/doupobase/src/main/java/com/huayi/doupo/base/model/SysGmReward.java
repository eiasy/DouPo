package com.huayi.doupo.base.model;

import java.io.*;

/**
	GM全服发奖系统表
*/
@SuppressWarnings("serial")
public class SysGmReward implements Serializable
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
		内容
	*/
	private String content;
	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		index = 2;
		result += index + "*String*" + content + "#";
	}

	public void setContent(String content, int bs) {
		this.content = content;
	}

	/**
		参数列表
	*/
	private String paramList;
	public String getParamList(){
		return paramList;
	}
	public void setParamList(String paramList) {
		this.paramList = paramList;
		index = 3;
		result += index + "*String*" + paramList + "#";
	}

	public void setParamList(String paramList, int bs) {
		this.paramList = paramList;
	}

	/**
		发放时间
	*/
	private String rewardTime;
	public String getRewardTime(){
		return rewardTime;
	}
	public void setRewardTime(String rewardTime) {
		this.rewardTime = rewardTime;
		index = 4;
		result += index + "*String*" + rewardTime + "#";
	}

	public void setRewardTime(String rewardTime, int bs) {
		this.rewardTime = rewardTime;
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
		index = 5;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public SysGmReward clone(){
		SysGmReward extend=new SysGmReward();
		extend.setId(this.id);
		extend.setContent(this.content);
		extend.setParamList(this.paramList);
		extend.setRewardTime(this.rewardTime);
		extend.setVersion(this.version);
		return extend;
	}
}
