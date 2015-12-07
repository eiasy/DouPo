package com.huayi.doupo.logic.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.DictChip;
import com.huayi.doupo.base.model.DictLevelProp;
import com.huayi.doupo.base.model.DictLootChip;
import com.huayi.doupo.base.model.DictLootDrop;
import com.huayi.doupo.base.model.DictLootNPC;
import com.huayi.doupo.base.model.DictMagic;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerChip;
import com.huayi.doupo.base.model.InstPlayerFormation;
import com.huayi.doupo.base.model.InstPlayerLoot;
import com.huayi.doupo.base.model.InstPlayerMagic;
import com.huayi.doupo.base.model.InstPlayerManualSkill;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticDailyTask;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.custom.CustomMarqueeType;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateType;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.EnemyPlayerUtil;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.LootUtil;
import com.huayi.doupo.logic.handler.util.ManualSkillUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.marquee.MarqueeUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 抢夺处理类
 * @author hzw
 * @date 2014-9-3下午5:40:32
 */
public class LootHandler extends BaseHandler{
	
	/**
	 * 下发抢夺玩家/NPC列表
	 * @author hzw
	 * @date 2014-9-4上午11:17:12
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked" })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void lootPlayer(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int chipId = (Integer)msgMap.get("chipId");
		String step = (String)msgMap.get("step");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(instPlayer.getLevel() < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.lootLeve)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		DictChip dictChip = DictMap.dictChipMap.get(chipId + "");
		int qualityId = 0;
		if(dictChip.getType() == 1){
			qualityId = DictMap.dictManualSkillMap.get(dictChip.getSkillOrKungFuId() + "").getQuality();
		}else{
			qualityId = DictMap.dictMagicMap.get(dictChip.getSkillOrKungFuId() + "").getMagicQualityId();
		}
		List<DictLootChip> dictLootChips = (List<DictLootChip>)DictMapList.dictLootChipMap.get(qualityId);
		int lootPlayerDS = 0;
		int lootNPCDS = 0;
		for(DictLootChip obj : dictLootChips){
			if(obj.getType() == dictChip.getType()){
				lootPlayerDS = obj.getLootPlayerDS();
				lootNPCDS = obj.getLootNPCDS();
			}
		}
		//组织概率描述
		MessageData dsMsgData = new MessageData();
		dsMsgData.putIntItem("1", lootPlayerDS);
		dsMsgData.putIntItem("2", lootNPCDS);
		
		int level = instPlayer.getLevel();
		int lootNPCLevel = DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootNPCLevel);
		
		
		List<DictLootNPC> dictLootNPCs = DictList.dictLootNPCList;
		int random = RandomUtil.getRandomInt(4);
		if(step.equals("20#4")){
			random = 4;
		}
		MessageData playersMsgData = new MessageData();
		MessageData npcsMsgData = new MessageData();
		List<Integer> rd = null;
		if(random < 4){                                                                                                   
			//组织PC
			int lootPlayerLevel = DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootPlayerLevel);
			String sql = "select a.id,a.`name`,a.`level` from Inst_Player a, Inst_Player_Chip b where a.id = b.instPlayerId and b.chipId = " + chipId + " and a.`level` BETWEEN " + (level - lootPlayerLevel) + " AND " +  + (level + lootPlayerLevel) + " ";
			JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.GetObjectWithSpringContext("JdbcTemplate");
			List<Map<String, Object>> rsSet = jdbcTemplate.queryForList(sql);
			if(rsSet.size() > 0){
				for(int num = 0; num < rsSet.size(); num ++){
					Map<String, Object> objMap = rsSet.get(num);
					int id = (int)objMap.get("id");
					if(id == instPlayerId){
						rsSet.remove(objMap);
						num --;
						continue;
					}
					List<InstPlayerLoot> instPlayerLoots = (List<InstPlayerLoot>)getInstPlayerLootDAL().getList(" instPlayerId = " + id + " and NOW() <= protectTime ", id);
					if(instPlayerLoots.size() > 0){
						rsSet.remove(objMap);
						num --;
					}
				}
			}
			if(rsSet.size() > 0){
				int min = 4 - random;
				if(rsSet.size() < 4 - random){min = rsSet.size();}
			
				rd  = RandomUtil.getRandomNoRepeat(min, rsSet.size());
				for(int i : rd){
					Map<String, Object> obj = rsSet.get(i - 1);
					MessageData playerMsgData = new MessageData();
					playerMsgData.putIntItem("1", (int)obj.get("id"));
					playerMsgData.putIntItem("2", (int)obj.get("level"));
					playerMsgData.putStringItem("3", (String)obj.get("name"));
					List<InstPlayerFormation> InstPlayerFormations = getInstPlayerFormationDAL().getList(" instPlayerId = " + (int)obj.get("id") + " and type = 1 limit 4", (int)obj.get("id"));
					String cards = "";
					for(InstPlayerFormation ipf : InstPlayerFormations){
						InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(ipf.getInstCardId(), (int)obj.get("id"));
						cards += instPlayerCard.getCardId() + "_" + instPlayerCard.getQualityId() + ";";
					}
					playerMsgData.putStringItem("4", cards.substring(0, cards.length() - 1));
					playersMsgData.putMessageItem("pc" + i, playerMsgData.getMsgData());
				}
			}
		}
		//组织NPC
		int min = 4;
		if(rd != null){
			min = 4 - rd.size();
		}
		if(min > 0){
			rd  = RandomUtil.getRandomNoRepeat(min, dictLootNPCs.size());
			for(int i : rd){
				DictLootNPC obj = dictLootNPCs.get(i - 1);
				MessageData npcMsgData = new MessageData();
				npcMsgData.putIntItem("1", obj.getId());
				npcMsgData.putIntItem("2", RandomUtil.getRangeInt(level - lootNPCLevel, level + lootNPCLevel));
				npcMsgData.putStringItem("3", obj.getName());
				String cards = "";
				for(int a = 0; a < obj.getCards().split(";").length; a++){
					DictCard card = DictMap.dictCardMap.get(obj.getCards().split(";")[a] + "");
					cards += card.getId() + "_" + card.getQualityId() + ";";
				}
				npcMsgData.putStringItem("4", cards.substring(0, cards.length() - 1));
				npcsMsgData.putMessageItem("npc" + i, npcMsgData.getMsgData());
			}
		}
		
		retMsgData.putMessageItem("ds", dsMsgData.getMsgData());
		retMsgData.putMessageItem("pc", playersMsgData.getMsgData());
		retMsgData.putMessageItem("npc", npcsMsgData.getMsgData());
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 和平镇
	 * @author hzw
	 * @date 2014-9-4下午5:46:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void lootPeace(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int type = (Integer)msgMap.get("type"); //1-和平牌 2-元宝
		int instPlayerLootId = (Integer)msgMap.get("instPlayerLootId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerLoot instPlayerLoot = getInstPlayerLootDAL().getModel(instPlayerLootId, instPlayerId);
		if (instPlayerLoot.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		if(type == 1){
			List<InstPlayerThing> instPlayerThings = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.peace, instPlayerId);
			if(instPlayerThings.size() <= 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPeace);
				return;
			}else{
				ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThings.get(0), 1, syncMsgData, msgMap);
			}
		}else{
			int gold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootGold);
			if(instPlayer.getGold() < gold){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
				return;
			}
			//扣除元宝
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		String peaceTime = instPlayerLoot.getProtectTime();
		int time = DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootPeaceTime);
		if(peaceTime == null || peaceTime.equals("0") || peaceTime.equals("")){
			peaceTime = DateUtil.getTimeByMill(DateUtil.getCurrMill() + time * 3600 * 1000);
		}else{
			peaceTime = DateUtil.getTimeByMill(DateUtil.getMillSecond(peaceTime) + time * 3600 * 1000);
		}
		instPlayerLoot.setProtectTime(peaceTime);
		getInstPlayerLootDAL().update(instPlayerLoot, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerLoot, instPlayerLoot.getId(), instPlayerLoot.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 抢夺
	 * @author hzw
	 * @date 2014-9-5上午11:37:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void lootWar(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int chipId = (Integer)msgMap.get("chipId");
		int type = (Integer)msgMap.get("type"); //1-PC 2-NPC
		int playerId = (Integer)msgMap.get("playerId"); 
		int instPlayerLootId = (Integer)msgMap.get("instPlayerLootId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		int h = DateUtil.getTimeInfo(DateType.HOUR_OF_DAY);
		if(h >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootCloseStartTime) && 
				h < DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootCloseEndTime) && type == 1){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notLootOpen);
			return;
		}
		
		//先验证自己是不是有这个碎片,如果没有无法抢夺别人的这个碎片
//		List<InstPlayerChip> ownInstPlayerChips = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + chipId, 0);
//		if (ownInstPlayerChips.size() <= 0) {
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_ownNoChip);
//			return;
//		}
		
		
		
		//验证是否有此碎片,如果有这个碎片了,就不能再抢了
		List<InstPlayerChip> ownInstPlayerChips = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + chipId, 0);
		if (ownInstPlayerChips.size() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveThisChip);
			return;
		}
		
		//验证玩家是否有此种类型的碎片,如果没有,除非他是固定可抢的碎片  或  他还有可拼合次数（自己的碎片被别人抢走）
		int typeChipNum = 0;
		DictChip dictChipObj = DictMap.dictChipMap.get(chipId + "");
		int magicId = dictChipObj.getSkillOrKungFuId();
		List<DictChip> magicChipList = (List<DictChip>)DictMapList.dictChipMap.get(magicId);
		for (DictChip obj : magicChipList) {
			int chipObjId = obj.getId();
			int chipObjIdNum = getInstPlayerChipDAL().getCount("instPlayerId = " + instPlayerId + " and chipId = " + chipObjId);
			if (chipObjIdNum > 0) {
				typeChipNum = 1;
			}
		}
//		System.out.println("typeChipNum=" + typeChipNum);
		InstPlayerLoot instPlayerLoot1 = getInstPlayerLootDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId).get(0);
		Map<Integer, Integer> magicCanLootTimesMap = new HashMap<Integer, Integer>();
		for (String loot : instPlayerLoot1.getKungFus().split(";")) {
			magicCanLootTimesMap.put(ConvertUtil.toInt(loot.split("_")[0]), ConvertUtil.toInt(loot.split("_")[1]));
		}
		for (String loot : instPlayerLoot1.getMagics().split(";")) {
			magicCanLootTimesMap.put(ConvertUtil.toInt(loot.split("_")[0]), ConvertUtil.toInt(loot.split("_")[1]));
		}
//		System.out.println("magicCanLootTimesMap=" + magicCanLootTimesMap);
		int canPieceTimes = 0;
		if (magicCanLootTimesMap.containsKey(magicId)) {
			canPieceTimes = magicCanLootTimesMap.get(magicId);
		}
//		System.out.println("canPieceTimes=" + canPieceTimes);
		if (typeChipNum == 0) {
			//当为永久抢的时候不处理
			if (magicId != DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId1) && magicId != DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId2)) {
				if (canPieceTimes == 0) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_ownNoChip);
					return;
				}
			} 
		}
		
		
		if(instPlayer.getLevel() < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.lootLeve)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		if(instPlayer.getVigor() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notVigor);
			return;
		}
		if(type == 1){
			InstPlayerLoot playerLoot = getInstPlayerLootDAL().getList(" instPlayerId = " + playerId, playerId).get(0);
			if(!playerLoot.getProtectTime().equals("0") && DateUtil.getMillSecond(playerLoot.getProtectTime()) >= DateUtil.getMillSecond(DateUtil.getCurrTime())){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notLoot);
				return;
			}
			List<InstPlayerChip> instPlayerChips = getInstPlayerChipDAL().getList("instPlayerId = " + playerId + " and chipId = " + chipId, 0);
			if(instPlayerChips.size() <= 0){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notLootChip);
				return;
			}
		}
		List<InstPlayerChip> instPlayerChips = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + chipId, instPlayerId);
		if(instPlayerChips.size() > 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_lootHavaChip);
			return;
		}
		
		InstPlayerLoot instPlayerLoot = getInstPlayerLootDAL().getModel(instPlayerLootId, instPlayerId);
		if(instPlayerId != instPlayerLoot.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		if(type == 1){
			String peaceTime = instPlayerLoot.getProtectTime();
			int time = DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootPeaceTime);
			if(peaceTime != null && !peaceTime.equals("0") && !peaceTime.equals("")){
				instPlayerLoot.setProtectTime("0");
				getInstPlayerLootDAL().update(instPlayerLoot, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerLoot, instPlayerLoot.getId(), instPlayerLoot.getResult(), syncMsgData);
			}
		}
		
		//处理抢夺得到的银币，经验，扣除的耐力
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + dictLevelProp.getLootFleetCopper()) + "");
		
		//处理战队能达到多少级和剩余经验
		FormulaUtil.calcPlayerLevelExp(instPlayer, instPlayer.getExp() + dictLevelProp.getLootFleetExp(), syncMsgData, msgMap);
		
		if(instPlayer.getVigor() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor) &&  (instPlayer.getVigor() - DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor)) < DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor)){
			instPlayer.setLastVigorRecoverTime(DateUtil.getCurrTime());
		}
		instPlayer.setVigor(instPlayer.getVigor() - DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor));
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//组织敌方玩家战斗相关数据
		retMsgData = EnemyPlayerUtil.retEnemyPlayerInfoMsgData(retMsgData, playerId);
		
		//组织抢夺验证码
		String lootYzm = UUID.randomUUID().toString();
//		System.out.println("lootYzm===============  " + lootYzm);
		ParamConfig.playerLootMap.put(instPlayerId, lootYzm);
		retMsgData.putStringItem("1", lootYzm);
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.skillChipLoot, 1, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 一键抢夺
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked"})
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aKeyLootWar(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		int chipId = (Integer)msgMap.get("chipId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(instPlayer.getLevel() < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.lootLeve)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		//验证是否有此碎片,如果有这个碎片了,就不能再抢了
		List<InstPlayerChip> ownInstPlayerChips = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + chipId, 0);
		if (ownInstPlayerChips.size() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveThisChip);
			return;
		}
		
		//验证玩家是否有此种类型的碎片,如果没有,除非他是固定可抢的碎片  或  他还有可拼合次数（自己的碎片被别人抢走）
		int typeChipNum = 0;
		DictChip dictChipObj = DictMap.dictChipMap.get(chipId + "");
		int magicId = dictChipObj.getSkillOrKungFuId();
		List<DictChip> magicChipList = (List<DictChip>)DictMapList.dictChipMap.get(magicId);
		for (DictChip obj : magicChipList) {
			int chipObjId = obj.getId();
			int chipObjIdNum = getInstPlayerChipDAL().getCount("instPlayerId = " + instPlayerId + " and chipId = " + chipObjId);
			if (chipObjIdNum > 0) {
				typeChipNum = 1;
			}
		}
		InstPlayerLoot instPlayerLoot1 = getInstPlayerLootDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId).get(0);
		Map<Integer, Integer> magicCanLootTimesMap = new HashMap<Integer, Integer>();
		for (String loot : instPlayerLoot1.getKungFus().split(";")) {
			magicCanLootTimesMap.put(ConvertUtil.toInt(loot.split("_")[0]), ConvertUtil.toInt(loot.split("_")[1]));
		}
		for (String loot : instPlayerLoot1.getMagics().split(";")) {
			magicCanLootTimesMap.put(ConvertUtil.toInt(loot.split("_")[0]), ConvertUtil.toInt(loot.split("_")[1]));
		}
		int canPieceTimes = 0;
		if (magicCanLootTimesMap.containsKey(magicId)) {
			canPieceTimes = magicCanLootTimesMap.get(magicId);
		}
		if (typeChipNum == 0) {
			//当为永久抢的时候不处理
			if (magicId != DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId1) && magicId != DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId2)) {
				if (canPieceTimes == 0) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_ownNoChip);
					return;
				}
			} 
		}
		
		DictChip dictChip = DictMap.dictChipMap.get(chipId + "");
		int quality = 0;
		if(dictChip.getType() == 1){
			quality = DictMap.dictManualSkillMap.get(dictChip.getSkillOrKungFuId() + "").getQuality();
		}else{
			quality = DictMap.dictMagicMap.get(dictChip.getSkillOrKungFuId() + "").getMagicQualityId();
		}
		InstPlayerLoot instPlayerLoot = getInstPlayerLootDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId).get(0);
		List<DictLootChip> dictLootChips = (List<DictLootChip>)DictMapList.dictLootChipMap.get(quality);
		int count = 0;
		for(DictLootChip obj : dictLootChips){
			if(obj.getType() == dictChip.getType()){
				int currentValue = instPlayer.getVigor();
				int step = DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor);
				boolean finalflag = false;
				while (currentValue >= step) {
					count++;
					currentValue-=step;
					float random = RandomUtil.getRandomFloat();
					boolean flag = false; //true-表示抢到碎片  false-没抢到
					float lootRandom = obj.getLootNPC();
					for(int i = 1;i <= instPlayerLoot.getLootNum()+count - DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootNum); i++){
						lootRandom = lootRandom * obj.getLootAdd();
					}
					if(random <= lootRandom){
						flag = true;
					}
					finalflag = finalflag||flag;
					if(finalflag){
						break;
					}
				}
				if(finalflag){
					LootUtil.addInstPlayerChip(instPlayerId, chipId, 1, syncMsgData, msgMap);
					retMsgData.putIntItem("1", 1);
					instPlayerLoot.setLootNum(0);
				}else{
					retMsgData.putIntItem("1", 0);
				}
			}
		}
		getInstPlayerLootDAL().update(instPlayerLoot, instPlayerId);
		if(instPlayer.getVigor() >= DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor) &&  (instPlayer.getVigor() - DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor)) < DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor)){
			instPlayer.setLastVigorRecoverTime(DateUtil.getCurrTime());
		}
		instPlayer.setVigor(instPlayer.getVigor() - count*DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootVigor));
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.skillChipLoot, count, syncMsgData);
		//碰撞100次
		List<DictLootDrop> dropList = new ArrayList<DictLootDrop>();
		StringBuilder result = new StringBuilder();
		for (int x = 0; x < 100; x++) {
			List<DictLootDrop> dictLootDropList = (List<DictLootDrop>)DictList.dictLootDropList;
			List<DictLootDrop> dictLootDrops = new ArrayList<>();
			for(DictLootDrop obj : dictLootDropList){
				dictLootDrops.add(obj);
			}
			
			Float num = 0.0f;
			for(DictLootDrop obj : dictLootDrops){
				num += obj.getChance();
			}
			for(int i = 0; i < count; i++){
				float random = RandomUtil.getRandomFloat();
				Float sum = 0.00f;
				for(DictLootDrop obj : dictLootDrops){
					sum += obj.getChance() / num;
					if(random <= sum){
						dropList.add(obj);
						dictLootDrops.remove(obj);
						num = num - obj.getChance();
						result.append((i+1)+":"+obj.getTableTypeId()+"_"+obj.getTableFieldId()+"_"+obj.getValue()+"|");
						break;
					}
				}
			}
			boolean tempFlag = false;
			for(DictLootDrop tempDrop:dropList){
				if (tempDrop.getTableTypeId() == StaticTableType.DictChip && tempDrop.getTableFieldId() == chipId) {
					tempFlag = true;
					break;
				}
			}
			if(tempFlag){
				dropList.clear();
				result.setLength(0);
				continue;
			}else{
				break;
			}
		}
		
		//或得的物品
		Map<String, String> thingMap = new HashMap<String, String>();
		if(dropList!=null&&dropList.size()>0){
			for(DictLootDrop tempDrop:dropList){
				ThingUtil.groupThingMap(thingMap, tempDrop.getTableTypeId(), tempDrop.getTableFieldId(), tempDrop.getValue());
//				ThingUtil.groupThing(instPlayer, tempDrop.getTableTypeId(), tempDrop.getTableFieldId(), tempDrop.getValue(), syncMsgData, msgMap);
			}
		}
		DictLevelProp dictLevelProp = DictMap.dictLevelPropMap.get(instPlayer.getLevel() + "");
		ThingUtil.groupThingMap(thingMap, StaticTableType.DictPlayerBaseProp, StaticPlayerBaseProp.copper, dictLevelProp.getLootFleetCopper() * count);
		
		ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);
		
		retMsgData.putStringItem("2", result.toString());
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 抢夺胜利
	 * @author hzw
	 * @date 2014-9-5下午4:55:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void lootWarWin(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int chipId = (Integer)msgMap.get("chipId");
		int type = (Integer)msgMap.get("type"); //1-PC 2-NPC
		int playerId = (Integer)msgMap.get("playerId");
		String step = (String)msgMap.get("step");
//		String coredata = (String)msgMap.get("coredata");//1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝
		String yzm = (String)msgMap.get("yzm");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if(instPlayer.getLevel() < DictMapUtil.getSysConfigFloatValue(StaticSysConfig.lootLeve)){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		
		//验证是否有此碎片,如果有这个碎片了,就不能再抢了
		List<InstPlayerChip> ownInstPlayerChips = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + chipId, 0);
		if (ownInstPlayerChips.size() > 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_haveThisChip);
			return;
		}
		
		//验证玩家是否有此种类型的碎片,如果没有,除非他是固定可抢的碎片  或  他还有可拼合次数（自己的碎片被别人抢走）
		int typeChipNum = 0;
		DictChip dictChipObj = DictMap.dictChipMap.get(chipId + "");
		int magicId = dictChipObj.getSkillOrKungFuId();
		List<DictChip> magicChipList = (List<DictChip>)DictMapList.dictChipMap.get(magicId);
		for (DictChip obj : magicChipList) {
			int chipObjId = obj.getId();
			int chipObjIdNum = getInstPlayerChipDAL().getCount("instPlayerId = " + instPlayerId + " and chipId = " + chipObjId);
			if (chipObjIdNum > 0) {
				typeChipNum = 1;
			}
		}
//		System.out.println("typeChipNum=" + typeChipNum);
		InstPlayerLoot instPlayerLoot1 = getInstPlayerLootDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId).get(0);
		Map<Integer, Integer> magicCanLootTimesMap = new HashMap<Integer, Integer>();
		for (String loot : instPlayerLoot1.getKungFus().split(";")) {
			magicCanLootTimesMap.put(ConvertUtil.toInt(loot.split("_")[0]), ConvertUtil.toInt(loot.split("_")[1]));
		}
		for (String loot : instPlayerLoot1.getMagics().split(";")) {
			magicCanLootTimesMap.put(ConvertUtil.toInt(loot.split("_")[0]), ConvertUtil.toInt(loot.split("_")[1]));
		}
//		System.out.println("magicCanLootTimesMap=" + magicCanLootTimesMap);
		int canPieceTimes = 0;
		if (magicCanLootTimesMap.containsKey(magicId)) {
			canPieceTimes = magicCanLootTimesMap.get(magicId);
		}
//		System.out.println("canPieceTimes=" + canPieceTimes);
		if (typeChipNum == 0) {
			//当为永久抢的时候不处理
			if (magicId != DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId1) && magicId != DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId2)) {
				if (canPieceTimes == 0) {
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_ownNoChip);
					return;
				}
			} 
		}
		
		//先验证自己是不是有这个碎片,如果没有无法抢夺别人的这个碎片
//		List<InstPlayerChip> ownInstPlayerChips = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + chipId, 0);
//		if (ownInstPlayerChips.size() <= 0) {
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_ownNoChip);
//			return;
//		}
		
		String playerYzm = ParamConfig.playerLootMap.get(instPlayerId);
//		System.out.println("playerYzm==========   " + playerYzm + "    client yzm =========" + yzm);
		if (playerYzm == null || playerYzm.equals("")) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		if (!playerYzm.equals(yzm)) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		} else {
			ParamConfig.playerLootMap.put(instPlayerId, "");//重置
		}
//		System.out.println("after====" + ParamConfig.playerLootMap.get(instPlayerId));
		
		//是否在强碎片限制时间内
		if (type == 1) {
			int h = DateUtil.getTimeInfo(DateType.HOUR_OF_DAY);
			if (DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootCloseStartTime) <= h && h <= DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootCloseEndTime)) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_LootNoTime);
				return;
			}
		}
		
		//用于验证玩家是否利用烧饼等修改器
//		if(VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)){
//			return;
//		}
		DictChip dictChip = DictMap.dictChipMap.get(chipId + "");
		int quality = 0;
		if(dictChip.getType() == 1){
			quality = DictMap.dictManualSkillMap.get(dictChip.getSkillOrKungFuId() + "").getQuality();
		}else{
			quality = DictMap.dictMagicMap.get(dictChip.getSkillOrKungFuId() + "").getMagicQualityId();
		}
		InstPlayerLoot instPlayerLoot = getInstPlayerLootDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId).get(0);
		instPlayerLoot.setLootNum(instPlayerLoot.getLootNum() + 1);
		List<DictLootChip> dictLootChips = (List<DictLootChip>)DictMapList.dictLootChipMap.get(quality);
		for(DictLootChip obj : dictLootChips){
			if(obj.getType() == dictChip.getType()){
				float random = RandomUtil.getRandomFloat();
				boolean flag = false; //true-表示抢到碎片  false-没抢到
				if(type == 1){
					float lootRandom = obj.getLootPlayer();
					if(instPlayer.getLevel() > getInstPlayerDAL().getModel(instPlayerId, instPlayerId).getLevel()){
						lootRandom = obj.getLootPlayer2();
					}
					for(int i = 1;i <= instPlayerLoot.getLootNum() - DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootNum); i++){
						lootRandom = lootRandom * obj.getLootAdd();
					}
					if(random <= lootRandom){
						flag = true;
						List<InstPlayerChip> instPlayerChipList = getInstPlayerChipDAL().getList("instPlayerId = " + playerId + " and chipId = " + chipId, 0);
						if(instPlayerChipList.size() > 0){
							InstPlayerChip instPlayerChip = instPlayerChipList.get(0);
							MessageData otherSyncMsgData = new MessageData();
							if(instPlayerChip.getNum() - 1 == 0){
								getInstPlayerChipDAL().deleteById(instPlayerChip.getId(), playerId);
								OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerChip, instPlayerChip.getId(), "", otherSyncMsgData);
							}else{
								instPlayerChip.setNum(instPlayerChip.getNum() - 1);
								getInstPlayerChipDAL().update(instPlayerChip, playerId);
								OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerChip, instPlayerChip.getId(), instPlayerChip.getResult(), otherSyncMsgData);
							}
							Player player = PlayerMapUtil.getPlayerByPlayerId(playerId);
							if(player != null){
								MessageUtil.sendSyncMsgToOne(player.getOpenId(), otherSyncMsgData);
							}
						}else{
							flag = false;
						}
					}
				}else{
					float lootRandom = obj.getLootNPC();
					for(int i = 1;i <= instPlayerLoot.getLootNum() - DictMapUtil.getSysConfigIntValue(StaticSysConfig.lootNum); i++){
						lootRandom = lootRandom * obj.getLootAdd();
					}
					if(random <= lootRandom){
						flag = true;
					}
				}
				//引导必掉
				if(step.equals("20B6")){
					flag = true;
				}
				if(flag){
					LootUtil.addInstPlayerChip(instPlayerId, chipId, 1, syncMsgData, msgMap);
					retMsgData.putIntItem("1", chipId);
					instPlayerLoot.setLootNum(0);
					
					//发邮件 -老版本
//					ActivityUtil.addInstPlayerMail(playerId, 1, instPlayer.getName(), chipId);
/*
 * 正式需求
 */
					//只有抢玩家的时候才发邮件
					if (type == 1) {
						Player player = PlayerMapUtil.getPlayerByPlayerId(playerId);//int instPlayerId, int type, String enemyName, int value
						PlayerUtil.addPlayerMail(player, playerId, "", chipId, 0, instPlayer.getName(), 1);
					}
				}
			}
		}
		getInstPlayerLootDAL().update(instPlayerLoot, instPlayerId);
		
		//碰撞100次
		String things = "";
		DictLootDrop dictLootDrop = null;
		
		for (int x = 0; x < 100; x++) {
			things = "";
			dictLootDrop = null;
			List<DictLootDrop> dictLootDropList = (List<DictLootDrop>)DictList.dictLootDropList;
			List<DictLootDrop> dictLootDrops = new ArrayList<>();
			for(DictLootDrop obj : dictLootDropList){
				dictLootDrops.add(obj);
			}
			
			Float num = 0.0f;
			for(DictLootDrop obj : dictLootDrops){
				num += obj.getChance();
			}
			for(int i = 0; i < 3; i++){
				float random = RandomUtil.getRandomFloat();
				Float sum = 0.00f;
				for(DictLootDrop obj : dictLootDrops){
					sum += obj.getChance() / num;
					if(random <= sum){
						if(i == 0){
							dictLootDrop = obj;
						}
						things += obj.getId() + ";";
						dictLootDrops.remove(obj);
						num = num - obj.getChance();
						break;
					}
				}
			}
			if (dictLootDrop.getTableTypeId() == StaticTableType.DictChip && dictLootDrop.getTableFieldId() == chipId) {
				continue;
			} else {
				break;
			}
		}
		
		ThingUtil.groupThing(instPlayer, dictLootDrop.getTableTypeId(), dictLootDrop.getTableFieldId(), dictLootDrop.getValue(), syncMsgData, msgMap);
		
		retMsgData.putStringItem("2", things.substring(0, things.length() - 1));
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 抢夺拼合
	 * @author hzw
	 * @date 2014-9-6下午2:43:27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void lootPiece(Map<String, Object> msgMap, String channelId) throws Exception{
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerLootId = (Integer)msgMap.get("instPlayerLootId");
		int skillOrKungFuId = (Integer)msgMap.get("skillOrKungFuId");
		int type = (Integer)msgMap.get("type");    //1-技能 2-功法3-法宝
		
		if (type < 2 || type > 3) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_paramError);
			return;
		}
		
		InstPlayerLoot instPlayerLoot = getInstPlayerLootDAL().getModel(instPlayerLootId, instPlayerId);
		if(instPlayerId != instPlayerLoot.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		List<DictChip> dictChips = (List<DictChip>)DictMapList.dictChipMap.get(skillOrKungFuId);
		for(DictChip dictChip : dictChips){
			if(dictChip.getType() == type){
				List<InstPlayerChip> instPlayerChipList = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId + " and chipId = " + dictChip.getId(), instPlayerId);
				if(instPlayerChipList.size() <= 0){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notLootHavaChip);
					return;
				}
			}
		}
		
		for(DictChip dictChip : dictChips){
			if(dictChip.getType() == type){
				LootUtil.updateInstPlayerChip(instPlayerId, dictChip.getId(), 1, syncMsgData);
			}
		}
		boolean tag = false;
		if(type == 1){
			//技能添加到玩家技能实例表
			InstPlayerManualSkill instPlayerManualSkill = ManualSkillUtil.addInstPlayerManualSkill(instPlayerId, skillOrKungFuId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerManualSkill, instPlayerManualSkill.getId(), instPlayerManualSkill.getResult(), syncMsgData);
			
//			if(skillOrKungFuId != 1 && skillOrKungFuId != 2){
				//更新抢夺实例表
				String skills = instPlayerLoot.getSkills();
				String skillAr[] = skills.split(";");
				skills = StringUtil.firstLastAddChar(skills, ";");
				for(int i = 0; i < skillAr.length; i++){
					String skill = skillAr[i];
					if(ConvertUtil.toInt(skill.split("_")[0]) == skillOrKungFuId){
						int num = ConvertUtil.toInt(skill.split("_")[1]);
						skill = StringUtil.firstLastAddChar(skill, ";");
						if(num > 1){
							skills = skills.replace(skill,  skill.split("_")[0] + "_" + (num - 1) + ";");
						}else{
							if(skillAr.length == 1){
								skills = skills.replace(skill, "");
							}else{
								skills = skills.replace(skill, ";");
							}
						}
					}
				}
				if(skills.length() > 0){
					skills = skills.substring(1, skills.length() - 1);
				}
				instPlayerLoot.setSkills(skills);
				tag = true;
//			}
		}else{
			
			//跑马灯相关
			MarqueeUtil.putMsgToMarquee(channelId, skillOrKungFuId + "", CustomMarqueeType.MARQUEE_TYPE_MAGIC, CustomMarqueeType.MARQUEE_SOURCE_CHIPJOIN);
			
			//功法添加到玩家功法实例表
			InstPlayerMagic instPlayerMagic = new InstPlayerMagic();
			DictMagic dictMagic = DictMap.dictMagicMap.get(skillOrKungFuId + "");
			instPlayerMagic.setInstPlayerId(instPlayerId);
			instPlayerMagic.setMagicId(skillOrKungFuId);
			instPlayerMagic.setMagicType(dictMagic.getType());
			instPlayerMagic.setQuality(dictMagic.getMagicQualityId());
			instPlayerMagic.setMagicLevelId(dictMagic.getMagicLevelId());
			instPlayerMagic.setExp(0);
			instPlayerMagic.setInstCardId(0);
			getInstPlayerMagicDAL().add(instPlayerMagic, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerMagic, instPlayerMagic.getId(), instPlayerMagic.getResult(), syncMsgData);
			
			//验证法宝功法成就
			if (dictMagic.getType() == 1) {
				AchievementUtil.magic1(instPlayerId, syncMsgData);
			} else if (dictMagic.getType() == 2) {
				AchievementUtil.magic2(instPlayerId, syncMsgData);
			}
			
//			if(skillOrKungFuId != 1 && skillOrKungFuId != 2){
				//更新抢夺实例表
				if(type == 2 && skillOrKungFuId != DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId1)){
					String kungFus = instPlayerLoot.getKungFus();
					String kungFuAr[] = kungFus.split(";");
					kungFus = StringUtil.firstLastAddChar(kungFus, ";");
					for(int i = 0; i < kungFuAr.length; i++){
						String kungFu = kungFuAr[i];
						if(ConvertUtil.toInt(kungFu.split("_")[0]) == skillOrKungFuId){
							int num = ConvertUtil.toInt(kungFu.split("_")[1]);
							kungFu = StringUtil.firstLastAddChar(kungFu, ";");
							if(num > 1){
								kungFus = kungFus.replace(kungFu, kungFu.split("_")[0] + "_" + (num - 1) + ";");
							}else{
								if(kungFuAr.length == 1){
									kungFus = kungFus.replace(kungFu, "");
								}else{
									kungFus = kungFus.replace(kungFu, ";");
								}
							}
						}
					}
					if(kungFus.length() > 0){
						kungFus = kungFus.substring(1, kungFus.length() - 1);
					}
					instPlayerLoot.setKungFus(kungFus);
				}
				if(type == 3 && skillOrKungFuId != DictMapUtil.getSysConfigIntValue(StaticSysConfig.magicId2)){
					String magics = instPlayerLoot.getMagics();
					String magicAr[] = magics.split(";");
					magics = StringUtil.firstLastAddChar(magics, ";");
					for(int i = 0; i < magicAr.length; i++){
						String magic = magicAr[i];
						if(ConvertUtil.toInt(magic.split("_")[0]) == skillOrKungFuId){
							int num = ConvertUtil.toInt(magic.split("_")[1]);
							magic = StringUtil.firstLastAddChar(magic, ";");
							if(num > 1){
								magics = magics.replace(magic, magic.split("_")[0] + "_" + (num - 1) + ";");
							}else{
								if(magicAr.length == 1){
									magics = magics.replace(magic, "");
								}else{
									magics = magics.replace(magic, ";");
								}
							}
						}
					}
					if(magics.length() > 0){
						magics = magics.substring(1, magics.length() - 1);
					}
					instPlayerLoot.setMagics(magics);
				}
				tag = true;
//			}
		}
		if(tag){
			getInstPlayerLootDAL().update(instPlayerLoot, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerLoot, instPlayerLoot.getId(), instPlayerLoot.getResult(), syncMsgData);
		}
		
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	@Override
	public void test() throws Exception {
		// TODO Auto-generated method stub
		try {
			MessageData syncMsgData = new MessageData();
//			LootUtil.addInstPlayerChip(823, 297, 1, syncMsgData);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("_____________________________");
		}
	}

}
