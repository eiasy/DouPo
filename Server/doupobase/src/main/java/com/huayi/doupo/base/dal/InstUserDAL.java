package com.huayi.doupo.base.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.huayi.doupo.base.dal.base.DALFather;
import com.huayi.doupo.base.model.InstUser;
import com.huayi.doupo.base.model.player.PlayerMemObj;

public class InstUserDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstUser instUser = new InstUser();
			instUser.setId(rs.getInt("id"), 0);
			instUser.setOpenId(rs.getString("openId"), 0);
			instUser.setOnlineState(rs.getInt("onlineState"), 0);
			instUser.setFirstLoginDate(rs.getString("firstLoginDate"), 0);
			instUser.setFirstLoginTime(rs.getString("firstLoginTime"), 0);
			instUser.setLoginTotalTimes(rs.getInt("loginTotalTimes"), 0);
			instUser.setOnlineTotalTime(rs.getString("onlineTotalTime"), 0);
			instUser.setLastLoginDate(rs.getString("lastLoginDate"), 0);
			instUser.setLastLoginTime(rs.getString("lastLoginTime"), 0);
			instUser.setLastLeaveDate(rs.getString("lastLeaveDate"), 0);
			instUser.setLastLeaveTime(rs.getString("lastLeaveTime"), 0);
			instUser.setChannel(rs.getString("channel"), 0);
			instUser.setFrozenTime(rs.getString("frozenTime"), 0);
			instUser.setServerId(rs.getInt("serverId"), 0);
			instUser.setAccountId(rs.getString("accountId"), 0);
			instUser.setVersion(rs.getInt("version"), 0);
			return instUser;
		}
	}

	public InstUser add(final InstUser model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_User (");
			strSql.append("openId,onlineState,firstLoginDate,firstLoginTime,loginTotalTimes,onlineTotalTime,lastLoginDate,lastLoginTime,lastLeaveDate,lastLeaveTime,channel,frozenTime,serverId,accountId,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, model.getOpenId());
					ps.setInt(2, model.getOnlineState());
					ps.setString(3, model.getFirstLoginDate());
					ps.setString(4, model.getFirstLoginTime());
					ps.setInt(5, model.getLoginTotalTimes());
					ps.setString(6, model.getOnlineTotalTime());
					ps.setString(7, model.getLastLoginDate());
					ps.setString(8, model.getLastLoginTime());
					ps.setString(9, model.getLastLeaveDate());
					ps.setString(10, model.getLastLeaveTime());
					ps.setString(11, model.getChannel());
					ps.setString(12, model.getFrozenTime());
					ps.setInt(13, model.getServerId());
					ps.setString(14, model.getAccountId());
					ps.setInt(15, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instUserMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstUser> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_User (");
		sql.append("openId,onlineState,firstLoginDate,firstLoginTime,loginTotalTimes,onlineTotalTime,lastLoginDate,lastLoginTime,lastLeaveDate,lastLeaveTime,channel,frozenTime,serverId,accountId,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstUser model = (InstUser)list.get(i);
					ps.setString(1, model.getOpenId());
					ps.setInt(2, model.getOnlineState());
					ps.setString(3, model.getFirstLoginDate());
					ps.setString(4, model.getFirstLoginTime());
					ps.setInt(5, model.getLoginTotalTimes());
					ps.setString(6, model.getOnlineTotalTime());
					ps.setString(7, model.getLastLoginDate());
					ps.setString(8, model.getLastLoginTime());
					ps.setString(9, model.getLastLeaveDate());
					ps.setString(10, model.getLastLeaveTime());
					ps.setString(11, model.getChannel());
					ps.setString(12, model.getFrozenTime());
					ps.setInt(13, model.getServerId());
					ps.setString(14, model.getAccountId());
					ps.setInt(15, 0);
			}
			public int getBatchSize(){
				return list.size();
			}
		};
		getJdbcTemplate().batchUpdate(sql.toString(), setter);
	}

	public int deleteById(int id, int instPlayerId) throws Exception {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instUserMap.remove(id);
			}
			String sql = "delete from Inst_User  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_User where 1=1 ");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" and " + strWhere);
			}
			return this.getJdbcTemplate().update(sql.toString());
		} catch (Exception e) {
			throw e;
		}
	}

	public int update(String sql) throws Exception {
		try {
			return this.getJdbcTemplate().update(sql.toString());
		} catch (Exception e) {
			throw e;
		}
	}

	public InstUser update(InstUser model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Inst_User set ");
			sql.append("openId=?,onlineState=?,firstLoginDate=?,firstLoginTime=?,loginTotalTimes=?,onlineTotalTime=?,lastLoginDate=?,lastLoginTime=?,lastLeaveDate=?,lastLeaveTime=?,channel=?,frozenTime=?,serverId=?,accountId=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getOpenId(),model.getOnlineState(),model.getFirstLoginDate(),model.getFirstLoginTime(),model.getLoginTotalTimes(),model.getOnlineTotalTime(),model.getLastLoginDate(),model.getLastLoginTime(),model.getLastLeaveDate(),model.getLastLeaveTime(),model.getChannel(),model.getFrozenTime(),model.getServerId(),model.getAccountId(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instUserMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstUser getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstUser model = playerMemObj.instUserMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_User where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instUserMap.put(id, (InstUser) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_User where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_User where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instUserMap.put(id, (InstUser) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instUserMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_User where id=?";
				Object[] params = new Object[]{id};
				InstUser model = ( InstUser) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstUser> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_User ");
		}else {
			sql = new StringBuffer("select * from Inst_User ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstUser> instUserList = (List<InstUser>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instUserList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_User ");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
			while (rsSet.next()) {
				listLong.add(rsSet.getLong("id"));
			}
			return listLong;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstUser> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_User ");
			}else {
				sql = new StringBuffer("select * from Inst_User ");
			}
			if(index <= 0 || size <= 0){
				throw new Exception("index or size must bigger than zero");
			}else{
				index = (index - 1) * size;
			}
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			sql.append(" limit " + index + "," + size + "");
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				return listCacheCommonHandler(sql.toString(), instPlayerId);
			} else {
				return (List<InstUser>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_User where id =?");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" or " + strWhere);
			}
			int count = this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
			return count > 0 ? true : false;
		} catch (Exception e) {
			throw e;
		}
	}

	public int getCount(String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_User");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Long> getCounts(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_User ");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(strWhere);
			}
			SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
			while (rsSet.next()) {
				listLong.add(rsSet.getLong("cnt"));
			}
			return listLong;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Map<String,Object>> sqlHelper(String sql) {
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List<InstUser> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_User a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_User a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstUser>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_User a ");
			if (afterSql != null && !afterSql.equals("")) {
				sql.append(afterSql);
			}
			SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
			while (rsSet.next()) {
				listLong.add(rsSet.getLong("id"));
			}
			return listLong;
		} catch (Exception e) {
			throw e;
		}
	}

	public int getStatResult(String statType, String conParam, String strWhere) throws Exception {
		int result = 0;
		try {
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_User");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstUser> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstUser> modelList = new ArrayList<InstUser>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstUser model = playerMemObj.instUserMap.get(id);
			if (model == null) {
				model = getModel(id, instPlayerId);
				model.result = "";
				modelList.add(model);
			} else {
				int cacheVersion = model.getVersion();
				if (cacheVersion != dbVersion) {
					model = getModel(id, instPlayerId);
				}
				model.result = "";
				modelList.add(model);
			}
		}
		return modelList;
	}

}