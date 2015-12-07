package com.huayi.doupo.base.model;

import java.io.*;

/**
	斗魂字典表
*/
@SuppressWarnings("serial")
public class DictFightSoulPositionCond implements Serializable
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
		位置
	*/
	private int position;
	public int getPosition(){
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
		index = 2;
		result += index + "*int*" + position + "#";
	}

	public void setPosition(int position, int bs) {
		this.position = position;
	}

	/**
		开放的称号Id
	*/
	private int openTitleId;
	public int getOpenTitleId(){
		return openTitleId;
	}
	public void setOpenTitleId(int openTitleId) {
		this.openTitleId = openTitleId;
		index = 3;
		result += index + "*int*" + openTitleId + "#";
	}

	public void setOpenTitleId(int openTitleId, int bs) {
		this.openTitleId = openTitleId;
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

	public DictFightSoulPositionCond clone(){
		DictFightSoulPositionCond extend=new DictFightSoulPositionCond();
		extend.setId(this.id);
		extend.setPosition(this.position);
		extend.setOpenTitleId(this.openTitleId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
