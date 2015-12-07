package mmo.common.module.clazz.charge.callback.channel;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeCData;
import mmo.common.charge.ChargeState;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.run.UpdateCharge2DBRun;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class ChargeConfirm extends AChargeContextHandle {

	public ChargeConfirm() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject json = new JSONObject();
		try {
			long friendid = Long.parseLong(request.getParameter(ChargeCData.ChargeConfirm.friendid));
			String orderid = request.getParameter(ChargeCData.ChargeConfirm.orderid);
			String channelId = request.getParameter(ChargeCData.ChargeConfirm.channelId);
			int money = Integer.parseInt(request.getParameter(ChargeCData.ChargeConfirm.money));
			int getmoney = Integer.parseInt(request.getParameter(ChargeCData.ChargeConfirm.getmoney));
			byte ctype = Byte.parseByte(request.getParameter(ChargeCData.ChargeConfirm.ctype));
			int gameId = Integer.parseInt(request.getParameter(ChargeCData.ChargeConfirm.GAME_ID));
			int serverId = Integer.parseInt(request.getParameter(ChargeCData.ChargeConfirm.SERVER_ID));
			byte state = Byte.parseByte(request.getParameter(ChargeCData.ChargeConfirm.state));

			String sign = request.getParameter(ChargeCData.Patch.sign);

			StringBuilder sb = new StringBuilder();
			sb.append(friendid).append('/');
			sb.append(orderid).append('/');
			sb.append(channelId).append('/');
			sb.append(money).append('/');
			sb.append(getmoney).append('/');
			sb.append(gameId).append('/');
			sb.append(serverId);
			if (MD5.getHashString(sb.toString()).equals(sign)) {
				LoggerDevelop.charge(0, state == ChargeState.GAME_SERVER_ADDED, 0, 0, ""+friendid, money, getmoney, orderid, "CTYPE:" + ctype);
				ChargeRecord cr = new ChargeRecord();
				cr.setId(friendid);
				cr.setOrderId(orderid);
				cr.setChannelId(channelId);
				cr.setMoney(money);
				cr.setGetmoney(getmoney);
				cr.setState((byte) 2);
				ChargeDatabaseHeartbeat.getInstance().execute(new UpdateCharge2DBRun(cr));
				json.put("code", 1);
				json.put("message", "ok");
				OrderformManager.getInstance().handleChargeRecord(friendid);
				return sendToClient(json.toString());
			} else {
				LoggerError.error("10003#sign not match");
				json.put("code", 2);
				json.put("message", "error sign");
				return sendToClient(json.toString());
			}
		} catch (Exception e) {
			LoggerError.error("LoadServerCharges", e);
			json.put("code", 2);
			json.put("message", "error parameters");
			return sendToClient(json.toString());
		}
	}

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		// TODO Auto-generated method stub
		return null;
	}
}
