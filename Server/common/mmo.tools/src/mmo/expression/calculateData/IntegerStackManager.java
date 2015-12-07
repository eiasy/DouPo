package mmo.expression.calculateData;

/**
 * 整数栈管理器, 固定大小, 循环分配
 */
public final class IntegerStackManager {

	// 几乎不可能用那么多
	private static IntegerStack[] stackArray = new IntegerStack[30];
	static {
		for (int i = 0; i < stackArray.length; i++) {
			stackArray[i] = new IntegerStack();
		}
	}

	/**
	 * 已被用过的栈ID
	 */
	private static int            curStackId = -1;

	public static IntegerStack getStack() {
		curStackId = ++curStackId >= stackArray.length ? 0 : curStackId;
		return stackArray[curStackId];
	}
}
