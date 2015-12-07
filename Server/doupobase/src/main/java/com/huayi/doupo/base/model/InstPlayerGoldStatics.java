package com.huayi.doupo.base.model;

import java.io.*;

/**
	元宝统计实例表
*/
@SuppressWarnings("serial")
public class InstPlayerGoldStatics implements Serializable
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
		类型 0-减法  1-加法
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 3;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		值
	*/
	private int value;
	public int getValue(){
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		index = 4;
		result += index + "*int*" + value + "#";
	}

	public void setValue(int value, int bs) {
		this.value = value;
	}

	/**
		来源 协议号
	*/
	private int source;
	public int getSource(){
		return source;
	}
	public void setSource(int source) {
		this.source = source;
		index = 5;
		result += index + "*int*" + source + "#";
	}

	public void setSource(int source, int bs) {
		this.source = source;
	}

	/**
		操作时间
	*/
	private String operTime;
	public String getOperTime(){
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
		index = 6;
		result += index + "*String*" + operTime + "#";
	}

	public void setOperTime(String operTime, int bs) {
		this.operTime = operTime;
	}

	/**
		操作日期
	*/
	private String operDate;
	public String getOperDate(){
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
		index = 7;
		result += index + "*String*" + operDate + "#";
	}

	public void setOperDate(String operDate, int bs) {
		this.operDate = operDate;
	}

	/**
		描述
	*/
	private String descrption;
	public String getDescrption(){
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
		index = 8;
		result += index + "*String*" + descrption + "#";
	}

	public void setDescrption(String descrption, int bs) {
		this.descrption = descrption;
	}

	/**
		
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

	public String getResult(){
		return result;
	}

	public InstPlayerGoldStatics clone(){
		InstPlayerGoldStatics extend=new InstPlayerGoldStatics();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setType(this.type);
		extend.setValue(this.value);
		extend.setSource(this.source);
		extend.setOperTime(this.operTime);
		extend.setOperDate(this.operDate);
		extend.setDescrption(this.descrption);
		extend.setVersion(this.version);
		return extend;
	}
}
