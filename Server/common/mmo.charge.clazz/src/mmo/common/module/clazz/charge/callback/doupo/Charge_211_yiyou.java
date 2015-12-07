package mmo.common.module.clazz.charge.callback.doupo;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;

import org.apache.mina.core.session.IoSession;

public class Charge_211_yiyou extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(request.getParameter("orderid"));
			sb.append(request.getParameter("amount"));
			sb.append(request.getParameter("paytype"));
			sb.append(request.getParameter("result"));
			sb.append(request.getParameter("paytime"));
			sb.append(request.getParameter("paydesc"));
			sb.append(ProjectCofigs.getParameter("yiyou_appsecret"));
			if (!MD5.getHashString(sb.toString()).toLowerCase().equals(request.getParameter("md5String"))) {
				return sendToClient("ERROR_SIGN");
			}
		} catch (Exception e) {
			LoggerError.error("验证yiyou签名时异常", e);
			return sendToClient("ERROR_SIGN");
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

			String customOrder = request.getParameter("orderid");
			int cents = (int) (100 * Float.parseFloat(request.getParameter("amount")));
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = request.getParameter("orderid");
			String channel = "yiyou";
			String channelProxy = request.getParameter("paytype");
			long proxyTime = 0;
			String userId = null;

			if ("1".equalsIgnoreCase(request.getParameter("result"))) {
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
					return sendToClient("ERROR_FAIL");
				}
				default: {
					return sendToClient("ERROR_FAIL");
				}
			}
		} catch (Exception e) {
			LoggerError.error("处理yiyou充值通知异常", e);
		}
		return sendToClient("ERROR_FAIL");
	}

}
