package mmo.common.bean.vip;

import java.util.HashMap;
import java.util.Map;

/**
 * VIP功能数据
 * 
 * @author 肖琼
 * 
 */
public class VipFunction {
	private int                   id;
	private boolean               isOpen;
	/** Vip等级与限制数据 */
	private Map<Short, LimitData> limitDatas = null;

	public VipFunction(int id) {
		this.id = id;
		this.limitDatas = new HashMap<Short, LimitData>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public void addLimitData(short vipLv, LimitData data) {
		this.limitDatas.put(vipLv, data);
	}

	public int getDefaultCount(short vipLv) {
		LimitData data = limitDatas.get(vipLv);
		if (data == null) {
			return 0;
		}
		return data.getDefaultCount();
	}

	public LimitData getVipLimitData(short vipLv) {
		return limitDatas.get(vipLv);
	}
}
