package mmo.common.module.service.charge.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.bean.role.QQChargeRecord;
import mmo.common.charge.ChargeCData;
import mmo.common.module.service.charge.http.AFlushChargeRecordRun;
import mmo.common.module.service.charge.http.HttpHandlerLogin;
import mmo.common.module.service.charge.service.ServiceCharge;
import mmo.common.module.service.charge.tencent.ACheckProvited;
import mmo.common.module.service.charge.tencent.QQCheckChargeHearbeat;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.ThreadObserver;
import mmo.tools.thread.pool.CThreadFactory;
import mmo.tools.thread.runnable.CRunnable;
import mmo.tools.thread.runnable.IHttpRequest;
import mmo.tools.util.MD5;

public class ThreadManager {
	// private final static ExecutorService requestHttp = new ThreadPoolExecutor(4, 8, 1000 * 60, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("REQUEST_HTTP"));
	private static ExecutorService pushAppOrder = Executors.newCachedThreadPool(new CThreadFactory("PUSH_APP_ORDER"));// new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("PUSH_APP_ORDER"));
	private static ExecutorService serverCharges = Executors.newCachedThreadPool(new CThreadFactory("LOAD_CHARGS"));// new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("LOAD_CHARGS"));
	private static Map<String, ExecutorService> channelThread = new HashMap<String, ExecutorService>();
	private static AtomicInteger threadCount = new AtomicInteger();

	public static void loadServerCharges(int productId, int serverId) {
		serverCharges.execute(new LoadServerCharges(productId, serverId));
	}

	static class LoadServerCharges implements CRunnable {
		private int productId;
		private int serverId;

		public LoadServerCharges(int productId, int serverId) {
			super();
			this.productId = productId;
			this.serverId = serverId;
		}

		@Override
		public void run() {
			List<ChargeRecord> chargeList = ServiceCharge.getInstance().loadCharges(productId, serverId);
			int csize = chargeList.size();
			if (csize > 0) {
				for (int ci = 0; ci < csize; ci++) {
					newCharge(chargeList.get(ci));
				}
			}
		}
	}

	public static void newCharge(ChargeRecord cr) {
		StringBuilder sb = new StringBuilder();
		sb.append(cr.getId()).append("/");
		sb.append(cr.getOrderId()).append("/");
		sb.append(cr.getGameId()).append("/");
		sb.append(cr.getServerId()).append("/");
		sb.append(cr.getChannelId()).append("/");
		sb.append(cr.getAccountId()).append("/");
		sb.append(cr.getRoleId()).append("/");
		sb.append(cr.getRolename()).append("/");
		sb.append(cr.getMoney()).append("/");
		sb.append(cr.getCtype()).append("/");
		sb.append(cr.getOrderform()).append("/");
		sb.append(cr.getUserid()).append("/");
		sb.append(cr.getProxy()).append("/");
		sb.append(cr.getGoodsId());

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ChargeCData.Record.id, cr.getId() + "");
		parameters.put(ChargeCData.Record.orderId, cr.getOrderId());
		parameters.put(ChargeCData.Record.gameId, cr.getGameId() + "");
		parameters.put(ChargeCData.Record.serverId, cr.getServerId() + "");
		parameters.put(ChargeCData.Record.channelId, cr.getChannelId() + "");
		parameters.put(ChargeCData.Record.accountId, cr.getAccountId() + "");
		parameters.put(ChargeCData.Record.roleId, cr.getRoleId() + "");
		parameters.put(ChargeCData.Record.rolename, cr.getRolename());
		parameters.put(ChargeCData.Record.money, cr.getMoney() + "");
		parameters.put(ChargeCData.Record.ctype, cr.getCtype() + "");
		parameters.put(ChargeCData.Record.note, cr.getNote());
		parameters.put(ChargeCData.Record.orderform, cr.getOrderform());
		parameters.put(ChargeCData.Record.proxy, cr.getProxy());
		parameters.put(ChargeCData.Record.proxyChannel, cr.getProxyChannel());
		parameters.put(ChargeCData.Record.proxyTime, cr.getProxyTime().getTime() + "");
		parameters.put(ChargeCData.Record.userid, cr.getUserid());
		parameters.put(ChargeCData.Record.channelSub, cr.getChannelSub());
		parameters.put(ChargeCData.Record.roleLevel, cr.getRoleLevel() + "");
		parameters.put(ChargeCData.Record.goodsId, cr.getGoodsId() + "");
		parameters.put(ChargeCData.Record.goodsName, cr.getGoodsName());
		parameters.put(ChargeCData.Record.goodsCount, cr.getGoodsCount() + "");
		parameters.put(ChargeCData.Record.deviceOS, cr.getDeviceOS());
		parameters.put(ChargeCData.Record.price, cr.getPrice() + "");
		parameters.put(ChargeCData.Record.deviceImei, cr.getDeviceImei());
		parameters.put(ChargeCData.Record.sign, MD5.getHashString(sb.toString()));
		try {
			AFlushChargeRecordRun run = (AFlushChargeRecordRun) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.run.FlushChargeRecordRun").newInstance();
			run.setCr(cr);
			run.setParameter(parameters);
			serverCharges.execute(run);
		} catch (Exception e) {
			LoggerError.error("加载类库异常#mmo.common.module.clazz.charge.callback.run.FlushChargeRecordRun", e);
		}
	}

	public final static void checkChargeRecord(QQChargeRecord cr) {
		try {
			ACheckProvited check = (ACheckProvited) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.channel.CheckTencentProvite").newInstance();
			check.setCr(cr);
			requestHttp("qq", check);
		} catch (Exception e) {
			LoggerError.error("检查腾讯是否发货失败", e);
		}
	}

	public static void requestHttp(String channel, IHttpRequest request) {
		if (channel == null) {
			return;
		}
		ExecutorService tpe = channelThread.get(channel);
		if (tpe != null) {
			if (tpe.isShutdown()) {
				channelThread.remove(channel);
				tpe = null;
			}
		}
		if (tpe == null) {
			if (threadCount.get() < 30) {
				threadCount.incrementAndGet();
				LoggerError.warn("创建线程池|" + channel + "|" + threadCount.get());
				tpe = Executors.newCachedThreadPool(new CThreadFactory("REQUEST_HTTP-" + channel));// new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("REQUEST_HTTP-" + channel));
				channelThread.put(channel, tpe);
			} else {
				LoggerError.warn("线程池数量达到上限|" + channel + "|" + threadCount.get());
				tpe = serverCharges;
				if (tpe.isShutdown()) {
					serverCharges = Executors.newCachedThreadPool(new CThreadFactory("REQUEST_HTTP-" + channel));// new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new CThreadFactory("REQUEST_HTTP-" + channel));
					tpe = serverCharges;
				}
			}
		}
		tpe.execute(request);

	}

	public static void pushAppOrder(IHttpRequest request) {
		pushAppOrder.execute(request);
	}

	public final static void initThread() {
		ThreadObserver.getInstance().registerHeartbeat("CHARGE-DATABASE", ChargeDatabaseHeartbeat.getInstance());
		ThreadObserver.getInstance().registerHeartbeat("CHARGE-ORDERFORM", OrderformHeartbeat.getInstance());
		ThreadObserver.getInstance().registerHeartbeat("CHECK_PROVITE", QQCheckChargeHearbeat.getInstance());
		ThreadObserver.getInstance().registerHeartbeat("APP_STORE", AppStoreHeartbeat.getInstance());
		ThreadObserver.getInstance().registerHeartbeat("IDFA_EVENT", IdfaEventHeartbeat.getInstance());
	}
}
