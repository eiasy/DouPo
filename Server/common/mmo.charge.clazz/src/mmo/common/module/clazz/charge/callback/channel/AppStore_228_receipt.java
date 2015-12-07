package mmo.common.module.clazz.charge.callback.channel;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.http.parameter.HttpParameter;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.run.AddStoreReceiptDBRun;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;

import org.apache.mina.core.session.IoSession;

public class AppStore_228_receipt extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			String receipt = request.getParameter(HttpParameter.AppStore.receipt);
			long time = System.currentTimeMillis();
			int productId = request.getIntParameter(HttpParameter.AppStore.product_id);
			int serverId = request.getIntParameter(HttpParameter.AppStore.server_id);
			String channelId = request.getParameter(HttpParameter.AppStore.channel_id);
			String channelSub = request.getParameter(HttpParameter.AppStore.channel_sub);
			int accountId = request.getIntParameter(HttpParameter.AppStore.account_id);
			String userId = request.getParameter(HttpParameter.AppStore.user_id);
			int roleId = request.getIntParameter(HttpParameter.AppStore.role_id);
			short level = (short) request.getIntParameter(HttpParameter.AppStore.level);
			long ed1 = request.getLongParameter("ed1");
			String roleName = request.getParameter(HttpParameter.AppStore.role_name);
			String deviceOS = request.getParameter(HttpParameter.AppStore.device_os);
			if ("".equals(deviceOS)) {
				deviceOS = "ios";
			}
			String deviceImei = request.getParameter(HttpParameter.AppStore.device_imei);
			String deviceSerial = request.getParameter(HttpParameter.AppStore.device_serial);
			String deviceMac = request.getParameter(HttpParameter.AppStore.device_mac);
			String idfa = request.getParameter(HttpParameter.AppStore.idfa);
			String appProductId = request.getParameter(HttpParameter.AppStore.app_product_id);
			String orderform = request.getParameter("orderform");

			if (receipt != null) {
				try {
					RoleStoreReceipt sr = new RoleStoreReceipt(receipt, productId, serverId, channelId, channelSub, accountId, roleId, level, roleName, deviceOS, userId, System.currentTimeMillis(), deviceImei, deviceSerial, deviceMac, idfa);
					sr.setProid(appProductId);
					sr.setOrderform(orderform);
					sr.setReceiptMd5(MD5.getHashString(receipt));
					LoggerCharge.chargeReceipt(sr);
					ChargeDatabaseHeartbeat.getInstance().execute(new AddStoreReceiptDBRun(sr));
				} catch (Exception e) {
					LoggerError.error("验证AppStore票据出现异常", e);
				}
			}
			return sendToClient("OK");
		} catch (Exception e) {
			LoggerError.error("处理易接充值通知异常", e);
		}
		return sendToClient("FAILURE");
	}
}
