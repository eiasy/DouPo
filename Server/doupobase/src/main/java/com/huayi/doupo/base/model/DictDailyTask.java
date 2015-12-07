package com.huayi.doupo.base.model;

import java.io.*;

/**
	每日任务字典表
*/
@SuppressWarnings("serial")
public class DictDailyTask implements Serializable
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
		任务名称
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
		任务图标Id
	*/
	private int uiId;
	public int getUiId(){
		return uiId;
	}
	public void setUiId(int uiId) {
		this.uiId = uiId;
		index = 3;
		result += index + "*int*" + uiId + "#";
	}

	public void setUiId(int uiId, int bs) {
		this.uiId = uiId;
	}

	/**
		每日几次算完成任务
	*/
	private int times;
	public int getTimes(){
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
		index = 4;
		result += index + "*int*" + times + "#";
	}

	public void setTimes(int times, int bs) {
		this.times = times;
	}

	/**
		完成任务后的奖励列表 tableType_tableField_TableValue;格式
	*/
	private String rewards;
	public String getRewards(){
		return rewards;
	}
	public void setRewards(String rewards) {
		this.rewards = rewards;
		index = 5;
		result += index + "*String*" + rewards + "#";
	}

	public void setRewards(String rewards, int bs) {
		this.rewards = rewards;
	}

	/**
		功能开放字典表Id 用于确定此任务的开放等级
	*/
	private int functionOpenId;
	public int getFunctionOpenId(){
		return functionOpenId;
	}
	public void setFunctionOpenId(int functionOpenId) {
		this.functionOpenId = functionOpenId;
		index = 6;
		result += index + "*int*" + functionOpenId + "#";
	}

	public void setFunctionOpenId(int functionOpenId, int bs) {
		this.functionOpenId = functionOpenId;
	}

	/**
		增加的积分进度
	*/
	private int plan;
	public int getPlan(){
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
		index = 7;
		result += index + "*int*" + plan + "#";
	}

	public void setPlan(int plan, int bs) {
		this.plan = plan;
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
		index = 8;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
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
		index = 9;
		result += index + "*String*" + sname + "#";
	}

	public void setSname(String sname, int bs) {
		this.sname = sname;
	}

	/**
		
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

	public DictDailyTask clone(){
		DictDailyTask extend=new DictDailyTask();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setUiId(this.uiId);
		extend.setTimes(this.times);
		extend.setRewards(this.rewards);
		extend.setFunctionOpenId(this.functionOpenId);
		extend.setPlan(this.plan);
		extend.setDescription(this.description);
		extend.setSname(this.sname);
		extend.setVersion(this.version);
		return extend;
	}
}
