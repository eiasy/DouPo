package mmo.common.module.clazz.event.context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mmo.common.module.clazz.event.service.ServiceAppConfigs;
import mmo.common.service.eventcenter.admin.AppConfigs;
import mmo.common.service.eventcenter.bean.ChargeOrderform;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.db.DBConnectUtil;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class AdminSelectCreateOrder extends AEventContextHandle {
	private int pageCount = 50;

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			int targetPage = request.getIntParameter("page");
			String chargeServer = request.getParameter("chargeServer");
			String startTime = request.getParameter("start");
			String stopTime = request.getParameter("stop");
			String platform = request.getParameter("platform");
			String serverTag = request.getParameter("serverTag");
			String idfa = request.getParameter("idfa");
			String imei = request.getParameter("imei");
			String orderId = request.getParameter("orderId");
			String channelTag = request.getParameter("channelTag");
			String channelSub = request.getParameter("channelSub");
			String userId = request.getParameter("userId");
			String roleName = request.getParameter("roleName");
			String roleLevel = request.getParameter("roleLevel");

			ServiceAppConfigs service = new ServiceAppConfigs();
			List<AppConfigs> list = service.getAppConfigsByName(chargeServer);

			JSONObject json = new JSONObject();
			if (list.size() > 0) {
				AppConfigs app = list.get(0);

				Connection conn = DBConnectUtil.getConnect(app.getDbIp(), app.getDbPort(), app.getDbName(), app.getDbUser(), app.getDbPass());
				int count = getCount(conn, app, platform, serverTag, idfa, imei, orderId, channelTag, channelSub, userId, roleName, roleLevel, startTime, stopTime);
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
				List<ChargeOrderform> listOrder = getCharge(conn, app, platform, serverTag, idfa, imei, orderId, channelTag, channelSub, userId, roleName, roleLevel, start, pageCount, startTime, stopTime);
				json.put("totalPage", totalPage);
				json.put("page", targetPage);
				json.put("code", "ok");
				json.put("message", "ok");
				json.put("orders", listOrder);
			} else {
				json.put("code", "fail");
				json.put("message", "选择的分区不存在");
			}

			return sendToClient(json.toString());
		} catch (Exception e) {
			LoggerError.error("处理检索玩家信息异常", e);
		}
		JSONObject json = new JSONObject();
		json.put("code", "fail");
		json.put("message", "fail");
		return sendToClient(json.toString());
	}

	private String selectCharge = "select id,gameid,serverid,orderform,channelid,channelsub,accountid,roleid,rolename,rolelevel,deviceos,itemid,itemname,itemprice,itemcount,status,timecreate,timehandle,userid,deviceimei,device_serial,device_mac,idfa,handle_zone_id from ";

	public List<ChargeOrderform> getCharge(Connection conn, AppConfigs app, String platform, String serverTag, String idfa, String imei, String orderId, String channelTag, String channelSub, String userId, String roleName, String roleLevel, int start, int count, String startTime, String stopTime) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ChargeOrderform> list = new ArrayList<ChargeOrderform>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(selectCharge).append(" zone_").append(0).append("_order where");
			int index = 0;
			if (startTime != null && startTime.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" timecreate>='").append(startTime).append("' ");
			}
			if (stopTime != null && stopTime.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" timecreate<='").append(stopTime).append("' ");
			}
			if (platform != null && platform.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" gameid='").append(platform).append("' ");
			}
			if (serverTag != null && serverTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" serverid='").append(serverTag).append("' ");
			}
			if (idfa != null && idfa.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" idfa='").append(idfa).append("' ");
			}
			if (imei != null && imei.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" deviceimei='").append(imei).append("' ");
			}
			if (orderId != null && orderId.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" orderform='").append(orderId).append("' ");
			}
			if (channelTag != null && channelTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" channelid='").append(channelTag).append("' ");
			}
			if (channelSub != null && channelSub.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" channelSub='").append(channelSub).append("' ");
			}
			if (userId != null && userId.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" userid='").append(userId).append("' ");
			}
			if (roleName != null && roleName.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" rolename like '%").append(roleName).append("%' ");
			}
			if (roleLevel != null && roleLevel.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" rolelevel=").append(roleLevel);
			}
			if (index < 1) {
				return list;
			}
			sb.append(" limit ").append(start).append(", ").append(count);
			stmt = conn.prepareStatement(sb.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				ChargeOrderform of = new ChargeOrderform();
				of.setId(rs.getLong(1));
				of.setGameId(rs.getInt(2));
				of.setServerId(rs.getInt(3));
				of.setOrderform(rs.getString(4));
				of.setChannelId(rs.getString(5));
				of.setChannelSub(rs.getString(6));
				of.setAccountId(rs.getInt(7));
				of.setRoleId(rs.getInt(8));
				of.setRoleName(rs.getString(9));
				of.setRoleLevel(rs.getShort(10));
				of.setDeviceOS("");
				of.setItemId(rs.getInt(12));
				of.setItemName(rs.getString(13));
				of.setItemPrice(rs.getInt(14));
				of.setItemCount(rs.getInt(15));
				of.setStatus(rs.getByte(16));
				Timestamp time = rs.getTimestamp(17);
				if (time != null) {
					of.setTimeCreate(time.getTime());
				}
				time = rs.getTimestamp(18);
				if (time != null) {
					of.setTimeHandle(time.getTime());
				}
				of.setUserId(rs.getString(19));
				of.setDeviceImei(rs.getString(20));
				of.setDeviceSerial(rs.getString(21));
				of.setDeviceMac(rs.getString(22));
				of.setIdfa(rs.getString(23));
				of.setHandleZoneId(rs.getInt(24));
				list.add(of);
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
			conn = null;
		}
		return list;
	}

	public int getCount(Connection conn, AppConfigs app, String platform, String serverTag, String idfa, String imei, String orderId, String channelTag, String channelSub, String userId, String roleName, String roleLevel, String start, String stop) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select count(id) totalCount from ").append(" zone_").append(0).append("_order where");
			int index = 0;
			if (start != null && start.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" timecreate>='").append(start).append("' ");
			}
			if (stop != null && stop.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" timecreate<='").append(stop).append("' ");
			}
			if (platform != null && platform.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" gameid='").append(platform).append("' ");
			}
			if (serverTag != null && serverTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" serverid='").append(serverTag).append("' ");
			}
			if (idfa != null && idfa.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" idfa='").append(idfa).append("' ");
			}
			if (imei != null && imei.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" deviceimei='").append(imei).append("' ");
			}
			if (orderId != null && orderId.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" orderform='").append(orderId).append("' ");
			}
			if (channelTag != null && channelTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" channelid='").append(channelTag).append("' ");
			}
			if (channelSub != null && channelSub.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" channelsub='").append(channelSub).append("' ");
			}
			if (userId != null && userId.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" userid='").append(userId).append("' ");
			}
			if (roleName != null && roleName.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" rolename like '%").append(roleName).append("%' ");
			}
			if (roleLevel != null && roleLevel.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" rolelevel=").append(roleLevel);
			}
			if (index < 1) {
				return 0;
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
			conn = null;
		}
		return 0;
	}

}
