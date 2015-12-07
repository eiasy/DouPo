package mmo.common.account;

public interface HttpCData {
	String CHARGE_URL          = "charge_url";
	String ACCOUNT_URL_NAME    = "account_listener";
	String PUSH_URL_NAME       = "push_listener";
	String MANAGER_KEY         = "manager_key";
	/** 新账号登录(注册并登录) */
	int    LOGIN_1_NEW_ACCOUNT = 1;
	/** 老账号登录 */
	int    LOGIN_2_OLD_ACCOUNT = 2;

	interface QQ {
		String qq_charge_mode       = "qq_charge_mode";
		String qq_check_charge_test = "qq_check_charge_test";
		String wx_check_charge_test = "wx_check_charge_test";
		String qq_check_charge      = "qq_check_charge";
		String wx_check_charge      = "wx_check_charge";

		String qq_cost_test         = "qq_cost_test";
		String wx_cost_test         = "wx_cost_test";
		String qq_cost              = "qq_cost";
		String wx_cost              = "wx_cost";
	}

	interface AccountDoupo {
		int    RT_0_OK               = 0;
		int    RT_1_FAIL             = 1;
		int    RT_2_FAIL_ACTIVE_CODE = 2;
		int    RT_3_NEED_ACTIVE_CODE = 3;
		int    RT_4_WHITE_LIST       = 4;
		String userId                = "user_id";
		String message               = "message";
		String result                = "result";
		String servers_all           = "servers_all";
		String servers_suggest       = "servers_suggest";
		String servers_history       = "servers_history";
		String active_code           = "active_code";
		String phone_type            = "phone_type";
		String imei                  = "imei";
		String phone_code            = "phone_code";
		String screen_width          = "screen_width";
		String screen_hight          = "screen_height";
		String device_os             = "os";
		String os_version            = "os_version";
		String udid                  = "udid";
		String mac                   = "mac";
		String ua                    = "ua";
		String serial_code           = "serial_code";
		String accountId             = "accountId";
		String username              = "username";
		String loginCount            = "loginCount";
		String lastEnter             = "lastEnter";
		String securityCode          = "security_code";
		String channel_mark          = "channel_mark";
		String channel_sub           = "channel_sub";
		String server_id             = "server_id";
		String client_version        = "client_version";
		String product_id            = "product_id";

	}

	interface A20001 {
		int    RT_1_OK            = 1;
		int    RT_2_NO            = 2;
		int    RT_3_ACCOUNT       = 3;
		int    RT_4_DEVICE_FREEZE = 4;
		int    RT_5_EXIST         = 5;
		String sessionId          = "sessionId";
		String connectId          = "connectId";
		String channelId          = "channelId";
		String belongto           = "belongto";
		String accountId          = "accountId";
		String userid             = "userid";
		String username           = "username";
		String password           = "password";
		String newpassword        = "newpassword";
		String channelSub         = "channelSub";
		String clientVersion      = "clientVersion";
		String productId          = "product_id";
		String imei               = "imei";
		String feature            = "feature";
		String loginServer        = "loginServer";
		String serverVersion      = "serverVersion";
		String deviceOS           = "deviceOS";
		String osVersion          = "osVersion";
		String screenWidth        = "screenWidth";
		String screenHeight       = "screenHeight";
		String deviceUdid         = "deviceUdid";
		String deviceMac          = "deviceMac";
		String deviceUa           = "deviceUa";
		String phoneType          = "phoneType";
		String remoteAddress      = "remoteAddress";
		String real_ip            = "X-Real-IP";
		String customData         = "customData";
		String clientCode         = "clientCode";
		String status             = "status";
		String time               = "time";
		String message            = "message";
		String result             = "result";
		String phone              = "phone";
		String permit             = "permit";
		String sex                = "sex";
		String notifyUri          = "notifyUri";
		String dbstatus           = "dbstatus";
		String money              = "money";
		String accountFreeze      = "accountFreeze";
		String deviceFreeze       = "deviceFreeze";
		String newAccount         = "newAccount";
		String loginCount         = "loginCount";
		String pwdStatus          = "pwdStatus";
		String securityCode       = "securityCode";
		String roleCount          = "roleCount";
		String lastEnter          = "lastEnter";
		String registerFrom       = "registerFrom";
		String serverId           = "serverId";
		String callback           = "callback";
		String cdata              = "cdata";
		String days               = "days";
		String token              = "token";
		String roleid             = "roleid";
	}

	interface Receipt {
		int    RT_1_OK       = 1;
		int    RT_2_NO       = 2;
		String receipt       = "receipt";
		String productId     = "productId";
		String serverId      = "serverId";

		String status        = "status";
		String message       = "message";
		String accountCenter = "accountCenter";
		String accountId     = "accountId";
		String userid        = "userid";
		String loginServer   = "loginServer";
		String clientVersion = "clientVersion";
		String timeRegister  = "timeRegister";
		String channelSub    = "channelSub";
		String belongto      = "belongto";
		String customData    = "customData";
		String charges       = "charges";
		String sign          = "sign";
	}

	interface ContextsAccount {
		String C_20001 = "account/20001";
		String C_20002 = "account/20002";
		String C_20003 = "account/20003";
		String C_20004 = "account/20004";
		String C_20005 = "account/20005";
		String C_20006 = "account/20006";
		String C_20007 = "account/20007";
		String C_20008 = "account/20008";
		String C_20009 = "account/20009";
		String C_20010 = "account/20010";
		String C_20011 = "account/20011";
		String C_20012 = "account/20012";
		String C_20013 = "account/20013";
		String C_20014 = "account/20014";
		String C_20015 = "account/20015";
		String C_20016 = "account/20016";
		String C_20017 = "account/20017";
		String C_20018 = "account/20018";
		String C_20019 = "account/20019";
		String C_20020 = "account/20020";
		String C_20021 = "account/20021";
		String C_20022 = "account/20022";
		String C_20023 = "account/20023";
		String C_20024 = "account/20024";
		String C_20025 = "account/20025";
		String C_20026 = "account/20026";
		String C_20031 = "account/20031";
	}

	interface ContextsManager {
		String C_10001 = "manager/10001";
		String C_10002 = "manager/10002";
	}

	interface ContextsPush {
		String PUSH_MESSAGE = "push/101";
	}

	interface ContextsCharge {
		String classload      = "/charges/classload";
		String chargesConfirm = "/charges/confirm";
		String loadconfigs    = "/charges/loadconfigs"; ;
		String tencentCharge  = "/charges/c106";
	}

	interface PushMessage {
		String id      = "id";
		String cdata   = "cdata";
		String channel = "channel";
		String target  = "target";
		String title   = "title";
		String content = "content";
	}

	interface BIDataField {
		String eventType  = "eventType";

		String eventMark  = "eventMark";
		String appName    = "appName";
		String gameServer = "gameServer";
		String platform   = "platform";
		String deviceMark = "deviceMark";
		String accountId  = "accountId";
		String channelId  = "channelId";
		String channelSub = "channelSub";
		String roleId     = "roleId";
		String roleLevel  = "roleLevel";
		String mission    = "mission";
		String duplicate  = "duplicate";
		String itemName   = "itemName";
		String moneyType  = "moneyType";
		String amount     = "amount";
		String orderform  = "orderform";
		String gameMoney  = "gameMoney";
		String count      = "count";
		String priceItem  = "priceItem";
		String price      = "price";
		String descript   = "descript";
		String scene      = "scene";
		String onlineTime = "onlineTime";
		String cdata      = "cdata";
	}

	interface TencentCharge {
		String resultCode   = "resultCode";
		String payChannel   = "payChannel";
		String payState     = "payState";
		String provideState = "provideState";
		String saveNum      = "saveNum";
		String extendInfo   = "extendInfo";
		String orderId      = "orderId";
		String sign         = "sign";
	}

	interface BIEvent {
		String accountCreate           = "1";
		String accountLogin            = "2";
		String customEvent             = "3";
		String deviceOpen              = "4";
		String roleBuyItem             = "5";
		String roleChargeRequest       = "6";
		String roleChargeSuccess       = "7";
		String roleChargeFail          = "8";
		String roleCostItem            = "9";
		String roleDuplicateBegin      = "10";
		String roleDuplicatePass       = "11";
		String roleDuplicateFail       = "12";
		String roleMissionBegin        = "13";
		String roleMissionCommit       = "14";
		String roleMissionFail         = "15";
		String roleEnterGame           = "16";
		String roleExitGame            = "17";
		String roleGetItem             = "18";
		String roleUpgrade             = "19";
		String switchServer            = "20";

		int    eventAccountCreate      = 1;
		int    eventAccountLogin       = 2;
		int    eventCustomEvent        = 3;
		int    eventDeviceOpen         = 4;
		int    eventRoleBuyItem        = 5;
		int    eventRoleChargeRequest  = 6;
		int    eventRoleChargeSuccess  = 7;
		int    eventRoleChargeFail     = 8;
		int    eventRoleCostItem       = 9;
		int    eventRoleDuplicateBegin = 10;
		int    eventRoleDuplicatePass  = 11;
		int    eventRoleDuplicateFail  = 12;
		int    eventRoleMissionBegin   = 13;
		int    eventRoleMissionCommit  = 14;
		int    eventRoleMissionFail    = 15;
		int    eventRoleEnterGame      = 16;
		int    eventRoleExitGame       = 17;
		int    eventRoleGetItem        = 18;
		int    eventRoleUpgrade        = 19;
		int    eventSwitchServer       = 20;
	}
}
