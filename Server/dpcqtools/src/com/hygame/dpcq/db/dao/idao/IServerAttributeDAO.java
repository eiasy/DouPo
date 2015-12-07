package com.hygame.dpcq.db.dao.idao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hygame.dpcq.db.conn.DBConn;


public abstract class IServerAttributeDAO implements IDAO {
/*
	public int add(Object o){
		int i = 0 ;
		try {
			ServerAttribute sa = (ServerAttribute) o;
			Connection conn = DBConn.getDbconn().getConn();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `server` VALUES (null, ? , ? , ? , ? , ? , ? , ?)");
			
			ps.setString(1,sa.getServername());
			ps.setString(2,sa.getServerip());
			ps.setInt(3,sa.getServerport());
			ps.setString(4,sa.getDbname());
			ps.setString(5,sa.getDbpassword());
			ps.setString(6,sa.getDbip());
			ps.setInt(7,sa.getDbport());
			i = ps.executeUpdate();
			conn.commit();

		}catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}
		return i;
	}*/
/*	public int update(Object o){
		int i = 0 ;
		try {
			ServerAttribute sa = new ServerAttribute();
			Connection conn = DBConn.getDbconn().getConn();
			PreparedStatement ps = conn.prepareStatement("update `server` set servername = ? , serverip = ? ," +
					" serverport = ? , dbname = ? , dbpassword = ? , dbip = ? , dbport = ? " +
					"where id = "+sa.getId());
			conn.setAutoCommit(false);
			ps.setString(1,sa.getServername());
			ps.setString(2,sa.getServerip());
			ps.setInt(3,sa.getServerport());
			ps.setString(4,sa.getDbname());
			ps.setString(5,sa.getDbpassword());
			ps.setString(6,sa.getDbip());
			ps.setInt(7,sa.getDbport());
			i = ps.executeUpdate();
			conn.commit();
			ps.close();
		}catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}
		return i;
	}*/
/*	public List<Object> findbyall(){
		List<Object> list = new ArrayList<Object>();
		try {
			
			Statement stat = DBConn.getDbconn().getConn(). createStatement ();
			ResultSet rs = stat.executeQuery("select * from `server`");
			while(rs.next()){
				ServerAttribute sa = new ServerAttribute();
				sa.setId(rs.getInt("id"));
				sa.setServername(rs.getString("servername"));
				sa.setServerip(rs.getString("serverip"));
				sa.setServerport(rs.getInt("serverport"));
				sa.setDbname(rs.getString("dbname"));
				sa.setDbpassword(rs.getString("dbpassword"));
				sa.setDbip(rs.getString("dbip"));
				sa.setDbport(rs.getInt("dbport"));
				list.add(sa);
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}*/
	public int del(int id){
		int i = 0;	

		try {
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `server`  WHERE id = "+id);
			conn.setAutoCommit(false);
			i = ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

		return i;

	}
}
