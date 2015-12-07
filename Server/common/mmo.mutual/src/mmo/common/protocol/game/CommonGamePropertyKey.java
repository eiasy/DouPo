package mmo.common.protocol.game;

public final class CommonGamePropertyKey {
	/**
	 * 公共属性
	 * 
	 * @author fanren
	 * 
	 */
	public static interface CommonKey {
		/** 等级 */
		short COMMON_LEVEL_0          = 0;
		/** 等级上限 */
		short COMMON_LEVEL_MAX_1      = 1;
		/** 当前经验 */
		short COMMON_EXPERIENCE_2     = 2;
		/** 经验上限 */
		short COMMON_EXPERIENCE_MAX_3 = 3;
		/** 累积经验 */
		short COMMON_ALL_EXPERIENCE_4 = 4;
		/** ID */
		short COMMON_ID_5             = 5;
		/** 名称 */
		short COMMON_NAME_6           = 6;
		/** ICON */
		short COMMON_ICON_7           = 7;
		/** 格子ID */
		short COMMON_INDEX_8          = 8;
		/** 最大值 */
		short COMMON_MAX_VALUE_9      = 9;
		/** 当前已有数量 */
		short COMMON_NOW_COUNT_10     = 10;
		/** 需求数量 */
		short COMMON_NEED_COUNT_11    = 11;
		/** 需求最大数量 */
		short COMMON_MAX_COUNT_12     = 12;
		/** 品阶 */
		short COMMON_QUALITY_13       = 13;
		/** 行为 */
		short COMMON_ACTION_14        = 14;
		/** 时间 */
		short COMMON_TIME_15          = 15;
		/** 成功率 */
		short COMMON_SUCCESS_RATIO_16 = 16;
		/** 状态 */
		short COMMON_STATUS_17        = 17;
		/** 类别 */
		short COMMON_CATE_18          = 18;
		/** 百分比 */
		short COMMON_PERCENT_19       = 19;
		/** 冷却百分比 */
		short COMMON_COOL_PERCENT_20  = 20;
		/** 详细 */
		short COMMON_DETAIL_23        = 23;
		/** 描述 */
		short COMMON_DESCRIBE_24      = 24;
		/** 序号 */
		short COMMON_SORT_26          = 26;
		/** 是否最新获得 */
		short COMMON_NEW_27           = 27;
		/** 分数 */
		short COMMON_VALUE_28         = 28;
		/** 胜利次数 */
		short COMMON_WIN_COUNT_29     = 29;
		/** 连胜次数 */
		short COMMON_CONTINUE_WIN_30  = 30;
		/** 总次数 */
		short COMMON_TOTAL_31         = 31;
		/** 预览 */
		short PREVIEW_32              = 32;
		/** 移动速度 */
		short COMMON_MOVE_SPEED_620   = 620;
		/** 系统当前时间(以100ms为单位) */
		short COMMON_SYSDATE_33       = 33;
		/** 领取中心有效期 */
		short COMMON_EFFECTIVE_34     = 34;
		/** 倍率 */
		short COMMON_MULTI_35         = 35;
		/** 音效 */
		short PROP_36_MUSIC           = 36;
		/** 版本号 */
		short VERSION_41              = 41;
	}

	/**
	 * 特殊货币
	 * 
	 * @author fanren
	 * 
	 */
	public static interface MoneyGoods {
		/** 需要的货币数量 */
		short MONEY_NEED_COUNT_300 = 300;
		/** 境界点 */
		short REALM_POINT_305      = 305;
		/** 活跃度 */
		short LIVENESS_306         = 306;
		/** 修炼点 */
		short PRACTICE_POINT_311   = 311;
		/** 建设度 */
		short BUILD_PROGRESS_312   = 312;
		/** 战绩 */
		short BATTLE_SCORE_313     = 313;
		/** 技能点 */
		short SKILL_POINT_314      = 314;

		/** 机缘 */
		short TREASURE_LUCKY_315   = 315;
		/** 修为点 */
		short TRAIN_POINT_316      = 316;
		/** 亲密度 */
		short ROLE_INTIMATE_317    = 317;
		/** 副本通关-星 */
		short ROLE_STAR_319        = 319;
		/** 仙府经验 */
		short MANSION_EXP_324      = 324;
	}

	/**
	 * 特殊货币上限
	 * 
	 * @author fanren
	 * 
	 */
	public static interface MoneyGoodsSup {
		/** 精力上限 */
		short MONEY_JINNGLI_SUP_403 = 403;
		/** 境界点上限 */
		short MONEY_REALM_SUP_405   = 405;
		/** 技能点上限 */
		short MONEY_SP_SUP_414      = 414;
		/** 修为点上限 */
		short MONEY_TRAIN_SUP_416   = 416;
		/** 体力上限 */
		short MONEY_TILI_SUP_422    = 422;
	}

	/**
	 * 币种
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Moneys {
		/** 灵石 */
		public static final short moneyLingshi_302 = 302;
		/** 币种 */
		public static final short MONEY_KEY_500    = 500;
		/** 数量 */
		public static final short MONEY_VALUE_501  = 501;
		public static final short MONEY_VALUE_502  = 502;
		public static final short MONEY_VALUE_503  = 503;
	}

	/**
	 * 战斗属性
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Fight {
		/** 体术 */
		short FIGHT_FORCE_600                 = 600;
		/** 法术 */
		short FIGHT_MAGIC_601                 = 601;
		/** 神识 */
		short FIGHT_SPRIT_602                 = 602;
		/** 生命 */
		short FIGHT_HP_603                    = 603;
		/** 攻击 */
		short FIGHT_ATTACK_604                = 604;
		/** 防御 */
		short FIGHT_DEFENSE_605               = 605;
		/** 命中 */
		short FIGHT_PROBABILITY_606           = 606;
		/** 闪避 */
		short FIGHT_DUCK_607                  = 607;
		/** 暴击 */
		short FIGHT_STRIKE_608                = 608;
		/** 韧性 */
		short FIGHT_TENACITY_609              = 609;
		/** 格挡 */
		short FIGHT_FENDER_610                = 610;
		/** 破甲 */
		short FIGHT_DESTROY_611               = 611;
		/** 吸血 */
		short FIGHT_SUCKHP_612                = 612;
		/** 反弹 */
		short FIGHT_REBOUND_613               = 613;
		/** 破防 */
		short FIGHT_REDUCE_DEFENCE_614        = 614;
		/** 抵抗 */
		short FIGHT_RESIST_615                = 615;
		/** 体术 */
		short FIGHT_FORCE_SUP_616             = 616;
		/** 法术 */
		short FIGHT_MAGIC_SUP_617             = 617;
		/** 神识 */
		short FIGHT_SPRIT_SUP_618             = 618;
		/** 精炼上限 */
		short FIGHT_REFINE_MAX_622            = 622;
		/** 金属性 */
		short FIGHT_PROP_GOLD_623             = 623;
		/** 木属性 */
		short FIGHT_PROP_WOOD_624             = 624;
		/** 水属性 */
		short FIGHT_PROP_WATER_625            = 625;
		/** 火属性 */
		short FIGHT_PROP_FIRE_626             = 626;
		/** 土属性 */
		short FIGHT_PROP_EARTH_627            = 627;

		/** 永久攻击 */
		short FIGHT_PERPETUAL_ATTACK_628      = 628;
		/** 永久防御 */
		short FIGHT_PERPETUAL_DEFENSE_629     = 629;
		/** 永久生命 */
		short FIGHT_PERPETUAL_LIFE_630        = 630;
		/** 永久命中 */
		short FIGHT_PERPETUAL_PROBABILITY_631 = 631;
		/** 永久闪避 */
		short FIGHT_PERPETUAL_DUCK_632        = 632;
		/** 永久暴击 */
		short FIGHT_PERPETUAL_STRIKE_633      = 633;
		/** 永久韧性 */
		short FIGHT_PERPETUAL_TENACITY_634    = 634;
		/** 永久格挡 */
		short FIGHT_PERPETUAL_PARRY_635       = 635;
		/** 永久破甲 */
		short FIGHT_PERPETUAL_HEAT_636        = 636;
		/** 战斗力 */
		short FIGHT_SUMPOWER_637              = 637;
		/** 培养后未保存前的体术 */
		short FIGHT_TRAIN_PRE_FORCE_638       = 638;
		/** 培养后未保存前的法术 */
		short FIGHT_TRAIN_PRE_MAGIC_639       = 639;
		/** 培养后未保存前的神识 */
		short FIGHT_TRAIN_PRE_SPRIT_640       = 640;
		/** 属性转化 */
		short FIGHT_PET_TRANSFORM_641         = 641;
		/** 法宝精炼 */
		short FIGHT_TREASURE_REFINE_642       = 642;
		/** 生命资质 */
		short FIGHT_APTITUDE_HP_643           = 643;
		/** 攻击资质 */
		short FIGHT_APTITUDE_ATTACK_644       = 644;
		/** 防御资质 */
		short FIGHT_APTITUDE_DEFENCE_645      = 645;

		/** 战斗力上限 */
		short FIGHT_FIGHT_MAX_646             = 646;
		/** 法力 */
		short MP_647                          = 647;
		/** 法力上限 */
		short MP_SUP_648                      = 648;
		/** 法力回复 */
		short MP_GROW_649                     = 649;
		/** 吸血百分比 */
		short SUCK_HP_PERCENT_650             = 650;
	}

	/** 角色 */
	public static interface RoleKey {
		/*** 体术上限 */
		short ROLE_FORCE_MAX_616           = 616;
		/*** 法术上限 */
		short ROLE_MAGIC_MAX_617           = 617;
		/*** 神识上限 */
		short ROLE_SPRIT_MAX_618           = 618;

		/** 战斗模式 */
		short ROLE_BATTLE_MODE_700         = 700;
		/** 职业 */
		short ROLE_PROFESSION_701          = 701;
		/** 性别 */
		short ROLE_SEX_702                 = 702;
		/** 角色类型 */
		short ROLE_TYPE_703                = 703;
		/** 阵营 */
		short ROLE_GROUP_704               = 704;
		/** 角色境界 */
		short ROLE_REALM_705               = 705;
		/** 视野 */
		short ROLE_EYES_RANGE_706          = 706;
		/** 关系 */
		short ROLE_RELATION_707            = 707;
		/** 称号 */
		short ROLE_TITLE_708               = 708;
		/** 亲密度 */
		short ROLE_INTIMATE_709            = 709;
		/** 死亡倒计时 */
		short ROLE_DEATH_COUNTDOWN_710     = 710;
		/** ANI文件名 */
		short ROLE_ANI_FILE_711            = 711;
		/** 怪物群组ID */
		short ROLE_GROUP_ID_712            = 712;
		/** AS文件名 */
		short ROLE_AS_FILE_839             = 839;
		/** 骨骼ID */
		short ROLE_SKELETON_ID_713         = 713;
		/** AVATAR头像 */
		short ROLE_AVATAR_714              = 714;
		/** 头顶状态 */
		short ROLE_HEAD_STATUS_715         = 715;
		/** 方向 */
		short ROLE_HEAD_DIRECT_716         = 716;
		/** 动作ID */
		short ROLE_ACTION_ID_717           = 717;
		/** PK状态 */
		short ROLE_PK_STATUS_718           = 718;
		/** 首次登录 */
		short ROLE_FIRST_LOGIN_719         = 719;
		/** 出生坐标X */
		short RELIVE_X                     = 720;
		/** 出生坐标Y */
		short RELIVE_Y                     = 721;
		/** 目的坐标X */
		short TARGET_X                     = 722;
		/** 目的坐标Y */
		short TARGET_Y                     = 723;
		/** AI类型 0.主动 1.被动怪 */
		short AI_CATEGORY                  = 724;
		/** 攻击距离 */
		short ATTACK_DISTANCE              = 725;
		/** 攻击间隔 */
		short ATTACK_OFFSET                = 726;
		/** 巡逻范围W */
		short PATROL_W                     = 727;
		/** 巡逻范围H */
		short PATROL_H                     = 728;
		/** 技能ID1 */
		short SKILL_ID_1                   = 729;
		/** 触发几率1 */
		short SKILL_RATE_1                 = 730;
		/** 技能ID2 */
		short SKILL_ID_2                   = 731;
		/** 触发几率2 */
		short SKILL_RATE_2                 = 732;
		/** 技能ID3 */
		short SKILL_ID_3                   = 733;
		/** 触发几率3 */
		short SKILL_RATE_3                 = 734;
		/** 迟缓系数 */
		short SLOW                         = 735;
		/** 怪物难度 */
		short MONSTER_DEGREE               = 736;
		/** AI脚本 */
		short AI_SCRIPT_737                = 737;
		/** AI关系 0.中立 1.敌对 2.友好3.无焦点 */
		short AI_RELATION                  = 738;
		/** 场景角色ID 角色在当前场景上的ID */
		// short SCENE_ROLE_ID = 738;
		/** 技能ID4 */
		short SKILL_ID_4                   = 739;
		/** 触发几率4 */
		short SKILL_RATE_4                 = 740;
		/** 技能ID5 */
		short SKILL_ID_5                   = 741;
		/** 触发几率5 */
		short SKILL_RATE_5                 = 742;
		/** 精灵类型 */
		short SPIRIT_TYPE_743              = 743;
		/** 头像品质 */
		short ROLE_AVATAR_QUALITY_745      = 745;
		/** 在线状况 */
		short ONLINE_STATE_746             = 746;
		/** 大头像 */
		short HEAD_AVATAR_BIG_748          = 748;
		/** 坐骑动画 */
		short ROLE_MOUNT_ANI_749           = 749;
		/** 坐骑的移动速度 */
		short ROLE_MOUNT_SPEED_750         = 750;
		/** 补血百分比 */
		short ROLE_ADDHP_PERCENT_751       = 751;
		/** 队伍ID */
		short ROLE_TEAM_ID_752             = 752;
		/** 缩放比例 */
		short SCALE_761                    = 761;
		/** 1:角色不可移动 */
		short NOT_MOVE_782                 = 782;
		/** 打坐精力值满时给客户端发字符串 */
		short DA_ZUO_783                   = 783;
		/** 法宝技能动画 */
		short ROLE_TREASURE_SKILL_ANI_1522 = 1522;

		/** 血包开启状态 */
		short HP_BAG_STATE_1617            = 1617;
		/** 播放动画 */
		short PLAY_ANI_1615                = 1615;
	}

	/** 技能 */
	public static interface SkillKey {
		/** 施法距离 */
		short SKILL_RANGE_800              = 800;
		/** 施法消耗 */
		short SKILL_CONSUME_801            = 801;
		/** 施法时间 */
		short SKILL_LAUNCH_TIME_802        = 802;
		/** 冷却时间 */
		short SKILL_COOL_TIME_803          = 803;
		/** 冷却速度 */
		short SKILL_COOL_SPEED_804         = 804;
		/** 当前介绍 */
		short SKILL_INTRODUCE_805          = 805;
		/** 下级介绍 */
		short SKILL_NEXT_LV_INTRO_806      = 806;
		/** 读条百分比 */
		short SKILL_PROGRESS_PERCENT_807   = 807;
		/** 施法目标 */
		short SKILL_ATTACK_TARGET_808      = 808;
		/** 等级限制 */
		short SKILL_LEVEL_LIMIT_809        = 809;
		/** 技能升级状态 */
		short SKILL_LEVELUP_STATUS_810     = 810;
		/** 心法升级状态 */
		short SKILL_XINFA_LEVELUP_811      = 811;
		/** 公共冷却ID */
		short SKILL_COMMON_COOL_ID_812     = 812;
		/** 介绍模版 */
		short SKILL_INTRODUCE_TEMPLATE_813 = 813;
		/** 技能标识值 */
		short SKILL_IDENTIFY_VALUE_814     = 814;
		/** 技能描述 */
		short SKILL_DESCRIPTION_815        = 815;
		/** 施放类型(主动、被动) */
		short SKILL_PLAY_TYPE_817          = 817;
		/** 效果类型 */
		short SKILL_EFFECT_TYPE_818        = 818;
		/** 影响范围(群体、单体) */
		short SKILL_EFFECT_SCOPE_819       = 819;
		/** 协战时间 */
		short HELP_WAR_TIME_848            = 848;
	}

	/** 任务 */
	public static interface MissionKey {
		/** 任务描述 */
		short MISSION_DESCRIPTOR_1550 = 1550;
		/** 任务奖励 */
		short MISSION_AWARD_1551      = 1551; // TODO 检查范围
		/** 任务目标 */
		short MISSION_TARGET_1552     = 1552;
		/** 对话 */
		short MISSION_DIALOG_1553     = 1553;
		/** 任务序列号 */
		short MISSION_SERIAL_1554     = 1554;
		/** 任务数量 */
		short MISSION_TOTAL_1555      = 1555;
		/** 任务参数1 */
		short MISSION_PARAM_1_1556    = 1556;
		/** 任务参数2 */
		short MISSION_PARAM_2_1557    = 1557;
		/** 任务参数3 */
		short MISSION_PARAM_3_1558    = 1558;
		/** 任务星级 */
		short MISSION_STAR_1559       = 1559;
		/** 任务冷却时间 (秒) */
		short MISSION_COOLTIME_1560   = 1560;
		/** 任务重做次数 */
		short MISSION_REPEAT_1561     = 1561;
		/** 任务状态 */
		short MISSION_STATE_1562      = 1562;
		/** 任务对应的副本难度 */
		short MISSION_DUPLEVEL_1563   = 1563;
	}

	/** 物品 */
	public static interface ItemKey {
		/** 耐久度 */
		short ITEM_HARDINESS_900         = 900;
		/** 价格 */
		short ITEM_PRICE_901             = 901;
		/** 物品详细 */
		short ITEM_DESC_902              = 902;
		/** 物品状态 */
		short ITEM_BIND_STATUS_903       = 903;
		/** 物品归属ID */
		short ITEM_BELONG_ID_904         = 904;
		/** 商品状态 */
		short GOODS_STATUS_905           = 905;
		/** 附加属性 */
		short ITEM_ADDITION_PROPERTY_906 = 906;
		/** 品质 */
		short ITEM_QUALITY_907           = 907;
		/** 物品真实ID */
		short ITEM_REAL_ID_908           = 908;
		/** 物品类型 */
		short ITEM_TYPE_909              = 909;
		/** 物品描述 */
		short ITEM_REPRESENT_910         = 910;
		/** 强化或制作 消耗费用 */
		short ITEM_COST_911              = 911;
		/** 装备属性成长 */
		short ITEM_GROW_PRO_912          = 912;
		/** 制作Id */
		short ITEM_MAKE_ID_913           = 913;
		/** 镶嵌孔状态 */
		short ITEM_INLAY_STATE_914       = 914;
		/** 开启的格子数 */
		short ITEM_OPEN_GRIDS_915        = 915;
		/** 基础属性 为了显示物品基础详细用 */
		short ITEM_BASE_DETAIL_916       = 916;
		/** 镶嵌属性 为了显示物品镶嵌详细用 */
		short ITEM_HOLE_DETAIL_917       = 917;
		/** 附魔属性 为了显示物品附魔详细用 */
		short ITEM_FUMO_918              = 918;
		/** 物品操作 */
		short ITEM_OPERATE_919           = 919;
		/** 商城价格 */
		short SHOP_PRICE_COUNT_920       = 920;
		/** 商城原价 */
		short SHOP_PRICE_NORMAL_921      = 921;
		/** 商城特价 */
		short SHOP_PRICE_NOW_922         = 922;
		/** 兑换货币 */
		short SHOP_PRICE_NAME_923        = 923;
		/** 星级 */
		short ITEM_STAR_924              = 924;
		/** 强化等级 */
		short ITEM_STRENGHT_925          = 925;
		/** 开启制作（多少级开启） */
		short ITEM_MAKE_LEVEL_926        = 926;
		/** 镶嵌孔颜色 */
		short ITEM_INLAY_COLOR_927       = 927;
		/** 套装ID */
		short ITEM_PERTAIN_928           = 928;
		/** 装备于 */
		short ITEM_EQUIP_ROLE_929        = 929;
		/** 是否是时装 0不是1是 */
		short ITEM_FASH_930              = 930;
		/** 好友数量上限 */
		short FRIEND_MAX_COUNT_959       = 959;
		/** 物品有效期 */
		short ITEM_IN_DATE_911           = 1002; // TODO 需要替换

		/** 物品开放状态 */
		short ITEM_OPEN_STATE_1609       = 1609;
	}

	/**
	 * 聊天
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Chat {

	}

	/** 邮件 */
	public static interface MailKey {

		/** 邮件ID */
		short EMAIL_ID_1000                = 1000;
		/** 邮件内容 */
		short EMAIL_CONTENTS_1001          = 1001;
		/** 邮件有效期 */
		short EMAIL_INDATE_1002            = 1002;
		/** 邮件状态 */
		short EMAIL_STATE_1003             = 1003;
		/** 邮件标题 */
		short EMAIL_TITLE_1004             = 1004;

		/** 来自 发件人地址 */
		short EMAIL_SEND_ADDRESS_1005      = 1005;
		/** 发往 收件人地址 */
		short EMAIL_RECE_ADDRESS_1006      = 1006;
		/** 附件1ID */
		short EMAIL_ATTACHMENT1_ID_1007    = 1007;
		/** 附件1ICON */
		short EMAIL_ATTACHMENT1_ICON_1008  = 1008;
		/** 附件1数量 */
		short EMAIL_ATTACHMENT1_COUNT_1009 = 1009;

		/** 附件1ID */
		short EMAIL_ATTACHMENT2_ID_1010    = 1010;
		/** 附件1ICON */
		short EMAIL_ATTACHMENT2_ICON_1011  = 1011;
		/** 附件1数量 */
		short EMAIL_ATTACHMENT2_COUNT_1012 = 1012;

		/** 附件1ID */
		short EMAIL_ATTACHMENT3_ID_1013    = 1013;
		/** 附件1ICON */
		short EMAIL_ATTACHMENT3_ICON_1014  = 1014;
		/** 附件1数量 */
		short EMAIL_ATTACHMENT3_COUNT_1015 = 1015;
	}

	/** 场景、副本 */
	public static interface SceneAndDupKey {
		/** 场景ID */
		short SCENE_ID_1050         = 1050;
		/** 通关评分 */
		short DUP_PASS_GRADE_1051   = 1051;
		/** 怪物ID */
		short MONSTER_ID_1052       = 1052;
		/** 坐标X */
		short TILE_X_1053           = 1053;
		/** 坐标Y */
		short TILE_Y_1054           = 1054;
		/** 场景切换信息 */
		short SCENE_CHANG_INFO_1055 = 1055;
		/** 场景属性 */
		short SCENE_PROPERTY_1056   = 1056;
		/** 当前次数 */
		short DUP_CUR_COUNT_1058    = 1058;
		/** 最大次数 */
		short DUP_MAX_COUNT_1059    = 1059;
		/** 副本通关星级 */
		short DUP_PASS_STAR_1060    = 1060;
		/** 通关时间 */
		short DUP_PASS_TIME_1061    = 1061;

		/** 场景描述 */
		short DUP_NOTE_1065         = 1065;
		/** 通关类型 */
		short DUP_PASS_MODEL_1066   = 1066;
		/** 消耗 */
		short DUP_COST_1067         = 1067;
		/** 副本开启 0.未开启 1.开启 */
		short DUP_OPEN_1068         = 1068;
		/** 挂机开启0.未开启 1.开启 */
		short DUP_AUTO_1069         = 1069;
		/** 多人副本组队平台的房间ID */
		short DUP_WAIT_1070         = 1070;
		/** 倒计时 */
		short DUP_TIME_1071         = 1071;
		/** 场景区域 */
		short DUP_AREA_1072         = 1072;
		/** 进入场景的朝向 */
		short DUP_DIRECT_1073       = 1073;
		/** 通关难度 */
		short DUP_LEVEL_1074        = 1074;
		/** 关卡序号 */
		short DUP_GATE_1075         = 1075;
		/** 开启英雄牌状态开启0.未开启 1(通关奖励) */
		short DUP_AWARD_HERO_1614   = 1614;

		/** 房间位置的状态 */
		short DUP_ROOM_STATE_1618   = 1618;

		short TEACHER_STEP_NUMBER   = 1400;
	}

	/** VIP */
	public static interface VIPKey {
		/** VIP等级 */
		short VIP_LV_1100           = 1100;
		/** 下级VIP上限 */
		short NEXT_VIP_LV_SUP_1101  = 1101;
		/** 充值累积 (未使用) */
		short RECHARGE_TOTAL_1102   = 1102;
		/** 当前充值额度 */
		short CURRENT_RECHARGE_1103 = 1103;
		/** 幸运值 */
		short LUCKY_VALUE_1105      = 1105;
	}

	/** 帐号 */
	public static interface RoleLoginKey {
		/** 服务器区 */
		short SERVER_ZONE_1150       = 1150;
		/** 帐号 (只在前端使用) */
		short ACCOUNT_1151           = 1151;
		/** 密码 */
		short PASSWORD_1152          = 1152;
		/** URL */
		short URL_1153               = 1153;
		/** 服务器ID */
		short SERVER_ID_1154         = 1154;
		/** 验证码 */
		short VERIFICATION_CODE_1155 = 1155;
		/** 首次登录 */
		short FIRST_LOGIN_1156       = 1156;

		/** 服务器ID */
		short WEI_XIN_1159           = 1159;
		/** 验证码 */
		short forum_1160             = 1160;
		/** 首次登录 */
		short QQ_1161                = 1161;
		/** 账号ID */
		short ACCOUNT_ID_1162        = 1162;
		/** 子渠道编号 */
		short CHANNEL_SUB_1163       = 1163;
	}

	/**
	 * 宗门
	 * 
	 * @author fanren
	 * 
	 */
	public static interface SectKey {
		/** 宗门ID */
		short SECT_ID_1200      = 1200;
		/** 宗门名称 */
		short SECT_NAME_1201    = 1201;
		/** 宗门职位 */
		short SECT_POSTION_1202 = 1202;
		/** 宗门等级 */
		short SECT_LEVEL_1204   = 1204;

		/** 宗门公告 */
		short SECT_NOTICE_1205  = 1205;

		/** 宗门主人 */
		short SECT_MASTER_1206  = 1206;
	}

	/**
	 * 宠物
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Pet {
		/** 灵兽ID */
		short PET_ID_1250             = 1250;
		/** 灵兽战斗状态 */
		short BATTLE_STATE_1251       = 1251;
		/** 灵兽激活状态 */
		short ACTIVE_STATE_1252       = 1252;
		/** 当前进化值 */
		short EVOLVE_NOW_1253         = 1253;
		/** 进化值上限 */
		short EVOLVE_SUP_1254         = 1254;
		/** 穴位冷却时间 */
		short IDENTITY_COL_TIME_1255  = 1255;
		/** 当前穴位等级 */
		short IDENTITY_NOW_LEVEL_1256 = 1256;
		/** 穴位状态 */
		short IDENTITY_STATE_1257     = 1257;
		/** 穴位属性 */
		short IDENTITY_PRO_1258       = 1258;
		/** 进化状态 */
		short EVOLVE_STATE_1259       = 1259;
		/** 宠物动画 */
		short PET_ANI_1260            = 1260;
		/** 培养成功率 */
		short TRAIN_PERCENT_1261      = 1261;
		/** 出战状态 */
		short OPEN_STATE_1262         = 1262;
		/** 技能锁定状态 */
		short SKILL_STATE_1263        = 1263;
		/** 学习价格 */
		short SKILL_COST_1264         = 1264;
		/** 宠物技能连招1 */
		short SKILL_PET_1266          = 1266;
		/** 宠物技能连招1 */
		short SKILL_PET_1267          = 1267;
		/** 宠物技能连招1 */
		short SKILL_PET_1268          = 1268;
		/** 宠物类型 */
		short PET_TYPE_1269           = 1269;
		/** 宠物等，只在好友协战界面用 */
		short PET_LEVEL               = 1270;
		/** 宠物对话脚本 */
		short PET_DIALOG_1271         = 1271;
		/** 模型ID */
		short PET_MODEL_ID_1272       = 1272;
		/** 宠物佩戴的法宝ID */
		short PET_FABAO_ID_1274       = 1274;
		/** 宠物佩戴的武器ID */
		short PET_WEAPON_ID_1275      = 1275;
		/** 1276 宠物携带的装备2 衣服 */
		short PET_CLOTHES_ID_1276     = 1276;
		/** 1277 宠物携带的装备3 头盔 */
		short PET_HELMET_ID_1277      = 1277;
		/** 1278 宠物携带的装备4 鞋子 */
		short PET_SHOE_ID_1278        = 1278;
	}

	/**
	 * 摆摊
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Shop {
	}

	/**
	 * 信息表
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Table {
		/** 主类别 */
		short INFO_MAIN_CATE_1350 = 1350;
		/** 子类别 */
		short INFO_SUBCATE_1351   = 1351;
		/** 序号 */
		short INFO_INDEX_1352     = 1352;
	}

	/**
	 * 教程
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Teacher {

	}

	/**
	 * 协议
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Protocol {
		/** 协议1 */
		short PROTOCOL01_2000 = 2000;
		/** 协议2 */
		short PROTOCOL01_2001 = 2001;
		/** 协议3 */
		short PROTOCOL01_2002 = 2002;
		short PROTOCOL01_2003 = 2003;
		short PROTOCOL01_2004 = 2004;
		short PROTOCOL01_2005 = 2005;
	}

	/**
	 * 参数1
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Parameter01 {
		/** 一号参数1 */
		short PARAMETER01_2050 = 2050;
		/** 一号参数2 */
		short PARAMETER01_2051 = 2051;
		/** 一号参数3 */
		short PARAMETER01_2052 = 2052;
		/** 一号参数4 */
		short PARAMETER01_2053 = 2053;
	}

	/**
	 * 参数2
	 * 
	 * @author fanren
	 * 
	 */
	public static interface Parameter02 {
		/** 二号参数1 */
		short PARAMETER02_2100 = 2100;
		/** 二号参数2 */
		short PARAMETER02_2101 = 2101;
		/** 二号参数3 */
		short PARAMETER02_2102 = 2102;
		/** 二号参数4 */
		short PARAMETER02_2103 = 2103;

		short PARAMETER02_2150 = 2150;

		short PARAMETER02_2151 = 2151;
	}

	/**
	 * 临时使用
	 */
	public static interface tempKey {
		/*** 2：hp */
		short TEMP_HP_CURR_603          = 603;
		/*** 3：最大hp */
		short TEMP_HP_MAX_619           = 619;
		/*** 4：灵力 */
		short TEMP_MP_CURR_2123         = 2123;
		/*** 5：最大灵力 */
		short TEMP_MP_MAX_2124          = 2124;
		/*** 79：鉴定 */
		short TEMP_CHECK_2121           = 2121;
		/** 免疫 */
		short TEMP_IMMUNE_2130          = 2130;
		/** 反弹 */
		short TEMP_REBOUND_PERCENT_2131 = 2131;
	}

	public static interface FuluKey {
		/** 能量上限 */
		short NERGY_MAX_1510    = 1510;
		/** 当前能量 */
		short ENERGY_1511       = 1511;
		/** 五行属性 */
		short ELEMENT_1513      = 1513;
		/** 每次回复灵气值 */
		short REPLY_ENERGY_1514 = 1514;
		/** 冷却百分比 */
		short LingQi_1515       = 1515;
	}

	public static interface TreasureKey {

		/** 开启状态（0未开启，1已开启，2已装备） */
		short TREASURE_OPEN_STATE_1500         = 1500;

		/** 格子属性 */
		short TREASURE_GRID_PROP_1501          = 1501;
		/** 格子行 */
		short TREASURE_GRID_ROW_1502           = 1502;
		/** 格子列 */
		short TREASURE_GRID_COL_1503           = 1503;

		/** 五行属性1 */
		short TREASURE_FIVEPROP1_1504          = 1504;
		/** 个数1 */
		short TREASURE_FIVEPROP1_NUMBER_1505   = 1505;

		/** 五行属性2 */
		short TREASURE_FIVEPROP2_1506          = 1506;
		/** 个数2 */
		short TREASURE_FIVEPROP2_NUMBER_1507   = 1507;

		/** 五行属性2 */
		short TREASURE_FIVEPROP3_1508          = 1508;
		/** 个数2 */
		short TREASURE_FIVEPROP3_NUMBER_1509   = 1509;

		/** 法定动画 */
		short TREASURE_ANIMATE_1516            = 1516;
		/** 格子属性值 */
		short TREASURE_GRID_PROP_VAL_1519      = 1519;
		/** 格子属性类别 */
		short TREASURE_GRID_PROP_TYPE_1520     = 1520;
		/** 格子属性技能 */
		short TREASURE_GRID_PROP_SKILL_1521    = 1521;
		/** 领悟状态 */
		short ROLE_TREASURE_SKILL_STATUS_1523  = 1523;
		/** 领悟时间 */
		short TREASURE_SKILL_UNDERSTAND_1524   = 1524;
		/** 领悟倒计时 */
		short TREASURE_SKILL_COUNTDOWN_1525    = 1525;
		/** 下一级格子属性值 */
		short TREASURE_GRID_NEXT_PROP_1526     = 1526;
		/** 洗炼费用 */
		short TREASURE_GRID_STRENGTH_COST_1527 = 1527;
	}

	/** 界面 */
	public static interface UIKey {

		/** 系统当前时间 (1/100秒) */
		short SYSTEM_TIME_1606       = 1606;

		/** 消息类别 */
		short TIPS_MESSAGE_TYPE_1607 = 1607;
	}

	/** 界面 */
	public static interface RealmKey {

		/** 穴位状态（0未激活（灰），1激活（浅蓝），2 全部点亮（深蓝）） */
		short REALM_CAVE_STATE_1700              = 1700;
		/** 境界属性（1炼气，2筑基，3结丹） */
		short REALM_PROPERTY_1701                = 1701;
		/** 额外加成属性（属性名称） */
		short REALM_PROPERTY_EXTRA_NAME_1702     = 1702;
		/** 额外加成值（属性值） */
		short REALM_PROPERTY_EXTRA_VALUE_1703    = 1703;
		/** 固定属性 */
		short REALM_PROPERTY_FIXED_PROPERTY_1704 = 1704;
		/** 固定属性值 */
		short REALM_PROPERTY_FIXED_VALUE_1705    = 1705;
		/** 成功率 */
		short REALM_SUCCESS_RATE_1706            = 1706;
		/** 护符状态 */
		short REALM_AMULET_STATE_1707            = 1707;
		/** 雷劫冷却百分比 */
		short REALM_AMULET_PERCENT_1708          = 1708;
		/** 冷却时间 */
		short REALM_COOL_TIME_1709               = 1709;
		/** 穴位全部开启 */
		short REALM_CAVE_ALLOPEN_1710            = 1710;
		/** 穴位焦点 */
		short REALM_CAVE_FOCUS_1711              = 1711;
		/** 穴位焦点 */
		short REALM_THUNDER_DOING_1712           = 1712;
		/** 穴位ID */
		short REALM_ACUPOINT_ID_1714             = 1714;
	}

	public static interface SkillProKey {
		/** 生命 */
		int PRO_1800_ = 1800;
		/** 攻击 */
		int PRO_1801_ = 1801;
		/** 防御 */
		int PRO_1802_ = 1802;
		/** 破甲 */
		int PRO_1803_ = 1803;
		/** 格挡 */
		int PRO_1804_ = 1804;
		/** 命中 */
		int PRO_1805_ = 1805;
		/** 闪避 */
		int PRO_1806_ = 1806;
		/** 暴击 */
		int PRO_1807_ = 1807;
		/** 韧性 */
		int PRO_1808_ = 1808;
		/** 吸血 */
		int PRO_1809_ = 1809;
		/** 反弹 */
		int PRO_1810_ = 1810;
		/** 生命高于对方时，伤害增加N% */
		int PRO_1811_ = 1811;
		/** 攻击高于对方时，伤害增加N% */
		int PRO_1812_ = 1812;
		/** 防御高于对方时，伤害增加N% */
		int PRO_1813_ = 1813;
		/** 被攻击时，生命高于对方，伤害减少N% */
		int PRO_1814_ = 1814;
		/** 被攻击时，攻击高于对方，伤害减少N% */
		int PRO_1815_ = 1815;
		/** 被攻击时，防御高于对方，伤害减少N% */
		int PRO_1816_ = 1816;
		/** 自身生命每降低15%，造成伤害增加N% */
		int PRO_1817_ = 1817;
		/** 自身生命每降低15%，受到伤害减少N% */
		int PRO_1818_ = 1818;
		/** 对攻击型宠物造成的伤害增加N% */
		int PRO_1819_ = 1819;
		/** 对防御型宠物造成的伤害增加N% */
		int PRO_1820_ = 1820;
		/** 对辅助型宠物造成的伤害增加N% */
		int PRO_1821_ = 1821;
		/** 受到攻击型宠物攻击时，伤害降低N% */
		int PRO_1822_ = 1822;
		/** 受到防御型宠物攻击时，伤害降低N% */
		int PRO_1823_ = 1823;
		/** 受到辅助型宠物攻击时，伤害降低N% */
		int PRO_1824_ = 1824;
		/** 受到近程攻击时，所受的伤害降低N% */
		int PRO_1825_ = 1825;
		/** 受到远程攻击时，所受的伤害降低N% */
		int PRO_1826_ = 1826;
		/** 伤害增加N% */
		int PRO_1827_ = 1827;
		/** 死亡时，为己方随机一个成员增加N%的伤害效果，直到战斗结束 */
		int PRO_1828_ = 1828;
		/** 死亡时，为己方随机一个成员减少N%受到的伤害，直到战斗结束 */
		int PRO_1829_ = 1829;
		/** 击杀目标时，回复自身N%的生命 */
		int PRO_1830_ = 1830;
		/** 造成的伤害有N%可以转化为自身生命 */
		int PRO_1831_ = 1831;
		/** 释放技能后，为自己追加一次回血效果 */
		int PRO_1832_ = 1832;
		/** 造成的伤害有一定比例转化为己方血量最少宠物的生命 */
		int PRO_1833_ = 1833;
		/** 死亡时，为己方血量最少的宠物回复一次生命 */
		int PRO_1834_ = 1834;
		/** 死亡时，为己方全体回复一次生命 */
		int PRO_1835_ = 1835;
		/** 增加己方攻击型宠物N%的攻击 */
		int PRO_1836_ = 1836;
		/** 增加己方防御型宠物N%的防御 */
		int PRO_1837_ = 1837;
		/** 增加己方辅助型宠物N%的生命 */
		int PRO_1838_ = 1838;
	}
}
