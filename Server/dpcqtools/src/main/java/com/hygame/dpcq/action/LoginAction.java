package com.hygame.dpcq.action;


import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.db.dao.daoimp.UserImp;
import com.hygame.dpcq.db.dao.idao.IUserDAO;
import com.hygame.dpcq.db.dao.model.User;
import com.hygame.dpcq.tools.LogUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	@Override 
	public String execute() throws Exception {

		IUserDAO iu = new UserImp();
		User u = iu.findbypas(username, password);
		ActionContext context=ActionContext.getContext();
		if(u != null){
			//组织服务器列表
			try {
				GameCoon.orgServerList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			context.getSession().put("user", u);
			
			try {
				LogUtil.info("login:username=" + username + ";password=" + password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return SUCCESS;
		}
		context.put("mes", "输入的密码与帐号不匹配");
		

		
		return INPUT;
	}
	
	public String logout()throws Exception {
		ActionContext context=ActionContext.getContext();
		context.getSession().remove("user");
		return INPUT;
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
}
