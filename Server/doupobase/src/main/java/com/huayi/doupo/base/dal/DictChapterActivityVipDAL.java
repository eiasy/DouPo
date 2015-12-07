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
import com.huayi.doupo.base.model.DictChapterActivityVip;

public class DictChapterActivityVipDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictChapterActivityVip dictChapterActivityVip = new DictChapterActivityVip();
			dictChapterActivityVip.setId(rs.getInt("id"), 0);
			dictChapterActivityVip.setVip1(rs.getInt("vip1"), 0);
			dictChapterActivityVip.setVip2(rs.getInt("vip2"), 0);
			dictChapterActivityVip.setVip3(rs.getInt("vip3"), 0);
			dictChapterActivityVip.setVip4(rs.getInt("vip4"), 0);
			dictChapterActivityVip.setVip5(rs.getInt("vip5"), 0);
			dictChapterActivityVip.setVip6(rs.getInt("vip6"), 0);
			dictChapterActivityVip.setVip7(rs.getInt("vip7"), 0);
			dictChapterActivityVip.setVip8(rs.getInt("vip8"), 0);
			dictChapterActivityVip.setVip9(rs.getInt("vip9"), 0);
			dictChapterActivityVip.setVip10(rs.getInt("vip10"), 0);
			dictChapterActivityVip.setVip11(rs.getInt("vip11"), 0);
			dictChapterActivityVip.setVip12(rs.getInt("vip12"), 0);
			dictChapterActivityVip.setVip13(rs.getInt("vip13"), 0);
			dictChapterActivityVip.setVip14(rs.getInt("vip14"), 0);
			dictChapterActivityVip.setVip15(rs.getInt("vip15"), 0);
			dictChapterActivityVip.setBuyGold(rs.getInt("buyGold"), 0);
			dictChapterActivityVip.setBuyGoldAdd(rs.getInt("buyGoldAdd"), 0);
			dictChapterActivityVip.setDescription(rs.getString("description"), 0);
			dictChapterActivityVip.setVersion(rs.getInt("version"), 0);
			return dictChapterActivityVip;
		}
	}

	public DictChapterActivityVip add(final DictChapterActivityVip model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_ChapterActivity_Vip (");
			strSql.append("vip1,vip2,vip3,vip4,vip5,vip6,vip7,vip8,vip9,vip10,vip11,vip12,vip13,vip14,vip15,buyGold,buyGoldAdd,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, model.getVip1());
					ps.setInt(2, model.getVip2());
					ps.setInt(3, model.getVip3());
					ps.setInt(4, model.getVip4());
					ps.setInt(5, model.getVip5());
					ps.setInt(6, model.getVip6());
					ps.setInt(7, model.getVip7());
					ps.setInt(8, model.getVip8());
					ps.setInt(9, model.getVip9());
					ps.setInt(10, model.getVip10());
					ps.setInt(11, model.getVip11());
					ps.setInt(12, model.getVip12());
					ps.setInt(13, model.getVip13());
					ps.setInt(14, model.getVip14());
					ps.setInt(15, model.getVip15());
					ps.setInt(16, model.getBuyGold());
					ps.setInt(17, model.getBuyGoldAdd());
					ps.setString(18, model.getDescription());
					ps.setInt(19, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictChapterActivityVipMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictChapterActivityVip> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_ChapterActivity_Vip (");
		sql.append("vip1,vip2,vip3,vip4,vip5,vip6,vip7,vip8,vip9,vip10,vip11,vip12,vip13,vip14,vip15,buyGold,buyGoldAdd,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictChapterActivityVip model = (DictChapterActivityVip)list.get(i);
					ps.setInt(1, model.getVip1());
					ps.setInt(2, model.getVip2());
					ps.setInt(3, model.getVip3());
					ps.setInt(4, model.getVip4());
					ps.setInt(5, model.getVip5());
					ps.setInt(6, model.getVip6());
					ps.setInt(7, model.getVip7());
					ps.setInt(8, model.getVip8());
					ps.setInt(9, model.getVip9());
					ps.setInt(10, model.getVip10());
					ps.setInt(11, model.getVip11());
					ps.setInt(12, model.getVip12());
					ps.setInt(13, model.getVip13());
					ps.setInt(14, model.getVip14());
					ps.setInt(15, model.getVip15());
					ps.setInt(16, model.getBuyGold());
					ps.setInt(17, model.getBuyGoldAdd());
					ps.setString(18, model.getDescription());
					ps.setInt(19, 0);
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
				playerMemObj.dictChapterActivityVipMap.remove(id);
			}
			String sql = "delete from Dict_ChapterActivity_Vip  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_ChapterActivity_Vip where 1=1 ");
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

	public DictChapterActivityVip update(DictChapterActivityVip model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_ChapterActivity_Vip set ");
			sql.append("vip1=?,vip2=?,vip3=?,vip4=?,vip5=?,vip6=?,vip7=?,vip8=?,vip9=?,vip10=?,vip11=?,vip12=?,vip13=?,vip14=?,vip15=?,buyGold=?,buyGoldAdd=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getVip1(),model.getVip2(),model.getVip3(),model.getVip4(),model.getVip5(),model.getVip6(),model.getVip7(),model.getVip8(),model.getVip9(),model.getVip10(),model.getVip11(),model.getVip12(),model.getVip13(),model.getVip14(),model.getVip15(),model.getBuyGold(),model.getBuyGoldAdd(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictChapterActivityVipMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictChapterActivityVip getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictChapterActivityVip model = playerMemObj.dictChapterActivityVipMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_ChapterActivity_Vip where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictChapterActivityVipMap.put(id, (DictChapterActivityVip) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_ChapterActivity_Vip where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_ChapterActivity_Vip where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictChapterActivityVipMap.put(id, (DictChapterActivityVip) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictChapterActivityVipMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_ChapterActivity_Vip where id=?";
				Object[] params = new Object[]{id};
				DictChapterActivityVip model = ( DictChapterActivityVip) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictChapterActivityVip> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_ChapterActivity_Vip ");
		}else {
			sql = new StringBuffer("select * from Dict_ChapterActivity_Vip ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictChapterActivityVip> dictChapterActivityVipList = (List<DictChapterActivityVip>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictChapterActivityVipList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_ChapterActivity_Vip ");
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
	public List<DictChapterActivityVip> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_ChapterActivity_Vip ");
			}else {
				sql = new StringBuffer("select * from Dict_ChapterActivity_Vip ");
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
				return (List<DictChapterActivityVip>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_ChapterActivity_Vip where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_ChapterActivity_Vip");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_ChapterActivity_Vip ");
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
	public List<DictChapterActivityVip> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_ChapterActivity_Vip a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_ChapterActivity_Vip a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictChapterActivityVip>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_ChapterActivity_Vip a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_ChapterActivity_Vip");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictChapterActivityVip> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictChapterActivityVip> modelList = new ArrayList<DictChapterActivityVip>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictChapterActivityVip model = playerMemObj.dictChapterActivityVipMap.get(id);
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