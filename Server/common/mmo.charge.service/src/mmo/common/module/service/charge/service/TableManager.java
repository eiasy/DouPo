package mmo.common.module.service.charge.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class TableManager extends TableCreateScript {
	protected static int chargeZone = 1;
	protected static int receiptZone = 0;
	public static int orderZone = 0;
	public static int qqZone = 0;
	protected static String sqlUpdateStoreReceipt;
	protected static String sqlUpdateQQ;
	protected static String sqlLoadStoreReceipt;
	protected static String sqlLoadQQ;
	protected static String sqlAddStoreReceipt;
	protected static String sqlAddQQ;
	protected static String sqlCheckReceiptExist;
	protected static String sqlCheckQQExist;
	protected static String sqlMaxIdStoreReceipt;

	protected static String sqlAddOrder;

	protected static String sqlUnhandled;

	public static void initSQL() {
		initStoreReceiptSQL();
		initOrderSQL();
		initChargeSQL();
		initIdfaActiveSQL();
		initIdfaEventSQL();
		initQQSQL();

		createTableRole();
	}

	private static void createTableRole() {
		Connection conn = DBConnection.getConnection();
		Statement stmt = null;
		try {
			boolean auto = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.addBatch(storeReceiptScript);
			stmt.addBatch(orderScript);
			stmt.addBatch(chargeScript);
			stmt.addBatch(idfaActiveScript);
			stmt.addBatch(idfaEventScript);
			stmt.addBatch(qqScript);
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(auto);
		} catch (SQLException e) {
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

	private static void initStoreReceiptSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append(START_ZONE).append(SYMBOL).append(receiptZone).append(SYMBOL).append(storeReceiptEnd);
		storeReceiptTitle = sb.toString();
		sb.setLength(0);
		sb.append("CREATE TABLE if not exists `");
		sb.append(storeReceiptTitle);
		sb.append("` (");
		sb.append("`id` int(11) NOT NULL AUTO_INCREMENT,");
		sb.append("`receipt` text,");
		sb.append("`status` int(11) DEFAULT '0',");
		sb.append("`gameid` int(11) DEFAULT '0',");
		sb.append("`serverid` int(11) DEFAULT '0',");
		sb.append("`channelid`  varchar(60) DEFAULT NULL,");
		sb.append("`channelsub` varchar(128) DEFAULT NULL,");
		sb.append("`accountid` int(11) DEFAULT '0',");
		sb.append("`roleid` int(11) DEFAULT '0',");
		sb.append("`rolelevel` smallint(6) DEFAULT '0',");
		sb.append("`rolename` varchar(30) DEFAULT NULL,");
		sb.append("`goodsid` int(11) DEFAULT '0',");
		sb.append("`goodsname` varchar(30) DEFAULT NULL,");
		sb.append("`price` int(11) DEFAULT '0',");
		sb.append("`count` int(11) DEFAULT '0',");
		sb.append("`deviceos` varchar(128) DEFAULT NULL,");
		sb.append("`userid` varchar(50) DEFAULT NULL,");
		sb.append("`atime` datetime DEFAULT NULL,");
		sb.append("`udate` datetime DEFAULT NULL,");
		sb.append("`orderform` varchar(128) DEFAULT NULL,");
		sb.append("`proid` varchar(128) DEFAULT NULL,");
		sb.append("`deviceimei` varchar(255) DEFAULT NULL,");
		sb.append("`devicemac` varchar(128) DEFAULT NULL,");
		sb.append("`deviceserial` varchar(128) DEFAULT NULL,");
		sb.append("`idfa` varchar(256) DEFAULT NULL,");
		sb.append("`receiptmd5` varchar(64) DEFAULT NULL,");
		sb.append("`checkResult` text,");
		sb.append("`chargeType` varchar(64) DEFAULT NULL,");
		sb.append("`appId` int(11) DEFAULT '0',");
		sb.append("PRIMARY KEY (`id`),");
		sb.append("KEY `index_md5` (`receiptmd5`)");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		storeReceiptScript = sb.toString();

		sb.setLength(0);
		sb.append("insert into ").append(storeReceiptTitle);
		sb.append("(receipt, status, channelid, channelsub, accountid, roleid, rolelevel, rolename, deviceos, userid, atime, udate,gameid,serverid,deviceimei,devicemac,deviceserial,idfa,proid,orderform,receiptmd5,appId)values(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
		sqlAddStoreReceipt = sb.toString();

		sb.setLength(0);
		sb.append("select id from ").append(storeReceiptTitle);
		sb.append(" where receiptmd5=? limit 0,1");
		sqlCheckReceiptExist = sb.toString();

		sb.setLength(0);
		sb.append("update ").append(storeReceiptTitle);
		sb.append(" set status=?,udate=?, goodsid=?, goodsname=?, price=?, count=?, checkResult=?,chargeType=? where id=?");
		sqlUpdateStoreReceipt = sb.toString();

		sb.setLength(0);
		sb.append("select id, receipt, status, channelid, channelsub, accountid, roleid, rolelevel, rolename, deviceos, userid, goodsid, goodsname, price, count, orderform, proid,gameid,serverid,deviceimei,receiptmd5,chargeType from ").append(storeReceiptTitle).append(" where status<?");
		sqlLoadStoreReceipt = sb.toString();

		sqlMaxIdStoreReceipt = "select max(id) from " + storeReceiptTitle;
	}

	private static void initQQSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append(START_ZONE).append(SYMBOL).append(qqZone).append(SYMBOL).append(qqEnd);
		qqTitle = sb.toString();
		sb.setLength(0);
		sb.append("CREATE TABLE if not exists `");
		sb.append(qqTitle);
		sb.append("` (");
		sb.append("`id` bigint(11) NOT NULL AUTO_INCREMENT, ");
		sb.append("`resultCode` int(11) DEFAULT NULL, ");
		sb.append("`payChannel` int(11) DEFAULT NULL, ");
		sb.append("`payState` int(11) DEFAULT NULL, ");
		sb.append("`provideState` int(11) DEFAULT NULL, ");
		sb.append("`saveNum` int(11) DEFAULT NULL, ");
		sb.append("`extendInfo` varchar(512) DEFAULT NULL,");
		sb.append("`orderId` varchar(64) DEFAULT NULL, ");
		sb.append("`remoteIp` varchar(64) DEFAULT NULL, ");
		sb.append("`openid` varchar(255) DEFAULT NULL, ");
		sb.append("`openkey` varchar(512) DEFAULT NULL, ");
		sb.append("`payToken` varchar(512) DEFAULT NULL, ");
		sb.append("`pf` varchar(512) DEFAULT NULL, ");
		sb.append("`pfkey` varchar(255) DEFAULT NULL, ");
		sb.append("`actionType` varchar(32) DEFAULT NULL, ");
		sb.append("`cdata` varchar(512) DEFAULT NULL, ");
		sb.append("`addTime` datetime DEFAULT NULL, ");
		sb.append("`updateTime` datetime DEFAULT NULL, ");
		sb.append("`imei` varchar(255) DEFAULT NULL, ");
		sb.append("`idfa` varchar(255) DEFAULT NULL, ");
		sb.append("`status` int(11) DEFAULT NULL, ");
		sb.append("`checkCount` int(11) DEFAULT NULL, ");
		sb.append("`chargeType` varchar(32) DEFAULT NULL, ");
		sb.append("`appId` int(11) DEFAULT NULL, ");
		sb.append("`handleAppId` int(11) DEFAULT NULL, ");
		sb.append("`zoneId` int(11) DEFAULT '0',");
		sb.append("`channelOrderId` varchar(255) DEFAULT NULL, ");
		sb.append("PRIMARY KEY (`id`), ");
		sb.append("KEY `index_order_id` (`orderId`)");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		qqScript = sb.toString();
		sb.setLength(0);
		sb.append("insert into ").append(qqTitle);
		sb.append("(resultCode,payChannel,payState,provideState,saveNum,extendInfo,orderId,remoteIp,openid,openkey,payToken,pf,pfkey,actionType,cdata,addTime,updateTime,imei,idfa,status,checkCount,chargeType,appId,handleAppId,zoneId)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		sqlAddQQ = sb.toString();

		sb.setLength(0);
		sb.append("select id,status,handleAppId from ").append(qqTitle);
		sb.append(" where orderId=? limit 0,1");
		sqlCheckQQExist = sb.toString();

		sb.setLength(0);
		sb.append("update ").append(qqTitle);
		sb.append(" set updateTime=?, status=?, checkCount=?, handleAppId=?,channelOrderId=?,chargeType=?,cdata=? where id=?");
		sqlUpdateQQ = sb.toString();

		sb.setLength(0);
		sb.append("select id,resultCode,payChannel,payState,provideState,saveNum,extendInfo,orderId,remoteIp,openid,openkey,payToken,pf,pfkey,actionType,cdata,addTime,updateTime,imei,idfa,status,checkCount,chargeType,appId,handleAppId,zoneId,channelOrderId from ").append(qqTitle).append(" where appId=? and status<?");
		sqlLoadQQ = sb.toString();
	}

	private static void initOrderSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append(START_ZONE).append(SYMBOL).append(orderZone).append(SYMBOL).append(orderEnd);
		orderTitle = sb.toString();
		sb.setLength(0);
		sb.append("CREATE TABLE if not exists `");
		sb.append(orderTitle);
		sb.append("` (");
		sb.append("`id` bigint(11) NOT NULL AUTO_INCREMENT,");
		sb.append("`gameid` int(11) DEFAULT NULL,");
		sb.append("`serverid` int(11) DEFAULT NULL,");
		sb.append("`orderform` varchar(256) DEFAULT NULL,");
		sb.append("`channelid` varchar(64) DEFAULT NULL,");
		sb.append("`channelsub` varchar(128) DEFAULT NULL,");
		sb.append("`accountid` int(11) DEFAULT NULL,");
		sb.append("`roleid` int(11) DEFAULT NULL,");
		sb.append("`rolename` varchar(30) DEFAULT NULL,");
		sb.append("`rolelevel` smallint(6) DEFAULT NULL,");
		sb.append("`deviceos` varchar(1024) DEFAULT NULL,");
		sb.append("`itemid` int(11) DEFAULT NULL,");
		sb.append("`itemname` varchar(30) DEFAULT NULL,");
		sb.append("`itemprice` int(11) DEFAULT NULL,");
		sb.append("`itemcount` int(11) DEFAULT NULL,");
		sb.append("`status` tinyint(3) DEFAULT NULL,");
		sb.append("`timecreate` datetime DEFAULT NULL,");
		sb.append("`timehandle` datetime DEFAULT NULL,");
		sb.append("`userid` varchar(256) DEFAULT NULL,");
		sb.append("`deviceimei` varchar(255) DEFAULT NULL,");
		sb.append("`device_serial` varchar(255) DEFAULT NULL,");
		sb.append("`device_mac` varchar(255) DEFAULT NULL,");
		sb.append("`idfa` varchar(255) DEFAULT NULL,");
		sb.append("`handle_zone_id` int(11) DEFAULT '0',");
		sb.append("`appId` int(11) DEFAULT '0',");
		sb.append("PRIMARY KEY (`id`),");
		sb.append("KEY `index_orderform` (`orderform`(255))");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		orderScript = sb.toString();

		sb.setLength(0);
		sb.append("insert into ").append(orderTitle);
		sb.append("(gameid,serverid,orderform,channelid,channelsub,accountid,roleid,rolename,rolelevel,deviceos,itemid,itemname,itemprice,itemcount,status,timecreate,timehandle,userid,deviceimei,device_serial,device_mac,idfa,appId) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		sqlAddOrder = sb.toString();
		sb.setLength(0);
	}

	private static void initIdfaActiveSQL() {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append("CREATE TABLE if not exists `");
		sb.append("idfa_active");
		sb.append("` (");
		sb.append("`id` bigint(11) NOT NULL AUTO_INCREMENT,");
		sb.append("`app_id` varchar(128) DEFAULT NULL,");
		sb.append("`channel_tag` varchar(64) DEFAULT NULL,");
		sb.append("`idfa` varchar(255) DEFAULT NULL,");
		sb.append("`device_mac` varchar(255) DEFAULT NULL,");
		sb.append("`device_imei` varchar(255) DEFAULT NULL,");
		sb.append("`ip` varchar(64) DEFAULT NULL, ");
		sb.append("`add_time` varchar(255) DEFAULT NULL,");
		sb.append("`app_start` int(3) DEFAULT NULL,");
		sb.append("`app_start_time` datetime DEFAULT NULL,");
		sb.append("`role_create` int(3) DEFAULT NULL,");
		sb.append("`role_create_time` datetime DEFAULT NULL,");
		sb.append("`role_create_channel` varchar(255) DEFAULT NULL,");
		sb.append("`role_user_id` varchar(255) DEFAULT NULL,");
		sb.append("`descript` varchar(512) DEFAULT NULL,");
		sb.append("`state` tinyint(3) DEFAULT '0',");
		sb.append("`device_udid` varchar(255) DEFAULT NULL,");
		sb.append("`device_serial` varchar(128) DEFAULT NULL,");
		sb.append("`device_ua` varchar(512) DEFAULT NULL,");
		sb.append("`device_os` varchar(255) DEFAULT NULL,");
		sb.append("`device_os_version` varchar(64) DEFAULT NULL,");
		sb.append("`channel_sub` varchar(64) DEFAULT NULL,");
		sb.append("`media` varchar(64) DEFAULT NULL,");
		sb.append("`callback` varchar(255) DEFAULT NULL,");
		sb.append("PRIMARY KEY (`id`)");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		idfaActiveScript = sb.toString();
	}

	private static void initIdfaEventSQL() {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append("CREATE TABLE if not exists `");
		sb.append("idfa_event");
		sb.append("` (");
		sb.append("`id` bigint(10) NOT NULL AUTO_INCREMENT,");
		sb.append("`event_type` varchar(64) DEFAULT NULL,");
		sb.append("`event_tag` varchar(64) DEFAULT NULL,");
		sb.append("`app_id` varchar(128) DEFAULT NULL,");
		sb.append("`channel_tag` varchar(128) DEFAULT NULL COMMENT '渠道标签',");
		sb.append("`idfa` varchar(255) DEFAULT NULL,");
		sb.append("`device_mac` varchar(128) DEFAULT NULL COMMENT 'mac地址',");
		sb.append("`device_imei` varchar(255) DEFAULT NULL,");
		sb.append("`ip` varchar(64) DEFAULT NULL,");
		sb.append("`time_add` datetime DEFAULT NULL,");
		sb.append("`device_udid` varchar(255) DEFAULT NULL COMMENT '设备UDID',");
		sb.append("`device_serial` varchar(255) DEFAULT NULL COMMENT '设备序列号',");
		sb.append("`device_ua` varchar(512) DEFAULT NULL COMMENT '设备UA',");
		sb.append("`device_os` varchar(128) DEFAULT NULL COMMENT '设备操作系统',");
		sb.append("`device_os_version` varchar(64) DEFAULT NULL,");
		sb.append("`phone_code` varchar(64) DEFAULT NULL,");
		sb.append("`descript` varchar(512) DEFAULT NULL,");
		sb.append("`channel_sub` varchar(64) DEFAULT NULL,");
		sb.append("`media` varchar(64) DEFAULT NULL,");
		sb.append("`callback` varchar(255) DEFAULT NULL,");
		sb.append("`key_1` varchar(64) DEFAULT NULL,");
		sb.append("`value_1` varchar(255) DEFAULT NULL,");
		sb.append("`key_2` varchar(64) DEFAULT NULL,");
		sb.append("`value_2` varchar(255) DEFAULT NULL,");
		sb.append("`key_3` varchar(64) DEFAULT NULL,");
		sb.append("`value_3` varchar(255) DEFAULT NULL,");
		sb.append("`key_4` varchar(64) DEFAULT NULL,");
		sb.append("`value_4` varchar(255) DEFAULT NULL,");
		sb.append("`key_5` varchar(64) DEFAULT NULL,");
		sb.append("`value_5` varchar(512) DEFAULT NULL,");
		sb.append("PRIMARY KEY (`id`)");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		idfaEventScript = sb.toString();
	}

	private static void initChargeSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append(START_ZONE).append(SYMBOL).append(chargeZone).append(SYMBOL).append(chargeEnd);
		chargeTitle = sb.toString();
		sb.setLength(0);
		sb.append("CREATE TABLE if not exists `");
		sb.append(chargeTitle);
		sb.append("` (");
		sb.append("`id` bigint(11) NOT NULL AUTO_INCREMENT,");
		sb.append("`orderid` varchar(256) DEFAULT NULL,");
		sb.append("`gameid` int(11) DEFAULT '0',");
		sb.append("`serverid` int(11) DEFAULT '0',");
		sb.append("`channelid` varchar(64) DEFAULT '0',");
		sb.append("`accountid` int(11) DEFAULT '0',");
		sb.append("`roleid` int(11) DEFAULT '0',");
		sb.append("`rolename` varchar(30) DEFAULT NULL,");
		sb.append("`money` int(11) DEFAULT '0',");
		sb.append("`getmoney` int(11) DEFAULT '0',");
		sb.append("`ctype` tinyint(3) DEFAULT '1',");
		sb.append("`state` tinyint(3) DEFAULT '0',");
		sb.append("`atime` datetime DEFAULT NULL,");
		sb.append("`dtime` datetime DEFAULT NULL,");
		sb.append("`note` varchar(1024) DEFAULT NULL,");
		sb.append("`orderform` varchar(255) DEFAULT NULL,");
		sb.append("`proxy` varchar(20) DEFAULT NULL,");
		sb.append("`proxychannel` varchar(20) DEFAULT NULL,");
		sb.append("`proxytime` datetime DEFAULT NULL,");
		sb.append("`userid` varchar(256) DEFAULT NULL,");
		sb.append("`channelsub` varchar(128) DEFAULT NULL,");
		sb.append("`rolelevel` smallint(6) DEFAULT '0',");
		sb.append("`goodsid` int(11) DEFAULT '0',");
		sb.append("`goodsname` varchar(20) DEFAULT NULL,");
		sb.append("`goodscount` int(11) DEFAULT '0',");
		sb.append("`deviceos` varchar(1024) DEFAULT NULL,");
		sb.append("`price` int(11) DEFAULT NULL,");
		sb.append("`deviceimei` varchar(255) DEFAULT NULL,");
		sb.append("`device_serial` varchar(255) DEFAULT NULL,");
		sb.append("`device_mac` varchar(255) DEFAULT NULL,");
		sb.append("`idfa` varchar(255) DEFAULT NULL,");
		sb.append("PRIMARY KEY (`id`),");
		sb.append("KEY `orderid_index` (`orderid`(255))");
		sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		chargeScript = sb.toString();

		// sb.setLength(0);
		// sb.append("insert into ").append(chargeTitle);
		// sb.append(" (orderid, gameid, serverid, channelid, accountid, roleid, rolename, money, getmoney, ctype, "
		// + "state, atime, dtime, note, orderform, proxy, proxychannel, proxytime, userid, channelsub, rolelevel, "
		// +
		// "goodsid, goodsname, goodscount, deviceos, price,deviceimei,device_serial,device_mac,idfa ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		// sqlAddCharge = sb.toString();
		sb.setLength(0);
		sb.append("select id, orderid, channelid, accountid, roleid, rolename, money, ctype, note, orderform, proxy, proxychannel, proxytime, userid, channelsub, rolelevel, goodsid, goodsname, goodscount, deviceos, price,deviceimei,gameid,serverid from ");
		sb.append(chargeTitle).append(" where state=?");
		sqlUnhandled = sb.toString();
	}

	public static List<String> getChargeTables() {
		List<String> userTableList = new ArrayList<String>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("show tables like '").append(START_ZONE).append("_").append("%").append("_").append(chargeEnd).append("'");
			stmt = conn.prepareStatement(sb.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userTableList.add(rs.getString(1));
			}
			rs.close();
		} catch (SQLException e) {
			LoggerError.error("加载charge表格名字报错", e);
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

		return userTableList;
	}
}
