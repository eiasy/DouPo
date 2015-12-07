package mmo.common.bean.role.data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import mmo.common.bean.role.confirm.AConfirmEvent;

public class RoleDataManager {
	private static RoleDataManager             instance          = new RoleDataManager();
	private static AtomicInteger               generateConfirmId = new AtomicInteger(1);
	private static Map<Integer, AConfirmEvent> confirmEvents     = new HashMap<Integer, AConfirmEvent>();

	public final static AConfirmEvent addConfirmObject(AConfirmEvent ce) {
		ce.setId(generateConfirmId.incrementAndGet());
		confirmEvents.put(ce.getId(), ce);
		return ce;
	}

	/**
	 * 获取确认事件
	 * 
	 * @param confirmEventId
	 *            事件编号
	 * @return
	 */
	public static final AConfirmEvent getConfirmEvent(int confirmEventId) {
		return confirmEvents.remove(confirmEventId);
	}

	public final static RoleDataManager getInstance() {
		if (instance == null) {
			instance = new RoleDataManager();
		}
		return instance;
	}

	private RoleDataManager() {

	}
}
