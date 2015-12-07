package com.huayi.doupo.logic.handler;

import java.util.HashMap;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.model.DictBarrier;
import com.huayi.doupo.base.model.DictBarrierLevel;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerArena;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.randomname.RandomNameUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.ChapterUtil;
import com.huayi.doupo.logic.handler.util.EquipmentUtil;
import com.huayi.doupo.logic.handler.util.KungFuUtil;
import com.huayi.doupo.logic.handler.util.PillUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.VerifyUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * 系统处理类
 * @author mp
 * @date 2013-11-7 下午3:56:59
 */
public class SysHandler extends BaseHandler{
	
	public static boolean closeServer = false;
	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(">>>>>>>>>>start");
		
		//加载资源
		SpringUtil.getSpringContext();
		DictData.dictData(0);
		RandomNameUtil.initRandomNameUtil();
		
		//创建用户账号
		String openId ="3600112";
//		UserUtil.initUser(openId, "gj");
		
		//创建玩家
		InstPlayer instPlayer = PlayerUtil.initHighPlayer(openId, null);
		int instPlayerId = instPlayer.getId();
		
		//创建阵容
		PlayerUtil.initHighPlayerLineup(instPlayerId);
		
		//创建物品
		/**
		 * 
		    3种境界突破材料	Dict_Thing	4-6	每种999个
		           菩提子Dict_Thing 	2	99999个
		           卡牌颜色3种颜色进阶材料	Dict_Thing	83-85	每种999个
			打孔器	Dict_Thing	1	999
			洗练石	Dict_Thing	8	9999
			洗练锁	Dict_Thing	3	9999
			耐力丹	Dict_Thing	79	999个
			体力丹	Dict_Thing	80	999个
			魂源	Dict_Thing	12	99999
		 */
		//格式为 thingId_num;
		String thingList = "4_999;5_999;6_999;2_99999;83_999;84_999;85_999;1_999;8_9999;3_9999;79_999;80_999;12_99999";
		for (String thing : thingList.split(";")) {
			int thingId = ConvertUtil.toInt(thing.split("_")[0]);
			int num = ConvertUtil.toInt(thing.split("_")[1]);
			ThingUtil.groupThing(instPlayer, StaticTableType.DictThing, thingId, num, new MessageData(), null);
		}
		
		
		//丹药材料  tableFieldId=Dict_PillThing.id
		for (int tableFieldId = 1; tableFieldId <= 60; tableFieldId++) {
			int value = 9999;
			PillUtil.addInstPlayerPillThing(instPlayerId, tableFieldId, value, new MessageData());
		}
		
		
		//装备
		String equipmentList = "1;1;45;45;65;65;88;88;112;112;127;127";
		for (String equipmentId : equipmentList.split(";")) {
			EquipmentUtil.addEquipment(instPlayerId, ConvertUtil.toInt(equipmentId));
		}
		
		//功法
		for (int kungFuId = 1; kungFuId <= 10; kungFuId++) {
			KungFuUtil.addInstPlayerKungFu(instPlayerId, kungFuId, 0);
		}
		
		//威望
		InstPlayerArena instPlayerArena = new InstPlayerArena();
		instPlayerArena.setInstPlayerId(instPlayerId);
		instPlayerArena.setRank(getInstPlayerArenaDAL().getList("", 0).size() + 1);
		instPlayerArena.setUpRank(0);
		instPlayerArena.setArenaNum(DictMapUtil.getSysConfigIntValue(StaticSysConfig.arenaNum));
		instPlayerArena.setArenaTime("");
		instPlayerArena.setPrestige(999999);
		instPlayerArena.setAwardRank(0);
		instPlayerArena.setOperTime(DateUtil.getCurrTime());
		getInstPlayerArenaDAL().add(instPlayerArena, instPlayerId);
		
		//普通副本(简单全部通过)
		for (int barrierLevelId = 1; barrierLevelId <= 298; barrierLevelId++) {
			DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
			if(dictBarrierLevel.getLevel() == 1){
				ChapterUtil.initInstPlayerBarrierLevel(instPlayerId, barrierLevelId, 1, DateUtil.getCurrTime(), new MessageData());
			}
		}
		
		//精英副本(全部通过)
		for (int barrierLevelId = 302; barrierLevelId <= 306; barrierLevelId++) {
			DictBarrierLevel dictBarrierLevel = DictMap.dictBarrierLevelMap.get(barrierLevelId + "");
			DictBarrier dictBarrier = DictMap.dictBarrierMap.get(dictBarrierLevel.getBarrierId() + "");
			ChapterUtil.initInstPlayerChapterType(instPlayerId, 2, dictBarrier.getChapterId(), 1, DateUtil.getCurrTime(), new MessageData());
		}
		
		System.out.println(">>>>>>>>>>end");
	}
	
	
	
	/**
	 * 1000
	 * 心跳检测
	 * @author mp
	 * @version 1.0, 2013-4-5 上午10:13:28
	 * @param msg
	 * @param channelId
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void heartbeat(HashMap<String, Object> msgMap, String channelId) throws Exception {
		System.out.println(Thread.currentThread().getName() + " xxxxxxxxx");
//		MessageUtil.sendMsg(channelId, msgMap, null);
//		MessageData msgData = (MessageData)msgMap.get("messageData");
//		MessageUtil.sendMsg(channelId, msgMap, msgData);
//		MessageData msgData = (MessageData)msgMap.get("messageData");
//		MessageUtil.sendMsg(channelId, msgMap, msgData);
	}
	
	/**
	 * 检测战斗
	 * @author glz
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void checkFight(HashMap<String, Object> msgMap, String channelId) throws Exception {
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
		//用于验证玩家是否利用烧饼等修改器
		if(coredata==null||VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		MessageData retMsgData = new MessageData();
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 系统补偿
	 * @author mp
	 * @date 2013-12-6 下午5:13:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void sysIndemnity(HashMap<String, Object> msgMap, String channelId) throws Exception {
		
//		String indemnityParamList = (String)msgMap.get("indemnityParamList");
//		String indemnityContent = (String)msgMap.get("indemnityContent");
//		
//		List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("");
//		for(InstPlayer obj : instPlayerList){
//			InstPlayerTransBook instPlayerTransBook = new InstPlayerTransBook();
//			instPlayerTransBook.setContent(indemnityContent);
//			instPlayerTransBook.setEnemyInstPlayerId(0);
//			instPlayerTransBook.setFightType(0);
//			instPlayerTransBook.setInstPlayerId(obj.getInstPlayerId());
//			instPlayerTransBook.setIsOperate(StaticCustomDict.notOperate);
//			instPlayerTransBook.setOperateTypeId(StaticOperateType.get);
//			instPlayerTransBook.setParamList(indemnityParamList);//金币—10000;包子_2;钻石_100
//			instPlayerTransBook.setThingTypeId(StaticThingType.system);
//			instPlayerTransBook.setViewTime(DateUtil.getFormateDate(BaseHandler.getNowTimeMill()));
//			getInstPlayerTransBookDAL().add(instPlayerTransBook);
//		}
	}
	/*
	@Test
	public void test(){
		try {
			SpringUtil.getSpringContext();
			DictData.dictData(0);
			getSysIndemnity(null, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	*//**
	 * 领取系统补偿
	 * @author mp
	 * @date 2013-12-6 下午5:28:19
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 *//*
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getSysIndemnity(HashMap<String, Object> msgMap,Integer channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//格式：tableTypeId(玩家属性)_tableFieldId(金币)_10000(数量);
		//只有金币，钻石和物品（箱子，钥匙除外），没有卡牌，技能
		int instPlayerTransBookId = (Integer)msgMap.get("instPlayerTransBookId");
		InstPlayerTransBook instPlayerTransBook = getInstPlayerTransBookDAL().getModel(instPlayerTransBookId);
		if(instPlayerId != instPlayerTransBook.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, "玩家不一致!");
			return;
		}
		
		if(instPlayerTransBook.getIsOperate() == StaticCustomDict.haveOperate){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveGet);
			return;
		}
		instPlayerTransBook.setIsOperate(StaticCustomDict.haveOperate);
		getInstPlayerTransBookDAL().update(instPlayerTransBook);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerTransBook, syncMsgData, 0, "0");
		
		String indemnityParamList = instPlayerTransBook.getParamList();
		String [] parmasArray = indemnityParamList.split(";");
		if(parmasArray.length > 0){
			int index = 0;
			for(String thingAndNum : parmasArray){
				index++;
				String [] detailParam = thingAndNum.split("_");
				int tableTypeId = Convert.toInt(detailParam[0]);
				int tableFieldId = Convert.toInt(detailParam[1]);
				int tableValue = Convert.toInt(detailParam[2]);
				if(tableTypeId == StaticTableType.DictPlayerBaseProp){
					if(tableFieldId == StaticPlayerBaseProp.gold){
						instPlayer.setGold((Convert.toLong(instPlayer.getGold()) + tableValue) + "");
					}else if(tableFieldId == StaticPlayerBaseProp.diamond){
						instPlayer.setDiamond(instPlayer.getDiamond() + tableValue);
					}
					getInstPlayerDAL().update(instPlayer);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, syncMsgData, 0, index+"");
				}else if(tableTypeId == StaticTableType.DictThing){
					
					DictThing thing = DictMap.dictThingMap.get(tableFieldId+"");
					int thingTypeId = thing.getThingTypeId();
					
					if(thingTypeId == StaticThingType.skillChip){
						
						//处理玩家技能碎片实例表
						List<InstPlayerSkillChip> instPlayerSkillChipList = getInstPlayerSkillChipDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + tableFieldId);
						if(instPlayerSkillChipList.size() <= 0){
							InstPlayerSkillChip instPlayerSkillChip = SkillUtil.addSkillChip(instPlayerId, tableFieldId);
							OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerSkillChip, syncMsgData, 0, index+"");
						}else{
							InstPlayerSkillChip instPlayerSkillChip = instPlayerSkillChipList.get(0);
							instPlayerSkillChip.setNum(instPlayerSkillChip.getNum() + tableValue);
							getInstPlayerSkillChipDAL().update(instPlayerSkillChip);
							OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerSkillChip, syncMsgData, 0, index+"");
						}
						
						//处理闯关技能实例表
						int skillId = thing.getSkillId();
						List<InstPlayerRiskSkill> instPlayerRiskSkillList = getInstPlayerRiskSkillDAL().getList("instPlayerId = " + instPlayerId + " and skillId = " + skillId);
						if(instPlayerRiskSkillList.size() <= 0){
							InstPlayerRiskSkill instPlayerRiskSkill = RiskUtil.addRiskSkill(instPlayerId, skillId);
							OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerRiskSkill, syncMsgData, 0, index+"");
						}else{
							InstPlayerRiskSkill instPlayerRiskSkill = instPlayerRiskSkillList.get(0);
							instPlayerRiskSkill.setCanMergeTimes(instPlayerRiskSkill.getCanMergeTimes() + tableValue);
							getInstPlayerRiskSkillDAL().update(instPlayerRiskSkill);
							OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerRiskSkill, syncMsgData, 0, index+"");
							//后端计算使用，不用和前端同步 
						}
					}else{
						
						if(thingTypeId == StaticThingType.box && thing.getThingDetailTypeId() == StaticThingDetailType.box){
							// 添加宝箱实例数据
							StoreUtil.addBoxThing(instPlayerId, tableValue, thing.getId(), thing.getThingMoreDetailTypeId(), syncMsgData);
						}
						else{
							//添加物品
							List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + tableFieldId);
							if(instPlayerThingList.size() <= 0){
								InstPlayerThing instPlayerThing = ThingUtil.addInstPlayerThing(instPlayerId, tableFieldId, tableValue);
								OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerThing, syncMsgData, 0, index+"");
							}else{
								InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
								instPlayerThing.setNum(instPlayerThing.getNum() + tableValue);
								getInstPlayerThingDAL().update(instPlayerThing);
								OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, syncMsgData, 0, index+"");
							}
						}
					}
				}else if(tableTypeId == StaticTableType.DictEquipment){
//					if(SysConfigHelper.getValue("net.environment").equals("0")){
						for(int a = 0 ; a < tableValue ; a++){
							InstPlayerEquipment instPlayerEquipment = EquipmentUtil.addEquipment(instPlayerId, tableFieldId);
							OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerEquipment, syncMsgData, 0, a+index+"");
						}
//					}else{
//						InstPlayerEquipment instPlayerEquipment = EquipmentUtil.addEquipment(instPlayerId, tableFieldId);
//						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerEquipment, syncMsgData, 0, index+"");
//					}
				}else if(tableTypeId == StaticTableType.DictSkill){
//					if(SysConfigHelper.getValue("net.environment").equals("0")){
						for(int a = 0 ; a < tableValue ; a++){
							InstPlayerSkill instPlayerSkill = SkillUtil.addPackageSkill(instPlayerId, tableFieldId);
							OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerSkill, syncMsgData, 0, a+index+"");
						}
//					}else{
//						InstPlayerSkill instPlayerSkill = SkillUtil.addPackageSkill(instPlayerId, tableFieldId);
//						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerSkill, syncMsgData, 0, index+"");
//					}
				}else if(tableTypeId == StaticTableType.DictWarriorShadow){
					WarriorUtil.addWarriorShadow(instPlayerId, tableFieldId, tableValue, syncMsgData);
				}
				if(tableTypeId == StaticTableType.DictActivityForge){
					for(int a = 0 ; a < tableValue ; a++){
						InstPlayerActivityForge instPlayerActivityForge = new InstPlayerActivityForge();
						instPlayerActivityForge.setInstPlayerId(instPlayerId);
						instPlayerActivityForge.setForgeId(tableFieldId);
						getInstPlayerActivityForgeDAL().add(instPlayerActivityForge);
						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerActivityForge, syncMsgData, 0, a+index+"");
					}
				}
			}
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, StaticCnServer.succ);
	}
	
	*//**
	 * 获取系统时间
	 * @author mp
	 * @date 2013-12-14 下午2:54:23
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
//	public void getSysTime(HashMap<String, Object> msgMap,String channelId) throws Exception {
//		MessageUtil.sendMsg(channelId, msgMap, null);
//	}
	
	/**
	 * 领取CDK兑换码奖励
	 * @author mp
	 * @date 2014-1-16 下午2:17:17
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 *//*
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@SuppressWarnings("unchecked")
	public void getCdkReward(HashMap<String, Object> msgMap,Integer channelId) throws Exception {
		MessageData retMsgData = new MessageData();
		MessageData testMsgData = new MessageData();
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId);
		
		String cdk = (String)msgMap.get("cdk");
		cdk = cdk.toLowerCase();
		
		List<SysCdKey> sysCdKeyList = DictList.sysCdKeyList;
		int cdKey = 0;
		int sysCdkTypeId = 0;
		for(SysCdKey obj : sysCdKeyList){
			if(obj.getCdk().equals(cdk)){
				cdKey++;
				sysCdkTypeId = obj.getCdKeyTypeId();
			}
		}
		if(cdKey == 0 || sysCdkTypeId == 0){
			MessageUtil.sendFailMsg(channelId, msgMap, "此兑换码无效");
			return;
		}
		
		SysCdKeyType sysCdKeyType = null;
		List<SysCdKeyType> sysCdKeyTypeList = DictList.sysCdKeyTypeList;
		for(SysCdKeyType obj : sysCdKeyTypeList){
			if(obj.getId() == sysCdkTypeId){
				sysCdKeyType = obj;
			}
		}
		
		boolean flag = VerifyUtil.vfGetCdk(channelId, msgMap, instPlayerId, cdk, sysCdKeyType);
		if(flag){
			return;
		}
		
		String thingId = sysCdKeyType.getThingId();
		
//		if(sysCdKeyType.getId() == StaticCdKeyType.jueDouCDK){
//			if(cdk.equals("lz487")){
//				thingId = thingId.split(";")[0];
//			}else if(cdk.equals("lz127")){
//				thingId = thingId.split(";")[1];
//			}else if(cdk.equals("lz458")){
//				thingId = thingId.split(";")[2];
//			}else{
//				thingId = thingId.split(";")[3];
//			}
//		}
		
		InstCdKeyGetRecord instCdKeyGetRecord = new InstCdKeyGetRecord();
		instCdKeyGetRecord.setCdk(cdk);
		instCdKeyGetRecord.setCdKeyTypeId(sysCdKeyType.getId());
		instCdKeyGetRecord.setInstPlayerId(instPlayerId);
		instCdKeyGetRecord.setGetTime(DateUtil.getFormateDate(getNowTimeMill()));
		getInstCdKeyGetRecordDAL().add(instCdKeyGetRecord);
		
		//开启礼包逻辑
		List<DictGiftOpenThing> dictGiftOpenThingList = (List<DictGiftOpenThing>)DictMapList.dictGiftOpenThingMap.get(Integer.valueOf(thingId));
		int index = 0;
		for(DictGiftOpenThing dictGiftOpenThing : dictGiftOpenThingList){
			index++;
			int tableTypeId = dictGiftOpenThing.getTableTypeId();
			int tableFieldId = dictGiftOpenThing.getTableFieldId();
			int num = dictGiftOpenThing.getTableValue();
			
			if(tableTypeId == StaticTableType.DictEquipment){
				InstPlayerEquipment instPlayerEquipment = EquipmentUtil.addEquipment(instPlayerId, tableFieldId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerEquipment, syncMsgData, 0, index+"");
			}
			if(tableTypeId == StaticTableType.DictSkill){
				InstPlayerSkill instPlayerSkill = SkillUtil.addPackageSkill(instPlayerId, tableFieldId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerSkill, syncMsgData, 0, index+"");
			}
			if(tableTypeId == StaticTableType.DictThing){
				DictThing dictThingObj = DictMap.dictThingMap.get(tableFieldId + "");
				int thingTypeId = dictThingObj.getThingTypeId();
				
				if(thingTypeId == StaticThingType.box && dictThingObj.getThingDetailTypeId() == StaticThingDetailType.box){
					// 添加宝箱实例数据
					StoreUtil.addBoxThing(instPlayerId, num, dictThingObj.getId(), dictThingObj.getThingMoreDetailTypeId(), syncMsgData);
				}
				else{
					List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList(" instPlayerId = " + instPlayerId + " and thingId = " + tableFieldId);
					if(instPlayerThingList.size() > 0){
						InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
						instPlayerThing.setNum(instPlayerThing.getNum() + num);
						getInstPlayerThingDAL().update(instPlayerThing);
						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerThing, syncMsgData, 0, index+"");
					}
					else{
						InstPlayerThing instPlayerThing = new InstPlayerThing();
						instPlayerThing = ThingUtil.addInstPlayerThing(instPlayerId, tableFieldId, num);
						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerThing, syncMsgData, 0, index+"");
					}
				}
			}
			if(tableTypeId == StaticTableType.DictPlayerBaseProp){
				if(tableFieldId == StaticPlayerBaseProp.gold){
					instPlayer.setGold((Convert.toLong(instPlayer.getGold()) + num) + "");
				}
				if(tableFieldId == StaticPlayerBaseProp.diamond){
					instPlayer.setDiamond(instPlayer.getDiamond() + num);
				}
				getInstPlayerDAL().update(instPlayer);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, syncMsgData, 0, index+"");
			}	
			if(tableTypeId == StaticTableType.DictActivityForge){
				for(int a = 0 ; a < num ; a++){
					InstPlayerActivityForge instPlayerActivityForge = new InstPlayerActivityForge();
					instPlayerActivityForge.setInstPlayerId(instPlayerId);
					instPlayerActivityForge.setForgeId(tableFieldId);
					getInstPlayerActivityForgeDAL().add(instPlayerActivityForge);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerActivityForge, syncMsgData, 0, a+index+"");
				}
			}
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", tableTypeId);//表Id
			msgData.putIntItem("2", tableFieldId);//字段Id
			msgData.putIntItem("3", num);//数量		
			testMsgData.putMessageItem(index + "", msgData.getMsgData());
		}
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		retMsgData.putMessageItem("getCdkReward", testMsgData.getMsgData());
		MessageUtil.sendSuccMsg(channelId, msgMap, StaticCnServer.succ,retMsgData);
	}
	
	public static void main(String[] args) {
		try {
			SpringHelper.getSpringContext();
			DictData.loadDictData();
			
			
			
			//Inst_PlayerActivity_ContinuousLogin
			int index = 0;
			List<InstPlayerActivityContinuousLogin> instPlayerActivityContinuousLoginList = getInstPlayerActivityContinuousLoginDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerActivityContinuousLogin size = " + instPlayerActivityContinuousLoginList.size());
			for(InstPlayerActivityContinuousLogin obj : instPlayerActivityContinuousLoginList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerActivityContinuousLoginDAL().deleteById(obj.getId());
			}

			
			index = 0;
			List<InstActivity> instPlayerActivityList = getInstActivityDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstActivity size = " + instPlayerActivityList.size());
			for(InstActivity obj : instPlayerActivityList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstActivityDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstCdKeyGetRecord> instCdKeyGetRecordList = getInstCdKeyGetRecordDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstCdKeyGetRecord size = " + instCdKeyGetRecordList.size());
			for(InstCdKeyGetRecord obj : instCdKeyGetRecordList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstCdKeyGetRecordDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstEquipmentGem> instEquipmentGemList = getInstEquipmentGemDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstEquipmentGem size = " + instEquipmentGemList.size());
			for(InstEquipmentGem obj : instEquipmentGemList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstEquipmentGemDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerActivityGod> instPlayerActivityGodList = getInstPlayerActivityGodDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerActivityGod size = " + instPlayerActivityGodList.size());
			for(InstPlayerActivityGod obj : instPlayerActivityGodList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerActivityGodDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerActivityLoginFestival> instPlayerActivityLoginFestivalList = getInstPlayerActivityLoginFestivalDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerActivityLoginFestival size = " + instPlayerActivityLoginFestivalList.size());
			for(InstPlayerActivityLoginFestival obj : instPlayerActivityLoginFestivalList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerActivityLoginFestivalDAL().deleteById(obj.getId());
			}
			
			
			
			index = 0;
			List<InstPlayerActivityMammon> instPlayerActivityMammonList = getInstPlayerActivityMammonDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerActivityMammon size = " + instPlayerActivityMammonList.size());
			for(InstPlayerActivityMammon obj : instPlayerActivityMammonList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerActivityMammonDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerActivitySeekingTreasures> instPlayerActivitySeekingTreasuresList = getInstPlayerActivitySeekingTreasuresDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerActivitySeekingTreasures size = " + instPlayerActivitySeekingTreasuresList.size());
			for(InstPlayerActivitySeekingTreasures obj : instPlayerActivitySeekingTreasuresList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerActivitySeekingTreasuresDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerEquipment> instPlayerEquipmentList = getInstPlayerEquipmentDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerEquipment size = " + instPlayerEquipmentList.size());
			for(InstPlayerEquipment obj : instPlayerEquipmentList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerEquipmentDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerFormation> instPlayerFormationList = getInstPlayerFormationDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerFormation size = " + instPlayerFormationList.size());
			for(InstPlayerFormation obj : instPlayerFormationList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerFormationDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerLineup> instPlayerLineupList = getInstPlayerLineupDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerLineup size = " + instPlayerLineupList.size());
			for(InstPlayerLineup obj : instPlayerLineupList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerLineupDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerPassedBarrier> instPlayerPassedBarrierList = getInstPlayerPassedBarrierDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerPassedBarrier size = " + instPlayerPassedBarrierList.size());
			for(InstPlayerPassedBarrier obj : instPlayerPassedBarrierList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerPassedBarrierDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerPractice> instPlayerPracticeList = getInstPlayerPracticeDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerPractice size = " + instPlayerPracticeList.size());
			for(InstPlayerPractice obj : instPlayerPracticeList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerPracticeDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerRecruit> instPlayerRecruitList = getInstPlayerRecruitDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerRecruit size = " + instPlayerRecruitList.size());
			for(InstPlayerRecruit obj : instPlayerRecruitList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerRecruitDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstWarriorTitle> instWarriorTitleList = getInstWarriorTitleDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstWarriorTitle size = " + instWarriorTitleList.size());
			for(InstWarriorTitle obj : instWarriorTitleList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstWarriorTitleDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstWarrior> instWarriorList = getInstWarriorDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstWarrior size = " + instWarriorList.size());
			for(InstWarrior obj : instWarriorList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstWarriorDAL().deleteById(obj.getInstWarriorId());
			}
			
			
			index = 0;
			List<InstPlayerWarriorShadow> instPlayerWarriorShadowList = getInstPlayerWarriorShadowDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerWarriorShadow size = " + instPlayerWarriorShadowList.size());
			for(InstPlayerWarriorShadow obj : instPlayerWarriorShadowList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerWarriorShadowDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerWarriorLuck> instPlayerWarriorLuckList = getInstPlayerWarriorLuckDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerWarriorLuck size = " + instPlayerWarriorLuckList.size());
			for(InstPlayerWarriorLuck obj : instPlayerWarriorLuckList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerWarriorLuckDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerTrain> instPlayerTrainList = getInstPlayerTrainDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerTrain size = " + instPlayerTrainList.size());
			for(InstPlayerTrain obj : instPlayerTrainList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerTrainDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerTransBook> instPlayerTransBookList = getInstPlayerTransBookDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerTransBook size = " + instPlayerTransBookList.size());
			for(InstPlayerTransBook obj : instPlayerTransBookList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerTransBookDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerTitle> instPlayerTitleList = getInstPlayerTitleDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerTitle size = " + instPlayerTitleList.size());
			for(InstPlayerTitle obj : instPlayerTitleList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerTitleDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerThing size = " + instPlayerThingList.size());
			for(InstPlayerThing obj : instPlayerThingList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerThingDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerSkillChipLootWarriorListTemp> instPlayerSkillChipLootWarriorListTempList = getInstPlayerSkillChipLootWarriorListTempDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerSkillChipLootWarriorListTemp size = " + instPlayerSkillChipLootWarriorListTempList.size());
			for(InstPlayerSkillChipLootWarriorListTemp obj : instPlayerSkillChipLootWarriorListTempList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerSkillChipLootWarriorListTempDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerSkillChip> instPlayerSkillChipList = getInstPlayerSkillChipDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerSkillChip size = " + instPlayerSkillChipList.size());
			for(InstPlayerSkillChip obj : instPlayerSkillChipList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerSkillChipDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerSkill> instPlayerSkillList = getInstPlayerSkillDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerSkill size = " + instPlayerSkillList.size());
			for(InstPlayerSkill obj : instPlayerSkillList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerSkillDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayerRiskSkill> instPlayerRiskSkillList = getInstPlayerRiskSkillDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayerRiskSkill size = " + instPlayerRiskSkillList.size());
			for(InstPlayerRiskSkill obj : instPlayerRiskSkillList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerRiskSkillDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstUser> instUserList = getInstUserDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstUser size = " + instUserList.size());
			for(InstUser obj : instUserList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstUserDAL().deleteById(obj.getId());
			}
			
			
			index = 0;
			List<InstPlayer> instPlayerList = getInstPlayerDAL().getListFromMoreTale(", tempInstPlayerId as b where a.instPlayerId = b.instPlayerId");
			System.out.println("InstPlayer size = " + instPlayerList.size());
			for(InstPlayer obj : instPlayerList){
				index++;
				if(index % 10 == 0){
					System.out.print(index+" ");
				}
				if(index % 100 == 0){
					System.out.println();
				}
				getInstPlayerDAL().deleteById(obj.getInstPlayerId());
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rechargeVip(){
		try {
			SpringHelper.getSpringContext();
			DictData.loadDictData();
			
			List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("");
			for(InstPlayer instPlayer : instPlayerList){
				int rechargeSum = instPlayer.getRechageSumRmb();
				int oldVIPLevel = instPlayer.getVipLevel();
				
				//充值用户，根据充值金额重新设置vip等级
				if(rechargeSum > 0){
					List<DictVIPFCUpgrade> vipfcUpgradeList = getDictVIPFCUpgradeDAL().getList("");
					//按VIP等级升序排列
					Collections.sort(vipfcUpgradeList, new Comparator<Object>() {
						public int compare(Object a, Object b) {
							int one = ((DictVIPFCUpgrade)a).getVipLevel();
							int two = ((DictVIPFCUpgrade)b).getVipLevel(); 
							return (int)(one - two); 
						}
					}); 
					DictVIPFCUpgrade vipfcUpgrade = null;
					for(DictVIPFCUpgrade obj : vipfcUpgradeList){
						if(obj.getRechageRmb() > rechargeSum){
							vipfcUpgrade = obj;
							break;
						}
					}
					int vipLevel = 0;
					if(vipfcUpgrade != null){
						//修改玩家vip等级
						vipLevel = vipfcUpgrade.getVipLevel() - 1;
						instPlayer.setVipLevel(vipLevel);
						
						//根据vip等级修改称号和气势加成
						List<InstPlayerTitle> instPlayerTitleList = getInstPlayerTitleDAL().getList("instPlayerId = " + instPlayer.getInstPlayerId() + " and titleId = " + 2);
						if(instPlayerTitleList.size() > 0){
							InstPlayerTitle instPlayerTitle = instPlayerTitleList.get(0);
							instPlayerTitle.setLevel(vipLevel);
							DictTitleUpgrade titleUpgrade = getDictTitleUpgradeDAL().getList("titleId = " + 2 + " and tableValue = " + vipLevel).get(0);
							float addMettle = titleUpgrade.getAddMettle();
							instPlayerTitle.setMettle(addMettle);
							getInstPlayerTitleDAL().update(instPlayerTitle);
							
							//修改玩家总气势
							List<InstPlayerTitle> instPlayerTitleBarrierList = getInstPlayerTitleDAL().getList("instPlayerId = " + instPlayer.getInstPlayerId() + " and titleId = " + 1);
							if(instPlayerTitleBarrierList.size() > 0){
								InstPlayerTitle instPlayerTitleBarrier = instPlayerTitleBarrierList.get(0);
								float barrierMettle = instPlayerTitleBarrier.getMettle();
								float shouldAddMettle = barrierMettle * addMettle;
								instPlayer.setMettle(Convert.toInt((float)instPlayer.getMettle() + shouldAddMettle));
							}
						}
						
						if(vipLevel >= oldVIPLevel){
							getInstPlayerDAL().update(instPlayer);
						}
					}
				}else{
					//未充值玩家，vip等级大于1
					if(instPlayer.getVipLevel() > 1){
						int vipLevel = instPlayer.getVipLevel();
						//根据vip等级修改称号和气势加成
						List<InstPlayerTitle> instPlayerTitleList = getInstPlayerTitleDAL().getList("instPlayerId = " + instPlayer.getInstPlayerId() + " and titleId = " + 2);
						if(instPlayerTitleList.size() > 0){
							InstPlayerTitle instPlayerTitle = instPlayerTitleList.get(0);
							instPlayerTitle.setLevel(vipLevel);
							DictTitleUpgrade titleUpgrade = getDictTitleUpgradeDAL().getList("titleId = " + 2 + " and tableValue = " + vipLevel).get(0);
							float addMettle = titleUpgrade.getAddMettle();
							instPlayerTitle.setMettle(addMettle);
							getInstPlayerTitleDAL().update(instPlayerTitle);
							
							//修改玩家总气势
							List<InstPlayerTitle> instPlayerTitleBarrierList = getInstPlayerTitleDAL().getList("instPlayerId = " + instPlayer.getInstPlayerId() + " and titleId = " + 1);
							if(instPlayerTitleBarrierList.size() > 0){
								InstPlayerTitle instPlayerTitleBarrier = instPlayerTitleBarrierList.get(0);
								float barrierMettle = instPlayerTitleBarrier.getMettle();
								float shouldAddMettle = barrierMettle * addMettle;
								instPlayer.setMettle(Convert.toInt((float)instPlayer.getMettle() + shouldAddMettle));
							}
						}
						getInstPlayerDAL().update(instPlayer);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	*/
}
