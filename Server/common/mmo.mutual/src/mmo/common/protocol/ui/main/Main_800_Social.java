package mmo.common.protocol.ui.main;

public interface Main_800_Social {
	/** 社交 */
	short main_802_socrial          = 802;
	/** 邮件 */
	short main_804_email            = 804;
	/** 通知消息 */
	short main_806_tip              = 806;
	/** 领奖中心 */
	short main_808_awardCenter      = 808;
	/** 领奖中心奖品 */
	short main_809_awardCenterGoods = 809;
	/** 排行榜界面 */
	short main_810_rank             = 810;
	/** 我在排行中的位置 */
	short main_811_rank_my_position = 811;

	interface Sub_800 {
		/** 推荐好友 */
		short sub_800_4_SEARCH     = 4;
		/** 推荐好友 */
		short sub_800_14_RECOMMEND = 14;

	}

	interface Sub_802 {
		/** 好友审批 */
		short sub_802_10_APPROVAL  = 10;
		/** 好友列表 */
		short sub_802_0_friend     = 0;
		/** 周围玩家（客户端） */
		short sub_802_1_around     = 1;
		/** 临时或最近访问 */
		short sub_802_2_temp       = 2;
		/** 建立队伍 */
		short sub_802_3_team       = 3;
		/** 基本信息 */
		short sub_802_100_BASEINFO = 100;

	}

	interface Sub_804 {
		byte sub_0_receive  = 0;
		byte sub_1_send     = 1;
		byte sub_2_newEmail = 2;
	}

	interface Sub_810 {
		/** 等级排行 */
		short sub_0_level     = 0;
		/** 战力排行 */
		short sub_1_fight     = 1;
		/** 斗法排行 */
		short sub_2_challenge = 2;
		/** 攻城排行 */
		short sub_3_city      = 3;
		/** 元宝排行 */
		short sub_4_yuanbao   = 4;
		/** 灵石排行 */
		short sub_5_lingshi   = 5;

	}

	/** 好友审批 */
	short index_802_100_10_approve = 10;

}
