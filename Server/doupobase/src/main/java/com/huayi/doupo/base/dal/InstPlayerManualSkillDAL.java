
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
import com.huayi.doupo.base.model.InstPlayerManualSkill;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.util.base.StringUtil;

public class InstPlayerManualSkillDAL extends DALFather {
    
    @SuppressWarnings("rawtypes")
    public class ItemMapper implements RowMapper {
        
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			InstPlayerManualSkill instPlayerManualSkill = new InstPlayerManualSkill();
            instPlayerManualSkill.setId(rs.getInt("id"), 0);
            instPlayerManualSkill.setInstPlayerId(rs.getInt("instPlayerId"), 0);
            instPlayerManualSkill.setType(rs.getInt("type"), 0);
            instPlayerManualSkill.setManualSkillId(rs.getInt("manualSkillId"), 0);
            instPlayerManualSkill.setLevel(rs.getInt("level"), 0);
            instPlayerManualSkill.setExp(rs.getInt("exp"), 0);
            instPlayerManualSkill.setIsUse(rs.getInt("isUse"), 0);
            instPlayerManualSkill.setOperTime(rs.getString("operTime"), 0);
            instPlayerManualSkill.setVersion(rs.getInt("version"), 0);
            instPlayerManualSkill.setInsertTime(rs.getString("insertTime"), 0);
            instPlayerManualSkill.setUpdateTime(rs.getString("updateTime"), 0);
			return instPlayerManualSkill;
		}
	}
    
	public InstPlayerManualSkill add(final InstPlayerManualSkill model, int instPlayerId) throws Exception {
		
		if (model.getId() != 0) {
			return model;
		}
		
        try { 
            final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            StringBuilder strSql = new StringBuilder();
	        strSql.append(" insert into Inst_Player_ManualSkill (");
	        strSql.append("instPlayerId,type,manualSkillId,level,exp,isUse,operTime,version,insertTime,updateTime");
	        strSql.append(" )");
	        strSql.append(" values (?,?,?,?,?,?,?,?,?,?) ");
            
            final String sql = strSql.toString();
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            this.getJdbcTemplate().update(new PreparedStatementCreator(){
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, model.getInstPlayerId());
                    ps.setInt(2, model.getType());
                    ps.setInt(3, model.getManualSkillId());
                    ps.setInt(4, model.getLevel());
                    ps.setInt(5, model.getExp());
                    ps.setInt(6, model.getIsUse());
                    ps.setString(7, model.getOperTime());
                    ps.setInt(8, 0);
                    ps.setString(9, updateTime);
                    ps.setString(10, updateTime);
                    return ps;
                } 
            },keyHolder);
            
            model.setId(keyHolder.getKey().intValue());
            model.setVersion(0);
            model.setInsertTime(updateTime, 0);
            model.setUpdateTime(updateTime, 0);
            PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		    if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
		    	playerMemObj.instPlayerManualSkillMap.put(model.getId(), model);
		    }
          
		} catch (Exception e) {
			throw e;
		}
		return model;
	}
    
    public void batchAdd (final List<InstPlayerManualSkill> list) {

        final String updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        StringBuilder sql = new StringBuilder();
	    sql.append(" insert into Inst_Player_ManualSkill (");
	    sql.append("instPlayerId,type,manualSkillId,level,exp,isUse,operTime,version,insertTime,updateTime");
	    sql.append(" )");
	    sql.append(" values (?,?,?,?,?,?,?,?,?,?) ");
            
	    BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
	          public void setValues(PreparedStatement ps, int i) throws SQLException{
	        	   InstPlayerManualSkill model = (InstPlayerManualSkill)list.get(i);
                   ps.setInt(1, model.getInstPlayerId());
                   ps.setInt(2, model.getType());
                   ps.setInt(3, model.getManualSkillId());
                   ps.setInt(4, model.getLevel());
                   ps.setInt(5, model.getExp());
                   ps.setInt(6, model.getIsUse());
                   ps.setString(7, model.getOperTime());
                    ps.setInt(8, 0);
                    ps.setString(9, updateTime);
                    ps.setString(10, updateTime);
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
                playerMemObj.instPlayerManualSkillMap.remove(id);
			}
			String sql = "delete from Inst_Player_ManualSkill  where id = ?";
            Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}
    
	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Inst_Player_ManualSkill where 1=1 ");

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
    
	public InstPlayerManualSkill update(InstPlayerManualSkill model, int instPlayerId) throws Exception {
		try {
            Object[] params = null;
            int version = model.getVersion() + 1;
            final String  updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
			StringBuffer sql = new StringBuffer("update Inst_Player_ManualSkill set ");
            
            sql.append("instPlayerId=?,type=?,manualSkillId=?,level=?,exp=?,isUse=?,operTime=?,version=?,insertTime=?,updateTime=? ");
            
            sql.append("where id=? and version=?");
            params = new Object[] { model.getInstPlayerId(),model.getType(),model.getManualSkillId(),model.getLevel(),model.getExp(),model.getIsUse(),model.getOperTime(),version,model.getInsertTime(),updateTime, model.getId(), model.getVersion() };

			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			}else{
				model.setVersion(version, 0);
                model.setUpdateTime(updateTime, 0);
                PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
	        		playerMemObj.instPlayerManualSkillMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}
    
    @SuppressWarnings("unchecked")
	public InstPlayerManualSkill getModel(int id, int instPlayerId) {
		try {
            PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
        		InstPlayerManualSkill model = playerMemObj.instPlayerManualSkillMap.get(id);
        		if (model == null) {
        			String sql = "select * from Inst_Player_ManualSkill where id=?";
				    Object[] params = new Object[]{id};
        			playerMemObj.instPlayerManualSkillMap.put(id, (InstPlayerManualSkill) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
			    } else {
                    int cacheVersion = model.getVersion();
                    List<Map<String, Object>> list = sqlHelper("select version from Inst_Player_ManualSkill where id = " + id);
                    int dbVersion = (int)list.get(0).get("version");
                    if (cacheVersion != dbVersion) {
                        String sql = "select * from Inst_Player_ManualSkill where id=?";
                        Object[] params = new Object[]{id};
                        playerMemObj.instPlayerManualSkillMap.put(id, (InstPlayerManualSkill) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
                    }
			    }
                
                model = playerMemObj.instPlayerManualSkillMap.get(id);
                model.result = "";
        		return model;
			} else {
			    String sql = "select * from Inst_Player_ManualSkill where id=?";
			    Object[] params = new Object[]{id};
			    InstPlayerManualSkill model = ( InstPlayerManualSkill) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
			    model.result = "";
                return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}
    
    @SuppressWarnings("unchecked")	
	public List<InstPlayerManualSkill> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
        PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
    		sql = new StringBuffer("select id, version from Inst_Player_ManualSkill ");
		}else {
			sql = new StringBuffer("select * from Inst_Player_ManualSkill ");
		}
        if (StringUtil.isNotEmpty(strWhere)) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
    		return listCacheCommonHandler(sql.toString(), instPlayerId);
    	} else {
		    List<InstPlayerManualSkill> instPlayerManualSkillList = (List<InstPlayerManualSkill>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		    return instPlayerManualSkillList;
		}
	}
    
	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Inst_Player_ManualSkill ");
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
	public List<InstPlayerManualSkill> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
            if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
                sql = new StringBuffer("select id, version from Inst_Player_ManualSkill ");
            }else {
                sql = new StringBuffer("select * from Inst_Player_ManualSkill ");
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
			    return (List<InstPlayerManualSkill>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}
    
	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_ManualSkill where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Inst_Player_ManualSkill");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Inst_Player_ManualSkill ");
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
	public List<InstPlayerManualSkill> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
        PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
        if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
            sql = new StringBuffer("select a.id, a.version from Inst_Player_ManualSkill a ");
        }else {
            sql = new StringBuffer("select a.* from Inst_Player_ManualSkill a ");
        }
		if (StringUtil.isNotEmpty(afterSql)) {
		    sql.append(afterSql);
	    }
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
		    return (List<InstPlayerManualSkill>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}
    
	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Inst_Player_ManualSkill a ");
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
            StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Inst_Player_ManualSkill");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			throw e;
		}
	}

	private List<InstPlayerManualSkill> listCacheCommonHandler(String sql, int instPlayerId){
		List<InstPlayerManualSkill> modelList = new ArrayList<InstPlayerManualSkill>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			InstPlayerManualSkill model = playerMemObj.instPlayerManualSkillMap.get(id);
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


