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
import com.huayi.doupo.base.model.DictFightSoulUpgradeProp;

public class DictFightSoulUpgradePropDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictFightSoulUpgradeProp dictFightSoulUpgradeProp = new DictFightSoulUpgradeProp();
			dictFightSoulUpgradeProp.setId(rs.getInt("id"), 0);
			dictFightSoulUpgradeProp.setFightSoulId(rs.getInt("fightSoulId"), 0);
			dictFightSoulUpgradeProp.setLevel(rs.getInt("level"), 0);
			dictFightSoulUpgradeProp.setFightPropValueType(rs.getInt("fightPropValueType"), 0);
			dictFightSoulUpgradeProp.setFightPropValue(rs.getFloat("fightPropValue"), 0);
			dictFightSoulUpgradeProp.setFightPropId(rs.getInt("fightPropId"), 0);
			dictFightSoulUpgradeProp.setFightValue(rs.getInt("fightValue"), 0);
			dictFightSoulUpgradeProp.setSellSilver(rs.getInt("sellSilver"), 0);
			dictFightSoulUpgradeProp.setDescription(rs.getString("description"), 0);
			dictFightSoulUpgradeProp.setVersion(rs.getInt("version"), 0);
			return dictFightSoulUpgradeProp;
		}
	}

	public DictFightSoulUpgradeProp add(final DictFightSoulUpgradeProp model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_FightSoul_UpgradeProp (");
			strSql.append("fightSoulId,level,fightPropValueType,fightPropValue,fightPropId,fightValue,sellSilver,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getFightSoulId());
					ps.setInt(2, model.getLevel());
					ps.setInt(3, model.getFightPropValueType());
					ps.setFloat(4, model.getFightPropValue());
					ps.setInt(5, model.getFightPropId());
					ps.setInt(6, model.getFightValue());
					ps.setInt(7, model.getSellSilver());
					ps.setString(8, model.getDescription());
					ps.setInt(9, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictFightSoulUpgradePropMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictFightSoulUpgradeProp> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_FightSoul_UpgradeProp (");
		sql.append("fightSoulId,level,fightPropValueType,fightPropValue,fightPropId,fightValue,sellSilver,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictFightSoulUpgradeProp model = (DictFightSoulUpgradeProp)list.get(i);
					ps.setInt(1, model.getFightSoulId());
					ps.setInt(2, model.getLevel());
					ps.setInt(3, model.getFightPropValueType());
					ps.setFloat(4, model.getFightPropValue());
					ps.setInt(5, model.getFightPropId());
					ps.setInt(6, model.getFightValue());
					ps.setInt(7, model.getSellSilver());
					ps.setString(8, model.getDescription());
					ps.setInt(9, 0);
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
				playerMemObj.dictFightSoulUpgradePropMap.remove(id);
			}
			String sql = "delete from Dict_FightSoul_UpgradeProp  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_FightSoul_UpgradeProp where 1=1 ");
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

	public DictFightSoulUpgradeProp update(DictFightSoulUpgradeProp model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_FightSoul_UpgradeProp set ");
			sql.append("fightSoulId=?,level=?,fightPropValueType=?,fightPropValue=?,fightPropId=?,fightValue=?,sellSilver=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getFightSoulId(),model.getLevel(),model.getFightPropValueType(),model.getFightPropValue(),model.getFightPropId(),model.getFightValue(),model.getSellSilver(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictFightSoulUpgradePropMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictFightSoulUpgradeProp getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictFightSoulUpgradeProp model = playerMemObj.dictFightSoulUpgradePropMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_FightSoul_UpgradeProp where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictFightSoulUpgradePropMap.put(id, (DictFightSoulUpgradeProp) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_FightSoul_UpgradeProp where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_FightSoul_UpgradeProp where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictFightSoulUpgradePropMap.put(id, (DictFightSoulUpgradeProp) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictFightSoulUpgradePropMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_FightSoul_UpgradeProp where id=?";
				Object[] params = new Object[]{id};
				DictFightSoulUpgradeProp model = ( DictFightSoulUpgradeProp) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictFightSoulUpgradeProp> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_FightSoul_UpgradeProp ");
		}else {
			sql = new StringBuffer("select * from Dict_FightSoul_UpgradeProp ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictFightSoulUpgradeProp> dictFightSoulUpgradePropList = (List<DictFightSoulUpgradeProp>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictFightSoulUpgradePropList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_FightSoul_UpgradeProp ");
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
	public List<DictFightSoulUpgradeProp> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_FightSoul_UpgradeProp ");
			}else {
				sql = new StringBuffer("select * from Dict_FightSoul_UpgradeProp ");
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
				return (List<DictFightSoulUpgradeProp>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_FightSoul_UpgradeProp where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_FightSoul_UpgradeProp");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_FightSoul_UpgradeProp ");
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
	public List<DictFightSoulUpgradeProp> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_FightSoul_UpgradeProp a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_FightSoul_UpgradeProp a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictFightSoulUpgradeProp>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_FightSoul_UpgradeProp a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_FightSoul_UpgradeProp");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictFightSoulUpgradeProp> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictFightSoulUpgradeProp> modelList = new ArrayList<DictFightSoulUpgradeProp>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictFightSoulUpgradeProp model = playerMemObj.dictFightSoulUpgradePropMap.get(id);
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