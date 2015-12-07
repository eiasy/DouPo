package com.huayi.doupo.base.model;

import java.io.*;

/**
	联盟等级权限字典表
*/
@SuppressWarnings("serial")
public class DictUnionLevelPriv implements Serializable
{
	private int index;
	public String result = "";
	/**
		联盟等级
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
		经验
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 2;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		联盟职位Id_数量
	*/
	private String grade;
	public String getGrade(){
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
		index = 3;
		result += index + "*String*" + grade + "#";
	}

	public void setGrade(String grade, int bs) {
		this.grade = grade;
	}

	/**
		总数
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
		版本号
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

	public DictUnionLevelPriv clone(){
		DictUnionLevelPriv extend=new DictUnionLevelPriv();
		extend.setId(this.id);
		extend.setExp(this.exp);
		extend.setGrade(this.grade);
		extend.setNum(this.num);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
