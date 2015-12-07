package com.huayi.doupo.base.model;

import java.io.*;

/**
	定时任务系统表
*/
@SuppressWarnings("serial")
public class SysQuartzJob implements Serializable
{
	private int index;
	public String result = "";
	/**
		ID
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
		工作名称
	*/
	private String jobName;
	public String getJobName(){
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
		index = 3;
		result += index + "*String*" + jobName + "#";
	}

	public void setJobName(String jobName, int bs) {
		this.jobName = jobName;
	}

	/**
		触发器名称
	*/
	private String triggerName;
	public String getTriggerName(){
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
		index = 4;
		result += index + "*String*" + triggerName + "#";
	}

	public void setTriggerName(String triggerName, int bs) {
		this.triggerName = triggerName;
	}

	/**
		调度表达式
	*/
	private String cronExpression;
	public String getCronExpression(){
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
		index = 5;
		result += index + "*String*" + cronExpression + "#";
	}

	public void setCronExpression(String cronExpression, int bs) {
		this.cronExpression = cronExpression;
	}

	/**
		开始时间
	*/
	private String startTime;
	public String getStartTime(){
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
		index = 6;
		result += index + "*String*" + startTime + "#";
	}

	public void setStartTime(String startTime, int bs) {
		this.startTime = startTime;
	}

	/**
		结束时间
	*/
	private String endTime;
	public String getEndTime(){
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
		index = 7;
		result += index + "*String*" + endTime + "#";
	}

	public void setEndTime(String endTime, int bs) {
		this.endTime = endTime;
	}

	/**
		工作执行的类路径
	*/
	private String jobClassPath;
	public String getJobClassPath(){
		return jobClassPath;
	}
	public void setJobClassPath(String jobClassPath) {
		this.jobClassPath = jobClassPath;
		index = 8;
		result += index + "*String*" + jobClassPath + "#";
	}

	public void setJobClassPath(String jobClassPath, int bs) {
		this.jobClassPath = jobClassPath;
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

	public SysQuartzJob clone(){
		SysQuartzJob extend=new SysQuartzJob();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setJobName(this.jobName);
		extend.setTriggerName(this.triggerName);
		extend.setCronExpression(this.cronExpression);
		extend.setStartTime(this.startTime);
		extend.setEndTime(this.endTime);
		extend.setJobClassPath(this.jobClassPath);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
