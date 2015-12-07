package com.hygame.dpcq.db.dao.idao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hygame.dpcq.db.conn.DBConn;
import com.hygame.dpcq.db.dao.model.MenuCom;
import com.hygame.dpcq.db.dao.model.User;
import com.hygame.dpcq.db.dao.model.UserCom;

public abstract class IUserComDAO implements IDAO {
	public int add(Object o) {
		int i = 0;
		try {
			UserCom uc = (UserCom) o;
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO `user_com` VALUES (null, ?, ? ,?)");
			ps.setInt(1, uc.getUserid());
			ps.setInt(2, uc.getCompetenceid());
			ps.setInt(3, uc.getType());
			i = ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}
		return i;
	}

	public int update(Object o) {
		int i = 0;
		try {
			UserCom uc = (UserCom) o;
			Connection conn = DBConn.getConn();
			PreparedStatement ps = conn
					.prepareStatement("update `user_com` set userid = ? , competenceid = ? , type = ? where id = "
							+ uc.getId());
			ps.setInt(1, uc.getUserid());
			ps.setInt(2, uc.getCompetenceid());
			ps.setInt(3, uc.getType());
			i = ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}
		return i;
	}

	public List<Object> findbyall() {
		List<Object> list = new ArrayList<Object>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from `user_com`");
			while (rs.next()) {
				UserCom uc = new UserCom();
				uc.setId(rs.getInt("id"));
				uc.setUserid(rs.getInt("userid"));
				uc.setCompetenceid(rs.getInt("competenceid"));
				uc.setType(rs.getInt("type"));
				list.add(uc);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConn.close(conn, stat, rs);
		}

	}

	public int del(int id) {
		int i = 0;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			i = stat.executeUpdate("DELETE FROM `user_com`  WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DBConn.close(conn, stat, rs);
		}

		return i;
	}

	public Map<Integer, List<MenuCom>> getuserCom(User u) {
		Map<Integer, List<MenuCom>> map = new LinkedHashMap<Integer, List<MenuCom>>();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			String[] com = u.getCompetenceid().split(",");
			if (com.length > 0) {
				for (int i = 0; i < com.length; i++) {
					MenuCom mc = new MenuCom();
					rs = stat.executeQuery("SELECT * FROM menucom where  id = " + com[i]);
					while (rs.next()) {
						mc.setId(rs.getInt("id"));
						mc.setName(rs.getString("name"));
						mc.setUrl(rs.getString("url"));
						mc.setParentNode(rs.getInt("parent_node"));
						//组装的挺漂亮
						if (mc.getId() < 10) {
							List<MenuCom> list = new ArrayList<MenuCom>();
							list.add(mc);
							map.put(mc.getId(), list);
						} else {
							map.get(mc.getParentNode()).add(mc);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConn.close(conn, stat, rs);
		}
		return map;
	}
}
