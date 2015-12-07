package mmo.common.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SystemActivityType {
	/** 老服活动 */
	public final static byte                CATE_0_OLD                  = 0;
	/** 新服活动 */
	public final static byte                CATE_1_NEW                  = 1;
	/***************************************************************************************/
	/** 累计充值 */
	public final static short               TYPE_1_CHARTE_TOTAL         = 1;
	/** 消费返礼 */
	public final static short               TYPE_2_CONSUME              = 2;
	/** 充值返礼 */
	public final static short               TYPE_3_CHARGE               = 3;
	/** 等级可购买礼包 */
	public final static short               TYPE_4_LEVEL_BAG            = 4;
	// /** 怪物攻城 */
	// public final static short TYPE_5_ATTACK_CITY = 5;
	// /** 世界首领 */
	// public final static short TYPE_6_LEADER = 6;
	/** 首充双倍 */
	public final static short               TYPE_7_CHARGE_FIRST         = 7;
	/** 充值返利 */
	public final static short               TYPE_8_CHARGE_BACK          = 8;
	/** 充值额外赠送 */
	public final static short               TYPE_9_CHARGE_EXTEND        = 9;
	/** 限时宠物 */
	public final static short               TYPE_10_LIMIT_PET           = 10;
	/** 财源猛进 */
	public final static short               TYPE_11_PICK_YUANBAO        = 11;
	/** 特惠商城 */
	public final static short               TYPE_12_DAY_LOW_PRICE       = 12;
	/** 冲级排行榜 */
	public final static short               TYPE_13_RANK_LEVEL          = 13;
	/** 斗法排行榜 */
	public final static short               TYPE_14_RANK_CHALLENGE      = 14;
	/** 战力排行榜 */
	public final static short               TYPE_15_RANK_FIGHT          = 15;
	/** 首充礼包 */
	public final static short               TYPE_16_CHARGE_FIRST_GIFT   = 16;
	/** 领取话费 */
	public final static short               TYPE_17_PHONE               = 17;
	/** 连续登录7天 */
	public final static short               TYPE_18_7_DAY               = 18;
	/** 在线奖励 */
	public final static short               TYPE_19_ONLINE_TIME         = 19;
	/** 领取充值卡 */
	public final static short               TYPE_20_RECHARGE_CARD       = 20;
	/** 首充双倍 */
	public final static short               TYPE_21_CHARGE_FIRST_DOUBLE = 21;
	/** 限时累计充值 */
	public final static short               TYPE_22_TIME_CHARGE         = 22;
	/** 限时兑换 */
	public final static short               TYPE_23_TIME_EXCHANGE       = 23;
	/** 限时累计消耗 */
	public final static short               TYPE_24_TIME_COST           = 24;
	/** 月卡 */
	public final static short               TYPE_25_MONTH_CARD          = 25;
	/** 开服基金 */
	public final static short               TYPE_26_FOUNDATION          = 26;
	/** 全民福利 */
	public final static short               TYPE_27_WELFARE             = 27;
	/** 月卡商店 */
	public final static short               TYPE_28_MONTH_CARD_SHOP     = 28;
	private final static Map<String, Short> TYPE_NAMES                  = new HashMap<String, Short>();

	static {
		TYPE_NAMES.put("月卡商店", TYPE_28_MONTH_CARD_SHOP);
		TYPE_NAMES.put("全民福利", TYPE_27_WELFARE);
		TYPE_NAMES.put("开服基金", TYPE_26_FOUNDATION);
		TYPE_NAMES.put("月卡", TYPE_25_MONTH_CARD);
		TYPE_NAMES.put("限时累计消耗", TYPE_24_TIME_COST);
		TYPE_NAMES.put("限时兑换", TYPE_23_TIME_EXCHANGE);
		TYPE_NAMES.put("限时累计充值", TYPE_22_TIME_CHARGE);
		TYPE_NAMES.put("首充双倍", TYPE_21_CHARGE_FIRST_DOUBLE);
		TYPE_NAMES.put("领取充值卡", TYPE_20_RECHARGE_CARD);
		TYPE_NAMES.put("在线奖励", TYPE_19_ONLINE_TIME);
		TYPE_NAMES.put("连续登录7天", TYPE_18_7_DAY);
		TYPE_NAMES.put("累计充值", TYPE_1_CHARTE_TOTAL);
		TYPE_NAMES.put("消费返礼", TYPE_2_CONSUME);
		TYPE_NAMES.put("充值返礼", TYPE_3_CHARGE);
		TYPE_NAMES.put("等级礼包", TYPE_4_LEVEL_BAG);
		// TYPE_NAMES.put("怪物攻城", TYPE_5_ATTACK_CITY);
		// TYPE_NAMES.put("世界首领", TYPE_6_LEADER);
		TYPE_NAMES.put("首充奖励", TYPE_7_CHARGE_FIRST);
		TYPE_NAMES.put("充值返利", TYPE_8_CHARGE_BACK);
		TYPE_NAMES.put("充值额外赠送", TYPE_9_CHARGE_EXTEND);
		TYPE_NAMES.put("限时宠物", TYPE_10_LIMIT_PET);
		TYPE_NAMES.put("财源猛进", TYPE_11_PICK_YUANBAO);
		TYPE_NAMES.put("特惠商城", TYPE_12_DAY_LOW_PRICE);
		TYPE_NAMES.put("冲级排行榜", TYPE_13_RANK_LEVEL);
		TYPE_NAMES.put("斗法排行榜", TYPE_14_RANK_CHALLENGE);
		TYPE_NAMES.put("战力排行榜", TYPE_15_RANK_FIGHT);
		TYPE_NAMES.put("首充礼包", TYPE_16_CHARGE_FIRST_GIFT);
		TYPE_NAMES.put("领取话费", TYPE_17_PHONE);
	}

	public final static String[] getTypeNames() {
		String[] names = new String[TYPE_NAMES.size()];
		TYPE_NAMES.keySet().toArray(names);
		return names;
	}

	public final static short getTypeValue(String typeName) {
		Short value = TYPE_NAMES.get(typeName);
		if (value == null) {
			return 0;
		}
		return value;
	}

	public final static String getTypeName(short typeValue) {
		Set<String> keys = TYPE_NAMES.keySet();
		for (String k : keys) {
			if (TYPE_NAMES.get(k) == typeValue) {
				return k;
			}
		}
		return "未知";
	}
}
