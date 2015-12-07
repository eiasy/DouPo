package mmo.common.protocol.command;

public interface ProPrivateClient_15000 {
	/** 向服务器注册 */
	int P_15001_REGISTER              = 15001;
	/** 心跳协议 */
	int P_15002_HEART_BEAT            = 15002;
	/** 单一用户的数据 */
	int P_15003_SINGLE_USER_DATA      = 15003;
	/** 一组用户的数据 */
	int P_15004_GROUP_USER_DATA       = 15004;
	/** 关闭与玩家之间的数据连接 */
	int P_15005_CLOSE_USER_NET        = 15005;
	/** 向网关返回验证码 */
	int P_15007_SECURITY_CODE         = 15007;
	/** 更新帐号在游戏服务器上的角色数量 */
	int P_15009_SERVER_ROLE           = 15009;
	/** 注册服務的平台 */
	int P_15010_PLATFORMS             = 15010;
	/** 验证兑换码 */
	int P_15011_VALIDATE_EXCHANGE     = 15011;
	/** 返回系统公告 */
	int P_15012_SYSTEM_NOTICE         = 15012;
	/** 游戏服务器已经记录充值记录 */
	int P_15013_CHARGE_ADDED          = 15013;
	/** 账号角色列表 */
	int P_15014_ACCOUNT_ROLE          = 15014;
	/** 角色详细信息 */
	int P_15015_ROLE_DETAIL           = 15015;
	/**
	 * 修改密码 int P_15016_ALTER_PWD = 15016;
	 */
	/** 验证手机号 */
	int P_15017_VALIDATE_PHONE        = 15017;
	/** 绑定手机 */
	int P_15018_BIND_PHONE            = 15018;
	/** 发奖列表 */
	int P_15019_AWARD_LIST            = 15019;
	/** 验证验证码 */
	int P_15020_SECURITY_CODE         = 15020;
	/** 验证服务器状态 */
	int P_15021_CHECK_SERVER          = 15021;
	/** 反向注册验证码 */
	int P_15022_REVERSE_SECURITY_CODE = 15022;
	/** 数据上传结果 */
	int P_15023_DATA_UPLOAD_RESULT    = 15023;
	/** 更新角色等级结果 */
	int P_15024_LEVEL_UPDATE          = 15024;
	/** 排行榜数据 */
	int P_15025_RANK_LIST             = 15025;
	/** 设备上线 */
	int P_15026_DEVICE_ONLINE         = 15026;
	/** 设备离线 */
	int P_15027_DEVICE_OFFLINE        = 15027;
	/** 推送一条消息 */
	int P_15028_PUSH_TIP              = 15028;
	/** 设置货币恢复满时推送的消息 */
	int P_15029_MONEY_TIP             = 15029;
	/** 响应管理器的操作 */
	int P_15030_RESPONSE_PACKET       = 15030;
	/** 向GM工具推送数据 */
	int P_15031_RESPONSE_DATA         = 15031;

}
