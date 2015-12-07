package mmo.common.module.service.charge.tencent;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import mmo.common.bean.role.QQChargeRecord;
import mmo.common.module.service.charge.http.HttpHandlerLogin;
import mmo.common.module.service.charge.service.Service;
import mmo.common.module.service.charge.thread.IPushQQCharge;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.heartbeat.AHeartbeat;

public class QQCheckChargeHearbeat extends AHeartbeat {
	private final static QQCheckChargeHearbeat instance = new QQCheckChargeHearbeat();

	public static QQCheckChargeHearbeat getInstance() {
		return instance;
	}

	private ConcurrentLinkedQueue<QQChargeRecord> checkQueue = new ConcurrentLinkedQueue<QQChargeRecord>();

	private QQCheckChargeHearbeat() {

	}

	public void init() {
		List<QQChargeRecord> list = Service.getInstance().loadQQChargeRecord(0, QQChargeRecord.STATUS_2_CONFIRM);
		for (int si = 0; si < list.size(); si++) {
			checkQueue.offer(list.get(si));
		}
	}

	public void enterQueue(QQChargeRecord cr) {
		if (cr != null) {
			cr.resetCheckTime();
			checkQueue.offer(cr);
		}
	}

	public void callback() {
		try {
			QQChargeRecord check = null;
			if ((check = checkQueue.peek()) != null) {
				if (check.isCheckable()) {
					checkQueue.poll();
					switch (check.getStatus()) {
						case QQChargeRecord.STATUS_0_NEW: {
							ThreadManager.checkChargeRecord(check);
							break;
						}
						case QQChargeRecord.STATUS_1_UNCONFIRM: {
							IPushQQCharge run = (IPushQQCharge) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.run.NewQQChargeRun").newInstance();
							run.setQQChargeRecord(check);
							ThreadManager.pushAppOrder(run);
							break;
						}
					}
				}
			}

		} catch (Exception e) {
			LoggerError.error("验证腾讯充值异常", e);
		}
	}
}
