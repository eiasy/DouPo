package com.huayi.doupo.base.model;

import java.io.*;

/**
	副本关卡掉落类型字典表
*/
@SuppressWarnings("serial")
public class DictBarrierDropType implements Serializable
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
	private String typeName;
	public String getTypeName(){
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
		index = 2;
		result += index + "*String*" + typeName + "#";
	}

	public void setTypeName(String typeName, int bs) {
		this.typeName = typeName;
	}

	/**
		类型 用于掉落物品的分类
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 3;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		值
	*/
	private int value;
	public int getValue(){
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		index = 4;
		result += index + "*int*" + value + "#";
	}

	public void setValue(int value, int bs) {
		this.value = value;
	}

	/**
		概率
	*/
	private float pr;
	public float getPr(){
		return pr;
	}
	public void setPr(float pr) {
		this.pr = pr;
		index = 5;
		result += index + "*float*" + pr + "#";
	}

	public void setPr(float pr, int bs) {
		this.pr = pr;
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

	public DictBarrierDropType clone(){
		DictBarrierDropType extend=new DictBarrierDropType();
		extend.setId(this.id);
		extend.setTypeName(this.typeName);
		extend.setType(this.type);
		extend.setValue(this.value);
		extend.setPr(this.pr);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
