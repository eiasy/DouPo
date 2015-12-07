package mmo.common.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class TriggerConfig extends BeanConfig {
	public static class Reason {
		/** 接任务触发 */
		public static final short               RECEIVE_MISSION  = 1;
//		/** 提交任务触发 */
//		public static final short               COMMIT_MISSION   = 2;
//		/** 进入场景触发 */
//		public static final short               ENTER_SCENE      = 3;
		/** 退出场景触发 */
		public static final short               EXIT_SCENE       = 4;
		/** 怪物死亡触发 */
		public static final short               MONSTER_DEATH    = 5;
//		/** 使用物品触发 */
//		public static final short               USE_GOODS        = 6;
		/** 获取物品触发 */
		public static final short               GET_GOODS        = 7;
//		/** 升级等级触发 */
//		public static final short               UPGRADE_LEVEL    = 8;
		/** 升级境界触发 */
		public static final short               UPGRADE_REALM    = 9;
//		/** 出口触发事件 */
//		public static final short               EXIT_TRIGER      = 10;
//		/** 与NPC交互 */
//		public static final short               NPC_MUTUAL       = 11;
		private final static Map<Short, String> triggerCondition = new HashMap<Short, String>();

		static {
			triggerCondition.put(RECEIVE_MISSION, "接任务触发");
//			triggerCondition.put(COMMIT_MISSION, "提交任务触发");
//			triggerCondition.put(ENTER_SCENE, "进入场景触发");
			triggerCondition.put(EXIT_SCENE, "退出场景触发 ");
			triggerCondition.put(MONSTER_DEATH, "怪物死亡触发");
//			triggerCondition.put(USE_GOODS, "使用物品触发");
			triggerCondition.put(GET_GOODS, "获取物品触发");
//			triggerCondition.put(UPGRADE_LEVEL, "升级等级触发");
			triggerCondition.put(UPGRADE_REALM, "升级境界触发");
//			triggerCondition.put(EXIT_TRIGER, "传送阵触发");
//			triggerCondition.put(NPC_MUTUAL, "与NPC交互");
		}

		public final static String getReason(short key) {
			String value = triggerCondition.get(key);
			if (value == null) {
				value = "NULL";
			}
			return value;
		}

		public final static String[] getReasons() {
			Collection<String> reasons = triggerCondition.values();
			String[] rs = new String[reasons.size()];
			reasons.toArray(rs);
			return rs;
		}

		public final static short getReasonKey(String reason) {
			if (reason == null) {
				return 0;
			}
			Set<Short> keys = triggerCondition.keySet();
			for (short k : keys) {
				if (reason.equals(triggerCondition.get(k))) {
					return k;
				}
			}
			return 0;
		}
	}

}
