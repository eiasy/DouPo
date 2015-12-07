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
import com.huayi.doupo.base.model.DictChapter;

public class DictChapterDAL extends DALFather {
	@SuppressWarnings("rawtypes")
	public class ItemMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictChapter dictChapter = new DictChapter();
			dictChapter.setId(rs.getInt("id"), 0);
			dictChapter.setName(rs.getString("name"), 0);
			dictChapter.setType(rs.getInt("type"), 0);
			dictChapter.setOpenTime(rs.getString("openTime"), 0);
			dictChapter.setOpenLeve(rs.getInt("openLeve"), 0);
			dictChapter.setChapterId(rs.getInt("chapterId"), 0);
			dictChapter.setStarNum(rs.getInt("starNum"), 0);
			dictChapter.setStarOne(rs.getInt("starOne"), 0);
			dictChapter.setThingsOne(rs.getString("thingsOne"), 0);
			dictChapter.setStarTwo(rs.getInt("starTwo"), 0);
			dictChapter.setThingsTwo(rs.getString("thingsTwo"), 0);
			dictChapter.setStarThree(rs.getInt("starThree"), 0);
			dictChapter.setThingsThree(rs.getString("thingsThree"), 0);
			dictChapter.setIsBarrier(rs.getInt("isBarrier"), 0);
			dictChapter.setFightNum(rs.getInt("fightNum"), 0);
			dictChapter.setBackGroundPictureS(rs.getString("backGroundPictureS"), 0);
			dictChapter.setBackGroundPictureD(rs.getString("backGroundPictureD"), 0);
			dictChapter.setBackGroundWar(rs.getString("backGroundWar"), 0);
			dictChapter.setDescription(rs.getString("description"), 0);
			dictChapter.setVersion(rs.getInt("version"), 0);
			return dictChapter;
		}
	}

	public DictChapter add(final DictChapter model, int instPlayerId) throws Exception {
		try {
			StringBuilder strSql = new StringBuilder();
			strSql.append(" insert into Dict_Chapter (");
			strSql.append("name,type,openTime,openLeve,chapterId,starNum,starOne,thingsOne,starTwo,thingsTwo,starThree,thingsThree,isBarrier,fightNum,backGroundPictureS,backGroundPictureD,backGroundWar,description,version");
			strSql.append(" )");
			strSql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			final String sql = strSql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getType());
					ps.setString(3, model.getOpenTime());
					ps.setInt(4, model.getOpenLeve());
					ps.setInt(5, model.getChapterId());
					ps.setInt(6, model.getStarNum());
					ps.setInt(7, model.getStarOne());
					ps.setString(8, model.getThingsOne());
					ps.setInt(9, model.getStarTwo());
					ps.setString(10, model.getThingsTwo());
					ps.setInt(11, model.getStarThree());
					ps.setString(12, model.getThingsThree());
					ps.setInt(13, model.getIsBarrier());
					ps.setInt(14, model.getFightNum());
					ps.setString(15, model.getBackGroundPictureS());
					ps.setString(16, model.getBackGroundPictureD());
					ps.setString(17, model.getBackGroundWar());
					ps.setString(18, model.getDescription());
					ps.setInt(19, 0);
					return ps;
				}
			},keyHolder);

			model.setId(keyHolder.getKey().intValue());
			model.setVersion(0);
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				playerMemObj.dictChapterMap.put(model.getId(), model);
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	public void batchAdd (final List<DictChapter> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into Dict_Chapter (");
		sql.append("name,type,openTime,openLeve,chapterId,starNum,starOne,thingsOne,starTwo,thingsTwo,starThree,thingsThree,isBarrier,fightNum,backGroundPictureS,backGroundPictureD,backGroundWar,description,version");
		sql.append(" )");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				DictChapter model = (DictChapter)list.get(i);
					ps.setString(1, model.getName());
					ps.setInt(2, model.getType());
					ps.setString(3, model.getOpenTime());
					ps.setInt(4, model.getOpenLeve());
					ps.setInt(5, model.getChapterId());
					ps.setInt(6, model.getStarNum());
					ps.setInt(7, model.getStarOne());
					ps.setString(8, model.getThingsOne());
					ps.setInt(9, model.getStarTwo());
					ps.setString(10, model.getThingsTwo());
					ps.setInt(11, model.getStarThree());
					ps.setString(12, model.getThingsThree());
					ps.setInt(13, model.getIsBarrier());
					ps.setInt(14, model.getFightNum());
					ps.setString(15, model.getBackGroundPictureS());
					ps.setString(16, model.getBackGroundPictureD());
					ps.setString(17, model.getBackGroundWar());
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
				playerMemObj.dictChapterMap.remove(id);
			}
			String sql = "delete from Dict_Chapter  where id = ?";
			Object[] params = new Object[]{id};
			return this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			throw e;
		}
	}

	public int deleteByWhere(String strWhere) throws Exception {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from Dict_Chapter where 1=1 ");
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

	public DictChapter update(DictChapter model, int instPlayerId) throws Exception {
		try {
			Object[] params = null;
			int version = model.getVersion() + 1;
			StringBuffer sql = new StringBuffer("update Dict_Chapter set ");
			sql.append("name=?,type=?,openTime=?,openLeve=?,chapterId=?,starNum=?,starOne=?,thingsOne=?,starTwo=?,thingsTwo=?,starThree=?,thingsThree=?,isBarrier=?,fightNum=?,backGroundPictureS=?,backGroundPictureD=?,backGroundWar=?,description=?,version=? ");
			sql.append("where id=? and version=?");
			params = new Object[] { model.getName(),model.getType(),model.getOpenTime(),model.getOpenLeve(),model.getChapterId(),model.getStarNum(),model.getStarOne(),model.getThingsOne(),model.getStarTwo(),model.getThingsTwo(),model.getStarThree(),model.getThingsThree(),model.getIsBarrier(),model.getFightNum(),model.getBackGroundPictureS(),model.getBackGroundPictureD(),model.getBackGroundWar(),model.getDescription(),version , model.getId(), model.getVersion() };
			int count = this.getJdbcTemplate().update(sql.toString(), params);
			if (count == 0) {
				throw new Exception("Concurrent Exception");
			} else {
				model.setVersion(version, 0);
				PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
				if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
					playerMemObj.dictChapterMap.put(model.getId(), model);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public DictChapter getModel(int id, int instPlayerId) {
		try {
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				DictChapter model = playerMemObj.dictChapterMap.get(id);
				if (model == null) {
					String sql = "select * from Dict_Chapter where id=?";
					Object[] params = new Object[]{id};
					playerMemObj.dictChapterMap.put(id, (DictChapter) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
				} else {
					int cacheVersion = model.getVersion();
					List<Map<String, Object>> list = sqlHelper("select version from Dict_Chapter where id = " + id);
					 int dbVersion = (int)list.get(0).get("version");
					if (cacheVersion != dbVersion) {
						String sql = "select * from Dict_Chapter where id=?";
						Object[] params = new Object[]{id};
						playerMemObj.dictChapterMap.put(id, (DictChapter) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));
					}
				}
				model = playerMemObj.dictChapterMap.get(id);
				model.result = "";
				return model;
			} else {
				String sql = "select * from Dict_Chapter where id=?";
				Object[] params = new Object[]{id};
				DictChapter model = ( DictChapter) this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());
				model.result = "";
				return model;
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictChapter> getList(String strWhere, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select id, version from Dict_Chapter ");
		}else {
			sql = new StringBuffer("select * from Dict_Chapter ");
		}
		if (strWhere != null && !strWhere.equals("")) {
			sql.append(" where " + strWhere);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			List<DictChapter> dictChapterList = (List<DictChapter>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			return dictChapterList;
		}
	}

	public List<Long> getListIdByWhere(String strWhere) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select id from Dict_Chapter ");
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
	public List<DictChapter> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {
		try {
			StringBuffer sql = null;
			PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
			if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
				sql = new StringBuffer("select id, version from Dict_Chapter ");
			}else {
				sql = new StringBuffer("select * from Dict_Chapter ");
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
				return (List<DictChapter>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isExist(long id, String strWhere) throws Exception {
		try {
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Chapter where id =?");
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
			StringBuffer sql = new StringBuffer("select count(*) from Dict_Chapter");
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
			StringBuffer sql = new StringBuffer("select count(*) as cnt from Dict_Chapter ");
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
	public List<DictChapter> getListFromMoreTale(String afterSql, int instPlayerId) {
		StringBuffer sql = null;
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			sql = new StringBuffer("select a.id, a.version from Dict_Chapter a ");
		}else {
			sql = new StringBuffer("select a.* from Dict_Chapter a ");
		}
		if (afterSql != null && !afterSql.equals("")) {
			sql.append(afterSql);
		}
		if (instPlayerId != 0 && isUseCach() && playerMemObj != null) {
			return listCacheCommonHandler(sql.toString(), instPlayerId);
		} else {
			return (List<DictChapter>) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());
		}
	}

	public List<Long> getListIdFromMoreTable(String afterSql) throws Exception {
		try {
			List<Long> listLong = new ArrayList<Long>();
			StringBuffer sql = new StringBuffer("select a.id from Dict_Chapter a ");
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
			StringBuffer sql = new StringBuffer("select "+statType+"("+conParam+") from  Dict_Chapter");
			if (strWhere != null && !strWhere.equals("")) {
				sql.append(" where " + strWhere);
			}
			return this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);
		} catch (Exception e) {
			return result;
		}
	}

	private List<DictChapter> listCacheCommonHandler(String sql, int instPlayerId){
		List<DictChapter> modelList = new ArrayList<DictChapter>();
		PlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);
		SqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());
		while (rsSet.next()) {
			int id = rsSet.getInt("id");
			int dbVersion = rsSet.getInt("version");
			DictChapter model = playerMemObj.dictChapterMap.get(id);
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