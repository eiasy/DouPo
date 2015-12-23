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
import com.huayi.doupo.base.model.InstUnionWarContributionRank;

public class InstUnionWarContributionRankDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstUnionWarContributionRank instUnionWarContributionRank = new InstUnionWarContributionRank();
			instUnionWarContributionRank.setId(rs.getInt("id"), 0);
			instUnionWarContributionRank.setInstUnionId(rs.getInt("instUnionId"), 0);
			instUnionWarContributionRank.setInstPlayerId(rs.getInt("instPlayerId"), 0);
			instUnionWarContributionRank.setInstPlayerLevel(rs.getInt("instPlayerLevel"), 0);
			instUnionWarContributionRank.setInstPlayerName(rs.getString("instPlayerName"), 0);
			instUnionWarContributionRank.setInstPlayerHead(rs.getInt("instPlayerHead"), 0);
			instUnionWarContributionRank.setGradeId(rs.getInt("gradeId"), 0);
			instUnionWarContributionRank.setIsMvp(rs.getInt("isMvp"), 0);
			instUnionWarContributionRank.setIsAlive(rs.getInt("isAlive"), 0);
			instUnionWarContributionRank.setLastType(rs.getInt("lastType"), 0);
			instUnionWarContributionRank.setFightCount(rs.getInt("fightCount"), 0);
			instUnionWarContributionRank.setKillCount(rs.getInt("killCount"), 0);
			instUnionWarContributionRank.setContribution(rs.getInt("contribution"), 0);
			instUnionWarContributionRank.setVersion(rs.getInt("version"), 0);
			return instUnionWarContributionRank;
		}
	}

	public InstUnionWarContributionRank add(final InstUnionWarContributionRank model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_UnionWar_ContributionRank (");
			strSql.append("instUnionId,instPlayerId,instPlayerLevel,instPlayerName,instPlayerHead,gradeId,isMvp,isAlive,lastType,fightCount,killCount,contribution,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getInstUnionId());
					ps.setInt(2, model.getInstPlayerId());
					ps.setInt(3, model.getInstPlayerLevel());
					ps.setString(4, model.getInstPlayerName());
					ps.setInt(5, model.getInstPlayerHead());
					ps.setInt(6, model.getGradeId());
					ps.setInt(7, model.getIsMvp());
					ps.setInt(8, model.getIsAlive());
					ps.setInt(9, model.getLastType());
					ps.setInt(10, model.getFightCount());
					ps.setInt(11, model.getKillCount());
					ps.setInt(12, model.getContribution());
					ps.setInt(13, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instUnionWarContributionRankMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstUnionWarContributionRank> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_UnionWar_ContributionRank (");
		sql.append("instUnionId,instPlayerId,instPlayerLevel,instPlayerName,instPlayerHead,gradeId,isMvp,isAlive,lastType,fightCount,killCount,contribution,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstUnionWarContributionRank model = (InstUnionWarContributionRank)list.get(i);
					ps.setInt(1, model.getInstUnionId());
					ps.setInt(2, model.getInstPlayerId());
					ps.setInt(3, model.getInstPlayerLevel());
					ps.setString(4, model.getInstPlayerName());
					ps.setInt(5, model.getInstPlayerHead());
					ps.setInt(6, model.getGradeId());
					ps.setInt(7, model.getIsMvp());
					ps.setInt(8, model.getIsAlive());
					ps.setInt(9, model.getLastType());
					ps.setInt(10, model.getFightCount());
					ps.setInt(11, model.getKillCount());
					ps.setInt(12, model.getContribution());
					ps.setInt(13, 0);
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
				playerMemObj.instUnionWarContributionRankMap.remove(id);
			}
			String sql = "delete from Inst_UnionWar_ContributionRank  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_UnionWar_ContributionRank where 1=1 ");
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

	public InstUnionWarContributionRank update(InstUnionWarContributionRank model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Inst_UnionWar_ContributionRank set ");
			sql.append("instUnionId=?,instPlayerId=?,instPlayerLevel=?,instPlayerName=?,instPlayerHead=?,gradeId=?,isMvp=?,isAlive=?,lastType=?,fightCount=?,killCount=?,contribution=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getInstUnionId(),model.getInstPlayerId(),model.getInstPlayerLevel(),model.getInstPlayerName(),model.getInstPlayerHead(),model.getGradeId(),model.getIsMvp(),model.getIsAlive(),model.getLastType(),model.getFightCount(),model.getKillCount(),model.getContribution(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instUnionWarContributionRankMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstUnionWarContributionRank getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstUnionWarContributionRank model = playerMemObj.instUnionWarContributionRankMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_UnionWar_ContributionRank where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instUnionWarContributionRankMap.put(id, (InstUnionWarContributionRank) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_UnionWar_ContributionRank where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_UnionWar_ContributionRank where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instUnionWarContributionRankMap.put(id, (InstUnionWarContributionRank) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instUnionWarContributionRankMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_UnionWar_ContributionRank where id=?";
				Object[] params = new Object[]{id};
				InstUnionWarContributionRank model = ( InstUnionWarContributionRank) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstUnionWarContributionRank> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_UnionWar_ContributionRank ");
		}else {
			sql = new StringBuffer("select * from Inst_UnionWar_ContributionRank ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstUnionWarContributionRank> instUnionWarContributionRankList = (List<InstUnionWarContributionRank>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instUnionWarContributionRankList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_UnionWar_ContributionRank ");
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
	public List<InstUnionWarContributionRank> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_UnionWar_ContributionRank ");
			}else {
				sql = new StringBuffer("select * from Inst_UnionWar_ContributionRank ");
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
				return (List<InstUnionWarContributionRank>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_UnionWar_ContributionRank where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_UnionWar_ContributionRank");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_UnionWar_ContributionRank ");
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
	public List<InstUnionWarContributionRank> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_UnionWar_ContributionRank a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_UnionWar_ContributionRank a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstUnionWarContributionRank>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_UnionWar_ContributionRank a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_UnionWar_ContributionRank");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstUnionWarContributionRank> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstUnionWarContributionRank> modelList = new ArrayList<InstUnionWarContributionRank>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstUnionWarContributionRank model = playerMemObj.instUnionWarContributionRankMap.get(id);
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