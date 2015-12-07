package mmo.common.bean.role;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoleState {
	public static final byte          DB_STATE_DELETED   = 1;

	/** 战斗状态持续N毫秒后自动消失 */
	public static final short         BATTLE_CONTINUE    = 15 * 1000;
	/** 处于该状态不能进行物理攻击 */
	public static final int           GAME_ESTOP_PHYSICS = 1 << 4;
	/** 处于该状态不能释放技能 */
	public static final int           GAME_ESTOP_MOGIC   = 1 << 5;
	/** 处于该状态下不能移动 */
	public static final int           GAME_ESTOP_MOVE    = 1 << 6;
	/** 攻击状态，处于该状态时激活需要该状态的技能 */
	public static final int           GAME_ATTACK        = 1 << 7;
	/** 防御状态，处于该状态时激活需要该状态的技能 */
	public static final int           GAME_DEFENCE       = 1 << 8;
	/** 魔化状态，处于该状态时激活需要该状态的技能 */
	public static final int           GAME_EVIL          = 1 << 9;
	/** 变身状态，处于该状态时激活需要该状态的技能 */
	public static final int           GAME_DISTORTION    = 1 << 10;
	/** 战斗状态 */
	public static final int           GAME_BATTLE        = 1 << 11;
	/** 打坐状态 */
	public static final int           GAME_DA_ZUO        = 1 << 21;

	final static Map<Integer, String> STATES             = new HashMap<Integer, String>();
	static {
		STATES.put(GAME_ESTOP_MOGIC, "禁止释放技能");
		STATES.put(GAME_ESTOP_MOVE, "禁止移动");
		STATES.put(GAME_ATTACK, "攻击状态");
		STATES.put(GAME_DEFENCE, "防御状态");
		STATES.put(GAME_EVIL, "魔化状态");
		STATES.put(GAME_DISTORTION, "变身状态");
		STATES.put(GAME_BATTLE, "战斗状态");
		STATES.put(GAME_DA_ZUO, "战斗状态");
	}

	public final static String[] getStateNames() {
		Collection<String> values = STATES.values();
		String[] cateNames = new String[values.size()];
		values.toArray(cateNames);
		return cateNames;
	}

	public final static Integer[] getStateValue() {
		Set<Integer> values = STATES.keySet();
		Integer[] cateValues = new Integer[values.size()];
		values.toArray(cateValues);
		return cateValues;
	}

	public final static String getStateName(long state) {
		String name = STATES.get(state);
		if (name == null) {
			name = state + ":NULL";
		}
		return name;
	}
}
