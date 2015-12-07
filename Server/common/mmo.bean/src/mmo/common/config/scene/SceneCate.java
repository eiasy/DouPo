package mmo.common.config.scene;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SceneCate {
	/** 主城 */
	public final static byte               CITY             = 0;
	/** 野外 */
	public final static byte               FIELD            = 1;
	/** 单人副本 */
	public final static byte               DUPLICATE_SINGLE = 2;
	/** 多人副本 */
	public final static byte               DUPLICATE_MULTI  = 3;
	/** 秘境 */
	public final static byte               SECRET           = 4;
	/** 战场 */
	public final static byte               BATTLE           = 5;

	/** 6 竞技场(客户端) */
	public final static byte               FIGHT            = 6;
	/** 7 一战到底(客户端) */
	public final static byte               ONE_FIGHT        = 7;
	/** 8 世界boss(客户端) */
	public final static byte               BOSS             = 8;
	/** 9 秘境中的世界boss(客户端) */
	public final static byte               SECRET_BOSS      = 9;
	/** 仙府 */
	public final static byte               MANSION          = 10;
	/** 剧情副本 */
	public final static byte               SHOW             = 11;
	/** 宗门-备用 */
	public final static byte               SECT             = 101;

	private final static Map<Byte, String> CATE_NAME        = new HashMap<Byte, String>();
	static {
		CATE_NAME.put(CITY, "主城");
		CATE_NAME.put(FIELD, "野外");
		CATE_NAME.put(DUPLICATE_SINGLE, "单人副本");
		CATE_NAME.put(DUPLICATE_MULTI, "多人副本");
		CATE_NAME.put(SECRET, "秘境");
		CATE_NAME.put(BATTLE, "战场");
		CATE_NAME.put(SECT, "宗门");
		CATE_NAME.put(MANSION, "仙府");
		CATE_NAME.put(FIGHT, "竞技场");
		CATE_NAME.put(ONE_FIGHT, "一战到底");
		CATE_NAME.put(BOSS, "世界boss");
		CATE_NAME.put(SECRET_BOSS, "秘境中的世界boss");
		CATE_NAME.put(SHOW, "剧情副本");
	}

	public static final String[] getNames() {
		Collection<String> values = CATE_NAME.values();
		String[] names = new String[values.size()];
		values.toArray(names);
		return names;
	}

	public static final byte getCateByteName(String name) {
		if (name == null) {
			return -1;
		}
		Set<Byte> keys = CATE_NAME.keySet();
		for (byte cate : keys) {
			if (CATE_NAME.get(cate).equals(name)) {
				return cate;
			}
		}
		return -1;
	}

	public static final String getNameByCate(byte cate) {
		return CATE_NAME.get(cate);
	}
}