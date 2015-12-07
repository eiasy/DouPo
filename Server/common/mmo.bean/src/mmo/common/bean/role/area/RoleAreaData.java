package mmo.common.bean.role.area;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import mmo.common.config.DuplicateConfig;

/**
 * 角色经历过的区域信息
 * 
 * @author 肖琼
 * 
 */
public class RoleAreaData {
	/** 区域的ID */
	private int             areaId             = 0;
	/** 区域可获得最大星数 */
	private int             maxTotalStar       = 0;
	/** 区域累计获得的星数 */
	private int             totalStar;
	/** 区域星数对应的奖励状态（0.为开启 1.可领取 2.已领取） */
	private Map<Byte, Byte> areaStarawardState = null;
	/** 区域通关关卡的数量 */
	private int             passGateCount      = 0;

	public RoleAreaData(int areaId) {
		this.areaId = areaId;
		this.areaStarawardState = new HashMap<Byte, Byte>();
	}

	public void release() {
		areaStarawardState.clear();
		areaStarawardState = null;
	}

	public int getTotalStar() {
		return totalStar;
	}

	public boolean setTotalStar(int totalStar) {
		if (totalStar > maxTotalStar) {
			totalStar = maxTotalStar;
		}
		this.totalStar = totalStar;
		// 检测是否更新领取状态
		byte state = 0;
		Set<Byte> stars = areaStarawardState.keySet();
		for (byte star : stars) {
			state = areaStarawardState.get(star);
			// 已开启，且达到领取条件，则设置为 可领取
			if (star <= totalStar && state == (byte) 0) {
				areaStarawardState.put(star, (byte) 1);
				return true;
			}
		}
		return false;
	}

	public void setAwardState(byte star, byte state) {
		areaStarawardState.put(star, state);
	}

	public byte getAwardState(byte star) {
		Byte state = areaStarawardState.get(star);
		if (state == null) {
			areaStarawardState.put(star, (byte) 0);
			state = 0;
		}
		if (state == 0 && this.totalStar >= star) {
			areaStarawardState.put(star, (byte) 1);
			state = 1;
		}
		return state;
	}

	public Map<Byte, Byte> getAreaStarAwardState() {
		return areaStarawardState;
	}

	public Set<Entry<Byte, Byte>> getAwardStateEntry() {
		return areaStarawardState.entrySet();
	}

	public int getAreaId() {
		return areaId;
	}

	public int getMaxTotalStar() {
		return maxTotalStar;
	}

	public void addPassGateCount() {
		this.passGateCount++;
	}

	public void setPassGateCount(int passGateCount) {
		this.passGateCount = passGateCount;
	}

	public int getPassGateCount() {
		return passGateCount;
	}

	public void setMaxTotalStar(int maxTotalStar) {
		this.maxTotalStar = maxTotalStar;
	}

	public byte areaStarState() {
		if (totalStar >= maxTotalStar) {
			return DuplicateConfig.AreaStarState.perfectPass;
		} else if (passGateCount >= (maxTotalStar / 3)) {
			return DuplicateConfig.AreaStarState.commonPass;
		}
		return DuplicateConfig.AreaStarState.unPass;
	}

}
