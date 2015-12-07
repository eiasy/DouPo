package mmo.common.bi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import mmo.common.account.HttpCData;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.runnable.RequestHttpRun;

public class BIManager implements Runnable {
	public class BIDataRun extends RequestHttpRun {

		public BIDataRun(Map<String, String> parameter) {
			super(parameter);
		}

		@Override
		public void run() {
			if (biUrl != null) {
				String json = request(biUrl);
				if (isLogger) {
					LoggerError.warn(parameter.get(HttpCData.BIDataField.eventType) + "|" + json);
				}
			}
		}
	}

	/** 持续处理的最长时间 */
	private final static int       EXECUTE_TIME = 30;
	/** 线程暂停时间 */
	private final static int       SLEEP_TIME   = 100;
	private final static BIManager instance     = new BIManager();

	public static BIManager getInstance() {
		return instance;
	}

	private long             exeTime    = 0;
	private String           biUrl;
	private boolean          isLogger;
	private boolean          running;
	/** 数据库操作事件队列 */
	private Queue<BIDataRun> eventQueue = new ConcurrentLinkedQueue<BIDataRun>();

	private BIManager() {
		startThread();
	}

	public void startThread() {
		if (!running) {
			running = true;
			new Thread(this, "GAME-BI").start();
		}
	}

	public final static void init(String url) {
		instance.setBiUrl(url);
	}

	String getBiUrl() {
		return biUrl;
	}

	void setBiUrl(String biUrl) {
		this.biUrl = biUrl;
	}

	public boolean isLogger() {
		return isLogger;
	}

	public void setLogger(boolean isLogger) {
		this.isLogger = isLogger;
	}

	public final void accountCreate(String accountId, String channelId, String channelSub, String appName, String platform, String deviceMark,
	        String cdata) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.accountCreate);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.platform, platform);
		parameter.put(HttpCData.BIDataField.deviceMark, deviceMark);
		parameter.put(HttpCData.BIDataField.cdata, cdata);
		addEvent(new BIDataRun(parameter));
	}

	public final void accountLogin(String accountId, String channelId, String channelSub, String appName, String platform, String deviceMark,
	        String cdata) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.accountLogin);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.platform, platform);
		parameter.put(HttpCData.BIDataField.deviceMark, deviceMark);
		parameter.put(HttpCData.BIDataField.cdata, cdata);
		addEvent(new BIDataRun(parameter));
	}

	public final void customEvent(String appName, String gameServer, int roleId, String eventMark, Map<String, String> datas) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.customEvent);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.eventMark, eventMark);
		if (datas != null) {
			List<String> keys = new ArrayList<String>(datas.keySet());
			Collections.sort(keys);
			String key = "k_";
			String value = "v_";
			StringBuilder sb = new StringBuilder();
			for (int ki = 0; ki < keys.size(); ki++) {
				sb.setLength(0);
				sb.append(key).append(ki);
				parameter.put(sb.toString() + ki, keys.get(ki));
				sb.setLength(0);
				sb.append(value).append(ki);
				parameter.put(sb.toString() + ki, datas.get(keys.get(ki)));
			}
		}
		addEvent(new BIDataRun(parameter));
	}

	public final void deviceOpen(String appName, String platform, String deviceMark, String channelId) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.deviceOpen);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.platform, platform);
		parameter.put(HttpCData.BIDataField.deviceMark, deviceMark);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleChargeRequest(String appName, String gameServer, String accountId, String channelId, String channelSub, int roleId,
	        short roleLevel, String itemName, String moneyType, int amount, String orderform) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleChargeRequest);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.itemName, itemName);
		parameter.put(HttpCData.BIDataField.moneyType, moneyType);
		parameter.put(HttpCData.BIDataField.amount, amount + "");
		parameter.put(HttpCData.BIDataField.orderform, orderform);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleChargeSuccess(String appName, String gameServer, String orderform, int gameMoney, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleChargeSuccess);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.orderform, orderform);
		parameter.put(HttpCData.BIDataField.gameMoney, gameMoney + "");
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleChargeFail(String appName, String gameServer, String orderform, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleChargeFail);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.orderform, orderform);
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleCostItem(String appName, String gameServer, String accountId, String channelId, String channelSub, int roleId,
	        short roleLevel, String item, int count, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleCostItem);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.itemName, item);
		parameter.put(HttpCData.BIDataField.count, count + "");
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleGetItem(String appName, String gameServer, String accountId, String channelId, String channelSub, int roleId,
	        short roleLevel, String item, int count, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleGetItem);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.itemName, item);
		parameter.put(HttpCData.BIDataField.count, count + "");
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleBuyItem(String appName, String gameServer, String accountId, String channelId, String channelSub, int roleId,
	        short roleLevel, String item, int count, String priceItem, int price, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleBuyItem);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.itemName, item);
		parameter.put(HttpCData.BIDataField.count, count + "");
		parameter.put(HttpCData.BIDataField.priceItem, priceItem);
		parameter.put(HttpCData.BIDataField.price, price + "");
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleUpgrade(String appName, String gameServer, String accountId, String channelId, String channelSub, int roleId,
	        int roleLevel, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleUpgrade);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleDuplicateBegin(String appName, String gameServer, String accountId, String channelId, String channelSub, String duplicate,
	        int roleId, short roleLevel, String cdata) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleDuplicateBegin);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.duplicate, duplicate);
		parameter.put(HttpCData.BIDataField.cdata, cdata);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleDuplicatePass(String appName, String gameServer, String accountId, String channelId, String channelSub, String duplicate,
	        int roleId, short roleLevel, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleDuplicatePass);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.duplicate, duplicate);
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleDuplicateFail(String appName, String gameServer, String accountId, String channelId, String channelSub, String duplicate,
	        int roleId, short roleLevel, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleDuplicateFail);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.duplicate, duplicate);
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleMissionBegin(String appName, String gameServer, String accountId, String channelId, String channelSub, String mission,
	        int roleId, short roleLevel) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleMissionBegin);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.mission, mission);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleMissionCommit(String appName, String gameServer, String accountId, String channelId, String channelSub, String mission,
	        int roleId, short roleLevel, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleMissionCommit);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.mission, mission);
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleMissionCancel(String appName, String gameServer, String accountId, String channelId, String channelSub, String mission,
	        int roleId, short roleLevel, String descript) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleMissionFail);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.mission, mission);
		parameter.put(HttpCData.BIDataField.descript, descript);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleEnterGame(String appName, String gameServer, int roleId, short roleLevel, String accountId, String channelId,
	        String channelSub, String platform, String deviceMark, String eventMark, String scene, String cdata) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleEnterGame);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.platform, platform);
		parameter.put(HttpCData.BIDataField.deviceMark, deviceMark);
		parameter.put(HttpCData.BIDataField.eventMark, eventMark);
		parameter.put(HttpCData.BIDataField.scene, scene);
		parameter.put(HttpCData.BIDataField.cdata, cdata);
		addEvent(new BIDataRun(parameter));
	}

	public final void roleExitGame(String appName, String gameServer, int roleId, short roleLevel, String accountId, String channelId,
	        String channelSub, String platform, String deviceMark, int onlineTime, String cdata) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.roleExitGame);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.roleId, roleId + "");
		parameter.put(HttpCData.BIDataField.roleLevel, roleLevel + "");
		parameter.put(HttpCData.BIDataField.accountId, accountId);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.channelSub, channelSub);
		parameter.put(HttpCData.BIDataField.platform, platform);
		parameter.put(HttpCData.BIDataField.deviceMark, deviceMark);
		parameter.put(HttpCData.BIDataField.onlineTime, onlineTime + "");
		parameter.put(HttpCData.BIDataField.cdata, cdata);
		addEvent(new BIDataRun(parameter));
	}

	public final void switchServer(String appName, String gameServer, String channelId, String platform, String deviceMark) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(HttpCData.BIDataField.eventType, HttpCData.BIEvent.switchServer);
		parameter.put(HttpCData.BIDataField.appName, appName);
		parameter.put(HttpCData.BIDataField.gameServer, gameServer);
		parameter.put(HttpCData.BIDataField.channelId, channelId);
		parameter.put(HttpCData.BIDataField.platform, platform);
		parameter.put(HttpCData.BIDataField.deviceMark, deviceMark);
		addEvent(new BIDataRun(parameter));
	}

	protected final void addEvent(BIDataRun run) {
		eventQueue.offer(run);
	}

	@Override
	public void run() {
		BIDataRun runEntity = null;
		while (running) {
			try {
				exeTime = +System.currentTimeMillis() + EXECUTE_TIME;
				while ((runEntity = eventQueue.poll()) != null) {
					runEntity.run();
					if (System.currentTimeMillis() > exeTime) {
						break;
					}
				}
				Thread.sleep(SLEEP_TIME);
			} catch (Exception e) {
				LoggerError.error("GAME-BI", e);
			}
		}
	}

}
