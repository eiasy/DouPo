package mmo.common.module.clazz.charge.callback.channel;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;
import mmo.common.http.parameter.HttpParameter;
import mmo.common.module.clazz.charge.callback.ChargeSDKCallback;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.service.OrderformService;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;

public class AppStore_229_order {

	public HttpResponseMessage callback(HttpRequestMessage request, ChargeSDKCallback manager) {
		StringBuilder sb = new StringBuilder();
		int gameId = string2int(request.getParameter(HttpParameter.AppStore.product_id));
		checkParameter(gameId, HttpParameter.AppStore.product_id, sb);
		int serverid = string2int(request.getParameter(HttpParameter.AppStore.server_id));
		checkParameter(serverid, HttpParameter.AppStore.server_id, sb);
		String channelid = request.getParameter(HttpParameter.AppStore.channel_id);
		checkParameter(channelid, HttpParameter.AppStore.channel_id, sb);
		String channelSub = request.getParameter(HttpParameter.AppStore.channel_sub);
		checkParameter(channelSub, HttpParameter.AppStore.channel_sub, sb);
		int accountId = string2int(request.getParameter(HttpParameter.AppStore.account_id));
		checkParameter(accountId, HttpParameter.AppStore.account_id, sb);
		int roleId = string2int(request.getParameter(HttpParameter.AppStore.role_id));
		checkParameter(roleId, HttpParameter.AppStore.role_id, sb);
		short roleLevel = (short) string2int(request.getParameter(HttpParameter.AppStore.level));
		checkParameter(roleLevel, HttpParameter.AppStore.level, sb);
		String roleName = request.getParameter(HttpParameter.AppStore.role_name);
		checkParameter(roleName, HttpParameter.AppStore.role_name, sb);
		int itemId = string2int(request.getParameter(HttpParameter.AppStore.item_id));
		checkParameter(itemId, HttpParameter.AppStore.item_id, sb);
		String itemName = request.getParameter(HttpParameter.AppStore.item_name);
		checkParameter(itemName, HttpParameter.AppStore.item_name, sb);
		int itemPrice = string2int(request.getParameter(HttpParameter.AppStore.item_price));
		checkParameter(itemPrice, HttpParameter.AppStore.item_price, sb);
		int itemCount = string2int(request.getParameter(HttpParameter.AppStore.item_count));
		checkParameter(itemCount, HttpParameter.AppStore.item_count, sb);
		String deviceOS = request.getParameter(HttpParameter.AppStore.device_os);
		checkParameter(deviceOS, HttpParameter.AppStore.device_os, sb);
		String userId = request.getParameter(HttpParameter.AppStore.user_id);
		checkParameter(userId, HttpParameter.AppStore.user_id, sb);
		long timeCreate = string2long(request.getParameter(HttpParameter.AppStore.time_create));
		checkParameter(timeCreate, HttpParameter.AppStore.time_create, sb);
		String channelOrder = request.getParameter("channelOrder");

		String deviceImei = request.getParameter(HttpParameter.AppStore.device_imei);
		checkParameter(deviceImei, HttpParameter.AppStore.device_imei, sb);

		String orderId = request.getParameter(HttpParameter.AppStore.order_id);

		StringBuilder sbOrder = new StringBuilder();
		sbOrder.append(gameId);
		sbOrder.append("-").append(serverid);
		sbOrder.append("+").append(channelid);
		sbOrder.append("-").append(channelSub);
		sbOrder.append("+").append(accountId);
		sbOrder.append("-").append(roleId);
		sbOrder.append("+").append(roleLevel);
		sbOrder.append("-").append(roleName);
		sbOrder.append("+").append(itemId);
		sbOrder.append("-").append(itemName);
		sbOrder.append("+").append(itemPrice);
		sbOrder.append("-").append(itemCount);
		sbOrder.append("+").append(deviceOS);
		sbOrder.append("-").append(userId);
		sbOrder.append("+").append(timeCreate);
		sbOrder.append("-").append(deviceImei);
		JSONObject json = new JSONObject();
		json.put("os", deviceOS);
		String newOrder = MD5.getHashString(sbOrder.toString());
		if (newOrder.equals(request.getParameter(HttpParameter.AppStore.sign))) {
			ChargeOrderform orderform = OrderformManager.getInstance().getOrderform(orderId);
			if (orderform == null) {
				return sendToClient("order_not_exist#" + orderId);
			}

			if (orderform.isHadled() && OrderformService.getInstance().validateOrderform(orderform.getOrderform())) {
				return sendToClient("ok");
			}

			orderform.setDeviceSerial(request.getParameter(HttpParameter.AppStore.device_serial));
			orderform.setDeviceMac(request.getParameter(HttpParameter.AppStore.device_mac));
			orderform.setIdfa(request.getParameter(HttpParameter.AppStore.idfa));

			LoggerCharge.chargeOrder("success_appstore", sb.toString(), orderform.getOrderform());
			try {
				ChargeAppStore chargeAppStore = (ChargeAppStore) manager.getClass("mmo.common.module.clazz.charge.callback.channel.ChargeAppStore").newInstance();
				chargeAppStore.setOrderform(orderform, channelOrder);
				chargeAppStore.setChargeType(request.getParameter("chargeType"));
				ChargeDatabaseHeartbeat.getInstance().execute(chargeAppStore);
			} catch (Exception e) {
				LoggerError.error("生成AppStore充值记录失败", e);
			}

			return sendToClient("ok");
		} else {
			return sendToClient("error_sign");
		}
	}

	private int string2int(String source) {
		if (source == null || source.trim().length() < 1) {
			return -1;
		}
		try {
			return Integer.parseInt(source.trim());
		} catch (Exception e) {
			return -1;
		}
	}

	private long string2long(String source) {
		if (source == null || source.trim().length() < 1) {
			return -1;
		}
		try {
			return Long.parseLong(source.trim());
		} catch (Exception e) {
			return -1;
		}
	}

	private void checkParameter(int value, String parameter, StringBuilder result) {
		if (result.length() > 0) {
			result.append("-");
		}
		result.append(value);
	}

	private void checkParameter(long value, String parameter, StringBuilder result) {
		if (result.length() > 0) {
			result.append("-");
		}
		result.append(value);
	}

	private void checkParameter(String value, String parameter, StringBuilder result) {
		if (result.length() > 0) {
			result.append("-");
		}
		result.append(value);
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content) {
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain;charset=utf-8");
		response.appendBody(content);
		return response;
	}

	public void printParameter(HttpRequestMessage request) {
		List<String> names = request.getParameterNames();
		HashMap<String, String> params = new HashMap<String, String>();
		for (String k : names) {
			params.put(k, request.getParameter(k));
			try {
				LoggerError.warn("k=" + k + ", v=" + URLDecoder.decode(request.getParameter(k), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				LoggerError.error("解码异常", e);
			}
		}
	}
}
