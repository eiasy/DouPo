package mmo.common.protocol.ui.main;

public interface Main_1400_sect {
	short main_1400_sect    = 1400;
	short main_1401_manager = 1401;

	interface Sub_1400 {
		/** 宗门信息 */
		byte                     sub_0_SectInfo      = 0;
		/** 宗门府第 */
		byte                     sub_1_Mansion       = 1;
		/** 宗门库房 */
		public static final byte sub_2_StoreGoods    = 2;
		/** 宗门金库 */
		public static final byte sub_3_Burse         = 3;
		/** 宗门兽园 */
		byte                     sub_4_BeastPark     = 4;
		/** 宗门书院 */
		public static final byte sub_5_College       = 5;
		/** 宗门兵部 */
		public static final byte sub_6_WarMinsitry   = 6;
		/** 增强兵力 */
		public static final byte sub_10_StrongArm    = 10;
		/** 招兵买马 */
		public static final byte sub_11_AddArm       = 11;
		/** 军备研究 */
		public static final byte sub_12_ExploreEquip = 12;
		/** 购买兵力 */
		public static final byte sub_16_BuyArm       = 16;
		/** 库房信息 */
		public static final byte sub_17_StoreInfo    = 17;
		/** 发起宗战 */
		byte                     sub_19_SectBattle   = 19;
		/** 阵法列表 */
		byte                     sub_20_SpellList    = 20;
		/** 通过关键字筛选宗门 */
		byte                     sub_23_FilterByName = 23;
		/** 查看领地 */
		public static final byte sub_24_LookManor    = 24;

	}

	interface Sub_1401 {
		/** 宗门信息 */
		public static final byte sub_0_SectInfo    = 0;
		/** 宗门成员 */
		public static final byte sub_1_SectMember  = 1;
		/** 请求加入宗门的角色列表 */
		public static final byte sub_2_RequestRole = 2;
	}
}
