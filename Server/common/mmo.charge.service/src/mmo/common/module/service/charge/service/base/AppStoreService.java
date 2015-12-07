package mmo.common.module.service.charge.service.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.module.service.charge.service.IStoreService;
import mmo.common.module.service.charge.service.TableManager;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class AppStoreService extends TableManager implements IStoreService {

	public AppStoreService() {
	}

	@Override
	public boolean addRoleStoreReceipt(RoleStoreReceipt receipt) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sqlCheckReceiptExist);
			stmt.setString(1, receipt.getReceiptMd5());
			rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				if (id > 0) {
					return false;
				}
			}
			try {
				stmt.close();
			} catch (Exception e) {
				LoggerError.error("关闭stmt时异常", e);
			}
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlAddStoreReceipt, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, receipt.getReceipt());
			stmt.setInt(2, receipt.getStatus());
			stmt.setString(3, receipt.getChannelId());
			stmt.setString(4, receipt.getChannelSub());
			stmt.setInt(5, receipt.getAccountId());
			stmt.setInt(6, receipt.getRoleId());
			stmt.setShort(7, receipt.getLevel());
			stmt.setString(8, receipt.getRoleName());
			stmt.setString(9, receipt.getDeviceOS());
			stmt.setString(10, receipt.getUserId());
			stmt.setTimestamp(11, new Timestamp(receipt.getTimeCreate()));
			stmt.setTimestamp(12, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(13, receipt.getGameId());
			stmt.setInt(14, receipt.getServerId());
			stmt.setString(15, receipt.getDeviceImei());
			stmt.setString(16, receipt.getDeviceMac());
			stmt.setString(17, receipt.getDeviceSerial());
			stmt.setString(18, receipt.getIdfa());
			stmt.setString(19, receipt.getProid());
			stmt.setString(20, receipt.getOrderform());
			stmt.setString(21, receipt.getReceiptMd5());
			stmt.setInt(22, ApplicationConfig.getInstance().getAppId());
			stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				Long key = (Long) rs.getObject(1);
				receipt.setId(key.intValue());
			}
			return true;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("添加AppStore票据异常", e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
		return false;
	}

	@Override
	public void updateRoleStoreReceipt(RoleStoreReceipt receipt) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlUpdateStoreReceipt);
			stmt.setInt(1, receipt.getStatus());
			stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(3, receipt.getGoodsId());
			stmt.setString(4, receipt.getGoodsName());
			stmt.setInt(5, receipt.getPrice());
			stmt.setInt(6, receipt.getCount());
			stmt.setString(7, receipt.getCheckResult());
			stmt.setString(8, receipt.getChargeType());
			stmt.setInt(9, receipt.getId());
			int count = stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			if (count < 1) {
				stmt.close();

				StringBuilder sb = new StringBuilder();
				sb.append(START_ZONE).append(SYMBOL).append(ApplicationConfig.getInstance().getAppId()).append(SYMBOL).append(storeReceiptEnd);
				String title = sb.toString();
				stmt = conn.prepareStatement("SHOW TABLES LIKE '" + title + "'");
				rs = stmt.executeQuery();
				if (rs.next()) {
					sb.setLength(0);
					sb.append("update ").append(title);
					sb.append(" set status=?,udate=?, goodsid=?, goodsname=?, price=?, count=? where id=?");
					autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					stmt = conn.prepareStatement(sb.toString());
					stmt.setInt(1, receipt.getStatus());
					stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
					stmt.setInt(3, receipt.getGoodsId());
					stmt.setString(4, receipt.getGoodsName());
					stmt.setInt(5, receipt.getPrice());
					stmt.setInt(6, receipt.getCount());
					stmt.setInt(7, receipt.getId());
					count = stmt.executeUpdate();
					conn.commit();
					conn.setAutoCommit(autoCommit);
				}
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("更新AppStore票据异常", e);
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

	public List<RoleStoreReceipt> loadRoleStoreReceipt(byte status) {
		List<RoleStoreReceipt> receipts = new ArrayList<RoleStoreReceipt>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		RoleStoreReceipt receipt = null;
		try {
			stmt = conn.prepareStatement(sqlLoadStoreReceipt);
			stmt.setByte(1, status);
			rs = stmt.executeQuery();
			while (rs.next()) {
				receipt = new RoleStoreReceipt();
				receipt.setId(rs.getInt(1));
				receipt.setReceipt(rs.getString(2));
				receipt.setStatus(rs.getInt(3));
				receipt.setChannelId(rs.getString(4));
				receipt.setChannelSub(rs.getString(5));
				receipt.setAccountId(rs.getInt(6));
				receipt.setRoleId(rs.getInt(7));
				receipt.setLevel(rs.getShort(8));
				receipt.setRoleName(rs.getString(9));
				receipt.setDeviceOS(rs.getString(10));
				receipt.setUserId(rs.getString(11));
				receipt.setGoodsId(rs.getInt(12));
				receipt.setGoodsName(rs.getString(13));
				receipt.setPrice(rs.getInt(14));
				receipt.setCount(rs.getInt(15));
				receipt.setOrderform(rs.getString(16));
				receipt.setProid(rs.getString(17));
				receipt.setGameId(rs.getInt(18));
				receipt.setServerId(rs.getInt(19));
				receipt.setDeviceImei(rs.getString(20));
				receipt.setReceiptMd5(rs.getString(21));
				receipt.setChargeType(rs.getString(22));
				receipts.add(receipt);
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
		return receipts;
	}

	public List<RoleStoreReceipt> loadRoleStoreReceipt(int appId, byte status) {
		StringBuilder sb = new StringBuilder();
		sb.append(START_ZONE).append(SYMBOL).append(appId).append(SYMBOL).append(storeReceiptEnd);
		String title = sb.toString();

		List<RoleStoreReceipt> receipts = new ArrayList<RoleStoreReceipt>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		RoleStoreReceipt receipt = null;
		try {

			stmt = conn.prepareStatement("SHOW TABLES LIKE '" + title + "'");
			rs = stmt.executeQuery();
			if (rs.next()) {
				stmt.close();
				sb.setLength(0);
				sb.append("select id, receipt, status, channelid, channelsub, accountid, roleid, rolelevel, rolename, deviceos, userid, goodsid, goodsname, price, count, orderform, proid,gameid,serverid,deviceimei from ").append(title).append(" where status<?");
				stmt = conn.prepareStatement(sb.toString());
				stmt.setByte(1, status);
				rs = stmt.executeQuery();
				while (rs.next()) {
					receipt = new RoleStoreReceipt();
					receipt.setId(rs.getInt(1));
					receipt.setReceipt(rs.getString(2));
					receipt.setStatus(rs.getInt(3));
					receipt.setChannelId(rs.getString(4));
					receipt.setChannelSub(rs.getString(5));
					receipt.setAccountId(rs.getInt(6));
					receipt.setRoleId(rs.getInt(7));
					receipt.setLevel(rs.getShort(8));
					receipt.setRoleName(rs.getString(9));
					receipt.setDeviceOS(rs.getString(10));
					receipt.setUserId(rs.getString(11));
					receipt.setGoodsId(rs.getInt(12));
					receipt.setGoodsName(rs.getString(13));
					receipt.setPrice(rs.getInt(14));
					receipt.setCount(rs.getInt(15));
					receipt.setOrderform(rs.getString(16));
					receipt.setProid(rs.getString(17));
					receipt.setGameId(rs.getInt(18));
					receipt.setServerId(rs.getInt(19));
					receipt.setDeviceImei(rs.getString(20));
					// receipt.setReceiptMd5(rs.getString(21));
					// receipt.setChargeType(rs.getString(22));
					receipts.add(receipt);
				}
				rs.close();
			}
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
		return receipts;
	}

}
