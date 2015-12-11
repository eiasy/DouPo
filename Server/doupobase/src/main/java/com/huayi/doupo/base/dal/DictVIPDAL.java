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
import com.huayi.doupo.base.model.DictVIP;

public class DictVIPDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictVIP dictVIP = new DictVIP();
			dictVIP.setId(rs.getInt("id"), 0);
			dictVIP.setName(rs.getString("name"), 0);
			dictVIP.setLevel(rs.getInt("level"), 0);
			dictVIP.setOpenLevel(rs.getInt("openLevel"), 0);
			dictVIP.setBuyItemsNum(rs.getString("buyItemsNum"), 0);
			dictVIP.setSilverRecruitAdd(rs.getString("silverRecruitAdd"), 0);
			dictVIP.setGoldRecruitAdd(rs.getString("goldRecruitAdd"), 0);
			dictVIP.setDiamondRecruitAdd(rs.getString("diamondRecruitAdd"), 0);
			dictVIP.setHJYReset(rs.getInt("hJYReset"), 0);
			dictVIP.setBuyArenaNum(rs.getInt("buyArenaNum"), 0);
			dictVIP.setSilverActivityChapterBuyTimes(rs.getInt("silverActivityChapterBuyTimes"), 0);
			dictVIP.setExpActivityChapterBuyTimes(rs.getInt("expActivityChapterBuyTimes"), 0);
			dictVIP.setPillActivityChapterBuyTimes(rs.getInt("pillActivityChapterBuyTimes"), 0);
			dictVIP.setSoulActivityChapterBuyTimes(rs.getInt("soulActivityChapterBuyTimes"), 0);
			dictVIP.setTalentActivityChapterBuyTimes(rs.getInt("talentActivityChapterBuyTimes"), 0);
			dictVIP.setEliteChapterBuyTimes(rs.getInt("eliteChapterBuyTimes"), 0);
			dictVIP.setPagodaSearchNum(rs.getInt("pagodaSearchNum"), 0);
			dictVIP.setPagodaResetNum(rs.getInt("pagodaResetNum"), 0);
			dictVIP.setWingChapterNum(rs.getInt("wingChapterNum"), 0);
			dictVIP.setFiendChapterNum(rs.getInt("fiendChapterNum"), 0);
			dictVIP.setIsContinuFight(rs.getInt("isContinuFight"), 0);
			dictVIP.setIsResetGenerBarrier(rs.getInt("isResetGenerBarrier"), 0);
			dictVIP.setIsUpSilverVip(rs.getInt("isUpSilverVip"), 0);
			dictVIP.setGiftBugUiId(rs.getInt("giftBugUiId"), 0);
			dictVIP.setGiftBagThings(rs.getString("giftBagThings"), 0);
			dictVIP.setLimit(rs.getInt("limit"), 0);
			dictVIP.setDescription(rs.getString("description"), 0);
			dictVIP.setVersion(rs.getInt("version"), 0);
			dictVIP.setHjyFreshCount(rs.getInt("hjyFreshCount"), 0);
			dictVIP.setChapterResetCount(rs.getInt("chapterResetCount"), 0);
			dictVIP.setHoldStarRewardRefreshTimes(rs.getInt("holdStarRewardRefreshTimes"), 0);
			return dictVIP;
		}
	}

	public DictVIP add(final DictVIP model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_VIP (");
			strSql.append("name,level,openLevel,buyItemsNum,silverRecruitAdd,goldRecruitAdd,diamondRecruitAdd,hJYReset,buyArenaNum,silverActivityChapterBuyTimes,expActivityChapterBuyTimes,pillActivityChapterBuyTimes,soulActivityChapterBuyTimes,talentActivityChapterBuyTimes,eliteChapterBuyTimes,pagodaSearchNum,pagodaResetNum,wingChapterNum,fiendChapterNum,isContinuFight,isResetGenerBarrier,isUpSilverVip,giftBugUiId,giftBagThings,limit,description,version,hjyFreshCount,chapterResetCount,holdStarRewardRefreshTimes");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getLevel());
					ps.setInt(3, model.getOpenLevel());
					ps.setString(4, model.getBuyItemsNum());
					ps.setString(5, model.getSilverRecruitAdd());
					ps.setString(6, model.getGoldRecruitAdd());
					ps.setString(7, model.getDiamondRecruitAdd());
					ps.setInt(8, model.getHJYReset());
					ps.setInt(9, model.getBuyArenaNum());
					ps.setInt(10, model.getSilverActivityChapterBuyTimes());
					ps.setInt(11, model.getExpActivityChapterBuyTimes());
					ps.setInt(12, model.getPillActivityChapterBuyTimes());
					ps.setInt(13, model.getSoulActivityChapterBuyTimes());
					ps.setInt(14, model.getTalentActivityChapterBuyTimes());
					ps.setInt(15, model.getEliteChapterBuyTimes());
					ps.setInt(16, model.getPagodaSearchNum());
					ps.setInt(17, model.getPagodaResetNum());
					ps.setInt(18, model.getWingChapterNum());
					ps.setInt(19, model.getFiendChapterNum());
					ps.setInt(20, model.getIsContinuFight());
					ps.setInt(21, model.getIsResetGenerBarrier());
					ps.setInt(22, model.getIsUpSilverVip());
					ps.setInt(23, model.getGiftBugUiId());
					ps.setString(24, model.getGiftBagThings());
					ps.setInt(25, model.getLimit());
					ps.setString(26, model.getDescription());
					ps.setInt(27, 0);
					ps.setInt(28, model.getHjyFreshCount());
					ps.setInt(29, model.getChapterResetCount());
					ps.setInt(30, model.getHoldStarRewardRefreshTimes());
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictVIPMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictVIP> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_VIP (");
		sql.append("name,level,openLevel,buyItemsNum,silverRecruitAdd,goldRecruitAdd,diamondRecruitAdd,hJYReset,buyArenaNum,silverActivityChapterBuyTimes,expActivityChapterBuyTimes,pillActivityChapterBuyTimes,soulActivityChapterBuyTimes,talentActivityChapterBuyTimes,eliteChapterBuyTimes,pagodaSearchNum,pagodaResetNum,wingChapterNum,fiendChapterNum,isContinuFight,isResetGenerBarrier,isUpSilverVip,giftBugUiId,giftBagThings,limit,description,version,hjyFreshCount,chapterResetCount,holdStarRewardRefreshTimes");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictVIP model = (DictVIP)list.get(i);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getLevel());
					ps.setInt(3, model.getOpenLevel());
					ps.setString(4, model.getBuyItemsNum());
					ps.setString(5, model.getSilverRecruitAdd());
					ps.setString(6, model.getGoldRecruitAdd());
					ps.setString(7, model.getDiamondRecruitAdd());
					ps.setInt(8, model.getHJYReset());
					ps.setInt(9, model.getBuyArenaNum());
					ps.setInt(10, model.getSilverActivityChapterBuyTimes());
					ps.setInt(11, model.getExpActivityChapterBuyTimes());
					ps.setInt(12, model.getPillActivityChapterBuyTimes());
					ps.setInt(13, model.getSoulActivityChapterBuyTimes());
					ps.setInt(14, model.getTalentActivityChapterBuyTimes());
					ps.setInt(15, model.getEliteChapterBuyTimes());
					ps.setInt(16, model.getPagodaSearchNum());
					ps.setInt(17, model.getPagodaResetNum());
					ps.setInt(18, model.getWingChapterNum());
					ps.setInt(19, model.getFiendChapterNum());
					ps.setInt(20, model.getIsContinuFight());
					ps.setInt(21, model.getIsResetGenerBarrier());
					ps.setInt(22, model.getIsUpSilverVip());
					ps.setInt(23, model.getGiftBugUiId());
					ps.setString(24, model.getGiftBagThings());
					ps.setInt(25, model.getLimit());
					ps.setString(26, model.getDescription());
					ps.setInt(27, 0);
					ps.setInt(28, model.getHjyFreshCount());
					ps.setInt(29, model.getChapterResetCount());
					ps.setInt(30, model.getHoldStarRewardRefreshTimes());
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
				playerMemObj.dictVIPMap.remove(id);
			}
			String sql = "delete from Dict_VIP  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_VIP where 1=1 ");
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

	public DictVIP update(DictVIP model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_VIP set ");
			sql.append("name=?,level=?,openLevel=?,buyItemsNum=?,silverRecruitAdd=?,goldRecruitAdd=?,diamondRecruitAdd=?,hJYReset=?,buyArenaNum=?,silverActivityChapterBuyTimes=?,expActivityChapterBuyTimes=?,pillActivityChapterBuyTimes=?,soulActivityChapterBuyTimes=?,talentActivityChapterBuyTimes=?,eliteChapterBuyTimes=?,pagodaSearchNum=?,pagodaResetNum=?,wingChapterNum=?,fiendChapterNum=?,isContinuFight=?,isResetGenerBarrier=?,isUpSilverVip=?,giftBugUiId=?,giftBagThings=?,limit=?,description=?,version=?,hjyFreshCount=?,chapterResetCount=?,holdStarRewardRefreshTimes=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getName(),model.getLevel(),model.getOpenLevel(),model.getBuyItemsNum(),model.getSilverRecruitAdd(),model.getGoldRecruitAdd(),model.getDiamondRecruitAdd(),model.getHJYReset(),model.getBuyArenaNum(),model.getSilverActivityChapterBuyTimes(),model.getExpActivityChapterBuyTimes(),model.getPillActivityChapterBuyTimes(),model.getSoulActivityChapterBuyTimes(),model.getTalentActivityChapterBuyTimes(),model.getEliteChapterBuyTimes(),model.getPagodaSearchNum(),model.getPagodaResetNum(),model.getWingChapterNum(),model.getFiendChapterNum(),model.getIsContinuFight(),model.getIsResetGenerBarrier(),model.getIsUpSilverVip(),model.getGiftBugUiId(),model.getGiftBagThings(),model.getLimit(),model.getDescription(),version,model.getHjyFreshCount(),model.getChapterResetCount(),model.getHoldStarRewardRefreshTimes() , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictVIPMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictVIP getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictVIP model = playerMemObj.dictVIPMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_VIP where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictVIPMap.put(id, (DictVIP) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_VIP where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_VIP where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictVIPMap.put(id, (DictVIP) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictVIPMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_VIP where id=?";
				Object[] params = new Object[]{id};
				DictVIP model = ( DictVIP) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictVIP> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_VIP ");
		}else {
			sql = new StringBuffer("select * from Dict_VIP ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictVIP> dictVIPList = (List<DictVIP>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictVIPList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_VIP ");
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
	public List<DictVIP> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_VIP ");
			}else {
				sql = new StringBuffer("select * from Dict_VIP ");
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
				return (List<DictVIP>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_VIP where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_VIP");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_VIP ");
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
	public List<DictVIP> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_VIP a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_VIP a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictVIP>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_VIP a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_VIP");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictVIP> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictVIP> modelList = new ArrayList<DictVIP>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictVIP model = playerMemObj.dictVIPMap.get(id);
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