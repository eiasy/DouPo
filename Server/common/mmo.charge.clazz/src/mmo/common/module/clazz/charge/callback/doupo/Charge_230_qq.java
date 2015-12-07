package mmo.common.module.clazz.charge.callback.doupo;

import java.net.SocketAddress;

import mmo.common.account.HttpCData;
import mmo.common.bean.role.QQChargeRecord;
import mmo.common.module.clazz.charge.callback.AChargeContextHandle;
import mmo.common.module.clazz.charge.callback.run.AddQQCharge2DBRun;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.extension.application.ApplicationConfig;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.util.MD5;

import org.apache.mina.core.session.IoSession;

public class Charge_230_qq extends AChargeContextHandle {

	@Override
	protected HttpResponseMessage checkParameters(HttpRequestMessage request) {
		return null;
	}

	@Override
	public HttpResponseMessage callback(IoSession session, HttpRequestMessage request) {
		int resultCode = request.getIntParameter("resultCode");
		int payChannel = request.getIntParameter("payChannel");
		int payState = request.getIntParameter("payState");
		int provideState = request.getIntParameter("provideState");
		int saveNum = request.getIntParameter("saveNum");
		String extendInfo = request.getParameter("extendInfo");
		String orderId = request.getParameter("orderId");
		String openid = request.getParameter("openid");
		String openkey = request.getParameter("openkey");
		String payToken = request.getParameter("payToken");
		String pf = request.getParameter("pf");
		String pfkey = request.getParameter("pfkey");
		String actionType = request.getParameter("actionType");
		String sign = request.getParameter("sign");
		int zoneId = request.getIntParameter("zoneid");

		String remoteIp = request.getHeaderValue(HttpCData.A20001.real_ip);
		if (remoteIp == null || "".equals(remoteIp)) {
			SocketAddress sa = session.getRemoteAddress();
			if (sa != null) {
				remoteIp = sa.toString();
			}
		}
		if (remoteIp.contains("/") && remoteIp.contains(":")) {
			remoteIp = remoteIp.substring(remoteIp.indexOf('/') + 1, remoteIp.indexOf(':'));
		}

		QQChargeRecord cr = new QQChargeRecord();
		cr.setActionType(actionType);
		cr.setAddTime(System.currentTimeMillis());
		cr.setAppId(ApplicationConfig.getInstance().getAppId());
		cr.setCdata("");
		cr.setChargeType("");
		cr.setCheckCount(0);
		cr.setExtendInfo(extendInfo);
		cr.setHandleAppId(0);
		cr.setIdfa("");
		cr.setImei("");
		cr.setOpenid(openid);
		cr.setOpenkey(openkey);
		cr.setOrderId(orderId);
		cr.setPayChannel(payChannel);
		cr.setPayState(payState);
		cr.setPayToken(payToken);
		cr.setPf(pf);
		cr.setPfkey(pfkey);
		cr.setProvideState(provideState);
		cr.setRemoteIp(remoteIp);
		cr.setResultCode(resultCode);
		cr.setSaveNum(saveNum);
		cr.setUpdateTime(System.currentTimeMillis());
		cr.setZoneId(zoneId);
		if (resultCode == 0) {
			if (saveNum < 0) {
				cr.setCdata("支付金额小于0");
				cr.setStatus(QQChargeRecord.STATUS_103_AMOUNT_ERROR);
			} else {
				cr.setCdata("新订单");
				cr.setStatus(QQChargeRecord.STATUS_0_NEW);
			}
		} else {
			cr.setCdata("失败订单");
			cr.setStatus(resultCode + 1000);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(resultCode).append(',');
		sb.append(payChannel).append(',');
		sb.append(payState).append(',');
		sb.append(provideState).append(',');
		sb.append(saveNum).append(',');
		sb.append(extendInfo).append(',');
		sb.append(orderId).append(',');
		sb.append(openid).append(',');
		sb.append(openkey).append(',');
		sb.append(payToken).append(',');
		sb.append(pf).append(',');
		sb.append(pfkey).append(',');
		sb.append(actionType);
		if (MD5.getHashString(sb.toString()).equals(sign)) {
			LoggerCharge.qqcharge(cr, true);
			ChargeDatabaseHeartbeat.getInstance().execute(new AddQQCharge2DBRun(cr));
			return sendToClient("ok");
		} else {
			LoggerCharge.qqcharge(cr, false);
			return sendToClient("FAILURE_SIGN");
		}
	}
}
