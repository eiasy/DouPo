package com.huayi.doupo.logic.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.doupo.activity.comparator.ComparatorAdvanceCard;
import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.DictAdvance;
import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.DictConstell;
import com.huayi.doupo.base.model.DictPill;
import com.huayi.doupo.base.model.DictQuality;
import com.huayi.doupo.base.model.DictRestore;
import com.huayi.doupo.base.model.DictTitleDetail;
import com.huayi.doupo.base.model.DictTrainProp;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerArena;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerCardSoul;
import com.huayi.doupo.base.model.InstPlayerConstell;
import com.huayi.doupo.base.model.InstPlayerFormation;
import com.huayi.doupo.base.model.InstPlayerKungFu;
import com.huayi.doupo.base.model.InstPlayerPill;
import com.huayi.doupo.base.model.InstPlayerRecruit;
import com.huayi.doupo.base.model.InstPlayerRestoreTemp;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.InstPlayerTrain;
import com.huayi.doupo.base.model.InstPlayerTrainTemp;
import com.huayi.doupo.base.model.InstPlayerYFire;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticCustomDict;
import com.huayi.doupo.base.model.statics.StaticDailyTask;
import com.huayi.doupo.base.model.statics.StaticFightProp;
import com.huayi.doupo.base.model.statics.StaticPillType;
import com.huayi.doupo.base.model.statics.StaticPlayerBaseProp;
import com.huayi.doupo.base.model.statics.StaticQuality;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.custom.CustomMarqueeType;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.CollectionUtil;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.AchievementUtil;
import com.huayi.doupo.logic.handler.util.CardUtil;
import com.huayi.doupo.logic.handler.util.FightSoulUtil;
import com.huayi.doupo.logic.handler.util.FormulaUtil;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.PillUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.YFireUtil;
import com.huayi.doupo.logic.handler.util.marquee.MarqueeUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * 卡牌处理类
 * @author hzw
 * @date 2014-6-23上午10:22:36
 */
public class CardHandler extends BaseHandler{

	/**
	 * 吃卡
	 * @author hzw
	 * @date 2014-6-26下午2:02:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteCard(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int eatInstCardId = (Integer)msgMap.get("eatInstCardId");
		String instCardIdList = (String)msgMap.get("instCardIdList");
		String[] paramList = instCardIdList.split(";");
		int expsum = 0;
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerCard eatInstPlayerCard = getInstPlayerCardDAL().getModel(eatInstCardId, instPlayerId);
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		if(instPlayerId != eatInstPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
	
		for(String str : paramList){
			InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			
			if (str.equals("") || instPlayerCard == null) {
				continue;
			}
			
			if(instPlayerId != instPlayerCard.getInstPlayerId()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if(eatInstCardId == ConvertUtil.toInt(str)){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardOneself);
				return;
			}
			if(instPlayerCard.getInTeam() == StaticCustomDict.inTeam){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardListInFormation);
				return;
			}
			expsum += FormulaUtil.eatCardExp(instPlayerCard.getLevel() > 1 ? instPlayerCard.getLevel() - 1 : instPlayerCard.getLevel()) + instPlayerCard.getExp();
		}
		 
		//更新吃卡卡牌的属性
		
		//等级,经验计算
		Map<String, Integer> retMap = FormulaUtil.calcLevel(eatInstPlayerCard.getLevel(), eatInstPlayerCard.getExp() + expsum, instPlayer.getLevel());
		int level = retMap.get("level");
		int exp = retMap.get("exp");
		int potential = retMap.get("potential");
		
		eatInstPlayerCard.setLevel(level);
		eatInstPlayerCard.setExp(exp);
		eatInstPlayerCard.setPotential(eatInstPlayerCard.getPotential() + potential);
		getInstPlayerCardDAL().update(eatInstPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, eatInstPlayerCard, eatInstPlayerCard.getId(), eatInstPlayerCard.getResult(), syncMsgData);
		
		//删除被吃卡牌
		for(String str : paramList){
			InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			if (str.equals("") || instPlayerCard == null) {
				continue;
			}
			
			//如果有斗魂，将斗魂卸下
			int instCardId = instPlayerCard.getId();
			FightSoulUtil.dropFightSoulIfCardExsits(instPlayerId, instCardId, syncMsgData);
			
			CardUtil.deletePlayerCard(instPlayerId, instPlayerCard, syncMsgData);
		}
		
		//扣除银币
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - expsum * DictMapUtil.getSysConfigIntValue(StaticSysConfig.eatExpCopper)) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.cardStrength, 1, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 卡牌魂魄召唤
	 * @author mp
	 * @date 2014-7-3 下午5:09:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cardSoulCall(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardSoulId = (Integer)msgMap.get("instPlayerCardSoulId");
		InstPlayerCardSoul instPlayerCardSoul = getInstPlayerCardSoulDAL().getModel(instPlayerCardSoulId, instPlayerId);
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if (step != null && !step.equals("")) {
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		//验证玩家是否一致
		if (instPlayerCardSoul.getInstPlayerId() != instPlayerId ) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//计算召唤所需魂魄数
		DictCard dictCard = DictMap.dictCardMap.get(instPlayerCardSoul.getCardId() + "");
		DictQuality dictQuality = DictMap.dictQualityMap.get(dictCard.getQualityId() + "");
		int needSouldNum = dictQuality.getSoulNum();
		
		//验证数量是否充足
		if (instPlayerCardSoul.getNum() < needSouldNum) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_numNotEnough);
			return;
		}
		
		//减去消耗的魂魄
		if(instPlayerCardSoul.getNum() - needSouldNum <= 0){
			getInstPlayerCardSoulDAL().deleteById(instPlayerCardSoulId, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerCardSoul, instPlayerCardSoul.getId(), "", syncMsgData);
		}else{
			instPlayerCardSoul.setNum(instPlayerCardSoul.getNum() - needSouldNum);
			getInstPlayerCardSoulDAL().update(instPlayerCardSoul, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCardSoul, instPlayerCardSoul.getId(), instPlayerCardSoul.getResult(), syncMsgData);
		}
		
		//将召唤的卡牌放入卡牌背包中
		InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, instPlayerCardSoul.getCardId());
		
		//初始化玩家卡牌命宫实例表
		if (!dictCard.getConstells().equals("")) {
		String constells[] = dictCard.getConstells().split(";");
			String instPlayerConstells = "";
			for(String constell : constells){
				DictConstell dictConstell = DictMap.dictConstellMap.get(ConvertUtil.toInt(constell) + "");
				InstPlayerConstell obj = PillUtil.initPlayerConstell(instPlayerId, instPlayerCard.getId(), ConvertUtil.toInt(constell), dictConstell.getPills().split(";").length);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, obj, obj.getId(), obj.getResult(), syncMsgData);
				instPlayerConstells += obj.getId() + ";";
			}
			instPlayerCard.setInstPlayerConstells(instPlayerConstells.substring(0, instPlayerConstells.length() - 1));
			getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		}
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 卡牌修炼
	 * @author hzw
	 * @date 2014-7-8上午11:22:23
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void train(Map<String, Object> msgMap, String channelId) throws Exception {

		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId"); 
		int trainPropId = (Integer)msgMap.get("trainPropId");
		int times = (Integer)msgMap.get("times");
		String fightProps = (String)msgMap.get("fightProps");
		
		if (times <= 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			return;
		}
		
		Map<Integer, Integer> fightsMap = new HashMap<>();
		if (fightProps != null && !fightProps.equals("")) {
			for (String fightObj : fightProps.split(";")) {
				int fightPropId = ConvertUtil.toInt(fightObj.split("_")[0]);
				int fightValue = ConvertUtil.toInt(fightObj.split("_")[1]);
				fightsMap.put(fightPropId, fightValue);
			}
		}
//		System.out.println(fightsMap);
		
//		times = 1;
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证上限-防止整数溢出
		int buyUpLimit = DictMapUtil.getSysConfigIntValue(StaticSysConfig.buyUpLimit);
		if (times > buyUpLimit) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyUpLimit + " " + buyUpLimit);
			return;
		}

		DictTrainProp dictTrainProp = DictMap.dictTrainPropMap.get(trainPropId + "");
		List<InstPlayerThing> lindenList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.linden, instPlayerId);
		int dLindenNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.trainLinden) * times;     //消耗的总菩提子个数
		int gold = dictTrainProp.getGold() * times;
		if(lindenList.size() <= 0){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notlindenLNum);
			return;
		}
		if(lindenList.size() > 0){
			InstPlayerThing potential = lindenList.get(0);
			if(potential.getNum() < dLindenNum){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notlindenLNum);
				return;
			}
			
			//处理消耗的菩提子
			ThingUtil.updateInstPlayerThing(instPlayerId, potential, dLindenNum, syncMsgData, msgMap);
		}
		
/*		if(instPlayer.getGold() < gold){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		//处理消耗的元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, gold, msgMap);
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);*/
		
		//删除上次遗留的临时修炼数据
		List<InstPlayerTrainTemp>  instPlayerTrainTempList = getInstPlayerTrainTempDAL().getList("instPlayerCardId = " + instPlayerCardId, 0);
		for(InstPlayerTrainTemp instPlayerTrainTemp : instPlayerTrainTempList){
			getInstPlayerTrainTempDAL().deleteById(instPlayerTrainTemp.getId(), 0);
		}
		
		List<InstPlayerTrain> instPlayerTrainList = getInstPlayerTrainDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerCardId = " + instPlayerCardId, instPlayerId);
		int blood = FormulaUtil.CardChangValue(instPlayerCard, StaticFightProp.blood);
		int wAttack = FormulaUtil.CardChangValue(instPlayerCard, StaticFightProp.wAttack);
		int wDefense = FormulaUtil.CardChangValue(instPlayerCard, StaticFightProp.wDefense);
		int fAttack = FormulaUtil.CardChangValue(instPlayerCard, StaticFightProp.fAttack);
		int fDefense = FormulaUtil.CardChangValue(instPlayerCard, StaticFightProp.fDefense);
		
		
		
		for(InstPlayerTrain obj : instPlayerTrainList){
			if(obj.getFightPropId() == StaticFightProp.blood){
				blood += obj.getFightPropValue() * DictMapUtil.getSysConfigFloatValue(StaticSysConfig.bloodFactor);
			}else if(obj.getFightPropId() == StaticFightProp.wAttack){
				wAttack += obj.getFightPropValue() * DictMapUtil.getSysConfigFloatValue(StaticSysConfig.wAttackFactor);
			}else if(obj.getFightPropId() == StaticFightProp.wDefense){
				wDefense += obj.getFightPropValue() * DictMapUtil.getSysConfigFloatValue(StaticSysConfig.wDefenseFactor);
			}else if(obj.getFightPropId() == StaticFightProp.fAttack){
				fAttack += obj.getFightPropValue() * DictMapUtil.getSysConfigFloatValue(StaticSysConfig.fAttackFactor);
			}else if(obj.getFightPropId() == StaticFightProp.fDefense){
				fDefense += obj.getFightPropValue() * DictMapUtil.getSysConfigFloatValue(StaticSysConfig.fDefenseFactor);
			}
		}
		
//		System.out.println("blood" + blood + "  wAttack" + wAttack + " wDefense" + wDefense + " fAttack" + " fDefense" + fDefense);
		
		Map<Integer, Integer> fightPropMap = new HashMap<Integer, Integer>();
		fightPropMap.put(1, blood);
		fightPropMap.put(2, wAttack);
		fightPropMap.put(3, wDefense);
		fightPropMap.put(4, fAttack);
		fightPropMap.put(5, fDefense);
		
//		System.out.println("==========" + fightPropMap);
		
		List<Integer> list = RandomUtil.getRandomNoRepeat(dictTrainProp.getPropertyNum(), fightPropMap.size());
		
		int potential = instPlayerCard.getPotential();//已有潜力点
		int dPotentialNum = 0;    //消耗的总潜力点
		int propertys[] = new int[dictTrainProp.getPropertyNum()]; //最终修炼的属性结果值
		
		int total = 0;
//		for(int i = 1; i <= times; i++){
			int maxTotal = 0;  //潜力点消耗的最大上限
			int minTotal = 0;  //潜力点消耗的最小上限
			if(potential > 0){
				minTotal = dictTrainProp.getTrainDownLimit();
				maxTotal = dictTrainProp.getTrainUpLimit();
				if(potential - dPotentialNum < dictTrainProp.getTrainDownLimit()){
					if(potential - dPotentialNum > 0){
						minTotal = potential - dPotentialNum;
						maxTotal = potential - dPotentialNum;
					}else{
						minTotal = 0;
						maxTotal = 0;
					}
				}
				else if(potential - dPotentialNum < dictTrainProp.getTrainUpLimit()){
					minTotal = dictTrainProp.getTrainDownLimit();
					maxTotal = potential - dPotentialNum;
				}
			}
			
			
			total = RandomUtil.getRangeInt(minTotal, maxTotal);   //当前消耗的潜力点
			
			dPotentialNum += total;
			int tempPropertys[] = FormulaUtil.Train(total, dictTrainProp.getChangeDownLimit(), dictTrainProp.getChangeUpLimit(), dictTrainProp.getPropertyNum());
			int addtemp = 0;//记录最小的大于零的值大于零的值(fightPropMap.get(v) + tempPropertys[list.indexOf(v)])
			for(int i = 0; i< 100; i++){
				int vn = 0;
				if(vn / 10000 >= 1){
					list = RandomUtil.getRandomNoRepeat(dictTrainProp.getPropertyNum(), fightPropMap.size());
				}
				for(int v : list){
					if(fightPropMap.get(v) + tempPropertys[list.indexOf(v)] >= 0){
						vn ++;
						addtemp = fightPropMap.get(v) + tempPropertys[list.indexOf(v)];
					}
				}
				if(vn == list.size()){
					break;
				}
				tempPropertys = CollectionUtil.getSequence(tempPropertys);
			}
			
		//经过自己测试行不通
			Map<Integer, Integer> del = new HashMap<Integer, Integer>(); //<属性Id, 小于零的值(fightPropMap.get(v) + tempPropertys[list.indexOf(v)])>记录小于零的属性
			Map<Integer, Integer> add = new HashMap<Integer, Integer>(); //<属性Id, 大于零的值(fightPropMap.get(v) + tempPropertys[list.indexOf(v)])>记录大于零的属性
			
			//处理出现小于0的情况
			for(int v : list){
				int i = fightPropMap.get(v) + tempPropertys[list.indexOf(v)];
				if(i < 0){
					del.put(v, tempPropertys[list.indexOf(v)] - i);
				}else{
					add.put(v, i);
					if(i < addtemp){
						addtemp = i;
					}
				}
			}
			
			//用来保证单个属性的范围
			if(addtemp > dictTrainProp.getChangeUpLimit()){
				addtemp = dictTrainProp.getChangeUpLimit();
			}
			
			if(del.size() > 0){
				for(Entry<Integer, Integer> map : del.entrySet()){
					if(add.size() == 1){
						addtemp = addtemp / del.size();
					}
					int random = RandomUtil.getRangeInt(map.getValue(), addtemp);
					tempPropertys[RandomUtil.getRandomInt(add.size())] -= (random - tempPropertys[map.getKey()]);// 由于属性值变化和是固定的，所以小于0的加值了，大于0的就要减值
					tempPropertys[map.getKey()] = random; //给小于0的重新赋值
				}
			}
			
			
			if(times > 1 && potential > 0){
				times = potential/total < times ? potential/total : times;
			}
			
			for(int a = 0; a < tempPropertys.length; a++){
//				propertys[a] += tempPropertys[a];
				propertys[a] = tempPropertys[a] * times;
			}
//		}
	    
		//组织map拦截记录结果
		Map<Integer, Integer> ljMap = new HashMap<>();
		Map<Integer, Integer> jiaMap = new HashMap<>();
		Map<Integer, Integer> jianMap = new HashMap<>();
		for(int v : list){
			int fightPropId = StaticFightProp.blood;
			if(v == 1){
				fightPropId = StaticFightProp.blood;
			}else if(v == 2){
				fightPropId = StaticFightProp.wAttack;
			}else if(v == 3){
				fightPropId = StaticFightProp.wDefense;
			}else if(v == 4){
				fightPropId = StaticFightProp.fAttack;
			}else if(v == 5){
				fightPropId = StaticFightProp.fDefense;
			}
			ljMap.put(fightPropId, propertys[list.indexOf(v)]);
			if (propertys[list.indexOf(v)] >= 0) {
				jiaMap.put(fightPropId, propertys[list.indexOf(v)]);
			} else {
				jianMap.put(fightPropId, propertys[list.indexOf(v)]);
			}
		}
//		System.out.println("ljMap = " + ljMap);
//		System.out.println("jiaMap = " + jiaMap);
//		System.out.println("jianMap = " + jianMap);
		
		int sumJianValue = 0;//需要从加map上减去该减去的值
		Map<Integer, Integer> trueJianMap = new HashMap<>();//真正的减法map
		for (Entry<Integer, Integer> entry : jianMap.entrySet()) {
			int fightPId = entry.getKey();
			int fightVa = entry.getValue();
			float trainBl = CardUtil.getTrainBl(fightPId);
			int oldValue = fightsMap.get(fightPId);
			int convAfterTalValue = (int)((oldValue) / trainBl);
			if ((convAfterTalValue + fightVa) < 0) {
				sumJianValue += Math.abs((convAfterTalValue + fightVa));
				trueJianMap.put(fightPId, (convAfterTalValue * -1));
			} else {
				trueJianMap.put(fightPId, fightVa);
			}
		}
		
		//给加map按值从大到小排序,再依次去减那个需要减的总值
		
		//组织真正的加法map
		int caozuoSumJianValue = sumJianValue;
		Map<Integer, Integer> trueJiaMap = new HashMap<>();
		if (sumJianValue > 0) {
			Map<Integer, Integer> sortedJiaMap = CollectionUtil.sortByValueDown(jiaMap);
			for (Entry<Integer, Integer> entry : sortedJiaMap.entrySet()) {
				int fightPId = entry.getKey();
				int fightVal = entry.getValue();
				if (fightVal - caozuoSumJianValue < 0) {
					caozuoSumJianValue = (caozuoSumJianValue - fightVal);
					trueJiaMap.put(fightPId, 0);
				} else {
					trueJiaMap.put(fightPId, (fightVal - caozuoSumJianValue));
					caozuoSumJianValue = 0;
				}
			}
		} else {
			trueJiaMap = jiaMap;
		}
		
//		System.out.println("jianMap = " + jianMap);
//		System.out.println("trueJianMap = " + trueJianMap);
//		
//		System.out.println("jiaMap = " + jiaMap);
//		System.out.println("truejiaMap = " + trueJiaMap);
		
		Map<Integer, Integer> resultMap = new HashMap<>();
		for (Entry<Integer, Integer> entry : trueJiaMap.entrySet()) {
			resultMap.put(entry.getKey(), entry.getValue());
		}
		for (Entry<Integer, Integer> entry : trueJianMap.entrySet()) {
			resultMap.put(entry.getKey(), entry.getValue());
		}
		
		MessageData rMsgData = new MessageData();
		
		int index = 0;
		for (Entry<Integer, Integer> entry : resultMap.entrySet()) {
			index++;
			int fightPid = entry.getKey();
			int fightVal = entry.getValue();
			InstPlayerTrainTemp instPlayerTrainTemp = new InstPlayerTrainTemp();
			instPlayerTrainTemp.setInstPlayerCardId(instPlayerCardId);
			instPlayerTrainTemp.setFightPropId(fightPid);
			instPlayerTrainTemp.setFightPropValue(fightVal);
			instPlayerTrainTemp.setTrainNum(times);
			getInstPlayerTrainTempDAL().add(instPlayerTrainTemp, 0);
		
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", fightPid);
			msgData.putIntItem("2", fightVal);
			rMsgData.putMessageItem(index + "", msgData.getMsgData());
		}
		
		
//		for(int v : list){
//			
//			int fightPropId = StaticFightProp.blood;
//			if(v == 1){
//				fightPropId = StaticFightProp.blood;
//			}else if(v == 2){
//				fightPropId = StaticFightProp.wAttack;
//			}else if(v == 3){
//				fightPropId = StaticFightProp.wDefense;
//			}else if(v == 4){
//				fightPropId = StaticFightProp.fAttack;
//			}else if(v == 5){
//				fightPropId = StaticFightProp.fDefense;
//			}
//			InstPlayerTrainTemp instPlayerTrainTemp = new InstPlayerTrainTemp();
//			instPlayerTrainTemp.setInstPlayerCardId(instPlayerCardId);
//			instPlayerTrainTemp.setFightPropId(fightPropId);
//			instPlayerTrainTemp.setFightPropValue(propertys[list.indexOf(v)]);
//			instPlayerTrainTemp.setTrainNum(times);
//			getInstPlayerTrainTempDAL().add(instPlayerTrainTemp, 0);
//		
//			MessageData msgData = new MessageData();
//			msgData.putIntItem("1", fightPropId);
//			msgData.putIntItem("2", propertys[list.indexOf(v)]);
//			rMsgData.putMessageItem((list.indexOf(v)+1) + "", msgData.getMsgData());
//		}
		
		//处理每日任务
		PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.state, 1, syncMsgData);
		
		//记录修炼次数(由于修炼次数不下发给客户端所以就不同步了)
		instPlayerCard.setTrainNum(instPlayerCard.getTrainNum() + times);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, rMsgData);
	}
	
	/**
	 * 接受卡牌修炼
	 * @author hzw
	 * @date 2014-7-10下午3:27:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void trainAccept(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		int dPotentialNum = 0;    //消耗的总潜力点
		int trainAcceptNum = 0;
		//处理临时修炼数据
		List<InstPlayerTrainTemp>  instPlayerTrainTempList = getInstPlayerTrainTempDAL().getList("instPlayerCardId = " + instPlayerCardId, instPlayerId);
		for(InstPlayerTrainTemp instPlayerTrainTemp : instPlayerTrainTempList){
			dPotentialNum += instPlayerTrainTemp.getFightPropValue();
			List<InstPlayerTrain> InstPlayerTrainList = getInstPlayerTrainDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerCardId = " + instPlayerTrainTemp.getInstPlayerCardId() 
					+ " and fightPropId = " + instPlayerTrainTemp.getFightPropId(), instPlayerId);
			if(InstPlayerTrainList.size() > 0){
				InstPlayerTrain instPlayerTrain = InstPlayerTrainList.get(0);
				instPlayerTrain.setFightPropValue(instPlayerTrain.getFightPropValue() + instPlayerTrainTemp.getFightPropValue());
				getInstPlayerTrainDAL().update(instPlayerTrain, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerTrain, instPlayerTrain.getId(), instPlayerTrain.getResult(), syncMsgData);
			}else{
				InstPlayerTrain instPlayerTrain = new InstPlayerTrain();
				instPlayerTrain.setInstPlayerId(instPlayerId);
				instPlayerTrain.setInstPlayerCardId(instPlayerCardId);
				instPlayerTrain.setFightPropId(instPlayerTrainTemp.getFightPropId());
				instPlayerTrain.setFightPropValue(instPlayerTrainTemp.getFightPropValue());
				getInstPlayerTrainDAL().add(instPlayerTrain, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerTrain, instPlayerTrain.getId(), instPlayerTrain.getResult(), syncMsgData);
			}
			trainAcceptNum = instPlayerTrainTemp.getTrainNum();
			getInstPlayerTrainTempDAL().deleteById(instPlayerTrainTemp.getId(), 0);
		}
		
		/*//处理卡牌潜力点,称号潜力值,称号(已取消这一部分)
		Map<String, Integer> retMap = FormulaUtil.calcTitle(instPlayerCard.getTitleDetailId(), instPlayerCard.getUseTalentValue() + dPotentialNum, 1);
		int titleDetailId = retMap.get("titleDetailId");
		int useTalentValue = retMap.get("useTalentValue");
		instPlayerCard.setTitleDetailId(titleDetailId);
		
		
		DictTitleDetail titleDetail = DictMap.dictTitleDetailMap.get(instPlayerCard.getTitleDetailId() + "");
		
		if (titleDetail.getValue() == 9 && useTalentValue >= titleDetail.getUseTalentValue()) {
			useTalentValue = titleDetail.getUseTalentValue();
		}
		
		instPlayerCard.setUseTalentValue(useTalentValue);*/
		instPlayerCard.setPotential(instPlayerCard.getPotential() - dPotentialNum);
		instPlayerCard.setTrainAcceptNum(instPlayerCard.getTrainAcceptNum() + trainAcceptNum);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/*
	*//**
	 * 卡牌突破
	 * @author hzw
	 * @date 2014-7-14上午10:17:11
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 *//*
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void breakThrough(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		DictTitleDetail dictTitleDetail = DictMap.dictTitleDetailMap.get(instPlayerCard.getTitleDetailId() + "");
		if(dictTitleDetail.getValue() != 9){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notCardTitle);
			return;
		}
		String conditions[] = dictTitleDetail.getConditions().split(";");
		for(String condition : conditions){
			if(ConvertUtil.toInt(condition.split("_")[0]) == StaticTableType.DictThing){
				List<InstPlayerThing> lindenList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + ConvertUtil.toInt(condition.split("_")[1]), instPlayerId);
				if(lindenList.size() <= 0 || lindenList.get(0).getNum() < ConvertUtil.toInt(condition.split("_")[2])){
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notThingNum);
					return;
				}
			}else if(ConvertUtil.toInt(condition.split("_")[0]) == StaticTableType.DictCardBaseProp){
				if(ConvertUtil.toInt(condition.split("_")[1]) == StaticCardBaseProp.level){
					if(instPlayerCard.getLevel() < ConvertUtil.toInt(condition.split("_")[2])){
						MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notCardLevel);
						return;
					}
				}
			}
		}
		for(String condition : conditions){
			if(ConvertUtil.toInt(condition.split("_")[0]) == StaticTableType.DictThing){
				List<InstPlayerThing> lindenList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + ConvertUtil.toInt(condition.split("_")[1]), instPlayerId);
				//处理消耗的材料
				ThingUtil.updateInstPlayerThing(instPlayerId, lindenList.get(0), ConvertUtil.toInt(condition.split("_")[2]), syncMsgData);
			}
		}
		
		//处理卡牌潜力点
		int nestTitleDetailId = instPlayerCard.getTitleDetailId();
		List<DictTitleDetail> dictTitleDetailList = (List<DictTitleDetail>)DictMapList.dictTitleDetailMap.get(dictTitleDetail.getTitleId()+1);
		for(DictTitleDetail obj : dictTitleDetailList){
			if(obj.getValue() == 0){
				nestTitleDetailId = obj.getId();
				break;
			}
		}
		Map<String, Integer> retMap = FormulaUtil.calcTitle(nestTitleDetailId, instPlayerCard.getUseTalentValue() - dictTitleDetail.getUseTalentValue(), 2);
		int titleDetailId = retMap.get("titleDetailId");
		int useTalentValue = retMap.get("useTalentValue");
		instPlayerCard.setTitleDetailId(titleDetailId);
		instPlayerCard.setUseTalentValue(useTalentValue);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		//跑马灯相关
		MarqueeUtil.putMsgToMarquee(channelId, titleDetailId + ";" + instPlayerCard.getCardId(), CustomMarqueeType.MARQUEE_TYPE_OTHER, CustomMarqueeType.MARQUEE_SOURCE_BREAK);
		
		//验证1人、5人称号成就
		AchievementUtil.title1(instPlayerId, syncMsgData);
		AchievementUtil.title5(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}*/
	
	/**
	 * 进入卡牌招募界面
	 * @author mp
	 * @date 2014-8-14 上午11:28:21
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getRecruitInfo(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData retMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		//组织招募实例map, 只查一次数据库
		Map<Integer, InstPlayerRecruit> playerRecruitMap = CardUtil.orgPlayerRecuitMap(instPlayerId);
		
		//组织白银招募数据
		CardUtil.orgSilverRecruitData(playerRecruitMap, retMsgData);
		
		//组织黄金招募数据
		CardUtil.orgGoldRecruitData(playerRecruitMap, retMsgData);
		
		//组织钻石招募数据
		CardUtil.orgDiamondRecruitData(playerRecruitMap, retMsgData);
		
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 卡牌招募
	 * @author mp
	 * @date 2014-8-14 上午11:26:50
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cardRecruit(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		MessageData retMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int recruitTypeId = (Integer)msgMap.get("recruitTypeId");//招募类型Id 1-白银招募 2-黄金招募 3-钻石招募 [定死即可]
		int diamondRecruitTypeId = (Integer)msgMap.get("diamondRecruitTypeId");//钻石招募类型, 1-招募令 2-普通招募 3-招募10次[非钻石招募传0]//2015/3/9孙毅决定将招募令放入白银招募
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		//组织招募实例map, 只查一次数据库
		Map<Integer, InstPlayerRecruit> playerRecruitMap = CardUtil.orgPlayerRecuitMap(instPlayerId);
		
		//白银招募处理
		if (recruitTypeId == 1) {
			int result = CardUtil.silverRecruitHandler(playerRecruitMap, instPlayer, syncMsgData, retMsgData, diamondRecruitTypeId, msgMap);
			if (result == 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_finishTimes);//验证招募次数
				return;
			}
			if (result == 2) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notUpTime);//验证免费招募时间
				return;
			}
			if (result == 3) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notEnoughRecruitSign);//验证招募令
				return;
			}
		//黄金招募处理
		} else if (recruitTypeId == 2) {
			int result = CardUtil.goldRecruitHandler(playerRecruitMap, instPlayer, syncMsgData, diamondRecruitTypeId, retMsgData, msgMap);
			if (result == 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);//验证元宝
				return;
			}
			
		//钻石招募处理
		} else if (recruitTypeId == 3) {
			int result = CardUtil.diamondRecruitHandler(playerRecruitMap, instPlayer, syncMsgData, diamondRecruitTypeId, retMsgData, msgMap);
			if (result == 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);//验证元宝
				return;
			}
		}
		
		//处理每日任务
		if (diamondRecruitTypeId == 3) {
			PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.extractCard, 10, syncMsgData);
		} else {
			PlayerUtil.updateDailyTask(instPlayer, StaticDailyTask.extractCard, 1, syncMsgData);
		}
		
		//处理积分[在限时英雄活动期内才计入积分]-放到最后处理
		SysActivity dictActivity = DictMap.sysActivityMap.get(StaticActivity.LimitTimeHero + "");
		if (dictActivity.getStartTime() != null && !dictActivity.getStartTime().equals("") && dictActivity.getEndTime() != null && !dictActivity.getEndTime().equals("")) {
			if (DateUtil.getMillSecond(dictActivity.getStartTime()) <= DateUtil.getCurrMill() && DateUtil.getCurrMill() <= DateUtil.getMillSecond(dictActivity.getEndTime())) {
				int jifen = 0;
				if (recruitTypeId == 1) {
					jifen = DictMapUtil.getSysConfigIntValue(StaticSysConfig.silverRecruitJifen);
					if(diamondRecruitTypeId == 3){
						jifen = 10* jifen;
					}
				} else if (recruitTypeId == 2) {
					if (diamondRecruitTypeId == 2) {
						jifen = DictMapUtil.getSysConfigIntValue(StaticSysConfig.goldRecruitJifen);
					} else if (diamondRecruitTypeId == 3) {
						jifen = DictMapUtil.getSysConfigIntValue(StaticSysConfig.goldTenRecruitJifen);
					}
				} else if (recruitTypeId == 3) {
					if (diamondRecruitTypeId == 2) {
						jifen = DictMapUtil.getSysConfigIntValue(StaticSysConfig.goldRecruitJifen);
					} else if (diamondRecruitTypeId == 3) {
						jifen = DictMapUtil.getSysConfigIntValue(StaticSysConfig.goldTenRecruitJifen);
					}
				}
				List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = " + instPlayerId + " and properties = '"+StaticBigTable.recruitJiFen+"'", 0);
				if (instPlayerBigTableList.size() > 0) {
					InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
					jifen = (ConvertUtil.toInt(instPlayerBigTable.getValue1()) + jifen);
					instPlayerBigTable.setValue1(jifen + "");
					getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
				} else {
					InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
					instPlayerBigTable.setInstPlayerId(instPlayerId);
					instPlayerBigTable.setProperties(StaticBigTable.recruitJiFen);
					instPlayerBigTable.setValue1(jifen + "");
					getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
				}
				
				ParamConfig.recruitJifenMap.put(instPlayerId, jifen);
			}
		}
		
		//经验丹处理,经小邀同意,经验丹和数量可以写死[白银招募送1个低级经验丹  黄金招募送1个高级经验丹]
		/**
		 *  31	低级经验丹
            32	中级经验丹
            33	高级经验丹
		 */
		String thing = "";
		if (recruitTypeId == 1) {//商城白银 低级经验丹
			thing = "1_31_1";
			if(diamondRecruitTypeId == 3){
				thing = "1_31_10";
			}
		} else if (recruitTypeId == 2) {//限时英雄 高级经验丹
			if (diamondRecruitTypeId == 2) {
				thing = "1_33_1";
			} else if (diamondRecruitTypeId == 3) {
				thing = "1_33_10";
			}
		} else if (recruitTypeId == 3) {//商城黄金 高级经验丹
			if (diamondRecruitTypeId == 2) {
				thing = "1_33_1";
			} else if (diamondRecruitTypeId == 3) {
				thing = "1_33_10";
			}
		}
		ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(thing.split("_")[0]), ConvertUtil.toInt(thing.split("_")[1]), ConvertUtil.toInt(thing.split("_")[2]), syncMsgData, msgMap);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);

	}
	
	/**
	 * 卡牌进阶
	 * @author mp
	 * @date 2014-9-1 下午5:12:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cardAdvance(Map<String, Object> msgMap, String channelId) throws Exception {
		
		//获取参数
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");//需要进阶的卡牌实例Id
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		if (step != null && !step.equals("")) {
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		//验证玩家是否一致
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		//验证卡边-白/金卡不可进阶
		if (instPlayerCard.getQualityId() == StaticQuality.white) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_whiteNoAdvance);
			return;
		}
		
		//获取进阶条件和进阶后的卡边颜色及星数
		int nextQualityId = 0;
		int nextStarLevelId = 0;
		String advanceCond = "";
		List<DictAdvance> advanceList = (List<DictAdvance>)DictMapList.dictAdvanceMap.get(instPlayerCard.getCardId());
		for (DictAdvance advance : advanceList) {
			if (advance.getQualityId() == instPlayerCard.getQualityId() && advance.getStarLevelId() == instPlayerCard.getStarLevelId()) {
				nextQualityId = advance.getNextQualityId();
				nextStarLevelId = advance.getNextStarLevelId();
				advanceCond = advance.getConds();
				break;
			}
		}
		
		//验证进阶条件是否满足
		String retMsg = CardUtil.valiAdvanceCond(advanceCond, instPlayerCard);
		if (StringUtil.isNotEmpty(retMsg)) {
			MessageUtil.sendFailMsg(channelId, msgMap, retMsg);
			return;
		}
		
		//扣除消耗 3_品质Id_星级数_卡牌字典Id_张数    实际填写为：3_品质Id_星级数_张数,  需要扣除；  5_品质Id_材料个数，  需要扣除 . 关于材料后修改为：5_物品材料Id_材料个数  6_异火ID
		String[] conds = advanceCond.split(";");
		for (String cond : conds) {
			int type = ConvertUtil.toInt(cond.split("_")[0]);
			if (type == 3) {
				int qualityId = ConvertUtil.toInt(cond.split("_")[1]);
				int starLevelId = ConvertUtil.toInt(cond.split("_")[2]);
				int needCardId = instPlayerCard.getCardId();
				int num = ConvertUtil.toInt(cond.split("_")[3]);
				List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId + " and cardId = " + needCardId + " and qualityId = " + qualityId + " and starLevelId = " + starLevelId + " and inTeam = 0 and id != " + instPlayerCardId, instPlayerId);
				
				if (instPlayerCardList.size() >= num) {
					int rmNum = 0;
					for (int i = 0; i < instPlayerCardList.size(); i++) {
						InstPlayerCard rmInstPlayerCard = instPlayerCardList.get(i);
						if (rmInstPlayerCard.getInTeam() == StaticCustomDict.unTeam) {
							
							//如果有斗魂，将斗魂卸下
							int instCardId = rmInstPlayerCard.getId();
							FightSoulUtil.dropFightSoulIfCardExsits(instPlayerId, instCardId, syncMsgData);
							
							getInstPlayerCardDAL().deleteById(rmInstPlayerCard.getId(), instPlayerId);
							OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, rmInstPlayerCard, rmInstPlayerCard.getId(), "", syncMsgData);
							rmNum ++;
						}
						if (rmNum >= num) {
							break;
						}
					}
				}
			} else if (type == 5) {
				//5_物品材料Id_材料个数
				int materialId = ConvertUtil.toInt(cond.split("_")[1]);
				int num = ConvertUtil.toInt(cond.split("_")[2]);
				List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + materialId, instPlayerId);
				if (instPlayerThingList.size() > 0) {
					ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThingList.get(0), num, syncMsgData, msgMap);
				}
			}  else if (type == 6) {
				//6_异火ID
				int fireId = ConvertUtil.toInt(cond.split("_")[1]);
				List<InstPlayerYFire> instPlayerYFireList = getInstPlayerYFireDAL().getList("instPlayerId = " + instPlayerId + " and fireId = " + fireId, instPlayerId);
				if (instPlayerYFireList.size() > 0) {
					InstPlayerYFire instPlayerYFire = instPlayerYFireList.get(0);
					YFireUtil.costFire(instPlayerYFire);
					getInstPlayerYFireDAL().update(instPlayerYFire, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerYFire, instPlayerYFire.getId(), instPlayerYFire.getResult(), syncMsgData);
				}else{
					MessageUtil.sendFailMsg(channelId, msgMap, "异火未激活");
					return;
				}
			}
		}
		
		//更新卡边和星数
		
		//这里做个假处理[偷梁换柱-把绿星的临时萧炎,替换成正式的]
		if (instPlayerCard.getCardId() == 154) {
			instPlayerCard.setCardId(88);
			
			//阵型上边的也要换
			List<InstPlayerFormation> instPlayerFormationList = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId + " and cardId = 154", instPlayerId);
			if (instPlayerFormationList.size() > 0) {
				InstPlayerFormation instPlayerFormation = instPlayerFormationList.get(0);
				instPlayerFormation.setCardId(88);
				getInstPlayerFormationDAL().update(instPlayerFormation, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerFormation, instPlayerFormation.getId(), instPlayerFormation.getResult(), syncMsgData);
			}
		}
		
		//封装跑马灯参数
		String dictId = instPlayerCard.getCardId() + ";" + instPlayerCard.getQualityId() + "_" + instPlayerCard.getStarLevelId() + ";" + nextQualityId + "_" + nextStarLevelId;
		
		instPlayerCard.setQualityId(nextQualityId);
		instPlayerCard.setStarLevelId(nextStarLevelId);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		//验证进阶成就
		AchievementUtil.advance(instPlayerId, syncMsgData);
		
		//跑马灯相关
		MarqueeUtil.putMsgToMarquee(channelId, dictId, CustomMarqueeType.MARQUEE_TYPE_OTHER, CustomMarqueeType.MARQUEE_SOURCE_ADWANCE);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 出售卡牌
	 * @author hzw
	 * @date 2014-9-2上午10:18:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void sellCards(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		String instCardIds = (String)msgMap.get("instCardIds");
		String[] paramList = instCardIds.split(";");
		int sellCopper = 0;
		
		for(String str : paramList){
			InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			if(instPlayerId != instPlayerCard.getInstPlayerId()){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
				return;
			}
			if(instPlayerCard.getInTeam() == StaticCustomDict.inTeam){
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardListInFormation);
				return;
			}
			sellCopper += FormulaUtil.sellCardCopper(instPlayerCard);
		}
		 
		//更新玩家银币
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) + sellCopper) + "");
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		//删除被出售卡牌
		for(String str : paramList){
			InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(ConvertUtil.toInt(str), instPlayerId);
			CardUtil.deletePlayerCard(instPlayerId, instPlayerCard, syncMsgData);
		}
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 吃丹
	 * @author hzw
	 * @date 2014-9-8下午4:35:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eatPill(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		int instPlayerPillId = (Integer)msgMap.get("instPlayerPillId");
		int num = (Integer)msgMap.get("num");
		
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
	
		InstPlayerPill instPlayerPill = getInstPlayerPillDAL().getModel(instPlayerPillId, instPlayerId);
		if(instPlayerId != instPlayerPill.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		
		DictPill dictPill = DictMap.dictPillMap.get(instPlayerPill.getPillId() + "");
		if(dictPill.getPillTypeId() == StaticPillType.exp){
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			
			//等级,经验计算
			Map<String, Integer> retMap = FormulaUtil.calcLevel(instPlayerCard.getLevel(), instPlayerCard.getExp() + dictPill.getValue() * num, instPlayer.getLevel());
			int level = retMap.get("level");
			int exp = retMap.get("exp");
			int potential = retMap.get("potential");
			instPlayerCard.setLevel(level);
			instPlayerCard.setExp(exp);
			instPlayerCard.setPotential(instPlayerCard.getPotential() + potential);
			
			//扣除银币
			instPlayer.setCopper((ConvertUtil.toInt(instPlayer.getCopper()) - dictPill.getValue() * num * DictMapUtil.getSysConfigIntValue(StaticSysConfig.eatExpCopper)) + "");
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		}
		/*else if(dictPill.getPillTypeId() == StaticPillType.potential){
			instPlayerCard.setPotential(instPlayerCard.getPotential() + dictPill.getValue() * num);
		}*/
		
		
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		//更新丹药
		PillUtil.updateInstPlayerPill(instPlayerId, instPlayerPill, num, syncMsgData);
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 锁卡
	 * @author hzw
	 * @date 2014-9-19下午4:34:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void lockCard(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instPlayerCard.getIsLock() == 0){
			instPlayerCard.setIsLock(1);
		}else{
			instPlayerCard.setIsLock(0);
		}
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

	/**
	 * 轮回预览
	 * @author hzw
	 * @date 2014-9-19下午4:34:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void restoreCardView_bak(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if(instPlayerCard.getInTeam() == StaticCustomDict.inTeam){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardListInFormation);
			return;
		}
		if(instPlayerCard.getIsLock() == 1){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardIsLock);
			return;
		}
		
		String fightSoulList = FightSoulUtil.getFightSoulIfCardExsits(instPlayerId, instPlayerCardId);
		
		int cardNum = 0;    //需要返还的卡牌数量（默认是0，实则最少是1卡牌本身）
		Map<String, Integer> thingMap = new HashMap<String, Integer>();  //返还的物品Map
		CardUtil.orgRestoreCardView(instPlayerCard, thingMap);
		
		//只有紫卡才会吃掉卡牌，所以只有是紫卡才处理返还卡牌
		if(instPlayerCard.getQualityId() == StaticQuality.purple){
			
			DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
			List<DictAdvance> advanceList = (List<DictAdvance>)DictMapList.dictAdvanceMap.get(instPlayerCard.getCardId());
			int titileDetailId = 0;
			int level = 1;
			int constellId = 0;   //字典命宫Id
			int pos = 0;		//此卡牌的第pos个命宫是开启
			
			/** 进阶所有条件类型
			 *  1_此卡牌等级
				2_此卡牌的第几个命宫是否开启（还需判断丹药是否满足）
				3_品质Id_星级数_卡牌字典Id_张数,  需要扣除
				4_详细称号字典表的Id
				5_品质Id_材料个数，  需要扣除
			*/
			for (DictAdvance advance : advanceList) {
				if (advance.getQualityId() < instPlayerCard.getQualityId() || (advance.getNextQualityId() == instPlayerCard.getQualityId() && advance.getNextStarLevelId() <= instPlayerCard.getStarLevelId())) {
					String[] conds = advance.getConds().split(";");
					for (String cond : conds) {
						int type = ConvertUtil.toInt(cond.split("_")[0]);
						if(advance.getNextQualityId() == StaticQuality.purple && advance.getNextStarLevelId() == 1){
							if(type == 1){
								level = ConvertUtil.toInt(cond.split("_")[1]);
							}else if(type == 2){
								pos = ConvertUtil.toInt(cond.split("_")[1]);
								constellId = ConvertUtil.toInt(dictCard.getConstells().split(";")[pos-1]);
							}else if (type == 4) {
								titileDetailId = ConvertUtil.toInt(cond.split("_")[1]);
							}
						}
						if(type == 3){
							cardNum += ConvertUtil.toInt(cond.split("_")[3]);
						}
					}
					
				}
			}
			if(titileDetailId == 0){
				titileDetailId = dictCard.getTitleDetailId();
			}
			
			InstPlayerCard objCard = new InstPlayerCard();
			objCard.setInstPlayerId(instPlayerId);
			objCard.setCardId(instPlayerCard.getCardId());
			objCard.setQualityId(StaticQuality.purple);
			objCard.setStarLevelId(1);
			objCard.setTitleDetailId(titileDetailId);
			objCard.setSex(dictCard.getSex());
			objCard.setExp(0);
			objCard.setLevel(level);
			objCard.setInTeam(StaticCustomDict.unTeam);
			objCard.setUseTalentValue(0);
			objCard.setInstPlayerKungFuId(0);
			objCard.setPotential(ConvertUtil.toInt(DictMap.dictTitleDetailMap.get(dictCard.getTitleDetailId() + "").getConditions()));
			objCard.setInstPlayerConstells(pos + "_" + constellId);
			objCard.setIsLock(0);
			
			for(int i = 1; i <= cardNum; i++){
				CardUtil.orgRestoreCardView(objCard, thingMap);
			}
		}
		
		
		//先删除轮回临时表已前旧数据
		List<InstPlayerRestoreTemp> instPlayerRestoreTempList = getInstPlayerRestoreTempDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId);
		for (InstPlayerRestoreTemp obj : instPlayerRestoreTempList) {
			getInstPlayerRestoreTempDAL().deleteById(obj.getId(), instPlayerId);
		}
		
		//需要返还的卡牌数量+卡牌自身（初始状态）
		thingMap.put(StaticTableType.DictCard+"_"+instPlayerCard.getCardId(), cardNum + 1);
		
		MessageData retMsgData = new MessageData();
		String thingString = "";
		//返还组织好的物品
		for(Entry<String, Integer> str : thingMap.entrySet()){
			int tableTypeId = ConvertUtil.toInt(str.getKey().split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(str.getKey().split("_")[1]);
			int num = str.getValue();
			
			//应急处理
			if (tableTypeId == 2 && tableFieldId == 83) {
				if (instPlayerCard.getQualityId() == StaticQuality.purple) {
					if (instPlayerCard.getStarLevelId() == 1) {
						num = 0;
					} else if (instPlayerCard.getStarLevelId() == 2) {
						num = 800;
					} else if (instPlayerCard.getStarLevelId() == 3) {
						num = 1800;
					} else if (instPlayerCard.getStarLevelId() == 4) {
						num = 3300;
					} else if (instPlayerCard.getStarLevelId() == 5) {
						num = 6300;
					} else if (instPlayerCard.getStarLevelId() == 6) {
						num = 11300;
					}
				}
			}
			if (num != 0) {
				thingString += tableTypeId + "_" + tableFieldId + "_" + num + ";";
				
				//加入轮回临时表
				InstPlayerRestoreTemp obj = new InstPlayerRestoreTemp();
				obj.setInstPlayerId(instPlayerId);
				obj.setInstCardId(instPlayerCardId);
				obj.setTableTypeId(tableTypeId);
				obj.setTableFieldId(tableFieldId);
				obj.setValue(num);
				getInstPlayerRestoreTempDAL().add(obj, instPlayerId);
			}
		}
		retMsgData.putStringItem("things", thingString.substring(0, thingString.length() - 1));
		retMsgData.putStringItem("fightSoulList", fightSoulList.length() > 0 ? StringUtil.noContainLastString(fightSoulList) : fightSoulList);//斗魂实例Id;  最后没有分号, 此处的值可能为空（没有斗魂）
		
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 轮回预览
	 * 
	 * @author hzw
	 * @date 2014-9-19下午4:34:04
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void restoreCardView(Map<String, Object> msgMap, String channelId) throws Exception {

		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);

		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}

		int instPlayerCardId = (Integer) msgMap.get("instPlayerCardId");
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		if (instPlayerId != instPlayerCard.getInstPlayerId()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		if (instPlayerCard.getInTeam() == StaticCustomDict.inTeam) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardListInFormation);
			return;
		}
		if (instPlayerCard.getIsLock() == 1) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardIsLock);
			return;
		}

		String fightSoulList = FightSoulUtil.getFightSoulIfCardExsits(instPlayerId, instPlayerCardId);
		
		// int cardNum = 0; // 需要返还的卡牌数量（默认是0，实则最少是1卡牌本身）
		Map<String, Integer> thingMap = new HashMap<String, Integer>(); // 返还的物品Map
		CardUtil.orgRestoreCardView(instPlayerCard, thingMap);

		// 只有紫卡才会吃掉卡牌，所以只有是紫卡才处理返还卡牌
		// if (instPlayerCard.getQualityId() == StaticQuality.purple) {
		List<DictAdvance> advanceList = (List<DictAdvance>) DictMapList.dictAdvanceMap.get(instPlayerCard.getCardId());
		Collections.sort(advanceList, new ComparatorAdvanceCard());

		List<DictAdvance> sourceList = new ArrayList<DictAdvance>();
		DictAdvance dictAdvance = null;
		int midLevel = 1;
		for (int ai = 0; ai < advanceList.size(); ai++) {
			dictAdvance = advanceList.get(ai);
			if (dictAdvance.getNextQualityId() == instPlayerCard.getQualityId() && dictAdvance.getNextStarLevelId() == instPlayerCard.getStarLevelId()) {
				sourceList.add(dictAdvance);
			}
			if (dictAdvance.getNextQualityId() - dictAdvance.getQualityId() == 1) {
				String[] conds = dictAdvance.getConds().split(";");
				for (String cond : conds) {
					int[] array = StringUtil.string2IntArray(cond, '_');
					if (array.length > 0) {
						switch (array[0]) {// 1_等级;3_品阶_星级_数量;5_物品ID_数量
							case 1: {// 等级
								midLevel = array[1];
								break;
							}
						}
					}
				}
			}
		}
		Map<String, Integer> data = new HashMap<String, Integer>();
		int exp = FormulaUtil.restoreCardExp(instPlayerCard.getLevel(), instPlayerCard.getExp()); // 返还的经验
		int stone= CardUtil.getAdvanceStone(advanceList, instPlayerCard.getQualityId(),instPlayerCard.getStarLevelId());
		if (sourceList.size() < 1) {
			data.put("card", 1);
		} else {
			data = CardUtil.decompostCard(advanceList, sourceList, midLevel);
			Integer expCount = data.get("exp");
			if (expCount != null) {
				exp += expCount;
			}
			Integer stoneCount = data.get("stone");
			if (stoneCount != null) {
				stone += stoneCount;
			}
			if(stone>0){
				thingMap.put( StaticTableType.DictThing+"_"+83, stone);
			}
		}
		// ///////////////////////////////计算经验及金币///////////////////////
		int copper = exp * DictMapUtil.getSysConfigIntValue(StaticSysConfig.eatExpCopper); // 返还的银票
		if (copper > 0) {
			if (thingMap.containsKey(StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.copper)) {
				thingMap.put(StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.copper, thingMap.get(StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.copper) + copper);
			} else {
				thingMap.put(StaticTableType.DictPlayerBaseProp + "_" + StaticPlayerBaseProp.copper, copper);
			}
		}
		String pillList = FormulaUtil.calcExpPillBySumExp(exp);
		if (!pillList.equals("")) {
			pillList = pillList.substring(0, pillList.length() - 1);
			for (String pill : pillList.split(";")) {
				int tableTypeId = ConvertUtil.toInt(pill.split("_")[0]);
				int tableFieldId = ConvertUtil.toInt(pill.split("_")[1]);
				int num = ConvertUtil.toInt(pill.split("_")[2]);
				if (thingMap.containsKey(tableTypeId + "_" + tableFieldId)) {
					thingMap.put(tableTypeId + "_" + tableFieldId, thingMap.get(tableTypeId + "_" + tableFieldId) + num);
				} else {
					thingMap.put(tableTypeId + "_" + tableFieldId, num);
				}
			}
		}
		// //////////////////////////////////////////////////////

		DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
		int titileDetailId = 0;
		if (titileDetailId == 0) {
			titileDetailId = dictCard.getTitleDetailId();
		}
		InstPlayerCard objCard = new InstPlayerCard();
		objCard.setInstPlayerId(instPlayerId);
		objCard.setCardId(instPlayerCard.getCardId());
		objCard.setQualityId(instPlayerCard.getQualityId());
		objCard.setStarLevelId(1);
		objCard.setTitleDetailId(titileDetailId);
		objCard.setSex(dictCard.getSex());
		objCard.setExp(0);
		objCard.setLevel(1);
		objCard.setInTeam(StaticCustomDict.unTeam);
		objCard.setUseTalentValue(0);
		objCard.setInstPlayerKungFuId(0);
		objCard.setPotential(ConvertUtil.toInt(DictMap.dictTitleDetailMap.get(dictCard.getTitleDetailId() + "").getConditions()));
		objCard.setInstPlayerConstells(0 + "_" + 0);
		objCard.setIsLock(0);

		// 先删除轮回临时表已前旧数据
		List<InstPlayerRestoreTemp> instPlayerRestoreTempList = getInstPlayerRestoreTempDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId);
		for (InstPlayerRestoreTemp obj : instPlayerRestoreTempList) {
			getInstPlayerRestoreTempDAL().deleteById(obj.getId(), instPlayerId);
		}

		// 需要返还的卡牌数量+卡牌自身（初始状态）
		thingMap.put(StaticTableType.DictCard + "_" + instPlayerCard.getCardId(), data.get("card"));

		MessageData retMsgData = new MessageData();
		String thingString = "";

		// 返还组织好的物品
		for (Entry<String, Integer> str : thingMap.entrySet()) {
			int tableTypeId = ConvertUtil.toInt(str.getKey().split("_")[0]);
			int tableFieldId = ConvertUtil.toInt(str.getKey().split("_")[1]);
			int num = str.getValue();

			if (num != 0) {
				thingString += tableTypeId + "_" + tableFieldId + "_" + num + ";";

				// 加入轮回临时表
				InstPlayerRestoreTemp obj = new InstPlayerRestoreTemp();
				obj.setInstPlayerId(instPlayerId);
				obj.setInstCardId(instPlayerCardId);
				obj.setTableTypeId(tableTypeId);
				obj.setTableFieldId(tableFieldId);
				obj.setValue(num);
				getInstPlayerRestoreTempDAL().add(obj, instPlayerId);
			}
		}
		retMsgData.putStringItem("things", thingString.substring(0, thingString.length() - 1));
		retMsgData.putStringItem("fightSoulList", fightSoulList.length() > 0 ? StringUtil.noContainLastString(fightSoulList) : fightSoulList);//斗魂实例Id;  最后没有分号, 此处的值可能为空（没有斗魂）
		
		String lhLog = "轮回-轮回预览： instPlayerId=" + instPlayerId + " 轮回的卡牌=" + dictCard.getName() + " 轮回得到的物品=" + thingString.substring(0, thingString.length() - 1) + " 轮回的卡牌实例Id=" + instPlayerCardId;
		LogUtil.info(lhLog);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 轮回
	 * @author hzw
	 * @date 2014-9-22上午11:35:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void restoreCard(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}
		List<DictRestore> dictRestores = (List<DictRestore>)DictMapList.dictRestoreMap.get(instPlayerCard.getQualityId());
		DictRestore dictRestore = null;
		for(int i = 0; i < dictRestores.size(); i++){
			if(dictRestores.get(i).getStarLevelId() == instPlayerCard.getStarLevelId()){
				dictRestore = dictRestores.get(i);
				break;
			}
		}
		if(instPlayer.getGold() < dictRestore.getGold()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
			return;
		}
		
		if(instPlayerCard.getInTeam() == StaticCustomDict.inTeam){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardListInFormation);
			return;
		}
		if(instPlayerCard.getIsLock() == 1){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_cardIsLock);
			return;
		}
		
		//修炼
		List<InstPlayerTrainTemp>  instPlayerTrainTempList = getInstPlayerTrainTempDAL().getList("instPlayerCardId = " + instPlayerCardId, instPlayerId);
		for(InstPlayerTrainTemp instPlayerTrainTemp : instPlayerTrainTempList){
			getInstPlayerTrainTempDAL().deleteById(instPlayerTrainTemp.getId(), 0);
		}
		List<InstPlayerTrain>  instPlayerTrainList = getInstPlayerTrainDAL().getList("instPlayerCardId = " + instPlayerCardId, instPlayerId);
		for(InstPlayerTrain instPlayerTrain : instPlayerTrainList){
			getInstPlayerTrainDAL().deleteById(instPlayerTrain.getId(), 0);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerTrain, instPlayerTrain.getId(), "", syncMsgData);
		}
		
		//命宫
		if(instPlayerCard.getInstPlayerConstells() != null && !instPlayerCard.getInstPlayerConstells().equals("")){
			String instPlayerConstells[] = instPlayerCard.getInstPlayerConstells().split(";");
			for(String instPlayerConstellId : instPlayerConstells){
				InstPlayerConstell instPlayerConstell = getInstPlayerConstellDAL().getModel(ConvertUtil.toInt(instPlayerConstellId), instPlayerId);
				if(instPlayerConstell.getPills().contains("1")){
					instPlayerConstell.setPills(instPlayerConstell.getPills().replace("1", "0"));
					getInstPlayerConstellDAL().update(instPlayerConstell, instPlayerId);
					OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerConstell, instPlayerConstell.getId(), instPlayerConstell.getResult(), syncMsgData);
				}
			}
		}
		
		//功法
		if(instPlayerCard.getInstPlayerKungFuId() != 0){
			InstPlayerKungFu instPlayerKungFu = getInstPlayerKungFuDAL().getModel(instPlayerCard.getInstPlayerKungFuId(), instPlayerId);
			instPlayerKungFu.setInstPlayerCardId(0);
			getInstPlayerKungFuDAL().update(instPlayerKungFu, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerKungFu, instPlayerKungFu.getId(), instPlayerKungFu.getResult(), syncMsgData);
		}
		
		//斗魂处理
		int instCardId = instPlayerCard.getId();
		String fightSoulList = FightSoulUtil.dropFightSoulIfCardExsits(instPlayerId, instCardId, syncMsgData);
		
		//删除原卡牌实例数据
		getInstPlayerCardDAL().deleteById(instPlayerCardId, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerCard, instPlayerCard.getId(), "", syncMsgData);
		
		//返还轮回临时表数据后删除
		List<InstPlayerRestoreTemp> instPlayerRestoreTempList = getInstPlayerRestoreTempDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCardId, instPlayerId);
		Map<String, String> thingMap = new HashMap<>();
		for(int i = 0; i < instPlayerRestoreTempList.size(); i++){
			InstPlayerRestoreTemp obj = instPlayerRestoreTempList.get(i);
			int tableTypeId = obj.getTableTypeId();
			int tableFieldId = obj.getTableFieldId();
			int num = obj.getValue();
			if(tableTypeId == StaticTableType.DictCard){
				thingMap.put(tableTypeId + "_" + tableFieldId + "_" + 1, num + "");
			}else{
				thingMap.put(tableTypeId + "_" + tableFieldId, num + "");
			}
			getInstPlayerRestoreTempDAL().deleteById(obj.getId(), instPlayerId);
		}
		ThingUtil.groupThingMap (instPlayer, thingMap, syncMsgData, msgMap);
		
		//更新玩家实例数据-消耗元宝
		PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, dictRestore.getGold(), msgMap);
		
		getInstPlayerDAL().update(instPlayer, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
		
		String lhLog = "轮回-确认轮回： instPlayerId=" + instPlayerId + " 轮回的卡牌=" + DictMap.dictCardMap.get(instPlayerCard.getCardId() + "").getName() + " 轮回得到的物品=" + thingMap + " 轮回的卡牌实例Id=" + instPlayerCardId + " 斗魂相关=" + fightSoulList;
		LogUtil.info(lhLog);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}
	
	/**
	 * 境界
	 * @author hzw
	 * @date 2015-7-1下午3:19:25
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void realm(Map<String, Object> msgMap, String channelId) throws Exception {
		
		MessageData syncMsgData = new MessageData();
		int instPlayerId = getInstPlayerId(channelId);
		
		if (instPlayerId == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
			return;
		}
		
		int instPlayerCardId = (Integer)msgMap.get("instPlayerCardId");
		InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerCardId, instPlayerId);
		
		//验证当前卡牌的称号是否已是最高
		int currCardTitleDetailId = instPlayerCard.getTitleDetailId();
		int dictTitleDetailId = DictList.dictTitleDetailList.get(DictList.dictTitleDetailList.size() - 1).getId();
		if (currCardTitleDetailId >= dictTitleDetailId) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_buyUpLimit);
			return;
		}
		
		//处理引导逻辑
		String step = (String)msgMap.get("step");//等级用'_'区分;  关卡用'b'区分
		if (step != null && !step.equals("")) {
		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			if (!PlayerUtil.guidStep(step, instPlayer, msgMap, syncMsgData).equals("a")) {
				MessageUtil.sendFailMsg(channelId, msgMap, "");
				return;
			}
		}
		
		if(instPlayerId != instPlayerCard.getInstPlayerId()){
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
			return;
		}

		//FIXED by cui
		//修改境界的消耗
		int nestTitleDetailId = instPlayerCard.getTitleDetailId();
		DictTitleDetail dictTitleDetail = DictMap.dictTitleDetailMap.get(nestTitleDetailId + "");
		String cost = dictTitleDetail.getCost();
		if(cost == null || cost.equals("")){
			MessageUtil.sendFailMsg(channelId, msgMap, "无法突破");
			return;
		}

		//验证进阶条件是否满足
		String retMsg = CardUtil.valiRealmCond(cost, instPlayerCard);
		if (StringUtil.isNotEmpty(retMsg)) {
			MessageUtil.sendFailMsg(channelId, msgMap, retMsg);
			return;
		}

		//确定消耗
		String[] costArray = cost.split(";");
		for (String line : costArray){
			String[] element = line.split("_");
			int type = ConvertUtil.toInt(element[0]);
			List<InstPlayerThing> lindenList;
			switch (type){
				default:
					MessageUtil.sendFailMsg(channelId, msgMap, "无法突破");
					return;
				case 1://境界丹
					int useTalentValue = ConvertUtil.toInt(element[1]);//消耗的境界丹
					lindenList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.realmPill, instPlayerId);
					//因提前判断过所以不用检查
					if(lindenList.size() > 0)
						ThingUtil.updateInstPlayerThing(instPlayerId, lindenList.get(0), useTalentValue, syncMsgData, msgMap);
					break;
				case 2://超级境界丹
					int useSuperValue = ConvertUtil.toInt(element[1]);//消耗的超级境界丹
					lindenList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.thing300, instPlayerId);
					if(lindenList.size() > 0)
						ThingUtil.updateInstPlayerThing(instPlayerId, lindenList.get(0), useSuperValue, syncMsgData, msgMap);
					break;
				case 3://威望
					int weiValue = ConvertUtil.toInt(element[1]);//消耗威望数量
					List<InstPlayerArena> instPlayerArenaList = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
					if (instPlayerArenaList.size() > 0) {
						InstPlayerArena instPlayerArena = instPlayerArenaList.get(0);
						if(instPlayerArena.getPrestige() < weiValue){
							instPlayerArena.setPrestige(0);
						}else{
							instPlayerArena.setPrestige(instPlayerArena.getPrestige() - weiValue);
						}
						getInstPlayerArenaDAL().update(instPlayerArena, instPlayerId);
						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);

					}
					break;
				case 4://异火火种
					int fireId = ConvertUtil.toInt(element[1]);
					int count = ConvertUtil.toInt(element[2]);
					List<InstPlayerYFire> instPlayerYFireList = getInstPlayerYFireDAL().getList(" instPlayerId = " + instPlayerId + " and fireId = " + fireId, instPlayerId);
					if(instPlayerYFireList.size() > 0){
						InstPlayerYFire instPlayerYFire = instPlayerYFireList.get(0);
						int deta = instPlayerYFire.getChipCount() - count;
						instPlayerYFire.setChipCount(deta <= 0 ? 0 : deta);

						getInstPlayerYFireDAL().update(instPlayerYFire, instPlayerId);
						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerYFire, instPlayerYFire.getId(), instPlayerYFire.getResult(), syncMsgData);
					}
					break;
				case 5://品阶
					//没有可消耗的
					break;
			}
		}


//		if(dictTitleDetail.getValue() == 0 && dictTitleDetail.getUseTalentValue() == 0){
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notCardTitle);
//			return;
//		}

//		int useTalentValue = dictTitleDetail.getUseTalentValue();//消耗的境界丹
//		List<InstPlayerThing> lindenList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.realmPill, instPlayerId);
//		if(lindenList.size() <= 0 || lindenList.get(0).getNum() < useTalentValue){
//			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notThingNum);
//			return;
//		}
//		ThingUtil.updateInstPlayerThing(instPlayerId, lindenList.get(0), useTalentValue, syncMsgData, msgMap);
		
		int conditions = ConvertUtil.toInt(dictTitleDetail.getConditions());//增加潜力值
		
		instPlayerCard.setTitleDetailId(nestTitleDetailId + 1);
		instPlayerCard.setPotential(instPlayerCard.getPotential() + conditions);
		getInstPlayerCardDAL().update(instPlayerCard, instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCard, instPlayerCard.getId(), instPlayerCard.getResult(), syncMsgData);
		
		//跑马灯相关 
		if (dictTitleDetail.getValue() == 9) {
			MarqueeUtil.putMsgToMarquee(channelId, nestTitleDetailId + 1 + ";" + instPlayerCard.getCardId(), CustomMarqueeType.MARQUEE_TYPE_OTHER, CustomMarqueeType.MARQUEE_SOURCE_BREAK);
		}
		
		//验证1人、5人称号成就
		AchievementUtil.title1(instPlayerId, syncMsgData);
		AchievementUtil.title5(instPlayerId, syncMsgData);
		
		MessageUtil.sendSyncMsg(channelId, syncMsgData);
		MessageUtil.sendSuccMsg(channelId, msgMap);
	}

}
