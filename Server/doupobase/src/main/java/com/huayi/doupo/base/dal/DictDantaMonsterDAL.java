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
import com.huayi.doupo.base.model.DictDantaMonster;

public class DictDantaMonsterDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictDantaMonster dictDantaMonster = new DictDantaMonster();
			dictDantaMonster.setId(rs.getInt("id"), 0);
			dictDantaMonster.setCardId(rs.getInt("cardId"), 0);
			dictDantaMonster.setCardLevel(rs.getInt("cardLevel"), 0);
			dictDantaMonster.setSkillOneLevel(rs.getInt("skillOneLevel"), 0);
			dictDantaMonster.setSkillTwoLevel(rs.getInt("skillTwoLevel"), 0);
			dictDantaMonster.setSkillThreeLevel(rs.getInt("skillThreeLevel"), 0);
			dictDantaMonster.setBloodAdd(rs.getFloat("bloodAdd"), 0);
			dictDantaMonster.setWuAttackAdd(rs.getFloat("wuAttackAdd"), 0);
			dictDantaMonster.setFaAttackAdd(rs.getFloat("faAttackAdd"), 0);
			dictDantaMonster.setWuDefenseAdd(rs.getFloat("wuDefenseAdd"), 0);
			dictDantaMonster.setFaDefenseAdd(rs.getFloat("faDefenseAdd"), 0);
			dictDantaMonster.setWaveNum(rs.getInt("waveNum"), 0);
			dictDantaMonster.setPosition(rs.getInt("position"), 0);
			dictDantaMonster.setIsBoss(rs.getInt("isBoss"), 0);
			dictDantaMonster.setQualityId(rs.getInt("qualityId"), 0);
			dictDantaMonster.setStarLevelId(rs.getInt("starLevelId"), 0);
			dictDantaMonster.setDescription(rs.getString("description"), 0);
			dictDantaMonster.setVersion(rs.getInt("version"), 0);
			return dictDantaMonster;
		}
	}

	public DictDantaMonster add(final DictDantaMonster model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Danta_Monster (");
			strSql.append("cardId,cardLevel,skillOneLevel,skillTwoLevel,skillThreeLevel,bloodAdd,wuAttackAdd,faAttackAdd,wuDefenseAdd,faDefenseAdd,waveNum,position,isBoss,qualityId,starLevelId,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getCardId());
					ps.setInt(2, model.getCardLevel());
					ps.setInt(3, model.getSkillOneLevel());
					ps.setInt(4, model.getSkillTwoLevel());
					ps.setInt(5, model.getSkillThreeLevel());
					ps.setFloat(6, model.getBloodAdd());
					ps.setFloat(7, model.getWuAttackAdd());
					ps.setFloat(8, model.getFaAttackAdd());
					ps.setFloat(9, model.getWuDefenseAdd());
					ps.setFloat(10, model.getFaDefenseAdd());
					ps.setInt(11, model.getWaveNum());
					ps.setInt(12, model.getPosition());
					ps.setInt(13, model.getIsBoss());
					ps.setInt(14, model.getQualityId());
					ps.setInt(15, model.getStarLevelId());
					ps.setString(16, model.getDescription());
					ps.setInt(17, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictDantaMonsterMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictDantaMonster> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Danta_Monster (");
		sql.append("cardId,cardLevel,skillOneLevel,skillTwoLevel,skillThreeLevel,bloodAdd,wuAttackAdd,faAttackAdd,wuDefenseAdd,faDefenseAdd,waveNum,position,isBoss,qualityId,starLevelId,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictDantaMonster model = (DictDantaMonster)list.get(i);
					ps.setInt(1, model.getCardId());
					ps.setInt(2, model.getCardLevel());
					ps.setInt(3, model.getSkillOneLevel());
					ps.setInt(4, model.getSkillTwoLevel());
					ps.setInt(5, model.getSkillThreeLevel());
					ps.setFloat(6, model.getBloodAdd());
					ps.setFloat(7, model.getWuAttackAdd());
					ps.setFloat(8, model.getFaAttackAdd());
					ps.setFloat(9, model.getWuDefenseAdd());
					ps.setFloat(10, model.getFaDefenseAdd());
					ps.setInt(11, model.getWaveNum());
					ps.setInt(12, model.getPosition());
					ps.setInt(13, model.getIsBoss());
					ps.setInt(14, model.getQualityId());
					ps.setInt(15, model.getStarLevelId());
					ps.setString(16, model.getDescription());
					ps.setInt(17, 0);
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
				playerMemObj.dictDantaMonsterMap.remove(id);
			}
			String sql = "delete from Dict_Danta_Monster  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Danta_Monster where 1=1 ");
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

	public DictDantaMonster update(DictDantaMonster model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Danta_Monster set ");
			sql.append("cardId=?,cardLevel=?,skillOneLevel=?,skillTwoLevel=?,skillThreeLevel=?,bloodAdd=?,wuAttackAdd=?,faAttackAdd=?,wuDefenseAdd=?,faDefenseAdd=?,waveNum=?,position=?,isBoss=?,qualityId=?,starLevelId=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getCardId(),model.getCardLevel(),model.getSkillOneLevel(),model.getSkillTwoLevel(),model.getSkillThreeLevel(),model.getBloodAdd(),model.getWuAttackAdd(),model.getFaAttackAdd(),model.getWuDefenseAdd(),model.getFaDefenseAdd(),model.getWaveNum(),model.getPosition(),model.getIsBoss(),model.getQualityId(),model.getStarLevelId(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictDantaMonsterMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictDantaMonster getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictDantaMonster model = playerMemObj.dictDantaMonsterMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Danta_Monster where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictDantaMonsterMap.put(id, (DictDantaMonster) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Danta_Monster where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Danta_Monster where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictDantaMonsterMap.put(id, (DictDantaMonster) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictDantaMonsterMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Danta_Monster where id=?";
				Object[] params = new Object[]{id};
				DictDantaMonster model = ( DictDantaMonster) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictDantaMonster> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Danta_Monster ");
		}else {
			sql = new StringBuffer("select * from Dict_Danta_Monster ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictDantaMonster> dictDantaMonsterList = (List<DictDantaMonster>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictDantaMonsterList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Danta_Monster ");
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
	public List<DictDantaMonster> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Danta_Monster ");
			}else {
				sql = new StringBuffer("select * from Dict_Danta_Monster ");
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
				return (List<DictDantaMonster>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Danta_Monster where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Danta_Monster");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Danta_Monster ");
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
	public List<DictDantaMonster> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Danta_Monster a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Danta_Monster a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictDantaMonster>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Danta_Monster a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Danta_Monster");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictDantaMonster> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictDantaMonster> modelList = new ArrayList<DictDantaMonster>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictDantaMonster model = playerMemObj.dictDantaMonsterMap.get(id);
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