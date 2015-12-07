package mmo.common.module.clazz.charge.callback.doupo;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class Charge_203_xiaomi extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		if (!ProjectCofigs.getParameter("xiaomi_appId").equals(request.getParameter("appId"))) {
			JSONObject json = new JSONObject();
			json.put("errcode", 1515);
			return sendToClient(json.toString());
		}

		try {
			List<String> keys = new ArrayList<String>(request.getParameterNames());
			keys.remove("signature");
			Collections.sort(keys);
			StringBuilder sb = new StringBuilder();
			for (int ki = 0; ki < keys.size(); ki++) {
				if (ki > 0) {
					sb.append("&");
				}
				sb.append(keys.get(ki)).append("=").append(URLDecoder.decode(request.getParameter(keys.get(ki)), "UTF-8"));
			}
			if (!HmacSHA1Encrypt(sb.toString(), ProjectCofigs.getParameter("xiaomi_appsecret")).equals(request.getParameter("signature"))) {
				JSONObject json = new JSONObject();
				json.put("errcode", 1525);
				return sendToClient(json.toString());
			}
		} catch (Exception e) {
			LoggerError.error("生成小米签名时异常", e);
			JSONObject json = new JSONObject();
			json.put("errcode", 1525);
			return sendToClient(json.toString());
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
			String customOrder = request.getParameter("cpOrderId");
			int cents = getInt(request, "payFee");
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = request.getParameter("orderId");
			String channel = "xiaomi";
			String channelProxy = "xiaomi";
			long proxyTime = 0;
			String userId = request.getParameter("uid");

			if ("TRADE_SUCCESS".equals(request.getParameter("orderStatus"))) {
				chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			}
			int orderStatus = handleOrder(customOrder, cents, chargeType, StringLib.roleCharge, channelOrder, channel, channelProxy, proxyTime,
			        userId);
			switch (orderStatus) {
				case RESULT_1_ORDER_OK: {
					JSONObject json = new JSONObject();
					json.put("errcode", 200);
					return sendToClient(json.toString());
				}
				case RESULT_2_ORDER_NOT_EXIST: {
					JSONObject json = new JSONObject();
					json.put("errcode", 1506);
					return sendToClient(json.toString());
				}
				case RESULT_3_USER_NOT_MATCH: {
					JSONObject json = new JSONObject();
					json.put("errcode", 1516);
					return sendToClient(json.toString());
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

	private String       MAC_NAME = "HmacSHA1";
	private final String ENCODING = "UTF-8";

	/**
	 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 * 
	 * @param encryptText
	 *            被签名的字符串
	 * @param encryptKey
	 *            密钥
	 * @return 返回被加密后的字符串
	 * @throws Exception
	 */
	private String HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
		byte[] data = encryptKey.getBytes(ENCODING);
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = Mac.getInstance(MAC_NAME);
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);
		byte[] text = encryptText.getBytes(ENCODING);
		// 完成 Mac 操作
		byte[] digest = mac.doFinal(text);
		StringBuilder sBuilder = bytesToHexString(digest);
		return sBuilder.toString();
	}

	/**
	 * 转换成Hex
	 * 
	 * @param bytesArray
	 */
	private StringBuilder bytesToHexString(byte[] bytesArray) {
		if (bytesArray == null) {
			return null;
		}
		StringBuilder sBuilder = new StringBuilder();
		for (byte b : bytesArray) {
			String hv = String.format("%02x", b);
			sBuilder.append(hv);
		}
		return sBuilder;
	}

	/**
	 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 * 
	 * @param encryptData
	 *            被签名的字符串
	 * @param encryptKey
	 *            密钥
	 * @return 返回被加密后的字符串
	 * @throws Exception
	 */
	private String hmacSHA1Encrypt(byte[] encryptData, String encryptKey) throws Exception {
		byte[] data = encryptKey.getBytes(ENCODING);
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = Mac.getInstance(MAC_NAME);
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);
		// 完成 Mac 操作
		byte[] digest = mac.doFinal(encryptData);
		StringBuilder sBuilder = bytesToHexString(digest);
		return sBuilder.toString();
	}

}
