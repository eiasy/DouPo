package com.huayi.doupo.base.model.dict;
import java.util.List;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.Map;
import java.util.LinkedHashMap;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.*;

import com.huayi.doupo.base.util.base.*;

public class DictDataUtil extends DALFactory{

	public static int flag = 0;
	public static int isAll = 1;
	public static String tableNameList = "";
	public static String path = "E:/doupoout/0BeanOut/lua/";

	/**
	 * 获取静态的字典数据
	 * @author hzw
	 * @version 1.0, Thu Nov 26 11:31:47 CST 2015
	 * @return
	 * @throws Exception
	*/
	public static void dictChapterUtil (int pd) throws Exception{
		List<DictChapter> dictChapterList = getDictChapterDAL().getList("", 0);
		DictList.dictChapterList = dictChapterList;
		Map<String, DictChapter> dictChapterMap = new LinkedHashMap<String, DictChapter>();
		StringBuffer sb = new StringBuffer();
		String className = "DictChapter";
		sb.append(className + "=" + "{}\n");
		for(DictChapter obj : dictChapterList){
			dictChapterMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictChapter[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictChapter.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictChapterMap=dictChapterMap;
	}

	public static void dictDantaDayAwardUtil (int pd) throws Exception{
		List<DictDantaDayAward> dictDantaDayAwardList = getDictDantaDayAwardDAL().getList("", 0);
		DictList.dictDantaDayAwardList = dictDantaDayAwardList;
		Map<String, DictDantaDayAward> dictDantaDayAwardMap = new LinkedHashMap<String, DictDantaDayAward>();
		StringBuffer sb = new StringBuffer();
		String className = "DictDantaDayAward";
		sb.append(className + "=" + "{}\n");
		for(DictDantaDayAward obj : dictDantaDayAwardList){
			dictDantaDayAwardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictDantaDayAward[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictDantaDayAward.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictDantaDayAwardMap=dictDantaDayAwardMap;
	}

	public static void dictUnionGradeUtil (int pd) throws Exception{
		List<DictUnionGrade> dictUnionGradeList = getDictUnionGradeDAL().getList("", 0);
		DictList.dictUnionGradeList = dictUnionGradeList;
		Map<String, DictUnionGrade> dictUnionGradeMap = new LinkedHashMap<String, DictUnionGrade>();
		StringBuffer sb = new StringBuffer();
		String className = "DictUnionGrade";
		sb.append(className + "=" + "{}\n");
		for(DictUnionGrade obj : dictUnionGradeList){
			dictUnionGradeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictUnionGrade[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictUnionGrade.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictUnionGradeMap=dictUnionGradeMap;
	}

	public static void dictWorldBossRewardUtil (int pd) throws Exception{
		List<DictWorldBossReward> dictWorldBossRewardList = getDictWorldBossRewardDAL().getList("", 0);
		DictList.dictWorldBossRewardList = dictWorldBossRewardList;
		Map<String, DictWorldBossReward> dictWorldBossRewardMap = new LinkedHashMap<String, DictWorldBossReward>();
		StringBuffer sb = new StringBuffer();
		String className = "DictWorldBossReward";
		sb.append(className + "=" + "{}\n");
		for(DictWorldBossReward obj : dictWorldBossRewardList){
			dictWorldBossRewardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictWorldBossReward[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictWorldBossReward.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictWorldBossRewardMap=dictWorldBossRewardMap;
	}

	public static void dictAchievementTypeUtil (int pd) throws Exception{
		List<DictAchievementType> dictAchievementTypeList = getDictAchievementTypeDAL().getList("", 0);
		DictList.dictAchievementTypeList = dictAchievementTypeList;
		Map<String, DictAchievementType> dictAchievementTypeMap = new LinkedHashMap<String, DictAchievementType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictAchievementType";
		sb.append(className + "=" + "{}\n");
		for(DictAchievementType obj : dictAchievementTypeList){
			dictAchievementTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictAchievementType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictAchievementType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictAchievementTypeMap=dictAchievementTypeMap;
	}

	public static void dictActivityLvStoreUtil (int pd) throws Exception{
		List<DictActivityLvStore> dictActivityLvStoreList = getDictActivityLvStoreDAL().getList("", 0);
		DictList.dictActivityLvStoreList = dictActivityLvStoreList;
		Map<String, DictActivityLvStore> dictActivityLvStoreMap = new LinkedHashMap<String, DictActivityLvStore>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityLvStore";
		sb.append(className + "=" + "{}\n");
		for(DictActivityLvStore obj : dictActivityLvStoreList){
			dictActivityLvStoreMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityLvStore[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityLvStore.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityLvStoreMap=dictActivityLvStoreMap;
	}

	public static void dictAcupointNodeUtil (int pd) throws Exception{
		List<DictAcupointNode> dictAcupointNodeList = getDictAcupointNodeDAL().getList("", 0);
		DictList.dictAcupointNodeList = dictAcupointNodeList;
		Map<String, DictAcupointNode> dictAcupointNodeMap = new LinkedHashMap<String, DictAcupointNode>();
		StringBuffer sb = new StringBuffer();
		String className = "DictAcupointNode";
		sb.append(className + "=" + "{}\n");
		for(DictAcupointNode obj : dictAcupointNodeList){
			dictAcupointNodeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictAcupointNode[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictAcupointNode.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictAcupointNodeMap=dictAcupointNodeMap;
	}

	public static void dictFightPropUtil (int pd) throws Exception{
		List<DictFightProp> dictFightPropList = getDictFightPropDAL().getList("", 0);
		DictList.dictFightPropList = dictFightPropList;
		Map<String, DictFightProp> dictFightPropMap = new LinkedHashMap<String, DictFightProp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFightProp";
		sb.append(className + "=" + "{}\n");
		for(DictFightProp obj : dictFightPropList){
			dictFightPropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFightProp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFightProp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFightPropMap=dictFightPropMap;
	}

	public static void dictActivityAddRechargeUtil (int pd) throws Exception{
		List<DictActivityAddRecharge> dictActivityAddRechargeList = getDictActivityAddRechargeDAL().getList("", 0);
		DictList.dictActivityAddRechargeList = dictActivityAddRechargeList;
		Map<String, DictActivityAddRecharge> dictActivityAddRechargeMap = new LinkedHashMap<String, DictActivityAddRecharge>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityAddRecharge";
		sb.append(className + "=" + "{}\n");
		for(DictActivityAddRecharge obj : dictActivityAddRechargeList){
			dictActivityAddRechargeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityAddRecharge[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityAddRecharge.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityAddRechargeMap=dictActivityAddRechargeMap;
	}

	public static void dictUnionLevelPrivUtil (int pd) throws Exception{
		List<DictUnionLevelPriv> dictUnionLevelPrivList = getDictUnionLevelPrivDAL().getList("", 0);
		DictList.dictUnionLevelPrivList = dictUnionLevelPrivList;
		Map<String, DictUnionLevelPriv> dictUnionLevelPrivMap = new LinkedHashMap<String, DictUnionLevelPriv>();
		StringBuffer sb = new StringBuffer();
		String className = "DictUnionLevelPriv";
		sb.append(className + "=" + "{}\n");
		for(DictUnionLevelPriv obj : dictUnionLevelPrivList){
			dictUnionLevelPrivMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictUnionLevelPriv[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictUnionLevelPriv.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictUnionLevelPrivMap=dictUnionLevelPrivMap;
	}

	public static void dictCardSoulUtil (int pd) throws Exception{
		List<DictCardSoul> dictCardSoulList = getDictCardSoulDAL().getList("", 0);
		DictList.dictCardSoulList = dictCardSoulList;
		Map<String, DictCardSoul> dictCardSoulMap = new LinkedHashMap<String, DictCardSoul>();
		StringBuffer sb = new StringBuffer();
		String className = "DictCardSoul";
		sb.append(className + "=" + "{}\n");
		for(DictCardSoul obj : dictCardSoulList){
			dictCardSoulMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictCardSoul[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictCardSoul.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictCardSoulMap=dictCardSoulMap;
	}

	public static void dictLootDropUtil (int pd) throws Exception{
		List<DictLootDrop> dictLootDropList = getDictLootDropDAL().getList("", 0);
		DictList.dictLootDropList = dictLootDropList;
		Map<String, DictLootDrop> dictLootDropMap = new LinkedHashMap<String, DictLootDrop>();
		StringBuffer sb = new StringBuffer();
		String className = "DictLootDrop";
		sb.append(className + "=" + "{}\n");
		for(DictLootDrop obj : dictLootDropList){
			dictLootDropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictLootDrop[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictLootDrop.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictLootDropMap=dictLootDropMap;
	}

	public static void dictManualSkillExpUtil (int pd) throws Exception{
		List<DictManualSkillExp> dictManualSkillExpList = getDictManualSkillExpDAL().getList("", 0);
		DictList.dictManualSkillExpList = dictManualSkillExpList;
		Map<String, DictManualSkillExp> dictManualSkillExpMap = new LinkedHashMap<String, DictManualSkillExp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictManualSkillExp";
		sb.append(className + "=" + "{}\n");
		for(DictManualSkillExp obj : dictManualSkillExpList){
			dictManualSkillExpMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictManualSkillExp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictManualSkillExp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictManualSkillExpMap=dictManualSkillExpMap;
	}

	public static void dictFireSkillUtil (int pd) throws Exception{
		List<DictFireSkill> dictFireSkillList = getDictFireSkillDAL().getList("", 0);
		DictList.dictFireSkillList = dictFireSkillList;
		Map<String, DictFireSkill> dictFireSkillMap = new LinkedHashMap<String, DictFireSkill>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFireSkill";
		sb.append(className + "=" + "{}\n");
		for(DictFireSkill obj : dictFireSkillList){
			dictFireSkillMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFireSkill[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFireSkill.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFireSkillMap=dictFireSkillMap;
	}

	public static void dictActivityLuckUtil (int pd) throws Exception{
		List<DictActivityLuck> dictActivityLuckList = getDictActivityLuckDAL().getList("", 0);
		DictList.dictActivityLuckList = dictActivityLuckList;
		Map<String, DictActivityLuck> dictActivityLuckMap = new LinkedHashMap<String, DictActivityLuck>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityLuck";
		sb.append(className + "=" + "{}\n");
		for(DictActivityLuck obj : dictActivityLuckList){
			dictActivityLuckMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityLuck[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityLuck.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityLuckMap=dictActivityLuckMap;
	}

	public static void dictBeautyCardExpUtil (int pd) throws Exception{
		List<DictBeautyCardExp> dictBeautyCardExpList = getDictBeautyCardExpDAL().getList("", 0);
		DictList.dictBeautyCardExpList = dictBeautyCardExpList;
		Map<String, DictBeautyCardExp> dictBeautyCardExpMap = new LinkedHashMap<String, DictBeautyCardExp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictBeautyCardExp";
		sb.append(className + "=" + "{}\n");
		for(DictBeautyCardExp obj : dictBeautyCardExpList){
			dictBeautyCardExpMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictBeautyCardExp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictBeautyCardExp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictBeautyCardExpMap=dictBeautyCardExpMap;
	}

	public static void dictActivityLimitTimeHeroRankRewardUtil (int pd) throws Exception{
		List<DictActivityLimitTimeHeroRankReward> dictActivityLimitTimeHeroRankRewardList = getDictActivityLimitTimeHeroRankRewardDAL().getList("", 0);
		DictList.dictActivityLimitTimeHeroRankRewardList = dictActivityLimitTimeHeroRankRewardList;
		Map<String, DictActivityLimitTimeHeroRankReward> dictActivityLimitTimeHeroRankRewardMap = new LinkedHashMap<String, DictActivityLimitTimeHeroRankReward>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityLimitTimeHeroRankReward";
		sb.append(className + "=" + "{}\n");
		for(DictActivityLimitTimeHeroRankReward obj : dictActivityLimitTimeHeroRankRewardList){
			dictActivityLimitTimeHeroRankRewardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityLimitTimeHeroRankReward[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityLimitTimeHeroRankReward.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityLimitTimeHeroRankRewardMap=dictActivityLimitTimeHeroRankRewardMap;
	}

	public static void dictPillRecipeUtil (int pd) throws Exception{
		List<DictPillRecipe> dictPillRecipeList = getDictPillRecipeDAL().getList("", 0);
		DictList.dictPillRecipeList = dictPillRecipeList;
		Map<String, DictPillRecipe> dictPillRecipeMap = new LinkedHashMap<String, DictPillRecipe>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPillRecipe";
		sb.append(className + "=" + "{}\n");
		for(DictPillRecipe obj : dictPillRecipeList){
			dictPillRecipeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPillRecipe[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPillRecipe.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPillRecipeMap=dictPillRecipeMap;
	}

	public static void sysGmRewardUtil (int pd) throws Exception{
		List<SysGmReward> sysGmRewardList = getSysGmRewardDAL().getList("", 0);
		DictList.sysGmRewardList = sysGmRewardList;
		Map<String, SysGmReward> sysGmRewardMap = new LinkedHashMap<String, SysGmReward>();
		StringBuffer sb = new StringBuffer();
		String className = "SysGmReward";
		sb.append(className + "=" + "{}\n");
		for(SysGmReward obj : sysGmRewardList){
			sysGmRewardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "SysGmReward[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,SysGmReward.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.sysGmRewardMap=sysGmRewardMap;
	}

	public static void sysMarqueeUtil (int pd) throws Exception{
		List<SysMarquee> sysMarqueeList = getSysMarqueeDAL().getList("", 0);
		DictList.sysMarqueeList = sysMarqueeList;
		Map<String, SysMarquee> sysMarqueeMap = new LinkedHashMap<String, SysMarquee>();
		StringBuffer sb = new StringBuffer();
		String className = "SysMarquee";
		sb.append(className + "=" + "{}\n");
		for(SysMarquee obj : sysMarqueeList){
			sysMarqueeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "SysMarquee[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,SysMarquee.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.sysMarqueeMap=sysMarqueeMap;
	}

	public static void dictPlayerBasePropUtil (int pd) throws Exception{
		List<DictPlayerBaseProp> dictPlayerBasePropList = getDictPlayerBasePropDAL().getList("", 0);
		DictList.dictPlayerBasePropList = dictPlayerBasePropList;
		Map<String, DictPlayerBaseProp> dictPlayerBasePropMap = new LinkedHashMap<String, DictPlayerBaseProp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPlayerBaseProp";
		sb.append(className + "=" + "{}\n");
		for(DictPlayerBaseProp obj : dictPlayerBasePropList){
			dictPlayerBasePropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPlayerBaseProp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPlayerBaseProp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPlayerBasePropMap=dictPlayerBasePropMap;
	}

	public static void dictActivityOpenServiceBagUtil (int pd) throws Exception{
		List<DictActivityOpenServiceBag> dictActivityOpenServiceBagList = getDictActivityOpenServiceBagDAL().getList("", 0);
		DictList.dictActivityOpenServiceBagList = dictActivityOpenServiceBagList;
		Map<String, DictActivityOpenServiceBag> dictActivityOpenServiceBagMap = new LinkedHashMap<String, DictActivityOpenServiceBag>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityOpenServiceBag";
		sb.append(className + "=" + "{}\n");
		for(DictActivityOpenServiceBag obj : dictActivityOpenServiceBagList){
			dictActivityOpenServiceBagMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityOpenServiceBag[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityOpenServiceBag.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityOpenServiceBagMap=dictActivityOpenServiceBagMap;
	}

	public static void dictAdvanceUtil (int pd) throws Exception{
		List<DictAdvance> dictAdvanceList = getDictAdvanceDAL().getList("", 0);
		DictList.dictAdvanceList = dictAdvanceList;
		Map<String, DictAdvance> dictAdvanceMap = new LinkedHashMap<String, DictAdvance>();
		StringBuffer sb = new StringBuffer();
		String className = "DictAdvance";
		sb.append(className + "=" + "{}\n");
		for(DictAdvance obj : dictAdvanceList){
			dictAdvanceMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictAdvance[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictAdvance.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictAdvanceMap=dictAdvanceMap;
	}

	public static void dictPagodaDropUtil (int pd) throws Exception{
		List<DictPagodaDrop> dictPagodaDropList = getDictPagodaDropDAL().getList("", 0);
		DictList.dictPagodaDropList = dictPagodaDropList;
		Map<String, DictPagodaDrop> dictPagodaDropMap = new LinkedHashMap<String, DictPagodaDrop>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPagodaDrop";
		sb.append(className + "=" + "{}\n");
		for(DictPagodaDrop obj : dictPagodaDropList){
			dictPagodaDropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPagodaDrop[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPagodaDrop.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPagodaDropMap=dictPagodaDropMap;
	}

	public static void dictFightSoulQualityUtil (int pd) throws Exception{
		List<DictFightSoulQuality> dictFightSoulQualityList = getDictFightSoulQualityDAL().getList("", 0);
		DictList.dictFightSoulQualityList = dictFightSoulQualityList;
		Map<String, DictFightSoulQuality> dictFightSoulQualityMap = new LinkedHashMap<String, DictFightSoulQuality>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFightSoulQuality";
		sb.append(className + "=" + "{}\n");
		for(DictFightSoulQuality obj : dictFightSoulQualityList){
			dictFightSoulQualityMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFightSoulQuality[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFightSoulQuality.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFightSoulQualityMap=dictFightSoulQualityMap;
	}

	public static void dictInitCardUtil (int pd) throws Exception{
		List<DictInitCard> dictInitCardList = getDictInitCardDAL().getList("", 0);
		DictList.dictInitCardList = dictInitCardList;
		Map<String, DictInitCard> dictInitCardMap = new LinkedHashMap<String, DictInitCard>();
		StringBuffer sb = new StringBuffer();
		String className = "DictInitCard";
		sb.append(className + "=" + "{}\n");
		for(DictInitCard obj : dictInitCardList){
			dictInitCardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictInitCard[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictInitCard.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictInitCardMap=dictInitCardMap;
	}

	public static void dictFightSoulUpgradeExpUtil (int pd) throws Exception{
		List<DictFightSoulUpgradeExp> dictFightSoulUpgradeExpList = getDictFightSoulUpgradeExpDAL().getList("", 0);
		DictList.dictFightSoulUpgradeExpList = dictFightSoulUpgradeExpList;
		Map<String, DictFightSoulUpgradeExp> dictFightSoulUpgradeExpMap = new LinkedHashMap<String, DictFightSoulUpgradeExp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFightSoulUpgradeExp";
		sb.append(className + "=" + "{}\n");
		for(DictFightSoulUpgradeExp obj : dictFightSoulUpgradeExpList){
			dictFightSoulUpgradeExpMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFightSoulUpgradeExp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFightSoulUpgradeExp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFightSoulUpgradeExpMap=dictFightSoulUpgradeExpMap;
	}

	public static void dictKungFuTierAddUtil (int pd) throws Exception{
		List<DictKungFuTierAdd> dictKungFuTierAddList = getDictKungFuTierAddDAL().getList("", 0);
		DictList.dictKungFuTierAddList = dictKungFuTierAddList;
		Map<String, DictKungFuTierAdd> dictKungFuTierAddMap = new LinkedHashMap<String, DictKungFuTierAdd>();
		StringBuffer sb = new StringBuffer();
		String className = "DictKungFuTierAdd";
		sb.append(className + "=" + "{}\n");
		for(DictKungFuTierAdd obj : dictKungFuTierAddList){
			dictKungFuTierAddMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictKungFuTierAdd[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictKungFuTierAdd.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictKungFuTierAddMap=dictKungFuTierAddMap;
	}

	public static void dictEquipmentUtil (int pd) throws Exception{
		List<DictEquipment> dictEquipmentList = getDictEquipmentDAL().getList("", 0);
		DictList.dictEquipmentList = dictEquipmentList;
		Map<String, DictEquipment> dictEquipmentMap = new LinkedHashMap<String, DictEquipment>();
		StringBuffer sb = new StringBuffer();
		String className = "DictEquipment";
		sb.append(className + "=" + "{}\n");
		for(DictEquipment obj : dictEquipmentList){
			dictEquipmentMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictEquipment[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictEquipment.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictEquipmentMap=dictEquipmentMap;
	}

	public static void sysQuartzJobUtil (int pd) throws Exception{
		List<SysQuartzJob> sysQuartzJobList = getSysQuartzJobDAL().getList("", 0);
		DictList.sysQuartzJobList = sysQuartzJobList;
		Map<String, SysQuartzJob> sysQuartzJobMap = new LinkedHashMap<String, SysQuartzJob>();
		StringBuffer sb = new StringBuffer();
		String className = "SysQuartzJob";
		sb.append(className + "=" + "{}\n");
		for(SysQuartzJob obj : sysQuartzJobList){
			sysQuartzJobMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "SysQuartzJob[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,SysQuartzJob.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.sysQuartzJobMap=sysQuartzJobMap;
	}

	public static void dictPillThingUtil (int pd) throws Exception{
		List<DictPillThing> dictPillThingList = getDictPillThingDAL().getList("", 0);
		DictList.dictPillThingList = dictPillThingList;
		Map<String, DictPillThing> dictPillThingMap = new LinkedHashMap<String, DictPillThing>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPillThing";
		sb.append(className + "=" + "{}\n");
		for(DictPillThing obj : dictPillThingList){
			dictPillThingMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPillThing[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPillThing.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPillThingMap=dictPillThingMap;
	}

	public static void dictActivityMonthCardStoreUtil (int pd) throws Exception{
		List<DictActivityMonthCardStore> dictActivityMonthCardStoreList = getDictActivityMonthCardStoreDAL().getList("", 0);
		DictList.dictActivityMonthCardStoreList = dictActivityMonthCardStoreList;
		Map<String, DictActivityMonthCardStore> dictActivityMonthCardStoreMap = new LinkedHashMap<String, DictActivityMonthCardStore>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityMonthCardStore";
		sb.append(className + "=" + "{}\n");
		for(DictActivityMonthCardStore obj : dictActivityMonthCardStoreList){
			dictActivityMonthCardStoreMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityMonthCardStore[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityMonthCardStore.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityMonthCardStoreMap=dictActivityMonthCardStoreMap;
	}

	public static void dictBagTypeUtil (int pd) throws Exception{
		List<DictBagType> dictBagTypeList = getDictBagTypeDAL().getList("", 0);
		DictList.dictBagTypeList = dictBagTypeList;
		Map<String, DictBagType> dictBagTypeMap = new LinkedHashMap<String, DictBagType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictBagType";
		sb.append(className + "=" + "{}\n");
		for(DictBagType obj : dictBagTypeList){
			dictBagTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictBagType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictBagType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictBagTypeMap=dictBagTypeMap;
	}

	public static void dictCardExpUtil (int pd) throws Exception{
		List<DictCardExp> dictCardExpList = getDictCardExpDAL().getList("", 0);
		DictList.dictCardExpList = dictCardExpList;
		Map<String, DictCardExp> dictCardExpMap = new LinkedHashMap<String, DictCardExp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictCardExp";
		sb.append(className + "=" + "{}\n");
		for(DictCardExp obj : dictCardExpList){
			dictCardExpMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictCardExp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictCardExp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictCardExpMap=dictCardExpMap;
	}

	public static void dictKungFuTypeUtil (int pd) throws Exception{
		List<DictKungFuType> dictKungFuTypeList = getDictKungFuTypeDAL().getList("", 0);
		DictList.dictKungFuTypeList = dictKungFuTypeList;
		Map<String, DictKungFuType> dictKungFuTypeMap = new LinkedHashMap<String, DictKungFuType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictKungFuType";
		sb.append(className + "=" + "{}\n");
		for(DictKungFuType obj : dictKungFuTypeList){
			dictKungFuTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictKungFuType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictKungFuType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictKungFuTypeMap=dictKungFuTypeMap;
	}

	public static void dictActivityLimitShoppingUtil (int pd) throws Exception{
		List<DictActivityLimitShopping> dictActivityLimitShoppingList = getDictActivityLimitShoppingDAL().getList("", 0);
		DictList.dictActivityLimitShoppingList = dictActivityLimitShoppingList;
		Map<String, DictActivityLimitShopping> dictActivityLimitShoppingMap = new LinkedHashMap<String, DictActivityLimitShopping>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityLimitShopping";
		sb.append(className + "=" + "{}\n");
		for(DictActivityLimitShopping obj : dictActivityLimitShoppingList){
			dictActivityLimitShoppingMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityLimitShopping[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityLimitShopping.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityLimitShoppingMap=dictActivityLimitShoppingMap;
	}

	public static void dictActivityGrabTheHourUtil (int pd) throws Exception{
		List<DictActivityGrabTheHour> dictActivityGrabTheHourList = getDictActivityGrabTheHourDAL().getList("", 0);
		DictList.dictActivityGrabTheHourList = dictActivityGrabTheHourList;
		Map<String, DictActivityGrabTheHour> dictActivityGrabTheHourMap = new LinkedHashMap<String, DictActivityGrabTheHour>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityGrabTheHour";
		sb.append(className + "=" + "{}\n");
		for(DictActivityGrabTheHour obj : dictActivityGrabTheHourList){
			dictActivityGrabTheHourMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityGrabTheHour[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityGrabTheHour.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityGrabTheHourMap=dictActivityGrabTheHourMap;
	}

	public static void dictactivityExchangeUtil (int pd) throws Exception{
		List<DictactivityExchange> dictactivityExchangeList = getDictactivityExchangeDAL().getList("", 0);
		DictList.dictactivityExchangeList = dictactivityExchangeList;
		Map<String, DictactivityExchange> dictactivityExchangeMap = new LinkedHashMap<String, DictactivityExchange>();
		StringBuffer sb = new StringBuffer();
		String className = "DictactivityExchange";
		sb.append(className + "=" + "{}\n");
		for(DictactivityExchange obj : dictactivityExchangeList){
			dictactivityExchangeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictactivityExchange[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictactivityExchange.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictactivityExchangeMap=dictactivityExchangeMap;
	}

	public static void dictRestoreUtil (int pd) throws Exception{
		List<DictRestore> dictRestoreList = getDictRestoreDAL().getList("", 0);
		DictList.dictRestoreList = dictRestoreList;
		Map<String, DictRestore> dictRestoreMap = new LinkedHashMap<String, DictRestore>();
		StringBuffer sb = new StringBuffer();
		String className = "DictRestore";
		sb.append(className + "=" + "{}\n");
		for(DictRestore obj : dictRestoreList){
			dictRestoreMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictRestore[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictRestore.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictRestoreMap=dictRestoreMap;
	}

	public static void dictArenaIntervalUtil (int pd) throws Exception{
		List<DictArenaInterval> dictArenaIntervalList = getDictArenaIntervalDAL().getList("", 0);
		DictList.dictArenaIntervalList = dictArenaIntervalList;
		Map<String, DictArenaInterval> dictArenaIntervalMap = new LinkedHashMap<String, DictArenaInterval>();
		StringBuffer sb = new StringBuffer();
		String className = "DictArenaInterval";
		sb.append(className + "=" + "{}\n");
		for(DictArenaInterval obj : dictArenaIntervalList){
			dictArenaIntervalMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictArenaInterval[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictArenaInterval.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictArenaIntervalMap=dictArenaIntervalMap;
	}

	public static void dictBarrierDropUtil (int pd) throws Exception{
		List<DictBarrierDrop> dictBarrierDropList = getDictBarrierDropDAL().getList("", 0);
		DictList.dictBarrierDropList = dictBarrierDropList;
		Map<String, DictBarrierDrop> dictBarrierDropMap = new LinkedHashMap<String, DictBarrierDrop>();
		StringBuffer sb = new StringBuffer();
		String className = "DictBarrierDrop";
		sb.append(className + "=" + "{}\n");
		for(DictBarrierDrop obj : dictBarrierDropList){
			dictBarrierDropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictBarrierDrop[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictBarrierDrop.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictBarrierDropMap=dictBarrierDropMap;
	}

	public static void dictActivityStrogerHeroUtil (int pd) throws Exception{
		List<DictActivityStrogerHero> dictActivityStrogerHeroList = getDictActivityStrogerHeroDAL().getList("", 0);
		DictList.dictActivityStrogerHeroList = dictActivityStrogerHeroList;
		Map<String, DictActivityStrogerHero> dictActivityStrogerHeroMap = new LinkedHashMap<String, DictActivityStrogerHero>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityStrogerHero";
		sb.append(className + "=" + "{}\n");
		for(DictActivityStrogerHero obj : dictActivityStrogerHeroList){
			dictActivityStrogerHeroMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityStrogerHero[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityStrogerHero.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityStrogerHeroMap=dictActivityStrogerHeroMap;
	}

	public static void dictWingAdvanceUtil (int pd) throws Exception{
		List<DictWingAdvance> dictWingAdvanceList = getDictWingAdvanceDAL().getList("", 0);
		DictList.dictWingAdvanceList = dictWingAdvanceList;
		Map<String, DictWingAdvance> dictWingAdvanceMap = new LinkedHashMap<String, DictWingAdvance>();
		StringBuffer sb = new StringBuffer();
		String className = "DictWingAdvance";
		sb.append(className + "=" + "{}\n");
		for(DictWingAdvance obj : dictWingAdvanceList){
			dictWingAdvanceMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictWingAdvance[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictWingAdvance.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictWingAdvanceMap=dictWingAdvanceMap;
	}

	public static void dictCardUtil (int pd) throws Exception{
		List<DictCard> dictCardList = getDictCardDAL().getList("", 0);
		DictList.dictCardList = dictCardList;
		Map<String, DictCard> dictCardMap = new LinkedHashMap<String, DictCard>();
		StringBuffer sb = new StringBuffer();
		String className = "DictCard";
		sb.append(className + "=" + "{}\n");
		for(DictCard obj : dictCardList){
			dictCardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictCard[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictCard.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictCardMap=dictCardMap;
	}

	public static void dictActivityVipStoreUtil (int pd) throws Exception{
		List<DictActivityVipStore> dictActivityVipStoreList = getDictActivityVipStoreDAL().getList("", 0);
		DictList.dictActivityVipStoreList = dictActivityVipStoreList;
		Map<String, DictActivityVipStore> dictActivityVipStoreMap = new LinkedHashMap<String, DictActivityVipStore>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityVipStore";
		sb.append(className + "=" + "{}\n");
		for(DictActivityVipStore obj : dictActivityVipStoreList){
			dictActivityVipStoreMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityVipStore[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityVipStore.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityVipStoreMap=dictActivityVipStoreMap;
	}

	public static void dictYFireChipUtil (int pd) throws Exception{
		List<DictYFireChip> dictYFireChipList = getDictYFireChipDAL().getList("", 0);
		DictList.dictYFireChipList = dictYFireChipList;
		Map<String, DictYFireChip> dictYFireChipMap = new LinkedHashMap<String, DictYFireChip>();
		StringBuffer sb = new StringBuffer();
		String className = "DictYFireChip";
		sb.append(className + "=" + "{}\n");
		for(DictYFireChip obj : dictYFireChipList){
			dictYFireChipMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictYFireChip[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictYFireChip.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictYFireChipMap=dictYFireChipMap;
	}

	public static void dictFireGainUtil (int pd) throws Exception{
		List<DictFireGain> dictFireGainList = getDictFireGainDAL().getList("", 0);
		DictList.dictFireGainList = dictFireGainList;
		Map<String, DictFireGain> dictFireGainMap = new LinkedHashMap<String, DictFireGain>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFireGain";
		sb.append(className + "=" + "{}\n");
		for(DictFireGain obj : dictFireGainList){
			dictFireGainMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFireGain[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFireGain.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFireGainMap=dictFireGainMap;
	}

	public static void dictMagicQualityUtil (int pd) throws Exception{
		List<DictMagicQuality> dictMagicQualityList = getDictMagicQualityDAL().getList("", 0);
		DictList.dictMagicQualityList = dictMagicQualityList;
		Map<String, DictMagicQuality> dictMagicQualityMap = new LinkedHashMap<String, DictMagicQuality>();
		StringBuffer sb = new StringBuffer();
		String className = "DictMagicQuality";
		sb.append(className + "=" + "{}\n");
		for(DictMagicQuality obj : dictMagicQualityList){
			dictMagicQualityMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictMagicQuality[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictMagicQuality.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictMagicQualityMap=dictMagicQualityMap;
	}

	public static void dictTitleDetailUtil (int pd) throws Exception{
		List<DictTitleDetail> dictTitleDetailList = getDictTitleDetailDAL().getList("", 0);
		DictList.dictTitleDetailList = dictTitleDetailList;
		Map<String, DictTitleDetail> dictTitleDetailMap = new LinkedHashMap<String, DictTitleDetail>();
		StringBuffer sb = new StringBuffer();
		String className = "DictTitleDetail";
		sb.append(className + "=" + "{}\n");
		for(DictTitleDetail obj : dictTitleDetailList){
			dictTitleDetailMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictTitleDetail[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictTitleDetail.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictTitleDetailMap=dictTitleDetailMap;
	}

	public static void dictFightTypeUtil (int pd) throws Exception{
		List<DictFightType> dictFightTypeList = getDictFightTypeDAL().getList("", 0);
		DictList.dictFightTypeList = dictFightTypeList;
		Map<String, DictFightType> dictFightTypeMap = new LinkedHashMap<String, DictFightType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFightType";
		sb.append(className + "=" + "{}\n");
		for(DictFightType obj : dictFightTypeList){
			dictFightTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFightType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFightType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFightTypeMap=dictFightTypeMap;
	}

	public static void dictMineWeatherUtil (int pd) throws Exception{
		List<DictMineWeather> dictMineWeatherList = getDictMineWeatherDAL().getList("", 0);
		DictList.dictMineWeatherList = dictMineWeatherList;
		Map<String, DictMineWeather> dictMineWeatherMap = new LinkedHashMap<String, DictMineWeather>();
		StringBuffer sb = new StringBuffer();
		String className = "DictMineWeather";
		sb.append(className + "=" + "{}\n");
		for(DictMineWeather obj : dictMineWeatherList){
			dictMineWeatherMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictMineWeather[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictMineWeather.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictMineWeatherMap=dictMineWeatherMap;
	}

	public static void dictUIUtil (int pd) throws Exception{
		List<DictUI> dictUIList = getDictUIDAL().getList("", 0);
		DictList.dictUIList = dictUIList;
		Map<String, DictUI> dictUIMap = new LinkedHashMap<String, DictUI>();
		StringBuffer sb = new StringBuffer();
		String className = "DictUI";
		sb.append(className + "=" + "{}\n");
		for(DictUI obj : dictUIList){
			dictUIMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictUI[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictUI.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictUIMap=dictUIMap;
	}

	public static void dictFireGainRuleUtil (int pd) throws Exception{
		List<DictFireGainRule> dictFireGainRuleList = getDictFireGainRuleDAL().getList("", 0);
		DictList.dictFireGainRuleList = dictFireGainRuleList;
		Map<String, DictFireGainRule> dictFireGainRuleMap = new LinkedHashMap<String, DictFireGainRule>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFireGainRule";
		sb.append(className + "=" + "{}\n");
		for(DictFireGainRule obj : dictFireGainRuleList){
			dictFireGainRuleMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFireGainRule[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFireGainRule.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFireGainRuleMap=dictFireGainRuleMap;
	}

	public static void dictMineBoxThingUtil (int pd) throws Exception{
		List<DictMineBoxThing> dictMineBoxThingList = getDictMineBoxThingDAL().getList("", 0);
		DictList.dictMineBoxThingList = dictMineBoxThingList;
		Map<String, DictMineBoxThing> dictMineBoxThingMap = new LinkedHashMap<String, DictMineBoxThing>();
		StringBuffer sb = new StringBuffer();
		String className = "DictMineBoxThing";
		sb.append(className + "=" + "{}\n");
		for(DictMineBoxThing obj : dictMineBoxThingList){
			dictMineBoxThingMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictMineBoxThing[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictMineBoxThing.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictMineBoxThingMap=dictMineBoxThingMap;
	}

	public static void dictLevelPropUtil (int pd) throws Exception{
		List<DictLevelProp> dictLevelPropList = getDictLevelPropDAL().getList("", 0);
		DictList.dictLevelPropList = dictLevelPropList;
		Map<String, DictLevelProp> dictLevelPropMap = new LinkedHashMap<String, DictLevelProp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictLevelProp";
		sb.append(className + "=" + "{}\n");
		for(DictLevelProp obj : dictLevelPropList){
			dictLevelPropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictLevelProp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictLevelProp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictLevelPropMap=dictLevelPropMap;
	}

	public static void dictActivitySoulChapterDropUtil (int pd) throws Exception{
		List<DictActivitySoulChapterDrop> dictActivitySoulChapterDropList = getDictActivitySoulChapterDropDAL().getList("", 0);
		DictList.dictActivitySoulChapterDropList = dictActivitySoulChapterDropList;
		Map<String, DictActivitySoulChapterDrop> dictActivitySoulChapterDropMap = new LinkedHashMap<String, DictActivitySoulChapterDrop>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivitySoulChapterDrop";
		sb.append(className + "=" + "{}\n");
		for(DictActivitySoulChapterDrop obj : dictActivitySoulChapterDropList){
			dictActivitySoulChapterDropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivitySoulChapterDrop[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivitySoulChapterDrop.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivitySoulChapterDropMap=dictActivitySoulChapterDropMap;
	}

	public static void dictRecruitTypeUtil (int pd) throws Exception{
		List<DictRecruitType> dictRecruitTypeList = getDictRecruitTypeDAL().getList("", 0);
		DictList.dictRecruitTypeList = dictRecruitTypeList;
		Map<String, DictRecruitType> dictRecruitTypeMap = new LinkedHashMap<String, DictRecruitType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictRecruitType";
		sb.append(className + "=" + "{}\n");
		for(DictRecruitType obj : dictRecruitTypeList){
			dictRecruitTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictRecruitType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictRecruitType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictRecruitTypeMap=dictRecruitTypeMap;
	}

	public static void dictBarrierLevelUtil (int pd) throws Exception{
		List<DictBarrierLevel> dictBarrierLevelList = getDictBarrierLevelDAL().getList("", 0);
		DictList.dictBarrierLevelList = dictBarrierLevelList;
		Map<String, DictBarrierLevel> dictBarrierLevelMap = new LinkedHashMap<String, DictBarrierLevel>();
		StringBuffer sb = new StringBuffer();
		String className = "DictBarrierLevel";
		sb.append(className + "=" + "{}\n");
		for(DictBarrierLevel obj : dictBarrierLevelList){
			dictBarrierLevelMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictBarrierLevel[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictBarrierLevel.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictBarrierLevelMap=dictBarrierLevelMap;
	}

	public static void dictArenaNPCUtil (int pd) throws Exception{
		List<DictArenaNPC> dictArenaNPCList = getDictArenaNPCDAL().getList("", 0);
		DictList.dictArenaNPCList = dictArenaNPCList;
		Map<String, DictArenaNPC> dictArenaNPCMap = new LinkedHashMap<String, DictArenaNPC>();
		StringBuffer sb = new StringBuffer();
		String className = "DictArenaNPC";
		sb.append(className + "=" + "{}\n");
		for(DictArenaNPC obj : dictArenaNPCList){
			dictArenaNPCMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictArenaNPC[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictArenaNPC.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictArenaNPCMap=dictArenaNPCMap;
	}

	public static void dictSpecialBoxThingUtil (int pd) throws Exception{
		List<DictSpecialBoxThing> dictSpecialBoxThingList = getDictSpecialBoxThingDAL().getList("", 0);
		DictList.dictSpecialBoxThingList = dictSpecialBoxThingList;
		Map<String, DictSpecialBoxThing> dictSpecialBoxThingMap = new LinkedHashMap<String, DictSpecialBoxThing>();
		StringBuffer sb = new StringBuffer();
		String className = "DictSpecialBoxThing";
		sb.append(className + "=" + "{}\n");
		for(DictSpecialBoxThing obj : dictSpecialBoxThingList){
			dictSpecialBoxThingMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictSpecialBoxThing[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictSpecialBoxThing.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictSpecialBoxThingMap=dictSpecialBoxThingMap;
	}

	public static void dictEquipAdvanceUtil (int pd) throws Exception{
		List<DictEquipAdvance> dictEquipAdvanceList = getDictEquipAdvanceDAL().getList("", 0);
		DictList.dictEquipAdvanceList = dictEquipAdvanceList;
		Map<String, DictEquipAdvance> dictEquipAdvanceMap = new LinkedHashMap<String, DictEquipAdvance>();
		StringBuffer sb = new StringBuffer();
		String className = "DictEquipAdvance";
		sb.append(className + "=" + "{}\n");
		for(DictEquipAdvance obj : dictEquipAdvanceList){
			dictEquipAdvanceMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictEquipAdvance[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictEquipAdvance.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictEquipAdvanceMap=dictEquipAdvanceMap;
	}

	public static void dictBeautyCardUtil (int pd) throws Exception{
		List<DictBeautyCard> dictBeautyCardList = getDictBeautyCardDAL().getList("", 0);
		DictList.dictBeautyCardList = dictBeautyCardList;
		Map<String, DictBeautyCard> dictBeautyCardMap = new LinkedHashMap<String, DictBeautyCard>();
		StringBuffer sb = new StringBuffer();
		String className = "DictBeautyCard";
		sb.append(className + "=" + "{}\n");
		for(DictBeautyCard obj : dictBeautyCardList){
			dictBeautyCardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictBeautyCard[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictBeautyCard.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictBeautyCardMap=dictBeautyCardMap;
	}

	public static void dictSpecialRuleUtil (int pd) throws Exception{
		List<DictSpecialRule> dictSpecialRuleList = getDictSpecialRuleDAL().getList("", 0);
		DictList.dictSpecialRuleList = dictSpecialRuleList;
		Map<String, DictSpecialRule> dictSpecialRuleMap = new LinkedHashMap<String, DictSpecialRule>();
		StringBuffer sb = new StringBuffer();
		String className = "DictSpecialRule";
		sb.append(className + "=" + "{}\n");
		for(DictSpecialRule obj : dictSpecialRuleList){
			dictSpecialRuleMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictSpecialRule[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictSpecialRule.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictSpecialRuleMap=dictSpecialRuleMap;
	}

	public static void dictPagodaCardUtil (int pd) throws Exception{
		List<DictPagodaCard> dictPagodaCardList = getDictPagodaCardDAL().getList("", 0);
		DictList.dictPagodaCardList = dictPagodaCardList;
		Map<String, DictPagodaCard> dictPagodaCardMap = new LinkedHashMap<String, DictPagodaCard>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPagodaCard";
		sb.append(className + "=" + "{}\n");
		for(DictPagodaCard obj : dictPagodaCardList){
			dictPagodaCardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPagodaCard[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPagodaCard.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPagodaCardMap=dictPagodaCardMap;
	}

	public static void dictFireExpUtil (int pd) throws Exception{
		List<DictFireExp> dictFireExpList = getDictFireExpDAL().getList("", 0);
		DictList.dictFireExpList = dictFireExpList;
		Map<String, DictFireExp> dictFireExpMap = new LinkedHashMap<String, DictFireExp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFireExp";
		sb.append(className + "=" + "{}\n");
		for(DictFireExp obj : dictFireExpList){
			dictFireExpMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFireExp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFireExp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFireExpMap=dictFireExpMap;
	}

	public static void dictActivityLimitTimeHeroJiFenRewardUtil (int pd) throws Exception{
		List<DictActivityLimitTimeHeroJiFenReward> dictActivityLimitTimeHeroJiFenRewardList = getDictActivityLimitTimeHeroJiFenRewardDAL().getList("", 0);
		DictList.dictActivityLimitTimeHeroJiFenRewardList = dictActivityLimitTimeHeroJiFenRewardList;
		Map<String, DictActivityLimitTimeHeroJiFenReward> dictActivityLimitTimeHeroJiFenRewardMap = new LinkedHashMap<String, DictActivityLimitTimeHeroJiFenReward>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityLimitTimeHeroJiFenReward";
		sb.append(className + "=" + "{}\n");
		for(DictActivityLimitTimeHeroJiFenReward obj : dictActivityLimitTimeHeroJiFenRewardList){
			dictActivityLimitTimeHeroJiFenRewardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityLimitTimeHeroJiFenReward[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityLimitTimeHeroJiFenReward.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityLimitTimeHeroJiFenRewardMap=dictActivityLimitTimeHeroJiFenRewardMap;
	}

	public static void dictUnionBuildUtil (int pd) throws Exception{
		List<DictUnionBuild> dictUnionBuildList = getDictUnionBuildDAL().getList("", 0);
		DictList.dictUnionBuildList = dictUnionBuildList;
		Map<String, DictUnionBuild> dictUnionBuildMap = new LinkedHashMap<String, DictUnionBuild>();
		StringBuffer sb = new StringBuffer();
		String className = "DictUnionBuild";
		sb.append(className + "=" + "{}\n");
		for(DictUnionBuild obj : dictUnionBuildList){
			dictUnionBuildMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictUnionBuild[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictUnionBuild.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictUnionBuildMap=dictUnionBuildMap;
	}

	public static void dictEquipTypeUtil (int pd) throws Exception{
		List<DictEquipType> dictEquipTypeList = getDictEquipTypeDAL().getList("", 0);
		DictList.dictEquipTypeList = dictEquipTypeList;
		Map<String, DictEquipType> dictEquipTypeMap = new LinkedHashMap<String, DictEquipType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictEquipType";
		sb.append(className + "=" + "{}\n");
		for(DictEquipType obj : dictEquipTypeList){
			dictEquipTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictEquipType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictEquipType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictEquipTypeMap=dictEquipTypeMap;
	}

	public static void dictActivityLevelBagUtil (int pd) throws Exception{
		List<DictActivityLevelBag> dictActivityLevelBagList = getDictActivityLevelBagDAL().getList("", 0);
		DictList.dictActivityLevelBagList = dictActivityLevelBagList;
		Map<String, DictActivityLevelBag> dictActivityLevelBagMap = new LinkedHashMap<String, DictActivityLevelBag>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityLevelBag";
		sb.append(className + "=" + "{}\n");
		for(DictActivityLevelBag obj : dictActivityLevelBagList){
			dictActivityLevelBagMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityLevelBag[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityLevelBag.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityLevelBagMap=dictActivityLevelBagMap;
	}

	public static void dictChipUtil (int pd) throws Exception{
		List<DictChip> dictChipList = getDictChipDAL().getList("", 0);
		DictList.dictChipList = dictChipList;
		Map<String, DictChip> dictChipMap = new LinkedHashMap<String, DictChip>();
		StringBuffer sb = new StringBuffer();
		String className = "DictChip";
		sb.append(className + "=" + "{}\n");
		for(DictChip obj : dictChipList){
			dictChipMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictChip[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictChip.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictChipMap=dictChipMap;
	}

	public static void dictFightSoulHuntRuleUtil (int pd) throws Exception{
		List<DictFightSoulHuntRule> dictFightSoulHuntRuleList = getDictFightSoulHuntRuleDAL().getList("", 0);
		DictList.dictFightSoulHuntRuleList = dictFightSoulHuntRuleList;
		Map<String, DictFightSoulHuntRule> dictFightSoulHuntRuleMap = new LinkedHashMap<String, DictFightSoulHuntRule>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFightSoulHuntRule";
		sb.append(className + "=" + "{}\n");
		for(DictFightSoulHuntRule obj : dictFightSoulHuntRuleList){
			dictFightSoulHuntRuleMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFightSoulHuntRule[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFightSoulHuntRule.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFightSoulHuntRuleMap=dictFightSoulHuntRuleMap;
	}

	public static void dictFightSoulUpgradePropUtil (int pd) throws Exception{
		List<DictFightSoulUpgradeProp> dictFightSoulUpgradePropList = getDictFightSoulUpgradePropDAL().getList("", 0);
		DictList.dictFightSoulUpgradePropList = dictFightSoulUpgradePropList;
		Map<String, DictFightSoulUpgradeProp> dictFightSoulUpgradePropMap = new LinkedHashMap<String, DictFightSoulUpgradeProp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFightSoulUpgradeProp";
		sb.append(className + "=" + "{}\n");
		for(DictFightSoulUpgradeProp obj : dictFightSoulUpgradePropList){
			dictFightSoulUpgradePropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFightSoulUpgradeProp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFightSoulUpgradeProp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFightSoulUpgradePropMap=dictFightSoulUpgradePropMap;
	}

	public static void dictActivityOnlineRewardsUtil (int pd) throws Exception{
		List<DictActivityOnlineRewards> dictActivityOnlineRewardsList = getDictActivityOnlineRewardsDAL().getList("", 0);
		DictList.dictActivityOnlineRewardsList = dictActivityOnlineRewardsList;
		Map<String, DictActivityOnlineRewards> dictActivityOnlineRewardsMap = new LinkedHashMap<String, DictActivityOnlineRewards>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityOnlineRewards";
		sb.append(className + "=" + "{}\n");
		for(DictActivityOnlineRewards obj : dictActivityOnlineRewardsList){
			dictActivityOnlineRewardsMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityOnlineRewards[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityOnlineRewards.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityOnlineRewardsMap=dictActivityOnlineRewardsMap;
	}

	public static void dictDantaLayerUtil (int pd) throws Exception{
		List<DictDantaLayer> dictDantaLayerList = getDictDantaLayerDAL().getList("", 0);
		DictList.dictDantaLayerList = dictDantaLayerList;
		Map<String, DictDantaLayer> dictDantaLayerMap = new LinkedHashMap<String, DictDantaLayer>();
		StringBuffer sb = new StringBuffer();
		String className = "DictDantaLayer";
		sb.append(className + "=" + "{}\n");
		for(DictDantaLayer obj : dictDantaLayerList){
			dictDantaLayerMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictDantaLayer[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictDantaLayer.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictDantaLayerMap=dictDantaLayerMap;
	}

	public static void dictLootChipUtil (int pd) throws Exception{
		List<DictLootChip> dictLootChipList = getDictLootChipDAL().getList("", 0);
		DictList.dictLootChipList = dictLootChipList;
		Map<String, DictLootChip> dictLootChipMap = new LinkedHashMap<String, DictLootChip>();
		StringBuffer sb = new StringBuffer();
		String className = "DictLootChip";
		sb.append(className + "=" + "{}\n");
		for(DictLootChip obj : dictLootChipList){
			dictLootChipMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictLootChip[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictLootChip.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictLootChipMap=dictLootChipMap;
	}

	public static void dictRechargeUtil (int pd) throws Exception{
		List<DictRecharge> dictRechargeList = getDictRechargeDAL().getList("", 0);
		DictList.dictRechargeList = dictRechargeList;
		Map<String, DictRecharge> dictRechargeMap = new LinkedHashMap<String, DictRecharge>();
		StringBuffer sb = new StringBuffer();
		String className = "DictRecharge";
		sb.append(className + "=" + "{}\n");
		for(DictRecharge obj : dictRechargeList){
			dictRechargeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictRecharge[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictRecharge.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictRechargeMap=dictRechargeMap;
	}

	public static void dictRecruitTimesGetUtil (int pd) throws Exception{
		List<DictRecruitTimesGet> dictRecruitTimesGetList = getDictRecruitTimesGetDAL().getList("", 0);
		DictList.dictRecruitTimesGetList = dictRecruitTimesGetList;
		Map<String, DictRecruitTimesGet> dictRecruitTimesGetMap = new LinkedHashMap<String, DictRecruitTimesGet>();
		StringBuffer sb = new StringBuffer();
		String className = "DictRecruitTimesGet";
		sb.append(className + "=" + "{}\n");
		for(DictRecruitTimesGet obj : dictRecruitTimesGetList){
			dictRecruitTimesGetMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictRecruitTimesGet[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictRecruitTimesGet.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictRecruitTimesGetMap=dictRecruitTimesGetMap;
	}

	public static void dictGuipStepUtil (int pd) throws Exception{
		List<DictGuipStep> dictGuipStepList = getDictGuipStepDAL().getList("", 0);
		DictList.dictGuipStepList = dictGuipStepList;
		Map<String, DictGuipStep> dictGuipStepMap = new LinkedHashMap<String, DictGuipStep>();
		StringBuffer sb = new StringBuffer();
		String className = "DictGuipStep";
		sb.append(className + "=" + "{}\n");
		for(DictGuipStep obj : dictGuipStepList){
			dictGuipStepMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictGuipStep[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictGuipStep.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictGuipStepMap=dictGuipStepMap;
	}

	public static void dictBarrierUtil (int pd) throws Exception{
		List<DictBarrier> dictBarrierList = getDictBarrierDAL().getList("", 0);
		DictList.dictBarrierList = dictBarrierList;
		Map<String, DictBarrier> dictBarrierMap = new LinkedHashMap<String, DictBarrier>();
		StringBuffer sb = new StringBuffer();
		String className = "DictBarrier";
		sb.append(className + "=" + "{}\n");
		for(DictBarrier obj : dictBarrierList){
			dictBarrierMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictBarrier[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictBarrier.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictBarrierMap=dictBarrierMap;
	}

	public static void dictKungFuQualityUtil (int pd) throws Exception{
		List<DictKungFuQuality> dictKungFuQualityList = getDictKungFuQualityDAL().getList("", 0);
		DictList.dictKungFuQualityList = dictKungFuQualityList;
		Map<String, DictKungFuQuality> dictKungFuQualityMap = new LinkedHashMap<String, DictKungFuQuality>();
		StringBuffer sb = new StringBuffer();
		String className = "DictKungFuQuality";
		sb.append(className + "=" + "{}\n");
		for(DictKungFuQuality obj : dictKungFuQualityList){
			dictKungFuQualityMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictKungFuQuality[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictKungFuQuality.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictKungFuQualityMap=dictKungFuQualityMap;
	}

	public static void dictWorldBossTimesRewardUtil (int pd) throws Exception{
		List<DictWorldBossTimesReward> dictWorldBossTimesRewardList = getDictWorldBossTimesRewardDAL().getList("", 0);
		DictList.dictWorldBossTimesRewardList = dictWorldBossTimesRewardList;
		Map<String, DictWorldBossTimesReward> dictWorldBossTimesRewardMap = new LinkedHashMap<String, DictWorldBossTimesReward>();
		StringBuffer sb = new StringBuffer();
		String className = "DictWorldBossTimesReward";
		sb.append(className + "=" + "{}\n");
		for(DictWorldBossTimesReward obj : dictWorldBossTimesRewardList){
			dictWorldBossTimesRewardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictWorldBossTimesReward[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictWorldBossTimesReward.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictWorldBossTimesRewardMap=dictWorldBossTimesRewardMap;
	}

	public static void dictTrainPropUtil (int pd) throws Exception{
		List<DictTrainProp> dictTrainPropList = getDictTrainPropDAL().getList("", 0);
		DictList.dictTrainPropList = dictTrainPropList;
		Map<String, DictTrainProp> dictTrainPropMap = new LinkedHashMap<String, DictTrainProp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictTrainProp";
		sb.append(className + "=" + "{}\n");
		for(DictTrainProp obj : dictTrainPropList){
			dictTrainPropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictTrainProp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictTrainProp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictTrainPropMap=dictTrainPropMap;
	}

	public static void dictPillUtil (int pd) throws Exception{
		List<DictPill> dictPillList = getDictPillDAL().getList("", 0);
		DictList.dictPillList = dictPillList;
		Map<String, DictPill> dictPillMap = new LinkedHashMap<String, DictPill>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPill";
		sb.append(className + "=" + "{}\n");
		for(DictPill obj : dictPillList){
			dictPillMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPill[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPill.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPillMap=dictPillMap;
	}

	public static void dictBarrierCardUtil (int pd) throws Exception{
		List<DictBarrierCard> dictBarrierCardList = getDictBarrierCardDAL().getList("", 0);
		DictList.dictBarrierCardList = dictBarrierCardList;
		Map<String, DictBarrierCard> dictBarrierCardMap = new LinkedHashMap<String, DictBarrierCard>();
		StringBuffer sb = new StringBuffer();
		String className = "DictBarrierCard";
		sb.append(className + "=" + "{}\n");
		for(DictBarrierCard obj : dictBarrierCardList){
			dictBarrierCardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictBarrierCard[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictBarrierCard.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictBarrierCardMap=dictBarrierCardMap;
	}

	public static void dictKungFuUtil (int pd) throws Exception{
		List<DictKungFu> dictKungFuList = getDictKungFuDAL().getList("", 0);
		DictList.dictKungFuList = dictKungFuList;
		Map<String, DictKungFu> dictKungFuMap = new LinkedHashMap<String, DictKungFu>();
		StringBuffer sb = new StringBuffer();
		String className = "DictKungFu";
		sb.append(className + "=" + "{}\n");
		for(DictKungFu obj : dictKungFuList){
			dictKungFuMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictKungFu[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictKungFu.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictKungFuMap=dictKungFuMap;
	}

	public static void dictactivityTotalCostUtil (int pd) throws Exception{
		List<DictactivityTotalCost> dictactivityTotalCostList = getDictactivityTotalCostDAL().getList("", 0);
		DictList.dictactivityTotalCostList = dictactivityTotalCostList;
		Map<String, DictactivityTotalCost> dictactivityTotalCostMap = new LinkedHashMap<String, DictactivityTotalCost>();
		StringBuffer sb = new StringBuffer();
		String className = "DictactivityTotalCost";
		sb.append(className + "=" + "{}\n");
		for(DictactivityTotalCost obj : dictactivityTotalCostList){
			dictactivityTotalCostMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictactivityTotalCost[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictactivityTotalCost.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictactivityTotalCostMap=dictactivityTotalCostMap;
	}

	public static void dictPillQualityUtil (int pd) throws Exception{
		List<DictPillQuality> dictPillQualityList = getDictPillQualityDAL().getList("", 0);
		DictList.dictPillQualityList = dictPillQualityList;
		Map<String, DictPillQuality> dictPillQualityMap = new LinkedHashMap<String, DictPillQuality>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPillQuality";
		sb.append(className + "=" + "{}\n");
		for(DictPillQuality obj : dictPillQualityList){
			dictPillQualityMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPillQuality[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPillQuality.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPillQualityMap=dictPillQualityMap;
	}

	public static void sysMsgRuleUtil (int pd) throws Exception{
		List<SysMsgRule> sysMsgRuleList = getSysMsgRuleDAL().getList("", 0);
		DictList.sysMsgRuleList = sysMsgRuleList;
		Map<String, SysMsgRule> sysMsgRuleMap = new LinkedHashMap<String, SysMsgRule>();
		StringBuffer sb = new StringBuffer();
		String className = "SysMsgRule";
		sb.append(className + "=" + "{}\n");
		for(SysMsgRule obj : sysMsgRuleList){
			sysMsgRuleMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "SysMsgRule[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,SysMsgRule.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.sysMsgRuleMap=sysMsgRuleMap;
	}

	public static void dictGenerBoxThingUtil (int pd) throws Exception{
		List<DictGenerBoxThing> dictGenerBoxThingList = getDictGenerBoxThingDAL().getList("", 0);
		DictList.dictGenerBoxThingList = dictGenerBoxThingList;
		Map<String, DictGenerBoxThing> dictGenerBoxThingMap = new LinkedHashMap<String, DictGenerBoxThing>();
		StringBuffer sb = new StringBuffer();
		String className = "DictGenerBoxThing";
		sb.append(className + "=" + "{}\n");
		for(DictGenerBoxThing obj : dictGenerBoxThingList){
			dictGenerBoxThingMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictGenerBoxThing[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictGenerBoxThing.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictGenerBoxThingMap=dictGenerBoxThingMap;
	}

	public static void dictRecruitSpecialCardUtil (int pd) throws Exception{
		List<DictRecruitSpecialCard> dictRecruitSpecialCardList = getDictRecruitSpecialCardDAL().getList("", 0);
		DictList.dictRecruitSpecialCardList = dictRecruitSpecialCardList;
		Map<String, DictRecruitSpecialCard> dictRecruitSpecialCardMap = new LinkedHashMap<String, DictRecruitSpecialCard>();
		StringBuffer sb = new StringBuffer();
		String className = "DictRecruitSpecialCard";
		sb.append(className + "=" + "{}\n");
		for(DictRecruitSpecialCard obj : dictRecruitSpecialCardList){
			dictRecruitSpecialCardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictRecruitSpecialCard[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictRecruitSpecialCard.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictRecruitSpecialCardMap=dictRecruitSpecialCardMap;
	}

	public static void sysStaticsUtil (int pd) throws Exception{
		List<SysStatics> sysStaticsList = getSysStaticsDAL().getList("", 0);
		DictList.sysStaticsList = sysStaticsList;
		Map<String, SysStatics> sysStaticsMap = new LinkedHashMap<String, SysStatics>();
		StringBuffer sb = new StringBuffer();
		String className = "SysStatics";
		sb.append(className + "=" + "{}\n");
		for(SysStatics obj : sysStaticsList){
			sysStaticsMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "SysStatics[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,SysStatics.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.sysStaticsMap=sysStaticsMap;
	}

	public static void dictActivityDailyDealsUtil (int pd) throws Exception{
		List<DictActivityDailyDeals> dictActivityDailyDealsList = getDictActivityDailyDealsDAL().getList("", 0);
		DictList.dictActivityDailyDealsList = dictActivityDailyDealsList;
		Map<String, DictActivityDailyDeals> dictActivityDailyDealsMap = new LinkedHashMap<String, DictActivityDailyDeals>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityDailyDeals";
		sb.append(className + "=" + "{}\n");
		for(DictActivityDailyDeals obj : dictActivityDailyDealsList){
			dictActivityDailyDealsMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityDailyDeals[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityDailyDeals.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityDailyDealsMap=dictActivityDailyDealsMap;
	}

	public static void dictFunctionOpenUtil (int pd) throws Exception{
		List<DictFunctionOpen> dictFunctionOpenList = getDictFunctionOpenDAL().getList("", 0);
		DictList.dictFunctionOpenList = dictFunctionOpenList;
		Map<String, DictFunctionOpen> dictFunctionOpenMap = new LinkedHashMap<String, DictFunctionOpen>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFunctionOpen";
		sb.append(className + "=" + "{}\n");
		for(DictFunctionOpen obj : dictFunctionOpenList){
			dictFunctionOpenMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFunctionOpen[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFunctionOpen.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFunctionOpenMap=dictFunctionOpenMap;
	}

	public static void dictTryToPracticeBarrierCardUtil (int pd) throws Exception{
		List<DictTryToPracticeBarrierCard> dictTryToPracticeBarrierCardList = getDictTryToPracticeBarrierCardDAL().getList("", 0);
		DictList.dictTryToPracticeBarrierCardList = dictTryToPracticeBarrierCardList;
		Map<String, DictTryToPracticeBarrierCard> dictTryToPracticeBarrierCardMap = new LinkedHashMap<String, DictTryToPracticeBarrierCard>();
		StringBuffer sb = new StringBuffer();
		String className = "DictTryToPracticeBarrierCard";
		sb.append(className + "=" + "{}\n");
		for(DictTryToPracticeBarrierCard obj : dictTryToPracticeBarrierCardList){
			dictTryToPracticeBarrierCardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictTryToPracticeBarrierCard[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictTryToPracticeBarrierCard.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictTryToPracticeBarrierCardMap=dictTryToPracticeBarrierCardMap;
	}

	public static void dictQualityUtil (int pd) throws Exception{
		List<DictQuality> dictQualityList = getDictQualityDAL().getList("", 0);
		DictList.dictQualityList = dictQualityList;
		Map<String, DictQuality> dictQualityMap = new LinkedHashMap<String, DictQuality>();
		StringBuffer sb = new StringBuffer();
		String className = "DictQuality";
		sb.append(className + "=" + "{}\n");
		for(DictQuality obj : dictQualityList){
			dictQualityMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictQuality[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictQuality.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictQualityMap=dictQualityMap;
	}

	public static void dictPagodaFormationUtil (int pd) throws Exception{
		List<DictPagodaFormation> dictPagodaFormationList = getDictPagodaFormationDAL().getList("", 0);
		DictList.dictPagodaFormationList = dictPagodaFormationList;
		Map<String, DictPagodaFormation> dictPagodaFormationMap = new LinkedHashMap<String, DictPagodaFormation>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPagodaFormation";
		sb.append(className + "=" + "{}\n");
		for(DictPagodaFormation obj : dictPagodaFormationList){
			dictPagodaFormationMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPagodaFormation[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPagodaFormation.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPagodaFormationMap=dictPagodaFormationMap;
	}

	public static void dictColorUtil (int pd) throws Exception{
		List<DictColor> dictColorList = getDictColorDAL().getList("", 0);
		DictList.dictColorList = dictColorList;
		Map<String, DictColor> dictColorMap = new LinkedHashMap<String, DictColor>();
		StringBuffer sb = new StringBuffer();
		String className = "DictColor";
		sb.append(className + "=" + "{}\n");
		for(DictColor obj : dictColorList){
			dictColorMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictColor[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictColor.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictColorMap=dictColorMap;
	}

	public static void dictMineTypeUtil (int pd) throws Exception{
		List<DictMineType> dictMineTypeList = getDictMineTypeDAL().getList("", 0);
		DictList.dictMineTypeList = dictMineTypeList;
		Map<String, DictMineType> dictMineTypeMap = new LinkedHashMap<String, DictMineType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictMineType";
		sb.append(className + "=" + "{}\n");
		for(DictMineType obj : dictMineTypeList){
			dictMineTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictMineType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictMineType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictMineTypeMap=dictMineTypeMap;
	}

	public static void dictHoleConsumeUtil (int pd) throws Exception{
		List<DictHoleConsume> dictHoleConsumeList = getDictHoleConsumeDAL().getList("", 0);
		DictList.dictHoleConsumeList = dictHoleConsumeList;
		Map<String, DictHoleConsume> dictHoleConsumeMap = new LinkedHashMap<String, DictHoleConsume>();
		StringBuffer sb = new StringBuffer();
		String className = "DictHoleConsume";
		sb.append(className + "=" + "{}\n");
		for(DictHoleConsume obj : dictHoleConsumeList){
			dictHoleConsumeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictHoleConsume[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictHoleConsume.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictHoleConsumeMap=dictHoleConsumeMap;
	}

	public static void dictPagodaStoreUtil (int pd) throws Exception{
		List<DictPagodaStore> dictPagodaStoreList = getDictPagodaStoreDAL().getList("", 0);
		DictList.dictPagodaStoreList = dictPagodaStoreList;
		Map<String, DictPagodaStore> dictPagodaStoreMap = new LinkedHashMap<String, DictPagodaStore>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPagodaStore";
		sb.append(className + "=" + "{}\n");
		for(DictPagodaStore obj : dictPagodaStoreList){
			dictPagodaStoreMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPagodaStore[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPagodaStore.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPagodaStoreMap=dictPagodaStoreMap;
	}

	public static void dictFightSoulPositionCondUtil (int pd) throws Exception{
		List<DictFightSoulPositionCond> dictFightSoulPositionCondList = getDictFightSoulPositionCondDAL().getList("", 0);
		DictList.dictFightSoulPositionCondList = dictFightSoulPositionCondList;
		Map<String, DictFightSoulPositionCond> dictFightSoulPositionCondMap = new LinkedHashMap<String, DictFightSoulPositionCond>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFightSoulPositionCond";
		sb.append(className + "=" + "{}\n");
		for(DictFightSoulPositionCond obj : dictFightSoulPositionCondList){
			dictFightSoulPositionCondMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFightSoulPositionCond[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFightSoulPositionCond.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFightSoulPositionCondMap=dictFightSoulPositionCondMap;
	}

	public static void dictActivitySignInUtil (int pd) throws Exception{
		List<DictActivitySignIn> dictActivitySignInList = getDictActivitySignInDAL().getList("", 0);
		DictList.dictActivitySignInList = dictActivitySignInList;
		Map<String, DictActivitySignIn> dictActivitySignInMap = new LinkedHashMap<String, DictActivitySignIn>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivitySignIn";
		sb.append(className + "=" + "{}\n");
		for(DictActivitySignIn obj : dictActivitySignInList){
			dictActivitySignInMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivitySignIn[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivitySignIn.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivitySignInMap=dictActivitySignInMap;
	}

	public static void dictUnionBoxUtil (int pd) throws Exception{
		List<DictUnionBox> dictUnionBoxList = getDictUnionBoxDAL().getList("", 0);
		DictList.dictUnionBoxList = dictUnionBoxList;
		Map<String, DictUnionBox> dictUnionBoxMap = new LinkedHashMap<String, DictUnionBox>();
		StringBuffer sb = new StringBuffer();
		String className = "DictUnionBox";
		sb.append(className + "=" + "{}\n");
		for(DictUnionBox obj : dictUnionBoxList){
			dictUnionBoxMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictUnionBox[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictUnionBox.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictUnionBoxMap=dictUnionBoxMap;
	}

	public static void dictManualSkillUtil (int pd) throws Exception{
		List<DictManualSkill> dictManualSkillList = getDictManualSkillDAL().getList("", 0);
		DictList.dictManualSkillList = dictManualSkillList;
		Map<String, DictManualSkill> dictManualSkillMap = new LinkedHashMap<String, DictManualSkill>();
		StringBuffer sb = new StringBuffer();
		String className = "DictManualSkill";
		sb.append(className + "=" + "{}\n");
		for(DictManualSkill obj : dictManualSkillList){
			dictManualSkillMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictManualSkill[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictManualSkill.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictManualSkillMap=dictManualSkillMap;
	}

	public static void dictCardExpAddUtil (int pd) throws Exception{
		List<DictCardExpAdd> dictCardExpAddList = getDictCardExpAddDAL().getList("", 0);
		DictList.dictCardExpAddList = dictCardExpAddList;
		Map<String, DictCardExpAdd> dictCardExpAddMap = new LinkedHashMap<String, DictCardExpAdd>();
		StringBuffer sb = new StringBuffer();
		String className = "DictCardExpAdd";
		sb.append(className + "=" + "{}\n");
		for(DictCardExpAdd obj : dictCardExpAddList){
			dictCardExpAddMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictCardExpAdd[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictCardExpAdd.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictCardExpAddMap=dictCardExpAddMap;
	}

	public static void dictVIPUtil (int pd) throws Exception{
		List<DictVIP> dictVIPList = getDictVIPDAL().getList("", 0);
		DictList.dictVIPList = dictVIPList;
		Map<String, DictVIP> dictVIPMap = new LinkedHashMap<String, DictVIP>();
		StringBuffer sb = new StringBuffer();
		String className = "DictVIP";
		sb.append(className + "=" + "{}\n");
		for(DictVIP obj : dictVIPList){
			dictVIPMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictVIP[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictVIP.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictVIPMap=dictVIPMap;
	}

	public static void dictSysConfigStrUtil (int pd) throws Exception{
		List<DictSysConfigStr> dictSysConfigStrList = getDictSysConfigStrDAL().getList("", 0);
		DictList.dictSysConfigStrList = dictSysConfigStrList;
		Map<String, DictSysConfigStr> dictSysConfigStrMap = new LinkedHashMap<String, DictSysConfigStr>();
		StringBuffer sb = new StringBuffer();
		String className = "DictSysConfigStr";
		sb.append(className + "=" + "{}\n");
		for(DictSysConfigStr obj : dictSysConfigStrList){
			dictSysConfigStrMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictSysConfigStr[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictSysConfigStr.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictSysConfigStrMap=dictSysConfigStrMap;
	}

	public static void dictActivityAllPeapleWealUtil (int pd) throws Exception{
		List<DictActivityAllPeapleWeal> dictActivityAllPeapleWealList = getDictActivityAllPeapleWealDAL().getList("", 0);
		DictList.dictActivityAllPeapleWealList = dictActivityAllPeapleWealList;
		Map<String, DictActivityAllPeapleWeal> dictActivityAllPeapleWealMap = new LinkedHashMap<String, DictActivityAllPeapleWeal>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityAllPeapleWeal";
		sb.append(className + "=" + "{}\n");
		for(DictActivityAllPeapleWeal obj : dictActivityAllPeapleWealList){
			dictActivityAllPeapleWealMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityAllPeapleWeal[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityAllPeapleWeal.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityAllPeapleWealMap=dictActivityAllPeapleWealMap;
	}

	public static void dictAuctionShopUtil (int pd) throws Exception{
		List<DictAuctionShop> dictAuctionShopList = getDictAuctionShopDAL().getList("", 0);
		DictList.dictAuctionShopList = dictAuctionShopList;
		Map<String, DictAuctionShop> dictAuctionShopMap = new LinkedHashMap<String, DictAuctionShop>();
		StringBuffer sb = new StringBuffer();
		String className = "DictAuctionShop";
		sb.append(className + "=" + "{}\n");
		for(DictAuctionShop obj : dictAuctionShopList){
			dictAuctionShopMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictAuctionShop[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictAuctionShop.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictAuctionShopMap=dictAuctionShopMap;
	}

	public static void dictActivityPrivateSaleUtil (int pd) throws Exception{
		List<DictActivityPrivateSale> dictActivityPrivateSaleList = getDictActivityPrivateSaleDAL().getList("", 0);
		DictList.dictActivityPrivateSaleList = dictActivityPrivateSaleList;
		Map<String, DictActivityPrivateSale> dictActivityPrivateSaleMap = new LinkedHashMap<String, DictActivityPrivateSale>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityPrivateSale";
		sb.append(className + "=" + "{}\n");
		for(DictActivityPrivateSale obj : dictActivityPrivateSaleList){
			dictActivityPrivateSaleMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityPrivateSale[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityPrivateSale.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityPrivateSaleMap=dictActivityPrivateSaleMap;
	}

	public static void dictCardBasePropUtil (int pd) throws Exception{
		List<DictCardBaseProp> dictCardBasePropList = getDictCardBasePropDAL().getList("", 0);
		DictList.dictCardBasePropList = dictCardBasePropList;
		Map<String, DictCardBaseProp> dictCardBasePropMap = new LinkedHashMap<String, DictCardBaseProp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictCardBaseProp";
		sb.append(className + "=" + "{}\n");
		for(DictCardBaseProp obj : dictCardBasePropList){
			dictCardBasePropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictCardBaseProp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictCardBaseProp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictCardBasePropMap=dictCardBasePropMap;
	}

	public static void dictTitleUtil (int pd) throws Exception{
		List<DictTitle> dictTitleList = getDictTitleDAL().getList("", 0);
		DictList.dictTitleList = dictTitleList;
		Map<String, DictTitle> dictTitleMap = new LinkedHashMap<String, DictTitle>();
		StringBuffer sb = new StringBuffer();
		String className = "DictTitle";
		sb.append(className + "=" + "{}\n");
		for(DictTitle obj : dictTitleList){
			dictTitleMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictTitle[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictTitle.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictTitleMap=dictTitleMap;
	}

	public static void dictActivityTreasuresUtil (int pd) throws Exception{
		List<DictActivityTreasures> dictActivityTreasuresList = getDictActivityTreasuresDAL().getList("", 0);
		DictList.dictActivityTreasuresList = dictActivityTreasuresList;
		Map<String, DictActivityTreasures> dictActivityTreasuresMap = new LinkedHashMap<String, DictActivityTreasures>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityTreasures";
		sb.append(className + "=" + "{}\n");
		for(DictActivityTreasures obj : dictActivityTreasuresList){
			dictActivityTreasuresMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityTreasures[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityTreasures.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityTreasuresMap=dictActivityTreasuresMap;
	}

	public static void sysCdKeyTypeUtil (int pd) throws Exception{
		List<SysCdKeyType> sysCdKeyTypeList = getSysCdKeyTypeDAL().getList("", 0);
		DictList.sysCdKeyTypeList = sysCdKeyTypeList;
		Map<String, SysCdKeyType> sysCdKeyTypeMap = new LinkedHashMap<String, SysCdKeyType>();
		StringBuffer sb = new StringBuffer();
		String className = "SysCdKeyType";
		sb.append(className + "=" + "{}\n");
		for(SysCdKeyType obj : sysCdKeyTypeList){
			sysCdKeyTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "SysCdKeyType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,SysCdKeyType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.sysCdKeyTypeMap=sysCdKeyTypeMap;
	}

	public static void dictActivityBannerUtil (int pd) throws Exception{
		List<DictActivityBanner> dictActivityBannerList = getDictActivityBannerDAL().getList("", 0);
		DictList.dictActivityBannerList = dictActivityBannerList;
		Map<String, DictActivityBanner> dictActivityBannerMap = new LinkedHashMap<String, DictActivityBanner>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityBanner";
		sb.append(className + "=" + "{}\n");
		for(DictActivityBanner obj : dictActivityBannerList){
			dictActivityBannerMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityBanner[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityBanner.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityBannerMap=dictActivityBannerMap;
	}

	public static void sysActivityUtil (int pd) throws Exception{
		List<SysActivity> sysActivityList = getSysActivityDAL().getList("", 0);
		DictList.sysActivityList = sysActivityList;
		Map<String, SysActivity> sysActivityMap = new LinkedHashMap<String, SysActivity>();
		StringBuffer sb = new StringBuffer();
		String className = "SysActivity";
		sb.append(className + "=" + "{}\n");
		for(SysActivity obj : sysActivityList){
			sysActivityMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "SysActivity[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,SysActivity.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.sysActivityMap=sysActivityMap;
	}

	public static void dictArenaDropUtil (int pd) throws Exception{
		List<DictArenaDrop> dictArenaDropList = getDictArenaDropDAL().getList("", 0);
		DictList.dictArenaDropList = dictArenaDropList;
		Map<String, DictArenaDrop> dictArenaDropMap = new LinkedHashMap<String, DictArenaDrop>();
		StringBuffer sb = new StringBuffer();
		String className = "DictArenaDrop";
		sb.append(className + "=" + "{}\n");
		for(DictArenaDrop obj : dictArenaDropList){
			dictArenaDropMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictArenaDrop[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictArenaDrop.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictArenaDropMap=dictArenaDropMap;
	}

	public static void dictRecruitCardUtil (int pd) throws Exception{
		List<DictRecruitCard> dictRecruitCardList = getDictRecruitCardDAL().getList("", 0);
		DictList.dictRecruitCardList = dictRecruitCardList;
		Map<String, DictRecruitCard> dictRecruitCardMap = new LinkedHashMap<String, DictRecruitCard>();
		StringBuffer sb = new StringBuffer();
		String className = "DictRecruitCard";
		sb.append(className + "=" + "{}\n");
		for(DictRecruitCard obj : dictRecruitCardList){
			dictRecruitCardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictRecruitCard[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictRecruitCard.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictRecruitCardMap=dictRecruitCardMap;
	}

	public static void dictWingLuckUtil (int pd) throws Exception{
		List<DictWingLuck> dictWingLuckList = getDictWingLuckDAL().getList("", 0);
		DictList.dictWingLuckList = dictWingLuckList;
		Map<String, DictWingLuck> dictWingLuckMap = new LinkedHashMap<String, DictWingLuck>();
		StringBuffer sb = new StringBuffer();
		String className = "DictWingLuck";
		sb.append(className + "=" + "{}\n");
		for(DictWingLuck obj : dictWingLuckList){
			dictWingLuckMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictWingLuck[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictWingLuck.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictWingLuckMap=dictWingLuckMap;
	}

	public static void dictActivityStarStoreUtil (int pd) throws Exception{
		List<DictActivityStarStore> dictActivityStarStoreList = getDictActivityStarStoreDAL().getList("", 0);
		DictList.dictActivityStarStoreList = dictActivityStarStoreList;
		Map<String, DictActivityStarStore> dictActivityStarStoreMap = new LinkedHashMap<String, DictActivityStarStore>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityStarStore";
		sb.append(className + "=" + "{}\n");
		for(DictActivityStarStore obj : dictActivityStarStoreList){
			dictActivityStarStoreMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityStarStore[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityStarStore.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityStarStoreMap=dictActivityStarStoreMap;
	}

	public static void dictEquipSuitUtil (int pd) throws Exception{
		List<DictEquipSuit> dictEquipSuitList = getDictEquipSuitDAL().getList("", 0);
		DictList.dictEquipSuitList = dictEquipSuitList;
		Map<String, DictEquipSuit> dictEquipSuitMap = new LinkedHashMap<String, DictEquipSuit>();
		StringBuffer sb = new StringBuffer();
		String className = "DictEquipSuit";
		sb.append(className + "=" + "{}\n");
		for(DictEquipSuit obj : dictEquipSuitList){
			dictEquipSuitMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictEquipSuit[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictEquipSuit.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictEquipSuitMap=dictEquipSuitMap;
	}

	public static void dictCoefficientUtil (int pd) throws Exception{
		List<DictCoefficient> dictCoefficientList = getDictCoefficientDAL().getList("", 0);
		DictList.dictCoefficientList = dictCoefficientList;
		Map<String, DictCoefficient> dictCoefficientMap = new LinkedHashMap<String, DictCoefficient>();
		StringBuffer sb = new StringBuffer();
		String className = "DictCoefficient";
		sb.append(className + "=" + "{}\n");
		for(DictCoefficient obj : dictCoefficientList){
			dictCoefficientMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictCoefficient[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictCoefficient.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictCoefficientMap=dictCoefficientMap;
	}

	public static void dictAuctionHJYUtil (int pd) throws Exception{
		List<DictAuctionHJY> dictAuctionHJYList = getDictAuctionHJYDAL().getList("", 0);
		DictList.dictAuctionHJYList = dictAuctionHJYList;
		Map<String, DictAuctionHJY> dictAuctionHJYMap = new LinkedHashMap<String, DictAuctionHJY>();
		StringBuffer sb = new StringBuffer();
		String className = "DictAuctionHJY";
		sb.append(className + "=" + "{}\n");
		for(DictAuctionHJY obj : dictAuctionHJYList){
			dictAuctionHJYMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictAuctionHJY[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictAuctionHJY.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictAuctionHJYMap=dictAuctionHJYMap;
	}

	public static void dictConstellUtil (int pd) throws Exception{
		List<DictConstell> dictConstellList = getDictConstellDAL().getList("", 0);
		DictList.dictConstellList = dictConstellList;
		Map<String, DictConstell> dictConstellMap = new LinkedHashMap<String, DictConstell>();
		StringBuffer sb = new StringBuffer();
		String className = "DictConstell";
		sb.append(className + "=" + "{}\n");
		for(DictConstell obj : dictConstellList){
			dictConstellMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictConstell[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictConstell.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictConstellMap=dictConstellMap;
	}

	public static void sysFilterWordsUtil (int pd) throws Exception{
		List<SysFilterWords> sysFilterWordsList = getSysFilterWordsDAL().getList("", 0);
		DictList.sysFilterWordsList = sysFilterWordsList;
		Map<String, SysFilterWords> sysFilterWordsMap = new LinkedHashMap<String, SysFilterWords>();
		StringBuffer sb = new StringBuffer();
		String className = "SysFilterWords";
		sb.append(className + "=" + "{}\n");
		for(SysFilterWords obj : sysFilterWordsList){
			sysFilterWordsMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "SysFilterWords[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,SysFilterWords.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.sysFilterWordsMap=sysFilterWordsMap;
	}

	public static void dictFightSoulUtil (int pd) throws Exception{
		List<DictFightSoul> dictFightSoulList = getDictFightSoulDAL().getList("", 0);
		DictList.dictFightSoulList = dictFightSoulList;
		Map<String, DictFightSoul> dictFightSoulMap = new LinkedHashMap<String, DictFightSoul>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFightSoul";
		sb.append(className + "=" + "{}\n");
		for(DictFightSoul obj : dictFightSoulList){
			dictFightSoulMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFightSoul[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFightSoul.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFightSoulMap=dictFightSoulMap;
	}

	public static void dictChapterActivityVipUtil (int pd) throws Exception{
		List<DictChapterActivityVip> dictChapterActivityVipList = getDictChapterActivityVipDAL().getList("", 0);
		DictList.dictChapterActivityVipList = dictChapterActivityVipList;
		Map<String, DictChapterActivityVip> dictChapterActivityVipMap = new LinkedHashMap<String, DictChapterActivityVip>();
		StringBuffer sb = new StringBuffer();
		String className = "DictChapterActivityVip";
		sb.append(className + "=" + "{}\n");
		for(DictChapterActivityVip obj : dictChapterActivityVipList){
			dictChapterActivityVipMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictChapterActivityVip[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictChapterActivityVip.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictChapterActivityVipMap=dictChapterActivityVipMap;
	}

	public static void dictMagicUtil (int pd) throws Exception{
		List<DictMagic> dictMagicList = getDictMagicDAL().getList("", 0);
		DictList.dictMagicList = dictMagicList;
		Map<String, DictMagic> dictMagicMap = new LinkedHashMap<String, DictMagic>();
		StringBuffer sb = new StringBuffer();
		String className = "DictMagic";
		sb.append(className + "=" + "{}\n");
		for(DictMagic obj : dictMagicList){
			dictMagicMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictMagic[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictMagic.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictMagicMap=dictMagicMap;
	}

	public static void dictDailyTaskUtil (int pd) throws Exception{
		List<DictDailyTask> dictDailyTaskList = getDictDailyTaskDAL().getList("", 0);
		DictList.dictDailyTaskList = dictDailyTaskList;
		Map<String, DictDailyTask> dictDailyTaskMap = new LinkedHashMap<String, DictDailyTask>();
		StringBuffer sb = new StringBuffer();
		String className = "DictDailyTask";
		sb.append(className + "=" + "{}\n");
		for(DictDailyTask obj : dictDailyTaskList){
			dictDailyTaskMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictDailyTask[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictDailyTask.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictDailyTaskMap=dictDailyTaskMap;
	}

	public static void dictYFireUtil (int pd) throws Exception{
		List<DictYFire> dictYFireList = getDictYFireDAL().getList("", 0);
		DictList.dictYFireList = dictYFireList;
		Map<String, DictYFire> dictYFireMap = new LinkedHashMap<String, DictYFire>();
		StringBuffer sb = new StringBuffer();
		String className = "DictYFire";
		sb.append(className + "=" + "{}\n");
		for(DictYFire obj : dictYFireList){
			dictYFireMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictYFire[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictYFire.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictYFireMap=dictYFireMap;
	}

	public static void dictTryToPracticeTypeUtil (int pd) throws Exception{
		List<DictTryToPracticeType> dictTryToPracticeTypeList = getDictTryToPracticeTypeDAL().getList("", 0);
		DictList.dictTryToPracticeTypeList = dictTryToPracticeTypeList;
		Map<String, DictTryToPracticeType> dictTryToPracticeTypeMap = new LinkedHashMap<String, DictTryToPracticeType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictTryToPracticeType";
		sb.append(className + "=" + "{}\n");
		for(DictTryToPracticeType obj : dictTryToPracticeTypeList){
			dictTryToPracticeTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictTryToPracticeType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictTryToPracticeType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictTryToPracticeTypeMap=dictTryToPracticeTypeMap;
	}

	public static void dictFireSkillChangeUtil (int pd) throws Exception{
		List<DictFireSkillChange> dictFireSkillChangeList = getDictFireSkillChangeDAL().getList("", 0);
		DictList.dictFireSkillChangeList = dictFireSkillChangeList;
		Map<String, DictFireSkillChange> dictFireSkillChangeMap = new LinkedHashMap<String, DictFireSkillChange>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFireSkillChange";
		sb.append(className + "=" + "{}\n");
		for(DictFireSkillChange obj : dictFireSkillChangeList){
			dictFireSkillChangeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFireSkillChange[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFireSkillChange.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFireSkillChangeMap=dictFireSkillChangeMap;
	}

	public static void dictHJYStoreUtil (int pd) throws Exception{
		List<DictHJYStore> dictHJYStoreList = getDictHJYStoreDAL().getList("", 0);
		DictList.dictHJYStoreList = dictHJYStoreList;
		Map<String, DictHJYStore> dictHJYStoreMap = new LinkedHashMap<String, DictHJYStore>();
		StringBuffer sb = new StringBuffer();
		String className = "DictHJYStore";
		sb.append(className + "=" + "{}\n");
		for(DictHJYStore obj : dictHJYStoreList){
			dictHJYStoreMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictHJYStore[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictHJYStore.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictHJYStoreMap=dictHJYStoreMap;
	}

	public static void dictActivityMonthCardStoreTempUtil (int pd) throws Exception{
		List<DictActivityMonthCardStoreTemp> dictActivityMonthCardStoreTempList = getDictActivityMonthCardStoreTempDAL().getList("", 0);
		DictList.dictActivityMonthCardStoreTempList = dictActivityMonthCardStoreTempList;
		Map<String, DictActivityMonthCardStoreTemp> dictActivityMonthCardStoreTempMap = new LinkedHashMap<String, DictActivityMonthCardStoreTemp>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityMonthCardStoreTemp";
		sb.append(className + "=" + "{}\n");
		for(DictActivityMonthCardStoreTemp obj : dictActivityMonthCardStoreTempList){
			dictActivityMonthCardStoreTempMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityMonthCardStoreTemp[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityMonthCardStoreTemp.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityMonthCardStoreTempMap=dictActivityMonthCardStoreTempMap;
	}

	public static void dictUnionStoreUtil (int pd) throws Exception{
		List<DictUnionStore> dictUnionStoreList = getDictUnionStoreDAL().getList("", 0);
		DictList.dictUnionStoreList = dictUnionStoreList;
		Map<String, DictUnionStore> dictUnionStoreMap = new LinkedHashMap<String, DictUnionStore>();
		StringBuffer sb = new StringBuffer();
		String className = "DictUnionStore";
		sb.append(className + "=" + "{}\n");
		for(DictUnionStore obj : dictUnionStoreList){
			dictUnionStoreMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictUnionStore[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictUnionStore.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictUnionStoreMap=dictUnionStoreMap;
	}

	public static void dictPillTypeUtil (int pd) throws Exception{
		List<DictPillType> dictPillTypeList = getDictPillTypeDAL().getList("", 0);
		DictList.dictPillTypeList = dictPillTypeList;
		Map<String, DictPillType> dictPillTypeMap = new LinkedHashMap<String, DictPillType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPillType";
		sb.append(className + "=" + "{}\n");
		for(DictPillType obj : dictPillTypeList){
			dictPillTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPillType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPillType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPillTypeMap=dictPillTypeMap;
	}

	public static void dictWorldBossStoreUtil (int pd) throws Exception{
		List<DictWorldBossStore> dictWorldBossStoreList = getDictWorldBossStoreDAL().getList("", 0);
		DictList.dictWorldBossStoreList = dictWorldBossStoreList;
		Map<String, DictWorldBossStore> dictWorldBossStoreMap = new LinkedHashMap<String, DictWorldBossStore>();
		StringBuffer sb = new StringBuffer();
		String className = "DictWorldBossStore";
		sb.append(className + "=" + "{}\n");
		for(DictWorldBossStore obj : dictWorldBossStoreList){
			dictWorldBossStoreMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictWorldBossStore[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictWorldBossStore.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictWorldBossStoreMap=dictWorldBossStoreMap;
	}

	public static void dictFireSkillQualityUtil (int pd) throws Exception{
		List<DictFireSkillQuality> dictFireSkillQualityList = getDictFireSkillQualityDAL().getList("", 0);
		DictList.dictFireSkillQualityList = dictFireSkillQualityList;
		Map<String, DictFireSkillQuality> dictFireSkillQualityMap = new LinkedHashMap<String, DictFireSkillQuality>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFireSkillQuality";
		sb.append(className + "=" + "{}\n");
		for(DictFireSkillQuality obj : dictFireSkillQualityList){
			dictFireSkillQualityMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFireSkillQuality[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFireSkillQuality.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFireSkillQualityMap=dictFireSkillQualityMap;
	}

	public static void dictArenaConvertUtil (int pd) throws Exception{
		List<DictArenaConvert> dictArenaConvertList = getDictArenaConvertDAL().getList("", 0);
		DictList.dictArenaConvertList = dictArenaConvertList;
		Map<String, DictArenaConvert> dictArenaConvertMap = new LinkedHashMap<String, DictArenaConvert>();
		StringBuffer sb = new StringBuffer();
		String className = "DictArenaConvert";
		sb.append(className + "=" + "{}\n");
		for(DictArenaConvert obj : dictArenaConvertList){
			dictArenaConvertMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictArenaConvert[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictArenaConvert.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictArenaConvertMap=dictArenaConvertMap;
	}

	public static void dictFireUtil (int pd) throws Exception{
		List<DictFire> dictFireList = getDictFireDAL().getList("", 0);
		DictList.dictFireList = dictFireList;
		Map<String, DictFire> dictFireMap = new LinkedHashMap<String, DictFire>();
		StringBuffer sb = new StringBuffer();
		String className = "DictFire";
		sb.append(className + "=" + "{}\n");
		for(DictFire obj : dictFireList){
			dictFireMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictFire[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictFire.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictFireMap=dictFireMap;
	}

	public static void dictThingExtendUtil (int pd) throws Exception{
		List<DictThingExtend> dictThingExtendList = getDictThingExtendDAL().getList("", 0);
		DictList.dictThingExtendList = dictThingExtendList;
		Map<String, DictThingExtend> dictThingExtendMap = new LinkedHashMap<String, DictThingExtend>();
		StringBuffer sb = new StringBuffer();
		String className = "DictThingExtend";
		sb.append(className + "=" + "{}\n");
		for(DictThingExtend obj : dictThingExtendList){
			dictThingExtendMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictThingExtend[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictThingExtend.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictThingExtendMap=dictThingExtendMap;
	}

	public static void dictWingUtil (int pd) throws Exception{
		List<DictWing> dictWingList = getDictWingDAL().getList("", 0);
		DictList.dictWingList = dictWingList;
		Map<String, DictWing> dictWingMap = new LinkedHashMap<String, DictWing>();
		StringBuffer sb = new StringBuffer();
		String className = "DictWing";
		sb.append(className + "=" + "{}\n");
		for(DictWing obj : dictWingList){
			dictWingMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictWing[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictWing.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictWingMap=dictWingMap;
	}

	public static void dictBarrierDropTypeUtil (int pd) throws Exception{
		List<DictBarrierDropType> dictBarrierDropTypeList = getDictBarrierDropTypeDAL().getList("", 0);
		DictList.dictBarrierDropTypeList = dictBarrierDropTypeList;
		Map<String, DictBarrierDropType> dictBarrierDropTypeMap = new LinkedHashMap<String, DictBarrierDropType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictBarrierDropType";
		sb.append(className + "=" + "{}\n");
		for(DictBarrierDropType obj : dictBarrierDropTypeList){
			dictBarrierDropTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictBarrierDropType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictBarrierDropType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictBarrierDropTypeMap=dictBarrierDropTypeMap;
	}

	public static void dictActivityHolidayUtil (int pd) throws Exception{
		List<DictActivityHoliday> dictActivityHolidayList = getDictActivityHolidayDAL().getList("", 0);
		DictList.dictActivityHolidayList = dictActivityHolidayList;
		Map<String, DictActivityHoliday> dictActivityHolidayMap = new LinkedHashMap<String, DictActivityHoliday>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityHoliday";
		sb.append(className + "=" + "{}\n");
		for(DictActivityHoliday obj : dictActivityHolidayList){
			dictActivityHolidayMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityHoliday[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityHoliday.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityHolidayMap=dictActivityHolidayMap;
	}

	public static void dictEquipQualityUtil (int pd) throws Exception{
		List<DictEquipQuality> dictEquipQualityList = getDictEquipQualityDAL().getList("", 0);
		DictList.dictEquipQualityList = dictEquipQualityList;
		Map<String, DictEquipQuality> dictEquipQualityMap = new LinkedHashMap<String, DictEquipQuality>();
		StringBuffer sb = new StringBuffer();
		String className = "DictEquipQuality";
		sb.append(className + "=" + "{}\n");
		for(DictEquipQuality obj : dictEquipQualityList){
			dictEquipQualityMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictEquipQuality[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictEquipQuality.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictEquipQualityMap=dictEquipQualityMap;
	}

	public static void dictWorldBossUtil (int pd) throws Exception{
		List<DictWorldBoss> dictWorldBossList = getDictWorldBossDAL().getList("", 0);
		DictList.dictWorldBossList = dictWorldBossList;
		Map<String, DictWorldBoss> dictWorldBossMap = new LinkedHashMap<String, DictWorldBoss>();
		StringBuffer sb = new StringBuffer();
		String className = "DictWorldBoss";
		sb.append(className + "=" + "{}\n");
		for(DictWorldBoss obj : dictWorldBossList){
			dictWorldBossMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictWorldBoss[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictWorldBoss.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictWorldBossMap=dictWorldBossMap;
	}

	public static void dictWingStrengthenUtil (int pd) throws Exception{
		List<DictWingStrengthen> dictWingStrengthenList = getDictWingStrengthenDAL().getList("", 0);
		DictList.dictWingStrengthenList = dictWingStrengthenList;
		Map<String, DictWingStrengthen> dictWingStrengthenMap = new LinkedHashMap<String, DictWingStrengthen>();
		StringBuffer sb = new StringBuffer();
		String className = "DictWingStrengthen";
		sb.append(className + "=" + "{}\n");
		for(DictWingStrengthen obj : dictWingStrengthenList){
			dictWingStrengthenMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictWingStrengthen[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictWingStrengthen.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictWingStrengthenMap=dictWingStrengthenMap;
	}

	public static void dictTryToPracticeUtil (int pd) throws Exception{
		List<DictTryToPractice> dictTryToPracticeList = getDictTryToPracticeDAL().getList("", 0);
		DictList.dictTryToPracticeList = dictTryToPracticeList;
		Map<String, DictTryToPractice> dictTryToPracticeMap = new LinkedHashMap<String, DictTryToPractice>();
		StringBuffer sb = new StringBuffer();
		String className = "DictTryToPractice";
		sb.append(className + "=" + "{}\n");
		for(DictTryToPractice obj : dictTryToPracticeList){
			dictTryToPracticeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictTryToPractice[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictTryToPractice.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictTryToPracticeMap=dictTryToPracticeMap;
	}

	public static void dictSysConfigUtil (int pd) throws Exception{
		List<DictSysConfig> dictSysConfigList = getDictSysConfigDAL().getList("", 0);
		DictList.dictSysConfigList = dictSysConfigList;
		Map<String, DictSysConfig> dictSysConfigMap = new LinkedHashMap<String, DictSysConfig>();
		StringBuffer sb = new StringBuffer();
		String className = "DictSysConfig";
		sb.append(className + "=" + "{}\n");
		for(DictSysConfig obj : dictSysConfigList){
			dictSysConfigMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictSysConfig[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictSysConfig.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictSysConfigMap=dictSysConfigMap;
	}

	public static void dictThingUtil (int pd) throws Exception{
		List<DictThing> dictThingList = getDictThingDAL().getList("", 0);
		DictList.dictThingList = dictThingList;
		Map<String, DictThing> dictThingMap = new LinkedHashMap<String, DictThing>();
		StringBuffer sb = new StringBuffer();
		String className = "DictThing";
		sb.append(className + "=" + "{}\n");
		for(DictThing obj : dictThingList){
			dictThingMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictThing[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictThing.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictThingMap=dictThingMap;
	}

	public static void dictTableTypeUtil (int pd) throws Exception{
		List<DictTableType> dictTableTypeList = getDictTableTypeDAL().getList("", 0);
		DictList.dictTableTypeList = dictTableTypeList;
		Map<String, DictTableType> dictTableTypeMap = new LinkedHashMap<String, DictTableType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictTableType";
		sb.append(className + "=" + "{}\n");
		for(DictTableType obj : dictTableTypeList){
			dictTableTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictTableType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictTableType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictTableTypeMap=dictTableTypeMap;
	}

	public static void dictDantaMonsterUtil (int pd) throws Exception{
		List<DictDantaMonster> dictDantaMonsterList = getDictDantaMonsterDAL().getList("", 0);
		DictList.dictDantaMonsterList = dictDantaMonsterList;
		Map<String, DictDantaMonster> dictDantaMonsterMap = new LinkedHashMap<String, DictDantaMonster>();
		StringBuffer sb = new StringBuffer();
		String className = "DictDantaMonster";
		sb.append(className + "=" + "{}\n");
		for(DictDantaMonster obj : dictDantaMonsterList){
			dictDantaMonsterMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictDantaMonster[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictDantaMonster.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictDantaMonsterMap=dictDantaMonsterMap;
	}

	public static void dictBeautyCardFightUtil (int pd) throws Exception{
		List<DictBeautyCardFight> dictBeautyCardFightList = getDictBeautyCardFightDAL().getList("", 0);
		DictList.dictBeautyCardFightList = dictBeautyCardFightList;
		Map<String, DictBeautyCardFight> dictBeautyCardFightMap = new LinkedHashMap<String, DictBeautyCardFight>();
		StringBuffer sb = new StringBuffer();
		String className = "DictBeautyCardFight";
		sb.append(className + "=" + "{}\n");
		for(DictBeautyCardFight obj : dictBeautyCardFightList){
			dictBeautyCardFightMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictBeautyCardFight[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictBeautyCardFight.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictBeautyCardFightMap=dictBeautyCardFightMap;
	}

	public static void dictActivityFundUtil (int pd) throws Exception{
		List<DictActivityFund> dictActivityFundList = getDictActivityFundDAL().getList("", 0);
		DictList.dictActivityFundList = dictActivityFundList;
		Map<String, DictActivityFund> dictActivityFundMap = new LinkedHashMap<String, DictActivityFund>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityFund";
		sb.append(className + "=" + "{}\n");
		for(DictActivityFund obj : dictActivityFundList){
			dictActivityFundMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityFund[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityFund.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityFundMap=dictActivityFundMap;
	}

	public static void dictPagodaStoreyUtil (int pd) throws Exception{
		List<DictPagodaStorey> dictPagodaStoreyList = getDictPagodaStoreyDAL().getList("", 0);
		DictList.dictPagodaStoreyList = dictPagodaStoreyList;
		Map<String, DictPagodaStorey> dictPagodaStoreyMap = new LinkedHashMap<String, DictPagodaStorey>();
		StringBuffer sb = new StringBuffer();
		String className = "DictPagodaStorey";
		sb.append(className + "=" + "{}\n");
		for(DictPagodaStorey obj : dictPagodaStoreyList){
			dictPagodaStoreyMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictPagodaStorey[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictPagodaStorey.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictPagodaStoreyMap=dictPagodaStoreyMap;
	}

	public static void dictEquipSuitReferUtil (int pd) throws Exception{
		List<DictEquipSuitRefer> dictEquipSuitReferList = getDictEquipSuitReferDAL().getList("", 0);
		DictList.dictEquipSuitReferList = dictEquipSuitReferList;
		Map<String, DictEquipSuitRefer> dictEquipSuitReferMap = new LinkedHashMap<String, DictEquipSuitRefer>();
		StringBuffer sb = new StringBuffer();
		String className = "DictEquipSuitRefer";
		sb.append(className + "=" + "{}\n");
		for(DictEquipSuitRefer obj : dictEquipSuitReferList){
			dictEquipSuitReferMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictEquipSuitRefer[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictEquipSuitRefer.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictEquipSuitReferMap=dictEquipSuitReferMap;
	}

	public static void dictMagicLevelUtil (int pd) throws Exception{
		List<DictMagicLevel> dictMagicLevelList = getDictMagicLevelDAL().getList("", 0);
		DictList.dictMagicLevelList = dictMagicLevelList;
		Map<String, DictMagicLevel> dictMagicLevelMap = new LinkedHashMap<String, DictMagicLevel>();
		StringBuffer sb = new StringBuffer();
		String className = "DictMagicLevel";
		sb.append(className + "=" + "{}\n");
		for(DictMagicLevel obj : dictMagicLevelList){
			dictMagicLevelMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictMagicLevel[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictMagicLevel.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictMagicLevelMap=dictMagicLevelMap;
	}

	public static void dictArenaAdvanceUtil (int pd) throws Exception{
		List<DictArenaAdvance> dictArenaAdvanceList = getDictArenaAdvanceDAL().getList("", 0);
		DictList.dictArenaAdvanceList = dictArenaAdvanceList;
		Map<String, DictArenaAdvance> dictArenaAdvanceMap = new LinkedHashMap<String, DictArenaAdvance>();
		StringBuffer sb = new StringBuffer();
		String className = "DictArenaAdvance";
		sb.append(className + "=" + "{}\n");
		for(DictArenaAdvance obj : dictArenaAdvanceList){
			dictArenaAdvanceMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictArenaAdvance[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictArenaAdvance.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictArenaAdvanceMap=dictArenaAdvanceMap;
	}

	public static void dictCardTypeUtil (int pd) throws Exception{
		List<DictCardType> dictCardTypeList = getDictCardTypeDAL().getList("", 0);
		DictList.dictCardTypeList = dictCardTypeList;
		Map<String, DictCardType> dictCardTypeMap = new LinkedHashMap<String, DictCardType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictCardType";
		sb.append(className + "=" + "{}\n");
		for(DictCardType obj : dictCardTypeList){
			dictCardTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictCardType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictCardType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictCardTypeMap=dictCardTypeMap;
	}

	public static void dictStrogerEquipUtil (int pd) throws Exception{
		List<DictStrogerEquip> dictStrogerEquipList = getDictStrogerEquipDAL().getList("", 0);
		DictList.dictStrogerEquipList = dictStrogerEquipList;
		Map<String, DictStrogerEquip> dictStrogerEquipMap = new LinkedHashMap<String, DictStrogerEquip>();
		StringBuffer sb = new StringBuffer();
		String className = "DictStrogerEquip";
		sb.append(className + "=" + "{}\n");
		for(DictStrogerEquip obj : dictStrogerEquipList){
			dictStrogerEquipMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictStrogerEquip[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictStrogerEquip.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictStrogerEquipMap=dictStrogerEquipMap;
	}

	public static void dictEquipStrengthenUtil (int pd) throws Exception{
		List<DictEquipStrengthen> dictEquipStrengthenList = getDictEquipStrengthenDAL().getList("", 0);
		DictList.dictEquipStrengthenList = dictEquipStrengthenList;
		Map<String, DictEquipStrengthen> dictEquipStrengthenMap = new LinkedHashMap<String, DictEquipStrengthen>();
		StringBuffer sb = new StringBuffer();
		String className = "DictEquipStrengthen";
		sb.append(className + "=" + "{}\n");
		for(DictEquipStrengthen obj : dictEquipStrengthenList){
			dictEquipStrengthenMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictEquipStrengthen[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictEquipStrengthen.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictEquipStrengthenMap=dictEquipStrengthenMap;
	}

	public static void dictActivityFlashSaleUtil (int pd) throws Exception{
		List<DictActivityFlashSale> dictActivityFlashSaleList = getDictActivityFlashSaleDAL().getList("", 0);
		DictList.dictActivityFlashSaleList = dictActivityFlashSaleList;
		Map<String, DictActivityFlashSale> dictActivityFlashSaleMap = new LinkedHashMap<String, DictActivityFlashSale>();
		StringBuffer sb = new StringBuffer();
		String className = "DictActivityFlashSale";
		sb.append(className + "=" + "{}\n");
		for(DictActivityFlashSale obj : dictActivityFlashSaleList){
			dictActivityFlashSaleMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictActivityFlashSale[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictActivityFlashSale.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictActivityFlashSaleMap=dictActivityFlashSaleMap;
	}

	public static void dictThingTypeUtil (int pd) throws Exception{
		List<DictThingType> dictThingTypeList = getDictThingTypeDAL().getList("", 0);
		DictList.dictThingTypeList = dictThingTypeList;
		Map<String, DictThingType> dictThingTypeMap = new LinkedHashMap<String, DictThingType>();
		StringBuffer sb = new StringBuffer();
		String className = "DictThingType";
		sb.append(className + "=" + "{}\n");
		for(DictThingType obj : dictThingTypeList){
			dictThingTypeMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictThingType[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictThingType.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictThingTypeMap=dictThingTypeMap;
	}

	public static void dictCardLuckUtil (int pd) throws Exception{
		List<DictCardLuck> dictCardLuckList = getDictCardLuckDAL().getList("", 0);
		DictList.dictCardLuckList = dictCardLuckList;
		Map<String, DictCardLuck> dictCardLuckMap = new LinkedHashMap<String, DictCardLuck>();
		StringBuffer sb = new StringBuffer();
		String className = "DictCardLuck";
		sb.append(className + "=" + "{}\n");
		for(DictCardLuck obj : dictCardLuckList){
			dictCardLuckMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictCardLuck[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictCardLuck.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictCardLuckMap=dictCardLuckMap;
	}

	public static void dictArenaRewardUtil (int pd) throws Exception{
		List<DictArenaReward> dictArenaRewardList = getDictArenaRewardDAL().getList("", 0);
		DictList.dictArenaRewardList = dictArenaRewardList;
		Map<String, DictArenaReward> dictArenaRewardMap = new LinkedHashMap<String, DictArenaReward>();
		StringBuffer sb = new StringBuffer();
		String className = "DictArenaReward";
		sb.append(className + "=" + "{}\n");
		for(DictArenaReward obj : dictArenaRewardList){
			dictArenaRewardMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictArenaReward[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictArenaReward.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictArenaRewardMap=dictArenaRewardMap;
	}

	public static void dictStarLevelUtil (int pd) throws Exception{
		List<DictStarLevel> dictStarLevelList = getDictStarLevelDAL().getList("", 0);
		DictList.dictStarLevelList = dictStarLevelList;
		Map<String, DictStarLevel> dictStarLevelMap = new LinkedHashMap<String, DictStarLevel>();
		StringBuffer sb = new StringBuffer();
		String className = "DictStarLevel";
		sb.append(className + "=" + "{}\n");
		for(DictStarLevel obj : dictStarLevelList){
			dictStarLevelMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictStarLevel[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictStarLevel.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictStarLevelMap=dictStarLevelMap;
	}

	public static void dictAchievementUtil (int pd) throws Exception{
		List<DictAchievement> dictAchievementList = getDictAchievementDAL().getList("", 0);
		DictList.dictAchievementList = dictAchievementList;
		Map<String, DictAchievement> dictAchievementMap = new LinkedHashMap<String, DictAchievement>();
		StringBuffer sb = new StringBuffer();
		String className = "DictAchievement";
		sb.append(className + "=" + "{}\n");
		for(DictAchievement obj : dictAchievementList){
			dictAchievementMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictAchievement[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictAchievement.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictAchievementMap=dictAchievementMap;
	}

	public static void dictLootNPCUtil (int pd) throws Exception{
		List<DictLootNPC> dictLootNPCList = getDictLootNPCDAL().getList("", 0);
		DictList.dictLootNPCList = dictLootNPCList;
		Map<String, DictLootNPC> dictLootNPCMap = new LinkedHashMap<String, DictLootNPC>();
		StringBuffer sb = new StringBuffer();
		String className = "DictLootNPC";
		sb.append(className + "=" + "{}\n");
		for(DictLootNPC obj : dictLootNPCList){
			dictLootNPCMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictLootNPC[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictLootNPC.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictLootNPCMap=dictLootNPCMap;
	}

	public static void dictEquipWashUtil (int pd) throws Exception{
		List<DictEquipWash> dictEquipWashList = getDictEquipWashDAL().getList("", 0);
		DictList.dictEquipWashList = dictEquipWashList;
		Map<String, DictEquipWash> dictEquipWashMap = new LinkedHashMap<String, DictEquipWash>();
		StringBuffer sb = new StringBuffer();
		String className = "DictEquipWash";
		sb.append(className + "=" + "{}\n");
		for(DictEquipWash obj : dictEquipWashList){
			dictEquipWashMap.put(obj.getId()+"", obj);
			Field[] field = obj.getClass().getDeclaredFields();
			String rowString = "={";
			String rowOut = "";
			for (int i = 0; i < field.length; i++) {
				String realFiledName = field[i].getName();
				String realFiledType = field[i].getType().getName();
				String filedName = field[i].getName();
				if (filedName.equals("index") || filedName.equals("result") || filedName.equals("version")) {
					continue;
				}
				filedName = filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1) .toUpperCase());
				Method m = obj.getClass().getMethod("get" + filedName);
				Object value =  m.invoke(obj) == null ? "" : m.invoke(obj);
				if (realFiledType.equals("java.lang.String")) {
					value = "\"" + value + "\"";
				}
				if (realFiledName.equals("id")) {
					rowOut = "DictEquipWash[\"" + value + "\"]";
				}
				String innerString = realFiledName + "=" + value + ",";
				rowString += innerString;
			}
			rowString = rowString.substring(0,rowString.length() - 1) + "}";
			rowString = rowOut + rowString;
			sb.append(rowString.toString());
			sb.append("\n");
		}
		if(pd == 1){
			FileUtil.writeContentToFile(path,DictEquipWash.class.getSimpleName()+".lua", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
		}
		DictMap.dictEquipWashMap=dictEquipWashMap;
	}

	public static void dictHoleConsumeGroupUtil (int pd) throws Exception{
		String sql = "select qualityId from Dict_Hole_Consume  group by qualityId";
		List<Map<String, Object>> listMaps = getDictHoleConsumeDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("qualityId") != null){
		int fieldIdName = (int)map.get("qualityId");
				List<DictHoleConsume> dictHoleConsumes = getDictHoleConsumeDAL().getList("qualityId ="+fieldIdName + "", 0);
				DictMapList.dictHoleConsumeMap.put(fieldIdName,dictHoleConsumes);
			}
		}
	}

	public static void dictAdvanceGroupUtil (int pd) throws Exception{
		String sql = "select cardId from Dict_Advance  group by cardId";
		List<Map<String, Object>> listMaps = getDictAdvanceDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("cardId") != null){
		int fieldIdName = (int)map.get("cardId");
				List<DictAdvance> dictAdvances = getDictAdvanceDAL().getList("cardId ="+fieldIdName + " order by qualityId, starLevelId", 0);
				DictMapList.dictAdvanceMap.put(fieldIdName,dictAdvances);
			}
		}
	}

	public static void dictTitleDetailGroupUtil (int pd) throws Exception{
		String sql = "select titleId from Dict_Title_Detail  group by titleId";
		List<Map<String, Object>> listMaps = getDictTitleDetailDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("titleId") != null){
		int fieldIdName = (int)map.get("titleId");
				List<DictTitleDetail> dictTitleDetails = getDictTitleDetailDAL().getList("titleId ="+fieldIdName + "", 0);
				DictMapList.dictTitleDetailMap.put(fieldIdName,dictTitleDetails);
			}
		}
	}

	public static void dictAcupointNodeGroupUtil (int pd) throws Exception{
		String sql = "select acupointId from Dict_AcupointNode  group by acupointId";
		List<Map<String, Object>> listMaps = getDictAcupointNodeDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("acupointId") != null){
		int fieldIdName = (int)map.get("acupointId");
				List<DictAcupointNode> dictAcupointNodes = getDictAcupointNodeDAL().getList("acupointId ="+fieldIdName + " order by tier, node", 0);
				DictMapList.dictAcupointNodeMap.put(fieldIdName,dictAcupointNodes);
			}
		}
	}

	public static void dictKungFuTierAddGroupUtil (int pd) throws Exception{
		String sql = "select kungFuId from Dict_KungFuTierAdd  group by kungFuId";
		List<Map<String, Object>> listMaps = getDictKungFuTierAddDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("kungFuId") != null){
		int fieldIdName = (int)map.get("kungFuId");
				List<DictKungFuTierAdd> dictKungFuTierAdds = getDictKungFuTierAddDAL().getList("kungFuId ="+fieldIdName + "", 0);
				DictMapList.dictKungFuTierAddMap.put(fieldIdName,dictKungFuTierAdds);
			}
		}
	}

	public static void dictFireGainGroupUtil (int pd) throws Exception{
		String sql = "select position from Dict_FireGain  group by position";
		List<Map<String, Object>> listMaps = getDictFireGainDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("position") != null){
		int fieldIdName = (int)map.get("position");
				List<DictFireGain> dictFireGains = getDictFireGainDAL().getList("position ="+fieldIdName + "", 0);
				DictMapList.dictFireGainMap.put(fieldIdName,dictFireGains);
			}
		}
	}

	public static void dictThingGroupUtil (int pd) throws Exception{
		String sql = "select thingTypeId from Dict_Thing  group by thingTypeId";
		List<Map<String, Object>> listMaps = getDictThingDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("thingTypeId") != null){
		int fieldIdName = (int)map.get("thingTypeId");
				List<DictThing> dictThings = getDictThingDAL().getList("thingTypeId ="+fieldIdName + "", 0);
				DictMapList.dictThingMap.put(fieldIdName,dictThings);
			}
		}
	}

	public static void dictPagodaDropGroupUtil (int pd) throws Exception{
		String sql = "select pagodaStoreyId from Dict_Pagoda_Drop  group by pagodaStoreyId";
		List<Map<String, Object>> listMaps = getDictPagodaDropDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("pagodaStoreyId") != null){
		int fieldIdName = (int)map.get("pagodaStoreyId");
				List<DictPagodaDrop> dictPagodaDrops = getDictPagodaDropDAL().getList("pagodaStoreyId ="+fieldIdName + "", 0);
				DictMapList.dictPagodaDropMap.put(fieldIdName,dictPagodaDrops);
			}
		}
	}

	public static void dictRecruitCardGroupUtil (int pd) throws Exception{
		String sql = "select recruitTypeId from Dict_Recruit_Card  group by recruitTypeId";
		List<Map<String, Object>> listMaps = getDictRecruitCardDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("recruitTypeId") != null){
		int fieldIdName = (int)map.get("recruitTypeId");
				List<DictRecruitCard> dictRecruitCards = getDictRecruitCardDAL().getList("recruitTypeId ="+fieldIdName + "", 0);
				DictMapList.dictRecruitCardMap.put(fieldIdName,dictRecruitCards);
			}
		}
	}

	public static void dictBarrierDropGroupUtil (int pd) throws Exception{
		String sql = "select barrierId from Dict_Barrier_Drop  group by barrierId";
		List<Map<String, Object>> listMaps = getDictBarrierDropDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("barrierId") != null){
		int fieldIdName = (int)map.get("barrierId");
				List<DictBarrierDrop> dictBarrierDrops = getDictBarrierDropDAL().getList("barrierId ="+fieldIdName + "", 0);
				DictMapList.dictBarrierDropMap.put(fieldIdName,dictBarrierDrops);
			}
		}
	}

	public static void dictBarrierGroupUtil (int pd) throws Exception{
		String sql = "select chapterId from Dict_Barrier  group by chapterId";
		List<Map<String, Object>> listMaps = getDictBarrierDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("chapterId") != null){
		int fieldIdName = (int)map.get("chapterId");
				List<DictBarrier> dictBarriers = getDictBarrierDAL().getList("chapterId ="+fieldIdName + " order by id", 0);
				DictMapList.dictBarrierMap.put(fieldIdName,dictBarriers);
			}
		}
	}

	public static void dictBarrierLevelGroupUtil (int pd) throws Exception{
		String sql = "select barrierId from Dict_Barrier_Level  group by barrierId";
		List<Map<String, Object>> listMaps = getDictBarrierLevelDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("barrierId") != null){
		int fieldIdName = (int)map.get("barrierId");
				List<DictBarrierLevel> dictBarrierLevels = getDictBarrierLevelDAL().getList("barrierId ="+fieldIdName + "", 0);
				DictMapList.dictBarrierLevelMap.put(fieldIdName,dictBarrierLevels);
			}
		}
	}

	public static void dictChapterGroupUtil (int pd) throws Exception{
		String sql = "select type from Dict_Chapter  group by type";
		List<Map<String, Object>> listMaps = getDictChapterDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("type") != null){
		int fieldIdName = (int)map.get("type");
				List<DictChapter> dictChapters = getDictChapterDAL().getList("type ="+fieldIdName + "", 0);
				DictMapList.dictChapterMap.put(fieldIdName,dictChapters);
			}
		}
	}

	public static void dictLootChipGroupUtil (int pd) throws Exception{
		String sql = "select qualityId from Dict_Loot_Chip  group by qualityId";
		List<Map<String, Object>> listMaps = getDictLootChipDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("qualityId") != null){
		int fieldIdName = (int)map.get("qualityId");
				List<DictLootChip> dictLootChips = getDictLootChipDAL().getList("qualityId ="+fieldIdName + " order by type", 0);
				DictMapList.dictLootChipMap.put(fieldIdName,dictLootChips);
			}
		}
	}

	public static void dictChipGroupUtil (int pd) throws Exception{
		String sql = "select skillOrKungFuId from Dict_Chip  group by skillOrKungFuId";
		List<Map<String, Object>> listMaps = getDictChipDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("skillOrKungFuId") != null){
		int fieldIdName = (int)map.get("skillOrKungFuId");
				List<DictChip> dictChips = getDictChipDAL().getList("skillOrKungFuId ="+fieldIdName + "", 0);
				DictMapList.dictChipMap.put(fieldIdName,dictChips);
			}
		}
	}

	public static void dictPillGroupUtil (int pd) throws Exception{
		String sql = "select pillTypeId from Dict_Pill  group by pillTypeId";
		List<Map<String, Object>> listMaps = getDictPillDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("pillTypeId") != null){
		int fieldIdName = (int)map.get("pillTypeId");
				List<DictPill> dictPills = getDictPillDAL().getList("pillTypeId ="+fieldIdName + "", 0);
				DictMapList.dictPillMap.put(fieldIdName,dictPills);
			}
		}
	}

	public static void dictRestoreGroupUtil (int pd) throws Exception{
		String sql = "select qualityId from Dict_Restore  group by qualityId";
		List<Map<String, Object>> listMaps = getDictRestoreDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("qualityId") != null){
		int fieldIdName = (int)map.get("qualityId");
				List<DictRestore> dictRestores = getDictRestoreDAL().getList("qualityId ="+fieldIdName + " order by starLevelId", 0);
				DictMapList.dictRestoreMap.put(fieldIdName,dictRestores);
			}
		}
	}

	public static void dictMagicLevelGroupUtil (int pd) throws Exception{
		String sql = "select type from Dict_Magic_Level  group by type";
		List<Map<String, Object>> listMaps = getDictMagicLevelDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("type") != null){
		int fieldIdName = (int)map.get("type");
				List<DictMagicLevel> dictMagicLevels = getDictMagicLevelDAL().getList("type ="+fieldIdName + " order by level", 0);
				DictMapList.dictMagicLevelMap.put(fieldIdName,dictMagicLevels);
			}
		}
	}

	public static void dictAchievementGroupUtil (int pd) throws Exception{
		String sql = "select achievementTypeId from Dict_Achievement  group by achievementTypeId";
		List<Map<String, Object>> listMaps = getDictAchievementDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("achievementTypeId") != null){
		int fieldIdName = (int)map.get("achievementTypeId");
				List<DictAchievement> dictAchievements = getDictAchievementDAL().getList("achievementTypeId ="+fieldIdName + " order by id", 0);
				DictMapList.dictAchievementMap.put(fieldIdName,dictAchievements);
			}
		}
	}

	public static void dictGenerBoxThingGroupUtil (int pd) throws Exception{
		String sql = "select type from Dict_Gener_BoxThing  group by type";
		List<Map<String, Object>> listMaps = getDictGenerBoxThingDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("type") != null){
		int fieldIdName = (int)map.get("type");
				List<DictGenerBoxThing> dictGenerBoxThings = getDictGenerBoxThingDAL().getList("type ="+fieldIdName + "", 0);
				DictMapList.dictGenerBoxThingMap.put(fieldIdName,dictGenerBoxThings);
			}
		}
	}

	public static void dictSpecialBoxThingGroupUtil (int pd) throws Exception{
		String sql = "select tableTypeId from Dict_Special_BoxThing  group by tableTypeId";
		List<Map<String, Object>> listMaps = getDictSpecialBoxThingDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("tableTypeId") != null){
		int fieldIdName = (int)map.get("tableTypeId");
				List<DictSpecialBoxThing> dictSpecialBoxThings = getDictSpecialBoxThingDAL().getList("tableTypeId ="+fieldIdName + "", 0);
				DictMapList.dictSpecialBoxThingMap.put(fieldIdName,dictSpecialBoxThings);
			}
		}
	}

	public static void dictCardGroupUtil (int pd) throws Exception{
		String sql = "select qualityId from Dict_Card  group by qualityId";
		List<Map<String, Object>> listMaps = getDictCardDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("qualityId") != null){
		int fieldIdName = (int)map.get("qualityId");
				List<DictCard> dictCards = getDictCardDAL().getList("qualityId ="+fieldIdName + "", 0);
				DictMapList.dictCardMap.put(fieldIdName,dictCards);
			}
		}
	}

	public static void dictEquipmentGroupUtil (int pd) throws Exception{
		String sql = "select equipQualityId from Dict_Equipment  group by equipQualityId";
		List<Map<String, Object>> listMaps = getDictEquipmentDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("equipQualityId") != null){
		int fieldIdName = (int)map.get("equipQualityId");
				List<DictEquipment> dictEquipments = getDictEquipmentDAL().getList("equipQualityId ="+fieldIdName + "", 0);
				DictMapList.dictEquipmentMap.put(fieldIdName,dictEquipments);
			}
		}
	}

	public static void dictMagicGroupUtil (int pd) throws Exception{
		String sql = "select type from Dict_Magic  group by type";
		List<Map<String, Object>> listMaps = getDictMagicDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("type") != null){
		int fieldIdName = (int)map.get("type");
				List<DictMagic> dictMagics = getDictMagicDAL().getList("type ="+fieldIdName + "", 0);
				DictMapList.dictMagicMap.put(fieldIdName,dictMagics);
			}
		}
	}

	public static void dictBarrierDropTypeGroupUtil (int pd) throws Exception{
		String sql = "select type from Dict_Barrier_Drop_Type  group by type";
		List<Map<String, Object>> listMaps = getDictBarrierDropTypeDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("type") != null){
		int fieldIdName = (int)map.get("type");
				List<DictBarrierDropType> dictBarrierDropTypes = getDictBarrierDropTypeDAL().getList("type ="+fieldIdName + "", 0);
				DictMapList.dictBarrierDropTypeMap.put(fieldIdName,dictBarrierDropTypes);
			}
		}
	}

	public static void dictRecruitSpecialCardGroupUtil (int pd) throws Exception{
		String sql = "select areaNo from Dict_Recruit_SpecialCard  group by areaNo";
		List<Map<String, Object>> listMaps = getDictRecruitSpecialCardDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("areaNo") != null){
		int fieldIdName = (int)map.get("areaNo");
				List<DictRecruitSpecialCard> dictRecruitSpecialCards = getDictRecruitSpecialCardDAL().getList("areaNo ="+fieldIdName + "", 0);
				DictMapList.dictRecruitSpecialCardMap.put(fieldIdName,dictRecruitSpecialCards);
			}
		}
	}

	public static void dictRecruitTimesGetGroupUtil (int pd) throws Exception{
		String sql = "select recruitTypeId from Dict_Recruit_TimesGet  group by recruitTypeId";
		List<Map<String, Object>> listMaps = getDictRecruitTimesGetDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("recruitTypeId") != null){
		int fieldIdName = (int)map.get("recruitTypeId");
				List<DictRecruitTimesGet> dictRecruitTimesGets = getDictRecruitTimesGetDAL().getList("recruitTypeId ="+fieldIdName + "", 0);
				DictMapList.dictRecruitTimesGetMap.put(fieldIdName,dictRecruitTimesGets);
			}
		}
	}

	public static void dictActivitySignInGroupUtil (int pd) throws Exception{
		String sql = "select month from Dict_Activity_SignIn  group by month";
		List<Map<String, Object>> listMaps = getDictActivitySignInDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("month") != null){
		int fieldIdName = (int)map.get("month");
				List<DictActivitySignIn> dictActivitySignIns = getDictActivitySignInDAL().getList("month ="+fieldIdName + " order by day", 0);
				DictMapList.dictActivitySignInMap.put(fieldIdName,dictActivitySignIns);
			}
		}
	}

	public static void dictActivityGrabTheHourGroupUtil (int pd) throws Exception{
		String sql = "select type from Dict_Activity_GrabTheHour  group by type";
		List<Map<String, Object>> listMaps = getDictActivityGrabTheHourDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("type") != null){
		int fieldIdName = (int)map.get("type");
				List<DictActivityGrabTheHour> dictActivityGrabTheHours = getDictActivityGrabTheHourDAL().getList("type ="+fieldIdName + "", 0);
				DictMapList.dictActivityGrabTheHourMap.put(fieldIdName,dictActivityGrabTheHours);
			}
		}
	}

	public static void dictActivityPrivateSaleGroupUtil (int pd) throws Exception{
		String sql = "select type from Dict_Activity_PrivateSale  group by type";
		List<Map<String, Object>> listMaps = getDictActivityPrivateSaleDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("type") != null){
		int fieldIdName = (int)map.get("type");
				List<DictActivityPrivateSale> dictActivityPrivateSales = getDictActivityPrivateSaleDAL().getList("type ="+fieldIdName + "", 0);
				DictMapList.dictActivityPrivateSaleMap.put(fieldIdName,dictActivityPrivateSales);
			}
		}
	}

	public static void dictActivityDailyDealsGroupUtil (int pd) throws Exception{
		String sql = "select day from Dict_Activity_DailyDeals  group by day";
		List<Map<String, Object>> listMaps = getDictActivityDailyDealsDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("day") != null){
		int fieldIdName = (int)map.get("day");
				List<DictActivityDailyDeals> dictActivityDailyDealss = getDictActivityDailyDealsDAL().getList("day ="+fieldIdName + "", 0);
				DictMapList.dictActivityDailyDealsMap.put(fieldIdName,dictActivityDailyDealss);
			}
		}
	}

	public static void dictActivityMonthCardStoreGroupUtil (int pd) throws Exception{
		String sql = "select type from Dict_Activity_MonthCardStore  group by type";
		List<Map<String, Object>> listMaps = getDictActivityMonthCardStoreDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("type") != null){
		int fieldIdName = (int)map.get("type");
				List<DictActivityMonthCardStore> dictActivityMonthCardStores = getDictActivityMonthCardStoreDAL().getList("type ="+fieldIdName + "", 0);
				DictMapList.dictActivityMonthCardStoreMap.put(fieldIdName,dictActivityMonthCardStores);
			}
		}
	}

	public static void dictEquipAdvanceGroupUtil (int pd) throws Exception{
		String sql = "select equipTypeId from Dict_Equip_Advance  group by equipTypeId";
		List<Map<String, Object>> listMaps = getDictEquipAdvanceDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("equipTypeId") != null){
		int fieldIdName = (int)map.get("equipTypeId");
				List<DictEquipAdvance> dictEquipAdvances = getDictEquipAdvanceDAL().getList("equipTypeId ="+fieldIdName + "", 0);
				DictMapList.dictEquipAdvanceMap.put(fieldIdName,dictEquipAdvances);
			}
		}
	}

	public static void dictFightSoulGroupUtil (int pd) throws Exception{
		String sql = "select fightSoulQualityId from Dict_FightSoul  group by fightSoulQualityId";
		List<Map<String, Object>> listMaps = getDictFightSoulDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("fightSoulQualityId") != null){
		int fieldIdName = (int)map.get("fightSoulQualityId");
				List<DictFightSoul> dictFightSouls = getDictFightSoulDAL().getList("fightSoulQualityId ="+fieldIdName + "", 0);
				DictMapList.dictFightSoulMap.put(fieldIdName,dictFightSouls);
			}
		}
	}

	public static void dictFightSoulUpgradeExpGroupUtil (int pd) throws Exception{
		String sql = "select fightSoulQualityId from Dict_FightSoul_UpgradeExp  group by fightSoulQualityId";
		List<Map<String, Object>> listMaps = getDictFightSoulUpgradeExpDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("fightSoulQualityId") != null){
		int fieldIdName = (int)map.get("fightSoulQualityId");
				List<DictFightSoulUpgradeExp> dictFightSoulUpgradeExps = getDictFightSoulUpgradeExpDAL().getList("fightSoulQualityId ="+fieldIdName + "", 0);
				DictMapList.dictFightSoulUpgradeExpMap.put(fieldIdName,dictFightSoulUpgradeExps);
			}
		}
	}

	public static void dictFightSoulUpgradePropGroupUtil (int pd) throws Exception{
		String sql = "select fightSoulId from Dict_FightSoul_UpgradeProp  group by fightSoulId";
		List<Map<String, Object>> listMaps = getDictFightSoulUpgradePropDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("fightSoulId") != null){
		int fieldIdName = (int)map.get("fightSoulId");
				List<DictFightSoulUpgradeProp> dictFightSoulUpgradeProps = getDictFightSoulUpgradePropDAL().getList("fightSoulId ="+fieldIdName + "", 0);
				DictMapList.dictFightSoulUpgradePropMap.put(fieldIdName,dictFightSoulUpgradeProps);
			}
		}
	}

	public static void dictMineBoxThingGroupUtil (int pd) throws Exception{
		String sql = "select type from Dict_Mine_BoxThing  group by type";
		List<Map<String, Object>> listMaps = getDictMineBoxThingDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("type") != null){
		int fieldIdName = (int)map.get("type");
				List<DictMineBoxThing> dictMineBoxThings = getDictMineBoxThingDAL().getList("type ="+fieldIdName + "", 0);
				DictMapList.dictMineBoxThingMap.put(fieldIdName,dictMineBoxThings);
			}
		}
	}

	public static void dictWingAdvanceGroupUtil (int pd) throws Exception{
		String sql = "select wingId from Dict_Wing_Advance  group by wingId";
		List<Map<String, Object>> listMaps = getDictWingAdvanceDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("wingId") != null){
		int fieldIdName = (int)map.get("wingId");
				List<DictWingAdvance> dictWingAdvances = getDictWingAdvanceDAL().getList("wingId ="+fieldIdName + "", 0);
				DictMapList.dictWingAdvanceMap.put(fieldIdName,dictWingAdvances);
			}
		}
	}

	public static void dictWingStrengthenGroupUtil (int pd) throws Exception{
		String sql = "select wingId from Dict_Wing_Strengthen  group by wingId";
		List<Map<String, Object>> listMaps = getDictWingStrengthenDAL().sqlHelper(sql);
		for(Map<String, Object> map : listMaps){
			if(map.get("wingId") != null){
		int fieldIdName = (int)map.get("wingId");
				List<DictWingStrengthen> dictWingStrengthens = getDictWingStrengthenDAL().getList("wingId ="+fieldIdName + "", 0);
				DictMapList.dictWingStrengthenMap.put(fieldIdName,dictWingStrengthens);
			}
		}
	}

}
