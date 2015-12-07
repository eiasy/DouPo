package mmo.common.module.clazz.event.context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class AdminOperateAppConfig extends AEventContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			int platform = request.getIntParameter("platform");
			int appId = request.getIntParameter("appId");
			String appName = request.getParameter("appName");
			String dbName = request.getParameter("dbName");
			String dbIp = request.getParameter("dbIp");
			int dbPort = request.getIntParameter("dbPort");
			String dbUser = request.getParameter("dbUser");
			String dbPassword = request.getParameter("dbPassword");
			String operate = request.getParameter("operate");
			JSONObject json = new JSONObject();
			if ("add".equals(operate)) {
				if (add(platform, appId, appName, dbName, dbIp, dbPort, dbUser, dbPassword)) {
					json.put("code", "ok");
					json.put("message", "添加成功");
				} else {
					json.put("code", "fail");
					json.put("message", "添加失败");
				}
			} else if ("update".equals(operate)) {
				if (update(request.getIntParameter("id"), platform, appId, appName, dbName, dbIp, dbPort, dbUser, dbPassword)) {
					json.put("code", "ok");
					json.put("message", "更新成功");
				} else {
					json.put("code", "fail");
					json.put("message", "添加失败");
				}
			} else {
				json.put("code", "fail");
				json.put("message", "无效的操作类型");
			}

			return sendToClient(json.toString());
		} catch (Exception e) {
			LoggerError.error("处理GAME_EVENT异常", e);
		}
		JSONObject json = new JSONObject();
		json.put("code", "fail");
		json.put("message", "fail");
		return sendToClient(json.toString());
	}

	private String sqlAdd = "insert into app_configs (projectId,appId,appName,dbName,dbIp,dbPort,dbUser,dbPass) values (?,?,?,?,?,?,?,?) ";

	public boolean add(int projectId, int appId, String appName, String dbName, String dbIp, int dbPort, String dbUser, String dbPassword) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;

		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlAdd);
			stmt.setInt(1, projectId);
			stmt.setInt(2, appId);
			stmt.setString(3, appName);
			stmt.setString(4, dbName);
			stmt.setString(5, dbIp);
			stmt.setInt(6, dbPort);
			stmt.setString(7, dbUser);
			stmt.setString(8, dbPassword);
			int count = stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			return count > 0;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("add01", e);
		} catch (Exception e) {
			LoggerError.error("add02", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("add03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}

	private String sqlUpdate = "update app_configs set projectId=?,appId=?,appName=?,dbName=?,dbIp=?,dbPort=?,dbUser=?,dbPass=? where id=? ";

	public boolean update(int id, int projectId, int appId, String appName, String dbName, String dbIp, int dbPort, String dbUser, String dbPassword) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;

		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlUpdate);
			stmt.setInt(1, projectId);
			stmt.setInt(2, appId);
			stmt.setString(3, appName);
			stmt.setString(4, dbName);
			stmt.setString(5, dbIp);
			stmt.setInt(6, dbPort);
			stmt.setString(7, dbUser);
			stmt.setString(8, dbPassword);
			stmt.setInt(9, id);
			int count = stmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(autoCommit);
			return count > 0;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("update01", e);
		} catch (Exception e) {
			LoggerError.error("udate02", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LoggerError.error("update03", e);
			}
			DBConnection.freeConnection(conn);
		}
		return false;
	}
}
