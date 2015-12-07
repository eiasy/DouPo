package com.huayi.doupo.base.model.statics;

import com.huayi.doupo.base.util.logic.system.DictMapUtil;

public class StaticCnServer {
	
	public static final String succ = new String("成功");

	public static final String fail = new String("失败");

	public static final String fail_concurrent = new String("并发错误");

	public static final String fail_data = new String("网络信号不好");

	public static final String fail_openIdOtherLogin = new String("账号在异地登录");

	public static final String fail_onlyOneInitCard = new String("只能初始化一次卡牌");

	public static final String succ_heart = new String("心跳检测");

	public static final String fail_differentPlayers = new String("所属玩家不一致");

	public static final String fail_cardInFormation = new String("此卡牌已上阵");

	public static final String fail_equipInLineup = new String("此装备已被使用");

	public static final String fail_cardListInFormation = new String("不可包含已上阵的卡牌");

	public static final String fail_cardOneself = new String("不可包含卡牌本身");

	public static final String fail_streng_level = new String("已强化到最大等级");

	public static final String fail_copperNotEnough = new String("银币不足");

	public static final String fail_openHole_upMaxNum = new String("已达到最大开孔数");

	public static final String fail_inlay_notOpenHole = new String("请先打孔");

	public static final String fail_levelNotUp = new String("等级不足");

	public static final String fail_gemNum_notEnough = new String("所需魔核不足");

	public static final String fail_notEquipType = new String("装备类型不一致");

	public static final String fail_openHole_notEnough = new String("打孔器不足");

	public static final String fail_cardRepeat = new String("上阵卡牌不可重复");

	public static final String fail_posRepeat = new String("此位置已有其他卡牌");

	public static final String fail_operError = new String("操作错误");

	public static final String fail_numNotEnough = new String("数量不足");

	public static final String fail_notThingNum = new String("所需物品数量不足");

	public static final String fail_equipNotInLineup = new String("此装备已被卸下");

	public static final String fail_notlindenLNum = new String("菩提子不足");

	public static final String fail_goldNotEnough = new String("元宝不足");

	public static final String fail_notCardLevel = new String("卡牌等级不足");

	public static final String fail_notCardTitle = new String("卡牌境界不足");

	public static final String fail_maxKungFu = new String("功法已达到最大等级");

	public static final String fail_useKungFu = new String("功法已被使用");

	public static final String fail_notCultureNum = new String("火能石不足");

	public static final String fail_notKungFuExp = new String("功法经验值不足");

	public static final String fail_notFireCrystalNum = new String("火晶不足");

	public static final String fail_notFireLevel = new String("异火等级不足");

	public static final String fail_fireOneself = new String("不可包含异火本身");

	public static final String fail_fireInheritOneself = new String("不可传承本身");

	public static final String fail_notFireInherit = new String("只可用异火来传承");

	public static final String fail_notFireUse = new String("只有异火可以上阵");

	public static final String fail_notFireSell = new String("只有兽火可以出售");

	public static final String fail_bagNotEnough = new String("背包容量不足");

	public static final String fail_paramError = new String("参数错误");

	public static final String fail_noResolveThing = new String("没有可分解的物品");

	public static final String fail_constellPositionUse = new String("此位置已填充命宫丹");

	public static final String fail_notPillNum = new String("丹药不足");

	public static final String fail_timesNull = new String("次数用完,提升VIP等级可增加购买次数");

	public static final String fail_buyRepeat = new String("不可重复购买");

	public static final String fail_noUpVip = new String("VIP等级不足");

	public static final String fail_notPillRecipeNum = new String("丹药药方不足");

	public static final String fail_notPillThingNum = new String("丹药材料不足");

	public static final String fail_notResetNum = new String("重置次数不足,请提升VIP等级");

	public static final String fail_mopNum = new String("未达到扫荡条件");

	public static final String fail_notPagodaStore = new String("条件未达到");

	public static final String fail_notMopNum = new String("已达到当前最高层");

	public static final String fail_notSearchNum = new String("搜寻紫妍次数不足,请提升VIP等级");

	public static final String fail_upLevel = new String("已达最高等级");

	public static final String fail_finishTimes = new String("次数已用完");

	public static final String fail_notUpTime = new String("时间未到");

	public static final String fail_notEnoughRecruitSign = new String("兑换令不足");

	public static final String fail_commonWarNotOpen = new String("未达到战斗资格");

	public static final String fail_notEnergy = new String("体力不足");

	public static final String fail_notPlayerLevel = new String("等级不足");

	public static final String fail_notStarNum = new String("星数不足");

	public static final String fail_notFightNum = new String("战斗次数不足");

	public static final String fail_commonWarNotTime = new String("冷却时间未到");

	public static final String fail_notBuyEliteFightNum = new String("购买次数不足");

	public static final String fail_notGetStarType = new String("已领取");

	public static final String fail_talkForbid = new String("您已被禁言");

	public static final String fail_whiteNoAdvance = new String("绿卡不可进阶");

	public static final String fail_advanceNoCon = new String("进阶条件不满足");

	public static final String fail_cardNumNotEnough = new String("指定卡牌张数不足");

	public static final String fail_titleLevelNotEnough = new String("称号等级不足");

	public static final String fail_constellNotOpen = new String("命宫未开启");

	public static final String fail_formationFull = new String("阵型已满,不可再上阵");

	public static final String fail_notPeace = new String("和平牌不足");

	public static final String fail_lootHavaChip = new String("已拥有此碎片");

	public static final String fail_notLootChip = new String("该玩家碎片已被抢走,请选择其他玩家");

	public static final String fail_notLootHavaChip = new String("碎片不足");

	public static final String fail_notVigor = new String("耐力不足，请服用耐力丹");

	public static final String fail_notLoot = new String("该玩家已进入和平镇,请选择其他玩家");

	public static final String fail_notManualSkillRepeat = new String("不可装备重复技能");

	public static final String fail_manualSkillIsUse = new String("不能包含已装备的技能");

	public static final String fail_cardIsLock = new String("此卡牌已锁定");

	public static final String fail_notWashRockNum = new String("洗练石不足");

	public static final String fail_notLockNum = new String("洗练锁不足");

	public static final String fail_notSoulSourceNum = new String("魂源不足");

	public static final String fail_openServiceBag = new String("已领取");

	public static final String fail_notOpenServiceBag = new String("不可领取");

	public static final String fail_buyUpLimit = new String("超过上限");

	public static final String fail_notArenaNum = new String("挑战次数不足");

	public static final String fail_arenaNum = new String("挑战次数未用完");

	public static final String fail_notLootOpen = new String("抢夺开始时间未到");

	public static final String fail_notPrestige = new String("威望不足");

	public static final String fail_notConvertNum = new String("兑换次数不足");

	public static final String fail_materialNum = new String("材料不足");

	public static final String fail_closeServer = new String("停服维护中");

	public static final String fail_filtername = new String("名字中含有敏感字符");

	public static final String fail_playerName_Rule = new String("请勿输入特殊字符");

	public static final String fail_havePlayer = new String("已有此名字的玩家");

	public static final String fail_magicIsUse = new String("不能包含已装备的法宝功法");

	public static final String fail_magicOneself = new String("不可包含法宝功法本身");

	public static final String fail_onFormat = new String("所选物品已在阵上");

	public static final String fail_noFormat = new String("所选物品未在阵上");

	public static final String fail_haveWash = new String("您已领取");

	public static final String fail_noWashTime = new String("洗澡时间还没到");

	public static final String fail_noCommonTime = new String("时间不一致");

	public static final String fail_noFinishTimes = new String("未达到任务完成次数");

	public static final String fail_noOperationWorldBoss = new String("操作失误，请退出血战魂殿重新进入");

	public static final String fail_copperInspire = new String("银币鼓舞只有一次机会，不可再次鼓舞");

	public static final String fail_inspire = new String("士气已满，不能继续鼓舞");

	public static final String fail_haveGive = new String("此步骤已给相应物品");

	public static final String fail_noAchievement = new String("成就未达成不可领取");

	public static final String fail_boxOrKeyNumNotEnough = new String("宝箱或钥匙数量不足");

	public static final String fail_boxOrKeyNumNotEnough2 = new String("礼盒数量不足");

	public static final String fail_maxOpenBoxNum = new String("单次最大开箱子数为" + DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxOpenBoxNum) + ", 大于时,请分多次开");

	public static final String fail_maxOpenBoxNum2 = new String("单次最大开礼盒数为" + DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxOpenBoxNum) + ", 大于时,请分多次开");

	public static final String fail_noThing = new String("没有此物品");

	public static final String fail_loginValidate = new String("登录验证未通过");

	public static final String fail_vipLevel = new String("VIP等级不足");

	public static final String fail_noCanSell = new String("在阵功法/法宝不可出售");

	public static final String fail_noPullBlack = new String("本次战斗无效，请重新战斗");

	public static final String fail_notWelfareBox = new String("不可领取");

	public static final String fail_equipHaveCore = new String("嵌有魔核，请先将魔核卸掉再出售");

	public static final String fail_unBlock = new String("暂未邂逅");

	public static final String fail_unConquer = new String("未征服");

	public static final String fail_saveAmt = new String("调用腾讯查询余额接口失败");

	public static final String fail_recharge = new String("请充值");

	public static final String fail_reLogin = new String("系统繁忙,请重新登录,验证到账情况");

	public static final String fail_recharge_queue = new String("暂未到账,请耐心等待1分钟或重新登录");

	public static final String fail_notLinger = new String("两情若在久长时，又岂在朝朝暮暮，请明日再来");

	public static final String fail_notOper = new String("操作频繁");

	public static final String fail_sellOut = new String("物品已售罄");

	public static final String fail_frozen = new String("账号已冻结, 解除联系管理员.");

	public static final String fail_cdkTypeOnlyOne = new String("此种类型兑换码玩家只能使用一次");

	public static final String fail_cdkGuoQi = new String("兑换码已过期");

	public static final String fail_cdkHaveUsed = new String("此兑换码已被使用");

	public static final String fail_serverBao = new String("服务器爆满!请选择其他服务器");

	public static final String fail_noFund = new String("您未购买基金");

	public static final String fail_noActivityDate = new String("未在活动期内");

	public static final String fail_dataError = new String("数据错误");

	public static final String fail_maxPage = new String("超过规定最大页数");

	public static final String fail_todayGoods = new String("非本日出售商品");

	public static final String fail_todayBuy = new String("今日已购买");

	public static final String fail_upLimitNum = new String("超过今日最大购买次数");

	public static final String fail_monthCard = new String("请先购买月卡");

	public static final String fail_noTryToPractice = new String("试练日条件未达到");

	public static final String fail_getRechargeOrder = new String("获取订单号失败");

	public static final String fail_recharge_status = new String("充值签名失败");

	public static final String fail_notApplyAddUnion = new String("该联盟成员人数已满");

	public static final String fail_notApplyAddUnionNum = new String("已达到同时可申请最大联盟数");

	public static final String fail_notApply = new String("无此权限");

	public static final String fail_notUnionName = new String("该联盟名称已存在");

	public static final String fail_notAppointUnion = new String("该职位人数已满");

	public static final String fail_notUnionBuild = new String("今日已建设");

	public static final String fail_notUnionLevel = new String("联盟等级不足");

	public static final String fail_inUnion = new String("已加入其他联盟");

	public static final String fail_notUnionApply = new String("24小时内不能申请加入联盟");

	public static final String fail_unionRefresh = new String("已不在联盟中");

	public static final String fail_notUnionOffer = new String("贡献不足");

	public static final String fail_chatLength = new String("少侠,您废话太多,请少于50字");

	public static final String fail_chatNoOnline = new String("玩家不在线或不存在");

	public static final String fail_chatNoMyself = new String("您要和自己聊天吗?");

	public static final String fail_chatNoUnion = new String("联盟不存在");

	public static final String fail_chatNoCd = new String("cd时间未过");

	public static final String fail_mailNoOnline = new String("未在线");

	public static final String fail_mailNoPlayer = new String("无此玩家或玩家已更名");

	public static final String fail_mailContentUpMaxNum = new String("超过最大字数");

	public static final String fail_webNotGood = new String("网络信号不好,请重试");

	public static final String fail_mailNoMyself = new String("您要给自己发邮件吗");

	public static final String fail_linkLoginServer = new String("连接登录服失败");

	public static final String fail_magicCoreNumNotEnough = new String("魔核数量不足");

	public static final String fail_magicstreng_level = new String("等级不能超过战队等级");

	public static final String fail_allPeapleWeal_NotEnough = new String("购买基金总人数不够");

	public static final String fail_addRechargeId_error = new String("请刷新后重试");

	public static final String fail_recharge_not_enough = new String("今天充值金额不够");

	public static final String fail_award_gotten = new String("已经领取过了");
	
	public static final String fail_monthCardUndue = new String("您购买的月卡还未到期");
	
	public static final String fail_noMonthCard = new String("您还未购买月卡");

	public static final String fail_noMonthCardPastDule = new String("您购买的月卡已过期");

	public static final String fail_monthCardHaveGetThing = new String("已领取月卡额外赠送物品");
	
	public static final String fail_PlayerIdVerfy = new String("非法操作,请重新登录");
	
	public static final String fail_activityOver = new String("活动已结束");
	
	public static final String fail_LootNoTime = new String("未在抢夺时间内");
	
	public static final String fail_haveMagicCore = new String("被吞噬的装备上嵌有魔核,无法完成进阶");

	public static final String fail_noInActivityDate = new String("未在活动期内");
	
	public static final String fail_equipUpAdavance = new String("已进阶到顶级");
	
	public static final String fail_treasuresMax = new String("招财进宝没有可用次数");
	
	public static final String fail_treasuresError = new String("招财进宝数据异常");
	
	public static final String fail_thingNoBuy = new String("此物品为非购买物品");
	
	public static final String fail_activityOutDate = new String("此活动已过期");

	public static final String fail_worldBossNoStart = new String("世界Boss未开启");
	
	public static final String fail_worldBossDataVerfy = new String("您的数据校验不符，请重新登录");
	
	public static final String fail_haveName = new String("您已有名字");
	
	public static final String fail_upBuyNum = new String("超过购买个数");
	
	public static final String fail_operTooFast = new String("您操作过于频繁");
	
	public static final String fail_ownNoChip = new String("您无此碎片,所以无法抢夺其他人的这个碎片");
	
	public static final String fail_haveThisChip = new String("您已有此碎片,无法进行抢夺");

	public static final String fail_mineRefresh = new String("资源矿有变动，刷新一下");
	
	public static final String fail_fightSoul_noLight = new String("此位置未点亮");

	public static final String fail_fightSoul_noCanLight = new String("此位置不可被点亮");
	
	public static final String fail_fightSoul_haveLight = new String("此位置已点亮");
	
	public static final String fail_fightSoul_noSell = new String("无可出售的斗魂");
	
	public static final String fail_fightSoul_upBagUpLimit = new String("超过背包上限,请先出售或升级,再进行猎魂");

	public static final String fail_fightSoul_silverFightSoulNoLock = new String("银魂不可被锁定");

	public static final String fail_fightSoul_expFightSoulNoLock = new String("经验斗魂不可被锁定");

	public static final String fail_fightSoul_fightSoulHaveLock = new String("此斗魂已被锁定");
	
	public static final String fail_fightSoul_expFightSoulNoStick = new String("经验斗魂不可被附魂");
	
	public static final String fail_fightSoul_silverFightSoulNoStick = new String("银魂不可被附魂");

	public static final String fail_fightSoul_fightSoulHaveStick = new String("此斗魂已被附魂");
	
	public static final String fail_fightSoul_noHaveFightSoul = new String("无此斗魂");
	
	public static final String fail_fightSoul_posNoHaveFightSoul = new String("此卡牌此位置上无此斗魂");
	
	public static final String fail_fightSoul_silverFightSoulNoUpgrade = new String("银魂不可升级");
	
	public static final String fail_fightSoul_silverFightSoulNoUpgraded = new String("银魂不可被升级");

	public static final String fail_fightSoul_expFightSoulNoUpgrade = new String("经验斗魂不可被升级");
	
	public static final String fail_fightSoul_noSelectedFightSoul = new String("未选择任何斗魂");
	
	public static final String fail_fightSoul_noCanUpgradeFightSoul = new String("无可升级的斗魂");
	
	public static final String fail_fightSoul_noCanUpgradedFightSoul = new String("无可被吞噬的斗魂");
	
	public static final String fail_fightSoul_upLevel = new String("您已升级到最高等级");
	
	public static final String fail_fightSoul_positionNoOpen = new String("此位置暂未开启");

	public static final String fail_fightSoul_noFormatFightSoul = new String("无可上阵的斗魂");
	
	public static final String fail_fightSoul_noOpenPosition = new String("当前称号未开放任何位置");

	public static final String fail_fightSoul_noPosition = new String("已无空闲位置");
	
	public static final String fail_fightSoul_noUpFightPropCommon = new String("不可上阵属性相同的斗魂");
	
	public static final String fail_fightSoul_haveBuy = new String("您已购买");
	
	public static final String fail_fightSoul_functionNoOpen = new String("功能暂未开放");
	
	public static final String fail_fightSoul_noExchange = new String("同一斗魂,无需更换");
	
	public static final String fail_union_noIn = new String("未在联盟中");
	
	public static final String fail_union_noInCommon = new String("未在同一联盟中");
	
	public static final String fail_union_noLeader = new String("弹劾对象非盟主");
	
	public static final String fail_union_no72 = new String("进盟需72小时才可弹劾盟主");
	
	public static final String fail_union_noSelf = new String("不能自己弹劾自己");

	public static final String fail_wing_otherCard = new String("已佩戴在其他卡牌身上");
	
	public static final String fail_wing_noInCard = new String("神羽未上阵,无须卸下");
	
	public static final String fail_wing_common = new String("神羽一致,无须转换");
	
	public static final String fail_wing_maxStarNum = new String("已进阶到最大级别");
	
	public static final String fail_wing_maxStrognerLevel = new String("已强化到最大级别");
	
	public static final String fail_wing_upSellNum = new String("超过出售列表上限");
	
	public static final String fail_wing_upTitle = new String("请升级称号");
	
	public static final String fail_wing_strongBoneNotEnough = new String("强化石不足");
	
	public static final String fail_boss_intergralNotEnough = new String("屠魔积分不足");
	
	public static final String fail_boss_ing = new String("世界Boss正在进行中,不可开大宝箱");
	
	public static final String fail_boss_noInTime = new String("未在开大宝箱时间内");
	
	public static final String fail_boss_opened = new String("已领大宝箱");
	
	public static final String fail_boss_noAdd = new String("您未参与世界Boss活动");
}
