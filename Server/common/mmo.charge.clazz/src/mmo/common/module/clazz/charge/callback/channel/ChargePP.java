package mmo.common.module.clazz.charge.callback.channel;

import java.sql.Timestamp;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeState;
import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.tools.pp.RSAEncrypt;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.service.OrderformService;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.run.AddChargeRun;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

public class ChargePP {

	public String        DEFAULT_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApB8Ze3N72YAXCWvL4oVW" + "\r\n"
	                                                + "hrB5p2tk6Kmp0frsCTInKX9lsS7BKCyaeZatHl8dIVUS8MQTHP5zAvszQ8hkvBJo" + "\r\n"
	                                                + "Y2nivOSbMQ0AwZiS0GhPdOZkduWGx0yiUETAvhLs041Voniqx6QlHMtxTu+eSC1y" + "\r\n"
	                                                + "LWaTwsiWXJrI8sk4YqHepnQQ2t3scu11+ZBLtfxP2MYNnJJg09ljDI2c4Xod+mad" + "\r\n"
	                                                + "W6vIAOENhe56mH2DuiOy4ktNJWpQPgaMG9PstwDe3cTyZoMuEk9hUNwh1ZmijJYq" + "\r\n"
	                                                + "PtC/0xM+5t98eTpCGLxOdOEefxc88CG6RLpQPMo6oKvY+zoYV+5yDAsL8w423QN7" + "\r\n" + "CQIDAQAB"
	                                                + "\r\n";

	private final String PROXY              = "CHUKONG";
	private final String CHANNEL            = "pp";
	private final String APP_ID_VALUE       = "4217";
	private final String APP_KEY            = "5544e36b09f55c0db84cd5aac84f89ce";

	private final String order_id           = "order_id";
	private final String billno             = "billno";
	private final String account            = "account";
	private final String amount             = "amount";
	private final String status             = "status";
	private final String app_id             = "app_id";
	private final String uuid               = "uuid";
	private final String roleid             = "roleid";
	private final String zone               = "zone";
	private final String sign               = "sign";

	private final String ERR_SUCC           = "success";

	private RSAEncrypt   rsaEncrypt         = null;

	public ChargePP() {
		rsaEncrypt = new RSAEncrypt();

		try {
			rsaEncrypt.loadPublicKey(DEFAULT_PUBLIC_KEY);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("加载公钥失败");
		}
	}

	public HttpResponseMessage callback(HttpRequestMessage request) {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		String jsonString = null;
		try {
			String signSource = request.getParameter(sign);
			byte[] dcDataStr = base64Decoder.decodeBuffer(signSource);
			byte[] plainData = rsaEncrypt.decrypt(rsaEncrypt.getPublicKey(), dcDataStr);
			jsonString = new String(plainData, "utf-8");
		} catch (Exception e) {
			LoggerError.error("pp节目失败", e);
			return sendToClient("fail");
		}
		try {
			JSONObject jsonObj = JSONObject.fromObject(jsonString);
			if (!request.getParameter(order_id).equals(jsonObj.getString(order_id))) {
				return sendToClient("fail");
			}
			if (!request.getParameter(billno).equals(jsonObj.getString(billno))) {
				return sendToClient("fail");
			}
			if (!request.getParameter(account).equals(jsonObj.getString(account))) {
				return sendToClient("fail");
			}
			if (!request.getParameter(amount).equals(jsonObj.getString(amount))) {
				return sendToClient("fail");
			}
			if (!request.getParameter(status).equals(jsonObj.getString(status))) {
				return sendToClient("fail");
			}
			if (!request.getParameter(app_id).equals(jsonObj.getString(app_id))) {
				return sendToClient("fail");
			}
		} catch (Exception e) {
			return sendToClient("fail");
		}

		ChargeOrderform orderform = OrderformManager.getInstance().getOrderform(request.getParameter(billno));
		if (orderform == null) {
			return sendToClient("fail");
		}

		if (orderform.isHadled() && OrderformService.getInstance().validateOrderform(orderform.getOrderform())) {
			return sendToClient(ERR_SUCC);
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
		if ("0".equals(request.getParameter(status))) {
			cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_SUCC);
		} else {
			cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_FAIL);
		}
		cr.setState(ChargeState.GAME_SERVER_UNADD);
		cr.setNote(StringLib.roleCharge);
		cr.setAtime(new Timestamp(System.currentTimeMillis()));
		cr.setOrderform(request.getParameter(order_id));
		cr.setProxy(PROXY);
		cr.setProxyChannel(CHANNEL);
		cr.setProxyTime(new Timestamp(System.currentTimeMillis()));
		cr.setUserid(request.getParameter(account));
		cr.setChannelSub(orderform.getChannelSub());
		cr.setRoleLevel(orderform.getRoleLevel());
		cr.setGoodsId(orderform.getItemId());
		cr.setGoodsName(orderform.getItemName());
		cr.setGoodsCount(orderform.getItemCount());
		cr.setDeviceOS(orderform.getDeviceOS());
		cr.setPrice(orderform.getItemPrice());
		ChargeDatabaseHeartbeat.getInstance().execute(new AddChargeRun(cr));
		return sendToClient(ERR_SUCC);
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
