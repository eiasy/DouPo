package mmo.common.module.clazz.charge.callback.doupo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class Charge_202_360 extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		List<String> names = new ArrayList<String>(request.getParameterNames());
		Collections.sort(names);
		StringBuilder sb = new StringBuilder();
		for (int ni = 0; ni < names.size(); ni++) {
			if ("sign".equals(names.get(ni)) || "sign_return".equals(names.get(ni))) {
				continue;
			}
			if (ni > 0) {
				sb.append('#');
			}
			sb.append(request.getParameter(names.get(ni)));
		}
		sb.append('#').append(ProjectCofigs.getParameter("360_appsecret"));
		if (!MD5.getHashString(sb.toString()).toLowerCase().equals(request.getParameter("sign"))) {
			return sendToClient("sign not match");
		}
		return null;
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		try {
			HttpResponseMessage message = checkParameters(request);
			if (message != null) {
				return message;
			}
			String customOrder = request.getParameter("app_order_id");
			int cents = getInt(request, "amount");
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = request.getParameter("order_id");
			String channel = "360";
			String channelProxy = "360";
			long proxyTime = System.currentTimeMillis();
			String userId = request.getParameter("user_id");

			if ("success".equalsIgnoreCase(request.getParameter("gateway_flag"))) {
				chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			}
			int orderStatus = handleOrder(customOrder, cents, chargeType, StringLib.roleCharge, channelOrder, channel, channelProxy, proxyTime,
			        userId);
			switch (orderStatus) {
				case RESULT_1_ORDER_OK: {
					return sendToClient("ok");
				}
				case RESULT_2_ORDER_NOT_EXIST: {
					return sendToClient("ORDER_NOT_EXIST");
				}
				case RESULT_3_USER_NOT_MATCH: {
					return sendToClient("USER_NOT_MATCH");
				}
				default: {
					return sendToClient("FAILURE");
				}
			}
		} catch (Exception e) {
			LoggerError.error("处理360充值通知异常", e);
		}
		return sendToClient("FAILURE");
	}

}
