package mmo.module.gm.bean;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import mmo.common.protocol.command.GmPower;
import mmo.tools.util.StringUtil;

public class BeanGmAccount extends UIData {
	private Map<Integer, String> powerMap = new TreeMap<Integer, String>();
	private String               userId;
	private String               timeAddd;
	private byte                 state;
	private String               powers;
	private String               note;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTimeAddd() {
		return timeAddd;
	}

	public void setTimeAddd(String timeAddd) {
		this.timeAddd = timeAddd;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public String getPowers() {
		return powers;
	}

	public void setPowers(String powers) {
		if (powers == null) {
			this.powers = "";
		}
		powerMap.clear();
		this.powers = powers;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPowerNote() {
		StringBuilder sb = new StringBuilder();
		Set<Integer> keys = powerMap.keySet();
		for (int key : keys) {
			sb.append(powerMap.get(key)).append("|");
		}
		return sb.toString();
	}

	public static final class State {
		private static final String[] states = new String[] { "冻结", "正常" };

		public static final String getState(byte state) {
			if (state > -1 && state < states.length) {
				return states[state];
			}
			return "异常#" + state;
		}

		public static final String[] getStates() {
			return states;
		}
	}

	public boolean isPower(int power) {
		return powerMap.get(power) != null;
	}

}
