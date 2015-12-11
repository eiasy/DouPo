package com.huayi.doupo.logic.handler.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.*;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticCustomDict;
import com.huayi.doupo.base.model.statics.StaticFightProp;
import com.huayi.doupo.base.model.statics.StaticQuality;
import com.huayi.doupo.base.model.statics.StaticRecruitType;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.model.statics.custom.CustomMarqueeType;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.base.util.base.HttpClientUtil;
import com.huayi.doupo.base.util.base.RandomUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.LogicLogUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.base.util.logic.system.log.ThreadOper;
import com.huayi.doupo.base.util.logic.system.log.ThreadPoolUtils;
import com.huayi.doupo.logic.handler.util.marquee.MarqueeUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 *卡牌工具类
 * @author hzw
 * @date 2014-6-19下午4:01:09
 */
public class CardUtil extends DALFactory{

	/**
	  * 初始卡牌
	  * @author hzw
	  * @date 2014-6-19下午5:02:17
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	public static InstPlayerCard initPlayerCard(int instPlayerId, int cardId, int level) throws Exception{
		DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
		InstPlayerCard instPlayerCard = new InstPlayerCard();
		instPlayerCard.setInstPlayerId(instPlayerId);
		instPlayerCard.setCardId(cardId);
		instPlayerCard.setQualityId(dictCard.getQualityId());
		instPlayerCard.setStarLevelId(dictCard.getStarLevelId());
		instPlayerCard.setTitleDetailId(dictCard.getTitleDetailId());
		instPlayerCard.setSex(dictCard.getSex());
		instPlayerCard.setExp(0);
		instPlayerCard.setLevel(level);
		instPlayerCard.setInTeam(StaticCustomDict.inTeam);
		instPlayerCard.setUseTalentValue(0);
		instPlayerCard.setInstPlayerKungFuId(0);
		instPlayerCard.setPotential(ConvertUtil.toInt(DictMap.dictTitleDetailMap.get(dictCard.getTitleDetailId() + "").getConditions()));
		instPlayerCard.setIsLock(0);
		instPlayerCard.setTrainNum(0);
		instPlayerCard.setTrainAcceptNum(0);
		instPlayerCard = getInstPlayerCardDAL().add(instPlayerCard, instPlayerId);
		
		//收集得到过的卡牌
		CardUtil.addPlayerCards(instPlayerId, cardId);
		
		return instPlayerCard;
	}
	
	/**
	 * 新增卡牌
	 * @author hzw
	 * @date 2014-9-30下午5:58:29
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayerCard addPlayerCard(int instPlayerId, int cardId, int level) throws Exception{
		DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
		InstPlayerCard instPlayerCard = new InstPlayerCard();
		instPlayerCard.setInstPlayerId(instPlayerId);
		instPlayerCard.setCardId(cardId);
		instPlayerCard.setQualityId(dictCard.getQualityId());
		instPlayerCard.setStarLevelId(dictCard.getStarLevelId());
		instPlayerCard.setTitleDetailId(dictCard.getTitleDetailId());
		instPlayerCard.setSex(dictCard.getSex());
		instPlayerCard.setExp(0);
		instPlayerCard.setLevel(level);
		instPlayerCard.setInTeam(StaticCustomDict.unTeam);
		instPlayerCard.setUseTalentValue(0);
		instPlayerCard.setInstPlayerKungFuId(0);
		instPlayerCard.setInstPlayerConstells("");
		instPlayerCard.setPotential(ConvertUtil.toInt(DictMap.dictTitleDetailMap.get(dictCard.getTitleDetailId() + "").getConditions()));
		instPlayerCard.setIsLock(0);
		instPlayerCard.setTrainNum(0);
		instPlayerCard.setTrainAcceptNum(0);
		instPlayerCard = getInstPlayerCardDAL().add(instPlayerCard, instPlayerId);
		
		//收集得到过的卡牌
	    CardUtil.addPlayerCards(instPlayerId, cardId);
		
		return instPlayerCard;
	}
	
	/**
	 * 新增卡牌
	 * @author mp
	 * @date 2015-8-23 上午11:08:52
	 * @param instPlayerId
	 * @param cardId
	 * @param level
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayerCard addPlayerCardBarrier (int instPlayerId, int cardId, int level) throws Exception{
		DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
		InstPlayerCard instPlayerCard = new InstPlayerCard();
		instPlayerCard.setInstPlayerId(instPlayerId);
		instPlayerCard.setCardId(cardId);
		instPlayerCard.setQualityId(dictCard.getQualityId());
		instPlayerCard.setStarLevelId(dictCard.getStarLevelId());
		instPlayerCard.setTitleDetailId(dictCard.getTitleDetailId());
		instPlayerCard.setSex(dictCard.getSex());
		instPlayerCard.setExp(0);
		instPlayerCard.setLevel(level);
		instPlayerCard.setInTeam(StaticCustomDict.unTeam);
		instPlayerCard.setUseTalentValue(0);
		instPlayerCard.setInstPlayerKungFuId(0);
		instPlayerCard.setInstPlayerConstells("");
		instPlayerCard.setPotential(ConvertUtil.toInt(DictMap.dictTitleDetailMap.get(dictCard.getTitleDetailId() + "").getConditions()));
		instPlayerCard.setIsLock(0);
		instPlayerCard.setTrainNum(0);
		instPlayerCard.setTrainAcceptNum(0);
		instPlayerCard = getInstPlayerCardDAL().add(instPlayerCard, instPlayerId);
		
		//收集得到过的卡牌
	    CardUtil.addPlayerCards(instPlayerId, cardId);
		
		return instPlayerCard;
	}
	
	/**
	 * 新增卡牌
	 * @author hzw
	 * @date 2014-7-2上午11:00:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static InstPlayerCard addPlayerCard(int instPlayerId, int cardId) throws Exception{
		DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
		InstPlayerCard instPlayerCard = new InstPlayerCard();
		instPlayerCard.setInstPlayerId(instPlayerId);
		instPlayerCard.setCardId(cardId);
		instPlayerCard.setQualityId(dictCard.getQualityId());
		instPlayerCard.setStarLevelId(dictCard.getStarLevelId());
		instPlayerCard.setTitleDetailId(dictCard.getTitleDetailId());
		instPlayerCard.setSex(dictCard.getSex());
		instPlayerCard.setExp(0);
		instPlayerCard.setLevel(1);
		instPlayerCard.setInTeam(StaticCustomDict.unTeam);
		instPlayerCard.setUseTalentValue(0);
		instPlayerCard.setInstPlayerKungFuId(0);
		instPlayerCard.setInstPlayerConstells("");
		instPlayerCard.setPotential(ConvertUtil.toInt(DictMap.dictTitleDetailMap.get(dictCard.getTitleDetailId() + "").getConditions()));
		instPlayerCard.setIsLock(0);
		instPlayerCard.setTrainNum(0);
		instPlayerCard.setTrainAcceptNum(0);
		instPlayerCard = getInstPlayerCardDAL().add(instPlayerCard, instPlayerId);
		
		//收集得到过的卡牌
		CardUtil.addPlayerCards(instPlayerId, cardId);
		
		return instPlayerCard;
	}
	
	/**
	 * 增加卡牌魂魄
	 * @author hzw
	 * @date 2014-9-30上午11:40:19
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addinstPlayerCardSoul(int instPlayerId, int cardSoulId, int num, MessageData syncMsgData) throws Exception{
		List<InstPlayerCardSoul> instPlayerCardSoulList = getInstPlayerCardSoulDAL().getList("instPlayerId = " + instPlayerId + " and cardSoulId = " + cardSoulId, instPlayerId);
		if(instPlayerCardSoulList == null || instPlayerCardSoulList.size() == 0){
//			System.out.println("cardSoulId==  " + cardSoulId);
			DictCardSoul dictCardSoul = DictMap.dictCardSoulMap.get(cardSoulId + "");
			InstPlayerCardSoul instPlayerCardSoul = new InstPlayerCardSoul();
			instPlayerCardSoul.setInstPlayerId(instPlayerId);
			instPlayerCardSoul.setCardId(dictCardSoul.getCardId());
			instPlayerCardSoul.setCardSoulId(cardSoulId);
			instPlayerCardSoul.setNum(num);
			getInstPlayerCardSoulDAL().add(instPlayerCardSoul, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, instPlayerCardSoul, instPlayerCardSoul.getId(), instPlayerCardSoul.getResult(), syncMsgData);
		}
		if(instPlayerCardSoulList.size() > 0){
			InstPlayerCardSoul instPlayerCardSoul = instPlayerCardSoulList.get(0);
			instPlayerCardSoul.setNum(instPlayerCardSoul.getNum() + num);
			getInstPlayerCardSoulDAL().update(instPlayerCardSoul, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerCardSoul, instPlayerCardSoul.getId(), instPlayerCardSoul.getResult(), syncMsgData);
		}
	}
	
	/**
	  * 新增玩家阵型
	  * @author hzw
	  * @date 2014-6-19下午5:08:02
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	public static InstPlayerFormation initPlayerFormation(int instPlayerId, InstPlayerCard instPlayerCard, int position, int formationType) throws Exception{
		InstPlayerFormation instPlayerFormation = new InstPlayerFormation();
		instPlayerFormation.setInstCardId(instPlayerCard.getId());
		instPlayerFormation.setInstPlayerId(instPlayerId);
		instPlayerFormation.setPosition(position);
		instPlayerFormation.setCardId(instPlayerCard.getCardId());
		instPlayerFormation.setType(formationType);//1-正规军 2-替补队员
		instPlayerFormation = getInstPlayerFormationDAL().add(instPlayerFormation, instPlayerId);
		return instPlayerFormation;
	}
	
	/**
	 * 组织玩家招募实例Map
	 * @author mp
	 * @date 2014-8-14 上午11:53:31
	 * @param instPlayerId
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static Map<Integer, InstPlayerRecruit> orgPlayerRecuitMap (int instPlayerId) throws Exception{
		Map<Integer, InstPlayerRecruit> playerRecruitMap = Maps.newHashMap();
		List<InstPlayerRecruit> instPlayerRecruitList = getInstPlayerRecruitDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		for (InstPlayerRecruit obj : instPlayerRecruitList) {
			playerRecruitMap.put(obj.getRecruitTypeId(), obj);
		}
		return playerRecruitMap;
	}
	
	/**
	 * 组织白银招募数据
	 * @author mp
	 * @date 2014-8-14 下午2:37:43
	 * @param playerRecruitMap
	 * @param retMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void orgSilverRecruitData (Map<Integer, InstPlayerRecruit> playerRecruitMap, MessageData retMsgData) throws Exception{
		
		long backTime = 0;//倒计时时间-毫秒数
		int remainTimes = 0;//剩余次数
		InstPlayerRecruit instPlayerRecruit = playerRecruitMap.get(StaticRecruitType.silverRecruit);
		
		//首次招募,没有倒计时[返回0], 剩余次数为5
		if (instPlayerRecruit == null) {
			backTime = 0;
			remainTimes = DictMapUtil.getSysConfigIntValue(StaticSysConfig.silverRecruitFreeNum);
		} else {
			//比较当前时间和最后一次招募时间是否为同一天,如果是同一天计算倒计时和剩余次数
			if (DateUtil.isSameDay(DateUtil.getYmdDate(), DateUtil.getYmdDate(instPlayerRecruit.getLastRecruitTime()), DateFormat.YMD)) {
				remainTimes = instPlayerRecruit.getRemainRecruitTimes();
				long freeTime = DateUtil.getMillSecond(instPlayerRecruit.getNextFreeRecruitTime());
				backTime = (freeTime - DateUtil.getCurrMill()) < 0 ? 0 : (freeTime - DateUtil.getCurrMill());
				if (backTime != 0) {
					backTime += 2000;
				}
			} else {
			//不是同一天,免费招募次数和倒计时都重置
				backTime = 0;
				remainTimes = DictMapUtil.getSysConfigIntValue(StaticSysConfig.silverRecruitFreeNum);
			}
		}
		MessageData silverMsgData = new MessageData();
		silverMsgData.putLongItem("1", backTime);//倒计时时间-毫秒数, 0表示没有倒计时 2秒冗余
		silverMsgData.putIntItem("2", remainTimes);//剩余次数
		retMsgData.putMessageItem(StaticRecruitType.silverRecruit + "", silverMsgData.getMsgData());
	}
	
	/**
	 * 组织黄金招募数据
	 * @author mp
	 * @date 2014-8-14 下午2:50:12
	 * @param playerRecruitMap
	 * @param retMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void orgGoldRecruitData (Map<Integer, InstPlayerRecruit> playerRecruitMap, MessageData retMsgData) throws Exception{
		
		MessageData goldMsgData = new MessageData();
		
		long backTime = 0;//倒计时时间-毫秒数
		int goldNum = 0;//元宝数
		InstPlayerRecruit instPlayerRecruit = playerRecruitMap.get(StaticRecruitType.goldRecruit);
		
		//花费元宝数固定,不随次数变化而变化
		goldNum = DictMap.dictRecruitTypeMap.get(StaticRecruitType.diamondRecruit + "").getGoldRecruitFee();
		
		//首次招募,没有倒计时[返回0], 元宝数为配置数据
		if (instPlayerRecruit == null) {
			backTime = 0;
			goldMsgData.putIntItem("3", 1);//是否首次招募 0-不是 1-是
		} else {
			//计算倒计时
			long freeTime = DateUtil.getMillSecond(instPlayerRecruit.getNextFreeRecruitTime());
			backTime = (freeTime - DateUtil.getCurrMill()) < 0 ? 0 : (freeTime - DateUtil.getCurrMill());
			if (backTime != 0) {
				backTime += 2000;
			}
		}
		
		goldMsgData.putLongItem("1", backTime);//倒计时时间-毫秒数
		goldMsgData.putIntItem("2", goldNum);//元宝数
		
		
		retMsgData.putMessageItem(StaticRecruitType.goldRecruit + "", goldMsgData.getMsgData());
	}
	
	/**
	 * 组织钻石招募数据
	 * @author mp
	 * @date 2014-8-14 下午2:51:12
	 * @param playerRecruitMap
	 * @param retMsgData
	 * @throws Exception
	 * @Description
	 */
	public static void orgDiamondRecruitData (Map<Integer, InstPlayerRecruit> playerRecruitMap, MessageData retMsgData) throws Exception{
		
		long backTime = 0;//倒计时时间-毫秒数
		int goldNum = 0;//元宝数
		int currRecruitTimes = 0;
		MessageData diamondMsgData = new MessageData();
		InstPlayerRecruit instPlayerRecruit = playerRecruitMap.get(StaticRecruitType.diamondRecruit);
		
		//花费元宝数固定,不随次数变化而变化
		goldNum = DictMap.dictRecruitTypeMap.get(StaticRecruitType.diamondRecruit + "").getGoldRecruitFee();
		
		//首次招募,没有倒计时[返回0], 元宝数为配置数据
		if (instPlayerRecruit == null) {
			backTime = 0;
			currRecruitTimes = 0;
			diamondMsgData.putIntItem("5", 1);//是否首次招募 0-不是 1-是
		} else {
			//计算倒计时
			if (instPlayerRecruit.getNextFreeRecruitTime().equals("")) {
				backTime = 0;
			} else {
				long freeTime = DateUtil.getMillSecond(instPlayerRecruit.getNextFreeRecruitTime());
				backTime = (freeTime - DateUtil.getCurrMill()) < 0 ? 0 : (freeTime - DateUtil.getCurrMill());
				if (backTime != 0) {
					backTime += 2000;
				}
			}
			currRecruitTimes = instPlayerRecruit.getRecruitSumTimes();
		}
		diamondMsgData.putLongItem("1", backTime);//倒计时时间-毫秒数
		diamondMsgData.putIntItem("2", goldNum);//元宝数
		diamondMsgData.putIntItem("3", DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitGoldNum));//10次招募话费元宝数
		diamondMsgData.putIntItem("4", nextRecruitTimes(currRecruitTimes, 3) - 1);//在购买几次可得紫卡
		
		retMsgData.putMessageItem(StaticRecruitType.diamondRecruit + "", diamondMsgData.getMsgData());
	}
	
	/**
	 * 在过几次可得紫卡
	 * @author mp
	 * @date 2014-8-16 上午9:52:45
	 * @param haveRecruitTimes
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static int nextRecruitTimes (int haveRecruitTimes, int recruitType) {
		
		int nextTimes = -1;
		
		//获取列表
		List<DictRecruitTimesGet> recruitTimesGetList = new ArrayList<>();
		for (DictRecruitTimesGet obj : (List<DictRecruitTimesGet>)DictMapList.dictRecruitTimesGetMap.get(recruitType)) {
			if (obj.getVersion() == 1) {
				recruitTimesGetList.add(obj);
			}
		}
		
		//从小到达排序
		Collections.sort(recruitTimesGetList, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int one = ((DictRecruitTimesGet)a).getRecruitTimes();
				int two = ((DictRecruitTimesGet)b).getRecruitTimes(); 
				return (int)(one - two); 
			}
		}); 
		
		for (DictRecruitTimesGet obj : recruitTimesGetList) {
			if (obj.getRecruitTimes() > haveRecruitTimes) {
				nextTimes = obj.getRecruitTimes() - haveRecruitTimes;
				break;
			}
		}
		
		//以最后一个为基准, 每隔repeat个给以卡
		int lastRecruitTimes = recruitTimesGetList.get(recruitTimesGetList.size() - 1).getRecruitTimes();
		
		//计算剩余次数
		if (nextTimes == -1) {
			nextTimes = DictMapUtil.getSysConfigIntValue(StaticSysConfig.repeatNum) - ((haveRecruitTimes - lastRecruitTimes) % DictMapUtil.getSysConfigIntValue(StaticSysConfig.repeatNum));
		}
		
		return nextTimes;
	}
	
	/**
	 * 白银招募处理
	 * @author mp
	 * @date 2014-8-14 下午4:50:04
	 * @param playerRecruitMap
	 * @param vipLevel
	 * @return
	 * @Description
	 */
	public static int silverRecruitHandler (Map<Integer, InstPlayerRecruit> playerRecruitMap, InstPlayer instPlayer, MessageData syncMsgData, MessageData retMsgData, int diamondRecruitTypeId, Map<String, Object> msgMap) throws Exception{
		
		//定义参数
		int result = 0;
		int instPlayerId = instPlayer.getId();
		int vipLevel = instPlayer.getVipLevel();
		String currTime = DateUtil.getCurrTime();
		long currMill = DateUtil.getCurrMill();
		InstPlayerRecruit instPlayerRecruit = playerRecruitMap.get(StaticRecruitType.silverRecruit);
		
		//招募令
		if (diamondRecruitTypeId == 1) {
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.recruitSign, instPlayerId);
			
			//验证招募令个数
			if (instPlayerThingList.size() <= 0) {
				return 3;
			}
			InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
			
			int cardId = 0;
			int qualityId = 0;
			
			if (instPlayerRecruit != null && isRecruitTimesHit(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.silverRecruit)) {
				int areaNo = getHitAreaNo(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.silverRecruit);
				cardId = FormulaUtil.calcSpecialRecruitCard(areaNo);
			} else {
				//计算招募品质
				qualityId = FormulaUtil.calcRecruitQuality(vipLevel, StaticRecruitType.silverRecruit);
				
				//计算招募得到的卡牌Id
				cardId = FormulaUtil.calcRecruitCard(qualityId, StaticRecruitType.silverRecruit);
			}
			
			//处理招募令
			ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, 1, syncMsgData, msgMap);
			
			//将卡牌放入背包[招募不验证背包]
			InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, cardId);
			
			//初始化玩家卡牌命宫实例表
			DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
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
			
			//更新招募令招募数据
			updateRecruitSignInfo(instPlayerId, currTime, instPlayerRecruit, cardId, StaticRecruitType.silverRecruit, diamondRecruitTypeId);
			
			//跑马灯相关
			String openId = instPlayer.getOpenId();
			MarqueeUtil.putMsgToMarquee(PlayerMapUtil.getPlayerByOpenId(openId).getChannelId(), cardId + "", CustomMarqueeType.MARQUEE_TYPE_CARD, CustomMarqueeType.MARQUEE_SOURCE_RECRUIT);
			
			retMsgData.putStringItem("1", instPlayerCard.getId() + "");
		}else if (diamondRecruitTypeId == 3){
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.recruitSign, instPlayerId);
			
			//验证招募令个数
			if (instPlayerThingList.size() <= 0) {
				return 3;
			}
			InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
			if(instPlayerThing.getNum()<10){
				return 3;
			}
			int cardId = 0;
			int qualityId = 0;
			String instCardIds = "";
			String cardIds = "";
			String names = "";
			String nameF = "";
			for(int i=0;i<10;i++){
				if (instPlayerRecruit != null && isRecruitTimesHit(instPlayerRecruit.getRecruitSumTimes() + i + 1, StaticRecruitType.silverRecruit)) {
					int areaNo = getHitAreaNo(instPlayerRecruit.getRecruitSumTimes() + i + 1, StaticRecruitType.silverRecruit);
					cardId = FormulaUtil.calcSpecialRecruitCard(areaNo);
				} else {
					//计算招募品质
					qualityId = FormulaUtil.calcRecruitQuality(vipLevel, StaticRecruitType.silverRecruit);
					
					//计算招募得到的卡牌Id
					cardId = FormulaUtil.calcRecruitCard(qualityId, StaticRecruitType.silverRecruit);
				}
				InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, cardId);
				instCardIds += instPlayerCard.getId() + ";";
				cardIds += cardId + ";";
				names += DictMap.dictCardMap.get(cardId + "").getName() + ";";
				if (DictMap.dictCardMap.get(cardId + "").getQualityId() == StaticQuality.blue || DictMap.dictCardMap.get(cardId + "").getQualityId() == StaticQuality.purple) {
					nameF += DictMap.dictCardMap.get(cardId + "").getName() + ";";
				}
				//初始化玩家卡牌命宫实例表
				DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
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
				
			}
			//处理招募令
			ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, 10, syncMsgData, msgMap);
			//更新招募令招募数据
			if (instPlayerRecruit != null) {
				instPlayerRecruit.setRecruitSumTimes(instPlayerRecruit.getRecruitSumTimes() + 10);
				getInstPlayerRecruitDAL().update(instPlayerRecruit, instPlayerId);
			}
			
			//记录招募日志
			try {
				String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|" + StaticRecruitType.silverRecruit + "|" + DictMap.dictRecruitTypeMap.get(StaticRecruitType.silverRecruit + "").getName() + "|" + 
			                        DateUtil.getCurrTime() + "|" + 1 + "|" + 0 + "|" + cardIds + "|" + names; 
				LogicLogUtil.info("recruit", logContent.replace("null", ""));
			} catch (Exception e) {
				LogUtil.error("招募日志错误", e);
			}
			
			//推送招募日志
			final int pId = instPlayerId;
			try {
				DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
				if (dictCard.getQualityId() == StaticQuality.blue || dictCard.getQualityId() == StaticQuality.purple) {
					final String nameFinal = nameF; 
					ThreadPoolUtils.executePushData( new ThreadOper() {
						@Override
						public void innerRun() {
							try {
								String pushDataCenterUrl = SysConfigUtil.getValue("push.datacenter.url");
								
								//组织参数
								Player player = PlayerMapUtil.getPlayerByPlayerId(pId);
								Map<String, String> paramMap = player.getCommonPushMap();
								paramMap.put("eventTag", "抽卡");
								paramMap.put("value1string", "招募令10次抽卡");
								paramMap.put("value2string", nameFinal);
								String params = HttpClientUtil.httpBuildQuery(paramMap);
								
								//推送数据
								HttpClientUtil.postMapSubmit(pushDataCenterUrl, params);
								
							} catch (Exception e) {
								e.printStackTrace();
								LogUtil.error("推送招募日志Error", e);
							}
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.error("推送招募日志Error", e);
			}
			//跑马灯相关
			String openId = instPlayer.getOpenId();
			MarqueeUtil.putMsgToMarquee(PlayerMapUtil.getPlayerByOpenId(openId).getChannelId(), cardIds, CustomMarqueeType.MARQUEE_TYPE_CARD, CustomMarqueeType.MARQUEE_SOURCE_RECRUIT);
			retMsgData.putStringItem("1", StringUtil.noContainLastString(instCardIds));
		} else {
			if (instPlayerRecruit != null) {
				//验证次数是否用完[当天并且次数已用完]
				if (DateUtil.isSameDay(DateUtil.getYmdDate(), DateUtil.getYmdDate(instPlayerRecruit.getLastRecruitTime()), DateFormat.YMD) && instPlayerRecruit.getRemainRecruitTimes() == 0) {
					return 1;
				}
				//验证免费招募时间是否已到,白银招募不能花元宝购买招募,只能等免费时间到了,每日固定次数,次日重置
				long nextFreeTime = DateUtil.getMillSecond(instPlayerRecruit.getNextFreeRecruitTime());
				long remainTime = (nextFreeTime - currMill) < 0 ? 0 : (nextFreeTime - currMill);
				if (remainTime != 0) {
					return 2;
				}
			}
			
			int cardId = 0;
			int qualityId = 0;
			if (instPlayerRecruit != null && isRecruitTimesHit(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.silverRecruit)) {
				int areaNo = getHitAreaNo(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.silverRecruit);
				cardId = FormulaUtil.calcSpecialRecruitCard(areaNo);
			} else {
				//计算招募品质
				qualityId = FormulaUtil.calcRecruitQuality(vipLevel, StaticRecruitType.silverRecruit);
				
				//计算招募得到的卡牌Id
				cardId = FormulaUtil.calcRecruitCard(qualityId, StaticRecruitType.silverRecruit);
			}
			
			//将卡牌放入背包[招募不验证背包]
			InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, cardId);
			
			//初始化玩家卡牌命宫实例表
			DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
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
			
			//更新白银招募数据
			updateSilverRecruitInfo(instPlayerId, currMill, currTime, instPlayerRecruit, cardId);
			
			//跑马灯相关
			String openId = instPlayer.getOpenId();
			MarqueeUtil.putMsgToMarquee(PlayerMapUtil.getPlayerByOpenId(openId).getChannelId(), cardId + "", CustomMarqueeType.MARQUEE_TYPE_CARD, CustomMarqueeType.MARQUEE_SOURCE_RECRUIT);
			
			retMsgData.putStringItem("1", instPlayerCard.getId() + "");
		}
		
		return result;
	}
	
	/**
	 * 黄金招募处理
	 * @author mp
	 * @date 2014-8-15 上午11:51:48
	 * @param playerRecruitMap
	 * @param vipLevel
	 * @param instPlayerId
	 * @param syncMsgData
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static int goldRecruitHandler (Map<Integer, InstPlayerRecruit> playerRecruitMap, InstPlayer instPlayer, MessageData syncMsgData, int diamondRecruitTypeId, MessageData retMsgData, Map<String, Object> msgMap) throws Exception{
		
		//定义返回值 1-次数用完
		int result = 0;
		int needGold = 0;
		int instPlayerId = instPlayer.getId();
		int vipLevel = instPlayer.getVipLevel();
		String currTime = DateUtil.getCurrTime();
		long currMill = DateUtil.getMillSecond(currTime);
		InstPlayerRecruit instPlayerRecruit = playerRecruitMap.get(StaticRecruitType.diamondRecruit);
		InstPlayerRecruit instPlayerRecruit1 = playerRecruitMap.get(StaticRecruitType.goldRecruit);
		
		//招募令招募
		if (diamondRecruitTypeId == 1) {
			
		} else if (diamondRecruitTypeId == 2) {
			
			int cardId = 0;
			long remainTime = 0;
			
			//如果未到免费招募时间,验证元宝数
			if (instPlayerRecruit1 == null) {
				remainTime = 0;
			} else {
				if (!instPlayerRecruit1.getNextFreeRecruitTime().equals("")) {
					long nextFreeTime = DateUtil.getMillSecond(instPlayerRecruit1.getNextFreeRecruitTime());
					remainTime = (nextFreeTime - currMill) < 0 ? 0 : (nextFreeTime - currMill);
				}
			}
			
			//验证元宝
			if (remainTime != 0) {
				needGold = DictMap.dictRecruitTypeMap.get(StaticRecruitType.diamondRecruit + "").getGoldRecruitFee();
				if (instPlayer.getGold() < needGold) {
					return 1;
				}
			}
			
			//招募次数是否命中, 如果命中, 从钻石卡牌库中随机给一张紫卡
			if (instPlayerRecruit != null && isRecruitTimesHit(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.diamondRecruit)) {
				int areaNo = getHitAreaNo(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.diamondRecruit);
				cardId = FormulaUtil.calcSpecialRecruitCard(areaNo);
			} else {
				
				//计算招募品质
				int qualityId = FormulaUtil.calcRecruitQuality(vipLevel, StaticRecruitType.diamondRecruit);
				
				//计算招募得到的卡牌Id
				cardId = FormulaUtil.calcRecruitCard(qualityId, StaticRecruitType.diamondRecruit);
			}
			
			//将卡牌放入背包[招募不验证背包]
			InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, cardId);
			
			//初始化玩家卡牌命宫实例表
			DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
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
			
			//扣除招募所耗元宝
			if (remainTime != 0) {
				PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, needGold, msgMap);
				getInstPlayerDAL().update(instPlayer, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			
			//更新钻石招募数据
			updateRecruitInfoForGold(instPlayerId, currMill, currTime, instPlayerRecruit, cardId, remainTime, StaticRecruitType.diamondRecruit, needGold);
			
			//跑马灯相关
			String openId = instPlayer.getOpenId();
			MarqueeUtil.putMsgToMarquee(PlayerMapUtil.getPlayerByOpenId(openId).getChannelId(), cardId + "", CustomMarqueeType.MARQUEE_TYPE_CARD, CustomMarqueeType.MARQUEE_SOURCE_RECRUIT);
			
			retMsgData.putStringItem("1", instPlayerCard.getId() + "");
			
		//10次招募
		} else if (diamondRecruitTypeId == 3) {
			
			//验证元宝
			int tenRecruitNeedGold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitGoldNum);
			if (instPlayer.getGold() < tenRecruitNeedGold) {
				return 1;
			}
			
			List<Integer> cardIdList = null;
			
			//首次招募, 得紫卡[3-5]张,其余为其他品质的卡牌,引导的时候有一次招募，所以不会再走首次招募逻辑
			if (instPlayerRecruit == null) {
				int maxNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitMaxNum);
				cardIdList = getFixNumTenCard(vipLevel, 3, maxNum);
			
			//非首次招募, 得紫卡[1-5]张
			} else {
				int minNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitMinNum);
				int maxNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitMaxNum);
				cardIdList = getFixNumTenCard(vipLevel, minNum, maxNum);
			}
			
			//处理元宝
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, tenRecruitNeedGold, msgMap);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			
			//处理卡牌 和 日志
			String instCardIds = "";
			String cardIds = "";
			String names = "";
			String nameF = "";
			
			for (int cardId : cardIdList) {
				InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, cardId);
				instCardIds += instPlayerCard.getId() + ";";
				cardIds += cardId + ";";
				names += DictMap.dictCardMap.get(cardId + "").getName() + ";";
				
				if (DictMap.dictCardMap.get(cardId + "").getQualityId() == StaticQuality.blue || DictMap.dictCardMap.get(cardId + "").getQualityId() == StaticQuality.purple) {
					nameF += DictMap.dictCardMap.get(cardId + "").getName() + ";";
				}
				
				//初始化玩家卡牌命宫实例表
				DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
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
				
				//记录招募日志-玩家Id[instPlayerId]#招募类型Id[recruitTypeId]#招募时间[recruitTime]#招募费用[recruitFee]#招募的卡牌Id[recruitCardId]
//						String content = instPlayerId + "#" + StaticRecruitType.diamondRecruit + "#" + currTime + "#" + tenRecruitNeedGold + "#" + cardId;
//						LogicLogUtil.info("recruit", content);
			}
			
			//记录招募日志
			try {
				String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|" + StaticRecruitType.diamondRecruit + "|" + DictMap.dictRecruitTypeMap.get(StaticRecruitType.diamondRecruit + "").getName() + "|" + 
			                        DateUtil.getCurrTime() + "|" + 0 + "|" + tenRecruitNeedGold + "|" + cardIds + "|" + names;
				LogicLogUtil.info("recruit", logContent.replace("null", ""));
			} catch (Exception e) {
				LogUtil.error("招募日志错误", e);
			}
			
			
			//推送招募日志
			try {
				final int pId = instPlayerId;
				final String nameFinal = nameF; 
				ThreadPoolUtils.executePushData( new ThreadOper() {
					@Override
					public void innerRun() {
						try {
							String pushDataCenterUrl = SysConfigUtil.getValue("push.datacenter.url");
							
							//组织参数
							Player player = PlayerMapUtil.getPlayerByPlayerId(pId);
							Map<String, String> paramMap = player.getCommonPushMap();
							paramMap.put("eventTag", "抽卡");
							paramMap.put("value1string", "十次抽卡");
							paramMap.put("value2string", nameFinal);
							String params = HttpClientUtil.httpBuildQuery(paramMap);
							
							//推送数据
							HttpClientUtil.postMapSubmit(pushDataCenterUrl, params);
							
						} catch (Exception e) {
							e.printStackTrace();
							LogUtil.error("推送招募日志Error", e);
						}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.error("推送招募日志Error", e);
			}
			
			//记录1次招募实例
			updateTenRecruitInfo(instPlayerId, currTime, instPlayerRecruit, StaticRecruitType.diamondRecruit);
			
			//跑马灯相关
			String openId = instPlayer.getOpenId();
			MarqueeUtil.putMsgToMarquee(PlayerMapUtil.getPlayerByOpenId(openId).getChannelId(), cardIds, CustomMarqueeType.MARQUEE_TYPE_CARD, CustomMarqueeType.MARQUEE_SOURCE_RECRUITTEN);
			
			retMsgData.putStringItem("1", StringUtil.noContainLastString(instCardIds));
		}
		
		return result;
	}
	
	/**
	 * 招募次数是否命中
	 * @author mp
	 * @date 2014-8-15 下午5:14:49
	 * @param recruitTimes
	 * @return
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	private static boolean isRecruitTimesHit (int recruitTimes, int recruitType) {
		
		//获取列表
		List<DictRecruitTimesGet> recruitTimesGetList = (List<DictRecruitTimesGet>)DictMapList.dictRecruitTimesGetMap.get(recruitType);
		
		//从小到达排序
		Collections.sort(recruitTimesGetList, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int one = ((DictRecruitTimesGet)a).getRecruitTimes();
				int two = ((DictRecruitTimesGet)b).getRecruitTimes(); 
				return (int)(one - two); 
			}
		}); 
		
		//先验证,招募次数是否在列表中
		for (DictRecruitTimesGet obj : recruitTimesGetList) {
			if (recruitTimes == obj.getRecruitTimes()) {
				return true;
			}
		}
		
		//最后一个到达几次可给紫卡
		int lastRecruitTimes = recruitTimesGetList.get(recruitTimesGetList.size() - 1).getRecruitTimes();
		
		//以最后一个为基准, 每隔repeat个给以卡
		DictRecruitType dictRecruitType = DictMap.dictRecruitTypeMap.get(recruitType + "");
		int repeatNum = dictRecruitType.getInterval();
		
		//(当前招募次数 - 最后一个) % 间隔 == 0, 命中
		if ((recruitTimes - lastRecruitTimes) % repeatNum == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 确定次数命中后,返回此次命中对应的卡牌库编号
	 * @author mp
	 * @date 2015-4-29 下午3:56:24
	 * @param recruitTimes
	 * @param recruitType
	 * @return
	 * @Description
	 */
	@SuppressWarnings({ "unchecked" })
	private static int getHitAreaNo (int recruitTimes, int recruitType) {
		
		//获取列表
		List<DictRecruitTimesGet> recruitTimesGetList = (List<DictRecruitTimesGet>)DictMapList.dictRecruitTimesGetMap.get(recruitType);
		
		//指定次数给卡的库号
		for (DictRecruitTimesGet obj : recruitTimesGetList) {
			if (recruitTimes == obj.getRecruitTimes()) {
				return obj.getAreaNo();
			}
		}
		
		//以最后一次为基准,固定间隔给卡的库号
		DictRecruitType dictRecruitType = DictMap.dictRecruitTypeMap.get(recruitType + "");
		return dictRecruitType.getIntervalAreaNo();
	}
	
	/**
	 * 招募次数到了,随机获取一个紫卡
	 * @author mp
	 * @date 2014-8-15 下午5:22:15
	 * @return
	 * @Description
	 */
//	@SuppressWarnings("unchecked")
//	private static int getCardByRecruitSign () {
//		
//		//组织紫卡列表
//		List<DictRecruitCard> purpleCardList = Lists.newArrayList();
//		List<DictRecruitCard> recruitCardList = (List<DictRecruitCard>)DictMapList.dictRecruitCardMap.get(StaticRecruitType.diamondRecruit);
//		for (DictRecruitCard obj : recruitCardList) {
//			if (obj.getQualityId() == StaticQuality.purple) {
//				purpleCardList.add(obj);
//			}
//		}
//		
//		//获取随机数
//		int randomNum = RandomUtil.getRandomInt(purpleCardList.size());
//		
//		return purpleCardList.get(randomNum).getCardId();
//	}
	
	/**
	 * 首次招募获得卡牌
	 * @author mp
	 * @date 2014-8-15 下午5:40:26
	 * @return
	 * @Description
	 */
//	private static int getCardByFirstRecruit (int recruitTypeId) {
//		String firstRecruitCardList = DictMap.dictRecruitTypeMap.get(recruitTypeId + "").getFirstRecruitCardList();
//		String [] cardArray = firstRecruitCardList.split(";");
//		int randomNum = RandomUtil.getRandomInt(cardArray.length);
//		return ConvertUtil.toInt(cardArray[randomNum]);
//	}
	
	/**
	 * 钻石招募处理
	 * @author mp
	 * @date 2014-8-15 上午11:54:07
	 * @param playerRecruitMap
	 * @param vipLevel
	 * @param instPlayerId
	 * @param syncMsgData
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static int diamondRecruitHandler (Map<Integer, InstPlayerRecruit> playerRecruitMap, InstPlayer instPlayer, MessageData syncMsgData, int diamondRecruitTypeId, MessageData retMsgData, Map<String, Object> msgMap) throws Exception{
		
		//定义返回值 1-次数用完
		int result = 0;
		int needGold = 0;
		int instPlayerId = instPlayer.getId();
		int vipLevel = instPlayer.getVipLevel();
		String currTime = DateUtil.getCurrTime();
		long currMill = DateUtil.getMillSecond(currTime);
		InstPlayerRecruit instPlayerRecruit = playerRecruitMap.get(StaticRecruitType.diamondRecruit);
		
		//招募令招募
		if (diamondRecruitTypeId == 1) {
			List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.recruitSign, instPlayerId);
			
			//验证招募令个数
			if (instPlayerThingList.size() <= 0) {
				return 3;
			}
			InstPlayerThing instPlayerThing = instPlayerThingList.get(0);
			if(instPlayerThing.getNum()<10){
				return 3;
			}
			int cardId = 0;
			int qualityId = 0;
			String instCardIds = "";
			String cardIds = "";
			String names = "";
			String nameF = "";
			for(int i=0;i<10;i++){
				if (instPlayerRecruit != null && isRecruitTimesHit(instPlayerRecruit.getRecruitSumTimes() + i + 1, StaticRecruitType.silverRecruit)) {
					int areaNo = getHitAreaNo(instPlayerRecruit.getRecruitSumTimes() + i + 1, StaticRecruitType.silverRecruit);
					cardId = FormulaUtil.calcSpecialRecruitCard(areaNo);
				} else {
					//计算招募品质
					qualityId = FormulaUtil.calcRecruitQuality(vipLevel, StaticRecruitType.silverRecruit);
					
					//计算招募得到的卡牌Id
					cardId = FormulaUtil.calcRecruitCard(qualityId, StaticRecruitType.silverRecruit);
				}
				InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, cardId);
				instCardIds += instPlayerCard.getId() + ";";
				cardIds += cardId + ";";
				names += DictMap.dictCardMap.get(cardId + "").getName() + ";";
				if (DictMap.dictCardMap.get(cardId + "").getQualityId() == StaticQuality.blue || DictMap.dictCardMap.get(cardId + "").getQualityId() == StaticQuality.purple) {
					nameF += DictMap.dictCardMap.get(cardId + "").getName() + ";";
				}
				//初始化玩家卡牌命宫实例表
				DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
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
				
			}
			//处理招募令
			ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, 10, syncMsgData, msgMap);
			//更新招募令招募数据
			//updateRecruitSignInfo(instPlayerId, currTime, instPlayerRecruit, cardId, StaticRecruitType.silverRecruit, diamondRecruitTypeId);
			

			if (instPlayerRecruit != null) {
				instPlayerRecruit.setRecruitSumTimes(instPlayerRecruit.getRecruitSumTimes() + 10);
				getInstPlayerRecruitDAL().update(instPlayerRecruit, instPlayerId);
			}
			
			//记录招募日志
			try {
				String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|" + StaticRecruitType.silverRecruit + "|" + DictMap.dictRecruitTypeMap.get(StaticRecruitType.silverRecruit + "").getName() + "|" + 
			                        DateUtil.getCurrTime() + "|" + 1 + "|" + 0 + "|" + cardIds + "|" + names; 
				LogicLogUtil.info("recruit", logContent.replace("null", ""));
			} catch (Exception e) {
				LogUtil.error("招募日志错误", e);
			}
			
			//推送招募日志
			final int pId = instPlayerId;
			try {
				DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
				if (dictCard.getQualityId() == StaticQuality.blue || dictCard.getQualityId() == StaticQuality.purple) {
					final String nameFinal = nameF; 
					ThreadPoolUtils.executePushData( new ThreadOper() {
						@Override
						public void innerRun() {
							try {
								String pushDataCenterUrl = SysConfigUtil.getValue("push.datacenter.url");
								
								//组织参数
								Player player = PlayerMapUtil.getPlayerByPlayerId(pId);
								Map<String, String> paramMap = player.getCommonPushMap();
								paramMap.put("eventTag", "抽卡");
								paramMap.put("value1string", "10次抽卡");
								paramMap.put("value2string", nameFinal);
								String params = HttpClientUtil.httpBuildQuery(paramMap);
								
								//推送数据
								HttpClientUtil.postMapSubmit(pushDataCenterUrl, params);
								
							} catch (Exception e) {
								e.printStackTrace();
								LogUtil.error("推送招募日志Error", e);
							}
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.error("推送招募日志Error", e);
			}
		
			
			//跑马灯相关
			String openId = instPlayer.getOpenId();
			MarqueeUtil.putMsgToMarquee(PlayerMapUtil.getPlayerByOpenId(openId).getChannelId(), cardIds, CustomMarqueeType.MARQUEE_TYPE_CARD, CustomMarqueeType.MARQUEE_SOURCE_RECRUIT);
			
			retMsgData.putStringItem("1", StringUtil.noContainLastString(instCardIds));
		} else if (diamondRecruitTypeId == 2) {
			
			int cardId = 0;
			long remainTime = 0;
			
			//处理引导招募[首次的时候是空的,不需要知道具体第几步]
			if (instPlayerRecruit == null) {
				cardId = ConvertUtil.toInt(DictMap.dictGuipStepMap.get(10000+"").getThings());
			} else {
				
				//如果未到免费招募时间,验证元宝数
				if (!instPlayerRecruit.getNextFreeRecruitTime().equals("")) {
					long nextFreeTime = DateUtil.getMillSecond(instPlayerRecruit.getNextFreeRecruitTime());
					remainTime = (nextFreeTime - currMill) < 0 ? 0 : (nextFreeTime - currMill);
				}
				
				//验证元宝
				if (remainTime != 0) {
					needGold = DictMap.dictRecruitTypeMap.get(StaticRecruitType.diamondRecruit + "").getGoldRecruitFee();
					if (instPlayer.getGold() < needGold) {
						return 1;
					}
				}
				
				//处理首次招募[首次使用钻石招募]
				if (instPlayerRecruit.getFeeRecruitTimes() == 0 && remainTime != 0) {
//					cardId = getCardByFirstRecruit(StaticRecruitType.diamondRecruit);//首抽不给紫卡了,修改时间：2015-03-17， 决定修改人：孙毅
					
					//招募次数是否命中, 如果命中, 从钻石卡牌库中随机给一张紫卡##2015-04-06改成到达招募次数和十连抽都从特殊库里根据配比来抽,并规定三种招募类型都有达到次数的需求。 修改人：贾克强
					//招募类型 1-白银 2-黄金 3-钻石 4-十连抽
					if (instPlayerRecruit != null && isRecruitTimesHit(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.diamondRecruit)) {
						int areaNo = getHitAreaNo(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.diamondRecruit);
						cardId = FormulaUtil.calcSpecialRecruitCard(areaNo);
					} else {
						
						//计算招募品质
						int qualityId = FormulaUtil.calcRecruitQuality(vipLevel, StaticRecruitType.diamondRecruit);
						
						//计算招募得到的卡牌Id
						cardId = FormulaUtil.calcRecruitCard(qualityId, StaticRecruitType.diamondRecruit);
					}
				} else {
					//招募次数是否命中, 如果命中, 从钻石卡牌库中随机给一张紫卡
					if (instPlayerRecruit != null && isRecruitTimesHit(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.diamondRecruit)) {
						int areaNo = getHitAreaNo(instPlayerRecruit.getRecruitSumTimes() + 1, StaticRecruitType.diamondRecruit);
						cardId = FormulaUtil.calcSpecialRecruitCard(areaNo);
					} else {
						
						//计算招募品质
						int qualityId = FormulaUtil.calcRecruitQuality(vipLevel, StaticRecruitType.diamondRecruit);
						
						//计算招募得到的卡牌Id
						cardId = FormulaUtil.calcRecruitCard(qualityId, StaticRecruitType.diamondRecruit);
					}
				}
			}
			
			//将卡牌放入背包[招募不验证背包]
			InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, cardId);
			
			//初始化玩家卡牌命宫实例表
			DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
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
			
			//扣除招募所耗元宝
			if (remainTime != 0) {
				PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, needGold, msgMap);
				getInstPlayerDAL().update(instPlayer, instPlayerId);
				OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			}
			
			//更新钻石招募数据
			updateRecruitInfo(instPlayerId, currMill, currTime, instPlayerRecruit, cardId, remainTime, StaticRecruitType.diamondRecruit, needGold);
			
			//跑马灯相关
			String openId = instPlayer.getOpenId();
			MarqueeUtil.putMsgToMarquee(PlayerMapUtil.getPlayerByOpenId(openId).getChannelId(), cardId + "", CustomMarqueeType.MARQUEE_TYPE_CARD, CustomMarqueeType.MARQUEE_SOURCE_RECRUIT);
			
			retMsgData.putStringItem("1", instPlayerCard.getId() + "");
			
		//10次招募
		} else if (diamondRecruitTypeId == 3) {
			
			//验证元宝
			int tenRecruitNeedGold = DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitGoldNum);
			if (instPlayer.getGold() < tenRecruitNeedGold) {
				return 1;
			}
			
			List<Integer> cardIdList = null;
			
			//首次招募, 得紫卡[3-5]张,其余为其他品质的卡牌
			if (instPlayerRecruit == null) {
				int maxNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitMaxNum);
				cardIdList = getFixNumTenCard(vipLevel, 3, maxNum);
			
			//非首次招募, 得紫卡[1-5]张
			} else {
				int minNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitMinNum);
				int maxNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.tenRecruitMaxNum);
				cardIdList = getFixNumTenCard(vipLevel, minNum, maxNum);
			}
			
			//处理元宝
			PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, tenRecruitNeedGold, msgMap);
			getInstPlayerDAL().update(instPlayer, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
			
			//处理卡牌 和 日志
			String instCardIds = "";
			String cardIds = "";
			String names = "";
			String nameF = "";
			
			for (int cardId : cardIdList) {
				InstPlayerCard instPlayerCard = CardUtil.addPlayerCard(instPlayerId, cardId);
				instCardIds += instPlayerCard.getId() + ";";
				cardIds += cardId + ";";
				names += DictMap.dictCardMap.get(cardId + "").getName() + ";";
				if (DictMap.dictCardMap.get(cardId + "").getQualityId() == StaticQuality.blue || DictMap.dictCardMap.get(cardId + "").getQualityId() == StaticQuality.purple) {
					nameF += DictMap.dictCardMap.get(cardId + "").getName() + ";";
				}
				
				//初始化玩家卡牌命宫实例表
				DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
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
				
				//记录招募日志-玩家Id[instPlayerId]#招募类型Id[recruitTypeId]#招募时间[recruitTime]#招募费用[recruitFee]#招募的卡牌Id[recruitCardId]
//				String content = instPlayerId + "#" + StaticRecruitType.diamondRecruit + "#" + currTime + "#" + tenRecruitNeedGold + "#" + cardId;
//				LogicLogUtil.info("recruit", content);
			}
			
			//记录招募日志
			try {
				String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|" + StaticRecruitType.diamondRecruit + "|" + DictMap.dictRecruitTypeMap.get(StaticRecruitType.diamondRecruit + "").getName() + "|" + 
			                        DateUtil.getCurrTime() + "|" + 0 + "|" + tenRecruitNeedGold + "|" + cardIds + "|" + names;
				LogicLogUtil.info("recruit", logContent.replace("null", ""));
			} catch (Exception e) {
				LogUtil.error("招募日志错误", e);
			}
			
			//推送招募日志
			try {
				final int pId = instPlayerId;
				final String nameFinal = nameF; 
				ThreadPoolUtils.executePushData( new ThreadOper() {
					@Override
					public void innerRun() {
						try {
							String pushDataCenterUrl = SysConfigUtil.getValue("push.datacenter.url");
							
							//组织参数
							Player player = PlayerMapUtil.getPlayerByPlayerId(pId);
							Map<String, String> paramMap = player.getCommonPushMap();
							paramMap.put("eventTag", "抽卡");
							paramMap.put("value1string", "十次抽卡");
							paramMap.put("value2string", nameFinal);
							String params = HttpClientUtil.httpBuildQuery(paramMap);
							
							//推送数据
							HttpClientUtil.postMapSubmit(pushDataCenterUrl, params);
							
						} catch (Exception e) {
							e.printStackTrace();
							LogUtil.error("推送招募日志Error", e);
						}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.error("推送招募日志Error", e);
			}
			
			//记录1次招募实例
			updateTenRecruitInfo(instPlayerId, currTime, instPlayerRecruit, StaticRecruitType.diamondRecruit);
			
			//跑马灯相关
			String openId = instPlayer.getOpenId();
			MarqueeUtil.putMsgToMarquee(PlayerMapUtil.getPlayerByOpenId(openId).getChannelId(), cardIds, CustomMarqueeType.MARQUEE_TYPE_CARD, CustomMarqueeType.MARQUEE_SOURCE_RECRUITTEN);
			
			retMsgData.putStringItem("1", StringUtil.noContainLastString(instCardIds));
		}
		
		return result;
	}
	
	/**
	 * 得到招募十次的卡牌
	 * @author mp
	 * @date 2014-8-16 上午8:39:09
	 * @param vipLevel
	 * @param minNum
	 * @param maxNum
	 * @return
	 * @Description
	 */
	private static List<Integer> getFixNumTenCard (int vipLevel, int minNum, int maxNum) {

		List<Integer> cardIdList = Lists.newArrayList();
		int cardNum = RandomUtil.getRangeInt(minNum, maxNum);
		
		//抽cardNum张紫卡
		for (int i = 0; i < cardNum; i++) {
			int cardId = FormulaUtil.calcSpecialRecruitCard(DictMap.dictRecruitTypeMap.get(StaticRecruitType.diamondRecruit + "").getDiamondTenAreaNo());
			cardIdList.add(cardId);
		}
		
		//获取剩余卡牌
		int index = 0;
		while (true) {
			index ++;
			
			//计算招募品质
			int qualityId = FormulaUtil.calcRecruitQuality(vipLevel, StaticRecruitType.diamondRecruit);
			
			//计算招募得到的卡牌Id
			int cardId = FormulaUtil.calcRecruitCard(qualityId, StaticRecruitType.diamondRecruit);
			
			if (DictMap.dictCardMap.get(cardId + "").getQualityId() == StaticQuality.purple) {
				continue;
			} else {
				cardIdList.add(cardId);
			}
			if (cardIdList.size() == DictMapUtil.getSysConfigIntValue(StaticSysConfig.continueNum)) {
				break;
			}
			//预防死循环
			if (index >= 100) {
				break;
			}
		}
		return cardIdList;
	}
	
	/**
	 * 招募令处理
	 * @author mp
	 * @date 2014-8-15 下午5:34:50
	 * @param instPlayerId
	 * @param currTime
	 * @param instPlayerRecruit
	 * @param cardId
	 * @param recruitTypeId
	 * @throws Exception
	 * @Description
	 */
	public static void updateRecruitSignInfo (int instPlayerId, String currTime, InstPlayerRecruit instPlayerRecruit, int cardId, int recruitTypeId, int diamondRecruitTypeId) throws Exception{
		if (instPlayerRecruit != null) {
			instPlayerRecruit.setRecruitSumTimes(instPlayerRecruit.getRecruitSumTimes() + 1);
			getInstPlayerRecruitDAL().update(instPlayerRecruit, instPlayerId);
		}
		
		//记录招募日志
		try {
			String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|" + recruitTypeId + "|" + DictMap.dictRecruitTypeMap.get(recruitTypeId + "").getName() + "|" + 
		                        DateUtil.getCurrTime() + "|" + 1 + "|" + 0 + "|" + cardId + "|" + DictMap.dictCardMap.get(cardId + "").getName(); 
			LogicLogUtil.info("recruit", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("招募日志错误", e);
		}
		
		//推送招募日志
		final int pId = instPlayerId;
		try {
			DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
			if (dictCard.getQualityId() == StaticQuality.blue || dictCard.getQualityId() == StaticQuality.purple) {
				final String name = dictCard.getName();
				ThreadPoolUtils.executePushData( new ThreadOper() {
					@Override
					public void innerRun() {
						try {
							String pushDataCenterUrl = SysConfigUtil.getValue("push.datacenter.url");
							
							//组织参数
							Player player = PlayerMapUtil.getPlayerByPlayerId(pId);
							Map<String, String> paramMap = player.getCommonPushMap();
							paramMap.put("eventTag", "抽卡");
							paramMap.put("value1string", "一次抽卡");
							paramMap.put("value2string", name);
							String params = HttpClientUtil.httpBuildQuery(paramMap);
							
							//推送数据
							HttpClientUtil.postMapSubmit(pushDataCenterUrl, params);
							
						} catch (Exception e) {
							e.printStackTrace();
							LogUtil.error("推送招募日志Error", e);
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("推送招募日志Error", e);
		}
		
		
//		//初始招募
//		if (instPlayerRecruit == null) {
//			InstPlayerRecruit model = new InstPlayerRecruit();
//			model.setInstPlayerId(instPlayerId);
//			model.setRecruitTypeId(recruitTypeId);
//			model.setRecruitSumTimes(1);
//			model.setFeeRecruitTimes(0);
//			model.setLastRecruitTime(currTime);
//			model.setLastFreeRecruitTime("");
//			model.setNextFreeRecruitTime("");
//			model.setRemainRecruitTimes(0);//黄金和钻石招募里没有剩余招募次数的概念
//			getInstPlayerRecruitDAL().add(model, instPlayerId);
//		} else {
//			instPlayerRecruit.setLastRecruitTime(currTime);
//			instPlayerRecruit.setRecruitSumTimes(instPlayerRecruit.getRecruitSumTimes() + 1);
//			getInstPlayerRecruitDAL().update(instPlayerRecruit, instPlayerId);
//		}
//		
//		//记录招募日志-玩家Id[instPlayerId]#招募类型Id[recruitTypeId]#招募时间[recruitTime]#招募费用[recruitFee]#招募的卡牌Id[recruitCardId]#招募令招募
//		String content = instPlayerId + "#" + recruitTypeId + "#" + currTime + "#" + 0 + "#" + cardId + "#" + diamondRecruitTypeId;
//		LogicLogUtil.info("recruit", content);
	}
	
	/**
	 * 更新十次招募数据
	 * @author mp
	 * @date 2014-8-16 上午8:49:36
	 * @param instPlayerId
	 * @param currTime
	 * @param instPlayerRecruit
	 * @param cardId
	 * @param recruitTypeId
	 * @throws Exception
	 * @Description
	 */
	public static void updateTenRecruitInfo (int instPlayerId, String currTime, InstPlayerRecruit instPlayerRecruit, int recruitTypeId) throws Exception{
		//初始招募
		if (instPlayerRecruit == null) {
			InstPlayerRecruit model = new InstPlayerRecruit();
			model.setInstPlayerId(instPlayerId);
			model.setRecruitTypeId(recruitTypeId);
			model.setRecruitSumTimes(10);
			model.setFeeRecruitTimes(10);
			model.setLastRecruitTime(currTime);
			model.setLastFreeRecruitTime("");
			model.setNextFreeRecruitTime("");
			model.setRemainRecruitTimes(0);//黄金和钻石招募里没有剩余招募次数的概念
			getInstPlayerRecruitDAL().add(model, 0);
		} else {
			instPlayerRecruit.setLastRecruitTime(currTime);
			instPlayerRecruit.setFeeRecruitTimes(instPlayerRecruit.getFeeRecruitTimes() + 10);
			instPlayerRecruit.setRecruitSumTimes(instPlayerRecruit.getRecruitSumTimes() + 10);
			getInstPlayerRecruitDAL().update(instPlayerRecruit, 0);
		}
	}
	
	/**
	 * 更新招募信息 [黄金]
	 * @author mp
	 * @date 2015-8-18 上午11:09:32
	 * @param instPlayerId
	 * @param currMill
	 * @param currTime
	 * @param instPlayerRecruit
	 * @param cardId
	 * @param remainTime
	 * @param recruitTypeId
	 * @param recruitFee
	 * @throws Exception
	 * @Description
	 */
	private static void updateRecruitInfoForGold (int instPlayerId, long currMill, String currTime, InstPlayerRecruit instPlayerRecruit, int cardId, long remainTime, int recruitTypeId, int recruitFee) throws Exception{
		
		//处理活动黄金招募  //等于0表示免费招募
		//初始招募
		List<InstPlayerRecruit> instPlayerRecruitList = getInstPlayerRecruitDAL().getList("instPlayerId = " + instPlayerId + " and recruitTypeId = " + StaticRecruitType.goldRecruit, 0);
		InstPlayerRecruit instPlayerRecruit1 = null;
		if (instPlayerRecruitList.size() > 0) {
			instPlayerRecruit1 = instPlayerRecruitList.get(0);
		}
		
		if (instPlayerRecruit1 == null) {
			recruitTypeId = StaticRecruitType.goldRecruit;
			InstPlayerRecruit model = new InstPlayerRecruit();
			model.setInstPlayerId(instPlayerId);
			model.setRecruitTypeId(recruitTypeId);
			
			long nextFreeMill = currMill + DictMap.dictRecruitTypeMap.get(recruitTypeId + "").getCoolTime() * 60 * 1000;
			model.setNextFreeRecruitTime(DateUtil.getTimeByMill(nextFreeMill));
			getInstPlayerRecruitDAL().add(model, 0);
		} else {
			recruitTypeId = StaticRecruitType.goldRecruit;
			//修改下次免费招募时间
			long nextFreeMill = currMill + DictMap.dictRecruitTypeMap.get(recruitTypeId + "").getCoolTime() * 60 * 1000;
			instPlayerRecruit1.setNextFreeRecruitTime(DateUtil.getTimeByMill(nextFreeMill));
			getInstPlayerRecruitDAL().update(instPlayerRecruit1, 0);
		}
		
		//当花元宝招募时,处理对商城黄金招募的影响
		if (remainTime != 0) {
			instPlayerRecruit.setRecruitSumTimes(instPlayerRecruit.getRecruitSumTimes() + 1);//只对再招募几次必招什么卡有影响
			getInstPlayerRecruitDAL().update(instPlayerRecruit, 0);
		}
		
		//记录招募日志
		try {
			String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|" + recruitTypeId + "|" + DictMap.dictRecruitTypeMap.get(recruitTypeId + "").getName() + "|" + 
		                        DateUtil.getCurrTime() + "|" + 0 + "|" + recruitFee + "|" + cardId + "|" + DictMap.dictCardMap.get(cardId + "").getName();
			LogicLogUtil.info("recruit", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("招募日志错误", e);
		}
		
		//推送招募日志
		final int pId = instPlayerId;
		try {
			DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
			if (dictCard.getQualityId() == StaticQuality.blue || dictCard.getQualityId() == StaticQuality.purple) {
				final String name = dictCard.getName();
				ThreadPoolUtils.executePushData( new ThreadOper() {
					@Override
					public void innerRun() {
						try {
							String pushDataCenterUrl = SysConfigUtil.getValue("push.datacenter.url");
							
							//组织参数
							Player player = PlayerMapUtil.getPlayerByPlayerId(pId);
							Map<String, String> paramMap = player.getCommonPushMap();
							paramMap.put("eventTag", "抽卡");
							paramMap.put("value1string", "一次抽卡");
							paramMap.put("value2string", name);
							String params = HttpClientUtil.httpBuildQuery(paramMap);
							
							//推送数据
							HttpClientUtil.postMapSubmit(pushDataCenterUrl, params);
							
						} catch (Exception e) {
							e.printStackTrace();
							LogUtil.error("推送招募日志Error", e);
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("推送招募日志Error", e);
		}
		
		//记录招募日志-玩家Id[instPlayerId]#招募类型Id[recruitTypeId]#招募时间[recruitTime]#招募费用[recruitFee]#招募的卡牌Id[recruitCardId]
//		String content = instPlayerId + "#" + recruitTypeId + "#" + currTime + "#" + recruitFee + "#" + cardId;
//		LogicLogUtil.info("recruit", content);
	}
	
	/**
	 * 更新招募信息 [黄金 & 钻石]
	 * @author mp
	 * @date 2014-8-15 下午2:26:41
	 * @param instPlayerId
	 * @param currMill
	 * @param currTime
	 * @param instPlayerRecruit
	 * @param cardId
	 * @param remainTime
	 * @throws Exception
	 * @Description
	 */
	private static void updateRecruitInfo (int instPlayerId, long currMill, String currTime, InstPlayerRecruit instPlayerRecruit, int cardId, long remainTime, int recruitTypeId, int recruitFee) throws Exception{
		//初始招募
		if (instPlayerRecruit == null) {
			InstPlayerRecruit model = new InstPlayerRecruit();
			model.setInstPlayerId(instPlayerId);
			model.setRecruitTypeId(recruitTypeId);
			model.setRecruitSumTimes(1);
			model.setFeeRecruitTimes(0);
			model.setLastRecruitTime(currTime);
			model.setLastFreeRecruitTime(currTime);
			
			long nextFreeMill = currMill + DictMap.dictRecruitTypeMap.get(recruitTypeId + "").getCoolTime() * 60 * 1000;
			model.setNextFreeRecruitTime(DateUtil.getTimeByMill(nextFreeMill));
			model.setRemainRecruitTimes(0);//黄金招募里没有剩余招募次数的概念
			getInstPlayerRecruitDAL().add(model, 0);
		} else {
			//等于0表示免费招募
			if (remainTime == 0) {
				instPlayerRecruit.setLastFreeRecruitTime(currTime);
				
				//修改下次免费招募时间
				long nextFreeMill = currMill + DictMap.dictRecruitTypeMap.get(recruitTypeId + "").getCoolTime() * 60 * 1000;
				instPlayerRecruit.setNextFreeRecruitTime(DateUtil.getTimeByMill(nextFreeMill));
			} else {
				instPlayerRecruit.setFeeRecruitTimes(instPlayerRecruit.getFeeRecruitTimes() + 1);
			}
			instPlayerRecruit.setLastRecruitTime(currTime);
			instPlayerRecruit.setRecruitSumTimes(instPlayerRecruit.getRecruitSumTimes() + 1);
			
			getInstPlayerRecruitDAL().update(instPlayerRecruit, 0);
		}
		
		//记录招募日志
		try {
			String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|" + recruitTypeId + "|" + DictMap.dictRecruitTypeMap.get(recruitTypeId + "").getName() + "|" + 
		                        DateUtil.getCurrTime() + "|" + 0 + "|" + recruitFee + "|" + cardId + "|" + DictMap.dictCardMap.get(cardId + "").getName();
			LogicLogUtil.info("recruit", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("招募日志错误", e);
		}
		
		//推送招募日志
		final int pId = instPlayerId;
		try {
			DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
			if (dictCard.getQualityId() == StaticQuality.blue || dictCard.getQualityId() == StaticQuality.purple) {
				final String name = dictCard.getName();
				ThreadPoolUtils.executePushData( new ThreadOper() {
					@Override
					public void innerRun() {
						try {
							String pushDataCenterUrl = SysConfigUtil.getValue("push.datacenter.url");
							
							//组织参数
							Player player = PlayerMapUtil.getPlayerByPlayerId(pId);
							Map<String, String> paramMap = player.getCommonPushMap();
							paramMap.put("eventTag", "抽卡");
							paramMap.put("value1string", "一次抽卡");
							paramMap.put("value2string", name);
							String params = HttpClientUtil.httpBuildQuery(paramMap);
							
							//推送数据
							HttpClientUtil.postMapSubmit(pushDataCenterUrl, params);
							
						} catch (Exception e) {
							e.printStackTrace();
							LogUtil.error("推送招募日志Error", e);
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("推送招募日志Error", e);
		}
		
		//记录招募日志-玩家Id[instPlayerId]#招募类型Id[recruitTypeId]#招募时间[recruitTime]#招募费用[recruitFee]#招募的卡牌Id[recruitCardId]
//		String content = instPlayerId + "#" + recruitTypeId + "#" + currTime + "#" + recruitFee + "#" + cardId;
//		LogicLogUtil.info("recruit", content);
	}
	
	/**
	 * 更新白银招募数据
	 * @author mp
	 * @date 2014-8-15 上午11:40:33
	 * @param instPlayerId
	 * @param currMill
	 * @param currTime
	 * @param instPlayerRecruit
	 * @param cardId
	 * @throws Exception
	 * @Description
	 */
	private static void updateSilverRecruitInfo (int instPlayerId, long currMill, String currTime, InstPlayerRecruit instPlayerRecruit, int cardId) throws Exception{
		//初始招募
		if (instPlayerRecruit == null) {
			InstPlayerRecruit model = new InstPlayerRecruit();
			model.setInstPlayerId(instPlayerId);
			model.setRecruitTypeId(StaticRecruitType.silverRecruit);
			model.setRecruitSumTimes(1);
			model.setFeeRecruitTimes(0);
			model.setLastRecruitTime(currTime);
			model.setLastFreeRecruitTime(currTime);
			
			long nextFreeMill = currMill + DictMap.dictRecruitTypeMap.get(StaticRecruitType.silverRecruit + "").getCoolTime() * 60 * 1000;
			model.setNextFreeRecruitTime(DateUtil.getTimeByMill(nextFreeMill));
			model.setRemainRecruitTimes(ConvertUtil.toInt(DictMap.dictSysConfigMap.get(StaticSysConfig.silverRecruitFreeNum + "").getValue()) - 1);
			getInstPlayerRecruitDAL().add(model, instPlayerId);
		} else {
			//判断是否为同一天,同一天更新时间和次数,不是同一天重置
			if (DateUtil.isSameDay(DateUtil.getYmdDate(), DateUtil.getYmdDate(instPlayerRecruit.getLastRecruitTime()), DateFormat.YMD)) {
				//当天次数-1
				instPlayerRecruit.setRemainRecruitTimes(instPlayerRecruit.getRemainRecruitTimes() - 1 );
			} else {
				//总次数-1
				instPlayerRecruit.setRemainRecruitTimes(ConvertUtil.toInt(DictMap.dictSysConfigMap.get(StaticSysConfig.silverRecruitFreeNum + "").getValue()) - 1 );
			}
			instPlayerRecruit.setLastRecruitTime(currTime);
			instPlayerRecruit.setLastFreeRecruitTime(currTime);
			instPlayerRecruit.setRecruitSumTimes(instPlayerRecruit.getRecruitSumTimes() + 1);
			
			//修改下次免费招募时间
			long nextFreeMill = currMill + DictMap.dictRecruitTypeMap.get(StaticRecruitType.silverRecruit + "").getCoolTime() * 60 * 1000;
			instPlayerRecruit.setNextFreeRecruitTime(DateUtil.getTimeByMill(nextFreeMill));
			getInstPlayerRecruitDAL().update(instPlayerRecruit, instPlayerId);
		}
		
		//记录招募日志
		try {
			String logContent = PlayerMapUtil.getPlayerByPlayerId(instPlayerId).getLogHeader() + "|" + StaticRecruitType.silverRecruit + "|" + DictMap.dictRecruitTypeMap.get(StaticRecruitType.silverRecruit + "").getName() + "|" + 
		                        DateUtil.getCurrTime() + "|" + 0 + "|" + 0 + "|" + cardId + "|" + DictMap.dictCardMap.get(cardId + "").getName(); 
			LogicLogUtil.info("recruit", logContent.replace("null", ""));
		} catch (Exception e) {
			LogUtil.error("招募日志错误", e);
		}
		
		//推送招募日志
		final int pId = instPlayerId;
		try {
			DictCard dictCard = DictMap.dictCardMap.get(cardId + "");
			if (dictCard.getQualityId() == StaticQuality.blue || dictCard.getQualityId() == StaticQuality.purple) {
				final String name = dictCard.getName();
				ThreadPoolUtils.executePushData( new ThreadOper() {
					@Override
					public void innerRun() {
						try {
							String pushDataCenterUrl = SysConfigUtil.getValue("push.datacenter.url");
							
							//组织参数
							Player player = PlayerMapUtil.getPlayerByPlayerId(pId);
							Map<String, String> paramMap = player.getCommonPushMap();
							paramMap.put("eventTag", "抽卡");
							paramMap.put("value1string", "一次抽卡");
							paramMap.put("value2string", name);
							String params = HttpClientUtil.httpBuildQuery(paramMap);
							
							//推送数据
							HttpClientUtil.postMapSubmit(pushDataCenterUrl, params);
							
						} catch (Exception e) {
							e.printStackTrace();
							LogUtil.error("推送招募日志Error", e);
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("推送招募日志Error", e);
		}
		
		//记录招募日志-玩家Id[instPlayerId]#招募类型Id[recruitTypeId]#招募时间[recruitTime]#招募费用[recruitFee]#招募的卡牌Id[recruitCardId]
//		String content = instPlayerId + "#" + StaticRecruitType.silverRecruit + "#" + currTime + "#" + 0 + "#" + cardId;
//		LogicLogUtil.info("recruit", content);
	}
	
	/**
	 * 根据等级获取vip对象
	 * @author mp
	 * @date 2014-8-14 下午3:23:01
	 * @param vipLevel
	 * @return
	 * @Description
	 */
	public static DictVIP getVipByLevel (int vipLevel) {
		List<DictVIP> vipList = DictList.dictVIPList;
		DictVIP vip = null;
		for (DictVIP obj : vipList) {
			if (obj.getLevel() == vipLevel) {
				vip = obj;
				break;
			}
		}
		return vip;
	}
	
	/**
	 * 验证进阶条件
	 * @author mp
	 * @date 2014-9-2 上午10:49:14
	 * @param advanceCond
	 * @return
	 * @Description
	 */
	public static String valiAdvanceCond (String advanceCond, InstPlayerCard instPlayerCard) {
		
		/** 进阶所有条件类型
		 *  1_此卡牌等级
			2_此卡牌的第几个命宫是否开启（还需判断丹药是否满足）
			3_品质Id_星级数_卡牌字典Id_张数,  需要扣除     实际填写为：3_品质Id_星级数_张数
			4_详细称号字典表的Id
			5_品质Id_材料个数，  需要扣除, 后修改为[2014-11-04]：5_物品材料Id_材料个数
		 	6_异火ID
		*/
		
		String retMsg = "";
		int cardId = instPlayerCard.getCardId();
		int instPlayerId = instPlayerCard.getInstPlayerId();
		int instCardId = instPlayerCard.getId();
		
		String[] conds = advanceCond.split(";");
		for (String cond : conds) {
			int type = ConvertUtil.toInt(cond.split("_")[0]);
			if (type == 1) {
				int cardLevel = ConvertUtil.toInt(cond.split("_")[1]);
				if (instPlayerCard.getLevel() < cardLevel) {
					return StaticCnServer.fail_notCardLevel;
				}
			} else if (type == 2) {
				int pos = ConvertUtil.toInt(cond.split("_")[1]);
				DictCard card = DictMap.dictCardMap.get(cardId + "");
				String constells = card.getConstells();
				int constellId = 0;
				if (constells.split(";").length >= pos) {
					constellId = ConvertUtil.toInt(constells.split(";")[(pos-1)]);
				}
				List<InstPlayerConstell> instPlayerConstellList = getInstPlayerConstellDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instCardId + " and constellId = " + constellId, instPlayerId);
				if (instPlayerConstellList.size() <= 0) {
					return StaticCnServer.fail_constellNotOpen;
				} else {
					InstPlayerConstell instPlayerConstell = instPlayerConstellList.get(0);
					if (instPlayerConstell.getPills().contains("0")) {
						return StaticCnServer.fail_constellNotOpen;
					}
				}
			} else if (type == 3) {
				int qualityId = ConvertUtil.toInt(cond.split("_")[1]);
				int starLevelId = ConvertUtil.toInt(cond.split("_")[2]);
				int needCardId = instPlayerCard.getCardId();
				int num = ConvertUtil.toInt(cond.split("_")[3]);
				List<InstPlayerCard> instPlayerCardList = getInstPlayerCardDAL().getList("instPlayerId = " + instPlayerId + " and cardId = " + needCardId + " and qualityId = " + qualityId + " and starLevelId = " + starLevelId + " and inTeam = 0 and id != " + instCardId, instPlayerId);
				if (instPlayerCardList.size() < num) {
					return StaticCnServer.fail_cardNumNotEnough;
				}
			} else if (type == 4) {
				int detailTitileId = ConvertUtil.toInt(cond.split("_")[1]);
				if (instPlayerCard.getTitleDetailId() < detailTitileId) {
					return StaticCnServer.fail_titleLevelNotEnough;
				}
			} else if (type == 5) {
				//暂不处理
//				5_品质Id_材料个数，  需要扣除
				int materialId = ConvertUtil.toInt(cond.split("_")[1]);
				int num = ConvertUtil.toInt(cond.split("_")[2]);
				List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + materialId, instPlayerId);
				if (instPlayerThingList.size() <= 0) {
					return StaticCnServer.fail_materialNum;
				} else {
					if (instPlayerThingList.get(0).getNum() < num) {
						return StaticCnServer.fail_materialNum;
					}
				}
			} else if(type == 6){
				//6_异火ID
				int fireId = ConvertUtil.toInt(cond.split("_")[1]);
				List<InstPlayerYFire> instPlayerYFireList = getInstPlayerYFireDAL().getList(" instPlayerId = " + instPlayerId + " and fireId = " + fireId, instPlayerId);
				if(instPlayerYFireList.size() <= 0){
					return "异火不存在";
				}
				if(instPlayerYFireList.get(0).getState() == 0){
					return "异火未激活";
				}

			}
		}
		
		return retMsg;
	}


	/**
	 * 检查境界条件
	 *  进阶所有条件类型
	 *
	 *  1-->境界丹(1_境界丹数量)
	 *  2-->超级境界丹(2_超级境界丹数量)
	 *  3-->威望(3_威望数量)
	 *  4-->异火火种(4_火种ID_火种数量)
	 *  5-->品阶(5_品质ID_星级ID)
	 *
	 * @author cui
	 * @date	15/11/04
	 * @param condition
	 * @param instPlayerCard
	 */
	public static String valiRealmCond(String condition, InstPlayerCard instPlayerCard){

		String retMsg = "";
		int instPlayerId = instPlayerCard.getInstPlayerId();

		String[] costArray = condition.split(";");
		for (String line : costArray){
			String[] element = line.split("_");
			int type = ConvertUtil.toInt(element[0]);
			List<InstPlayerThing> lindenList;
			switch (type){
				default:
					return "";
				case 1://境界丹
					int useTalentValue = ConvertUtil.toInt(element[1]);//消耗的境界丹
					lindenList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.realmPill, instPlayerId);
					if (lindenList.size() <= 0 || lindenList.get(0).getNum() < useTalentValue) {
						return "境界丹数量不足";
					}
					break;
				case 2://超级境界丹
					int useSuperValue = ConvertUtil.toInt(element[1]);//消耗的超级境界丹
					lindenList = getInstPlayerThingDAL().getList("instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.thing300, instPlayerId);
					if(lindenList.size() <= 0 || lindenList.get(0).getNum() < useSuperValue){
						return "超级境界丹数量不足";
					}
					break;
				case 3://威望
					int weiValue = ConvertUtil.toInt(element[1]);//消耗威望数量
					List<InstPlayerArena> instPlayerArenaList = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
					if (instPlayerArenaList.size() > 0) {
						InstPlayerArena instPlayerArena = instPlayerArenaList.get(0);
						if(instPlayerArena.getPrestige() < weiValue){
							return "";
						}

//						instPlayerArena.setPrestige(instPlayerArena.getPrestige() + value);
//						getInstPlayerArenaDAL().update(instPlayerArena, instPlayerId);
//						OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerArena, instPlayerArena.getId(), instPlayerArena.getResult(), syncMsgData);
					}
					break;
				case 4://异火火种
					int fireId = ConvertUtil.toInt(element[1]);
					int count = ConvertUtil.toInt(element[2]);
					List<InstPlayerYFire> instPlayerYFireList = getInstPlayerYFireDAL().getList(" instPlayerId = " + instPlayerId + " and fireId = " + fireId, instPlayerId);
					if(instPlayerYFireList.size() <= 0){
						return "火种数量不足";
					}
					if(instPlayerYFireList.get(0).getChipCount() < count){
						return "火种数量不足";
					}
					break;
				case 5://卡牌品阶
					int quality = ConvertUtil.toInt(element[1]);   //品阶
					int star = ConvertUtil.toInt(element[2]);       //星数
					if (instPlayerCard.getQualityId() < quality) {
						return "卡牌品阶不足";
					}else if(instPlayerCard.getQualityId() == quality){
						if(instPlayerCard.getStarLevelId() < star){
							return "卡牌星级不足";
						}
					}
					break;

			}
		}

		return retMsg;
	}
	
	/**
	 * 删除卡牌以及卡牌自身其他实例数据
	 * @author hzw
	 * @date 2014-9-2下午3:34:56
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void deletePlayerCard(int instPlayerId, InstPlayerCard instPlayerCard, MessageData syncMsgData) throws Exception{
		
		//删除卡牌修炼实例数据
		List<InstPlayerTrainTemp>  instPlayerTrainTempList = getInstPlayerTrainTempDAL().getList("instPlayerCardId = " + instPlayerCard.getId(), instPlayerId);
		for(InstPlayerTrainTemp instPlayerTrainTemp : instPlayerTrainTempList){
			getInstPlayerTrainTempDAL().deleteById(instPlayerTrainTemp.getId(), 0);
		}
		List<InstPlayerTrain> instPlayerTrains = getInstPlayerTrainDAL().getList(" instPlayerCardId = " + instPlayerCard.getId() , instPlayerId);
		for(InstPlayerTrain obj : instPlayerTrains){
			getInstPlayerTrainDAL().deleteById(obj.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
		}
		
		//删除卡牌命宫实例数据
		List<InstPlayerConstell> instPlayerConstells = getInstPlayerConstellDAL().getList(" instCardId = " + instPlayerCard.getId() , instPlayerId);
		for(InstPlayerConstell obj : instPlayerConstells){
			getInstPlayerConstellDAL().deleteById(obj.getId(), instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, obj, obj.getId(), "", syncMsgData);
		}
		
		//功法
		InstPlayerKungFu instPlayerKungFu = getInstPlayerKungFuDAL().getModel(instPlayerCard.getInstPlayerKungFuId(), instPlayerId);
		if(instPlayerKungFu != null){
			instPlayerKungFu.setInstPlayerCardId(0);
			getInstPlayerKungFuDAL().update(instPlayerKungFu, instPlayerId);
			OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerKungFu, instPlayerKungFu.getId(), instPlayerKungFu.getResult(), syncMsgData);
		}
		
		getInstPlayerCardDAL().deleteById(instPlayerCard.getId(), instPlayerId);
		OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.delete, instPlayerCard, instPlayerCard.getId(), "", syncMsgData);
		
	}

//	/**
//	 *	错误的方法废弃
//	 */
//	public static Map<String, Integer> decompostCard(List<DictAdvance> advanceList, List<DictAdvance> sourceList, int midLevel) throws Exception {
//		Map<String, Integer> data = new HashMap<String, Integer>();
//		if (advanceList == null || advanceList.size() < 1 || sourceList.size() < 0) {
//			data.put("card", 0);
//			data.put("exp", 0);
//			return data;
//		}
//		int cardNum = 0;
//		int expSum = 0;
//		int stoneSum = 0;
//		List<DictAdvance> newSourceList = new ArrayList<DictAdvance>();
//		DictAdvance da = null;
//		for (int si = 0; si < sourceList.size(); si++) {
//			da = sourceList.get(si);
//			int index = advanceList.indexOf(da);
//			if (index == 0) {
//				cardNum++;
//			} else {
//				newSourceList.add(advanceList.get(index - 1));
//				String[] conds = da.getConds().split(";");
//				for (String cond : conds) {
//					int[] array = StringUtil.string2IntArray(cond, '_');
//					if (array.length > 0) {
//						switch (array[0]) {// 1_等级;3_品阶_星级_数量;5_物品ID_数量
//							case 1: {// 等级
//								break;
//							}
//							case 3: {// 消耗卡片
//								if (array.length > 3 && array[3] > 0) {
//									DictAdvance newSource = null;
//									DictAdvance dictAdvance = null;
//									int exp = 0;
//									int stone=0;
//									for (int ai = 0; ai < advanceList.size(); ai++) {
//										dictAdvance = advanceList.get(ai);
//										if (dictAdvance.getQualityId() == array[1] && dictAdvance.getStarLevelId() == array[2]) {
//											newSource = dictAdvance;
//											if (array[1] > 3 && midLevel > 1) {
//												exp = FormulaUtil.restoreCardExp(midLevel, 0);
//											}
//											stone= CardUtil.getAdvanceStone(advanceList, newSource.getQualityId(),newSource.getStarLevelId());
//
//
//											break;
//										}
//									}
//									if (newSource != null) {
//										for (int c = 0; c < array[3]; c++) {
//											newSourceList.add(newSource);
//										}
//										expSum+=exp * array[3];
//										stoneSum+=stone * array[3];
//									}
//								}
//								break;
//							}
//							case 5: {// 消耗物品
//								break;
//							}
//						}
//					}
//				}
//			}
//		}
//		if (newSourceList.size() > 0) {
//			Map<String, Integer> newData = decompostCard(advanceList, newSourceList, midLevel);
//			Integer cardCount = newData.get("card");
//			if (cardCount != null) {
//				cardNum += cardCount;
//			}
//			Integer expCount = newData.get("exp");
//			if (expCount != null) {
//				expSum+=expCount;
//			}
//			Integer stoneCount = newData.get("stone");
//			if (stoneCount != null) {
//				stoneSum+=stoneCount;
//			}
//			data.put("card", cardNum);
//			data.put("exp", expSum);
//			data.put("stone", stoneSum);
//			return data;
//		} else {
//			data.put("card", cardNum);
//			data.put("exp", expSum);
//			data.put("stone", stoneSum);
//			return data;
//		}
//	}
//
//	/**
//	 * 错误的方法，废弃
//	 */
//	public static int getAdvanceStone(List<DictAdvance> advanceList,int qualityId,int starLevelId){
//		int count = 0;
//		if(advanceList != null && advanceList.size() > 0){
//			for (DictAdvance advance : advanceList) {
//				if (advance.getQualityId() < qualityId || (advance.getNextQualityId() == qualityId && advance.getNextStarLevelId() <= starLevelId)) {
//					for (String cond : advance.getConds().split(";")) {
//						int type = ConvertUtil.toInt(cond.split("_")[0]);
//						if (type == 5) {
//							//5_物品材料Id_材料个数
//							count+= ConvertUtil.toInt(cond.split("_")[2]);
//						}
//					}
//				}
//			}
//		}
//		return count;
//	}


	/**
	 * 轮回递归计算，之前版本有问题，重新计算
	 * @author cui
	 * @date	2015/12/04
	 * @param thingMap
	 * @param advanceList
	 * @param sourceList
	 * @param midLevel
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Integer>  decompostCard(Map<String, Integer> thingMap, List<DictAdvance> advanceList, List<DictAdvance> sourceList, int midLevel) throws Exception {
		Map<String, Integer> data = new HashMap<String, Integer>();
		if (advanceList == null || advanceList.size() < 1 || sourceList.size() < 0) {
			data.put("card", 0);
			data.put("exp", 0);
			return data;
		}
		int cardNum = 0;
		int expSum = 0;
		List<DictAdvance> newSourceList = new ArrayList<DictAdvance>();
		DictAdvance da = null;
		for (int si = 0; si < sourceList.size(); si++) {
			da = sourceList.get(si);
			int index = advanceList.indexOf(da);
			if (index == 0) {
				cardNum++;
			} else {
				newSourceList.add(advanceList.get(index - 1));
				String[] conds = da.getConds().split(";");
				for (String cond : conds) {
					int[] array = StringUtil.string2IntArray(cond, '_');
					if (array.length > 0) {
						switch (array[0]) {// 1_等级;3_品阶_星级_数量;5_物品ID_数量
							case 1: {// 等级
								break;
							}
							case 3: {// 消耗卡片
								if (array.length > 3 && array[3] > 0) {
									DictAdvance newSource = null;
									DictAdvance dictAdvance = null;
									int exp = 0;
									for (int ai = 0; ai < advanceList.size(); ai++) {
										dictAdvance = advanceList.get(ai);
										if (dictAdvance.getQualityId() == array[1] && dictAdvance.getStarLevelId() == array[2]) {
											newSource = dictAdvance;
											if (array[1] > 3 && midLevel > 1) {
												exp = FormulaUtil.restoreCardExp(midLevel, 0);
											}
											CardUtil.calAdvanceThing(advanceList, newSource.getQualityId(), newSource.getStarLevelId(),thingMap, array[3]);
											break;
										}
									}
									if (newSource != null) {
										for (int c = 0; c < array[3]; c++) {
											newSourceList.add(newSource);
										}
										expSum += exp * array[3];
//										System.out.println("CardUtil.decompostCard 消耗的卡牌数量："+array[3]);
									}
								}
								break;
							}
							case 5: {// 消耗物品
								break;
							}
						}
					}
				}
			}
		}
		if (newSourceList.size() > 0) {
			Map<String, Integer> newdata = CardUtil.decompostCard(thingMap, advanceList, newSourceList, midLevel);
			if (newdata.containsKey("card")) {
				newdata.put("card", newdata.get("card") + cardNum);
			} else {
				newdata.put("card", cardNum);
			}
			if (newdata.containsKey("exp")) {
				newdata.put("exp", newdata.get("exp") + expSum);
			} else {
				newdata.put("exp", expSum);
			}
			return newdata;
		} else {
			data.put("card", cardNum);
			data.put("exp", expSum);
			return data;
		}
	}

	/**
	 * 计算 一条Advance上的物品消耗
	 * @author	cui
	 * @date	2015/12/04
	 * @param advanceList
	 * @param qualityId
	 * @param starLevelId
	 * @param thingMap		放到一个容器里记入
	 * @param mutiple   	消耗的倍数
	 */
	public static void calAdvanceThing(List<DictAdvance> advanceList, int qualityId, int starLevelId, Map<String, Integer> thingMap, int mutiple) {
		if (advanceList != null && advanceList.size() > 0) {
			for (DictAdvance advance : advanceList) {
				if (advance.getQualityId() < qualityId || (advance.getNextQualityId() == qualityId && advance.getNextStarLevelId() <= starLevelId)) {
					for (String cond : advance.getConds().split(";")) {
						int type = ConvertUtil.toInt(cond.split("_")[0]);
						if (type == 5) {
							//5_物品材料Id_材料个数
							int tableTypeId = StaticTableType.DictThing;
							int tableFieldId = ConvertUtil.toInt(cond.split("_")[1]);
							int num = ConvertUtil.toInt(cond.split("_")[2]);
							if (thingMap.containsKey(tableTypeId + "_" + tableFieldId)) {
								thingMap.put(tableTypeId + "_" + tableFieldId, thingMap.get(tableTypeId + "_" + tableFieldId) + num * mutiple);
							} else {
								thingMap.put(tableTypeId + "_" + tableFieldId, num * mutiple);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 轮回预览
	 * @author hzw
	 * @date 2014-10-1上午10:08:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static void orgRestoreCardView(InstPlayerCard instPlayerCard, Map<String, Integer> thingMap) throws Exception{
		int instPlayerId = instPlayerCard.getInstPlayerId();
		
		//称号(由之前的返还潜力值改为返还境界丹)
		int realmPill = 0;//需要返还的境界丹
		int superRealmPill = 0;//需要返还的超级境界丹
		List<DictTitleDetail> dictTitleDetailList = (List<DictTitleDetail>)DictList.dictTitleDetailList;
		for(DictTitleDetail obj : dictTitleDetailList){
			if(obj.getId() < instPlayerCard.getTitleDetailId()){
				String objCost = obj.getCost();
				if(objCost != null && !objCost.equals("")){
					String[] goodsList = objCost.split(";");
					for (String goods : goodsList){
						String[] pill = goods.split("_");
						switch (pill[0]){
							case "1":
								realmPill += Integer.valueOf(pill[1]);
								break;
							case "2":
								superRealmPill += Integer.valueOf(pill[1]);
								break;
						}
					}
				}
			}
		}
		//普通的 境界丹
		if(realmPill > 0){
			if(thingMap.containsKey(StaticTableType.DictThing+"_"+StaticThing.realmPill)){
				thingMap.put(StaticTableType.DictThing+"_"+StaticThing.realmPill, thingMap.get(StaticTableType.DictThing+"_"+StaticThing.realmPill) + realmPill);
			}
			else{
				thingMap.put(StaticTableType.DictThing+"_"+StaticThing.realmPill, realmPill);
			}
		}

		//超级境界丹
		if(superRealmPill > 0){
			if(thingMap.containsKey(StaticTableType.DictThing+"_"+StaticThing.thing300)){
				thingMap.put(StaticTableType.DictThing+"_"+StaticThing.thing300, thingMap.get(StaticTableType.DictThing+"_"+StaticThing.thing300) + superRealmPill);
			}
			else{
				thingMap.put(StaticTableType.DictThing+"_"+StaticThing.thing300, superRealmPill);
			}
		}
		/*int potential = FormulaUtil.restoreCardPotential(instPlayerCard.getTitleDetailId(), instPlayerCard.getLevel(), instPlayerCard.getPotential()); //返还的潜力值
		pillList = FormulaUtil.calcPotentialPillBySumPotential(potential);
		if(!pillList.equals("")){
			for(String pill : pillList.split(";")){
				int tableTypeId = ConvertUtil.toInt(pill.split("_")[0]);
				int tableFieldId = ConvertUtil.toInt(pill.split("_")[1]);
				int num = ConvertUtil.toInt(pill.split("_")[2]);
				if(thingMap.containsKey(tableTypeId+"_"+tableFieldId)){
					thingMap.put(tableTypeId+"_"+tableFieldId, thingMap.get(tableTypeId+"_"+tableFieldId) + num);
				}
				else{
					thingMap.put(tableTypeId+"_"+tableFieldId, num);
				}
			}
		}*/
		/*List<DictTitleDetail> dictTitleDetailList = (List<DictTitleDetail>)DictList.dictTitleDetailList;
		for(DictTitleDetail obj : dictTitleDetailList){
			if(obj.getId() < instPlayerCard.getTitleDetailId()){
				if(obj != null && !obj.getConditions().equals("")){
					String conditions[] = obj.getConditions().split(";");
					for(String condition : conditions){
						if(ConvertUtil.toInt(condition.split("_")[0]) == StaticTableType.DictThing){
							int tableTypeId = ConvertUtil.toInt(condition.split("_")[0]);
							int tableFieldId = ConvertUtil.toInt(condition.split("_")[1]);
							int num = ConvertUtil.toInt(condition.split("_")[2]);
							if(thingMap.containsKey(tableTypeId+"_"+tableFieldId)){
								thingMap.put(tableTypeId+"_"+tableFieldId, thingMap.get(tableTypeId+"_"+tableFieldId) + num);
							}
							else{
								thingMap.put(tableTypeId+"_"+tableFieldId, num);
							}
						}
					}
				}
			}
		}*/
		
	    //修炼
		int trainNum = instPlayerCard.getTrainNum();
		if(trainNum > 0){
			if(thingMap.containsKey(StaticTableType.DictThing+"_"+StaticThing.linden)){
				thingMap.put(StaticTableType.DictThing+"_"+StaticThing.linden, thingMap.get(StaticTableType.DictThing+"_"+StaticThing.linden) + trainNum * DictMapUtil.getSysConfigIntValue(StaticSysConfig.trainLinden));
			}
			else{
				thingMap.put(StaticTableType.DictThing+"_"+StaticThing.linden, trainNum * DictMapUtil.getSysConfigIntValue(StaticSysConfig.trainLinden));
			}
		}
		
		//卡边/品质
		List<DictAdvance> advanceList = (List<DictAdvance>)DictMapList.dictAdvanceMap.get(instPlayerCard.getCardId());
		if(advanceList != null && advanceList.size() > 0){
			for (DictAdvance advance : advanceList) {
				if (advance.getQualityId() < instPlayerCard.getQualityId() || (advance.getNextQualityId() == instPlayerCard.getQualityId() && advance.getNextStarLevelId() <= instPlayerCard.getStarLevelId())) {
					for (String cond : advance.getConds().split(";")) {
						int type = ConvertUtil.toInt(cond.split("_")[0]);
						if (type == 5) {
							//5_物品材料Id_材料个数
							int tableTypeId = StaticTableType.DictThing;
							int tableFieldId = ConvertUtil.toInt(cond.split("_")[1]);
							int num = ConvertUtil.toInt(cond.split("_")[2]);
							if(thingMap.containsKey(tableTypeId+"_"+tableFieldId)){
								thingMap.put(tableTypeId+"_"+tableFieldId, thingMap.get(tableTypeId+"_"+tableFieldId) + num);
							}
							else{
								thingMap.put(tableTypeId+"_"+tableFieldId, num);
							}
						}
					}
				}
			}
		}
		//命宫
		if(instPlayerCard.getInstPlayerConstells() != null && !instPlayerCard.getInstPlayerConstells().equals("")){
			if(instPlayerCard.getInstPlayerConstells().contains("_")){
				/*//说明是处理吃掉的卡牌
				if(thingMap.containsKey(StaticTableType.DictCard+"_"+instPlayerCard.getCardId())){
					thingMap.put(StaticTableType.DictCard+"_"+instPlayerCard.getCardId(), thingMap.get(StaticTableType.DictCard+"_"+instPlayerCard.getCardId()) + 1);
				}
				else{
					thingMap.put(StaticTableType.DictCard+"_"+instPlayerCard.getCardId(), 1);
				}*/
				
				String instPlayerConstells[] = instPlayerCard.getInstPlayerConstells().split("_");
				DictCard dictCard = DictMap.dictCardMap.get(instPlayerCard.getCardId() + "");
				String constells[] = dictCard.getConstells().split(";");
				int size = ConvertUtil.toInt(instPlayerConstells[0]) - 1;
				for(int i = 0; i < size; i++){
					DictConstell dictConstell = DictMap.dictConstellMap.get(constells[i] + "");
					String pills[] = dictConstell.getPills().split(";");
					for(int j = 0; j < pills.length; j++){
						int tableTypeId = StaticTableType.DictPill;
						int tableFieldId = ConvertUtil.toInt(pills[j]);
						if(thingMap.containsKey(tableTypeId+"_"+tableFieldId)){
							thingMap.put(tableTypeId+"_"+tableFieldId, thingMap.get(tableTypeId+"_"+tableFieldId) + 1);
						}
						else{
							thingMap.put(tableTypeId+"_"+tableFieldId, 1);
						}
					}
				}
			}else{
				String instPlayerConstells[] = instPlayerCard.getInstPlayerConstells().split(";");
				for(String instPlayerConstellId : instPlayerConstells){
					InstPlayerConstell instPlayerConstell = getInstPlayerConstellDAL().getModel(ConvertUtil.toInt(instPlayerConstellId), instPlayerId);
					if(instPlayerConstell.getPills().contains("1")){
						DictConstell dictConstell = DictMap.dictConstellMap.get(instPlayerConstell.getConstellId() + "");
						String pills[] = instPlayerConstell.getPills().split(";");
						for(int i = 0; i < pills.length; i++){
							if(ConvertUtil.toInt(pills[i]) == 1){
								int tableTypeId = StaticTableType.DictPill;
								int tableFieldId = ConvertUtil.toInt(dictConstell.getPills().split(";")[i]);
								if(thingMap.containsKey(tableTypeId+"_"+tableFieldId)){
									thingMap.put(tableTypeId+"_"+tableFieldId, thingMap.get(tableTypeId+"_"+tableFieldId) + 1);
								}
								else{
									thingMap.put(tableTypeId+"_"+tableFieldId, 1);
								}
							}
						}
					}
				}
			}
		}
		
	}	
	
	/**
	 * 收集玩家得到过的卡牌不重复
	 * @author hzw
	 * @date 2015-3-30上午11:17:02
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void addPlayerCards(int instPlayerId, int cardId) throws Exception{
		List<InstPlayerCards> instPlayerCardsList = getInstPlayerCardsDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if(instPlayerCardsList == null || instPlayerCardsList.size() <= 0){
			InstPlayerCards instPlayerCards = new InstPlayerCards();
			instPlayerCards.setInstPlayerId(instPlayerId);
			instPlayerCards.setCardIds(cardId + "");
			getInstPlayerCardsDAL().add(instPlayerCards, instPlayerId);
		}else{
			InstPlayerCards instPlayerCards = instPlayerCardsList.get(0);
			String cardIds = instPlayerCards.getCardIds();
			if(!StringUtil.contains(cardIds, cardId + "", ";")){
				instPlayerCards.setCardIds(cardIds + ";" + cardId);
				getInstPlayerCardsDAL().update(instPlayerCards, instPlayerId);
			}
		}
	}	

	/**
	 * 根据战斗属性值获取兑换比例
	 * @author mp
	 * @date 2015-9-8 下午2:02:45
	 * @param fightPId
	 * @return
	 * @Description
	 */
	public static float getTrainBl (int fightPId) {
		float f = 0.0f;
		if (fightPId == StaticFightProp.blood) {
			f = DictMapUtil.getSysConfigFloatValue(StaticSysConfig.bloodFactor);
		} else if (fightPId == StaticFightProp.wAttack) {
			f = DictMapUtil.getSysConfigFloatValue(StaticSysConfig.wAttackFactor);
		} else if (fightPId == StaticFightProp.wDefense) {
			f = DictMapUtil.getSysConfigFloatValue(StaticSysConfig.wDefenseFactor);
		} else if (fightPId == StaticFightProp.fAttack) {
			f = DictMapUtil.getSysConfigFloatValue(StaticSysConfig.fAttackFactor);
		} else if (fightPId == StaticFightProp.fDefense) {
			f = DictMapUtil.getSysConfigFloatValue(StaticSysConfig.fDefenseFactor);
		}
		return f;
	}
}
