package mmo.common.module.clazz.event.context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mmo.common.module.clazz.event.service.ServiceAppConfigs;
import mmo.common.service.eventcenter.admin.AppConfigs;
import mmo.common.service.eventcenter.bean.InstPlayer;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.db.DBConnectUtil;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class AdminSelectPlayer extends AEventContextHandle {
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
			String userId = request.getParameter("userId");
			String roleName = request.getParameter("roleName");

			ServiceAppConfigs service = new ServiceAppConfigs();
			List<AppConfigs> list = service.getAppConfigs(platform, serverTag);
			JSONObject json = new JSONObject();
			if (list.size() > 0) {
				AppConfigs app = list.get(0);
				Connection conn = DBConnectUtil.getConnect(app.getDbIp(), app.getDbPort(), app.getDbName(), app.getDbUser(), app.getDbPass());
				int count = getCount(conn, app, userId, roleName);
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
				List<InstPlayer> listData = getCharge(conn, app, userId, roleName, start, pageCount);
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

	private String selectCharge = "select id,openId,name,level,gold,copper,exp,energy,maxEnergy,vigor,maxVigor,cardNum,maxCardNum,guidStep,chapterId,barrierId,fireGainRuleId,instPlayerFireId,vipLevel,vigour,culture,lastEnergyRecoverTime,lastVigorRecoverTime,barrierNum,buyEnergyNum,buyVigorNum,lastBuyEnergyTime,lastBuyVigorTime,guipedBarrier,washTime,dailyTashTime,headerCardId,vipIds,pullBlack,isGetFirstRechargeGift,beautyCardTime,serverId,channel,version,insertTime,updateTime from ";

	public List<InstPlayer> getCharge(Connection conn, AppConfigs app, String userId, String roleName, int start, int count) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<InstPlayer> list = new ArrayList<InstPlayer>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(selectCharge).append(" Inst_Player where");
			int index = 0;
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
				sb.append(" name like '%").append(roleName).append("%' ");
			}
			if (index < 1) {
				return list;
			}
			sb.append(" limit ").append(start).append(", ").append(count);
			stmt = conn.prepareStatement(sb.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				InstPlayer extend = new InstPlayer();
				extend.setId(rs.getInt(1));
				extend.setOpenId(rs.getString(2));
				extend.setName(rs.getString(3));
				extend.setLevel(rs.getInt(4));
				extend.setGold(rs.getInt(5));
				extend.setCopper(rs.getString(6));
				extend.setExp(rs.getInt(7));
				extend.setEnergy(rs.getInt(8));
				extend.setMaxEnergy(rs.getInt(9));
				extend.setVigor(rs.getInt(10));
				extend.setMaxVigor(rs.getInt(11));
				extend.setCardNum(rs.getInt(12));
				extend.setMaxCardNum(rs.getInt(13));
				extend.setGuidStep(rs.getString(14));
				extend.setChapterId(rs.getInt(15));
				extend.setBarrierId(rs.getInt(16));
				extend.setFireGainRuleId(rs.getInt(17));
				extend.setInstPlayerFireId(rs.getInt(18));
				extend.setVipLevel(rs.getInt(19));
				extend.setVigour(rs.getInt(20));
				extend.setCulture(rs.getInt(21));
				extend.setLastEnergyRecoverTime(rs.getString(22));
				extend.setLastVigorRecoverTime(rs.getString(23));
				extend.setBarrierNum(rs.getInt(24));
				extend.setBuyEnergyNum(rs.getInt(25));
				extend.setBuyVigorNum(rs.getInt(26));
				extend.setLastBuyEnergyTime(rs.getString(27));
				extend.setLastBuyVigorTime(rs.getString(28));
				extend.setGuipedBarrier(rs.getString(29));
				extend.setWashTime(rs.getString(30));
				extend.setDailyTashTime(rs.getString(31));
				extend.setHeaderCardId(rs.getInt(32));
				extend.setVipIds(rs.getString(33));
				extend.setPullBlack(rs.getInt(34));
				extend.setIsGetFirstRechargeGift(rs.getInt(35));
				extend.setBeautyCardTime(rs.getString(36));
				extend.setServerId(rs.getInt(37));
				extend.setChannel(rs.getString(38));
				extend.setVersion(rs.getInt(39));
				extend.setInsertTime(rs.getString(40));
				extend.setUpdateTime(rs.getString(41));
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

	public int getCount(Connection conn, AppConfigs app, String userId, String roleName) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select count(id) totalCount from ").append(" Inst_Player where");
			int index = 0;
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
				sb.append(" name like '%").append(roleName).append("%' ");
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
