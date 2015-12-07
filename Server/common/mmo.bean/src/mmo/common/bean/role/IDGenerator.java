package mmo.common.bean.role;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import mmo.tools.log.LoggerError;

public class IDGenerator {
	/** ID生成器 */
	private final static AtomicInteger                  otherRoleIdGenerator = new AtomicInteger(1);
	/** ID池 */
	private final static ConcurrentLinkedQueue<Integer> otherRoleIdPool      = new ConcurrentLinkedQueue<Integer>();

	private static AtomicInteger                        userIdGenerator      = null;

	public final static void setUserIdGenerator(AtomicInteger userIdGenerator) {
		IDGenerator.userIdGenerator = userIdGenerator;
	}

	public final static int nextUserId() {
		if (userIdGenerator == null) {
			LoggerError.messageLog.error("userIdGenerator 未初始化！");
			return 0;
		}
		return userIdGenerator.incrementAndGet();
	}

	public static final int nextSceneRoleId() {
		Integer roleId = otherRoleIdPool.poll();
		if (roleId != null) {
			return roleId;
		}
		return otherRoleIdGenerator.incrementAndGet();
	}

	public static final void resetRoleId(int id) {
		if (id > otherRoleIdGenerator.get()) {
			otherRoleIdGenerator.set(id);
		}
	}

	public static final void freeWorldId(int id) {
		otherRoleIdPool.offer(id);
	}
}
