package com.huayi.doupo.base.model;

import java.io.*;

/**
	联盟宝箱字典表
*/
@SuppressWarnings("serial")
public class DictUnionBox implements Serializable
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
		进度
	*/
	private int plan;
	public int getPlan(){
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
		index = 2;
		result += index + "*int*" + plan + "#";
	}

	public void setPlan(int plan, int bs) {
		this.plan = plan;
	}

	/**
		物品
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

	public DictUnionBox clone(){
		DictUnionBox extend=new DictUnionBox();
		extend.setId(this.id);
		extend.setPlan(this.plan);
		extend.setThings(this.things);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
