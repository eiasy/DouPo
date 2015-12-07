package com.hygame.dpcq.db.dao.idao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hygame.dpcq.db.conn.DBConn;
import com.hygame.dpcq.db.dao.model.User;


public abstract class IUserDAO implements IDAO{
	public int add(Object o){
		int i = 0;
		try {
			User u = (User) o;
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `user` VALUES (null, ? , ? , ? , ? , ? , ?)");
			ps.setString(1,u.getUsername());
			ps.setString(2,u.getPassword());
			ps.setString(3,u.getName());
			ps.setString(4,u.getIp());
			ps.setString(5,u.getCompetenceid());
			ps.setString(6,u.getRemark());
			i = ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}
		return i;
	}
	public int update(Object o){
		int i = 0;
		try {
			User u = (User) o;
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn.prepareStatement("update `user` set username = ? , password = ? ," +
					" name = ? , ip = ? , competenceid = ? , remark = ? " +
					"where id = "+u.getId());
			ps.setString(1,u.getUsername());
			ps.setString(2,u.getPassword());
			ps.setString(3,u.getName());
			ps.setString(4,u.getIp());
			ps.setString(5,u.getCompetenceid());
			ps.setString(6,u.getRemark());
			i = ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}
		return i;
	}
	public List<Object> findbyall(){
		List<Object> list = new ArrayList<Object>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from `user`");
			while(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setCompetenceid(rs.getString("competenceid"));
				u.setIp(rs.getString("ip"));
				u.setName(rs.getString("name"));
				u.setRemark(rs.getString("remark"));
				list.add(u);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConn.close(conn, stat, rs);
		}

	}
	public int del(int id){
		int i = 0;	
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			i = stat.executeUpdate("DELETE FROM `user`  WHERE id = "+id);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBConn.close(conn, stat, rs);
		}

		return i;
	}
	public abstract User findbypas(String name , String password);
}
