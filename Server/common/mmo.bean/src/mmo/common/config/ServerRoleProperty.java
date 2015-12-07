package mmo.common.config;

import java.util.HashMap;
import java.util.Map;

import mmo.common.protocol.game.CommonGamePropertyKey;
import mmo.tools.log.LoggerError;

/**
 * 服务器用到的属性配置表
 * 
 * @author 李天喜
 * 
 */
public class ServerRoleProperty {
	/** 生命 */
	public static final short               HP_MAX          = 1;
	/** 攻击 */
	public static final short               ATTACK          = 2;
	/** 防御 */
	public static final short               DEFENCE         = 3;
	/** 闪避 */
	public final static short               DUCK            = 4;
	/** 暴击 */
	public final static short               CRIT            = 5;
	/** 韧性 */
	public final static short               TENACITY        = 6;
	/** 格挡 */
	public final static short               BLOCK           = 7;
	/** 破甲 */
	public final static short               BREAK           = 8;
	/** 命中 */
	public final static short               RATIO           = 9;
	/** 破防 */
	public final static short               REDUCE          = 10;
	/** 抵抗 */
	public final static short               RESIST          = 11;
	/** 吸血 */
	public final static short               SUCK            = 12;
	/** 反弹 */
	public final static short               REBOUND         = 13;
	/** 法力上限 */
	public final static short               MP_MAX          = 14;

	/** 体术 */
	public final static short               FORCE           = 20;
	/** 法术 */
	public final static short               MAGIC           = 21;
	/** 神识 */
	public final static short               SPRIT           = 22;

	/** 速度 */
	public final static short               SPEED           = 30;

	private static final Map<String, Short> PROP_KEY        = new HashMap<String, Short>();
	private final static Map<Short, String> PROP_NAME       = new HashMap<Short, String>();
	private final static Map<Short, Short>  PROP_CLIENT_KEY = new HashMap<Short, Short>();

	static {
		PROP_KEY.put("攻击", ATTACK);
		PROP_KEY.put("防御", DEFENCE);
		PROP_KEY.put("生命", HP_MAX);
		PROP_KEY.put("法力", MP_MAX);
		PROP_KEY.put("闪避", DUCK);
		PROP_KEY.put("暴击", CRIT);
		PROP_KEY.put("韧性", TENACITY);
		PROP_KEY.put("格挡", BLOCK);
		PROP_KEY.put("破甲", BREAK);
		PROP_KEY.put("命中", RATIO);
		PROP_KEY.put("破防", REDUCE);
		PROP_KEY.put("抵抗", RESIST);
		PROP_KEY.put("吸血", SUCK);
		PROP_KEY.put("反弹", REBOUND);
		PROP_KEY.put("体术", FORCE);
		PROP_KEY.put("法术", MAGIC);
		PROP_KEY.put("神识", SPRIT);
		PROP_KEY.put("速度", SPEED);

		PROP_NAME.put(HP_MAX, "生命");
		PROP_NAME.put(MP_MAX, "法力");
		PROP_NAME.put(ATTACK, "攻击");
		PROP_NAME.put(DEFENCE, "防御");
		PROP_NAME.put(RATIO, "命中");
		PROP_NAME.put(DUCK, "闪避");
		PROP_NAME.put(CRIT, "暴击");
		PROP_NAME.put(TENACITY, "韧性");
		PROP_NAME.put(BLOCK, "格挡");
		PROP_NAME.put(BREAK, "破甲");
		PROP_NAME.put(REDUCE, "破防");
		PROP_NAME.put(RESIST, "抵抗");
		PROP_NAME.put(SUCK, "吸血");
		PROP_NAME.put(REBOUND, "反弹");
		PROP_NAME.put(FORCE, "体术");
		PROP_NAME.put(MAGIC, "法术");
		PROP_NAME.put(SPRIT, "神识");
		PROP_NAME.put(SPEED, "速度");

		PROP_CLIENT_KEY.put(HP_MAX, CommonGamePropertyKey.Fight.FIGHT_HP_603);
		PROP_CLIENT_KEY.put(ATTACK, CommonGamePropertyKey.Fight.FIGHT_ATTACK_604);
		PROP_CLIENT_KEY.put(DEFENCE, CommonGamePropertyKey.Fight.FIGHT_DEFENSE_605);
		PROP_CLIENT_KEY.put(RATIO, CommonGamePropertyKey.Fight.FIGHT_PROBABILITY_606);
		PROP_CLIENT_KEY.put(DUCK, CommonGamePropertyKey.Fight.FIGHT_DUCK_607);
		PROP_CLIENT_KEY.put(CRIT, CommonGamePropertyKey.Fight.FIGHT_STRIKE_608);
		PROP_CLIENT_KEY.put(TENACITY, CommonGamePropertyKey.Fight.FIGHT_TENACITY_609);
		PROP_CLIENT_KEY.put(BLOCK, CommonGamePropertyKey.Fight.FIGHT_FENDER_610);
		PROP_CLIENT_KEY.put(BREAK, CommonGamePropertyKey.Fight.FIGHT_DESTROY_611);
		PROP_CLIENT_KEY.put(REDUCE, CommonGamePropertyKey.Fight.FIGHT_REDUCE_DEFENCE_614);
		PROP_CLIENT_KEY.put(RESIST, CommonGamePropertyKey.Fight.FIGHT_RESIST_615);
		PROP_CLIENT_KEY.put(SUCK, CommonGamePropertyKey.Fight.FIGHT_SUCKHP_612);
		PROP_CLIENT_KEY.put(REBOUND, CommonGamePropertyKey.Fight.FIGHT_REBOUND_613);
		PROP_CLIENT_KEY.put(FORCE, CommonGamePropertyKey.Fight.FIGHT_FORCE_600);
		PROP_CLIENT_KEY.put(MAGIC, CommonGamePropertyKey.Fight.FIGHT_MAGIC_601);
		PROP_CLIENT_KEY.put(SPRIT, CommonGamePropertyKey.Fight.FIGHT_SPRIT_602);
	}

	public final static short getPropertyKey(String propertyName) {
		Short key = PROP_KEY.get(propertyName);
		if (key == null) {
			LoggerError.error("ServerRoleProperty 未定义属性：" + propertyName);
			return 0;
		}
		return key;
	}

	/**
	 * 通过属性序号获取名称
	 * 
	 * @param key
	 * @return
	 */
	public final static String getPropertyName(short key) {
		String name = PROP_NAME.get(key);
		if (name == null) {
			LoggerError.error("ServerRoleProperty定义属性序号：" + key);
			return StringLib.CommonStr.commonNo;
		}
		return name;
	}

	/**
	 * 通过服务器端属性key获取客户端属性key
	 * 
	 * @param serverKey
	 * @return
	 */
	public final static short getClientKey(short serverKey) {
		Short clientKey = PROP_CLIENT_KEY.get(serverKey);
		if (clientKey == null) {
			return 0;
		}
		return clientKey;
	}

	/**
	 * 通过模糊的名称，获取属性KEY
	 * 
	 * @param fuzzyName
	 *            模糊的名称
	 * @return
	 */
	public final static short getFuzzyPropertyKey(String fuzzyName) {
		if (fuzzyName == null) {
			return -1;
		} else if (fuzzyName.startsWith("生命")) {
			return HP_MAX;
		} else if (fuzzyName.startsWith("攻击")) {
			return ATTACK;
		} else if (fuzzyName.startsWith("防御")) {
			return DEFENCE;
		} else if (fuzzyName.startsWith("命中")) {
			return RATIO;
		} else if (fuzzyName.startsWith("闪避")) {
			return DUCK;
		} else if (fuzzyName.startsWith("暴击")) {
			return CRIT;
		} else if (fuzzyName.startsWith("韧性")) {
			return TENACITY;
		} else if (fuzzyName.startsWith("格挡")) {
			return BLOCK;
		} else if (fuzzyName.startsWith("破甲")) {
			return BREAK;
		} else if (fuzzyName.startsWith("破防")) {
			return REDUCE;
		} else if (fuzzyName.startsWith("抵抗")) {
			return RESIST;
		} else if (fuzzyName.startsWith("吸血")) {
			return SUCK;
		} else if (fuzzyName.startsWith("反弹")) {
			return REBOUND;
		} else if (fuzzyName.startsWith("体术")) {
			return FORCE;
		} else if (fuzzyName.startsWith("法术")) {
			return MAGIC;
		} else if (fuzzyName.startsWith("神识")) {
			return SPRIT;
		} else {
			return -1;
		}
	}

}
