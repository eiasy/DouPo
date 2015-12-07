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
import com.huayi.doupo.base.model.DictCard;

public class DictCardDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictCard dictCard = new DictCard();
			dictCard.setId(rs.getInt("id"), 0);
			dictCard.setName(rs.getString("name"), 0);
			dictCard.setSmallUiId(rs.getInt("smallUiId"), 0);
			dictCard.setBigUiId(rs.getInt("bigUiId"), 0);
			dictCard.setNickname(rs.getInt("nickname"), 0);
			dictCard.setSex(rs.getInt("sex"), 0);
			dictCard.setCardTypeId(rs.getInt("cardTypeId"), 0);
			dictCard.setFightTypeId(rs.getInt("fightTypeId"), 0);
			dictCard.setQualityId(rs.getInt("qualityId"), 0);
			dictCard.setStarLevelId(rs.getInt("starLevelId"), 0);
			dictCard.setTitleDetailId(rs.getInt("titleDetailId"), 0);
			dictCard.setCoefficientId(rs.getInt("coefficientId"), 0);
			dictCard.setBlood(rs.getInt("blood"), 0);
			dictCard.setWuAttack(rs.getInt("wuAttack"), 0);
			dictCard.setFaAttack(rs.getInt("faAttack"), 0);
			dictCard.setWuDefense(rs.getInt("wuDefense"), 0);
			dictCard.setFaDefense(rs.getInt("faDefense"), 0);
			dictCard.setDodge(rs.getInt("dodge"), 0);
			dictCard.setCrit(rs.getInt("crit"), 0);
			dictCard.setHit(rs.getInt("hit"), 0);
			dictCard.setFlex(rs.getInt("flex"), 0);
			dictCard.setBloodAdd(rs.getInt("bloodAdd"), 0);
			dictCard.setWuAttackAdd(rs.getInt("wuAttackAdd"), 0);
			dictCard.setFaAttackAdd(rs.getInt("faAttackAdd"), 0);
			dictCard.setWuDefenseAdd(rs.getInt("wuDefenseAdd"), 0);
			dictCard.setFaDefenseAdd(rs.getInt("faDefenseAdd"), 0);
			dictCard.setDodgeAdd(rs.getInt("dodgeAdd"), 0);
			dictCard.setCritAdd(rs.getInt("critAdd"), 0);
			dictCard.setHitAdd(rs.getInt("hitAdd"), 0);
			dictCard.setFlexAdd(rs.getInt("flexAdd"), 0);
			dictCard.setSkillOne(rs.getInt("skillOne"), 0);
			dictCard.setSkillTwo(rs.getInt("skillTwo"), 0);
			dictCard.setSkillThree(rs.getInt("skillThree"), 0);
			dictCard.setPotential(rs.getInt("potential"), 0);
			dictCard.setConstells(rs.getString("constells"), 0);
			dictCard.setIsSkillName(rs.getInt("isSkillName"), 0);
			dictCard.setAnimationFiles(rs.getString("animationFiles"), 0);
			dictCard.setCamp(rs.getString("camp"), 0);
			dictCard.setDubOne(rs.getString("dubOne"), 0);
			dictCard.setDubTwo(rs.getString("dubTwo"), 0);
			dictCard.setDescription(rs.getString("description"), 0);
			dictCard.setVersion(rs.getInt("version"), 0);
			return dictCard;
		}
	}

	public DictCard add(final DictCard model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Card (");
			strSql.append("name,smallUiId,bigUiId,nickname,sex,cardTypeId,fightTypeId,qualityId,starLevelId,titleDetailId,coefficientId,blood,wuAttack,faAttack,wuDefense,faDefense,dodge,crit,hit,flex,bloodAdd,wuAttackAdd,faAttackAdd,wuDefenseAdd,faDefenseAdd,dodgeAdd,critAdd,hitAdd,flexAdd,skillOne,skillTwo,skillThree,potential,constells,isSkillName,animationFiles,camp,dubOne,dubTwo,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getSmallUiId());
					ps.setInt(3, model.getBigUiId());
					ps.setInt(4, model.getNickname());
					ps.setInt(5, model.getSex());
					ps.setInt(6, model.getCardTypeId());
					ps.setInt(7, model.getFightTypeId());
					ps.setInt(8, model.getQualityId());
					ps.setInt(9, model.getStarLevelId());
					ps.setInt(10, model.getTitleDetailId());
					ps.setInt(11, model.getCoefficientId());
					ps.setInt(12, model.getBlood());
					ps.setInt(13, model.getWuAttack());
					ps.setInt(14, model.getFaAttack());
					ps.setInt(15, model.getWuDefense());
					ps.setInt(16, model.getFaDefense());
					ps.setInt(17, model.getDodge());
					ps.setInt(18, model.getCrit());
					ps.setInt(19, model.getHit());
					ps.setInt(20, model.getFlex());
					ps.setInt(21, model.getBloodAdd());
					ps.setInt(22, model.getWuAttackAdd());
					ps.setInt(23, model.getFaAttackAdd());
					ps.setInt(24, model.getWuDefenseAdd());
					ps.setInt(25, model.getFaDefenseAdd());
					ps.setInt(26, model.getDodgeAdd());
					ps.setInt(27, model.getCritAdd());
					ps.setInt(28, model.getHitAdd());
					ps.setInt(29, model.getFlexAdd());
					ps.setInt(30, model.getSkillOne());
					ps.setInt(31, model.getSkillTwo());
					ps.setInt(32, model.getSkillThree());
					ps.setInt(33, model.getPotential());
					ps.setString(34, model.getConstells());
					ps.setInt(35, model.getIsSkillName());
					ps.setString(36, model.getAnimationFiles());
					ps.setString(37, model.getCamp());
					ps.setString(38, model.getDubOne());
					ps.setString(39, model.getDubTwo());
					ps.setString(40, model.getDescription());
					ps.setInt(41, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictCardMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictCard> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Card (");
		sql.append("name,smallUiId,bigUiId,nickname,sex,cardTypeId,fightTypeId,qualityId,starLevelId,titleDetailId,coefficientId,blood,wuAttack,faAttack,wuDefense,faDefense,dodge,crit,hit,flex,bloodAdd,wuAttackAdd,faAttackAdd,wuDefenseAdd,faDefenseAdd,dodgeAdd,critAdd,hitAdd,flexAdd,skillOne,skillTwo,skillThree,potential,constells,isSkillName,animationFiles,camp,dubOne,dubTwo,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictCard model = (DictCard)list.get(i);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getSmallUiId());
					ps.setInt(3, model.getBigUiId());
					ps.setInt(4, model.getNickname());
					ps.setInt(5, model.getSex());
					ps.setInt(6, model.getCardTypeId());
					ps.setInt(7, model.getFightTypeId());
					ps.setInt(8, model.getQualityId());
					ps.setInt(9, model.getStarLevelId());
					ps.setInt(10, model.getTitleDetailId());
					ps.setInt(11, model.getCoefficientId());
					ps.setInt(12, model.getBlood());
					ps.setInt(13, model.getWuAttack());
					ps.setInt(14, model.getFaAttack());
					ps.setInt(15, model.getWuDefense());
					ps.setInt(16, model.getFaDefense());
					ps.setInt(17, model.getDodge());
					ps.setInt(18, model.getCrit());
					ps.setInt(19, model.getHit());
					ps.setInt(20, model.getFlex());
					ps.setInt(21, model.getBloodAdd());
					ps.setInt(22, model.getWuAttackAdd());
					ps.setInt(23, model.getFaAttackAdd());
					ps.setInt(24, model.getWuDefenseAdd());
					ps.setInt(25, model.getFaDefenseAdd());
					ps.setInt(26, model.getDodgeAdd());
					ps.setInt(27, model.getCritAdd());
					ps.setInt(28, model.getHitAdd());
					ps.setInt(29, model.getFlexAdd());
					ps.setInt(30, model.getSkillOne());
					ps.setInt(31, model.getSkillTwo());
					ps.setInt(32, model.getSkillThree());
					ps.setInt(33, model.getPotential());
					ps.setString(34, model.getConstells());
					ps.setInt(35, model.getIsSkillName());
					ps.setString(36, model.getAnimationFiles());
					ps.setString(37, model.getCamp());
					ps.setString(38, model.getDubOne());
					ps.setString(39, model.getDubTwo());
					ps.setString(40, model.getDescription());
					ps.setInt(41, 0);
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
				playerMemObj.dictCardMap.remove(id);
			}
			String sql = "delete from Dict_Card  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Card where 1=1 ");
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

	public DictCard update(DictCard model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Card set ");
			sql.append("name=?,smallUiId=?,bigUiId=?,nickname=?,sex=?,cardTypeId=?,fightTypeId=?,qualityId=?,starLevelId=?,titleDetailId=?,coefficientId=?,blood=?,wuAttack=?,faAttack=?,wuDefense=?,faDefense=?,dodge=?,crit=?,hit=?,flex=?,bloodAdd=?,wuAttackAdd=?,faAttackAdd=?,wuDefenseAdd=?,faDefenseAdd=?,dodgeAdd=?,critAdd=?,hitAdd=?,flexAdd=?,skillOne=?,skillTwo=?,skillThree=?,potential=?,constells=?,isSkillName=?,animationFiles=?,camp=?,dubOne=?,dubTwo=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getName(),model.getSmallUiId(),model.getBigUiId(),model.getNickname(),model.getSex(),model.getCardTypeId(),model.getFightTypeId(),model.getQualityId(),model.getStarLevelId(),model.getTitleDetailId(),model.getCoefficientId(),model.getBlood(),model.getWuAttack(),model.getFaAttack(),model.getWuDefense(),model.getFaDefense(),model.getDodge(),model.getCrit(),model.getHit(),model.getFlex(),model.getBloodAdd(),model.getWuAttackAdd(),model.getFaAttackAdd(),model.getWuDefenseAdd(),model.getFaDefenseAdd(),model.getDodgeAdd(),model.getCritAdd(),model.getHitAdd(),model.getFlexAdd(),model.getSkillOne(),model.getSkillTwo(),model.getSkillThree(),model.getPotential(),model.getConstells(),model.getIsSkillName(),model.getAnimationFiles(),model.getCamp(),model.getDubOne(),model.getDubTwo(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictCardMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictCard getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictCard model = playerMemObj.dictCardMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Card where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictCardMap.put(id, (DictCard) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Card where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Card where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictCardMap.put(id, (DictCard) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictCardMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Card where id=?";
				Object[] params = new Object[]{id};
				DictCard model = ( DictCard) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictCard> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Card ");
		}else {
			sql = new StringBuffer("select * from Dict_Card ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictCard> dictCardList = (List<DictCard>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictCardList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Card ");
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
	public List<DictCard> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Card ");
			}else {
				sql = new StringBuffer("select * from Dict_Card ");
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
				return (List<DictCard>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Card where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Card");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Card ");
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
	public List<DictCard> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Card a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Card a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictCard>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Card a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Card");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictCard> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictCard> modelList = new ArrayList<DictCard>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictCard model = playerMemObj.dictCardMap.get(id);
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