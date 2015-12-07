package com.huayi.doupo.base.model;

import java.io.*;

/**
	联盟建设字典表
*/
@SuppressWarnings("serial")
public class DictUnionBuild implements Serializable
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
		增加的进度
	*/
	private int plan;
	public int getPlan(){
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
		index = 2;
		result += index + "*int*" + plan + "#";
	}

	public void setPlan(int plan, int bs) {
		this.plan = plan;
	}

	/**
		增加的经验值
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 3;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		增加的贡献值
	*/
	private int contribution;
	public int getContribution(){
		return contribution;
	}
	public void setContribution(int contribution) {
		this.contribution = contribution;
		index = 4;
		result += index + "*int*" + contribution + "#";
	}

	public void setContribution(int contribution, int bs) {
		this.contribution = contribution;
	}

	/**
		购买类型 1-银币 2-元宝
	*/
	private int buyType;
	public int getBuyType(){
		return buyType;
	}
	public void setBuyType(int buyType) {
		this.buyType = buyType;
		index = 5;
		result += index + "*int*" + buyType + "#";
	}

	public void setBuyType(int buyType, int bs) {
		this.buyType = buyType;
	}

	/**
		购买价格
	*/
	private int buyValue;
	public int getBuyValue(){
		return buyValue;
	}
	public void setBuyValue(int buyValue) {
		this.buyValue = buyValue;
		index = 6;
		result += index + "*int*" + buyValue + "#";
	}

	public void setBuyValue(int buyValue, int bs) {
		this.buyValue = buyValue;
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
		index = 7;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		版本号
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictUnionBuild clone(){
		DictUnionBuild extend=new DictUnionBuild();
		extend.setId(this.id);
		extend.setPlan(this.plan);
		extend.setExp(this.exp);
		extend.setContribution(this.contribution);
		extend.setBuyType(this.buyType);
		extend.setBuyValue(this.buyValue);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
