package mmo.common.config.role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class RoleProfession {
	/** 全职业 */
	public final static short                         ALL                         = 0;
	/** 玄仙 */
	public final static byte                          XuanXian_1                  = 1;
	/** 修罗 */
	public final static byte                          XiuLuo_2                    = 2;
	/** 御灵 */
	public final static byte                          YuLing_16                   = 16;

	/** 职业对应的动画文件 */
	private final static Map<Byte, String>            PROFESSION_ANI              = new HashMap<Byte, String>();
	private static final Map<Byte, String>            PROFESSION_NAMES            = new HashMap<Byte, String>();
	/** 职业对应的骨骼(男) **/
	private static final Map<Byte, List<Byte>>        PROFESSION_SKELETONS        = new HashMap<Byte, List<Byte>>();
	/** 职业对应的骨骼(女) **/
	private static final Map<Byte, List<Byte>>        PROFESSION_SKELETONS_FEMALE = new HashMap<Byte, List<Byte>>();
	/** 默认的骨骼动画 **/
	private static final Map<Byte, Map<Byte, String>> DEFAULT_SKELETONS           = new HashMap<Byte, Map<Byte, String>>();
	/** 默认的骨骼动画 **/
	private static final Map<Byte, Map<Byte, String>> DEFAULT_SKELETONS_FEMALE    = new HashMap<Byte, Map<Byte, String>>();
	/** 在线机器人数据 */
	private static final List<RoleRobotData>          onlineRobotList             = new ArrayList<RoleRobotData>();
	/** 排行榜机器人 */
	private static final List<RoleRobotData>          rankRobotList               = new ArrayList<RoleRobotData>();

	static {
		PROFESSION_ANI.put(XuanXian_1, "herob.ani");
		PROFESSION_ANI.put(XiuLuo_2, "heroa.ani");
		PROFESSION_ANI.put(YuLing_16, "heroc.ani");

		PROFESSION_NAMES.put(XuanXian_1, "玄仙");
		PROFESSION_NAMES.put(XiuLuo_2, "修罗");
		PROFESSION_NAMES.put(YuLing_16, "御灵");

		List<Byte> skeletons = new ArrayList<Byte>();
		skeletons.add(RoleSkeleton.CLOTHES_XUANXIAN_8);
		skeletons.add(RoleSkeleton.SWORD_1);
		skeletons.add(RoleSkeleton.SHADOW_5);
		skeletons.add(RoleSkeleton.HORSE_6);
		skeletons.add(RoleSkeleton.ROLE_MOUNT_9);
		skeletons.add(RoleSkeleton.WING_3);
		PROFESSION_SKELETONS_FEMALE.put(XuanXian_1, skeletons);
		skeletons = new ArrayList<Byte>();
		skeletons.add(RoleSkeleton.CLOTHES_XUANXIAN_8);
		skeletons.add(RoleSkeleton.SWORD_1);
		skeletons.add(RoleSkeleton.SHADOW_5);
		skeletons.add(RoleSkeleton.HORSE_6);
		skeletons.add(RoleSkeleton.ROLE_MOUNT_9);
		skeletons.add(RoleSkeleton.WING_3);
		PROFESSION_SKELETONS.put(XuanXian_1, skeletons);

		skeletons = new ArrayList<Byte>();
		skeletons.add(RoleSkeleton.CLOTHES_XIULUO_7);
		skeletons.add(RoleSkeleton.KNIFE_0);
		skeletons.add(RoleSkeleton.SHADOW_5);
		skeletons.add(RoleSkeleton.HORSE_6);
		skeletons.add(RoleSkeleton.ROLE_MOUNT_9);
		skeletons.add(RoleSkeleton.WING_3);
		PROFESSION_SKELETONS_FEMALE.put(XiuLuo_2, skeletons);
		skeletons = new ArrayList<Byte>();
		skeletons.add(RoleSkeleton.CLOTHES_XIULUO_7);
		skeletons.add(RoleSkeleton.KNIFE_0);
		skeletons.add(RoleSkeleton.SHADOW_5);
		skeletons.add(RoleSkeleton.HORSE_6);
		skeletons.add(RoleSkeleton.ROLE_MOUNT_9);
		skeletons.add(RoleSkeleton.WING_3);
		PROFESSION_SKELETONS.put(XiuLuo_2, skeletons);

		skeletons = new ArrayList<Byte>();
		skeletons.add(RoleSkeleton.CLOTHES_YULING_4);
		skeletons.add(RoleSkeleton.FAN_2);
		skeletons.add(RoleSkeleton.SHADOW_5);
		skeletons.add(RoleSkeleton.HORSE_6);
		skeletons.add(RoleSkeleton.ROLE_MOUNT_9);
		skeletons.add(RoleSkeleton.WING_3);
		PROFESSION_SKELETONS_FEMALE.put(YuLing_16, skeletons);
		skeletons = new ArrayList<Byte>();
		skeletons.add(RoleSkeleton.CLOTHES_YULING_4);
		skeletons.add(RoleSkeleton.FAN_2);
		skeletons.add(RoleSkeleton.SHADOW_5);
		skeletons.add(RoleSkeleton.HORSE_6);
		skeletons.add(RoleSkeleton.ROLE_MOUNT_9);
		skeletons.add(RoleSkeleton.WING_3);
		PROFESSION_SKELETONS.put(YuLing_16, skeletons);
		/****************************************************/
		Map<Byte, String> skeletonFile = new HashMap<Byte, String>();
		skeletonFile.put(RoleSkeleton.SHADOW_5, "shadow.ani");
		skeletonFile.put(RoleSkeleton.CLOTHES_XUANXIAN_8, "bd_b1.ani");
		skeletonFile.put(RoleSkeleton.SWORD_1, "wpb_1.ani");
		skeletonFile.put(RoleSkeleton.HORSE_6, "wpb_1.ani");
		skeletonFile.put(RoleSkeleton.ROLE_MOUNT_9, "bd_b1.ani");
		DEFAULT_SKELETONS.put(XuanXian_1, skeletonFile);
		skeletonFile = new HashMap<Byte, String>();
		skeletonFile.put(RoleSkeleton.SHADOW_5, "shadow.ani");
		skeletonFile.put(RoleSkeleton.CLOTHES_XUANXIAN_8, "bd_b1.ani");
		skeletonFile.put(RoleSkeleton.SWORD_1, "wpb_1.ani");
		skeletonFile.put(RoleSkeleton.HORSE_6, "wpb_1.ani");
		skeletonFile.put(RoleSkeleton.ROLE_MOUNT_9, "bd_b1.ani");
		DEFAULT_SKELETONS_FEMALE.put(XuanXian_1, skeletonFile);

		skeletonFile = new HashMap<Byte, String>();
		skeletonFile.put(RoleSkeleton.SHADOW_5, "shadow.ani");
		skeletonFile.put(RoleSkeleton.CLOTHES_XIULUO_7, "bd_a1.ani");
		skeletonFile.put(RoleSkeleton.KNIFE_0, "wpa_1.ani");
		skeletonFile.put(RoleSkeleton.HORSE_6, "wpa_1.ani");
		skeletonFile.put(RoleSkeleton.ROLE_MOUNT_9, "bd_a1.ani");
		DEFAULT_SKELETONS.put(XiuLuo_2, skeletonFile);
		skeletonFile = new HashMap<Byte, String>();
		skeletonFile.put(RoleSkeleton.SHADOW_5, "shadow.ani");
		skeletonFile.put(RoleSkeleton.CLOTHES_XIULUO_7, "bd_a1.ani");
		skeletonFile.put(RoleSkeleton.KNIFE_0, "wpa_1.ani");
		skeletonFile.put(RoleSkeleton.HORSE_6, "wpa_1.ani");
		skeletonFile.put(RoleSkeleton.ROLE_MOUNT_9, "bd_a1.ani");
		DEFAULT_SKELETONS_FEMALE.put(XiuLuo_2, skeletonFile);

		skeletonFile = new HashMap<Byte, String>();
		skeletonFile.put(RoleSkeleton.SHADOW_5, "shadow.ani");
		skeletonFile.put(RoleSkeleton.CLOTHES_YULING_4, "bd_c1.ani");
		skeletonFile.put(RoleSkeleton.FAN_2, "wpc_1.ani");
		skeletonFile.put(RoleSkeleton.HORSE_6, "wpc_1.ani");
		skeletonFile.put(RoleSkeleton.ROLE_MOUNT_9, "bd_c1.ani");
		DEFAULT_SKELETONS.put(YuLing_16, skeletonFile);
		skeletonFile = new HashMap<Byte, String>();
		skeletonFile.put(RoleSkeleton.SHADOW_5, "shadow.ani");
		skeletonFile.put(RoleSkeleton.CLOTHES_YULING_4, "bd_c1.ani");
		skeletonFile.put(RoleSkeleton.FAN_2, "wpc_1.ani");
		skeletonFile.put(RoleSkeleton.HORSE_6, "wpc_1.ani");
		skeletonFile.put(RoleSkeleton.ROLE_MOUNT_9, "bd_c1.ani");
		DEFAULT_SKELETONS_FEMALE.put(YuLing_16, skeletonFile);
	}

	public final static void resetSkeketion(byte profession, byte sex, byte skeletion, String skeletionFile) {
		switch (sex) {
			case RoleSex.FEMALE: {
				Map<Byte, String> skeletonFile = DEFAULT_SKELETONS_FEMALE.get(profession);
				if (skeletonFile == null) {
					skeletonFile = new HashMap<Byte, String>();
					DEFAULT_SKELETONS_FEMALE.put(profession, skeletonFile);
				}
				skeletonFile.put(skeletion, skeletionFile);
				break;
			}
			case RoleSex.MALE: {
				Map<Byte, String> skeletonFile = DEFAULT_SKELETONS.get(profession);
				if (skeletonFile == null) {
					skeletonFile = new HashMap<Byte, String>();
					DEFAULT_SKELETONS.put(profession, skeletonFile);
				}
				skeletonFile.put(skeletion, skeletionFile);
				break;
			}
		}
	}

	public final static String getHeroAniFile(byte profession, byte sex) {
		String ani = PROFESSION_ANI.get(profession);
		if (ani == null) {
			return PROFESSION_ANI.get(XuanXian_1);
		}
		return ani;
	}

	public final static String getHeroAniFile(byte profession) {
		String ani = PROFESSION_ANI.get(profession);
		if (ani == null) {
			return PROFESSION_ANI.get(XuanXian_1);
		}
		return ani;
	}

	public static final String getDefaultSkeleton(byte profession, byte sex, byte skeleton) {
		if (profession == 0) {
			return "";
		}
		String skeletonFile = null;
		if (sex == RoleSex.FEMALE) {
			skeletonFile = DEFAULT_SKELETONS_FEMALE.get(profession).get(skeleton);
		} else {
			skeletonFile = DEFAULT_SKELETONS.get(profession).get(skeleton);
		}
		if (skeletonFile == null) {
			return "";
		}
		return skeletonFile;
	}

	public static final List<Byte> getSkeletons(byte profession, byte sex) {
		if (sex == RoleSex.FEMALE) {
			return PROFESSION_SKELETONS_FEMALE.get(profession);
		} else {
			return PROFESSION_SKELETONS.get(profession);
		}
	}

	public static final boolean hasSkeleton(byte profession, byte skeleton, byte sex) {
		if (sex == RoleSex.FEMALE) {
			return PROFESSION_SKELETONS_FEMALE.get(profession).contains(skeleton);
		} else {
			return PROFESSION_SKELETONS.get(profession).contains(skeleton);
		}

	}

	public static final String getProfessionNames(byte key) {
		if (key == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Set<Byte> keys = PROFESSION_NAMES.keySet();
		for (Byte temp : keys) {
			if ((temp & key) != 0) {
				sb.append(PROFESSION_NAMES.get(temp)).append(" ");
			}
		}
		return sb.toString();
	}

	public static final Set<Byte> getProfessions() {
		return PROFESSION_NAMES.keySet();
	}

	/**
	 * 获取物品面向的职业
	 * 
	 * @param key
	 *            职业键
	 * @return 职业名称
	 */
	public static final String getProfessionNameForGoods(byte key) {
		if (key == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		Set<Byte> keys = PROFESSION_NAMES.keySet();
		int size = keys.size();
		int count = 0;
		for (Byte temp : keys) {
			if ((temp & key) != 0) {
				sb.append(PROFESSION_NAMES.get(temp)).append(" ");
				count++;
			}
		}
		if (count < size) {
			return sb.toString().trim();
		} else {
			return null;
		}
	}

	public static final String getProfessionName(byte key) {
		return PROFESSION_NAMES.get(key);
	}

	public static final String[] getProfessionNames() {
		Collection<String> values = PROFESSION_NAMES.values();
		String[] names = new String[values.size()];
		values.toArray(names);
		return names;
	}

	public static final byte getProfessionValue(String name) {
		if (name == null) {
			return 0;
		}
		Set<Byte> keys = PROFESSION_NAMES.keySet();
		for (Byte key : keys) {
			if (name.equals(PROFESSION_NAMES.get(key))) {
				return key;
			}
		}
		return 0;
	}

	public static List<RoleRobotData> getOnlineRobotList() {
		return onlineRobotList;
	}

	public static List<RoleRobotData> getRankRobotList() {
		return rankRobotList;
	}
}
