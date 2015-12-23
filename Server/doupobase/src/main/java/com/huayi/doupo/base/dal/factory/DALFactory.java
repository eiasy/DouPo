package com.huayi.doupo.base.dal.factory;

import com.huayi.doupo.base.dal.*;
import com.huayi.doupo.base.util.logic.system.SpringUtil;

public class DALFactory {

	public static InstActivityExchangeDAL getInstActivityExchangeDAL() {
		return (InstActivityExchangeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstActivityExchangeDAL);
	}
	
	public static DictActivityGroupGiveZiRuleDAL getDictActivityGroupGiveZiRuleDAL() {
		return (DictActivityGroupGiveZiRuleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityGroupGiveZiRuleDAL);
	}
	
	public static InstPlayerGroupDAL getInstPlayerGroupDAL() {
		return (InstPlayerGroupDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerGroupDAL);
	}

	public static DictActivityGroupDiscountDAL getDictActivityGroupDiscountDAL() {
		return (DictActivityGroupDiscountDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityGroupDiscountDAL);
	}

	public static DictActivityGroupRateDAL getDictActivityGroupRateDAL() {
		return (DictActivityGroupRateDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityGroupRateDAL);
	}
	
	public static DictHoldStarGradeRewardDAL getDictHoldStarGradeRewardDAL() {
		return (DictHoldStarGradeRewardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictHoldStarGradeRewardDAL);
	}

	public static DictHoldStarRewardRefreshTimesDAL getDictHoldStarRewardRefreshTimesDAL() {
		return (DictHoldStarRewardRefreshTimesDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictHoldStarRewardRefreshTimesDAL);
	}

	public static DictHoldStarStepDAL getDictHoldStarStepDAL() {
		return (DictHoldStarStepDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictHoldStarStepDAL);
	}

	public static InstPlayerHoldStarDAL getInstPlayerHoldStarDAL() {
		return (InstPlayerHoldStarDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerHoldStarDAL);
	}

	public static DictHoldStarGradeDAL getDictHoldStarGradeDAL() {
		return (DictHoldStarGradeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictHoldStarGradeDAL);
	}

	public static DictHoldStarZodiacDAL getDictHoldStarZodiacDAL() {
		return (DictHoldStarZodiacDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictHoldStarZodiacDAL);
	}

	public static DictHoldStarRewardPosDAL getDictHoldStarRewardPosDAL() {
		return (DictHoldStarRewardPosDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictHoldStarRewardPosDAL);
	}
	
	public static DictWorldBossDAL getDictWorldBossDAL() {
		return (DictWorldBossDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictWorldBossDAL);
	}

	public static InstWorldBossDAL getInstWorldBossDAL() {
		return (InstWorldBossDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstWorldBossDAL);
	}

	public static DictWorldBossStoreDAL getDictWorldBossStoreDAL() {
		return (DictWorldBossStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictWorldBossStoreDAL);
	}

	public static DictWorldBossTimesRewardDAL getDictWorldBossTimesRewardDAL() {
		return (DictWorldBossTimesRewardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictWorldBossTimesRewardDAL);
	}
	
	public static DictWingLuckDAL getDictWingLuckDAL() {
		return (DictWingLuckDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictWingLuckDAL);
	}
	
	public static InstPlayerWingDAL getInstPlayerWingDAL() {
		return (InstPlayerWingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerWingDAL);
	}

	public static DictWingStrengthenDAL getDictWingStrengthenDAL() {
		return (DictWingStrengthenDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictWingStrengthenDAL);
	}

	public static DictWingAdvanceDAL getDictWingAdvanceDAL() {
		return (DictWingAdvanceDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictWingAdvanceDAL);
	}

	public static DictWingDAL getDictWingDAL() {
		return (DictWingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictWingDAL);
	}
	
	public static DictStrogerEquipDAL getDictStrogerEquipDAL() {
		return (DictStrogerEquipDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictStrogerEquipDAL);
	}

	public static DictFightSoulPositionCondDAL getDictFightSoulPositionCondDAL() {
		return (DictFightSoulPositionCondDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFightSoulPositionCondDAL);
	}

	public static DictFightSoulHuntRuleDAL getDictFightSoulHuntRuleDAL() {
		return (DictFightSoulHuntRuleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFightSoulHuntRuleDAL);
	}

	public static InstPlayerFightSoulDAL getInstPlayerFightSoulDAL() {
		return (InstPlayerFightSoulDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerFightSoulDAL);
	}

	public static DictFightSoulDAL getDictFightSoulDAL() {
		return (DictFightSoulDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFightSoulDAL);
	}

	public static DictFightSoulUpgradePropDAL getDictFightSoulUpgradePropDAL() {
		return (DictFightSoulUpgradePropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFightSoulUpgradePropDAL);
	}

	public static InstPlayerFightSoulHuntRuleDAL getInstPlayerFightSoulHuntRuleDAL() {
		return (InstPlayerFightSoulHuntRuleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerFightSoulHuntRuleDAL);
	}

	public static DictFightSoulUpgradeExpDAL getDictFightSoulUpgradeExpDAL() {
		return (DictFightSoulUpgradeExpDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFightSoulUpgradeExpDAL);
	}

	public static DictFightSoulQualityDAL getDictFightSoulQualityDAL() {
		return (DictFightSoulQualityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFightSoulQualityDAL);
	}

	public static DictActivitySoulChapterDropDAL getDictActivitySoulChapterDropDAL() {
		return (DictActivitySoulChapterDropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivitySoulChapterDropDAL);
	}
	
	public static DictActivityBannerDAL getDictActivityBannerDAL() {
		return (DictActivityBannerDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityBannerDAL);
	}
	
	public static DictActivityHolidayDAL getDictActivityHolidayDAL() {
		return (DictActivityHolidayDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityHolidayDAL);
	}
	
	public static InstTotalCostDAL getInstTotalCostDAL() {
		return (InstTotalCostDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstTotalCostDAL);
	}

	public static DictChapterDAL getDictChapterDAL() {
		return (DictChapterDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictChapterDAL);
	}

	public static InstPlayerManualSkillDAL getInstPlayerManualSkillDAL() {
		return (InstPlayerManualSkillDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerManualSkillDAL);
	}

	public static InstPlayerDailyTaskDAL getInstPlayerDailyTaskDAL() {
		return (InstPlayerDailyTaskDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerDailyTaskDAL);
	}

	public static DictDantaDayAwardDAL getDictDantaDayAwardDAL() {
		return (DictDantaDayAwardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictDantaDayAwardDAL);
	}

	public static DictUnionGradeDAL getDictUnionGradeDAL() {
		return (DictUnionGradeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictUnionGradeDAL);
	}

	public static DictAchievementTypeDAL getDictAchievementTypeDAL() {
		return (DictAchievementTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictAchievementTypeDAL);
	}

	public static DictWorldBossRewardDAL getDictWorldBossRewardDAL() {
		return (DictWorldBossRewardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictWorldBossRewardDAL);
	}

	public static DictActivityLvStoreDAL getDictActivityLvStoreDAL() {
		return (DictActivityLvStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityLvStoreDAL);
	}

	public static DictAcupointNodeDAL getDictAcupointNodeDAL() {
		return (DictAcupointNodeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictAcupointNodeDAL);
	}

	public static DictFightPropDAL getDictFightPropDAL() {
		return (DictFightPropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFightPropDAL);
	}

	public static DictActivityAddRechargeDAL getDictActivityAddRechargeDAL() {
		return (DictActivityAddRechargeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityAddRechargeDAL);
	}

	public static InstPlayerArenaConvertDAL getInstPlayerArenaConvertDAL() {
		return (InstPlayerArenaConvertDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerArenaConvertDAL);
	}

	public static DictCardSoulDAL getDictCardSoulDAL() {
		return (DictCardSoulDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictCardSoulDAL);
	}

	public static DictUnionLevelPrivDAL getDictUnionLevelPrivDAL() {
		return (DictUnionLevelPrivDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictUnionLevelPrivDAL);
	}

	public static InstPlayerGrabTheHourDAL getInstPlayerGrabTheHourDAL() {
		return (InstPlayerGrabTheHourDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerGrabTheHourDAL);
	}

	public static DictLootDropDAL getDictLootDropDAL() {
		return (DictLootDropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictLootDropDAL);
	}

	public static DictManualSkillExpDAL getDictManualSkillExpDAL() {
		return (DictManualSkillExpDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictManualSkillExpDAL);
	}

	public static DictFireSkillDAL getDictFireSkillDAL() {
		return (DictFireSkillDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFireSkillDAL);
	}

	public static DictActivityLuckDAL getDictActivityLuckDAL() {
		return (DictActivityLuckDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityLuckDAL);
	}

	public static DictBeautyCardExpDAL getDictBeautyCardExpDAL() {
		return (DictBeautyCardExpDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictBeautyCardExpDAL);
	}

	public static DictActivityLimitTimeHeroRankRewardDAL getDictActivityLimitTimeHeroRankRewardDAL() {
		return (DictActivityLimitTimeHeroRankRewardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityLimitTimeHeroRankRewardDAL);
	}

	public static InstUnionMemberDAL getInstUnionMemberDAL() {
		return (InstUnionMemberDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstUnionMemberDAL);
	}

	public static InstPlayerBagExpandDAL getInstPlayerBagExpandDAL() {
		return (InstPlayerBagExpandDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerBagExpandDAL);
	}

	public static DictPillRecipeDAL getDictPillRecipeDAL() {
		return (DictPillRecipeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPillRecipeDAL);
	}

	public static SysGmRewardDAL getSysGmRewardDAL() {
		return (SysGmRewardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysGmRewardDAL);
	}

	public static InstRankDantaDAL getInstRankDantaDAL() {
		return (InstRankDantaDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstRankDantaDAL);
	}

	public static SysMarqueeDAL getSysMarqueeDAL() {
		return (SysMarqueeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysMarqueeDAL);
	}

	public static DictPlayerBasePropDAL getDictPlayerBasePropDAL() {
		return (DictPlayerBasePropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPlayerBasePropDAL);
	}

	public static DictActivityOpenServiceBagDAL getDictActivityOpenServiceBagDAL() {
		return (DictActivityOpenServiceBagDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityOpenServiceBagDAL);
	}

	public static DictAdvanceDAL getDictAdvanceDAL() {
		return (DictAdvanceDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictAdvanceDAL);
	}

	public static DictPagodaDropDAL getDictPagodaDropDAL() {
		return (DictPagodaDropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPagodaDropDAL);
	}

	public static InstPlayerTryToPracticeDAL getInstPlayerTryToPracticeDAL() {
		return (InstPlayerTryToPracticeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerTryToPracticeDAL);
	}

	public static InstPlayerBoxCountDAL getInstPlayerBoxCountDAL() {
		return (InstPlayerBoxCountDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerBoxCountDAL);
	}

	public static InstPlayerFireDAL getInstPlayerFireDAL() {
		return (InstPlayerFireDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerFireDAL);
	}

	public static DictInitCardDAL getDictInitCardDAL() {
		return (DictInitCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictInitCardDAL);
	}

	public static DictKungFuTierAddDAL getDictKungFuTierAddDAL() {
		return (DictKungFuTierAddDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictKungFuTierAddDAL);
	}

	public static DictEquipmentDAL getDictEquipmentDAL() {
		return (DictEquipmentDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipmentDAL);
	}

	public static SysQuartzJobDAL getSysQuartzJobDAL() {
		return (SysQuartzJobDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysQuartzJobDAL);
	}

	public static DictPillThingDAL getDictPillThingDAL() {
		return (DictPillThingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPillThingDAL);
	}

	public static DictActivityMonthCardStoreDAL getDictActivityMonthCardStoreDAL() {
		return (DictActivityMonthCardStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityMonthCardStoreDAL);
	}

	public static DictBagTypeDAL getDictBagTypeDAL() {
		return (DictBagTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictBagTypeDAL);
	}

	public static InstEquipGemDAL getInstEquipGemDAL() {
		return (InstEquipGemDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstEquipGemDAL);
	}

	public static InstPlayerMagicDAL getInstPlayerMagicDAL() {
		return (InstPlayerMagicDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerMagicDAL);
	}

	public static DictCardExpDAL getDictCardExpDAL() {
		return (DictCardExpDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictCardExpDAL);
	}

	public static InstActivityDAL getInstActivityDAL() {
		return (InstActivityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstActivityDAL);
	}

	public static DictKungFuTypeDAL getDictKungFuTypeDAL() {
		return (DictKungFuTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictKungFuTypeDAL);
	}

	public static DictActivityLimitShoppingDAL getDictActivityLimitShoppingDAL() {
		return (DictActivityLimitShoppingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityLimitShoppingDAL);
	}

	public static DictActivityGrabTheHourDAL getDictActivityGrabTheHourDAL() {
		return (DictActivityGrabTheHourDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityGrabTheHourDAL);
	}

	public static DictactivityExchangeDAL getDictactivityExchangeDAL() {
		return (DictactivityExchangeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictactivityExchangeDAL);
	}

	public static DictRestoreDAL getDictRestoreDAL() {
		return (DictRestoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictRestoreDAL);
	}

	public static DictArenaIntervalDAL getDictArenaIntervalDAL() {
		return (DictArenaIntervalDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictArenaIntervalDAL);
	}

	public static DictBarrierDropDAL getDictBarrierDropDAL() {
		return (DictBarrierDropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictBarrierDropDAL);
	}

	public static DictActivityStrogerHeroDAL getDictActivityStrogerHeroDAL() {
		return (DictActivityStrogerHeroDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityStrogerHeroDAL);
	}

	public static InstPlayerGoldStaticsDAL getInstPlayerGoldStaticsDAL() {
		return (InstPlayerGoldStaticsDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerGoldStaticsDAL);
	}

	public static DictCardDAL getDictCardDAL() {
		return (DictCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictCardDAL);
	}

	public static DictActivityVipStoreDAL getDictActivityVipStoreDAL() {
		return (DictActivityVipStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityVipStoreDAL);
	}

	public static InstPlayerRestoreTempDAL getInstPlayerRestoreTempDAL() {
		return (InstPlayerRestoreTempDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerRestoreTempDAL);
	}

	public static DictFireGainDAL getDictFireGainDAL() {
		return (DictFireGainDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFireGainDAL);
	}

	public static DictMagicQualityDAL getDictMagicQualityDAL() {
		return (DictMagicQualityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictMagicQualityDAL);
	}

	public static InstPlayerTrainTempDAL getInstPlayerTrainTempDAL() {
		return (InstPlayerTrainTempDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerTrainTempDAL);
	}

	public static DictTitleDetailDAL getDictTitleDetailDAL() {
		return (DictTitleDetailDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictTitleDetailDAL);
	}

	public static DictFightTypeDAL getDictFightTypeDAL() {
		return (DictFightTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFightTypeDAL);
	}

	public static InstPlayerThingDAL getInstPlayerThingDAL() {
		return (InstPlayerThingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerThingDAL);
	}

	public static InstPlayerPillRecipeThingDAL getInstPlayerPillRecipeThingDAL() {
		return (InstPlayerPillRecipeThingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerPillRecipeThingDAL);
	}

	public static DictUIDAL getDictUIDAL() {
		return (DictUIDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictUIDAL);
	}

	public static DictFireGainRuleDAL getDictFireGainRuleDAL() {
		return (DictFireGainRuleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFireGainRuleDAL);
	}

	public static DictLevelPropDAL getDictLevelPropDAL() {
		return (DictLevelPropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictLevelPropDAL);
	}

	public static InstPlayerLineupDAL getInstPlayerLineupDAL() {
		return (InstPlayerLineupDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerLineupDAL);
	}

	public static DictRecruitTypeDAL getDictRecruitTypeDAL() {
		return (DictRecruitTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictRecruitTypeDAL);
	}

	public static DictBarrierLevelDAL getDictBarrierLevelDAL() {
		return (DictBarrierLevelDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictBarrierLevelDAL);
	}

	public static DictArenaNPCDAL getDictArenaNPCDAL() {
		return (DictArenaNPCDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictArenaNPCDAL);
	}

	public static InstPlayerPillThingDAL getInstPlayerPillThingDAL() {
		return (InstPlayerPillThingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerPillThingDAL);
	}

	public static DictSpecialBoxThingDAL getDictSpecialBoxThingDAL() {
		return (DictSpecialBoxThingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictSpecialBoxThingDAL);
	}

	public static DictEquipAdvanceDAL getDictEquipAdvanceDAL() {
		return (DictEquipAdvanceDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipAdvanceDAL);
	}

	public static DictBeautyCardDAL getDictBeautyCardDAL() {
		return (DictBeautyCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictBeautyCardDAL);
	}

	public static DictSpecialRuleDAL getDictSpecialRuleDAL() {
		return (DictSpecialRuleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictSpecialRuleDAL);
	}

	public static DictPagodaCardDAL getDictPagodaCardDAL() {
		return (DictPagodaCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPagodaCardDAL);
	}

	public static InstPlayerCardSoulDAL getInstPlayerCardSoulDAL() {
		return (InstPlayerCardSoulDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerCardSoulDAL);
	}

	public static DictFireExpDAL getDictFireExpDAL() {
		return (DictFireExpDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFireExpDAL);
	}

	public static DictActivityLimitTimeHeroJiFenRewardDAL getDictActivityLimitTimeHeroJiFenRewardDAL() {
		return (DictActivityLimitTimeHeroJiFenRewardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityLimitTimeHeroJiFenRewardDAL);
	}

	public static DictUnionBuildDAL getDictUnionBuildDAL() {
		return (DictUnionBuildDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictUnionBuildDAL);
	}

	public static DictEquipTypeDAL getDictEquipTypeDAL() {
		return (DictEquipTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipTypeDAL);
	}

	public static DictActivityLevelBagDAL getDictActivityLevelBagDAL() {
		return (DictActivityLevelBagDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityLevelBagDAL);
	}

	public static InstUnionDynamicDAL getInstUnionDynamicDAL() {
		return (InstUnionDynamicDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstUnionDynamicDAL);
	}

	public static InstActivitySignInDAL getInstActivitySignInDAL() {
		return (InstActivitySignInDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstActivitySignInDAL);
	}

	public static DictChipDAL getDictChipDAL() {
		return (DictChipDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictChipDAL);
	}

	public static InstPlayerRecruitDAL getInstPlayerRecruitDAL() {
		return (InstPlayerRecruitDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerRecruitDAL);
	}

	public static DictActivityOnlineRewardsDAL getDictActivityOnlineRewardsDAL() {
		return (DictActivityOnlineRewardsDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityOnlineRewardsDAL);
	}

	public static DictDantaLayerDAL getDictDantaLayerDAL() {
		return (DictDantaLayerDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictDantaLayerDAL);
	}

	public static SysCdKeyDAL getSysCdKeyDAL() {
		return (SysCdKeyDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysCdKeyDAL);
	}

	public static InstPlayerAchievementDAL getInstPlayerAchievementDAL() {
		return (InstPlayerAchievementDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerAchievementDAL);
	}

	public static InstPlayerRechargeDAL getInstPlayerRechargeDAL() {
		return (InstPlayerRechargeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerRechargeDAL);
	}

	public static DictLootChipDAL getDictLootChipDAL() {
		return (DictLootChipDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictLootChipDAL);
	}

	public static DictRechargeDAL getDictRechargeDAL() {
		return (DictRechargeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictRechargeDAL);
	}

	public static DictRecruitTimesGetDAL getDictRecruitTimesGetDAL() {
		return (DictRecruitTimesGetDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictRecruitTimesGetDAL);
	}

	public static DictGuipStepDAL getDictGuipStepDAL() {
		return (DictGuipStepDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictGuipStepDAL);
	}

	public static InstPlayerPagodaDAL getInstPlayerPagodaDAL() {
		return (InstPlayerPagodaDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerPagodaDAL);
	}

	public static DictBarrierDAL getDictBarrierDAL() {
		return (DictBarrierDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictBarrierDAL);
	}

	public static DictKungFuQualityDAL getDictKungFuQualityDAL() {
		return (DictKungFuQualityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictKungFuQualityDAL);
	}

	public static InstPlayerPartnerDAL getInstPlayerPartnerDAL() {
		return (InstPlayerPartnerDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerPartnerDAL);
	}

	public static InstPlayerAchievementValueDAL getInstPlayerAchievementValueDAL() {
		return (InstPlayerAchievementValueDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerAchievementValueDAL);
	}

	public static InstPlayerMailDAL getInstPlayerMailDAL() {
		return (InstPlayerMailDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerMailDAL);
	}

	public static InstActivityLevelBagDAL getInstActivityLevelBagDAL() {
		return (InstActivityLevelBagDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstActivityLevelBagDAL);
	}

	public static DictTrainPropDAL getDictTrainPropDAL() {
		return (DictTrainPropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictTrainPropDAL);
	}

	public static InstPlayerKungFuDAL getInstPlayerKungFuDAL() {
		return (InstPlayerKungFuDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerKungFuDAL);
	}

	public static InstPlayerChapterDAL getInstPlayerChapterDAL() {
		return (InstPlayerChapterDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerChapterDAL);
	}

	public static DictPillDAL getDictPillDAL() {
		return (DictPillDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPillDAL);
	}

	public static DictBarrierCardDAL getDictBarrierCardDAL() {
		return (DictBarrierCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictBarrierCardDAL);
	}

	public static DictKungFuDAL getDictKungFuDAL() {
		return (DictKungFuDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictKungFuDAL);
	}

	public static InstPlayerRechargeFailDAL getInstPlayerRechargeFailDAL() {
		return (InstPlayerRechargeFailDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerRechargeFailDAL);
	}

	public static DictPillQualityDAL getDictPillQualityDAL() {
		return (DictPillQualityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPillQualityDAL);
	}

	public static DictactivityTotalCostDAL getDictactivityTotalCostDAL() {
		return (DictactivityTotalCostDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictactivityTotalCostDAL);
	}

	public static InstPlayerPrivateSaleDAL getInstPlayerPrivateSaleDAL() {
		return (InstPlayerPrivateSaleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerPrivateSaleDAL);
	}

	public static InstAuctionShopDAL getInstAuctionShopDAL() {
		return (InstAuctionShopDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstAuctionShopDAL);
	}

	public static InstPlayerArenaNPCDAL getInstPlayerArenaNPCDAL() {
		return (InstPlayerArenaNPCDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerArenaNPCDAL);
	}

	public static SysMsgRuleDAL getSysMsgRuleDAL() {
		return (SysMsgRuleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysMsgRuleDAL);
	}

	public static DictGenerBoxThingDAL getDictGenerBoxThingDAL() {
		return (DictGenerBoxThingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictGenerBoxThingDAL);
	}

	public static DictRecruitSpecialCardDAL getDictRecruitSpecialCardDAL() {
		return (DictRecruitSpecialCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictRecruitSpecialCardDAL);
	}

	public static SysStaticsDAL getSysStaticsDAL() {
		return (SysStaticsDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysStaticsDAL);
	}

	public static DictActivityDailyDealsDAL getDictActivityDailyDealsDAL() {
		return (DictActivityDailyDealsDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityDailyDealsDAL);
	}

	public static DictFunctionOpenDAL getDictFunctionOpenDAL() {
		return (DictFunctionOpenDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFunctionOpenDAL);
	}

	public static DictTryToPracticeBarrierCardDAL getDictTryToPracticeBarrierCardDAL() {
		return (DictTryToPracticeBarrierCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictTryToPracticeBarrierCardDAL);
	}

	public static DictQualityDAL getDictQualityDAL() {
		return (DictQualityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictQualityDAL);
	}

	public static DictPagodaFormationDAL getDictPagodaFormationDAL() {
		return (DictPagodaFormationDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPagodaFormationDAL);
	}

	public static DictColorDAL getDictColorDAL() {
		return (DictColorDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictColorDAL);
	}

	public static InstBlobDataDAL getInstBlobDataDAL() {
		return (InstBlobDataDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstBlobDataDAL);
	}

	public static DictHoleConsumeDAL getDictHoleConsumeDAL() {
		return (DictHoleConsumeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictHoleConsumeDAL);
	}

	public static DictPagodaStoreDAL getDictPagodaStoreDAL() {
		return (DictPagodaStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPagodaStoreDAL);
	}

	public static InstUnionStoreDAL getInstUnionStoreDAL() {
		return (InstUnionStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstUnionStoreDAL);
	}

	public static InstPlayerManualSkillLineDAL getInstPlayerManualSkillLineDAL() {
		return (InstPlayerManualSkillLineDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerManualSkillLineDAL);
	}

	public static DictActivitySignInDAL getDictActivitySignInDAL() {
		return (DictActivitySignInDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivitySignInDAL);
	}

	public static DictUnionBoxDAL getDictUnionBoxDAL() {
		return (DictUnionBoxDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictUnionBoxDAL);
	}

	public static DictManualSkillDAL getDictManualSkillDAL() {
		return (DictManualSkillDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictManualSkillDAL);
	}

	public static InstPlayerCardDAL getInstPlayerCardDAL() {
		return (InstPlayerCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerCardDAL);
	}

	public static DictCardExpAddDAL getDictCardExpAddDAL() {
		return (DictCardExpAddDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictCardExpAddDAL);
	}

	public static InstPlayerLootDAL getInstPlayerLootDAL() {
		return (InstPlayerLootDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerLootDAL);
	}

	public static InstUnionBoxDAL getInstUnionBoxDAL() {
		return (InstUnionBoxDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstUnionBoxDAL);
	}

	public static DictVIPDAL getDictVIPDAL() {
		return (DictVIPDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictVIPDAL);
	}

	public static InstRankDantaDayDAL getInstRankDantaDayDAL() {
		return (InstRankDantaDayDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstRankDantaDayDAL);
	}

	public static DictSysConfigStrDAL getDictSysConfigStrDAL() {
		return (DictSysConfigStrDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictSysConfigStrDAL);
	}

	public static DictActivityAllPeapleWealDAL getDictActivityAllPeapleWealDAL() {
		return (DictActivityAllPeapleWealDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityAllPeapleWealDAL);
	}

	public static DictAuctionShopDAL getDictAuctionShopDAL() {
		return (DictAuctionShopDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictAuctionShopDAL);
	}

	public static DictActivityPrivateSaleDAL getDictActivityPrivateSaleDAL() {
		return (DictActivityPrivateSaleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityPrivateSaleDAL);
	}

	public static DictCardBasePropDAL getDictCardBasePropDAL() {
		return (DictCardBasePropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictCardBasePropDAL);
	}

	public static DictTitleDAL getDictTitleDAL() {
		return (DictTitleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictTitleDAL);
	}

	public static DictActivityTreasuresDAL getDictActivityTreasuresDAL() {
		return (DictActivityTreasuresDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityTreasuresDAL);
	}

	public static SysCdKeyTypeDAL getSysCdKeyTypeDAL() {
		return (SysCdKeyTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysCdKeyTypeDAL);
	}

	public static InstUnionDAL getInstUnionDAL() {
		return (InstUnionDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstUnionDAL);
	}

	public static InstPlayerAwardDAL getInstPlayerAwardDAL() {
		return (InstPlayerAwardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerAwardDAL);
	}

	public static SysActivityDAL getSysActivityDAL() {
		return (SysActivityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysActivityDAL);
	}

	public static InstUnionApplyDAL getInstUnionApplyDAL() {
		return (InstUnionApplyDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstUnionApplyDAL);
	}

	public static DictArenaDropDAL getDictArenaDropDAL() {
		return (DictArenaDropDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictArenaDropDAL);
	}

	public static InstActivityTreasuresDAL getInstActivityTreasuresDAL() {
		return (InstActivityTreasuresDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstActivityTreasuresDAL);
	}

	public static DictRecruitCardDAL getDictRecruitCardDAL() {
		return (DictRecruitCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictRecruitCardDAL);
	}

	public static InstPlayerChipDAL getInstPlayerChipDAL() {
		return (InstPlayerChipDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerChipDAL);
	}

	public static InstPlayerFireTempDAL getInstPlayerFireTempDAL() {
		return (InstPlayerFireTempDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerFireTempDAL);
	}

	public static DictActivityStarStoreDAL getDictActivityStarStoreDAL() {
		return (DictActivityStarStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityStarStoreDAL);
	}

	public static InstPlayerConstellDAL getInstPlayerConstellDAL() {
		return (InstPlayerConstellDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerConstellDAL);
	}

	public static DictEquipSuitDAL getDictEquipSuitDAL() {
		return (DictEquipSuitDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipSuitDAL);
	}

	public static DictCoefficientDAL getDictCoefficientDAL() {
		return (DictCoefficientDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictCoefficientDAL);
	}

	public static DictAuctionHJYDAL getDictAuctionHJYDAL() {
		return (DictAuctionHJYDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictAuctionHJYDAL);
	}

	public static DictConstellDAL getDictConstellDAL() {
		return (DictConstellDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictConstellDAL);
	}

	public static SysFilterWordsDAL getSysFilterWordsDAL() {
		return (SysFilterWordsDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysFilterWordsDAL);
	}

	public static InstCdKeyGetRecordDAL getInstCdKeyGetRecordDAL() {
		return (InstCdKeyGetRecordDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstCdKeyGetRecordDAL);
	}

	public static DictChapterActivityVipDAL getDictChapterActivityVipDAL() {
		return (DictChapterActivityVipDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictChapterActivityVipDAL);
	}

	public static InstPlayerEquipDAL getInstPlayerEquipDAL() {
		return (InstPlayerEquipDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerEquipDAL);
	}

	public static DictMagicDAL getDictMagicDAL() {
		return (DictMagicDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictMagicDAL);
	}

	public static InstPlayerRechargeCallBackDAL getInstPlayerRechargeCallBackDAL() {
		return (InstPlayerRechargeCallBackDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerRechargeCallBackDAL);
	}

	public static DictDailyTaskDAL getDictDailyTaskDAL() {
		return (DictDailyTaskDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictDailyTaskDAL);
	}

	public static InstPlayerCardsDAL getInstPlayerCardsDAL() {
		return (InstPlayerCardsDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerCardsDAL);
	}

	public static InstPlayerBeautyCardDAL getInstPlayerBeautyCardDAL() {
		return (InstPlayerBeautyCardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerBeautyCardDAL);
	}

	public static DictTryToPracticeTypeDAL getDictTryToPracticeTypeDAL() {
		return (DictTryToPracticeTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictTryToPracticeTypeDAL);
	}

	public static InstPlayerPillDAL getInstPlayerPillDAL() {
		return (InstPlayerPillDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerPillDAL);
	}

	public static DictFireSkillChangeDAL getDictFireSkillChangeDAL() {
		return (DictFireSkillChangeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFireSkillChangeDAL);
	}

	public static DictHJYStoreDAL getDictHJYStoreDAL() {
		return (DictHJYStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictHJYStoreDAL);
	}

	public static DictActivityMonthCardStoreTempDAL getDictActivityMonthCardStoreTempDAL() {
		return (DictActivityMonthCardStoreTempDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityMonthCardStoreTempDAL);
	}

	public static DictUnionStoreDAL getDictUnionStoreDAL() {
		return (DictUnionStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictUnionStoreDAL);
	}

	public static InstPlayerStoreDAL getInstPlayerStoreDAL() {
		return (InstPlayerStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerStoreDAL);
	}

	public static InstChapterActivityDAL getInstChapterActivityDAL() {
		return (InstChapterActivityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstChapterActivityDAL);
	}

	public static InstUserDAL getInstUserDAL() {
		return (InstUserDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstUserDAL);
	}

	public static InstPlayerChapterTypeDAL getInstPlayerChapterTypeDAL() {
		return (InstPlayerChapterTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerChapterTypeDAL);
	}

	public static DictPillTypeDAL getDictPillTypeDAL() {
		return (DictPillTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPillTypeDAL);
	}

	public static InstPlayerWashDAL getInstPlayerWashDAL() {
		return (InstPlayerWashDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerWashDAL);
	}

	public static DictFireSkillQualityDAL getDictFireSkillQualityDAL() {
		return (DictFireSkillQualityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFireSkillQualityDAL);
	}

	public static InstPlayerFormationDAL getInstPlayerFormationDAL() {
		return (InstPlayerFormationDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerFormationDAL);
	}

	public static DictArenaConvertDAL getDictArenaConvertDAL() {
		return (DictArenaConvertDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictArenaConvertDAL);
	}

	public static DictFireDAL getDictFireDAL() {
		return (DictFireDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictFireDAL);
	}

	public static DictThingExtendDAL getDictThingExtendDAL() {
		return (DictThingExtendDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictThingExtendDAL);
	}

	public static InstActivityOnlineRewardsDAL getInstActivityOnlineRewardsDAL() {
		return (InstActivityOnlineRewardsDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstActivityOnlineRewardsDAL);
	}

	public static InstPlayerResolveTempDAL getInstPlayerResolveTempDAL() {
		return (InstPlayerResolveTempDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerResolveTempDAL);
	}

	public static InstHJYStoreDAL getInstHJYStoreDAL() {
		return (InstHJYStoreDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstHJYStoreDAL);
	}

	public static DictBarrierDropTypeDAL getDictBarrierDropTypeDAL() {
		return (DictBarrierDropTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictBarrierDropTypeDAL);
	}

	public static DictEquipQualityDAL getDictEquipQualityDAL() {
		return (DictEquipQualityDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipQualityDAL);
	}

	public static DictTryToPracticeDAL getDictTryToPracticeDAL() {
		return (DictTryToPracticeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictTryToPracticeDAL);
	}

	public static DictSysConfigDAL getDictSysConfigDAL() {
		return (DictSysConfigDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictSysConfigDAL);
	}

	public static DictThingDAL getDictThingDAL() {
		return (DictThingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictThingDAL);
	}

	public static DictTableTypeDAL getDictTableTypeDAL() {
		return (DictTableTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictTableTypeDAL);
	}

	public static DictDantaMonsterDAL getDictDantaMonsterDAL() {
		return (DictDantaMonsterDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictDantaMonsterDAL);
	}

	public static InstPlayerBarrierDAL getInstPlayerBarrierDAL() {
		return (InstPlayerBarrierDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerBarrierDAL);
	}

	public static InstPlayerArenaDAL getInstPlayerArenaDAL() {
		return (InstPlayerArenaDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerArenaDAL);
	}

	public static DictBeautyCardFightDAL getDictBeautyCardFightDAL() {
		return (DictBeautyCardFightDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictBeautyCardFightDAL);
	}

	public static DictActivityFundDAL getDictActivityFundDAL() {
		return (DictActivityFundDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityFundDAL);
	}

	public static DictPagodaStoreyDAL getDictPagodaStoreyDAL() {
		return (DictPagodaStoreyDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictPagodaStoreyDAL);
	}

	public static DictEquipSuitReferDAL getDictEquipSuitReferDAL() {
		return (DictEquipSuitReferDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipSuitReferDAL);
	}

	public static InstPlayerBigTableDAL getInstPlayerBigTableDAL() {
		return (InstPlayerBigTableDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerBigTableDAL);
	}

	public static InstActivityOpenServiceBagDAL getInstActivityOpenServiceBagDAL() {
		return (InstActivityOpenServiceBagDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstActivityOpenServiceBagDAL);
	}

	public static DictMagicLevelDAL getDictMagicLevelDAL() {
		return (DictMagicLevelDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictMagicLevelDAL);
	}

	public static InstPlayerPillRecipeDAL getInstPlayerPillRecipeDAL() {
		return (InstPlayerPillRecipeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerPillRecipeDAL);
	}

	public static InstPlayerDAL getInstPlayerDAL() {
		return (InstPlayerDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerDAL);
	}

	public static DictArenaAdvanceDAL getDictArenaAdvanceDAL() {
		return (DictArenaAdvanceDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictArenaAdvanceDAL);
	}

	public static DictCardTypeDAL getDictCardTypeDAL() {
		return (DictCardTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictCardTypeDAL);
	}

	public static DictEquipStrengthenDAL getDictEquipStrengthenDAL() {
		return (DictEquipStrengthenDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipStrengthenDAL);
	}

	public static DictActivityFlashSaleDAL getDictActivityFlashSaleDAL() {
		return (DictActivityFlashSaleDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictActivityFlashSaleDAL);
	}

	public static InstWorldBossRankDAL getInstWorldBossRankDAL() {
		return (InstWorldBossRankDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstWorldBossRankDAL);
	}

	public static DictThingTypeDAL getDictThingTypeDAL() {
		return (DictThingTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictThingTypeDAL);
	}

	public static InstPlayerTrainDAL getInstPlayerTrainDAL() {
		return (InstPlayerTrainDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerTrainDAL);
	}

	public static DictCardLuckDAL getDictCardLuckDAL() {
		return (DictCardLuckDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictCardLuckDAL);
	}

	public static DictArenaRewardDAL getDictArenaRewardDAL() {
		return (DictArenaRewardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictArenaRewardDAL);
	}

	public static InstPlayerGmRewardDAL getInstPlayerGmRewardDAL() {
		return (InstPlayerGmRewardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerGmRewardDAL);
	}

	public static DictStarLevelDAL getDictStarLevelDAL() {
		return (DictStarLevelDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictStarLevelDAL);
	}

	public static InstLuckRankDAL getInstLuckRankDAL() {
		return (InstLuckRankDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstLuckRankDAL);
	}

	public static DictAchievementDAL getDictAchievementDAL() {
		return (DictAchievementDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictAchievementDAL);
	}

	public static DictLootNPCDAL getDictLootNPCDAL() {
		return (DictLootNPCDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictLootNPCDAL);
	}

	public static DictEquipWashDAL getDictEquipWashDAL() {
		return (DictEquipWashDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipWashDAL);
	}

	public static InstPlayerMineDAL getInstPlayerMineDAL() {
		return (InstPlayerMineDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerMineDAL);
	}

	public static DictMineTypeDAL getDictMineTypeDAL() {
		return (DictMineTypeDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictMineTypeDAL);
	}

	public static DictMineBoxThingDAL getDictMineBoxThingDAL() {
		return (DictMineBoxThingDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictMineBoxThingDAL);
	}

	public static InstPlayerMineAwardDAL getInstPlayerMineAwardDAL() {
		return (InstPlayerMineAwardDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerMineAwardDAL);
	}

	public static DictMineWeatherDAL getDictMineWeatherDAL() {
		return (DictMineWeatherDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictMineWeatherDAL);
	}

	public static DictYFireDAL getDictYFireDAL() {
		return (DictYFireDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictYFireDAL);
	}

	public static InstPlayerYFireDAL getInstPlayerYFireDAL() {
		return (InstPlayerYFireDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerYFireDAL);
	}

	public static DictYFireChipDAL getDictYFireChipDAL() {
		return (DictYFireChipDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictYFireChipDAL);
	}

	public static DictEquipAdvanceredDAL getDictEquipAdvanceredDAL() {
		return (DictEquipAdvanceredDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipAdvanceredDAL);
	}

	public static DictEquipSuitredDAL getDictEquipSuitredDAL() {
		return (DictEquipSuitredDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictEquipSuitredDAL);
	}

	public static InstPlayerRedEquipDAL getInstPlayerRedEquipDAL() {
		return (InstPlayerRedEquipDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerRedEquipDAL);
	}

	public static DictMagicrefiningDAL getDictMagicrefiningDAL() {
		return (DictMagicrefiningDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictMagicrefiningDAL);
	}

	public static DictUnionWarAmbushDAL getDictUnionWarAmbushDAL() {
		return (DictUnionWarAmbushDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictUnionWarAmbushDAL);
	}

	public static DictUnionWarBattlefieldDAL getDictUnionWarBattlefieldDAL() {
		return (DictUnionWarBattlefieldDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictUnionWarBattlefieldDAL);
	}

	public static DictUnionWarInfoDAL getDictUnionWarInfoDAL() {
		return (DictUnionWarInfoDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.DictUnionWarInfoDAL);
	}

	public static InstUnionWarAgainstDAL getInstUnionWarAgainstDAL() {
		return (InstUnionWarAgainstDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstUnionWarAgainstDAL);
	}

	public static InstUnionWarContributionRankDAL getInstUnionWarContributionRankDAL() {
		return (InstUnionWarContributionRankDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstUnionWarContributionRankDAL);
	}

	public static InstPlayerChrisDAL getInstPlayerChrisDAL() {
		return (InstPlayerChrisDAL)SpringUtil.GetObjectWithSpringContext(SpringDalContext.InstPlayerChrisDAL);
	}

}