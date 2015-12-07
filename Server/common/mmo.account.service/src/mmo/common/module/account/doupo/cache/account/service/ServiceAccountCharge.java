package mmo.common.module.account.doupo.cache.account.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mmo.common.module.account.doupo.cache.account.charge.AccountCharge;
import mmo.tools.db.DBConnection;

public class ServiceAccountCharge {
	private final static ServiceAccountCharge instance = new ServiceAccountCharge();

	public final static ServiceAccountCharge getInstance() {
		return instance;
	}

	private ServiceAccountCharge() {

	}

	public List<AccountCharge> loadAccountCharges(byte status) {
		Connection conn = DBConnection.getConnection();
		List<AccountCharge> list = new ArrayList<AccountCharge>();
		PreparedStatement stmt = null;
		AccountCharge ac = null;
		try {
			stmt = conn.prepareStatement("select id, accountid, rmb, counter, gameid, serverid,chargetime from account_award where status=?");
			stmt.setByte(1, status);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ac = new AccountCharge();
				ac.setId(rs.getInt(1));
				ac.setAccountId(rs.getInt(2));
				ac.setRmb(rs.getInt(3));
				ac.setCounter(rs.getInt(4));
				ac.setGameId(rs.getInt(5));
				ac.setServerId(rs.getInt(6));
				ac.setTimeCharge(rs.getTimestamp(7).getTime());
				ac.setStatus(status);
				list.add(ac);
			}
			rs.close();
			return list;
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
		return list;
	}

	public void updateAccountCharge(AccountCharge ac) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement("update account_award set status=?,roleid=?,roleserverid=?, awardtime=? where id=?");
			stmt.setByte(1, ac.getStatus());
			stmt.setInt(2, ac.getRoleId());
			stmt.setInt(3, ac.getRoleServerId());
			stmt.setTimestamp(4, new Timestamp(ac.getTimeAward()));
			stmt.setInt(5, ac.getId());
			stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
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
}
