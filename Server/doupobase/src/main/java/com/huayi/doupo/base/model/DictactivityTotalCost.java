package com.huayi.doupo.base.model;

import java.io.*;

/**
	累计消耗
*/
@SuppressWarnings("serial")
public class DictactivityTotalCost implements Serializable
{
	private int index;
	public String result = "";
	/**
		
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
		开始时间[2015-08-01 01:20:00]
	*/
	private String timeStart;
	public String getTimeStart(){
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
		index = 2;
		result += index + "*String*" + timeStart + "#";
	}

	public void setTimeStart(String timeStart, int bs) {
		this.timeStart = timeStart;
	}

	/**
		结束时间[2015-08-01 01:20:00]
	*/
	private String timeStop;
	public String getTimeStop(){
		return timeStop;
	}
	public void setTimeStop(String timeStop) {
		this.timeStop = timeStop;
		index = 3;
		result += index + "*String*" + timeStop + "#";
	}

	public void setTimeStop(String timeStop, int bs) {
		this.timeStop = timeStop;
	}

	/**
		元宝档次
	*/
	private int yuanbao;
	public int getYuanbao(){
		return yuanbao;
	}
	public void setYuanbao(int yuanbao) {
		this.yuanbao = yuanbao;
		index = 4;
		result += index + "*int*" + yuanbao + "#";
	}

	public void setYuanbao(int yuanbao, int bs) {
		this.yuanbao = yuanbao;
	}

	/**
		消耗元宝档次及奖励：1_2_3;2_5_6
	*/
	private String awards;
	public String getAwards(){
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
		index = 5;
		result += index + "*String*" + awards + "#";
	}

	public void setAwards(String awards, int bs) {
		this.awards = awards;
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

	public DictactivityTotalCost clone(){
		DictactivityTotalCost extend=new DictactivityTotalCost();
		extend.setId(this.id);
		extend.setTimeStart(this.timeStart);
		extend.setTimeStop(this.timeStop);
		extend.setYuanbao(this.yuanbao);
		extend.setAwards(this.awards);
		extend.setVersion(this.version);
		return extend;
	}
}
