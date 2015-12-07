package mmo.common.config.skill;

public class SkillFlag {
	/** 连招 */
	public static final short COMBO    = 1 << 0;
	/** 首招 */
	public static final short FIRST    = 1 << 1;
	/** 减益 */
	public static final short MINUS    = 1 << 2;
	/** 主动 */
	public static final short ACTIVE   = 1 << 3;
	/** 物理攻击 */
	public static final short PHYSICS  = 1 << 4;
	/** 施法对象包括施法者 */
	public static final short FIRER_IN = 1 << 5;
}
