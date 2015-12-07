package mmo.common.module.clazz.event.context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mmo.common.service.eventcenter.admin.AppConfigs;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class AdminSelectAppConfig extends AEventContextHandle {
	private int pageCount = 50;

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			int targetPage = request.getIntParameter("page");
			int platform = request.getIntParameter("platform");
			String serverTag = request.getParameter("serverTag");

			JSONObject json = new JSONObject();

			int count = getCount(platform, serverTag);
			int totalPage = count / pageCount;
			if (count % pageCount > 0) {
				totalPage = totalPage + 1;
			}
			if (targetPage > totalPage) {
				targetPage = totalPage;
			}
			if (targetPage < 1) {
				targetPage = 1;
			}
			int start = (targetPage - 1) * pageCount;
			List<AppConfigs> listData = getCharge(platform, serverTag, start, pageCount);
			json.put("totalPage", totalPage);
			json.put("page", targetPage);
			json.put("code", "ok");
			json.put("message", "ok");
			json.put("datas", listData);

			return sendToClient(json.toString());
		} catch (Exception e) {
			LoggerError.error("处理GAME_EVENT异常", e);
		}
		JSONObject json = new JSONObject();
		json.put("code", "fail");
		json.put("message", "fail");
		return sendToClient(json.toString());
	}

	private String selectCharge = "select id,projectId,appId,appName,appMark,appIp,appPort1,appPort1Desc,appPort2,appPort2Desc,appPort3,appPort3Desc,callbackUrl,dbName,dbIp,dbPort,dbUser,dbPass,logLevel,logSwitch,configFile from app_configs ";

	public List<AppConfigs> getCharge(int projectId, String appName, int start, int count) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AppConfigs> list = new ArrayList<AppConfigs>();
		Connection conn = DBConnection.getConnection();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(selectCharge);
			int index = 0;
			if (projectId > 0) {
				if (index == 0) {
					sb.append(" where ");
				}
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" projectId=").append(projectId);
			}
			if (appName != null && appName.length() > 0) {
				if (index == 0) {
					sb.append(" where ");
				}
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" appName like '%").append(appName).append("%' ");
			}
			sb.append(" limit ").append(start).append(", ").append(count);
			stmt = conn.prepareStatement(sb.toString());
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

	public int getCount(int projectId, String appName) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = DBConnection.getConnection();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select count(id) totalCount from ").append(" app_configs ");
			int index = 0;
			if (projectId > 0) {
				if (index == 0) {
					sb.append(" where ");
				}
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" projectId=").append(projectId);
			}
			if (appName != null && appName.length() > 0) {
				if (index == 0) {
					sb.append(" where ");
				}
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" appName like '%").append(appName).append("%' ");
			}
			stmt = conn.prepareStatement(sb.toString());
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("totalCount");
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
		return 0;
	}

}
