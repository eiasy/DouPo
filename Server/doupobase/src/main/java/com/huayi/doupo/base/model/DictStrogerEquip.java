package com.huayi.doupo.base.model;

import java.io.*;

/**
	强力装备字典表
*/
@SuppressWarnings("serial")
public class DictStrogerEquip implements Serializable
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
		物品（套装）列表,用分号分开
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
		购买需要元宝数
	*/
	private int needGold;
	public int getNeedGold(){
		return needGold;
	}
	public void setNeedGold(int needGold) {
		this.needGold = needGold;
		index = 3;
		result += index + "*int*" + needGold + "#";
	}

	public void setNeedGold(int needGold, int bs) {
		this.needGold = needGold;
	}

	/**
		赠送物品
	*/
	private String donateThings;
	public String getDonateThings(){
		return donateThings;
	}
	public void setDonateThings(String donateThings) {
		this.donateThings = donateThings;
		index = 4;
		result += index + "*String*" + donateThings + "#";
	}

	public void setDonateThings(String donateThings, int bs) {
		this.donateThings = donateThings;
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

	public DictStrogerEquip clone(){
		DictStrogerEquip extend=new DictStrogerEquip();
		extend.setId(this.id);
		extend.setThings(this.things);
		extend.setNeedGold(this.needGold);
		extend.setDonateThings(this.donateThings);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
