package mmo.common.config.skill;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** 技能类型 */
public class SkillMainType {
	/** 秘籍 */
	public static final short               CHEATS = 1;
	/** 技能 */
	public static final short               SKILL  = 2;
	/** 心法 */
	public static final short               HEART  = 3;
	private final static Map<Short, String> TYPES  = new HashMap<Short, String>();
	static {
		TYPES.put(CHEATS, "秘籍");
		TYPES.put(SKILL, "技能");
		TYPES.put(HEART, "心法");
	}

	public final static String[] getTypeNames() {
		Collection<String> values = TYPES.values();
		String[] cateNames = new String[values.size()];
		values.toArray(cateNames);
		return cateNames;
	}

	public final static Short[] getTypeValues() {
		Set<Short> values = TYPES.keySet();
		Short[] cateValues = new Short[values.size()];
		values.toArray(cateValues);
		return cateValues;
	}

	public final static String getTypeName(short cate) {
		String name = TYPES.get(cate);
		if (name == null) {
			name = cate + ":NULL";
		}
		return name;
	}
}