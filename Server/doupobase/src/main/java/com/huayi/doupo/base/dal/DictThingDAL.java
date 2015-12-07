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
import com.huayi.doupo.base.model.DictThing;

public class DictThingDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictThing dictThing = new DictThing();
			dictThing.setId(rs.getInt("id"), 0);
			dictThing.setName(rs.getString("name"), 0);
			dictThing.setLevel(rs.getInt("level"), 0);
			dictThing.setSmallUiId(rs.getInt("smallUiId"), 0);
			dictThing.setBigUiId(rs.getInt("bigUiId"), 0);
			dictThing.setOldBuyGold(rs.getInt("oldBuyGold"), 0);
			dictThing.setBuyGold(rs.getInt("buyGold"), 0);
			dictThing.setBuyCopper(rs.getInt("buyCopper"), 0);
			dictThing.setSellCopper(rs.getInt("sellCopper"), 0);
			dictThing.setThingTypeId(rs.getInt("thingTypeId"), 0);
			dictThing.setBagTypeId(rs.getInt("bagTypeId"), 0);
			dictThing.setFightPropId(rs.getInt("fightPropId"), 0);
			dictThing.setFightPropValue(rs.getInt("fightPropValue"), 0);
			dictThing.setValue(rs.getInt("value"), 0);
			dictThing.setEquipmentId(rs.getInt("equipmentId"), 0);
			dictThing.setOutBarrier(rs.getString("outBarrier"), 0);
			dictThing.setIsCanBuy(rs.getInt("isCanBuy"), 0);
			dictThing.setIsUse(rs.getInt("isUse"), 0);
			dictThing.setStoreBuyGoldGrow(rs.getInt("storeBuyGoldGrow"), 0);
			dictThing.setCoreConvCopper(rs.getInt("coreConvCopper"), 0);
			dictThing.setIndexOrder(rs.getInt("indexOrder"), 0);
			dictThing.setChildThings(rs.getString("childThings"), 0);
			dictThing.setSname(rs.getString("sname"), 0);
			dictThing.setBkGround(rs.getInt("bkGround"), 0);
			dictThing.setDescription(rs.getString("description"), 0);
			dictThing.setVersion(rs.getInt("version"), 0);
			return dictThing;
		}
	}

	public DictThing add(final DictThing model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Thing (");
			strSql.append("name,level,smallUiId,bigUiId,oldBuyGold,buyGold,buyCopper,sellCopper,thingTypeId,bagTypeId,fightPropId,fightPropValue,value,equipmentId,outBarrier,isCanBuy,isUse,storeBuyGoldGrow,coreConvCopper,indexOrder,childThings,sname,bkGround,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getLevel());
					ps.setInt(3, model.getSmallUiId());
					ps.setInt(4, model.getBigUiId());
					ps.setInt(5, model.getOldBuyGold());
					ps.setInt(6, model.getBuyGold());
					ps.setInt(7, model.getBuyCopper());
					ps.setInt(8, model.getSellCopper());
					ps.setInt(9, model.getThingTypeId());
					ps.setInt(10, model.getBagTypeId());
					ps.setInt(11, model.getFightPropId());
					ps.setInt(12, model.getFightPropValue());
					ps.setInt(13, model.getValue());
					ps.setInt(14, model.getEquipmentId());
					ps.setString(15, model.getOutBarrier());
					ps.setInt(16, model.getIsCanBuy());
					ps.setInt(17, model.getIsUse());
					ps.setInt(18, model.getStoreBuyGoldGrow());
					ps.setInt(19, model.getCoreConvCopper());
					ps.setInt(20, model.getIndexOrder());
					ps.setString(21, model.getChildThings());
					ps.setString(22, model.getSname());
					ps.setInt(23, model.getBkGround());
					ps.setString(24, model.getDescription());
					ps.setInt(25, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictThingMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictThing> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Thing (");
		sql.append("name,level,smallUiId,bigUiId,oldBuyGold,buyGold,buyCopper,sellCopper,thingTypeId,bagTypeId,fightPropId,fightPropValue,value,equipmentId,outBarrier,isCanBuy,isUse,storeBuyGoldGrow,coreConvCopper,indexOrder,childThings,sname,bkGround,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictThing model = (DictThing)list.get(i);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getLevel());
					ps.setInt(3, model.getSmallUiId());
					ps.setInt(4, model.getBigUiId());
					ps.setInt(5, model.getOldBuyGold());
					ps.setInt(6, model.getBuyGold());
					ps.setInt(7, model.getBuyCopper());
					ps.setInt(8, model.getSellCopper());
					ps.setInt(9, model.getThingTypeId());
					ps.setInt(10, model.getBagTypeId());
					ps.setInt(11, model.getFightPropId());
					ps.setInt(12, model.getFightPropValue());
					ps.setInt(13, model.getValue());
					ps.setInt(14, model.getEquipmentId());
					ps.setString(15, model.getOutBarrier());
					ps.setInt(16, model.getIsCanBuy());
					ps.setInt(17, model.getIsUse());
					ps.setInt(18, model.getStoreBuyGoldGrow());
					ps.setInt(19, model.getCoreConvCopper());
					ps.setInt(20, model.getIndexOrder());
					ps.setString(21, model.getChildThings());
					ps.setString(22, model.getSname());
					ps.setInt(23, model.getBkGround());
					ps.setString(24, model.getDescription());
					ps.setInt(25, 0);
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
				playerMemObj.dictThingMap.remove(id);
			}
			String sql = "delete from Dict_Thing  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Thing where 1=1 ");
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

	public DictThing update(DictThing model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Thing set ");
			sql.append("name=?,level=?,smallUiId=?,bigUiId=?,oldBuyGold=?,buyGold=?,buyCopper=?,sellCopper=?,thingTypeId=?,bagTypeId=?,fightPropId=?,fightPropValue=?,value=?,equipmentId=?,outBarrier=?,isCanBuy=?,isUse=?,storeBuyGoldGrow=?,coreConvCopper=?,indexOrder=?,childThings=?,sname=?,bkGround=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getName(),model.getLevel(),model.getSmallUiId(),model.getBigUiId(),model.getOldBuyGold(),model.getBuyGold(),model.getBuyCopper(),model.getSellCopper(),model.getThingTypeId(),model.getBagTypeId(),model.getFightPropId(),model.getFightPropValue(),model.getValue(),model.getEquipmentId(),model.getOutBarrier(),model.getIsCanBuy(),model.getIsUse(),model.getStoreBuyGoldGrow(),model.getCoreConvCopper(),model.getIndexOrder(),model.getChildThings(),model.getSname(),model.getBkGround(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictThingMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictThing getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictThing model = playerMemObj.dictThingMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Thing where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictThingMap.put(id, (DictThing) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Thing where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Thing where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictThingMap.put(id, (DictThing) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictThingMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Thing where id=?";
				Object[] params = new Object[]{id};
				DictThing model = ( DictThing) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictThing> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Thing ");
		}else {
			sql = new StringBuffer("select * from Dict_Thing ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictThing> dictThingList = (List<DictThing>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictThingList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Thing ");
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
	public List<DictThing> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Thing ");
			}else {
				sql = new StringBuffer("select * from Dict_Thing ");
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
				return (List<DictThing>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Thing where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Thing");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Thing ");
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
	public List<DictThing> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Thing a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Thing a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictThing>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Thing a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Thing");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictThing> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictThing> modelList = new ArrayList<DictThing>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictThing model = playerMemObj.dictThingMap.get(id);
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