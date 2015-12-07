package mmo.common.module.clazz.charge.callback.doupo.order;

import java.util.Date;

import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.thread.OrderformHeartbeat;
import mmo.common.module.service.charge.thread.run.AddOrderformRun;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.runnable.RequestHttpRun2;
import mmo.tools.util.DateUtil;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

import com.gionee.pay.util.RSASignature;

public class RequestJinLiRun extends RequestHttpRun2 {
	private IoSession       session;
	private ChargeOrderform orderform;
	private String          orderInfo;

	public RequestJinLiRun() {

	}

	public void setData(IoSession session, ChargeOrderform orderform, String orderInfo, String userId, String dealPrice, String deliver_type,
	        String itemName, String totalPrice) {
		this.session = session;
		this.orderform = orderform;
		this.orderInfo = orderInfo;

		String time = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");

		StringBuilder signSB = new StringBuilder();
		signSB.append(ProjectCofigs.getParameter("jinli_apiKey"));
		signSB.append(dealPrice);
		signSB.append(deliver_type);
		signSB.append(orderform.getOrderform());
		signSB.append(itemName);
		signSB.append(time);
		signSB.append(totalPrice);

		JSONObject parms = new JSONObject();
		parms.put("player_id", userId);
		parms.put("api_key", ProjectCofigs.getParameter("jinli_apiKey"));
		parms.put("deal_price", dealPrice);
		parms.put("deliver_type", deliver_type);
		parms.put("out_order_no", orderform.getOrderform());
		parms.put("subject", itemName);
		parms.put("submit_time", time);
		parms.put("total_fee", totalPrice);
		try {
			parms.put("sign", RSASignature.sign(signSB.toString(), ProjectCofigs.getParameter("jinli_privateKey"), "UTF-8"));
		} catch (Exception e) {
			LoggerError.error("生成金立签名异常", e);
		}
		setParameter(parms.toString());
	}

	@Override
	public void run() {
		try {
			String result = request("https://pay.gionee.com/order/create");
			LoggerError.error("金立流水号|" + result);
			JSONObject os = JSONObject.fromObject(orderform.getDeviceOS());
			os.put("channel_data", result);
			orderform.setDeviceOS(os.toString());

			OrderformHeartbeat.getInstance().execute(new AddOrderformRun(orderform));

			JSONObject jsonResult = JSONObject.fromObject(result);
			if ("200010000".equals(jsonResult.getString("status"))) {
				LoggerCharge.chargeOrder("success", orderInfo, orderform.getOrderform());
				JSONObject json = new JSONObject();
				json.put("code", 1);
				json.put("customData", orderform.getOrderform());
				json.put("message", "OK");
				json.put("channel_data", result);
				sendMessage(session, json.toString());
			} else {
				LoggerCharge.chargeOrder("fail", orderInfo, orderform.getOrderform());
				JSONObject json = new JSONObject();
				json.put("code", 2);
				json.put("message", "暂时无法充值");
				sendMessage(session, json.toString());
			}
		} catch (Exception e) {
			LoggerError.error("创建金立订单失败", e);
			JSONObject json = new JSONObject();
			json.put("code", 2);
			json.put("message", "暂时无法充值");
			sendMessage(session, json.toString());
		}
	}

//	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";
//
//	/**
//	 * RSA签名
//	 * 
//	 * @param content
//	 *            待签名数据
//	 * @param privateKey
//	 *            商户私钥
//	 * @param encode
//	 *            字符集编码
//	 * @return 签名值
//	 * @throws IOException
//	 * @throws NoSuchAlgorithmException
//	 * @throws InvalidKeySpecException
//	 * @throws SignatureException
//	 * @throws InvalidKeyException
//	 */
//	public static String sign(String content, String privateKey, String encode) throws IOException, NoSuchAlgorithmException,
//	        InvalidKeySpecException, SignatureException, InvalidKeyException {
//		String charset = CharEncoding.UTF_8;
//		if (!StringUtils.isBlank(encode)) {
//			charset = encode;
//		}
//		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
//		KeyFactory keyf = KeyFactory.getInstance("RSA");
//		PrivateKey priKey = keyf.generatePrivate(priPKCS8);
//
//		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
//		signature.initSign(priKey);
//		signature.update(content.getBytes(charset));
//		byte[] signed = signature.sign();
//		return Base64.encodeBytes(signed);
//
//	}
//
//	/**
//	 * <pre>
//	 * <p>函数功能说明:RSA验签名检查</p>
//	 * <p>修改者名字:guocl</p>
//	 * <p>修改日期:2012-11-30</p>
//	 * <p>修改内容:抛异常</p>
//	 * </pre>
//	 * 
//	 * @param content
//	 *            待签名数据
//	 * @param sign
//	 *            签名值
//	 * @param publicKey
//	 *            支付宝公钥
//	 * @param encode
//	 *            字符集编码
//	 * @return 布尔值
//	 * @throws NoSuchAlgorithmException
//	 * @throws IOException
//	 * @throws InvalidKeySpecException
//	 * @throws InvalidKeyException
//	 * @throws SignatureException
//	 */
//	public static boolean doCheck(String content, String sign, String publicKey, String encode) throws NoSuchAlgorithmException, IOException,
//	        InvalidKeySpecException, InvalidKeyException, SignatureException {
//		String charset = CharEncoding.UTF_8;
//		if (!StringUtils.isBlank(encode)) {
//			charset = encode;
//		}
//		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//		byte[] encodedKey = Base64.decode(publicKey);
//		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
//
//		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
//
//		signature.initVerify(pubKey);
//		signature.update(content.getBytes(charset));
//
//		boolean bverify = signature.verify(Base64.decode(sign));
//		return bverify;
//
//	}

}
