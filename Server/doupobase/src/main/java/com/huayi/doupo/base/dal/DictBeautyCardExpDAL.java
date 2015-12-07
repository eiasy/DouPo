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
import com.huayi.doupo.base.model.DictBeautyCardExp;

public class DictBeautyCardExpDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictBeautyCardExp dictBeautyCardExp = new DictBeautyCardExp();
			dictBeautyCardExp.setId(rs.getInt("id"), 0);
			dictBeautyCardExp.setExp(rs.getInt("exp"), 0);
			dictBeautyCardExp.setVeryLow(rs.getInt("veryLow"), 0);
			dictBeautyCardExp.setVeryLowPr(rs.getFloat("veryLowPr"), 0);
			dictBeautyCardExp.setVeryLowPrAdd(rs.getFloat("veryLowPrAdd"), 0);
			dictBeautyCardExp.setLow(rs.getFloat("low"), 0);
			dictBeautyCardExp.setLowPr(rs.getFloat("lowPr"), 0);
			dictBeautyCardExp.setLowPrAdd(rs.getFloat("lowPrAdd"), 0);
			dictBeautyCardExp.setCentre(rs.getFloat("centre"), 0);
			dictBeautyCardExp.setCentrePr(rs.getFloat("centrePr"), 0);
			dictBeautyCardExp.setCentrePrAdd(rs.getFloat("centrePrAdd"), 0);
			dictBeautyCardExp.setTall(rs.getFloat("tall"), 0);
			dictBeautyCardExp.setTallPr(rs.getFloat("tallPr"), 0);
			dictBeautyCardExp.setTallPrAdd(rs.getFloat("tallPrAdd"), 0);
			dictBeautyCardExp.setDescription(rs.getString("description"), 0);
			dictBeautyCardExp.setVersion(rs.getInt("version"), 0);
			return dictBeautyCardExp;
		}
	}

	public DictBeautyCardExp add(final DictBeautyCardExp model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Beauty_CardExp (");
			strSql.append("exp,veryLow,veryLowPr,veryLowPrAdd,low,lowPr,lowPrAdd,centre,centrePr,centrePrAdd,tall,tallPr,tallPrAdd,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getExp());
					ps.setInt(2, model.getVeryLow());
					ps.setFloat(3, model.getVeryLowPr());
					ps.setFloat(4, model.getVeryLowPrAdd());
					ps.setFloat(5, model.getLow());
					ps.setFloat(6, model.getLowPr());
					ps.setFloat(7, model.getLowPrAdd());
					ps.setFloat(8, model.getCentre());
					ps.setFloat(9, model.getCentrePr());
					ps.setFloat(10, model.getCentrePrAdd());
					ps.setFloat(11, model.getTall());
					ps.setFloat(12, model.getTallPr());
					ps.setFloat(13, model.getTallPrAdd());
					ps.setString(14, model.getDescription());
					ps.setInt(15, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictBeautyCardExpMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictBeautyCardExp> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Beauty_CardExp (");
		sql.append("exp,veryLow,veryLowPr,veryLowPrAdd,low,lowPr,lowPrAdd,centre,centrePr,centrePrAdd,tall,tallPr,tallPrAdd,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictBeautyCardExp model = (DictBeautyCardExp)list.get(i);
					ps.setInt(1, model.getExp());
					ps.setInt(2, model.getVeryLow());
					ps.setFloat(3, model.getVeryLowPr());
					ps.setFloat(4, model.getVeryLowPrAdd());
					ps.setFloat(5, model.getLow());
					ps.setFloat(6, model.getLowPr());
					ps.setFloat(7, model.getLowPrAdd());
					ps.setFloat(8, model.getCentre());
					ps.setFloat(9, model.getCentrePr());
					ps.setFloat(10, model.getCentrePrAdd());
					ps.setFloat(11, model.getTall());
					ps.setFloat(12, model.getTallPr());
					ps.setFloat(13, model.getTallPrAdd());
					ps.setString(14, model.getDescription());
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
				playerMemObj.dictBeautyCardExpMap.remove(id);
			}
			String sql = "delete from Dict_Beauty_CardExp  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Beauty_CardExp where 1=1 ");
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

	public DictBeautyCardExp update(DictBeautyCardExp model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Beauty_CardExp set ");
			sql.append("exp=?,veryLow=?,veryLowPr=?,veryLowPrAdd=?,low=?,lowPr=?,lowPrAdd=?,centre=?,centrePr=?,centrePrAdd=?,tall=?,tallPr=?,tallPrAdd=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getExp(),model.getVeryLow(),model.getVeryLowPr(),model.getVeryLowPrAdd(),model.getLow(),model.getLowPr(),model.getLowPrAdd(),model.getCentre(),model.getCentrePr(),model.getCentrePrAdd(),model.getTall(),model.getTallPr(),model.getTallPrAdd(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictBeautyCardExpMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictBeautyCardExp getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictBeautyCardExp model = playerMemObj.dictBeautyCardExpMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Beauty_CardExp where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictBeautyCardExpMap.put(id, (DictBeautyCardExp) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Beauty_CardExp where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Beauty_CardExp where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictBeautyCardExpMap.put(id, (DictBeautyCardExp) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictBeautyCardExpMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Beauty_CardExp where id=?";
				Object[] params = new Object[]{id};
				DictBeautyCardExp model = ( DictBeautyCardExp) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictBeautyCardExp> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Beauty_CardExp ");
		}else {
			sql = new StringBuffer("select * from Dict_Beauty_CardExp ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictBeautyCardExp> dictBeautyCardExpList = (List<DictBeautyCardExp>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictBeautyCardExpList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Beauty_CardExp ");
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
	public List<DictBeautyCardExp> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Beauty_CardExp ");
			}else {
				sql = new StringBuffer("select * from Dict_Beauty_CardExp ");
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
				return (List<DictBeautyCardExp>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Beauty_CardExp where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Beauty_CardExp");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Beauty_CardExp ");
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
	public List<DictBeautyCardExp> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Beauty_CardExp a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Beauty_CardExp a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictBeautyCardExp>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Beauty_CardExp a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Beauty_CardExp");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictBeautyCardExp> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictBeautyCardExp> modelList = new ArrayList<DictBeautyCardExp>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictBeautyCardExp model = playerMemObj.dictBeautyCardExpMap.get(id);
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