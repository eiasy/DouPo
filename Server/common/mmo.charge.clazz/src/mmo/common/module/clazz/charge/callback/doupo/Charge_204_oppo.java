package mmo.common.module.clazz.charge.callback.doupo;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;

import org.apache.commons.codec.binary.Base64;
import org.apache.mina.core.session.IoSession;

public class Charge_204_oppo extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		StringBuilder sb = new StringBuilder();
		sb.append("notifyId=").append(request.getParameter("notifyId"));
		sb.append("&partnerOrder=").append(request.getParameter("partnerOrder"));
		sb.append("&productName=").append(request.getParameter("productName"));
		sb.append("&productDesc=").append(request.getParameter("productDesc"));
		sb.append("&price=").append(request.getParameter("price"));
		sb.append("&count=").append(request.getParameter("count"));
		sb.append("&attach=").append(request.getParameter("attach"));
		System.out.println("sb.toString()=" + sb.toString());
		try {
			if (!doCheck(sb.toString(), request.getParameter("sign"), ProjectCofigs.getParameter("opp_public_key"))) {
				return sendToClient("result=FAIL&resultMsg=SIGN　NOT MATCH");
			}
		} catch (Exception e) {
			LoggerError.error("验证Oppo签名异常", e);
			return sendToClient("result=FAIL&resultMsg=SIGN　EXCEPTION");
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

			String customOrder = request.getParameter("partnerOrder");
			int cents = Integer.parseInt(request.getParameter("price"));
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = request.getParameter("notifyId");
			String channel = "oppo";
			String channelProxy = "oppo";
			long proxyTime = 0;
			String userId = null;

			chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			int orderStatus = handleOrder(customOrder, cents, chargeType, StringLib.roleCharge, channelOrder, channel, channelProxy, proxyTime,
			        userId);

			switch (orderStatus) {
				case RESULT_1_ORDER_OK: {
					return sendToClient("result=OK&resultMsg=OK");
				}
				case RESULT_2_ORDER_NOT_EXIST: {
					return sendToClient("result=FAIL&resultMsg=Order not exist");
				}
				case RESULT_3_USER_NOT_MATCH: {
					return sendToClient("result=FAIL&resultMsg=Order not match");
				}
				default: {
					return sendToClient("result=FAIL&resultMsg=unkonw");
				}
			}
		} catch (Exception e) {
			LoggerError.error("处理OPPO充值通知异常", e);
		}
		return sendToClient("result=FAIL&resultMsg=exception");
	}

	public boolean doCheck(String content, String sign, String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64.decodeBase64(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		java.security.Signature signature = java.security.Signature.getInstance("SHA1WithRSA");

		signature.initVerify(pubKey);
		signature.update(content.getBytes("UTF-8"));
		System.out.println(Base64.decodeBase64(sign).length);
		boolean bverify = signature.verify(Base64.decodeBase64(sign));
		System.out.println("bverify="+bverify);
		return bverify;
	}
}
