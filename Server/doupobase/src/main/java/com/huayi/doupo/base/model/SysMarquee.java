package com.huayi.doupo.base.model;

import java.io.*;

/**
	平台GM跑马灯字典表
*/
@SuppressWarnings("serial")
public class SysMarquee implements Serializable
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
		开始日期 格式：[2014-10-01 00:00:00]
	*/
	private String startTime;
	public String getStartTime(){
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
		index = 2;
		result += index + "*String*" + startTime + "#";
	}

	public void setStartTime(String startTime, int bs) {
		this.startTime = startTime;
	}

	/**
		结束日期 格式：[2014-10-01 00:00:00]
	*/
	private String endTime;
	public String getEndTime(){
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
		index = 3;
		result += index + "*String*" + endTime + "#";
	}

	public void setEndTime(String endTime, int bs) {
		this.endTime = endTime;
	}

	/**
		轮询间隔 单位-秒
	*/
	private int inteval;
	public int getInteval(){
		return inteval;
	}
	public void setInteval(int inteval) {
		this.inteval = inteval;
		index = 4;
		result += index + "*int*" + inteval + "#";
	}

	public void setInteval(int inteval, int bs) {
		this.inteval = inteval;
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
		index = 5;
		result += index + "*String*" + content + "#";
	}

	public void setContent(String content, int bs) {
		this.content = content;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public SysMarquee clone(){
		SysMarquee extend=new SysMarquee();
		extend.setId(this.id);
		extend.setStartTime(this.startTime);
		extend.setEndTime(this.endTime);
		extend.setInteval(this.inteval);
		extend.setContent(this.content);
		extend.setVersion(this.version);
		return extend;
	}
}
