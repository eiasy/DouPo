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
import com.huayi.doupo.base.model.InstPlayerRechargeFail;
import com.huayi.doupo.base.model.player.PlayerMemObj;

public class InstPlayerRechargeFailDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstPlayerRechargeFail instPlayerRechargeFail = new InstPlayerRechargeFail();
			instPlayerRechargeFail.setId(rs.getInt("id"), 0);
			instPlayerRechargeFail.setInstPlayerId(rs.getInt("instPlayerId"), 0);
			instPlayerRechargeFail.setOpenId(rs.getString("openId"), 0);
			instPlayerRechargeFail.setPlayerName(rs.getString("playerName"), 0);
			instPlayerRechargeFail.setChannel(rs.getString("channel"), 0);
			instPlayerRechargeFail.setMoney(rs.getInt("money"), 0);
			instPlayerRechargeFail.setOrderId(rs.getString("orderId"), 0);
			instPlayerRechargeFail.setAccountId(rs.getString("accountId"), 0);
			instPlayerRechargeFail.setCtype(rs.getInt("ctype"), 0);
			instPlayerRechargeFail.setOrderform(rs.getString("orderform"), 0);
			instPlayerRechargeFail.setRechargeRecordId(rs.getString("rechargeRecordId"), 0);
			instPlayerRechargeFail.setGoodsId(rs.getString("goodsId"), 0);
			instPlayerRechargeFail.setOperateDate(rs.getString("operateDate"), 0);
			instPlayerRechargeFail.setOperateTime(rs.getString("operateTime"), 0);
			instPlayerRechargeFail.setDes(rs.getString("des"), 0);
			instPlayerRechargeFail.setVersion(rs.getInt("version"), 0);
			return instPlayerRechargeFail;
		}
	}

	public InstPlayerRechargeFail add(final InstPlayerRechargeFail model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Inst_Player_RechargeFail (");
			strSql.append("instPlayerId,openId,playerName,channel,money,orderId,accountId,ctype,orderform,rechargeRecordId,goodsId,operateDate,operateTime,des,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getInstPlayerId());
					ps.setString(2, model.getOpenId());
					ps.setString(3, model.getPlayerName());
					ps.setString(4, model.getChannel());
					ps.setInt(5, model.getMoney());
					ps.setString(6, model.getOrderId());
					ps.setString(7, model.getAccountId());
					ps.setInt(8, model.getCtype());
					ps.setString(9, model.getOrderform());
					ps.setString(10, model.getRechargeRecordId());
					ps.setString(11, model.getGoodsId());
					ps.setString(12, model.getOperateDate());
					ps.setString(13, model.getOperateTime());
					ps.setString(14, model.getDes());
					ps.setInt(15, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.instPlayerRechargeFailMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<InstPlayerRechargeFail> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Inst_Player_RechargeFail (");
		sql.append("instPlayerId,openId,playerName,channel,money,orderId,accountId,ctype,orderform,rechargeRecordId,goodsId,operateDate,operateTime,des,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				InstPlayerRechargeFail model = (InstPlayerRechargeFail)list.get(i);
					ps.setInt(1, model.getInstPlayerId());
					ps.setString(2, model.getOpenId());
					ps.setString(3, model.getPlayerName());
					ps.setString(4, model.getChannel());
					ps.setInt(5, model.getMoney());
					ps.setString(6, model.getOrderId());
					ps.setString(7, model.getAccountId());
					ps.setInt(8, model.getCtype());
					ps.setString(9, model.getOrderform());
					ps.setString(10, model.getRechargeRecordId());
					ps.setString(11, model.getGoodsId());
					ps.setString(12, model.getOperateDate());
					ps.setString(13, model.getOperateTime());
					ps.setString(14, model.getDes());
					ps.setInt(15, 0);
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
				playerMemObj.instPlayerRechargeFailMap.remove(id);
			}
			String sql = "delete from Inst_Player_RechargeFail  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_Player_RechargeFail where 1=1 ");
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

	public InstPlayerRechargeFail update(InstPlayerRechargeFail model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Inst_Player_RechargeFail set ");
			sql.append("instPlayerId=?,openId=?,playerName=?,channel=?,money=?,orderId=?,accountId=?,ctype=?,orderform=?,rechargeRecordId=?,goodsId=?,operateDate=?,operateTime=?,des=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getInstPlayerId(),model.getOpenId(),model.getPlayerName(),model.getChannel(),model.getMoney(),model.getOrderId(),model.getAccountId(),model.getCtype(),model.getOrderform(),model.getRechargeRecordId(),model.getGoodsId(),model.getOperateDate(),model.getOperateTime(),model.getDes(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.instPlayerRechargeFailMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public InstPlayerRechargeFail getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				InstPlayerRechargeFail model = playerMemObj.instPlayerRechargeFailMap.get(id);
				if (model == null) {
					String sql = "select * from Inst_Player_RechargeFail where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.instPlayerRechargeFailMap.put(id, (InstPlayerRechargeFail) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Inst_Player_RechargeFail where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Inst_Player_RechargeFail where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.instPlayerRechargeFailMap.put(id, (InstPlayerRechargeFail) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.instPlayerRechargeFailMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Inst_Player_RechargeFail where id=?";
				Object[] params = new Object[]{id};
				InstPlayerRechargeFail model = ( InstPlayerRechargeFail) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InstPlayerRechargeFail> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Inst_Player_RechargeFail ");
		}else {
			sql = new StringBuffer("select * from Inst_Player_RechargeFail ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<InstPlayerRechargeFail> instPlayerRechargeFailList = (List<InstPlayerRechargeFail>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return instPlayerRechargeFailList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_Player_RechargeFail ");
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
	public List<InstPlayerRechargeFail> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Inst_Player_RechargeFail ");
			}else {
				sql = new StringBuffer("select * from Inst_Player_RechargeFail ");
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
				return (List<InstPlayerRechargeFail>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_RechargeFail where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_RechargeFail");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_Player_RechargeFail ");
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
	public List<InstPlayerRechargeFail> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Inst_Player_RechargeFail a ");
		}else {
			sql = new StringBuffer("select a.* from Inst_Player_RechargeFail a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<InstPlayerRechargeFail>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_Player_RechargeFail a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_Player_RechargeFail");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<InstPlayerRechargeFail> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstPlayerRechargeFail> modelList = new ArrayList<InstPlayerRechargeFail>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstPlayerRechargeFail model = playerMemObj.instPlayerRechargeFailMap.get(id);
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