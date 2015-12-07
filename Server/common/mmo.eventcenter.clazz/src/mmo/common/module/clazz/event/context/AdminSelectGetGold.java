package mmo.common.module.clazz.event.context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mmo.common.module.clazz.event.service.ServiceAppConfigs;
import mmo.common.service.eventcenter.admin.AppConfigs;
import mmo.common.service.eventcenter.bean.InstPlayerRecharge;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.db.DBConnectUtil;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class AdminSelectGetGold extends AEventContextHandle {
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
			int platform = request.getIntParameter("platform");
			String serverTag = request.getParameter("serverTag");
			String orderId = request.getParameter("orderId");
			String channelOrder = request.getParameter("channelOrder");
			String channelTag = request.getParameter("channelTag");
			String userId = request.getParameter("userId");
			String roleName = request.getParameter("roleName");

			ServiceAppConfigs service = new ServiceAppConfigs();
			List<AppConfigs> list = service.getAppConfigs(platform, serverTag);
			JSONObject json = new JSONObject();
			if (list.size() > 0) {
				AppConfigs app = list.get(0);

				Connection conn = DBConnectUtil.getConnect(app.getDbIp(),  app.getDbPort(),app.getDbName(), app.getDbUser(), app.getDbPass());
				int count = getCount(conn, app, orderId, channelOrder, channelTag, userId, roleName, startTime, stopTime);
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
				List<InstPlayerRecharge> listData = getCharge(conn, app, orderId, channelOrder, channelTag, userId, roleName, start, pageCount, startTime, stopTime);
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

	private String selectCharge = "select id,instPlayerId,openId,playerName,channel,thisrmb,thisamt,saveamt,payChannel,balance,genbalance,saveNum,source,orderId,serverId,accountId,orderform,roleLevel,goodsId,ctype,money,rechargeRecordId,operateDate,operateTime,version from ";

	public List<InstPlayerRecharge> getCharge(Connection conn, AppConfigs app, String orderId, String channelOrder, String channelTag, String userId, String roleName, int start, int count, String startTime, String stopTime) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<InstPlayerRecharge> list = new ArrayList<InstPlayerRecharge>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(selectCharge).append(" Inst_Player_Recharge where");
			int index = 0;
			if (startTime != null && startTime.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" operateTime>='").append(startTime).append("' ");
			}
			if (stopTime != null && stopTime.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" operateTime<='").append(stopTime).append("' ");
			}
			if (orderId != null && orderId.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" orderId='").append(orderId).append("' ");
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
				sb.append(" channel='").append(channelTag).append("' ");
			}
			if (userId != null && userId.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" openId='").append(userId).append("' ");
			}
			if (roleName != null && roleName.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" playerName like '%").append(roleName).append("%' ");
			}
			if (index < 1) {
				return list;
			}
			sb.append(" limit ").append(start).append(", ").append(count);
			stmt = conn.prepareStatement(sb.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				InstPlayerRecharge extend = new InstPlayerRecharge();
				extend.setId(rs.getInt(1));
				extend.setInstPlayerId(rs.getInt(2));
				extend.setOpenId(rs.getString(3));
				extend.setPlayerName(rs.getString(4));
				extend.setChannel(rs.getString(5));
				extend.setThisrmb(rs.getInt(6));
				extend.setThisamt(rs.getInt(7));
				extend.setSaveamt(rs.getInt(8));
				extend.setPayChannel(rs.getInt(9));
				extend.setBalance(rs.getInt(10));
				extend.setGenbalance(rs.getInt(11));
				extend.setSaveNum(rs.getInt(12));
				extend.setSource(rs.getString(13));
				extend.setOrderId(rs.getString(14));
				extend.setServerId(rs.getInt(15));
				extend.setAccountId(rs.getString(16));
				extend.setOrderform(rs.getString(17));
				extend.setRoleLevel(rs.getInt(18));
				extend.setGoodsId(rs.getInt(19));
				extend.setCtype(rs.getInt(20));
				extend.setMoney(rs.getInt(21));
				extend.setRechargeRecordId(rs.getString(22));
				extend.setOperateDate(rs.getString(23));
				extend.setOperateTime(rs.getString(24));
				extend.setVersion(rs.getInt(25));
				list.add(extend);
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

	public int getCount(Connection conn, AppConfigs app, String orderId, String channelOrder, String channelTag, String userId, String roleName, String start, String stop) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select count(id) totalCount from ").append(" Inst_Player_Recharge where");
			int index = 0;
			if (start != null && start.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" operateTime>='").append(start).append("' ");
			}
			if (stop != null && stop.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" operateTime<='").append(stop).append("' ");
			}
			if (orderId != null && orderId.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" orderId='").append(orderId).append("' ");
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
				sb.append(" channel='").append(channelTag).append("' ");
			}
			if (userId != null && userId.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" openId='").append(userId).append("' ");
			}
			if (roleName != null && roleName.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" playerName like '%").append(roleName).append("%' ");
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
