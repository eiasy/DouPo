package mmo.common.config.goods;

import java.util.HashMap;
import java.util.Map;

public class GoodsActions {
	/** 使用 */
	public final static byte               ACTION_1_USE     = 1;
	/** 直接使用 */
	public final static byte               ACTION_2_DIR_USE = 2;

	private final static Map<String, Byte> actions          = new HashMap<String, Byte>();

	static {
		actions.put("使用", ACTION_1_USE);
		actions.put("直接使用", ACTION_2_DIR_USE);
	}

	public final static byte getActionValue(String actionName) {
		Byte action = actions.get(actionName);
		if (action == null) {
			return 0;
		}
		return action;
	}
}
