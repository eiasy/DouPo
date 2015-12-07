package com.huayi.doupo.base.model;

import java.io.*;

/**
	物品扩展字典表 目前是用于价格
*/
@SuppressWarnings("serial")
public class DictThingExtend implements Serializable
{
	private int index;
	public String result = "";
	/**
		物品Id
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
		扩充字段 down_up_gold 分号隔开(数量下限_上限_元宝)
	*/
	private String extend;
	public String getExtend(){
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
		index = 2;
		result += index + "*String*" + extend + "#";
	}

	public void setExtend(String extend, int bs) {
		this.extend = extend;
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
		index = 3;
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
		index = 4;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictThingExtend clone(){
		DictThingExtend extend=new DictThingExtend();
		extend.setId(this.id);
		extend.setExtend(this.extend);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
