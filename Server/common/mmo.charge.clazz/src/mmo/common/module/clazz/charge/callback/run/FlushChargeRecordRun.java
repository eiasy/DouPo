package mmo.common.module.clazz.charge.callback.run;

import mmo.common.charge.ChargeCData;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.http.AFlushChargeRecordRun;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.run.UpdateCharge2DBRun;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import net.sf.json.JSONObject;

public class FlushChargeRecordRun extends AFlushChargeRecordRun {

	@Override
	public void run() {
		NetAddress address = ProjectCofigs.getNetAddress(ChargeOrderform.getNoticeKey(cr.getOrderId()));
		if (address == null) {
			LoggerError.error("订单未设置充值回调地址@" + cr.getOrderId());
			return;
		}
		LoggerError.warn("parameter=" + HttpsUtil.httpBuildQuery(parameter, true));
		String result = HttpsUtil.request(address.getUrl(), HttpsUtil.httpBuildQuery(parameter, true));
		StringBuilder sb = new StringBuilder();
		sb.append(FLUSH_CHARGE_RECORD);
		sb.append('|').append(cr.getOrderId());
		sb.append('|').append(parameter.get(ChargeCData.Record.orderform));
		sb.append('|').append(result);
		LoggerError.messageLog.warn(sb.toString());
		JSONObject json = JSONObject.fromObject(result);
		if ("4".equals(json.getString("code")) || "3".equals(json.getString("code"))) {
			cr.setState((byte) 2);
			ChargeDatabaseHeartbeat.getInstance().execute(new UpdateCharge2DBRun(cr));
			OrderformManager.getInstance().handleChargeRecord(cr.getId());
		}
	}
}
