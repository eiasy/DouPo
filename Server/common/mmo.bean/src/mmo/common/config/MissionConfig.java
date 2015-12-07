package mmo.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MissionConfig {
	/** 任务刷星间隔时间 */
	public static final int                   STAR_OFFSET        = 3599;

	public static final String                XML                = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

	public static final String[]              FLAG_TITLE         = { "可以放弃", "不能放弃" };

	private static final Map<String, Byte>    FLAG_VALUE         = new HashMap<String, Byte>();

	private static final Map<String, Short>   MISSION_EVENT_TYPE = new HashMap<String, Short>();

	/** 副本难度名称 */
	private static final Map<Integer, String> hardLevelNameMap   = new HashMap<Integer, String>();

	static {
		FLAG_VALUE.put(FLAG_TITLE[0], Flag.YES);
		FLAG_VALUE.put(FLAG_TITLE[1], Flag.NO);

		MISSION_EVENT_TYPE.put("调用脚本ID", Event.CALL_SCRIPT_ID);
		MISSION_EVENT_TYPE.put("发送教程ID", Event.SEND_TEACHE_ID);
		MISSION_EVENT_TYPE.put("装备", Event.EQUIP_OPE);
		MISSION_EVENT_TYPE.put("开启功能ID", Event.OPEN_FUNS);
		MISSION_EVENT_TYPE.put("开启秘境ID", Event.MIJIN_ID);

		hardLevelNameMap.put(0, "简单");
		hardLevelNameMap.put(1, "普通");
		hardLevelNameMap.put(2, "困难");
		hardLevelNameMap.put(3, "极难");
	}

	public static class Award {
		/** 奖励物品 */
		public static final int                   GOODS       = 1 << 0;
		/** 奖励经验 */
		public static final int                   EXPERIENCE  = 1 << 1;
		/** 奖励货币 */
		public static final int                   MONEY       = 1 << 2;
		/** 境界点 */
		public static final int                   REALM_POINT = 1 << 4;
		/** 贡献点 */
		public static final int                   CONTRIBUTE  = 1 << 5;
		/** 宗门奖励 */
		public static final int                   SECT_AWARD  = 1 << 6;
		private static final Map<Integer, String> AWARD_TAGS  = new HashMap<Integer, String>();
		static {
			AWARD_TAGS.put(GOODS, "道具");
			AWARD_TAGS.put(EXPERIENCE, "经验");
			AWARD_TAGS.put(MONEY, "绑灵");
			AWARD_TAGS.put(REALM_POINT, "境界点");
			AWARD_TAGS.put(CONTRIBUTE, "贡献点");
			AWARD_TAGS.put(SECT_AWARD, "宗门奖励");
		}
	}

	public static class Category {
		public static final short               NONE           = 0;
		/** 主线 */
		public static final short               TRUNK          = 1;
		/** 支线 */
		public static final short               BRANCH         = 2;
		/** 每日-玩法任务 */
		public static final short               DAY_PLAY       = 13;
		/** 每日-升级任务 */
		public static final short               DAY_LEVEL      = 14;
		/** 每日-收藏任务 */
		public static final short               DAY_COLLECT    = 15;
		/** 每日-交互任务 */
		public static final short               DAY_MUTUAL     = 16;
		/** 活跃度任务 */
		public static final short               LIVENESS       = 17;

		private static final Map<Short, String> names          = new HashMap<Short, String>();
		private static final List<Short>        EVERY_DAY_CATE = new ArrayList<Short>();
		static {
			names.put(TRUNK, "[主线]");
			names.put(DAY_PLAY, "玩法任务");
			names.put(DAY_LEVEL, "升级任务");
			names.put(DAY_COLLECT, "收藏任务");
			names.put(DAY_MUTUAL, "交互任务");
			names.put(LIVENESS, "活跃度任务");

			EVERY_DAY_CATE.add(DAY_PLAY);
			EVERY_DAY_CATE.add(DAY_LEVEL);
			EVERY_DAY_CATE.add(DAY_COLLECT);
			EVERY_DAY_CATE.add(DAY_MUTUAL);
		}

		public static final boolean isNpcDispatchMission(short category) {
			return category == TRUNK;
		}

		public static final boolean isEveryDayMission(short category) {
			return category == DAY_PLAY || category == DAY_LEVEL || category == DAY_COLLECT || category == DAY_MUTUAL;
		}

		public static final boolean isLivenessMission(short category) {
			return category == LIVENESS;
		}

		public static final List<Short> getEveryDayCate() {
			return EVERY_DAY_CATE;
		}

		public static final int getEveryDaySub(short category) {
			switch (category) {
				case TRUNK: {
					return 0;
				}
				case DAY_PLAY:
					return 1;
				case DAY_LEVEL:
					return 2;
				case DAY_COLLECT:
					return 3;
				case DAY_MUTUAL:
					return 4;
				case LIVENESS:
					return 0;
				default:
					return -1;
			}
		}

		public static final String[] getNames() {
			String[] temp = new String[names.size()];
			names.values().toArray(temp);
			return temp;
		}

		public static final String getName(short cate) {
			return names.get(cate);
		}

		public static final short getCate(String name) {
			Set<Short> keys = names.keySet();
			for (short key : keys) {
				if (name.equals(names.get(key))) {
					return key;
				}
			}
			return DAY_PLAY;
		}
	}

	public static interface Flag {
		public static final byte YES = 0;
		public static final byte NO  = 1;
	}

	public static interface Event {
		public static final short CALL_SCRIPT_ID = 100;
		public static final short SEND_TEACHE_ID = 101;
		public static final short EQUIP_OPE      = 102;
		public static final short OPEN_FUNS      = 103;
		public static final short MIJIN_ID       = 104;

	}

	public static final byte getFlag(String title) {
		return FLAG_VALUE.get(title);
	}

	public static Short getMissionEventType(String key) {
		return MISSION_EVENT_TYPE.get(key);
	}

	public static String getHardlevelName(int key) {
		return hardLevelNameMap.get(key) == null ? "" : hardLevelNameMap.get(key);
	}

	public static interface Liveness {
		// 完成状态(0.前往 1.可领取2.已达成)
		public static final byte HAVE_SCRIPT_0 = 0;
		public static final byte FINISH_1      = 1;
		public static final byte NO_SCRIPT_2   = 2;
	}
}
