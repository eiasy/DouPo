package mmo.common.module.clazz.charge.callback.channel;

import java.sql.Timestamp;

import mmo.common.account.HttpCData;
import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeState;
import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.service.OrderformService;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.run.AddChargeRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.util.MD5;
import mmo.tools.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class ChargeTencent extends AContextHandle {
	private final static String PROXY         = "TENCENT";
	private String              MSG_1_OK      = "OK";
	private String              MSG_3_ACCOUNT = "账号信息不匹配";
	private String              MSG_2_NO      = "账号不存在";
	private String              MSG_ERR       = "操作失败";
	private final static String ERR_ORDERFORM = "{\"ErrorCode\":\"11\",\"ErrorDesc\":\"商户订单号无效\"}";

	public ChargeTencent() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			int resultCode = getInt(request, HttpCData.TencentCharge.resultCode);
			int payChannel = getInt(request, HttpCData.TencentCharge.payChannel);
			int payState = getInt(request, HttpCData.TencentCharge.payState);
			int provideState = getInt(request, HttpCData.TencentCharge.provideState);
			int saveNum = getInt(request, HttpCData.TencentCharge.saveNum);
			String extendInfo = request.getParameter(HttpCData.TencentCharge.extendInfo);
			String orderId = request.getParameter(HttpCData.TencentCharge.orderId);
			String sign = request.getParameter(HttpCData.TencentCharge.sign);
			StringBuilder sb = new StringBuilder();
			sb.append(resultCode).append(payChannel).append(payState).append(provideState).append(saveNum).append(extendInfo).append(orderId)
			        .append(HttpCData.TencentCharge.sign);
			if (MD5.getHashString(sb.toString()).equals(sign)) {

				// //////////////////////////////////////////////////////////////////////////////////////////////
				ChargeOrderform orderform = OrderformManager.getInstance().getOrderform(orderId);
				if (orderform == null) {
					loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
					loginResult.put(HttpCData.A20001.message, ERR_ORDERFORM);
					return sendToClient(loginResult.toString());
				}

				if (orderform.isHadled() && OrderformService.getInstance().validateOrderform(orderform.getOrderform())) {
					loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
					loginResult.put(HttpCData.A20001.message, "orderId is repeat");
					return sendToClient(loginResult.toString());
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
				cr.setMoney(orderform.getItemCount() * orderform.getItemPrice() * 100);
				if (resultCode == 0 && payState == 0) {
					cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_SUCC);
				} else {
					cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_FAIL);
				}
				cr.setNote(StringLib.roleCharge + "|" + resultCode + "|" + payState + "|" + provideState + "|" + saveNum + "|" + extendInfo);
				cr.setState(ChargeState.UNPROVITE_TENCENT);
				cr.setAtime(new Timestamp(System.currentTimeMillis()));
				cr.setOrderform(orderId);
				cr.setProxy(PROXY);
				cr.setProxyChannel(payChannel + "");
				cr.setProxyTime(new Timestamp(System.currentTimeMillis()));
				cr.setUserid(orderform.getUserId());
				cr.setChannelSub(orderform.getChannelSub());
				cr.setRoleLevel(orderform.getRoleLevel());
				String[] array = StringUtil.splitString(orderform.getDeviceOS(), '@');
				if (array.length > 2) {
					cr.setDeviceOS(array[0]);
					cr.setNote(cr.getNote() + "|" + array[1]);
					cr.setNote(cr.getNote() + "|" + array[2]);
				} else {
					cr.setDeviceOS(array[0]);
					cr.setNote(cr.getNote() + "|" + array[1]);
				}

				cr.setDeviceImei(orderform.getDeviceImei());
				cr.setGoodsId(orderform.getItemId());
				cr.setGoodsName(orderform.getItemName());
				cr.setGoodsCount(orderform.getItemCount());
				cr.setPrice(orderform.getItemPrice());
				ChargeDatabaseHeartbeat.getInstance().execute(new AddChargeRun(cr));

				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
				// //////////////////////////////////////////////////////////////////////////////////////////////
			} else {
				loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
				loginResult.put(HttpCData.A20001.message, "签名错误");
			}

			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, e.getMessage());
			return sendToClient(loginResult.toString());
		}
	}

}
