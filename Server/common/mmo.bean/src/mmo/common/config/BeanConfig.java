package mmo.common.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BeanConfig {
	// ///////////////////////////////////////////////////////
	// ////////////////////// 状态 ////////////////////////////
	// ///////////////////////////////////////////////////////
	/** 关闭或停止使用 */
	public final static byte               STATE_OFF    = 1;
	/** 开启或已经被激活 */
	public final static byte               STATE_ON     = 0;
	private final static Map<Byte, String> STATE_SWITCH = new HashMap<Byte, String>();

	static {
		STATE_SWITCH.put(STATE_OFF, "OFF");
		STATE_SWITCH.put(STATE_ON, "ON");
	}

	public final static String[] getStateSwitchNames() {
		Collection<String> values = STATE_SWITCH.values();
		String[] names = new String[values.size()];
		values.toArray(names);
		return names;
	}

	public final static String getStateName(byte pkSwitch) {
		String name = STATE_SWITCH.get(pkSwitch);
		if (name == null) {
			name = STATE_SWITCH.get(STATE_OFF);
		}
		return name;
	}

	public static class EffectConfig {
		/** 宠物类型因素 */
		public static final byte PET_1   = 1;
		/** 物品类型因素 */
		public static final byte GOODS_2 = 2;
	}
}
