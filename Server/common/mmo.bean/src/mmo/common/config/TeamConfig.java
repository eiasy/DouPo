package mmo.common.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TeamConfig {
	/** 队伍人数上限 */
	public static final byte               MAX_COUNT        = 5;
	public static final byte               CATE_COMMON      = 1;
	public static final byte               CATE_COPY        = 2;
	/** 房间人数上限 */
	public static final short              ROOM_MAX_ROLE    = 4;
	/** 房间位置状态 */
	public static final byte               ROOM_INDEX_OPEN  = 0;                          // 开启
	public static final byte               ROOM_INDEX_CLOSE = 1;                          // 关闭
	public static final byte               ROOM_INDEX_READY = 2;                          // 准备
	/**好友 申请列表上限*/
	public static final byte               FRIEND_REQ_MAX_COUNT        = 10;

	private static final Map<Byte, String> CATE_NAME        = new HashMap<Byte, String>();
	static {
		CATE_NAME.put(CATE_COMMON, "普通");
		CATE_NAME.put(CATE_COPY, "副本");
	}

	public final static String[] getCateNames() {
		Collection<String> values = CATE_NAME.values();
		String[] names = new String[values.size()];
		values.toArray(names);
		return names;
	}

	public final static String getCateName(byte cateValue) {
		return CATE_NAME.get(cateValue);
	}
}
