package mmo.expression.calculateData;


/**
 * 整数栈
 */
public final class IntegerStack {
	/**
	 * 栈数据
	 */
	private int[] stackInfo;
	/**
	 * 栈高度
	 */
	public int stackLen;

	public IntegerStack() {
		stackInfo = new int[30];
	}

	public IntegerStack(int length) {
		stackInfo = new int[length];
	}

	public IntegerStack(int[] stack) {
		stackInfo = Array.arrayCopy(stack);
		stackLen = stack.length;
	}

	/**
	 * 初始化栈
	 */
	public void init() {

		stackLen = 0;
	}

	/**
	 * 初始化栈
	 * 
	 * @param stack
	 *            待拷贝的栈数据
	 */
	public void init(int[] stack) {
		if (stackInfo.length < stack.length) {
			stackInfo = Array.arrayCopy(stack);
		} else {
			System.arraycopy(stack, 0, stackInfo, 0, stack.length);
		}
		stackLen = stack.length;
	}

	/**
	 * 压入整数
	 * 
	 * @param intInfo
	 *            整数
	 */
	public void push(int intInfo) {
		if (stackLen >= stackInfo.length) {
			stackInfo = Array.arrayGrow(stackInfo, 3);
		}
		stackInfo[stackLen] = intInfo;
		stackLen++;
	}

	/**
	 * 弹出栈顶整数
	 */
	public int pop() {
		if (stackLen > 0) {
			stackLen--;
			return stackInfo[stackLen];
		} else {
			return -1;
		}
	}

	/**
	 * 栈是否为空
	 */
	public boolean isEmpty() {
		return stackLen <= 0;
	}

	/**
	 * 获取栈顶整数
	 */
	public int getLast() {
		int s = pop();
		push(s);
		return s;
	}
	public void free(){
		stackInfo=null;
	}
}
