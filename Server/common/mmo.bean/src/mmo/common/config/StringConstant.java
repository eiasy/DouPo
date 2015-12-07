package mmo.common.config;

public interface StringConstant {
	String logDivide         = "|";                 // ##
	String logUnknown        = "未知";
	String logUser           = "用户";
	String logSystem         = "系统";
	String logRegister       = "注册";
	String logLogin          = "登录";
	String logSuccess        = "成功";
	String logFail           = "失败";
	String logOnline         = "上线";
	String logOffline        = "下线";
	String logKickOffline    = "踢下线";
	String logRoleCreate     = "创建";
	String logRoleUpgrade    = "RoleUpgrade";
	String logVipUpgrade     = "VipUpgrade";
	String logRoleDelete     = "删除";
	String logFailSecurity   = "用户未通过验证";
	String logFailIPLimit    = "同一个IP注册账号达到当天的限制值";
	String logFailLength     = "长度不满足要求";
	String logFailNum        = "以数字开头";
	String logFailLaw        = "非法";
	String logFailPWD        = "密码长度非法";
	String logRepeat         = "已经被占用";
	String logFailPassword   = "账号密码不匹配";
	String logFailRoleState  = "角色状态异常";
	String colon             = "|";                 // :
	String logFailEnter      = "角色已经进入游戏世界";
	String logRelogin        = "账号已经在其他地方登录";
	String logExchangeCodeOK = "验证兑换码成功";
	String logExchangeCodeNG = "验证兑换码失败";
}
