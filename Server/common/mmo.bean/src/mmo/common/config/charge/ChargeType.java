package mmo.common.config.charge;

import java.util.HashMap;
import java.util.Map;

public class ChargeType {
	/** 角色充值成功 */
	public final static byte TYPE_ROLE_CHARGE_SUCC = 1;
	/** GM补点 */
	public final static byte TYPE_GM_ADD = 2;
	/** 角色充值失败 */
	public final static byte TYPE_ROLE_CHARGE_FAIL = 3;
	/** 角色充值异常 */
	public final static byte TYPE_4_ERROR = 4;

	private final static Map<Byte, String> notes = new HashMap<Byte, String>();

	static {
		notes.put(TYPE_ROLE_CHARGE_SUCC, "成功");
		notes.put(TYPE_GM_ADD, "GM补点");
		notes.put(TYPE_ROLE_CHARGE_FAIL, "失败");
		notes.put(TYPE_4_ERROR, "异常");
	}

	public final static String getNote(byte type) {
		String note = notes.get(type);
		if (note == null) {
			return "未知@" + type;
		}
		return note;
	}

}
