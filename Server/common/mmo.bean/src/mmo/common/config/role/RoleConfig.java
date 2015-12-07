package mmo.common.config.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleMini;
import mmo.common.protocol.game.CommonGamePropertyKey;

public final class RoleConfig {
	private static List<Short>               props           = new ArrayList<Short>();
	private static final Map<String, String> headAvatarSmall = new HashMap<String, String>();
	private static final Map<String, String> headAvatarBig   = new HashMap<String, String>();

	public static List<Short> getPropsTable() {
		return props;
	}

	static {
		/* 最大生命 */
		props.add(CommonGamePropertyKey.tempKey.TEMP_HP_MAX_619);
		/* 攻击 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_ATTACK_604);
		/* 防御 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_DEFENSE_605);
		/* 命中 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_PROBABILITY_606);
		/* 闪避 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_DUCK_607);
		/* 暴击 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_STRIKE_608);
		/* 韧性 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_TENACITY_609);
		/* 格挡 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_FENDER_610);
		/* 破甲 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_DESTROY_611);
		/* 吸血 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_SUCKHP_612);
		/* 反弹 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_REBOUND_613);
		/* 破防 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_REDUCE_DEFENCE_614);
		/* 抵抗 */
		props.add(CommonGamePropertyKey.Fight.FIGHT_RESIST_615);
	}

	/** 角色类型 */
	public static class RoleType {
		/** 英雄 */
		public static final byte ROLE_SELF  = 1;
		/** 其他玩家 */
		public static final byte ROLE_OTHER = 2;
		/** 怪物 */
		public static final byte MONSTER    = 3;
		/** NPC */
		public static final byte NPC        = 4;
		/** 宠物 */
		public static final byte PET        = 5;

	}

	/** 角色状态 */
	public static class RoleState {
		/** 正常 */
		public static final byte NORMAL      = 0;
		/** 玩家删除 */
		public static final byte USER_DELETE = 1;
		/** 系统封号 */
		public static final byte SYSTEM_SEAL = 2;
		/** 合并删除 */
		public static final byte COMBINE     = 3;

		public final static String getStateName(byte state) {
			switch (state) {
				case NORMAL: {
					return "正常";
				}
				case USER_DELETE: {
					return "用户删除";
				}
				case SYSTEM_SEAL: {
					return "封号";
				}
				case COMBINE: {
					return "合服删除";
				}
				default: {
					return "未知";
				}
			}
		}
	}

	/** 角色构建者 */
	public static class Builder {
		/** 系统产出 */
		public static final byte system = 1;
		/** 技能创建 */
		public static final byte skill  = 2;
		/** 物品创建 */
		public static final byte goods  = 3;
	}

	public static String getAvatar(Role role) {
		return headAvatarSmall.get(RoleProfession.getProfessionName(role.getProfession()));
	}

	public static String getAvatar(byte profession) {
		return headAvatarSmall.get(RoleProfession.getProfessionName(profession));
	}

	public static String getAvatarBig(byte profession) {
		return headAvatarBig.get(RoleProfession.getProfessionName(profession));
	}

	public static String getAvatarBig(Role role) {
		return headAvatarBig.get(RoleProfession.getProfessionName(role.getProfession()));
	}

	public static String getAvatar(RoleMini role) {
		return headAvatarSmall.get(RoleProfession.getProfessionName(role.getProfession()));
	}

	public static String getAvatarBig(RoleMini role) {
		return headAvatarBig.get(RoleProfession.getProfessionName(role.getProfession()));
	}

	public static void addAvatar(String key, String value) {
		headAvatarSmall.put(key, value);
	}

	public static void addAvatarBig(String key, String value) {
		headAvatarBig.put(key, value);
	}
}
