package com.hygame.dpcq.db.dao.idao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hygame.dpcq.db.conn.DBConn;
import com.hygame.dpcq.db.dao.model.FileCom;

public abstract class IFileComDAO implements IDAO{

	public int add(Object o){
		int i = 0;
		try {
			FileCom cf = (FileCom) o;
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `filecom` VALUES (null, ?, ?,?)");
			ps.setString(1, cf.getName());
			ps.setString(2, cf.getFilename());
			ps.setString(3, cf.getFileurl());
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
		FileCom cf = (FileCom) o;
		Connection conn = DBConn.getConn();
		PreparedStatement ps = conn.prepareStatement("update `filecom` set name = ?, filename = ?, fileurl = ? where id = "+cf.getId());
		ps.setString(1, cf.getName());
		ps.setString(2, cf.getFilename());
		ps.setString(3, cf.getFileurl());
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
			rs = stat.executeQuery("select * from `filecom`");
			while(rs.next()){
				FileCom cf = new FileCom();
				cf.setId(rs.getInt("id"));
				cf.setName(rs.getString("name"));
				cf.setFilename(rs.getString("filename"));
				cf.setFileurl(rs.getString("fileurl"));
				list.add(cf);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, stat, rs);
		}
		return null;

	}
	public int del(int id){
		int i = 0;	
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			i = stat.executeUpdate("DELETE FROM `filecom`  WHERE id = "+id);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBConn.close(conn, stat, rs);
		}
		
		return i;
	}
	/*public static void main (String gare[]){
		IFileComDAO ifd = new IFileComDAO();
		for(int i = 1 ;i <= 10 ; i++){
			FileCom cf = new FileCom();
			cf.setId(i);
			cf.setName("name" + i );
			cf.setFilename("filename" + i );
			cf.setFileurl("fileurl" + i );
			ifd.del(cf.getId());
		}
		List<Object> list = ifd.findbyall();
		System.out.println(list.size());
		for(int i = 0 ; i < list.size() ;i++){
			FileCom cf = (FileCom)list.get(i);
			System.out.println("id"+i+" = "+cf.getId());
			System.out.println("name"+i+" = "+cf.getName());
			System.out.println("filename"+i+" = "+cf.getFilename());
			System.out.println("name"+i+" = "+cf.getFileurl());
		}
	}*/
}
