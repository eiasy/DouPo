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
import com.huayi.doupo.base.model.InstRankDantaDay;

public class InstRankDantaDayDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstRankDantaDay instRankDantaDay = new InstRankDantaDay();
			instRankDantaDay.setId(rs.getInt("id"), 0);
			instRankDantaDay.setPlayerId(rs.getInt("playerId"), 0);
			instRankDantaDay.setPlayerName(rs.getString("playerName"), 0);
			instRankDantaDay.setPlayerLevel(rs.getInt("playerLevel"), 0);
			instRankDantaDay.setRankIndex(rs.getInt("rankIndex"), 0);
			instRankDantaDay.setMaxLayer(rs.getInt("maxLayer"), 0);
			instRankDantaDay.setAddTime(rs.getString("addTime"), 0);
			instRankDantaDay.setUpdateTime(rs.getString("updateTime"), 0);
			instRankDantaDay.setStartCount(rs.getInt("startCount"), 0);
			instRankDantaDay.setLayerInfo(rs.getString("layerInfo"), 0);
			instRankDantaDay.setMedalCount(rs.getInt("medalCount"), 0);
			instRankDantaDay.setStartLayer(rs.getInt("startLayer"), 0);
			instRankDantaDay.setFinishLayer(rs.getInt("finishLayer"), 0);
			instRankDantaDay.setVersion(rs.getInt("version"), 0);
			instRankDantaDay.setInstUnionId(rs.getInt("instUnionId"), 0);
			instRankDantaDay.setHeaderCardId(rs.getInt("headerCardId"), 0);
			return instRankDantaDay;
		}
	}

	public InstRankDantaDay add(final InstRankDantaDay model, int instPlayerId) throws Exception {
		try {
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_RankDantaDay (");
			strSql.append("playerId,playerName,playerLevel,rankIndex,maxLayer,addTime,updateTime,startCount,layerInfo,medalCount,startLayer,finishLayer,version,instUnionId,headerCardId");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getPlayerId());
					ps.setString(2, model.getPlayerName());
					ps.setInt(3, model.getPlayerLevel());
					ps.setInt(4, model.getRankIndex());
					ps.setInt(5, model.getMaxLayer());
					ps.setString(6, model.getAddTime());
					ps.setString(7, updateTime);
					ps.setInt(8, model.getStartCount());
					ps.setString(9, model.getLayerInfo());
					ps.setInt(10, model.getMedalCount());
					ps.setInt(11, model.getStartLayer());
					ps.setInt(12, model.getFinishLayer());
					ps.setInt(13, 0);
					ps.setInt(14, model.getInstUnionId());
					ps.setInt(15, model.getHeaderCardId());
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			model.setUpdateTime(updateTime, 0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instRankDantaDayMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstRankDantaDay> list) {
		final String updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_RankDantaDay (");
		sql.append("playerId,playerName,playerLevel,rankIndex,maxLayer,addTime,updateTime,startCount,layerInfo,medalCount,startLayer,finishLayer,version,instUnionId,headerCardId");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstRankDantaDay model = (InstRankDantaDay)list.get(i);
					ps.setInt(1, model.getPlayerId());
					ps.setString(2, model.getPlayerName());
					ps.setInt(3, model.getPlayerLevel());
					ps.setInt(4, model.getRankIndex());
					ps.setInt(5, model.getMaxLayer());
					ps.setString(6, model.getAddTime());
					ps.setString(7, updateTime);
					ps.setInt(8, model.getStartCount());
					ps.setString(9, model.getLayerInfo());
					ps.setInt(10, model.getMedalCount());
					ps.setInt(11, model.getStartLayer());
					ps.setInt(12, model.getFinishLayer());
					ps.setInt(13, 0);
					ps.setInt(14, model.getInstUnionId());
					ps.setInt(15, model.getHeaderCardId());
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
				playerMemObj.instRankDantaDayMap.remove(id);
			}
			String sql = "delete from Inst_RankDantaDay  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_RankDantaDay where 1=1 ");
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

	public InstRankDantaDay update(InstRankDantaDay model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuffer sql = new StringBuffer("update Inst_RankDantaDay set ");
			sql.append("playerId=?,playerName=?,playerLevel=?,rankIndex=?,maxLayer=?,addTime=?,updateTime=?,startCount=?,layerInfo=?,medalCount=?,startLayer=?,finishLayer=?,version=?,instUnionId=?,headerCardId=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getPlayerId(),model.getPlayerName(),model.getPlayerLevel(),model.getRankIndex(),model.getMaxLayer(),model.getAddTime(),updateTime,model.getStartCount(),model.getLayerInfo(),model.getMedalCount(),model.getStartLayer(),model.getFinishLayer(),version,model.getInstUnionId(),model.getHeaderCardId() , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				model.setUpdateTime(updateTime, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instRankDantaDayMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstRankDantaDay getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstRankDantaDay model = playerMemObj.instRankDantaDayMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_RankDantaDay where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instRankDantaDayMap.put(id, (InstRankDantaDay) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_RankDantaDay where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_RankDantaDay where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instRankDantaDayMap.put(id, (InstRankDantaDay) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instRankDantaDayMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_RankDantaDay where id=?";
				Object[] params = new Object[]{id};
				InstRankDantaDay model = ( InstRankDantaDay) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstRankDantaDay> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_RankDantaDay ");
		}else {
			sql = new StringBuffer("select * from Inst_RankDantaDay ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstRankDantaDay> instRankDantaDayList = (List<InstRankDantaDay>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instRankDantaDayList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_RankDantaDay ");
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
	public List<InstRankDantaDay> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_RankDantaDay ");
			}else {
				sql = new StringBuffer("select * from Inst_RankDantaDay ");
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
				return (List<InstRankDantaDay>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_RankDantaDay where id =" + id);
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_RankDantaDay");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_RankDantaDay ");
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
	public List<InstRankDantaDay> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_RankDantaDay a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_RankDantaDay a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstRankDantaDay>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_RankDantaDay a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_RankDantaDay");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstRankDantaDay> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstRankDantaDay> modelList = new ArrayList<InstRankDantaDay>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstRankDantaDay model = playerMemObj.instRankDantaDayMap.get(id);
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