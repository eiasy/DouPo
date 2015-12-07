package com.huayi.doupo.base.model;

import java.io.*;

/**
	塔阵字典表
*/
@SuppressWarnings("serial")
public class DictPagodaFormation implements Serializable
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
		过阵奖励（TableType_FieldId_Nums）
	*/
	private String reward;
	public String getReward(){
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
		index = 3;
		result += index + "*String*" + reward + "#";
	}

	public void setReward(String reward, int bs) {
		this.reward = reward;
	}

	/**
		第一层塔层Id
	*/
	private int pagodaStorey1;
	public int getPagodaStorey1(){
		return pagodaStorey1;
	}
	public void setPagodaStorey1(int pagodaStorey1) {
		this.pagodaStorey1 = pagodaStorey1;
		index = 4;
		result += index + "*int*" + pagodaStorey1 + "#";
	}

	public void setPagodaStorey1(int pagodaStorey1, int bs) {
		this.pagodaStorey1 = pagodaStorey1;
	}

	/**
		第二层塔层Id
	*/
	private int pagodaStorey2;
	public int getPagodaStorey2(){
		return pagodaStorey2;
	}
	public void setPagodaStorey2(int pagodaStorey2) {
		this.pagodaStorey2 = pagodaStorey2;
		index = 5;
		result += index + "*int*" + pagodaStorey2 + "#";
	}

	public void setPagodaStorey2(int pagodaStorey2, int bs) {
		this.pagodaStorey2 = pagodaStorey2;
	}

	/**
		第三层塔层Id
	*/
	private int pagodaStorey3;
	public int getPagodaStorey3(){
		return pagodaStorey3;
	}
	public void setPagodaStorey3(int pagodaStorey3) {
		this.pagodaStorey3 = pagodaStorey3;
		index = 6;
		result += index + "*int*" + pagodaStorey3 + "#";
	}

	public void setPagodaStorey3(int pagodaStorey3, int bs) {
		this.pagodaStorey3 = pagodaStorey3;
	}

	/**
		第四层塔层Id
	*/
	private int pagodaStorey4;
	public int getPagodaStorey4(){
		return pagodaStorey4;
	}
	public void setPagodaStorey4(int pagodaStorey4) {
		this.pagodaStorey4 = pagodaStorey4;
		index = 7;
		result += index + "*int*" + pagodaStorey4 + "#";
	}

	public void setPagodaStorey4(int pagodaStorey4, int bs) {
		this.pagodaStorey4 = pagodaStorey4;
	}

	/**
		第五层塔层Id
	*/
	private int pagodaStorey5;
	public int getPagodaStorey5(){
		return pagodaStorey5;
	}
	public void setPagodaStorey5(int pagodaStorey5) {
		this.pagodaStorey5 = pagodaStorey5;
		index = 8;
		result += index + "*int*" + pagodaStorey5 + "#";
	}

	public void setPagodaStorey5(int pagodaStorey5, int bs) {
		this.pagodaStorey5 = pagodaStorey5;
	}

	/**
		神秘层塔层Id
	*/
	private int pagodaStorey6;
	public int getPagodaStorey6(){
		return pagodaStorey6;
	}
	public void setPagodaStorey6(int pagodaStorey6) {
		this.pagodaStorey6 = pagodaStorey6;
		index = 9;
		result += index + "*int*" + pagodaStorey6 + "#";
	}

	public void setPagodaStorey6(int pagodaStorey6, int bs) {
		this.pagodaStorey6 = pagodaStorey6;
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
		index = 10;
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
		index = 11;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictPagodaFormation clone(){
		DictPagodaFormation extend=new DictPagodaFormation();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setReward(this.reward);
		extend.setPagodaStorey1(this.pagodaStorey1);
		extend.setPagodaStorey2(this.pagodaStorey2);
		extend.setPagodaStorey3(this.pagodaStorey3);
		extend.setPagodaStorey4(this.pagodaStorey4);
		extend.setPagodaStorey5(this.pagodaStorey5);
		extend.setPagodaStorey6(this.pagodaStorey6);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
