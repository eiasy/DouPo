package mmo.common.protocol.activemq.sub;

public interface SubPro_20047_COMMANDS {
	/** 重新加载客户端机型配置 */
	int SUB_1_RELOAD_CLIENT_URL  = 1;
	/** 加载类库 */
	int SUB_2_CLASSLOADER        = 2;
	/** 登录限制 */
	int SUB_3_LOGIN_LIMIT        = 3;
	/** 特权账号 */
	int SUB_4_SPECIAL_ACCOUNT    = 4;
	/** 加载特权账号 */
	int SUB_5_LOAD_ACCOUNT_LIMIT = 5;
	/** 加载测试账号 */
	int SUB_6_LOAD_ACCOUNT_TEST  = 6;
	/**加载配置项*/
	int SUB_7_LOAD_CONFIG        = 7;
}
