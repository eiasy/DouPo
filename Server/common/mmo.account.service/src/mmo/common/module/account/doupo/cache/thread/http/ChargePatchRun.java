package mmo.common.module.account.doupo.cache.thread.http;

import java.util.Map;

import mmo.common.charge.ChargeCData;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.runnable.RequestHttpRun;

public class ChargePatchRun extends RequestHttpRun {
	private static final String CHARGE_PATCH = "CHARGE_PATCH";
	private static final String CHARGE_URL   = "charge_url";
	private static final String CONTEXT      = "/charges/c104";

	public ChargePatchRun(Map<String, String> parameter) {
		super(parameter);
	}

	@Override
	public void run() {
		NetAddress address = ProjectCofigs.getNetAddress(CHARGE_URL);
		if (address == null) {
			LoggerError.error("产品未设置充值地址");
			return;
		}

		String result = request(address.getUrl() + CONTEXT);
		StringBuilder sb = new StringBuilder();
		sb.append(CHARGE_PATCH);
		sb.append('|').append(parameter.get(ChargeCData.Patch.accountId));
		sb.append('|').append(parameter.get(ChargeCData.Patch.roleid));
		sb.append('|').append(parameter.get(ChargeCData.Patch.amount));
		sb.append('|').append(result);
		LoggerError.messageLog.warn(sb.toString());
	}

}
