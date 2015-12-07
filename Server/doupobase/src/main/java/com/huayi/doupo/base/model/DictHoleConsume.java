package com.huayi.doupo.base.model;

import java.io.*;

/**
	开槽消耗字典表
*/
@SuppressWarnings("serial")
public class DictHoleConsume implements Serializable
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
		品质Id
	*/
	private int qualityId;
	public int getQualityId(){
		return qualityId;
	}
	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
		index = 2;
		result += index + "*int*" + qualityId + "#";
	}

	public void setQualityId(int qualityId, int bs) {
		this.qualityId = qualityId;
	}

	/**
		次数
	*/
	private int times;
	public int getTimes(){
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
		index = 3;
		result += index + "*int*" + times + "#";
	}

	public void setTimes(int times, int bs) {
		this.times = times;
	}

	/**
		数量 消耗多少个开孔器
	*/
	private int num;
	public int getNum(){
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		index = 4;
		result += index + "*int*" + num + "#";
	}

	public void setNum(int num, int bs) {
		this.num = num;
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

	public DictHoleConsume clone(){
		DictHoleConsume extend=new DictHoleConsume();
		extend.setId(this.id);
		extend.setQualityId(this.qualityId);
		extend.setTimes(this.times);
		extend.setNum(this.num);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
