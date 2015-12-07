package mmo.common.bean.sect.equip;

import org.apache.mina.core.buffer.IoBuffer;

public interface ISectEquip {
	public int getId();

	public void setId(int id);

	public short getLevel();

	public void setLevel(short level);

	public void flushData(IoBuffer buf);
	
	public void upgrade();
}
