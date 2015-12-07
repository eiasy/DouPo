package com.huayi.doupo.base.model;

import java.io.*;

/**
	每日优惠字典表
*/
@SuppressWarnings("serial")
public class DictActivityVipStore implements Serializable
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
		vip等级
	*/
	private int vipLevel;
	public int getVipLevel(){
		return vipLevel;
	}
	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
		index = 2;
		result += index + "*int*" + vipLevel + "#";
	}

	public void setVipLevel(int vipLevel, int bs) {
		this.vipLevel = vipLevel;
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
		物品列表 tableTypeId_tableFieldId_value;
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 5;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
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
		index = 6;
		result += index + "*int*" + limitNum + "#";
	}

	public void setLimitNum(int limitNum, int bs) {
		this.limitNum = limitNum;
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
		index = 7;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictActivityVipStore clone(){
		DictActivityVipStore extend=new DictActivityVipStore();
		extend.setId(this.id);
		extend.setVipLevel(this.vipLevel);
		extend.setOldPrice(this.oldPrice);
		extend.setNowPrice(this.nowPrice);
		extend.setThings(this.things);
		extend.setLimitNum(this.limitNum);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
