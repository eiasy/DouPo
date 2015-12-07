package mmo.common.http.parameter;

public interface HttpParameter {
	interface AppStore {
		String receipt        = "receipt";
		String product_id     = "product_id";
		String server_id      = "server_id";
		String status         = "status";
		String message        = "message";
		String account_id     = "account_id";
		String user_id        = "user_id";
		String channel_id     = "channel_id";
		String channel_sub    = "channel_sub";
		String belongto       = "belongto";
		String custom_data    = "custom_data";
		String sign           = "sign";
		String role_id        = "role_id";
		String role_name      = "role_name";
		String level          = "level";
		String device_os      = "device_os";
		String device_imei    = "device_imei";
		String device_serial  = "device_serial";
		String device_mac     = "device_mac";
		String idfa           = "idfa";
		String item_id        = "item_id";
		String item_name      = "item_name";
		String item_price     = "item_price";
		String item_count     = "item_count";
		String time_create    = "time_create";
		String order_id       = "order_id";
		String app_product_id = "app_product_id";
	}

	interface GameEvent {
		String app_id            = "app_id";
		String channel_tag       = "channel_tag";
		String idfa              = "idfa";
		String device_mac        = "device_mac";
		String device_udid       = "device_udid";
		String device_imei       = "device_imei";
		String device_serial     = "device_serial";
		String device_ua         = "device_ua";
		String device_os         = "device_os";
		String device_os_version = "device_os_version";
		String phone_code        = "phone_code";
		String event_tag         = "event_tag";
		String desc              = "desc";
		String key_1             = "key_1";
		String value_1           = "value_1";
		String key_2             = "key_2";
		String value_2           = "value_2";
		String key_3             = "key_3";
		String value_3           = "value_3";
		String key_4             = "key_4";
		String value_4           = "value_4";
		String key_5             = "key_5";
		String value_5           = "value_5";
		String user_id           = "user_id";
	}

	interface Account {
		String message         = "message";
		String result          = "result";
		String servers_all     = "servers_all";
		String servers_suggest = "servers_suggest";
		String code_version    = "code_version";
		String servers_history = "servers_history";
		String active_code     = "active_code";
		String phone_type      = "phone_type";
		String imei            = "imei";
		String feature         = "feature";
		String phone_code      = "phone_code";
		String screen_width    = "screen_width";
		String screen_height   = "screen_height";
		String device_os       = "os";
		String os_version      = "os_version";
		String udid            = "udid";
		String mac             = "mac";
		String ua              = "ua";
		String serial_code     = "serial_code";
		String accountId       = "accountId";
		String user_name       = "user_name";
		String loginCount      = "loginCount";
		String lastEnter       = "lastEnter";
		String securityCode    = "security_code";
		String channel_mark    = "channel_mark";
		String channel_id      = "channel_id";
		String channel_sub     = "channel_sub";
		String server_id       = "server_id";
		String client_version  = "client_version";
		String product_id      = "product_id";
		String belongto        = "belongto";
		String login_server    = "login_server";
		String server_version  = "server_version";
		String real_ip         = "real_ip";
		String permit          = "permit";
		String user_id         = "user_id";
		String custom_data     = "custom_data";
	}
}
