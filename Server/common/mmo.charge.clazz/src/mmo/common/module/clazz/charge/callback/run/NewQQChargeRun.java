package mmo.common.module.clazz.charge.callback.run;

import java.util.HashMap;
import java.util.Map;

import mmo.common.bean.role.QQChargeRecord;
import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.http.parameter.HttpParameter;
import mmo.common.module.service.charge.tencent.QQCheckChargeHearbeat;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.IPushQQCharge;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import mmo.tools.util.MD5;

public class NewQQChargeRun implements IPushQQCharge {

	private final static String STATUS_OK = "ok";

	private QQChargeRecord cr;

	public void setQQChargeRecord(QQChargeRecord cr) {
		this.cr = cr;
	}

	public NewQQChargeRun() {

	}

	public NewQQChargeRun(QQChargeRecord cr) {
		super();
		this.cr = cr;
	}

	@Override
	public void run() {
		try {
			Map<String, String> parameter = new HashMap<String, String>();
			parameter.put(HttpParameter.AppStore.item_price, cr.getSaveNum() + "");
			parameter.put(HttpParameter.AppStore.time_create, cr.getAddTime() + "");
			parameter.put(HttpParameter.AppStore.order_id, cr.getOrderId());
			parameter.put("payChannel", cr.getPayChannel() + "");
			parameter.put("chargeType", cr.getChargeType());
			parameter.put("channelOrder", cr.getChannelOrderId());
			parameter.put("sign", MD5.getHashString(cr.getAddTime() + "8" + cr.getChannelOrderId() + "9" + cr.getOrderId()));
			String result = HttpsUtil.request(ProjectCofigs.getParameter("qq_charge_record"), HttpsUtil.httpBuildQuery(parameter));
			if (STATUS_OK.equalsIgnoreCase(result)) {
				cr.setStatus(QQChargeRecord.STATUS_2_CONFIRM);
				cr.setCdata("已确认");
				ChargeDatabaseHeartbeat.getInstance().execute(new UpdateQQCharge2DBRun(cr));
			} else if (result.startsWith("order_not_exis")) {
				cr.setStatus(404);
				cr.setCdata("订单不存在");
				ChargeDatabaseHeartbeat.getInstance().execute(new UpdateQQCharge2DBRun(cr));
			} else if (result.startsWith("error_sign")) {
				cr.setStatus(405);
				cr.setCdata("签名错误");
				ChargeDatabaseHeartbeat.getInstance().execute(new UpdateQQCharge2DBRun(cr));
			} else {
				QQCheckChargeHearbeat.getInstance().enterQueue(cr);
			}
		} catch (Exception e) {
			LoggerError.error("请求订单号异常", e);
			QQCheckChargeHearbeat.getInstance().enterQueue(cr);
		}
	}

}
