package mmo.common.module.clazz.charge.callback.channel;

import java.sql.Timestamp;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeState;
import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.tools.itoos.RSASignature;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.service.OrderformService;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.run.AddChargeRun;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class ChargeITools {
	private final static String PROXY          = "CHUKONG";
	private final static String CHANNEL        = "iTools";
	private final static String APP_ID_VALUE   = "11005";

	private final static String SIGN           = "sign";
	private final static String notify_data    = "notify_data";

	private final static String order_id_com   = "order_id_com";
	private final static String user_id        = "user_id";
	private final static String amount         = "amount";
	private final static String account        = "account";
	private final static String order_id       = "order_id";
	private final static String result         = "result";

	private final static String RESULT_SUCCESS = "success";

	private final static String ERR_ORDERFORM  = "{\"ErrorCode\":\"11\",\"ErrorDesc\":\"商户订单号无效\"}";

	public HttpResponseMessage callback(HttpRequestMessage request) {
		String notifyData = request.getParameter(notify_data);
		String sign = request.getParameter(SIGN);

		boolean verified = false;
		String notifyJson = "";
		try {
			// 公钥RSA解密后的json字符串
			notifyJson = RSASignature.decrypt(notifyData);

			// 公钥对数据进行RSA签名校验
			verified = RSASignature.verify(notifyJson, sign);
		} catch (Exception e) {
			e.printStackTrace();
			return sendToClient("fail");
		}


		if (!verified) {
			return sendToClient("fail");
		}
		try {
			JSONObject jsonObj = JSONObject.fromObject(notifyJson);
			ChargeOrderform orderform = OrderformManager.getInstance().getOrderform(jsonObj.getString(order_id_com));
			if (orderform == null) {
				return sendToClient(ERR_ORDERFORM);
			}

			if (orderform.isHadled() && OrderformService.getInstance().validateOrderform(orderform.getOrderform())) {
				return sendToClient(RESULT_SUCCESS);
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
			cr.setMoney((int) (100 * Float.parseFloat(jsonObj.getString(amount))));
			if (RESULT_SUCCESS.equals(jsonObj.getString(result))) {
				cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_SUCC);
			} else {
				cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_FAIL);
			}
			cr.setState(ChargeState.GAME_SERVER_UNADD);
			cr.setNote(StringLib.roleCharge);
			cr.setAtime(new Timestamp(System.currentTimeMillis()));
			cr.setOrderform(jsonObj.getString(order_id));
			cr.setProxy(PROXY);
			cr.setProxyChannel(CHANNEL);
			cr.setProxyTime(new Timestamp(System.currentTimeMillis()));
			cr.setUserid(jsonObj.getString(user_id));
			cr.setChannelSub(orderform.getChannelSub());
			cr.setRoleLevel(orderform.getRoleLevel());
			cr.setDeviceOS(orderform.getDeviceOS());
			cr.setDeviceImei(orderform.getDeviceImei());
			cr.setGoodsId(orderform.getItemId());
			cr.setGoodsName(orderform.getItemName());
			cr.setGoodsCount(orderform.getItemCount());
			cr.setPrice(orderform.getItemPrice());
			ChargeDatabaseHeartbeat.getInstance().execute(new AddChargeRun(cr));

			return sendToClient(RESULT_SUCCESS);
		} catch (Exception e) {
			LoggerError.error("解析报错", e);
			return sendToClient("fail");
		}
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
