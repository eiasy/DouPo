package com.hygame.dpcq.model;

import java.io.*;

/**
	每日优惠字典表
*/
@SuppressWarnings("serial")
public class DictActivityDailyDeals implements Serializable
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
		天 填写31天的数据
	*/
	private int day;
	public int getDay(){
		return day;
	}
	public void setDay(int day) {
		this.day = day;
		index = 2;
		result += index + "*int*" + day + "#";
	}

	public void setDay(int day, int bs) {
		this.day = day;
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
		index = 3;
		result += index + "*int*" + oldPrice + "#";
	}

	public void setOldPrice(int oldPrice, int bs) {
		this.oldPrice = oldPrice;
	}

	/**
		现价
	*/
	private int nowPrice;
	public int getNowPrice(){
		return nowPrice;
	}
	public void setNowPrice(int nowPrice) {
		this.nowPrice = nowPrice;
		index = 4;
		result += index + "*int*" + nowPrice + "#";
	}

	public void setNowPrice(int nowPrice, int bs) {
		this.nowPrice = nowPrice;
	}

	/**
		物品列表名称
	*/
	private String thingsName;
	public String getThingsName(){
		return thingsName;
	}
	public void setThingsName(String thingsName) {
		this.thingsName = thingsName;
		index = 5;
		result += index + "*String*" + thingsName + "#";
	}

	public void setThingsName(String thingsName, int bs) {
		this.thingsName = thingsName;
	}

	/**
		物品列表 tableTypeId_tableFieldId_value;
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 6;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
	}

	/**
		物品价格
	*/
	private String price;
	public String getPrice(){
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
		index = 7;
		result += index + "*String*" + price + "#";
	}

	public void setPrice(String price, int bs) {
		this.price = price;
	}

	/**
		
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

	public DictActivityDailyDeals clone(){
		DictActivityDailyDeals extend=new DictActivityDailyDeals();
		extend.setId(this.id);
		extend.setDay(this.day);
		extend.setOldPrice(this.oldPrice);
		extend.setNowPrice(this.nowPrice);
		extend.setThingsName(this.thingsName);
		extend.setThings(this.things);
		extend.setPrice(this.price);
		extend.setVersion(this.version);
		extend.setDescription(this.description);
		return extend;
	}
}
