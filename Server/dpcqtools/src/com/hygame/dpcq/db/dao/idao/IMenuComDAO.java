package com.hygame.dpcq.db.dao.idao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hygame.dpcq.db.conn.DBConn;
import com.hygame.dpcq.db.dao.model.MenuCom;

public abstract class IMenuComDAO implements IDAO{
	public int add(Object o){
		int i = 0;
		try {
			MenuCom mc = (MenuCom) o;
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `menucom` VALUES (null, ?, ?)");
			ps.setString(1, mc.getName());
			ps.setString(2, mc.getUrl());
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
			MenuCom mc = (MenuCom) o;
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn.prepareStatement("update `menucom` set name = ?, url = ? where id = "+mc.getId());
			ps.setString(1, mc.getName());
			ps.setString(2, mc.getUrl());
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
			rs = stat.executeQuery("select * from `menucom`");
			while(rs.next()){
				MenuCom mc = new MenuCom();
				mc.setId(rs.getInt("id"));
				mc.setName(rs.getString("name"));
				mc.setUrl(rs.getString("url"));
				list.add(mc);
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
			i = stat.executeUpdate("DELETE FROM `menucom`  WHERE id = "+id);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBConn.close(conn, stat, rs);
		}

		return i;
	}
}
