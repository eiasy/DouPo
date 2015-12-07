package mmo.common.module.service.charge.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class OrderformService extends TableManager {
	private final static OrderformService instance = new OrderformService();

	public final static OrderformService getInstance() {
		return instance;
	}

	private OrderformService() {

	}

	public final boolean addChargeOrderform(ChargeOrderform of) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlAddOrder, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, of.getGameId());
			stmt.setInt(2, of.getServerId());
			stmt.setString(3, of.getOrderform());
			stmt.setString(4, of.getChannelId());
			stmt.setString(5, of.getChannelSub());
			stmt.setInt(6, of.getAccountId());
			stmt.setInt(7, of.getRoleId());
			stmt.setString(8, of.getRoleName());
			stmt.setShort(9, of.getRoleLevel());
			stmt.setString(10, of.getDeviceOS());
			stmt.setInt(11, of.getItemId());
			stmt.setString(12, of.getItemName());
			stmt.setInt(13, of.getItemPrice());
			stmt.setInt(14, of.getItemCount());
			stmt.setByte(15, of.getStatus());
			stmt.setTimestamp(16, new Timestamp(of.getTimeCreate()));
			stmt.setTimestamp(17, new Timestamp(of.getTimeCreate()));
			stmt.setString(18, of.getUserId());
			stmt.setString(19, of.getDeviceImei());
			stmt.setString(20, of.getDeviceSerial());
			stmt.setString(21, of.getDeviceMac());
			stmt.setString(22, of.getIdfa());
			stmt.setInt(23, of.getAppId());
			int result = stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);

			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				Long key = (Long) rs.getObject(1);
				of.setId(key.intValue());
			}

			return result > 0;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("addChargeOrderform-01", e);
		} catch (Exception e) {
			LoggerError.error("addChargeOrderform-02", e);
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
				LoggerError.error("addChargeOrderform-03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}

	private static String sqlGetOrder = "select id,gameid,serverid,orderform,channelid,channelsub,accountid,roleid,rolename,rolelevel,deviceos,itemid,itemname,itemprice,itemcount,status,userid,deviceimei from ";

	public ChargeOrderform getOrderform(String orderform) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(sqlGetOrder).append(TableManager.START_ZONE).append(TableCreateScript.SYMBOL).append(ChargeOrderform.getZoneId(orderform)).append(TableCreateScript.SYMBOL).append(TableCreateScript.orderEnd);
			sb.append(" where orderform=?");
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, orderform);
			rs = stmt.executeQuery();
			if (rs.next()) {
				ChargeOrderform of = new ChargeOrderform();
				of.setId(rs.getLong(1));
				of.setGameId(rs.getInt(2));
				of.setServerId(rs.getInt(3));
				of.setOrderform(rs.getString(4));
				of.setChannelId(rs.getString(5));
				of.setChannelSub(rs.getString(6));
				of.setAccountId(rs.getInt(7));
				of.setRoleId(rs.getInt(8));
				of.setRoleName(rs.getString(9));
				of.setRoleLevel(rs.getShort(10));
				of.setDeviceOS(rs.getString(11));
				of.setItemId(rs.getInt(12));
				of.setItemName(rs.getString(13));
				of.setItemPrice(rs.getInt(14));
				of.setItemCount(rs.getInt(15));
				of.setStatus(rs.getByte(16));
				of.setUserId(rs.getString(17));
				of.setDeviceImei(rs.getString(18));
				return of;
			} else {

			}
			return null;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("getOrderform-01", e);
		} catch (Exception e) {
			LoggerError.error("getOrderform-02", e);
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
				LoggerError.error("getOrderform-03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return null;
	}

	public boolean validateOrderform(String orderform) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select handle_zone_id from ").append(TableManager.START_ZONE).append(TableCreateScript.SYMBOL).append(ChargeOrderform.getZoneId(orderform)).append(TableCreateScript.SYMBOL).append(TableCreateScript.orderEnd);
			sb.append(" where orderform=? limit 0,1");
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, orderform);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
			return false;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("validateOrderform-01", e);
		} catch (Exception e) {
			LoggerError.error("validateOrderform-02", e);
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
				LoggerError.error("validateOrderform-03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}

	public void updateChargeOrderform(ChargeOrderform orderform) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		sb.append("update ").append(TableManager.START_ZONE).append(TableCreateScript.SYMBOL).append(ChargeOrderform.getZoneId(orderform.getOrderform())).append(TableCreateScript.SYMBOL).append(TableCreateScript.orderEnd);
		sb.append(" set status=?, timehandle=?,handle_zone_id=? where id=?");
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sb.toString());
			stmt.setByte(1, orderform.getStatus());
			stmt.setTimestamp(2, new Timestamp(orderform.getTimeCreate()));
			stmt.setInt(3, ApplicationConfig.getInstance().getAppId());
			stmt.setLong(4, orderform.getId());
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
			LoggerError.error("updateChargeOrderform-01", e);
		} catch (Exception e) {
			LoggerError.error("updateChargeOrderform-02", e);
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
				LoggerError.error("updateChargeOrderform-03", e);
			}
			DBConnection.freeConnection(conn);
		}
	}
}
