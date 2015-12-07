package mmo.common.protocol.ui.main;

public interface Main_200_Equip {
	/** 物品 主类别 */
	short main_200_GoodsInfo      = 200;

	/** 洗孔消耗 主类别 */
	short main_202_HoleCost       = 202;
	/** 物品镶嵌 主类别 */
	short main_203_GoodsInlay     = 203;
	/** 查看物品 主类别 */
	short main_204_LookGoods      = 204;
	/** 装备的法宝 */
	short main_221_equipedFabao   = 221;
	/** 阵容信息 主类别 */
	short main_222_ArrayInfo      = 222;
	/** 阵容装备 主类别 */
	short main_223_ArrayEquip     = 223;

	// ////////////////////////版本分割线////////////////////
	/** 阵容 */
	short main_224_ArrayInfo      = 224;
	/** 查看阵容 */
	short main_225_CheckArrayInfo = 225;
	/** 查看阵容详细 */
	short main_226_petDetail      = 226;

	interface Main_224_Sub {
		/** 普通阵容 */
		short common_0_array     = 0;
		/** 斗法阵容 */
		short doufa_1_array      = 1;
		/** 世界BOSS */
		short worldBoss_2_array  = 2;
		/** 一战到底 */
		short one_stand_3_array  = 3;
		/** 秘籍 */
		short miJing_4_array     = 4;
		/** 怪物攻城 */
		short monsterAtk_5_array = 5;

	}
}