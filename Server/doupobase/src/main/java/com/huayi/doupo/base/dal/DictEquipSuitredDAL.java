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
import com.huayi.doupo.base.model.DictEquipSuitred;

public class DictEquipSuitredDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictEquipSuitred dictEquipSuitred = new DictEquipSuitred();
			dictEquipSuitred.setId(rs.getInt("id"), 0);
			dictEquipSuitred.setName(rs.getString("name"), 0);
			dictEquipSuitred.setSuitEquipIdList(rs.getString("suitEquipIdList"), 0);
			dictEquipSuitred.setSuit0StarProp(rs.getString("suit0StarProp"), 0);
			dictEquipSuitred.setSuit1StarProp(rs.getString("suit1StarProp"), 0);
			dictEquipSuitred.setSuit2StarProp(rs.getString("suit2StarProp"), 0);
			dictEquipSuitred.setSuit3StarProp(rs.getString("suit3StarProp"), 0);
			dictEquipSuitred.setSuit4StarProp(rs.getString("suit4StarProp"), 0);
			dictEquipSuitred.setSuit5StarProp(rs.getString("suit5StarProp"), 0);
			dictEquipSuitred.setDescription(rs.getString("description"), 0);
			dictEquipSuitred.setVersion(rs.getInt("version"), 0);
			return dictEquipSuitred;
		}
	}

	public DictEquipSuitred add(final DictEquipSuitred model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Equip_Suit_red (");
			strSql.append("name,suitEquipIdList,suit0StarProp,suit1StarProp,suit2StarProp,suit3StarProp,suit4StarProp,suit5StarProp,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, model.getName());
					ps.setString(2, model.getSuitEquipIdList());
					ps.setString(3, model.getSuit0StarProp());
					ps.setString(4, model.getSuit1StarProp());
					ps.setString(5, model.getSuit2StarProp());
					ps.setString(6, model.getSuit3StarProp());
					ps.setString(7, model.getSuit4StarProp());
					ps.setString(8, model.getSuit5StarProp());
					ps.setString(9, model.getDescription());
					ps.setInt(10, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictEquipSuitredMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictEquipSuitred> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Equip_Suit_red (");
		sql.append("name,suitEquipIdList,suit0StarProp,suit1StarProp,suit2StarProp,suit3StarProp,suit4StarProp,suit5StarProp,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictEquipSuitred model = (DictEquipSuitred)list.get(i);
					ps.setString(1, model.getName());
					ps.setString(2, model.getSuitEquipIdList());
					ps.setString(3, model.getSuit0StarProp());
					ps.setString(4, model.getSuit1StarProp());
					ps.setString(5, model.getSuit2StarProp());
					ps.setString(6, model.getSuit3StarProp());
					ps.setString(7, model.getSuit4StarProp());
					ps.setString(8, model.getSuit5StarProp());
					ps.setString(9, model.getDescription());
					ps.setInt(10, 0);
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
				playerMemObj.dictEquipSuitredMap.remove(id);
			}
			String sql = "delete from Dict_Equip_Suit_red  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Equip_Suit_red where 1=1 ");
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

	public DictEquipSuitred update(DictEquipSuitred model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Equip_Suit_red set ");
			sql.append("name=?,suitEquipIdList=?,suit0StarProp=?,suit1StarProp=?,suit2StarProp=?,suit3StarProp=?,suit4StarProp=?,suit5StarProp=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getName(),model.getSuitEquipIdList(),model.getSuit0StarProp(),model.getSuit1StarProp(),model.getSuit2StarProp(),model.getSuit3StarProp(),model.getSuit4StarProp(),model.getSuit5StarProp(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictEquipSuitredMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictEquipSuitred getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictEquipSuitred model = playerMemObj.dictEquipSuitredMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Equip_Suit_red where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictEquipSuitredMap.put(id, (DictEquipSuitred) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Equip_Suit_red where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Equip_Suit_red where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictEquipSuitredMap.put(id, (DictEquipSuitred) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictEquipSuitredMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Equip_Suit_red where id=?";
				Object[] params = new Object[]{id};
				DictEquipSuitred model = ( DictEquipSuitred) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictEquipSuitred> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Equip_Suit_red ");
		}else {
			sql = new StringBuffer("select * from Dict_Equip_Suit_red ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictEquipSuitred> dictEquipSuitredList = (List<DictEquipSuitred>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictEquipSuitredList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Equip_Suit_red ");
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
	public List<DictEquipSuitred> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Equip_Suit_red ");
			}else {
				sql = new StringBuffer("select * from Dict_Equip_Suit_red ");
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
				return (List<DictEquipSuitred>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Equip_Suit_red where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Equip_Suit_red");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Equip_Suit_red ");
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
	public List<DictEquipSuitred> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Equip_Suit_red a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Equip_Suit_red a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictEquipSuitred>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Equip_Suit_red a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Equip_Suit_red");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictEquipSuitred> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictEquipSuitred> modelList = new ArrayList<DictEquipSuitred>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictEquipSuitred model = playerMemObj.dictEquipSuitredMap.get(id);
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