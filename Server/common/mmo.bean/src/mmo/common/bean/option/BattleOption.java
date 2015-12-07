package mmo.common.bean.option;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 战斗设置项
 * 
 * @author fanren
 * 
 */
public class BattleOption extends GameOption {
	/** 对应的ID */
	protected int     id;
	/** 是否自动购买 */
	protected boolean autoBuy;
	/** 是否自动加血 */
	protected boolean autoAdd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void packetData(IoBuffer packet) {
		packet.putShort(key);
		packet.putInt(value);
		packet.putInt(id);
		packet.putBoolean(autoAdd);
		packet.putBoolean(autoBuy);
	}

	public void initData(DataInputStream dis) throws IOException {
		value = dis.readInt();
		id = dis.readInt();
		autoAdd = dis.readBoolean();
		autoBuy = dis.readBoolean();
	}

	public boolean isAutoBuy() {
		return autoBuy;
	}

	public void setAutoBuy(boolean autoBuy) {
		this.autoBuy = autoBuy;
	}

	public boolean isAutoAdd() {
		return autoAdd;
	}

	public void setAutoAdd(boolean autoAdd) {
		this.autoAdd = autoAdd;
	}
}
