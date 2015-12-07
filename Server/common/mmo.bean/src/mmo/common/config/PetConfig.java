package mmo.common.config;

public final class PetConfig {
	/** 宠物进阶等级上限 */
	public static final byte ADVANCE_MAX = 5;
	/** 1星 */
	public static final byte STAR_1      = 1;
	/** 2星 */
	public static final byte STAR_2      = 2;
	/** 3星 */
	public static final byte STAR_3      = 3;
	/** 4星 */
	public static final byte STAR_4      = 4;
	/** 5星 */
	public static final byte STAR_5      = 5;

	public static class PetPropType {
		/** 攻击型 */
		public static final byte TYPE_ATTACK  = 0;
		public static String     NAME_ATTACK  = "攻击型";
		/** 防御型 */
		public static final byte TYPE_DEFENCE = 1;
		public static String     NAME_DEFENCE = "防御型";
		/** 辅助型 */
		public static final byte TYPE_HELP    = 2;
		public static String     NAME_HELP    = "辅助型";

		/**
		 * 通过类型名称获取类型
		 * 
		 * @param name
		 * @return
		 */
		public static byte getType(String name) {
			if (NAME_HELP.equals(name)) {
				return TYPE_HELP;
			} else if (NAME_ATTACK.equals(name)) {
				return TYPE_ATTACK;
			} else if (NAME_DEFENCE.equals(name)) {
				return TYPE_DEFENCE;
			} else {
				return 0;
			}
		}

		public static String getTypeName(byte type) {
			if (type == TYPE_HELP) {
				return NAME_HELP;
			} else if (type == TYPE_ATTACK) {
				return NAME_ATTACK;
			} else if (type == TYPE_DEFENCE) {
				return NAME_DEFENCE;
			} else {
				return StringLib.CommonStr.commonNo;
			}
		}
	}

	/***
	 * 按职业划分宝宝类型
	 * 
	 * @author 李天喜
	 * 
	 */
	public static class Profession {
		/** 治疗 */
		public static final byte cure   = 1;
		/** 战斗 */
		public static final byte battle = 2;
		/** 尸王 */
		public static final byte KING   = 3;
	}

	/**
	 * 宠物出战状态
	 */
	public static class State {
		/** 出战 */
		public static final byte  OPEN      = 1;
		/** 休息 */
		public static final byte  CLOSE     = 0;
		/** 出生 */
		public static final short Born      = 10;
		/** 跟随 */
		public static final short Follow    = 11;
		public static final short AutoSpell = 12;
		/** 在协战场景隐藏 */
		public static final short HIDE      = 21;
	}

	/**
	 * 战斗模式
	 * 
	 */
	public static class BattleMode {
		/** 闲逛-不攻击 */
		public static final byte NO_ATTACK_0 = 2;
		/** 主动-攻击角色攻击的对象 */
		public static final byte ATTACK_1    = 1;
		/** 被动-攻击第一个攻击角色的对象 */
		public static final byte BE_ATTACK_2 = 0;
	}

	/**
	 * 宠物穴位的激活状态
	 * 
	 * @author 肖琼
	 * 
	 */
	public static class PetAcupointState {
		/** 未激活 */
		public static final byte NO_ACTIVITY  = 0;
		/** 可激活 */
		public static final byte CAN_ACTIVITY = 2;
		/** 已激活 */
		public static final byte ACTIVITY_ED  = 1;
	}

	/**
	 * 宠物的种族
	 * 
	 * @author 肖琼
	 * 
	 */
	public static class PetRace {
		/** 人族 */
		public static final byte HUMAN = 1;
		/** 妖族 */
		public static final byte YAO   = 2;
		/** 魔族 */
		public static final byte DEMON = 3;
	}

	/**
	 * 阵容状态
	 * 
	 * @author 肖琼
	 * 
	 */
	public static class ArrayState {
		/** 无 */
		public static final byte NONE    = 0;
		/** 默认出战 */
		public static final byte DEFAULT = 1;
	}

	public static class SourceMark {
		/** 碎片召唤 */
		public static final byte SOURCE_CHIPS_1   = 1;
		/** 祭坛召唤 */
		public static final byte SOURCE_ALTAR_2   = 2;
		/** 机器人的伙伴 */
		public static final byte SOURCE_ROBOT_3   = 3;
		/** 任务奖励 */
		public static final byte SOURCE_MISSION_4 = 4;
		/** 限时抽取活动 */
		public static final byte SOURCE_LIMIT_5   = 5;
		/** GM指令获得 */
		public static final byte SOURCE_GM_6      = 6;
		/** 其它 */
		public static final byte SOURCE_OTHER_7   = 7;
	}

}
