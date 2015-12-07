package com.hygame.dpcq.db.dao.model;

public class UserCom {

	private	int id ;//表id
	private	int userid ;//user表id
	private	int competenceid ;//权限id
	private	int type ;//所属权限属性1，菜单 ，2 ，功能模块 ，3，文件
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCompetenceid() {
		return competenceid;
	}
	public void setCompetenceid(int competenceid) {
		this.competenceid = competenceid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
