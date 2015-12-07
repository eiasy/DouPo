package mmo.common.module.clazz.account.doupo.context;

import org.apache.mina.core.session.IoSession;

import mmo.common.account.HttpCData;
import mmo.common.charge.ChargeCData;
import mmo.common.module.account.doupo.cache.thread.AccountValidateHeartbeat;
import mmo.common.module.account.doupo.cache.thread.validate.RoleChargePatchRun;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import net.sf.json.JSONObject;

public class A20022_gmChargePatch extends AContextHandle {
	private String MSG_1_OK = "OK";
	private String MSG_ERR  = "操作失败";

	public A20022_gmChargePatch() {
	}

	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		JSONObject loginResult = new JSONObject();
		try {
			int gameid = getInt(request, ChargeCData.Patch.gameid);
			int serverid = getInt(request, ChargeCData.Patch.serverid);
			int roleid = getInt(request, ChargeCData.Patch.roleid);
			String roleName = request.getParameter(ChargeCData.Patch.roleName);
			int amount = getInt(request, ChargeCData.Patch.amount);
			String reason = request.getParameter(ChargeCData.Patch.reason);
			byte ctype = (byte) getInt(request, ChargeCData.Patch.ctype);
			byte state = (byte) getInt(request, ChargeCData.Patch.state);
			int accountId = getInt(request, ChargeCData.Patch.accountId);
			String userid = request.getParameter(ChargeCData.Patch.userid);
			int goodsId = getInt(request, ChargeCData.Patch.goodsId);
			String goodsName = request.getParameter(ChargeCData.Patch.goodsName);
			short count = (short) getInt(request, ChargeCData.Patch.goodsCount);
			String managerKey = request.getParameter(HttpCData.MANAGER_KEY);
			AccountValidateHeartbeat.getInstance().execute(
			        new RoleChargePatchRun(gameid, serverid, roleid, roleName, amount, reason, ctype, state, accountId, userid, goodsId,
			                goodsName, count, managerKey));

			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_1_OK);
			loginResult.put(HttpCData.A20001.message, MSG_1_OK);
			return sendToClient(loginResult.toString());
		} catch (Exception e) {
			LoggerError.error("A20021_checkDeviceFreezed", e);
			loginResult.put(HttpCData.A20001.status, HttpCData.A20001.RT_2_NO);
			loginResult.put(HttpCData.A20001.message, MSG_ERR);
			return sendToClient(loginResult.toString());
		}
	}

}
