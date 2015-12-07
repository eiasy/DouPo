package com.hygame.dpcq.db.dao.daoimp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hygame.dpcq.db.conn.DBConn;
import com.hygame.dpcq.db.dao.idao.IUserDAO;
import com.hygame.dpcq.db.dao.model.User;

public class UserImp extends IUserDAO{
	//登录查找方法
	public User findbypas(String name , String password){
		User u = new User();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from `user` where username = '"+name+"' and  password = '"+password+"'");
			if (rs.next()){
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setCompetenceid(rs.getString("competenceid"));
				u.setIp(rs.getString("ip"));
				u.setName(rs.getString("name"));
				u.setRemark(rs.getString("remark"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, stat, rs);
		}
		
		return null;		
	}
	public User findbyid(int id){
		User u = new User();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from `user` where id = "+id);
			if (rs.next()){
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setCompetenceid(rs.getString("competenceid"));
				u.setIp(rs.getString("ip"));
				u.setName(rs.getString("name"));
				u.setRemark(rs.getString("remark"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, stat, rs);
		}
		return null;
	}
}
