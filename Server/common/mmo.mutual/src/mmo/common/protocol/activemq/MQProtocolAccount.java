package mmo.common.protocol.activemq;

public interface MQProtocolAccount {
	/**
	 * 账号登录 int P_20001_USER_LOGIN = 20001;
	 */
	/**
	 * 登录结果 int P_20002_USER_LOGIN_RESULT = 20002;
	 */
	/** 服务器通过消息队列注册到管理器 */
	int P_20003_SERVER_REGISTER       = 20003;
	/** 推送游戏服信息 */
	int P_20004_GAME_SERVERS          = 20004;
	/** 向服务器管理器推送验证码 */
	int P_20005_PUSH_SECURITY_CODE    = 20005;
	/**
	 * 用户直接注册 int P_20006_USER_REGISTER_DIRECT = 20006;
	 */
	/**
	 * 注册结果 int P_20007_USER_REGISTER_RESULT = 20007;
	 */
	/**
	 * 通过HTML SDK登录 int P_20008_HTML_LOGIN = 20008;
	 */
	/**
	 * SDK登录结果 int P_20009_HTML_LOGIN_RESULT = 20009;
	 */
	/**
	 * 通过渠道 SDK登录 int P_20010_CHANNEL_LOGIN = 20010;
	 */
	/** 渠道SDK登录结果 */
	int P_20011_CHANNEL_LOGIN_RESULT  = 20011;
	/**
	 * 修改密码 int P_20012_ALTER_PASSORD = 20012;
	 */
	/**
	 * 修改密码结果 int P_20013_ALTER_PASSORD_RESULT = 20013;
	 */
	/**
	 * 不再提示修改密码 int P_20014_UNRESET_PASSWORD = 20014;
	 */
	/**
	 * 角色修改密码 int P_20015_ROLE_ALTER_PASSORD = 20015;
	 */
	/**
	 * 角色修改密码结果 int P_20016_ROLE_ALTER_PASSORDf_RESULT = 20016;
	 */
	/**
	 * 注册账号 int P_20017_ACCOUNT_REGISTER = 20017;
	 */
	/**
	 * 验证手机是否已经被绑定 int P_20018_BIND_PHONE_CHECK = 20018;
	 */
	/**
	 * 验证手机是否已经被绑定 int P_20019_BIND_PHONE_CHECK_RESULT = 20019;
	 */
	/**
	 * 绑定手机号 int P_20020_BIND_PHONE = 20020;
	 */
	/**
	 * 绑定手机结果 int P_20021_BIND_PHONE_RESULT = 20021;
	 */
	/**
	 * 通过手机重置密码 int P_20022_RESET_PASSWORD = 20022;
	 */
	/**
	 * 重置密码结果 int P_20023_RESET_PASSWORD_RESULT = 20023;
	 */
	/** 验证第三方登录数据 */
	int P_20024_CHANNEL_SDK           = 20024;
	/** 验证第三方登录数据结果 */
	int P_20025_CHANNEL_SDK_RESULT    = 20025;

	/**
	 * 加载游戏服未确认的充值订单 int P_20026_SERVER_CHARGES = 20026;
	 */
	/***/
	int P_20027_SERVER_CHARGES_RESULT = 20027;

	/**
	 * 游戏服务器确认已经记录充值记录 int P_20028_CHARGE_COFIRM = 20028;
	 */

	/**
	 * 充值补点 int P_20029_CHARGE_PATCH = 20029;
	 */
	/** 唤醒服务器 */
	int P_20030_NOTIFY_SERVER         = 20030;
	/**
	 * 更新最后一次进入的游戏服 int P_20031_SERVER_LAST = 20031;
	 */
	/**
	 * 模糊查询账号 int P_20032_SELECT_ACCOUNT = 20032;
	 */
	/**
	 * 返回账号信息 int P_20033_ACCOUNT_LIST = 20033;
	 */
	/**
	 * 获取被冻结的账号信息 int P_20034_SELECT_FREEZE_ACCOUNT = 20034;
	 */
	/**
	 * 冻结账号 int P_20035_FREEZE_FREEZE = 20035;
	 */
	/** 反馈操作结果 */
	int P_20036_REPONSE_RESULT        = 20036;
	/**
	 * 充值账号密码 int P_20037_RESET_PWD = 20037;
	 */
	/**
	 * 冻结设备 int P_20038_FREEZE_DEVICE = 20038;
	 */
	/**
	 * 检查设备是否被冻结 int P_20039_CHECK_FREEZE_DEVICE = 20039;
	 */
	/**
	 * 冻结设备列表 int P_20040_FREEZE_DEVICE_LIST = 20040;
	 */

	/**
	 * 请求冻结设备列表 int P_20041_REQUEST_FREEZE_DEVICE_LIST = 20041;
	 */
	/** 维护消息队列服务 */
	int P_20042_REPAIR_MQ             = 20042;
	/** 取消注册 */
	int P_20043_CANCEL_REGISTER       = 20043;
	/** 诊断消息队列 */
	int P_20044_MQ_DIAGNOS            = 20044;
	/**
	 * 服务器角色数量 int P_20045_SERVER_ROLE_COUNT = 20045;
	 */
	/** 补点信息发往账号服务器 */
	int P_20046_CHARGE_PATCH_ACCOUNT  = 20046;
	/** 向队列服务器发出指令 */
	int P_20047_COMMANDS              = 20047;
	/** 登录服请求公告信息 */
	int P_20048_REQUEST_NOTICE        = 20048;
	/** 发送 */
	int P_20049_REPONSE_NOTICE        = 20049;
	/** 删除登录服公告 */
	int P_20050_DEL_LOGIN_NOTICE      = 20050;
}
