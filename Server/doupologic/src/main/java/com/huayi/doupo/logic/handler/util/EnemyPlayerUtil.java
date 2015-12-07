package com.huayi.doupo.logic.handler.util;

import com.google.common.collect.Lists;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.*;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.logic.util.MessageData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyPlayerUtil extends DALFactory{
	
	
	/**
	  * 组织玩家卡牌实例数据
	  * @author hzw
	  * @date 2014-6-19下午4:58:56
	  * @param msgMap
	  * @param channelId
	  * @throws Exception
	  * @Description
	  */
	public static void orgInstPlayerCard(MessageData cardMsgData, List<InstPlayerCard> cardList, Map<Integer, Integer> cardMap){
		for(InstPlayerCard instPlayerCard : cardList){
			MessageData msgData = orgInstPlayerCard(instPlayerCard);
			cardMsgData.putMessageItem(instPlayerCard.getId()+"", msgData.getMsgData());
			cardMap.put(instPlayerCard.getId(), instPlayerCard.getId());
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
		msgData.putIntItem("3", instPlayerCard.getCardId());
		msgData.putIntItem("4", instPlayerCard.getQualityId());
		msgData.putIntItem("5", instPlayerCard.getStarLevelId());
		msgData.putIntItem("6", instPlayerCard.getTitleDetailId());
		msgData.putIntItem("8", instPlayerCard.getExp());
		msgData.putIntItem("9", instPlayerCard.getLevel());
		msgData.putIntItem("10", instPlayerCard.getInTeam());
		msgData.putIntItem("11", instPlayerCard.getUseTalentValue());
		msgData.putIntItem("12", instPlayerCard.getInstPlayerKungFuId());
		msgData.putStringItem("13", instPlayerCard.getInstPlayerConstells());

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
		msgData.putIntItem("3", instPlayerPartner.getInstCardId());
		msgData.putIntItem("4", instPlayerPartner.getCardId());
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
		msgData.putIntItem("3", instPlayerFormation.getInstCardId());
		msgData.putIntItem("4", instPlayerFormation.getType());
		msgData.putIntItem("5", instPlayerFormation.getPosition());
		msgData.putIntItem("6", instPlayerFormation.getCardId());
		return msgData;
	}
	
	/**
     *  组织玩家卡牌阵容实例数据
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
	  *  组织玩家卡牌阵容实例数据
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
		msgData.putIntItem("3", instPlayerLineup.getInstPlayerFormationId());
		msgData.putIntItem("4", instPlayerLineup.getEquipTypeId());
		msgData.putIntItem("5", instPlayerLineup.getInstPlayerEquipId());

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
	public static void orgInstPlayerEquip(MessageData equipMsgData, List<InstPlayerEquip> equipList, Map<Integer, Integer> equipMap){
		for(InstPlayerEquip instPlayerEquip : equipList){
			MessageData msgData = orgInstPlayerEquip(instPlayerEquip);
			equipMsgData.putMessageItem(instPlayerEquip.getId()+"", msgData.getMsgData());
			equipMap.put(instPlayerEquip.getId(), instPlayerEquip.getId());
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
		msgData.putIntItem("3", instPlayerEquip.getEquipTypeId());
		msgData.putIntItem("4", instPlayerEquip.getEquipId());
		msgData.putIntItem("5", instPlayerEquip.getLevel());
		msgData.putIntItem("8", instPlayerEquip.getEquipAdvanceId());
		return msgData;
	}
	
	/**
	 * 组织装备宝石
	 * @author mp
	 * @date 2014-8-11 下午2:41:51
	 * @param bagExpandMsgData
	 * @param bagExpandList
	 * @Description
	 */
	public static void orgInstEquipGem(MessageData equipGemMsgData, List<InstEquipGem> equipGemList, Map<Integer, Integer> equipMap){
		for(InstEquipGem instEquipGem : equipGemList){
			if(equipMap.containsKey(instEquipGem.getInstPlayerEquipId())){
				MessageData msgData = orgInstEquipGem(instEquipGem);
				equipGemMsgData.putMessageItem(instEquipGem.getId()+"", msgData.getMsgData());
			}
		}
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
		msgData.putIntItem("3", instEquipGem.getInstPlayerEquipId());
		msgData.putIntItem("4", instEquipGem.getThingId());
		msgData.putIntItem("5", instEquipGem.getPosition());
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
	public static void orgInstPlayerWash(MessageData washMsgData, List<InstPlayerWash> washList, Map<Integer, Integer> equipMap){
		for(InstPlayerWash instPlayerWash : washList){
			if(equipMap.containsKey(instPlayerWash.getInstPlayerEquipId())){
				MessageData msgData = orgInstPlayerWash(instPlayerWash);
				washMsgData.putMessageItem(instPlayerWash.getId()+"", msgData.getMsgData());
			}
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
		msgData.putIntItem("3", instPlayerWash.getInstPlayerEquipId());
		msgData.putIntItem("4", instPlayerWash.getFightPropId());
		msgData.putIntItem("5", instPlayerWash.getEquipWashId());


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
		msgData.putStringItem("8", instPlayerKungFu.getFightPropOne());
		msgData.putStringItem("9", instPlayerKungFu.getFightPropTwo());
		msgData.putStringItem("10", instPlayerKungFu.getFightPropThree());
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
		msgData.putIntItem("3", instPlayerFire.getFireId());
		msgData.putIntItem("4", instPlayerFire.getLevel());
		msgData.putStringItem("6", instPlayerFire.getBySkillIds());

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
	public static void orgInstPlayerConstellDAL(MessageData constellMsgData, List<InstPlayerConstell> constellList, Map<Integer, Integer> cardMap){
		for(InstPlayerConstell instPlayerConstell : constellList){
			if(cardMap.containsKey(instPlayerConstell.getInstCardId())){
				MessageData msgData = orgInstPlayerConstellDAL(instPlayerConstell);
				constellMsgData.putMessageItem(instPlayerConstell.getId()+"", msgData.getMsgData());
			}
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
		msgData.putIntItem("4", instPlayerConstell.getConstellId());
		msgData.putStringItem("5", instPlayerConstell.getPills());

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
	public static void orgInstPlayerTrainDAL(MessageData trainMsgData, List<InstPlayerTrain> trainList, Map<Integer, Integer> cardMap){
		for(InstPlayerTrain instPlayerTrain : trainList){
			if(cardMap.containsKey(instPlayerTrain.getInstPlayerCardId())){
				MessageData msgData = orgInstPlayerTrainDAL(instPlayerTrain);
				trainMsgData.putMessageItem(instPlayerTrain.getId()+"", msgData.getMsgData());
			}
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
		msgData.putIntItem("3", instPlayerTrain.getInstPlayerCardId());
		msgData.putIntItem("4", instPlayerTrain.getFightPropId());
		msgData.putIntItem("5", instPlayerTrain.getFightPropValue());

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
		msgData.putIntItem("4", instPlayerManualSkill.getManualSkillId());
		msgData.putIntItem("5", instPlayerManualSkill.getLevel());

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
		msgData.putIntItem("3", instPlayerManualSkillLine.getPosition1());
		msgData.putIntItem("4", instPlayerManualSkillLine.getPosition2());
		msgData.putIntItem("5", instPlayerManualSkillLine.getPosition3());
		msgData.putIntItem("6", instPlayerManualSkillLine.getPosition4());

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
		msgData.putIntItem("3", instPlayerMagic.getMagicId());
		msgData.putIntItem("4", instPlayerMagic.getMagicType());
		msgData.putIntItem("5", instPlayerMagic.getQuality());
		msgData.putIntItem("6", instPlayerMagic.getMagicLevelId());
		msgData.putIntItem("8", instPlayerMagic.getInstCardId());

		return msgData;
	}

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
		msgData.putIntItem("4", instPlayer.getLevel());
		
		return msgData;
	}
	
	public static void orgInstPlayerBeautyCardDAL(MessageData beautyCardMsgData, List<InstPlayerBeautyCard> beautyCardList){
		for(InstPlayerBeautyCard instPlayerBeautyCard : beautyCardList){
			MessageData msgData = orgInstPlayerBeautyCardDAL(instPlayerBeautyCard);
			beautyCardMsgData.putMessageItem(instPlayerBeautyCard.getId()+"", msgData.getMsgData());
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
	public static MessageData orgInstPlayerBeautyCardDAL(InstPlayerBeautyCard instPlayerBeautyCard){
		MessageData msgData = new MessageData();
		msgData.putIntItem("3", instPlayerBeautyCard.getBeautyCardId());
		msgData.putIntItem("4", instPlayerBeautyCard.getBeautyCardExpId());

		return msgData;
	}
	
	/**
	 * 返回敌方玩家战斗相关数据
	 * @author hzw
	 * @date 2014-10-30下午3:08:19
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData retEnemyPlayerInfoMsgData(MessageData retMsgData, int instPlayerId) throws Exception{
			InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		
			//组织玩家实例数据
			if(instPlayer != null){
				MessageData playerMsgData = EnemyPlayerUtil.orgInstPlayer(instPlayer);
				retMsgData.putMessageItem(instPlayer.getClass().getSimpleName(), playerMsgData.getMsgData());
			}
			
			//翅膀实例数据
			List<InstPlayerWing> PlayerWingList = getInstPlayerWingDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			if(PlayerWingList.size() > 0){
				MessageData PlayerWingMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerWingDAL(PlayerWingMsgData, PlayerWingList);
				retMsgData.putMessageItem(InstPlayerWing.class.getSimpleName(), PlayerWingMsgData.getMsgData());
			}
			
			//斗魂实例数据
			List<InstPlayerFightSoul> PlayerFightSoulList = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			if(PlayerFightSoulList.size() > 0){
				MessageData PlayerFightSoulMsgData = new MessageData();
				OrgFrontMsgUtil.orgPlayerFightSoulDAL(PlayerFightSoulMsgData, PlayerFightSoulList);
				retMsgData.putMessageItem(InstPlayerFightSoul.class.getSimpleName(), PlayerFightSoulMsgData.getMsgData());
			}
			
			//斗魂-猎魂规则实例数据
			List<InstPlayerFightSoulHuntRule> PlayerFightSoulHuntRuleList = getInstPlayerFightSoulHuntRuleDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			if(PlayerFightSoulHuntRuleList.size() > 0){
				MessageData PlayerFightSoulHuntRuleMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerFightSoulHuntRuleDAL(PlayerFightSoulHuntRuleMsgData, PlayerFightSoulHuntRuleList);
				retMsgData.putMessageItem(InstPlayerFightSoulHuntRule.class.getSimpleName(), PlayerFightSoulHuntRuleMsgData.getMsgData());
			}
		
			//组织玩家卡牌实例数据
			Map<Integer, Integer> cardMap = new HashMap<Integer, Integer>();
			List<InstPlayerCard> cardList = getInstPlayerCardDAL().getList(" instPlayerId = " + instPlayerId + " and inTeam = 1", instPlayerId);
			if(cardList.size() > 0){
				MessageData cardMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerCard(cardMsgData, cardList, cardMap);
				retMsgData.putMessageItem(InstPlayerCard.class.getSimpleName(), cardMsgData.getMsgData());
			}
			
			//组织玩家小伙伴实例数据
			List<InstPlayerPartner> partnerList = getInstPlayerPartnerDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if(partnerList.size() > 0){
				MessageData partnerMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerPartner(partnerMsgData, partnerList);
				retMsgData.putMessageItem(InstPlayerPartner.class.getSimpleName(), partnerMsgData.getMsgData());
			}
			
			//组织玩家卡牌阵型实例数据
			List<InstPlayerFormation> formationList = getInstPlayerFormationDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if(formationList.size() > 0){
				MessageData formationMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerFormation(formationMsgData, formationList);
				retMsgData.putMessageItem(InstPlayerFormation.class.getSimpleName(), formationMsgData.getMsgData());
			}
			
			//组织玩家阵容实例数据
			List<InstPlayerLineup> lineupList = getInstPlayerLineupDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if(lineupList.size() > 0){
				MessageData lineupMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerLineup(lineupMsgData, lineupList);
				retMsgData.putMessageItem(InstPlayerLineup.class.getSimpleName(), lineupMsgData.getMsgData());
			}
			
			//组织玩家装备实例数据 
			Map<Integer, Integer> equipMap = new HashMap<Integer, Integer>();
			List<InstPlayerEquip> equipList = getInstPlayerEquipDAL().getList(" instPlayerId = " + instPlayerId + " and instCardId > 0", instPlayerId);
			if(equipList.size() > 0){
				MessageData equipMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerEquip(equipMsgData, equipList, equipMap);
				retMsgData.putMessageItem(InstPlayerEquip.class.getSimpleName(), equipMsgData.getMsgData());
			}
			
			//组织玩家洗练实例数据
			List<InstPlayerWash> washList = getInstPlayerWashDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if(washList.size() > 0){
				MessageData washMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerWash(washMsgData, washList, equipMap);
				retMsgData.putMessageItem(InstPlayerWash.class.getSimpleName(), washMsgData.getMsgData());
			}
			
			/*//组织玩家功法实例数据
			List<InstPlayerKungFu> kungFuList = getInstPlayerKungFuDAL().getList(" instPlayerId = " + instPlayerId + " and instPlayerCardId > 0 ", instPlayerId);
			if(kungFuList.size() > 0){
				MessageData kungFuMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerKungFu(kungFuMsgData, kungFuList);
				retMsgData.putMessageItem(InstPlayerKungFu.class.getSimpleName(), kungFuMsgData.getMsgData());
			}*/
			
			//组织玩家卡牌命宫实例数据
			List<InstPlayerConstell> constellList = getInstPlayerConstellDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if(constellList.size() > 0){
				MessageData constellMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerConstellDAL(constellMsgData, constellList, cardMap);
				retMsgData.putMessageItem(InstPlayerConstell.class.getSimpleName(), constellMsgData.getMsgData());
			}
			
			//玩家卡牌修炼实例表
			List<InstPlayerTrain> trainList = getInstPlayerTrainDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			if(trainList.size() > 0){
				MessageData trainMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerTrainDAL(trainMsgData, trainList, cardMap);
				retMsgData.putMessageItem(InstPlayerTrain.class.getSimpleName(), trainMsgData.getMsgData());
			}
			
			//装备宝石
			List<InstEquipGem> equipGemList = getInstEquipGemDAL().getList("instPlayerId = " + instPlayerId + " and thingId > 0", instPlayerId);
			if(equipGemList.size() > 0){
				MessageData equipGemMsgData = new MessageData();
				EnemyPlayerUtil.orgInstEquipGem(equipGemMsgData, equipGemList, equipMap);
				retMsgData.putMessageItem(InstEquipGem.class.getSimpleName(), equipGemMsgData.getMsgData());
			}
			
			//玩家手动技能实例表
			List<InstPlayerManualSkill> manualSkillList = getInstPlayerManualSkillDAL().getList("instPlayerId = " + instPlayerId + " and isUse > 0", instPlayerId);
			if(manualSkillList.size() > 0){
				MessageData manualSkillMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerManualSkillDAL(manualSkillMsgData, manualSkillList);
				retMsgData.putMessageItem(InstPlayerManualSkill.class.getSimpleName(), manualSkillMsgData.getMsgData());
			}
			
			//玩家手动技能排布实例表
			List<InstPlayerManualSkillLine> manualSkillLineList = getInstPlayerManualSkillLineDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
			if(manualSkillLineList.size() > 0){
				MessageData manualSkillLineMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerManualSkillLineDAL(manualSkillLineMsgData, manualSkillLineList);
				retMsgData.putMessageItem(InstPlayerManualSkillLine.class.getSimpleName(), manualSkillLineMsgData.getMsgData());
			}
			
			//组织玩家异火实例数据
			List<InstPlayerFire> fireList = getInstPlayerFireDAL().getList(" instPlayerId = " + instPlayerId + " and isUse = 1", instPlayerId);
			if(fireList.size() > 0){
				MessageData fireMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerFire(fireMsgData, fireList);
				retMsgData.putMessageItem(InstPlayerFire.class.getSimpleName(), fireMsgData.getMsgData());
			}
			
			//玩家功法法宝实例表
			List<InstPlayerMagic> magicList = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			if(magicList.size() > 0){
				MessageData magicMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerMagicDAL(magicMsgData, magicList);
				retMsgData.putMessageItem(InstPlayerMagic.class.getSimpleName(), magicMsgData.getMsgData());
			}
			
			//玩家美人实例表
			List<InstPlayerBeautyCard> beautyCardList = getInstPlayerBeautyCardDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			if(beautyCardList.size() > 0){
				MessageData beautyCardMsgData = new MessageData();
				EnemyPlayerUtil.orgInstPlayerBeautyCardDAL(beautyCardMsgData, beautyCardList);
				retMsgData.putMessageItem(InstPlayerBeautyCard.class.getSimpleName(), beautyCardMsgData.getMsgData());
			}

			//玩家新异火实例数据
			List<InstPlayerYFire> instPlayerYFireList = getInstPlayerYFireDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
			if(instPlayerYFireList.size() > 0){
				MessageData yfireMsgData = new MessageData();
				OrgFrontMsgUtil.orgInstPlayerYFireDAL(yfireMsgData, instPlayerYFireList);
				retMsgData.putMessageItem(InstPlayerYFire.class.getSimpleName(), yfireMsgData.getMsgData());
			}
		
		return retMsgData;
	}
	
	/**
	 * 组织NPC敌方玩家战斗相关数据
	 * @author hzw
	 * @date 2015-2-3下午7:53:31
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static MessageData retEnemyNPCInfoMsgData(MessageData retMsgData, int instPlayerId) throws Exception{
		InstPlayerArenaNPC instPlayerArenaNPC = NPCPlayerUtil.instPlayerArenaNPCMap.get(instPlayerId);
	
		//组织玩家实例数据
		MessageData playerMsgData = new MessageData();
		playerMsgData.putIntItem("4", instPlayerArenaNPC.getLevel());
		retMsgData.putMessageItem("InstPlayer", playerMsgData.getMsgData());
		
	
		//组织玩家卡牌实例数据
		MessageData cardMsgData = new MessageData();
		String cards = instPlayerArenaNPC.getCards(); //卡牌列表  : id|cardID|qualityId|StarLevelId|TitleDetailId|exp|Level;  从0开始的
		for(String card : cards.split(";")){
			MessageData msgData = new MessageData();
			msgData.putIntItem("3", ConvertUtil.toInt(card.split("\\|")[1]));
			msgData.putIntItem("4", ConvertUtil.toInt(card.split("\\|")[2]));
			msgData.putIntItem("5", ConvertUtil.toInt(card.split("\\|")[3]));
			msgData.putIntItem("6", ConvertUtil.toInt(card.split("\\|")[4]));
			msgData.putIntItem("8", ConvertUtil.toInt(card.split("\\|")[5]));
			msgData.putIntItem("9", ConvertUtil.toInt(card.split("\\|")[6]));
			msgData.putIntItem("10", 1);
			msgData.putIntItem("11", 0);
			msgData.putIntItem("12", 0);
			msgData.putStringItem("13", "");
			cardMsgData.putMessageItem(card.split("\\|")[0], msgData.getMsgData());
		}
		retMsgData.putMessageItem("InstPlayerCard", cardMsgData.getMsgData());
		
		//组织玩家卡牌阵型实例数据
		MessageData formationMsgData = new MessageData();
		String formations = instPlayerArenaNPC.getFormations(); //阵型列表  id|Type|InstCardId|Position|CardId
		for(String formation : formations.split(";")){
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", ConvertUtil.toInt(formation.split("\\|")[0]));
			msgData.putIntItem("3", ConvertUtil.toInt(formation.split("\\|")[2]));
			msgData.putIntItem("4", ConvertUtil.toInt(formation.split("\\|")[1]));
			msgData.putIntItem("5", ConvertUtil.toInt(formation.split("\\|")[3]));
			msgData.putIntItem("6", ConvertUtil.toInt(formation.split("\\|")[4]));
			formationMsgData.putMessageItem(formation.split("\\|")[0], msgData.getMsgData());
		}
		retMsgData.putMessageItem("InstPlayerFormation", formationMsgData.getMsgData());
		
		//组织玩家阵容实例数据
		MessageData lineupMsgData = new MessageData();
		String lineups = instPlayerArenaNPC.getLineups(); //阵容列表  id|InstPlayerFormationId|EquipTypeId|InstPlayerEquipId
		for(String lineup : lineups.split(";")){
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", ConvertUtil.toInt(lineup.split("\\|")[0]));
			msgData.putIntItem("3", ConvertUtil.toInt(lineup.split("\\|")[1]));
			msgData.putIntItem("4", ConvertUtil.toInt(lineup.split("\\|")[2]));
			msgData.putIntItem("5", ConvertUtil.toInt(lineup.split("\\|")[3]));
			lineupMsgData.putMessageItem(lineup.split("\\|")[0], msgData.getMsgData());
		}
		retMsgData.putMessageItem("InstPlayerLineup", lineupMsgData.getMsgData());
		
		//组织玩家装备实例数据 
		MessageData equipMsgData = new MessageData();
		String equips = instPlayerArenaNPC.getEquips(); //装备列表  id|EquipId|Level
		for(String equip : equips.split(";")){
			MessageData msgData = new MessageData();
			msgData.putIntItem("4", ConvertUtil.toInt(equip.split("\\|")[1]));
			msgData.putIntItem("5", ConvertUtil.toInt(equip.split("\\|")[2]));
			equipMsgData.putMessageItem(equip.split("\\|")[0], msgData.getMsgData());
		}
		retMsgData.putMessageItem("InstPlayerEquip", equipMsgData.getMsgData());
		
		
		//玩家功法法宝实例表
		MessageData magicMsgData = new MessageData();
		String magics = instPlayerArenaNPC.getMagics(); //功法法宝列表  id|MagicId|MagicType|Quality|MagicLevelId|InstCardId
		for(String magic : magics.split(";")){
			MessageData msgData = new MessageData();
			msgData.putIntItem("1", ConvertUtil.toInt(magic.split("\\|")[0]));
			msgData.putIntItem("3", ConvertUtil.toInt(magic.split("\\|")[1]));
			msgData.putIntItem("4", ConvertUtil.toInt(magic.split("\\|")[2]));
			msgData.putIntItem("5", ConvertUtil.toInt(magic.split("\\|")[3]));
			msgData.putIntItem("6", ConvertUtil.toInt(magic.split("\\|")[4]));
			msgData.putIntItem("8", ConvertUtil.toInt(magic.split("\\|")[5]));
			magicMsgData.putMessageItem(magic.split("\\|")[0], msgData.getMsgData());
		}
		retMsgData.putMessageItem("InstPlayerMagic", magicMsgData.getMsgData());
	
	return retMsgData;
}
	
	
}
