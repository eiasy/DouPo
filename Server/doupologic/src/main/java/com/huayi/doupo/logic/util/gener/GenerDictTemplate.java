package com.huayi.doupo.logic.util.gener;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictMapListObj;
import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.base.util.base.StringUtil;

public class GenerDictTemplate{

	private static int whichDB = 6;

	private static Map<Integer, String> dbInfoMap = new HashMap<Integer, String>();
	static{
		dbInfoMap.put(0, "127.0.0.1_doupogener_root_123456");
		dbInfoMap.put(1, "192.168.1.43_20150427_root_123456");
		dbInfoMap.put(2, "192.168.1.42_dragonball_root_123456");
		dbInfoMap.put(3, "192.168.4.202_dragonball1_dragonball_dragonballdbpass");
		dbInfoMap.put(4, "192.168.4.202_dragonball2_dragonball_dragonballdbpass");
		dbInfoMap.put(5, "192.168.1.42_doupo_root_123456");
		dbInfoMap.put(6, "192.168.1.42_doupoNC_root_123456");
		dbInfoMap.put(7, "192.168.1.30_doupo_root_666666");
	}
	private static String dBName = dbInfoMap.get(whichDB).split("_")[1];
    
    private static String mapList = "Dict_Hole_Consume:qualityId;Dict_Advance:cardId:qualityId_starLevelId;Dict_Title_Detail:titleId;Dict_AcupointNode:acupointId:tier_node;Dict_KungFuTierAdd:kungFuId;" +
    		"Dict_FireGain:position;Dict_Thing:thingTypeId;Dict_Pagoda_Drop:pagodaStoreyId;Dict_Recruit_Card:recruitTypeId;Dict_Barrier_Drop:barrierId;" +
    		"Dict_Barrier:chapterId:id;Dict_Barrier_Level:barrierId;Dict_Chapter:type;Dict_Loot_Chip:qualityId:type;Dict_Chip:skillOrKungFuId;Dict_Pill:pillTypeId;"+
    		"Dict_Restore:qualityId:starLevelId;Dict_Magic_Level:type:level;Dict_Achievement:achievementTypeId:id;Dict_Gener_BoxThing:type;Dict_Special_BoxThing:tableTypeId;Dict_Card:qualityId;" +
    		"Dict_Equipment:equipQualityId;Dict_Magic:type;Dict_Barrier_Drop_Type:type;Dict_Recruit_SpecialCard:areaNo;Dict_Recruit_TimesGet:recruitTypeId;Dict_Activity_SignIn:month:day;Dict_Activity_GrabTheHour:type;" +
    		"Dict_Activity_PrivateSale:type;Dict_Activity_DailyDeals:day;Dict_Activity_MonthCardStore:type;Dict_Equip_Advance:equipTypeId;Dict_FightSoul:fightSoulQualityId;Dict_FightSoul_UpgradeExp:fightSoulQualityId;" +
    		"Dict_FightSoul_UpgradeProp:fightSoulId;Dict_Mine_BoxThing:type;Dict_Wing_Advance:wingId;Dict_Wing_Strengthen:wingId;Dict_HoldStar_GradeReward:starNum;"+
    		"Dict_Equip_Advance_red:equipId;Dict_Magic_refining:MagicId;Dict_Card_Luck:cardId;Dict_Equip_SuitRefer:equipId;Dict_Wing_Luck:cardId";

    private static List<DictMapListObj> mapListObjList = new ArrayList<DictMapListObj>();
    
	public static void getMapListObj (String mapList){
		if (mapList.equals("")) {
			return;
		}
		String[] tableSplit = mapList.split(";");
		for(String str : tableSplit){
			String[] fieldSplit = str.split(":");
			DictMapListObj dictMapListObj = new DictMapListObj();
			for(int i = 0; i < fieldSplit.length; i++){
				if(i == 0){
					dictMapListObj.setTalbeName(fieldSplit[i]);
				}else if(i == 1){
					dictMapListObj.setFiledIdName(fieldSplit[i]);
				}else if (i == 2) {
					dictMapListObj.setOrderFileds(fieldSplit[i].split("_"));
				}
			}
			mapListObjList.add(dictMapListObj);
		}
	}
    
	/**
	 * 连接数据库
	 * @return
	 * @throws Exception
	 */
	private static Connection getMySQLConnection() throws Exception {
		String dbInfo = dbInfoMap.get(whichDB);
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbInfo.split("_")[0]
				+ ":3306/" + dbInfo.split("_")[1] + "", dbInfo.split("_")[2], dbInfo.split("_")[3]);
		return conn;
	}

	/**
	 * 获取类名或首字母的列名，用于get和set方法
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
		return string.substring(0,1).toUpperCase()+string.substring(1);
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
	   * 删除文件夹下所有内容，包括此文件夹
	   * @param f
	   * @throws IOException
	   */
	  @SuppressWarnings("unused")
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
		 * 创建文件夹
		 * @param path
		 */
		@SuppressWarnings("unused")
		private static void createDir(String path) {
			File dict = new File(path);
			if (!dict.exists()) {
				dict.mkdirs();
			}
		}
		
	  /**
	   * 给前端生成字典数据类
	   * @author mp
	   * @version 1.0, 2013-4-18 上午11:39:34
	   * @param list
	   */
	  public static void getCsInstDictData(List<String> list) throws Exception{
		  StringBuilder sb = new StringBuilder();
		  sb.append("namespace yy.web.model.dict\n");
		  sb.append("{\n");
		  sb.append("\t /// <summary>\n");
		  sb.append("\t ///  Author   : liujian\n");
		  sb.append("\t ///  Date     : 2013年4月16日\n");
		  sb.append("\t ///  Duty     : 表数据对象\n");
		  sb.append("\t  ///  Revision : \n");
		  sb.append("\t /// </summary>\n");
		  sb.append("\t public class InstDictData\n");
		  sb.append("\t{\n");
		  for(String string : list){
			  sb.append("\t\tpublic System.Collections.Generic.Dictionary<string, "+string+"> "+StringUtil.capitalize(string)+" { set; get; }\n");
		  }
		  sb.append("\t}\n");
		  sb.append("}");
		  
		  String path = "D:/0BeanOut/AutoGen/Inst/";
		  GenerBeanTemPlate.geneFile(sb.toString(), path, "InstDictData.cs");
	  }
		
	  /**
	   * 生成前端用的字典数据类
	   * @author mp
	   * @version 1.0, 2013-4-18 上午11:40:07
	   * @param list
	   */
	  public static void getInstDictData(List<String> list){
		 
		  StringBuilder sb = new StringBuilder();
		  sb.append("package com.huayi.doupo.base.model.dict;\n");
		  sb.append("import java.util.Map;\n");
		  sb.append("import com.huayi.doupo.base.model.*;\n\n");
		  sb.append("public class InstDictData {\n\n");
		  
		  for(String string : list){
			  sb.append("\tprivate Map<String,"+string+"> "+StringUtil.uncapitalize(string)+";\n");
			  sb.append("\tpublic Map<String, "+string+"> get"+string+"() {\n");
			  sb.append("\t\treturn "+StringUtil.uncapitalize(string)+";\n");
			  sb.append("\t}\n");
			  
			  sb.append("\tpublic void set"+string+"(Map<String, "+string+"> "+StringUtil.uncapitalize(string)+") {\n");
			  sb.append("\t\tthis."+StringUtil.uncapitalize(string)+" = "+StringUtil.uncapitalize(string)+";\n");
			  sb.append("\t}\n\n");
		  }
		  sb.append("}");
		  
		  String pathArr=new GenerDictTemplate().getClass().getClassLoader().getResource("").getPath();
		  String path = pathArr.split("bin/")[0].substring(0,pathArr.split("bin/")[0].length() - 15).replace("doupologic", "doupobase")+"src/main/java/com/huayi/doupo/base/model/dict/";
		  FileUtil.writeContentToFile(StringUtil.noContainFirstString(path), "InstDictData.java", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
	  }
	  
	  /**
	   * 生成自己用的字典数据类
	   * @author mp
	   * @version 1.0, 2013-4-18 上午11:40:07
	   * @param list
	   */
	  public static void generDictMap(List<String> list){
		 
		  StringBuilder sb = new StringBuilder();
		  sb.append("package com.huayi.doupo.base.model.dict;\n");
		  sb.append("import java.util.Map;\n");
		  sb.append("import com.huayi.doupo.base.model.*;\n\n");
		  sb.append("public class DictMap {\n\n");
		  
		  for(String string : list){
			  sb.append("\tpublic static Map<String,"+string+"> "+StringUtil.uncapitalize(string)+"Map = null;\n");
		  }
		  sb.append("}");
		  
		  String pathArr=new GenerDictTemplate().getClass().getClassLoader().getResource("").getPath();
		  String path = pathArr.split("bin/")[0].substring(0,pathArr.split("bin/")[0].length() - 15).replace("doupologic", "doupobase")+"src/main/java/com/huayi/doupo/base/model/dict/";
		  FileUtil.writeContentToFile(StringUtil.noContainFirstString(path), "DictMap.java", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
	  }
	  
	  
	  public static void playerMemObj(List<String> list){
			 
		  StringBuilder sb = new StringBuilder();
		  sb.append("package com.huayi.doupo.base.model.player;\n");
		  sb.append("import java.util.concurrent.ConcurrentHashMap;\n");
		  sb.append("import com.huayi.doupo.base.model.*;\n\n");
		  sb.append("public class PlayerMemObj {\n\n");
		  sb.append("\tpublic PlayerMemObj (long currTime) {\n");
		  sb.append("\t\tthis.currTime = currTime;\n");
		  sb.append("\t}\n\n");
		  sb.append("\tpublic long currTime;\n");
		  for(String string : list){
			  if(StringUtil.contains(string, "Inst")){
				  sb.append("\tpublic ConcurrentHashMap<Integer, "+string+"> "+StringUtil.uncapitalize(string)+"Map = new ConcurrentHashMap<Integer, "+string+">();\n");
			  }else{
				  sb.append("\tpublic ConcurrentHashMap<Integer, "+string+"> "+StringUtil.uncapitalize(string)+"Map = null;\n");
			  }
		  }
		  sb.append("}");
		  
		  String pathArr=new GenerDictTemplate().getClass().getClassLoader().getResource("").getPath();
		  String path = pathArr.split("bin/")[0].substring(0,pathArr.split("bin/")[0].length() - 15).replace("doupologic", "doupobase")+"src/main/java/com/huayi/doupo/base/model/player/";
		  
		  FileUtil.writeContentToFile(StringUtil.noContainFirstString(path), "PlayerMemObj.java", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
	  }
	  
	  public static void generDictMapList(){
		  
		  StringBuilder sb = new StringBuilder();
		  sb.append("package com.huayi.doupo.base.model.dict;\n\n");
		  sb.append("import java.util.HashMap;\n");
		  sb.append("import java.util.Map;\n\n");
		  sb.append("public class DictMapList {\n");
		  
		  if(mapListObjList.size() > 0){
			  for(DictMapListObj obj : mapListObjList){
				  String tableName = obj.getTalbeName();
				  String tableClassName = tableName.replace("_", "");
				  sb.append("\tpublic static Map<Integer, Object> "+StringUtil.uncapitalize(tableClassName)+"Map = new HashMap<Integer, Object>();\n");
			  }
		  }
		  sb.append("}");
		  
		  String pathArr=new GenerDictTemplate().getClass().getClassLoader().getResource("").getPath();
		  String path = pathArr.split("bin/")[0].substring(0,pathArr.split("bin/")[0].length() - 15).replace("doupologic", "doupobase")+"src/main/java/com/huayi/doupo/base/model/dict/";
		  
		  FileUtil.writeContentToFile(StringUtil.noContainFirstString(path), "DictMapList.java", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
	  }
	  
	  public static void generDictList(List<String> list){
		  StringBuilder sb = new StringBuilder();
		  sb.append("package com.huayi.doupo.base.model.dict;\n");
		  sb.append("import java.util.List;\n");
		  sb.append("import com.huayi.doupo.base.model.*;\n\n");
		  sb.append("public class DictList {\n\n");
		  
		  for(String string : list){
			  sb.append("\tpublic static List<"+string+"> "+StringUtil.uncapitalize(string)+"List = null;\n");
		  }
		  sb.append("}");
		  
		  String pathArr=new GenerDictTemplate().getClass().getClassLoader().getResource("").getPath();
		  String path = pathArr.split("bin/")[0].substring(0,pathArr.split("bin/")[0].length() - 15).replace("doupologic", "doupobase")+"src/main/java/com/huayi/doupo/base/model/dict/";
		  
		  FileUtil.writeContentToFile(StringUtil.noContainFirstString(path), "DictList.java", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
	  }
	  
	  /**
	   * 生成前端用DictData类
	   * @author mp
	   * @version 1.0, 2013-4-18 上午11:41:11
	   * @param list
	   */
	  public static void generDictData(List<String> list){
		  
		  StringBuilder sb = new StringBuilder();
		  sb.append("package com.huayi.doupo.base.model.dict;\n");
		  sb.append("import com.huayi.doupo.base.dal.factory.DALFactory;\n");
		  
		  sb.append("public class DictData extends DALFactory{\n\n");
		  
		  sb.append("\tpublic static int flag = 0;\n");
		  sb.append("\tpublic static int isAll = 1;\n");
		  sb.append("\tpublic static String tableNameList = \"\";\n\n");

		  sb.append("\t/**\n");
		  sb.append("\t * 获取静态的字典数据\n");
		  sb.append("\t * @author mp\n");
		  sb.append("\t * @version 1.0, "+new Date()+"\n");
		  sb.append("\t * @return\n");
		  sb.append("\t * @throws Exception\n");
		  sb.append("\t*/\n");
		  
		  sb.append("\tpublic static void loadDictData() throws Exception{\n");
		  sb.append("\t\tif(flag==0){\n");
		  sb.append("\t\t\tdictData(0);\n");
		  sb.append("\t\t}\n");
		  sb.append("\t\tflag++;\n");
		  sb.append("\t}\n\n");
		  System.out.println();
		  
		  sb.append("\tpublic static void dictData(int pd) throws Exception{\n\n");
		  sb.append("\t\tString [] tableNameArray = tableNameList.split(\";\");\n\n");
		  sb.append("\t\tfor(String tableName : tableNameArray){\n");
		  
		  for(String string : list){
			  sb.append("\t\t\tif(tableName.equals(\""+string+"\") || isAll == 1){\n");
			  sb.append("\t\t\t\tDictDataUtil."+StringUtil.uncapitalize(string)+"Util(pd);\n");
			  sb.append("\t\t\t}\n\n");
		  }
		  
		  if(mapListObjList.size() > 0){
			  for(DictMapListObj mapListObj : mapListObjList){
					String tableName = mapListObj.getTalbeName();
					String tableClassName = tableName.replace("_", "");
					
					sb.append("\t\t\tif(tableName.equals(\""+StringUtil.capitalize(tableClassName)+"\") || isAll == 1){\n");
					sb.append("\t\t\t\tDictDataUtil."+StringUtil.uncapitalize(tableClassName)+"GroupUtil(pd);\n");
					sb.append("\t\t\t}\n\n");
				}
			  sb.append("\t\t}\n");
		  }
		  sb.append("\t}\n");
		  sb.append("}\n");

		  String pathArr=new GenerDictTemplate().getClass().getClassLoader().getResource("").getPath();
		  String path = pathArr.split("bin/")[0].substring(0,pathArr.split("bin/")[0].length() - 15).replace("doupologic", "doupobase")+"src/main/java/com/huayi/doupo/base/model/dict/";
		  
		  FileUtil.writeContentToFile(StringUtil.noContainFirstString(path), "DictData.java", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
	  }
	  
	  
	  /**
	   * 生成前端用DictDataUtil类
	   * @author mp
	   * @version 1.0, 2013-4-18 上午11:41:11
	   * @param list
	   */
	  public static void generDictDataUtil(List<String> list){
		  
		  StringBuilder sb = new StringBuilder();
		  sb.append("package com.huayi.doupo.base.model.dict;\n");
		  sb.append("import java.util.List;\n");
		  sb.append("import java.lang.reflect.*;\n");
		  sb.append("import java.nio.file.*;\n");
		  sb.append("import java.util.Map;\n");
		  sb.append("import java.util.LinkedHashMap;\n");
		  sb.append("import com.huayi.doupo.base.dal.factory.DALFactory;\n");
		  sb.append("import com.huayi.doupo.base.model.*;\n\n");
		  sb.append("import com.huayi.doupo.base.util.base.*;\n\n");
		  
		  sb.append("public class DictDataUtil extends DALFactory{\n\n");
		  
		  sb.append("\tpublic static int flag = 0;\n");
		  sb.append("\tpublic static int isAll = 1;\n");
		  sb.append("\tpublic static String tableNameList = \"\";\n");
		  sb.append("\tpublic static String path = \"E:/doupoout/0BeanOut/lua/\";\n\n");

		  sb.append("\t/**\n");
		  sb.append("\t * 获取静态的字典数据\n");
		  sb.append("\t * @author hzw\n");
		  sb.append("\t * @version 1.0, "+new Date()+"\n");
		  sb.append("\t * @return\n");
		  sb.append("\t * @throws Exception\n");
		  sb.append("\t*/\n");
		  

		  System.out.println();
		  
		  
		  for(String string : list){
			  sb.append("\tpublic static void "+StringUtil.uncapitalize(string)+"Util (int pd) throws Exception{\n");
			  sb.append("\t\tList<"+string+"> "+StringUtil.uncapitalize(string)+"List = get"+string+"DAL().getList(\"\", 0);\n");
			  sb.append("\t\tDictList."+StringUtil.uncapitalize(string)+"List = "+StringUtil.uncapitalize(string)+"List;\n");
			  sb.append("\t\tMap<String, "+string+"> "+StringUtil.uncapitalize(string)+"Map = new LinkedHashMap<String, "+string+">();\n");
			  sb.append("\t\tStringBuffer sb = new StringBuffer();\n");
			  sb.append("\t\tString className = \"" + string+ "\";\n");
			  sb.append("\t\tsb.append(className + \"=\" + \"{}\\n\");\n");
			  sb.append("\t\tfor("+string+" obj : "+StringUtil.uncapitalize(string)+"List){\n");
			  sb.append("\t\t\t"+StringUtil.uncapitalize(string)+"Map.put(obj.getId()+\"\", obj);\n");
			  sb.append("\t\t\tField[] field = obj.getClass().getDeclaredFields();\n");
			  sb.append("\t\t\tString rowString = \"={\";\n");
			  sb.append("\t\t\tString rowOut = \"\";\n");
			  sb.append("\t\t\tfor (int i = 0; i < field.length; i++) {\n");
			  sb.append("\t\t\t\tString realFiledName = field[i].getName();\n");
			  sb.append("\t\t\t\tString realFiledType = field[i].getType().getName();\n");
			  sb.append("\t\t\t\tString filedName = field[i].getName();\n");
			  sb.append("\t\t\t\tif (filedName.equals(\"index\") || filedName.equals(\"result\") || filedName.equals(\"version\")) {\n");
			  sb.append("\t\t\t\t\tcontinue;\n");
			  sb.append("\t\t\t\t}\n");
			  sb.append("\t\t\t\tfiledName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());\n");
			  sb.append("\t\t\t\tMethod m = obj.getClass().getMethod(\"get\" + filedName);\n");
			  sb.append("\t\t\t\tObject value =  m.invoke(obj) == null ? \"\" : m.invoke(obj);\n");
			  sb.append("\t\t\t\tif (realFiledType.equals(\"java.lang.String\")) {\n");
			  sb.append("\t\t\t\t\tvalue = \"\\\"\" + value + \"\\\"\";\n");
			  sb.append("\t\t\t\t}\n");
			  sb.append("\t\t\t\tif (realFiledName.equals(\"id\")) {\n");
			  sb.append("\t\t\t\t\trowOut = \""+string+"[\\\"\" + value + \"\\\"]\";\n");
			  sb.append("\t\t\t\t}\n");
			  sb.append("\t\t\t\tString innerString = realFiledName + \"=\" + value + \",\";\n");
			  sb.append("\t\t\t\trowString += innerString;\n");
			  sb.append("\t\t\t}\n");
			  sb.append("\t\t\trowString = rowString.substring(0,rowString.length() - 1) + \"}\";\n");
			  sb.append("\t\t\trowString = rowOut + rowString;\n");
			  sb.append("\t\t\tsb.append(rowString.toString());\n");
			  sb.append("\t\t\tsb.append(\"\\n\");\n");
			  sb.append("\t\t}\n");
			  sb.append("\t\tif(pd == 1){\n");
			  sb.append("\t\t\tFileUtil.writeContentToFile(path,"+StringUtil.capitalize(string)+".class.getSimpleName()+\".lua\", \"utf-8\", sb.toString(), StandardOpenOption.CREATE_NEW);\n");
			  sb.append("\t\t}\n");
			  sb.append("\t\tDictMap."+StringUtil.uncapitalize(string)+"Map="+StringUtil.uncapitalize(string)+"Map;\n");
			  sb.append("\t}\n\n");
		  }
		  
		  if(mapListObjList.size() > 0){
			  
			  for(DictMapListObj mapListObj : mapListObjList){
					String tableName = mapListObj.getTalbeName();
					String tableClassName = tableName.replace("_", "");
					String fieldIdName = mapListObj.getFiledIdName();
					
					sb.append("\tpublic static void "+StringUtil.uncapitalize(tableClassName)+"GroupUtil (int pd) throws Exception{\n");
					
					String orderCond = "";
					if (mapListObj.getOrderFileds() != null && mapListObj.getOrderFileds().length != 0) {
						orderCond = " order by ";
						for (String orderFiled : mapListObj.getOrderFileds()) {
							orderCond += orderFiled + ", ";
						}
						orderCond = orderCond.substring(0, orderCond.length() - 2);
					}
					
					sb.append("\t\tString sql = \"select "+fieldIdName+" from "+ tableName+"  group by "+fieldIdName+"\";\n");
					sb.append("\t\tList<Map<String, Object>> listMaps = get"+StringUtil.capitalize(tableClassName)+"DAL().sqlHelper(sql);\n");
					sb.append("\t\tfor(Map<String, Object> map : listMaps){\n");
					sb.append("\t\t\tif(map.get(\""+fieldIdName+"\") != null){\n");
					sb.append("\t\tint fieldIdName = (int)map.get(\""+fieldIdName+"\");\n");
					sb.append("\t\t\t\tList<"+StringUtil.capitalize(tableClassName)+"> "+StringUtil.uncapitalize(tableClassName)+"s = get"+StringUtil.capitalize(tableClassName)+"DAL().getList(\""+fieldIdName+" =\"+fieldIdName + \""+orderCond+"\", 0);\n");
					sb.append("\t\t\t\tDictMapList."+StringUtil.uncapitalize(tableClassName)+"Map.put(fieldIdName,"+StringUtil.uncapitalize(tableClassName)+"s);\n");
					sb.append("\t\t\t}\n");
					sb.append("\t\t}\n");
					sb.append("\t}\n\n");
				}
		  }
		  sb.append("}\n");

		  String pathArr=new GenerDictTemplate().getClass().getClassLoader().getResource("").getPath();
		  String path = pathArr.split("bin/")[0].substring(0,pathArr.split("bin/")[0].length() - 15).replace("doupologic", "doupobase")+"src/main/java/com/huayi/doupo/base/model/dict/";
		  
		  FileUtil.writeContentToFile(StringUtil.noContainFirstString(path), "DictDataUtil.java", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
	  }
	  
	  /**
	   * 生成GenerDictData类
	   * @author mp
	   * @version 1.0, 2013-4-18 上午11:41:11
	   * @param list
	   */
	  public static void getFrontDict(List<String> list){
		  
		  StringBuilder sb = new StringBuilder();
		  sb.append("package yy.web.model.dict;\n");
		  sb.append("import java.util.List;\n");
		  sb.append("import java.util.Map;\n");
		  sb.append("import java.util.LinkedHashMap;\n");
		  sb.append("import yy.web.bll.context.BLLContext;\n");
		  sb.append("import yy.web.model.dict.InstDictData;\n");
		  sb.append("import yy.web.model.*;\n\n");
		  
		  sb.append("public class GenerFrontDictData extends BLLContext{\n\n");
		  
		  sb.append("\tpublic static int flag=0;\n");
		  sb.append("\tpublic static InstDictData instStaticDictData= null;\n\n");

		  sb.append("\t/**\n");
		  sb.append("\t * 获取静态的字典数据\n");
		  sb.append("\t * @author mp\n");
		  sb.append("\t * @version 1.0, "+new Date()+"\n");
		  sb.append("\t * @return\n");
		  sb.append("\t * @throws Exception\n");
		  sb.append("\t*/\n");
		  
		  sb.append("\tpublic static InstDictData getStaticInstDictData() throws Exception{\n");
		  sb.append("\t\tif(flag!=0){\n");
		  sb.append("\t\t\treturn instStaticDictData;\n");
		  sb.append("\t\t}\n");
		  sb.append("\t\tflag++;\n");
		  sb.append("\t\tinstStaticDictData = getInstDictData();\n");
		  sb.append("\t\treturn instStaticDictData;\n");
		  sb.append("\t}\n\n");
		  System.out.println();
		  
		  sb.append("\tpublic static InstDictData getInstDictData() throws Exception{\n\n");
		  sb.append("\t\tInstDictData instDictData = new InstDictData();\n\n");
		  
		  for(String string : list){
			  sb.append("\t\tList<"+string+"> "+StringUtil.uncapitalize(string)+"List = get"+string+"BLL().getList(\"\");\n");
			  sb.append("\t\tStaticDictList."+StringUtil.uncapitalize(string)+"List = "+StringUtil.uncapitalize(string)+"List;\n");
			  sb.append("\t\tMap<String, "+string+"> "+StringUtil.uncapitalize(string)+"Map = new LinkedHashMap<String, "+string+">();\n");
			  sb.append("\t\tfor("+string+" obj : "+StringUtil.uncapitalize(string)+"List){\n");
			  sb.append("\t\t\t"+StringUtil.uncapitalize(string)+"Map.put(obj.getId()+\"\", obj);\n");
			  sb.append("\t\t}\n");
			  sb.append("\t\tinstDictData.set"+string+"("+StringUtil.uncapitalize(string)+"Map);\n\n\n");
		  }
		  sb.append("\t\treturn instDictData;\n");
		  sb.append("\t}\n");
		  sb.append("}\n");
		  
		  String pathArr=new GenerDictTemplate().getClass().getClassLoader().getResource("").getPath();
		  String path = pathArr.split("bin/")[0].substring(0,pathArr.split("bin/")[0].length() - 15).replace("doupologic", "doupobase")+"/src/yy/web/model/dict/";
		  
		  FileUtil.writeContentToFile(StringUtil.noContainFirstString(path), "GenerFrontDictData.java", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
	  }
	  

	public static void generate(){
			
			try {
				
//				String frontTableString = "Dict_Skeleton,Dict_Server,Dict_Warrior,Dict_Component,Dict_UiIcon,Dict_Material," +
//						"Dict_Thing,Dict_Part,Dict_Model,Dict_Image,Dict_Setting,Dict_ProfessionType,Dict_AttackRangeType," +
//						"Dict_SkillHurtScope,Dict_Skill,Dict_Quality,Dict_Formation,Dict_Formation_Organize,Dict_PositionProp"+
//						"Dict_ThingType,Dict_BagType,Dict_PlayerBaseProp,Dict_Realm,Dict_TableType,Dict_FightProp,Dict_Function," +
//						"Dict_ThingUsedEffect";
				Hashtable<String, String> tableMap = getTableInfo();
				Iterator<String> tableNames = tableMap.keySet().iterator();
				List<String> list = new ArrayList<String>();
				List<String> listFront = new ArrayList<String>();
				List<String> listPlayerMemObj = new ArrayList<String>();
				while (tableNames.hasNext()) {
					String table = tableNames.next();
					if(table.startsWith("Dict") || table.startsWith("Sys")){
						if (!table.equals("Sys_CdKey")) {
							list.add(getClassColumnName(table));
							listFront.add(getClassColumnName(table));
						}
					}
//					if(frontTableString.contains(table)){
//						listFront.add(getClassColumnName(table));
//					}
					listPlayerMemObj.add(getClassColumnName(table));
				}
				
				getMapListObj(mapList);
				generDictList(list);
				generDictMap(list);
				generDictMapList();
				generDictData(list);
				generDictDataUtil(list);
				playerMemObj(listPlayerMemObj);
//				String pathArr=new GenerUpdateInfoTemp().getClass().getClassLoader().getResource("").getPath();
//				String path = pathArr.split("bin/")[0];
//				JSONUtil.objToJsonFile(DictData.getDictMap(), "d:/testtest.json");
				DictData.dictData(1);
//				System.out.println("--------生成完成--------");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) throws Exception{
		generate();
		System.out.println("--------生成完成--------");
	}
	
}