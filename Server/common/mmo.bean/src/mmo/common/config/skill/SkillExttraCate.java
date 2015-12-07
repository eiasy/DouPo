package mmo.common.config.skill;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
	 * 附加类型
	 * 
	 * @author 李天喜
	 * 
	 */
	public class SkillExttraCate {
		/** 类别名称 */
		private static final Map<Short, String> names           = new HashMap<Short, String>();
		/** 技能 */
		public static final short               skill           = 0;
		/** 药丸 */
		public static final short               pill            = 1;
		/** 普通攻击 */
		public static final short               physical        = 2;
		/** 可执行的buf */
		public static final short               bufExecute      = 3;
		/** 监听伤害 */
		public static final short               bufListenHurt   = 4;
		/** 监听治疗 */
		public static final short               bufListenCure   = 5;
		/** 监听经验值变更 */
		public static final short               bufListenExp    = 6;
		/** 监听攻击 */
		public static final short               bufListenAttack = 7;

		static {
			names.put(skill, "技能");
			names.put(pill, "药品");
			names.put(physical, "普通攻击");
			names.put(bufExecute, "执行BUF");
			names.put(bufListenHurt, "BUF监听伤害");
			names.put(bufListenCure, "BUF监听治疗");
			names.put(bufListenExp, "BUF监听经验");
			names.put(bufListenAttack, "BUF监听攻击");
		}

		public static final String[] getNames() {
			String[] array = new String[names.size()];
			names.values().toArray(array);
			return array;
		}

		public static final short getExtraCate(String name) {
			if (name == null) {
				return 0;
			}
			Set<Short> set = names.keySet();
			for (short key : set) {
				if (name.equals(names.get(key))) {
					return key;
				}
			}
			return 0;
		}

		public static final String getName(short cate) {
			return names.get(cate);
		}
	}