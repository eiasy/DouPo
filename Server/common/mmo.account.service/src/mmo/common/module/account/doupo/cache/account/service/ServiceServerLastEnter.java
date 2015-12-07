package mmo.common.module.account.doupo.cache.account.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mmo.common.module.account.doupo.cache.account.bean.ServerLastEnter;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class ServiceServerLastEnter {
	private final static ServiceServerLastEnter instance = new ServiceServerLastEnter();

	public final static ServiceServerLastEnter getInstance() {
		return instance;
	}

	private final static String LOAD_LAST_SERVER   = "select accountid, servers from server_last_enter";
	private final static String ADD_LAST_SERVER    = "insert into server_last_enter (accountid, servers) values (?, ?) ";
	private final static String UPDATE_LAST_SERVER = "update server_last_enter set servers=? where accountid=?";

	public void loadAccountToCache() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ServerLastEnter lastEnter = null;
		try {
			stmt = conn.prepareStatement(LOAD_LAST_SERVER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lastEnter = new ServerLastEnter();
				lastEnter.setAccountId(rs.getInt(1));
				lastEnter.setServers(rs.getString(2));
				AccountCache.getInstance().initServerLastEnter(lastEnter);
			}
			rs.close();
		} catch (Exception e) {
			LoggerError.error("初始化LastLoginServer时抛出异常" + lastEnter, e);
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

	public void addOrUpdate(int accountId, String servers) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(UPDATE_LAST_SERVER);
			stmt.setString(1, servers);
			stmt.setInt(2, accountId);
			int result = stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			if (stmt != null) {
				stmt.close();
			}
			if (result < 1) {
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				stmt = conn.prepareStatement(ADD_LAST_SERVER);
				stmt.setInt(1, accountId);
				stmt.setString(2, servers);
				stmt.executeUpdate();
				conn.commit();
				conn.setAutoCommit(autoCommit);
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					LoggerError.error("更新登录历史异常#rollback", e1);
				}
			}
			LoggerError.error("更新登录历史异常", e);
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
