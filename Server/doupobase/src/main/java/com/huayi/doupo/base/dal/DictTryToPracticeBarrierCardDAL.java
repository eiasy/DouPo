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
import com.huayi.doupo.base.model.DictTryToPracticeBarrierCard;

public class DictTryToPracticeBarrierCardDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictTryToPracticeBarrierCard dictTryToPracticeBarrierCard = new DictTryToPracticeBarrierCard();
			dictTryToPracticeBarrierCard.setId(rs.getInt("id"), 0);
			dictTryToPracticeBarrierCard.setTryToPracticeId(rs.getInt("tryToPracticeId"), 0);
			dictTryToPracticeBarrierCard.setCardId(rs.getInt("cardId"), 0);
			dictTryToPracticeBarrierCard.setCardLevel(rs.getInt("cardLevel"), 0);
			dictTryToPracticeBarrierCard.setSkillOneLevel(rs.getInt("skillOneLevel"), 0);
			dictTryToPracticeBarrierCard.setSkillTwoLevel(rs.getInt("skillTwoLevel"), 0);
			dictTryToPracticeBarrierCard.setSkillThreeLevel(rs.getInt("skillThreeLevel"), 0);
			dictTryToPracticeBarrierCard.setBloodAdd(rs.getFloat("bloodAdd"), 0);
			dictTryToPracticeBarrierCard.setWuAttackAdd(rs.getFloat("wuAttackAdd"), 0);
			dictTryToPracticeBarrierCard.setFaAttackAdd(rs.getFloat("faAttackAdd"), 0);
			dictTryToPracticeBarrierCard.setWuDefenseAdd(rs.getFloat("wuDefenseAdd"), 0);
			dictTryToPracticeBarrierCard.setFaDefenseAdd(rs.getFloat("faDefenseAdd"), 0);
			dictTryToPracticeBarrierCard.setWaveNum(rs.getInt("waveNum"), 0);
			dictTryToPracticeBarrierCard.setPosition(rs.getInt("position"), 0);
			dictTryToPracticeBarrierCard.setIsBoss(rs.getInt("isBoss"), 0);
			dictTryToPracticeBarrierCard.setQualityId(rs.getInt("qualityId"), 0);
			dictTryToPracticeBarrierCard.setStarLevelId(rs.getInt("starLevelId"), 0);
			dictTryToPracticeBarrierCard.setDescription(rs.getString("description"), 0);
			dictTryToPracticeBarrierCard.setVersion(rs.getInt("version"), 0);
			return dictTryToPracticeBarrierCard;
		}
	}

	public DictTryToPracticeBarrierCard add(final DictTryToPracticeBarrierCard model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_TryToPractice_BarrierCard (");
			strSql.append("tryToPracticeId,cardId,cardLevel,skillOneLevel,skillTwoLevel,skillThreeLevel,bloodAdd,wuAttackAdd,faAttackAdd,wuDefenseAdd,faDefenseAdd,waveNum,position,isBoss,qualityId,starLevelId,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getTryToPracticeId());
					ps.setInt(2, model.getCardId());
					ps.setInt(3, model.getCardLevel());
					ps.setInt(4, model.getSkillOneLevel());
					ps.setInt(5, model.getSkillTwoLevel());
					ps.setInt(6, model.getSkillThreeLevel());
					ps.setFloat(7, model.getBloodAdd());
					ps.setFloat(8, model.getWuAttackAdd());
					ps.setFloat(9, model.getFaAttackAdd());
					ps.setFloat(10, model.getWuDefenseAdd());
					ps.setFloat(11, model.getFaDefenseAdd());
					ps.setInt(12, model.getWaveNum());
					ps.setInt(13, model.getPosition());
					ps.setInt(14, model.getIsBoss());
					ps.setInt(15, model.getQualityId());
					ps.setInt(16, model.getStarLevelId());
					ps.setString(17, model.getDescription());
					ps.setInt(18, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictTryToPracticeBarrierCardMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictTryToPracticeBarrierCard> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_TryToPractice_BarrierCard (");
		sql.append("tryToPracticeId,cardId,cardLevel,skillOneLevel,skillTwoLevel,skillThreeLevel,bloodAdd,wuAttackAdd,faAttackAdd,wuDefenseAdd,faDefenseAdd,waveNum,position,isBoss,qualityId,starLevelId,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictTryToPracticeBarrierCard model = (DictTryToPracticeBarrierCard)list.get(i);
					ps.setInt(1, model.getTryToPracticeId());
					ps.setInt(2, model.getCardId());
					ps.setInt(3, model.getCardLevel());
					ps.setInt(4, model.getSkillOneLevel());
					ps.setInt(5, model.getSkillTwoLevel());
					ps.setInt(6, model.getSkillThreeLevel());
					ps.setFloat(7, model.getBloodAdd());
					ps.setFloat(8, model.getWuAttackAdd());
					ps.setFloat(9, model.getFaAttackAdd());
					ps.setFloat(10, model.getWuDefenseAdd());
					ps.setFloat(11, model.getFaDefenseAdd());
					ps.setInt(12, model.getWaveNum());
					ps.setInt(13, model.getPosition());
					ps.setInt(14, model.getIsBoss());
					ps.setInt(15, model.getQualityId());
					ps.setInt(16, model.getStarLevelId());
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
				playerMemObj.dictTryToPracticeBarrierCardMap.remove(id);
			}
			String sql = "delete from Dict_TryToPractice_BarrierCard  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_TryToPractice_BarrierCard where 1=1 ");
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

	public DictTryToPracticeBarrierCard update(DictTryToPracticeBarrierCard model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_TryToPractice_BarrierCard set ");
			sql.append("tryToPracticeId=?,cardId=?,cardLevel=?,skillOneLevel=?,skillTwoLevel=?,skillThreeLevel=?,bloodAdd=?,wuAttackAdd=?,faAttackAdd=?,wuDefenseAdd=?,faDefenseAdd=?,waveNum=?,position=?,isBoss=?,qualityId=?,starLevelId=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getTryToPracticeId(),model.getCardId(),model.getCardLevel(),model.getSkillOneLevel(),model.getSkillTwoLevel(),model.getSkillThreeLevel(),model.getBloodAdd(),model.getWuAttackAdd(),model.getFaAttackAdd(),model.getWuDefenseAdd(),model.getFaDefenseAdd(),model.getWaveNum(),model.getPosition(),model.getIsBoss(),model.getQualityId(),model.getStarLevelId(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictTryToPracticeBarrierCardMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictTryToPracticeBarrierCard getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictTryToPracticeBarrierCard model = playerMemObj.dictTryToPracticeBarrierCardMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_TryToPractice_BarrierCard where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictTryToPracticeBarrierCardMap.put(id, (DictTryToPracticeBarrierCard) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_TryToPractice_BarrierCard where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_TryToPractice_BarrierCard where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictTryToPracticeBarrierCardMap.put(id, (DictTryToPracticeBarrierCard) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictTryToPracticeBarrierCardMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_TryToPractice_BarrierCard where id=?";
				Object[] params = new Object[]{id};
				DictTryToPracticeBarrierCard model = ( DictTryToPracticeBarrierCard) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictTryToPracticeBarrierCard> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_TryToPractice_BarrierCard ");
		}else {
			sql = new StringBuffer("select * from Dict_TryToPractice_BarrierCard ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictTryToPracticeBarrierCard> dictTryToPracticeBarrierCardList = (List<DictTryToPracticeBarrierCard>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictTryToPracticeBarrierCardList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_TryToPractice_BarrierCard ");
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
	public List<DictTryToPracticeBarrierCard> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_TryToPractice_BarrierCard ");
			}else {
				sql = new StringBuffer("select * from Dict_TryToPractice_BarrierCard ");
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
				return (List<DictTryToPracticeBarrierCard>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_TryToPractice_BarrierCard where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_TryToPractice_BarrierCard");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_TryToPractice_BarrierCard ");
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
	public List<DictTryToPracticeBarrierCard> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_TryToPractice_BarrierCard a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_TryToPractice_BarrierCard a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictTryToPracticeBarrierCard>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_TryToPractice_BarrierCard a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_TryToPractice_BarrierCard");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictTryToPracticeBarrierCard> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictTryToPracticeBarrierCard> modelList = new ArrayList<DictTryToPracticeBarrierCard>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictTryToPracticeBarrierCard model = playerMemObj.dictTryToPracticeBarrierCardMap.get(id);
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