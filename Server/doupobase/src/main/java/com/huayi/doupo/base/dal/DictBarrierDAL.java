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
import com.huayi.doupo.base.model.DictBarrier;

public class DictBarrierDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictBarrier dictBarrier = new DictBarrier();
			dictBarrier.setId(rs.getInt("id"), 0);
			dictBarrier.setName(rs.getString("name"), 0);
			dictBarrier.setChapterId(rs.getInt("chapterId"), 0);
			dictBarrier.setBarrierId(rs.getInt("barrierId"), 0);
			dictBarrier.setType(rs.getInt("type"), 0);
			dictBarrier.setX(rs.getInt("x"), 0);
			dictBarrier.setY(rs.getInt("y"), 0);
			dictBarrier.setStarNum(rs.getInt("starNum"), 0);
			dictBarrier.setCardId(rs.getInt("cardId"), 0);
			dictBarrier.setEnergy(rs.getInt("energy"), 0);
			dictBarrier.setFightNum(rs.getInt("fightNum"), 0);
			dictBarrier.setResetNum(rs.getInt("resetNum"), 0);
			dictBarrier.setThings(rs.getString("things"), 0);
			dictBarrier.setOpenLevel(rs.getInt("openLevel"), 0);
			dictBarrier.setWelfareBox(rs.getString("welfareBox"), 0);
			dictBarrier.setBoxX(rs.getInt("boxX"), 0);
			dictBarrier.setBoxY(rs.getInt("boxY"), 0);
			dictBarrier.setDescription(rs.getString("description"), 0);
			dictBarrier.setVersion(rs.getInt("version"), 0);
			return dictBarrier;
		}
	}

	public DictBarrier add(final DictBarrier model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Barrier (");
			strSql.append("name,chapterId,barrierId,type,x,y,starNum,cardId,energy,fightNum,resetNum,things,openLevel,welfareBox,boxX,boxY,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getChapterId());
					ps.setInt(3, model.getBarrierId());
					ps.setInt(4, model.getType());
					ps.setInt(5, model.getX());
					ps.setInt(6, model.getY());
					ps.setInt(7, model.getStarNum());
					ps.setInt(8, model.getCardId());
					ps.setInt(9, model.getEnergy());
					ps.setInt(10, model.getFightNum());
					ps.setInt(11, model.getResetNum());
					ps.setString(12, model.getThings());
					ps.setInt(13, model.getOpenLevel());
					ps.setString(14, model.getWelfareBox());
					ps.setInt(15, model.getBoxX());
					ps.setInt(16, model.getBoxY());
					ps.setString(17, model.getDescription());
					ps.setInt(18, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictBarrierMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictBarrier> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Barrier (");
		sql.append("name,chapterId,barrierId,type,x,y,starNum,cardId,energy,fightNum,resetNum,things,openLevel,welfareBox,boxX,boxY,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictBarrier model = (DictBarrier)list.get(i);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getChapterId());
					ps.setInt(3, model.getBarrierId());
					ps.setInt(4, model.getType());
					ps.setInt(5, model.getX());
					ps.setInt(6, model.getY());
					ps.setInt(7, model.getStarNum());
					ps.setInt(8, model.getCardId());
					ps.setInt(9, model.getEnergy());
					ps.setInt(10, model.getFightNum());
					ps.setInt(11, model.getResetNum());
					ps.setString(12, model.getThings());
					ps.setInt(13, model.getOpenLevel());
					ps.setString(14, model.getWelfareBox());
					ps.setInt(15, model.getBoxX());
					ps.setInt(16, model.getBoxY());
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
				playerMemObj.dictBarrierMap.remove(id);
			}
			String sql = "delete from Dict_Barrier  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Barrier where 1=1 ");
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

	public DictBarrier update(DictBarrier model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Barrier set ");
			sql.append("name=?,chapterId=?,barrierId=?,type=?,x=?,y=?,starNum=?,cardId=?,energy=?,fightNum=?,resetNum=?,things=?,openLevel=?,welfareBox=?,boxX=?,boxY=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getName(),model.getChapterId(),model.getBarrierId(),model.getType(),model.getX(),model.getY(),model.getStarNum(),model.getCardId(),model.getEnergy(),model.getFightNum(),model.getResetNum(),model.getThings(),model.getOpenLevel(),model.getWelfareBox(),model.getBoxX(),model.getBoxY(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictBarrierMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictBarrier getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictBarrier model = playerMemObj.dictBarrierMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Barrier where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictBarrierMap.put(id, (DictBarrier) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Barrier where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Barrier where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictBarrierMap.put(id, (DictBarrier) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictBarrierMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Barrier where id=?";
				Object[] params = new Object[]{id};
				DictBarrier model = ( DictBarrier) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictBarrier> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Barrier ");
		}else {
			sql = new StringBuffer("select * from Dict_Barrier ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictBarrier> dictBarrierList = (List<DictBarrier>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictBarrierList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Barrier ");
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
	public List<DictBarrier> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Barrier ");
			}else {
				sql = new StringBuffer("select * from Dict_Barrier ");
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
				return (List<DictBarrier>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Barrier where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Barrier");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Barrier ");
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
	public List<DictBarrier> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Barrier a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Barrier a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictBarrier>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Barrier a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Barrier");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictBarrier> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictBarrier> modelList = new ArrayList<DictBarrier>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictBarrier model = playerMemObj.dictBarrierMap.get(id);
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