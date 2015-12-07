package mmo.common.module.clazz.event.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mmo.common.service.eventcenter.admin.AppConfigs;
import mmo.tools.db.DBConnection;

public class ServiceAppConfigs {
	private String selectAppConfigs = "select id,projectId,appId,appName,appMark,appIp,appPort1,appPort1Desc,appPort2,appPort2Desc,appPort3,appPort3Desc,callbackUrl,dbName,dbIp,dbPort,dbUser,dbPass,logLevel,logSwitch,configFile from app_configs ";

	public ServiceAppConfigs() {
	}

	public List<AppConfigs> getAppConfigsByName(String appName) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AppConfigs> list = new ArrayList<AppConfigs>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(selectAppConfigs + " where appName=?");
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, appName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				AppConfigs app = new AppConfigs();
				app.setId(rs.getInt(1));
				app.setProjectId(rs.getInt(2));
				app.setAppId(rs.getInt(3));
				app.setAppName(rs.getString(4));
				app.setAppMark(rs.getString(5));
				app.setAppIp(rs.getString(6));
				app.setAppPort1(rs.getInt(7));
				app.setAppPort1Desc(rs.getString(8));
				app.setAppPort2(rs.getInt(9));
				app.setAppPort2Desc(rs.getString(10));
				app.setAppPort3(rs.getInt(11));
				app.setAppPort3Desc(rs.getString(12));
				app.setCallbackUrl(rs.getString(13));
				app.setDbName(rs.getString(14));
				app.setDbIp(rs.getString(15));
				app.setDbPort(rs.getInt(16));
				app.setDbUser(rs.getString(17));
				app.setDbPass(rs.getString(18));
				app.setLogLevel(rs.getString(19));
				app.setLogSwitch(rs.getString(20));
				app.setConfigFile(rs.getString(21));
				list.add(app);
			}
			rs.close();
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

	public List<AppConfigs> getAppConfigs(int projectId, String appName) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AppConfigs> list = new ArrayList<AppConfigs>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(selectAppConfigs + " where projectId=? and appName=?");
			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, projectId);
			stmt.setString(2, appName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				AppConfigs app = new AppConfigs();
				app.setId(rs.getInt(1));
				app.setProjectId(rs.getInt(2));
				app.setAppId(rs.getInt(3));
				app.setAppName(rs.getString(4));
				app.setAppMark(rs.getString(5));
				app.setAppIp(rs.getString(6));
				app.setAppPort1(rs.getInt(7));
				app.setAppPort1Desc(rs.getString(8));
				app.setAppPort2(rs.getInt(9));
				app.setAppPort2Desc(rs.getString(10));
				app.setAppPort3(rs.getInt(11));
				app.setAppPort3Desc(rs.getString(12));
				app.setCallbackUrl(rs.getString(13));
				app.setDbName(rs.getString(14));
				app.setDbIp(rs.getString(15));
				app.setDbPort(rs.getInt(16));
				app.setDbUser(rs.getString(17));
				app.setDbPass(rs.getString(18));
				app.setLogLevel(rs.getString(19));
				app.setLogSwitch(rs.getString(20));
				app.setConfigFile(rs.getString(21));
				list.add(app);
			}
			rs.close();
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

	public List<AppConfigs> getAppConfigsByProjectId(int projectId) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AppConfigs> list = new ArrayList<AppConfigs>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(selectAppConfigs + " where projectId=?");
			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, projectId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				AppConfigs app = new AppConfigs();
				app.setId(rs.getInt(1));
				app.setProjectId(rs.getInt(2));
				app.setAppId(rs.getInt(3));
				app.setAppName(rs.getString(4));
				app.setAppMark(rs.getString(5));
				app.setAppIp(rs.getString(6));
				app.setAppPort1(rs.getInt(7));
				app.setAppPort1Desc(rs.getString(8));
				app.setAppPort2(rs.getInt(9));
				app.setAppPort2Desc(rs.getString(10));
				app.setAppPort3(rs.getInt(11));
				app.setAppPort3Desc(rs.getString(12));
				app.setCallbackUrl(rs.getString(13));
				app.setDbName(rs.getString(14));
				app.setDbIp(rs.getString(15));
				app.setDbPort(rs.getInt(16));
				app.setDbUser(rs.getString(17));
				app.setDbPass(rs.getString(18));
				app.setLogLevel(rs.getString(19));
				app.setLogSwitch(rs.getString(20));
				app.setConfigFile(rs.getString(21));
				list.add(app);
			}
			rs.close();
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

}
