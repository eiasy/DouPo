package mmo.common.bean.extension;

import java.util.Map;

public class ProgressState {
	private int                maxProgress    = 0;
	private int                curProgress    = 0;
	private byte               awardState     = 0;   // 0.未领取 1.可领取 2。已领取
	private int                awardId        = 0;   // 奖励目标ID
	private Map<Integer, Byte> conditionState = null; // 状态（0.未达成 1.达成）

	public ProgressState() {
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

	public int getCurProgress() {
		return curProgress;
	}

	public void setCurProgress(int curProgress) {
		this.curProgress = curProgress;
	}

	public byte getAwardState() {
		return awardState;
	}

	public void setAwardState(byte awardState) {
		this.awardState = awardState;
	}

	public Map<Integer, Byte> getConditionState() {
		return conditionState;
	}

	public void setConditionState(Map<Integer, Byte> conditionState) {
		this.conditionState = conditionState;
	}

	public int getAwardId() {
		return awardId;
	}

	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}

	public byte getConditionState(int goodsId) {
		Byte value = conditionState.get(goodsId);
		if (value == null) {
			return 0;
		}
		return value;
	}

	public boolean isFitCondition(Integer goodsId) {
		if (this.conditionState.containsKey(goodsId)) {
			this.conditionState.put(goodsId, (byte) 1);
			++curProgress;
			if (curProgress >= maxProgress) {
				curProgress = maxProgress;
				awardState = 1;
			}
			return true;
		}
		return false;
	}

}
