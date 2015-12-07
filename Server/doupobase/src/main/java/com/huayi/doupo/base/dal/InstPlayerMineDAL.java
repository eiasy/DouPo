package com.huayi.doupo.base.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.io.InputStream;

import java.sql.PreparedStatement;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.huayi.doupo.base.dal.base.DALFather;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.model.InstPlayerMine;

public class InstPlayerMineDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstPlayerMine instPlayerMine = new InstPlayerMine();
			instPlayerMine.setId(rs.getInt("id"), 0);
			instPlayerMine.setMineId(rs.getInt("mineId"), 0);
			instPlayerMine.setMineType(rs.getInt("mineType"), 0);
			instPlayerMine.setMineZone(rs.getInt("mineZone"), 0);
			instPlayerMine.setMinePage(rs.getInt("minePage"), 0);
			instPlayerMine.setMineIndex(rs.getInt("mineIndex"), 0);
			instPlayerMine.setInstPlayerId(rs.getInt("instPlayerId"), 0);
			instPlayerMine.setInitTime(rs.getString("initTime"), 0);
			instPlayerMine.setMasterTime(rs.getString("masterTime"), 0);
			instPlayerMine.setAssistId1(rs.getInt("assistId1"), 0);
			instPlayerMine.setATime1(rs.getString("aTime1"), 0);
			instPlayerMine.setAssistId2(rs.getInt("assistId2"), 0);
			instPlayerMine.setATime2(rs.getString("aTime2"), 0);
			instPlayerMine.setExtratime(rs.getInt("extratime"), 0);
			instPlayerMine.setWeather(rs.getInt("weather"), 0);
			instPlayerMine.setMasterThing(rs.getString("masterThing"), 0);
			instPlayerMine.setMasterThingState(rs.getInt("masterThingState"), 0);
			instPlayerMine.setAssistThing(rs.getString("assistThing"), 0);
			instPlayerMine.setAssistThingState(rs.getInt("assistThingState"), 0);
			instPlayerMine.setVersion(rs.getInt("version"), 0);
			return instPlayerMine;
		}
	}

	public InstPlayerMine add(final InstPlayerMine model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_Player_Mine (");
			strSql.append("mineId,mineType,mineZone,minePage,mineIndex,instPlayerId,initTime,masterTime,assistId1,aTime1,assistId2,aTime2,extratime,weather,masterThing,masterThingState,assistThing,assistThingState,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getMineId());
					ps.setInt(2, model.getMineType());
					ps.setInt(3, model.getMineZone());
					ps.setInt(4, model.getMinePage());
					ps.setInt(5, model.getMineIndex());
					ps.setInt(6, model.getInstPlayerId());
					ps.setString(7, model.getInitTime());
					ps.setString(8, model.getMasterTime());
					ps.setInt(9, model.getAssistId1());
					ps.setString(10, model.getATime1());
					ps.setInt(11, model.getAssistId2());
					ps.setString(12, model.getATime2());
					ps.setInt(13, model.getExtratime());
					ps.setInt(14, model.getWeather());
					ps.setString(15, model.getMasterThing());
					ps.setInt(16, model.getMasterThingState());
					ps.setString(17, model.getAssistThing());
					ps.setInt(18, model.getAssistThingState());
					ps.setInt(19, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instPlayerMineMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstPlayerMine> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_Player_Mine (");
		sql.append("mineId,mineType,mineZone,minePage,mineIndex,instPlayerId,initTime,masterTime,assistId1,aTime1,assistId2,aTime2,extratime,weather,masterThing,masterThingState,assistThing,assistThingState,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstPlayerMine model = (InstPlayerMine)list.get(i);
					ps.setInt(1, model.getMineId());
					ps.setInt(2, model.getMineType());
					ps.setInt(3, model.getMineZone());
					ps.setInt(4, model.getMinePage());
					ps.setInt(5, model.getMineIndex());
					ps.setInt(6, model.getInstPlayerId());
					ps.setString(7, model.getInitTime());
					ps.setString(8, model.getMasterTime());
					ps.setInt(9, model.getAssistId1());
					ps.setString(10, model.getATime1());
					ps.setInt(11, model.getAssistId2());
					ps.setString(12, model.getATime2());
					ps.setInt(13, model.getExtratime());
					ps.setInt(14, model.getWeather());
					ps.setString(15, model.getMasterThing());
					ps.setInt(16, model.getMasterThingState());
					ps.setString(17, model.getAssistThing());
					ps.setInt(18, model.getAssistThingState());
					ps.setInt(19, 0);
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
				playerMemObj.instPlayerMineMap.remove(id);
			}
			String sql = "delete from Inst_Player_Mine  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_Player_Mine where 1=1 ");
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

	public InstPlayerMine update(InstPlayerMine model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Inst_Player_Mine set ");
			sql.append("mineId=?,mineType=?,mineZone=?,minePage=?,mineIndex=?,instPlayerId=?,initTime=?,masterTime=?,assistId1=?,aTime1=?,assistId2=?,aTime2=?,extratime=?,weather=?,masterThing=?,masterThingState=?,assistThing=?,assistThingState=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getMineId(),model.getMineType(),model.getMineZone(),model.getMinePage(),model.getMineIndex(),model.getInstPlayerId(),model.getInitTime(),model.getMasterTime(),model.getAssistId1(),model.getATime1(),model.getAssistId2(),model.getATime2(),model.getExtratime(),model.getWeather(),model.getMasterThing(),model.getMasterThingState(),model.getAssistThing(),model.getAssistThingState(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instPlayerMineMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstPlayerMine getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstPlayerMine model = playerMemObj.instPlayerMineMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_Player_Mine where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instPlayerMineMap.put(id, (InstPlayerMine) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_Player_Mine where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_Player_Mine where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instPlayerMineMap.put(id, (InstPlayerMine) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instPlayerMineMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_Player_Mine where id=?";
				Object[] params = new Object[]{id};
				InstPlayerMine model = ( InstPlayerMine) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstPlayerMine> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_Player_Mine ");
		}else {
			sql = new StringBuffer("select * from Inst_Player_Mine ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstPlayerMine> instPlayerMineList = (List<InstPlayerMine>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instPlayerMineList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_Player_Mine ");
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
	public List<InstPlayerMine> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_Player_Mine ");
			}else {
				sql = new StringBuffer("select * from Inst_Player_Mine ");
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
				return (List<InstPlayerMine>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_Mine where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_Mine");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_Player_Mine ");
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
	public List<InstPlayerMine> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_Player_Mine a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_Player_Mine a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstPlayerMine>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_Player_Mine a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_Player_Mine");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstPlayerMine> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstPlayerMine> modelList = new ArrayList<InstPlayerMine>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstPlayerMine model = playerMemObj.instPlayerMineMap.get(id);
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