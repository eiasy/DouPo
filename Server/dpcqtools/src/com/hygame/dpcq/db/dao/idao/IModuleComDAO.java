package com.hygame.dpcq.db.dao.idao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hygame.dpcq.db.conn.DBConn;
import com.hygame.dpcq.db.dao.model.ModuleCom;

public abstract class IModuleComDAO implements IDAO{
	public int add(Object o){
		int i = 0;
		try {
			ModuleCom mc = (ModuleCom) o;
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `modulecom` VALUES (null, ?, ?)");
			ps.setString(1, mc.getName());
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
			ModuleCom mc = (ModuleCom) o;
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn.prepareStatement("update `modulecom` set name = ? where id = "+mc.getId());
			ps.setString(1, mc.getName());
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
			rs = stat.executeQuery("select * from `modulecom`");
			while(rs.next()){
				ModuleCom mc = new ModuleCom();
				mc.setId(rs.getInt("id"));
				mc.setName(rs.getString("name"));
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
			i = stat.executeUpdate("DELETE FROM `modulecom`  WHERE id = "+id);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBConn.close(conn, stat, rs);
		}
		return i;
	}
}
