package mmo.common.module.clazz.charge.callback.chukong;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.DateUtil;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class ChargeChukong {
	protected final static String pay_time       = "pay_time";
	protected final static String pay_status     = "pay_status";
	protected final static String source         = "source";
	protected final static String private_data   = "private_data";
	protected final static String user_id        = "user_id";
	protected final static String product_name   = "product_name";
	protected final static String server_id      = "server_id";
	protected final static String amount         = "amount";
	protected final static String product_id     = "product_id";
	protected final static String product_count  = "product_count";
	protected final static String game_user_id   = "game_user_id";
	protected final static String order_id       = "order_id";
	protected final static String sign           = "sign";
	protected final static String channel_number = "channel_number";
	protected final static String order_type     = "order_type";

	private final static String   ERR_ORDERFORM  = "err:orderid";

	public HttpResponseMessage s_101(HttpRequestMessage request) {
		String md5Sign = getSignForAnyValid(request);
		if (md5Sign.equals(request.getParameter(sign))) {
			String orderid = request.getParameter(private_data);
			ChargeOrderform orderform = OrderformManager.getInstance().getOrderform(orderid);
			if (orderform == null) {
				return sendToClient(ERR_ORDERFORM);
			}

			if (orderform.isHadled() && OrderformService.getInstance().validateOrderform(orderform.getOrderform())) {
				return sendToClient("ok");
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
			cr.setMoney((int) (100 * Float.parseFloat(request.getParameter(amount))));
			if ("1".equals(request.getParameter(pay_status))) {
				cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_SUCC);
			} else {
				cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_FAIL);
			}
			cr.setState(ChargeState.GAME_SERVER_UNADD);
			cr.setNote(StringLib.roleCharge);
			cr.setAtime(new Timestamp(System.currentTimeMillis()));
			cr.setOrderform(request.getParameter(order_id));
			cr.setProxy("CHUKONG");
			cr.setPrice(orderform.getItemPrice());
			cr.setProxyChannel(request.getParameter(order_type));
			cr.setProxyTime(new Timestamp(DateUtil.stringToDate(request.getParameter(pay_time)).getTime()));
			cr.setUserid(request.getParameter(user_id));
			cr.setChannelSub(orderform.getChannelSub());
			cr.setAccountId(orderform.getAccountId());
			cr.setRoleLevel(orderform.getRoleLevel());
			cr.setGoodsId(orderform.getItemId());
			cr.setGoodsName(orderform.getItemName());
			cr.setGoodsCount(orderform.getItemCount());
			cr.setDeviceOS(orderform.getDeviceOS());
			cr.setDeviceImei(orderform.getDeviceImei());
			ChargeDatabaseHeartbeat.getInstance().execute(new AddChargeRun(cr));
			return sendToClient("ok");
		} else {
			LoggerError.error("触控充值签名验证未通过#订单号#" + request.getParameter(order_id));
		}
		return sendToClient("sign error");
	}

	public final String getSignForAnyValid(HttpRequestMessage request) {
		List<String> requestParams = request.getParameterNames();// 获得所有的参数名
		List<String> params = new ArrayList<String>(requestParams);
		sortParamNames(params);
		String paramValues = "";
		for (String param : params) {// 拼接参数值
			if (param.equals("sign")) {
				continue;
			}
			String paramValue = request.getParameter(param);
			if (paramValue != null) {
				paramValues += paramValue;
			}
		}
		String md5Values = MD5Encode(paramValues);
		md5Values = MD5Encode(md5Values.toLowerCase() + ProjectCofigs.getParameter("chukong_private_key")).toLowerCase();
		return md5Values;
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

	private void sortParamNames(List<String> paramNames) {
		Collections.sort(paramNames, new Comparator<String>() {
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
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
	
	public void printParameter(HttpRequestMessage request) {
		List<String> names = request.getParameterNames();
		HashMap<String, String> params = new HashMap<String, String>();
		for (String k : names) {
			params.put(k, request.getParameter(k));
			LoggerError.warn("k=" + k + ", v=" + request.getParameter(k));
		}
	}
}
