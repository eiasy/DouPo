package com.huayi.doupo.logic.util.gener;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.base.util.base.StringUtil;

public class GenerBeanTemPlate {

	private static int whichDB = 0;

	private static String outPath = "E:/doupoout/0BeanOut";
	private static String containTables = "Dict_SpecialEffectType";
	private static Connection conn;

	private static Map<Integer, String> dbInfoMap = new HashMap<Integer, String>();
	static {
		dbInfoMap.put(0, "127.0.0.1_doupogener_root_123456");
		dbInfoMap.put(1, "192.168.1.43_dragonball_root_mysql");
		dbInfoMap.put(2, "192.168.1.42_dragonball_root_123456");
		dbInfoMap.put(3, "192.168.4.202_dragonball1_dragonball_dragonballdbpass");
		dbInfoMap.put(4, "192.168.4.202_dragonball2_dragonball_dragonballdbpass");
		dbInfoMap.put(5, "192.168.1.42_doupo_root_123456");
		dbInfoMap.put(6, "192.168.1.42_doupoNC_root_123456");
		dbInfoMap.put(7, "192.168.1.30_gener_root_666666");
	}
	private static String dBName = dbInfoMap.get(whichDB).split("_")[1];

	/**
	 * 连接数据库
	 * 
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
	 * 获取类名或首字母的列名，用于get和set方法
	 * 
	 * @param name
	 * @return
	 */
	private static String getClassColumnName(String name) {

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
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	/**
	 * 获取表名和表注释
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Hashtable<String, String> getTableInfo() throws Exception {
		Hashtable<String, String> tableMap = new Hashtable<String, String>();
		String sql = "SELECT table_name ,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES  WHERE table_schema = '" + dBName + "'";
		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			tableMap.put(rs.getString("table_name"), rs.getString("TABLE_COMMENT"));
		}
		return tableMap;
	}

	/**
	 * 根据某个表，获取表下字段及其字段注释 for Java
	 * 
	 * @param tableName
	 * @param tableCommon
	 * @throws Exception
	 */
	private static void getJavaBean(String tableName, String tableCommon) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT " + " FROM INFORMATION_SCHEMA.COLUMNS " + " WHERE table_name = '" + tableName + "' " + " AND table_schema = '" + dBName + "' ";

		sBuffer.append("package com.huayi.doupo.base.model;\n\n");
		sBuffer.append("import java.io.*;\n\n");
		sBuffer.append("/**\n");
		sBuffer.append("\t" + tableCommon + "\n");
		sBuffer.append("*/\n");
		sBuffer.append("@SuppressWarnings(\"serial\")\n");
		sBuffer.append("public class " + getClassColumnName(tableName) + " implements Serializable\n");
		sBuffer.append("{\n");
		sBuffer.append("\tprivate int index;\n");
		sBuffer.append("\tpublic String result = \"\";\n");
		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		StringBuffer extendBuffer = new StringBuffer();
		extendBuffer.append("\tpublic " + getClassColumnName(tableName) + " clone(){\n");
		extendBuffer.append("\t\t" + getClassColumnName(tableName) + " extend=new " + getClassColumnName(tableName) + "();\n");
		while (rs.next()) {
			String colName = rs.getString("COLUMN_NAME");
			String colType = rs.getString("DATA_TYPE");
			String colComm = rs.getString("COLUMN_COMMENT");
			sBuffer.append("\t/**\n");
			sBuffer.append("\t\t" + colComm + "\n");
			sBuffer.append("\t*/\n");

			sBuffer.append("\tprivate " + getType(colType) + " " + colName + ";\n");

			if (colName.equals("className")) {
				sBuffer.append("\tpublic " + getType(colType) + " get" + getClassColumnName(colName) + "(){\n");
				sBuffer.append("\t\tthis.className = \"" + getClassColumnName(tableName) + "\";\n");
				sBuffer.append("\t\treturn " + colName + ";\n");
				sBuffer.append("\t}\n");
			} else {
				sBuffer.append("\tpublic " + getType(colType) + " get" + getClassColumnName(colName) + "(){\n");
				sBuffer.append("\t\treturn " + colName + ";\n");
				sBuffer.append("\t}\n");
			}

			sBuffer.append("\tpublic void set" + getClassColumnName(colName) + "(" + getType(colType) + " " + colName + ") {\n");
			sBuffer.append("\t\tthis." + colName + " = " + colName + ";\n");
			sBuffer.append("\t\tindex = " + rs.getRow() + ";\n");
			sBuffer.append("\t\tresult += index + \"*" + getType(colType) + "*\" + " + colName + " + \"#\";\n");
			sBuffer.append("\t}\n\n");

			sBuffer.append("\tpublic void set" + getClassColumnName(colName) + "(" + getType(colType) + " " + colName + ", int bs) {\n");
			sBuffer.append("\t\tthis." + colName + " = " + colName + ";\n");
			sBuffer.append("\t}\n\n");
			extendBuffer.append("\t\textend.set" + getClassColumnName(colName) + "(this." + colName + ");\n");
		}
		extendBuffer.append("\t\treturn extend;\n");
		extendBuffer.append("\t}\n");
		sBuffer.append("\tpublic String getResult(){\n\t\treturn result;\n\t}\n\n");
		sBuffer.append(extendBuffer.toString());
		sBuffer.append("}\n");
		getFile(sBuffer.toString(), getClassColumnName(tableName), "java");

	}

	/**
	 * 根据某个表，获取表下字段及其字段注释 for VS
	 * 
	 * @param tableName
	 * @param tableCommon
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static void getVSBean(String tableName, String tableCommon) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT " + " FROM INFORMATION_SCHEMA.COLUMNS " + " WHERE table_name = '" + tableName + "' " + " AND table_schema = '" + dBName + "' ";

		sBuffer.append("namespace yy.web.model\n");
		sBuffer.append("{\n");
		sBuffer.append("\t/// <summary>\n");
		sBuffer.append("\t/// " + tableCommon + "\n");
		sBuffer.append("\t/// </summary>\n");
		sBuffer.append("\tpublic class " + getClassColumnName(tableName));
		sBuffer.append("\t{\n\n");
		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		// StringBuffer extendBuffer=new StringBuffer();
		// extendBuffer.append("\tpublic "+getClassColumnName(tableName)+"Extend cloneToExtend(){\n");
		// extendBuffer.append("\t\t"+getClassColumnName(tableName)+"Extend extend=new "+getClassColumnName(tableName)+"Extend();\n");
		while (rs.next()) {
			String colName = rs.getString("COLUMN_NAME");
			String colType = rs.getString("DATA_TYPE");
			String colComm = rs.getString("COLUMN_COMMENT");
			sBuffer.append("\t\t/// <summary>\n");
			sBuffer.append("\t\t///" + colComm + "\n");
			sBuffer.append("\t\t/// </summary>\n");
			// StringUtil.firstToUpper(colName)
			sBuffer.append("\t\tpublic  " + getVSType(colType) + " " + StringUtil.capitalize(colName) + " { get; set; }\n\n");

			//
			// if (colName.equals("className")) {
			// sBuffer.append("\tpublic " + getType(colType) + " get"
			// + getClassColumnName(colName) + "(){\n");
			// sBuffer.append("\t\tthis.className = \"" + getClassColumnName(tableName)
			// + "\";\n");
			// sBuffer.append("\t\treturn " + colName + ";\n");
			// sBuffer.append("\t}\n");
			// } else {
			// sBuffer.append("\tpublic " + getType(colType) + " get"
			// + getClassColumnName(colName) + "(){\n");
			// sBuffer.append("\t\treturn " + colName + ";\n");
			// sBuffer.append("\t}\n");
			// }

			// sBuffer.append("\tpublic void set" + getClassColumnName(colName) + "("
			// + getType(colType) + " " + colName + ") {\n");
			// sBuffer.append("\t\tthis." + colName + " = " + colName + ";\n");
			// sBuffer.append("\t}\n\n");
			// extendBuffer.append("\t\textend.set"+ getClassColumnName(colName) +"(this."+colName+");\n");
		}
		// extendBuffer.append("\t\treturn extend;\n");
		// extendBuffer.append("\t}\n");
		// sBuffer.append(extendBuffer.toString());
		sBuffer.append("\t}\n");
		sBuffer.append("}\n");
		getFile(sBuffer.toString(), getClassColumnName(tableName), "VS");

	}

	/**
	 * 根据某个表，获取表下字段及其字段注释 for Java_Static
	 * 
	 * @param tableName
	 * @param tableCommon
	 * @throws Exception
	 */
	private static void getStatic_Java(String tableName, String tableCommon) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT " + " FROM INFORMATION_SCHEMA.COLUMNS " + " WHERE table_name = '" + tableName + "' " + " AND table_schema = '" + dBName + "' ";

		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);

		String fileName = "";
		if (tableName.contains("Dict")) {
			fileName = tableName.replace("Dict_", "Static");
		} else if (tableName.contains("Cn")) {
			fileName = tableName.replace("Cn_", "StaticCn");
		} else if (tableName.contains("Sys")) {
			fileName = tableName.replace("Sys_", "Static");
		}
		// 去掉下划线
		if (fileName.contains("_")) {
			fileName = fileName.replace("_", "");
		}

		while (rs.next()) {

			String colName = rs.getString("COLUMN_NAME");

			if (colName.equals("sname")) {
				sBuffer.append("package com.huayi.doupo.base.model.statics;\n\n");
				sBuffer.append("public class " + fileName + "{\n\n");
				String sqlstString = "select id,name,sname from " + tableName + "";
				if (tableName.equals("Sys_MsgRule")) {
					sqlstString = "select id,name,sname, classPath from " + tableName + "";
				} else if (tableName.equals("Dict_SysConfig") || tableName.equals("Dict_CustomDict")) {
					sqlstString = "select id,name,sname, value from " + tableName + "";
				}
				Statement statement2 = getMySQLConnection().createStatement();
				ResultSet rs2 = statement2.executeQuery(sqlstString);

				StringBuffer sb2 = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
				sb2.append("<actions>\n");

				while (rs2.next()) {
					String id = rs2.getString("id");
					String name = rs2.getString("name");
					String sname = rs2.getString("sname");
					int value = 0;
					int intValue = 0;
					if (tableName.equals("Sys_MsgRule")) {
						String classPath = rs2.getString("classPath");
						sb2.append("\t<action id=\"" + id + "\" class=\"" + classPath + "\" method=\"" + sname + "\" description=\"" + name + "\" />\n");
					} else if (tableName.equals("Dict_SysConfig")) {
						value = rs2.getInt("id");
					} else if (tableName.equals("Dict_CustomDict")) {
						intValue = rs2.getInt("value");
					}

					if (name == null || sname == null || name.equals("") || sname.equals("")) {
						throw new Exception("coulmn is not null when creating StaticClass:" + tableName.replace("Dict", "Static"));
					}
					if (tableName.equals("Cn_Server")) {
						sBuffer.append("\t\tpublic static final String " + sname + " = new String(\"" + name + "\");\n\n");
					} else if (tableName.equals("Dict_SysConfig")) {
						sBuffer.append("\t\tpublic static final Integer " + sname + " = new Integer(" + value + ");//" + name + "\n\n");
					} else if (tableName.equals("Dict_CustomDict")) {
						sBuffer.append("\t\tpublic static final Integer " + sname + " = new Integer(" + intValue + ");//" + name + "\n\n");
					} else {
						sBuffer.append("\t\tpublic static final Integer " + sname + " = new Integer(" + id + ");//" + name + "\n\n");
					}
				}
				sb2.append("</actions>");
				if (tableName.equals("Sys_MsgRule")) {
					getFile(sb2.toString(), tableName.replace("Dict", "Static"), "requestGame");
				}

				sBuffer.append("}\n");
				getFile(sBuffer.toString(), fileName, "static");
			}
		}
	}

	/**
	 * 根据某个表，获取表下字段及其字段注释 for As_Static
	 * 
	 * @param tableName
	 * @param tableCommon
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static void getStatic_As(String tableName, String tableCommon) throws Exception {

		StringBuffer sBuffer = new StringBuffer();
		String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT " + " FROM INFORMATION_SCHEMA.COLUMNS " + " WHERE table_name = '" + tableName + "' " + " AND table_schema = '" + dBName + "' ";

		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {

			String colName = rs.getString("COLUMN_NAME");
			if (tableName.equals("Cn")) {
				continue;
			} else {
				if (colName.equals("sname")) {
					sBuffer.append("package code.model.constData{\n\n");
					sBuffer.append("\tpublic final class " + tableName.replace("Dict", "Static") + "{\n\n");
					String sqlstString = "select id,name,sname from " + tableName + "";
					Statement statement2 = getMySQLConnection().createStatement();
					ResultSet rs2 = statement2.executeQuery(sqlstString);
					while (rs2.next()) {
						String id = rs2.getString("id");
						String name = rs2.getString("name");
						String sname = rs2.getString("sname");
						if (name == null || sname == null || name.equals("") || sname.equals("")) {
							throw new Exception("coulmn is not null when creating StaticClass:" + tableName.replace("Dict", "Static"));
						}
						if (tableName.equals("Cn")) {
							sBuffer.append("\t\tpublic static final String " + sname + " = \"" + name + "\";\n\n");
						} else {
							sBuffer.append("\t\t/** " + name + " */\n");
							sBuffer.append("\t\tpublic static const " + sname + ":int = " + id + ";\n\n");
						}
					}
					sBuffer.append("\t}\n");
					sBuffer.append("}\n");
					getFile(sBuffer.toString(), tableName.replace("Dict", "Static"), "staticAs");
				}
			}
		}
	}

	/**
	 * 根据某个表，获取表下字段及其字段注释 for VS_Static
	 * 
	 * @author mp
	 * @version 1.0, 2013-4-4 下午5:52:30
	 * @param tableName
	 * @param tableCommon
	 * @throws Exception
	 */
	private static void getStatic_CADD(String tableName, String tableCommon) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT " + " FROM INFORMATION_SCHEMA.COLUMNS " + " WHERE table_name = '" + tableName + "' " + " AND table_schema = '" + dBName + "' ";

		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {

			String colName = rs.getString("COLUMN_NAME");

			if (colName.equals("sname")) {

				String fileName = "";
				if (tableName.contains("Dict")) {
					fileName = tableName.replace("Dict_", "Static");
				} else if (tableName.contains("Cn")) {
					fileName = tableName.replace("Cn_", "StaticCn");
				} else if (tableName.contains("Sys")) {
					fileName = tableName.replace("Sys_", "Static");
				}

				sBuffer.append(fileName + " = {\n\n");
				String sqlstString = "select id,name,sname,description from " + tableName + "";
				Statement statement2 = getMySQLConnection().createStatement();
				ResultSet rs2 = statement2.executeQuery(sqlstString);
				while (rs2.next()) {
					String id = rs2.getString("id");
					String name = rs2.getString("name");
					String sname = rs2.getString("sname");
					// String description = "";
					// try {
					// description = rs2.getString("description");
					// } catch (Exception e) {
					// }
					if (name == null || sname == null || name.equals("") || sname.equals("")) {
						throw new Exception("coulmn is not null when creating StaticClass:" + tableName.replace("Dict", "Static"));
					}
					if (tableName.equals("Cn")) {
						sBuffer.append("\t\tpublic const String " + StringUtil.capitalize(sname) + " = new String(\"" + name + "\");\n\n");
					} else {
						// if(containTables.contains(tableName)){
						// sBuffer.append("\t\t/// <summary>\n");
						// sBuffer.append("\t\t/// " + description+ " + "+id+"\n");
						// sBuffer.append("\t\t/// </summary>\n");
						// }else{
						// sBuffer.append("\t\t/// <summary>\n");
						// sBuffer.append("\t\t/// " + name+ " + "+id+"\n");
						// sBuffer.append("\t\t/// </summary>\n");
						// }
						sBuffer.append("\t\t" + sname + " = " + id + ",--" + name + "\n\n");
					}
				}
				if (sBuffer.toString().contains(",")) {
					int pos = sBuffer.lastIndexOf(",");
					sBuffer = sBuffer.deleteCharAt(pos);
				}

				sBuffer.append("}\n");

				getFile(sBuffer.toString(), fileName, "staticCADD");
			}
		}
	}

	@SuppressWarnings("unused")
	private static void getStatic_VS(String tableName, String tableCommon) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT " + " FROM INFORMATION_SCHEMA.COLUMNS " + " WHERE table_name = '" + tableName + "' " + " AND table_schema = '" + dBName + "' ";

		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {

			String colName = rs.getString("COLUMN_NAME");

			if (colName.equals("sname")) {

				String fileName = "";
				if (tableName.contains("Dict")) {
					fileName = tableName.replace("Dict_", "Static");
				} else if (tableName.contains("Cn")) {
					fileName = tableName.replace("Cn_", "StaticCn");
				} else if (tableName.contains("Sys")) {
					fileName = tableName.replace("Sys_", "Static");
				}

				sBuffer.append("public class " + fileName + "{\n\n");
				String sqlstString = "select id,name,sname,description from " + tableName + "";
				Statement statement2 = getMySQLConnection().createStatement();
				ResultSet rs2 = statement2.executeQuery(sqlstString);
				while (rs2.next()) {
					String id = rs2.getString("id");
					String name = rs2.getString("name");
					String sname = rs2.getString("sname");
					String description = "";
					try {
						description = rs2.getString("description");
					} catch (Exception e) {
					}
					if (name == null || sname == null || name.equals("") || sname.equals("")) {
						throw new Exception("coulmn is not null when creating StaticClass:" + tableName.replace("Dict", "Static"));
					}
					if (tableName.equals("Cn")) {
						sBuffer.append("\t\tpublic const String " + StringUtil.capitalize(sname) + " = \"" + name + "\";\n\n");
					} else {
						if (containTables.contains(tableName)) {
							sBuffer.append("\t\t/// <summary>\n");
							sBuffer.append("\t\t/// " + description + " + " + id + "\n");
							sBuffer.append("\t\t/// </summary>\n");
						} else {
							sBuffer.append("\t\t/// <summary>\n");
							sBuffer.append("\t\t/// " + name + " + " + id + "\n");
							sBuffer.append("\t\t/// </summary>\n");
						}
						sBuffer.append("\t\tpublic const int " + StringUtil.capitalize(sname) + " = " + id + ";\n\n");
					}
				}
				sBuffer.append("}\n");

				getFile(sBuffer.toString(), fileName, "staticVS");
			}
		}
	}

	/**
	 * 根据某个表，获取表下字段及其字段注释 for AS
	 * 
	 * @param tableName
	 * @param tableCommon
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static void getASBean(String tableName, String tableCommon) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT " + " FROM INFORMATION_SCHEMA.COLUMNS " + " WHERE table_name = '" + tableName + "' " + " AND table_schema = '" + dBName + "' ";

		if (tableName.contains("Dict")) {
			sBuffer.append("package code.model.dbData\n");
		} else if (tableName.contains("Inst")) {
			sBuffer.append("package code.model.instData\n");
		}

		sBuffer.append("{\n\n");
		sBuffer.append("\timport code.model.DataObj;\n\n");
		sBuffer.append("\t/**\n");
		sBuffer.append("\t\t" + tableCommon + "\n");
		sBuffer.append("\t*/\n");

		sBuffer.append("\tpublic class " + getClassColumnName(tableName) + " extends DataObj\n");
		sBuffer.append("\t{\n");

		Statement statement = getMySQLConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			String colName = rs.getString("COLUMN_NAME");
			String colType = rs.getString("DATA_TYPE");
			String colComm = rs.getString("COLUMN_COMMENT");
			if (colName.equals("id") || colName.equals("className")) {
				continue;
			}
			sBuffer.append("\t\t/**\n");
			sBuffer.append("\t\t\t" + colComm + "\n");
			sBuffer.append("\t\t*/\n");
			sBuffer.append("\t\tpublic var " + colName + ":" + getCSType(colType) + " = " + getCSInitValue(colType) + ";\n\n");
		}
		sBuffer.append("\t\tpublic function " + getClassColumnName(tableName) + "(id:String){\n");
		sBuffer.append("\t\t\tsuper(id);\n");
		sBuffer.append("\t\t\tthis.className=\"" + getClassColumnName(tableName) + "\";\n");
		sBuffer.append("\t\t}\n");
		sBuffer.append("\t}\n");
		sBuffer.append("}\n");
		getFile(sBuffer.toString(), getClassColumnName(tableName), "as");

	}

	/**
	 * 将字符串写入指定文件
	 * 
	 * @param content
	 * @param fileName
	 * @param type
	 * @throws Exception
	 */
	private static void getFile(String content, String fileName, String type) throws Exception {

		if (type.equals("as")) {
			fileName = fileName + ".as";
			if (fileName.contains("Dict")) {
				String filePath = outPath + "/as/Dict/";
				geneFile(content, filePath, fileName);

			} else if (fileName.contains("Inst")) {
				String filePath = outPath + "/as/Inst/";
				geneFile(content, filePath, fileName);
			} else {
				String filePath = outPath + "/as/";
				geneFile(content, filePath, fileName);
			}
		} else if (type.equals("requestGame")) {
			fileName = "protocol.xml";
			String filePath = outPath + "/msgRule/";
			geneFile(content, filePath, fileName);
		} else if (type.equals("VS")) {
			fileName = fileName + ".cs";
			if (fileName.contains("Dict") && !fileName.equals("SysAssetDict.cs") && !fileName.equals("SysDictData.cs")) {
				String filePath = outPath + "/AutoGen/Dict/";
				geneFile(content, filePath, fileName);
			} else if (fileName.contains("Inst")) {
				String filePath = outPath + "/AutoGen/Inst/";
				geneFile(content, filePath, fileName);
			} else if (fileName.contains("Sys")) {
				String filePath = outPath + "/AutoGen/Sys/";
				geneFile(content, filePath, fileName);
			} else if (fileName.contains("Cn")) {
				String filePath = outPath + "/AutoGen/Cn/";
				geneFile(content, filePath, fileName);
			} else {
				String filePath = outPath + "/AutoGen/";
				geneFile(content, filePath, fileName);
			}
		} else if (type.equals("static")) {
			fileName = fileName + ".java";
			String filePath = outPath + "/static/";
			geneFile(content, filePath, fileName);
		} else if (type.equals("java")) {
			fileName = fileName + ".java";
			String filePath = outPath + "/java/";
			geneFile(content, filePath, fileName);
		} else if (type.equals("staticAs")) {
			fileName = fileName + ".as";
			String filePath = outPath + "/staticAs/";
			geneFile(content, filePath, fileName);
		} else if (type.equals("staticVS")) {
			fileName = fileName + ".cs";
			String filePath = outPath + "/AutoGen/Static/";
			geneFile(content, filePath, fileName);
		} else if (type.equals("staticCADD")) {
			fileName = fileName + ".lua";
			String filePath = outPath + "/lua/";
			geneFile(content, filePath, fileName);
		}
	}

	/**
	 * 生成文件
	 * 
	 * @param content
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	public static void geneFile(String content, String filePath, String fileName) throws Exception {
		// BufferedWriter bw = null;
		try {
			createDir(filePath);
			FileUtil.writeContentToFile(filePath, fileName, "utf-8", content, StandardOpenOption.CREATE_NEW);
			/*
			 * bw = new BufferedWriter(new FileWriter(createFile(filePath+fileName))); bw.write(content); bw.flush();
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// bw.close();
		}
	}

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 */
	private static void createDir(String path) {
		File dict = new File(path);
		if (!dict.exists()) {
			dict.mkdirs();
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 */
	private static File createFile(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * AS匹配类型
	 * 
	 * @param type
	 * @return
	 */
	private static String getCSType(String type) {
		String dataType = "";
		if (type.equals("int")) {
			dataType = "int";
		} else if (type.equals("bigint")) {
			dataType = "Number";
		} else if (type.equals("varchar")) {
			dataType = "String";
		} else if (type.equals("float")) {
			dataType = "Number";
		} else if (type.equals("double")) {
			dataType = "Number";
		} else if (type.equals("smallint")) {
			dataType = "int";
		} else if (type.equals("decimal")) {
			dataType = "Number";
		} else if (type.equals("numeric")) {
			dataType = "Number";
		} else if (type.equals("timestamp")) {
			dataType = "Date";
		} else if (type.equals("datetime")) {
			dataType = "Date";
		} else if (type.equals("blob")) {
			dataType = "String";
		} else if (type.equals("text")) {
			dataType = "String";
		}
		return dataType;
	}

	/**
	 * AS初始值
	 * 
	 * @param type
	 * @return
	 */
	private static String getCSInitValue(String type) {
		String dataType = "";
		if (type.equals("int")) {
			dataType = "0";
		} else if (type.equals("bigint")) {
			dataType = "0";
		} else if (type.equals("varchar")) {
			dataType = "null";
		} else if (type.equals("float")) {
			dataType = "0";
		} else if (type.equals("double")) {
			dataType = "0";
		} else if (type.equals("smallint")) {
			dataType = "0";
		} else if (type.equals("decimal")) {
			dataType = "0";
		} else if (type.equals("numeric")) {
			dataType = "0";
		} else if (type.equals("timestamp")) {
			dataType = "null";
		} else if (type.equals("datetime")) {
			dataType = "null";
		} else if (type.equals("blob")) {
			dataType = "null";
		} else if (type.equals("text")) {
			dataType = "null";
		}
		return dataType;
	}

	/**
	 * JAVA匹配类型
	 * 
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
		} else if (type.contains("mediumblob")) {
			dataType = "InputStream";
		} else if (type.equals("float")) {
			dataType = "float";
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
	 * VS C#匹配类型
	 * 
	 * @param type
	 * @return
	 */
	private static String getVSType(String type) {
		String dataType = "";
		if (type.equals("int")) {
			dataType = "int";
		} else if (type.equals("bigint")) {
			dataType = "long";
		} else if (type.contains("mediumblob")) {
			dataType = "InputStream";
		} else if (type.equals("varchar")) {
			dataType = "string";
		} else if (type.equals("float")) {
			dataType = "float";
		} else if (type.equals("double")) {
			dataType = "double";
		} else if (type.equals("smallint")) {
			dataType = "int";
		} else if (type.equals("decimal")) {
			dataType = "float";
		} else if (type.equals("numeric")) {
			dataType = "numeric";
		} else if (type.equals("timestamp")) {
			dataType = "System.Timestamp";
		} else if (type.equals("datetime")) {
			dataType = "DateTime";
		} else if (type.equals("longblob")) {
			dataType = "object";
		} else if (type.equals("text")) {
			dataType = "string";
		}
		return dataType;
	}

	/**
	 * 删除文件夹下所有内容，包括此文件夹
	 * 
	 * @param f
	 * @throws IOException
	 */
	private static void delAll(File f) throws IOException {
		if (!f.exists())
			return;
		boolean rslt = true;
		if (!(rslt = f.delete())) {
			File subs[] = f.listFiles();
			for (int i = 0; i <= subs.length - 1; i++) {
				if (subs[i].isDirectory())
					delAll(subs[i]);
				rslt = subs[i].delete();
			}
			rslt = f.delete();

			if (!rslt)
				throw new IOException("无法删除:" + f.getName());
		}
		return;
	}

	public static void generate() {
		try {
			delAll(new File(outPath));
			Hashtable<String, String> tableMap = getTableInfo();
			Iterator<String> tableNames = tableMap.keySet().iterator();
			while (tableNames.hasNext()) {
				String table = tableNames.next();

				getJavaBean(table, tableMap.get(table));
				// getVSBean(table, tableMap.get(table));
				// getASBean(table, tableMap.get(table));
				// getVSBean(table, tableMap.get(table));
				getStatic_Java(table, tableMap.get(table));
				// getStatic_As(table, tableMap.get(table));
				// getStatic_VS(table, tableMap.get(table));
				getStatic_CADD(table, tableMap.get(table));// c++

			}
			// System.out.println("全部生成完成!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		generate();
		System.out.println("全部生成完成!");
	}

}