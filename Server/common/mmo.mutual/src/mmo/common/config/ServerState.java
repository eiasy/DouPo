package mmo.common.config;

import java.util.HashMap;
import java.util.Map;

public class ServerState {
	/** 火爆 */
	public static final String             stateHuoBao    = "火爆";
	/** 满员 */
	public static final String             stateFull      = "满员 ";
	/** 流畅 */
	public static final String             stateLiuChang  = "流畅";
	/** 良好 */
	public static final String             stateWell      = "良好";
	/** 维护中 */
	public static final String             stateRepair    = "维护";
	/** 爆满 */
	public static final String             stateBaoMan    = "爆满";

	/** 火爆 */
	public static final byte               STATE_HUOBAO   = 1;
//	/** 已满 */
//	public static final byte               STATE_FULL     = 2;
	/** 流畅 */
	public static final byte               STATE_LIUCHANG = 3;
	/** 良好 */
	public static final byte               STATE_WELL     = 4;
	/** 维护 */
	public static final byte               STATE_REPAIR   = 5;
	/** 爆满 */
	public static final byte               STATE_BAOMAN   = 6;

	private static final Map<Byte, String> serverStates   = new HashMap<Byte, String>();
	static {
		serverStates.put(STATE_REPAIR, stateRepair);
//		serverStates.put(STATE_FULL, stateFull);
		serverStates.put(STATE_LIUCHANG, stateLiuChang);
		serverStates.put(STATE_WELL, stateWell);
		serverStates.put(STATE_HUOBAO, stateHuoBao);
		serverStates.put(STATE_BAOMAN, stateBaoMan);
	}

	public static final String getServerState(byte state) {
		String info = serverStates.get(state);
		if (info == null) {
			return "未知";
		}
		return info;
	}

	public static final String getServerState(int connectMax, int connectCount, int enterMax, int enterCount) {
		if (connectCount < connectMax) {
			if (enterCount < enterMax / 4) {
				return stateLiuChang;
			} else if (enterCount < enterMax / 2) {
				return stateWell;
			} else if (enterCount < enterMax * 3 / 4) {
				return stateHuoBao;
			}
			return stateBaoMan;
		} else {
			return stateFull;
		}
	}

	public static final byte getServerStateValue(int connectMax, int connectCount, int enterMax, int enterCount) {
		if (connectCount < connectMax) {
			if (enterCount < enterMax / 4) {
				return STATE_LIUCHANG;
			} else if (enterCount < enterMax / 2) {
				return STATE_WELL;
			} else if (enterCount < enterMax * 3 / 4) {
				return STATE_HUOBAO;
			}
			return STATE_BAOMAN;
		} else {
			return STATE_BAOMAN;
		}
	}
}
