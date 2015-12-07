package mmo.common.module.clazz.charge.callback.doupo;

import mmo.common.config.charge.ChargeType;
import mmo.common.http.parameter.HttpParameter;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;

import org.apache.mina.core.session.IoSession;

public class Charge_231_QQOrder extends AChargeContextHandle {

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			HttpResponseMessage message = checkParameters(request);
			if (message != null) {
				return message;
			}
			String chargeTypeNote = request.getParameter("chargeType");
			String customOrder = request.getParameter(HttpParameter.AppStore.order_id);
			int cents = 100 * request.getIntParameter(HttpParameter.AppStore.item_price);
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			String channelOrder = request.getParameter("channelOrder");
			String channel = "qq";
			String channelProxy = request.getParameter("payChannel");
			long proxyTime = request.getLongParameter(HttpParameter.AppStore.time_create);
			String userId = null;
			int orderStatus = handleOrder(customOrder, cents, chargeType, chargeTypeNote, channelOrder, channel, channelProxy, proxyTime, userId);
			switch (orderStatus) {
				case RESULT_1_ORDER_OK: {
					return sendToClient("ok");
				}
				case RESULT_2_ORDER_NOT_EXIST: {
					return sendToClient("order_not_exis");
				}
				case RESULT_3_USER_NOT_MATCH: {
					return sendToClient("FAILURE");
				}
				default: {
					return sendToClient("FAILURE");
				}
			}
		} catch (Exception e) {
			LoggerError.error("处理QQ充值通知异常", e);
		}
		return sendToClient("FAILURE");

	}

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		try {
			long timeCreate = request.getLongParameter(HttpParameter.AppStore.time_create);
			String channelOrder = request.getParameter("channelOrder");
			String orderId = request.getParameter(HttpParameter.AppStore.order_id);
			String sign = MD5.getHashString(timeCreate + "8" + channelOrder + "9" + orderId);
			if (!sign.equals(request.getParameter(HttpParameter.AppStore.sign))) {
				return sendToClient("error_sign");
			}
		} catch (Exception e) {
			LoggerError.error("验证QQOrder签名时异常", e);
			return sendToClient("error_sign");
		}
		return null;

	}

}
