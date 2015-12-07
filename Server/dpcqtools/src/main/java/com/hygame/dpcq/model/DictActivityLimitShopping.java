package com.hygame.dpcq.model;

import java.io.*;

/**
	限时抢购字典表
*/
@SuppressWarnings("serial")
public class DictActivityLimitShopping implements Serializable
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
		物品 tableTypeId_tableField_value
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
		限购次数
	*/
	private int limitNum;
	public int getLimitNum(){
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
		index = 3;
		result += index + "*int*" + limitNum + "#";
	}

	public void setLimitNum(int limitNum, int bs) {
		this.limitNum = limitNum;
	}

	/**
		原价
	*/
	private int oldPrice;
	public int getOldPrice(){
		return oldPrice;
	}
	public void setOldPrice(int oldPrice) {
		this.oldPrice = oldPrice;
		index = 4;
		result += index + "*int*" + oldPrice + "#";
	}

	public void setOldPrice(int oldPrice, int bs) {
		this.oldPrice = oldPrice;
	}

	/**
		现价
	*/
	private int newPrice;
	public int getNewPrice(){
		return newPrice;
	}
	public void setNewPrice(int newPrice) {
		this.newPrice = newPrice;
		index = 5;
		result += index + "*int*" + newPrice + "#";
	}

	public void setNewPrice(int newPrice, int bs) {
		this.newPrice = newPrice;
	}

	/**
		排序
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 6;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
	}

	/**
		货币类型 1-金币 2-银币
	*/
	private int moneyType;
	public int getMoneyType(){
		return moneyType;
	}
	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
		index = 7;
		result += index + "*int*" + moneyType + "#";
	}

	public void setMoneyType(int moneyType, int bs) {
		this.moneyType = moneyType;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
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

	public String getResult(){
		return result;
	}

	public DictActivityLimitShopping clone(){
		DictActivityLimitShopping extend=new DictActivityLimitShopping();
		extend.setId(this.id);
		extend.setThing(this.thing);
		extend.setLimitNum(this.limitNum);
		extend.setOldPrice(this.oldPrice);
		extend.setNewPrice(this.newPrice);
		extend.setRank(this.rank);
		extend.setMoneyType(this.moneyType);
		extend.setVersion(this.version);
		extend.setDescription(this.description);
		return extend;
	}
}
