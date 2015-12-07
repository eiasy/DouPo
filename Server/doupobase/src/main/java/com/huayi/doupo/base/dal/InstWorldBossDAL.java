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
import com.huayi.doupo.base.model.InstWorldBoss;

public class InstWorldBossDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstWorldBoss instWorldBoss = new InstWorldBoss();
			instWorldBoss.setId(rs.getInt("id"), 0);
			instWorldBoss.setCurrBossId(rs.getInt("currBossId"), 0);
			instWorldBoss.setCurrBossBlood(rs.getInt("currBossBlood"), 0);
			instWorldBoss.setCurrHitBossSecs(rs.getInt("currHitBossSecs"), 0);
			instWorldBoss.setCurrBossBloodPer(rs.getFloat("currBossBloodPer"), 0);
			instWorldBoss.setLastBossId(rs.getInt("lastBossId"), 0);
			instWorldBoss.setLastBossBlood(rs.getInt("lastBossBlood"), 0);
			instWorldBoss.setLastHitBossSecs(rs.getInt("lastHitBossSecs"), 0);
			instWorldBoss.setLastBossBloodPer(rs.getFloat("lastBossBloodPer"), 0);
			instWorldBoss.setInsertTime(rs.getString("insertTime"), 0);
			instWorldBoss.setUpdateTime(rs.getString("updateTime"), 0);
			instWorldBoss.setVersion(rs.getInt("version"), 0);
			return instWorldBoss;
		}
	}

	public InstWorldBoss add(final InstWorldBoss model, int instPlayerId) throws Exception {
		try {
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_WorldBoss (");
			strSql.append("currBossId,currBossBlood,currHitBossSecs,currBossBloodPer,lastBossId,lastBossBlood,lastHitBossSecs,lastBossBloodPer,insertTime,updateTime,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getCurrBossId());
					ps.setInt(2, model.getCurrBossBlood());
					ps.setInt(3, model.getCurrHitBossSecs());
					ps.setFloat(4, model.getCurrBossBloodPer());
					ps.setInt(5, model.getLastBossId());
					ps.setInt(6, model.getLastBossBlood());
					ps.setInt(7, model.getLastHitBossSecs());
					ps.setFloat(8, model.getLastBossBloodPer());
					ps.setString(9, updateTime);
					ps.setString(10, updateTime);
					ps.setInt(11, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			model.setInsertTime(updateTime, 0);
			model.setUpdateTime(updateTime, 0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instWorldBossMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstWorldBoss> list) {
		final String updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_WorldBoss (");
		sql.append("currBossId,currBossBlood,currHitBossSecs,currBossBloodPer,lastBossId,lastBossBlood,lastHitBossSecs,lastBossBloodPer,insertTime,updateTime,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstWorldBoss model = (InstWorldBoss)list.get(i);
					ps.setInt(1, model.getCurrBossId());
					ps.setInt(2, model.getCurrBossBlood());
					ps.setInt(3, model.getCurrHitBossSecs());
					ps.setFloat(4, model.getCurrBossBloodPer());
					ps.setInt(5, model.getLastBossId());
					ps.setInt(6, model.getLastBossBlood());
					ps.setInt(7, model.getLastHitBossSecs());
					ps.setFloat(8, model.getLastBossBloodPer());
					ps.setString(9, updateTime);
					ps.setString(10, updateTime);
					ps.setInt(11, 0);
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
				playerMemObj.instWorldBossMap.remove(id);
			}
			String sql = "delete from Inst_WorldBoss  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_WorldBoss where 1=1 ");
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

	public InstWorldBoss update(InstWorldBoss model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuffer sql = new StringBuffer("update Inst_WorldBoss set ");
			sql.append("currBossId=?,currBossBlood=?,currHitBossSecs=?,currBossBloodPer=?,lastBossId=?,lastBossBlood=?,lastHitBossSecs=?,lastBossBloodPer=?,insertTime=?,updateTime=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getCurrBossId(),model.getCurrBossBlood(),model.getCurrHitBossSecs(),model.getCurrBossBloodPer(),model.getLastBossId(),model.getLastBossBlood(),model.getLastHitBossSecs(),model.getLastBossBloodPer(),model.getInsertTime(),updateTime,version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				model.setUpdateTime(updateTime, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instWorldBossMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstWorldBoss getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstWorldBoss model = playerMemObj.instWorldBossMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_WorldBoss where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instWorldBossMap.put(id, (InstWorldBoss) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_WorldBoss where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_WorldBoss where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instWorldBossMap.put(id, (InstWorldBoss) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instWorldBossMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_WorldBoss where id=?";
				Object[] params = new Object[]{id};
				InstWorldBoss model = ( InstWorldBoss) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstWorldBoss> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_WorldBoss ");
		}else {
			sql = new StringBuffer("select * from Inst_WorldBoss ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstWorldBoss> instWorldBossList = (List<InstWorldBoss>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instWorldBossList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_WorldBoss ");
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
	public List<InstWorldBoss> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_WorldBoss ");
			}else {
				sql = new StringBuffer("select * from Inst_WorldBoss ");
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
				return (List<InstWorldBoss>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_WorldBoss where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_WorldBoss");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_WorldBoss ");
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
	public List<InstWorldBoss> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_WorldBoss a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_WorldBoss a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstWorldBoss>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_WorldBoss a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_WorldBoss");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstWorldBoss> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstWorldBoss> modelList = new ArrayList<InstWorldBoss>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstWorldBoss model = playerMemObj.instWorldBossMap.get(id);
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