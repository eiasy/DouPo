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
import com.huayi.doupo.base.model.InstUnionWarAgainst;

public class InstUnionWarAgainstDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstUnionWarAgainst instUnionWarAgainst = new InstUnionWarAgainst();
			instUnionWarAgainst.setId(rs.getInt("id"), 0);
			instUnionWarAgainst.setAgainstIndex(rs.getInt("againstIndex"), 0);
			instUnionWarAgainst.setIsAWin(rs.getInt("isAWin"), 0);
			instUnionWarAgainst.setTeamA(rs.getInt("teamA"), 0);
			instUnionWarAgainst.setTeamNameA(rs.getString("teamNameA"), 0);
			instUnionWarAgainst.setMvpNameA(rs.getString("mvpNameA"), 0);
			instUnionWarAgainst.setABattlefieldScore1(rs.getInt("aBattlefieldScore1"), 0);
			instUnionWarAgainst.setABattlefieldScore2(rs.getInt("aBattlefieldScore2"), 0);
			instUnionWarAgainst.setABattlefieldScore3(rs.getInt("aBattlefieldScore3"), 0);
			instUnionWarAgainst.setABattlefieldScore4(rs.getInt("aBattlefieldScore4"), 0);
			instUnionWarAgainst.setTeamB(rs.getInt("teamB"), 0);
			instUnionWarAgainst.setTeamNameB(rs.getString("teamNameB"), 0);
			instUnionWarAgainst.setMvpNameB(rs.getString("mvpNameB"), 0);
			instUnionWarAgainst.setBBattlefieldScore1(rs.getInt("bBattlefieldScore1"), 0);
			instUnionWarAgainst.setBBattlefieldScore2(rs.getInt("bBattlefieldScore2"), 0);
			instUnionWarAgainst.setBBattlefieldScore3(rs.getInt("bBattlefieldScore3"), 0);
			instUnionWarAgainst.setBBattlefieldScore4(rs.getInt("bBattlefieldScore4"), 0);
			instUnionWarAgainst.setVersion(rs.getInt("version"), 0);
			return instUnionWarAgainst;
		}
	}

	public InstUnionWarAgainst add(final InstUnionWarAgainst model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_UnionWar_Against (");
			strSql.append("againstIndex,isAWin,teamA,teamNameA,mvpNameA,aBattlefieldScore1,aBattlefieldScore2,aBattlefieldScore3,aBattlefieldScore4,teamB,teamNameB,mvpNameB,bBattlefieldScore1,bBattlefieldScore2,bBattlefieldScore3,bBattlefieldScore4,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getAgainstIndex());
					ps.setInt(2, model.getIsAWin());
					ps.setInt(3, model.getTeamA());
					ps.setString(4, model.getTeamNameA());
					ps.setString(5, model.getMvpNameA());
					ps.setInt(6, model.getABattlefieldScore1());
					ps.setInt(7, model.getABattlefieldScore2());
					ps.setInt(8, model.getABattlefieldScore3());
					ps.setInt(9, model.getABattlefieldScore4());
					ps.setInt(10, model.getTeamB());
					ps.setString(11, model.getTeamNameB());
					ps.setString(12, model.getMvpNameB());
					ps.setInt(13, model.getBBattlefieldScore1());
					ps.setInt(14, model.getBBattlefieldScore2());
					ps.setInt(15, model.getBBattlefieldScore3());
					ps.setInt(16, model.getBBattlefieldScore4());
					ps.setInt(17, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instUnionWarAgainstMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstUnionWarAgainst> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_UnionWar_Against (");
		sql.append("againstIndex,isAWin,teamA,teamNameA,mvpNameA,aBattlefieldScore1,aBattlefieldScore2,aBattlefieldScore3,aBattlefieldScore4,teamB,teamNameB,mvpNameB,bBattlefieldScore1,bBattlefieldScore2,bBattlefieldScore3,bBattlefieldScore4,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstUnionWarAgainst model = (InstUnionWarAgainst)list.get(i);
					ps.setInt(1, model.getAgainstIndex());
					ps.setInt(2, model.getIsAWin());
					ps.setInt(3, model.getTeamA());
					ps.setString(4, model.getTeamNameA());
					ps.setString(5, model.getMvpNameA());
					ps.setInt(6, model.getABattlefieldScore1());
					ps.setInt(7, model.getABattlefieldScore2());
					ps.setInt(8, model.getABattlefieldScore3());
					ps.setInt(9, model.getABattlefieldScore4());
					ps.setInt(10, model.getTeamB());
					ps.setString(11, model.getTeamNameB());
					ps.setString(12, model.getMvpNameB());
					ps.setInt(13, model.getBBattlefieldScore1());
					ps.setInt(14, model.getBBattlefieldScore2());
					ps.setInt(15, model.getBBattlefieldScore3());
					ps.setInt(16, model.getBBattlefieldScore4());
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
				playerMemObj.instUnionWarAgainstMap.remove(id);
			}
			String sql = "delete from Inst_UnionWar_Against  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_UnionWar_Against where 1=1 ");
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

	public InstUnionWarAgainst update(InstUnionWarAgainst model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Inst_UnionWar_Against set ");
			sql.append("againstIndex=?,isAWin=?,teamA=?,teamNameA=?,mvpNameA=?,aBattlefieldScore1=?,aBattlefieldScore2=?,aBattlefieldScore3=?,aBattlefieldScore4=?,teamB=?,teamNameB=?,mvpNameB=?,bBattlefieldScore1=?,bBattlefieldScore2=?,bBattlefieldScore3=?,bBattlefieldScore4=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getAgainstIndex(),model.getIsAWin(),model.getTeamA(),model.getTeamNameA(),model.getMvpNameA(),model.getABattlefieldScore1(),model.getABattlefieldScore2(),model.getABattlefieldScore3(),model.getABattlefieldScore4(),model.getTeamB(),model.getTeamNameB(),model.getMvpNameB(),model.getBBattlefieldScore1(),model.getBBattlefieldScore2(),model.getBBattlefieldScore3(),model.getBBattlefieldScore4(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instUnionWarAgainstMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstUnionWarAgainst getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstUnionWarAgainst model = playerMemObj.instUnionWarAgainstMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_UnionWar_Against where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instUnionWarAgainstMap.put(id, (InstUnionWarAgainst) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_UnionWar_Against where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_UnionWar_Against where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instUnionWarAgainstMap.put(id, (InstUnionWarAgainst) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instUnionWarAgainstMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_UnionWar_Against where id=?";
				Object[] params = new Object[]{id};
				InstUnionWarAgainst model = ( InstUnionWarAgainst) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstUnionWarAgainst> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_UnionWar_Against ");
		}else {
			sql = new StringBuffer("select * from Inst_UnionWar_Against ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstUnionWarAgainst> instUnionWarAgainstList = (List<InstUnionWarAgainst>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instUnionWarAgainstList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_UnionWar_Against ");
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
	public List<InstUnionWarAgainst> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_UnionWar_Against ");
			}else {
				sql = new StringBuffer("select * from Inst_UnionWar_Against ");
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
				return (List<InstUnionWarAgainst>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_UnionWar_Against where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_UnionWar_Against");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_UnionWar_Against ");
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
	public List<InstUnionWarAgainst> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_UnionWar_Against a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_UnionWar_Against a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstUnionWarAgainst>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_UnionWar_Against a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_UnionWar_Against");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstUnionWarAgainst> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstUnionWarAgainst> modelList = new ArrayList<InstUnionWarAgainst>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstUnionWarAgainst model = playerMemObj.instUnionWarAgainstMap.get(id);
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