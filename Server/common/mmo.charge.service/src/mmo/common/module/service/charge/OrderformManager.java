package mmo.common.module.service.charge;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.service.OrderformService;
import mmo.common.module.service.charge.service.ServiceCharge;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.common.module.service.charge.thread.run.AddOrderform2DBRun;
import mmo.common.module.service.charge.thread.run.UpdateOrderform2DBRun;
import mmo.tools.thread.runnable.IExecuteEntity;

public class OrderformManager implements IExecuteEntity {
	private final static int              OVERTIME = 1000 * 60 * 60 * 8;
	private final static OrderformManager instance = new OrderformManager();

	public final static OrderformManager getInstance() {
		return instance;
	}

	/** 数据库操作事件队列 */
	private Queue<ChargeOrderform>       orderformQueue    = new ConcurrentLinkedQueue<ChargeOrderform>();
	private Map<Long, ChargeRecord>      chargeRecordMap   = new ConcurrentHashMap<Long, ChargeRecord>();
	private Queue<ChargeRecord>          chargeRecordQueue = new ConcurrentLinkedQueue<ChargeRecord>();
	private Map<String, ChargeOrderform> orderformMap      = new ConcurrentHashMap<String, ChargeOrderform>();

	private OrderformManager() {

	}

	public void init() {
		List<ChargeRecord> list = ServiceCharge.getInstance().loadUnhandledCharges();
		for (int li = 0; li < list.size(); li++) {
			addChargeRecord(list.get(li));
		}
	}

	@Override
	public void run(long arg0) {
		ChargeOrderform orderform = null;
		while ((orderform = orderformQueue.peek()) != null) {
			if (orderform.isHadled()) {
				orderformMap.remove(orderform.getOrderform());
				orderformQueue.poll();
				continue;
			}

			if (orderform.isOvertime()) {
				orderformMap.remove(orderform.getOrderform());
				orderformQueue.poll();
			} else {
				break;
			}
		}

		ChargeRecord cr = null;
		while ((cr = chargeRecordQueue.peek()) != null) {
			if (cr.getState() == 2) {
				chargeRecordMap.remove(cr.getId());
				chargeRecordQueue.poll();
				continue;
			}

			if (cr.isFlush(arg0)) {
				ThreadManager.newCharge(cr);
				chargeRecordQueue.poll();
				chargeRecordQueue.offer(cr);
			} else {
				return;
			}
		}
	}

	public void handleChargeRecord(long crId) {
		ChargeRecord cr = chargeRecordMap.remove(crId);
		if (cr != null) {
			cr.setState((byte) 2);
		}
	}

	public void addChargeRecord(ChargeRecord cr) {
		cr.resetFlushTime();
		chargeRecordMap.put(cr.getId(), cr);
		chargeRecordQueue.offer(cr);
	}

	public void addOrderform(ChargeOrderform orderform) {
		orderform.setOvertime(System.currentTimeMillis() + OVERTIME);
		orderformMap.put(orderform.getOrderform(), orderform);
		orderformQueue.offer(orderform);
		ChargeDatabaseHeartbeat.getInstance().execute(new AddOrderform2DBRun(orderform));
	}

	public void addAppStoreOrderform(ChargeOrderform orderform) {
		ChargeDatabaseHeartbeat.getInstance().execute(new AddOrderform2DBRun(orderform));
	}

	public ChargeOrderform getOrderform(String orderform) {
		ChargeOrderform co = orderformMap.get(orderform);
		if (co == null) {
			return OrderformService.getInstance().getOrderform(orderform);
		}
		return co;
	}

	public void hadleOrderform(ChargeOrderform orderform) {
		orderform.setStatus(ChargeOrderform.STATUS_1_HANDLED);
		orderformMap.remove(orderform.getOrderform());
		ChargeDatabaseHeartbeat.getInstance().execute(new UpdateOrderform2DBRun(orderform));
	}

	public void hadleAppStoreOrderform(ChargeOrderform orderform) {
		orderform.setStatus(ChargeOrderform.STATUS_1_HANDLED);
		ChargeDatabaseHeartbeat.getInstance().execute(new UpdateOrderform2DBRun(orderform));
	}

}
