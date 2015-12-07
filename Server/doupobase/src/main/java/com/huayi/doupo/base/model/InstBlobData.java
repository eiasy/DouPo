package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class InstBlobData implements Serializable
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
		大数据
	*/
	private InputStream datas;
	public InputStream getDatas(){
		return datas;
	}
	public void setDatas(InputStream datas) {
		this.datas = datas;
		index = 3;
		result += index + "*InputStream*" + datas + "#";
	}

	public void setDatas(InputStream datas, int bs) {
		this.datas = datas;
	}

	/**
		描述
	*/
	private String note;
	public String getNote(){
		return note;
	}
	public void setNote(String note) {
		this.note = note;
		index = 4;
		result += index + "*String*" + note + "#";
	}

	public void setNote(String note, int bs) {
		this.note = note;
	}

	/**
		最后一次更新时间
	*/
	private String lastTime;
	public String getLastTime(){
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
		index = 5;
		result += index + "*String*" + lastTime + "#";
	}

	public void setLastTime(String lastTime, int bs) {
		this.lastTime = lastTime;
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

	public InstBlobData clone(){
		InstBlobData extend=new InstBlobData();
		extend.setId(this.id);
		extend.setType(this.type);
		extend.setDatas(this.datas);
		extend.setNote(this.note);
		extend.setLastTime(this.lastTime);
		extend.setVersion(this.version);
		return extend;
	}
}
