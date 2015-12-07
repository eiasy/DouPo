package com.hygame.dpcq.action;

import java.util.List;

import com.hygame.dpcq.db.dao.daoimp.UserImp;
import com.hygame.dpcq.db.dao.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserImp ui = new UserImp();
	private int id;
	private String username;
	private String password;
	private String name;
	private String ip;
	private String compet;
	private List<Object> list;
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCompet() {
		return compet;
	}
	public void setCompet(String compet) {
		this.compet = compet;
	}
	public String addUser() throws Exception {
		ActionContext context=ActionContext.getContext();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setIp(ip);
		int i = ui.add(user);
		//返回列表数据
		context.put("userlist",ui.findbyall());
		if(i > 0){
			context.put("mes", "帐号注册成功请给其分配权限");
		}else
			context.put("mes", "帐号注册失败请联系管理员");
		return SUCCESS;	
	}
	public String updateUser() throws Exception {
		ActionContext context=ActionContext.getContext();
		//修改帐号一般信息
		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setIp(ip);
		int i = ui.add(user);
		if(i > 0){
			context.put("mes", "帐号修改成功");
		}else
			context.put("mes", "帐号修改失败请联系管理员");
		return SUCCESS;	
	}
	public String updateUserPassword() throws Exception {
	//	System.out.println("修改密码");
		ActionContext context=ActionContext.getContext();
		//修改密码
		User user = ui.findbyid(id);
		user.setPassword(password);
		int i = ui.add(user);
		if(i > 0){
			context.put("mes", "帐号修改密码成功");
		}else
			context.put("mes", "帐号修改密码失败请联系管理员");
		return "xgmm";	
	}
	public String findUserAll() throws Exception {
		ActionContext context=ActionContext.getContext();		
		List<Object> list = ui.findbyall();
		context.put("mes", list.size());
		context.put("userlist",list);
		return SUCCESS;	
	}
	public String delUser() throws Exception {
		ActionContext context=ActionContext.getContext();
		int i = ui.del(id);
		//返回列表数据
		context.put("userlist",ui.findbyall());
		if(i > 0){
			context.put("mes", "删除帐号成功");
		}else
			context.put("mes", "删除帐号失败请联系管理员");
		return SUCCESS;	
	}
	public String findQxgl() throws Exception {
		ActionContext context=ActionContext.getContext();		
		List<Object> list = ui.findbyall();
		context.put("mes", list.size());
		context.put("userlist",list);
		return "qxgl";	
	}
	public String addQxgl() throws Exception {
		User user = ui.findbyid(id);
		user .setCompetenceid(compet);
		ui.update(user);
		return "qxgl";	
	}
}
