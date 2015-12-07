package com.huayi.doupo.base.model;

import java.io.*;

/**
	丹药材料字典表
*/
@SuppressWarnings("serial")
public class DictPillThing implements Serializable
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
		名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 2;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		小头像Id
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 3;
		result += index + "*int*" + smallUiId + "#";
	}

	public void setSmallUiId(int smallUiId, int bs) {
		this.smallUiId = smallUiId;
	}

	/**
		大头像Id
	*/
	private int bigUiId;
	public int getBigUiId(){
		return bigUiId;
	}
	public void setBigUiId(int bigUiId) {
		this.bigUiId = bigUiId;
		index = 4;
		result += index + "*int*" + bigUiId + "#";
	}

	public void setBigUiId(int bigUiId, int bs) {
		this.bigUiId = bigUiId;
	}

	/**
		产出副本多个用分号隔开
	*/
	private String outBarrier;
	public String getOutBarrier(){
		return outBarrier;
	}
	public void setOutBarrier(String outBarrier) {
		this.outBarrier = outBarrier;
		index = 5;
		result += index + "*String*" + outBarrier + "#";
	}

	public void setOutBarrier(String outBarrier, int bs) {
		this.outBarrier = outBarrier;
	}

	/**
		出售价格
	*/
	private int sellCopper;
	public int getSellCopper(){
		return sellCopper;
	}
	public void setSellCopper(int sellCopper) {
		this.sellCopper = sellCopper;
		index = 6;
		result += index + "*int*" + sellCopper + "#";
	}

	public void setSellCopper(int sellCopper, int bs) {
		this.sellCopper = sellCopper;
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

	public DictPillThing clone(){
		DictPillThing extend=new DictPillThing();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setOutBarrier(this.outBarrier);
		extend.setSellCopper(this.sellCopper);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
