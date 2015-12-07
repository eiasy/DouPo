package com.huayi.doupo.base.model;

import java.io.*;

/**
	碎片字典表 技能/功法
*/
@SuppressWarnings("serial")
public class DictChip implements Serializable
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
		类型 1-技能  2-功法 3-法宝
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 3;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		技能/功法/法宝Id
	*/
	private int skillOrKungFuId;
	public int getSkillOrKungFuId(){
		return skillOrKungFuId;
	}
	public void setSkillOrKungFuId(int skillOrKungFuId) {
		this.skillOrKungFuId = skillOrKungFuId;
		index = 4;
		result += index + "*int*" + skillOrKungFuId + "#";
	}

	public void setSkillOrKungFuId(int skillOrKungFuId, int bs) {
		this.skillOrKungFuId = skillOrKungFuId;
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

	public DictChip clone(){
		DictChip extend=new DictChip();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setType(this.type);
		extend.setSkillOrKungFuId(this.skillOrKungFuId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
