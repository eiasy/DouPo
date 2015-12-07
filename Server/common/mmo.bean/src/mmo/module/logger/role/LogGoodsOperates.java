package mmo.module.logger.role;

/***
 * 道具日志中的操作项
 * 
 * @author fanren
 * 
 */
public interface LogGoodsOperates {
	/**
	 * 充值
	 * 
	 * @author fanren
	 * 
	 */
	interface Charge {
		String OPERATE_CHARGE       = "充值";

		String PROJECT_FIRST_AWARD  = "首充奖励";
		String PROJECT_EXTEND_AWARD = "额外奖励";
		String PROJECT_BACK         = "充值返利";
		String PROJECT_CHARGE       = "充值获得";
		String PROJECT_CHARGE_COST  = "补点扣减";
		String PROJECT_MONTH_CARD   = "月卡奖励";
		String PROJECT_FOUNDATION   = "基金奖励";
		String PROJECT_WELFARE      = "福利奖励";
		String PROJECT_FIRST        = "首充赠送";
	}

	interface TimerRecover {
		String OPERATE_TIMER       = "定时恢复";

		String PROJECT_JING_LI     = "精力恢复";
		String PROJECT_TI_LI       = "体力恢复";
		String PROJECT_SKILL_POINT = "技能点恢复";
	}

	interface SystemExchange {
		String OPERATE_EXCHANGE        = "系统交易";
		String OPERATE_TIME_EXCHANGE   = "限时兑换";

		String PROJECT_CARD_FIRST      = "首次抽卡";
		String PROJECT_SELL_GOODS      = "卖出道具";
		String PROJECT_RESET_SP        = "重置技能点";
		String PROJECT_BUY_JINGLI      = "购买精力值";
		String PROJECT_BUY_TILI        = "购买体力值";
		String PROJECT_BUY_LINGSHI     = "购买灵石";
		String PROJECT_BUY_HERO_COUNT  = "购买英雄副本次数";
		String PROJECT_BUY_DOUFA_COUNT = "购买斗法次数";
		String PROJECT_RESET_SKILL     = "重置技能点";
	}

	interface SystemOutput {
		String OPERATE_OUTPUT             = "系统产出";

		String PROJECT_MONSTER            = "怪物掉落";
		String PROJECT_LEVEL_UP           = "升级奖励";
		String PROJECT_EXCHANGE_CODE      = "兑换码";
		String PROJECT_HUO_YUE            = "活跃奖励";
		String PROJECT_JING_JIE_POINT     = "领取境界点";
		String PROJECT_BOX_AWARD          = "寻宝获得";
		String PROJECT_LOGIN_DAY          = "连续登录";
		String PROJECT_SIGN_CHARGE        = "豪华签到";
		String PROJECT_NEW_ROLE           = "新建角色";
		String PROJECT_CLOUD              = "云测奖励";
		String PROJECT_MISSION_AWARD      = "任务奖励";
		String PROJECT_LIVE_MISSION_AWARD = "活跃度奖励";
		String PROJECT_VIP_LV             = "VIP等级奖励";
		String PROJECT_USE_GOODS          = "使用道具";
		String PROJECT_GATE_AWARD         = "首关卡赠送";
		String PROJECT_CALL_SOCRE         = "召唤成就";
	}

	interface SystemSociality {
		String OPERATE_SOCIALITY = "社交系统";
		String PROJECT_FRIEND    = "好友赠送";
	}

	interface SystemDup {
		String OPERATE_DUP               = "副本系统";

		String PROJECT_GATE_STAR         = "关卡星奖励";
		String PROJECT_SWEEP             = "扫荡副本";
		String PROJECT_TONG_GUAN_FIRST   = "首次通关奖励";
		String PROJECT_DUP_HERO          = "英雄副本";
		String PROJECT_SECRET_BOSS       = "秘境BOSS";
		String PROJECT_DOU_FA_CHALLENGE  = "斗法挑战";
		String PROJECT_DOU_FA_SUCC       = "斗法胜利";
		String PROJECT_DOU_FA_FAIL       = "斗法失败";
		String PROJECT_ONE_STAND         = "一战到底";
		String PROJECT_SHORTLY_BOSS      = "解封BOSS";
		String PROJECT_HELP_AWARD        = "协战奖励";
		String PROJECT_GATE_COST         = "关卡消耗";
		String PROJECT_SECRET_COST       = "秘境消耗";
		String PROJECT_SECRET_BOSS_AWARD = "秘境奖励";
		String PROJECT_SECRET_GATE_AWARD = "秘境关卡奖励";
		String PROJECT_DUP_GATE_AWARD    = "副本通关奖励";
		String PROJECT_DOU_FA_AWARD      = "斗法奖励";
		String PROJECT_ONE_STAND_AWARD   = "一战到底奖励";
		String PROJECT_LEADER_AWARD      = "世界首领奖励";
		String PROJECT_MONSTER_ATK_CITY  = "怪物攻城副本";
	}

	interface SystemActivity {
		String OPERATE_ACTIVITY     = "系统活动";

		String PROJECT_QI_FU        = "祈福";
		String PROJECT_CUI_HUA      = "催化";
		String PROJECT_XIAN_LU      = "领取仙露";
		String PROJECT_DA_ZUO       = "打坐修炼";
		String PROJECT_LEVEL        = "等级奖励";
		String PROJECT_LIMIT_PET    = "限时宠物";
		String PROJECT_MORE_MONEY   = "财源广进";
		String PROJECT_RANK_LEVEL   = "冲级排行活动";
		String PROJECT_RANK_DOUFA   = "斗法排行活动";
		String PROJECT_RANK_FIGHT   = "战力排行活动";
		String PROJECT_LIMIT_LEVEL  = "限时等级购买";
		String PROJECT_SEVEN_DAY    = "开服七天乐";
		String PROJECT_ONLINE_TIME  = "在线奖励";
		String PROJECT_CHARGE_FIRST = "首充礼包";
	}

	interface SystemCost {
		String OPERATE_COST                 = "系统消耗";

		String PROJECT_SKILL_LEVEL          = "技能升级";
		String PROJECT_PET_STRENGTH         = "宠物强化";
		String PROJECT_PET_ADVANCE          = "宠物进阶";
		String PROJECT_ROLE_ADVANCE         = "角色进阶";
		String PROJECT_PET_EVOLVE           = "宠物进化";
		String PROJECT_ENTER_SECRET         = "进入秘境";
		String PROJECT_TRANSFER             = "地图传送";
		String PROJECT_LOCAL_RELIVE         = "原地复活";
		String PROJECT_CHAT_WORLD           = "世界喊话";
		String PROJECT_NAVIGATE             = "地图导航";
		String PROJECT_OFFLINE_EXP          = "离线经验";
		String PROJECT_JING_JIE_UP          = "升级境界";
		String PROJECT_OPEN_BOX             = "寻宝消耗";
		String PROJECT_DU_JIE               = "渡劫消耗";
		String PROJECT_OPEN_FA_BAO          = "开启法宝";
		String PROJECT_FA_BAO_SKILL         = "领悟技能";
		String PROJECT_OPEN_TRAIN_GRID      = "开启培养格";
		String PROJECT_GRID_LEVEL_UP        = "提升格子等级";
		String PROJECT_GRID_QUALITY         = "升级格子品阶";
		String PROJECT_OPEN_HOLE            = "打孔";
		String PROJECT_HOLE_COLOR           = "洗孔颜色";
		String PROJECT_INSERT_STONE         = "镶嵌宝石";
		String PROJECT_CUI_HUA_COST         = "催化消耗";
		String PROJECT_QI_FU_COST           = "祈福消耗";
		String PROJECT_USE_GOODS            = "使用消耗";
		String PROJECT_BUY_FUNDATION        = "购买基金";
		String PROJECT_RESET_DOU_FA_TIME    = "重置斗法时间";
		String PROJECT_REFRESH_MYSTERY_SHOP = "刷新神秘商店";
		String PROJECT_ADVANCE_PETSKILL     = "进阶宠物技能";
		String PROJECT_REFRESH_DOUFA_SHOP   = "刷新斗法商店";
	}

	interface SystemFunction {
		String OPERATE_FUNCTION           = "系统功能";
		String OPERATE_AWARD_CENTER       = "领奖中心";
		String Operate_GOODS_USED         = "使用道具";

		String PROJECT_GM                 = "GM操作";
		String PROJECT_TREASURE_MAP       = "藏宝图";
		String PROJECT_STORE_BACK         = "仓库取出";
		String PROJECT_PET_COMPOSITE      = "宠物合成";
		String PROJECT_EQUIP_COMPOSITE    = "装备合成";
		String PROJECT_CHIP_COMPOSITE     = "碎片合成";
		String PROJECT_CHIP_DECOMPOSE     = "分解伙伴";
		String PROJECT_GRAB_REFRESH       = "刷新夺宝";
		String PROJECT_REMOVE_STONE       = "移除宝石";
		String PROJECT_COMPOSITE_STONE    = "宝石合成";
		String PROJECT_EQUIP_STRENGTH     = "装备强化";
		String PROJECT_ONE_KEY_STRENGTH   = "一键强化";
		String PROJECT_ACTIVITY_ACUPOINT  = "激活穴位";
		String PROJECT_CALL_PET           = "召唤宠物";
		String PROJECT_EQUIP              = "穿戴装备";
		String PROJECT_UNEQUIP            = "卸载装备";
		String PROJECT_GRAB               = "抢夺宝物";
		String PROJECT_CLEAR              = "定时清理";
		String PROJECT_LING_GEN           = "开启灵根";
		String PROJECT_ZHAO_HUAN          = "召唤伙伴";
		String PROJECT_COMPOSITE_MATERIAL = "首次合成材料";

	}

	interface Email {
		String OPERATE_PICKOUT = "邮件提取";
	}

	interface RoleShop {
		String OPERATE_ROLE_SHOP = "角色商店";

		String PROJECT_SHOP2BAG  = "货物下架";
		String PROJECT_SHOP_OL   = "随身商店";
	}

	interface SystemSect {
		String OPERATE_SECT             = "宗门";

		String PROJECT_SECT_CREATE      = "创建宗门";
		String PROJECT_SECT_EXCHANGE    = "宗门兑换";
		String PROJECT_SECT_OPENMANOR   = "开启领地";
		String PROJECT_SECT_SIGN        = "宗门签到";
		String PROJECT_SECT_CONTRIBUTE  = "宗门贡献";
		String PROJECT_SECTBATTLE_AWARD = "宗战奖励";
	}
}
