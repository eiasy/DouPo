package mmo.common.bean.mission.extension;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IAddition {
	public static final byte               NONE     = 0;
	/** 法宝满灵 */
	public static final byte               MAN_LING = 1;
	private static final Map<Byte, String> additions = new HashMap<Byte, String>();
	static {
		additions.put(NONE, "-- --");
		additions.put(MAN_LING, "满灵");
	}

	public static final String[] getAdditionNames() {
		String[] names = new String[additions.size()];
		additions.values().toArray(names);
		return names;
	}

	public static byte getAdditionValue(String text) {
		Set<Byte> keys = additions.keySet();
		for (byte key : keys) {
			if (additions.get(key).equals(text)) {
				return key;
			}
		}
		return 0;
	}

	public static final String getAdditionName(byte addition) {
		String name = additions.get(addition);
		if (name == null) {
			return "-- --";
		}
		return name;
	}
}
