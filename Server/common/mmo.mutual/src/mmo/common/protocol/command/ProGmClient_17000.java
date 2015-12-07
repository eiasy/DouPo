package mmo.common.protocol.command;

public interface ProGmClient_17000 {
	/** GM工具注册 */
	int P_17001_REGISTER             = 17001;
	/** 加载服务器列表 */
	int P_17002_APP_LIST             = 17002;
	/** 增开新服 */
	int P_17003_ADD_APP              = 17003;
	/** 更新服务器状态 */
	int P_17004_UPDATE_APP_STATE     = 17004;
	/** 添加系统公告 */
	int P_17005_ADD_SYSTEM_NOTICE    = 17005;
	/** 跑马灯 */
	int P_17006_PAO_MA_DENG_ADD      = 17006;
	/** 更新服务器-全部信息 */
	int P_17007_UPDATE_APP           = 17007;
	/** 请求系统公告列表 */
	int P_17008_SYSTME_NOTICE        = 17008;
	/** 请求跑马灯列表 */
	int P_17009_PAO_MA_DENG          = 17009;
	/** 删除跑马灯 */
	int P_17010_DEL_PAO_MA_DENG      = 17010;
	/** 修改跑马灯 */
	int P_17011_UPDATE_PAO_MA_DENG   = 17011;
	/** 账号管理 冻结 解冻 强制下线 */
	int P_17012_FREEZE_ACCOUNT       = 17012;
	/** 把角色踢下线 */
	int P_17013_KICKOUT_ROLE         = 17013;
	/** 充值补点 */
	int P_17014_CHARGE_PATCH         = 17014;
	/** 重置密码 */
	int P_17015_PWD_RESET            = 17015;
	/** 邮件奖励 */
	int P_17016_EMAIL_AWARD          = 17016;
	/** 禁止发言 */
	int P_17017_FORBID_CHAT          = 17017;
	/** 脱离卡死 */
	int P_17018_ROLE_FREE            = 17018;
	/** 账号信息 */
	int P_17019_ACCOUNT_INFO         = 17019;
	/** 账号下面的角色 */
	int P_17020_ACCOUNT_ROLE         = 17020;
	/** 角色详细 */
	int P_17021_ROLE_DETAIL          = 17021;
	/** 充值开关 */
	int P_17022_CHARGE_SWITCH        = 17022;
	/** 添加一条活动记录 */
	int P_17023_ADD_ACTIVITY         = 17023;
	/** 加载活动列表 */
	int P_17024_ACTIVITY_LIST        = 17024;
	/** 更新活动 */
	int P_17025_UPDATE_ACTIVITY      = 17025;
	/** 游戏开关项 */
	int P_17028_GAME_SWITCH          = 17028;
	/** 添加或更新消息 */
	int P_17029_ADD_UPDATE_MSG       = 17029;
	/** 请求消息 */
	int P_17030_REQUEST_MSG          = 17030;
	/** 请求加载发奖记录列表 */
	int P_17031_AWARD_LIST           = 17031;
	/** 添加一条发奖记录 */
	int P_17032_AWARD_ADD            = 17032;
	/** 更新一条发奖记录 */
	int P_17033_AWARD_UPDATE         = 17033;
	/** 游戏列表 */
	int P_17034_GAME_LIST            = 17034;
	/** 添加一个新的游戏 */
	int P_17035_GAME_ADD             = 17035;
	/** 更新游戏信息 */
	int P_17036_GAME_UPDATE          = 17036;
	/** 冻结角色 */
	int P_17037_FREEZE_ROLE          = 17037;
	/** 修改单个服务器的充值状态 */
	int P_17038_SERVER_CHARGE_STATE  = 17038;
	/** 加载角色列表 */
	int P_17039_GM_LIST              = 17039;
	/** 添加GM账号 */
	int P_17040_GM_ADD               = 17040;
	/** 更新GM账号 */
	int P_17041_GM_UPDATE            = 17041;
	/** 重置GM密码 */
	int P_17042_GM_PWD               = 17042;
	/** 上传数据文件 */
	int P_17043_DATA_UPLOAD          = 17043;
	/** 加载冻结账号 */
	int P_17044_LOAD_FREEZE          = 17044;
	/** 更新角色等级 */
	int P_17045_LEVEL_RESET          = 17045;
	/** 请求排行榜数据 */
	int P_17046_RANK_LIST            = 17046;
	/** 增加礼包 */
	int P_17047_GIFT_ADD             = 17047;
	/** 更新礼包 */
	int P_17048_GIFT_UPDATE          = 17048;
	/** 加载礼包列表 */
	int P_17049_GIFT_LIST            = 17049;
	/** 添加兑换码 */
	int P_17050_EXCHANGE_ADD         = 17050;
	/** 清理过期的兑换码 */
	int P_17051_EXCHANGE_CLEAR       = 17051;
	/** 加载兑换码标签 */
	int P_17052_EXCHANGE_TAG         = 17052;
	/** 推送消息开关 */
	int P_17053_TIP_SWITCH           = 17053;
	/** 维护登录服务器 */
	int P_17054_LOGIN_REPAIR         = 17054;
	/** 加载角色详细信息 */
	int P_17055_ROLE_DETAIL          = 17055;
	/** GM修改角色的数据 */
	int P_17056_GM_UPDATE_ROLE       = 17056;
	/** 批量冻结角色 */
	int P_17057_gmCmds               = 17057;
	/** 冻结设备 */
	int P_17058_FREEZE_DEVICE        = 17058;
	/** 查询设备是否被冻结 */
	int P_17059_CHECK_FREEZE_DEVICE  = 17059;
	/** 加载冻结设备列表 */
	int P_17060_FREEZE_DEVICE_LIST   = 17060;
	/** 请求消息队列列表 */
	int P_17061_REQUEST_MQ_LIST      = 17061;
	/** 维护消息队列 */
	int P_17062_MQ_REPAIR            = 17062;
	/** 诊断消息队列 */
	int P_17063_MQ_DINGAOS           = 17063;
	/** 通知登录服重新加载渠道机型配置 */
	int P_17064_CLIENT_CHANNEL       = 17064;
	/** 加载类库 */
	int P_17065_CLASSLOADER          = 17065;
	/** 登录限制类型 */
	int P_17066_LOGIN_LIMIT          = 17066;
	/** 添加特权账号 */
	int P_17067_SPECIAL_ACCOUNT      = 17067;
	/** 添加活动 */
	int P_17068_ADD_ACTIVITY         = 17068;
	/** 加载游戏活动 */
	int P_17069_GAME_ACTIVITY        = 17069;
	/** 删除游戏活动 */
	int P_17070_DEL_GAME_ACTIVITY    = 17070;
	/** 更新游戏服务的活动 */
	int P_17071_UPDATE_GAME_ACTIVITY = 17071;
	/** 重新加载特权账号 */
	int P_17072_LOAD_ACCOUNT_UNLIMIT = 17072;
	/** 登录服公告 */
	int P_17073_LOGIN_NOTICE         = 17073;
	/** 更新登录服公告 */
	int P_17074_UPDATE_LOGIN_NOTICE  = 17074;
	/** 添加登录服公告 */
	int P_17075_ADD_LOGIN_NOTICE     = 17075;
	/** 删除登录服公告 */
	int P_17076_DEL_LOGIN_NOTICE     = 17076;
	/** 加载测试账号 */
	int P_17077_LOAD_ACCOUNT_TEST    = 17077;
	/** 按类型加载活动 */
	int P_17078_LOAD_ACTIVITY_TYPE   = 17078;
	/** 开启活动 */
	int P_17079_ACTIVITY_OPEN        = 17079;
	/** 更新并入区编号 */
	int P_17080_UPDATE_LINK_ID       = 17080;
	/** 更新开服时间 */
	int P_17081_UPDATE_OPEN_TIME     = 17081;
	/** 加载游戏服的活动信息 */
	int P_17082_LOAD_SERVER_ACTIVITY = 17082;
	/** 编辑游戏服的活动信息 */
	int P_17083_EDIT_SERVER_ACTIVITY = 17083;
	/** 服务器指令 */
	int P_17084_SERVER_CMDS          = 17084;
	/** 加载推送通知 */
	int P_17085_LOAD_PUSH_MESSAGE    = 17085;
	/** 添加推送通知 */
	int P_17086_ADD_PUSH_MESSAGE      = 17086;
	/** 编辑推送通知 */
	int P_17087_UPDATE_PUSH_MESSAGE  = 17087;
	/** 删除推送通知 */
	int P_17088_UPDATE_PUSH_MESSAGE_STATUS  = 17088;
}
