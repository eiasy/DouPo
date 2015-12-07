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
import com.huayi.doupo.base.model.DictAdvance;

public class DictAdvanceDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictAdvance dictAdvance = new DictAdvance();
			dictAdvance.setId(rs.getInt("id"), 0);
			dictAdvance.setCardId(rs.getInt("cardId"), 0);
			dictAdvance.setQualityId(rs.getInt("qualityId"), 0);
			dictAdvance.setStarLevelId(rs.getInt("starLevelId"), 0);
			dictAdvance.setNextQualityId(rs.getInt("nextQualityId"), 0);
			dictAdvance.setNextStarLevelId(rs.getInt("nextStarLevelId"), 0);
			dictAdvance.setConds(rs.getString("conds"), 0);
			dictAdvance.setBlood(rs.getInt("blood"), 0);
			dictAdvance.setWuAttack(rs.getInt("wuAttack"), 0);
			dictAdvance.setFaAttack(rs.getInt("faAttack"), 0);
			dictAdvance.setWuDefense(rs.getInt("wuDefense"), 0);
			dictAdvance.setFaDefense(rs.getInt("faDefense"), 0);
			dictAdvance.setBloodAdd(rs.getFloat("bloodAdd"), 0);
			dictAdvance.setWuAttackAdd(rs.getFloat("wuAttackAdd"), 0);
			dictAdvance.setFaAttackAdd(rs.getFloat("faAttackAdd"), 0);
			dictAdvance.setWuDefenseAdd(rs.getFloat("wuDefenseAdd"), 0);
			dictAdvance.setFaDefenseAdd(rs.getFloat("faDefenseAdd"), 0);
			dictAdvance.setDescription(rs.getString("description"), 0);
			dictAdvance.setVersion(rs.getInt("version"), 0);
			return dictAdvance;
		}
	}

	public DictAdvance add(final DictAdvance model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Advance (");
			strSql.append("cardId,qualityId,starLevelId,nextQualityId,nextStarLevelId,conds,blood,wuAttack,faAttack,wuDefense,faDefense,bloodAdd,wuAttackAdd,faAttackAdd,wuDefenseAdd,faDefenseAdd,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getCardId());
					ps.setInt(2, model.getQualityId());
					ps.setInt(3, model.getStarLevelId());
					ps.setInt(4, model.getNextQualityId());
					ps.setInt(5, model.getNextStarLevelId());
					ps.setString(6, model.getConds());
					ps.setInt(7, model.getBlood());
					ps.setInt(8, model.getWuAttack());
					ps.setInt(9, model.getFaAttack());
					ps.setInt(10, model.getWuDefense());
					ps.setInt(11, model.getFaDefense());
					ps.setFloat(12, model.getBloodAdd());
					ps.setFloat(13, model.getWuAttackAdd());
					ps.setFloat(14, model.getFaAttackAdd());
					ps.setFloat(15, model.getWuDefenseAdd());
					ps.setFloat(16, model.getFaDefenseAdd());
					ps.setString(17, model.getDescription());
					ps.setInt(18, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictAdvanceMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictAdvance> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Advance (");
		sql.append("cardId,qualityId,starLevelId,nextQualityId,nextStarLevelId,conds,blood,wuAttack,faAttack,wuDefense,faDefense,bloodAdd,wuAttackAdd,faAttackAdd,wuDefenseAdd,faDefenseAdd,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictAdvance model = (DictAdvance)list.get(i);
					ps.setInt(1, model.getCardId());
					ps.setInt(2, model.getQualityId());
					ps.setInt(3, model.getStarLevelId());
					ps.setInt(4, model.getNextQualityId());
					ps.setInt(5, model.getNextStarLevelId());
					ps.setString(6, model.getConds());
					ps.setInt(7, model.getBlood());
					ps.setInt(8, model.getWuAttack());
					ps.setInt(9, model.getFaAttack());
					ps.setInt(10, model.getWuDefense());
					ps.setInt(11, model.getFaDefense());
					ps.setFloat(12, model.getBloodAdd());
					ps.setFloat(13, model.getWuAttackAdd());
					ps.setFloat(14, model.getFaAttackAdd());
					ps.setFloat(15, model.getWuDefenseAdd());
					ps.setFloat(16, model.getFaDefenseAdd());
					ps.setString(17, model.getDescription());
					ps.setInt(18, 0);
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
				playerMemObj.dictAdvanceMap.remove(id);
			}
			String sql = "delete from Dict_Advance  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Advance where 1=1 ");
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

	public DictAdvance update(DictAdvance model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Advance set ");
			sql.append("cardId=?,qualityId=?,starLevelId=?,nextQualityId=?,nextStarLevelId=?,conds=?,blood=?,wuAttack=?,faAttack=?,wuDefense=?,faDefense=?,bloodAdd=?,wuAttackAdd=?,faAttackAdd=?,wuDefenseAdd=?,faDefenseAdd=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getCardId(),model.getQualityId(),model.getStarLevelId(),model.getNextQualityId(),model.getNextStarLevelId(),model.getConds(),model.getBlood(),model.getWuAttack(),model.getFaAttack(),model.getWuDefense(),model.getFaDefense(),model.getBloodAdd(),model.getWuAttackAdd(),model.getFaAttackAdd(),model.getWuDefenseAdd(),model.getFaDefenseAdd(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictAdvanceMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictAdvance getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictAdvance model = playerMemObj.dictAdvanceMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Advance where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictAdvanceMap.put(id, (DictAdvance) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Advance where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Advance where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictAdvanceMap.put(id, (DictAdvance) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictAdvanceMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Advance where id=?";
				Object[] params = new Object[]{id};
				DictAdvance model = ( DictAdvance) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictAdvance> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Advance ");
		}else {
			sql = new StringBuffer("select * from Dict_Advance ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictAdvance> dictAdvanceList = (List<DictAdvance>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictAdvanceList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Advance ");
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
	public List<DictAdvance> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Advance ");
			}else {
				sql = new StringBuffer("select * from Dict_Advance ");
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
				return (List<DictAdvance>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Advance where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Advance");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Advance ");
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
	public List<DictAdvance> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Advance a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Advance a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictAdvance>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Advance a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Advance");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictAdvance> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictAdvance> modelList = new ArrayList<DictAdvance>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictAdvance model = playerMemObj.dictAdvanceMap.get(id);
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