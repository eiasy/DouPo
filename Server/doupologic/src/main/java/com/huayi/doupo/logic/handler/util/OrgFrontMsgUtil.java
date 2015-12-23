package com.huayi.doupo.logic.handler.util;

import java.util.List;

import com.google.common.collect.Lists;
import com.huayi.doupo.base.dal.base.DALFather;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.*;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.memcalc.CardPropObj;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.DateUtil.DateFormat;
import com.huayi.doupo.logic.util.MessageData;

/**
 * 组织前端数据工具类
 * @author hzw
 * @date 2014-06-11 下午3:29:09
 */
/**
 * 
 * @author hzw
 * @date 2014-9-2下午3:28:27
 */
public class OrgFrontMsgUtil extends DALFactory{

	/**
	 * 组织玩家实例
	 * @author mp
	 * @date 2013-10-11 下午3:31:19
	 * @param retMsgData
	 * @param instPlayer
	 * @Description
	 */
	public static MessageData orgInstPlayer(InstPlayer instPlayer){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayer.getId());
		msgData.putStringItem("2", instPlayer.getOpenId());
		msgData.putStringItem("3", instPlayer.getName());
		msgData.putIntItem("4", instPlayer.getLevel());
		msgData.putIntItem("5", instPlayer.getGold());
		msgData.putStringItem("6", instPlayer.getCopper());
		msgData.putIntItem("7", instPlayer.getExp());
		msgData.putIntItem("8", instPlayer.getEnergy());
		msgData.putIntItem("9", instPlayer.getMaxEnergy());
		msgData.putIntItem("10", instPlayer.getVigor());
		msgData.putIntItem("11", instPlayer.getMaxVigor());
		msgData.putIntItem("12", instPlayer.getCardNum());
		msgData.putIntItem("13", instPlayer.getMaxCardNum());
		msgData.putStringItem("14", instPlayer.getGuidStep());
		msgData.putIntItem("15", instPlayer.getChapterId());
		msgData.putIntItem("16", instPlayer.getBarrierId());
		msgData.putIntItem("17", instPlayer.getFireGainRuleId());
		msgData.putIntItem("18", instPlayer.getInstPlayerFireId());
		msgData.putIntItem("19", instPlayer.getVipLevel());
		msgData.putIntItem("20", instPlayer.getVigour());
		msgData.putIntItem("21", instPlayer.getCulture());
		msgData.putStringItem("22", instPlayer.getLastEnergyRecoverTime());
		msgData.putStringItem("23", instPlayer.getLastVigorRecoverTime());
		msgData.putIntItem("24", instPlayer.getBarrierNum());
		msgData.putIntItem("25", instPlayer.getBuyEnergyNum());
		msgData.putIntItem("26", instPlayer.getBuyVigorNum());
		msgData.putStringItem("27", instPlayer.getLastBuyEnergyTime());
		msgData.putStringItem("28", instPlayer.getLastBuyVigorTime());
		msgData.putStringItem("29", instPlayer.getGuipedBarrier());
		msgData.putStringItem("30", instPlayer.getWashTime());
		msgData.putIntItem("32", instPlayer.getHeaderCardId());
		msgData.putStringItem("33", instPlayer.getVipIds());
		msgData.putIntItem("34", instPlayer.getPullBlack());
		msgData.putIntItem("35", instPlayer.getIsGetFirstRechargeGift());
		msgData.putStringItem("36", instPlayer.getBeautyCardTime());
		return msgData;
	}
	
	/**
	  * 组织玩家卡牌实例数据
	  * @author hzw
	  * @date 2014-6-19下午4:58:56
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	public static void orgInstPlayerCard(MessageData cardMsgData, List<InstPlayerCard> cardList){
		for(InstPlayerCard instPlayerCard : cardList){
			MessageData msgData = orgInstPlayerCard(instPlayerCard);
			cardMsgData.putMessageItem(instPlayerCard.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	  *  组织玩家卡牌实例数据
	  * @author hzw
	  * @date 2014-6-19下午4:58:53
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	public static MessageData orgInstPlayerCard(InstPlayerCard instPlayerCard){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerCard.getId());
		msgData.putIntItem("2", instPlayerCard.getInstPlayerId());
		msgData.putIntItem("3", instPlayerCard.getCardId());
		msgData.putIntItem("4", instPlayerCard.getQualityId());
		msgData.putIntItem("5", instPlayerCard.getStarLevelId());
		msgData.putIntItem("6", instPlayerCard.getTitleDetailId());
		msgData.putIntItem("7", instPlayerCard.getSex());
		msgData.putIntItem("8", instPlayerCard.getExp());
		msgData.putIntItem("9", instPlayerCard.getLevel());
		msgData.putIntItem("10", instPlayerCard.getInTeam());
		msgData.putIntItem("11", instPlayerCard.getUseTalentValue());
		msgData.putIntItem("12", instPlayerCard.getInstPlayerKungFuId());
		msgData.putStringItem("13", instPlayerCard.getInstPlayerConstells());
		msgData.putIntItem("14", instPlayerCard.getPotential());
		msgData.putIntItem("15", instPlayerCard.getIsLock());

		return msgData;
	}
	
	/**
	 * 组织小伙伴数据
	 * @author mp
	 * @date 2014-9-13 下午4:27:52
	 * @param partnerMsgData
	 * @param partnerList
	 * @Description
	 */
	public static void orgInstPlayerPartner(MessageData partnerMsgData, List<InstPlayerPartner> partnerList){
		for(InstPlayerPartner instPlayerPartner : partnerList){
			MessageData msgData = orgInstPlayerPartner(instPlayerPartner);
			partnerMsgData.putMessageItem(instPlayerPartner.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织小伙伴数据
	 * @author mp
	 * @date 2014-9-13 下午4:27:11
	 * @param instPlayerPartner
	 * @return
	 * @Description
	 */
	public static MessageData orgInstPlayerPartner (InstPlayerPartner instPlayerPartner) {
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerPartner.getId());
		msgData.putIntItem("2", instPlayerPartner.getInstPlayerId());
		msgData.putIntItem("3", instPlayerPartner.getInstCardId());
		msgData.putIntItem("4", instPlayerPartner.getCardId());
		msgData.putIntItem("5", instPlayerPartner.getPosition());
		return msgData;
	}
	
	/**
	 * 组织玩家卡牌阵型实例数据
	 * @author hzw
	 * @date 2014-7-2下午4:49:00
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerFormation(MessageData formationMsgData, List<InstPlayerFormation> formationList){
		for(InstPlayerFormation instPlayerFormation : formationList){
			MessageData msgData = orgInstPlayerFormation(instPlayerFormation);
			formationMsgData.putMessageItem(instPlayerFormation.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家占星数据
	 * @author mp
	 * @date 2015-12-4 上午11:17:39
	 * @param formationMsgData
	 * @param formationList
	 * @Description
	 */
	public static void orgInstPlayerHoldStar (MessageData holdStarMsgData, List<InstPlayerHoldStar> holdStarList){
		for(InstPlayerHoldStar instPlayerHoldStar : holdStarList){
			MessageData msgData = orgInstPlayerHoldStar(instPlayerHoldStar);
			holdStarMsgData.putMessageItem(instPlayerHoldStar.getId() + "", msgData.getMsgData());
		}
	}
	
	/**
	  *  组织玩家卡牌阵型实例数据
	  * @author hzw
	  * @date 2014-6-19下午4:58:53
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	public static MessageData orgInstPlayerFormation(InstPlayerFormation instPlayerFormation){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerFormation.getId());
		msgData.putIntItem("2", instPlayerFormation.getInstPlayerId());
		msgData.putIntItem("3", instPlayerFormation.getInstCardId());
		msgData.putIntItem("4", instPlayerFormation.getType());
		msgData.putIntItem("5", instPlayerFormation.getPosition());
		msgData.putIntItem("6", instPlayerFormation.getCardId());
		return msgData;
	}
	
	/**
	 * 组织玩家占星数据
	 * @author mp
	 * @date 2015-12-4 上午11:22:26
	 * @param instPlayerFormation
	 * @return
	 * @Description
	 */
	public static MessageData orgInstPlayerHoldStar (InstPlayerHoldStar instPlayerHoldStar){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerHoldStar.getId());
		msgData.putIntItem("2", instPlayerHoldStar.getInstPlayerId());
		msgData.putIntItem("3", instPlayerHoldStar.getHoldStarGradeId());
		msgData.putIntItem("4", instPlayerHoldStar.getStep());
		msgData.putIntItem("5", instPlayerHoldStar.getStarNum());
		msgData.putIntItem("6", instPlayerHoldStar.getHoldStarTimes());
		msgData.putStringItem("7", instPlayerHoldStar.getHoldStarTime());
		msgData.putIntItem("8", instPlayerHoldStar.getHoldStarFreeRefreshedTimes());
		msgData.putIntItem("9", instPlayerHoldStar.getHoldStarNoFreeRefreshedTimes());
		msgData.putStringItem("10", instPlayerHoldStar.getHoldStarRefreshedTime());
		msgData.putIntItem("11", instPlayerHoldStar.getRewardRefreshedTimes());
		msgData.putStringItem("12", instPlayerHoldStar.getRewardRefreshedTime());
		msgData.putStringItem("13", instPlayerHoldStar.getUpStars());
		msgData.putStringItem("14", instPlayerHoldStar.getDownStars());
		msgData.putStringItem("15", instPlayerHoldStar.getRewards());
		msgData.putStringItem("16", instPlayerHoldStar.getSysRefreshTime());
		return msgData;
	}
	
	/**
	 *  组织玩家装备实例数据
     * @author hzw
     * @date 2014-6-19下午4:58:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerEquip(MessageData equipMsgData, List<InstPlayerEquip> equipList){
		for(InstPlayerEquip instPlayerEquip : equipList){
			MessageData msgData = orgInstPlayerEquip(instPlayerEquip);
			equipMsgData.putMessageItem(instPlayerEquip.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织装备宝石
	 * @author mp
	 * @date 2014-8-11 下午2:41:51
	 * @param bagExpandMsgData
	 * @param bagExpandList
	 * @Description
	 */
	public static void orgInstEquipGem(MessageData equipGemMsgData, List<InstEquipGem> equipGemList){
		for(InstEquipGem instEquipGem : equipGemList){
			MessageData msgData = orgInstEquipGem(instEquipGem);
			equipGemMsgData.putMessageItem(instEquipGem.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	  *  组织玩家装备实例数据
	  * @author hzw
	  * @date 2014-6-19下午4:58:53
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	public static MessageData orgInstPlayerEquip(InstPlayerEquip instPlayerEquip){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerEquip.getId());
		msgData.putIntItem("2", instPlayerEquip.getInstPlayerId());
		msgData.putIntItem("3", instPlayerEquip.getEquipTypeId());
		msgData.putIntItem("4", instPlayerEquip.getEquipId());
		msgData.putIntItem("5", instPlayerEquip.getLevel());
		msgData.putIntItem("6", instPlayerEquip.getInstCardId());
		msgData.putIntItem("7", instPlayerEquip.getIsInlay());
		msgData.putIntItem("8", instPlayerEquip.getEquipAdvanceId());
		return msgData;
	}
	
	/**
	 * 组织玩家背包扩容数据
	 * @author mp
	 * @date 2014-8-5 下午3:49:21
	 * @param bagExpandMsgData
	 * @param bagExpandList
	 * @Description
	 */
	public static void orgInstPlayerBagExpand(MessageData bagExpandMsgData, List<InstPlayerBagExpand> bagExpandList){
		for(InstPlayerBagExpand instPlayerBagExpand : bagExpandList){
			MessageData msgData = orgInstPlayerBagExpand(instPlayerBagExpand);
			bagExpandMsgData.putMessageItem(instPlayerBagExpand.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家背包扩容数据
	 * @author mp
	 * @date 2014-8-5 下午3:48:18
	 * @param instPlayerBagExpand
	 * @return
	 * @Description
	 */
	public static MessageData orgInstPlayerBagExpand(InstPlayerBagExpand instPlayerBagExpand){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerBagExpand.getId());
		msgData.putIntItem("2", instPlayerBagExpand.getInstPlayerId());
		msgData.putIntItem("3", instPlayerBagExpand.getBagTypeId());
		msgData.putIntItem("4", instPlayerBagExpand.getExpandSumNum());
		msgData.putIntItem("5", instPlayerBagExpand.getExpandSumGold());
		msgData.putIntItem("6", instPlayerBagExpand.getExpandSumTimes());
		return msgData;
	}
	
	/**
	 * 组织装备宝石
	 * @author mp
	 * @date 2014-8-11 下午2:43:23
	 * @param instPlayerBagExpand
	 * @return
	 * @Description
	 */
	public static MessageData orgInstEquipGem(InstEquipGem instEquipGem){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instEquipGem.getId());
		msgData.putIntItem("2", instEquipGem.getInstPlayerId());
		msgData.putIntItem("3", instEquipGem.getInstPlayerEquipId());
		msgData.putIntItem("4", instEquipGem.getThingId());
		msgData.putIntItem("5", instEquipGem.getPosition());
		return msgData;
	}
	
	/**
     *  组织玩家卡牌阵阵容例数据
     * @author hzw
     * @date 2014-6-19下午4:58:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerLineup(MessageData equipMsgData, List<InstPlayerLineup> lineupList){
		for(InstPlayerLineup instPlayerLineup : lineupList){
			MessageData msgData = orgInstPlayerLineup(instPlayerLineup);
			equipMsgData.putMessageItem(instPlayerLineup.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	  *  组织玩家卡牌阵阵容例数据
	  * @author hzw
	  * @date 2014-6-19下午4:58:53
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	public static MessageData orgInstPlayerLineup(InstPlayerLineup instPlayerLineup){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerLineup.getId());
		msgData.putIntItem("2", instPlayerLineup.getInstPlayerId());
		msgData.putIntItem("3", instPlayerLineup.getInstPlayerFormationId());
		msgData.putIntItem("4", instPlayerLineup.getEquipTypeId());
		msgData.putIntItem("5", instPlayerLineup.getInstPlayerEquipId());

		return msgData;
	}
	
	/**
	 * 组织玩家洗练实例数据
	 * @author hzw
	 * @date 2014-7-9下午3:19:46
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerWash(MessageData washMsgData, List<InstPlayerWash> washList){
		for(InstPlayerWash instPlayerWash : washList){
			MessageData msgData = orgInstPlayerWash(instPlayerWash);
			washMsgData.putMessageItem(instPlayerWash.getId()+"", msgData.getMsgData());
		}
	}

	/**
	 * 组织玩家洗练实例数据
	 * @author hzw
	 * @date 2014-7-9下午3:19:33
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerWash(InstPlayerWash instPlayerWash){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerWash.getId());
		msgData.putIntItem("2", instPlayerWash.getInstPlayerId());
		msgData.putIntItem("3", instPlayerWash.getInstPlayerEquipId());
		msgData.putIntItem("4", instPlayerWash.getFightPropId());
		msgData.putIntItem("5", instPlayerWash.getEquipWashId());


		return msgData;
	}
	
	/**
	 * 玩家物品实例表
	 * @author hzw
	 * @date 2014-7-15下午5:31:07
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerThing(MessageData thingMsgData, List<InstPlayerThing> thingList){
		for(InstPlayerThing instPlayerThing : thingList){
			MessageData msgData = orgInstPlayerThing(instPlayerThing);
			thingMsgData.putMessageItem(instPlayerThing.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家物品实例表
	 * @author hzw
	 * @date 2014-7-15下午5:31:26
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerThing(InstPlayerThing instPlayerThing){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerThing.getId());
		msgData.putIntItem("2", instPlayerThing.getInstPlayerId());
		msgData.putIntItem("3", instPlayerThing.getThingId());
		msgData.putIntItem("4", instPlayerThing.getLevel());
		msgData.putIntItem("5", instPlayerThing.getNum());
		msgData.putIntItem("6", instPlayerThing.getThingTypeId());
		msgData.putIntItem("7", instPlayerThing.getBagTypeId());
		msgData.putIntItem("8", instPlayerThing.getIndexOrder());

		return msgData;
	}
	
	/**
	 * 组织玩家功法实例数据
	 * @author hzw
	 * @date 2014-7-17下午2:50:08
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerKungFu(MessageData kungFuMsgData, List<InstPlayerKungFu> kungFuList){
		for(InstPlayerKungFu instPlayerKungFu : kungFuList){
			MessageData msgData = orgInstPlayerKungFu(instPlayerKungFu);
			kungFuMsgData.putMessageItem(instPlayerKungFu.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家功法实例数据
	 * @author hzw
	 * @date 2014-7-17下午2:50:16
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerKungFu(InstPlayerKungFu instPlayerKungFu){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerKungFu.getId());
		msgData.putIntItem("2", instPlayerKungFu.getInstPlayerId());
		msgData.putIntItem("3", instPlayerKungFu.getKungFuId());
		msgData.putIntItem("4", instPlayerKungFu.getExp());
		msgData.putIntItem("5", instPlayerKungFu.getKungFuTierAddId());
		msgData.putIntItem("6", instPlayerKungFu.getAcupointNodeId());
		msgData.putStringItem("7", instPlayerKungFu.getAcupointNodes());
		msgData.putStringItem("8", instPlayerKungFu.getFightPropOne());
		msgData.putStringItem("9", instPlayerKungFu.getFightPropTwo());
		msgData.putStringItem("10", instPlayerKungFu.getFightPropThree());
		msgData.putIntItem("11", instPlayerKungFu.getInstPlayerCardId());
		msgData.putStringItem("12", instPlayerKungFu.getFightPropFour());
		msgData.putStringItem("13", instPlayerKungFu.getFightPropFive());
		msgData.putStringItem("14", instPlayerKungFu.getFightPropSix());

		return msgData;
	}
	
	/**
	 * 组织玩家异火实例数据
	 * @author hzw
	 * @date 2014-7-23下午5:13:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerFire(MessageData kungFuMsgData, List<InstPlayerFire> fireList){
		for(InstPlayerFire instPlayerFire : fireList){
			MessageData msgData = orgInstPlayerFire(instPlayerFire);
			kungFuMsgData.putMessageItem(instPlayerFire.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家异火实例数据
	 * @author hzw
	 * @date 2014-7-23下午5:13:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerFire(InstPlayerFire instPlayerFire){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerFire.getId());
		msgData.putIntItem("2", instPlayerFire.getInstPlayerId());
		msgData.putIntItem("3", instPlayerFire.getFireId());
		msgData.putIntItem("4", instPlayerFire.getLevel());
		msgData.putIntItem("5", instPlayerFire.getExp());
		msgData.putStringItem("6", instPlayerFire.getBySkillIds());
		msgData.putIntItem("7", instPlayerFire.getIsUse());

		return msgData;
	}
	
	/**
	 * 组织玩家卡牌命宫实例数据
	 * @author hzw
	 * @date 2014-8-1上午11:48:43
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerConstellDAL(MessageData constellMsgData, List<InstPlayerConstell> constellList){
		for(InstPlayerConstell instPlayerConstell : constellList){
			MessageData msgData = orgInstPlayerConstellDAL(instPlayerConstell);
			constellMsgData.putMessageItem(instPlayerConstell.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家卡牌命宫实例数据
	 * @author hzw
	 * @date 2014-8-1上午11:49:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerConstellDAL(InstPlayerConstell instPlayerConstell){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerConstell.getId());
		msgData.putIntItem("2", instPlayerConstell.getInstPlayerId());
		msgData.putIntItem("3", instPlayerConstell.getInstCardId());
		msgData.putIntItem("4", instPlayerConstell.getConstellId());
		msgData.putStringItem("5", instPlayerConstell.getPills());

		return msgData;
	}
	
	/**
	 * 组织玩家丹药实例数据
	 * @author hzw
	 * @date 2014-8-5上午10:58:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerPillDAL(MessageData pillMsgData, List<InstPlayerPill> pillList){
		for(InstPlayerPill instPlayerPill : pillList){
			MessageData msgData = orgInstPlayerPillDAL(instPlayerPill);
			pillMsgData.putMessageItem(instPlayerPill.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家丹药实例数据
	 * @author hzw
	 * @date 2014-8-5上午10:58:15
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerPillDAL(InstPlayerPill instPlayerPill){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerPill.getId());
		msgData.putIntItem("2", instPlayerPill.getInstPlayerId());
		msgData.putIntItem("3", instPlayerPill.getPillId());
		msgData.putIntItem("4", instPlayerPill.getNum());

		return msgData;
	}
	
	/**
	 * 组织玩家丹药药方实例数据
	 * @author hzw
	 * @date 2014-8-5上午11:00:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerPillRecipeDAL(MessageData pillRecipeMsgData, List<InstPlayerPillRecipe> pillRecipeList){
		for(InstPlayerPillRecipe instPlayerPillRecipe : pillRecipeList){
			MessageData msgData = orgInstPlayerPillRecipeDAL(instPlayerPillRecipe);
			pillRecipeMsgData.putMessageItem(instPlayerPillRecipe.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家丹药药方实例数据
	 * @author hzw
	 * @date 2014-8-5上午11:01:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerPillRecipeDAL(InstPlayerPillRecipe instPlayerPillRecipe){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerPillRecipe.getId());
		msgData.putIntItem("2", instPlayerPillRecipe.getInstPlayerId());
		msgData.putIntItem("3", instPlayerPillRecipe.getPillRecipeId());
		msgData.putIntItem("4", instPlayerPillRecipe.getNum());
		
		return msgData;
	}
	
	/**
	 * 组织玩家丹药药方材料实例数据
	 * @author hzw
	 * @date 2014-8-5上午11:02:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerPillRecipeThingDAL(MessageData pillRecipeThingMsgData, List<InstPlayerPillRecipeThing> pillRecipeThingList){
		for(InstPlayerPillRecipeThing instPlayerPillRecipeThing : pillRecipeThingList){
			MessageData msgData = orgInstPlayerPillRecipeThingDAL(instPlayerPillRecipeThing);
			pillRecipeThingMsgData.putMessageItem(instPlayerPillRecipeThing.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家丹药药方材料实例数据
	 * @author hzw
	 * @date 2014-8-5上午11:02:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerPillRecipeThingDAL(InstPlayerPillRecipeThing instPlayerPillRecipeThing){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerPillRecipeThing.getId());
		msgData.putIntItem("2", instPlayerPillRecipeThing.getInstPlayerId());
		msgData.putIntItem("3", instPlayerPillRecipeThing.getPillRecipeThingId());
		msgData.putIntItem("4", instPlayerPillRecipeThing.getNum());

		return msgData;
	}
	
	/**
	 * 组织玩家丹药材料实例数据
	 * @author hzw
	 * @date 2014-8-5上午11:03:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerPillThingDAL(MessageData pillThingMsgData, List<InstPlayerPillThing> pillThingList){
		for(InstPlayerPillThing instPlayerPillThing : pillThingList){
			MessageData msgData = orgInstPlayerPillThingDAL(instPlayerPillThing);
			pillThingMsgData.putMessageItem(instPlayerPillThing.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家丹药材料实例数据
	 * @author hzw
	 * @date 2014-8-5上午11:03:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerPillThingDAL(InstPlayerPillThing instPlayerPillThing){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerPillThing.getId());
		msgData.putIntItem("2", instPlayerPillThing.getInstPlayerId());
		msgData.putIntItem("3", instPlayerPillThing.getPillThingId());
		msgData.putIntItem("4", instPlayerPillThing.getNum());

		return msgData;
	}
	
	/**
	 * 组织玩家爬塔实例数据 
	 * @author hzw
	 * @date 2014-8-14上午10:14:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerPagodaDAL(MessageData pagodaMsgData, List<InstPlayerPagoda> pagodaList){
		for(InstPlayerPagoda instPlayerPagoda : pagodaList){
			MessageData msgData = orgInstPlayerPagodaDAL(instPlayerPagoda);
			pagodaMsgData.putMessageItem(instPlayerPagoda.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家爬塔实例数据 
	 * @author hzw
	 * @date 2014-8-14上午10:14:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerPagodaDAL(InstPlayerPagoda instPlayerPagoda){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerPagoda.getId());
		msgData.putIntItem("2", instPlayerPagoda.getInstPlayerId());
		msgData.putIntItem("3", instPlayerPagoda.getPagodaStoreyId());
		msgData.putIntItem("4", instPlayerPagoda.getNum());
		msgData.putIntItem("5", instPlayerPagoda.getResetNum());
		msgData.putIntItem("6", instPlayerPagoda.getSearchNum());
		msgData.putIntItem("7", instPlayerPagoda.getMaxPagodaStoreyId());
		msgData.putStringItem("8", instPlayerPagoda.getOperTime());
		msgData.putStringItem("9", instPlayerPagoda.getPagodaStores());

		return msgData;
	}
	
	/**
	 * 玩家副本章节类型实例表
	 * @author hzw
	 * @date 2014-8-20下午3:09:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerChapterTypeDAL(MessageData chapterTypeMsgData, List<InstPlayerChapterType> chapterTypeList){
		for(InstPlayerChapterType instPlayerChapterType : chapterTypeList){
			MessageData msgData = orgInstPlayerChapterTypeDAL(instPlayerChapterType);
			chapterTypeMsgData.putMessageItem(instPlayerChapterType.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家副本章节类型实例表
	 * @author hzw
	 * @date 2014-8-20下午3:09:50
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerChapterTypeDAL(InstPlayerChapterType instPlayerChapterType){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerChapterType.getId());
		msgData.putIntItem("2", instPlayerChapterType.getInstPlayerId());
		msgData.putIntItem("3", instPlayerChapterType.getChapterType());
		msgData.putIntItem("4", instPlayerChapterType.getFightNum());
		msgData.putStringItem("5", instPlayerChapterType.getAKeyTime());
		msgData.putIntItem("6", instPlayerChapterType.getBuyNum());
		msgData.putStringItem("7", instPlayerChapterType.getOperTime());

		return msgData;
	}
	
	/**
	 * 玩家副本章节关卡实例表
	 * @author hzw
	 * @date 2014-8-20下午3:13:28
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerBarrierDAL(MessageData BarrierMsgData, List<InstPlayerBarrier> barrierList){
		for(InstPlayerBarrier instPlayerBarrier : barrierList){
			MessageData msgData = orgInstPlayerBarrierDAL(instPlayerBarrier);
			BarrierMsgData.putMessageItem(instPlayerBarrier.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家副本章节关卡实例表
	 * @author hzw
	 * @date 2014-8-20下午3:13:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerBarrierDAL(InstPlayerBarrier instPlayerBarrier){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerBarrier.getId());
		msgData.putIntItem("2", instPlayerBarrier.getInstPlayerId());
		msgData.putIntItem("3", instPlayerBarrier.getBarrierId());
		msgData.putIntItem("4", instPlayerBarrier.getFightNum());
		msgData.putIntItem("5", instPlayerBarrier.getChapterId());
		msgData.putIntItem("6", instPlayerBarrier.getBarrierLevel());
		msgData.putIntItem("7", instPlayerBarrier.getResetNum());
		msgData.putStringItem("8", instPlayerBarrier.getOperTime());
		msgData.putIntItem("9", instPlayerBarrier.getWelfareBox());
		
		return msgData;
	}
	
	/**
	 * 玩家副本章节实例表
	 * @author hzw
	 * @date 2014-8-20下午3:18:24
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerChapterDAL(MessageData chapterMsgData, List<InstPlayerChapter> chapterList){
		for(InstPlayerChapter instPlayerChapter : chapterList){
			MessageData msgData = orgInstPlayerChapterDAL(instPlayerChapter);
			chapterMsgData.putMessageItem(instPlayerChapter.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家副本章节实例表
	 * @author hzw
	 * @date 2014-8-20下午3:18:33
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerChapterDAL(InstPlayerChapter instPlayerChapter){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerChapter.getId());
		msgData.putIntItem("2", instPlayerChapter.getInstPlayerId());
		msgData.putIntItem("3", instPlayerChapter.getChapterId());
		msgData.putIntItem("4", instPlayerChapter.getFightNum());
		msgData.putIntItem("5", instPlayerChapter.getStarNum());
		msgData.putIntItem("6", instPlayerChapter.getIsPass());
		msgData.putStringItem("7", instPlayerChapter.getGetStarType());
		msgData.putIntItem("8", instPlayerChapter.getBuyNum());
		msgData.putStringItem("9", instPlayerChapter.getOperTime());

		return msgData;
	}
	
	/**
	 * 活动副本实例表
	 * @author hzw
	 * @date 2014-8-26上午11:09:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstChapterActivityDAL(MessageData ChapterMsgData, List<InstChapterActivity> instChapterActivityList){
		for(InstChapterActivity instChapterActivity : instChapterActivityList){
			MessageData msgData = orgInstChapterActivityDAL(instChapterActivity);
			ChapterMsgData.putMessageItem(instChapterActivity.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 活动副本实例表
	 * @author hzw
	 * @date 2014-8-26上午11:09:41
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstChapterActivityDAL(InstChapterActivity instChapterActivity){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instChapterActivity.getId());
		msgData.putIntItem("2", instChapterActivity.getChapterId());

		return msgData;
	}
	
	/**
	 * 玩家卡牌魂魄实例表
	 * @author hzw
	 * @date 2014-8-29下午5:59:43
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerCardSoulDAL(MessageData cardSoulMsgData, List<InstPlayerCardSoul> cardSoulList){
		for(InstPlayerCardSoul instPlayerCardSoul : cardSoulList){
			MessageData msgData = orgInstPlayerCardSoulDAL(instPlayerCardSoul);
			cardSoulMsgData.putMessageItem(instPlayerCardSoul.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家卡牌魂魄实例表
	 * @author hzw
	 * @date 2014-8-29下午6:00:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerCardSoulDAL(InstPlayerCardSoul instPlayerCardSoul){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerCardSoul.getId());
		msgData.putIntItem("2", instPlayerCardSoul.getInstPlayerId());
		msgData.putIntItem("3", instPlayerCardSoul.getCardId());
		msgData.putIntItem("4", instPlayerCardSoul.getCardSoulId());
		msgData.putIntItem("5", instPlayerCardSoul.getNum());

		return msgData;
	}
	
	/**
	 * 玩家卡牌修炼实例表 
	 * @author hzw
	 * @date 2014-9-2下午3:28:07
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerTrainDAL(MessageData trainMsgData, List<InstPlayerTrain> trainList){
		for(InstPlayerTrain instPlayerTrain : trainList){
			MessageData msgData = orgInstPlayerTrainDAL(instPlayerTrain);
			trainMsgData.putMessageItem(instPlayerTrain.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家卡牌修炼实例表 
	 * @author hzw
	 * @date 2014-9-2下午3:28:29
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerTrainDAL(InstPlayerTrain instPlayerTrain){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerTrain.getId());
		msgData.putIntItem("2", instPlayerTrain.getInstPlayerId());
		msgData.putIntItem("3", instPlayerTrain.getInstPlayerCardId());
		msgData.putIntItem("4", instPlayerTrain.getFightPropId());
		msgData.putIntItem("5", instPlayerTrain.getFightPropValue());

		return msgData;
	}
	
	/**
	 * 碎片实例表
	 * @author hzw
	 * @date 2014-9-4下午5:56:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerChipDAL(MessageData chipMsgData, List<InstPlayerChip> chipList){
		for(InstPlayerChip instPlayerChip : chipList){
			MessageData msgData = orgInstPlayerChipDAL(instPlayerChip);
			chipMsgData.putMessageItem(instPlayerChip.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 碎片实例表
	 * @author hzw
	 * @date 2014-9-4下午5:56:32
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerChipDAL(InstPlayerChip instPlayerChip){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerChip.getId());
		msgData.putIntItem("2", instPlayerChip.getInstPlayerId());
		msgData.putIntItem("3", instPlayerChip.getChipId());
		msgData.putIntItem("4", instPlayerChip.getNum());

		return msgData;
	}
	
	/**
	 * 抢夺实例表
	 * @author hzw
	 * @date 2014-9-4下午5:56:18
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerLootDAL(MessageData lootMsgData, List<InstPlayerLoot> lootList){
		for(InstPlayerLoot instPlayerLoot : lootList){
			MessageData msgData = orgInstPlayerLootDAL(instPlayerLoot);
			lootMsgData.putMessageItem(instPlayerLoot.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 抢夺实例表
	 * @author hzw
	 * @date 2014-9-4下午5:56:03
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerLootDAL(InstPlayerLoot instPlayerLoot){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerLoot.getId());
		msgData.putIntItem("2", instPlayerLoot.getInstPlayerId());
		msgData.putStringItem("3", instPlayerLoot.getProtectTime());
		msgData.putStringItem("4", instPlayerLoot.getSkills());
		msgData.putStringItem("5", instPlayerLoot.getKungFus());
		msgData.putStringItem("6", instPlayerLoot.getMagics());

		return msgData;
	}
	
	/**
	 * 玩家手动技能实例表
	 * @author hzw
	 * @date 2014-9-11下午3:54:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerManualSkillDAL(MessageData manualSkillMsgData, List<InstPlayerManualSkill> manualSkillList){
		for(InstPlayerManualSkill InstPlayerManualSkill : manualSkillList){
			MessageData msgData = orgInstPlayerManualSkillDAL(InstPlayerManualSkill);
			manualSkillMsgData.putMessageItem(InstPlayerManualSkill.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家手动技能实例表
	 * @author hzw
	 * @date 2014-9-11下午3:54:45
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerManualSkillDAL(InstPlayerManualSkill instPlayerManualSkill){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerManualSkill.getId());
		msgData.putIntItem("2", instPlayerManualSkill.getInstPlayerId());
		msgData.putIntItem("3", instPlayerManualSkill.getType());
		msgData.putIntItem("4", instPlayerManualSkill.getManualSkillId());
		msgData.putIntItem("5", instPlayerManualSkill.getLevel());
		msgData.putIntItem("6", instPlayerManualSkill.getExp());
		msgData.putIntItem("7", instPlayerManualSkill.getIsUse());

		return msgData;
	}
	
	/**
	 * 玩家手动技能排布实例表
	 * @author hzw
	 * @date 2014-9-11下午3:55:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerManualSkillLineDAL(MessageData manualSkillLineMsgData, List<InstPlayerManualSkillLine> manualSkillLineList){
		for(InstPlayerManualSkillLine InstPlayerManualSkillLine : manualSkillLineList){
			MessageData msgData = orgInstPlayerManualSkillLineDAL(InstPlayerManualSkillLine);
			manualSkillLineMsgData.putMessageItem(InstPlayerManualSkillLine.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家手动技能排布实例表
	 * @author hzw
	 * @date 2014-9-11下午3:55:57
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerManualSkillLineDAL(InstPlayerManualSkillLine instPlayerManualSkillLine){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerManualSkillLine.getId());
		msgData.putIntItem("2", instPlayerManualSkillLine.getInstPlayerId());
		msgData.putIntItem("3", instPlayerManualSkillLine.getPosition1());
		msgData.putIntItem("4", instPlayerManualSkillLine.getPosition2());
		msgData.putIntItem("5", instPlayerManualSkillLine.getPosition3());
		msgData.putIntItem("6", instPlayerManualSkillLine.getPosition4());

		return msgData;
	}
	 
	/**
	 * 玩家活动实例数据
	 * @author hzw
	 * @date 2014-9-26上午11:23:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstActivityDAL(MessageData activityMsgData, List<InstActivity> activityList){
		for(InstActivity instActivity : activityList){
			MessageData msgData = orgInstActivityDAL(instActivity);
			activityMsgData.putMessageItem(instActivity.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家活动实例数据
	 * @author hzw
	 * @date 2014-9-26上午11:23:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstActivityDAL(InstActivity instActivity){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instActivity.getId());
		msgData.putIntItem("2", instActivity.getInstPlayerId());
		msgData.putIntItem("3", instActivity.getActivityId());
		msgData.putStringItem("4", instActivity.getActivityTime());
		msgData.putIntItem("5", instActivity.getIsForever());
		msgData.putIntItem("6", instActivity.getUseNum());
		msgData.putStringItem("7", instActivity.getSpareOne());
		msgData.putStringItem("8", instActivity.getSpareTwo());

		return msgData;
	}
	
	/**
	 * 玩家拍卖行实例数据
	 * @author hzw
	 * @date 2014-9-26上午11:29:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstAuctionShopDAL(MessageData auctionShopMsgData, List<InstAuctionShop> auctionShopList){
		for(InstAuctionShop instAuctionShop : auctionShopList){
			MessageData msgData = orgInstAuctionShopDAL(instAuctionShop);
			auctionShopMsgData.putMessageItem(instAuctionShop.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家拍卖行实例数据
	 * @author hzw
	 * @date 2014-9-26上午11:29:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstAuctionShopDAL(InstAuctionShop instAuctionShop){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instAuctionShop.getId());
		msgData.putIntItem("2", instAuctionShop.getInstPlayerId());
		msgData.putIntItem("3", instAuctionShop.getTableTypeId());
		msgData.putIntItem("4", instAuctionShop.getTableFieldId());
		msgData.putIntItem("5", instAuctionShop.getValue());
		msgData.putIntItem("6", instAuctionShop.getSellType());
		msgData.putIntItem("7", instAuctionShop.getSellValue());
		msgData.putIntItem("8", instAuctionShop.getSellOut());

		return msgData;
	}

	/**
	 * 玩家拍卖行实例数据
	 * @author hzw
	 * @date 2014-9-26上午11:29:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstHJYStoreDAL(MessageData auctionShopMsgData, List<InstHJYStore> hJYStoreList){
		for(InstHJYStore instHJYStore : hJYStoreList){
			MessageData msgData = orgInstHJYStoreDAL(instHJYStore);
			auctionShopMsgData.putMessageItem(instHJYStore.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家拍卖行实例数据
	 * @author hzw
	 * @date 2014-9-26上午11:29:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstHJYStoreDAL(InstHJYStore instHJYStore){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instHJYStore.getId());
		msgData.putIntItem("2", instHJYStore.getInstPlayerId());
		msgData.putIntItem("3", instHJYStore.getTableTypeId());
		msgData.putIntItem("4", instHJYStore.getTableFieldId());
		msgData.putIntItem("5", instHJYStore.getValue());
		msgData.putIntItem("6", instHJYStore.getSellType());
		msgData.putIntItem("7", instHJYStore.getSellValue());
		msgData.putIntItem("8", instHJYStore.getSellOut());

		return msgData;
	}
	
	/**
	 * 组织玩家在线奖励活动实例数据
	 * @author hzw
	 * @date 2014-10-17上午11:04:14
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstActivityOnlineRewardsDAL(MessageData onlineRewardsMsgData, List<InstActivityOnlineRewards> onlineRewardsList){
		for(InstActivityOnlineRewards instActivityOnlineRewards : onlineRewardsList){
			MessageData msgData = orgInstActivityOnlineRewardsDAL(instActivityOnlineRewards);
			onlineRewardsMsgData.putMessageItem(instActivityOnlineRewards.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家在线奖励活动实例数据
	 * @author hzw
	 * @date 2014-10-16上午10:46:46
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstActivityOnlineRewardsDAL(InstActivityOnlineRewards instActivityOnlineRewards){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instActivityOnlineRewards.getId());
		msgData.putIntItem("2", instActivityOnlineRewards.getInstPlayerId());
		msgData.putIntItem("3", instActivityOnlineRewards.getOnlineRewardsId());
		msgData.putIntItem("4", instActivityOnlineRewards.getOnlineTime());
		msgData.putStringItem("5", instActivityOnlineRewards.getThings());

		return msgData;
	}
	
	/**
	 * 组织玩家开服礼包活动实例数据
	 * @author hzw
	 * @date 2014-10-17上午11:20:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstActivityOpenServiceBagDAL(MessageData openServiceBagMsgData, List<InstActivityOpenServiceBag> openServiceBagList){
		for(InstActivityOpenServiceBag instActivityOpenServiceBag : openServiceBagList){
			MessageData msgData = orgInstActivityOpenServiceBagDAL(instActivityOpenServiceBag);
			openServiceBagMsgData.putMessageItem(instActivityOpenServiceBag.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家开服礼包活动实例数据
	 * @author hzw
	 * @date 2014-10-16上午10:46:55
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstActivityOpenServiceBagDAL(InstActivityOpenServiceBag instActivityOpenServiceBag){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instActivityOpenServiceBag.getId());
		msgData.putIntItem("2", instActivityOpenServiceBag.getInstPlayerId());
		msgData.putIntItem("3", instActivityOpenServiceBag.getDay());
		msgData.putStringItem("4", instActivityOpenServiceBag.getCan());
		msgData.putStringItem("5", instActivityOpenServiceBag.getEnd());
		
		return msgData;
	}
	
	/**
	 * 组织玩家等级礼包活动实例数据
	 * @author hzw
	 * @date 2014-10-17上午11:21:52
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstActivityLevelBagDAL(MessageData levelBagMsgData, List<InstActivityLevelBag> levelBagList){
		for(InstActivityLevelBag instActivityLevelBag : levelBagList){
			MessageData msgData = orgInstActivityLevelBagDAL(instActivityLevelBag);
			levelBagMsgData.putMessageItem(instActivityLevelBag.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家等级礼包活动实例数据
	 * @author hzw
	 * @date 2014-10-16上午10:46:55
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstActivityLevelBagDAL(InstActivityLevelBag instActivityLevelBag){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instActivityLevelBag.getId());
		msgData.putIntItem("2", instActivityLevelBag.getInstPlayerId());
		msgData.putStringItem("3", instActivityLevelBag.getEnd());
		
		return msgData;
	}
	
	/**
	 * 组织玩家签到活动实例数据
	 * @author hzw
	 * @date 2014-10-20下午4:44:56
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstActivitySignInDAL(MessageData SignInMsgData, List<InstActivitySignIn> SignInList){
		for(InstActivitySignIn instActivitySignIn : SignInList){
			MessageData msgData = orgInstActivitySignInDAL(instActivitySignIn);
			SignInMsgData.putMessageItem(instActivitySignIn.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家签到活动实例数据
	 * @author hzw
	 * @date 2014-10-20下午4:45:20
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static MessageData orgInstActivitySignInDAL(InstActivitySignIn instActivitySignIn) {
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instActivitySignIn.getId());
		msgData.putIntItem("2", instActivitySignIn.getInstPlayerId());
		msgData.putIntItem("3", instActivitySignIn.getType());
		int whichDay = instActivitySignIn.getDay();
		if (instActivitySignIn.getType() == 2) {
			if (!DateUtil.isSameDay(instActivitySignIn.getUpdateTime(), DateUtil.getCurrTime(), DateFormat.YMDHMS)) {
				List<DictActivitySignIn> activitySignInList = (List<DictActivitySignIn>) DictMapList.dictActivitySignInMap.get(0); // 0为豪华奖励的月份
				if (whichDay == activitySignInList.size()) {
					whichDay = 0; // 周期循环为0
				}
			}
		}
		msgData.putIntItem("4", whichDay);
		msgData.putIntItem("5", instActivitySignIn.getIsTwo());
		msgData.putStringItem("8", instActivitySignIn.getUpdateTime());

		return msgData;
	}
	
	/**
	 * 活动字典数据
	 * @author hzw
	 * @date 2014-10-20下午4:44:40
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgDictActivityDAL(MessageData activityMsgData, List<SysActivity> dictActivityList){
		for(SysActivity dictActivity : dictActivityList){
			MessageData msgData = orgDictActivityDAL(dictActivity);
			activityMsgData.putMessageItem(dictActivity.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 活动Banner
	 * @author mp
	 * @date 2015-9-25 下午4:46:15
	 * @param activityBannerMsgData
	 * @param dictActivityBannerList
	 * @Description
	 */
	public static void orgDictActivityBannerDAL(MessageData activityBannerMsgData, List<DictActivityBanner> dictActivityBannerList){
		for(DictActivityBanner dictActivityBanner : dictActivityBannerList){
			MessageData msgData = orgDictActivityBannerDAL(dictActivityBanner);
			activityBannerMsgData.putMessageItem(dictActivityBanner.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 活动字典数据
	 * @author hzw
	 * @date 2014-10-20下午4:44:36
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgDictActivityDAL(SysActivity dictActivity){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", dictActivity.getId());
		msgData.putStringItem("2", dictActivity.getName());
		msgData.putIntItem("3", dictActivity.getSmallUiId());
		msgData.putStringItem("4", dictActivity.getStartTime() == null ? "" : dictActivity.getStartTime().trim());
		msgData.putStringItem("5", dictActivity.getEndTime() == null ? "" :dictActivity.getEndTime().trim());
		msgData.putIntItem("6", dictActivity.getIsNew());
		msgData.putIntItem("7", dictActivity.getIsForevery());
		msgData.putIntItem("8", dictActivity.getIsView());
		msgData.putStringItem("9", dictActivity.getSname());
		msgData.putStringItem("10", dictActivity.getDescription());
		msgData.putIntItem("11", dictActivity.getRank());
		msgData.putIntItem("12", dictActivity.getIsHotKey());
		return msgData;
	}
	
	/**
	 * 活动Banner
	 * @author mp
	 * @date 2015-9-25 下午4:46:48
	 * @param dictActivity
	 * @return
	 * @Description
	 */
	public static MessageData orgDictActivityBannerDAL(DictActivityBanner dictActivityBanner){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", dictActivityBanner.getId());
		msgData.putStringItem("2", dictActivityBanner.getTitle());
		msgData.putStringItem("3", dictActivityBanner.getUi());
		msgData.putIntItem("4", dictActivityBanner.getIsInActivity());
		msgData.putIntItem("5", dictActivityBanner.getActivityId());
		msgData.putStringItem("6", dictActivityBanner.getSkip());
		msgData.putStringItem("7", dictActivityBanner.getRank());
		msgData.putStringItem("8", dictActivityBanner.getDescription());
		return msgData;
	}
	
	/**
	 * 组织玩家领奖中心实例数据
	 * @author hzw
	 * @date 2014-10-21下午3:29:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerAwardDAL(MessageData awardMsgData, List<InstPlayerAward> awardList){
		for(InstPlayerAward instPlayerAward : awardList){
			MessageData msgData = orgInstPlayerAwardDAL(instPlayerAward);
			awardMsgData.putMessageItem(instPlayerAward.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家领奖中心实例数据
	 * @author hzw
	 * @date 2014-10-21下午3:30:27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerAwardDAL(InstPlayerAward instPlayerAward){
/*		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerAward.getId());
		msgData.putIntItem("2", instPlayerAward.getInstPlayerId());
		msgData.putIntItem("3", instPlayerAward.getName());
		msgData.putStringItem("4", instPlayerAward.getThings());
		msgData.putStringItem("5", instPlayerAward.getOperTime());
		msgData.putStringItem("6", instPlayerAward.getDescription());
		msgData.putStringItem("7", instPlayerAward.getInsertTime());*/
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerAward.getId());
		msgData.putIntItem("2", instPlayerAward.getInstPlayerId());
		msgData.putIntItem("3", instPlayerAward.getName());
		msgData.putStringItem("4", instPlayerAward.getThings());
		msgData.putStringItem("5", instPlayerAward.getOperTime());
		msgData.putStringItem("6", instPlayerAward.getDescription());
		msgData.putStringItem("8", instPlayerAward.getInsertTime());
		msgData.putStringItem("9", instPlayerAward.getUpdateTime());
		
		return msgData;
	}
	
	/**
	 * 组织玩家竞技场实例数据
	 * @author hzw
	 * @date 2014-10-28上午11:08:49
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerArenaDAL(MessageData arenaMsgData, List<InstPlayerArena> arenaList){
		for(InstPlayerArena instPlayerArena : arenaList){
			MessageData msgData = orgInstPlayerArenaDAL(instPlayerArena);
			arenaMsgData.putMessageItem(instPlayerArena.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家竞技场实例数据
	 * @author hzw
	 * @date 2014-10-28上午11:09:30
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerArenaDAL(InstPlayerArena instPlayerArena){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerArena.getId());
		msgData.putIntItem("2", instPlayerArena.getInstPlayerId());
		msgData.putIntItem("3", instPlayerArena.getRank());
		msgData.putIntItem("4", instPlayerArena.getUpRank());
		msgData.putIntItem("5", instPlayerArena.getArenaNum());
		msgData.putStringItem("6", instPlayerArena.getArenaTime());
		msgData.putIntItem("7", instPlayerArena.getPrestige());
		msgData.putIntItem("8", instPlayerArena.getBuyArenaNum());
		msgData.putStringItem("10", instPlayerArena.getOperTime());
		
		return msgData;
	}
	
	/**
	 * 组织玩家竞技场兑换实例数据
	 * @author hzw
	 * @date 2014-10-30上午11:21:44
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerArenaConvertDAL(MessageData arenaConvertMsgData, List<InstPlayerArenaConvert> arenaConvertList){
		for(InstPlayerArenaConvert instPlayerArenaConvert : arenaConvertList){
			MessageData msgData = orgInstPlayerArenaConvertDAL(instPlayerArenaConvert);
			arenaConvertMsgData.putMessageItem(instPlayerArenaConvert.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 组织玩家竞技场兑换实例数据
	 * @author hzw
	 * @date 2014-10-30上午11:22:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerArenaConvertDAL(InstPlayerArenaConvert instPlayerArenaConvert){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerArenaConvert.getId());
		msgData.putIntItem("2", instPlayerArenaConvert.getInstPlayerId());
		msgData.putIntItem("3", instPlayerArenaConvert.getArenaConvertId());
		msgData.putIntItem("4", instPlayerArenaConvert.getConvertNum());
		msgData.putStringItem("5", instPlayerArenaConvert.getOperTime());
		return msgData;
	}

	/**
	 * 玩家功法法宝实例表
	 * @author hzw
	 * @date 2014-9-4下午5:56:18
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerMagicDAL(MessageData magicMsgData, List<InstPlayerMagic> magicList){
		for(InstPlayerMagic instPlayerMagic : magicList){
			MessageData msgData = orgInstPlayerMagicDAL(instPlayerMagic);
			magicMsgData.putMessageItem(instPlayerMagic.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家功法法宝实例表
	 * @author hzw
	 * @date 2014-9-4下午5:56:03
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerMagicDAL(InstPlayerMagic instPlayerMagic){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerMagic.getId());
		msgData.putIntItem("2", instPlayerMagic.getInstPlayerId());
		msgData.putIntItem("3", instPlayerMagic.getMagicId());
		msgData.putIntItem("4", instPlayerMagic.getMagicType());
		msgData.putIntItem("5", instPlayerMagic.getQuality());
		msgData.putIntItem("6", instPlayerMagic.getMagicLevelId());
		msgData.putIntItem("7", instPlayerMagic.getExp());
		msgData.putIntItem("8", instPlayerMagic.getInstCardId());
		//Update by cui 数据库增加了字段
		msgData.putIntItem("10", instPlayerMagic.getAdvanceId());

		return msgData;
	}
	
	/**
	 * 玩家每日任务实例表
	 * @author mp
	 * @date 2014-12-29 上午10:48:50
	 * @param dailyTaskMsgData
	 * @param dailyTaskList
	 * @Description
	 */
	public static void orgInstPlayerDailyTaskDAL(MessageData dailyTaskMsgData, List<InstPlayerDailyTask> dailyTaskList){
		for(InstPlayerDailyTask instPlayerDailyTask : dailyTaskList){
			MessageData msgData = orgInstPlayerDailyTaskDAL(instPlayerDailyTask);
			dailyTaskMsgData.putMessageItem(instPlayerDailyTask.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家每日任务实例表
	 * @author mp
	 * @date 2014-12-29 上午10:49:42
	 * @param instPlayerMagic
	 * @return
	 * @Description
	 */
	public static MessageData orgInstPlayerDailyTaskDAL(InstPlayerDailyTask instPlayerDailyTask){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerDailyTask.getId());
		msgData.putIntItem("2", instPlayerDailyTask.getInstPlayerId());
		msgData.putIntItem("3", instPlayerDailyTask.getDailyTashkId());
		msgData.putIntItem("4", instPlayerDailyTask.getTimes());
		msgData.putIntItem("5", instPlayerDailyTask.getRewardState());
		return msgData;
	}
	
	/**
	 * 玩家成就实例表
	 * @author hzw
	 * @date 2015-1-22上午11:21:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerAchievementDAL(MessageData achievementMsgData, List<InstPlayerAchievement> achievementList){
		for(InstPlayerAchievement instPlayerAchievement : achievementList){
			MessageData msgData = orgInstPlayerAchievementDAL(instPlayerAchievement);
			achievementMsgData.putMessageItem(instPlayerAchievement.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家成就实例表
	 * @author hzw
	 * @date 2015-1-22上午11:21:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerAchievementDAL(InstPlayerAchievement instPlayerAchievement){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerAchievement.getId());
		msgData.putIntItem("2", instPlayerAchievement.getInstPlayerId());
		msgData.putIntItem("3", instPlayerAchievement.getAchievementTypeId());
		msgData.putIntItem("4", instPlayerAchievement.getAchievementId());
		msgData.putIntItem("5", instPlayerAchievement.getCanAchievementId());
		return msgData;
	}
	
	/**
	 * 玩家成就计数实例表
	 * @author hzw
	 * @date 2015-1-21下午5:15:56
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerAchievementValueDAL(MessageData achievementValueMsgData, List<InstPlayerAchievementValue> achievementValueList){
		for(InstPlayerAchievementValue instPlayerAchievementValue : achievementValueList){
			MessageData msgData = orgInstPlayerAchievementValueDAL(instPlayerAchievementValue);
			achievementValueMsgData.putMessageItem(instPlayerAchievementValue.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家成就计数实例表
	 * @author hzw
	 * @date 2015-1-21下午5:16:06
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerAchievementValueDAL(InstPlayerAchievementValue instPlayerAchievementValue){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerAchievementValue.getId());
		msgData.putIntItem("2", instPlayerAchievementValue.getInstPlayerId());
		msgData.putIntItem("3", instPlayerAchievementValue.getAchievementTypeId());
		msgData.putIntItem("4", instPlayerAchievementValue.getValue());
		return msgData;
	}
	
	/**
	 * 玩家美人实例表
	 * @author hzw
	 * @date 2015-3-24下午2:03:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerBeautyCardDAL(MessageData beautyCardMsgData, List<InstPlayerBeautyCard> beautyCardList){
		for(InstPlayerBeautyCard instPlayerBeautyCard : beautyCardList){
			MessageData msgData = orgInstPlayerBeautyCardDAL(instPlayerBeautyCard);
			beautyCardMsgData.putMessageItem(instPlayerBeautyCard.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 玩家美人实例表
	 * @author hzw
	 * @date 2015-3-24下午2:03:48
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerBeautyCardDAL(InstPlayerBeautyCard instPlayerBeautyCard){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerBeautyCard.getId());
		msgData.putIntItem("2", instPlayerBeautyCard.getInstPlayerId());
		msgData.putIntItem("3", instPlayerBeautyCard.getBeautyCardId());
		msgData.putIntItem("4", instPlayerBeautyCard.getBeautyCardExpId());
		msgData.putIntItem("5", instPlayerBeautyCard.getExp());
		msgData.putIntItem("6", instPlayerBeautyCard.getPr());
		return msgData;
	}

	/**
	 * 组织玩家邮件实例数据
	 * @author hzw
	 * @date 2014-10-21下午3:29:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerMailDAL(MessageData awardMsgData, List<InstPlayerMail> awardList)throws Exception{
/*
 * 
 * 正式版
 */
 //		String dateString = DateUtil.getCurrTime();
		for(InstPlayerMail instPlayerMail : awardList){
//			if(DateUtil.dayDiff(dateString, instPlayerMail.getInsertTime()) > 0){//只保留当天的数据
//				getInstPlayerMailDAL().deleteById(instPlayerMail.getId(), instPlayerMail.getInstPlayerId());
//			}else{
				MessageData msgData = orgInstPlayerMailDAL(instPlayerMail);
				awardMsgData.putMessageItem(instPlayerMail.getId()+"", msgData.getMsgData());
//			}
		}
		
		
/*
 * 老版
 * 		String dateString = DateUtil.getCurrTime();
		for(InstPlayerMail instPlayerMail : awardList){
			if(DateUtil.dayDiff(dateString, instPlayerMail.getInsertTime()) > 0){//只保留当天的数据
				getInstPlayerMailDAL().deleteById(instPlayerMail.getId(), instPlayerMail.getInstPlayerId());
			}else{
				MessageData msgData = orgInstPlayerMailDAL(instPlayerMail);
				awardMsgData.putMessageItem(instPlayerMail.getId()+"", msgData.getMsgData());
			}
		}*/
		
	}
	
	/**
	 * 组织玩家邮件实例数据
	 * @author hzw
	 * @date 2014-10-21下午3:30:27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerMailDAL(InstPlayerMail instPlayerMail){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerMail.getId());
		msgData.putIntItem("2", instPlayerMail.getInstPlayerId());
		msgData.putStringItem("3", instPlayerMail.getEnemyName());
		msgData.putIntItem("4", instPlayerMail.getType());
		msgData.putIntItem("5", instPlayerMail.getValue());
		msgData.putIntItem("6", instPlayerMail.getResults());
		msgData.putStringItem("7", instPlayerMail.getContent());
		msgData.putStringItem("9", instPlayerMail.getInsertTime());
		msgData.putStringItem("10", instPlayerMail.getUpdateTime());
		return msgData;
	}
	
	/**
	 * 整点抢实例数据
	 * @author hzw
	 * @date 2015-6-4下午4:28:46
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerGrabTheHourDAL(MessageData grabTheHourMsgData, List<InstPlayerGrabTheHour> grabTheHourList)throws Exception{
		for(InstPlayerGrabTheHour instPlayerGrabTheHour : grabTheHourList){
			MessageData msgData = orgInstPlayerGrabTheHourDAL(instPlayerGrabTheHour);
			grabTheHourMsgData.putMessageItem(instPlayerGrabTheHour.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 整点抢实例数据
	 * @author hzw
	 * @date 2015-6-4下午4:28:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerGrabTheHourDAL(InstPlayerGrabTheHour instPlayerGrabTheHour){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerGrabTheHour.getId());
		msgData.putIntItem("3", instPlayerGrabTheHour.getGrabTheHourId());
		
		return msgData;
	}
	
	/**
	 * 特卖会实例数据
	 * @author hzw
	 * @date 2015-6-4下午4:28:46
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerPrivateSaleDAL(MessageData privateSaleMsgData, List<InstPlayerPrivateSale> privateSaleList)throws Exception{
		for(InstPlayerPrivateSale instPlayerPrivateSale : privateSaleList){
			MessageData msgData = orgInstPlayerPrivateSaleDAL(instPlayerPrivateSale);
			privateSaleMsgData.putMessageItem(instPlayerPrivateSale.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 特卖会实例数据
	 * @author hzw
	 * @date 2015-6-4下午4:28:53
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerPrivateSaleDAL(InstPlayerPrivateSale instPlayerPrivateSale){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerPrivateSale.getId());
		msgData.putIntItem("3", instPlayerPrivateSale.getPrivateSaleId());
		msgData.putIntItem("4", instPlayerPrivateSale.getCount());
		
		return msgData;
	}
	
	/**
	 * 联盟成员信息
	 * @author hzw
	 * @date 2015-7-16下午3:09:29
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstUnionMemberDAL(MessageData unionMemberMsgData, List<InstUnionMember> unionMemberList)throws Exception{
		for(InstUnionMember instUnionMember : unionMemberList){
			MessageData msgData = orgInstUnionMemberDAL(instUnionMember);
			unionMemberMsgData.putMessageItem(instUnionMember.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 联盟成员信息
	 * @author hzw
	 * @date 2015-7-16下午3:09:50
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstUnionMemberDAL(InstUnionMember instUnionMember){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instUnionMember.getId());
		msgData.putIntItem("2", instUnionMember.getInstUnionId());
		msgData.putIntItem("3", instUnionMember.getInstPlayerId());
		msgData.putIntItem("4", instUnionMember.getUnionGradeId());
		msgData.putIntItem("5", instUnionMember.getSumOffer());
		msgData.putIntItem("6", instUnionMember.getCurrentOffer());
		msgData.putIntItem("7", instUnionMember.getUnionBuildId());
		
		return msgData;
	}
	
	/**
	 * 联盟申请信息
	 * @author hzw
	 * @date 2015-7-16下午3:31:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstUnionApplyDAL(MessageData unionApplyMsgData, List<InstUnionApply> unionApplyList)throws Exception{
		for(InstUnionApply instUnionApply : unionApplyList){
			MessageData msgData = orgInstUnionApplyDAL(instUnionApply);
			unionApplyMsgData.putMessageItem(instUnionApply.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 联盟申请信息
	 * @author hzw
	 * @date 2015-7-16下午3:09:50
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstUnionApplyDAL(InstUnionApply instUnionApply){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instUnionApply.getId());
		msgData.putIntItem("2", instUnionApply.getInstPlayerId());
		msgData.putIntItem("3", instUnionApply.getInstUnionrId());
		
		return msgData;
	}
	
	/**
	 * 联盟宝箱信息
	 * @author hzw
	 * @date 2015-7-25下午3:26:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstUnionBoxDAL(MessageData unionBoxMsgData, List<InstUnionBox> unionBoxList)throws Exception{
		for(InstUnionBox instUnionBox : unionBoxList){
			MessageData msgData = orgInstUnionBoxDAL(instUnionBox);
			unionBoxMsgData.putMessageItem(instUnionBox.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 联盟宝箱信息
	 * @author hzw
	 * @date 2015-7-25下午3:26:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstUnionBoxDAL(InstUnionBox instUnionBox){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instUnionBox.getId());
		msgData.putIntItem("2", instUnionBox.getInstUnionId());
		msgData.putStringItem("3", instUnionBox.getUnionBoxs());
		
		return msgData;
	}

	/**
	 * 试练日实例信息
	 * @author hzw
	 * @date 2015-7-25下午3:26:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerTryToPracticeDAL(MessageData PlayerTryToPracticeMsgData, List<InstPlayerTryToPractice> PlayerTryToPracticeList)throws Exception{
		for(InstPlayerTryToPractice instPlayerTryToPractice : PlayerTryToPracticeList){
			MessageData msgData = orgInstPlayerTryToPracticeDAL(instPlayerTryToPractice);
			PlayerTryToPracticeMsgData.putMessageItem(instPlayerTryToPractice.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 斗魂信息
	 * @author mp
	 * @date 2015-10-19 下午3:51:19
	 * @param PlayerFightSoulMsgData
	 * @param PlayerFightSoulList
	 * @throws Exception
	 * @Description
	 */
	public static void orgPlayerFightSoulDAL(MessageData PlayerFightSoulMsgData, List<InstPlayerFightSoul> PlayerFightSoulList)throws Exception{
		for(InstPlayerFightSoul instPlayerFightSoul : PlayerFightSoulList){
			MessageData msgData = orgInstPlayerFightSoulDAL(instPlayerFightSoul);
			PlayerFightSoulMsgData.putMessageItem(instPlayerFightSoul.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 猎魂信息
	 * @author mp
	 * @date 2015-10-19 下午3:51:27
	 * @param PlayerTryToPracticeMsgData
	 * @param PlayerTryToPracticeList
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerFightSoulHuntRuleDAL(MessageData PlayerFightSoulHuntRuleMsgData, List<InstPlayerFightSoulHuntRule> PlayerFightSoulHuntRuleList)throws Exception{
		for(InstPlayerFightSoulHuntRule instPlayerFightSoulHuntRule : PlayerFightSoulHuntRuleList){
			MessageData msgData = orgInstPlayerFightSoulHuntRuleDAL(instPlayerFightSoulHuntRule);
			PlayerFightSoulHuntRuleMsgData.putMessageItem(instPlayerFightSoulHuntRule.getId()+"", msgData.getMsgData());
		}
	}
	
	/**
	 * 翅膀信息
	 * @author mp
	 * @date 2015-11-12 下午3:29:15
	 * @param PlayerFightSoulHuntRuleMsgData
	 * @param PlayerFightSoulHuntRuleList
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerWingDAL(MessageData PlayerWingMsgData, List<InstPlayerWing> PlayerWingList)throws Exception{
		for(InstPlayerWing instPlayerWing : PlayerWingList){
			MessageData msgData = orgInstPlayerWing(instPlayerWing);
			PlayerWingMsgData.putMessageItem(instPlayerWing.getId()+"", msgData.getMsgData());
		}
	}
	
	
	/**
	 * 试练日实例信息
	 * @author hzw
	 * @date 2015-7-25下午3:26:22
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerTryToPracticeDAL(InstPlayerTryToPractice instPlayerTryToPractice){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerTryToPractice.getId());
		msgData.putIntItem("2", instPlayerTryToPractice.getInstPlayerId());
		msgData.putStringItem("3", instPlayerTryToPractice.getTryToPracticeIds());
		msgData.putStringItem("4", instPlayerTryToPractice.getCanTryToPracticeIds());
		msgData.putStringItem("5", instPlayerTryToPractice.getAwards());
		
		return msgData;
	}

	/**
	 * 斗魂实例信息
	 * @author mp
	 * @date 2015-10-19 下午3:57:04
	 * @param instPlayerFightSoul
	 * @return
	 * @Description
	 */
	public static MessageData orgInstPlayerFightSoulDAL(InstPlayerFightSoul instPlayerFightSoul){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerFightSoul.getId());
		msgData.putIntItem("2", instPlayerFightSoul.getInstPlayerId());
		msgData.putIntItem("3", instPlayerFightSoul.getFightSoulId());
		msgData.putIntItem("4", instPlayerFightSoul.getFightSoulQualityId());
		msgData.putIntItem("5", instPlayerFightSoul.getLevel());
		msgData.putIntItem("6", instPlayerFightSoul.getLockState());
		msgData.putIntItem("7", instPlayerFightSoul.getInstCardId());
		msgData.putIntItem("8", instPlayerFightSoul.getPosition());
		msgData.putIntItem("9", instPlayerFightSoul.getExp());

		return msgData;
	}

	/**
	 * 猎魂实例信息
	 * @author mp
	 * @date 2015-10-19 下午3:57:20
	 * @param instPlayerFightSoulHuntRule
	 * @return
	 * @Description
	 */
	public static MessageData orgInstPlayerFightSoulHuntRuleDAL(InstPlayerFightSoulHuntRule instPlayerFightSoulHuntRule){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerFightSoulHuntRule.getId());
		msgData.putIntItem("2", instPlayerFightSoulHuntRule.getInstPlayerId());
		msgData.putIntItem("3", instPlayerFightSoulHuntRule.getFightSouleHuntRuleId());
		return msgData;
	}
	
	/**
	 * 翅膀实例信息
	 * @author mp
	 * @date 2015-11-12 下午3:30:47
	 * @param instPlayerFightSoulHuntRule
	 * @return
	 * @Description
	 */
	public static MessageData orgInstPlayerWing(InstPlayerWing instPlayerWing){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerWing.getId());
		msgData.putIntItem("2", instPlayerWing.getInstPlayerId());
		msgData.putIntItem("3", instPlayerWing.getWingId());
		msgData.putIntItem("4", instPlayerWing.getLevel());
		msgData.putIntItem("5", instPlayerWing.getStarNum());
		msgData.putIntItem("6", instPlayerWing.getInstCardId());
		return msgData;
	}
	
	/**
	 * 玩家异火实例表
	 * @author cui
	 * @date  15/10/27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerYFireDAL(MessageData magicMsgData, List<InstPlayerYFire> yfireList){
		for(InstPlayerYFire instPlayerYFire : yfireList){
			MessageData msgData = orgInstPlayerYFireDAL(instPlayerYFire);
			magicMsgData.putMessageItem(instPlayerYFire.getId()+"", msgData.getMsgData());
		}
	}

	/**
	 * 玩家异火实例表
	 * @author cui
	 * @date  15/10/27
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerYFireDAL(InstPlayerYFire instPlayerYFire){
		//比较特殊的用法
		DictYFire dictYFire = null;
		for (DictYFire fire : DictList.dictYFireList) {
			if(fire.getId() == instPlayerYFire.getFireId()){
				dictYFire = fire;
				break;
			}
		}
		int cost = 0;
		if(dictYFire != null) {
			cost = dictYFire.getCost();
		}
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerYFire.getId());
		msgData.putIntItem("2", instPlayerYFire.getInstPlayerId());
		msgData.putIntItem("3", instPlayerYFire.getFireId());
		msgData.putIntItem("4", instPlayerYFire.getState());
		msgData.putStringItem("5", instPlayerYFire.getFireTime());
		msgData.putIntItem("6", instPlayerYFire.getHp());
		msgData.putIntItem("7", cost);
		msgData.putStringItem("8", instPlayerYFire.getOwner());
		msgData.putIntItem("9", instPlayerYFire.getChipCount());

		return msgData;
	}

	/**
	 *  组织玩家红装淬炼值实例数据
	 * @author cui
	 * @date	2015/12/09
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void orgInstPlayerRedEquip(MessageData redEquipMsgData, List<InstPlayerRedEquip> redEquipList){
		for(InstPlayerRedEquip instPlayerRedEquip : redEquipList){
			MessageData msgData = orgInstPlayerRedEquip(instPlayerRedEquip);
			redEquipMsgData.putMessageItem(instPlayerRedEquip.getId()+"", msgData.getMsgData());
		}
	}


	/**
	 *  组织玩家红装淬炼值实例数据
	 * @author cui
	 * @date	2015、12、09
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData orgInstPlayerRedEquip(InstPlayerRedEquip instPlayerRedEquip){
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", instPlayerRedEquip.getId());
		msgData.putIntItem("2", instPlayerRedEquip.getInstPlayerId());
		msgData.putIntItem("3", instPlayerRedEquip.getEquipInstId());
		msgData.putIntItem("4", instPlayerRedEquip.getContions());
		return msgData;
	}
	
	/**
	 * 首次登录向客户端发的缓存信息
	 * @author mp
	 * @date 2015-6-26 下午12:01:02
	 * @param retMsgData
	 * @param instPlayer
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static MessageData retFirstLoginPlayerInfoMsgData (MessageData retMsgData, InstPlayer instPlayer, int isAward) throws Exception{
		
		int instPlayerId = instPlayer.getId();
		PlayerMemObj playerMemObj = DALFather.getPlayerMemObjByPlayerId(instPlayerId);
		
		//组织玩家实例数据
		if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerMap.size() == 0){
			playerMemObj.instPlayerMap.put(instPlayerId, instPlayer);
		}
		MessageData playerMsgData = OrgFrontMsgUtil.orgInstPlayer(instPlayer);
		retMsgData.putMessageItem(instPlayer.getClass().getSimpleName(), playerMsgData.getMsgData());
		
		//组织活动字典数据
		List<SysActivity> dictActivityList = DictList.sysActivityList;
		if(dictActivityList.size() > 0){
			MessageData activityMsgData = new MessageData();
			OrgFrontMsgUtil.orgDictActivityDAL(activityMsgData, dictActivityList);
			retMsgData.putMessageItem(SysActivity.class.getSimpleName(), activityMsgData.getMsgData());
		}
		
		//组织活动Banner字典数据
		List<DictActivityBanner> dictActivityBannerList = DictList.dictActivityBannerList;
		if(dictActivityBannerList.size() > 0){
			MessageData activityBannerMsgData = new MessageData();
			OrgFrontMsgUtil.orgDictActivityBannerDAL(activityBannerMsgData, dictActivityBannerList);
			retMsgData.putMessageItem(DictActivityBanner.class.getSimpleName(), activityBannerMsgData.getMsgData());
		}
		
		//组织领奖中心
		if (isAward != 0) {
			List<InstPlayerAward> awardList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerAwardMap.size() == 0){
				awardList = getInstPlayerAwardDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerAward obj : awardList) {
					playerMemObj.instPlayerAwardMap.put(obj.getId(), obj);
				}
			} else {
				awardList = getInstPlayerAwardDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(awardList.size() > 0){
				MessageData awardMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerAwardDAL(awardMsgData, awardList);
				retMsgData.putMessageItem(InstPlayerAward.class.getSimpleName(), awardMsgData.getMsgData());
			}
		}
		
		//每日特惠是否购买标识
		retMsgData.putStringItem("DailyDealState", "0");
		
		return retMsgData;
	}
	
	/**
	 * AchievementData
	 * @author mp
	 * @date 2013-10-11 下午4:54:20
	 * @param retMsgData
	 * @param instPlayer
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static MessageData retPlayerInfoMsgData(MessageData retMsgData, InstPlayer instPlayer) throws Exception{
		
		int instPlayerId = instPlayer.getId();
		PlayerMemObj playerMemObj = DALFather.getPlayerMemObjByPlayerId(instPlayerId);
		
		//组织玩家实例数据
		if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerMap.size() == 0){
			playerMemObj.instPlayerMap.put(instPlayerId, instPlayer);
		}
		MessageData playerMsgData = OrgFrontMsgUtil.orgInstPlayer(instPlayer);
		retMsgData.putMessageItem(instPlayer.getClass().getSimpleName(), playerMsgData.getMsgData());
		
		/*//组织玩家卡牌实例数据
		if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerCardMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerCard> cardList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerCardMap.size() == 0){
				cardList = getInstPlayerCardDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerCard obj : cardList) {
					playerMemObj.instPlayerCardMap.put(obj.getId(), obj);
				}
			} else {
				cardList = getInstPlayerCardDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(cardList.size() > 0){
				MessageData cardMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerCard(cardMsgData, cardList);
				retMsgData.putMessageItem(InstPlayerCard.class.getSimpleName(), cardMsgData.getMsgData());
			}
//		}
		
		//组织玩家小伙伴实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPartnerMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerPartner> partnerList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerPartnerMap.size() == 0){
				partnerList = getInstPlayerPartnerDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerPartner obj : partnerList) {
					playerMemObj.instPlayerPartnerMap.put(obj.getId(), obj);
				}
			} else {
				partnerList = getInstPlayerPartnerDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(partnerList.size() > 0){
				MessageData partnerMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerPartner(partnerMsgData, partnerList);
				retMsgData.putMessageItem(InstPlayerPartner.class.getSimpleName(), partnerMsgData.getMsgData());
			}
//		}
		
		//组织玩家卡牌阵型实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerFormationMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerFormation> formationList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerFormationMap.size() == 0){
				formationList = getInstPlayerFormationDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerFormation obj : formationList) {
					playerMemObj.instPlayerFormationMap.put(obj.getId(), obj);
				}
			} else {
				formationList = getInstPlayerFormationDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(formationList.size() > 0){
				MessageData formationMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerFormation(formationMsgData, formationList);
				retMsgData.putMessageItem(InstPlayerFormation.class.getSimpleName(), formationMsgData.getMsgData());
			}
//		}
		
		//组织玩家装备实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerEquipMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerEquip> equipList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerEquipMap.size() == 0){
				equipList = getInstPlayerEquipDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerEquip obj : equipList) {
					playerMemObj.instPlayerEquipMap.put(obj.getId(), obj);
				}
			} else {
				equipList = getInstPlayerEquipDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(equipList.size() > 0){
				MessageData equipMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerEquip(equipMsgData, equipList);
				retMsgData.putMessageItem(InstPlayerEquip.class.getSimpleName(), equipMsgData.getMsgData());
			}
		    //Update by cui 红装淬炼值表
			List<InstPlayerRedEquip> redEquipList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerRedEquipMap.size() == 0){
				redEquipList = getInstPlayerRedEquipDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerRedEquip obj : redEquipList) {
					playerMemObj.instPlayerRedEquipMap.put(obj.getId(), obj);
				}
			} else {
				redEquipList = getInstPlayerRedEquipDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(redEquipList.size() > 0){
				MessageData redEquipMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerRedEquip(redEquipMsgData, redEquipList);
				retMsgData.putMessageItem(InstPlayerRedEquip.class.getSimpleName(), redEquipMsgData.getMsgData());
			}
//		}
		
		//组织玩家阵容实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerLineupMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerLineup> lineupList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerLineupMap.size() == 0){
				lineupList = getInstPlayerLineupDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerLineup obj : lineupList) {
					playerMemObj.instPlayerLineupMap.put(obj.getId(), obj);
				}
			} else {
				lineupList = getInstPlayerLineupDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(lineupList.size() > 0){
				MessageData lineupMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerLineup(lineupMsgData, lineupList);
				retMsgData.putMessageItem(InstPlayerLineup.class.getSimpleName(), lineupMsgData.getMsgData());
			}
//		}
			
			//组织活动Banner字典数据
			List<DictActivityBanner> dictActivityBannerList = DictList.dictActivityBannerList;
			if(dictActivityBannerList.size() > 0){
				MessageData activityBannerMsgData = new MessageData();
				OrgFrontMsgUtil.orgDictActivityBannerDAL(activityBannerMsgData, dictActivityBannerList);
				retMsgData.putMessageItem(DictActivityBanner.class.getSimpleName(), activityBannerMsgData.getMsgData());
			}
		
		//组织玩家洗练实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerWashMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerWash> washList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerWashMap.size() == 0){
				washList = getInstPlayerWashDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerWash obj : washList) {
					playerMemObj.instPlayerWashMap.put(obj.getId(), obj);
				}
			} else {
				washList = getInstPlayerWashDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(washList.size() > 0){
				MessageData washMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerWash(washMsgData, washList);
				retMsgData.putMessageItem(InstPlayerWash.class.getSimpleName(), washMsgData.getMsgData());
			}
//		}
		
		//组织玩家物品实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerThingMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerThing> thingList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerThingMap.size() == 0){
				thingList = getInstPlayerThingDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerThing obj : thingList) {
					playerMemObj.instPlayerThingMap.put(obj.getId(), obj);
				}
			} else {
				thingList = getInstPlayerThingDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(thingList.size() > 0){
				MessageData thingMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerThing(thingMsgData, thingList);
				retMsgData.putMessageItem(InstPlayerThing.class.getSimpleName(), thingMsgData.getMsgData());
			}
//		}
		
		//组织玩家卡牌命宫实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerConstellMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerConstell> constellList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerConstellMap.size() == 0){
				constellList = getInstPlayerConstellDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerConstell obj : constellList) {
					playerMemObj.instPlayerConstellMap.put(obj.getId(), obj);
				}
			} else {
				constellList = getInstPlayerConstellDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(constellList.size() > 0){
				MessageData constellMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerConstellDAL(constellMsgData, constellList);
				retMsgData.putMessageItem(InstPlayerConstell.class.getSimpleName(), constellMsgData.getMsgData());
			}
//		}
		
		//组织玩家丹药实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPillMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerPill> pillList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerPillMap.size() == 0){
				pillList = getInstPlayerPillDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerPill obj : pillList) {
					playerMemObj.instPlayerPillMap.put(obj.getId(), obj);
				}
			} else {
				pillList = getInstPlayerPillDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(pillList.size() > 0){
				MessageData pillMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerPillDAL(pillMsgData, pillList);
				retMsgData.putMessageItem(InstPlayerPill.class.getSimpleName(), pillMsgData.getMsgData());
			}
//		}
				
		//组织玩家丹药药方实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPillRecipeMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerPillRecipe> pillRecipeList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerPillRecipeMap.size() == 0){
				pillRecipeList = getInstPlayerPillRecipeDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerPillRecipe obj : pillRecipeList) {
					playerMemObj.instPlayerPillRecipeMap.put(obj.getId(), obj);
				}
			} else {
				pillRecipeList = getInstPlayerPillRecipeDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(pillRecipeList.size() > 0){
				MessageData pillRecipeMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerPillRecipeDAL(pillRecipeMsgData, pillRecipeList);
				retMsgData.putMessageItem(InstPlayerPillRecipe.class.getSimpleName(), pillRecipeMsgData.getMsgData());
			}
//		}
		
		//组织玩家丹药药方材料实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPillRecipeThingMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerPillRecipeThing> pillRecipeThingList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerPillRecipeThingMap.size() == 0){
				pillRecipeThingList = getInstPlayerPillRecipeThingDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerPillRecipeThing obj : pillRecipeThingList) {
					playerMemObj.instPlayerPillRecipeThingMap.put(obj.getId(), obj);
				}
			} else {
				pillRecipeThingList = getInstPlayerPillRecipeThingDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(pillRecipeThingList.size() > 0){
				MessageData pillRecipeThingMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerPillRecipeThingDAL(pillRecipeThingMsgData, pillRecipeThingList);
				retMsgData.putMessageItem(InstPlayerPillRecipeThing.class.getSimpleName(), pillRecipeThingMsgData.getMsgData());
			}
//		}
		
		//组织玩家丹药材料实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPillThingMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerPillThing> pillThingList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerPillThingMap.size() == 0){
				pillThingList = getInstPlayerPillThingDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerPillThing obj : pillThingList) {
					playerMemObj.instPlayerPillThingMap.put(obj.getId(), obj);
				}
			} else {
				pillThingList = getInstPlayerPillThingDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(pillThingList.size() > 0){
				MessageData pillThingMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerPillThingDAL(pillThingMsgData, pillThingList);
				retMsgData.putMessageItem(InstPlayerPillThing.class.getSimpleName(), pillThingMsgData.getMsgData());
			}
//		}
		
		//组织玩家背包扩充实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerBagExpandMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerBagExpand> bagExpandList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerBagExpandMap.size() == 0){
				bagExpandList = getInstPlayerBagExpandDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerBagExpand obj : bagExpandList) {
					playerMemObj.instPlayerBagExpandMap.put(obj.getId(), obj);
				}
			} else {
				bagExpandList = getInstPlayerBagExpandDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(bagExpandList.size() > 0){
				MessageData bagExpandMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerBagExpand(bagExpandMsgData, bagExpandList);
				retMsgData.putMessageItem(InstPlayerBagExpand.class.getSimpleName(), bagExpandMsgData.getMsgData());
			}
//		}
		
		//组织玩家爬塔实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPagodaMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerPagoda> pagodaList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerPagodaMap.size() == 0){
				pagodaList = getInstPlayerPagodaDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerPagoda obj : pagodaList) {
					playerMemObj.instPlayerPagodaMap.put(obj.getId(), obj);
				}
			} else {
				pagodaList = getInstPlayerPagodaDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(pagodaList.size() > 0){
				MessageData pagodaMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerPagodaDAL(pagodaMsgData, pagodaList);
				retMsgData.putMessageItem(InstPlayerPagoda.class.getSimpleName(), pagodaMsgData.getMsgData());
			}
//		}
		
		//装备宝石
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instEquipGemMap.size() == 0) {
			//不处理
		} else {*/
			List<InstEquipGem> equipGemList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instEquipGemMap.size() == 0){
				equipGemList = getInstEquipGemDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstEquipGem obj : equipGemList) {
					playerMemObj.instEquipGemMap.put(obj.getId(), obj);
				}
			} else {
				equipGemList = getInstEquipGemDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(equipGemList.size() > 0){
				MessageData equipGemMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstEquipGem(equipGemMsgData, equipGemList);
				retMsgData.putMessageItem(InstEquipGem.class.getSimpleName(), equipGemMsgData.getMsgData());
			}
//		}
		
		//玩家副本章节类型实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerChapterTypeMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerChapterType> chapterTypeList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerChapterTypeMap.size() == 0){
				chapterTypeList = getInstPlayerChapterTypeDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerChapterType obj : chapterTypeList) {
					playerMemObj.instPlayerChapterTypeMap.put(obj.getId(), obj);
				}
			} else {
				chapterTypeList = getInstPlayerChapterTypeDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(chapterTypeList.size() > 0){
				MessageData chapterTypeMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerChapterTypeDAL(chapterTypeMsgData, chapterTypeList);
				retMsgData.putMessageItem(InstPlayerChapterType.class.getSimpleName(), chapterTypeMsgData.getMsgData());
			}
//		}
		
		//玩家副本关卡实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerBarrierMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerBarrier> barrierList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerBarrierMap.size() == 0){
				barrierList = getInstPlayerBarrierDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerBarrier obj : barrierList) {
					playerMemObj.instPlayerBarrierMap.put(obj.getId(), obj);
				}
			} else {
				barrierList = getInstPlayerBarrierDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(barrierList.size() > 0){
				MessageData barrierMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerBarrierDAL(barrierMsgData, barrierList);
				retMsgData.putMessageItem(InstPlayerBarrier.class.getSimpleName(), barrierMsgData.getMsgData());
			}
//		}
		
		//玩家副本章节实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerChapterMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerChapter> chapterList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerChapterMap.size() == 0){
				chapterList = getInstPlayerChapterDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerChapter obj : chapterList) {
					playerMemObj.instPlayerChapterMap.put(obj.getId(), obj);
				}
			} else {
				chapterList = getInstPlayerChapterDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(chapterList.size() > 0){
				MessageData chapterMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerChapterDAL(chapterMsgData, chapterList);
				retMsgData.putMessageItem(InstPlayerChapter.class.getSimpleName(), chapterMsgData.getMsgData());
			}
//		}
		
		//活动副本实例表
		List<InstChapterActivity> instChapterActivityList = getInstChapterActivityDAL().getList("", 0);
		if(instChapterActivityList.size() > 0){
			MessageData chapterActivityMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstChapterActivityDAL(chapterActivityMsgData, instChapterActivityList);
			retMsgData.putMessageItem(InstChapterActivity.class.getSimpleName(), chapterActivityMsgData.getMsgData());
		}
		
		//玩家卡牌魂魄实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerCardSoulMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerCardSoul> cardSoulList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerCardSoulMap.size() == 0){
				cardSoulList = getInstPlayerCardSoulDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerCardSoul obj : cardSoulList) {
					playerMemObj.instPlayerCardSoulMap.put(obj.getId(), obj);
				}
			} else {
				cardSoulList = getInstPlayerCardSoulDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(cardSoulList.size() > 0){
				MessageData cardSoulMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerCardSoulDAL(cardSoulMsgData, cardSoulList);
				retMsgData.putMessageItem(InstPlayerCardSoul.class.getSimpleName(), cardSoulMsgData.getMsgData());
			}
//		}
		
		//玩家卡牌修炼实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerTrainMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerTrain> trainList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerTrainMap.size() == 0){
				trainList = getInstPlayerTrainDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerTrain obj : trainList) {
					playerMemObj.instPlayerTrainMap.put(obj.getId(), obj);
				}
			} else {
				trainList = getInstPlayerTrainDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(trainList.size() > 0){
				MessageData trainMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerTrainDAL(trainMsgData, trainList);
				retMsgData.putMessageItem(InstPlayerTrain.class.getSimpleName(), trainMsgData.getMsgData());
			}
//		}
		
		//玩家碎片实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerChipMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerChip> chipList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerChipMap.size() == 0){
				chipList = getInstPlayerChipDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerChip obj : chipList) {
					playerMemObj.instPlayerChipMap.put(obj.getId(), obj);
				}
			} else {
				chipList = getInstPlayerChipDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(chipList.size() > 0){
				MessageData chipMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerChipDAL(chipMsgData, chipList);
				retMsgData.putMessageItem(InstPlayerChip.class.getSimpleName(), chipMsgData.getMsgData());
			}
//		}
		
		//玩家抢夺实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerLootMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerLoot> lootList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerLootMap.size() == 0){
				lootList = getInstPlayerLootDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerLoot obj : lootList) {
					playerMemObj.instPlayerLootMap.put(obj.getId(), obj);
				}
			} else {
				lootList = getInstPlayerLootDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(lootList.size() > 0){
				MessageData lootMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerLootDAL(lootMsgData, lootList);
				retMsgData.putMessageItem(InstPlayerLoot.class.getSimpleName(), lootMsgData.getMsgData());
			}
//		}
		
		//玩家活动实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instActivityMap.size() == 0) {
			//不处理
		} else {*/
			List<InstActivity> activityList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instActivityMap.size() == 0){
				activityList = getInstActivityDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstActivity obj : activityList) {
					playerMemObj.instActivityMap.put(obj.getId(), obj);
				}
			} else {
				activityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(activityList.size() > 0){
				MessageData activityMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstActivityDAL(activityMsgData, activityList);
				retMsgData.putMessageItem(InstActivity.class.getSimpleName(), activityMsgData.getMsgData());
			}
//		}
		
		//玩家拍卖行实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instAuctionShopMap.size() == 0) {
			//不处理
		} else {*/
			List<InstAuctionShop> auctionShopList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instAuctionShopMap.size() == 0){
				auctionShopList = getInstAuctionShopDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstAuctionShop obj : auctionShopList) {
					playerMemObj.instAuctionShopMap.put(obj.getId(), obj);
				}
			} else {
				auctionShopList = getInstAuctionShopDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(auctionShopList.size() > 0){
				MessageData auctionShopMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstAuctionShopDAL(auctionShopMsgData, auctionShopList);
				retMsgData.putMessageItem(InstAuctionShop.class.getSimpleName(), auctionShopMsgData.getMsgData());
			}
//		}
		
		//玩家黑角域卖场实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instHJYStoreMap.size() == 0) {
			//不处理
		} else {*/
			List<InstHJYStore> hJYStoreList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instHJYStoreMap.size() == 0){
				hJYStoreList = getInstHJYStoreDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstHJYStore obj : hJYStoreList) {
					playerMemObj.instHJYStoreMap.put(obj.getId(), obj);
				}
			} else {
				hJYStoreList = getInstHJYStoreDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(hJYStoreList.size() > 0){
				MessageData hJYStoreMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstHJYStoreDAL(hJYStoreMsgData, hJYStoreList);
				retMsgData.putMessageItem(InstHJYStore.class.getSimpleName(), hJYStoreMsgData.getMsgData());
			}
//		}
		
		//组织玩家在线奖励活动实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instActivityOnlineRewardsMap.size() == 0) {
			//不处理
		} else {*/
			List<InstActivityOnlineRewards> onlineRewardsList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instActivityOnlineRewardsMap.size() == 0){
				onlineRewardsList = getInstActivityOnlineRewardsDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstActivityOnlineRewards obj : onlineRewardsList) {
					playerMemObj.instActivityOnlineRewardsMap.put(obj.getId(), obj);
				}
			} else {
				onlineRewardsList = getInstActivityOnlineRewardsDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(onlineRewardsList.size() > 0){
				MessageData onlineRewardsMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstActivityOnlineRewardsDAL(onlineRewardsMsgData, onlineRewardsList);
				retMsgData.putMessageItem(InstActivityOnlineRewards.class.getSimpleName(), onlineRewardsMsgData.getMsgData());
			}
//		}
		
		//组织玩家开服礼包活动实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instActivityOpenServiceBagMap.size() == 0) {
			//不处理
		} else {*/
			List<InstActivityOpenServiceBag> openServiceBagList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instActivityOpenServiceBagMap.size() == 0){
				openServiceBagList = getInstActivityOpenServiceBagDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstActivityOpenServiceBag obj : openServiceBagList) {
					playerMemObj.instActivityOpenServiceBagMap.put(obj.getId(), obj);
				}
			} else {
				openServiceBagList = getInstActivityOpenServiceBagDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(openServiceBagList.size() > 0){
				MessageData openServiceBagMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstActivityOpenServiceBagDAL(openServiceBagMsgData, openServiceBagList);
				retMsgData.putMessageItem(InstActivityOpenServiceBag.class.getSimpleName(), openServiceBagMsgData.getMsgData());
			}
//		}
		
		//组织玩家等级礼包活动实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instActivityLevelBagMap.size() == 0) {
			//不处理
		} else {*/
			List<InstActivityLevelBag> levelBagList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instActivityLevelBagMap.size() == 0){
				levelBagList = getInstActivityLevelBagDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstActivityLevelBag obj : levelBagList) {
					playerMemObj.instActivityLevelBagMap.put(obj.getId(), obj);
				}
			} else {
				levelBagList = getInstActivityLevelBagDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(levelBagList.size() > 0){
				MessageData levelBagMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstActivityLevelBagDAL(levelBagMsgData, levelBagList);
				retMsgData.putMessageItem(InstActivityLevelBag.class.getSimpleName(), levelBagMsgData.getMsgData());
			}
//		}
		
		//组织活动字典数据
//		List<DictActivity> dictActivityList = getDictActivityDAL().getList("", 0);
		List<SysActivity> dictActivityList = DictList.sysActivityList;
		if(dictActivityList.size() > 0){
			MessageData activityMsgData = new MessageData();
			OrgFrontMsgUtil.orgDictActivityDAL(activityMsgData, dictActivityList);
			retMsgData.putMessageItem(SysActivity.class.getSimpleName(), activityMsgData.getMsgData());
		}
		
		//组织玩家签到活动实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instActivitySignInMap.size() == 0) {
			//不处理
		} else {*/
			List<InstActivitySignIn> signInList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instActivitySignInMap.size() == 0){
				signInList = getInstActivitySignInDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstActivitySignIn obj : signInList) {
					playerMemObj.instActivitySignInMap.put(obj.getId(), obj);
				}
			} else {
				signInList = getInstActivitySignInDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(signInList.size() > 0){
				MessageData SignInMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstActivitySignInDAL(SignInMsgData, signInList);
				retMsgData.putMessageItem(InstActivitySignIn.class.getSimpleName(), SignInMsgData.getMsgData());
			}
//		}
		
		//组织玩家领奖中心活动实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerAwardMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerAward> awardList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerAwardMap.size() == 0){
				awardList = getInstPlayerAwardDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerAward obj : awardList) {
					playerMemObj.instPlayerAwardMap.put(obj.getId(), obj);
				}
			} else {
				awardList = getInstPlayerAwardDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(awardList.size() > 0){
				MessageData awardMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerAwardDAL(awardMsgData, awardList);
				retMsgData.putMessageItem(InstPlayerAward.class.getSimpleName(), awardMsgData.getMsgData());
			}
//		}
		
		//组织玩家竞技场实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerArenaMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerArena> arenaList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerArenaMap.size() == 0){
				arenaList = getInstPlayerArenaDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerArena obj : arenaList) {
					playerMemObj.instPlayerArenaMap.put(obj.getId(), obj);
				}
			} else {
				arenaList = getInstPlayerArenaDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(arenaList.size() > 0){
				MessageData arenaMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerArenaDAL(arenaMsgData, arenaList);
				retMsgData.putMessageItem(InstPlayerArena.class.getSimpleName(), arenaMsgData.getMsgData());
			}
//		}
		
		//组织玩家竞技场兑换实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerArenaConvertMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerArenaConvert> arenaConvertList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerArenaConvertMap.size() == 0){
				arenaConvertList = getInstPlayerArenaConvertDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerArenaConvert obj : arenaConvertList) {
					playerMemObj.instPlayerArenaConvertMap.put(obj.getId(), obj);
				}
			} else {
				arenaConvertList = getInstPlayerArenaConvertDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(arenaConvertList.size() > 0){
				MessageData arenaConvertMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerArenaConvertDAL(arenaConvertMsgData, arenaConvertList);
				retMsgData.putMessageItem(InstPlayerArenaConvert.class.getSimpleName(), arenaConvertMsgData.getMsgData());
			}
//		}
		
		//玩家功法法宝实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerMagicMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerMagic> magicList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerMagicMap.size() == 0){
				magicList = getInstPlayerMagicDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerMagic obj : magicList) {
					playerMemObj.instPlayerMagicMap.put(obj.getId(), obj);
				}
			} else {
				magicList = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(magicList.size() > 0){
				MessageData magicMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerMagicDAL(magicMsgData, magicList);
				retMsgData.putMessageItem(InstPlayerMagic.class.getSimpleName(), magicMsgData.getMsgData());
			}
//		}
		
		//玩家日常任务实例表 [*****情况特殊,不用new/old做是否查询的逻辑处理*****]
		List<InstPlayerDailyTask> dailyTaskList = Lists.newArrayList();
		if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerDailyTaskMap.size() == 0){
			dailyTaskList = getInstPlayerDailyTaskDAL().getList(" instPlayerId = " + instPlayerId, 0);
			for (InstPlayerDailyTask obj : dailyTaskList) {
				playerMemObj.instPlayerDailyTaskMap.put(obj.getId(), obj);
			}
		} else {
			dailyTaskList = getInstPlayerDailyTaskDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		}
		if(dailyTaskList.size() > 0){
			MessageData dailyTaskMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerDailyTaskDAL(dailyTaskMsgData, dailyTaskList);
			retMsgData.putMessageItem(InstPlayerDailyTask.class.getSimpleName(), dailyTaskMsgData.getMsgData());
		}
		
		//玩家成就实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerAchievementMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerAchievement> achievementList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerAchievementMap.size() == 0){
				achievementList = getInstPlayerAchievementDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerAchievement obj : achievementList) {
					playerMemObj.instPlayerAchievementMap.put(obj.getId(), obj);
				}
			} else {
				achievementList = getInstPlayerAchievementDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(achievementList.size() > 0){
				MessageData achievementMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerAchievementDAL(achievementMsgData, achievementList);
				retMsgData.putMessageItem(InstPlayerAchievement.class.getSimpleName(), achievementMsgData.getMsgData());
			}
//		}
		
		//玩家成就计数实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerAchievementValueMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerAchievementValue> achievementValueList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerAchievementValueMap.size() == 0){
				achievementValueList = getInstPlayerAchievementValueDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerAchievementValue obj : achievementValueList) {
					playerMemObj.instPlayerAchievementValueMap.put(obj.getId(), obj);
				}
			} else {
				achievementValueList = getInstPlayerAchievementValueDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(achievementValueList.size() > 0){
				MessageData achievementValueMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerAchievementValueDAL(achievementValueMsgData, achievementValueList);
				retMsgData.putMessageItem(InstPlayerAchievementValue.class.getSimpleName(), achievementValueMsgData.getMsgData());
			}
//		}
		
		//玩家美人实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerBeautyCardMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerBeautyCard> beautyCardList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerBeautyCardMap.size() == 0){
				beautyCardList = getInstPlayerBeautyCardDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerBeautyCard obj : beautyCardList) {
					playerMemObj.instPlayerBeautyCardMap.put(obj.getId(), obj);
				}
			} else {
				beautyCardList = getInstPlayerBeautyCardDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(beautyCardList.size() > 0){
				MessageData beautyCardMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerBeautyCardDAL(beautyCardMsgData, beautyCardList);
				retMsgData.putMessageItem(InstPlayerBeautyCard.class.getSimpleName(), beautyCardMsgData.getMsgData());
			}
//		}
		
		//组织玩家邮件实例数据
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerMailMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerMail> mailList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerMailMap.size() == 0){
				mailList = getInstPlayerMailDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerMail obj : mailList) {
					playerMemObj.instPlayerMailMap.put(obj.getId(), obj);
				}
			} else {
				mailList = getInstPlayerMailDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(mailList.size() > 0){
				if(DateUtil.dayDiff(DateUtil.getCurrTime(), mailList.get(mailList.size() - 1).getInsertTime()) == 0){
					retMsgData.putIntItem("yj", 1);
				}
				MessageData mailMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerMailDAL(mailMsgData, mailList);
				retMsgData.putMessageItem(InstPlayerMail.class.getSimpleName(), mailMsgData.getMsgData());
			}
//		}
		
		//玩家整点抢实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerGrabTheHourMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerGrabTheHour> grabTheHourList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerGrabTheHourMap.size() == 0){
				grabTheHourList = getInstPlayerGrabTheHourDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerGrabTheHour obj : grabTheHourList) {
					playerMemObj.instPlayerGrabTheHourMap.put(obj.getId(), obj);
				}
			} else {
				grabTheHourList = getInstPlayerGrabTheHourDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(grabTheHourList.size() > 0){
				MessageData grabTheHourMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerGrabTheHourDAL(grabTheHourMsgData, grabTheHourList);
				retMsgData.putMessageItem(InstPlayerGrabTheHour.class.getSimpleName(), grabTheHourMsgData.getMsgData());
			}
//		}
		
		//玩家特卖会实例表
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPrivateSaleMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerPrivateSale> privateSaleList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerPrivateSaleMap.size() == 0){
				privateSaleList = getInstPlayerPrivateSaleDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerPrivateSale obj : privateSaleList) {
					playerMemObj.instPlayerPrivateSaleMap.put(obj.getId(), obj);
				}
			} else {
				privateSaleList = getInstPlayerPrivateSaleDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(privateSaleList.size() > 0){
				MessageData privateSaleMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerPrivateSaleDAL(privateSaleMsgData, privateSaleList);
				retMsgData.putMessageItem(InstPlayerPrivateSale.class.getSimpleName(), privateSaleMsgData.getMsgData());
			}
//		}
		
		//每日特惠是否购买标识
		List<InstActivity> instActivityList = getInstActivityDAL().getList("instPlayerId = " + instPlayer.getId() + " and activityId = " + StaticActivity.dailyDeals, instPlayer.getId());
		if (instActivityList.size() <= 0) {
			retMsgData.putStringItem("DailyDealState", "0");
		} else {
			InstActivity instActivity = instActivityList.get(0);
			if (instActivity.getActivityTime().equals(DateUtil.getYmdDate())) {
				retMsgData.putStringItem("DailyDealState", "1");
			} else {
				retMsgData.putStringItem("DailyDealState", "0");
			}
		}
		
		//联盟成员（自己）
		/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPrivateSaleMap.size() == 0) {
			//不处理
		} else {*/
			List<InstUnionMember> unionMemberList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instUnionMemberMap.size() == 0){
				unionMemberList = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstUnionMember obj : unionMemberList) {
					playerMemObj.instUnionMemberMap.put(obj.getId(), obj);
				}
			} else {
				unionMemberList = getInstUnionMemberDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(unionMemberList.size() > 0){
				MessageData unionMemberMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstUnionMemberDAL(unionMemberMsgData, unionMemberList);
				retMsgData.putMessageItem(InstUnionMember.class.getSimpleName(), unionMemberMsgData.getMsgData());
			}
//				}
			
			//联盟申请（自己）
			/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPrivateSaleMap.size() == 0) {
			//不处理
		} else {*/
			List<InstUnionApply> unionApplyList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instUnionApplyMap.size() == 0){
				unionApplyList = getInstUnionApplyDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstUnionApply obj : unionApplyList) {
					playerMemObj.instUnionApplyMap.put(obj.getId(), obj);
				}
			} else {
				unionApplyList = getInstUnionApplyDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(unionApplyList.size() > 0){
				MessageData unionApplyMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstUnionApplyDAL(unionApplyMsgData, unionApplyList);
				retMsgData.putMessageItem(InstUnionApply.class.getSimpleName(), unionApplyMsgData.getMsgData());
			}
//				}
			
			
			//联盟保险箱（自己）
			/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPrivateSaleMap.size() == 0) {
			//不处理
		} else {*/
			List<InstUnionBox> unionBoxList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instUnionBoxMap.size() == 0){
				unionBoxList = getInstUnionBoxDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstUnionBox obj : unionBoxList) {
					playerMemObj.instUnionBoxMap.put(obj.getId(), obj);
				}
			} else {
				unionBoxList = getInstUnionBoxDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(unionBoxList.size() > 0){
				MessageData unionBoxMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstUnionBoxDAL(unionBoxMsgData, unionBoxList);
				retMsgData.putMessageItem(InstUnionBox.class.getSimpleName(), unionBoxMsgData.getMsgData());
			}
//				}
			
			//联盟保险箱（自己）
			/*if (DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.status == 1 && playerMemObj.instPlayerPrivateSaleMap.size() == 0) {
			//不处理
		} else {*/
			List<InstPlayerTryToPractice> PlayerTryToPracticeList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerTryToPracticeMap.size() == 0){
				PlayerTryToPracticeList = getInstPlayerTryToPracticeDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerTryToPractice obj : PlayerTryToPracticeList) {
					playerMemObj.instPlayerTryToPracticeMap.put(obj.getId(), obj);
				}
			} else {
				PlayerTryToPracticeList = getInstPlayerTryToPracticeDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(PlayerTryToPracticeList.size() > 0){
				MessageData PlayerTryToPracticeMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerTryToPracticeDAL(PlayerTryToPracticeMsgData, PlayerTryToPracticeList);
				retMsgData.putMessageItem(InstPlayerTryToPractice.class.getSimpleName(), PlayerTryToPracticeMsgData.getMsgData());
			}
//				}
			
		//新异火实力表（自己的）
		List<InstPlayerYFire> yFireList = Lists.newArrayList();
		if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerYFireMap.size() == 0){
			yFireList = getInstPlayerYFireDAL().getList(" instPlayerId = " + instPlayerId, 0);
			for (InstPlayerYFire obj : yFireList) {
				playerMemObj.instPlayerYFireMap.put(obj.getId(), obj);
			}
		} else {
			yFireList = getInstPlayerYFireDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		}
		if(yFireList.size() > 0){
			MessageData yfireMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerYFireDAL(yfireMsgData, yFireList);
			retMsgData.putMessageItem(InstPlayerYFire.class.getSimpleName(), yfireMsgData.getMsgData());
		}
		
		//斗魂
			List<InstPlayerFightSoul> PlayerFightSoulList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerFightSoulMap.size() == 0){
				PlayerFightSoulList = getInstPlayerFightSoulDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (int i = 0; i < PlayerFightSoulList.size(); i++) {
					InstPlayerFightSoul obj = PlayerFightSoulList.get(i);
					if (obj.getInstCardId() != 0) {
						if (getInstPlayerCardDAL().getModel(obj.getInstCardId(), 0) == null) {
							obj.setInstCardId(0);
							obj.setPosition(0);
							getInstPlayerFightSoulDAL().update(obj, 0);
						}
					}
				}
				PlayerFightSoulList = getInstPlayerFightSoulDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerFightSoul obj : PlayerFightSoulList) {
					playerMemObj.instPlayerFightSoulMap.put(obj.getId(), obj);
				}
			} else {
				PlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
				for (int i = 0; i < PlayerFightSoulList.size(); i++) {
					InstPlayerFightSoul obj = PlayerFightSoulList.get(i);
					if (obj.getInstCardId() != 0) {
						if (getInstPlayerCardDAL().getModel(obj.getInstCardId(), 0) == null) {
							obj.setInstCardId(0);
							obj.setPosition(0);
							getInstPlayerFightSoulDAL().update(obj, 0);
						}
					}
				}
				PlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(PlayerFightSoulList.size() > 0){
				MessageData PlayerFightSoulMsgData = new MessageData();
				OrgFrontMsgUtil.orgPlayerFightSoulDAL(PlayerFightSoulMsgData, PlayerFightSoulList);
				retMsgData.putMessageItem(InstPlayerFightSoul.class.getSimpleName(), PlayerFightSoulMsgData.getMsgData());
			}
			
			List<InstPlayerFightSoulHuntRule> PlayerFightSoulHuntRuleList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerFightSoulHuntRuleMap.size() == 0){
				PlayerFightSoulHuntRuleList = getInstPlayerFightSoulHuntRuleDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerFightSoulHuntRule obj : PlayerFightSoulHuntRuleList) {
					playerMemObj.instPlayerFightSoulHuntRuleMap.put(obj.getId(), obj);
				}
			} else {
				PlayerFightSoulHuntRuleList = getInstPlayerFightSoulHuntRuleDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(PlayerFightSoulHuntRuleList.size() > 0){
				MessageData PlayerFightSoulHuntRuleMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerFightSoulHuntRuleDAL(PlayerFightSoulHuntRuleMsgData, PlayerFightSoulHuntRuleList);
				retMsgData.putMessageItem(InstPlayerFightSoulHuntRule.class.getSimpleName(), PlayerFightSoulHuntRuleMsgData.getMsgData());
			}
			
			//翅膀
			List<InstPlayerWing> PlayerWingList = Lists.newArrayList();
			if(DALFather.isUseCach() == true && playerMemObj != null && playerMemObj.instPlayerWingMap.size() == 0){
				PlayerWingList = getInstPlayerWingDAL().getList(" instPlayerId = " + instPlayerId, 0);
				for (InstPlayerWing obj : PlayerWingList) {
					playerMemObj.instPlayerWingMap.put(obj.getId(), obj);
				}
			} else {
				PlayerWingList = getInstPlayerWingDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			}
			if(PlayerWingList.size() > 0){
				MessageData PlayerWingMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerWingDAL(PlayerWingMsgData, PlayerWingList);
				retMsgData.putMessageItem(InstPlayerWing.class.getSimpleName(), PlayerWingMsgData.getMsgData());
			}
			
		
		return retMsgData;
	}
	
	/**
	  * 组织玩家阵容数据(用于查看阵容)
	  * @author hzw
	  * @date 2014-6-23上午10:52:13
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	public static MessageData retCardMsgData(int instPlayerId) throws Exception{
		MessageData retMsgData = new MessageData();
		
		//组织玩家卡牌实例数据
		List<InstPlayerCard> cardList = getInstPlayerCardDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if(cardList.size() > 0){
			MessageData cardMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerCard(cardMsgData, cardList);
			retMsgData.putMessageItem(InstPlayerCard.class.getSimpleName(), cardMsgData.getMsgData());
		}

		//组织玩家卡牌阵型实例数据
		List<InstPlayerFormation> formationList = getInstPlayerFormationDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if(formationList.size() > 0){
			MessageData formationMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerFormation(formationMsgData, formationList);
			retMsgData.putMessageItem(InstPlayerFormation.class.getSimpleName(), formationMsgData.getMsgData());
		}

		//组织玩家卡牌命宫实例数据
		List<InstPlayerConstell> constellList = getInstPlayerConstellDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
		if(constellList.size() > 0){
			MessageData constellMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerConstellDAL(constellMsgData, constellList);
			retMsgData.putMessageItem(InstPlayerConstell.class.getSimpleName(), constellMsgData.getMsgData());
		}
		
		//玩家成就实例表
		List<InstPlayerAchievement> achievementList = getInstPlayerAchievementDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if(achievementList.size() > 0){
			MessageData achievementMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerAchievementDAL(achievementMsgData, achievementList);
			retMsgData.putMessageItem(InstPlayerAchievement.class.getSimpleName(), achievementMsgData.getMsgData());
		}
		
		//玩家成就计数实例表
		List<InstPlayerAchievementValue> achievementValueList = getInstPlayerAchievementValueDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if(achievementValueList.size() > 0){
			MessageData achievementValueMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerAchievementValueDAL(achievementValueMsgData, achievementValueList);
			retMsgData.putMessageItem(InstPlayerAchievementValue.class.getSimpleName(), achievementValueMsgData.getMsgData());
		}
		
		//爬塔
		List<InstPlayerPagoda> pagodaList = getInstPlayerPagodaDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if(pagodaList.size() > 0){
			MessageData pagodaMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerPagodaDAL(pagodaMsgData, pagodaList);
			retMsgData.putMessageItem(InstPlayerPagoda.class.getSimpleName(), pagodaMsgData.getMsgData());
		}
		
		//抢夺
		List<InstPlayerLoot> lootList = getInstPlayerLootDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if(lootList.size() > 0){
			MessageData lootMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerLootDAL(lootMsgData, lootList);
			retMsgData.putMessageItem(InstPlayerLoot.class.getSimpleName(), lootMsgData.getMsgData());
		}
		
		//活动
		List<InstActivity> activityList = getInstActivityDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if(activityList.size() > 0){
			MessageData activityMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstActivityDAL(activityMsgData, activityList);
			retMsgData.putMessageItem(InstActivity.class.getSimpleName(), activityMsgData.getMsgData());
		}

		//每日任务
		List<InstPlayerDailyTask> dailyTaskList = getInstPlayerDailyTaskDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if(dailyTaskList.size() > 0){
			MessageData dailyTaskMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstPlayerDailyTaskDAL(dailyTaskMsgData, dailyTaskList);
			retMsgData.putMessageItem(InstPlayerDailyTask.class.getSimpleName(), dailyTaskMsgData.getMsgData());
		}
		
		//组织玩家在线奖励活动实例数据
		List<InstActivityOnlineRewards> onlineRewardsList = getInstActivityOnlineRewardsDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if(onlineRewardsList.size() > 0){
			MessageData onlineRewardsMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstActivityOnlineRewardsDAL(onlineRewardsMsgData, onlineRewardsList);
			retMsgData.putMessageItem(InstActivityOnlineRewards.class.getSimpleName(), onlineRewardsMsgData.getMsgData());
		}
		
		//组织玩家开服礼包活动实例数据
		List<InstActivityOpenServiceBag> openServiceBagList = getInstActivityOpenServiceBagDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if(openServiceBagList.size() > 0){
			MessageData openServiceBagMsgData = new MessageData();
			OrgFrontMsgUtil.orgInstActivityOpenServiceBagDAL(openServiceBagMsgData, openServiceBagList);
			retMsgData.putMessageItem(InstActivityOpenServiceBag.class.getSimpleName(), openServiceBagMsgData.getMsgData());
		}
		
		return retMsgData;
	}
	
	/**
	 * 组织同步数据
	 * @author hzw
	 * @date 2014-6-23上午10:53:06
	 * @param operate 具体操作
	 * @param obj 具体对象
	 * @param objId 具体对象的Id
	 * @param result 具体对象的更改记录
	 * @param syncMsgData
	 * @Description
	 */
	public static void orgSyncMsgData(String operate, Object obj, int objId, String result, MessageData syncMsgData){
//		System.out.println(operate + " " + obj.getClass().getSimpleName() + " " + objId + " " + result);
		MessageData tableMsgData = new MessageData();
		tableMsgData.putStringItem("a", obj.getClass().getSimpleName());
		tableMsgData.putStringItem("b", operate);
		tableMsgData.putIntItem("c", objId);
		
		if(!operate.equals(StaticSyncState.delete)){
			if (!result.equals("")) {
				MessageData tableFieldMsgData = orgMsgData(result);
				tableMsgData.putMessageItem(objId+"", tableFieldMsgData.getMsgData());
				syncMsgData.putMessageItem(obj.getClass().getSimpleName() + objId, tableMsgData.getMsgData());
			}
		} else {
			syncMsgData.putMessageItem(obj.getClass().getSimpleName() + objId, tableMsgData.getMsgData());
		}
	}
	
	/**
	 * 带标识的组织消息格式
	 * @author mp
	 * @date 2015-4-20 下午4:36:28
	 * @param operate
	 * @param obj
	 * @param objId
	 * @param result
	 * @param syncMsgData
	 * @param bs
	 * @Description
	 */
	public static void orgSyncMsgData(String operate, Object obj, int objId, String result, MessageData syncMsgData, int bs){
//		System.out.println(operate + " " + obj.getClass().getSimpleName() + " " + objId + " " + result);
		MessageData tableMsgData = new MessageData();
		tableMsgData.putStringItem("a", obj.getClass().getSimpleName());
		tableMsgData.putStringItem("b", operate);
		tableMsgData.putIntItem("c", objId);
		
		if(!operate.equals(StaticSyncState.delete)){
			if (!result.equals("")) {
				MessageData tableFieldMsgData = orgMsgData(result);
				tableMsgData.putMessageItem(objId+"", tableFieldMsgData.getMsgData());
				syncMsgData.putMessageItem(obj.getClass().getSimpleName() + objId + bs, tableMsgData.getMsgData());
			}
		} else {
			syncMsgData.putMessageItem(obj.getClass().getSimpleName() + objId + bs, tableMsgData.getMsgData());
		}
	}
	
	/**
	 * 组织卡牌属性同步数据
	 * @author mp
	 * @date 2014-6-26 下午3:57:25
	 * @param operate
	 * @param obj
	 * @param objId
	 * @param cardPropMsgData
	 * @param syncMsgData
	 * @Description
	 */
	public static void orgSyncMsgData(String operate, String className, int objId, MessageData cardPropMsgData, MessageData syncMsgData){
		MessageData tableMsgData = new MessageData();
		tableMsgData.putStringItem("a", className);
		tableMsgData.putStringItem("b", operate);
		tableMsgData.putIntItem("c", objId);
		
		if(!operate.equals(StaticSyncState.delete)){
			tableMsgData.putMessageItem(objId+"", cardPropMsgData.getMsgData());
		}
		
		syncMsgData.putMessageItem(className + objId, tableMsgData.getMsgData());
	}
	
	/**
	 * 组织单个卡牌消息对象
	 * @author mp
	 * @date 2014-6-26 下午4:00:23
	 * @param getCardProp
	 * @return
	 * @Description
	 */
	public static MessageData orgCardPropMsgData(CardPropObj getCardProp) {
		MessageData msgData = new MessageData();
		msgData.putIntItem("1", getCardProp.getBlood());//血量
		msgData.putIntItem("2", getCardProp.getwAttack());//物攻
		msgData.putIntItem("3", getCardProp.getfAttack());//法功
		msgData.putIntItem("4", getCardProp.getwDefense());//物防
		msgData.putIntItem("5", getCardProp.getfDefense());//法防
		msgData.putIntItem("6", getCardProp.getwDefensePer());//物防系数
		msgData.putIntItem("7", getCardProp.getfDefensePer());//法防系数
		msgData.putIntItem("8", getCardProp.getDodge());//闪避
		msgData.putIntItem("9", getCardProp.getCrit());//暴击
		msgData.putIntItem("10", getCardProp.getHit());//命中
		msgData.putIntItem("11", getCardProp.getFlex());//韧性
		msgData.putIntItem("12", getCardProp.getDodgePer());//闪避系数
		msgData.putIntItem("13", getCardProp.getCritPer());//暴击系数
		msgData.putIntItem("14", getCardProp.getHitPer());//命中系数
		msgData.putIntItem("15", getCardProp.getFlexPer());//韧性系数
		return msgData;
	}
	
	/**
	 * 组织数据
	 * @author mp
	 * @date 2014-6-25 上午11:41:35
	 * @param result
	 * @return
	 * @Description
	 */
	private static MessageData orgMsgData(String result){
		//6:int:10#
		MessageData msgData = new MessageData();
		String [] dataArray = result.split("#");
		for (String data : dataArray) {
			String [] detailDataArray = data.split("\\*");
			String id = detailDataArray[0];
			String type = detailDataArray[1];
			String value = "";
			if (type.equals("String")) {
				if (detailDataArray.length > 2) {
					value = detailDataArray[2];
				}
			}else{
				value = detailDataArray[2];
			}
			
//			if (type.equals("int")) {
//				msgData.putIntItem(id, ConvertUtil.toInt(value));
//			} else if (type.equals("String")) {
//				msgData.putStringItem(id, value);
//			} else if (type.equals("float")) {
//				msgData.putFloatItem(id, ConvertUtil.toFloat(value));
//			} else if (type.equals("long")) {
//				msgData.putLongItem(id, ConvertUtil.toLong(value));
//			} else if (type.equals("double")) {
//				msgData.putDoubleItem(id, ConvertUtil.toDouble(value));
//			}
			
			if (type.equals("int")) {
				msgData.putIntItem(id, ConvertUtil.toInt(value), msgData);
			} else if (type.equals("String")) {
				msgData.putStringItem(id, value, msgData);
			} else if (type.equals("float")) {
				msgData.putFloatItem(id, ConvertUtil.toFloat(value), msgData);
			} else if (type.equals("long")) {
				msgData.putLongItem(id, ConvertUtil.toLong(value), msgData);
			} else if (type.equals("double")) {
				msgData.putDoubleItem(id, ConvertUtil.toDouble(value), msgData);
			}
		}
		return msgData;
	}

}
