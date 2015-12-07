package mmo.common.module.account.doupo.cache.account.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mmo.common.module.account.doupo.cache.account.bean.ServerRoleCount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class ServiceServerRoleCount {
	private final static ServiceServerRoleCount instance = new ServiceServerRoleCount();

	public final static ServiceServerRoleCount getInstance() {
		return instance;
	}

	private final static String LOAD_ROLE_COUNT          = "select accountid, rolecount from server_role_count";
	private final static String ADD_SERVER_ROLE_COUNT    = "insert into server_role_count (accountid, rolecount) values (?, ?) ";
	private final static String UPDATE_SERVER_ROLE_COUNT = "update server_role_count set rolecount=? where accountid=?";

	public void loadAccountToCache() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ServerRoleCount roleCount = null;
		try {
			stmt = conn.prepareStatement(LOAD_ROLE_COUNT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				roleCount = new ServerRoleCount();
				roleCount.setAccountId(rs.getInt(1));
				roleCount.setRoleCount(rs.getString(2));
				AccountCache.getInstance().initServerRoleCount(roleCount);
			}
			rs.close();
		} catch (Exception e) {
			LoggerError.error("初始化ServerRoleCount时抛出异常" + roleCount, e);
		} finally {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				LoggerError.error("关闭 PreparedStatement 时抛出异常", e);
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
	}

	public void addOrUpdate(int accountId, String serverRoleCount) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(UPDATE_SERVER_ROLE_COUNT);
			stmt.setString(1, serverRoleCount);
			stmt.setInt(2, accountId);
			int result = stmt.executeUpdate();
			if (stmt != null) {
				stmt.close();
			}
			if (result < 1) {
				stmt = conn.prepareStatement(ADD_SERVER_ROLE_COUNT);
				stmt.setInt(1, accountId);
				stmt.setString(2, serverRoleCount);
				stmt.executeUpdate();
			}
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
	}
}
