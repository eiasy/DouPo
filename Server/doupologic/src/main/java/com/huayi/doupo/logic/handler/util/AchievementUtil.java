package com.huayi.doupo.logic.handler.util;

import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictAchievement;
import com.huayi.doupo.base.model.DictEquipment;
import com.huayi.doupo.base.model.DictMagic;
import com.huayi.doupo.base.model.DictPill;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.DictTitleDetail;
import com.huayi.doupo.base.model.InstPlayerAchievement;
import com.huayi.doupo.base.model.InstPlayerAchievementValue;
import com.huayi.doupo.base.model.InstPlayerChapter;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticAchievementType;
import com.huayi.doupo.base.model.statics.StaticCustomDict;
import com.huayi.doupo.base.model.statics.StaticQuality;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.logic.util.MessageData;

public class AchievementUtil extends DALFactory{

	
	
	/**
	 * 更新成就
	 * @author hzw
	 * @date 2015-1-22上午10:01:07
	 * @param instPlayerId 玩家Id
	 * @param achievementTypeId 成就类型Id
	 * @param value 数据值（对应成就的条件）
	 * @param syncMsgData 同步数据
	 * @throws Exception
	 * @Description 整体规则当前有可领取的成就就不处理当前成就
	 */
	public static void updateAchievement (int instPlayerId, int achievementTypeId, int value,  MessageData syncMsgData, InstPlayerAchievement instPlayerAchievement) throws Exception{
		
		//等级成就
		if(instPlayerAchievement == null){
			instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + achievementTypeId, instPlayerId).get(0);
		}
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			int achievementId = instPlayerAchievement.getAchievementId();
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(achievementId + "");
			if(value >= ConvertUtil.toInt(dictAchievement.getProgress())){
				instPlayerAchievement.setAchievementId(dictAchievement.getNextId());
				instPlayerAchievement.setCanAchievementId(achievementId);
				getInstPlayerAchievementDAL().update(instPlayerAchievement, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerAchievement, instPlayerAchievement.getId(), instPlayerAchievement.getResult(), syncMsgData);
				
			}
		}

	}
	
	/**
	 * 更新玩家成就计数实例数据
	 * @author hzw
	 * @date 2015-1-21下午4:48:33
	 * @param instPlayerId 玩家Id
	 * @param achievementTypeId 成就类型Id
	 * @param valueType 用于区分数据值
	 * @param value 数据值（对应成就的条件）
	 * @param syncMsgData 同步数据
	 * @throws Exception
	 * @Description
	 */
	public static void updateAchievementValue(int instPlayerId, int achievementTypeId, int value,  MessageData syncMsgData) throws Exception{
		InstPlayerAchievementValue instPlayerAchievementValue = getInstPlayerAchievementValueDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + achievementTypeId, instPlayerId).get(0);
		if(achievementTypeId == StaticAchievementType.chapter || achievementTypeId == StaticAchievementType.hJYStore || achievementTypeId == StaticAchievementType.arena 
				|| achievementTypeId == StaticAchievementType.worldBoss || achievementTypeId == StaticAchievementType.addPill){
			instPlayerAchievementValue.setValue(instPlayerAchievementValue.getValue() + value);
		}else if(achievementTypeId == StaticAchievementType.pagoda || achievementTypeId == StaticAchievementType.strengthen || achievementTypeId == StaticAchievementType.addEquip 
				|| achievementTypeId == StaticAchievementType.inlay || achievementTypeId == StaticAchievementType.magic1 || achievementTypeId == StaticAchievementType.magic2 || achievementTypeId == StaticAchievementType.title1 
				|| achievementTypeId == StaticAchievementType.title5 || achievementTypeId == StaticAchievementType.advance || achievementTypeId == StaticAchievementType.purpleEquip){
			instPlayerAchievementValue.setValue(value);
		}
		getInstPlayerAchievementValueDAL().update(instPlayerAchievementValue, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerAchievementValue, instPlayerAchievementValue.getId(), instPlayerAchievementValue.getResult(), syncMsgData);
		
		//更新成就
		updateAchievement (instPlayerId, achievementTypeId, instPlayerAchievementValue.getValue(), syncMsgData, null);
	}
	
	/**
	 * 验证洗练成就(不要了)
	 * @author hzw
	 * @date 2015-1-24上午4:11:38
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void wash(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就（洗练）
	/*	InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.wash, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			int achievementValue = 0;  //用于洗练星计数
			List<InstPlayerWash> instPlayerWashList = getInstPlayerWashDAL().getListFromMoreTale(", Inst_Player_Equip b where a.instPlayerId = "+instPlayerId+" and a.instPlayerEquipId = b.id and b.instCardId != 0", instPlayerId);
			for(InstPlayerWash obj : instPlayerWashList){
				achievementValue += DictMap.dictEquipWashMap.get("" + obj.getEquipWashId()).getLevel();
			}
			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.wash, achievementValue, syncMsgData);
		}*/
	}
	
	/**
	 * 验证强化装备成就
	 * @author hzw
	 * @date 2015-1-24上午4:16:51
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void strengthen(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.strengthen, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			int achievementValue = 0;  //用于强化装备计数
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
			List<Long> listLong = getInstPlayerEquipDAL().getCounts(" where instPlayerId =  " + instPlayerId + " and instCardId != 0  and level >= " + condition + " GROUP BY instCardId");
			for(Long i : listLong){
				if(i == 4){
					achievementValue ++;
				}
			}
			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.strengthen, achievementValue, syncMsgData);
		}
	}
	
	/**
	 * 验证穿齐装备成就
	 * @author hzw
	 * @date 2015-1-23下午5:37:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addEquip(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就（洗练）
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.addEquip, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			int achievementValue = 0;  //用于强化装备计数
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
			List<Long> listLong = getInstPlayerEquipDAL().getCounts(" where instPlayerId =  " + instPlayerId + " and instCardId != 0  GROUP BY instCardId");
			for(Long i : listLong){
				if(i >= condition){
					achievementValue ++;
				}
			}
			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.addEquip, achievementValue, syncMsgData);
		}
	}
	
	/**
	 * 验证镶嵌魔核成就
	 * @author hzw
	 * @date 2015-1-26下午3:55:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void inlay(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就（洗练）
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.inlay, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
//			int achievementValue = 0;  //用于强化装备计数
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
//			String strWhere = "";
//			if(condition == 0){
//				strWhere = "b.level > " + condition;
//			}else{
//				strWhere = "b.level = " + condition;
//			}
//			List<Long> listLong = getInstEquipGemDAL().getCounts(" a,Dict_Thing b where a.instPlayerId = " + instPlayerId + " and a.thingId = b.id and a.thingId != 0 and "+ strWhere);
//			for(Long i : listLong){
//				achievementValue += i;
//			}
			
			int count = 0;
			String sql = "select thingId from Inst_Equip_Gem where instPlayerId = "+instPlayerId+" and thingId != 0";
			List<Map<String,Object>> thingIdList = getInstEquipGemDAL().sqlHelper(sql);
			for (Map<String,Object> innerMap : thingIdList) {
				int thingId = (int)(double)Double.valueOf(innerMap.get("thingId").toString());
				DictThing dictThingObj = DictMap.dictThingMap.get(thingId + "");
				if (dictThingObj != null) {
					if (condition == 0) {
						if (dictThingObj.getLevel() > condition) {
							count ++;
						}
					} else {
						if (dictThingObj.getLevel() == condition) {
							count ++;
						}
					}
				}
			}
			
			
			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.inlay, count, syncMsgData);
		}
	}
	
	/**
	 * 验证法宝成就
	 * @author hzw
	 * @date 2015-1-26下午4:48:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void magic1(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就（洗练）
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.magic1, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
			String strWhere = "";
			if(condition != 0){
				strWhere = " and quality = " + condition;
			}
//			int achievementValue = 0;
//			List<Long> listLong = getInstPlayerMagicDAL().getCounts(" a,Dict_Magic b where a.instPlayerId = " + instPlayerId + " and a.magicId = b.id and a.magicType = 1 and b.value1 != 3 "+ strWhere);
//			for(Long i : listLong){
//				achievementValue += i;
//			}
			
			int count = 0;
			String sql = "select magicId from Inst_Player_Magic where instPlayerId = "+instPlayerId+" and magicType = 1 " + strWhere;
			List<Map<String,Object>> magicIdList = getInstPlayerMagicDAL().sqlHelper(sql);
			for (Map<String,Object> innerMap : magicIdList) {
				int magicId = (int)(double)Double.valueOf(innerMap.get("magicId").toString());
				DictMagic dictMagicObj = DictMap.dictMagicMap.get(magicId + "");
				if (dictMagicObj != null) {
					if (!dictMagicObj.getValue1().equals("3")) {
						count++;
					}
				}
			}
			
			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.magic1, count, syncMsgData);
		}
	}
	
	/**
	 * 验证功法成就
	 * @author hzw
	 * @date 2015-1-26下午4:48:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void magic2(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就（洗练）
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.magic2, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
			String strWhere = "";
			if(condition != 0){
				strWhere = " and quality = " + condition;
			}
//			int achievementValue = 0;
//			List<Long> listLong = getInstPlayerMagicDAL().getCounts(" a,Dict_Magic b where a.instPlayerId = " + instPlayerId + " and a.magicId = b.id and a.magicType = 2 and b.value1 != 3 "+ strWhere);
//			for(Long i : listLong){
//				achievementValue += i;
//			}
			
			int count = 0;
			String sql = "select magicId from Inst_Player_Magic where instPlayerId = "+instPlayerId+" and magicType = 2 " + strWhere;
			List<Map<String,Object>> magicIdList = getInstPlayerMagicDAL().sqlHelper(sql);
			for (Map<String,Object> innerMap : magicIdList) {
				int magicId = (int)(double)Double.valueOf(innerMap.get("magicId").toString());
				DictMagic dictMagicObj = DictMap.dictMagicMap.get(magicId + "");
				if (dictMagicObj != null) {
					if (!dictMagicObj.getValue1().equals("3")) {
						count++;
					}
				}
			}
			
			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.magic2, count, syncMsgData);
		}
	}
	
	/**
	 * 验证炼丹成就
	 * @author hzw
	 * @date 2015-1-27上午10:38:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addPill(int instPlayerId, DictPill dictPill, int achievementValue,  MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就（洗练）
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.addPill, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
			if(condition == dictPill.getPillQualityId()){
				//更新玩家成就计数实例数据
				updateAchievementValue(instPlayerId, StaticAchievementType.addPill, achievementValue, syncMsgData);
			}
			
		}
	}
	
	/**
	 * 验证1人称号成就
	 * @author hzw
	 * @date 2015-1-27上午11:24:33
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void title1(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就（洗练）
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.title1, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
//			int achievementValue = 0;  //用于验证1人称号成就计数
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
//			List<Long> listLong = getInstPlayerCardDAL().getCounts(" a ,Dict_Title_Detail b where a.instPlayerId = " + instPlayerId + " and a.inTeam = "+ StaticCustomDict.inTeam +" and  a.titleDetailId = b.id and b.titleId >= "+ condition);
//			for(Long i : listLong){
//				achievementValue += i;
//			}
			
			int count = 0;
			String sql = "select titleDetailId from Inst_Player_Card where instPlayerId = "+instPlayerId+" and inTeam = " + StaticCustomDict.inTeam;
			List<Map<String,Object>> titleDetailIdList = getInstPlayerCardDAL().sqlHelper(sql);
			for (Map<String,Object> innerMap : titleDetailIdList) {
				int titleDetailId = (int)(double)Double.valueOf(innerMap.get("titleDetailId").toString());
				DictTitleDetail dictTitleDetailObj = DictMap.dictTitleDetailMap.get(titleDetailId + "");
				if (dictTitleDetailObj != null) {
					if (dictTitleDetailObj.getTitleId() >= condition) {
						count++;
					}
				}
			}

			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.title1, count, syncMsgData);
		}
	}
	
	/**
	 * 验证5人称号成就
	 * @author hzw
	 * @date 2015-1-27下午3:00:23
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void title5(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.title5, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
//			int achievementValue = 0;  //用于验证1人称号成就计数
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
//			List<Long> listLong = getInstPlayerCardDAL().getCounts(" a ,Dict_Title_Detail b where a.instPlayerId = " + instPlayerId + " and a.inTeam = "+ StaticCustomDict.inTeam +" and  a.titleDetailId = b.id and b.titleId >= "+ condition);
//			for(Long i : listLong){
//				achievementValue += i;
//			}

			int count = 0;
			String sql = "select titleDetailId from Inst_Player_Card where instPlayerId = "+instPlayerId+" and inTeam = " + StaticCustomDict.inTeam;
			List<Map<String,Object>> titleDetailIdList = getInstPlayerCardDAL().sqlHelper(sql);
			for (Map<String,Object> innerMap : titleDetailIdList) {
				int titleDetailId = (int)(double)Double.valueOf(innerMap.get("titleDetailId").toString());
				DictTitleDetail dictTitleDetailObj = DictMap.dictTitleDetailMap.get(titleDetailId + "");
				if (dictTitleDetailObj != null) {
					if (dictTitleDetailObj.getTitleId() >= condition) {
						count++;
					}
				}
			}
			
			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.title5, count, syncMsgData);
		}
	}
	
	/**
	 * 验证进阶成就
	 * @author hzw
	 * @date 2015-1-27下午3:32:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void advance(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.advance, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
			int achievementValue = getInstPlayerCardDAL().getCount(" instPlayerId = " + instPlayerId + " and qualityId = "+ StaticQuality.purple +" and starLevelId = "+ condition);
			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.advance, achievementValue, syncMsgData);
		}
	}
	
	/**
	 * 验证紫色装备成就
	 * @author hzw
	 * @date 2015-1-27下午3:54:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void purpleEquip(int instPlayerId, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.purpleEquip, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
//			int achievementValue = 0;
//			List<Long> listLong = getInstPlayerEquipDAL().getCounts("a, Dict_Equipment b where a.instPlayerId = " + instPlayerId + " and a.equipId = b.id and b.equipQualityId = "+ condition);
//			if(listLong.size() > 0){
//				for(Long i : listLong){
//					achievementValue += i;
//				}
//			}
			
			int count = 0;
			String sql = "select equipId from Inst_Player_Equip where instPlayerId = " + instPlayerId;
			List<Map<String,Object>> equipIdList = getInstPlayerEquipDAL().sqlHelper(sql);
			for (Map<String,Object> innerMap : equipIdList) {
				int equipId = (int)(double)Double.valueOf(innerMap.get("equipId").toString());
				DictEquipment dictEquipmentObj = DictMap.dictEquipmentMap.get(equipId + "");
				if (dictEquipmentObj != null) {
					if (dictEquipmentObj.getEquipQualityId() == condition) {
						count++;
					}
				}
			}
			
			//更新玩家成就计数实例数据
			updateAchievementValue(instPlayerId, StaticAchievementType.purpleEquip, count, syncMsgData);
		}
	}
	
	/**
	 * 验证副本成就
	 * @author hzw
	 * @date 2015-1-27下午3:54:47
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void barrier(int instPlayerId, InstPlayerChapter instPlayerChapter, MessageData syncMsgData) throws Exception{
		//没有可领取的才处理成就
		InstPlayerAchievement instPlayerAchievement = getInstPlayerAchievementDAL().getList("instPlayerId =  " + instPlayerId + " and achievementTypeId = " + StaticAchievementType.barrier, instPlayerId).get(0);
		if(instPlayerAchievement.getCanAchievementId() == 0 && instPlayerAchievement.getAchievementId() != 0){
			DictAchievement dictAchievement = DictMap.dictAchievementMap.get(instPlayerAchievement.getAchievementId() + "");
			int condition = ConvertUtil.toInt(dictAchievement.getConditions());
			if(condition == instPlayerChapter.getChapterId()&&instPlayerChapter.getIsPass()==1){
				//更新玩家成就计数实例数据
				updateAchievement (instPlayerId, StaticAchievementType.barrier, 1, syncMsgData, instPlayerAchievement);
			}
		}
	}
	
	
}
