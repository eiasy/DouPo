package mmo.common.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SceneConfig extends BeanConfig {
	public static interface ConfigFile {
		String subDir     = "scene";
		/** 场景技能 */
		String sceneSkill = "scene-skill.xml";
	}

	/*********************** 地形 *******************************/
	/** 存放地形类别与名称的键值对 */
	private final static Map<Short, String>             LANDFORM               = new HashMap<Short, String>();
	/** 未定义 */
	public final static short                           LANDFORM_NULL          = 0;
	/** 海洋 */
	public final static short                           LANDFORM_SEA           = 1;
	/** 森林 */
	public final static short                           LANDFORM_FOREST        = 2;
	/** 草地 */
	public final static short                           LANDFORM_LEA           = 3;
	/** 山区 */
	public final static short                           LANDFORM_HILL          = 4;
	/** 城镇 */
	public final static short                           LANDFORM_TOWN          = 5;

	/*********************** 天气类别 ***************************/

	/*********************** 空间划分 ***************************/
	/** 存放地形类别与名称的键值对 */
	private final static Map<Short, String>             INTERSPACE             = new HashMap<Short, String>();
	private final static Map<Short, Integer>            INTERSPACE_MAP         = new HashMap<Short, Integer>();
	// /** 未定义 */
	// public final static short INTERSPACE_NULL = 0;
	/** 人界 */
	public final static short                           INTERSPACE_PERSON      = 1;
	/** 魔界 */
	public final static short                           INTERSPACE_DEVILDOM    = 2;
	/** 灵界 */
	public final static short                           INTERSPACE_ELF         = 3;
	/** 仙界 */
	public final static short                           INTERSPACE_CELESTIAL   = 4;

	/*********************** 区域划分 ***************************/
	/** 存放地形类别与名称的键值对 */
	private final static Map<Short, Map<Short, String>> REGION                 = new HashMap<Short, Map<Short, String>>();
	/** 人界的区域 */
	private final static Map<Short, String>             REGION_PERSON          = new HashMap<Short, String>();
	/** 魔界区域 */
	private final static Map<Short, String>             REGION_DEVILDOM        = new HashMap<Short, String>();
	/** 灵界的区域 */
	private final static Map<Short, String>             REGION_ELF             = new HashMap<Short, String>();
	/** 仙界的区域 */
	private final static Map<Short, String>             REGION_CELESTAL        = new HashMap<Short, String>();
	/** 未定义的区域 */
	public final static short                           REGION_0               = 0;
	/** 天南 */
	public final static short                           REGION_1_1001          = 1001;
	/** 大晋 */
	public final static short                           REGION_1_1002          = 1002;
	/** 乱星海 */
	public final static short                           REGION_1_1003          = 1003;

	/** 秘境场景ID */
	public final static int                             REGION_MIJING_ID_70000 = 9001;

	static {
		LANDFORM.put(LANDFORM_NULL, "NULL");
		LANDFORM.put(LANDFORM_FOREST, "森林");
		LANDFORM.put(LANDFORM_HILL, "山区");
		LANDFORM.put(LANDFORM_LEA, "草地");
		LANDFORM.put(LANDFORM_SEA, "海洋");
		LANDFORM.put(LANDFORM_TOWN, "城镇");
		// /////////////////////////////////////
		INTERSPACE_MAP.put(INTERSPACE_PERSON, 10000);
		INTERSPACE_MAP.put(INTERSPACE_DEVILDOM, 10000);
		INTERSPACE_MAP.put(INTERSPACE_ELF, 10000);
		INTERSPACE_MAP.put(INTERSPACE_CELESTIAL, 10000);
		// ////////////////////////////////////

		// /////////////////////////////////
		// INTERSPACE.put(INTERSPACE_NULL, "NULL");
		INTERSPACE.put(INTERSPACE_PERSON, "人界");
		INTERSPACE.put(INTERSPACE_DEVILDOM, "魔界");
		INTERSPACE.put(INTERSPACE_ELF, "灵界");
		INTERSPACE.put(INTERSPACE_CELESTIAL, "仙界");
		// ///////////////////////////////////
		REGION.put(INTERSPACE_PERSON, REGION_PERSON);
		REGION.put(INTERSPACE_DEVILDOM, REGION_DEVILDOM);
		REGION.put(INTERSPACE_ELF, REGION_ELF);
		REGION.put(INTERSPACE_CELESTIAL, REGION_CELESTAL);
		REGION_PERSON.put(REGION_1_1001, "天南");
		REGION_PERSON.put(REGION_1_1002, "大晋");
		REGION_PERSON.put(REGION_1_1003, "乱星海");
		REGION_DEVILDOM.put(REGION_0, "NULL");
		REGION_ELF.put(REGION_0, "NULL");
		REGION_CELESTAL.put(REGION_0, "NULL");
	}

	public static int getInterspaceMap(short interspace) {
		Integer map = INTERSPACE_MAP.get(interspace);
		if (map == null) {
			return INTERSPACE_PERSON;
		}
		return map;
	}

	public final static String[] getLandformNames() {
		Collection<String> values = LANDFORM.values();
		String[] names = new String[values.size()];
		values.toArray(names);
		return names;
	}

	public final static String getLandformName(short landform) {
		String name = LANDFORM.get(landform);
		if (name == null) {
			name = landform + ":" + LANDFORM.get(0);
		}
		return name;
	}

	public final static String[] getInterspaceNames() {
		Collection<String> values = INTERSPACE.values();
		String[] names = new String[values.size()];
		values.toArray(names);
		return names;
	}

	public final static String getInterspaceName(short interspace) {
		String name = INTERSPACE.get(interspace);
		if (name == null) {
			name = interspace + ":" + INTERSPACE.get(0);
		}
		return name;
	}

	public final static String[] getInterspaceRegions(short interspace) {
		Map<Short, String> regionMap = REGION.get(interspace);
		if (regionMap == null) {
			return new String[] { interspace + ":NULL" };
		}
		Collection<String> values = regionMap.values();
		String[] regions = new String[values.size()];
		values.toArray(regions);
		return regions;
	}

	public final static String getInterspaceRegion(short region) {
		String name = null;
		Map<Short, String> regionMap = REGION.get((short) (region / 1000));
		if (regionMap == null) {
			return region + ":未分配空间";
		}
		name = regionMap.get(region);
		if (name == null) {
			return region + ":NULL";
		}
		return name;
	}

	public final static class PK {
		/** 战斗 */
		public static final byte               battle       = 0;
		/** 和平 */
		public static final byte               peace        = 1;
		private final static Map<Byte, String> STATE_SWITCH = new HashMap<Byte, String>();

		static {
			STATE_SWITCH.put(peace, "和平");
			STATE_SWITCH.put(battle, "战斗");
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
	}
}
