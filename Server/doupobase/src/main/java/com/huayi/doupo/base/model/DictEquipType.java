package com.huayi.doupo.base.model;

import java.io.*;

/**
	装备类型字典表
*/
@SuppressWarnings("serial")
public class DictEquipType implements Serializable
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
		名字
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
		战斗属性
	*/
	private String fightProps;
	public String getFightProps(){
		return fightProps;
	}
	public void setFightProps(String fightProps) {
		this.fightProps = fightProps;
		index = 4;
		result += index + "*String*" + fightProps + "#";
	}

	public void setFightProps(String fightProps, int bs) {
		this.fightProps = fightProps;
	}

	/**
		
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

	public DictEquipType clone(){
		DictEquipType extend=new DictEquipType();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSname(this.sname);
		extend.setFightProps(this.fightProps);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
