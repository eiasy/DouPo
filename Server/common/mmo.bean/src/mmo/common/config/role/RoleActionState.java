package mmo.common.config.role;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class RoleActionState {
	/** 归位状态，在该状态下，怪物将从当前位置移动到复活点 */
	public static final short       ACTION_01_RESET  = 1;
	/** 移动状态，在该状态下接受怪物的移动路径 */
	public static final short       ACTION_02_PATROL = 2;
	/** 复活状态 */
	public static final short       ACTION_04_RELIVE = 4;
	/** 死亡状态 */
	public static final short       ACTION_08_DEATH  = 8;
	/** 逃跑状态 */
	public static final short       ACTION_16_FLEE   = 16;
	/** 追踪状态 */
	public static final short       ACTION_32_FOLLOW = 32;
	/** 攻击状态，处于该状态时激活需要该状态的技能 */
	public static final short       ACTION_64_ATTACK = 64;
	/** 警觉状态 */
	public static final short       ACTION_65_ALERT  = 65;
	/** 不可视 */
	public static final short       ACTION_66_HIDE   = 66;
	/** 角色已经死亡 */
	public static final short       STATE_DEATH      = 13;
	/** 摆摊状态 */
	public static final short       STATE_SHOP       = 67;

	final static Map<Short, String> STATES           = new HashMap<Short, String>();
	static {
		STATES.put(ACTION_01_RESET, "重置");
		STATES.put(ACTION_02_PATROL, "巡逻");
		STATES.put(ACTION_04_RELIVE, "复活");
		STATES.put(ACTION_08_DEATH, "死亡");
		STATES.put(ACTION_16_FLEE, "逃跑");
		STATES.put(ACTION_32_FOLLOW, "追踪");
		STATES.put(ACTION_64_ATTACK, "战斗");
		STATES.put(ACTION_65_ALERT, "警觉");
		STATES.put(ACTION_66_HIDE, "不可视");
	}

	public final static String[] getStateNames() {
		Collection<String> values = STATES.values();
		String[] cateNames = new String[values.size()];
		values.toArray(cateNames);
		return cateNames;
	}

	public final static Short[] getStateValue() {
		Set<Short> values = STATES.keySet();
		Short[] cateValues = new Short[values.size()];
		values.toArray(cateValues);
		return cateValues;
	}

	public final static String getStateName(short state) {
		String name = STATES.get(state);
		if (name == null) {
			name = state + ":NULL";
		}
		return name;
	}
}