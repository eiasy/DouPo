
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
import com.huayi.doupo.base.model.SysMsgRule;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.util.base.StringUtil;

public class SysMsgRuleDAL extends DALFather {
    
    @SuppressWarnings("rawtypes")
    public class ItemMapper implements RowMapper {
        
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysMsgRule sysMsgRule = new SysMsgRule();
            sysMsgRule.setId(rs.getInt("id"), 0);
            sysMsgRule.setName(rs.getString("name"), 0);
            sysMsgRule.setSname(rs.getString("sname"), 0);
            sysMsgRule.setClassPath(rs.getString("classPath"), 0);
            sysMsgRule.setDescription(rs.getString("description"), 0);
            sysMsgRule.setVersion(rs.getInt("version"), 0);
			return sysMsgRule;
		}
	}
    
	public SysMsgRule add(final SysMsgRule model, int instPlayerId) throws Exception {
        try { 
            StringBuilder strSql = new StringBuilder();
	        strSql.append(" insert into Sys_MsgRule (");
	        strSql.append("name,sname,classPath,description,version");
	        strSql.append(" )");
	        strSql.append(" values (?,?,?,?,?) ");
            
            final String sql = strSql.toString();
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            this.getJdbcTemplate().update(new PreparedStatementCreator(){
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, model.getName());
                    ps.setString(2, model.getSname());
                    ps.setString(3, model.getClassPath());
                    ps.setString(4, model.getDescription());
                    ps.setInt(5, 0);
                    return ps;
                } 
            },keyHolder);
            
            model.setId(keyHolder.getKey().intValue());
            model.setVersion(0);
            PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		    if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
                 playerMemObj.sysMsgRuleMap.put(model.getId(), model);
		    }
          
		} catch (Exception e) {
			throw e;
		}
		return model;
	}
    
    public void batchAdd (final List<SysMsgRule> list) {

        StringBuilder sql = new StringBuilder();
	    sql.append(" insert into Sys_MsgRule (");
	    sql.append("name,sname,classPath,description,version");
	    sql.append(" )");
	    sql.append(" values (?,?,?,?,?) ");
            
	    BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
	          public void setValues(PreparedStatement ps, int i) throws SQLException{
	        	   SysMsgRule model = (SysMsgRule)list.get(i);
                   ps.setString(1, model.getName());
                   ps.setString(2, model.getSname());
                   ps.setString(3, model.getClassPath());
                   ps.setString(4, model.getDescription());
                    ps.setInt(5, 0);
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
                playerMemObj.sysMsgRuleMap.remove(id);
			}
			String sql = "delete from Sys_MsgRule  where id = ?";
            Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}
    
	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Sys_MsgRule where 1=1 ");

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
    
	public SysMsgRule update(SysMsgRule model, int instPlayerId) throws Exception {
		try {
            Object[] params = null;
            int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Sys_MsgRule set ");
            
            sql.append("name=?,sname=?,classPath=?,description=?,version=? ");
            
            sql.append("where id=? and version=?");
            params = new Object[] { model.getName(),model.getSname(),model.getClassPath(),model.getDescription(),version, model.getId(), model.getVersion() };

			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			}else{
				model.setVersion(version, 0);
                PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
	        		playerMemObj.sysMsgRuleMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}
    
    @SuppressWarnings("unchecked")
	public SysMsgRule getModel(int id, int instPlayerId) {
		try {
            PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
        		SysMsgRule model = playerMemObj.sysMsgRuleMap.get(id);
        		if (model == null) {
        			String sql = "select * from Sys_MsgRule where id=?";
				    Object[] params = new Object[]{id};
        			playerMemObj.sysMsgRuleMap.put(id, (SysMsgRule) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
			    } else {
                    int cacheVersion = model.getVersion();
                    List<Map<String, Object>> list = sqlHelper("select version from Sys_MsgRule where id = " + id);
                    int dbVersion = (int)list.get(0).get("version");
                    if (cacheVersion != dbVersion) {
                        String sql = "select * from Sys_MsgRule where id=?";
                        Object[] params = new Object[]{id};
                        playerMemObj.sysMsgRuleMap.put(id, (SysMsgRule) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
                    }
			    }
        		return playerMemObj.sysMsgRuleMap.get(id);
			} else {
			    String sql = "select * from Sys_MsgRule where id=?";
			    Object[] params = new Object[]{id};
			    SysMsgRule model = ( SysMsgRule) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
			    return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}
    
    @SuppressWarnings("unchecked")	
	public List<SysMsgRule> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
        PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
    		sql = new StringBuffer("select id, version from Sys_MsgRule ");
		}else {
			sql = new StringBuffer("select * from Sys_MsgRule ");
		}
        if (StringUtil.isNotEmpty(strWhere)) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
    		return listCacheCommonHandler(sql.toString(), instPlayerId);
    	} else {
		    List<SysMsgRule> sysMsgRuleList = (List<SysMsgRule>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		    return sysMsgRuleList;
		}
	}
    
	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Sys_MsgRule ");
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
	public List<SysMsgRule> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
            if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
                sql = new StringBuffer("select id, version from Sys_MsgRule ");
            }else {
                sql = new StringBuffer("select * from Sys_MsgRule ");
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
			    return (List<SysMsgRule>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}
    
	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Sys_MsgRule where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Sys_MsgRule");
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
	public List<SysMsgRule> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
        PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
        if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
            sql = new StringBuffer("select a.id, a.version from Sys_MsgRule a ");
        }else {
            sql = new StringBuffer("select a.* from Sys_MsgRule a ");
        }
		if (StringUtil.isNotEmpty(afterSql)) {
		    sql.append(afterSql);
	    }
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
		    return (List<SysMsgRule>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}
    
	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Sys_MsgRule a ");
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
            StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Sys_MsgRule");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			throw e;
		}
	}

	private List<SysMsgRule> listCacheCommonHandler(String sql, int instPlayerId){
		List<SysMsgRule> modelList = new ArrayList<SysMsgRule>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			SysMsgRule model = playerMemObj.sysMsgRuleMap.get(id);
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


