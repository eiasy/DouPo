package mmo.common.module.clazz.charge.callback.channel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.DateUtil;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class Charge91 extends AChargeContextHandle {
	private final static String PROXY           = "CHUKONG";
	private final static String CHANNEL         = "91";
	private final static String APP_ID_VALUE    = "116051";
	private String              APP_KEY         = "9af85a8f0a38f248d0c4a513025b37993750b0f917d07660";

	private final static String AppId           = "AppId";
	private final static String Act             = "Act";
	private final static String ProductName     = "ProductName";
	private final static String MerchantId      = "MerchantId";
	private final static String ConsumeStreamId = "ConsumeStreamId";
	private final static String CooOrderSerial  = "CooOrderSerial";
	private final static String Uin             = "Uin";
	private final static String GoodsId         = "GoodsId";
	private final static String GoodsInfo       = "GoodsInfo";
	private final static String GoodsCount      = "GoodsCount";
	private final static String OriginalMoney   = "OriginalMoney";
	private final static String OrderMoney      = "OrderMoney";
	private final static String Note            = "Note";
	private final static String PayStatus       = "PayStatus";
	private final static String CreateTime      = "CreateTime";
	private final static String Sign            = "Sign";

	private final static String ACT_VALUE       = "1";

	private final static String ERR_ACT         = "{\"ErrorCode\":\"3\",\"ErrorDesc\":\"接收失败\"}";
	private final static String ERR_SIGN        = "{\"ErrorCode\":\"5\",\"ErrorDesc\":\"接收失败\"}";
	private final static String ERR_APP_ID      = "{\"ErrorCode\":\"2\",\"ErrorDesc\":\"接收失败\"}";
	private final static String ERR_FAIL        = "{\"ErrorCode\":\"0\",\"ErrorDesc\":\"接收失败\"}";
	private final static String ERR_SUCC        = "{\"ErrorCode\":\"1\",\"ErrorDesc\":\"接收失败\"}";
	private final static String ERR_UNKONW      = "{\"ErrorCode\":\"1\",\"ErrorDesc\":\"未知结果\"}";
	private final static String ERR_USER        = "{\"ErrorCode\":\"1\",\"ErrorDesc\":\"用户账号不匹配\"}";

	private final static String ERR_ORDERFORM   = "{\"ErrorCode\":\"11\",\"ErrorDesc\":\"商户订单号无效\"}";
	// 91的服务器地址
	private String              goUrl           = "http://service.sj.91.com/usercenter/ap.aspx?";

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		int resrult = 0;
		try {
			resrult = payResultNotify(request.getParameter(AppId), request.getParameter(Act),
			        URLDecoder.decode(request.getParameter(ProductName), "utf-8"), request.getParameter(ConsumeStreamId),
			        request.getParameter(CooOrderSerial), request.getParameter(Uin), request.getParameter(GoodsId),
			        URLDecoder.decode(request.getParameter(GoodsInfo), "utf-8"), request.getParameter(GoodsCount),
			        request.getParameter(OriginalMoney), request.getParameter(OrderMoney), request.getParameter(Note),
			        request.getParameter(PayStatus), URLDecoder.decode(request.getParameter(CreateTime), "utf-8"), request.getParameter(Sign));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resrult != 1) {
			LoggerError.error("91充值签名验证未通过#订单号#" + request.getParameter(ConsumeStreamId));
			return sendToClient(ERR_SIGN);
		}
		if (!ACT_VALUE.equals(request.getParameter(Act))) {
			return sendToClient(ERR_ACT);
		}

		if (!APP_ID_VALUE.equals(request.getParameter(AppId))) {
			return sendToClient(ERR_APP_ID);
		}

		return null;
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		HttpResponseMessage message = checkParameters(request);
		if (message != null) {
			return message;
		}
		byte chargeType = ChargeType.TYPE_ROLE_CHARGE_FAIL;
		if ("1".equals(request.getParameter(PayStatus))) {
			chargeType = ChargeType.TYPE_ROLE_CHARGE_SUCC;
		}
		long proxyTime = 0;
		try {
			proxyTime = DateUtil.stringToDate(URLDecoder.decode(request.getParameter(CreateTime), "utf-8")).getTime();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int orderStatus = handleOrder(request.getParameter(CooOrderSerial), (int) (100 * Float.parseFloat(request.getParameter(OrderMoney))),
		        chargeType, StringLib.roleCharge, request.getParameter(ConsumeStreamId), PROXY, CHANNEL, proxyTime, request.getParameter(Uin));

		switch (orderStatus) {
			case RESULT_1_ORDER_OK: {
				return sendToClient(ERR_SUCC);
			}
			case RESULT_2_ORDER_NOT_EXIST: {
				return sendToClient(ERR_ORDERFORM);
			}
			case RESULT_3_USER_NOT_MATCH: {
				return sendToClient(ERR_USER);
			}
			default: {
				return sendToClient(ERR_UNKONW);
			}
		}
	}

	/**
	 * 接收支付购买结果
	 * 
	 * @param appid
	 * @param act
	 * @param productName
	 * @param consumeStreamId
	 * @param cooOrderSerial
	 * @param uin
	 * @param goodsId
	 * @param goodsInfo
	 * @param goodsCount
	 * @param originalMoney
	 * @param orderMoney
	 * @param note
	 * @param payStatus
	 * @param createTime
	 * @param fromSign
	 * @return 支付结果
	 * @throws UnsupportedEncodingException
	 */
	public int payResultNotify(String appid, String act, String productName, String consumeStreamId, String cooOrderSerial, String uin,
	        String goodsId, String goodsInfo, String goodsCount, String originalMoney, String orderMoney, String note, String payStatus,
	        String createTime, String fromSign) throws UnsupportedEncodingException {
		StringBuilder strSign = new StringBuilder();
		strSign.append(appid);
		strSign.append(act);
		strSign.append(productName);
		strSign.append(consumeStreamId);
		strSign.append(cooOrderSerial);
		strSign.append(uin);
		strSign.append(goodsId);
		strSign.append(goodsInfo);
		strSign.append(goodsCount);
		strSign.append(originalMoney);
		strSign.append(orderMoney);
		strSign.append(note);
		strSign.append(payStatus);
		strSign.append(createTime);
		strSign.append(APP_KEY);
		String sign = md5(strSign.toString());
		if (!APP_ID_VALUE.equals(appid)) {
			return 2; // appid无效
		}
		if (!"1".equals(act)) {
			return 3; // Act无效
		}
		if (!sign.toLowerCase().equals(fromSign.toLowerCase())) {
			return 5; // sign无效
		}
		int payResult = -1;
		if ("1".equals(payStatus)) {
			try {
				if (queryPayResult(cooOrderSerial) == 1) {
					payResult = 1; // 有订单
				} else {
					payResult = 11; // 无订单
				}
			} catch (Exception e) {
				payResult = 6; // 自定义：网络问题
			}
			return payResult;
		}
		return 0; // 错误
	}

	/**
	 * 查询支付购买结果的API调用
	 * 
	 * @param cooOrderSerial
	 *            商户订单号
	 * @return ERRORCODE的值
	 * @throws Exception
	 *             API调用失败
	 */
	public int queryPayResult(String cooOrderSerial) throws Exception {
		String act = "1";
		StringBuilder strSign = new StringBuilder();
		strSign.append(APP_ID_VALUE);
		strSign.append(act);
		strSign.append(cooOrderSerial);
		strSign.append(APP_KEY);
		String sign = md5(strSign.toString());
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("Appid=");
		getUrl.append(APP_ID_VALUE);
		getUrl.append("&Act=");
		getUrl.append(act);
		getUrl.append("&CooOrderSerial=");
		getUrl.append(cooOrderSerial);
		getUrl.append("&Sign=");
		getUrl.append(sign);
		return GetResult(HttpGetGo(getUrl.toString()));
	}

	/**
	 * 获取91服务器返回的结果
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	private int GetResult(String jsonStr) throws Exception {
		// Pattern p = Pattern.compile("(?<=\"ErrorCode\":\")\\d{1,3}(?=\")");
		// Matcher m = p.matcher(jsonStr);
		// m.find();
		// return Integer.parseInt(m.group());

		// 这里需要引入JSON-LIB包内的JAR
		JSONObject jo = JSONObject.fromObject(jsonStr);
		return Integer.parseInt(jo.getString("ErrorCode"));
	}

	/**
	 * 发送GET请求并获取结果
	 * 
	 * @param getUrl
	 * @return
	 * @throws Exception
	 */
	private String HttpGetGo(String getUrl) throws Exception {
		StringBuffer readOneLineBuff = new StringBuffer();
		String content = "";
		URL url = new URL(goUrl + getUrl);
		URLConnection conn = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			readOneLineBuff.append(line);
		}
		content = readOneLineBuff.toString();
		reader.close();
		return content;
	}

	/**
	 * 对字符串进行MD5并返回结果
	 * 
	 * @param sourceStr
	 * @return
	 */
	private String md5(String sourceStr) {
		String signStr = "";
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if (md5Byte != null) {
				signStr = HexBin.encode(md5Byte);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signStr;
	}

	public final String getSignForAnyValid(HttpRequestMessage request) {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(request.getParameter(AppId));
			sb.append(request.getParameter(Act));
			sb.append(URLDecoder.decode(request.getParameter(ProductName), "utf-8"));
			sb.append(request.getParameter(ConsumeStreamId));
			sb.append(request.getParameter(CooOrderSerial));
			sb.append(request.getParameter(MerchantId));
			sb.append(request.getParameter(Uin));
			sb.append(request.getParameter(GoodsId));
			sb.append(URLDecoder.decode(request.getParameter(GoodsInfo), "utf-8"));
			sb.append(request.getParameter(GoodsCount));
			sb.append(request.getParameter(OriginalMoney));
			sb.append(request.getParameter(OrderMoney));
			sb.append(request.getParameter(Note));
			sb.append(request.getParameter(PayStatus));
			sb.append(URLDecoder.decode(request.getParameter(CreateTime), "utf-8"));
			sb.append(APP_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MD5.getHashString(sb.toString());
	}

	// MD5编码
	public String MD5Encode(String sourceStr) {
		String signStr = null;
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if (md5Byte != null) {
				signStr = HexBin.encode(md5Byte);
			}
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return signStr;

	}

}
