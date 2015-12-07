package mmo.common.config.skill;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class SkillCate {
	public final static short               PHYSICS    = 1;
	/** 主动 */
	public final static short               DO_SKILL   = 2;
	public final static short               BUF        = 3;
	public final static short               STATE_ZONE = 4;
	public final static short               EXPENDABLE = 5;
	/** 被动 */
	public final static short               ED_SKILL   = 6;
	private final static Map<Short, String> CATES      = new HashMap<Short, String>();
	static {
		CATES.put(PHYSICS, "普攻");
		CATES.put(DO_SKILL, "主动技");
		CATES.put(ED_SKILL, "被动技");
		CATES.put(BUF, "BUF");
		CATES.put(STATE_ZONE, "状态区");
		CATES.put(EXPENDABLE, "消耗品");
	}

	public final static String[] getCateNames() {
		Collection<String> values = CATES.values();
		String[] cateNames = new String[values.size()];
		values.toArray(cateNames);
		return cateNames;
	}

	public final static Short[] getCateValue() {
		Set<Short> values = CATES.keySet();
		Short[] cateValues = new Short[values.size()];
		values.toArray(cateValues);
		return cateValues;
	}

	public final static String getCateName(short cate) {
		String name = CATES.get(cate);
		if (name == null) {
			name = cate + ":NULL";
		}
		return name;
	}
}