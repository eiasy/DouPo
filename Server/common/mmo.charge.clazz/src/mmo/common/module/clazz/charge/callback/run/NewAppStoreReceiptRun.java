package mmo.common.module.clazz.charge.callback.run;

import java.util.HashMap;
import java.util.Map;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.http.parameter.HttpParameter;
import mmo.common.module.service.charge.thread.AppStoreHeartbeat;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.IPushStoreOrder;
import mmo.common.module.service.charge.thread.run.UpdateStoreReceiptDBRun;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import mmo.tools.util.MD5;

public class NewAppStoreReceiptRun implements IPushStoreOrder {

	private final static String STATUS_OK = "ok";

	private RoleStoreReceipt receipt;

	public void setReceipt(RoleStoreReceipt receipt) {
		this.receipt = receipt;
	}

	public NewAppStoreReceiptRun() {

	}

	public NewAppStoreReceiptRun(RoleStoreReceipt receipt) {
		super();
		this.receipt = receipt;
	}

	@Override
	public void run() {
		try {
			Map<String, String> parameter = new HashMap<String, String>();
			parameter.put(HttpParameter.AppStore.product_id, receipt.getGameId() + "");
			parameter.put(HttpParameter.AppStore.server_id, receipt.getServerId() + "");
			parameter.put(HttpParameter.AppStore.channel_id, receipt.getChannelId() + "");
			parameter.put(HttpParameter.AppStore.channel_sub, receipt.getChannelSub());
			parameter.put(HttpParameter.AppStore.account_id, receipt.getAccountId() + "");
			parameter.put(HttpParameter.AppStore.role_id, receipt.getRoleId() + "");
			parameter.put(HttpParameter.AppStore.level, receipt.getLevel() + "");
			parameter.put(HttpParameter.AppStore.role_name, receipt.getRoleName());
			parameter.put(HttpParameter.AppStore.item_id, receipt.getGoodsId() + "");
			parameter.put(HttpParameter.AppStore.item_name, receipt.getGoodsName());
			parameter.put(HttpParameter.AppStore.item_price, receipt.getPrice() + "");
			parameter.put(HttpParameter.AppStore.item_count, receipt.getCount() + "");
			parameter.put(HttpParameter.AppStore.device_os, receipt.getDeviceOS());
			parameter.put(HttpParameter.AppStore.user_id, receipt.getUserId());
			parameter.put(HttpParameter.AppStore.time_create, receipt.getTimeCreate() + "");
			parameter.put(HttpParameter.AppStore.device_imei, receipt.getDeviceImei());
			parameter.put(HttpParameter.AppStore.order_id, receipt.getOrderform());
			parameter.put("chargeType", receipt.getChargeType());
			if (receipt.getReceiptMd5() != null) {
				parameter.put("channelOrder", receipt.getReceiptMd5());
			} else {
				parameter.put("channelOrder", MD5.getHashString(receipt.getReceipt()));
			}
			parameter.put(HttpParameter.AppStore.sign, receipt.getSign());
			parameter.put(HttpParameter.AppStore.device_mac, receipt.getDeviceMac());
			parameter.put(HttpParameter.AppStore.device_serial, receipt.getDeviceSerial());
			parameter.put(HttpParameter.AppStore.idfa, receipt.getIdfa());
			String result = HttpsUtil.request(ProjectCofigs.getParameter("app_store_order"), HttpsUtil.httpBuildQuery(parameter));
			if (STATUS_OK.equalsIgnoreCase(result)) {
				receipt.setStatus(RoleStoreReceipt.STAUS_2_CHARGED);
				ChargeDatabaseHeartbeat.getInstance().execute(new UpdateStoreReceiptDBRun(receipt));
			} else if (result.startsWith("order_not_exis")) {
				receipt.setStatus(404);
				ChargeDatabaseHeartbeat.getInstance().execute(new UpdateStoreReceiptDBRun(receipt));
			} else if (result.startsWith("error_sign")) {
				receipt.setStatus(405);
				ChargeDatabaseHeartbeat.getInstance().execute(new UpdateStoreReceiptDBRun(receipt));
			} else {
				AppStoreHeartbeat.getInstance().execute(new ReceiptEnterQueueRne(receipt));
			}
		} catch (Exception e) {
			LoggerError.error("请求订单号异常", e);
			AppStoreHeartbeat.getInstance().execute(new ReceiptEnterQueueRne(receipt));
		}
	}

}
