package mmo.common.charge;

public interface ChargeCData {
	interface Record {
		String id           = "id";
		String orderId      = "orderId";
		String gameId       = "gameId";
		String serverId     = "serverId";
		String channelId    = "channelId";
		String accountId    = "accountId";
		String roleId       = "roleId";
		String rolename     = "rolename";
		String money        = "money";
		String ctype        = "ctype";
		String note         = "note";
		String orderform    = "orderform";
		String proxy        = "proxy";
		String proxyChannel = "proxyChannel";
		String proxyTime    = "proxyTime";
		String userid       = "userid";
		String channelSub   = "channelSub";
		String roleLevel    = "roleLevel";
		String goodsId      = "goodsId";
		String goodsName    = "goodsName";
		String goodsCount   = "goodsCount";
		String deviceOS     = "deviceOS";
		String price        = "price";
		String sign         = "sign";
		String deviceImei   = "deviceImei";
	}

	interface Patch {
		String gameid     = "gameid";
		String serverid   = "serverid";
		String roleid     = "roleid";
		String roleName   = "roleName";
		String amount     = "amount";
		String reason     = "reason";
		String ctype      = "ctype";
		String state      = "state";
		String accountId  = "accountId";
		String userid     = "userid";
		String channelId  = "channelId";
		String channelSub = "channelSub";
		String roleLevel  = "roleLevel";
		String goodsId    = "goodsId";
		String goodsName  = "goodsName";
		String goodsCount = "goodsCount";
		String sign       = "sign";
	}

	interface ServerCharges {
		String gameid   = "gameid";
		String serverid = "serverid";
		String sign     = "sign";
	}

	interface ChargeConfirm {
		String result    = "result";
		String friendid  = "friendid";
		String orderid   = "orderid";
		String accountId = "accountId";
		String channelId = "channelId";
		String money     = "money";
		String getmoney  = "getmoney";
		String ctype     = "ctype";
		String state     = "state";
		String GAME_ID   = "gameId";
		String SERVER_ID = "serverid";
		String sign      = "sign";
	}

	interface Orderform {
		String GAME_ID        = "gameid";
		String SERVER_ID      = "serverid";
		String CHANNEL_ID     = "channelid";
		String CHANNEL_SUB    = "channelsub";
		String ACCOUNT_ID     = "accountid";
		String ROLE_ID        = "roleid";
		String ROLE_LEVEL     = "rolelevel";
		String ROLE_NAME      = "rolename";
		String ITEM_ID        = "itemid";
		String ITEM_NAME      = "itemname";
		String ITEM_PRICE     = "itemprice";
		String ITEM_COUNT     = "itemcount";
		String DEVICE_OS      = "deviceos";
		String USER_ID        = "userid";
		String TIME_CREATE    = "timecreate";
		String ORDER_ID       = "orderid";
		String deviceImei     = "deviceImei";
		String customData     = "customData";
		String requestTencent = "requestTencent";
		String ip             = "ip";
	}
}
