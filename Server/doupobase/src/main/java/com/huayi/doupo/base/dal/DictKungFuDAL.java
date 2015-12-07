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
import com.huayi.doupo.base.model.DictKungFu;

public class DictKungFuDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictKungFu dictKungFu = new DictKungFu();
			dictKungFu.setId(rs.getInt("id"), 0);
			dictKungFu.setName(rs.getString("name"), 0);
			dictKungFu.setSmallUiId(rs.getInt("smallUiId"), 0);
			dictKungFu.setBigUiId(rs.getInt("bigUiId"), 0);
			dictKungFu.setKungFuTypeId(rs.getInt("kungFuTypeId"), 0);
			dictKungFu.setKungFuQualityId(rs.getInt("kungFuQualityId"), 0);
			dictKungFu.setExp(rs.getInt("exp"), 0);
			dictKungFu.setAcupoint(rs.getInt("acupoint"), 0);
			dictKungFu.setExpSum(rs.getInt("expSum"), 0);
			dictKungFu.setNextKungFu(rs.getInt("nextKungFu"), 0);
			dictKungFu.setTier(rs.getInt("tier"), 0);
			dictKungFu.setMaxNode(rs.getInt("maxNode"), 0);
			dictKungFu.setChops(rs.getString("chops"), 0);
			dictKungFu.setDescription(rs.getString("description"), 0);
			dictKungFu.setVersion(rs.getInt("version"), 0);
			return dictKungFu;
		}
	}

	public DictKungFu add(final DictKungFu model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_KungFu (");
			strSql.append("name,smallUiId,bigUiId,kungFuTypeId,kungFuQualityId,exp,acupoint,expSum,nextKungFu,tier,maxNode,chops,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getSmallUiId());
					ps.setInt(3, model.getBigUiId());
					ps.setInt(4, model.getKungFuTypeId());
					ps.setInt(5, model.getKungFuQualityId());
					ps.setInt(6, model.getExp());
					ps.setInt(7, model.getAcupoint());
					ps.setInt(8, model.getExpSum());
					ps.setInt(9, model.getNextKungFu());
					ps.setInt(10, model.getTier());
					ps.setInt(11, model.getMaxNode());
					ps.setString(12, model.getChops());
					ps.setString(13, model.getDescription());
					ps.setInt(14, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictKungFuMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictKungFu> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_KungFu (");
		sql.append("name,smallUiId,bigUiId,kungFuTypeId,kungFuQualityId,exp,acupoint,expSum,nextKungFu,tier,maxNode,chops,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictKungFu model = (DictKungFu)list.get(i);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getSmallUiId());
					ps.setInt(3, model.getBigUiId());
					ps.setInt(4, model.getKungFuTypeId());
					ps.setInt(5, model.getKungFuQualityId());
					ps.setInt(6, model.getExp());
					ps.setInt(7, model.getAcupoint());
					ps.setInt(8, model.getExpSum());
					ps.setInt(9, model.getNextKungFu());
					ps.setInt(10, model.getTier());
					ps.setInt(11, model.getMaxNode());
					ps.setString(12, model.getChops());
					ps.setString(13, model.getDescription());
					ps.setInt(14, 0);
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
				playerMemObj.dictKungFuMap.remove(id);
			}
			String sql = "delete from Dict_KungFu  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_KungFu where 1=1 ");
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

	public DictKungFu update(DictKungFu model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_KungFu set ");
			sql.append("name=?,smallUiId=?,bigUiId=?,kungFuTypeId=?,kungFuQualityId=?,exp=?,acupoint=?,expSum=?,nextKungFu=?,tier=?,maxNode=?,chops=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getName(),model.getSmallUiId(),model.getBigUiId(),model.getKungFuTypeId(),model.getKungFuQualityId(),model.getExp(),model.getAcupoint(),model.getExpSum(),model.getNextKungFu(),model.getTier(),model.getMaxNode(),model.getChops(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictKungFuMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictKungFu getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictKungFu model = playerMemObj.dictKungFuMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_KungFu where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictKungFuMap.put(id, (DictKungFu) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_KungFu where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_KungFu where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictKungFuMap.put(id, (DictKungFu) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictKungFuMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_KungFu where id=?";
				Object[] params = new Object[]{id};
				DictKungFu model = ( DictKungFu) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictKungFu> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_KungFu ");
		}else {
			sql = new StringBuffer("select * from Dict_KungFu ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictKungFu> dictKungFuList = (List<DictKungFu>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictKungFuList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_KungFu ");
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
	public List<DictKungFu> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_KungFu ");
			}else {
				sql = new StringBuffer("select * from Dict_KungFu ");
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
				return (List<DictKungFu>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_KungFu where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_KungFu");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_KungFu ");
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
	public List<DictKungFu> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_KungFu a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_KungFu a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictKungFu>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_KungFu a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_KungFu");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictKungFu> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictKungFu> modelList = new ArrayList<DictKungFu>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictKungFu model = playerMemObj.dictKungFuMap.get(id);
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