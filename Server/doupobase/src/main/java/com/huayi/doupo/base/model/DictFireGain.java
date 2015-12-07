package com.huayi.doupo.base.model;

import java.io.*;

/**
	异火抓取字典表
*/
@SuppressWarnings("serial")
public class DictFireGain implements Serializable
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
		位置
	*/
	private int position;
	public int getPosition(){
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
		index = 2;
		result += index + "*int*" + position + "#";
	}

	public void setPosition(int position, int bs) {
		this.position = position;
	}

	/**
		异火技能Id
	*/
	private int fireId;
	public int getFireId(){
		return fireId;
	}
	public void setFireId(int fireId) {
		this.fireId = fireId;
		index = 3;
		result += index + "*int*" + fireId + "#";
	}

	public void setFireId(int fireId, int bs) {
		this.fireId = fireId;
	}

	/**
		配比
	*/
	private int chance;
	public int getChance(){
		return chance;
	}
	public void setChance(int chance) {
		this.chance = chance;
		index = 4;
		result += index + "*int*" + chance + "#";
	}

	public void setChance(int chance, int bs) {
		this.chance = chance;
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

	public DictFireGain clone(){
		DictFireGain extend=new DictFireGain();
		extend.setId(this.id);
		extend.setPosition(this.position);
		extend.setFireId(this.fireId);
		extend.setChance(this.chance);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
