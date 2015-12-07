package com.hygame.dpcq.db.dao.model;

public class User {
	private int id;  //表id
	private	String	username;// 账户名
	private	String	password; //密码
	private	String	name; //真实姓名
	private	String	ip; //ip
	private	String	competenceid;// 权限id
	private	String	remark;//备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCompetenceid() {
		return competenceid;
	}
	public void setCompetenceid(String competenceid) {
		this.competenceid = competenceid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	} 

}
