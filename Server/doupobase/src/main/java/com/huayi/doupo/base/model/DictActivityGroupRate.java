package com.huayi.doupo.base.model;

import java.io.*;

/**
	团购返利字典表
*/
@SuppressWarnings("serial")
public class DictActivityGroupRate implements Serializable
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
		排名
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 2;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
	}

	/**
		返利
	*/
	private float rebate;
	public float getRebate(){
		return rebate;
	}
	public void setRebate(float rebate) {
		this.rebate = rebate;
		index = 3;
		result += index + "*float*" + rebate + "#";
	}

	public void setRebate(float rebate, int bs) {
		this.rebate = rebate;
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

	public DictActivityGroupRate clone(){
		DictActivityGroupRate extend=new DictActivityGroupRate();
		extend.setId(this.id);
		extend.setRank(this.rank);
		extend.setRebate(this.rebate);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
