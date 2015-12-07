package mmo.common.service.eventcenter.service.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mmo.common.service.eventcenter.module.Admin;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class AdminService {
	private static String getAdmin = "select id,userId,userName,userPwd,powers,ipLimit,sessionId,overtime,status from admin where ";
	private static String updateAdminSession = "update admin set sessionId=?,overtime=? where id=?";

	public AdminService() {
	}

	public Admin getAdmin(String where) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Admin admin = null;
		try {
			stmt = conn.prepareStatement(getAdmin + where + " limit 0,1");
			rs = stmt.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt(1));
				admin.setUserId(rs.getString(2));
				admin.setUserName(rs.getString(3));
				admin.setUserPwd(rs.getString(4));
				admin.setPowers(rs.getString(5));
				admin.setIpLimit(rs.getString(6));
				admin.setSessionId(rs.getString(7));
				admin.setOvertime(rs.getLong(8));
				admin.setStatus(rs.getInt(9));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
		return admin;
	}

	public void updateAdminSession(Admin admin) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(updateAdminSession);
			stmt.setString(1, admin.getSessionId());
			stmt.setLong(2, admin.getOvertime());
			stmt.setInt(3, admin.getId());
			stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("updateAdmin异常", e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
	}
}
