package com.huayi.doupo.base.model;

import java.io.*;

/**
	系数字典表
*/
@SuppressWarnings("serial")
public class DictCoefficient implements Serializable
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
		血量系数
	*/
	private float bloodPer;
	public float getBloodPer(){
		return bloodPer;
	}
	public void setBloodPer(float bloodPer) {
		this.bloodPer = bloodPer;
		index = 2;
		result += index + "*float*" + bloodPer + "#";
	}

	public void setBloodPer(float bloodPer, int bs) {
		this.bloodPer = bloodPer;
	}

	/**
		物攻系数
	*/
	private float wAttackPer;
	public float getWAttackPer(){
		return wAttackPer;
	}
	public void setWAttackPer(float wAttackPer) {
		this.wAttackPer = wAttackPer;
		index = 3;
		result += index + "*float*" + wAttackPer + "#";
	}

	public void setWAttackPer(float wAttackPer, int bs) {
		this.wAttackPer = wAttackPer;
	}

	/**
		法攻系数
	*/
	private float fAttackPer;
	public float getFAttackPer(){
		return fAttackPer;
	}
	public void setFAttackPer(float fAttackPer) {
		this.fAttackPer = fAttackPer;
		index = 4;
		result += index + "*float*" + fAttackPer + "#";
	}

	public void setFAttackPer(float fAttackPer, int bs) {
		this.fAttackPer = fAttackPer;
	}

	/**
		物防系数
	*/
	private float wDefensePer;
	public float getWDefensePer(){
		return wDefensePer;
	}
	public void setWDefensePer(float wDefensePer) {
		this.wDefensePer = wDefensePer;
		index = 5;
		result += index + "*float*" + wDefensePer + "#";
	}

	public void setWDefensePer(float wDefensePer, int bs) {
		this.wDefensePer = wDefensePer;
	}

	/**
		法防系数
	*/
	private float fDefensePer;
	public float getFDefensePer(){
		return fDefensePer;
	}
	public void setFDefensePer(float fDefensePer) {
		this.fDefensePer = fDefensePer;
		index = 6;
		result += index + "*float*" + fDefensePer + "#";
	}

	public void setFDefensePer(float fDefensePer, int bs) {
		this.fDefensePer = fDefensePer;
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

	public DictCoefficient clone(){
		DictCoefficient extend=new DictCoefficient();
		extend.setId(this.id);
		extend.setBloodPer(this.bloodPer);
		extend.setWAttackPer(this.wAttackPer);
		extend.setFAttackPer(this.fAttackPer);
		extend.setWDefensePer(this.wDefensePer);
		extend.setFDefensePer(this.fDefensePer);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
