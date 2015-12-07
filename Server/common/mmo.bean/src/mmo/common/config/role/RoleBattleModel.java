package mmo.common.config.role;

public final class RoleBattleModel {
	/** 和平模式 */
	public static final byte      modelPeace = 0;
	/** 屠杀 */
	public static final byte      modelKill  = 1;
	/** 组队 */
	public static final byte      modelTeam  = 2;
	/** 宗战 */
	public static final byte      modelSect  = 3;
	/** 势力 */
	public static final byte      modelPower = 4;
	private static final String[] names      = { "和平", "屠杀", "组队", "宗战", "势力" };

	public final static String getName(int model) {
		if (model < 0) {
			model = 1;
		}
		if (model > 4) {
			model = 0;
		}
		return names[model];
	}
}