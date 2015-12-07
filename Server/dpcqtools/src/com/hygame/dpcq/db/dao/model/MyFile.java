package com.hygame.dpcq.db.dao.model;
//文件显示实体类
public class MyFile {
	private String name ; //文件名
	private String date ;//上传时间
	private String exp ;//说明
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}

	
}
