package com.hygame.dpcq.model;

import java.io.*;

/**
	累计充值字典表
*/
@SuppressWarnings("serial")
public class DictActivityAddRecharge implements Serializable
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
		进度（rmb）十位代表周几……每天刷新；101……活动期间不刷新
	*/
	private int progress;
	public int getProgress(){
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
		index = 2;
		result += index + "*int*" + progress + "#";
	}

	public void setProgress(int progress, int bs) {
		this.progress = progress;
	}

	/**
		物品列表 tableType_TableField_value;格式
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 3;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
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
		index = 4;
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
		index = 5;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictActivityAddRecharge clone(){
		DictActivityAddRecharge extend=new DictActivityAddRecharge();
		extend.setId(this.id);
		extend.setProgress(this.progress);
		extend.setThings(this.things);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
