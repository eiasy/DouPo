package mmo.common.module.clazz.charge.callback.doupo;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.encrypt.Base64;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class Charge_206_baidu extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		try {
			StringBuilder sb = new StringBuilder();
			// MD5(AppID+OrderSerial+CooperatorOrderSerial+Content+SecretKey)
			sb.append(request.getParameter("AppID")).append(request.getParameter("OrderSerial"))
			        .append(request.getParameter("CooperatorOrderSerial")).append(request.getParameter("Content"))
			        .append(ProjectCofigs.getParameter("baidu_appsecret"));
			if (!MD5.getHashString(sb.toString()).equals(request.getParameter("Sign"))) {
				return sendToClient(failmsg(request, "2", "签名不一致"));
			}
		} catch (Exception e) {
			LoggerError.error("验证百度签名时异常", e);
			return sendToClient(failmsg(request, "3", "参数验证异常"));
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
			JSONObject contentJosn = JSONObject.fromObject(Base64.decode(request.getParameter("Content")));
			String customOrder = request.getParameter("CooperatorOrderSerial");
			int cents = (int) (100 * Double.parseDouble(contentJosn.getString("OrderMoney")));
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = request.getParameter("OrderSerial");
			String channel = "baidu";
			String channelProxy = channel;
			long proxyTime = 0;
			String userId = contentJosn.getString("UID");

			if ("1".equalsIgnoreCase(contentJosn.getString("OrderStatus"))) {
				chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			}
			int orderStatus = handleOrder(customOrder, cents, chargeType, StringLib.roleCharge, channelOrder, channel, channelProxy, proxyTime,
			        userId);

			switch (orderStatus) {
				case RESULT_1_ORDER_OK: {
					return sendToClient(failmsg(request, "1", "成功"));
				}
				case RESULT_2_ORDER_NOT_EXIST: {
					return sendToClient(failmsg(request, "4", "订单号不存在"));
				}
				case RESULT_3_USER_NOT_MATCH: {
					return sendToClient(failmsg(request, "5", "用户不匹配"));
				}
				default: {
					return sendToClient(failmsg(request, "6", "充值发生异常"));
				}
			}
		} catch (Exception e) {
			LoggerError.error("处理百度充值通知异常", e);
		}
		return sendToClient(failmsg(request, "6", "充值发生异常"));
	}

	/**
	 * 组织返回信息
	 * 
	 * @author mp
	 * @date 2015-7-17 上午10:25:06
	 * @param request
	 * @param resultCode
	 * @param resultMsg
	 * @return
	 * @Description
	 */
	private String failmsg(HttpRequestMessage request, String resultCode, String resultMsg) {
		JSONObject retMap = new JSONObject();
		retMap.put("AppID", request.getParameter("AppID"));
		retMap.put("ResultCode", resultCode);
		retMap.put("ResultMsg", resultMsg);
		retMap.put("Sign", MD5.getHashString(request.getParameter("AppID") + resultCode + ProjectCofigs.getParameter("baidu_appsecret")));// MD5(AppID+ResultCode+SecretKey)
		return retMap.toString();
	}

}
