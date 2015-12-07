package mmo.common.protocol.game.sub;

public interface SubPro_1128_CHALLENGE {
	/** 打开单人竞技场 */
	byte OPT_1_OPEN_SINGLE_ARENA    = 1;
	/** 挑战单人竞技场 */
	byte OPT_2_ENTER_SINGLE_ARENA   = 2;
	/** 挑战成功 */
	byte OPT_3_DEFIER_SUCCEED       = 3;
	/** 挑战失败 */
	byte OPT_4_DEFIER_FAIL          = 4;
	/** 排行榜查看角色所在页 */
	byte OPT_7_CUR_PAGE             = 7;
	/** 查看排行榜第一页 */
	byte TOP_PAGE                   = 8;
	/** 平局 */
	byte DEFIER_DOGFALL             = 9;
	/** 查看可领取的斗法奖励 */
	byte CHECK_ARENA_AWARD          = 10;
	/** 竞技场说明 */
	byte OPT_11_OPEN_EXPLAIN        = 11;

	/** 竞技场斗法 上一页 */
	byte PRE_PAGE_12                = 12;
	/** 竞技场斗法 下一页 */
	byte NEXT_PAGE_13               = 13;
	/** 换一批 */
	byte REFRESH_ENEMY_14           = 14;
	/** 重置时间 */
	byte PRO_15_RESET_TIME          = 15;
	/** 查看规则 */
	byte PRO_16_REGULAR             = 16;
	/** 对战记录 */
	byte OPT_17_HISTORY             = 17;
	/** 兑换奖励 */
	byte PRO_18_TO_SHOP_NPC         = 18;

	/** 打开斗法-机缘商店 */
	byte OPT_20_OPEN_JY_SHOP        = 20;

	/** 等级排行 */
	byte OPT_21_RANK_LEVEL          = 21;
	/** 战力排行 */
	byte OPT_22_RANK_FIGHT          = 22;
	/** 斗法排行 */
	byte OPT_23_RANK_CHALLENGE      = 23;
	/** 攻城排行 */
	byte OPT_24_RANK_CITY           = 24;
	/** 元宝排行 */
	byte OPT_25_RANK_YUAN_BAO       = 25;
	/** 灵石排行 */
	byte OPT_26_RANK_LING_SHI       = 26;
	/** 打开秘境界面 */
	byte OPT_27_OPEN_SECRET         = 27;
	/** 点击开启的秘境 */
	byte OPT_28_CLICK_SECRTE        = 28;
	/** 取消多人秘境排队 */
	byte OPT_29_CANCEL_SECRET_QUEUE = 29;
	/** 刷新机缘商店 */
	byte OPT_30_UPDATE_JY_SHOP      = 30;
	/** 私战 */
	byte OPT_31_PRIVATE_CHALLENGE   = 31;
}
