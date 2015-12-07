package mmo.common.protocol.ui.main;

/**
 * 系统商城
 * 
 * @author 李天喜
 * 
 */
public interface Main_1200_SystemShop {
	/** 主类别-普通商城 */
	short main_1200_CommonShop          = 1200;
	/** 主类别-系统商城 */
	short main_1201_SystemShop          = 1201;
	/** 每日签到 */
	short main_1202_SIGN                = 1202;
	/** 等级礼包 */
	short main_1203_LEVEL_AWARD         = 1203;
	/** 神秘小瓶 */
	short main_1204_TREE                = 1204;
	/** 打坐 */
	short main_1205_DA_ZUO              = 1205;
	/** 等级限时礼包 */
	short main_1206_LIMIT_LEVEL_GIFT    = 1206;
	/** 抽卡 */
	short main_1207_PET_CARD            = 1207;
	/** 抽卡结果 */
	short main_1208_PET                 = 1208;
	/** VIP信息 */
	short main_1209_VIP_INFO            = 1209;
	/** VIP奖品 */
	short main_1210_VIP_BAG             = 1210;
	/** 充值商店 */
	short main_1211_CHARGE_SHOP         = 1211;
	/** 限时宠物 */
	short main_1212_LIMIT_PET           = 1212;
	/** 财源广进 */
	short main_1219_MORE_MONEY          = 1219;

	/** 冲级排行活动 */
	short main_1213_rank_level          = 1213;
	/** 冲级排行奖品 */
	short main_1214_rank_level_award    = 1214;
	/** 斗法排行活动 */
	short main_1215_rank_doufa          = 1215;
	/** 斗法排行奖品 */
	short main_1216_rank_doufa_award    = 1216;
	/** 战力排行活动 */
	short main_1217_rank_fight          = 1217;
	/** 战力排行奖品 */
	short main_1218_rank_fight_award    = 1218;
	/** 消费返还 */
	short main_1220_amount_return       = 1220;
	/** 消费返还奖励 */
	short main_1221_amount_return_award = 1221;
	/** 充值返还 */
	short main_1222_rechare_return      = 1222;
	/** 充话费 */
	short main_1223_phone_money         = 1223;
	/** 首冲礼包 */
	short main_1224_firstRecharge       = 1224;
	/** 在线礼包 */
	short main_1225_onlineAward         = 1225;
	/** 7天登录礼包 */
	short main_1226_sevenDay            = 1226;
	/** 7天登录礼包物品数据 */
	short main_1227_sevenDayGoods       = 1227;
	/** 次日留存领话费 */
	short main_1228_nextDay             = 1228;
	/** 召唤成就 */
	short main_1229_callScore           = 1229;
	/** 普通签到奖励 */
	short main_1230_sign_goods          = 1230;
	/** 豪华签到奖励 */
	short main_1231_charge_sign_goods   = 1231;
	/** 月卡活动 */
	short main_1232_month_card          = 1232;
	/** 限时充值活动信息 */
	short main_1233_TIME_CHARGE         = 1233;
	/** 限时充值奖励 */
	short main_1234_TIME_CHARGE_AWARD   = 1234;
	/** 限时兑换活动信息 */
	short main_1235_TIME_EXCHANGE       = 1235;
	/** 限时兑换材料 */
	short main_1236_EXCHANGE_MATERIAL   = 1236;
	/** 限时消费活动信息 */
	short main_1237_TIME_COST           = 1237;
	/** 获取限时消费奖励 */
	short main_1238_COST_AWARD          = 1238;

	/** 开服基金活动 */
	short main_1239_foundation          = 1239;

	interface Sub_1233 {
		/** 信息 */
		short sub_0_INFO   = 0;
		/** 限时充值 */
		short sub_1_CHARGE = 1;
	}

	interface Sub_1235 {
		/** 信息 */
		short sub_0_INFO     = 0;
		/** 基金 */
		short sub_1_EXCHANGE = 1;
	}

	interface Sub_1239 {
		/** 信息 */
		short sub_0_INFO       = 0;
		/** 基金 */
		short sub_1_FOUNDATION = 1;
		/** 福利 */
		short sub_2_WELFARE    = 2;
	}

	interface Sub_1204 {
		/** 神秘小瓶信息 */
		short sub_0_INFO     = 0;
		/** 摇钱树信息 */
		short sub_1_YAO_QIAN = 1;
		/** 仙丹树信息 */
		short sub_2_XIAN_DAN = 2;
	}

	interface Sub_1201 {
		/** 系统商城（道具） */
		short sub_0_ITEM            = 0;

		/** 系统商城（礼包） */
		short sub_1_GIFT            = 1;

		/** 系统商城（机缘） */
		short sub_2_JY              = 2;
		/** 系统商城（荣誉） */
		short sub_3_RY              = 3;
		/** 神秘商店 */
		short sub_4_MYSTERY_SHOP    = 4;
		/** 系统商城(时装) */
		short sub_5_FASH_SHOP       = 5;
		/** 特惠商城 */
		short sub_6_SPECIAL_SHOP    = 6;
		/** 月卡商城 */
		short sub_8_MONTH_CARD_SHOP = 8;
	}

	interface Sub_1202 {
		/** 签到信息 */
		short sub_0_sign_info   = 0;
		/** 普通签到 */
		short sub_1_sign        = 1;
		/** 豪华签到 */
		short sub_2_sign_charge = 2;
	}

	interface Sub_1232 {
		/** 活动信息 */
		short sub_0_info      = 0;
		/** 购买后可以直接领取的奖励 */
		short sub_1_award_dir = 1;
		/** 购买后每天领取的奖励 */
		short sub_2_award_day = 2;
	}
}
