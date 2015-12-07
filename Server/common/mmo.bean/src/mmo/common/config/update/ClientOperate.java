package mmo.common.config.update;

public interface ClientOperate {
	/** 验证通过 */
	byte OPT_OK            = 1;
	/** 强制更新客户端 */
	byte OPT_DOWN_CLIENT   = 2;
	/** 提升小版本，建议更新客户端 */
	byte OPT_UP_VERSION    = 3;
	/** 版本号错误，强制更新客户端 */
	byte OPT_ERR_VERSION   = 4;
	/** 高版本 */
	byte OPT_HIGHT_VERSION = 5;
}
