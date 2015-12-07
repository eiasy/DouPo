package com.huayi.doupo.base.model;

import java.io.*;

/**
	异火抓取规则字典表
*/
@SuppressWarnings("serial")
public class DictFireGainRule implements Serializable
{
	private int index;
	public String result = "";
	/**
		位置
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
		铜钱
	*/
	private int copper;
	public int getCopper(){
		return copper;
	}
	public void setCopper(int copper) {
		this.copper = copper;
		index = 2;
		result += index + "*int*" + copper + "#";
	}

	public void setCopper(int copper, int bs) {
		this.copper = copper;
	}

	/**
		上升概率
	*/
	private float changeUp;
	public float getChangeUp(){
		return changeUp;
	}
	public void setChangeUp(float changeUp) {
		this.changeUp = changeUp;
		index = 3;
		result += index + "*float*" + changeUp + "#";
	}

	public void setChangeUp(float changeUp, int bs) {
		this.changeUp = changeUp;
	}

	/**
		跌落概率
	*/
	private float changeDown;
	public float getChangeDown(){
		return changeDown;
	}
	public void setChangeDown(float changeDown) {
		this.changeDown = changeDown;
		index = 4;
		result += index + "*float*" + changeDown + "#";
	}

	public void setChangeDown(float changeDown, int bs) {
		this.changeDown = changeDown;
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

	public DictFireGainRule clone(){
		DictFireGainRule extend=new DictFireGainRule();
		extend.setId(this.id);
		extend.setCopper(this.copper);
		extend.setChangeUp(this.changeUp);
		extend.setChangeDown(this.changeDown);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
