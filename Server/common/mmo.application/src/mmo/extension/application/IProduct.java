package mmo.extension.application;

public interface IProduct {
	/** 成功为应用添加一个主机 */
	public static final byte RESULT_SUCCESS  = 0;
	/** 成功为应用添加一个主机 */
	public static final byte RESULT_FAIL     = 1;
	/** 添加主机时key已经被占用 */
	public static final byte RESULT_REPEAT   = 2;

	public static final int  PRODUCT_GATEWAY = 1;
	public static final int  PRODUCT_RL      = 2;
}
