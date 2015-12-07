package mmo.common.module.account.doupo.cache.account.service;

import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mmo.common.module.account.doupo.cache.account.ActiveCodeManager;
import mmo.common.module.account.doupo.cache.account.bean.AccountDetail;
import mmo.common.module.account.doupo.cache.account.bean.ActiveCode;
import mmo.common.module.account.doupo.cache.account.bean.UserAccount;
import mmo.common.module.account.doupo.cache.account.cache.AccountCache;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;
import mmo.tools.util.DateUtil;

public class ServiceAccount {
	private final static ServiceAccount instance = new ServiceAccount();

	public final static ServiceAccount getInstance() {
		return instance;
	}

	private static final String UPDATE_USERPASSWORD = "update accounts set password=?, pwdstate=? where id=?";
	private static final String UPDATE_PWD_STATE    = "update accounts set pwdstate=? where id=?";
	private static final String UPDATE_PHONE        = "update accounts set phonecode=? where id=?";
	private static final String UPDATE_ACTIVE_CODE  = "update accounts set active_code=? where id=?";
	private final static String addUserSql          = "insert into accounts (id,userid,reuserid,username,password,sex, phonecode, email, productid, channelid, belongto, permit, "
	                                                        + "rtime,ltime, registerip, state, registerfrom, money, freeze ,deviceos, osversion, deviceserial,deviceudid, devicemac,deviceua, screenwidth,screenheight,codeversion,pwdstate,logincount,channelsub,active_code)"
	                                                        + " values(?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?,?)";

	private static final String LOAD_ACCOUNTS       = "select id, userid, reuserid,username,password,sex,phonecode,email,channelid, belongto,permit,rtime,state,money,freeze,deviceserial,pwdstate,freezeday,logincount, channelsub,active_code from accounts";
	private static final String LOAD_ACTIVE_CODE    = "select id, ecode, stoptime from product_exchange";
	private static final String GET_FREEZE_ACCOUNT  = "select id,userid,reuserid,username,phonecode,email,productid,channelid, rtime,freeze,state,freezeday from accounts where freeze>?";
	private static final String FREEZE_ACCOUNT      = "update accounts set freeze=?,freezeday=? where id=?";
	private static final String UPDATE_LTIME        = "update accounts set ltime=?,logincount=logincount+1 where id=?";

	private ServiceAccount() {

	}

	public void loadAccountToCache() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		UserAccount account = null;
		try {
			stmt = conn.prepareStatement(LOAD_ACCOUNTS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				account = new UserAccount();
				account.setAccountId(rs.getInt(1));
				account.setUserid(rs.getString(2));
				account.setReuserid(rs.getString(3));
				account.setUsername(rs.getString(4));
				account.setPassword(rs.getString(5));
				account.setSex(rs.getByte(6));
				account.setPhone(rs.getString(7));
				account.setEmail(rs.getString(8));
				account.setChannelId(rs.getString(9));
				account.setBelongto(rs.getInt(10));
				account.setPermit(rs.getString(11));
				account.setTimeRegister(rs.getTimestamp(12).getTime());
				account.setState(rs.getByte(13));
				account.setMoney(rs.getInt(14));
				account.setTimeFreeze(rs.getLong(15));
				account.setDeviceImei(rs.getString(16));
				account.setPwdState(rs.getByte(17));
				account.setFreezeDay(rs.getLong(18));
				account.setLoginCount(rs.getInt(19));
				account.setChannelSub(rs.getString(20));
				account.setActiveCode(rs.getString(21));
				AccountCache.getInstance().initAccount(account);
			}
			rs.close();
		} catch (Exception e) {
			LoggerError.error("AccountToCache时抛出异常" + account, e);
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

	public void loadActiveCode() {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(LOAD_ACTIVE_CODE);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ActiveCodeManager.getInstance().initActiveCode(new ActiveCode(rs.getInt(1), rs.getString(2), rs.getTimestamp(3).getTime()));
			}
			rs.close();
		} catch (Exception e) {
			LoggerError.error("loadActiveCode时抛出异常", e);
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

	/**
	 * 添加帐号
	 * 
	 * @param user
	 * @return 结果编号（0正常，1帐号重复）
	 */
	public int register(UserAccount user, String deviceOS, String osVersion, String deviceUdid, String deviceMac, String deviceUA, String phoneType,
	        int screenHeight, int screenWidth, String codeVersion, int productId, String registerIp, byte registerFrom) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			user.setTimeFreeze(0);
			stmt = conn.prepareStatement(addUserSql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, user.getAccountId());
			stmt.setString(2, user.getUserid());
			stmt.setString(3, user.getReuserid());
			stmt.setString(4, user.getUsername());
			stmt.setString(5, user.getPassword());
			stmt.setByte(6, user.getSex());
			stmt.setString(7, user.getPhone());
			stmt.setString(8, user.getEmail());
			stmt.setInt(9, productId);
			stmt.setString(10, user.getChannelId());
			stmt.setInt(11, user.getBelongto() & 0xFFFFFFF0);
			stmt.setString(12, user.getPermit());
			stmt.setTimestamp(13, new Timestamp(user.getTimeRegister()));
			stmt.setString(14, DateUtil.getJtime());
			stmt.setString(15, registerIp);
			stmt.setByte(16, user.getState());
			stmt.setByte(17, registerFrom);
			stmt.setInt(18, user.getMoney());
			stmt.setLong(19, user.getTimeFreeze());
			stmt.setString(20, deviceOS);
			stmt.setString(21, osVersion);
			stmt.setString(22, user.getDeviceImei());
			stmt.setString(23, deviceUdid);
			stmt.setString(24, deviceMac);
			stmt.setString(25, deviceUA);
			stmt.setInt(26, screenWidth);
			stmt.setInt(27, screenHeight);
			stmt.setString(28, codeVersion);
			stmt.setByte(29, user.getPwdState());
			stmt.setInt(30, user.getLoginCount());
			stmt.setString(31, user.getChannelSub());
			stmt.setString(32, user.getActiveCode());
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
			LoggerError.error("register01", e);
		} catch (Exception e) {
			LoggerError.error("register02", e);
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
				LoggerError.error("register03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return -1;
	}

	/**
	 * 获取当天同一个IP地址注册的帐号数量
	 * 
	 * @param socket
	 * @return
	 */
	public int todaySameIp(SocketAddress socket) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			String IP = (socket + "").substring(1);
			String sql = "select count(id) ipCount from accounts where registerip=? and curdate()=Date_FORMAT(rtime,'%Y-%m-%d')";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, IP);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("ipCount");
			}
			return 0;
		} catch (Exception e) {
			LoggerError.error("todaySameIp", e);
			return 0;
		} finally {
			DBConnection.freeConnection(conn);
		}
	}

	public boolean updateUserPassword(UserAccount ua) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(UPDATE_USERPASSWORD);
			stmt.setString(1, ua.getPassword());
			stmt.setByte(2, ua.getPwdState());
			stmt.setInt(3, ua.getAccountId());
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
			LoggerError.error("updateUserPassword-01", e);
		} catch (Exception e) {
			LoggerError.error("updateUserPassword-02", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("updateUserPassword-03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}

	public boolean updatePasswordState(UserAccount ua) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(UPDATE_PWD_STATE);
			stmt.setByte(1, ua.getPwdState());
			stmt.setInt(2, ua.getAccountId());
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
			LoggerError.error("updatePasswordState-01", e);
		} catch (Exception e) {
			LoggerError.error("updatePasswordState-02", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("updatePasswordState-03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}

	public boolean updatePhone(UserAccount ua) {
		if (ua == null) {
			return false;
		}
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(UPDATE_PHONE);
			stmt.setString(1, ua.getPhone());
			stmt.setInt(2, ua.getAccountId());
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
			LoggerError.error("updatePhone-01", e);
		} catch (Exception e) {
			LoggerError.error("updatePhone-02", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("updatePhone-03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}

	public boolean updateActiveCode(UserAccount ua) {
		if (ua == null) {
			return false;
		}
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(UPDATE_ACTIVE_CODE);
			stmt.setString(1, ua.getActiveCode());
			stmt.setInt(2, ua.getAccountId());
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
			LoggerError.error("updatePhone-01", e);
		} catch (Exception e) {
			LoggerError.error("updatePhone-02", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("updatePhone-03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}

	private static final String GET_USERS_1 = "select id,userid,reuserid,username,phonecode,email,productid,channelid, rtime,freeze,state,freezeday,deviceserial from accounts where userid like '%";
	private static final String GET_USERS_2 = "%' or username like '%";
	private static final String GET_USERS_3 = "%' or reuserid like '%";
	private static final String GET_USERS_4 = "%'";

	public List<AccountDetail> getUsers(String userid) {
		List<AccountDetail> users = new ArrayList<AccountDetail>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(GET_USERS_1).append(userid).append(GET_USERS_2).append(userid).append(GET_USERS_3).append(userid).append(GET_USERS_4);
			stmt = conn.prepareStatement(sb.toString());
			ResultSet rs = stmt.executeQuery();
			AccountDetail user = null;
			while (rs.next()) {
				user = new AccountDetail();
				user.setAccountId(rs.getInt(1));
				user.setUserid(rs.getString(2));
				user.setReuserid(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPhone(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setProductId(rs.getInt(7));
				user.setChannelId(rs.getString(8));
				user.setTimeRegister(rs.getTimestamp(9).getTime());
				user.setTimeFreeze(rs.getLong(10));
				user.setState(rs.getByte(11));
				user.setFreezeDay(rs.getLong(12));
				user.setDeviceImei(rs.getString(13));
				users.add(user);
			}
			rs.close();
			return users;
		} catch (Exception e) {
			LoggerError.error("加载账号列表时抛出异常", e);
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
		return users;
	}

	public List<AccountDetail> getFreezeAccount() {
		List<AccountDetail> users = new ArrayList<AccountDetail>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_FREEZE_ACCOUNT);
			stmt.setLong(1, System.currentTimeMillis());
			ResultSet rs = stmt.executeQuery();
			AccountDetail user = null;
			while (rs.next()) {
				user = new AccountDetail();
				user.setAccountId(rs.getInt(1));
				user.setUserid(rs.getString(2));
				user.setReuserid(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPhone(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setProductId(rs.getInt(7));
				user.setChannelId(rs.getString(8));
				user.setTimeRegister(rs.getTimestamp(9).getTime());
				user.setTimeFreeze(rs.getLong(10));
				user.setState(rs.getByte(11));
				user.setFreezeDay(rs.getLong(12));
				users.add(user);
			}
			rs.close();
			return users;
		} catch (Exception e) {
			LoggerError.error("加载冻结账号列表时抛出异常", e);
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
		return users;
	}

	public boolean freezeAccount(UserAccount ua) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(FREEZE_ACCOUNT);
			stmt.setLong(1, ua.getTimeFreeze());
			stmt.setLong(2, ua.getFreezeDay());
			stmt.setInt(3, ua.getAccountId());
			int result = stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			return result > 0;
		} catch (Exception e) {
			LoggerError.error("freezeAccount-01", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("freezeAccount-02", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}

	public boolean updateLastTime(UserAccount ua) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(UPDATE_LTIME);
			stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(2, ua.getAccountId());
			int result = stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			return result > 0;
		} catch (Exception e) {
			LoggerError.error("updateLastTime-01", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("updateLastTime-02", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}
}
