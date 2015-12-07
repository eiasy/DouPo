package mmo.common.bean.sect;

import mmo.common.bean.sect.arm.IProductArm;

import org.apache.mina.core.buffer.IoBuffer;


public interface ISectArm extends IProductArm {

	boolean isOut();

	void death();

	int getCount();

	public void flushData(IoBuffer buf);

	void setCount(int count);

	/**
	 * 数量发生改变
	 * 
	 * @param offset
	 *            变化量
	 * @return 剩余量
	 */
	int changeCount(int offset);
}
