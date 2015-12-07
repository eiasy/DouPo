package mmo.common.bean.role.store;

import mmo.common.config.role.StoreHouseConfig;

/** 角色仓库 **/
public class StoreHouse {
	/** 仓库状态 - 0：关闭，1开启 */
	private byte  state;
	/** 仓库ID - 对应界面的子类别 */
	private short storeId;
	/** 仓库容量 */
	private short capality;
	/** 仓库有效期 */
	private long  effectTime;

	public StoreHouse() {

	}

	public final boolean isClosed() {
		return state == StoreHouseConfig.stateClosed;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public short getStoreId() {
		return storeId;
	}

	public void setStoreId(short storeId) {
		this.storeId = storeId;
	}

	public short getCapality() {
		return capality;
	}

	public void setCapality(short capality) {
		this.capality = capality;
	}

	public long getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(long effectTime) {
		this.effectTime = effectTime;
	}

	@Override
    public String toString() {
	    return "StoreHouse [state=" + state + ", storeId=" + storeId + ", capality=" + capality + ", effectTime=" + effectTime + "]";
    }
}
