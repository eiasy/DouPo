package com.huayi.doupo.base.model;

import java.io.*;

/**
	特殊宝箱开启规则字典表
*/
@SuppressWarnings("serial")
public class DictSpecialRule implements Serializable
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
		表类型Id
	*/
	private int minNum;
	public int getMinNum(){
		return minNum;
	}
	public void setMinNum(int minNum) {
		this.minNum = minNum;
		index = 2;
		result += index + "*int*" + minNum + "#";
	}

	public void setMinNum(int minNum, int bs) {
		this.minNum = minNum;
	}

	/**
		表字段Id
	*/
	private int maxNum;
	public int getMaxNum(){
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
		index = 3;
		result += index + "*int*" + maxNum + "#";
	}

	public void setMaxNum(int maxNum, int bs) {
		this.maxNum = maxNum;
	}

	/**
		数量
	*/
	private int tableTypeId;
	public int getTableTypeId(){
		return tableTypeId;
	}
	public void setTableTypeId(int tableTypeId) {
		this.tableTypeId = tableTypeId;
		index = 4;
		result += index + "*int*" + tableTypeId + "#";
	}

	public void setTableTypeId(int tableTypeId, int bs) {
		this.tableTypeId = tableTypeId;
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

	public DictSpecialRule clone(){
		DictSpecialRule extend=new DictSpecialRule();
		extend.setId(this.id);
		extend.setMinNum(this.minNum);
		extend.setMaxNum(this.maxNum);
		extend.setTableTypeId(this.tableTypeId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
