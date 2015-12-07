package mmo.common.module.clazz.charge.callback.channel;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

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

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class ChargeTongBu {
	private final static String PROXY        = "CHUKONG";
	private final static String CHANNEL      = "tongbu";
	private final static String APP_ID_VALUE = "141087";
	private final static String APP_KEY      = "E7Rd2qYNkaH@Uh5tEQodqAXNZxHUJg5E";

	private final static String source       = "source";
	private final static String trade_no     = "trade_no";
	private final static String amount       = "amount";
	private final static String partner      = "partner";
	private final static String paydes       = "paydes";
	private final static String debug        = "debug";
	private final static String tborder      = "tborder";
	private final static String sign         = "sign";

	private final static String ERR_OK       = "{\"status\":\"success\"}";
	private final static String ERR_PARENER  = "{\"status\":\"error_partner\"}";
	private final static String ERR_SIGN     = "{\"status\":\"error_sign\"}";
	private final static String ERR_trade_no = "{\"status\":\"error_trade_no\"}";

	public HttpResponseMessage callback(HttpRequestMessage request) {
		if (!APP_ID_VALUE.equals(request.getParameter(partner))) {
			return sendToClient(ERR_PARENER);
		}
		String md5Sign = getSignForAnyValid(request);
		if (!md5Sign.equals(request.getParameter(sign))) {
			LoggerError.error("同步推充值签名验证未通过#订单号#" + request.getParameter(tborder));
			return sendToClient(ERR_SIGN);
		}

		ChargeOrderform orderform = OrderformManager.getInstance().getOrderform(request.getParameter(trade_no));
		if (orderform == null) {
			return sendToClient(ERR_trade_no);
		}

		if (orderform.isHadled() && OrderformService.getInstance().validateOrderform(orderform.getOrderform())) {
			return sendToClient(ERR_OK);
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
		cr.setMoney((int) (Float.parseFloat(request.getParameter(amount))));
		cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_SUCC);
		cr.setState(ChargeState.GAME_SERVER_UNADD);
		cr.setNote(StringLib.roleCharge);
		cr.setAtime(new Timestamp(System.currentTimeMillis()));
		cr.setOrderform(request.getParameter(tborder));
		cr.setProxy(PROXY);
		cr.setProxyChannel(CHANNEL);
		cr.setProxyTime(new Timestamp(System.currentTimeMillis()));
		cr.setUserid(orderform.getUserId());
		cr.setChannelSub(orderform.getChannelSub());
		cr.setRoleLevel(orderform.getRoleLevel());
		cr.setGoodsId(orderform.getItemId());
		cr.setGoodsName(orderform.getItemName());
		cr.setGoodsCount(orderform.getItemCount());
		cr.setDeviceOS(orderform.getDeviceOS());
		cr.setDeviceImei(orderform.getDeviceImei());
		cr.setPrice(orderform.getItemPrice());
		ChargeDatabaseHeartbeat.getInstance().execute(new AddChargeRun(cr));
		return sendToClient(ERR_OK);
	}

	public final String getSignForAnyValid(HttpRequestMessage request) {
		StringBuilder sb = new StringBuilder();
		sb.append(source).append("=").append(request.getParameter(source));
		sb.append("&").append(trade_no).append("=").append(request.getParameter(trade_no));
		sb.append("&").append(amount).append("=").append(request.getParameter(amount));
		sb.append("&").append(partner).append("=").append(request.getParameter(partner));
		sb.append("&").append(paydes).append("=").append(request.getParameter(paydes));
		sb.append("&").append(debug).append("=").append(request.getParameter(debug));
		sb.append("&").append(tborder).append("=").append(request.getParameter(tborder));
		sb.append("&key=").append(APP_KEY);
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
