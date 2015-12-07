package mmo.common.service.eventcenter.service.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import mmo.common.bean.bi.EventDefault;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class EventService {
	private static String addEventAccount = "insert into event_account (eventSource,eventTag,appTag,platform,serverTag,channelTag,channelSub,accountId,userId,roleId,roleName,roleLevel,vipLevel,value1string,value2string,value3string,value4string,value5string,value6string,value7string,value8string,key1int,value1int,key2int,value2int,key3int,value3int,key1long,value1long,key1double,value1double,timeAdd) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String addEventCharge = "insert into event_charge (eventSource,eventTag,appTag,platform,serverTag,channelTag,channelSub,accountId,userId,roleId,roleName,roleLevel,vipLevel,value1string,value2string,value3string,value4string,value5string,value6string,value7string,value8string,key1int,value1int,key2int,value2int,key3int,value3int,key1long,value1long,key1double,value1double,timeAdd) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String addEventDefault = "insert into event_default (eventSource,eventTag,appTag,platform,serverTag,channelTag,channelSub,accountId,userId,roleId,roleName,roleLevel,vipLevel,value1string,value2string,value3string,value4string,value5string,value6string,value7string,value8string,key1int,value1int,key2int,value2int,key3int,value3int,key1long,value1long,key1double,value1double,timeAdd) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public EventService() {
	}

	public void addEvent(EventDefault event) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			if ("account".equalsIgnoreCase(event.getEventSource())) {
				stmt = conn.prepareStatement(addEventAccount);
			} else if ("charge".equalsIgnoreCase(event.getEventSource())) {
				stmt = conn.prepareStatement(addEventCharge);
			} else {
				stmt = conn.prepareStatement(addEventDefault);
			}

			stmt.setString(1, event.getEventSource());
			stmt.setString(2, event.getEventTag());
			stmt.setString(3, event.getAppTag());
			stmt.setString(4, event.getPlatform());
			stmt.setString(5, event.getServerTag());
			stmt.setString(6, event.getChannelTag());
			stmt.setString(7, event.getChannelSub());
			stmt.setInt(8, event.getAccountId());
			stmt.setString(9, event.getUserId());
			stmt.setInt(10, event.getRoleId());
			stmt.setString(11, event.getRoleName());
			stmt.setInt(12, event.getRoleLevel());
			stmt.setInt(13, event.getVipLevel());
			stmt.setString(14, event.getValue1string());
			stmt.setString(15, event.getValue2string());
			stmt.setString(16, event.getValue3string());
			stmt.setString(17, event.getValue4string());
			stmt.setString(18, event.getValue5string());
			stmt.setString(19, event.getValue6string());
			stmt.setString(20, event.getValue7string());
			stmt.setString(21, event.getValue8string());
			stmt.setString(22, event.getKey1int());
			stmt.setInt(23, event.getValue1int());
			stmt.setString(24, event.getKey2int());
			stmt.setInt(25, event.getValue2int());
			stmt.setString(26, event.getKey3int());
			stmt.setInt(27, event.getValue3int());
			stmt.setString(28, event.getKey1long());
			stmt.setLong(29, event.getValue1long());
			stmt.setString(30, event.getKey1double());
			stmt.setDouble(31, event.getValue1double());
			stmt.setTimestamp(32, new Timestamp(System.currentTimeMillis()));
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
			LoggerError.error("addIdfaActive异常", e);
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
	
	public void addEvent(String sql) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
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
			e.printStackTrace();
			LoggerError.error("addIdfaActive异常", e);
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
