package mmo.common.bean.extension;

import java.util.ArrayList;
import java.util.List;

public class CallScore {
	private int           scoreId;
	private int           targetId;
	private List<Integer> conditions = null;

	public CallScore() {
		this.conditions = new ArrayList<Integer>();
	}

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public List<Integer> getConditions() {
		return conditions;
	}

	public void setConditions(List<Integer> conditions) {
		this.conditions = conditions;
	}

	public void addCondition(int condition) {
		if (!this.conditions.contains((Integer) condition)) {
			this.conditions.add(condition);
		}
	}
}
