package mmo.common.config.goods;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import mmo.tools.util.MathUtil;

public class GoodsCateSub {
	/** 衣服 */
	public static final short        CLOTHES_1           = 1;
	/** 鞋子 */
	public static final short        SHEOES_1            = 2;
	/** 戒指 */
	public static final short        RING                = 3;
	/** 刀 */
	public static final short        WEAPON_1            = 4;
	/** 剑 */
	public static final short        WEAPON_2            = 5;
	/** 扇子 */
	public static final short        WEAPON_3            = 6;
	/** 全职业 */
	public static final short        WEAPON_ALL          = 7;
	/** 项链 */
	public static final short        NECKLACE            = 8;
	/** 玉佩 */
	public static final short        JADE                = 9;
	/** 头盔 */
	public static final short        ARMET               = 10;
	/** 法宝 */
	public static final short        TRUMP               = 11;
	/** 器灵 */
	public static final short        qiling              = 12;
	/** 秘籍 */
	public static final short        SKILL_BOOK          = 13;
	/** 储物袋 */
	public static final short        BAG                 = 14;
	/** 时装武器 */
	public static final short        FASH_WEAPON         = 15;
	/** 时装衣服 */
	public static final short        FASH_CLOTHES        = 16;
	/** 符箓 */
	public static final short        SYMBOL              = 17;
	/** 翅膀 */
	public static final short        CLOAK               = 18;
	/** 金狮 */
	public static final short        SIT_LINO            = 19;
	/** 雪狐 */
	public static final short        SIT_FOX             = 20;
	/** 附魔-冥王 */
	public static final short        MAGIC_MINGWANG      = 25;
	/** 附魔-疾风 */
	public static final short        MAGIC_JIFENG        = 26;
	/** 附魔-影遁 */
	public static final short        MAGIC_YINGDUN       = 27;
	/** 附魔-伏虎 */
	public static final short        MAGIC_FUHU          = 28;
	/** 附魔-锁神 */
	public static final short        MAGIC_SUOSHEN       = 29;
	/** 附魔-百脉 */
	public static final short        MAGIC_BAIMAI        = 30;
	/** 消耗品 */
	public static final short        DEPLETE             = 31;
	/** 材料 */
	public static final short        MATERIAL            = 32;
	/** 任务物品 */
	public static final short        MISSION             = 33;
	/** 宠物技能书 */
	public static final short        PET_SKILL_STUDY     = 34;
	/** 传送卷 */
	public static final short        TRANSFER            = 35;
	/** 任务卷轴 */
	public static final short        MISSION_BOOK        = 36;
	/** 打孔卷轴 */
	public static final short        HOLE_BOOK           = 37;
	/** 药品丹 */
	public static final short        DRUG_HP_DAN         = 38;
	/** 药品丸 */
	public static final short        DRUG_HP_WAN         = 39;
	/** 鉴定符 */
	public static final short        CHECK               = 40;
	/** 副本令牌 */
	public static final short        TOKEN               = 41;
	/** 摘除符 */
	public static final short        REMOVE              = 42;

	/** 增加神兽的经验 */
	public static final short        ADD_BEAST_EXP       = 43;
	/** 增加神兽的开心度 */
	public static final short        ADD_BEAST_HAPPY     = 44;
	/** 货币-游戏币 */
	public static final short        MONEY_LINGSHI       = 45;
	/** 货币-元宝 */
	public static final short        MONEY_YUANBAO       = 46;
	/** 货币-交易币 */
	public static final short        MONEY_XIANLU        = 47;
	/** 货币-精力值 */
	public static final short        MONEY_JINGLI        = 48;
	/** 货币-境界点 */
	public static final short        MONEY_JINGJIE       = 50;
	/** 货币-荣誉 */
	public static final short        MONEY_RONGYU        = 51;
	/** 货币-杀戮点 */
	public static final short        MONEY_SHALU         = 52;
	/** 货币-经验值 */
	public static final short        MONEY_JINGYAN       = 53;
	/** 货币-阅历 */
	public static final short        MONEY_YUELI         = 54;
	/** 货币-贡献 */
	public static final short        MONEY_GONGXIAN      = 55;
	/** 货币-极品灵石 */
	public static final short        MONEY_JIPIN_LINGSHI = 56;
	/** 货币-机缘 */
	public static final short        MONEY_JIYUAN        = 57;
	/** 货币-修为点 */
	public static final short        MONEY_XIUWEI        = 58;
	/** 货币-建设度 */
	public static final short        MONEY_JIANSHE       = 59;
	/** 货币-战绩 */
	public static final short        MONEY_ZHANJI        = 60;
	/** 货币-技能点 */
	public static final short        MONEY_JINENG        = 61;
	/** 货币-亲密度 */
	public static final short        MONEY_QINMI         = 62;
	/** 货币-培养丹 */
	public static final short        MONEY_PEIYANG       = 63;
	/** 合成材料 */
	public static final short        COMPOSITE           = 64;
	/** 图纸 */
	public static final short        DRAWING             = 65;
	/** 宠物 */
	public static final short        PET                 = 66;
	/** 传音 */
	public static final short        VOICE               = 67;
	/** 护符 */
	public static final short        AMULET              = 68;
	/** 复活 */
	public static final short        RELIVE              = 69;
	/** 精元丹 */
	public static final short        JIN_YUAN_DAN        = 70;
	/** 角色血包 */
	public static final short        HP_BAG_ROLE         = 71;
	/** 宠物血包 */
	public static final short        HP_BAG_PET          = 72;
	/** 礼包 */
	public static final short        GIFT_BAG            = 73;
	/** 兑换 */
	public static final short        ITEM_EXCHANGE       = 74;
	/** 伙伴礼包 */
	public static final short        GIFT_PET            = 75;
	/** 货币-星 */
	public static final short        MONEY_STAR          = 76;
	/** 货币-英雄牌 */
	public static final short        MONEY_HERO          = 77;
	/** 货币-召唤牌 */
	public static final short        MONEY_CALL          = 78;
	/** 货币-体力 */
	public static final short        MONEY_POWER         = 79;
	/** 藏宝图钥匙 */
	public static final short        MAP_KEY             = 80;
	/** 动态礼包 */
	public static final short        GIFT_BAG_DYNAMIC    = 81;
	/** VIP礼包 */
	public static final short        GIFT_VIP_BAG        = 82;
	/** 道具 */
	public static final short        ITEM_83             = 83;
	/** 月卡 */
	public static final short        CARD_MONTH_83       = 83;
	/** 充值 */
	public static final short        CHARGE_84           = 84;
	/** 宝箱 */
	public static final short        BOX                 = 86;
	/** 基金 */
	public static final short        FOUNDATION          = 87;
	/************************* 不能修改的子类别 *******************************/
	/** 宝石-红宝石 */
	public static final short        STONE_RED           = 151;
	/** 宝石-黄宝石 */
	public static final short        STONE_YELLOW        = 152;
	/** 宝石-蓝宝石 */
	public static final short        STONE_BLUE          = 153;
	/** 宝石-绿宝石 */
	public static final short        STONE_GREEN         = 154;

	/******************************************************************/
	public static Map<Short, String> cateSubNames        = new HashMap<Short, String>();
	static {
		cateSubNames.put(CLOTHES_1, "衣服");
		cateSubNames.put(SHEOES_1, "鞋子");
		cateSubNames.put(RING, "戒指");
		cateSubNames.put(WEAPON_1, "刀");
		cateSubNames.put(WEAPON_2, "剑");
		cateSubNames.put(WEAPON_3, "扇子");
		cateSubNames.put(WEAPON_ALL, "全职业");
		cateSubNames.put(NECKLACE, "项链");
		cateSubNames.put(JADE, "玉佩");
		cateSubNames.put(ARMET, "头盔");
		cateSubNames.put(TRUMP, "法宝");
		cateSubNames.put(qiling, "器灵");
		cateSubNames.put(SKILL_BOOK, "秘籍");
		cateSubNames.put(BAG, "储物袋");
		/** 红命石 */
		cateSubNames.put(STONE_RED, "红宝石 ");
		/** 黄击石 */
		cateSubNames.put(STONE_YELLOW, "黄宝石");
		/** 蓝宝石 */
		cateSubNames.put(STONE_BLUE, "蓝宝石");
		/** 绿宝石 */
		cateSubNames.put(STONE_GREEN, "绿宝石");
		cateSubNames.put(MAGIC_MINGWANG, "冥王");
		cateSubNames.put(MAGIC_JIFENG, "疾风");
		cateSubNames.put(MAGIC_YINGDUN, "影遁");
		cateSubNames.put(MAGIC_FUHU, "伏虎");
		cateSubNames.put(MAGIC_SUOSHEN, "锁神");
		cateSubNames.put(MAGIC_BAIMAI, "百脉");
		cateSubNames.put(MONEY_JINGLI, "精力值");
		cateSubNames.put(FASH_WEAPON, "时装武器");
		cateSubNames.put(FASH_CLOTHES, "时装衣服");
		cateSubNames.put(SYMBOL, "符箓");
		cateSubNames.put(CLOAK, "翅膀");
		cateSubNames.put(SIT_LINO, "坐骑");
		cateSubNames.put(SIT_FOX, "坐骑");
		cateSubNames.put(DEPLETE, "消耗品");
		cateSubNames.put(MATERIAL, "材料");
		cateSubNames.put(MISSION, "任务道具");
		cateSubNames.put(PET_SKILL_STUDY, "宠物技能书");
		cateSubNames.put(TRANSFER, "传送符");
		cateSubNames.put(MISSION_BOOK, "任务符");
		cateSubNames.put(HOLE_BOOK, "裂孔锥");
		cateSubNames.put(DRUG_HP_DAN, "药品丹");
		cateSubNames.put(DRUG_HP_WAN, "药品丸");
		cateSubNames.put(CHECK, "鉴定符");
		cateSubNames.put(TOKEN, "副本令牌");
		cateSubNames.put(REMOVE, "摘除符");
		cateSubNames.put(ADD_BEAST_EXP, "增加神兽的经验");
		cateSubNames.put(ADD_BEAST_HAPPY, "增加神兽的开心度 ");
		/** 货币-灵石 */
		cateSubNames.put(MONEY_LINGSHI, "灵石");
		/** 货币-元宝 */
		cateSubNames.put(MONEY_YUANBAO, "元宝");
		/** 货币-仙露 */
		cateSubNames.put(MONEY_XIANLU, "仙露");
		/** 货币-精力值 */
		cateSubNames.put(MONEY_JINGLI, "精力值");
		/** 货币-境界点 */
		cateSubNames.put(MONEY_JINGJIE, "境界点");
		/** 货币-荣誉 */
		cateSubNames.put(MONEY_RONGYU, "荣誉");
		/** 货币-杀戮点 */
		cateSubNames.put(MONEY_SHALU, "杀戮点");
		/** 货币-经验值 */
		cateSubNames.put(MONEY_JINGYAN, "经验值");
		/** 货币-阅历 */
		cateSubNames.put(MONEY_YUELI, "阅历");
		/** 货币-贡献 */
		cateSubNames.put(MONEY_GONGXIAN, "贡献");
		/** 货币-极品灵石 */
		cateSubNames.put(MONEY_JIPIN_LINGSHI, "极品灵石");
		/** 货币-机缘 */
		cateSubNames.put(MONEY_JIYUAN, "机缘");
		cateSubNames.put(MONEY_XIUWEI, "修为点");
		cateSubNames.put(MONEY_JIANSHE, "建设度");
		cateSubNames.put(MONEY_ZHANJI, "战绩");
		cateSubNames.put(MONEY_JINENG, "技能点");
		cateSubNames.put(MONEY_QINMI, "亲密度");
		cateSubNames.put(MONEY_PEIYANG, "培养丹");
		cateSubNames.put(MONEY_STAR, "星");
		cateSubNames.put(MONEY_HERO, "英雄牌");
		cateSubNames.put(MONEY_CALL, "召唤牌");
		cateSubNames.put(MONEY_POWER, "体力");
		/** 合成材料-合成材料 */
		cateSubNames.put(COMPOSITE, "合成材料");
		cateSubNames.put(DRAWING, "图纸");
		cateSubNames.put(PET, "宠物");
		cateSubNames.put(VOICE, "传音");
		cateSubNames.put(AMULET, "护符");
		cateSubNames.put(JIN_YUAN_DAN, "精元丹");
		cateSubNames.put(HP_BAG_ROLE, "角色血包");
		cateSubNames.put(HP_BAG_PET, "宠物血包");
		cateSubNames.put(GIFT_BAG, "礼包");
		cateSubNames.put(ITEM_EXCHANGE, "兑换");
		cateSubNames.put(ITEM_83, "道具");
		cateSubNames.put(CARD_MONTH_83, "月卡");
		cateSubNames.put(CHARGE_84, "充值");
		cateSubNames.put(BOX, "宝箱");
		cateSubNames.put(FOUNDATION, "基金");
	}

	public static final String getCateSubName(short key) {
		String name = cateSubNames.get(key);
		if (name == null) {
			name = key + ":NULL";
		}
		return name;
	}

	public static final short getRandomStone() {
		return (short) MathUtil.getRandom(STONE_RED, STONE_BLUE);
	}

	public static short getGoodsCateSub(short cate, String goodsName, String cateName) {
		switch (cate) {
			case GoodsCate.AMULET: {
				return AMULET;
			}
			case GoodsCate.CARD_MONTH_83: {
				return CARD_MONTH_83;
			}
			case GoodsCate.CHARGE_84: {
				return CHARGE_84;
			}
			case GoodsCate.ITEM_82: {
				return ITEM_83;
			}
			case GoodsCate.DRUG_HP: {
				if (goodsName.endsWith("丹")) {
					return DRUG_HP_DAN;
				}
				return DRUG_HP_WAN;
			}
			case GoodsCate.JIN_YUAN_DAN: {
				return JIN_YUAN_DAN;
			}
			case GoodsCate.MAP_KEY: {
				return MAP_KEY;
			}
			case GoodsCate.HP_BAG: {
				if (goodsName.startsWith("角色")) {
					return HP_BAG_ROLE;
				}
				return HP_BAG_PET;
			}
			case GoodsCate.VOICE: {
				return VOICE;
			}
			case GoodsCate.TRANSFER: {
				return TRANSFER;
			}
			case GoodsCate.RELIVE: {
				return RELIVE;
			}
			case GoodsCate.GIFT_BAG: {
				if (goodsName.startsWith("VIP")) {
					return GIFT_VIP_BAG;
				}
				return GIFT_BAG;
			}
			case GoodsCate.BOX: {
				return BOX;
			}
			case GoodsCate.FOUNDATION: {
				return FOUNDATION;
			}
			case GoodsCate.GIFT_PET: {
				return GIFT_PET;
			}
			case GoodsCate.MISSION: {
				return MISSION;
			}
			case GoodsCate.MONEY: {
				String name = null;
				Set<Entry<Short, String>> entrys = cateSubNames.entrySet();
				for (Entry<Short, String> entry : entrys) {
					name = entry.getValue();
					if (goodsName.contains(name)) {
						return entry.getKey();
					}
				}
				return 0;
			}
			case GoodsCate.WEAPON: {
				return WEAPON_ALL;
			}
			case GoodsCate.CLOTHES: {
				return CLOTHES_1;
			}
			case GoodsCate.HELMET: {
				return ARMET;
			}
			case GoodsCate.SHEOES: {
				return SHEOES_1;
			}
			case GoodsCate.JADE: {
				return JADE;
			}
			case GoodsCate.RING: {
				return RING;
			}
			case GoodsCate.TRUMP: {
				return TRUMP;
			}
			case GoodsCate.FASH: {
				if (cateName.contains("衣服")) {
					return FASH_CLOTHES;
				}
				return FASH_WEAPON;
			}
			case GoodsCate.CLOAK: {
				return CLOAK;
			}
			case GoodsCate.SYMBOL: {
				return SYMBOL;
			}
			case GoodsCate.SIT: {
				if (goodsName.contains("狮")) {
					return SIT_LINO;
				}
				return SIT_FOX;
			}
			case GoodsCate.STONE: {
				if (goodsName.contains("红")) {
					return GoodsCateSub.STONE_RED;
				} else if (goodsName.contains("黄")) {
					return GoodsCateSub.STONE_YELLOW;
				} else if (goodsName.contains("蓝")) {
					return GoodsCateSub.STONE_BLUE;
				} else if (goodsName.contains("绿")) {
					return GoodsCateSub.STONE_GREEN;
				}
				return 0;
			}
			case GoodsCate.MAGIC: {
				if (goodsName.contains("冥王")) {
					return GoodsCateSub.MAGIC_MINGWANG;
				} else if (goodsName.contains("疾风")) {
					return GoodsCateSub.MAGIC_JIFENG;
				} else if (goodsName.contains("影遁")) {
					return GoodsCateSub.MAGIC_YINGDUN;
				} else if (goodsName.contains("伏虎")) {
					return GoodsCateSub.MAGIC_FUHU;
				} else if (goodsName.contains("锁神")) {
					return GoodsCateSub.MAGIC_SUOSHEN;
				}
				return GoodsCateSub.MAGIC_BAIMAI;
			}
			case GoodsCate.PET: {
				return PET;
			}
		}
		return 0;
	}

}