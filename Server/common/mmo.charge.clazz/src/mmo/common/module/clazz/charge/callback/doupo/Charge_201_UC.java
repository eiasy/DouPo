package mmo.common.module.clazz.charge.callback.doupo;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class Charge_201_UC extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		try {
			JSONObject root = JSONObject.fromObject(request.getParameterNames().get(0));
			JSONObject josn = root.getJSONObject("data");
			StringBuilder sb = new StringBuilder();
			sb.append("accountId=").append(josn.getString("accountId"));
			sb.append("amount=").append(josn.getString("amount"));
			sb.append("callbackInfo=").append(josn.getString("callbackInfo"));
			sb.append("cpOrderId=").append(josn.getString("cpOrderId"));
			sb.append("creator=").append(josn.getString("creator"));
			sb.append("failedDesc=").append(josn.getString("failedDesc"));
			sb.append("gameId=").append(josn.getString("gameId"));
			sb.append("orderId=").append(josn.getString("orderId"));
			sb.append("orderStatus=").append(josn.getString("orderStatus"));
			sb.append("payWay=").append(josn.getString("payWay"));
			sb.append(ProjectCofigs.getParameter("uc_apiKey"));
			if (!MD5.getHashString(sb.toString()).equals(root.getString("sign"))) {
				return sendToClient("FAILURE");
			}
		} catch (Exception e) {
			LoggerError.error("验证UC签名时异常", e);
			return sendToClient("FAILURE");
		}
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			HttpResponseMessage message = checkParameters(request);
			if (message != null) {
				return message;
			}
			JSONObject josn = JSONObject.fromObject(request.getParameterNames().get(0)).getJSONObject("data");

			String customOrder = josn.getString("cpOrderId");
			int cents = (int) (100 * Float.parseFloat(josn.getString("amount")));
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = josn.getString("orderId");
			String channel = "uc";
			String channelProxy = josn.getString("payWay");
			long proxyTime = 0;
			String userId = josn.getString("accountId");

			if ("s".equalsIgnoreCase(josn.getString("orderStatus"))) {
				chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			}
			int orderStatus = handleOrder(customOrder, cents, chargeType, StringLib.roleCharge, channelOrder, channel, channelProxy, proxyTime,
			        userId);
			switch (orderStatus) {
				case RESULT_1_ORDER_OK: {
					return sendToClient("SUCCESS");
				}
				case RESULT_2_ORDER_NOT_EXIST: {
					return sendToClient("FAILURE");
				}
				case RESULT_3_USER_NOT_MATCH: {
					return sendToClient("FAILURE");
				}
				default: {
					return sendToClient("FAILURE");
				}
			}
		} catch (Exception e) {
			LoggerError.error("处理UC充值通知异常", e);
		}
		return sendToClient("FAILURE");
	}

}
