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
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.player.PlayerMemObj;

public class InstPlayerDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstPlayer instPlayer = new InstPlayer();
			instPlayer.setId(rs.getInt("id"), 0);
			instPlayer.setOpenId(rs.getString("openId"), 0);
			instPlayer.setName(rs.getString("name"), 0);
			instPlayer.setLevel(rs.getInt("level"), 0);
			instPlayer.setGold(rs.getInt("gold"), 0);
			instPlayer.setCopper(rs.getString("copper"), 0);
			instPlayer.setExp(rs.getInt("exp"), 0);
			instPlayer.setEnergy(rs.getInt("energy"), 0);
			instPlayer.setMaxEnergy(rs.getInt("maxEnergy"), 0);
			instPlayer.setVigor(rs.getInt("vigor"), 0);
			instPlayer.setMaxVigor(rs.getInt("maxVigor"), 0);
			instPlayer.setCardNum(rs.getInt("cardNum"), 0);
			instPlayer.setMaxCardNum(rs.getInt("maxCardNum"), 0);
			instPlayer.setGuidStep(rs.getString("guidStep"), 0);
			instPlayer.setChapterId(rs.getInt("chapterId"), 0);
			instPlayer.setBarrierId(rs.getInt("barrierId"), 0);
			instPlayer.setFireGainRuleId(rs.getInt("fireGainRuleId"), 0);
			instPlayer.setInstPlayerFireId(rs.getInt("instPlayerFireId"), 0);
			instPlayer.setVipLevel(rs.getInt("vipLevel"), 0);
			instPlayer.setVigour(rs.getInt("vigour"), 0);
			instPlayer.setCulture(rs.getInt("culture"), 0);
			instPlayer.setLastEnergyRecoverTime(rs.getString("lastEnergyRecoverTime"), 0);
			instPlayer.setLastVigorRecoverTime(rs.getString("lastVigorRecoverTime"), 0);
			instPlayer.setBarrierNum(rs.getInt("barrierNum"), 0);
			instPlayer.setBuyEnergyNum(rs.getInt("buyEnergyNum"), 0);
			instPlayer.setBuyVigorNum(rs.getInt("buyVigorNum"), 0);
			instPlayer.setLastBuyEnergyTime(rs.getString("lastBuyEnergyTime"), 0);
			instPlayer.setLastBuyVigorTime(rs.getString("lastBuyVigorTime"), 0);
			instPlayer.setGuipedBarrier(rs.getString("guipedBarrier"), 0);
			instPlayer.setWashTime(rs.getString("washTime"), 0);
			instPlayer.setDailyTashTime(rs.getString("dailyTashTime"), 0);
			instPlayer.setHeaderCardId(rs.getInt("headerCardId"), 0);
			instPlayer.setVipIds(rs.getString("vipIds"), 0);
			instPlayer.setPullBlack(rs.getInt("pullBlack"), 0);
			instPlayer.setIsGetFirstRechargeGift(rs.getInt("isGetFirstRechargeGift"), 0);
			instPlayer.setBeautyCardTime(rs.getString("beautyCardTime"), 0);
			instPlayer.setServerId(rs.getInt("serverId"), 0);
			instPlayer.setChannel(rs.getString("channel"), 0);
			instPlayer.setVersion(rs.getInt("version"), 0);
			instPlayer.setInsertTime(rs.getString("insertTime"), 0);
			instPlayer.setUpdateTime(rs.getString("updateTime"), 0);
			return instPlayer;
		}
	}

	public InstPlayer add(final InstPlayer model, int instPlayerId) throws Exception {
		try {
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_Player (");
			strSql.append("openId,name,level,gold,copper,exp,energy,maxEnergy,vigor,maxVigor,cardNum,maxCardNum,guidStep,chapterId,barrierId,fireGainRuleId,instPlayerFireId,vipLevel,vigour,culture,lastEnergyRecoverTime,lastVigorRecoverTime,barrierNum,buyEnergyNum,buyVigorNum,lastBuyEnergyTime,lastBuyVigorTime,guipedBarrier,washTime,dailyTashTime,headerCardId,vipIds,pullBlack,isGetFirstRechargeGift,beautyCardTime,serverId,channel,version,insertTime,updateTime");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, model.getOpenId());
					ps.setString(2, model.getName());
					ps.setInt(3, model.getLevel());
					ps.setInt(4, model.getGold());
					ps.setString(5, model.getCopper());
					ps.setInt(6, model.getExp());
					ps.setInt(7, model.getEnergy());
					ps.setInt(8, model.getMaxEnergy());
					ps.setInt(9, model.getVigor());
					ps.setInt(10, model.getMaxVigor());
					ps.setInt(11, model.getCardNum());
					ps.setInt(12, model.getMaxCardNum());
					ps.setString(13, model.getGuidStep());
					ps.setInt(14, model.getChapterId());
					ps.setInt(15, model.getBarrierId());
					ps.setInt(16, model.getFireGainRuleId());
					ps.setInt(17, model.getInstPlayerFireId());
					ps.setInt(18, model.getVipLevel());
					ps.setInt(19, model.getVigour());
					ps.setInt(20, model.getCulture());
					ps.setString(21, model.getLastEnergyRecoverTime());
					ps.setString(22, model.getLastVigorRecoverTime());
					ps.setInt(23, model.getBarrierNum());
					ps.setInt(24, model.getBuyEnergyNum());
					ps.setInt(25, model.getBuyVigorNum());
					ps.setString(26, model.getLastBuyEnergyTime());
					ps.setString(27, model.getLastBuyVigorTime());
					ps.setString(28, model.getGuipedBarrier());
					ps.setString(29, model.getWashTime());
					ps.setString(30, model.getDailyTashTime());
					ps.setInt(31, model.getHeaderCardId());
					ps.setString(32, model.getVipIds());
					ps.setInt(33, model.getPullBlack());
					ps.setInt(34, model.getIsGetFirstRechargeGift());
					ps.setString(35, model.getBeautyCardTime());
					ps.setInt(36, model.getServerId());
					ps.setString(37, model.getChannel());
					ps.setInt(38, 0);
					ps.setString(39, updateTime);
					ps.setString(40, updateTime);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			model.setInsertTime(updateTime, 0);
			model.setUpdateTime(updateTime, 0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instPlayerMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstPlayer> list) {
		final String updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_Player (");
		sql.append("openId,name,level,gold,copper,exp,energy,maxEnergy,vigor,maxVigor,cardNum,maxCardNum,guidStep,chapterId,barrierId,fireGainRuleId,instPlayerFireId,vipLevel,vigour,culture,lastEnergyRecoverTime,lastVigorRecoverTime,barrierNum,buyEnergyNum,buyVigorNum,lastBuyEnergyTime,lastBuyVigorTime,guipedBarrier,washTime,dailyTashTime,headerCardId,vipIds,pullBlack,isGetFirstRechargeGift,beautyCardTime,serverId,channel,version,insertTime,updateTime");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstPlayer model = (InstPlayer)list.get(i);
					ps.setString(1, model.getOpenId());
					ps.setString(2, model.getName());
					ps.setInt(3, model.getLevel());
					ps.setInt(4, model.getGold());
					ps.setString(5, model.getCopper());
					ps.setInt(6, model.getExp());
					ps.setInt(7, model.getEnergy());
					ps.setInt(8, model.getMaxEnergy());
					ps.setInt(9, model.getVigor());
					ps.setInt(10, model.getMaxVigor());
					ps.setInt(11, model.getCardNum());
					ps.setInt(12, model.getMaxCardNum());
					ps.setString(13, model.getGuidStep());
					ps.setInt(14, model.getChapterId());
					ps.setInt(15, model.getBarrierId());
					ps.setInt(16, model.getFireGainRuleId());
					ps.setInt(17, model.getInstPlayerFireId());
					ps.setInt(18, model.getVipLevel());
					ps.setInt(19, model.getVigour());
					ps.setInt(20, model.getCulture());
					ps.setString(21, model.getLastEnergyRecoverTime());
					ps.setString(22, model.getLastVigorRecoverTime());
					ps.setInt(23, model.getBarrierNum());
					ps.setInt(24, model.getBuyEnergyNum());
					ps.setInt(25, model.getBuyVigorNum());
					ps.setString(26, model.getLastBuyEnergyTime());
					ps.setString(27, model.getLastBuyVigorTime());
					ps.setString(28, model.getGuipedBarrier());
					ps.setString(29, model.getWashTime());
					ps.setString(30, model.getDailyTashTime());
					ps.setInt(31, model.getHeaderCardId());
					ps.setString(32, model.getVipIds());
					ps.setInt(33, model.getPullBlack());
					ps.setInt(34, model.getIsGetFirstRechargeGift());
					ps.setString(35, model.getBeautyCardTime());
					ps.setInt(36, model.getServerId());
					ps.setString(37, model.getChannel());
					ps.setInt(38, 0);
					ps.setString(39, updateTime);
					ps.setString(40, updateTime);
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
				playerMemObj.instPlayerMap.remove(id);
			}
			String sql = "delete from Inst_Player  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_Player where 1=1 ");
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

	public InstPlayer update(InstPlayer model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuffer sql = new StringBuffer("update Inst_Player set ");
			sql.append("openId=?,name=?,level=?,gold=?,copper=?,exp=?,energy=?,maxEnergy=?,vigor=?,maxVigor=?,cardNum=?,maxCardNum=?,guidStep=?,chapterId=?,barrierId=?,fireGainRuleId=?,instPlayerFireId=?,vipLevel=?,vigour=?,culture=?,lastEnergyRecoverTime=?,lastVigorRecoverTime=?,barrierNum=?,buyEnergyNum=?,buyVigorNum=?,lastBuyEnergyTime=?,lastBuyVigorTime=?,guipedBarrier=?,washTime=?,dailyTashTime=?,headerCardId=?,vipIds=?,pullBlack=?,isGetFirstRechargeGift=?,beautyCardTime=?,serverId=?,channel=?,version=?,insertTime=?,updateTime=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getOpenId(),model.getName(),model.getLevel(),model.getGold(),model.getCopper(),model.getExp(),model.getEnergy(),model.getMaxEnergy(),model.getVigor(),model.getMaxVigor(),model.getCardNum(),model.getMaxCardNum(),model.getGuidStep(),model.getChapterId(),model.getBarrierId(),model.getFireGainRuleId(),model.getInstPlayerFireId(),model.getVipLevel(),model.getVigour(),model.getCulture(),model.getLastEnergyRecoverTime(),model.getLastVigorRecoverTime(),model.getBarrierNum(),model.getBuyEnergyNum(),model.getBuyVigorNum(),model.getLastBuyEnergyTime(),model.getLastBuyVigorTime(),model.getGuipedBarrier(),model.getWashTime(),model.getDailyTashTime(),model.getHeaderCardId(),model.getVipIds(),model.getPullBlack(),model.getIsGetFirstRechargeGift(),model.getBeautyCardTime(),model.getServerId(),model.getChannel(),version,model.getInsertTime(),updateTime , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				model.setUpdateTime(updateTime, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instPlayerMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstPlayer getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstPlayer model = playerMemObj.instPlayerMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_Player where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instPlayerMap.put(id, (InstPlayer) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_Player where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_Player where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instPlayerMap.put(id, (InstPlayer) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instPlayerMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_Player where id=?";
				Object[] params = new Object[]{id};
				InstPlayer model = ( InstPlayer) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstPlayer> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_Player ");
		}else {
			sql = new StringBuffer("select * from Inst_Player ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstPlayer> instPlayerList = (List<InstPlayer>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instPlayerList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_Player ");
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
	public List<InstPlayer> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_Player ");
			}else {
				sql = new StringBuffer("select * from Inst_Player ");
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
				return (List<InstPlayer>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_Player ");
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
	public List<InstPlayer> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_Player a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_Player a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstPlayer>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_Player a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_Player");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstPlayer> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstPlayer> modelList = new ArrayList<InstPlayer>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstPlayer model = playerMemObj.instPlayerMap.get(id);
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