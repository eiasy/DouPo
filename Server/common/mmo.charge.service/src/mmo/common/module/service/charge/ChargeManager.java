package mmo.common.module.service.charge;

import java.util.concurrent.atomic.AtomicInteger;

import mmo.extension.application.ApplicationConfig;
import mmo.tools.util.MD5;
import mmo.tools.util.MathUtil;

public class ChargeManager {
	/** 订单索引生成器 */
	private final static AtomicInteger orderformIndexGenerator = new AtomicInteger(1);
	private static IChargeApplication  chargeApplication       = null;

	public final static void init(IChargeApplication chargeApplication) {
		ChargeManager.chargeApplication = chargeApplication;
	}

	public final static String getOrderFormId(int productId, int serverId) {
		StringBuilder sb = new StringBuilder();
		sb.append("HYC");
		sb.append(System.currentTimeMillis()).append("-");
		sb.append(ApplicationConfig.getInstance().getProductId()).append("-");
		sb.append(ApplicationConfig.getInstance().getAppId()).append("-");
		sb.append(productId).append("-");
		sb.append(serverId).append("-");
		sb.append(MathUtil.random(10000, 99999)).append("-");
		sb.append(orderformIndexGenerator.getAndIncrement());
		return MD5.getHashString(sb.toString());
	}

	public static void reloadClass() {
		chargeApplication.reloadClass();
	}
}
