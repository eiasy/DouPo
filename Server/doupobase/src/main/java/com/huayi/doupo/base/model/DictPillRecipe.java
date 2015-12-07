package com.huayi.doupo.base.model;

import java.io.*;

/**
	丹药药方字典表
*/
@SuppressWarnings("serial")
public class DictPillRecipe implements Serializable
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
		丹药字典Id
	*/
	private int pillId;
	public int getPillId(){
		return pillId;
	}
	public void setPillId(int pillId) {
		this.pillId = pillId;
		index = 5;
		result += index + "*int*" + pillId + "#";
	}

	public void setPillId(int pillId, int bs) {
		this.pillId = pillId;
	}

	/**
		丹药材料1 丹药材料Id_数量
	*/
	private String thingOne;
	public String getThingOne(){
		return thingOne;
	}
	public void setThingOne(String thingOne) {
		this.thingOne = thingOne;
		index = 6;
		result += index + "*String*" + thingOne + "#";
	}

	public void setThingOne(String thingOne, int bs) {
		this.thingOne = thingOne;
	}

	/**
		丹药材料2 丹药材料Id_数量
	*/
	private String thingTwo;
	public String getThingTwo(){
		return thingTwo;
	}
	public void setThingTwo(String thingTwo) {
		this.thingTwo = thingTwo;
		index = 7;
		result += index + "*String*" + thingTwo + "#";
	}

	public void setThingTwo(String thingTwo, int bs) {
		this.thingTwo = thingTwo;
	}

	/**
		丹药材料3 丹药材料Id_数量
	*/
	private String thingThree;
	public String getThingThree(){
		return thingThree;
	}
	public void setThingThree(String thingThree) {
		this.thingThree = thingThree;
		index = 8;
		result += index + "*String*" + thingThree + "#";
	}

	public void setThingThree(String thingThree, int bs) {
		this.thingThree = thingThree;
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

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 10;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictPillRecipe clone(){
		DictPillRecipe extend=new DictPillRecipe();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setPillId(this.pillId);
		extend.setThingOne(this.thingOne);
		extend.setThingTwo(this.thingTwo);
		extend.setThingThree(this.thingThree);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
