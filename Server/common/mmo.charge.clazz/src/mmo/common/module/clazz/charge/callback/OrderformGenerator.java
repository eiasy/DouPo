package mmo.common.module.clazz.charge.callback;

import mmo.common.charge.ChargeCData;
import mmo.common.module.clazz.charge.callback.doupo.order.RequestJinLiRun;
import mmo.common.module.clazz.charge.callback.doupo.order.RequestKupaiRun;
import mmo.common.module.clazz.charge.callback.doupo.order.RequestVivoRun;
import mmo.common.module.service.charge.ChargeManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.thread.OrderformHeartbeat;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.common.module.service.charge.thread.run.AddOrderformRun;
import mmo.extension.application.ApplicationConfig;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class OrderformGenerator {
	// private final static String accesstoken = "accesstoken";
	// private final static String action = "action";
	// private final static String open_id = "open_id";
	// private final static String paytoken = "paytoken";
	// private final static String pf = "pf";
	// private final static String pf_key = "pf_key";

	/*****************************************************************************************************/

	// private final static int QQ_APP_ID = 1104244259;
	// private final static String WX_APP_ID = "wxe6fc33c405f3968f";
	// private final static String QQ_APP_KEY = "X6U6ZZch0ZEXHt8r";
	// private final static String WX_APP_KEY = "652a1e1d7f366fd18da4f95a285c9700";
	// private static final String QQ_URL = "http://msdktest.qq.com/auth/verify_login/?";
	// private static final String WX_URL = "http://msdktest.qq.com/auth/refresh_token/";

	/*****************************************************************************************************/

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request, ChargeSDKCallback callback) {
		StringBuilder sb = new StringBuilder();
		int gameId = request.getIntParameter(ChargeCData.Orderform.GAME_ID);
		checkParameter(gameId, ChargeCData.Orderform.GAME_ID, sb);
		int serverid = request.getIntParameter(ChargeCData.Orderform.SERVER_ID);
		checkParameter(serverid, ChargeCData.Orderform.SERVER_ID, sb);
		String channelid = request.getParameter(ChargeCData.Orderform.CHANNEL_ID);
		checkParameter(channelid, ChargeCData.Orderform.CHANNEL_ID, sb);

		String channelSub = request.getParameter(ChargeCData.Orderform.CHANNEL_SUB);
		checkParameter(channelSub, ChargeCData.Orderform.CHANNEL_SUB, sb);
		int accountId = string2int(request.getParameter(ChargeCData.Orderform.ACCOUNT_ID));
		checkParameter(accountId, ChargeCData.Orderform.ACCOUNT_ID, sb);
		int roleId = string2int(request.getParameter(ChargeCData.Orderform.ROLE_ID));
		checkParameter(roleId, ChargeCData.Orderform.ROLE_ID, sb);
		short roleLevel = (short) string2int(request.getParameter(ChargeCData.Orderform.ROLE_LEVEL));
		checkParameter(roleLevel, ChargeCData.Orderform.ROLE_LEVEL, sb);
		String roleName = request.getParameter(ChargeCData.Orderform.ROLE_NAME);
		checkParameter(roleName, ChargeCData.Orderform.ROLE_NAME, sb);
		int itemId = string2int(request.getParameter(ChargeCData.Orderform.ITEM_ID));
		checkParameter(itemId, ChargeCData.Orderform.ITEM_ID, sb);
		String itemName = request.getParameter(ChargeCData.Orderform.ITEM_NAME);
		checkParameter(itemName, ChargeCData.Orderform.ITEM_NAME, sb);
		int itemPrice = string2int(request.getParameter(ChargeCData.Orderform.ITEM_PRICE));
		checkParameter(itemPrice, ChargeCData.Orderform.ITEM_PRICE, sb);
		int itemCount = string2int(request.getParameter(ChargeCData.Orderform.ITEM_COUNT));
		checkParameter(itemCount, ChargeCData.Orderform.ITEM_COUNT, sb);
		String deviceOS = request.getParameter(ChargeCData.Orderform.DEVICE_OS);
		checkParameter(deviceOS, ChargeCData.Orderform.DEVICE_OS, sb);
		int limit = 0;
		try {
			limit = Integer.parseInt(ProjectCofigs.getParameter("charge_close_" + channelid));
		} catch (Exception e) {

		}
//		if("com.doupo.zhangyue.zy".equals(channelSub)&&itemPrice/100>10){
//			StringBuilder sbo = new StringBuilder();
//			sbo.append("关闭掌阅充值|").append(channelid).append('|').append(ProjectCofigs.getParameter("charge_close_" + channelid)).append('|').append(gameId).append('|').append(serverid).append('|').append(itemId).append('|').append(itemName).append('|').append(itemPrice);
//			LoggerError.warn(sbo.toString());
//			JSONObject json = new JSONObject();
//			json.put("code", 2);
//			json.put("message", "暂时无法充值");
//			return sendToClient(json.toString());
//		}
		if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("charge_close_all"))) {// 关闭充值
			StringBuilder sbo = new StringBuilder();
			sbo.append("关闭所有充值|").append(channelid).append('|').append(gameId).append('|').append(serverid).append('|').append(itemId).append('|').append(itemName).append('|').append(itemPrice);
			LoggerError.warn(sbo.toString());
			JSONObject json = new JSONObject();
			json.put("code", 2);
			String notice = ProjectCofigs.getParameter("charge_close_all_notice");
			if (notice != null && notice.trim().length() > 0) {
				json.put("message", notice);
			} else {
				json.put("message", "暂时无法充值");
			}
			return sendToClient(json.toString());
		}
		if (limit > 0 && itemPrice / 100 >= limit) {// 关闭充值
			StringBuilder sbo = new StringBuilder();
			sbo.append("关闭充值|").append(channelid).append('|').append(ProjectCofigs.getParameter("charge_close_" + channelid)).append('|').append(gameId).append('|').append(serverid).append('|').append(itemId).append('|').append(itemName).append('|').append(itemPrice);
			LoggerError.warn(sbo.toString());
			JSONObject json = new JSONObject();
			json.put("code", 2);

			String notice = ProjectCofigs.getParameter("charge_close_" + channelid + "_notice");
			if (notice != null && notice.trim().length() > 0) {
				json.put("message", notice);
			} else {
				json.put("message", "暂时无法充值");
			}
			return sendToClient(json.toString());
		}
		String userId = request.getParameter(ChargeCData.Orderform.USER_ID);
		checkParameter(userId, ChargeCData.Orderform.USER_ID, sb);

		String ip = request.getParameter(ChargeCData.Orderform.ip);
		checkParameter(deviceOS, ChargeCData.Orderform.ip, sb);

		String deviceImei = request.getParameter(ChargeCData.Orderform.deviceImei);
		checkParameter(deviceImei, ChargeCData.Orderform.deviceImei, sb);

		String customData = request.getParameter(ChargeCData.Orderform.customData);
		JSONObject os = new JSONObject();
		os.put("os", deviceOS);
		os.put("ip", ip);
		os.put("customData", customData);
		// if (channelid == 670000 &&
		// "true".equalsIgnoreCase(request.getParameter(ChargeCData.Orderform.requestTencent))) {
		// tencentOrderId(json, customData, itemId, itemName, itemName, itemPrice, itemCount, serverid, ip);
		//
		// }

		String orderId = ChargeManager.getOrderFormId(gameId, serverid) + "_" + gameId + "_" + serverid;

		ChargeOrderform orderform = new ChargeOrderform(gameId, serverid, channelid, channelSub, accountId, userId, roleId, roleLevel, roleName, itemId, itemName, itemPrice, itemCount, os.toString(), deviceImei);
		orderform.setTimeCreate(System.currentTimeMillis());
		orderform.setOrderform(orderId);
		orderform.setAppId(ApplicationConfig.getInstance().getAppId());
		switch (channelid) {
			case "jinli": {
				try {
					RequestJinLiRun run = (RequestJinLiRun) callback.getClass("mmo.common.module.clazz.charge.callback.doupo.order.RequestJinLiRun").newInstance();
					float price = itemPrice;
					price = price / 100;
					run.setData(session, orderform, sb.toString(), userId, price + "", "1", itemName, price + "");
					ThreadManager.requestHttp(channelid,run);
				} catch (Exception e) {
					LoggerError.error("构建RequestJinLiRun异常", e);
				}
				return null;
			}
			case "kupai": {
				try {
					RequestKupaiRun run = (RequestKupaiRun) callback.getClass("mmo.common.module.clazz.charge.callback.doupo.order.RequestKupaiRun").newInstance();
					float price = itemPrice;
					price = price / 100;
					run.setData(session, orderform, sb.toString(), itemId, itemName, roleId + "#" + serverid);
					ThreadManager.requestHttp(channelid,run);
				} catch (Exception e) {
					LoggerError.error("构建RequestJinLiRun异常", e);
				}
				return null;
			}
			case "vivo": {
				try {
					RequestVivoRun run = (RequestVivoRun) callback.getClass("mmo.common.module.clazz.charge.callback.doupo.order.RequestVivoRun").newInstance();
					run.setData(session, orderform, sb.toString());
					ThreadManager.requestHttp(channelid,run);
				} catch (Exception e) {
					LoggerError.error("构建RequestVivoRun异常", e);
				}
				return null;
			}
			default: {
				LoggerCharge.chargeOrder("success", sb.toString(), orderform.getOrderform());
				OrderformHeartbeat.getInstance().execute(new AddOrderformRun(orderform));
				JSONObject json = new JSONObject();
				json.put("code", 1);
				json.put("customData", orderId);
				json.put("message", "OK");
				json.put("channel_data", "");

				return sendToClient(json.toString());
			}
		}

	}

//	private void tencentOrderId(JSONObject jsonOut, String customData, int goodsId, String goodsName, String goodsDesc, int price, int count, int serverId, String ip) {
//		URL url = null;
//		HttpURLConnection conn = null;
//		try {
//			JSONObject json = JSONObject.fromObject(customData);
//			long time = System.currentTimeMillis() / 1000;
//			String uri = URLEncoder.encode("/mpay/buy_goods_m", "UTF-8");
//			String method = "GET";
//
//			Map<String, String> parameters = new HashMap<String, String>();
//
//			parameters.put("amt", "" + price * count);
//			parameters.put("appid", "" + QQ_APP_ID);
//			parameters.put("appmode", "" + 1);
//			parameters.put("format", "json");
//			parameters.put("goodsmeta", URLEncoder.encode(goodsName, "UTF-8") + "%" + Integer.toHexString('*').toUpperCase() + URLEncoder.encode(goodsDesc, "UTF-8"));
//			parameters.put("goodsurl", URLEncoder.encode("http://www.huayigame.com/frRes/yuanbao.png", "UTF-8"));
//			parameters.put("openid", json.getString(open_id));
//			parameters.put("openkey", URLEncoder.encode(json.getString(accesstoken), "UTF-8"));
//			parameters.put("pay_token", URLEncoder.encode(json.getString(paytoken), "UTF-8"));
//			parameters.put("payitem", goodsId + "%" + Integer.toHexString('*').toUpperCase() + price + "%" + Integer.toHexString('*').toUpperCase() + count);
//			parameters.put("pf", URLEncoder.encode(json.getString(pf), "UTF-8"));
//			parameters.put("pfkey", URLEncoder.encode(json.getString(pf_key), "UTF-8"));
//			parameters.put("ts", "" + time);
//			parameters.put("zoneid", "" + serverId);
//
//			List<String> keyList = new ArrayList<String>(parameters.keySet());
//			Collections.sort(keyList);
//			StringBuilder sb = new StringBuilder();
//			for (int li = 0; li < keyList.size(); li++) {
//				if (li > 0) {
//					sb.append("&");
//				}
//				sb.append(keyList.get(li)).append("=").append(parameters.get(keyList.get(li)));
//			}
//			String parameter = sb.toString();
//
//			sb.setLength(0);
//			sb.append(method).append("&").append(uri).append("&").append(URLEncoder.encode(parameter, "UTF-8"));
//
//			String sign = new BASE64Encoder().encode(HMACSHA1.getHmacSHA1(sb.toString(), QQ_APP_KEY + "&"));
//
//			sb.setLength(0);
//			sb.append("http://msdktest.qq.com/mpay/buy_goods_m?");
//			for (int li = 0; li < keyList.size(); li++) {
//				if (li > 0) {
//					sb.append("&");
//				}
//				sb.append(keyList.get(li)).append("=").append(URLEncoder.encode(parameters.get(keyList.get(li)), "UTF-8"));
//			}
//			sb.append("&sig=").append(URLEncoder.encode(sign, "UTF-8"));
//
//			url = new URL(sb.toString());
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Connection", "close");
//			conn.setDoInput(true);
//			String line = null;
//			String result = null;
//			BufferedReader bufferedReader;
//			InputStreamReader streamReader = null;
//			sb.setLength(0);
//			try {
//				streamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
//			} catch (IOException e) {
//				streamReader = new InputStreamReader(conn.getErrorStream(), "UTF-8");
//			} finally {
//				if (streamReader != null) {
//					bufferedReader = new BufferedReader(streamReader);
//					sb = new StringBuilder();
//					while ((line = bufferedReader.readLine()) != null) {
//						sb.append(line);
//					}
//					result = sb.toString();
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	protected int string2int(String source) {
		if (source == null || source.trim().length() < 1) {
			return -1;
		}
		try {
			return Integer.parseInt(source.trim());
		} catch (Exception e) {
			return -1;
		}
	}

	protected long string2long(String source) {
		if (source == null || source.trim().length() < 1) {
			return -1;
		}
		try {
			return Long.parseLong(source.trim());
		} catch (Exception e) {
			return -1;
		}
	}

	protected void checkParameter(int value, String parameter, StringBuilder result) {
		if (result.length() > 0) {
			result.append("-");
		}
		result.append(value);
	}

	protected void checkParameter(long value, String parameter, StringBuilder result) {
		if (result.length() > 0) {
			result.append("-");
		}
		result.append(value);
	}

	protected void checkParameter(String value, String parameter, StringBuilder result) {
		if (result.length() > 0) {
			result.append("-");
		}
		result.append(value);
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content) {
		return sendToClient(content, "text/plain;charset=utf-8");
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content, String contentType) {
		HttpResponseMessage response = new HttpResponseMessage();
		if (contentType != null) {
			response.setContentType(contentType);
		} else {
			response.setContentType("text/plain;charset=utf-8");
		}
		response.appendBody(content);
		return response;
	}
}
