package mmo.common.config.mission;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.tools.util.StringUtil;

public class FinishCondition {
	/** 与NPC对话 */
	public static final int                   DIALOG                         = 1;
	/** 采集物品 */
	public static final int                   PLUCK                          = 4;
	/** 游历 */
	public static final int                   YOU_LI                         = 3;
	/** 收集道具 */
	public static final int                   ITEM                           = 2;
	/** 闯关故事 */
	public static final int                   PASS_DUPLICATE                 = 5;
	/** 杀死怪物 */
	public static final int                   KILL                           = 8;
	/** 使用物品 */
	public static final int                   USE_GOODS                      = 127;
	/** 送包裹 */
	public static final int                   PKG                            = 128;
	/** 挑战英雄 */
	public static final int                   PASS_HERO_COUNTS               = 132;
	/** 关卡星级 */
	public static final int                   GATE_STAR_COUNTS               = 134;
	/** 斗法高手 */
	public static final int                   DOUFA_WIN_COUNTS               = 135;
	/** 斗法高手 */
	public static final int                   DOUFA_CONTINUE_WIN_COUNTS      = 136;
	/** 交友广泛 */
	public static final int                   FRIEND_ADD                     = 141;
	/** 秘境冒险 */
	public static final int                   FIND_MJ                        = 146;
	/** 秘境冒险 */
	public static final int                   PASS_MJ                        = 147;
	/** 寻宝达人 */
	public static final int                   FIND_TREASURE                  = 152;
	/** 玩家升级 */
	public static final int                   ROLE_LEVEL                     = 155;
	/** 伙伴升级 */
	public static final int                   PET_LEVEL                      = 156;
	/** 强化装备 */
	public static final int                   STRENG_EQUIP                   = 157;
	/** 提升进阶 */
	public static final int                   PET_ADVANCE                    = 158;
	/** 贵宾身份 */
	public static final int                   VIP_LEVEL                      = 160;
	/** 突破境界 */
	public static final int                   REALM_LEVEL                    = 161;
	/** 收集伙伴 */
	public static final int                   PET_COUNTS                     = 162;
	/** 收集装备 */
	public static final int                   EQUIP_COUNTS                   = 163;
	/** 召唤达人 */
	public static final int                   CALL_COUNTS                    = 164;
	/** 宝箱达人 */
	public static final int                   BOX_COUNTS                     = 165;
	/** 乐于助人 */
	public static final int                   HELP_WAR_COUNTS                = 166;
	/** 屌丝逆袭 */
	public static final int                   PASS_HARD_DUP                  = 167;
	/** 参与宗战 */
	public static final int                   JOIN_SECT_BATTLE               = 168;
	/** 任务达人 */
	public static final int                   COMMIT_MISSION_COUNT           = 169;
	/** 进入场景 */
	public static final int                   ENTER_SCENE_COUNT              = 170;
	/** 故事 */
	public static final int                   PASS_COMMON_COUNTS             = 171;
	/** 一战到底 */
	public static final int                   ENTER_ONE_STAND                = 172;
	/** 宝石合成 */
	public static final int                   STONE_COMPOSITION              = 173;
	/** 打孔 */
	public static final int                   HOLE_OPEN                      = 174;
	/** 洗孔 */
	public static final int                   HOLE_UPDATE                    = 175;
	/** 世界首领 */
	public static final int                   LEADER                         = 176;
	/** 送体力 */
	public static final int                   GRANT_POWER                    = 177;
	/** 摇钱树 */
	public static final int                   SHAKE_MONEY_TREE               = 178;
	/** 强化装备次数 */
	public static final int                   STRENGTH_COUNT                 = 179;
	/** 伙伴升级次数 */
	public static final int                   PET_LEVEL_COUNT                = 180;
	/** 仙府升级 */
	public static final int                   MANSION_LEVEL                  = 181;
	/** 通关区域 */
	public static final int                   PASS_AREA                      = 182;
	/** 斗法 */
	public static final int                   DOUFA_JOIN_COUNTS              = 183;

	public static final String                TITLE_USE_GOODS                = "使用道具";
	public static final String                TITLE_KILL                     = "杀死怪物";
	public static final String                TITLE_GET_GOODS                = "获取道具";
	public static final String                TITLE_PASS_DUP                 = "通关副本";
	public static final String                TITLE_MUTUAL_NPC               = "与NPC交互";
	public static final String                TITLE_PACKGE                   = "送包裹";

	public static final String                TITLE_FRIEND_ADD               = "新增好友";
	public static final String                TITLE_PASS_HERO_COUNT          = "挑战英雄";
	public static final String                TITLE_GATE_STAR_COUNT          = "关卡星级";
	public static final String                TITLE_DOUFA_WIN_COUNT          = "斗法胜利";
	public static final String                TITLE_DOUFA_CONTINUE_WIN_COUNT = "斗法连胜";
	public static final String                TITLE_FIND_MJ                  = "发现秘境";
	public static final String                TITLE_PASS_MJ                  = "挑战秘境";
	public static final String                TITLE_FIND_TREASURE            = "寻宝达人";
	public static final String                TITLE_ROLE_LEVEL               = "角色升级";
	public static final String                TITLE_PET_LEVEL                = "伙伴升级";
	public static final String                TITLE_STRENG_EQUIP             = "强化装备";
	public static final String                TITLE_PET_ADVANCE              = "伙伴进阶";
	public static final String                TITLE_VIP_LEVEL                = "贵宾身份";
	public static final String                TITLE_REALM_LEVEL              = "突破境界";
	public static final String                TITLE_PET_COUNTS               = "收集伙伴";
	public static final String                TITLE_EQUIP_COUNTS             = "收集装备";
	public static final String                TITLE_CALL_COUNTS              = "召唤达人";
	public static final String                TITLE_BOX_COUNTS               = "宝箱达人";
	public static final String                TITLE_JOIN_SECT_BATTLE         = "参与宗战";
	public static final String                TITLE_COMMIT_MISSION           = "完成任务";
	public static final String                TITLE_ENTER_SCENE              = "进入场景";
	public static final String                TITLE_STRENGTH_COUNT           = "提升强化等级";
	public static final String                TITLE_PET_LEVEL_COUNT          = "提升伙伴等级";
	public static final String                TITLE_DOUFA_JOIN               = "斗法挑战";

	private static final Map<String, Integer> FINISH_VALUE                   = new HashMap<String, Integer>();
	static {
		FINISH_VALUE.put(TITLE_KILL, KILL);
		FINISH_VALUE.put(TITLE_GET_GOODS, ITEM);
		FINISH_VALUE.put(TITLE_MUTUAL_NPC, DIALOG);
		FINISH_VALUE.put(TITLE_PASS_DUP, PASS_DUPLICATE);
		FINISH_VALUE.put(TITLE_PACKGE, PKG);
		FINISH_VALUE.put(TITLE_FRIEND_ADD, FRIEND_ADD);
		FINISH_VALUE.put(TITLE_PASS_HERO_COUNT, PASS_HERO_COUNTS);
		FINISH_VALUE.put(TITLE_GATE_STAR_COUNT, GATE_STAR_COUNTS);
		FINISH_VALUE.put(TITLE_DOUFA_WIN_COUNT, DOUFA_WIN_COUNTS);
		FINISH_VALUE.put(TITLE_DOUFA_CONTINUE_WIN_COUNT, DOUFA_CONTINUE_WIN_COUNTS);
		FINISH_VALUE.put(TITLE_FIND_MJ, FIND_MJ);
		FINISH_VALUE.put(TITLE_PASS_MJ, PASS_MJ);
		FINISH_VALUE.put(TITLE_FIND_TREASURE, FIND_TREASURE);
		FINISH_VALUE.put(TITLE_ROLE_LEVEL, ROLE_LEVEL);
		FINISH_VALUE.put(TITLE_PET_LEVEL, PET_LEVEL);
		FINISH_VALUE.put(TITLE_STRENG_EQUIP, STRENG_EQUIP);
		FINISH_VALUE.put(TITLE_PET_ADVANCE, PET_ADVANCE);
		FINISH_VALUE.put(TITLE_VIP_LEVEL, VIP_LEVEL);
		FINISH_VALUE.put(TITLE_REALM_LEVEL, REALM_LEVEL);
		FINISH_VALUE.put(TITLE_PET_COUNTS, PET_COUNTS);
		FINISH_VALUE.put(TITLE_EQUIP_COUNTS, EQUIP_COUNTS);
		FINISH_VALUE.put(TITLE_CALL_COUNTS, CALL_COUNTS);
		FINISH_VALUE.put(TITLE_BOX_COUNTS, BOX_COUNTS);
		FINISH_VALUE.put(TITLE_DOUFA_JOIN, DOUFA_JOIN_COUNTS);

	}

	public static int getConditionValue(String title) {
		return FINISH_VALUE.get(title);
	}

	public static final String[] getConditionTitles() {
		Set<String> keys = FINISH_VALUE.keySet();
		String[] values = new String[keys.size()];
		keys.toArray(values);
		return values;
	}

	public static final class TargetItems {
		private final static char                 SUB_TAG                 = '】';
		/** 标签与数值的映射 */
		private final static Map<String, Integer> TAG_ITEM                = new HashMap<String, Integer>();
		/** 采集 */
		public final static int                   ITEM_PLUCK              = 1;
		/** 收集 */
		public final static int                   ITEM_COLLECT            = 2;
		/** 打怪 */
		public final static int                   ITEM_MONSTER            = 3;
		/** 游历 */
		public final static int                   ITEM_YOU_LI             = 4;
		/** 送信 */
		public final static int                   ITEM_MAIL               = 5;
		/** 副本 */
		public final static int                   ITEM_DUP                = 6;
		/** 关卡 */
		public final static int                   ITEM_GATE               = 7;
		/** 英雄 */
		public final static int                   ITEM_HERO               = 8;
		/** 关卡星级 */
		public final static int                   ITEM_GATE_STAR          = 9;
		/** 发现秘境 */
		public final static int                   ITEM_FIND_MJ            = 10;
		/** 通关秘境 */
		public final static int                   ITEM_PASS_MJ            = 11;
		/** 寻宝 */
		public final static int                   ITEM_TREASURE           = 12;
		/** 角色升级 */
		public final static int                   ITEM_ROLE_LEVEL         = 13;
		/** 伙伴升级 */
		public final static int                   ITEM_PET_LEVEL          = 14;
		/** 强化装备 */
		public final static int                   ITEM_STRENG_EQUIP       = 15;
		/** 伙伴进阶 */
		public final static int                   ITEM_PET_ADVANCE        = 16;
		/** 境界 */
		public final static int                   ITEM_REALM              = 17;
		/** VIP */
		public final static int                   ITEM_VIP                = 18;
		/** 收集法宝 */
		public final static int                   ITEM_COLLECT_FABAO      = 19;
		/** 收集伙伴 */
		public final static int                   ITEM_COLLECT_PET        = 20;
		/** 收集装备 */
		public final static int                   ITEM_COLLECT_EQUIP      = 21;
		/** 召唤 */
		public final static int                   ITEM_CALL               = 22;
		/** 宝箱 */
		public final static int                   ITEM_BOX                = 23;
		/** 添加好友 */
		public final static int                   ITEM_ADD_FRIEND         = 24;
		/** 协战 */
		public final static int                   ITEM_HELP_WAR           = 25;
		/** 挑战难度 */
		public final static int                   ITEM_PASS_HARD          = 26;
		/** 斗法胜利 */
		public final static int                   ITEM_DOUFA_WIN          = 27;
		/** 斗法连胜 */
		public final static int                   ITEM_DOUFA_CONTINUE_WIN = 28;
		/** 故事 */
		public final static int                   ITEM_COMMON_DUP         = 29;
		/** 一战到底 */
		public final static int                   ITEM_ONE_STAND          = 30;
		/** 宝石合成 */
		public final static int                   ITEM_STONE_COMPOSITION  = 31;
		/** 打孔 */
		public final static int                   ITEM_OPEN_HOLE          = 32;
		/** 洗孔 */
		public final static int                   ITEM_UPDATE_HOLE        = 33;
		/** 世界首领 */
		public final static int                   ITEM_LEADER             = 34;
		/** 送体力 */
		public final static int                   ITEM_GRANT_POWER        = 35;
		/** 摇钱树 */
		public final static int                   ITEM_SHAKE_MONEY_TREE   = 36;
		/** 强化装备次数 */
		public final static int                   ITEM_STRENGTH_COUNT     = 37;
		/** 伙伴升级次数 */
		public final static int                   ITEM_PET_LEVEL_COUNT    = 38;
		/** 仙府升级 */
		public final static int                   ITEM_MANSION_LEVEL      = 39;
		/** 通关区域 */
		public final static int                   ITEM_PASS_AREA          = 40;
		/** 斗法挑战 */
		public final static int                   ITEM_DOUFA_JOIN         = 41;
		static {
			TAG_ITEM.put("【采集】", ITEM_PLUCK);
			TAG_ITEM.put("【收集】", ITEM_COLLECT);
			TAG_ITEM.put("【打怪】", ITEM_MONSTER);
			TAG_ITEM.put("【游历】", ITEM_YOU_LI);
			TAG_ITEM.put("【送信】", ITEM_MAIL);
			TAG_ITEM.put("【副本】", ITEM_DUP);
			TAG_ITEM.put("【关卡】", ITEM_GATE);
			TAG_ITEM.put("【英雄】", ITEM_HERO);
			TAG_ITEM.put("【关卡星级】", ITEM_GATE_STAR);
			TAG_ITEM.put("【发现秘境】", ITEM_FIND_MJ);
			TAG_ITEM.put("【挑战秘境】", ITEM_PASS_MJ);
			TAG_ITEM.put("【寻宝】", ITEM_TREASURE);
			TAG_ITEM.put("【玩家升级】", ITEM_ROLE_LEVEL);
			TAG_ITEM.put("【伙伴升级】", ITEM_PET_LEVEL);
			TAG_ITEM.put("【强化装备】", ITEM_STRENG_EQUIP);
			TAG_ITEM.put("【伙伴进阶】", ITEM_PET_ADVANCE);
			TAG_ITEM.put("【境界】", ITEM_REALM);
			TAG_ITEM.put("【VIP】", ITEM_VIP);
			TAG_ITEM.put("【收集法宝】", ITEM_COLLECT_FABAO);
			TAG_ITEM.put("【伙伴收集】", ITEM_COLLECT_PET);
			TAG_ITEM.put("【收集装备】", ITEM_COLLECT_EQUIP);
			TAG_ITEM.put("【召唤】", ITEM_CALL);
			TAG_ITEM.put("【宝箱】", ITEM_BOX);
			TAG_ITEM.put("【添加好友】", ITEM_ADD_FRIEND);
			TAG_ITEM.put("【协战】", ITEM_HELP_WAR);
			TAG_ITEM.put("【挑战困难】", ITEM_PASS_HARD);
			TAG_ITEM.put("【斗法胜利】", ITEM_DOUFA_WIN);
			TAG_ITEM.put("【斗法】", ITEM_DOUFA_JOIN);
			TAG_ITEM.put("【斗法连胜】", ITEM_DOUFA_CONTINUE_WIN);
			TAG_ITEM.put("【故事】", ITEM_COMMON_DUP);
			TAG_ITEM.put("【一战到底】", ITEM_ONE_STAND);
			TAG_ITEM.put("【宝石合成】", ITEM_STONE_COMPOSITION);
			TAG_ITEM.put("【打孔】", ITEM_OPEN_HOLE);
			TAG_ITEM.put("【洗孔】", ITEM_UPDATE_HOLE);
			TAG_ITEM.put("【世界首领】", ITEM_LEADER);
			TAG_ITEM.put("【送体力】", ITEM_GRANT_POWER);
			TAG_ITEM.put("【摇钱树】", ITEM_SHAKE_MONEY_TREE);
			TAG_ITEM.put("【强化装备次数】", ITEM_STRENGTH_COUNT);
			TAG_ITEM.put("【伙伴升级次数】", ITEM_PET_LEVEL_COUNT);
			TAG_ITEM.put("【阵容升级】", ITEM_MANSION_LEVEL);
			TAG_ITEM.put("【通关】", ITEM_PASS_AREA);
		}

		public final static int getTargetItem(String targetItem) {
			if (targetItem == null) {
				return 0;
			}
			Integer result = TAG_ITEM.get(StringUtil.subString(targetItem, SUB_TAG));
			if (result == null) {
				return 0;
			}
			return result;
		}
	}

	public static final boolean isPointConditionFlag(int flag) {
		switch (flag) {
			case PASS_DUPLICATE:
			case PASS_COMMON_COUNTS:
			case PASS_HARD_DUP:
			case PASS_HERO_COUNTS:
			case DOUFA_WIN_COUNTS:
			case DOUFA_CONTINUE_WIN_COUNTS:
			case PASS_MJ:
			case ENTER_SCENE_COUNT:
			case ENTER_ONE_STAND:
			case LEADER:
			case PASS_AREA:
				return true;
			default:
				return false;
		}
	}

}
