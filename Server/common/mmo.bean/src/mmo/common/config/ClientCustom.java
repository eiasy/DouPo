package mmo.common.config;

public class ClientCustom {
	public static final String MODEL = "model";

	/** 客户端包类型 */
	public interface ClientModel {
		/** 正式包 */
		byte V_0_RELEASE    = 0;
		/** 云测试包 */
		byte V_1_CLOUD_TEST = 1;
		/** 系统机器人，提供协战数据及排行数据 */
		byte V_2_ROBOT      = 2;
		/** 压力测试机器人 */
		byte V_3_FORCE_TEST = 3;
	}
}
