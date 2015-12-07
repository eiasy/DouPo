package mmo.common.module.service.charge.service.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mmo.common.bean.advertise.IdfaActive;
import mmo.common.bean.advertise.IdfaEvent;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;

public class IdfaService {
	private static String addIdfaActive     = "insert into idfa_active (id,app_id,channel_tag,idfa,device_mac,device_imei,ip,add_time,app_start,app_start_time,role_create,role_create_time,role_create_channel,role_user_id,descript,state,device_udid,device_serial,device_ua,device_os,device_os_version,channel_sub,media,callback) "
	                                                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String updateIdfaActive  = "update idfa_active set app_start=?,app_start_time=?,role_create=?,role_create_time=?,role_create_channel=?,role_user_id=?,descript=?,state=? where id=?";
	private static String loadAllIdfaActive = "select id,app_id,channel_tag,idfa,device_mac,device_imei,ip,add_time,app_start,app_start_time,role_create,role_create_time,role_create_channel,role_user_id,descript,state,device_udid,callback from idfa_active";
	private static String addIdfaEvent      = "insert into idfa_event (event_type,event_tag,app_id,channel_tag,idfa,device_mac,device_imei,ip,time_add,device_udid,device_serial,device_ua,device_os,device_os_version,phone_code,descript,channel_sub,media,callback,key_1,value_1,key_2,value_2,key_3,value_3,key_4,value_4,key_5,value_5) "
	                                                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public IdfaService() {
	}

	public void addIdfaActive(IdfaActive idfa) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(addIdfaActive);
			stmt.setLong(1, idfa.getId());
			stmt.setString(2, idfa.getAppId());
			stmt.setString(3, idfa.getChannelTag());
			stmt.setString(4, idfa.getIdfa());
			stmt.setString(5, idfa.getDeviceMac());
			stmt.setString(6, idfa.getDeviceImei());
			stmt.setString(7, idfa.getIp());
			stmt.setTimestamp(8, new Timestamp(idfa.getAddTime()));
			stmt.setInt(9, idfa.getAppStart());
			stmt.setTimestamp(10, new Timestamp(idfa.getAddTime()));
			stmt.setInt(11, idfa.getRoleCreate());
			stmt.setTimestamp(12, new Timestamp(idfa.getAddTime()));
			stmt.setString(13, idfa.getRoleCreateChannel());
			stmt.setString(14, idfa.getRoleUserId());
			stmt.setString(15, idfa.getDesc());
			stmt.setByte(16, idfa.getStatus());
			stmt.setString(17, idfa.getDeviceUdid());
			stmt.setString(18, idfa.getDeviceSerial());
			stmt.setString(19, idfa.getDeviceUA());
			stmt.setString(20, idfa.getDeviceOS());
			stmt.setString(21, idfa.getDeviceOsVersion());
			stmt.setString(22, idfa.getChannelSub());
			stmt.setString(23, idfa.getMedia());
			stmt.setString(24, idfa.getCallback());
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

	public void addIdfaEvent(IdfaEvent idfa) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(addIdfaEvent);
			stmt.setString(1, idfa.getEventType());
			stmt.setString(2, idfa.getEventTag());
			stmt.setString(3, idfa.getAppId());
			stmt.setString(4, idfa.getChannelTag());
			stmt.setString(5, idfa.getIdfa());
			stmt.setString(6, idfa.getDeviceMac());
			stmt.setString(7, idfa.getDeviceImei());
			stmt.setString(8, idfa.getIp());
			stmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			stmt.setString(10, idfa.getDeviceUdid());
			stmt.setString(11, idfa.getDeviceSerial());
			stmt.setString(12, idfa.getDeviceUA());
			stmt.setString(13, idfa.getDeviceOS());
			stmt.setString(14, idfa.getDeviceOsVersion());
			stmt.setString(15, idfa.getPhoneCode());
			stmt.setString(16, idfa.getDesc());
			stmt.setString(17, idfa.getChannelSub());
			stmt.setString(18, idfa.getMedia());
			stmt.setString(19, idfa.getCallback());
			Map<String, String> map = idfa.getCustoms();
			Set<String> keys = map.keySet();
			int pi = 20;
			int index = 0;
			for (String k : keys) {
				stmt.setString(pi++, k);
				stmt.setString(pi++, map.get(k));
				index++;
			}
			for (; index < 5; index++) {
				stmt.setString(pi++, "");
				stmt.setString(pi++, "");
			}
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

	public void updateIdfaActive(IdfaActive idfa) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(updateIdfaActive);
			stmt.setInt(1, idfa.getAppStart());
			stmt.setTimestamp(2, new Timestamp(idfa.getAddTime()));
			stmt.setInt(3, idfa.getRoleCreate());
			stmt.setTimestamp(4, new Timestamp(idfa.getAddTime()));
			stmt.setString(5, idfa.getRoleCreateChannel());
			stmt.setString(6, idfa.getRoleUserId());
			stmt.setString(7, idfa.getDesc());
			stmt.setByte(8, idfa.getStatus());
			stmt.setLong(9, idfa.getId());
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
			LoggerError.error("updateIdfaActive异常", e);
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

	public List<IdfaActive> loadAllIdfaActive() {
		List<IdfaActive> idfas = new ArrayList<IdfaActive>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		IdfaActive idfa = null;
		try {
			stmt = conn.prepareStatement(loadAllIdfaActive);
			rs = stmt.executeQuery();
			while (rs.next()) {
				idfa = new IdfaActive();
				idfa.setId(rs.getLong(1));
				idfa.setAppId(rs.getString(2));
				idfa.setChannelTag(rs.getString(3));
				idfa.setIdfa(rs.getString(4));
				idfa.setDeviceMac(rs.getString(5));
				idfa.setDeviceImei(rs.getString(6));
				idfa.setIp(rs.getString(7));
				idfa.setAddTime(rs.getTimestamp(8).getTime());
				idfa.setAppStart(rs.getInt(9));
				idfa.setAddTime(rs.getTimestamp(10).getTime());
				idfa.setRoleCreate(rs.getInt(11));
				idfa.setAddTime(rs.getTimestamp(12).getTime());
				idfa.setRoleCreateChannel(rs.getString(13));
				idfa.setRoleUserId(rs.getString(14));
				idfa.setDesc(rs.getString(15));
				idfa.setStatus(rs.getByte(16));
				idfa.setDeviceUdid(rs.getString(17));
				idfa.setCallback(rs.getString(18));
				idfas.add(idfa);
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
		return idfas;
	}

}
