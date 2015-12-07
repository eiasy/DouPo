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
import com.huayi.doupo.base.model.DictMagic;

public class DictMagicDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictMagic dictMagic = new DictMagic();
			dictMagic.setId(rs.getInt("id"), 0);
			dictMagic.setSmallUiId(rs.getInt("smallUiId"), 0);
			dictMagic.setBigUiId(rs.getInt("bigUiId"), 0);
			dictMagic.setName(rs.getString("name"), 0);
			dictMagic.setType(rs.getInt("type"), 0);
			dictMagic.setMagicQualityId(rs.getInt("magicQualityId"), 0);
			dictMagic.setGrade(rs.getInt("grade"), 0);
			dictMagic.setMagicLevelId(rs.getInt("magicLevelId"), 0);
			dictMagic.setSellCopper(rs.getInt("sellCopper"), 0);
			dictMagic.setExp(rs.getInt("exp"), 0);
			dictMagic.setValue1(rs.getString("value1"), 0);
			dictMagic.setValue2(rs.getString("value2"), 0);
			dictMagic.setValue3(rs.getString("value3"), 0);
			dictMagic.setValue4(rs.getString("value4"), 0);
			dictMagic.setValue5(rs.getString("value5"), 0);
			dictMagic.setValue6(rs.getString("value6"), 0);
			dictMagic.setChops(rs.getString("chops"), 0);
			dictMagic.setDescription(rs.getString("description"), 0);
			dictMagic.setVersion(rs.getInt("version"), 0);
			return dictMagic;
		}
	}

	public DictMagic add(final DictMagic model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Magic (");
			strSql.append("smallUiId,bigUiId,name,type,magicQualityId,grade,magicLevelId,sellCopper,exp,value1,value2,value3,value4,value5,value6,chops,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getSmallUiId());
					ps.setInt(2, model.getBigUiId());
					ps.setString(3, model.getName());
					ps.setInt(4, model.getType());
					ps.setInt(5, model.getMagicQualityId());
					ps.setInt(6, model.getGrade());
					ps.setInt(7, model.getMagicLevelId());
					ps.setInt(8, model.getSellCopper());
					ps.setInt(9, model.getExp());
					ps.setString(10, model.getValue1());
					ps.setString(11, model.getValue2());
					ps.setString(12, model.getValue3());
					ps.setString(13, model.getValue4());
					ps.setString(14, model.getValue5());
					ps.setString(15, model.getValue6());
					ps.setString(16, model.getChops());
					ps.setString(17, model.getDescription());
					ps.setInt(18, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictMagicMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictMagic> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Magic (");
		sql.append("smallUiId,bigUiId,name,type,magicQualityId,grade,magicLevelId,sellCopper,exp,value1,value2,value3,value4,value5,value6,chops,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictMagic model = (DictMagic)list.get(i);
					ps.setInt(1, model.getSmallUiId());
					ps.setInt(2, model.getBigUiId());
					ps.setString(3, model.getName());
					ps.setInt(4, model.getType());
					ps.setInt(5, model.getMagicQualityId());
					ps.setInt(6, model.getGrade());
					ps.setInt(7, model.getMagicLevelId());
					ps.setInt(8, model.getSellCopper());
					ps.setInt(9, model.getExp());
					ps.setString(10, model.getValue1());
					ps.setString(11, model.getValue2());
					ps.setString(12, model.getValue3());
					ps.setString(13, model.getValue4());
					ps.setString(14, model.getValue5());
					ps.setString(15, model.getValue6());
					ps.setString(16, model.getChops());
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
				playerMemObj.dictMagicMap.remove(id);
			}
			String sql = "delete from Dict_Magic  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Magic where 1=1 ");
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

	public DictMagic update(DictMagic model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Magic set ");
			sql.append("smallUiId=?,bigUiId=?,name=?,type=?,magicQualityId=?,grade=?,magicLevelId=?,sellCopper=?,exp=?,value1=?,value2=?,value3=?,value4=?,value5=?,value6=?,chops=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getSmallUiId(),model.getBigUiId(),model.getName(),model.getType(),model.getMagicQualityId(),model.getGrade(),model.getMagicLevelId(),model.getSellCopper(),model.getExp(),model.getValue1(),model.getValue2(),model.getValue3(),model.getValue4(),model.getValue5(),model.getValue6(),model.getChops(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictMagicMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictMagic getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictMagic model = playerMemObj.dictMagicMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Magic where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictMagicMap.put(id, (DictMagic) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Magic where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Magic where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictMagicMap.put(id, (DictMagic) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictMagicMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Magic where id=?";
				Object[] params = new Object[]{id};
				DictMagic model = ( DictMagic) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictMagic> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Magic ");
		}else {
			sql = new StringBuffer("select * from Dict_Magic ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictMagic> dictMagicList = (List<DictMagic>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictMagicList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Magic ");
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
	public List<DictMagic> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Magic ");
			}else {
				sql = new StringBuffer("select * from Dict_Magic ");
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
				return (List<DictMagic>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Magic where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Magic");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Magic ");
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
	public List<DictMagic> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Magic a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Magic a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictMagic>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Magic a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Magic");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictMagic> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictMagic> modelList = new ArrayList<DictMagic>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictMagic model = playerMemObj.dictMagicMap.get(id);
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