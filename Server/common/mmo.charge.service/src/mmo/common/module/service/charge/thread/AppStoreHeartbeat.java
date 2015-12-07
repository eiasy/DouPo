package mmo.common.module.service.charge.thread;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.module.service.charge.http.HttpHandlerLogin;
import mmo.common.module.service.charge.service.IStoreService;
import mmo.common.module.service.charge.thread.run.IStoreRunnable;
import mmo.common.module.service.charge.thread.run.IValidateReceipt;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.heartbeat.AHeartbeat;

public class AppStoreHeartbeat extends AHeartbeat {

	private final static AppStoreHeartbeat instance = new AppStoreHeartbeat();

	public final static AppStoreHeartbeat getInstance() {
		return instance;
	}

	private ConcurrentLinkedQueue<RoleStoreReceipt> receiptQueue = new ConcurrentLinkedQueue<RoleStoreReceipt>();
	private IStoreService storeService;

	public IStoreService getStoreService() {
		return storeService;
	}

	private AppStoreHeartbeat() {
	}

	public void init(IStoreService service) {
		this.storeService = service;
		if (service != null) {
			List<RoleStoreReceipt> list = service.loadRoleStoreReceipt(RoleStoreReceipt.STAUS_2_CHARGED);
			if (list != null && list.size() > 0) {
				for (int ri = 0; ri < list.size(); ri++) {
					if (list.get(ri).getStatus() == 0) {
						receiptQueue.offer(list.get(ri));
					}
				}
			}

			list = service.loadRoleStoreReceipt(ApplicationConfig.getInstance().getAppId(), RoleStoreReceipt.STAUS_2_CHARGED);
			if (list != null && list.size() > 0) {
				for (int ri = 0; ri < list.size(); ri++) {
					if (list.get(ri).getStatus() == 0) {
						receiptQueue.offer(list.get(ri));
					}
				}
			}
		}
	}

	public void execute(IStoreRunnable run) {
		addEvent(run);
	}

	public void enterQueue(RoleStoreReceipt receipt) {
		receipt.setNextTime(System.currentTimeMillis() + RoleStoreReceipt.TIME_WAIT);
		receiptQueue.offer(receipt);
	}

	public void callback() {
		RoleStoreReceipt receipt = null;
		if ((receipt = receiptQueue.peek()) != null) {
			switch (receipt.getStatus()) {
				case RoleStoreReceipt.STAUS_0_NEW: {
					if (receipt.isValidate()) {
						receiptQueue.poll();
						try {
							IValidateReceipt run = (IValidateReceipt) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.run.ValidateReceiptRun").newInstance();
							run.setReceipt(receipt);
							ThreadManager.requestHttp("apple", run);
						} catch (Exception e) {
							LoggerError.error("验证AppStore票据出现异常", e);
						}
					} else {
						return;
					}
					break;
				}
				case RoleStoreReceipt.STAUS_1_UNCHARGE: {
					if (receipt.isValidate()) {
						receiptQueue.poll();
						try {
							IPushStoreOrder run = (IPushStoreOrder) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.run.NewAppStoreReceiptRun").newInstance();
							run.setReceipt(receipt);
							ThreadManager.pushAppOrder(run);
						} catch (Exception e) {
						}
					} else {
						return;
					}
					break;
				}
				case RoleStoreReceipt.STAUS_2_CHARGED: {
					receiptQueue.poll();
					break;
				}
				default: {
					receiptQueue.poll();
					break;
				}
			}
		}
	}
}
