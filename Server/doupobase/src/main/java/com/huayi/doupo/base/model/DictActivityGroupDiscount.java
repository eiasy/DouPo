package com.huayi.doupo.base.model;

import java.io.*;

/**
	团购折扣字典表
*/
@SuppressWarnings("serial")
public class DictActivityGroupDiscount implements Serializable
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
		开始数
	*/
	private int startNum;
	public int getStartNum(){
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
		index = 2;
		result += index + "*int*" + startNum + "#";
	}

	public void setStartNum(int startNum, int bs) {
		this.startNum = startNum;
	}

	/**
		结束数
	*/
	private int endNum;
	public int getEndNum(){
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
		index = 3;
		result += index + "*int*" + endNum + "#";
	}

	public void setEndNum(int endNum, int bs) {
		this.endNum = endNum;
	}

	/**
		折扣
	*/
	private float discount;
	public float getDiscount(){
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
		index = 4;
		result += index + "*float*" + discount + "#";
	}

	public void setDiscount(float discount, int bs) {
		this.discount = discount;
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
		index = 5;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictActivityGroupDiscount clone(){
		DictActivityGroupDiscount extend=new DictActivityGroupDiscount();
		extend.setId(this.id);
		extend.setStartNum(this.startNum);
		extend.setEndNum(this.endNum);
		extend.setDiscount(this.discount);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
