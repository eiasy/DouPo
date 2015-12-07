package mmo.common.module.clazz.charge.callback.channel;

import java.sql.Timestamp;

import mmo.common.account.HttpCData;
import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeCData;
import mmo.common.module.service.charge.ChargeManager;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.run.AddChargeRun;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.MD5;

public class ChargePatch104 {

	private final String ERR_SUCC = "success";

	public ChargePatch104() {
	}

	public HttpResponseMessage callback(HttpRequestMessage request) {
		try {
			int gameid = Integer.parseInt(request.getParameter(ChargeCData.Patch.gameid));
			int serverid = Integer.parseInt(request.getParameter(ChargeCData.Patch.serverid));
			int roleid = Integer.parseInt(request.getParameter(ChargeCData.Patch.roleid));
			String roleName = request.getParameter(ChargeCData.Patch.roleName);
			int amount = Integer.parseInt(request.getParameter(ChargeCData.Patch.amount));
			String reason = request.getParameter(ChargeCData.Patch.reason);
			byte ctype = Byte.parseByte(request.getParameter(ChargeCData.Patch.ctype));
			byte state = Byte.parseByte(request.getParameter(ChargeCData.Patch.state));
			int accountId = Integer.parseInt(request.getParameter(ChargeCData.Patch.accountId));
			String userid = request.getParameter(ChargeCData.Patch.userid);
			String channelId = request.getParameter(ChargeCData.ChargeConfirm.channelId);
			String channelSub = request.getParameter(ChargeCData.Patch.channelSub);
			int goodsId = Integer.parseInt(request.getParameter(ChargeCData.Patch.goodsId));
			String goodsName = request.getParameter(ChargeCData.Patch.goodsName);
			short count = Short.parseShort(request.getParameter(ChargeCData.Patch.goodsCount));
			String managerKey = request.getParameter(HttpCData.MANAGER_KEY);
			String sign = request.getParameter(ChargeCData.Patch.sign);

			StringBuilder sb = new StringBuilder();
			sb.append(gameid).append('^');
			sb.append(serverid).append('^');
			sb.append(roleid).append('^');
			sb.append(roleName).append('^');
			sb.append(amount).append('^');
			sb.append(ctype).append('^');
			sb.append(accountId).append('^');
			sb.append(userid).append('^');
			sb.append(channelId).append('^');
			if (MD5.getHashString(sb.toString()).equals(sign)) {
				ChargeRecord cr = new ChargeRecord();
				cr.setAtime(new Timestamp(System.currentTimeMillis()));
				cr.setCtype(ctype);
				cr.setGameId(gameid);
				cr.setMoney(amount);
				cr.setNote(reason);
				String orderId = ChargeManager.getOrderFormId(gameid, serverid) + "_" + gameid + "_" + serverid;
				cr.setOrderId(orderId);
				cr.setRoleId(roleid);
				cr.setRolename(roleName);
				cr.setServerId(serverid);
				cr.setProxyTime(new Timestamp(System.currentTimeMillis()));
				cr.setState(state);
				cr.setOrderform(cr.getOrderId());
				cr.setProxyChannel("GM");
				cr.setProxy("GM");
				cr.setDeviceOS("win32");
				cr.setDeviceImei("win32@" + accountId);
				cr.setAccountId(accountId);
				cr.setUserid(userid);
				cr.setChannelId(channelId);
				cr.setChannelSub(channelSub);
				cr.setGoodsId(goodsId);
				cr.setGoodsName(goodsName);
				cr.setGoodsCount(count);
				ChargeDatabaseHeartbeat.getInstance().execute(new AddChargeRun(cr));
				return sendToClient(ERR_SUCC);
			} else {
				return sendToClient("err:sign");
			}
		} catch (Exception e) {
			LoggerError.error("ChargeRecordHandler", e);
			return sendToClient("err:parameter");
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
