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
import com.huayi.doupo.base.model.DictMineType;

public class DictMineTypeDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictMineType dictMineType = new DictMineType();
			dictMineType.setId(rs.getInt("id"), 0);
			dictMineType.setMineType(rs.getInt("mineType"), 0);
			dictMineType.setMineName(rs.getString("mineName"), 0);
			dictMineType.setMasterCsType(rs.getInt("masterCsType"), 0);
			dictMineType.setMasterCsValue(rs.getInt("masterCsValue"), 0);
			dictMineType.setSlaveCsType(rs.getInt("slaveCsType"), 0);
			dictMineType.setSlaveCsValue(rs.getInt("slaveCsValue"), 0);
			dictMineType.setCycleTime(rs.getInt("cycleTime"), 0);
			dictMineType.setMasterCopperCount(rs.getInt("masterCopperCount"), 0);
			dictMineType.setMasterThing83Count(rs.getInt("masterThing83Count"), 0);
			dictMineType.setSlaveCopperCount(rs.getInt("slaveCopperCount"), 0);
			dictMineType.setSlaveThing83Count(rs.getInt("slaveThing83Count"), 0);
			dictMineType.setMultiple(rs.getFloat("multiple"), 0);
			dictMineType.setVersion(rs.getInt("version"), 0);
			return dictMineType;
		}
	}

	public DictMineType add(final DictMineType model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Mine_Type (");
			strSql.append("mineType,mineName,masterCsType,masterCsValue,slaveCsType,slaveCsValue,cycleTime,masterCopperCount,masterThing83Count,slaveCopperCount,slaveThing83Count,multiple,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getMineType());
					ps.setString(2, model.getMineName());
					ps.setInt(3, model.getMasterCsType());
					ps.setInt(4, model.getMasterCsValue());
					ps.setInt(5, model.getSlaveCsType());
					ps.setInt(6, model.getSlaveCsValue());
					ps.setInt(7, model.getCycleTime());
					ps.setInt(8, model.getMasterCopperCount());
					ps.setInt(9, model.getMasterThing83Count());
					ps.setInt(10, model.getSlaveCopperCount());
					ps.setInt(11, model.getSlaveThing83Count());
					ps.setFloat(12, model.getMultiple());
					ps.setInt(13, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictMineTypeMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictMineType> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Mine_Type (");
		sql.append("mineType,mineName,masterCsType,masterCsValue,slaveCsType,slaveCsValue,cycleTime,masterCopperCount,masterThing83Count,slaveCopperCount,slaveThing83Count,multiple,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictMineType model = (DictMineType)list.get(i);
					ps.setInt(1, model.getMineType());
					ps.setString(2, model.getMineName());
					ps.setInt(3, model.getMasterCsType());
					ps.setInt(4, model.getMasterCsValue());
					ps.setInt(5, model.getSlaveCsType());
					ps.setInt(6, model.getSlaveCsValue());
					ps.setInt(7, model.getCycleTime());
					ps.setInt(8, model.getMasterCopperCount());
					ps.setInt(9, model.getMasterThing83Count());
					ps.setInt(10, model.getSlaveCopperCount());
					ps.setInt(11, model.getSlaveThing83Count());
					ps.setFloat(12, model.getMultiple());
					ps.setInt(13, 0);
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
				playerMemObj.dictMineTypeMap.remove(id);
			}
			String sql = "delete from Dict_Mine_Type  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Mine_Type where 1=1 ");
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

	public DictMineType update(DictMineType model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Mine_Type set ");
			sql.append("mineType=?,mineName=?,masterCsType=?,masterCsValue=?,slaveCsType=?,slaveCsValue=?,cycleTime=?,masterCopperCount=?,masterThing83Count=?,slaveCopperCount=?,slaveThing83Count=?,multiple=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getMineType(),model.getMineName(),model.getMasterCsType(),model.getMasterCsValue(),model.getSlaveCsType(),model.getSlaveCsValue(),model.getCycleTime(),model.getMasterCopperCount(),model.getMasterThing83Count(),model.getSlaveCopperCount(),model.getSlaveThing83Count(),model.getMultiple(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictMineTypeMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictMineType getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictMineType model = playerMemObj.dictMineTypeMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Mine_Type where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictMineTypeMap.put(id, (DictMineType) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Mine_Type where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Mine_Type where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictMineTypeMap.put(id, (DictMineType) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictMineTypeMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Mine_Type where id=?";
				Object[] params = new Object[]{id};
				DictMineType model = ( DictMineType) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictMineType> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Mine_Type ");
		}else {
			sql = new StringBuffer("select * from Dict_Mine_Type ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictMineType> dictMineTypeList = (List<DictMineType>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictMineTypeList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Mine_Type ");
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
	public List<DictMineType> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Mine_Type ");
			}else {
				sql = new StringBuffer("select * from Dict_Mine_Type ");
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
				return (List<DictMineType>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Mine_Type where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Mine_Type");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Mine_Type ");
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
	public List<DictMineType> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Mine_Type a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Mine_Type a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictMineType>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Mine_Type a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Mine_Type");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictMineType> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictMineType> modelList = new ArrayList<DictMineType>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictMineType model = playerMemObj.dictMineTypeMap.get(id);
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