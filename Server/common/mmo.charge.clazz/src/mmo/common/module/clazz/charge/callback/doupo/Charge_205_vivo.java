package mmo.common.module.clazz.charge.callback.doupo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.DateUtil;

import org.apache.mina.core.session.IoSession;

public class Charge_205_vivo extends AChargeContextHandle {
	private VivoSignUtils signUtil = new VivoSignUtils();

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		try {
			Map<String, String> para = new HashMap<String, String>();
			List<String> keys = request.getParameterNames();
			for (String k : keys) {
				para.put(k, request.getParameter(k));
			}
			if (!signUtil.verifySignature(para, ProjectCofigs.getParameter("vivo_cpKey"))) {
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

			String customOrder = request.getParameter("cpOrderNumber");
			int cents = Integer.parseInt(request.getParameter("orderAmount"));
			byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
			String channelOrder = request.getParameter("orderNumber");
			String channel = "vivo";
			String channelProxy = request.getParameter("tradeType");
			long proxyTime = DateUtil.stringToDec(request.getParameter("payTime"), "yyyyMMddHHmmss");
			String userId = request.getParameter("uid");
			if ("0000".equals(request.getParameter("tradeStatus"))) {
				chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
			}
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
			LoggerError.error("处理vivo充值通知异常", e);
		}
		return sendToClient("result=FAIL&resultMsg=exception");
	}

	class VivoSignUtils {

		// 签名key
		public final static String SIGNATURE     = "signature";
		// 签名方法key
		public final static String SIGN_METHOD   = "signMethod";
		// =
		public static final String QSTRING_EQUAL = "=";
		// &
		public static final String QSTRING_SPLIT = "&";

		/**
		 * 拼接请求字符串
		 * 
		 * @param req
		 *            请求要素
		 * @param key
		 *            vivo分配给商户的密钥
		 * @return 请求字符串
		 */
		public String buildReq(Map<String, String> req, String key) {
			// 除去数组中的空值和签名参数
			Map<String, String> filteredReq = paraFilter(req);
			// 根据参数获取vivo签名
			String signature = getVivoSign(filteredReq, key);
			// 签名结果与签名方式加入请求提交参数组中
			filteredReq.put(SIGNATURE, signature);
			filteredReq.put(SIGN_METHOD, "MD5");

			return createLinkString(filteredReq, false, true); // 请求字符串，key不需要排序，value需要URL编码
		}

		/**
		 * 异步通知消息验签
		 * 
		 * @param para
		 *            异步通知消息
		 * @param key
		 *            vivo分配给商户的密钥
		 * @return 验签结果
		 */
		public boolean verifySignature(Map<String, String> para, String key) {
			// 除去数组中的空值和签名参数
			Map<String, String> filteredReq = paraFilter(para);
			// 根据参数获取vivo签名
			String signature = getVivoSign(filteredReq, key);
			// 获取参数中的签名值
			String respSignature = para.get(SIGNATURE);
			// 对比签名值
			if (null != respSignature && respSignature.equals(signature)) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * 获取vivo签名
		 * 
		 * @param para
		 *            参与签名的要素<key,value>
		 * @param key
		 *            vivo分配给商户的密钥
		 * @return 签名结果
		 */
		public String getVivoSign(Map<String, String> para, String key) {
			// 除去数组中的空值和签名参数
			Map<String, String> filteredReq = paraFilter(para);

			String prestr = createLinkString(filteredReq, true, false); // 得到待签名字符串 需要对map进行sort，不需要对value进行URL编码
			prestr = prestr + QSTRING_SPLIT + md5Summary(key);

			return md5Summary(prestr);
		}

		/**
		 * 除去请求要素中的空值和签名参数
		 * 
		 * @param para
		 *            请求要素
		 * @return 去掉空值与签名参数后的请求要素
		 */
		public Map<String, String> paraFilter(Map<String, String> para) {

			Map<String, String> result = new HashMap<String, String>();

			if (para == null || para.size() <= 0) {
				return result;
			}

			for (String key : para.keySet()) {
				String value = para.get(key);
				if (value == null || value.equals("") || key.equalsIgnoreCase(SIGNATURE) || key.equalsIgnoreCase(SIGN_METHOD)) {
					continue;
				}
				result.put(key, value);
			}

			return result;
		}

		/**
		 * 把请求要素按照“参数=参数值”的模式用“&”字符拼接成字符串
		 * 
		 * @param para
		 *            请求要素
		 * @param sort
		 *            是否需要根据key值作升序排列
		 * @param encode
		 *            是否需要URL编码
		 * @return 拼接成的字符串
		 */
		public String createLinkString(Map<String, String> para, boolean sort, boolean encode) {

			List<String> keys = new ArrayList<String>(para.keySet());

			if (sort)
				Collections.sort(keys);

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < keys.size(); i++) {
				String key = keys.get(i);
				String value = para.get(key);

				if (encode) {
					try {
						value = URLEncoder.encode(value, "utf-8");
					} catch (UnsupportedEncodingException e) {
					}
				}

				if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
					sb.append(key).append(QSTRING_EQUAL).append(value);
				} else {
					sb.append(key).append(QSTRING_EQUAL).append(value).append(QSTRING_SPLIT);
				}
			}
			return sb.toString();
		}

		/**
		 * 对传入的参数进行MD5摘要
		 * 
		 * @param str
		 *            需进行MD5摘要的数据
		 * @return MD5摘要值
		 */
		public String md5Summary(String str) {

			if (str == null) {
				return null;
			}

			MessageDigest messageDigest = null;

			try {
				messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.reset();
				messageDigest.update(str.getBytes("utf-8"));
			} catch (NoSuchAlgorithmException e) {

				return str;
			} catch (UnsupportedEncodingException e) {
				return str;
			}

			byte[] byteArray = messageDigest.digest();

			StringBuffer md5StrBuff = new StringBuffer();

			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}

			return md5StrBuff.toString();
		}

		public void main(String[] args) {
			Map<String, String> para = new HashMap<String, String>();
			para.clear();

			para.put("version", "1.0.0");
			para.put("signMethod", "MD5");
			para.put("signature", "XXXXXXX");

			para.put("cpId", "123456");
			para.put("appId", "123");
			para.put("cpOrderNumber", "123456878");
			para.put("notifyUrl", "http://127.0.0.1:8080/vivopay/vivoPay/testNotify");
			para.put("orderTime", "20150408162237");
			para.put("orderAmount", "10.00");
			para.put("orderTitle", "手机");
			para.put("orderDesc", "vivo Xplay");
			para.put("extInfo", "Payment3.0.1");
			String key = "123456789123456";

			String sign = getVivoSign(para, key);
			System.out.println("vivo签名值：" + sign);

			boolean verify = verifySignature(para, key);
			System.out.println("验签成功：" + verify);
		}
	}
}
