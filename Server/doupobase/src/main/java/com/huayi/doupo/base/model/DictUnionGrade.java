package com.huayi.doupo.base.model;

import java.io.*;

/**
	联盟职位字典表
*/
@SuppressWarnings("serial")
public class DictUnionGrade implements Serializable
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
		联盟类型
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
		职位名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 3;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		职位编号
	*/
	private int gradeId;
	public int getGradeId(){
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
		index = 4;
		result += index + "*int*" + gradeId + "#";
	}

	public void setGradeId(int gradeId, int bs) {
		this.gradeId = gradeId;
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

	public DictUnionGrade clone(){
		DictUnionGrade extend=new DictUnionGrade();
		extend.setId(this.id);
		extend.setType(this.type);
		extend.setName(this.name);
		extend.setGradeId(this.gradeId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
