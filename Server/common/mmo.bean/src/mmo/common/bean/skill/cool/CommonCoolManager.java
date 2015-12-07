package mmo.common.bean.skill.cool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.bean.xml.ParseCommonCoolXML;
import mmo.tools.util.FileUtil;

public class CommonCoolManager {
	private static String                         commonCoolFile = null;
	/** 所有的公共冷去<key=冷却ID, value=冷却对象> */
	private static final Map<Integer, CommonCool> allCommonCool  = new HashMap<Integer, CommonCool>();
	/** 每个职业对应的公共冷却列表<key=职业编号，value=公共冷却列表> */
	private static final Map<Byte, List<Integer>> professionCool = new HashMap<Byte, List<Integer>>();
	/** 技能引用的公共冷却<key=技能ID, value=公共冷却ID> */
	private static Map<Integer, Integer>          skillCool      = new HashMap<Integer, Integer>();
	/** 物品引用的公共冷却<key=物品ID, value=公共冷却ID> */
	private static Map<Integer, Integer>          goodsCool      = new HashMap<Integer, Integer>();

	/**
	 * 加载公共冷去
	 * 
	 * @param configRoot
	 *            配置文件根目录
	 */
	public static final void loadCommonCoolFile(String configRoot) {
		StringBuilder sb = new StringBuilder();
		sb.append(configRoot).append(FileUtil.FILE_SEPARATOR).append("cool").append(FileUtil.FILE_SEPARATOR).append("common-cool.xml");
		commonCoolFile = sb.toString();
		/** 保存游戏中共用冷却时间值 */
		ParseCommonCoolXML.parseCommonCool(commonCoolFile);
	}

	/**
	 * 按照职业添加技能引用的公共冷却
	 * 
	 * @param profession
	 * @param coolId
	 * @param skillId
	 */
	public static void addSkillCool(int coolId, int skillId) {
		skillCool.put(skillId, coolId);
	}

	/**
	 * 按照职业添加技能引用的公共冷却
	 * 
	 * @param profession
	 * @param coolId
	 * @param skillId
	 */
	public static void addGoodsCool(int coolId, int goodsId) {
		goodsCool.put(goodsId, coolId);
	}

	/**
	 * 添加新的冷却对象
	 * 
	 * @param cool
	 */
	public static void addCommonCool(byte[] professions, CommonCool cool) {
		if (professions != null) {
			for (byte profession : professions) {
				List<Integer> coolList = professionCool.get(profession);
				if (coolList == null) {
					coolList = new ArrayList<Integer>();
					coolList.add(cool.getId());
					professionCool.put(profession, coolList);
				} else {
					if (coolList.contains(cool.getId())) {
						return;
					} else {
						coolList.add(cool.getId());
					}
				}
			}
		}
		allCommonCool.put(cool.getId(), cool);
	}

	/**
	 * 通过职业编号获取公用冷却时间对象列表
	 * 
	 * @param profession
	 * @return
	 */
	public static List<Integer> getCommonCoolList(byte profession) {
		return professionCool.get(profession);
	}

	/**
	 * 获取冷却时间对象
	 * 
	 * @param key
	 * @return
	 */
	public static CommonCool getCommoncool(int key) {
		return allCommonCool.get(key);
	}

	public static CommonCool getGoodsCommonCool(int goodsId) {
		Integer value = goodsCool.get(goodsId);
		if (value == null) {
			return null;
		}
		return allCommonCool.get(value);
	}

	public static CommonCool getSkillCommonCool(int skillId) {
		Integer value = skillCool.get(skillId);
		if (value == null) {
			return null;
		}
		return allCommonCool.get(value);
	}

	/**
	 * 获取技能的共用冷却时间对象ID
	 * 
	 * @param skillId
	 * @return
	 */
	public static int getSkillCoolId(int skillId) {
		Integer value = skillCool.get(skillId);
		if (value != null) {
			return value;
		}
		return 0;
	}

	/**
	 * 获取物品的共用冷却时间对象ID
	 * 
	 * @param goodsId
	 * @return
	 */
	public static int getGoodsCoolId(int goodsId) {
		Integer value = goodsCool.get(goodsId);
		if (value != null) {
			return value;
		}
		return 0;
	}
}
