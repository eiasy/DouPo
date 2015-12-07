package com.huayi.doupo.base.model;

import java.io.*;

/**
	普通宝箱物品字典表
*/
@SuppressWarnings("serial")
public class DictMineBoxThing implements Serializable
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
		类型 1-矿主金矿 2-矿主银矿   11-协助金矿  12-协助银矿
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 2;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		物品
	*/
	private String thing;
	public String getThing(){
		return thing;
	}
	public void setThing(String thing) {
		this.thing = thing;
		index = 3;
		result += index + "*String*" + thing + "#";
	}

	public void setThing(String thing, int bs) {
		this.thing = thing;
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

	public DictMineBoxThing clone(){
		DictMineBoxThing extend=new DictMineBoxThing();
		extend.setId(this.id);
		extend.setType(this.type);
		extend.setThing(this.thing);
		extend.setChance(this.chance);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
