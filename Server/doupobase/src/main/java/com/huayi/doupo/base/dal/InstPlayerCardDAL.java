package com.huayi.doupo.base.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.huayi.doupo.base.dal.base.DALFather;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.player.PlayerMemObj;

public class InstPlayerCardDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstPlayerCard instPlayerCard = new InstPlayerCard();
			instPlayerCard.setId(rs.getInt("id"), 0);
			instPlayerCard.setInstPlayerId(rs.getInt("instPlayerId"), 0);
			instPlayerCard.setCardId(rs.getInt("cardId"), 0);
			instPlayerCard.setQualityId(rs.getInt("qualityId"), 0);
			instPlayerCard.setStarLevelId(rs.getInt("starLevelId"), 0);
			instPlayerCard.setTitleDetailId(rs.getInt("titleDetailId"), 0);
			instPlayerCard.setSex(rs.getInt("sex"), 0);
			instPlayerCard.setExp(rs.getInt("exp"), 0);
			instPlayerCard.setLevel(rs.getInt("level"), 0);
			instPlayerCard.setInTeam(rs.getInt("inTeam"), 0);
			instPlayerCard.setUseTalentValue(rs.getInt("useTalentValue"), 0);
			instPlayerCard.setInstPlayerKungFuId(rs.getInt("instPlayerKungFuId"), 0);
			instPlayerCard.setInstPlayerConstells(rs.getString("instPlayerConstells"), 0);
			instPlayerCard.setPotential(rs.getInt("potential"), 0);
			instPlayerCard.setIsLock(rs.getInt("isLock"), 0);
			instPlayerCard.setTrainNum(rs.getInt("trainNum"), 0);
			instPlayerCard.setTrainAcceptNum(rs.getInt("trainAcceptNum"), 0);
			instPlayerCard.setVersion(rs.getInt("version"), 0);
			instPlayerCard.setInsertTime(rs.getString("insertTime"), 0);
			instPlayerCard.setUpdateTime(rs.getString("updateTime"), 0);
			return instPlayerCard;
		}
	}

	public InstPlayerCard add(final InstPlayerCard model, int instPlayerId) throws Exception {
		try {
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_Player_Card (");
			strSql.append("instPlayerId,cardId,qualityId,starLevelId,titleDetailId,sex,exp,level,inTeam,useTalentValue,instPlayerKungFuId,instPlayerConstells,potential,isLock,trainNum,trainAcceptNum,version,insertTime,updateTime");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getInstPlayerId());
					ps.setInt(2, model.getCardId());
					ps.setInt(3, model.getQualityId());
					ps.setInt(4, model.getStarLevelId());
					ps.setInt(5, model.getTitleDetailId());
					ps.setInt(6, model.getSex());
					ps.setInt(7, model.getExp());
					ps.setInt(8, model.getLevel());
					ps.setInt(9, model.getInTeam());
					ps.setInt(10, model.getUseTalentValue());
					ps.setInt(11, model.getInstPlayerKungFuId());
					ps.setString(12, model.getInstPlayerConstells());
					ps.setInt(13, model.getPotential());
					ps.setInt(14, model.getIsLock());
					ps.setInt(15, model.getTrainNum());
					ps.setInt(16, model.getTrainAcceptNum());
					ps.setInt(17, 0);
					ps.setString(18, updateTime);
					ps.setString(19, updateTime);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			model.setInsertTime(updateTime, 0);
			model.setUpdateTime(updateTime, 0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instPlayerCardMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstPlayerCard> list) {
		final String updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_Player_Card (");
		sql.append("instPlayerId,cardId,qualityId,starLevelId,titleDetailId,sex,exp,level,inTeam,useTalentValue,instPlayerKungFuId,instPlayerConstells,potential,isLock,trainNum,trainAcceptNum,version,insertTime,updateTime");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstPlayerCard model = (InstPlayerCard)list.get(i);
					ps.setInt(1, model.getInstPlayerId());
					ps.setInt(2, model.getCardId());
					ps.setInt(3, model.getQualityId());
					ps.setInt(4, model.getStarLevelId());
					ps.setInt(5, model.getTitleDetailId());
					ps.setInt(6, model.getSex());
					ps.setInt(7, model.getExp());
					ps.setInt(8, model.getLevel());
					ps.setInt(9, model.getInTeam());
					ps.setInt(10, model.getUseTalentValue());
					ps.setInt(11, model.getInstPlayerKungFuId());
					ps.setString(12, model.getInstPlayerConstells());
					ps.setInt(13, model.getPotential());
					ps.setInt(14, model.getIsLock());
					ps.setInt(15, model.getTrainNum());
					ps.setInt(16, model.getTrainAcceptNum());
					ps.setInt(17, 0);
					ps.setString(18, updateTime);
					ps.setString(19, updateTime);
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
				playerMemObj.instPlayerCardMap.remove(id);
			}
			String sql = "delete from Inst_Player_Card  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_Player_Card where 1=1 ");
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

	public InstPlayerCard update(InstPlayerCard model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuffer sql = new StringBuffer("update Inst_Player_Card set ");
			sql.append("instPlayerId=?,cardId=?,qualityId=?,starLevelId=?,titleDetailId=?,sex=?,exp=?,level=?,inTeam=?,useTalentValue=?,instPlayerKungFuId=?,instPlayerConstells=?,potential=?,isLock=?,trainNum=?,trainAcceptNum=?,version=?,insertTime=?,updateTime=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getInstPlayerId(),model.getCardId(),model.getQualityId(),model.getStarLevelId(),model.getTitleDetailId(),model.getSex(),model.getExp(),model.getLevel(),model.getInTeam(),model.getUseTalentValue(),model.getInstPlayerKungFuId(),model.getInstPlayerConstells(),model.getPotential(),model.getIsLock(),model.getTrainNum(),model.getTrainAcceptNum(),version,model.getInsertTime(),updateTime , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				model.setUpdateTime(updateTime, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instPlayerCardMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstPlayerCard getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstPlayerCard model = playerMemObj.instPlayerCardMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_Player_Card where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instPlayerCardMap.put(id, (InstPlayerCard) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_Player_Card where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_Player_Card where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instPlayerCardMap.put(id, (InstPlayerCard) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instPlayerCardMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_Player_Card where id=?";
				Object[] params = new Object[]{id};
				InstPlayerCard model = ( InstPlayerCard) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstPlayerCard> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_Player_Card ");
		}else {
			sql = new StringBuffer("select * from Inst_Player_Card ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstPlayerCard> instPlayerCardList = (List<InstPlayerCard>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instPlayerCardList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_Player_Card ");
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
	public List<InstPlayerCard> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_Player_Card ");
			}else {
				sql = new StringBuffer("select * from Inst_Player_Card ");
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
				return (List<InstPlayerCard>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_Card where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_Card");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_Player_Card ");
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
	public List<InstPlayerCard> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_Player_Card a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_Player_Card a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstPlayerCard>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_Player_Card a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_Player_Card");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstPlayerCard> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstPlayerCard> modelList = new ArrayList<InstPlayerCard>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstPlayerCard model = playerMemObj.instPlayerCardMap.get(id);
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