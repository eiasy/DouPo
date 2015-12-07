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

import com.lenovo.pay.sign.CpTransSyncSignValid;

public class Charge_210_lianxiang extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		try {
//			LoggerError.error("---- insert method -----");
			String sign = request.getParameter("sign");
			String transdata = request.getParameter("transdata");
			String appKey = ProjectCofigs.getParameter("lianxiang_key");
			
//			LoggerError.error("sign=" + sign + " transdata= " + transdata);
			
			boolean isOk = CpTransSyncSignValid.validSign(transdata, sign, appKey);
			
//			LoggerError.error("isOK= " + isOk);
			
			if (!isOk) {
				return sendToClient("FAILURE");
			}
		} catch (Exception e) {
			LoggerError.error("验证联想签名时异常", e);
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
			JSONObject josn = JSONObject.fromObject(request.getParameter("transdata"));
			
			String customOrder = josn.getString("exorderno");
//			LoggerError.error("customOrder= " + customOrder);
			int cents = Integer.parseInt(josn.getString("money"));//本次交易的金额，单位：分
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = josn.getString("transid");
			String channel = "lianxiang";
			String channelProxy = josn.getString("paytype");
			long proxyTime = 0;
			String userId = null;

//			LoggerError.error("result= " + josn.getString("result"));
			
			if ("0".equalsIgnoreCase(josn.getString("result"))) {
				chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			}
			int orderStatus = handleOrder(customOrder, cents, chargeType, StringLib.roleCharge, channelOrder, channel, channelProxy, proxyTime, userId);
//			LoggerError.error("orderStatus="+orderStatus);
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
			LoggerError.error("处理联想充值通知异常", e);
		}
		return sendToClient("FAILURE");
	}

}
