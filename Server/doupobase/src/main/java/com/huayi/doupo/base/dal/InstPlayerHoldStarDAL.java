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
import com.huayi.doupo.base.model.InstPlayerHoldStar;

public class InstPlayerHoldStarDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstPlayerHoldStar instPlayerHoldStar = new InstPlayerHoldStar();
			instPlayerHoldStar.setId(rs.getInt("id"), 0);
			instPlayerHoldStar.setInstPlayerId(rs.getInt("instPlayerId"), 0);
			instPlayerHoldStar.setHoldStarGradeId(rs.getInt("holdStarGradeId"), 0);
			instPlayerHoldStar.setStep(rs.getInt("step"), 0);
			instPlayerHoldStar.setStarNum(rs.getInt("starNum"), 0);
			instPlayerHoldStar.setHoldStarTimes(rs.getInt("holdStarTimes"), 0);
			instPlayerHoldStar.setHoldStarTime(rs.getString("holdStarTime"), 0);
			instPlayerHoldStar.setHoldStarFreeRefreshedTimes(rs.getInt("holdStarFreeRefreshedTimes"), 0);
			instPlayerHoldStar.setHoldStarNoFreeRefreshedTimes(rs.getInt("holdStarNoFreeRefreshedTimes"), 0);
			instPlayerHoldStar.setHoldStarRefreshedTime(rs.getString("holdStarRefreshedTime"), 0);
			instPlayerHoldStar.setRewardRefreshedTimes(rs.getInt("rewardRefreshedTimes"), 0);
			instPlayerHoldStar.setRewardRefreshedTime(rs.getString("rewardRefreshedTime"), 0);
			instPlayerHoldStar.setUpStars(rs.getString("upStars"), 0);
			instPlayerHoldStar.setDownStars(rs.getString("downStars"), 0);
			instPlayerHoldStar.setRewards(rs.getString("rewards"), 0);
			instPlayerHoldStar.setSysRefreshTime(rs.getString("sysRefreshTime"), 0);
			instPlayerHoldStar.setInsertTime(rs.getString("insertTime"), 0);
			instPlayerHoldStar.setUpdateTime(rs.getString("updateTime"), 0);
			instPlayerHoldStar.setVersion(rs.getInt("version"), 0);
			return instPlayerHoldStar;
		}
	}

	public InstPlayerHoldStar add(final InstPlayerHoldStar model, int instPlayerId) throws Exception {
		try {
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_Player_HoldStar (");
			strSql.append("instPlayerId,holdStarGradeId,step,starNum,holdStarTimes,holdStarTime,holdStarFreeRefreshedTimes,holdStarNoFreeRefreshedTimes,holdStarRefreshedTime,rewardRefreshedTimes,rewardRefreshedTime,upStars,downStars,rewards,sysRefreshTime,insertTime,updateTime,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getInstPlayerId());
					ps.setInt(2, model.getHoldStarGradeId());
					ps.setInt(3, model.getStep());
					ps.setInt(4, model.getStarNum());
					ps.setInt(5, model.getHoldStarTimes());
					ps.setString(6, model.getHoldStarTime());
					ps.setInt(7, model.getHoldStarFreeRefreshedTimes());
					ps.setInt(8, model.getHoldStarNoFreeRefreshedTimes());
					ps.setString(9, model.getHoldStarRefreshedTime());
					ps.setInt(10, model.getRewardRefreshedTimes());
					ps.setString(11, model.getRewardRefreshedTime());
					ps.setString(12, model.getUpStars());
					ps.setString(13, model.getDownStars());
					ps.setString(14, model.getRewards());
					ps.setString(15, model.getSysRefreshTime());
					ps.setString(16, updateTime);
					ps.setString(17, updateTime);
					ps.setInt(18, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			model.setInsertTime(updateTime, 0);
			model.setUpdateTime(updateTime, 0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instPlayerHoldStarMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstPlayerHoldStar> list) {
		final String updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_Player_HoldStar (");
		sql.append("instPlayerId,holdStarGradeId,step,starNum,holdStarTimes,holdStarTime,holdStarFreeRefreshedTimes,holdStarNoFreeRefreshedTimes,holdStarRefreshedTime,rewardRefreshedTimes,rewardRefreshedTime,upStars,downStars,rewards,sysRefreshTime,insertTime,updateTime,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstPlayerHoldStar model = (InstPlayerHoldStar)list.get(i);
					ps.setInt(1, model.getInstPlayerId());
					ps.setInt(2, model.getHoldStarGradeId());
					ps.setInt(3, model.getStep());
					ps.setInt(4, model.getStarNum());
					ps.setInt(5, model.getHoldStarTimes());
					ps.setString(6, model.getHoldStarTime());
					ps.setInt(7, model.getHoldStarFreeRefreshedTimes());
					ps.setInt(8, model.getHoldStarNoFreeRefreshedTimes());
					ps.setString(9, model.getHoldStarRefreshedTime());
					ps.setInt(10, model.getRewardRefreshedTimes());
					ps.setString(11, model.getRewardRefreshedTime());
					ps.setString(12, model.getUpStars());
					ps.setString(13, model.getDownStars());
					ps.setString(14, model.getRewards());
					ps.setString(15, model.getSysRefreshTime());
					ps.setString(16, updateTime);
					ps.setString(17, updateTime);
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
				playerMemObj.instPlayerHoldStarMap.remove(id);
			}
			String sql = "delete from Inst_Player_HoldStar  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_Player_HoldStar where 1=1 ");
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

	public InstPlayerHoldStar update(InstPlayerHoldStar model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuffer sql = new StringBuffer("update Inst_Player_HoldStar set ");
			sql.append("instPlayerId=?,holdStarGradeId=?,step=?,starNum=?,holdStarTimes=?,holdStarTime=?,holdStarFreeRefreshedTimes=?,holdStarNoFreeRefreshedTimes=?,holdStarRefreshedTime=?,rewardRefreshedTimes=?,rewardRefreshedTime=?,upStars=?,downStars=?,rewards=?,sysRefreshTime=?,insertTime=?,updateTime=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getInstPlayerId(),model.getHoldStarGradeId(),model.getStep(),model.getStarNum(),model.getHoldStarTimes(),model.getHoldStarTime(),model.getHoldStarFreeRefreshedTimes(),model.getHoldStarNoFreeRefreshedTimes(),model.getHoldStarRefreshedTime(),model.getRewardRefreshedTimes(),model.getRewardRefreshedTime(),model.getUpStars(),model.getDownStars(),model.getRewards(),model.getSysRefreshTime(),model.getInsertTime(),updateTime,version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				model.setUpdateTime(updateTime, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instPlayerHoldStarMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstPlayerHoldStar getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstPlayerHoldStar model = playerMemObj.instPlayerHoldStarMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_Player_HoldStar where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instPlayerHoldStarMap.put(id, (InstPlayerHoldStar) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_Player_HoldStar where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_Player_HoldStar where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instPlayerHoldStarMap.put(id, (InstPlayerHoldStar) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instPlayerHoldStarMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_Player_HoldStar where id=?";
				Object[] params = new Object[]{id};
				InstPlayerHoldStar model = ( InstPlayerHoldStar) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstPlayerHoldStar> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_Player_HoldStar ");
		}else {
			sql = new StringBuffer("select * from Inst_Player_HoldStar ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstPlayerHoldStar> instPlayerHoldStarList = (List<InstPlayerHoldStar>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instPlayerHoldStarList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_Player_HoldStar ");
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
	public List<InstPlayerHoldStar> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_Player_HoldStar ");
			}else {
				sql = new StringBuffer("select * from Inst_Player_HoldStar ");
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
				return (List<InstPlayerHoldStar>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_HoldStar where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_HoldStar");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_Player_HoldStar ");
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
	public List<InstPlayerHoldStar> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_Player_HoldStar a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_Player_HoldStar a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstPlayerHoldStar>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_Player_HoldStar a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_Player_HoldStar");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstPlayerHoldStar> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstPlayerHoldStar> modelList = new ArrayList<InstPlayerHoldStar>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstPlayerHoldStar model = playerMemObj.instPlayerHoldStarMap.get(id);
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