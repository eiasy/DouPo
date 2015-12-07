package mmo.common.protocol.ui.main;

public interface Main_0_account {
	/** 常用 */
	short main_1_common          = 1;
	/** 功能 */
	short main_2_function        = 2;
	/** 服务器信息 */
	byte  main_3_servers         = 3;
	/** 游戏设置 */
	byte  main_4_set             = 4;
	/** 物品库 */
	byte  main_6_goods           = 6;
	/** 邮件的附件 */
	byte  main_9_Attachment      = 9; // TODO 被废弃的主类别9
	/** 提示客户端确认操作 */
	byte  main_13_CONFIRM_UI     = 13;
	/** 队伍 */
	byte  main_14_team           = 14; // TODO 被废弃的主类别14
	/** 主角物品孔 */
	byte  main_32_goodsHole      = 32; // TODO 被废弃的主类别32
	/** 物品 */
	byte  main_33_targetGoods    = 33;
	/** 焦点物品孔 */
	byte  main_35_focusGoodsHole = 35; // TODO 被废弃的主类别35
	/** 境界 */
	byte  main_41_realm          = 41;
	/** 主类别 */
	byte  main_43_rank           = 43;
	/** 主类别 */
	byte  main_44_notice         = 44;
	/** 活动奖励 */
	byte  main_45_activityAward  = 45;
	/** 目标奖励 */
	byte  main_46_targetAward    = 46;
	/** 主类别 */
	byte  main_48_dayLoginAward  = 48;
	/** 教程 */
	byte  main_51_teacher        = 51;
	/** 主类别 */
	byte  main_54_chargeAward    = 54;

	interface Sub_1 {
		/** 客户端系统提示 */
		byte  sub_2_clientMessage     = 2;
		/** 系统提示 */
		byte  sub_3_message           = 3;
		/** 对话 */
		byte  sub_4_dialog            = 4;
		byte  sub_1_6_Guid            = 6;
		/** 创建角色 */
		byte  sub_9_CreateRole        = 9;
		byte  sub_7_SystemNotice      = 7;
		/** 查看说明 */
		byte  sub_15_checkExplaininfo = 15;
		byte  sub_16_Gm               = 16;
		byte  sub_17_Clienturl        = 17;
		byte  sub_20_Channel          = 20;
		byte  sub_21_RoleQueue        = 21;
		byte  sub_22_OnlineNotice     = 22;
		/** 客户端版本 */
		short sub_101_clientVersion   = 101;
		/** 兑换界面子类别 */
		byte  sub_102_codeKey         = 102;
	}

	interface Sub_3 {
		/** 服务器列表 */
		byte sub_0_List     = 0;
		/** 最后一次登录的服务器 */
		byte sub_1_Last     = 1;
		/** 推荐服务器 */
		byte sub_2_Suggest  = 2;
		/** 服务器分类 */
		byte sub_3_category = 3;
	}

	interface Sub_4 {
		byte sub_0_Gameset = 0;
	}

	interface Sub_41 {
		final byte sub_0_list = 0;
	}

	interface Sub_43 {
		/** 子类别 */
		byte sub_0_rank = 0;
	}

	interface Sub_44 {
		/** 服务器通知 */
		public final static byte sub_0_ServerNotice = 0;

	}

	interface Sub_48 {
		/** 离线经验 */
		byte sub_1_offLineExperience = 1;
	}

	interface Sub_54 {
		/** 首充奖励 */
		byte sub_1_chargeFirst = 1;
	}

	interface Serial_2_0 {
		/** 功能-秘境 */
		short serial_16_secretScene      = 16;
		/** 功能-秘境奖励 */
		short serial_17_secretSceneAward = 17;
		/** 功能-商城 */
		short serial_23_shop             = 23;
	}

	interface Sub_2 {
		byte sub_0_function = 0;
	}

	interface Serial_44_0 {
		/** 公告 */
		public final static byte   indexNotice       = 0;
		/** 私聊 */
		public final static byte   indexChatPrivate  = 1;
		/** 邮件 */
		public final static byte   indexEmail        = 2;
		/** 物品损坏 */
		public final static byte   indexGoodsBad     = 3;
		/** 队伍聊天 */
		public final static byte   indexChatTeam     = 4;
		/** 留言 */
		public final static byte   indexLeaveMessage = 5;
		/** 拍卖 */
		public final static byte   indexSalePublic   = 6;
		/** 宗战 */
		public final static byte   indexSectBattle   = 7;

		/** 公告 */
		public final static String nameNotice        = "【公告】";
		/** 私聊 */
		public final static String nameChatPrivate   = "【私聊】";
		/** 邮件 */
		public final static String nameEmail         = "【邮件】";
		/** 物品损坏 */
		public final static String nameGoodsBad      = "【物品损坏】";
		/** 队伍聊天 */
		public final static String nameChatTeam      = "【队伍聊天】";
		/** 留言 */
		public final static String nameLeaveMessage  = "【留言】";
		/** 拍卖 */
		public final static String nameSalePublic    = "【拍卖】";
		/** 宗战 */
		public final static String nameSectBattle    = "【宗战】";
	}
}
