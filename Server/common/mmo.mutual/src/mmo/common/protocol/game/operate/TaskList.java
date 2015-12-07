package mmo.common.protocol.game.operate;

public interface TaskList {
	/** 已接任务 */
	public final static byte Going    = 1;
	/** 可接任务 */
	public final static byte New      = 2;
	/** 循环任务 */
	public final static byte Cycle    = 3;
	/** 活动 */
	public final static byte Activity = 4;
	/** 每日任务*/
	public final static byte Everyday = 5;
}
