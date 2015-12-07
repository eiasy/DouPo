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
import com.huayi.doupo.base.model.InstPlayerRecharge;
import com.huayi.doupo.base.model.player.PlayerMemObj;

public class InstPlayerRechargeDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstPlayerRecharge instPlayerRecharge = new InstPlayerRecharge();
			instPlayerRecharge.setId(rs.getInt("id"), 0);
			instPlayerRecharge.setInstPlayerId(rs.getInt("instPlayerId"), 0);
			instPlayerRecharge.setOpenId(rs.getString("openId"), 0);
			instPlayerRecharge.setPlayerName(rs.getString("playerName"), 0);
			instPlayerRecharge.setChannel(rs.getString("channel"), 0);
			instPlayerRecharge.setThisrmb(rs.getInt("thisrmb"), 0);
			instPlayerRecharge.setThisamt(rs.getInt("thisamt"), 0);
			instPlayerRecharge.setSaveamt(rs.getInt("saveamt"), 0);
			instPlayerRecharge.setPayChannel(rs.getInt("payChannel"), 0);
			instPlayerRecharge.setBalance(rs.getInt("balance"), 0);
			instPlayerRecharge.setGenbalance(rs.getInt("genbalance"), 0);
			instPlayerRecharge.setSaveNum(rs.getInt("saveNum"), 0);
			instPlayerRecharge.setSource(rs.getString("source"), 0);
			instPlayerRecharge.setOrderId(rs.getString("orderId"), 0);
			instPlayerRecharge.setServerId(rs.getInt("serverId"), 0);
			instPlayerRecharge.setAccountId(rs.getString("accountId"), 0);
			instPlayerRecharge.setOrderform(rs.getString("orderform"), 0);
			instPlayerRecharge.setRoleLevel(rs.getInt("roleLevel"), 0);
			instPlayerRecharge.setGoodsId(rs.getInt("goodsId"), 0);
			instPlayerRecharge.setCtype(rs.getInt("ctype"), 0);
			instPlayerRecharge.setMoney(rs.getInt("money"), 0);
			instPlayerRecharge.setRechargeRecordId(rs.getString("rechargeRecordId"), 0);
			instPlayerRecharge.setOperateDate(rs.getString("operateDate"), 0);
			instPlayerRecharge.setOperateTime(rs.getString("operateTime"), 0);
			instPlayerRecharge.setVersion(rs.getInt("version"), 0);
			return instPlayerRecharge;
		}
	}

	public InstPlayerRecharge add(final InstPlayerRecharge model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_Player_Recharge (");
			strSql.append("instPlayerId,openId,playerName,channel,thisrmb,thisamt,saveamt,payChannel,balance,genbalance,saveNum,source,orderId,serverId,accountId,orderform,roleLevel,goodsId,ctype,money,rechargeRecordId,operateDate,operateTime,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getInstPlayerId());
					ps.setString(2, model.getOpenId());
					ps.setString(3, model.getPlayerName());
					ps.setString(4, model.getChannel());
					ps.setInt(5, model.getThisrmb());
					ps.setInt(6, model.getThisamt());
					ps.setInt(7, model.getSaveamt());
					ps.setInt(8, model.getPayChannel());
					ps.setInt(9, model.getBalance());
					ps.setInt(10, model.getGenbalance());
					ps.setInt(11, model.getSaveNum());
					ps.setString(12, model.getSource());
					ps.setString(13, model.getOrderId());
					ps.setInt(14, model.getServerId());
					ps.setString(15, model.getAccountId());
					ps.setString(16, model.getOrderform());
					ps.setInt(17, model.getRoleLevel());
					ps.setInt(18, model.getGoodsId());
					ps.setInt(19, model.getCtype());
					ps.setInt(20, model.getMoney());
					ps.setString(21, model.getRechargeRecordId());
					ps.setString(22, model.getOperateDate());
					ps.setString(23, model.getOperateTime());
					ps.setInt(24, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instPlayerRechargeMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstPlayerRecharge> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_Player_Recharge (");
		sql.append("instPlayerId,openId,playerName,channel,thisrmb,thisamt,saveamt,payChannel,balance,genbalance,saveNum,source,orderId,serverId,accountId,orderform,roleLevel,goodsId,ctype,money,rechargeRecordId,operateDate,operateTime,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstPlayerRecharge model = (InstPlayerRecharge)list.get(i);
					ps.setInt(1, model.getInstPlayerId());
					ps.setString(2, model.getOpenId());
					ps.setString(3, model.getPlayerName());
					ps.setString(4, model.getChannel());
					ps.setInt(5, model.getThisrmb());
					ps.setInt(6, model.getThisamt());
					ps.setInt(7, model.getSaveamt());
					ps.setInt(8, model.getPayChannel());
					ps.setInt(9, model.getBalance());
					ps.setInt(10, model.getGenbalance());
					ps.setInt(11, model.getSaveNum());
					ps.setString(12, model.getSource());
					ps.setString(13, model.getOrderId());
					ps.setInt(14, model.getServerId());
					ps.setString(15, model.getAccountId());
					ps.setString(16, model.getOrderform());
					ps.setInt(17, model.getRoleLevel());
					ps.setInt(18, model.getGoodsId());
					ps.setInt(19, model.getCtype());
					ps.setInt(20, model.getMoney());
					ps.setString(21, model.getRechargeRecordId());
					ps.setString(22, model.getOperateDate());
					ps.setString(23, model.getOperateTime());
					ps.setInt(24, 0);
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
				playerMemObj.instPlayerRechargeMap.remove(id);
			}
			String sql = "delete from Inst_Player_Recharge  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_Player_Recharge where 1=1 ");
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

	public InstPlayerRecharge update(InstPlayerRecharge model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Inst_Player_Recharge set ");
			sql.append("instPlayerId=?,openId=?,playerName=?,channel=?,thisrmb=?,thisamt=?,saveamt=?,payChannel=?,balance=?,genbalance=?,saveNum=?,source=?,orderId=?,serverId=?,accountId=?,orderform=?,roleLevel=?,goodsId=?,ctype=?,money=?,rechargeRecordId=?,operateDate=?,operateTime=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getInstPlayerId(),model.getOpenId(),model.getPlayerName(),model.getChannel(),model.getThisrmb(),model.getThisamt(),model.getSaveamt(),model.getPayChannel(),model.getBalance(),model.getGenbalance(),model.getSaveNum(),model.getSource(),model.getOrderId(),model.getServerId(),model.getAccountId(),model.getOrderform(),model.getRoleLevel(),model.getGoodsId(),model.getCtype(),model.getMoney(),model.getRechargeRecordId(),model.getOperateDate(),model.getOperateTime(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instPlayerRechargeMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstPlayerRecharge getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstPlayerRecharge model = playerMemObj.instPlayerRechargeMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_Player_Recharge where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instPlayerRechargeMap.put(id, (InstPlayerRecharge) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_Player_Recharge where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_Player_Recharge where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instPlayerRechargeMap.put(id, (InstPlayerRecharge) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instPlayerRechargeMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_Player_Recharge where id=?";
				Object[] params = new Object[]{id};
				InstPlayerRecharge model = ( InstPlayerRecharge) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstPlayerRecharge> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_Player_Recharge ");
		}else {
			sql = new StringBuffer("select * from Inst_Player_Recharge ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstPlayerRecharge> instPlayerRechargeList = (List<InstPlayerRecharge>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instPlayerRechargeList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_Player_Recharge ");
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
	public List<InstPlayerRecharge> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_Player_Recharge ");
			}else {
				sql = new StringBuffer("select * from Inst_Player_Recharge ");
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
				return (List<InstPlayerRecharge>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_Recharge where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_Recharge");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_Player_Recharge ");
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
	public List<InstPlayerRecharge> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_Player_Recharge a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_Player_Recharge a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstPlayerRecharge>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_Player_Recharge a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_Player_Recharge");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstPlayerRecharge> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstPlayerRecharge> modelList = new ArrayList<InstPlayerRecharge>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstPlayerRecharge model = playerMemObj.instPlayerRechargeMap.get(id);
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