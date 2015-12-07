package com.huayi.doupo.base.model;

import java.io.*;

/**
	限时抢购字典表
*/
@SuppressWarnings("serial")
public class DictActivityFlashSale implements Serializable
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
		奖励 物品列表 tableType_tableField_TableValue;格式
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 2;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
	}

	/**
		购买价格
	*/
	private int buyGold;
	public int getBuyGold(){
		return buyGold;
	}
	public void setBuyGold(int buyGold) {
		this.buyGold = buyGold;
		index = 3;
		result += index + "*int*" + buyGold + "#";
	}

	public void setBuyGold(int buyGold, int bs) {
		this.buyGold = buyGold;
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

	public DictActivityFlashSale clone(){
		DictActivityFlashSale extend=new DictActivityFlashSale();
		extend.setId(this.id);
		extend.setThings(this.things);
		extend.setBuyGold(this.buyGold);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
