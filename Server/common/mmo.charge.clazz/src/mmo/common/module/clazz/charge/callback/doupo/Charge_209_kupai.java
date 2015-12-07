package mmo.common.module.clazz.charge.callback.doupo;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

import com.iapppay.sign.SignHelper;

public class Charge_209_kupai extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		try {
			if (!SignHelper.verify(request.getParameter("transdata"), request.getParameter("sign"),
			        ProjectCofigs.getParameter("kupai_platformPubKey"))) {
				return sendToClient("FAILURE");
			}
		} catch (Exception e) {
			LoggerError.error("验证酷派签名异常", e);
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
			JSONObject jsonData = JSONObject.fromObject(request.getParameter("transdata"));
			String customOrder = jsonData.getString("cporderid");
			int cents = (int) (jsonData.getDouble("money") * 100);
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = jsonData.getString("transid");
			String channel = "kupai";
			String channelProxy = "kupai";
			long proxyTime = 0;
			String userId = null;
			if (0 == jsonData.getInt("result")) {
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
			LoggerError.error("处理酷派充值通知异常", e);
		}
		return sendToClient("FAILURE");
	}

}
