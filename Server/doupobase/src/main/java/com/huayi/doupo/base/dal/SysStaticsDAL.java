
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
import com.huayi.doupo.base.model.SysStatics;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.util.base.StringUtil;

public class SysStaticsDAL extends DALFather {
    
    @SuppressWarnings("rawtypes")
    public class ItemMapper implements RowMapper {
        
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysStatics sysStatics = new SysStatics();
            sysStatics.setId(rs.getInt("id"), 0);
            sysStatics.setMaxOnlineNum(rs.getInt("maxOnlineNum"), 0);
            sysStatics.setMaxOnlineTime(rs.getString("maxOnlineTime"), 0);
            sysStatics.setMinOnlineNum(rs.getInt("minOnlineNum"), 0);
            sysStatics.setMinOnlineTime(rs.getString("minOnlineTime"), 0);
            sysStatics.setMaxHitBossNum(rs.getInt("maxHitBossNum"), 0);
            sysStatics.setTwoDayBackPer(rs.getFloat("twoDayBackPer"), 0);
            sysStatics.setThreeDayBackPer(rs.getFloat("threeDayBackPer"), 0);
            sysStatics.setSevenDayBackPer(rs.getFloat("sevenDayBackPer"), 0);
            sysStatics.setThirtyDayBackPer(rs.getFloat("thirtyDayBackPer"), 0);
            sysStatics.setSettleTime(rs.getString("settleTime"), 0);
            sysStatics.setVersion(rs.getInt("version"), 0);
			return sysStatics;
		}
	}
    
	public SysStatics add(final SysStatics model, int instPlayerId) throws Exception {
        try { 
            StringBuilder strSql = new StringBuilder();
	        strSql.append(" insert into Sys_Statics (");
	        strSql.append("maxOnlineNum,maxOnlineTime,minOnlineNum,minOnlineTime,maxHitBossNum,twoDayBackPer,threeDayBackPer,sevenDayBackPer,thirtyDayBackPer,settleTime,version");
	        strSql.append(" )");
	        strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?) ");
            
            final String sql = strSql.toString();
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            this.getJdbcTemplate().update(new PreparedStatementCreator(){
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, model.getMaxOnlineNum());
                    ps.setString(2, model.getMaxOnlineTime());
                    ps.setInt(3, model.getMinOnlineNum());
                    ps.setString(4, model.getMinOnlineTime());
                    ps.setInt(5, model.getMaxHitBossNum());
                    ps.setFloat(6, model.getTwoDayBackPer());
                    ps.setFloat(7, model.getThreeDayBackPer());
                    ps.setFloat(8, model.getSevenDayBackPer());
                    ps.setFloat(9, model.getThirtyDayBackPer());
                    ps.setString(10, model.getSettleTime());
                    ps.setInt(11, 0);
                    return ps;
                } 
            },keyHolder);
            
            model.setId(keyHolder.getKey().intValue());
            model.setVersion(0);
            PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		    if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
                 playerMemObj.sysStaticsMap.put(model.getId(), model);
		    }
          
		} catch (Exception e) {
			throw e;
		}
		return model;
	}
    
    public void batchAdd (final List<SysStatics> list) {

        StringBuilder sql = new StringBuilder();
	    sql.append(" insert into Sys_Statics (");
	    sql.append("maxOnlineNum,maxOnlineTime,minOnlineNum,minOnlineTime,maxHitBossNum,twoDayBackPer,threeDayBackPer,sevenDayBackPer,thirtyDayBackPer,settleTime,version");
	    sql.append(" )");
	    sql.append(" values (?,?,?,?,?,?,?,?,?,?,?) ");
            
	    BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
	          public void setValues(PreparedStatement ps, int i) throws SQLException{
	        	   SysStatics model = (SysStatics)list.get(i);
                   ps.setInt(1, model.getMaxOnlineNum());
                   ps.setString(2, model.getMaxOnlineTime());
                   ps.setInt(3, model.getMinOnlineNum());
                   ps.setString(4, model.getMinOnlineTime());
                   ps.setInt(5, model.getMaxHitBossNum());
                   ps.setFloat(6, model.getTwoDayBackPer());
                   ps.setFloat(7, model.getThreeDayBackPer());
                   ps.setFloat(8, model.getSevenDayBackPer());
                   ps.setFloat(9, model.getThirtyDayBackPer());
                   ps.setString(10, model.getSettleTime());
                    ps.setInt(11, 0);
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
                playerMemObj.sysStaticsMap.remove(id);
			}
			String sql = "delete from Sys_Statics  where id = ?";
            Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}
    
	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Sys_Statics where 1=1 ");

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
    
	public SysStatics update(SysStatics model, int instPlayerId) throws Exception {
		try {
            Object[] params = null;
            int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Sys_Statics set ");
            
            sql.append("maxOnlineNum=?,maxOnlineTime=?,minOnlineNum=?,minOnlineTime=?,maxHitBossNum=?,twoDayBackPer=?,threeDayBackPer=?,sevenDayBackPer=?,thirtyDayBackPer=?,settleTime=?,version=? ");
            
            sql.append("where id=? and version=?");
            params = new Object[] { model.getMaxOnlineNum(),model.getMaxOnlineTime(),model.getMinOnlineNum(),model.getMinOnlineTime(),model.getMaxHitBossNum(),model.getTwoDayBackPer(),model.getThreeDayBackPer(),model.getSevenDayBackPer(),model.getThirtyDayBackPer(),model.getSettleTime(),version, model.getId(), model.getVersion() };

			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			}else{
				model.setVersion(version, 0);
                PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
	        		playerMemObj.sysStaticsMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}
    
    @SuppressWarnings("unchecked")
	public SysStatics getModel(int id, int instPlayerId) {
		try {
            PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
        		SysStatics model = playerMemObj.sysStaticsMap.get(id);
        		if (model == null) {
        			String sql = "select * from Sys_Statics where id=?";
				    Object[] params = new Object[]{id};
        			playerMemObj.sysStaticsMap.put(id, (SysStatics) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
			    } else {
                    int cacheVersion = model.getVersion();
                    List<Map<String, Object>> list = sqlHelper("select version from Sys_Statics where id = " + id);
                    int dbVersion = (int)list.get(0).get("version");
                    if (cacheVersion != dbVersion) {
                        String sql = "select * from Sys_Statics where id=?";
                        Object[] params = new Object[]{id};
                        playerMemObj.sysStaticsMap.put(id, (SysStatics) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
                    }
			    }
        		return playerMemObj.sysStaticsMap.get(id);
			} else {
			    String sql = "select * from Sys_Statics where id=?";
			    Object[] params = new Object[]{id};
			    SysStatics model = ( SysStatics) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
			    return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}
    
    @SuppressWarnings("unchecked")	
	public List<SysStatics> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
        PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
    		sql = new StringBuffer("select id, version from Sys_Statics ");
		}else {
			sql = new StringBuffer("select * from Sys_Statics ");
		}
        if (StringUtil.isNotEmpty(strWhere)) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
    		return listCacheCommonHandler(sql.toString(), instPlayerId);
    	} else {
		    List<SysStatics> sysStaticsList = (List<SysStatics>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		    return sysStaticsList;
		}
	}
    
	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Sys_Statics ");
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
	public List<SysStatics> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
            if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
                sql = new StringBuffer("select id, version from Sys_Statics ");
            }else {
                sql = new StringBuffer("select * from Sys_Statics ");
            }
            if(index <= 0 || size <= 0){
				throw new Exception("index or size must bigger than zero");
			}else{
				index = (index - 1) * size;
			}
			if (StringUtil.isNotEmpty(strWhere)) {
			sql.append(" where " + strWhere);
		    }
			sql.append(" limit " + index + "," + size + "");
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				return listCacheCommonHandler(sql.toString(), instPlayerId);
	    	} else {
			    return (List<SysStatics>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}
    
	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Sys_Statics where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Sys_Statics");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Map<String,Object>> sqlHelper(String sql) {
		return this.getJdbcTemplate().queryForList(sql);
	}
    
    @SuppressWarnings("unchecked")	
	public List<SysStatics> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
        PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
        if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
            sql = new StringBuffer("select a.id, a.version from Sys_Statics a ");
        }else {
            sql = new StringBuffer("select a.* from Sys_Statics a ");
        }
		if (StringUtil.isNotEmpty(afterSql)) {
		    sql.append(" where " + afterSql);
	    }
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
		    return (List<SysStatics>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}
    
	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Sys_Statics a ");
			if (StringUtil.isNotEmpty(afterSql)) {
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
		try {
            StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Sys_Statics");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			throw e;
		}
	}

	private List<SysStatics> listCacheCommonHandler(String sql, int instPlayerId){
		List<SysStatics> modelList = new ArrayList<SysStatics>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			SysStatics model = playerMemObj.sysStaticsMap.get(id);
			if (model == null) {
				model = getModel(id, instPlayerId);
				modelList.add(model);
			} else {
				int cacheVersion = model.getVersion();
				if (cacheVersion != dbVersion) {
					model = getModel(id, instPlayerId);
				}
				modelList.add(model);
			}
		}
		return modelList;
	}
}


