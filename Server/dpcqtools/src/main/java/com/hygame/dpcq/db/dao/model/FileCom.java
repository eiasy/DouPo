package com.hygame.dpcq.db.dao.model;

public class FileCom {
	private int id ; //表id
	private String name ;//权限名称
	private String filename ;//文件名
	private String fileurl ;//文件路径
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

}
