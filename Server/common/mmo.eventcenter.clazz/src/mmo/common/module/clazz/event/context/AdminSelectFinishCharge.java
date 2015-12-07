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
import mmo.common.service.eventcenter.bean.ChargeRecord;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.db.DBConnectUtil;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class AdminSelectFinishCharge extends AEventContextHandle {
	private int pageCount = 50;

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			int targetPage = request.getIntParameter("page");
			String startTime = request.getParameter("start");
			String stopTime = request.getParameter("stop");
			String platform = request.getParameter("platform");
			String serverTag = request.getParameter("serverTag");
			String idfa = request.getParameter("idfa");
			String imei = request.getParameter("imei");
			String orderId = request.getParameter("orderId");
			String channelOrder = request.getParameter("channelOrder");
			String channelTag = request.getParameter("channelTag");
			String channelSub = request.getParameter("channelSub");
			String userId = request.getParameter("userId");
			String roleName = request.getParameter("roleName");

			ServiceAppConfigs service = new ServiceAppConfigs();
			List<AppConfigs> list = service.getAppConfigsByProjectId(10002);

			JSONObject json = new JSONObject();
			if (list.size() > 0) {
				AppConfigs app = list.get(0);

				Connection conn = DBConnectUtil.getConnect(app.getDbIp(),  app.getDbPort(),app.getDbName(), app.getDbUser(), app.getDbPass());
				int count = getCount(conn, app, platform, serverTag, idfa, imei, orderId, channelOrder, channelTag, channelSub, userId, roleName, startTime, stopTime);
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
				List<ChargeRecord> listData = getCharge(conn, app, platform, serverTag, idfa, imei, orderId, channelOrder, channelTag, channelSub, userId, roleName, start, pageCount, startTime, stopTime);
				json.put("totalPage", totalPage);
				json.put("page", targetPage);
				json.put("code", "ok");
				json.put("message", "ok");
				json.put("datas", listData);
			} else {
				json.put("code", "fail");
				json.put("message", "未配置充值分区");
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

	private String selectCharge = "select id,orderid,gameid,serverid,channelid,accountid,roleid,rolename,money,getmoney,ctype,state,atime,dtime,note,orderform,proxy,proxychannel,proxytime,userid,channelsub,rolelevel,goodsid,goodsname,goodscount,deviceos,price,deviceimei,device_serial,device_mac,idfa from ";

	public List<ChargeRecord> getCharge(Connection conn, AppConfigs app, String platform, String serverTag, String idfa, String imei, String orderId, String channelOrder, String channelTag, String channelSub, String userId, String roleName, int start, int count, String startTime, String stopTime) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ChargeRecord> list = new ArrayList<ChargeRecord>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(selectCharge).append(" zone_1_charge where");
			int index = 0;
			if (startTime != null && startTime.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" atime>='").append(startTime).append("' ");
			}
			if (stopTime != null && stopTime.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" atime<='").append(stopTime).append("' ");
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
				sb.append(" orderid='").append(orderId).append("' ");
			}
			if (channelOrder != null && channelOrder.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" orderform='").append(channelOrder).append("' ");
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
			if (index < 1) {
				return list;
			}
			sb.append(" limit ").append(start).append(", ").append(count);
			stmt = conn.prepareStatement(sb.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				ChargeRecord cr = new ChargeRecord();
				cr.setId(rs.getLong(1));
				cr.setOrderId(rs.getString(2));
				cr.setGameId(rs.getInt(3));
				cr.setServerId(rs.getInt(4));
				cr.setChannelId(rs.getString(5));
				cr.setAccountId(rs.getInt(6));
				cr.setRoleId(rs.getInt(7));
				cr.setRolename(rs.getString(8));
				cr.setMoney(rs.getInt(9));
				cr.setGetmoney(rs.getInt(10));
				cr.setCtype(rs.getByte(11));
				cr.setState(rs.getByte(12));
				Timestamp time = rs.getTimestamp(13);
				if (time != null) {
					cr.setAtime(time.getTime());
				}
				time = rs.getTimestamp(14);
				if (time != null) {
					cr.setDtime(time.getTime());
				}
				cr.setNote(rs.getString(15));
				cr.setOrderform(rs.getString(16));
				cr.setProxy(rs.getString(17));
				cr.setProxyChannel(rs.getString(18));
				time = rs.getTimestamp(19);
				if (time != null) {
					cr.setProxyTime(time.getTime());
				}
				cr.setUserid(rs.getString(20));
				cr.setChannelSub(rs.getString(21));
				cr.setRoleLevel(rs.getShort(22));
				cr.setGoodsId(rs.getInt(23));
				cr.setGoodsName(rs.getString(24));
				cr.setGoodsCount(rs.getInt(25));
				cr.setDeviceOS("");
				cr.setPrice(rs.getInt(27));
				cr.setDeviceImei(rs.getString(28));
				cr.setDeviceSerial(rs.getString(29));
				cr.setDeviceMac(rs.getString(30));
				cr.setIdfa(rs.getString(31));
				list.add(cr);
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

	public int getCount(Connection conn, AppConfigs app, String platform, String serverTag, String idfa, String imei, String orderId, String channelOrder, String channelTag, String channelSub, String userId, String roleName, String start, String stop) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select count(id) totalCount from ").append(" zone_1_charge where");
			int index = 0;
			if (start != null && start.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" atime>='").append(start).append("' ");
			}
			if (stop != null && stop.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" atime<='").append(stop).append("' ");
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
				sb.append(" orderid='").append(orderId).append("' ");
			}
			if (channelOrder != null && channelOrder.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" orderform='").append(channelOrder).append("' ");
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
