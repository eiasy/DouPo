package mmo.common.service.eventcenter.service.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.bi.EventDefault;
import mmo.tools.db.DBConnection;

public class AdminDefaultService {
	private static String selectCharge = "select id,eventSource,eventTag,appTag,platform,serverTag,channelTag,channelSub,accountId,userId,roleId,roleName,roleLevel,vipLevel,value1string,value2string,value3string,value4string,value5string,value6string,value7string,value8string,key1int,value1int,key2int,value2int,key3int,value3int,key1long,value1long,key1double,value1double,timeAdd from event_default where ";

	public AdminDefaultService() {
	}

	public List<EventDefault> getGoods(String platform, String serverTag, String eventTag, String value1string, String value2string, String channelTag, String channelSub, String userId, String roleName, String roleLevel, int start, int count, String startTime, String stopTime) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<EventDefault> list = new ArrayList<EventDefault>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(selectCharge);
			int index = 0;
			if (startTime != null && startTime.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" timeAdd>='").append(startTime).append("' ");
			}
			if (stopTime != null && stopTime.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" timeAdd<='").append(stopTime).append("' ");
			}
			if (platform != null && platform.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" platform='").append(platform).append("' ");
			}
			if (serverTag != null && serverTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" serverTag='").append(serverTag).append("' ");
			}
			if (eventTag != null && eventTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" eventTag='").append(eventTag).append("' ");
			}
			if (value1string != null && value1string.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" value1string like '%").append(value1string).append("%' ");
			}
			if (value2string != null && value2string.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" value2string like '%").append(value2string).append("%' ");
			}
			if (channelTag != null && channelTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" channelTag='").append(channelTag).append("' ");
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
				sb.append(" userId='").append(userId).append("' ");
			}
			if (roleName != null && roleName.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" roleName='").append(roleName).append("' ");
			}
			if (roleLevel != null && roleLevel.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" roleLevel=").append(roleLevel);
			}
			if (index < 1) {
				return list;
			}
			sb.append(" limit ").append(start).append(", ").append(count);
			stmt = conn.prepareStatement(sb.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				EventDefault event = new EventDefault();
				event.setId(rs.getLong(1));
				event.setEventSource(rs.getString(2));
				event.setEventTag(rs.getString(3));
				event.setAppTag(rs.getString(4));
				event.setPlatform(rs.getString(5));
				event.setServerTag(rs.getString(6));
				event.setChannelTag(rs.getString(7));
				event.setChannelSub(rs.getString(8));
				event.setAccountId(rs.getInt(9));
				event.setUserId(rs.getString(10));
				event.setRoleId(rs.getInt(11));
				event.setRoleName(rs.getString(12));
				event.setRoleLevel(rs.getInt(13));
				event.setVipLevel(rs.getInt(14));
				event.setValue1string(rs.getString(15));
				event.setValue2string(rs.getString(16));
				event.setValue3string(rs.getString(17));
				event.setValue4string(rs.getString(18));
				event.setValue5string(rs.getString(19));
				event.setValue6string(rs.getString(20));
				event.setValue7string(rs.getString(21));
				event.setValue8string(rs.getString(22));
				event.setKey1int(rs.getString(23));
				event.setValue1int(rs.getInt(24));
				event.setKey2int(rs.getString(25));
				event.setValue2int(rs.getInt(26));
				event.setKey3int(rs.getString(27));
				event.setValue3int(rs.getInt(28));
				event.setKey1long(rs.getString(29));
				event.setValue1long(rs.getLong(30));
				event.setKey1double(rs.getString(31));
				event.setValue1double(rs.getDouble(32));
				Timestamp time = rs.getTimestamp(33);
				if (time != null) {
					event.setTimeAdd(time.getTime());
				}
				list.add(event);
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

	public int getCount(String platform, String serverTag, String eventTag, String value1string, String value2string, String channelTag, String channelSub, String userId, String roleName, String roleLevel, String start, String stop) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select count(id) totalCount from event_default where");
			int index = 0;
			if (start != null && start.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" timeAdd>='").append(start).append("' ");
			}
			if (stop != null && stop.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" timeAdd<='").append(stop).append("' ");
			}
			if (platform != null && platform.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" platform='").append(platform).append("' ");
			}
			if (platform != null && platform.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" platform='").append(platform).append("' ");
			}
			if (serverTag != null && serverTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" serverTag='").append(serverTag).append("' ");
			}
			if (eventTag != null && eventTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" eventTag='").append(eventTag).append("' ");
			}
			if (value1string != null && value1string.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" value1string like '%").append(value1string).append("%' ");
			}
			if (value2string != null && value2string.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" value2string like '%").append(value2string).append("%' ");
			}
			if (channelTag != null && channelTag.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" channelTag='").append(channelTag).append("' ");
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
				sb.append(" userId='").append(userId).append("' ");
			}
			if (roleName != null && roleName.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" roleName='").append(roleName).append("' ");
			}
			if (roleLevel != null && roleLevel.length() > 0) {
				if (index++ > 0) {
					sb.append(" and ");
				}
				sb.append(" roleLevel=").append(roleLevel);
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
			DBConnection.freeConnection(conn);
			conn = null;
		}
		return 0;
	}

}
