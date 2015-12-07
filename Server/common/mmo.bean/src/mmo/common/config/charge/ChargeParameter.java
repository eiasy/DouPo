package mmo.common.config.charge;

public interface ChargeParameter {
	public static final String sign           = "sign";
	/** 账户类型,参考账户类型表 */
	public static final String accountType    = "accountType";
	/** 用户名 */
	public static final String userName       = "userName";
	/** 用户ip地址 */
	public static final String userIp         = "userIp";
	/** 角色名 */
	public static final String roleName       = "roleName";
	/** 游戏id,平台分配 */
	public static final String gameId         = "gameId";
	/** 游戏服务器id,平台分配 */
	public static final String serverId       = "serverId";
	/** 渠道id,参考市场部渠道编号文档 */
	public static final String channelId      = "channelId";
	/** 充值卡类型,参考卡类型表 */
	public static final String cardType       = "cardType";
	/** 卡号 */
	public static final String cardNumber     = "cardNumber";
	/** 卡密码 */
	public static final String cardPassword   = "cardPassword";
	/** 卡金额,单位:分 */
	public static final String amount         = "amount";
	/** 附加内容,计费通知时会原样返回 */
	public static final String addition       = "addition";
	/** 渠道附加信息 */
	public static final String channelAddition = "channelAddition";
}
