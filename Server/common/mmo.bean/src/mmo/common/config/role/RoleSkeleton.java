package mmo.common.config.role;

import java.util.HashMap;
import java.util.Map;

import mmo.tools.util.StringUtil;

public final class RoleSkeleton {
	/** 修罗武器 */
	public static final byte               KNIFE_0            = 0;
	/** 玄仙武器 */
	public static final byte               SWORD_1            = 1;
	/** 御灵武器 */
	public static final byte               FAN_2              = 2;
	/** 翅膀 */
	public static final byte               WING_3             = 3;
	/** 御灵衣服 */
	public static final byte               CLOTHES_YULING_4   = 4;
	/** 影子 */
	public static final byte               SHADOW_5           = 5;
	/** 武器骑乘 */
	public static final byte               HORSE_6            = 6;

	/** 修罗衣服 */
	public static final byte               CLOTHES_XIULUO_7   = 7;
	/** 玄仙衣服 */
	public static final byte               CLOTHES_XUANXIAN_8 = 8;
	/** 角色乘骑 */
	public static final byte               ROLE_MOUNT_9       = 9;

	private static final Map<String, Byte> skeletonKeys       = new HashMap<String, Byte>();
	private static final Map<String, Byte> nameKeys           = new HashMap<String, Byte>();

	static {
		nameKeys.put("刀", KNIFE_0);
		nameKeys.put("影子", SHADOW_5);
		nameKeys.put("修罗衣服", CLOTHES_XIULUO_7);
		nameKeys.put("剑", SWORD_1);
		nameKeys.put("玄仙衣服", CLOTHES_XUANXIAN_8);
		nameKeys.put("武器乘骑", HORSE_6);
		nameKeys.put("扇子", FAN_2);
		nameKeys.put("御灵衣服", CLOTHES_YULING_4);
		nameKeys.put("角色乘骑", ROLE_MOUNT_9);
	}

	public final static byte getSkeletionByName(String skeletionName) {
		Byte s = nameKeys.get(skeletionName);
		if (s == null) {
			return -1;
		}
		return s;
	}

	public static final void addSkeleton(String file, byte skeleton) {
		if (file == null || file.trim().length() < 1) {
			return;
		}
		skeletonKeys.put(file, skeleton);
	}

	public static final byte getSkeletonByName(String file) {
		Byte skeleton = skeletonKeys.get(file);
		if (skeleton == null) {
			return -1;
		}
		return skeleton;
	}

	public final static Map<Byte, String> parseSkeletons(String skeletons) {
		Map<Byte, String> map = new HashMap<Byte, String>();
		skeletons = skeletons.replaceAll("|", ";");
		String[] array = StringUtil.splitString(skeletons, ';');
		for (int ai = 0; ai < array.length; ai++) {
			String[] array2 = StringUtil.splitString(array[ai], '=');
			if (array2.length == 2) {
				map.put(Byte.parseByte(array2[0]), array2[1]);
			}
		}
		return map;
	}

	/**
	 * 通过职业获取时装骨骼号
	 * 
	 * @param profession
	 * @return
	 */
	public static byte getSkeletonByProfession(byte profession) {
		switch (profession) {
			case RoleProfession.XuanXian_1: {
				return CLOTHES_XUANXIAN_8;
			}
			case RoleProfession.XiuLuo_2: {
				return CLOTHES_XIULUO_7;
			}
			case RoleProfession.YuLing_16: {
				return CLOTHES_YULING_4;
			}
			default: {
				return CLOTHES_XIULUO_7;
			}
		}
	}
}