package com.huayi.doupo.base.model.dict;
import com.huayi.doupo.base.dal.factory.DALFactory;
public class DictData extends DALFactory{

	public static int flag = 0;
	public static int isAll = 1;
	public static String tableNameList = "";

	/**
	 * 获取静态的字典数据
	 * @author mp
	 * @version 1.0, Tue Dec 22 15:35:36 CST 2015
	 * @return
	 * @throws Exception
	*/
	public static void loadDictData() throws Exception{
		if(flag==0){
			dictData(0);
		}
		flag++;
	}

	public static void dictData(int pd) throws Exception{

		String [] tableNameArray = tableNameList.split(";");

		for(String tableName : tableNameArray){
			if(tableName.equals("DictChapter") || isAll == 1){
				DictDataUtil.dictChapterUtil(pd);
			}

			if(tableName.equals("DictDantaDayAward") || isAll == 1){
				DictDataUtil.dictDantaDayAwardUtil(pd);
			}

			if(tableName.equals("DictUnionGrade") || isAll == 1){
				DictDataUtil.dictUnionGradeUtil(pd);
			}

			if(tableName.equals("DictWorldBossReward") || isAll == 1){
				DictDataUtil.dictWorldBossRewardUtil(pd);
			}

			if(tableName.equals("DictAchievementType") || isAll == 1){
				DictDataUtil.dictAchievementTypeUtil(pd);
			}

			if(tableName.equals("DictActivityLvStore") || isAll == 1){
				DictDataUtil.dictActivityLvStoreUtil(pd);
			}

			if(tableName.equals("DictAcupointNode") || isAll == 1){
				DictDataUtil.dictAcupointNodeUtil(pd);
			}

			if(tableName.equals("DictFightProp") || isAll == 1){
				DictDataUtil.dictFightPropUtil(pd);
			}

			if(tableName.equals("DictActivityAddRecharge") || isAll == 1){
				DictDataUtil.dictActivityAddRechargeUtil(pd);
			}

			if(tableName.equals("DictUnionLevelPriv") || isAll == 1){
				DictDataUtil.dictUnionLevelPrivUtil(pd);
			}

			if(tableName.equals("DictCardSoul") || isAll == 1){
				DictDataUtil.dictCardSoulUtil(pd);
			}

			if(tableName.equals("DictLootDrop") || isAll == 1){
				DictDataUtil.dictLootDropUtil(pd);
			}

			if(tableName.equals("DictManualSkillExp") || isAll == 1){
				DictDataUtil.dictManualSkillExpUtil(pd);
			}

			if(tableName.equals("DictFireSkill") || isAll == 1){
				DictDataUtil.dictFireSkillUtil(pd);
			}

			if(tableName.equals("DictActivityLuck") || isAll == 1){
				DictDataUtil.dictActivityLuckUtil(pd);
			}

			if(tableName.equals("DictBeautyCardExp") || isAll == 1){
				DictDataUtil.dictBeautyCardExpUtil(pd);
			}

			if(tableName.equals("DictEquipAdvancered") || isAll == 1){
				DictDataUtil.dictEquipAdvanceredUtil(pd);
			}

			if(tableName.equals("DictHoldStarGradeReward") || isAll == 1){
				DictDataUtil.dictHoldStarGradeRewardUtil(pd);
			}

			if(tableName.equals("DictActivityLimitTimeHeroRankReward") || isAll == 1){
				DictDataUtil.dictActivityLimitTimeHeroRankRewardUtil(pd);
			}

			if(tableName.equals("DictPillRecipe") || isAll == 1){
				DictDataUtil.dictPillRecipeUtil(pd);
			}

			if(tableName.equals("SysGmReward") || isAll == 1){
				DictDataUtil.sysGmRewardUtil(pd);
			}

			if(tableName.equals("SysMarquee") || isAll == 1){
				DictDataUtil.sysMarqueeUtil(pd);
			}

			if(tableName.equals("DictPlayerBaseProp") || isAll == 1){
				DictDataUtil.dictPlayerBasePropUtil(pd);
			}

			if(tableName.equals("DictActivityOpenServiceBag") || isAll == 1){
				DictDataUtil.dictActivityOpenServiceBagUtil(pd);
			}

			if(tableName.equals("DictAdvance") || isAll == 1){
				DictDataUtil.dictAdvanceUtil(pd);
			}

			if(tableName.equals("DictActivityGroupRate") || isAll == 1){
				DictDataUtil.dictActivityGroupRateUtil(pd);
			}

			if(tableName.equals("DictActivityGroupDiscount") || isAll == 1){
				DictDataUtil.dictActivityGroupDiscountUtil(pd);
			}

			if(tableName.equals("DictPagodaDrop") || isAll == 1){
				DictDataUtil.dictPagodaDropUtil(pd);
			}

			if(tableName.equals("DictFightSoulQuality") || isAll == 1){
				DictDataUtil.dictFightSoulQualityUtil(pd);
			}

			if(tableName.equals("DictInitCard") || isAll == 1){
				DictDataUtil.dictInitCardUtil(pd);
			}

			if(tableName.equals("DictFightSoulUpgradeExp") || isAll == 1){
				DictDataUtil.dictFightSoulUpgradeExpUtil(pd);
			}

			if(tableName.equals("DictKungFuTierAdd") || isAll == 1){
				DictDataUtil.dictKungFuTierAddUtil(pd);
			}

			if(tableName.equals("DictEquipment") || isAll == 1){
				DictDataUtil.dictEquipmentUtil(pd);
			}

			if(tableName.equals("SysQuartzJob") || isAll == 1){
				DictDataUtil.sysQuartzJobUtil(pd);
			}

			if(tableName.equals("DictPillThing") || isAll == 1){
				DictDataUtil.dictPillThingUtil(pd);
			}

			if(tableName.equals("DictActivityMonthCardStore") || isAll == 1){
				DictDataUtil.dictActivityMonthCardStoreUtil(pd);
			}

			if(tableName.equals("DictBagType") || isAll == 1){
				DictDataUtil.dictBagTypeUtil(pd);
			}

			if(tableName.equals("DictCardExp") || isAll == 1){
				DictDataUtil.dictCardExpUtil(pd);
			}

			if(tableName.equals("DictKungFuType") || isAll == 1){
				DictDataUtil.dictKungFuTypeUtil(pd);
			}

			if(tableName.equals("DictUnionWarBattlefield") || isAll == 1){
				DictDataUtil.dictUnionWarBattlefieldUtil(pd);
			}

			if(tableName.equals("DictActivityLimitShopping") || isAll == 1){
				DictDataUtil.dictActivityLimitShoppingUtil(pd);
			}

			if(tableName.equals("DictActivityGrabTheHour") || isAll == 1){
				DictDataUtil.dictActivityGrabTheHourUtil(pd);
			}

			if(tableName.equals("DictactivityExchange") || isAll == 1){
				DictDataUtil.dictactivityExchangeUtil(pd);
			}

			if(tableName.equals("DictRestore") || isAll == 1){
				DictDataUtil.dictRestoreUtil(pd);
			}

			if(tableName.equals("DictArenaInterval") || isAll == 1){
				DictDataUtil.dictArenaIntervalUtil(pd);
			}

			if(tableName.equals("DictBarrierDrop") || isAll == 1){
				DictDataUtil.dictBarrierDropUtil(pd);
			}

			if(tableName.equals("DictHoldStarStep") || isAll == 1){
				DictDataUtil.dictHoldStarStepUtil(pd);
			}

			if(tableName.equals("DictActivityStrogerHero") || isAll == 1){
				DictDataUtil.dictActivityStrogerHeroUtil(pd);
			}

			if(tableName.equals("DictWingAdvance") || isAll == 1){
				DictDataUtil.dictWingAdvanceUtil(pd);
			}

			if(tableName.equals("DictCard") || isAll == 1){
				DictDataUtil.dictCardUtil(pd);
			}

			if(tableName.equals("DictActivityVipStore") || isAll == 1){
				DictDataUtil.dictActivityVipStoreUtil(pd);
			}

			if(tableName.equals("DictYFireChip") || isAll == 1){
				DictDataUtil.dictYFireChipUtil(pd);
			}

			if(tableName.equals("DictFireGain") || isAll == 1){
				DictDataUtil.dictFireGainUtil(pd);
			}

			if(tableName.equals("DictMagicQuality") || isAll == 1){
				DictDataUtil.dictMagicQualityUtil(pd);
			}

			if(tableName.equals("DictTitleDetail") || isAll == 1){
				DictDataUtil.dictTitleDetailUtil(pd);
			}

			if(tableName.equals("DictFightType") || isAll == 1){
				DictDataUtil.dictFightTypeUtil(pd);
			}

			if(tableName.equals("DictMineWeather") || isAll == 1){
				DictDataUtil.dictMineWeatherUtil(pd);
			}

			if(tableName.equals("DictUI") || isAll == 1){
				DictDataUtil.dictUIUtil(pd);
			}

			if(tableName.equals("DictFireGainRule") || isAll == 1){
				DictDataUtil.dictFireGainRuleUtil(pd);
			}

			if(tableName.equals("DictMineBoxThing") || isAll == 1){
				DictDataUtil.dictMineBoxThingUtil(pd);
			}

			if(tableName.equals("DictLevelProp") || isAll == 1){
				DictDataUtil.dictLevelPropUtil(pd);
			}

			if(tableName.equals("DictActivitySoulChapterDrop") || isAll == 1){
				DictDataUtil.dictActivitySoulChapterDropUtil(pd);
			}

			if(tableName.equals("DictRecruitType") || isAll == 1){
				DictDataUtil.dictRecruitTypeUtil(pd);
			}

			if(tableName.equals("DictBarrierLevel") || isAll == 1){
				DictDataUtil.dictBarrierLevelUtil(pd);
			}

			if(tableName.equals("DictArenaNPC") || isAll == 1){
				DictDataUtil.dictArenaNPCUtil(pd);
			}

			if(tableName.equals("DictSpecialBoxThing") || isAll == 1){
				DictDataUtil.dictSpecialBoxThingUtil(pd);
			}

			if(tableName.equals("DictEquipAdvance") || isAll == 1){
				DictDataUtil.dictEquipAdvanceUtil(pd);
			}

			if(tableName.equals("DictBeautyCard") || isAll == 1){
				DictDataUtil.dictBeautyCardUtil(pd);
			}

			if(tableName.equals("DictMagicrefining") || isAll == 1){
				DictDataUtil.dictMagicrefiningUtil(pd);
			}

			if(tableName.equals("DictSpecialRule") || isAll == 1){
				DictDataUtil.dictSpecialRuleUtil(pd);
			}

			if(tableName.equals("DictPagodaCard") || isAll == 1){
				DictDataUtil.dictPagodaCardUtil(pd);
			}

			if(tableName.equals("DictFireExp") || isAll == 1){
				DictDataUtil.dictFireExpUtil(pd);
			}

			if(tableName.equals("DictActivityLimitTimeHeroJiFenReward") || isAll == 1){
				DictDataUtil.dictActivityLimitTimeHeroJiFenRewardUtil(pd);
			}

			if(tableName.equals("DictUnionBuild") || isAll == 1){
				DictDataUtil.dictUnionBuildUtil(pd);
			}

			if(tableName.equals("DictEquipType") || isAll == 1){
				DictDataUtil.dictEquipTypeUtil(pd);
			}

			if(tableName.equals("DictActivityLevelBag") || isAll == 1){
				DictDataUtil.dictActivityLevelBagUtil(pd);
			}

			if(tableName.equals("DictChip") || isAll == 1){
				DictDataUtil.dictChipUtil(pd);
			}

			if(tableName.equals("DictFightSoulHuntRule") || isAll == 1){
				DictDataUtil.dictFightSoulHuntRuleUtil(pd);
			}

			if(tableName.equals("DictFightSoulUpgradeProp") || isAll == 1){
				DictDataUtil.dictFightSoulUpgradePropUtil(pd);
			}

			if(tableName.equals("DictActivityOnlineRewards") || isAll == 1){
				DictDataUtil.dictActivityOnlineRewardsUtil(pd);
			}

			if(tableName.equals("DictDantaLayer") || isAll == 1){
				DictDataUtil.dictDantaLayerUtil(pd);
			}

			if(tableName.equals("DictLootChip") || isAll == 1){
				DictDataUtil.dictLootChipUtil(pd);
			}

			if(tableName.equals("DictRecharge") || isAll == 1){
				DictDataUtil.dictRechargeUtil(pd);
			}

			if(tableName.equals("DictRecruitTimesGet") || isAll == 1){
				DictDataUtil.dictRecruitTimesGetUtil(pd);
			}

			if(tableName.equals("DictGuipStep") || isAll == 1){
				DictDataUtil.dictGuipStepUtil(pd);
			}

			if(tableName.equals("DictBarrier") || isAll == 1){
				DictDataUtil.dictBarrierUtil(pd);
			}

			if(tableName.equals("DictKungFuQuality") || isAll == 1){
				DictDataUtil.dictKungFuQualityUtil(pd);
			}

			if(tableName.equals("DictWorldBossTimesReward") || isAll == 1){
				DictDataUtil.dictWorldBossTimesRewardUtil(pd);
			}

			if(tableName.equals("DictHoldStarGrade") || isAll == 1){
				DictDataUtil.dictHoldStarGradeUtil(pd);
			}

			if(tableName.equals("DictTrainProp") || isAll == 1){
				DictDataUtil.dictTrainPropUtil(pd);
			}

			if(tableName.equals("DictPill") || isAll == 1){
				DictDataUtil.dictPillUtil(pd);
			}

			if(tableName.equals("DictBarrierCard") || isAll == 1){
				DictDataUtil.dictBarrierCardUtil(pd);
			}

			if(tableName.equals("DictKungFu") || isAll == 1){
				DictDataUtil.dictKungFuUtil(pd);
			}

			if(tableName.equals("DictactivityTotalCost") || isAll == 1){
				DictDataUtil.dictactivityTotalCostUtil(pd);
			}

			if(tableName.equals("DictPillQuality") || isAll == 1){
				DictDataUtil.dictPillQualityUtil(pd);
			}

			if(tableName.equals("DictEquipSuitred") || isAll == 1){
				DictDataUtil.dictEquipSuitredUtil(pd);
			}

			if(tableName.equals("SysMsgRule") || isAll == 1){
				DictDataUtil.sysMsgRuleUtil(pd);
			}

			if(tableName.equals("DictGenerBoxThing") || isAll == 1){
				DictDataUtil.dictGenerBoxThingUtil(pd);
			}

			if(tableName.equals("DictRecruitSpecialCard") || isAll == 1){
				DictDataUtil.dictRecruitSpecialCardUtil(pd);
			}

			if(tableName.equals("SysStatics") || isAll == 1){
				DictDataUtil.sysStaticsUtil(pd);
			}

			if(tableName.equals("DictActivityDailyDeals") || isAll == 1){
				DictDataUtil.dictActivityDailyDealsUtil(pd);
			}

			if(tableName.equals("DictTryToPracticeBarrierCard") || isAll == 1){
				DictDataUtil.dictTryToPracticeBarrierCardUtil(pd);
			}

			if(tableName.equals("DictFunctionOpen") || isAll == 1){
				DictDataUtil.dictFunctionOpenUtil(pd);
			}

			if(tableName.equals("DictQuality") || isAll == 1){
				DictDataUtil.dictQualityUtil(pd);
			}

			if(tableName.equals("DictPagodaFormation") || isAll == 1){
				DictDataUtil.dictPagodaFormationUtil(pd);
			}

			if(tableName.equals("DictUnionWarAmbush") || isAll == 1){
				DictDataUtil.dictUnionWarAmbushUtil(pd);
			}

			if(tableName.equals("DictColor") || isAll == 1){
				DictDataUtil.dictColorUtil(pd);
			}

			if(tableName.equals("DictMineType") || isAll == 1){
				DictDataUtil.dictMineTypeUtil(pd);
			}

			if(tableName.equals("DictHoleConsume") || isAll == 1){
				DictDataUtil.dictHoleConsumeUtil(pd);
			}

			if(tableName.equals("DictPagodaStore") || isAll == 1){
				DictDataUtil.dictPagodaStoreUtil(pd);
			}

			if(tableName.equals("DictFightSoulPositionCond") || isAll == 1){
				DictDataUtil.dictFightSoulPositionCondUtil(pd);
			}

			if(tableName.equals("DictActivitySignIn") || isAll == 1){
				DictDataUtil.dictActivitySignInUtil(pd);
			}

			if(tableName.equals("DictUnionBox") || isAll == 1){
				DictDataUtil.dictUnionBoxUtil(pd);
			}

			if(tableName.equals("DictManualSkill") || isAll == 1){
				DictDataUtil.dictManualSkillUtil(pd);
			}

			if(tableName.equals("DictCardExpAdd") || isAll == 1){
				DictDataUtil.dictCardExpAddUtil(pd);
			}

			if(tableName.equals("DictUnionWarInfo") || isAll == 1){
				DictDataUtil.dictUnionWarInfoUtil(pd);
			}

			if(tableName.equals("DictVIP") || isAll == 1){
				DictDataUtil.dictVIPUtil(pd);
			}

			if(tableName.equals("DictSysConfigStr") || isAll == 1){
				DictDataUtil.dictSysConfigStrUtil(pd);
			}

			if(tableName.equals("DictActivityAllPeapleWeal") || isAll == 1){
				DictDataUtil.dictActivityAllPeapleWealUtil(pd);
			}

			if(tableName.equals("DictAuctionShop") || isAll == 1){
				DictDataUtil.dictAuctionShopUtil(pd);
			}

			if(tableName.equals("DictActivityPrivateSale") || isAll == 1){
				DictDataUtil.dictActivityPrivateSaleUtil(pd);
			}

			if(tableName.equals("DictCardBaseProp") || isAll == 1){
				DictDataUtil.dictCardBasePropUtil(pd);
			}

			if(tableName.equals("DictTitle") || isAll == 1){
				DictDataUtil.dictTitleUtil(pd);
			}

			if(tableName.equals("DictActivityTreasures") || isAll == 1){
				DictDataUtil.dictActivityTreasuresUtil(pd);
			}

			if(tableName.equals("SysCdKeyType") || isAll == 1){
				DictDataUtil.sysCdKeyTypeUtil(pd);
			}

			if(tableName.equals("DictActivityBanner") || isAll == 1){
				DictDataUtil.dictActivityBannerUtil(pd);
			}

			if(tableName.equals("SysActivity") || isAll == 1){
				DictDataUtil.sysActivityUtil(pd);
			}

			if(tableName.equals("DictArenaDrop") || isAll == 1){
				DictDataUtil.dictArenaDropUtil(pd);
			}

			if(tableName.equals("DictRecruitCard") || isAll == 1){
				DictDataUtil.dictRecruitCardUtil(pd);
			}

			if(tableName.equals("DictActivityGroupGiveZiRule") || isAll == 1){
				DictDataUtil.dictActivityGroupGiveZiRuleUtil(pd);
			}

			if(tableName.equals("DictWingLuck") || isAll == 1){
				DictDataUtil.dictWingLuckUtil(pd);
			}

			if(tableName.equals("DictActivityStarStore") || isAll == 1){
				DictDataUtil.dictActivityStarStoreUtil(pd);
			}

			if(tableName.equals("DictEquipSuit") || isAll == 1){
				DictDataUtil.dictEquipSuitUtil(pd);
			}

			if(tableName.equals("DictCoefficient") || isAll == 1){
				DictDataUtil.dictCoefficientUtil(pd);
			}

			if(tableName.equals("DictAuctionHJY") || isAll == 1){
				DictDataUtil.dictAuctionHJYUtil(pd);
			}

			if(tableName.equals("DictConstell") || isAll == 1){
				DictDataUtil.dictConstellUtil(pd);
			}

			if(tableName.equals("SysFilterWords") || isAll == 1){
				DictDataUtil.sysFilterWordsUtil(pd);
			}

			if(tableName.equals("DictFightSoul") || isAll == 1){
				DictDataUtil.dictFightSoulUtil(pd);
			}

			if(tableName.equals("DictChapterActivityVip") || isAll == 1){
				DictDataUtil.dictChapterActivityVipUtil(pd);
			}

			if(tableName.equals("DictMagic") || isAll == 1){
				DictDataUtil.dictMagicUtil(pd);
			}

			if(tableName.equals("DictDailyTask") || isAll == 1){
				DictDataUtil.dictDailyTaskUtil(pd);
			}

			if(tableName.equals("DictYFire") || isAll == 1){
				DictDataUtil.dictYFireUtil(pd);
			}

			if(tableName.equals("DictTryToPracticeType") || isAll == 1){
				DictDataUtil.dictTryToPracticeTypeUtil(pd);
			}

			if(tableName.equals("DictFireSkillChange") || isAll == 1){
				DictDataUtil.dictFireSkillChangeUtil(pd);
			}

			if(tableName.equals("DictHJYStore") || isAll == 1){
				DictDataUtil.dictHJYStoreUtil(pd);
			}

			if(tableName.equals("DictActivityMonthCardStoreTemp") || isAll == 1){
				DictDataUtil.dictActivityMonthCardStoreTempUtil(pd);
			}

			if(tableName.equals("DictHoldStarZodiac") || isAll == 1){
				DictDataUtil.dictHoldStarZodiacUtil(pd);
			}

			if(tableName.equals("DictUnionStore") || isAll == 1){
				DictDataUtil.dictUnionStoreUtil(pd);
			}

			if(tableName.equals("DictPillType") || isAll == 1){
				DictDataUtil.dictPillTypeUtil(pd);
			}

			if(tableName.equals("DictWorldBossStore") || isAll == 1){
				DictDataUtil.dictWorldBossStoreUtil(pd);
			}

			if(tableName.equals("DictFireSkillQuality") || isAll == 1){
				DictDataUtil.dictFireSkillQualityUtil(pd);
			}

			if(tableName.equals("DictArenaConvert") || isAll == 1){
				DictDataUtil.dictArenaConvertUtil(pd);
			}

			if(tableName.equals("DictThingExtend") || isAll == 1){
				DictDataUtil.dictThingExtendUtil(pd);
			}

			if(tableName.equals("DictFire") || isAll == 1){
				DictDataUtil.dictFireUtil(pd);
			}

			if(tableName.equals("DictWing") || isAll == 1){
				DictDataUtil.dictWingUtil(pd);
			}

			if(tableName.equals("DictBarrierDropType") || isAll == 1){
				DictDataUtil.dictBarrierDropTypeUtil(pd);
			}

			if(tableName.equals("DictActivityHoliday") || isAll == 1){
				DictDataUtil.dictActivityHolidayUtil(pd);
			}

			if(tableName.equals("DictEquipQuality") || isAll == 1){
				DictDataUtil.dictEquipQualityUtil(pd);
			}

			if(tableName.equals("DictWorldBoss") || isAll == 1){
				DictDataUtil.dictWorldBossUtil(pd);
			}

			if(tableName.equals("DictWingStrengthen") || isAll == 1){
				DictDataUtil.dictWingStrengthenUtil(pd);
			}

			if(tableName.equals("DictTryToPractice") || isAll == 1){
				DictDataUtil.dictTryToPracticeUtil(pd);
			}

			if(tableName.equals("DictHoldStarRewardPos") || isAll == 1){
				DictDataUtil.dictHoldStarRewardPosUtil(pd);
			}

			if(tableName.equals("DictSysConfig") || isAll == 1){
				DictDataUtil.dictSysConfigUtil(pd);
			}

			if(tableName.equals("DictThing") || isAll == 1){
				DictDataUtil.dictThingUtil(pd);
			}

			if(tableName.equals("DictTableType") || isAll == 1){
				DictDataUtil.dictTableTypeUtil(pd);
			}

			if(tableName.equals("DictDantaMonster") || isAll == 1){
				DictDataUtil.dictDantaMonsterUtil(pd);
			}

			if(tableName.equals("DictBeautyCardFight") || isAll == 1){
				DictDataUtil.dictBeautyCardFightUtil(pd);
			}

			if(tableName.equals("DictActivityFund") || isAll == 1){
				DictDataUtil.dictActivityFundUtil(pd);
			}

			if(tableName.equals("DictPagodaStorey") || isAll == 1){
				DictDataUtil.dictPagodaStoreyUtil(pd);
			}

			if(tableName.equals("DictEquipSuitRefer") || isAll == 1){
				DictDataUtil.dictEquipSuitReferUtil(pd);
			}

			if(tableName.equals("DictMagicLevel") || isAll == 1){
				DictDataUtil.dictMagicLevelUtil(pd);
			}

			if(tableName.equals("DictArenaAdvance") || isAll == 1){
				DictDataUtil.dictArenaAdvanceUtil(pd);
			}

			if(tableName.equals("DictCardType") || isAll == 1){
				DictDataUtil.dictCardTypeUtil(pd);
			}

			if(tableName.equals("DictStrogerEquip") || isAll == 1){
				DictDataUtil.dictStrogerEquipUtil(pd);
			}

			if(tableName.equals("DictEquipStrengthen") || isAll == 1){
				DictDataUtil.dictEquipStrengthenUtil(pd);
			}

			if(tableName.equals("DictActivityFlashSale") || isAll == 1){
				DictDataUtil.dictActivityFlashSaleUtil(pd);
			}

			if(tableName.equals("DictThingType") || isAll == 1){
				DictDataUtil.dictThingTypeUtil(pd);
			}

			if(tableName.equals("DictCardLuck") || isAll == 1){
				DictDataUtil.dictCardLuckUtil(pd);
			}

			if(tableName.equals("DictArenaReward") || isAll == 1){
				DictDataUtil.dictArenaRewardUtil(pd);
			}

			if(tableName.equals("DictHoldStarRewardRefreshTimes") || isAll == 1){
				DictDataUtil.dictHoldStarRewardRefreshTimesUtil(pd);
			}

			if(tableName.equals("DictStarLevel") || isAll == 1){
				DictDataUtil.dictStarLevelUtil(pd);
			}

			if(tableName.equals("DictAchievement") || isAll == 1){
				DictDataUtil.dictAchievementUtil(pd);
			}

			if(tableName.equals("DictLootNPC") || isAll == 1){
				DictDataUtil.dictLootNPCUtil(pd);
			}

			if(tableName.equals("DictEquipWash") || isAll == 1){
				DictDataUtil.dictEquipWashUtil(pd);
			}

			if(tableName.equals("DictHoleConsume") || isAll == 1){
				DictDataUtil.dictHoleConsumeGroupUtil(pd);
			}

			if(tableName.equals("DictAdvance") || isAll == 1){
				DictDataUtil.dictAdvanceGroupUtil(pd);
			}

			if(tableName.equals("DictTitleDetail") || isAll == 1){
				DictDataUtil.dictTitleDetailGroupUtil(pd);
			}

			if(tableName.equals("DictAcupointNode") || isAll == 1){
				DictDataUtil.dictAcupointNodeGroupUtil(pd);
			}

			if(tableName.equals("DictKungFuTierAdd") || isAll == 1){
				DictDataUtil.dictKungFuTierAddGroupUtil(pd);
			}

			if(tableName.equals("DictFireGain") || isAll == 1){
				DictDataUtil.dictFireGainGroupUtil(pd);
			}

			if(tableName.equals("DictThing") || isAll == 1){
				DictDataUtil.dictThingGroupUtil(pd);
			}

			if(tableName.equals("DictPagodaDrop") || isAll == 1){
				DictDataUtil.dictPagodaDropGroupUtil(pd);
			}

			if(tableName.equals("DictRecruitCard") || isAll == 1){
				DictDataUtil.dictRecruitCardGroupUtil(pd);
			}

			if(tableName.equals("DictBarrierDrop") || isAll == 1){
				DictDataUtil.dictBarrierDropGroupUtil(pd);
			}

			if(tableName.equals("DictBarrier") || isAll == 1){
				DictDataUtil.dictBarrierGroupUtil(pd);
			}

			if(tableName.equals("DictBarrierLevel") || isAll == 1){
				DictDataUtil.dictBarrierLevelGroupUtil(pd);
			}

			if(tableName.equals("DictChapter") || isAll == 1){
				DictDataUtil.dictChapterGroupUtil(pd);
			}

			if(tableName.equals("DictLootChip") || isAll == 1){
				DictDataUtil.dictLootChipGroupUtil(pd);
			}

			if(tableName.equals("DictChip") || isAll == 1){
				DictDataUtil.dictChipGroupUtil(pd);
			}

			if(tableName.equals("DictPill") || isAll == 1){
				DictDataUtil.dictPillGroupUtil(pd);
			}

			if(tableName.equals("DictRestore") || isAll == 1){
				DictDataUtil.dictRestoreGroupUtil(pd);
			}

			if(tableName.equals("DictMagicLevel") || isAll == 1){
				DictDataUtil.dictMagicLevelGroupUtil(pd);
			}

			if(tableName.equals("DictAchievement") || isAll == 1){
				DictDataUtil.dictAchievementGroupUtil(pd);
			}

			if(tableName.equals("DictGenerBoxThing") || isAll == 1){
				DictDataUtil.dictGenerBoxThingGroupUtil(pd);
			}

			if(tableName.equals("DictSpecialBoxThing") || isAll == 1){
				DictDataUtil.dictSpecialBoxThingGroupUtil(pd);
			}

			if(tableName.equals("DictCard") || isAll == 1){
				DictDataUtil.dictCardGroupUtil(pd);
			}

			if(tableName.equals("DictEquipment") || isAll == 1){
				DictDataUtil.dictEquipmentGroupUtil(pd);
			}

			if(tableName.equals("DictMagic") || isAll == 1){
				DictDataUtil.dictMagicGroupUtil(pd);
			}

			if(tableName.equals("DictBarrierDropType") || isAll == 1){
				DictDataUtil.dictBarrierDropTypeGroupUtil(pd);
			}

			if(tableName.equals("DictRecruitSpecialCard") || isAll == 1){
				DictDataUtil.dictRecruitSpecialCardGroupUtil(pd);
			}

			if(tableName.equals("DictRecruitTimesGet") || isAll == 1){
				DictDataUtil.dictRecruitTimesGetGroupUtil(pd);
			}

			if(tableName.equals("DictActivitySignIn") || isAll == 1){
				DictDataUtil.dictActivitySignInGroupUtil(pd);
			}

			if(tableName.equals("DictActivityGrabTheHour") || isAll == 1){
				DictDataUtil.dictActivityGrabTheHourGroupUtil(pd);
			}

			if(tableName.equals("DictActivityPrivateSale") || isAll == 1){
				DictDataUtil.dictActivityPrivateSaleGroupUtil(pd);
			}

			if(tableName.equals("DictActivityDailyDeals") || isAll == 1){
				DictDataUtil.dictActivityDailyDealsGroupUtil(pd);
			}

			if(tableName.equals("DictActivityMonthCardStore") || isAll == 1){
				DictDataUtil.dictActivityMonthCardStoreGroupUtil(pd);
			}

			if(tableName.equals("DictEquipAdvance") || isAll == 1){
				DictDataUtil.dictEquipAdvanceGroupUtil(pd);
			}

			if(tableName.equals("DictFightSoul") || isAll == 1){
				DictDataUtil.dictFightSoulGroupUtil(pd);
			}

			if(tableName.equals("DictFightSoulUpgradeExp") || isAll == 1){
				DictDataUtil.dictFightSoulUpgradeExpGroupUtil(pd);
			}

			if(tableName.equals("DictFightSoulUpgradeProp") || isAll == 1){
				DictDataUtil.dictFightSoulUpgradePropGroupUtil(pd);
			}

			if(tableName.equals("DictMineBoxThing") || isAll == 1){
				DictDataUtil.dictMineBoxThingGroupUtil(pd);
			}

			if(tableName.equals("DictWingAdvance") || isAll == 1){
				DictDataUtil.dictWingAdvanceGroupUtil(pd);
			}

			if(tableName.equals("DictWingStrengthen") || isAll == 1){
				DictDataUtil.dictWingStrengthenGroupUtil(pd);
			}

			if(tableName.equals("DictHoldStarGradeReward") || isAll == 1){
				DictDataUtil.dictHoldStarGradeRewardGroupUtil(pd);
			}

			if(tableName.equals("DictEquipAdvancered") || isAll == 1){
				DictDataUtil.dictEquipAdvanceredGroupUtil(pd);
			}

			if(tableName.equals("DictMagicrefining") || isAll == 1){
				DictDataUtil.dictMagicrefiningGroupUtil(pd);
			}

			if(tableName.equals("DictCardLuck") || isAll == 1){
				DictDataUtil.dictCardLuckGroupUtil(pd);
			}

			if(tableName.equals("DictEquipSuitRefer") || isAll == 1){
				DictDataUtil.dictEquipSuitReferGroupUtil(pd);
			}

			if(tableName.equals("DictWingLuck") || isAll == 1){
				DictDataUtil.dictWingLuckGroupUtil(pd);
			}

		}
	}
}
