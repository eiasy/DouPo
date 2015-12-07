package mmo.common.module.clazz.charge.callback.doupo.order;

import java.net.URLDecoder;
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
import mmo.tools.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.session.IoSession;

import com.iapppay.sign.SignHelper;

public class RequestKupaiRun extends RequestHttpRun2 {
	private IoSession       session;
	private ChargeOrderform orderform;
	private String          orderInfo;

	public RequestKupaiRun() {

	}

	public void setData(IoSession session, ChargeOrderform orderform, String orderInfo, int goodsId, String goodsName, String appuserid) {
		this.session = session;
		this.orderform = orderform;
		this.orderInfo = orderInfo;
		float price = orderform.getItemPrice();
		price = price / 100;
		JSONObject jsonSign = new JSONObject();
		jsonSign.put("appid", ProjectCofigs.getParameter("kupai_appId"));
		jsonSign.put("waresid", goodsId);
		jsonSign.put("price", price);
		jsonSign.put("waresname", goodsName);
		jsonSign.put("cporderid", orderform.getOrderform());
		jsonSign.put("currency", "RMB");
		jsonSign.put("appuserid", appuserid);
		jsonSign.put("notifyurl", ProjectCofigs.getParameter("notifyUri_kupai"));
		String sign = SignHelper.sign(jsonSign.toString(), ProjectCofigs.getParameter("kupai_appPriKey"));
		LoggerError.error("jsonSign=" + jsonSign.toString());
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("transdata", jsonSign.toString());
		parms.put("sign", sign);
		parms.put("signtype", "RSA");
		setParameter(HttpsUtil.httpBuildQuery(parms));
	}

	@Override
	public void run() {
		try {
			System.out.println("parameter=" + parameter);
			String result = URLDecoder.decode(HttpsUtil.request("http://pay.coolyun.com:6988/payapi/order", parameter), "utf-8");
			LoggerError.error("酷派流水号|" + result);
			JSONObject os = JSONObject.fromObject(orderform.getDeviceOS());
			os.put("channel_data", result);
			orderform.setDeviceOS(os.toString());
			OrderformHeartbeat.getInstance().execute(new AddOrderformRun(orderform));

			String[] array = StringUtils.split(result, '&');
			if (array != null && array.length > 0) {
				for (int ai = 0; ai < array.length; ai++) {
					if (array[ai].startsWith("transdata")) {
						String[] array2 = StringUtil.splitString(array[ai], '=');
						LoggerCharge.chargeOrder("success", orderInfo, orderform.getOrderform());
						JSONObject json = new JSONObject();
						json.put("code", 1);
						json.put("customData", orderform.getOrderform());
						json.put("message", "OK");
						json.put("channel_data", array2[1]);
						sendMessage(session, json.toString());
						return;
					}
				}
			}
			JSONObject json = new JSONObject();
			json.put("code", 2);
			json.put("message", "暂时无法充值");
			sendMessage(session, json.toString());
			LoggerCharge.chargeOrder("fail", orderInfo, orderform.getOrderform());
		} catch (Exception e) {
			LoggerError.error("创建酷派订单失败", e);
			JSONObject json = new JSONObject();
			json.put("code", 2);
			json.put("message", "暂时无法充值");
			sendMessage(session, json.toString());
		}
	}
}
