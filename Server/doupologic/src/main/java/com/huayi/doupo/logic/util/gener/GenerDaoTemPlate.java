package com.huayi.doupo.logic.util.gener;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.util.base.FileUtil;

public class GenerDaoTemPlate {
	
	private static int whichDB = 0;
	private static Connection conn;
	private static String outPath="E:/doupoout/DaoOut";
	private static Map<Integer, String> dbInfoMap = new HashMap<Integer, String>();
	static{
		dbInfoMap.put(0, "127.0.0.1_doupogener_root_123456");
		dbInfoMap.put(1, "192.168.1.36_doupo_root_123456");
		dbInfoMap.put(2, "192.168.1.42_doupoNC_root_123456");
		dbInfoMap.put(3, "192.168.4.202_dragonball1_dragonball_dragonballdbpass");
		dbInfoMap.put(4, "192.168.4.202_dragonball2_dragonball_dragonballdbpass");
		dbInfoMap.put(5, "192.168.1.42_doupo_root_123456");
		dbInfoMap.put(6, "192.168.1.42_doupoNC_root_123456");
		dbInfoMap.put(7, "192.168.1.30_gener_root_666666");
	}
	private static String dBName = dbInfoMap.get(whichDB).split("_")[1];

	/**
	 * 连接数据库
	 * @return
	 * @throws Exception
	 */
	private static Connection getMySQLConnection() throws Exception {
		if(conn == null) {
			String dbInfo = dbInfoMap.get(whichDB);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + dbInfo.split("_")[0] + ":3306/" + dbInfo.split("_")[1] + "", dbInfo.split("_")[2], dbInfo.split("_")[3]);
		}
		return conn;
	}
	
	/**
	 * 类名
	 * @author mp
	 * @date 2015-6-18 下午2:42:45
	 * @param name
	 * @return
	 * @Description
	 */
	private static String classUpper(String name) {
		String string = name;
		int ix = name.indexOf("_");
		if (ix >= 0) {
			String[] strs = name.split("_");
			String className = "";
			for (String s : strs) {
				className = className + s;
			}
			string = className;
		}
		return string.substring(0,1).toUpperCase()+string.substring(1);
	}

	/**
	 * 类变量名
	 * @author mp
	 * @date 2015-6-18 下午2:43:32
	 * @param name
	 * @return
	 * @Description
	 */
	private static String classLower(String name) {
		String string = name;
		int ix = name.indexOf("_");
		if (ix >= 0) {
			String[] strs = name.split("_");
			String className = "";
			for (String s : strs) {
				className = className + s;
			}
			string = className;
		}
		return string.substring(0,1).toLowerCase()+string.substring(1);
	}
	
	/**
	 * 列名大写的
	 * @author mp
	 * @date 2015-6-18 下午2:57:57
	 * @param name
	 * @return
	 * @Description
	 */
	private static String colUpper(String name) {
		
		String string = name;
		int ix = name.indexOf("_");
		if (ix >= 0) {
			String[] strs = name.split("_");
			String className = "";
			for (String s : strs) {
				className = className + s;
			}
			string = className;
		}
		return string.substring(0,1).toUpperCase()+string.substring(1);
	}
	
	/**
	 * 列名类型大写的
	 * @author mp
	 * @date 2015-6-18 下午2:58:53
	 * @return
	 * @Description
	 */
	private static String colTypeUpper (String string) {
		return string.substring(0,1).toUpperCase()+string.substring(1);
	}
	
	/**
	 * 获取所有列,以逗号分开的,例如：instPlayerId,activityId,activityTime,isForever,useNum,spareOne,spareTwo,version,insertTime,updateTime
	 * @author mp
	 * @date 2015-6-18 下午3:22:08
	 * @param nameAndTypeList
	 * @return
	 * @Description
	 */
	private static String allCols (List<String> nameAndTypeList) {
		StringBuilder ret = new StringBuilder();
		for (String cols : nameAndTypeList) {
			String col = cols.split("_")[0];
			ret.append(col).append(",");
		}
		return ret.toString().substring(0, ret.toString().length() - 1);
	}
	
	/**
	 * 指定符号用,分隔  例如：?,?,?,?,?,?,?,?,?,?
	 * @author mp
	 * @date 2015-6-24 上午11:40:14
	 * @param nameAndTypeList
	 * @return
	 * @Description
	 */
	private static String spaceCharacter (List<String> nameAndTypeList) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < nameAndTypeList.size(); i++) {
			ret.append("?").append(",");
		}
		return ret.toString().substring(0, ret.toString().length() - 1);
	}

	/**
	 * 获取所有列和指定符号的组合,用,分隔的 例如：instPlayerId=?,activityId=?,activityTime=?,isForever=?,useNum=?
	 * @author mp
	 * @date 2015-6-24 上午11:41:56
	 * @param nameAndTypeList
	 * @return
	 * @Description
	 */
	private static String allColsAndChar (List<String> nameAndTypeList) {
		StringBuilder ret = new StringBuilder();
		for (String cols : nameAndTypeList) {
			String col = cols.split("_")[0];
			ret.append(col).append("=?").append(",");
		}
		return ret.toString().substring(0, ret.toString().length() - 1);
	}
	
	/**
	 * 获取所有列和指定对象的组合,用,分隔的 例如：model.getInstPlayerId(),model.getActivityId(),model.getActivityTime()
	 * @author mp
	 * @date 2015-6-24 上午11:49:12
	 * @param nameAndTypeList
	 * @return
	 * @Description
	 */
	private static String allColsAndObj (List<String> nameAndTypeList) {
		StringBuilder ret = new StringBuilder();
		for (String cols : nameAndTypeList) {
			String col = cols.split("_")[0];
			if (col.equals("version")) {
				ret.append("version").append(",");
			} else if (col.equals("updateTime")) {
				ret.append("updateTime").append(",");
			} else {
				ret.append("model.get").append(colUpper(col)).append("()").append(",");
			}
		}
		return ret.toString().substring(0, ret.toString().length() - 1);
	}
	
	/**
	 * 获取id和version
	 * @author mp
	 * @date 2015-6-24 上午11:53:48
	 * @return
	 * @Description
	 */
	private static String idAndVersion () {
		return ", model.getId(), model.getVersion()";
	}
	
	/**
	 * 获取表名和表注释
	 * @return
	 * @throws Exception
	 */
	private static Hashtable<String, String> getTableInfo() throws Exception {
		Hashtable<String, String> tableMap = new Hashtable<String, String>();
		String sql = "SELECT table_name ,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES  WHERE table_schema = '"
				+ dBName + "'";
		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			tableMap.put(rs.getString("table_name"), rs
					.getString("TABLE_COMMENT"));
		}
		return tableMap;
	}
	
	/**
	 * 生成SpringDalContext
	 * @author mp
	 * @date 2015-6-25 上午11:40:59
	 * @param tableNames
	 * @throws Exception
	 * @Description
	 */
	private static void genSpringDalContext(Iterator<String> tableNames) throws Exception {
		
		//生成所有finalDal
		StringBuilder finalDalMapper = new StringBuilder();
		while (tableNames.hasNext()) {
			String tableName = tableNames.next();
			finalDalMapper.append("\tpublic static final String "+classUpper(tableName)+"DAL = \""+classUpper(tableName)+"DAL\";\n");
		}
		
		//生成所有
		StringBuilder allMapper = new StringBuilder();
		allMapper.append("package com.huayi.doupo.base.dal.factory;\n\n");
		allMapper.append("public class SpringDalContext {\n");
		allMapper.append(finalDalMapper);
		allMapper.append("}");
		
		//生成文件
		geneFile(allMapper.toString(), outPath, "SpringDalContext.java");
		
//		System.out.println(allMapper);
	}
	
	/**
	 * 生成DALFactory
	 * @author mp
	 * @date 2015-6-25 上午11:26:20
	 * @param tableNames
	 * @throws Exception
	 * @Description
	 */
	private static void genDalFactory(Iterator<String> tableNames) throws Exception {
		
		//生成package和import
		StringBuilder importMapper = new StringBuilder();
		importMapper.append("package com.huayi.doupo.base.dal.factory;\n\n");
		importMapper.append("import com.huayi.doupo.base.dal.*;\n");
		importMapper.append("import com.huayi.doupo.base.util.logic.system.SpringUtil;\n\n");
		
		//生成所有getDao方法
		StringBuilder getDaoMapper = new StringBuilder();
		while (tableNames.hasNext()) {
			String tableName = tableNames.next();
			getDaoMapper.append("\tpublic static "+classUpper(tableName)+"DAL get"+classUpper(tableName)+"DAL() {\n");
			getDaoMapper.append("\t\treturn ("+classUpper(tableName)+"DAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext."+classUpper(tableName)+"DAL);\n");
			getDaoMapper.append("\t}\n\n");
		}
		
		//生成所有
		StringBuilder allMapper = new StringBuilder();
		allMapper.append(importMapper);
		allMapper.append("public class DALFactory {\n\n");
		allMapper.append(getDaoMapper);
		allMapper.append("}");
		
		//生成文件
		geneFile(allMapper.toString(), outPath, "DALFactory.java");
		
//		System.out.println(allMapper);
	}
	
	/**
	 * 生成dao相关xml
	 * @author mp
	 * @date 2015-6-25 上午10:54:16
	 * @param tableName
	 * @param tableCommon
	 * @throws Exception
	 * @Description
	 */
	private static void genDaoXml(Iterator<String> tableNames) throws Exception {
		
		//生成头和beans
		StringBuilder beansMapper = new StringBuilder();
		beansMapper.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		beansMapper.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"\n");
		beansMapper.append("\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:aop=\"http://www.springframework.org/schema/aop\"\n");
		beansMapper.append("\txmlns:tx=\"http://www.springframework.org/schema/tx\"\n");
		beansMapper.append("\txsi:schemaLocation=\"\n");
		beansMapper.append("\thttp://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd\n");
		beansMapper.append("\thttp://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd\n");
		beansMapper.append("\thttp://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd\n");
		beansMapper.append("\thttp://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd\">\n\n");
		
		//生成bean
		StringBuilder beanMapper = new StringBuilder();
		while (tableNames.hasNext()) {
			String tableName = tableNames.next();
			beanMapper.append("\t<bean id=\""+classUpper(tableName)+"DAL\"  class=\"com.huayi.doupo.base.dal."+classUpper(tableName)+"DAL\">\n");
			beanMapper.append("\t\t<property name=\"jdbcTemplate\" ref=\"JdbcTemplate\" />\n");
			beanMapper.append("\t</bean>\n\n");
		}
		
		//生成所有
		StringBuilder allMapper = new StringBuilder();
		allMapper.append(beansMapper).append(beanMapper).append("</beans>");
		
		//生成文件
		geneFile(allMapper.toString(), outPath, "AppDAL.xml");
		
//		System.out.println(allMapper);
	}
	
	/**
	 * 生成Dao层数据
	 * @author mp
	 * @date 2015-6-17 下午4:33:03
	 * @param tableName
	 * @param tableCommon
	 * @throws Exception
	 * @Description
	 */
	private static void genDaoJava(String tableName, String tableCommon) throws Exception {
		
		//生成package 和 import
		StringBuilder importMapper = new StringBuilder();
		importMapper.append("package com.huayi.doupo.base.dal;\n\n");
		importMapper.append("import java.sql.ResultSet;\n");
		importMapper.append("import java.sql.SQLException;\n");
		importMapper.append("import java.util.ArrayList;\n");
		importMapper.append("import java.util.List;\n");
		importMapper.append("import java.util.Map;\n");
		importMapper.append("import java.sql.Connection;\n");
		importMapper.append("import java.io.InputStream;\n\n");
		importMapper.append("import java.sql.PreparedStatement;\n");
		importMapper.append("import org.springframework.jdbc.support.rowset.SqlRowSet;\n");
		importMapper.append("import org.springframework.dao.DataAccessException;\n");
		importMapper.append("import org.springframework.jdbc.core.PreparedStatementCreator;\n");
		importMapper.append("import org.springframework.jdbc.core.BatchPreparedStatementSetter;\n");
		importMapper.append("import org.springframework.jdbc.core.RowMapper;\n");
		importMapper.append("import org.springframework.jdbc.support.GeneratedKeyHolder;\n");
		importMapper.append("import org.springframework.jdbc.support.KeyHolder;\n");
		importMapper.append("import com.huayi.doupo.base.dal.base.DALFather;\n");
		importMapper.append("import com.huayi.doupo.base.model.player.PlayerMemObj;\n");
		importMapper.append("import com.huayi.doupo.base.model."+classUpper(tableName)+";\n\n");
		
		//准备工作,将列名和列类型存入List方便以后使用
		List<String> nameAndTypeList = new ArrayList<String>();
		List<String> nameAndTypeNoIdList = new ArrayList<String>();
		String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT " + " FROM INFORMATION_SCHEMA.COLUMNS " + " WHERE table_name = '" + tableName + "' " + " AND table_schema = '" + dBName + "' ";
		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);		
		while (rs.next()) {
			String colName = rs.getString("COLUMN_NAME");
			String colType = getType(rs.getString("DATA_TYPE"));
			if (!colName.equals("id")) {
				nameAndTypeNoIdList.add(colName + "_" + colType);
			}
			nameAndTypeList.add(colName + "_" + colType);
		}
		String allCols = allCols(nameAndTypeNoIdList);
		
		
		//生成ItemMapper
		StringBuilder itemMapper = new StringBuilder();
		itemMapper.append("\t@SuppressWarnings(\"rawtypes\")\n");
		itemMapper.append("\tpublic class ItemMapper implements RowMapper {\n");
		itemMapper.append("\t\tpublic Object mapRow(ResultSet rs, int rowNum) throws SQLException {\n");
		itemMapper.append("\t\t\t" + classUpper(tableName) + " "+classLower(tableName)+" = new "+classUpper(tableName)+"();\n");
		for(String cols : nameAndTypeList) {
			String col = cols.split("_")[0];
			String colType = cols.split("_")[1];
			itemMapper.append("\t\t\t"+classLower(tableName)+".set"+colUpper(col)+"(rs.get"+colTypeUpper(colType)+"(\""+col+"\"), 0);\n");
		}
		itemMapper.append("\t\t\treturn "+classLower(tableName)+";\n");
		itemMapper.append("\t\t}\n");
		itemMapper.append("\t}\n\n");
		
		//生成add方法
		StringBuilder addMapper = new StringBuilder();
		addMapper.append("\tpublic "+classUpper(tableName)+" add(final "+classUpper(tableName)+" model, int instPlayerId) throws Exception {\n");
		addMapper.append("\t\ttry {\n");
		if (allCols.contains("insertTime") || allCols.contains("updateTime")) {
			addMapper.append("\t\t\tfinal String  updateTime = new java.text.SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\").format(System.currentTimeMillis());\n");
		}
		addMapper.append("\t\t\tStringBuilder strSql = new StringBuilder();\n");
		addMapper.append("\t\t\tstrSql.append(\" insert into "+tableName+" (\");\n");
		addMapper.append("\t\t\tstrSql.append(\""+allCols+"\");\n");
		addMapper.append("\t\t\tstrSql.append(\" )\");\n");
		String spaceCharacter  = spaceCharacter(nameAndTypeNoIdList);;
		addMapper.append("\t\t\tstrSql.append(\" values ("+spaceCharacter+") \");\n");
		addMapper.append("\n");
		addMapper.append("\t\t\tfinal String sql = strSql.toString();\n");
		addMapper.append("\t\t\tKeyHolder keyHolder = new GeneratedKeyHolder();\n");
		addMapper.append("\n");
		addMapper.append("\t\t\tthis.getJdbcTemplate().update(new PreparedStatementCreator(){\n");
		addMapper.append("\t\t\t\tpublic PreparedStatement createPreparedStatement(Connection conn) throws SQLException {\n");
		addMapper.append("\t\t\t\t\tPreparedStatement ps = conn.prepareStatement(sql);\n");
		int i = 0;
		for(String cols : nameAndTypeNoIdList) {
			i++;
			String col = cols.split("_")[0];
			String colType = cols.split("_")[1];
			if (col.equals("version")) {
				addMapper.append("\t\t\t\t\tps.set"+colTypeUpper(colType)+"("+i+", 0);\n");
			} else if (col.equals("insertTime")) {
				addMapper.append("\t\t\t\t\tps.set"+colTypeUpper(colType)+"("+i+", updateTime);\n");
			} else if (col.equals("updateTime")) {
				addMapper.append("\t\t\t\t\tps.set"+colTypeUpper(colType)+"("+i+", updateTime);\n");
			} else {
				addMapper.append("\t\t\t\t\tps.set"+colTypeUpper(colType)+"("+i+", model.get"+colUpper(col)+"());\n");
			}
		}
		addMapper.append("\t\t\t\t\treturn ps;\n");
		addMapper.append("\t\t\t\t}\n");
		addMapper.append("\t\t\t},keyHolder);\n");
		addMapper.append("\n");
		addMapper.append("\t\t\tmodel.setId(keyHolder.getKey().intValue());\n");
		addMapper.append("\t\t\tmodel.setVersion(0);\n");
		if (allCols.contains("insertTime")) {
			addMapper.append("\t\t\tmodel.setInsertTime(updateTime, 0);\n");
		}
		if (allCols.contains("updateTime")) {
			addMapper.append("\t\t\tmodel.setUpdateTime(updateTime, 0);\n");
		}
		addMapper.append("\t\t\tPlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);\n");
		addMapper.append("\t\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		addMapper.append("\t\t\t\tplayerMemObj." +classLower(tableName)+ "Map.put(model.getId(), model);\n");
		addMapper.append("\t\t\t}\n");
		addMapper.append("\t\t} catch (Exception e) {\n");
		addMapper.append("\t\t\tthrow e;\n");
		addMapper.append("\t\t}\n");
		addMapper.append("\t\treturn model;\n");
		addMapper.append("\t}\n\n");
		
		//生成batchAdd方法
		StringBuilder batAddMapper = new StringBuilder();
		batAddMapper.append("\tpublic void batchAdd (final List<"+classUpper(tableName)+"> list) {\n");
		if (allCols.contains("insertTime") || allCols.contains("updateTime")) {
			batAddMapper.append("\t\tfinal String updateTime = new java.text.SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\").format(System.currentTimeMillis());\n");
		}
		batAddMapper.append("\t\tStringBuilder sql = new StringBuilder();\n");
		batAddMapper.append("\t\tsql.append(\" insert into "+tableName+" (\");\n");
		batAddMapper.append("\t\tsql.append(\""+allCols+"\");\n");
		batAddMapper.append("\t\tsql.append(\" )\");\n");
		batAddMapper.append("\t\tsql.append(\" values ("+spaceCharacter+") \");\n");
		batAddMapper.append("\t\tBatchPreparedStatementSetter setter = new BatchPreparedStatementSetter (){\n");
		batAddMapper.append("\t\t\tpublic void setValues(PreparedStatement ps, int i) throws SQLException{\n");
		batAddMapper.append("\t\t\t\t"+classUpper(tableName)+" model = ("+classUpper(tableName)+")list.get(i);\n");
		i = 0;
		for(String cols : nameAndTypeNoIdList) {
			i++;
			String col = cols.split("_")[0];
			String colType = cols.split("_")[1];
			if (col.equals("version")) {
				batAddMapper.append("\t\t\t\t\tps.set"+colTypeUpper(colType)+"("+i+", 0);\n");
			} else if (col.equals("insertTime")) {
				batAddMapper.append("\t\t\t\t\tps.set"+colTypeUpper(colType)+"("+i+", updateTime);\n");
			} else if (col.equals("updateTime")) {
				batAddMapper.append("\t\t\t\t\tps.set"+colTypeUpper(colType)+"("+i+", updateTime);\n");
			} else {
				batAddMapper.append("\t\t\t\t\tps.set"+colTypeUpper(colType)+"("+i+", model.get"+colUpper(col)+"());\n");
			}
		}
		batAddMapper.append("\t\t\t}\n");
		batAddMapper.append("\t\t\tpublic int getBatchSize(){\n");
		batAddMapper.append("\t\t\t\treturn list.size();\n");
		batAddMapper.append("\t\t\t}\n");
		batAddMapper.append("\t\t};\n");
		batAddMapper.append("\t\tgetJdbcTemplate().batchUpdate(sql.toString(), setter);\n");
		batAddMapper.append("\t}\n\n");
		
		//生成deleteById方法
		StringBuilder deleteByIdMapper = new StringBuilder();
		deleteByIdMapper.append("\tpublic int deleteById(int id, int instPlayerId) throws Exception {\n");
		deleteByIdMapper.append("\t\ttry {\n");
		deleteByIdMapper.append("\t\t\tPlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);\n");
		deleteByIdMapper.append("\t\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		deleteByIdMapper.append("\t\t\t\tplayerMemObj."+classLower(tableName)+"Map.remove(id);\n");
		deleteByIdMapper.append("\t\t\t}\n");
		deleteByIdMapper.append("\t\t\tString sql = \"delete from "+tableName+"  where id = ?\";\n");
		deleteByIdMapper.append("\t\t\tObject[] params = new Object[]{id};\n");
		deleteByIdMapper.append("\t\t\treturn this.getJdbcTemplate().update(sql, params);\n");
		deleteByIdMapper.append("\t\t} catch (Exception e) {\n");
		deleteByIdMapper.append("\t\t\tthrow e;\n");
		deleteByIdMapper.append("\t\t}\n");
		deleteByIdMapper.append("\t}\n\n");
		
		//生成deleteById方法
		StringBuilder deleteByWhereMapper = new StringBuilder();
		deleteByWhereMapper.append("\tpublic int deleteByWhere(String strWhere) throws Exception {\n");
		deleteByWhereMapper.append("\t\ttry {\n");
		deleteByWhereMapper.append("\t\t\tStringBuilder sql = new StringBuilder();\n");
		deleteByWhereMapper.append("\t\t\tsql.append(\"delete from "+tableName+" where 1=1 \");\n");
		deleteByWhereMapper.append("\t\t\tif (strWhere != null && !strWhere.equals(\"\")) {\n");
		deleteByWhereMapper.append("\t\t\t\tsql.append(\" and \" + strWhere);\n");
		deleteByWhereMapper.append("\t\t\t}\n");
		deleteByWhereMapper.append("\t\t\treturn this.getJdbcTemplate().update(sql.toString());\n");
		deleteByWhereMapper.append("\t\t} catch (Exception e) {\n");
		deleteByWhereMapper.append("\t\t\tthrow e;\n");
		deleteByWhereMapper.append("\t\t}\n");
		deleteByWhereMapper.append("\t}\n\n");
		
		//生成updateBySql方法
		StringBuilder updateSqlMapper = new StringBuilder();
		updateSqlMapper.append("\tpublic int update(String sql) throws Exception {\n");
		updateSqlMapper.append("\t\ttry {\n");
		updateSqlMapper.append("\t\t\treturn this.getJdbcTemplate().update(sql.toString());\n");
		updateSqlMapper.append("\t\t} catch (Exception e) {\n");
		updateSqlMapper.append("\t\t\tthrow e;\n");
		updateSqlMapper.append("\t\t}\n");
		updateSqlMapper.append("\t}\n\n");
		
		//生成updateByObj方法
		StringBuilder updateModelMapper = new StringBuilder();
		updateModelMapper.append("\tpublic "+classUpper(tableName)+" update("+classUpper(tableName)+" model, int instPlayerId) throws Exception {\n");
		updateModelMapper.append("\t\ttry {\n");
		updateModelMapper.append("\t\t\tObject[] params = null;\n");
		updateModelMapper.append("\t\t\tint version = model.getVersion() + 1;\n");
		if (allCols.contains("insertTime") || allCols.contains("updateTime")) {
			updateModelMapper.append("\t\t\tfinal String  updateTime = new java.text.SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\").format(System.currentTimeMillis());\n");
		}
		updateModelMapper.append("\t\t\tStringBuffer sql = new StringBuffer(\"update "+tableName+" set \");\n");
		String allColsAndChar = allColsAndChar(nameAndTypeNoIdList);
		updateModelMapper.append("\t\t\tsql.append(\""+allColsAndChar+" \");\n");
		updateModelMapper.append("\t\t\tsql.append(\"where id=? and version=?\");\n");
		updateModelMapper.append("\t\t\tparams = new Object[] { "+allColsAndObj(nameAndTypeNoIdList)+" "+idAndVersion()+" };\n");
		updateModelMapper.append("\t\t\tint count = this.getJdbcTemplate().update(sql.toString(), params);\n");
		updateModelMapper.append("\t\t\tif (count == 0) {\n");
		updateModelMapper.append("\t\t\t\tthrow new Exception(\"Concurrent Exception\");\n");
		updateModelMapper.append("\t\t\t} else {\n");
		updateModelMapper.append("\t\t\t\tmodel.setVersion(version, 0);\n");
		if (allCols.contains("updateTime")) {
			updateModelMapper.append("\t\t\t\tmodel.setUpdateTime(updateTime, 0);\n");
		}
		updateModelMapper.append("\t\t\t\tPlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);\n");
		updateModelMapper.append("\t\t\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		updateModelMapper.append("\t\t\t\t\tplayerMemObj."+classLower(tableName)+"Map.put(model.getId(), model);\n");
		updateModelMapper.append("\t\t\t\t}\n");
		updateModelMapper.append("\t\t\t}\n");
		updateModelMapper.append("\t\t} catch (Exception e) {\n");
		updateModelMapper.append("\t\t\tthrow e;\n");
		updateModelMapper.append("\t\t}\n");
		updateModelMapper.append("\t\treturn model;\n");
		updateModelMapper.append("\t}\n\n");
		
		//生成getModel方法
		StringBuilder getModelMapper = new StringBuilder();
		getModelMapper.append("\t@SuppressWarnings(\"unchecked\")\n");
		getModelMapper.append("\tpublic "+classUpper(tableName)+" getModel(int id, int instPlayerId) {\n");
		getModelMapper.append("\t\ttry {\n");
		getModelMapper.append("\t\t\tPlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);\n");
		getModelMapper.append("\t\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		getModelMapper.append("\t\t\t\t"+classUpper(tableName)+" model = playerMemObj."+classLower(tableName)+"Map.get(id);\n");
		getModelMapper.append("\t\t\t\tif (model == null) {\n");
		getModelMapper.append("\t\t\t\t\tString sql = \"select * from "+tableName+" where id=?\";\n");
		getModelMapper.append("\t\t\t\t\tObject[] params = new Object[]{id};\n");
		getModelMapper.append("\t\t\t\t\tplayerMemObj."+classLower(tableName)+"Map.put(id, ("+classUpper(tableName)+") this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));\n");
		getModelMapper.append("\t\t\t\t} else {\n");
		getModelMapper.append("\t\t\t\t\tint cacheVersion = model.getVersion();\n");
		getModelMapper.append("\t\t\t\t\tList<Map<String, Object>> list = sqlHelper(\"select version from "+tableName+" where id = \" + id);\n");
		getModelMapper.append("\t\t\t\t\t int dbVersion = (int)list.get(0).get(\"version\");\n");
		getModelMapper.append("\t\t\t\t\tif (cacheVersion != dbVersion) {\n");
		getModelMapper.append("\t\t\t\t\t\tString sql = \"select * from "+tableName+" where id=?\";\n");
		getModelMapper.append("\t\t\t\t\t\tObject[] params = new Object[]{id};\n");
		getModelMapper.append("\t\t\t\t\t\tplayerMemObj."+classLower(tableName)+"Map.put(id, ("+classUpper(tableName)+") this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper()));\n");
		getModelMapper.append("\t\t\t\t\t}\n");
		getModelMapper.append("\t\t\t\t}\n");
		getModelMapper.append("\t\t\t\tmodel = playerMemObj."+classLower(tableName)+"Map.get(id);\n");
		getModelMapper.append("\t\t\t\tmodel.result = \"\";\n");
		getModelMapper.append("\t\t\t\treturn model;\n");
		getModelMapper.append("\t\t\t} else {\n");
		getModelMapper.append("\t\t\t\tString sql = \"select * from "+tableName+" where id=?\";\n");
		getModelMapper.append("\t\t\t\tObject[] params = new Object[]{id};\n");
		getModelMapper.append("\t\t\t\t"+classUpper(tableName)+" model = ( "+classUpper(tableName)+") this.getJdbcTemplate().queryForObject(sql, params, new ItemMapper());\n");
		getModelMapper.append("\t\t\t\tmodel.result = \"\";\n");
		getModelMapper.append("\t\t\t\treturn model;\n");
		getModelMapper.append("\t\t\t}\n");
		getModelMapper.append("\t\t} catch (DataAccessException e) {\n");
		getModelMapper.append("\t\t\treturn null;\n");
		getModelMapper.append("\t\t}\n");
		getModelMapper.append("\t}\n\n");
		
		//生成getList方法
		StringBuilder getListMapper = new StringBuilder();
		getListMapper.append("\t@SuppressWarnings(\"unchecked\")\n");
		getListMapper.append("\tpublic List<"+classUpper(tableName)+"> getList(String strWhere, int instPlayerId) {\n");
		getListMapper.append("\t\tStringBuffer sql = null;\n");
		getListMapper.append("\t\tPlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);\n");
		getListMapper.append("\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		getListMapper.append("\t\t\tsql = new StringBuffer(\"select id, version from "+tableName+" \");\n");
		getListMapper.append("\t\t}else {\n");
		getListMapper.append("\t\t\tsql = new StringBuffer(\"select * from "+tableName+" \");\n");
		getListMapper.append("\t\t}\n");
		getListMapper.append("\t\tif (strWhere != null && !strWhere.equals(\"\")) {\n");
		getListMapper.append("\t\t\tsql.append(\" where \" + strWhere);\n");
		getListMapper.append("\t\t}\n");
		getListMapper.append("\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		getListMapper.append("\t\t\treturn listCacheCommonHandler(sql.toString(), instPlayerId);\n");
		getListMapper.append("\t\t} else {\n");
		getListMapper.append("\t\t\tList<"+classUpper(tableName)+"> "+classLower(tableName)+"List = (List<"+classUpper(tableName)+">) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());\n");
		getListMapper.append("\t\t\treturn "+classLower(tableName)+"List;\n");
		getListMapper.append("\t\t}\n");
		getListMapper.append("\t}\n\n");
		
		//生成getListIdByWhere方法
		StringBuilder getListIdByWhereMapper = new StringBuilder();
		getListIdByWhereMapper.append("\tpublic List<Long> getListIdByWhere(String strWhere) throws Exception {\n");
		getListIdByWhereMapper.append("\t\ttry {\n");
		getListIdByWhereMapper.append("\t\t\tList<Long> listLong = new ArrayList<Long>();\n");
		getListIdByWhereMapper.append("\t\t\tStringBuffer sql = new StringBuffer(\"select id from "+tableName+" \");\n");
		getListIdByWhereMapper.append("\t\t\tif (strWhere != null && !strWhere.equals(\"\")) {\n");
		getListIdByWhereMapper.append("\t\t\t\tsql.append(\" where \" + strWhere);\n");
		getListIdByWhereMapper.append("\t\t\t}\n");
		getListIdByWhereMapper.append("\t\t\tSqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());\n");
		getListIdByWhereMapper.append("\t\t\twhile (rsSet.next()) {\n");
		getListIdByWhereMapper.append("\t\t\t\tlistLong.add(rsSet.getLong(\"id\"));\n");
		getListIdByWhereMapper.append("\t\t\t}\n");
		getListIdByWhereMapper.append("\t\t\treturn listLong;\n");
		getListIdByWhereMapper.append("\t\t} catch (Exception e) {\n");
		getListIdByWhereMapper.append("\t\t\tthrow e;\n");
		getListIdByWhereMapper.append("\t\t}\n");
		getListIdByWhereMapper.append("\t}\n\n");
		
		//生成getListPagination方法
		StringBuilder getListPaginationMapper = new StringBuilder();
		getListPaginationMapper.append("\t@SuppressWarnings(\"unchecked\")\n");
		getListPaginationMapper.append("\tpublic List<"+classUpper(tableName)+"> getListPagination(int index, int size, String strWhere, int instPlayerId) throws Exception {\n");
		getListPaginationMapper.append("\t\ttry {\n");
		getListPaginationMapper.append("\t\t\tStringBuffer sql = null;\n");
		getListPaginationMapper.append("\t\t\tPlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);\n");
		getListPaginationMapper.append("\t\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		getListPaginationMapper.append("\t\t\t\tsql = new StringBuffer(\"select id, version from "+tableName+" \");\n");
		getListPaginationMapper.append("\t\t\t}else {\n");
		getListPaginationMapper.append("\t\t\t\tsql = new StringBuffer(\"select * from "+tableName+" \");\n");
		getListPaginationMapper.append("\t\t\t}\n");
		getListPaginationMapper.append("\t\t\tif(index <= 0 || size <= 0){\n");
		getListPaginationMapper.append("\t\t\t\tthrow new Exception(\"index or size must bigger than zero\");\n");
		getListPaginationMapper.append("\t\t\t}else{\n");
		getListPaginationMapper.append("\t\t\t\tindex = (index - 1) * size;\n");
		getListPaginationMapper.append("\t\t\t}\n");
		getListPaginationMapper.append("\t\t\tif (strWhere != null && !strWhere.equals(\"\")) {\n");
		getListPaginationMapper.append("\t\t\t\tsql.append(\" where \" + strWhere);\n");
		getListPaginationMapper.append("\t\t\t}\n");
		getListPaginationMapper.append("\t\t\tsql.append(\" limit \" + index + \",\" + size + \"\");\n");
		getListPaginationMapper.append("\t\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		getListPaginationMapper.append("\t\t\t\treturn listCacheCommonHandler(sql.toString(), instPlayerId);\n");
		getListPaginationMapper.append("\t\t\t} else {\n");
		getListPaginationMapper.append("\t\t\t\treturn (List<"+classUpper(tableName)+">) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());\n");
		getListPaginationMapper.append("\t\t\t}\n");
		getListPaginationMapper.append("\t\t} catch (Exception e) {\n");
		getListPaginationMapper.append("\t\t\tthrow e;\n");
		getListPaginationMapper.append("\t\t}\n");
		getListPaginationMapper.append("\t}\n\n");
		
		//生成isExist方法
		StringBuilder isExistMapper = new StringBuilder();
		isExistMapper.append("\tpublic boolean isExist(long id, String strWhere) throws Exception {\n");
		isExistMapper.append("\t\ttry {\n");
		isExistMapper.append("\t\t\tStringBuffer sql = new StringBuffer(\"select count(*) from "+tableName+" where id =?\");\n");
		isExistMapper.append("\t\t\tif (strWhere != null && !strWhere.equals(\"\")) {\n");
		isExistMapper.append("\t\t\t\tsql.append(\" or \" + strWhere);\n");
		isExistMapper.append("\t\t\t}\n");
		isExistMapper.append("\t\t\tint count = this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);\n");
		isExistMapper.append("\t\t\treturn count > 0 ? true : false;\n");
		isExistMapper.append("\t\t} catch (Exception e) {\n");
		isExistMapper.append("\t\t\tthrow e;\n");
		isExistMapper.append("\t\t}\n");
		isExistMapper.append("\t}\n\n");
		
		//生成getCount方法
		StringBuilder getCountMapper = new StringBuilder();
		getCountMapper.append("\tpublic int getCount(String strWhere) throws Exception {\n");
		getCountMapper.append("\t\ttry {\n");
		getCountMapper.append("\t\t\tStringBuffer sql = new StringBuffer(\"select count(*) from "+tableName+"\");\n");
		getCountMapper.append("\t\t\tif (strWhere != null && !strWhere.equals(\"\")) {\n");
		getCountMapper.append("\t\t\t\tsql.append(\" where \" + strWhere);\n");
		getCountMapper.append("\t\t\t}\n");
		getCountMapper.append("\t\t\treturn this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);\n");
		getCountMapper.append("\t\t} catch (Exception e) {\n");
		getCountMapper.append("\t\t\tthrow e;\n");
		getCountMapper.append("\t\t}\n");
		getCountMapper.append("\t}\n\n");
		
		//生成getCounts方法
		StringBuilder getCountsMapper = new StringBuilder();
		getCountsMapper.append("\tpublic List<Long> getCounts(String strWhere) throws Exception {\n");
		getCountsMapper.append("\t\ttry {\n");
		getCountsMapper.append("\t\t\tList<Long> listLong = new ArrayList<Long>();\n");
		getCountsMapper.append("\t\t\tStringBuffer sql = new StringBuffer(\"select count(*) as cnt from "+tableName+" \");\n");
		getCountsMapper.append("\t\t\tif (strWhere != null && !strWhere.equals(\"\")) {\n");
		getCountsMapper.append("\t\t\t\tsql.append(strWhere);\n");
		getCountsMapper.append("\t\t\t}\n");
		getCountsMapper.append("\t\t\tSqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());\n");
		getCountsMapper.append("\t\t\twhile (rsSet.next()) {\n");
		getCountsMapper.append("\t\t\t\tlistLong.add(rsSet.getLong(\"cnt\"));\n");
		getCountsMapper.append("\t\t\t}\n");
		getCountsMapper.append("\t\t\treturn listLong;\n");
		getCountsMapper.append("\t\t} catch (Exception e) {\n");
		getCountsMapper.append("\t\t\tthrow e;\n");
		getCountsMapper.append("\t\t}\n");
		getCountsMapper.append("\t}\n\n");
		
		//生成sqlHelper方法
		StringBuilder sqlHelperMapper = new StringBuilder();
		sqlHelperMapper.append("\tpublic List<Map<String,Object>> sqlHelper(String sql) {\n");
		sqlHelperMapper.append("\t\treturn this.getJdbcTemplate().queryForList(sql);\n");
		sqlHelperMapper.append("\t}\n\n");
		
		//生成getListFromMoreTale方法
		StringBuilder getListFromMoreTaleMapper = new StringBuilder();
		getListFromMoreTaleMapper.append("\t@SuppressWarnings(\"unchecked\")\n");
		getListFromMoreTaleMapper.append("\tpublic List<"+classUpper(tableName)+"> getListFromMoreTale(String afterSql, int instPlayerId) {\n");
		getListFromMoreTaleMapper.append("\t\tStringBuffer sql = null;\n");
		getListFromMoreTaleMapper.append("\t\tPlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);\n");
		getListFromMoreTaleMapper.append("\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		getListFromMoreTaleMapper.append("\t\t\tsql = new StringBuffer(\"select a.id, a.version from "+tableName+" a \");\n");
		getListFromMoreTaleMapper.append("\t\t}else {\n");
		getListFromMoreTaleMapper.append("\t\t\tsql = new StringBuffer(\"select a.* from "+tableName+" a \");\n");
		getListFromMoreTaleMapper.append("\t\t}\n");
		getListFromMoreTaleMapper.append("\t\tif (afterSql != null && !afterSql.equals(\"\")) {\n");
		getListFromMoreTaleMapper.append("\t\t\tsql.append(afterSql);\n");
		getListFromMoreTaleMapper.append("\t\t}\n");
		getListFromMoreTaleMapper.append("\t\tif (instPlayerId != 0 && isUseCach() && playerMemObj != null) {\n");
		getListFromMoreTaleMapper.append("\t\t\treturn listCacheCommonHandler(sql.toString(), instPlayerId);\n");
		getListFromMoreTaleMapper.append("\t\t} else {\n");
		getListFromMoreTaleMapper.append("\t\t\treturn (List<"+classUpper(tableName)+">) this.getJdbcTemplate().query(sql.toString(), new ItemMapper());\n");
		getListFromMoreTaleMapper.append("\t\t}\n");
		getListFromMoreTaleMapper.append("\t}\n\n");
		
		//生成getListIdFromMoreTable方法
		StringBuilder getListIdFromMoreTableMapper = new StringBuilder();
		getListIdFromMoreTableMapper.append("\tpublic List<Long> getListIdFromMoreTable(String afterSql) throws Exception {\n");
		getListIdFromMoreTableMapper.append("\t\ttry {\n");
		getListIdFromMoreTableMapper.append("\t\t\tList<Long> listLong = new ArrayList<Long>();\n");
		getListIdFromMoreTableMapper.append("\t\t\tStringBuffer sql = new StringBuffer(\"select a.id from "+tableName+" a \");\n");
		getListIdFromMoreTableMapper.append("\t\t\tif (afterSql != null && !afterSql.equals(\"\")) {\n");
		getListIdFromMoreTableMapper.append("\t\t\t\tsql.append(afterSql);\n");
		getListIdFromMoreTableMapper.append("\t\t\t}\n");
		getListIdFromMoreTableMapper.append("\t\t\tSqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());\n");
		getListIdFromMoreTableMapper.append("\t\t\twhile (rsSet.next()) {\n");
		getListIdFromMoreTableMapper.append("\t\t\t\tlistLong.add(rsSet.getLong(\"id\"));\n");
		getListIdFromMoreTableMapper.append("\t\t\t}\n");
		getListIdFromMoreTableMapper.append("\t\t\treturn listLong;\n");
		getListIdFromMoreTableMapper.append("\t\t} catch (Exception e) {\n");
		getListIdFromMoreTableMapper.append("\t\t\tthrow e;\n");
		getListIdFromMoreTableMapper.append("\t\t}\n");
		getListIdFromMoreTableMapper.append("\t}\n\n");
		
		//生成getStatResult方法
		StringBuilder getStatResultMapper = new StringBuilder();
		getStatResultMapper.append("\tpublic int getStatResult(String statType, String conParam, String strWhere) throws Exception {\n");
		getStatResultMapper.append("\t\tint result = 0;\n");
		getStatResultMapper.append("\t\ttry {\n");
		getStatResultMapper.append("\t\t\tStringBuffer sql = new StringBuffer(\"select \"+statType+\"(\"+conParam+\") from  "+tableName+"\");\n");
		getStatResultMapper.append("\t\t\tif (strWhere != null && !strWhere.equals(\"\")) {\n");
		getStatResultMapper.append("\t\t\t\tsql.append(\" where \" + strWhere);\n");
		getStatResultMapper.append("\t\t\t}\n");
		getStatResultMapper.append("\t\t\treturn this.getJdbcTemplate().queryForObject(sql.toString(), Integer.class);\n");
		getStatResultMapper.append("\t\t} catch (Exception e) {\n");
		getStatResultMapper.append("\t\t\treturn result;\n");
		getStatResultMapper.append("\t\t}\n");
		getStatResultMapper.append("\t}\n\n");
		
		//生成listCacheCommonHandler方法
		StringBuilder listCacheCommonHandlerMapper = new StringBuilder();
		listCacheCommonHandlerMapper.append("\tprivate List<"+classUpper(tableName)+"> listCacheCommonHandler(String sql, int instPlayerId){\n");
		listCacheCommonHandlerMapper.append("\t\tList<"+classUpper(tableName)+"> modelList = new ArrayList<"+classUpper(tableName)+">();\n");
		listCacheCommonHandlerMapper.append("\t\tPlayerMemObj playerMemObj = getPlayerMemObjByPlayerId(instPlayerId);\n");
		listCacheCommonHandlerMapper.append("\t\tSqlRowSet rsSet = this.getJdbcTemplate().queryForRowSet(sql.toString());\n");
		listCacheCommonHandlerMapper.append("\t\twhile (rsSet.next()) {\n");
		listCacheCommonHandlerMapper.append("\t\t\tint id = rsSet.getInt(\"id\");\n");
		listCacheCommonHandlerMapper.append("\t\t\tint dbVersion = rsSet.getInt(\"version\");\n");
		listCacheCommonHandlerMapper.append("\t\t\t"+classUpper(tableName)+" model = playerMemObj."+classLower(tableName)+"Map.get(id);\n");
		listCacheCommonHandlerMapper.append("\t\t\tif (model == null) {\n");
		listCacheCommonHandlerMapper.append("\t\t\t\tmodel = getModel(id, instPlayerId);\n");
		listCacheCommonHandlerMapper.append("\t\t\t\tmodel.result = \"\";\n");
		listCacheCommonHandlerMapper.append("\t\t\t\tmodelList.add(model);\n");
		listCacheCommonHandlerMapper.append("\t\t\t} else {\n");
		listCacheCommonHandlerMapper.append("\t\t\t\tint cacheVersion = model.getVersion();\n");
		listCacheCommonHandlerMapper.append("\t\t\t\tif (cacheVersion != dbVersion) {\n");
		listCacheCommonHandlerMapper.append("\t\t\t\t\tmodel = getModel(id, instPlayerId);\n");
		listCacheCommonHandlerMapper.append("\t\t\t\t}\n");
		listCacheCommonHandlerMapper.append("\t\t\t\tmodel.result = \"\";\n");
		listCacheCommonHandlerMapper.append("\t\t\t\tmodelList.add(model);\n");
		listCacheCommonHandlerMapper.append("\t\t\t}\n");
		listCacheCommonHandlerMapper.append("\t\t}\n");
		listCacheCommonHandlerMapper.append("\t\treturn modelList;\n");
		listCacheCommonHandlerMapper.append("\t}\n\n");
		
		//组装所有方法
		StringBuilder allMapper = new StringBuilder();
		allMapper.append(importMapper);
		allMapper.append("public class "+classUpper(tableName)+"DAL extends DALFather {\n");
		allMapper.append(itemMapper);
		allMapper.append(addMapper);
		allMapper.append(batAddMapper);
		allMapper.append(deleteByIdMapper);
		allMapper.append(deleteByWhereMapper);
		allMapper.append(updateSqlMapper);
		allMapper.append(updateModelMapper);
		allMapper.append(getModelMapper);
		allMapper.append(getListMapper);
		allMapper.append(getListIdByWhereMapper);
		allMapper.append(getListPaginationMapper);
		allMapper.append(isExistMapper);
		allMapper.append(getCountMapper);
		allMapper.append(getCountsMapper);
		allMapper.append(sqlHelperMapper);
		allMapper.append(getListFromMoreTaleMapper);
		allMapper.append(getListIdFromMoreTableMapper);
		allMapper.append(getStatResultMapper);
		allMapper.append(listCacheCommonHandlerMapper);
		allMapper.append("}");
		
		//生成文件
		geneFile(allMapper.toString(), outPath + "/dao/", classUpper(tableName) + "DAL.java");
		
		/**
		   	listCacheCommonHandlerMapper.append("\t\n");
		   	listCacheCommonHandlerMapper.append("\t\t\n");
		   	listCacheCommonHandlerMapper.append("\t\t\t\n");
		   	listCacheCommonHandlerMapper.append("\t\t\t\t\n");
		   	listCacheCommonHandlerMapper.append("\t\t\t\t\t\n");
		   	listCacheCommonHandlerMapper.append("\t\t\t\t\t\t\n");
		   	
			System.out.println(allMapper.toString());
		 */
	}
	
	/**
	 * 生成文件
	 * @param content
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	public static void geneFile(String content, String filePath,String fileName) throws Exception{
//		BufferedWriter bw = null;
		try {
			createDir(filePath);
			FileUtil.writeContentToFile(filePath, fileName, "utf-8", content, StandardOpenOption.CREATE_NEW);
			/*bw = new BufferedWriter(new FileWriter(createFile(filePath+fileName)));
			bw.write(content);
			bw.flush();*/
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
//			bw.close();
		}
	}

	/**
	 * 创建文件夹
	 * @param path
	 */
	private static void createDir(String path) {
		File dict = new File(path);
		if (!dict.exists()) {
			dict.mkdirs();
		}
	}

	/**
	 * JAVA匹配类型
	 * @param type
	 * @return
	 */
	private static String getType(String type) {
		String dataType = "";
		if (type.equals("int")) {
			dataType = "int";
		} else if (type.equals("bigint")) {
			dataType = "long";
		} else if (type.equals("varchar")) {
			dataType = "String";
		} else if (type.equals("float")) {
			dataType = "float";
		} else if (type.contains("mediumblob")) {
			dataType = "InputStream";
		} else if (type.equals("double")) {
			dataType = "double";
		} else if (type.equals("smallint")) {
			dataType = "int";
		} else if (type.equals("decimal")) {
			dataType = "float";
		} else if (type.equals("numeric")) {
			dataType = "numeric";
		} else if (type.equals("timestamp")) {
			dataType = "Timestamp";
		} else if (type.equals("datetime")) {
			dataType = "Date";
		} else if (type.equals("longblob")) {
			dataType = "Object";
		} else if (type.equals("text")) {
			dataType = "String";
		}
		return dataType;
	}
	
   /**
    * 删除文件夹下所有内容，包括此文件夹
    * @param f
    * @throws IOException
    */
	private static void delAll(File f) throws IOException {
	    if(!f.exists())
	    	return;
	    	boolean rslt=true;
		    if(!(rslt=f.delete())){
		      File subs[] = f.listFiles();
		      for (int i = 0; i <= subs.length - 1; i++) {
		        if (subs[i].isDirectory())
		          delAll(subs[i]);
		        rslt = subs[i].delete();
		      }
		      rslt = f.delete();
		    if(!rslt)
		      throw new IOException("无法删除:"+f.getName());
	    }
	    return;
	  }

	/**
	 * 生成方法
	 * @author mp
	 * @date 2015-6-17 上午10:48:31
	 * @Description
	 */
	public static void generate(){
		
		try {
			
			//删除原始文件
			delAll(new File(outPath));
			
			//获取所有表
			Hashtable<String, String> tableMap = getTableInfo();
			Iterator<String> tableNames = tableMap.keySet().iterator();
			
			//生成所有Dao.java
			while (tableNames.hasNext()) {
				String table = tableNames.next();
				genDaoJava(table, tableMap.get(table));
			}
			
			//生成AppDAL.xml
			genDaoXml(tableMap.keySet().iterator());
			
			//生成DALFactory.java
			genDalFactory(tableMap.keySet().iterator());
			
			//生成SpringDalContext.java
			genSpringDalContext(tableMap.keySet().iterator());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		generate();
		System.out.println("全部生成完成!");
	}
	
}