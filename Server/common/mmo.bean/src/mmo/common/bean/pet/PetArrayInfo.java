package mmo.common.bean.pet;

import java.util.ArrayList;
import java.util.List;

public class PetArrayInfo {
	private short         arrayId;
	private List<Integer> arrayInfos;
	private int           fightValue;

	public PetArrayInfo(short arrayId) {
		this.arrayId = arrayId;
		this.arrayInfos = new ArrayList<Integer>();
	}

	public short getArrayId() {
		return arrayId;
	}

	public void setArrayId(short arrayId) {
		this.arrayId = arrayId;
	}

	public List<Integer> getArrayInfos() {
		return arrayInfos;
	}

	public void setArrayInfos(List<Integer> arrayInfos) {
		this.arrayInfos = arrayInfos;
	}

	public int getFightValue() {
		return fightValue;
	}

	public void setFightValue(int fightValue) {
		this.fightValue = fightValue;
	}

	public void addFightValue(int value) {
		this.fightValue += value;
	}

}
