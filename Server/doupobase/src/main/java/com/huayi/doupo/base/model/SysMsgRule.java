package com.huayi.doupo.base.model;

import java.io.*;

/**
	前后端消息约定规则表
*/
@SuppressWarnings("serial")
public class SysMsgRule implements Serializable
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
		注释
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
		静态文件字段名也是服务端方法名
	*/
	private String sname;
	public String getSname(){
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
		index = 3;
		result += index + "*String*" + sname + "#";
	}

	public void setSname(String sname, int bs) {
		this.sname = sname;
	}

	/**
		服务端Class路径
	*/
	private String classPath;
	public String getClassPath(){
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
		index = 4;
		result += index + "*String*" + classPath + "#";
	}

	public void setClassPath(String classPath, int bs) {
		this.classPath = classPath;
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

	public SysMsgRule clone(){
		SysMsgRule extend=new SysMsgRule();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSname(this.sname);
		extend.setClassPath(this.classPath);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
