
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
import com.huayi.doupo.base.model.InstPlayerRecruit;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.util.base.StringUtil;

public class InstPlayerRecruitDAL extends DALFather {
    
    @SuppressWarnings("rawtypes")
    public class ItemMapper implements RowMapper {
        
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstPlayerRecruit instPlayerRecruit = new InstPlayerRecruit();
            instPlayerRecruit.setId(rs.getInt("id"), 0);
            instPlayerRecruit.setInstPlayerId(rs.getInt("instPlayerId"), 0);
            instPlayerRecruit.setRecruitTypeId(rs.getInt("recruitTypeId"), 0);
            instPlayerRecruit.setRecruitSumTimes(rs.getInt("recruitSumTimes"), 0);
            instPlayerRecruit.setFeeRecruitTimes(rs.getInt("feeRecruitTimes"), 0);
            instPlayerRecruit.setLastRecruitTime(rs.getString("lastRecruitTime"), 0);
            instPlayerRecruit.setLastFreeRecruitTime(rs.getString("lastFreeRecruitTime"), 0);
            instPlayerRecruit.setNextFreeRecruitTime(rs.getString("nextFreeRecruitTime"), 0);
            instPlayerRecruit.setRemainRecruitTimes(rs.getInt("remainRecruitTimes"), 0);
            instPlayerRecruit.setVersion(rs.getInt("version"), 0);
            instPlayerRecruit.setInsertTime(rs.getString("insertTime"), 0);
            instPlayerRecruit.setUpdateTime(rs.getString("updateTime"), 0);
			return instPlayerRecruit;
		}
	}
    
	public InstPlayerRecruit add(final InstPlayerRecruit model, int instPlayerId) throws Exception {
		
		if (model.getId() != 0) {
			return model;
		}
		
        try { 
            final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            StringBuilder strSql = new StringBuilder();
	        strSql.append(" insert into Inst_Player_Recruit (");
	        strSql.append("instPlayerId,recruitTypeId,recruitSumTimes,feeRecruitTimes,lastRecruitTime,lastFreeRecruitTime,nextFreeRecruitTime,remainRecruitTimes,version,insertTime,updateTime");
	        strSql.append(" )");
	        strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?) ");
            
            final String sql = strSql.toString();
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            this.getJdbcTemplate().update(new PreparedStatementCreator(){
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, model.getInstPlayerId());
                    ps.setInt(2, model.getRecruitTypeId());
                    ps.setInt(3, model.getRecruitSumTimes());
                    ps.setInt(4, model.getFeeRecruitTimes());
                    ps.setString(5, model.getLastRecruitTime());
                    ps.setString(6, model.getLastFreeRecruitTime());
                    ps.setString(7, model.getNextFreeRecruitTime());
                    ps.setInt(8, model.getRemainRecruitTimes());
                    ps.setInt(9, 0);
                    ps.setString(10, updateTime);
                    ps.setString(11, updateTime);
                    return ps;
                } 
            },keyHolder);
            
            model.setId(keyHolder.getKey().intValue());
            model.setVersion(0);
            model.setInsertTime(updateTime, 0);
            model.setUpdateTime(updateTime, 0);
            PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		    if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
                 playerMemObj.instPlayerRecruitMap.put(model.getId(), model);
		    }
          
		} catch (Exception e) {
			throw e;
		}
		return model;
	}
    
    public void batchAdd (final List<InstPlayerRecruit> list) {

        final String updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        StringBuilder sql = new StringBuilder();
	    sql.append(" insert into Inst_Player_Recruit (");
	    sql.append("instPlayerId,recruitTypeId,recruitSumTimes,feeRecruitTimes,lastRecruitTime,lastFreeRecruitTime,nextFreeRecruitTime,remainRecruitTimes,version,insertTime,updateTime");
	    sql.append(" )");
	    sql.append(" values (?,?,?,?,?,?,?,?,?,?,?) ");
            
	    BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
	          public void setValues(PreparedStatement ps, int i) throws SQLException{
	        	   InstPlayerRecruit model = (InstPlayerRecruit)list.get(i);
                   ps.setInt(1, model.getInstPlayerId());
                   ps.setInt(2, model.getRecruitTypeId());
                   ps.setInt(3, model.getRecruitSumTimes());
                   ps.setInt(4, model.getFeeRecruitTimes());
                   ps.setString(5, model.getLastRecruitTime());
                   ps.setString(6, model.getLastFreeRecruitTime());
                   ps.setString(7, model.getNextFreeRecruitTime());
                   ps.setInt(8, model.getRemainRecruitTimes());
                    ps.setInt(9, 0);
                    ps.setString(10, updateTime);
                    ps.setString(11, updateTime);
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
                playerMemObj.instPlayerRecruitMap.remove(id);
			}
			String sql = "delete from Inst_Player_Recruit  where id = ?";
            Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}
    
	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_Player_Recruit where 1=1 ");

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
    
	public InstPlayerRecruit update(InstPlayerRecruit model, int instPlayerId) throws Exception {
		try {
            Object[] params = null;
            int version = model.getVersion() + 1;
            final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuffer sql = new StringBuffer("update Inst_Player_Recruit set ");
            
            sql.append("instPlayerId=?,recruitTypeId=?,recruitSumTimes=?,feeRecruitTimes=?,lastRecruitTime=?,lastFreeRecruitTime=?,nextFreeRecruitTime=?,remainRecruitTimes=?,version=?,insertTime=?,updateTime=? ");
            
            sql.append("where id=? and version=?");
            params = new Object[] { model.getInstPlayerId(),model.getRecruitTypeId(),model.getRecruitSumTimes(),model.getFeeRecruitTimes(),model.getLastRecruitTime(),model.getLastFreeRecruitTime(),model.getNextFreeRecruitTime(),model.getRemainRecruitTimes(),version,model.getInsertTime(),updateTime, model.getId(), model.getVersion() };

			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			}else{
				model.setVersion(version, 0);
                model.setUpdateTime(updateTime, 0);
                PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
	        		playerMemObj.instPlayerRecruitMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}
    
    @SuppressWarnings("unchecked")
	public InstPlayerRecruit getModel(int id, int instPlayerId) {
		try {
            PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
        		InstPlayerRecruit model = playerMemObj.instPlayerRecruitMap.get(id);
        		if (model == null) {
        			String sql = "select * from Inst_Player_Recruit where id=?";
				    Object[] params = new Object[]{id};
        			playerMemObj.instPlayerRecruitMap.put(id, (InstPlayerRecruit) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
			    } else {
                    int cacheVersion = model.getVersion();
                    List<Map<String, Object>> list = sqlHelper("select version from Inst_Player_Recruit where id = " + id);
                    int dbVersion = (int)list.get(0).get("version");
                    if (cacheVersion != dbVersion) {
                        String sql = "select * from Inst_Player_Recruit where id=?";
                        Object[] params = new Object[]{id};
                        playerMemObj.instPlayerRecruitMap.put(id, (InstPlayerRecruit) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
                    }
			    }
                
                model = playerMemObj.instPlayerRecruitMap.get(id);
                model.result = "";
        		return model;
			} else {
			    String sql = "select * from Inst_Player_Recruit where id=?";
			    Object[] params = new Object[]{id};
			    InstPlayerRecruit model = ( InstPlayerRecruit) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
			    model.result = "";
                return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}
    
    @SuppressWarnings("unchecked")	
	public List<InstPlayerRecruit> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
        PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
    		sql = new StringBuffer("select id, version from Inst_Player_Recruit ");
		}else {
			sql = new StringBuffer("select * from Inst_Player_Recruit ");
		}
        if (StringUtil.isNotEmpty(strWhere)) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
    		return listCacheCommonHandler(sql.toString(), instPlayerId);
    	} else {
		    List<InstPlayerRecruit> instPlayerRecruitList = (List<InstPlayerRecruit>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		    return instPlayerRecruitList;
		}
	}
    
	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_Player_Recruit ");
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
	public List<InstPlayerRecruit> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
            if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
                sql = new StringBuffer("select id, version from Inst_Player_Recruit ");
            }else {
                sql = new StringBuffer("select * from Inst_Player_Recruit ");
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
			    return (List<InstPlayerRecruit>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}
    
	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_Recruit where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_Recruit");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_Player_Recruit ");
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
	public List<InstPlayerRecruit> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
        PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
        if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
            sql = new StringBuffer("select a.id, a.version from Inst_Player_Recruit a ");
        }else {
            sql = new StringBuffer("select a.* from Inst_Player_Recruit a ");
        }
		if (StringUtil.isNotEmpty(afterSql)) {
		    sql.append(afterSql);
	    }
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
		    return (List<InstPlayerRecruit>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}
    
	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_Player_Recruit a ");
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
            StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_Player_Recruit");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			throw e;
		}
	}

	private List<InstPlayerRecruit> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstPlayerRecruit> modelList = new ArrayList<InstPlayerRecruit>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstPlayerRecruit model = playerMemObj.instPlayerRecruitMap.get(id);
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


