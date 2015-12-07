package mmo.common.module.clazz.charge.callback.doupo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.DateUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.CharEncoding;
import org.apache.mina.core.session.IoSession;

import com.gionee.pay.util.RSASignature;

public class Charge_208_jinli extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		try {
			StringBuilder signSB = new StringBuilder();
			List<String> keys = new ArrayList<String>(request.getParameterNames());
			Collections.sort(keys);
			for (int ki = 0; ki < keys.size(); ki++) {
				if ("sign".equals(keys.get(ki))) {
					continue;
				}
				if (ki > 0) {
					signSB.append("&");
				}
				signSB.append(keys.get(ki)).append("=").append(request.getParameter(keys.get(ki)));
			}

			if (!RSASignature.doCheck(signSB.toString(), request.getParameter("sign"), ProjectCofigs.getParameter("jinli_publicKey"),
			        CharEncoding.UTF_8)) {
				return sendToClient("fail sign");
			}
		} catch (Exception e) {
			LoggerError.error("生成金立签名时异常", e);
			return sendToClient("fail sign");
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
			String customOrder = request.getParameter("out_order_no");
			int cents = (int) (Float.parseFloat(request.getParameter("deal_price")) * 100);
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			String channelOrder = null;
			String channel = "jinli";
			String channelProxy = request.getParameter("pay_channel");
			long proxyTime = DateUtil.stringToDec(request.getParameter("create_time"), "yyyyMMddHHmmss");
			String userId = null;
			ChargeOrderform orderform = OrderformManager.getInstance().getOrderform(customOrder);
			if (orderform != null) {
				JSONObject jsonOrder = JSONObject.fromObject(orderform.getDeviceOS());
				JSONObject cd = jsonOrder.getJSONObject("channel_data");
				channelOrder = cd.getString("order_no");
			}
			int orderStatus = handleOrder(customOrder, cents, chargeType, StringLib.roleCharge, channelOrder, channel, channelProxy, proxyTime,
			        userId);
			switch (orderStatus) {
				case RESULT_1_ORDER_OK: {
					return sendToClient("success");
				}
				case RESULT_2_ORDER_NOT_EXIST: {
					return sendToClient("fail order not exist");
				}
				case RESULT_3_USER_NOT_MATCH: {
					return sendToClient("fail user id not match");
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
