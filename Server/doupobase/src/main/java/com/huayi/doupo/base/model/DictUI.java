package com.huayi.doupo.base.model;

import java.io.*;

/**
	前端资源字典表
*/
@SuppressWarnings("serial")
public class DictUI implements Serializable
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
		文件名
	*/
	private String fileName;
	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
		index = 3;
		result += index + "*String*" + fileName + "#";
	}

	public void setFileName(String fileName, int bs) {
		this.fileName = fileName;
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
		index = 4;
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
		index = 5;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictUI clone(){
		DictUI extend=new DictUI();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setFileName(this.fileName);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
