package com.hygame.dpcq.db.dao.model;

public class MenuCom {
	private int id; 
	private	String	name; 
	private	String	url;
	private int parentNode;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getParentNode() {
		return parentNode;
	}
	public void setParentNode(int parentNode) {
		this.parentNode = parentNode;
	} 
	
}
