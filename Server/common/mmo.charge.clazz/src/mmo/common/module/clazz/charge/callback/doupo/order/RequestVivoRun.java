package mmo.common.module.clazz.charge.callback.doupo.order;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.thread.OrderformHeartbeat;
import mmo.common.module.service.charge.thread.run.AddOrderformRun;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import mmo.tools.thread.runnable.RequestHttpRun2;
import mmo.tools.util.DateUtil;
import mmo.tools.util.MD5;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

public class RequestVivoRun extends RequestHttpRun2 {
	private IoSession       session;
	private ChargeOrderform orderform;
	private String          orderInfo;

	public RequestVivoRun() {

	}

	public void setData(IoSession session, ChargeOrderform orderform, String orderInfo) {
		this.session = session;
		this.orderform = orderform;
		this.orderInfo = orderInfo;
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("version", "1.0.0");
		parameters.put("signMethod", "MD5");
		parameters.put("cpId", ProjectCofigs.getParameter("vivo_cpId"));
		parameters.put("appId", ProjectCofigs.getParameter("vivo_appId"));
		parameters.put("cpOrderNumber", orderform.getOrderform());
		parameters.put("notifyUrl", ProjectCofigs.getParameter("notifyUri_vivo"));
		parameters.put("orderTime", DateUtil.formatDate(new Date(orderform.getTimeCreate()), "yyyyMMddHHmmss"));
		parameters.put("orderAmount", orderform.getItemPrice() + "");
		parameters.put("orderTitle", orderform.getItemName());
		parameters.put("orderDesc", orderform.getItemName());
		parameters.put("extInfo", orderform.getRoleId() + "");

		StringBuilder sb = new StringBuilder();
		sb.append("appId=").append(ProjectCofigs.getParameter("vivo_appId"));
		sb.append("&cpId=").append(ProjectCofigs.getParameter("vivo_cpId"));
		sb.append("&cpOrderNumber=").append(orderform.getOrderform());
		sb.append("&extInfo=").append(orderform.getRoleId());
		sb.append("&notifyUrl=").append(ProjectCofigs.getParameter("notifyUri_vivo"));
		sb.append("&orderAmount=").append(orderform.getItemPrice());
		sb.append("&orderDesc=").append(orderform.getItemName());
		sb.append("&orderTime=").append(parameters.get("orderTime"));
		sb.append("&orderTitle=").append(parameters.get("orderTitle"));
		sb.append("&version=").append(parameters.get("version"));
		sb.append("&").append(MD5.getHashString(ProjectCofigs.getParameter("vivo_cpKey").toLowerCase()));

		parameters.put("signature", MD5.getHashString(sb.toString()).toLowerCase());
		setParameter(HttpsUtil.httpBuildQuery(parameters));
	}

	@Override
	public void run() {
		try {
			String result = request("https://pay.vivo.com.cn/vcoin/trade");
			JSONObject os = JSONObject.fromObject(orderform.getDeviceOS());
			os.put("channel_data", result);
			orderform.setDeviceOS(os.toString());

			OrderformHeartbeat.getInstance().execute(new AddOrderformRun(orderform));

			JSONObject jsonResult = JSONObject.fromObject(result);
			if ("200".equals(jsonResult.getString("respCode"))) {
				LoggerCharge.chargeOrder("success", orderInfo, orderform.getOrderform());
				JSONObject json = new JSONObject();
				json.put("code", 1);
				json.put("customData", orderform.getOrderform());
				json.put("message", "OK");
				json.put("channel_data", result);
				sendMessage(session, json.toString());
			} else {
				LoggerCharge.chargeOrder("fail", orderInfo, orderform.getOrderform());
				JSONObject json = new JSONObject();
				json.put("code", 2);
				json.put("message", "暂时无法充值");
				sendMessage(session, json.toString());
			}
		} catch (Exception e) {
			LoggerError.error("创建VIVO订单失败", e);
			JSONObject json = new JSONObject();
			json.put("code", 2);
			json.put("message", "暂时无法充值");
			sendMessage(session, json.toString());
		}
	}
}
