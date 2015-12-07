package mmo.common.config.role;

import java.util.HashMap;
import java.util.Map;

/**
 * 境界配置
 * 
 * @author 李天喜
 * 
 */
public final class RoleRealmConfig {

	/** 穴位总数 */
	public final static byte               REALM_ALL_CAVE_NUMBER   = 12;
	/** 穴位开启最大数 */
	public final static byte               REALM_OPEN_ALL_CAVE_SUM = 24;
	/** 穴位前中后期 */
	public final static byte               REALM_3_PHASE           = 4;

	// 穴位状态（0未激活（灰），1激活（浅蓝），2 全部点亮（深蓝））
	public final static byte               REALM_CAVE_CLOSE        = 0;
	public final static byte               REALM_CAVE_OPEN_1       = 1;
	public final static byte               REALM_CAVE_OPEN_2       = 2;
	/** 下一个可开启穴位 */
	public final static byte               REALM_CAVE_NEXT_OPEN    = 3;

	/** 境界数量 */
	public static final byte               count                   = 5;
	/** 炼气 */
	public static final byte               LianQi_1                = 0;
	/** 筑基 */
	public static final byte               ZhuJi_2                 = 1;
	/** 结丹 */
	public static final byte               JieDan_3                = 2;
	/** 元婴 */
	public static final byte               YuanYing                = 3;
	/** 化神 */
	public static final byte               HuaSheng                = 4;
	/** 炼虚 */
	public static final byte               LianXu_6                = 5;
	/** 合体 */
	public static final byte               HeTi_7                  = 6;
	/** 大乘 */
	public static final byte               DaCheng_8               = 7;

	/** 境界前期 */
	public static final byte               REALM_START             = 0;
	/** 境界中期 */
	public static final byte               REALM_MIDDLE            = 1;
	/** 境界后期 */
	public static final byte               REALM_END               = 2;

	private static final Map<Byte, String> names                   = new HashMap<Byte, String>();
	private static final Map<String, Byte> N_R                     = new HashMap<String, Byte>();
	private static final Map<String, Byte> REALM_PHASE             = new HashMap<String, Byte>();

	static {
		names.put(LianQi_1, "炼气");
		names.put(ZhuJi_2, "筑基");
		names.put(JieDan_3, "结丹");
		names.put(YuanYing, "元婴");
		names.put(HuaSheng, "化神");
		names.put(LianXu_6, "炼虚");
		names.put(HeTi_7, "合体");
		names.put(DaCheng_8, "大乘");

		/***********************************/
		N_R.put("炼气", LianQi_1);
		N_R.put("筑基", ZhuJi_2);
		N_R.put("结丹", JieDan_3);
		N_R.put("元婴", YuanYing);
		N_R.put("化神", HuaSheng);
		N_R.put("炼虚", LianXu_6);
		N_R.put("合体", HeTi_7);
		N_R.put("大乘", DaCheng_8);

		/***********************************/
		REALM_PHASE.put("前期", REALM_START);
		REALM_PHASE.put("中期", REALM_MIDDLE);
		REALM_PHASE.put("后期", REALM_END);
	}

	public static final byte nextRealm(byte realm) {
		if (realm < 0) {
			return LianQi_1;
		} else if (realm > DaCheng_8) {
			return DaCheng_8;
		} else {
			return ++realm;
		}
	}

	public final static byte getRealm(String name) {
		Byte realm = N_R.get(name);
		if (realm == null) {
			return LianQi_1;
		}
		return realm;
	}

	public final static byte getRealmPhase(String name) {
		Byte realm = REALM_PHASE.get(name);
		if (realm == null) {
			return REALM_START;
		}
		return realm;
	}

	public final static String[] getNames() {
		String[] arr = new String[names.size()];
		names.values().toArray(arr);
		return arr;
	}

	public static final String getName(byte realm) {
		return names.get(realm);
	}

	/**
	 * 通过境界获取等级上限
	 * 
	 * @param realm
	 * @return
	 */
	public static final short getMaxLevel(short realm) {
		switch (realm) {
			case LianQi_1:
				return 30;
			case ZhuJi_2:
				return 50;
			case JieDan_3:
				return 70;
			case YuanYing:
				return 90;
			case HuaSheng:
				return 100;
			default:
				return 30;
		}
	}

	public static final class Identitys {
		/** 穴位- 子 */
		public final static byte               IDENTITY_zi   = 1;
		/** 穴位- 丑 */
		public final static byte               IDENTITY_chou = 2;
		/** 穴位- 寅 */
		public final static byte               IDENTITY_yin  = 3;
		/** 穴位- 卯 */
		public final static byte               IDENTITY_mou  = 4;
		/** 穴位- 辰 */
		public final static byte               IDENTITY_chen = 5;
		/** 穴位- 巳 */
		public final static byte               IDENTITY_si   = 6;
		/** 穴位- 午 */
		public final static byte               IDENTITY_wu   = 7;
		/** 穴位- 未 */
		public final static byte               IDENTITY_wei  = 8;
		/** 穴位- 申 */
		public final static byte               IDENTITY_shen = 9;
		/** 穴位- 酉 */
		public final static byte               IDENTITY_you  = 10;
		/** 穴位- 戌 */
		public final static byte               IDENTITY_xu   = 11;
		/** 穴位- 亥 */
		public final static byte               IDENTITY_hai  = 12;
		private final static Map<String, Byte> N_I           = new HashMap<String, Byte>();

		static {
			N_I.put("子", IDENTITY_zi);
			N_I.put("丑", IDENTITY_chou);
			N_I.put("寅", IDENTITY_yin);
			N_I.put("卯", IDENTITY_mou);
			N_I.put("辰", IDENTITY_chen);
			N_I.put("巳", IDENTITY_si);
			N_I.put("午", IDENTITY_wu);
			N_I.put("未", IDENTITY_wei);
			N_I.put("申", IDENTITY_shen);
			N_I.put("酉", IDENTITY_you);
			N_I.put("戌", IDENTITY_xu);
			N_I.put("亥", IDENTITY_hai);
		}

		public final static byte getIdentity(String name) {
			Byte identity = N_I.get(name);
			if (identity == null) {
				return -1;
			}
			return identity;
		}
	}
}