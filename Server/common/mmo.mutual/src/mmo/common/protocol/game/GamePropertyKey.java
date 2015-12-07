package mmo.common.protocol.game;

import java.util.HashMap;
import java.util.Map;

public final class GamePropertyKey {

	public static final class PropertyListKey {
		public static final short ROLE  = 1;
		public static final short GOODS = 2;
	}

	public static final class GoodsProListKey {
		public static final short BASE   = 1;
		public static final short EXPAND = 2;
	}

	// /** 数量 */
	// public static final short COUNT_10 = 10;
	/** 耐久度 */
	public static final short              HARDINESS_47          = 47;
	/** 升级需要获得的经验值 */
	public static final short              LEVEL_EXPERIENCE_48   = 48;
	/** 角色和英雄的关系 */
	/** 家族 */
	public static final short              FAMILY_51             = 51;
	/** 背包格子ID */
	public static final short              POSGRID_55            = 55;
	/** 技能最大等级 */
	public static final short              SKILL_MAX_LEVEL_60    = 60;
	/** 技能下一等级介绍 */
	public static final short              NEXT_LEVEL_NOTE_64    = 64;
	/** 下一招技能 */
	public static final short              NEXT_SKILL_68         = 68;
	/** 技能标记（主被动，首招……） */
	public static final short              SKILL_FLAG_69         = 69;

	/** 惩罚倒计时 */
	public static final short              punishTime_82         = 82;
	/** 201 邮件货币状态 */
	public static final short              EMAIL_MENEY_STATE     = 201;
	/** 118 卖给商店的价格 */
	public static final short              MONEY_PRICE_118       = 118;
	/** 卡牌状态 */
	public static final short              cardState             = 129;
	/** 宗门势力 */
	public static final short              SECTGROUP_144         = 144;
	/** 成功率 */
	public static final short              succRate_134          = 134;
	/** 精炼价格 */
	public static final short              strengPrice_135       = 135;
	/** 记住密码 */
	public static final short              savePassword_136      = 136;
	/** 宗门职位 */
	public static final short              sectPosition_137      = 137;
	/** 宗门名称 */
	public static final short              sectName_138          = 138;
	/** 公共冷却编号 */
	public static final short              commonCool_140        = 140;
	/** 达到下一的充值上限 */
	public static final short              nextLeveSup_142       = 142;
	/** 达到下一级的充值进度 */
	public static final short              nextLevelProgress_143 = 143;
	/** 势力 */
	public static final short              group_144             = 144;
	/** AI类型 */
	public static final short              aiCate_146            = 146;
	/** 摆摊标语 */
	public static final short              shop_150              = 150;
	/** 角色战斗状态 */
	public static final short              battleState_154       = 154;

	public static final Map<Short, String> PROPS                 = new HashMap<Short, String>();
	/** 战斗属性名称与key的映射 */
	public static final Map<String, Short> NAME_KEY              = new HashMap<String, Short>();
	static {
		// PROPS.put(COUNT_10, "数量");
		PROPS.put(HARDINESS_47, "耐久");
		PROPS.put(CommonGamePropertyKey.tempKey.TEMP_CHECK_2121, "鉴定");
		PROPS.put(CommonGamePropertyKey.RoleKey.ROLE_RELATION_707, "境界点");
		PROPS.put(CommonGamePropertyKey.MissionKey.MISSION_DESCRIPTOR_1550, "任务描述");
		PROPS.put(CommonGamePropertyKey.MissionKey.MISSION_DIALOG_1553, "对话");
	}

	public static String getPropertyName(short property) {
		String name = PROPS.get(property);
		if (name == null) {
			return property + "：未匹配到名称";
		}
		return name;
	}

	/**
	 * 通过战斗属性名称获取key
	 * 
	 * @param name
	 * @return
	 */
	public static short getKeyByName(String name) {
		Short key = NAME_KEY.get(name);
		if (key == null) {
			return 0;
		}
		return key;
	}
}
