package com.huayi.doupo.base.model;

import java.io.*;

/**
	引导数据字典表
*/
@SuppressWarnings("serial")
public class DictGuipStep implements Serializable
{
	private int index;
	public String result = "";
	/**
		等级/关卡数
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
		类型 1-表示等级  2-表示关卡
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
		
	*/
	private int LevelOrBarrierId;
	public int getLevelOrBarrierId(){
		return LevelOrBarrierId;
	}
	public void setLevelOrBarrierId(int LevelOrBarrierId) {
		this.LevelOrBarrierId = LevelOrBarrierId;
		index = 3;
		result += index + "*int*" + LevelOrBarrierId + "#";
	}

	public void setLevelOrBarrierId(int LevelOrBarrierId, int bs) {
		this.LevelOrBarrierId = LevelOrBarrierId;
	}

	/**
		步骤 当id大于等于10000时，表示关卡
	*/
	private int step;
	public int getStep(){
		return step;
	}
	public void setStep(int step) {
		this.step = step;
		index = 4;
		result += index + "*int*" + step + "#";
	}

	public void setStep(int step, int bs) {
		this.step = step;
	}

	/**
		物品列表 tableType_tableField_TableValue;格式
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
		描述
	*/
	private String description;
	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		index = 6;
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
		index = 7;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictGuipStep clone(){
		DictGuipStep extend=new DictGuipStep();
		extend.setId(this.id);
		extend.setType(this.type);
		extend.setLevelOrBarrierId(this.LevelOrBarrierId);
		extend.setStep(this.step);
		extend.setThings(this.things);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
