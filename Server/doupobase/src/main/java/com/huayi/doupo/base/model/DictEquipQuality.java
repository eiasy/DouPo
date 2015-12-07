package com.huayi.doupo.base.model;

import java.io.*;

/**
	装备品质字典表
*/
@SuppressWarnings("serial")
public class DictEquipQuality implements Serializable
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
		名字
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
		拥有宝石孔数
	*/
	private int holeNum;
	public int getHoleNum(){
		return holeNum;
	}
	public void setHoleNum(int holeNum) {
		this.holeNum = holeNum;
		index = 3;
		result += index + "*int*" + holeNum + "#";
	}

	public void setHoleNum(int holeNum, int bs) {
		this.holeNum = holeNum;
	}

	/**
		所需碎片数量
	*/
	private int thingNum;
	public int getThingNum(){
		return thingNum;
	}
	public void setThingNum(int thingNum) {
		this.thingNum = thingNum;
		index = 4;
		result += index + "*int*" + thingNum + "#";
	}

	public void setThingNum(int thingNum, int bs) {
		this.thingNum = thingNum;
	}

	/**
		分解后能得到的洗练石数量(此字段废除) 
	*/
	private int washRockNum;
	public int getWashRockNum(){
		return washRockNum;
	}
	public void setWashRockNum(int washRockNum) {
		this.washRockNum = washRockNum;
		index = 5;
		result += index + "*int*" + washRockNum + "#";
	}

	public void setWashRockNum(int washRockNum, int bs) {
		this.washRockNum = washRockNum;
	}

	/**
		分解所需铜币
	*/
	private int resolveNeedCopper;
	public int getResolveNeedCopper(){
		return resolveNeedCopper;
	}
	public void setResolveNeedCopper(int resolveNeedCopper) {
		this.resolveNeedCopper = resolveNeedCopper;
		index = 6;
		result += index + "*int*" + resolveNeedCopper + "#";
	}

	public void setResolveNeedCopper(int resolveNeedCopper, int bs) {
		this.resolveNeedCopper = resolveNeedCopper;
	}

	/**
		
	*/
	private String sname;
	public String getSname(){
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
		index = 7;
		result += index + "*String*" + sname + "#";
	}

	public void setSname(String sname, int bs) {
		this.sname = sname;
	}

	/**
		
	*/
	private String description;
	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		index = 8;
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
		index = 9;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictEquipQuality clone(){
		DictEquipQuality extend=new DictEquipQuality();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setHoleNum(this.holeNum);
		extend.setThingNum(this.thingNum);
		extend.setWashRockNum(this.washRockNum);
		extend.setResolveNeedCopper(this.resolveNeedCopper);
		extend.setSname(this.sname);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
