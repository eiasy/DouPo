package mmo.common.module.service.charge.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeState;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class ServiceCharge extends TableManager {
	private final static ServiceCharge instance = new ServiceCharge();

	public final static ServiceCharge getInstance() {
		return instance;
	}

	private ServiceCharge() {

	}

	public final boolean addChargeRecord(ChargeRecord cr) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into ").append(TableManager.START_ZONE).append(TableCreateScript.SYMBOL).append(chargeZone)
			        .append(TableCreateScript.SYMBOL).append(TableCreateScript.chargeEnd);
			sb.append(" (orderid, gameid, serverid, channelid, accountid, roleid, rolename, money, getmoney, ctype, "
			        + "state, atime, dtime, note, orderform, proxy, proxychannel, proxytime, userid, channelsub, rolelevel, "
			        + "goodsid, goodsname, goodscount, deviceos, price,deviceimei,device_serial,device_mac,idfa ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cr.getOrderId());
			stmt.setInt(2, cr.getGameId());
			stmt.setInt(3, cr.getServerId());
			stmt.setString(4, cr.getChannelId());
			stmt.setInt(5, cr.getAccountId());
			stmt.setInt(6, cr.getRoleId());
			stmt.setString(7, cr.getRolename());
			stmt.setInt(8, cr.getMoney());
			stmt.setInt(9, cr.getGetmoney());
			stmt.setByte(10, cr.getCtype());
			stmt.setByte(11, cr.getState());
			stmt.setTimestamp(12, cr.getAtime());
			stmt.setTimestamp(13, cr.getDtime());
			stmt.setString(14, cr.getNote());
			stmt.setString(15, cr.getOrderform());
			stmt.setString(16, cr.getProxy());
			stmt.setString(17, cr.getProxyChannel());
			stmt.setTimestamp(18, cr.getProxyTime());
			stmt.setString(19, cr.getUserid());
			stmt.setString(20, cr.getChannelSub());
			stmt.setShort(21, cr.getRoleLevel());
			stmt.setInt(22, cr.getGoodsId());
			stmt.setString(23, cr.getGoodsName());
			stmt.setInt(24, cr.getGoodsCount());
			stmt.setString(25, cr.getDeviceOS());
			stmt.setInt(26, cr.getPrice());
			stmt.setString(27, cr.getDeviceImei());
			stmt.setString(28, cr.getDeviceSerial());
			stmt.setString(29, cr.getDeviceMac());
			stmt.setString(30, cr.getIdfa());
			int result = stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				Long key = (Long) rs.getObject(1);
				cr.setId(key);
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
			LoggerError.error("addChargeRecord-01", e);
		} catch (Exception e) {
			LoggerError.error("addChargeRecord-02", e);
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
				LoggerError.error("addChargeRecord-03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}

	public List<ChargeRecord> loadCharges(int productId, int applicationId) {
		Connection conn = DBConnection.getConnection();
		List<ChargeRecord> list = new ArrayList<ChargeRecord>();
		PreparedStatement stmt = null;
		ChargeRecord cr = null;
		List<String> chargeTables = getChargeTables();
		try {
			StringBuilder sb = new StringBuilder();
			for (int ti = 0; ti < chargeTables.size(); ti++) {
				sb.setLength(0);
				sb.append("select id, orderid, channelid, accountid, roleid, rolename, money, ctype, note, orderform, proxy, proxychannel, proxytime, userid, channelsub, rolelevel, goodsid, goodsname, goodscount, deviceos, price,deviceimei from ");
				sb.append(chargeTables.get(ti)).append(" where gameid=? and serverid=? and state=?");
				stmt = conn.prepareStatement(sb.toString());
				stmt.setInt(1, productId);
				stmt.setInt(2, applicationId);
				stmt.setByte(3, ChargeState.GAME_SERVER_UNADD);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					cr = new ChargeRecord();
					cr.setId(rs.getLong(1));
					cr.setOrderId(rs.getString(2));
					cr.setChannelId(rs.getString(3));
					cr.setAccountId(rs.getInt(4));
					cr.setRoleId(rs.getInt(5));
					cr.setRolename(rs.getString(6));
					cr.setMoney(rs.getInt(7));
					cr.setCtype(rs.getByte(8));
					cr.setNote(rs.getString(9));
					cr.setOrderform(rs.getString(10));
					cr.setProxy(rs.getString(11));
					cr.setProxyChannel(rs.getString(12));
					cr.setProxyTime(rs.getTimestamp(13));
					cr.setUserid(rs.getString(14));
					cr.setChannelSub(rs.getString(15));
					cr.setRoleLevel(rs.getShort(16));
					cr.setGoodsId(rs.getInt(17));
					cr.setGoodsName(rs.getString(18));
					cr.setGoodsCount(rs.getInt(19));
					cr.setDeviceOS(rs.getString(20));
					cr.setPrice(rs.getInt(21));
					cr.setDeviceImei(rs.getString(22));
					cr.setState(ChargeState.GAME_SERVER_UNADD);
					cr.setGameId(productId);
					cr.setServerId(applicationId);
					list.add(cr);
				}
				rs.close();
			}
			return list;
		} catch (SQLException e) {
			LoggerError.error("updateChargeRecord-01", e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				LoggerError.error("updateChargeRecord-02", e);
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
		return list;
	}

	public List<ChargeRecord> loadUnhandledCharges() {
		Connection conn = DBConnection.getConnection();
		List<ChargeRecord> list = new ArrayList<ChargeRecord>();
		PreparedStatement stmt = null;
		ChargeRecord cr = null;
		try {
			stmt = conn.prepareStatement(sqlUnhandled);
			stmt.setByte(1, ChargeState.GAME_SERVER_UNADD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cr = new ChargeRecord();
				cr.setId(rs.getLong(1));
				cr.setOrderId(rs.getString(2));
				cr.setChannelId(rs.getString(3));
				cr.setAccountId(rs.getInt(4));
				cr.setRoleId(rs.getInt(5));
				cr.setRolename(rs.getString(6));
				cr.setMoney(rs.getInt(7));
				cr.setCtype(rs.getByte(8));
				cr.setNote(rs.getString(9));
				cr.setOrderform(rs.getString(10));
				cr.setProxy(rs.getString(11));
				cr.setProxyChannel(rs.getString(12));
				cr.setProxyTime(rs.getTimestamp(13));
				cr.setUserid(rs.getString(14));
				cr.setChannelSub(rs.getString(15));
				cr.setRoleLevel(rs.getShort(16));
				cr.setGoodsId(rs.getInt(17));
				cr.setGoodsName(rs.getString(18));
				cr.setGoodsCount(rs.getInt(19));
				cr.setDeviceOS(rs.getString(20));
				cr.setPrice(rs.getInt(21));
				cr.setDeviceImei(rs.getString(22));
				cr.setState(ChargeState.GAME_SERVER_UNADD);
				cr.setGameId(rs.getInt(23));
				cr.setServerId(rs.getInt(24));
				list.add(cr);
			}
			rs.close();
			return list;
		} catch (SQLException e) {
			LoggerError.error("loadUnhandledCharges-01", e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				LoggerError.error("loadUnhandledCharges-02", e);
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
		return list;
	}

	public boolean updateChargeRecord(ChargeRecord cr) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("update ").append(TableManager.START_ZONE).append(TableCreateScript.SYMBOL).append(chargeZone).append(TableCreateScript.SYMBOL)
			        .append(TableCreateScript.chargeEnd);
			sb.append(" set state=?,dtime=? where id=?");
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sb.toString());
			stmt.setByte(1, cr.getState());
			stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			stmt.setLong(3, cr.getId());
			int result = stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			return result > 0;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("updateChargeRecord-01", e);
		} catch (Exception e) {
			LoggerError.error("updateChargeRecord-02", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("updateChargeRecord-03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}
}
