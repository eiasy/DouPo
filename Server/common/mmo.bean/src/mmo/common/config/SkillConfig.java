package mmo.common.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import mmo.common.bean.role.RoleState;
import mmo.common.config.skill.CheatsCate;
import mmo.common.config.skill.SkillCate;
import mmo.common.config.skill.SkillMainType;

public class SkillConfig {
	/** 初始的技能等级=1 */
	public static short DEFAULT_LEVEL  = 1;
	/** 击退的默认距离 */
	public static short REPEL_DISTANCE = 80;
	/** 击退的默认速度值 */
	public static short REPEL_SPEED    = -8;
	/** 突进的默认速度值 */
	public static short FORWARD_SPEED  = 16;
	/** 冲锋的默认速度值 */
	public static short ASSAULT_SPEED  = 32;
	/** BUF协议6008的操作项-添加BUF */
	public static byte  CREATE         = 0;
	/** BUF协议6008的操作项-移除BUF */
	public static byte  CLEAR          = 1;
	/** BUF协议6008的操作项-更新角色 */
	public static byte  UPDATE         = 2;

	/**
	 * 技能类别
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class Cate {
		/** 职业技能 */
		public static final short PROFESSION        = 1;
		/** 法宝技能 */
		public static final short FABAO             = 2;
		/** 宠物技能 */
		public static final short PET               = 3;
		/** BUFF */
		public static final short BUF               = 4;
		/** 怪物技能 */
		public static final short MONSTER           = 5;
		/** 天赋技能 */
		public static final short GENIUS            = 6;
		/** 缘分技能 */
		public static final short LUCKS             = 7;
		/** 新技能 */
		public static final short NEW_SKILL         = 8;
		/** 新BUFF */
		public static final short NEW_BUFF          = 9;
		/** 新怪物技能 */
		public static final short NEW_MONSTER_SKILL = 10;
	}

	/**
	 * 施放类型
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class playType {
		/** 主动 */
		public static final byte   INITIATIVE      = 0;
		public static final String INITIATIVE_NAME = "主动";
		/** 被动 */
		public static final byte   PASSIVITY       = 1;
		public static final String PASSIVITY_NAME  = "被动";
		/** 触发 */
		public static final byte   TRIGGER         = 2;
		public static final String TRIGGER_NAME    = "触发";
	}

	/**
	 * 获取施放类型对应的名称
	 * 
	 * @param type
	 * @return
	 */
	public static final String getPlayTypeName(byte type) {
		if (playType.INITIATIVE == type) {
			return playType.INITIATIVE_NAME;
		} else if (playType.PASSIVITY == type) {
			return playType.PASSIVITY_NAME;
		} else if (playType.TRIGGER == type) {
			return playType.TRIGGER_NAME;
		}
		return StringLib.CommonStr.commonNo;
	}

	/**
	 * 施法对象
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class PlayTarget {
		/** 目标焦点 */
		public static final byte FOCUS  = 0;
		/** 目标区域 */
		public static final byte AREA   = 1;
		/** 自身 */
		public static final byte MYSELF = 2;
	}

	/**
	 * 影响范围
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class EffectScope {
		/** 单体 */
		public static final byte   SINGLE      = 0;
		public static final String SINGLE_NAME = "单体";
		/** 群体 */
		public static final byte   GROUP       = 1;
		public static final String GROUP_NAME  = "群体";
		/** 全体 */
		public static final byte   ALL         = 2;
		public static final String ALL_NAME    = "全体";
	}

	/**
	 * 获取影响范围对应的名称
	 * 
	 * @param cate
	 * @return
	 */
	public static final String getEffectScopeName(short cate) {
		byte type = (byte) cate;
		if (EffectScope.SINGLE == type) {
			return EffectScope.SINGLE_NAME;
		} else if (EffectScope.GROUP == type) {
			return EffectScope.GROUP_NAME;
		} else if (EffectScope.ALL == type) {
			return EffectScope.ALL_NAME;
		}
		return StringLib.CommonStr.commonNo;
	}

	/**
	 * 效果类型
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class EffectType {
		/** 伤害 */
		public static final byte HURT      = 0;
		/** 辅助 */
		public static final byte AUXILIARY = 1;
		/** 全体 */
		public static final byte ALL       = 2;
	}

	/**
	 * BUF类型
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class BufType {
		/** 增益 */
		public static final byte BUFF   = 0;
		/** 减益 */
		public static final byte DEBUFF = 1;
	}

	/**
	 * 效果属性
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class FiveElements {
		/** 金 */
		public static final short GOLD  = 1;
		/** 木 */
		public static final short WOOD  = 2;
		/** 水 */
		public static final short WATER = 3;
		/** 火 */
		public static final short FIRE  = 4;
		/** 土 */
		public static final short EARTH = 5;

		public static short getWuXingValue(String name) {
			if ("金".equals(name)) {
				return SkillConfig.FiveElements.GOLD;
			} else if ("木".equals(name)) {
				return SkillConfig.FiveElements.WOOD;
			} else if ("水".equals(name)) {
				return SkillConfig.FiveElements.WATER;
			} else if ("火".equals(name)) {
				return SkillConfig.FiveElements.FIRE;
			} else if ("土".equals(name)) {
				return SkillConfig.FiveElements.EARTH;
			} else {
				return 0;
			}
		}
	}

	public static final class BufCate {
		/** 反弹 */
		public static final byte REBOUND      = 1;
		/** 吸血 */
		public static final byte SUCK         = 2;
		/** 易伤 */
		public static final byte HURT_EASY    = 3;
		/** 伤害减弱 */
		public static final byte HURT_REDUCE  = 4;
		/** 无敌 */
		public static final byte HURT_IGNORE  = 5;
		/** 溅射 */
		public static final byte HURT_SPLASH  = 7;
		/** 修改属性 */
		public static final byte UPDATE_PROP  = 8;
		/** 只允许存在一个该BUF,效果好的进行替换,如眩晕 */
		public static final byte ONLY_ONE     = 9;
		/** 可同时存在不同来源的该BUF，BUF本身层数上限共享,如迟缓等 */
		public static final byte SHOW_MAX     = 10;
		/** 可同时存在不同来源的该BUF，BUF本身层数独立,如中毒等 */
		public static final byte NOT_SHOW_MAX = 11;
	}

	/***
	 * 释放条件
	 * 
	 * @author 李天喜
	 * 
	 */
	public static final class Condition {
		/** 持有BUF激活 */
		public static final byte HAND_BUF   = 1;
		/** 没有buf激活 */
		public static final byte NO_BUF     = 2;
		/** 持有状态激活 */
		public static final byte HAND_STATE = 3;
	}

	public static final class AtatackEffect {
		private static Map<Integer, String> effectName = new HashMap<Integer, String>();
		/** 暴击攻击 */
		public static final int             crueAttack = 0x01000000;
		/** 抵抗 */
		public static final int             resist     = 0x02000000;
		/** 吸血 */
		public static final int             suck       = 0x03000000;
		/** 反弹 */
		public static final int             rebound    = 0x04000000;
		/** 治疗 */
		public static final int             cure       = 0x05000000;
		/** 攻击 */
		public static final int             attack     = 0x06000000;
		/** 闪避 */
		public static final int             duck       = 0x07000000;
		/** 暴击治疗 */
		public static final int             crueCure   = 0x08000000;
		/** 破防 */
		public static final int             broken     = 0x09000000;
		/** 麻痹 */
		public static final int             palsy      = 0x0A000000;
		/** 使用技能扣MP */
		public static final int             skillMp    = 0x0B000000;
		/** 让客户端强制显示数值 */
		public static final int             showValue  = 0x40000000;

		static {
			effectName.put(crueAttack, "暴击攻击");
			effectName.put(resist, "抵抗");
			effectName.put(suck, "吸血");
			effectName.put(rebound, "反弹");
			effectName.put(cure, "治疗");
			effectName.put(attack, "攻击");
			effectName.put(duck, "闪避");
			effectName.put(crueCure, "暴击治疗");
			effectName.put(broken, "破防");
			effectName.put(palsy, "麻痹 ");
			effectName.put(skillMp, "技能消耗MP ");
		}

		public static String getName(int effect) {
			return effectName.get(effect);
		}
	}

	/**
	 * 角色技能状态
	 * 
	 * @author hp
	 * 
	 */
	public static final class State {
		/** 未开启 */
		public static final byte UNOPEN  = 0;
		/** 未学习 */
		public static final byte UNSTUDY = 1;
		/** 已学习未装备 */
		public static final byte STUDYED = 2;
		/** 装备 */
		public static final byte EQUIPED = 3;
	}

	public static final String getTypeNote(short type, short cate) {
		StringBuilder sb = new StringBuilder();
		sb.append(SkillMainType.getTypeName(type));
		switch (type) {
			case SkillMainType.CHEATS: {
				sb.append("-").append(CheatsCate.getCateName(cate));
				break;
			}
			case SkillMainType.SKILL: {
				sb.append("-").append(SkillCate.getCateName(cate));
				break;
			}
			case SkillMainType.HEART: {
				break;
			}
		}
		return sb.toString();
	}

	public static final class ActionCate {
		public final static byte               NEED_AS    = 1 << 0;
		public final static byte               ICON_BAR   = 1 << 1;
		public final static byte               ICON_HEAD  = 1 << 2;
		public final static byte               ICON_FLASH = 1 << 3;
		// public final static byte ANI_HEAD = 1 << 4;
		// public final static byte ANI_BAR = 1 << 5;
		// public final static byte ANI_FLASH = 1 << 6;
		public final static byte               NEED_ICON  = ICON_BAR | ICON_HEAD | ICON_FLASH;
		// public final static byte NEED_ANI = ANI_HEAD | ANI_BAR | ANI_FLASH;
		private final static Map<Byte, String> CATES      = new HashMap<Byte, String>();
		static {
			CATES.put(NEED_AS, "需要动作脚本");
			CATES.put(ICON_BAR, "BUF栏ICON");
			CATES.put(ICON_HEAD, "头顶ICON");
			CATES.put(ICON_FLASH, "闪动ICON");
			// CATES.put(ANI_HEAD, "头顶动画");
			// CATES.put(ANI_BAR, "BUF栏动画");
			// CATES.put(ANI_FLASH, "闪动动画");
		}

		public final static String[] getIconActionNames() {
			String[] actions = new String[3];
			actions[0] = CATES.get(ICON_BAR);
			actions[1] = CATES.get(ICON_HEAD);
			actions[2] = CATES.get(ICON_FLASH);
			return actions;
		}

		// public final static String[] getAniActionNames() {
		// String[] actions = new String[3];
		// actions[0] = CATES.get(ANI_HEAD);
		// actions[1] = CATES.get(ANI_BAR);
		// actions[2] = CATES.get(ANI_FLASH);
		// return actions;
		// }

		public final static String[] getActionNames() {
			Collection<String> values = CATES.values();
			String[] cateNames = new String[values.size()];
			values.toArray(cateNames);
			return cateNames;
		}

		public final static Byte[] getCateValue() {
			Set<Byte> values = CATES.keySet();
			Byte[] cateValues = new Byte[values.size()];
			values.toArray(cateValues);
			return cateValues;
		}

		public final static String getCateName(byte cate) {
			Set<Byte> values = CATES.keySet();
			String name = "";
			for (Iterator<Byte> iterator = values.iterator(); iterator.hasNext();) {
				Byte byte1 = iterator.next();
				if ((cate & byte1) != 0) {
					name += CATES.get(byte1) + ":";
				}
			}
			if (name == null || name.trim().length() == 0) {
				name = cate + ":-- --";
			}
			return name;
		}
	}

	public static final class Effect {
		public static final byte               PLUS    = 0;                          // 增益
		public static final byte               MINUS   = 1;

		private static final Map<Byte, String> EFFECTS = new HashMap<Byte, String>();
		static {
			EFFECTS.put(PLUS, "  +");
			EFFECTS.put(MINUS, "  -");
		}

		public final static String[] getEffectNames() {
			Collection<String> values = EFFECTS.values();
			String[] cateNames = new String[values.size()];
			values.toArray(cateNames);
			return cateNames;
		}

		public final static Byte[] getEffectKeys() {
			Set<Byte> values = EFFECTS.keySet();
			Byte[] effectValues = new Byte[values.size()];
			values.toArray(effectValues);
			return effectValues;
		}

		public final static String getEffectName(byte effect) {
			String name = EFFECTS.get(effect);
			if (name == null) {
				name = effect + ":NULL";
			}
			return name;
		}
	}

	public static final class Depend {
		private final static Map<Integer, String> DEPEND_STATE = new HashMap<Integer, String>();
		static {

			DEPEND_STATE.put(RoleState.GAME_ATTACK, "攻击");
			DEPEND_STATE.put(RoleState.GAME_DEFENCE, "防御");
			DEPEND_STATE.put(RoleState.GAME_EVIL, "魔化");
			DEPEND_STATE.put(RoleState.GAME_DISTORTION, "变身");
		}

		public final static String[] getDependStates() {
			Collection<String> values = DEPEND_STATE.values();
			String[] cateNames = new String[values.size()];
			values.toArray(cateNames);
			return cateNames;
		}

		public final static Integer[] getDependStateValue() {
			Set<Integer> values = DEPEND_STATE.keySet();
			Integer[] cateValues = new Integer[values.size()];
			values.toArray(cateValues);
			return cateValues;
		}

		public final static String getDependStateName(short cate) {
			String name = DEPEND_STATE.get(cate);
			if (name == null) {
				name = cate + ":NULL";
			}
			return name;
		}

	}

	public static final class Target {
		public static final byte               SELF         = 0;
		public static final byte               SELF_SINGLE  = 1;
		public static final byte               SELF_GROUP   = 2;
		public static final byte               ENEMY_SINGLE = 3;
		public static final byte               ENEMY_GROUP  = 4;
		private final static Map<Byte, String> TARGET_NOTE  = new HashMap<Byte, String>();
		static {

			TARGET_NOTE.put(SELF, "自己");
			TARGET_NOTE.put(SELF_SINGLE, "己方单体");
			TARGET_NOTE.put(SELF_GROUP, "己方群体");
			TARGET_NOTE.put(ENEMY_SINGLE, "敌方单体");
			TARGET_NOTE.put(ENEMY_GROUP, "敌方群体");
		}

		public final static String[] getTargetNotes() {
			Collection<String> values = TARGET_NOTE.values();
			String[] cateNames = new String[values.size()];
			values.toArray(cateNames);
			return cateNames;
		}

		public final static Byte[] getTargets() {
			Set<Byte> values = TARGET_NOTE.keySet();
			Byte[] cateValues = new Byte[values.size()];
			values.toArray(cateValues);
			return cateValues;
		}

		public final static String getTargetNote(byte target) {
			String name = TARGET_NOTE.get(target);
			if (name == null) {
				name = target + ":NULL";
			}
			return name;
		}
	}
}
