package mmo.common.module.service.charge.service.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.role.QQChargeRecord;
import mmo.common.module.service.charge.service.TableManager;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class QQChargeService extends TableManager {

	public QQChargeService() {
	}

	public boolean addQQCharge(QQChargeRecord cr) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sqlCheckQQExist);
			stmt.setString(1, cr.getOrderId());
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
			stmt = conn.prepareStatement(sqlAddQQ, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, cr.getResultCode());
			stmt.setInt(2, cr.getPayChannel());
			stmt.setInt(3, cr.getPayState());
			stmt.setInt(4, cr.getProvideState());
			stmt.setInt(5, cr.getSaveNum());
			stmt.setString(6, cr.getExtendInfo());
			stmt.setString(7, cr.getOrderId());
			stmt.setString(8, cr.getRemoteIp());
			stmt.setString(9, cr.getOpenid());
			stmt.setString(10, cr.getOpenkey());
			stmt.setString(11, cr.getPayToken());
			stmt.setString(12, cr.getPf());
			stmt.setString(13, cr.getPfkey());
			stmt.setString(14, cr.getActionType());
			stmt.setString(15, cr.getCdata());
			stmt.setTimestamp(16, new Timestamp(cr.getAddTime()));
			stmt.setTimestamp(17, new Timestamp(cr.getUpdateTime()));
			stmt.setString(18, cr.getImei());
			stmt.setString(19, cr.getIdfa());
			stmt.setInt(20, cr.getStatus());
			stmt.setInt(21, cr.getCheckCount());
			stmt.setString(22, cr.getChargeType());
			stmt.setInt(23, cr.getAppId());
			stmt.setInt(24, cr.getHandleAppId());
			stmt.setInt(25, cr.getZoneId());
			stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				Long key = (Long) rs.getObject(1);
				cr.setId(key);
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

	public void updateQQCharge(QQChargeRecord cr) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlUpdateQQ);
			stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(2, cr.getStatus());
			stmt.setInt(3, cr.getCheckCount());
			stmt.setInt(4, cr.getHandleAppId());
			stmt.setString(5, cr.getChannelOrderId());
			stmt.setString(6, cr.getChargeType());
			stmt.setString(7, cr.getCdata());
			stmt.setLong(8, cr.getId());
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

	public List<QQChargeRecord> loadQQChargeRecord(int appId, int status) {
		List<QQChargeRecord> receipts = new ArrayList<QQChargeRecord>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		QQChargeRecord cr = null;
		try {
			if (appId > 0) {
				stmt = conn.prepareStatement(sqlLoadQQ);
				stmt.setInt(1, appId);
				stmt.setInt(2, status);
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("select id,resultCode,payChannel,payState,provideState,saveNum,extendInfo,orderId,remoteIp,openid,openkey,payToken,pf,pfkey,actionType,cdata,addTime,updateTime,imei,idfa,status,checkCount,chargeType,appId,handleAppId,zoneId,channelOrderId from ").append(qqTitle).append(" where status<?");
				stmt = conn.prepareStatement(sb.toString());
				stmt.setInt(1, status);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				cr = new QQChargeRecord();
				cr.setId(rs.getLong(1));
				cr.setResultCode(rs.getInt(2));
				cr.setPayChannel(rs.getInt(3));
				cr.setPayState(rs.getInt(4));
				cr.setProvideState(rs.getInt(5));
				cr.setSaveNum(rs.getInt(6));
				cr.setExtendInfo(rs.getString(7));
				cr.setOrderId(rs.getString(8));
				cr.setRemoteIp(rs.getString(9));
				cr.setOpenid(rs.getString(10));
				cr.setOpenkey(rs.getString(11));
				cr.setPayToken(rs.getString(12));
				cr.setPf(rs.getString(13));
				cr.setPfkey(rs.getString(14));
				cr.setActionType(rs.getString(15));
				cr.setCdata(rs.getString(16));
				cr.setAddTime(rs.getTimestamp(17).getTime());
				cr.setUpdateTime(rs.getTimestamp(18).getTime());
				cr.setImei(rs.getString(19));
				cr.setIdfa(rs.getString(20));
				cr.setStatus(rs.getInt(21));
				cr.setCheckCount(rs.getInt(22));
				cr.setChargeType(rs.getString(23));
				cr.setAppId(rs.getInt(24));
				cr.setHandleAppId(rs.getInt(25));
				cr.setZoneId(rs.getInt(26));
				cr.setChannelOrderId(rs.getString(27));
				receipts.add(cr);
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

}
