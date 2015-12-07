package mmo.common.protocol.command;

public interface ProPrivateServer_10000 {
	/** 联网确认 */
	int P_10001_NET_CONFIRM        = 10001;
	/** 转发用户数据 */
	int P_10002_USER_DATA          = 10002;
	/** 用户离线 */
	int P_10003_OUTLINE            = 10003;
	/** 消息 */
	int P_10004_MESSAGE            = 10004;
	/** 应用状态 */
	int P_10005_APPLICATION_STATE  = 10005;
	/** 验证码 */
	int P_10006_SECURITY_CODE      = 10006;
	/** 网关列表 */
	int P_10009_GATEWAY_LIST       = 10009;
	/** 更新服务器上的角色数量 */
	int P_10010_ROLE_COUNT         = 10010;
	/** 回应心跳 */
	int P_10011_RESPONSE_HEARTBEAT = 10011;
	/** 进入维护状态 */
	int P_10012_APP_STATE          = 10012;
	/** 兑换码验证结果 */
	int P_10013_EXCHANGE_RESULT    = 10013;
	/** 修改承载人数 */
	int P_10014_SUPPORT_COUNT      = 10014;
	/** 添加系统公告 */
	int P_10015_ADD_SYSTEM_NOTICE  = 10015;
	/** 添加跑马灯 */
	int P_10016_ADD_PAO_MA_DENG    = 10016;
	/** 系统公告列表 */
	int P_10017_SYSTEM_NOTICE      = 10017;
	/** 删除跑马灯 */
	int P_10018_DEL_PAO_MA_DENG    = 10018;
	/** 更新跑马灯 */
	int P_10019_UPDATE_PAO_MA_DENG = 10019;
	/** 账号管理 */
	int P_10020_MANAGER_ROLE       = 10020;
	/** 添加一条充值记录 */
	int P_10021_CHARGE_ADD         = 10021;
	/** 服务器信息 */
	int P_10022_SERVER_INFO        = 10022;
	/** 充值信息 */
	int P_10023_CHARGE_INFO        = 10023;
	/** 获取账号下面的角色 */
	int P_10024_ACCOUNT_ROLE       = 10024;
	/** 获取角色详细信息 */
	int P_10025_ROLE_DETAIL        = 10025;
	/** 把角色踢下线 */
	int P_10026_KICKOUT_ROLE       = 10026;
	/** GM邮件奖励 */
	int P_10027_EMAIL_AWARD        = 10027;
	/** 禁言 */
	int P_10028_FORBID_CHAT        = 10028;
	/** 脱离卡死 */
	int P_10029_ROLE_FREE          = 10029;
	/** 充值失败 */
	int P_10030_CHARGE_FAIL        = 10030;
	/** 充值开关 */
	int P_10031_CHARGE_SWITCH      = 10031;
	/** 添加活动 */
	int P_10032_ADD_GMACTIVITY     = 10032;
	/** 更新活动内容 */
	int P_10033_UPDATE_GMACTIVITY  = 10033;
	/** 停止活动 */
	int P_10034_STOP_GMACTIVITY    = 10034;
	/** 确认事件 */
	int P_10035_CONFIRM_EVENT      = 10035;
	/** 确认事件 */
	int P_10036_CONFIRM_EVENT      = 10036;
	/** 游戏开关项 */
	int P_10037_SWITCH_ITEM        = 10037;
	/** 请求发奖记录列表 */
	int P_10038_AWARD_LIST         = 10038;
	/** 添加发奖记录 */
	int P_10039_AWARD_ADD          = 10039;
	/** 更新发奖记录 */
	int P_10040_AWARD_UPDATE       = 10040;
	/** 把服务器列表转发给各个游戏分区 */
	int P_10041_SERVER_LIST        = 10041;
	/** 添加新服 */
	int P_10042_SERVER_ADD         = 10042;
	/** 更新服务器信息 */
	int P_10043_SERVER_UPDATE      = 10043;
	/** 移除服务器 */
	int P_10044_SERVER_REMOVE      = 10044;
	/** 确认服务器状态 */
	int P_10045_CHECK_SEVER        = 10045;
	/** 冻结角色 */
	int P_10046_FREEZE_ROLE        = 10046;
	/** 上传数据 */
	int P_10047_DATA_UPLOAD        = 10047;
	/** 更新角色等级 */
	int P_10048_LEVEL_UPDATE       = 10048;
	/** 排行榜数据 */
	int P_10049_RANK_LIST          = 10049;
	/** 添加或更新礼包 */
	int P_10050_GIFT_ADD_UPDATE    = 10050;
	/** 请求角色详细数据 */
	int P_10051_ROLE_DETAIL        = 10051;
	/** 更新角色数据 */
	int P_10052_UPDATE_ROLE_DATA   = 10052;
	/** 批量冻结角色 */
	int P_10053_gmCmds   = 10053;
}
