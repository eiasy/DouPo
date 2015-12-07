package mmo.common.module.clazz.charge.callback.doupo;

import java.net.URLDecoder;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;

import org.apache.mina.core.session.IoSession;

public class Charge_227_yijie extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("app=").append(URLDecoder.decode(request.getParameter("app"), "UTF-8"));
			sb.append("&cbi=").append(request.getParameter("cbi"));
			sb.append("&ct=").append(request.getParameter("ct"));
			sb.append("&fee=").append(request.getParameter("fee"));
			sb.append("&pt=").append(request.getParameter("pt"));
			sb.append("&sdk=").append(URLDecoder.decode(request.getParameter("sdk"), "UTF-8"));
			sb.append("&ssid=").append(request.getParameter("ssid"));
			sb.append("&st=").append(request.getParameter("st"));
			sb.append("&tcd=").append(request.getParameter("tcd"));
			sb.append("&uid=").append(request.getParameter("uid"));
			sb.append("&ver=").append(request.getParameter("ver"));
			sb.append(ProjectCofigs.getParameter("yijie_public_key"));

			if (!MD5.getHashString(sb.toString()).equals(request.getParameter("sign"))) {
				return sendToClient("sign not match");
			}
		} catch (Exception e) {
			LoggerError.error("生成易接签名时异常", e);
			return sendToClient("sign exception");
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
			String customOrder = request.getParameter("cbi");
			int cents = getInt(request, "fee");
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = request.getParameter("tcd");
			String channel = "yijie";
			String channelProxy = request.getParameter("sdk");
			long proxyTime = 0;
			String userId = request.getParameter("uid");

			if ("1".equals(request.getParameter("st"))) {
				chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			}
			int orderStatus = handleOrder(customOrder, cents, chargeType, StringLib.roleCharge, channelOrder, channel, channelProxy, proxyTime,
			        userId);
			switch (orderStatus) {
				case RESULT_1_ORDER_OK: {
					return sendToClient("SUCCESS");
				}
				case RESULT_2_ORDER_NOT_EXIST: {
					return sendToClient("order not exist");
				}
				case RESULT_3_USER_NOT_MATCH: {
					return sendToClient("user not match");
				}
				default: {
					return sendToClient("FAILURE");
				}
			}
		} catch (Exception e) {
			LoggerError.error("处理易接充值通知异常", e);
		}
		return sendToClient("FAILURE");
	}
}
