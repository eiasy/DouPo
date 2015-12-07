package mmo.common.config.goods;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 物品类别
 * 
 * @author 李天喜
 * 
 */
public class GoodsCate {
	/** 衣服 */
	public static final short CLOTHES         = 1;
	/** 鞋子 */
	public static final short SHEOES          = 2;
	/** 戒指 */
	public static final short RING            = 3;
	/** 武器 */
	public static final short WEAPON          = 4;
	/** 项链 */
	public static final short NECKLACE        = 5;
	/** 玉佩 */
	public static final short JADE            = 6;
	/** 头盔 */
	public static final short HELMET          = 7;
	/** 法宝 */
	public static final short TRUMP           = 8;
	/** 秘籍 */
	public static final short SKILL_BOOK      = 9;
	/** 储物袋 */
	public static final short BAG             = 10;
	/** 时装 */
	public static final short FASH            = 11;
	/** 符箓 */
	public static final short SYMBOL          = 12;
	/** 翅膀 */
	public static final short CLOAK           = 13;
	/** 坐骑 */
	public static final short SIT             = 14;
	/** 宝石 */
	public static final short STONE           = 15;
	/** 附魔石 */
	public static final short MAGIC           = 16;
	/** 消耗品 */
	public static final short DEPLETE         = 51;
	/** 材料 */
	public static final short MATERIAL        = 52;
	/** 任务道具 */
	public static final short MISSION         = 53;
	/** 技能书 */
	public static final short SKILL_STUDY     = 54;
	/** 传送符 */
	public static final short TRANSFER        = 56;
	/** 任务卷 */
	public static final short MISSION_BOOK    = 57;
	/** 药品 */
	public static final short DRUG_HP         = 59;
	/** 鉴定符 */
	public static final short CHECK           = 61;
	/** 副本令牌 */
	public static final short TOKEN           = 62;
	/** 摘除符 */
	public static final short REMOVE          = 63;
	/** 增加神兽的经验 */
	public static final short ADD_BEAST_EXP   = 64;
	/** 增加神兽的开心度 */
	public static final short ADD_BEAST_HAPPY = 65;
	/** 货币 */
	public static final short MONEY           = 66;
	/** 合成材料 */
	public static final short COMPOSITE       = 68;
	/** 图纸 */
	public static final short DRAWING         = 69;
	/** 宠物 */
	public static final short PET             = 70;
	/** 传音 */
	public static final short VOICE           = 71;
	/** 护符 */
	public static final short AMULET          = 72;
	/** 复活 */
	public static final short RELIVE          = 73;
	/** 精元丹 */
	public static final short JIN_YUAN_DAN    = 74;
	/** 血包 */
	public static final short HP_BAG          = 75;
	/** 礼包 */
	public static final short GIFT_BAG        = 76;
	/** 兑换 */
	public static final short ITEM_EXCHANGE   = 77;
	/** 宠物分身 */
	public static final short FEN_SHEN        = 78;
	/** 装备碎片 */
	public static final short EQUIP_CHIP      = 79;
	/** 宠物礼包 */
	public static final short GIFT_PET        = 80;
	/** 藏宝图钥匙 */
	public static final short MAP_KEY         = 81;
	/** 道具 */
	public static final short ITEM_82         = 82;
	/** 月卡 */
	public static final short CARD_MONTH_83   = 83;
	/** 充值 */
	public static final short CHARGE_84       = 84;
	/** 技能点 */
	public static final short SKILL_POINT_85  = 85;
	/** 宝箱 */
	public static final short BOX             = 86;
	/** 基金 */
	public static final short FOUNDATION      = 87;

	/** 是否是货币类别 */
	public final static boolean isMoneyCate(short cate) {
		return cate == MONEY;
	}

	/** 是否是装备类别 */
	public final static boolean isEquipCate(short cate) {
		return cate == CLOTHES || cate == SHEOES || cate == RING || cate == WEAPON || cate == NECKLACE || cate == JADE || cate == HELMET;
	}

	/** 是否是法宝类别 */
	public final static boolean isTrumpCate(short cate) {
		return cate == TRUMP;
	}

	/** 是否是宠物类别 */
	public final static boolean isPetCate(short cate) {
		return cate == PET;
	}

	/** 是否是宝石类别 */
	public final static boolean isStoneCate(short cate) {
		return cate == STONE || cate == MAGIC;
	}

	/** 是否是时装类别 */
	public final static boolean isFashCate(short cate) {
		return cate == FASH || cate == SIT || cate == CLOAK;
	}

	/** 是否是坐骑类别 */
	public final static boolean isSitCate(short cate) {
		return cate == SIT;
	}

	/** 是否是翅膀类别 */
	public final static boolean isCloakCate(short cate) {
		return cate == CLOAK;
	}

	/** 是否是装备碎片 */
	public final static boolean isEquipChip(short cate) {
		return cate == EQUIP_CHIP;
	}

	/** 是否是宠物分身 */
	public final static boolean isPetFenShen(short cate) {
		return cate == FEN_SHEN;
	}

	/** 是否是藏宝图钥匙 */
	public final static boolean isMapKey(short cate) {
		return cate == MAP_KEY;
	}

	private static final Map<Short, String> CATES = new HashMap<Short, String>();

	static {
		CATES.put(FOUNDATION, "基金");
		CATES.put(WEAPON, "武器");
		CATES.put(CLOTHES, "衣服");
		CATES.put(SHEOES, "鞋子");
		CATES.put(HELMET, "头盔");
		CATES.put(CLOAK, "翅膀");
		CATES.put(NECKLACE, "项链");
		CATES.put(JADE, "玉佩");
		CATES.put(RING, "戒指");
		CATES.put(TRUMP, "法宝");
		CATES.put(STONE, "宝石");
		CATES.put(MAGIC, "附魔石");
		CATES.put(FASH, "时装");
		CATES.put(DEPLETE, "消耗品");
		CATES.put(MATERIAL, "材料");
		CATES.put(MISSION, "任务道具");
		CATES.put(SKILL_STUDY, "技能书");
		CATES.put(SKILL_BOOK, "秘籍");
		CATES.put(TRANSFER, "传送符");
		CATES.put(MISSION_BOOK, "任务卷");
		CATES.put(BAG, "储物袋");
		CATES.put(DRUG_HP, "药品");
		CATES.put(CHECK, "鉴定符");
		CATES.put(TOKEN, "副本令牌");
		CATES.put(REMOVE, "摘除符");
		CATES.put(ADD_BEAST_EXP, "增加神兽经验");
		CATES.put(ADD_BEAST_HAPPY, "增加神兽开心度");
		CATES.put(MONEY, "货币");
		// CATES.put(FABAO_CHIP, "法宝碎片");
		CATES.put(SIT, "坐骑");
		CATES.put(COMPOSITE, "合成材料");
		CATES.put(DRAWING, "图纸");
		CATES.put(PET, "宠物");
		CATES.put(VOICE, "传音");
		CATES.put(AMULET, "护符");
		CATES.put(RELIVE, "复活");
		CATES.put(JIN_YUAN_DAN, "精元丹");
		CATES.put(HP_BAG, "血包");
		CATES.put(GIFT_BAG, "礼包");
		CATES.put(BOX, "宝箱");
		CATES.put(GIFT_PET, "伙伴礼包");
		CATES.put(MAP_KEY, "藏宝图钥匙");
		CATES.put(ITEM_EXCHANGE, "兑换");
		CATES.put(FEN_SHEN, "分身");
		CATES.put(EQUIP_CHIP, "碎片");
		CATES.put(ITEM_82, "道具");
		CATES.put(CARD_MONTH_83, "月卡");
		CATES.put(CHARGE_84, "充值");
		CATES.put(SKILL_POINT_85, "技能点");
	}

	public static final short getCateValue(String cateName) {
		if (cateName == null) {
			return MATERIAL;
		}
		Set<Short> keys = CATES.keySet();
		for (short key : keys) {
			if (cateName.equals(CATES.get(key))) {
				return key;
			}
		}
		return MATERIAL;
	}

	public static final String[] getCateNames() {
		Collection<String> values = CATES.values();
		String[] names = new String[values.size()];
		values.toArray(names);
		return names;
	}

	public static final String getCateName(short key) {
		String name = CATES.get(key);
		if (name == null) {
			name = key + ":NULL";
		}
		return name;
	}

	/**
	 * 通过6大装备类型获取位置序号
	 * 
	 * @param cate
	 * @return
	 */
	public static final int getIndexByEquipCate(short cate) {
		return getIndexByEquipCate(cate, (short) 0);
	}

	public static final int getIndexByEquipCate(short cate, short cateSub) {
		switch (cate) {
			case CLOTHES: {
				return GoodsConfig.Grid.CLOTHES;
			}
			case SHEOES: {
				return GoodsConfig.Grid.SHEOES;
			}
			case RING: {
				return GoodsConfig.Grid.RING;
			}
			case WEAPON: {
				return GoodsConfig.Grid.WEAPON;
			}
			case JADE: {
				return GoodsConfig.Grid.JADE;
			}
			case HELMET: {
				return GoodsConfig.Grid.ARMET;
			}
			case FASH: {
				if (cateSub == GoodsCateSub.FASH_CLOTHES) {
					return GoodsConfig.Grid.FASH_CLOTHES;
				} else {
					return GoodsConfig.Grid.FASH_WEAPON;
				}
			}
			case CLOAK: {
				return GoodsConfig.Grid.FASH_CLOAK;
			}
			case SIT: {
				return GoodsConfig.Grid.FASH_MOUNT;
			}
			default:
				return -1;
		}
	}

	public static short getGoodsCate(String cateName) {
		if (cateName.contains("时装")) {
			return FASH;
		} else if (cateName.contains("伙伴礼包")) {
			return GIFT_PET;
		} else {
			String str = null;
			Set<Entry<Short, String>> entrys = CATES.entrySet();
			for (Entry<Short, String> entry : entrys) {
				str = entry.getValue();
				if (str != null && cateName != null && cateName.contains(str)) {
					return entry.getKey();
				}
			}
		}
		return 0;
	}
}