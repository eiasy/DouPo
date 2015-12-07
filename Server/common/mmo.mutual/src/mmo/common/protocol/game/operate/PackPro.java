package mmo.common.protocol.game.operate;

/**
 * 背包相关的操作标志
 */
public interface PackPro {
	/** 查看物品 */
	public final static byte LOOK_MISSION_GOODS       = 1;
	/** 装上装备 */
	public final static byte EQUIP_GOODS              = 2;
	/** 使用物品 */
	public final static byte USE_GOODS                = 3;
	/** 碎片合成 */
	public final static byte COMPOS_CHIPS             = 4;
	/** 出售物品 */
	public final static byte SELL_GOODS               = 5;
	/** 卸载装备 */
	public final static byte UNEQUIP_GOODS            = 8;
	/** 刷新孔的颜色 */
	public final static byte UPDATE_HOLE              = 7;
	/** 查看洗孔消耗 */
	public final static byte HOLE_COSTS               = 9;
	/** 购买装备 */
	public final static byte BUY_GOODS                = 10;
	/** 打开镶嵌孔 */
	public final static byte OPEN_HOLE                = 11;
	/** 镶嵌宝石 */
	public final static byte ADD_STONE                = 12;
	/** 强化 */
	public final static byte STRENGTHEN_GOODS         = 13;
	/** 摘除 */
	public final static byte REMOVE_STONE             = 14;
	/** 聊天栏的物品 */
	public final static byte CHAT_LOOK_GOODS          = 15;
	/** 个人摆摊上架 */
	public final static byte PUTAWAY_GOODS            = 17;
	/** 个人摆摊下架 */
	public final static byte RECEVING_GOODS           = 18;
	/** 个人摆摊购买 */
	public final static byte STORAGE_BUY_GOODS        = 19;
	/** 打开仓库 */
	public final static byte STORE_OPEN               = 20;
	/** 加载仓库内的物品 */
	public final static byte STORE_LOAD_GOODS         = 21;
	/** 把背包中的物品移入仓库 */
	public final static byte STORE_ENTER              = 22;
	/** 把仓库中的物品移入背包 */
	public final static byte STORE_OUT                = 23;
	/** 打开系统商城 */
	public final static byte OPEN_SYSTEM_SHOP         = 24;
	/** 查看VIP Box */
	public final static byte VIP_BOX                  = 27;
	/** 购买精力值 */
	public final static byte BUY_JINGLI               = 30;
	/** 解除装备绑定 */
	public final static byte REMOVE_BINGD             = 34;
	/** 查看VIP宝箱 */
	public final static byte LOOK_VIP_BOX             = 35;
	/** 分解物品 */
	public final static byte RESOLVE_GOODS            = 36;
	/** 摘除全部宝石 */
	public final static byte REMOVE_ALL_STONE         = 40;
	/** 一键穿戴装备 */
	public final static byte ONE_KEY_EQUIP            = 44;
	/** 自动强化 */
	public final static byte ONE_KEY_STRENGTH         = 45;
	/** 刷新神秘商店 */
	public final static byte OPT_46_MYSTREY_REFRESH   = 46;
	/** 打开神秘商店 */
	public final static byte OPT_47_OPEN_MYSTERY_SHOP = 47;
	/** 查看商店礼包详细 */
	public final static byte OPT_48_SHOP_GIFT_DETAIL  = 48;
	/** 打开充值商店 */
	public final static byte OPT_49_OPEN_CHARGE_UI    = 49;
	/** 卸载装备（需要伙伴ID，装备ID） */
	public final static byte OPT_50_UNEQUIP_NEW       = 50;
	/** 装备法宝 */
	public final static byte OPT_51_EQUIP_FA_BAO      = 51;
	/** 卸载法宝 */
	public final static byte OPT_52_UNEQUIP_FA_BAO    = 52;
	/** 合成装备 */
	public final static byte OPT_53_COMPOSITE_EQUIP   = 53;
	/** 选择合成装备 */
	public final static byte OPT_54_SELECT_EQUIP      = 54;
	/** 教程合成装备材料 */
	public final static byte OPT_55_MATERIAL          = 55;
	/** 打开斗法商店 */
	public final static byte OPT_56_CHALLENGE_SHOP    = 56;
	/** 打开一站到底商店 */
	public final static byte OPT_57_STAND_SHOP        = 57;
	/** 打开月卡商店 */
	public final static byte OPT_58_MONTH_CARD_SHOP   = 58;
	/** 打开月卡 */
	public final static byte OPT_59_MONTH_CARD        = 59;
	/** 领取月卡奖励 */
	public final static byte OPT_60_MONTH_CARD_AWARD  = 60;
	/** 打开开服基金 */
	public final static byte OPT_61_FOUNDATION        = 61;
	/** 刷新全民福利 */
	public final static byte OPT_62_WELFARE           = 62;
	/** 开服基金充值 */
	public final static byte OPT_63_FOUNDATION_CHARGE = 63;
	/** 领取全民福利 */
	public final static byte OPT_64_GET_WELFARE       = 64;
	/** 领取基金奖励 */
	public final static byte OPT_65_GET_FOUNDATION    = 65;
	/** 打开限时累计充值 */
	public final static byte OPT_66_TIME_CHARGE       = 66;
	/** 领取限时累计充值奖励 */
	public final static byte OPT_67_TIME_CHARGE_AWARD = 67;
	/** 打开限时兑换 */
	public final static byte OPT_68_TIME_EXCHANGE     = 68;
	/** 兑换物品 */
	public final static byte OPT_69_EXCHANG_GOODS     = 69;
	/** 打开限时累计消耗 */
	public final static byte OPT_70_TIME_COST         = 70;
	/** 领取限时累计消耗奖励 */
	public final static byte OPT_71_TIME_COST_AWARD   = 71;
	/** 合成伙伴 */
	public final static byte OPT_72_COMPOSITE_PET     = 72;
	/** 分解伙伴 */
	public final static byte OPT_73_DECOMPOSE_PET     = 73;
	/** 选择伙伴 */
	public final static byte OPT_74_SELECT_PET        = 74;
	/** 预览礼包 */
	public final static byte OPT_75_PREVIEW           = 75;
}