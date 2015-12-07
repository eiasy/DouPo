package com.hygame.dpcq.model;

import java.io.*;

/**
	幸运轮盘字典表
*/
@SuppressWarnings("serial")
public class DictActivityLuck implements Serializable
{
	private int index;
	public String result = "";
	/**
		
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
		标签：普通，限定，1-1
	*/
	private String mark;
	public String getMark(){
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
		index = 2;
		result += index + "*String*" + mark + "#";
	}

	public void setMark(String mark, int bs) {
		this.mark = mark;
	}

	/**
		道具：类型_编号_数量_权重1_权重2
	*/
	private String data;
	public String getData(){
		return data;
	}
	public void setData(String data) {
		this.data = data;
		index = 3;
		result += index + "*String*" + data + "#";
	}

	public void setData(String data, int bs) {
		this.data = data;
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

	public DictActivityLuck clone(){
		DictActivityLuck extend=new DictActivityLuck();
		extend.setId(this.id);
		extend.setMark(this.mark);
		extend.setData(this.data);
		extend.setVersion(this.version);
		return extend;
	}
}
