package mmo.common.module.clazz.charge.callback.channel;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeState;
import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.service.OrderformService;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.run.AddChargeRun;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

public class ChargeXY {
	private final static String PROXY         = "CHUKONG";
	private final static String CHANNEL       = "XY";
	private final static String APP_ID_VALUE  = "100005276";

	private final static String name_sign     = "sign";
	private final static String name_sig      = "sig";
	private final static String name_orderid  = "orderid";
	private final static String name_uid      = "uid";
	private final static String name_amount   = "amount";
	private final static String name_extra    = "extra";
	private final static String name_serverid = "serverid";
	private final static String name_ts       = "ts";

	private final static String AppKey        = "SLVCZ4KKOgjJzM5fpHXoOgHImWyqFZKv";
	private final static String PayKey        = "qvW998cojZ0acgNNtU3S98N7GffMYajB";
	private final static String success       = "success";

	public HttpResponseMessage callback(HttpRequestMessage request) {

		try {
			String sign = request.getParameter(name_sign);
			String sig = request.getParameter(name_sig);
			String orderid = request.getParameter(name_orderid);
			String uid = request.getParameter(name_uid);
			String amount = request.getParameter(name_amount);
			String extra = request.getParameter(name_extra);
			String serverid = request.getParameter(name_serverid);
			String ts = request.getParameter(name_ts);

			Map<String, String> maps = new TreeMap<String, String>();
			maps.put(name_uid, uid);
			maps.put(name_orderid, orderid);
			maps.put(name_serverid, serverid);
			maps.put(name_amount, amount);
			maps.put(name_extra, extra);
			maps.put(name_ts, ts);
			
			StringBuilder sb = new StringBuilder();
			sb.append(AppKey);
			sb.append(name_amount).append("=").append(amount);
			sb.append("&").append(name_extra).append("=").append(extra);
			sb.append("&").append(name_orderid).append("=").append(orderid);
			sb.append("&").append(name_serverid).append("=").append(serverid);
			sb.append("&").append(name_ts).append("=").append(ts);
			sb.append("&").append(name_uid).append("=").append(uid);

			if (!MD5.getHashString(sb.toString()).equalsIgnoreCase(sign)) {
				String msg = responseResult(6);
				LoggerError.error(msg);
				return sendToClient(msg);
			}
			sb.setLength(0);
			sb.append(PayKey);
			sb.append(name_amount).append("=").append(amount);
			sb.append("&").append(name_extra).append("=").append(extra);
			sb.append("&").append(name_orderid).append("=").append(orderid);
			sb.append("&").append(name_serverid).append("=").append(serverid);
			sb.append("&").append(name_ts).append("=").append(ts);
			sb.append("&").append(name_uid).append("=").append(uid);
			if (!sig.isEmpty()) {
				if (!MD5.getHashString(sb.toString()).equalsIgnoreCase(sig)) {
					String msg = responseResult(6);
					LoggerError.error(msg);
					return sendToClient(msg);
				}
			}

			ChargeOrderform orderform = OrderformManager.getInstance().getOrderform(extra);
			if (orderform == null) {
				String msg = responseResult(8);
				LoggerError.error(msg);
				return sendToClient(msg);
			}

			if (orderform.isHadled() && OrderformService.getInstance().validateOrderform(orderform.getOrderform())) {
				String msg = responseResult(0);
				LoggerError.error(msg);
				return sendToClient(success);
			}

			OrderformManager.getInstance().hadleOrderform(orderform);

			ChargeRecord cr = new ChargeRecord();
			cr.setOrderId(orderform.getOrderform());
			cr.setGameId(orderform.getGameId());
			cr.setServerId(orderform.getServerId());
			cr.setChannelId(orderform.getChannelId());
			cr.setAccountId(orderform.getAccountId());
			cr.setRoleId(orderform.getRoleId());
			cr.setRolename(orderform.getRoleName());
			cr.setMoney((int) (100 * Float.parseFloat(amount)));
			cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_SUCC);
			cr.setState(ChargeState.GAME_SERVER_UNADD);
			cr.setNote(StringLib.roleCharge);
			cr.setAtime(new Timestamp(System.currentTimeMillis()));
			cr.setOrderform(orderid);
			cr.setProxy(PROXY);
			cr.setProxyChannel(CHANNEL);
			cr.setProxyTime(new Timestamp(System.currentTimeMillis()));
			cr.setUserid(uid);
			cr.setChannelSub(orderform.getChannelSub());
			cr.setRoleLevel(orderform.getRoleLevel());
			cr.setDeviceOS(orderform.getDeviceOS());
			cr.setDeviceImei(orderform.getDeviceImei());
			cr.setGoodsId(orderform.getItemId());
			cr.setGoodsName(orderform.getItemName());
			cr.setGoodsCount(orderform.getItemCount());
			cr.setPrice(orderform.getItemPrice());
			ChargeDatabaseHeartbeat.getInstance().execute(new AddChargeRun(cr));
			return sendToClient(success);
		} catch (Exception e) {
			LoggerError.error("解析报错", e);
			return sendToClient(responseResult(1));
		}
	}

	public static String getGenSafeSign(Map<String, String> maps, String keys) {
		if (maps.isEmpty())
			return "";
		String params = "";
		for (Iterator it = maps.entrySet().iterator(); it.hasNext();) {
			java.util.Map.Entry pairs = (java.util.Map.Entry) it.next();
			if (pairs.getKey().equals("sign") || pairs.getKey().equals("sig")) {
				it.remove();
			} else {
				if (params != "")
					params = (new StringBuilder()).append(params).append("&").toString();
				params = (new StringBuilder()).append(params).append(pairs.getKey()).append("=").append(pairs.getValue()).toString();
			}
		}

		String sign = MD5.getHashString((new StringBuilder()).append(keys).append(params).toString());
		return sign;
	}

	/**
	 * 响应结果方法
	 */
	public static String responseResult(int code) {
		JSONObject map = new JSONObject();
		map.put("ret", code);
		switch (code) {
			case 0:
				map.put("msg", "回调成功");
				break;
			case 1:
				map.put("msg", "参数错误");
				break;
			case 2:
				map.put("msg", "玩家不存在");
				break;
			case 3:
				map.put("msg", "游戏服不存在");
				break;
			case 4:
				map.put("msg", "订单已存在");
				break;
			case 5:
				map.put("msg", "透传信息错误");
				break;
			case 6:
				map.put("msg", "签名校验错误");
				break;
			case 7:
				map.put("msg", "数据库错误");
				break;
			case 8:
				map.put("msg", "其它错误");
				break;
		}

		return map.toString();
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content) {
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain;charset=utf-8");
		response.appendBody(content);
		return response;
	}

}
