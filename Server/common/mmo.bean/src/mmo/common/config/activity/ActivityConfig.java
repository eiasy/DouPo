package mmo.common.config.activity;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ActivityConfig {
	private static final Map<Byte, String> nameFinish        = new TreeMap<Byte, String>();
	/***************************************/
	/*************** 完成活动的条件 ***************/
	/***************************************/
	/** 使用道具 */
	public static final byte               finishUseGoods    = 1;
	/** 杀死怪物 */
	public static final byte               finishKillMonster = 2;
	/** 参与宗战 */
	public static final byte               finishSectBattle  = 3;
	/** 完成副本 */
	public static final byte               finishDuplicate   = 4;
	/** 完成任务 */
	public static final byte               finishMission     = 5;
	/** 进入指定场景 */
	public static final byte               finishEnterScene   = 6;

	/***************************************/
	/*************** 活动的执行事件事件 ***************/
	/***************************************/
	/** 活动开始的的事件 */
	public static final byte               executeAtart      = 1;
	/** 活动结束时的事件 */
	public static final byte               executeEnd        = 2;

	/***************************************/
	/*************** 活动相关的事件类型 ***************/
	/***************************************/
	/** 添加NPC */
	public static final byte               eventAddNpc       = 1;
	/** 清理NPC */
	public static final byte               eventClearNpc     = 2;
	/** 添加怪物 */
	public static final byte               eventAddMonster   = 3;
	/** 清理怪物 */
	public static final byte               eventClearMonster = 4;
	static {
		nameFinish.put(finishUseGoods, "使用道具");
		nameFinish.put(finishKillMonster, "杀死怪物");
		nameFinish.put(finishSectBattle, "参与宗战");
		nameFinish.put(finishDuplicate, "完成副本");
		nameFinish.put(finishMission, "完成任务");
		nameFinish.put(finishEnterScene, "进入指定场景");
	}

	public static final String[] getNameFinishs() {
		Collection<String> values = nameFinish.values();
		String[] names = new String[values.size()];
		values.toArray(names);
		return names;
	}

	public static final String getFinishName(byte finish) {
		return nameFinish.get(finish);
	}

	public static final byte getFinish(String name) {
		Set<Byte> keys = nameFinish.keySet();
		for (byte key : keys) {
			if (name.equals(nameFinish.get(key))) {
				return key;
			}
		}
		return 0;
	}

	public static final String tagFinishsStart = "<finishs>";
	public static final String tagFinishsEnd   = "</finishs>";
	public static final String tagAwardsStart  = "<awards>";
	public static final String tagAwardsEnd    = "</awards>";
	public static final String tagEventsStart  = "<events>";
	public static final String tagEventsEnd    = "</events>";
}
