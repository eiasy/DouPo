package com.huayi.doupo.base.model;

import java.io.*;

/**
	全民福利字典表
*/
@SuppressWarnings("serial")
public class DictActivityAllPeapleWeal implements Serializable
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
		物品 tableTypeId_tableFileId_value
	*/
	private String thing;
	public String getThing(){
		return thing;
	}
	public void setThing(String thing) {
		this.thing = thing;
		index = 2;
		result += index + "*String*" + thing + "#";
	}

	public void setThing(String thing, int bs) {
		this.thing = thing;
	}

	/**
		购买人数
	*/
	private int buyerNum;
	public int getBuyerNum(){
		return buyerNum;
	}
	public void setBuyerNum(int buyerNum) {
		this.buyerNum = buyerNum;
		index = 3;
		result += index + "*int*" + buyerNum + "#";
	}

	public void setBuyerNum(int buyerNum, int bs) {
		this.buyerNum = buyerNum;
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

	public DictActivityAllPeapleWeal clone(){
		DictActivityAllPeapleWeal extend=new DictActivityAllPeapleWeal();
		extend.setId(this.id);
		extend.setThing(this.thing);
		extend.setBuyerNum(this.buyerNum);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
