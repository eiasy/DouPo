package mmo.common.service.eventcenter.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import mmo.tools.db.DBConnection;

public class TableManager extends TableCreateScript {

	public static void initSQL() {
		initEventAccountSQL();
		initEventChargeSQL();
		initEventDefaultSQL();
		createTable();
	}

	private static void createTable() {
		Connection conn = DBConnection.getConnection();
		Statement stmt = null;
		try {
			boolean auto = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.addBatch(eventAccountScript);
			stmt.addBatch(eventChargeScript);
			stmt.addBatch(eventDefaultScript);
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(auto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
	}

	private static void initEventAccountSQL() {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append("CREATE TABLE if not exists `");
		sb.append("event_account");
		sb.append("` (");
		sb.append("`id` bigint(10) NOT NULL AUTO_INCREMENT,");
		sb.append("`eventSource` varchar(64) DEFAULT NULL,");
		sb.append("`eventTag` varchar(64) DEFAULT NULL,");
		sb.append("`appTag` varchar(64) DEFAULT NULL,");
		sb.append("`platform` varchar(64) DEFAULT NULL,");
		sb.append("`serverTag` varchar(64) DEFAULT '0',");
		sb.append("`channelTag` varchar(128) DEFAULT NULL COMMENT '渠道标签',");
		sb.append("`channelSub` varchar(128) DEFAULT NULL,");
		sb.append("`accountId` int(11) DEFAULT '0',");
		sb.append("`userId` varchar(255) DEFAULT NULL,");
		sb.append("`roleId` int(11) DEFAULT '0',");
		sb.append("`roleName` varchar(32) DEFAULT NULL,");
		sb.append("`roleLevel` int(11) DEFAULT '0',");
		sb.append("`vipLevel` int(11) DEFAULT '0',");
		sb.append("`value1string` varchar(255) DEFAULT NULL,");
		sb.append("`value2string` varchar(255) DEFAULT NULL,");
		sb.append("`value3string` varchar(255) DEFAULT NULL,");
		sb.append("`value4string` varchar(255) DEFAULT NULL,");
		sb.append("`value5string` varchar(255) DEFAULT NULL,");
		sb.append("`value6string` varchar(255) DEFAULT NULL,");
		sb.append("`value7string` varchar(255) DEFAULT NULL,");
		sb.append("`value8string` varchar(255) DEFAULT NULL,");
		sb.append("`key1int` varchar(64) DEFAULT NULL,");
		sb.append("`value1int` int(11) DEFAULT '0',");
		sb.append("`key2int` varchar(64) DEFAULT NULL,");
		sb.append("`value2int` int(11) DEFAULT '0',");
		sb.append("`key3int` varchar(64) DEFAULT NULL,");
		sb.append("`value3int` int(11) DEFAULT NULL,");
		sb.append("`key1long` varchar(64) DEFAULT NULL,");
		sb.append("`value1long` bigint(11) DEFAULT '0',");
		sb.append("`key1double` varchar(64) DEFAULT NULL,");
		sb.append("`value1double` double DEFAULT '0',");
		sb.append("`timeAdd` datetime DEFAULT NULL,");

		sb.append("PRIMARY KEY (`id`)");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		eventChargeScript = sb.toString();
	}

	private static void initEventChargeSQL() {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append("CREATE TABLE if not exists `");
		sb.append("event_charge");
		sb.append("` (");
		sb.append("`id` bigint(10) NOT NULL AUTO_INCREMENT,");
		sb.append("`eventSource` varchar(64) DEFAULT NULL,");
		sb.append("`eventTag` varchar(64) DEFAULT NULL,");
		sb.append("`appTag` varchar(64) DEFAULT NULL,");
		sb.append("`platform` varchar(64) DEFAULT NULL,");
		sb.append("`serverTag` varchar(64) DEFAULT '0',");
		sb.append("`channelTag` varchar(128) DEFAULT NULL COMMENT '渠道标签',");
		sb.append("`channelSub` varchar(128) DEFAULT NULL,");
		sb.append("`accountId` int(11) DEFAULT '0',");
		sb.append("`userId` varchar(255) DEFAULT NULL,");
		sb.append("`roleId` int(11) DEFAULT '0',");
		sb.append("`roleName` varchar(32) DEFAULT NULL,");
		sb.append("`roleLevel` int(11) DEFAULT '0',");
		sb.append("`vipLevel` int(11) DEFAULT '0',");
		sb.append("`value1string` varchar(255) DEFAULT NULL,");
		sb.append("`value2string` varchar(255) DEFAULT NULL,");
		sb.append("`value3string` varchar(255) DEFAULT NULL,");
		sb.append("`value4string` varchar(255) DEFAULT NULL,");
		sb.append("`value5string` varchar(255) DEFAULT NULL,");
		sb.append("`value6string` varchar(255) DEFAULT NULL,");
		sb.append("`value7string` varchar(255) DEFAULT NULL,");
		sb.append("`value8string` varchar(255) DEFAULT NULL,");
		sb.append("`key1int` varchar(64) DEFAULT NULL,");
		sb.append("`value1int` int(11) DEFAULT '0',");
		sb.append("`key2int` varchar(64) DEFAULT NULL,");
		sb.append("`value2int` int(11) DEFAULT '0',");
		sb.append("`key3int` varchar(64) DEFAULT NULL,");
		sb.append("`value3int` int(11) DEFAULT NULL,");
		sb.append("`key1long` varchar(64) DEFAULT NULL,");
		sb.append("`value1long` bigint(11) DEFAULT '0',");
		sb.append("`key1double` varchar(64) DEFAULT NULL,");
		sb.append("`value1double` double DEFAULT '0',");
		sb.append("`timeAdd` datetime DEFAULT NULL,");
		sb.append("PRIMARY KEY (`id`)");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		eventAccountScript = sb.toString();
	}

	private static void initEventDefaultSQL() {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append("CREATE TABLE if not exists `");
		sb.append("event_default");
		sb.append("` (");
		sb.append("`id` bigint(10) NOT NULL AUTO_INCREMENT,");
		sb.append("`eventSource` varchar(64) DEFAULT NULL,");
		sb.append("`eventTag` varchar(64) DEFAULT NULL,");
		sb.append("`appTag` varchar(64) DEFAULT NULL,");
		sb.append("`platform` varchar(64) DEFAULT NULL,");
		sb.append("`serverTag` varchar(64) DEFAULT '0',");
		sb.append("`channelTag` varchar(128) DEFAULT NULL COMMENT '渠道标签',");
		sb.append("`channelSub` varchar(128) DEFAULT NULL,");
		sb.append("`accountId` int(11) DEFAULT '0',");
		sb.append("`userId` varchar(255) DEFAULT NULL,");
		sb.append("`roleId` int(11) DEFAULT '0',");
		sb.append("`roleName` varchar(32) DEFAULT NULL,");
		sb.append("`roleLevel` int(11) DEFAULT '0',");
		sb.append("`vipLevel` int(11) DEFAULT '0',");
		sb.append("`value1string` varchar(255) DEFAULT NULL,");
		sb.append("`value2string` varchar(255) DEFAULT NULL,");
		sb.append("`value3string` varchar(255) DEFAULT NULL,");
		sb.append("`value4string` varchar(255) DEFAULT NULL,");
		sb.append("`value5string` varchar(255) DEFAULT NULL,");
		sb.append("`value6string` varchar(255) DEFAULT NULL,");
		sb.append("`value7string` varchar(255) DEFAULT NULL,");
		sb.append("`value8string` varchar(255) DEFAULT NULL,");
		sb.append("`key1int` varchar(64) DEFAULT NULL,");
		sb.append("`value1int` int(11) DEFAULT '0',");
		sb.append("`key2int` varchar(64) DEFAULT NULL,");
		sb.append("`value2int` int(11) DEFAULT '0',");
		sb.append("`key3int` varchar(64) DEFAULT NULL,");
		sb.append("`value3int` int(11) DEFAULT NULL,");
		sb.append("`key1long` varchar(64) DEFAULT NULL,");
		sb.append("`value1long` bigint(11) DEFAULT '0',");
		sb.append("`key1double` varchar(64) DEFAULT NULL,");
		sb.append("`value1double` double DEFAULT '0',");
		sb.append("`timeAdd` datetime DEFAULT NULL,");
		sb.append("PRIMARY KEY (`id`)");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		eventDefaultScript = sb.toString();
	}
}
