package com.huayi.doupo.base.model;

import java.io.*;

/**
	招募达到多少次必给紫卡字典表
*/
@SuppressWarnings("serial")
public class DictRecruitTimesGet implements Serializable
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
		此表仅用于到指定招募次数和钻石十连抽；招募类型 1-白银 2-黄金 3-钻石
	*/
	private int recruitTypeId;
	public int getRecruitTypeId(){
		return recruitTypeId;
	}
	public void setRecruitTypeId(int recruitTypeId) {
		this.recruitTypeId = recruitTypeId;
		index = 2;
		result += index + "*int*" + recruitTypeId + "#";
	}

	public void setRecruitTypeId(int recruitTypeId, int bs) {
		this.recruitTypeId = recruitTypeId;
	}

	/**
		招募次数
	*/
	private int recruitTimes;
	public int getRecruitTimes(){
		return recruitTimes;
	}
	public void setRecruitTimes(int recruitTimes) {
		this.recruitTimes = recruitTimes;
		index = 3;
		result += index + "*int*" + recruitTimes + "#";
	}

	public void setRecruitTimes(int recruitTimes, int bs) {
		this.recruitTimes = recruitTimes;
	}

	/**
		卡牌库编号
	*/
	private int areaNo;
	public int getAreaNo(){
		return areaNo;
	}
	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
		index = 4;
		result += index + "*int*" + areaNo + "#";
	}

	public void setAreaNo(int areaNo, int bs) {
		this.areaNo = areaNo;
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
		是否必得橙 0-不是 1-是  当是钻石招募得时候如果配置成1，即指的是ui界面上还剩几次必得橙的意思
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

	public DictRecruitTimesGet clone(){
		DictRecruitTimesGet extend=new DictRecruitTimesGet();
		extend.setId(this.id);
		extend.setRecruitTypeId(this.recruitTypeId);
		extend.setRecruitTimes(this.recruitTimes);
		extend.setAreaNo(this.areaNo);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
