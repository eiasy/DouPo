package mmo.common.module.account.doupo.cache.account.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import mmo.common.module.account.doupo.cache.account.bean.DeviceFreeze;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class ServiceDeviceFreeze {
	private final static ServiceDeviceFreeze instance = new ServiceDeviceFreeze();

	public final static ServiceDeviceFreeze getInstance() {
		return instance;
	}

	private final static String LOAD_DEVICE_FREEZE   = "select id, deviceimei, timefreeze, timeoperate from device_freeze where timefreeze>?";
	private final static String FREEZE_DEVICE_ADD    = "insert into device_freeze (deviceimei, timefreeze, timeoperate) values (?, ?, ?)";
	private final static String FREEZE_DEVICE_UPDATE = "update device_freeze set timefreeze=?, timeoperate=? where deviceimei=?";

	public void loadDeviceFreezeToCache() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		DeviceFreeze df = null;
		try {
			stmt = conn.prepareStatement(LOAD_DEVICE_FREEZE);
			stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				df = new DeviceFreeze();
				df.setDeviceImei(rs.getString(2));
				df.setTimeFreeze(rs.getTimestamp(3).getTime());
				df.setTimeOperate(rs.getTimestamp(4).getTime());
				AccountCache.getInstance().initDeviceFreeze(df);
			}
			rs.close();
		} catch (Exception e) {
			LoggerError.error("初始化DeviceFreeze时抛出异常" + df, e);
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

	public int freezeDevice(DeviceFreeze df) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(FREEZE_DEVICE_UPDATE);
			stmt.setTimestamp(1, new Timestamp(df.getTimeFreeze()));
			stmt.setTimestamp(2, new Timestamp(df.getTimeOperate()));
			stmt.setString(3, df.getDeviceImei());
			int r = stmt.executeUpdate();
			if (r < 1) {
				if (stmt != null) {
					stmt.close();
				}
				stmt = conn.prepareStatement(FREEZE_DEVICE_ADD, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, df.getDeviceImei());
				stmt.setTimestamp(2, new Timestamp(df.getTimeFreeze()));
				stmt.setTimestamp(3, new Timestamp(df.getTimeOperate()));
				stmt.executeUpdate();
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
			return 0;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("freezeDevice01", e);
		} catch (Exception e) {
			LoggerError.error("freezeDevice02", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("freezeDevice03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return -1;
	}

	public int updateFreezeDevice(DeviceFreeze df) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(FREEZE_DEVICE_UPDATE);
			stmt.setTimestamp(1, new Timestamp(df.getTimeFreeze()));
			stmt.setTimestamp(2, new Timestamp(df.getTimeOperate()));
			stmt.setString(3, df.getDeviceImei());
			stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			return 0;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("updateFreezeDevice01", e);
		} catch (Exception e) {
			LoggerError.error("updateFreezeDevice02", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("updateFreezeDevice03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return -1;
	}

}
