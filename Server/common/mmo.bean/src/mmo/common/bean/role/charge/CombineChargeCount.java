package mmo.common.bean.role.charge;

import java.sql.Timestamp;

public class CombineChargeCount extends ChargeCount {
	private Timestamp timeFirst;
	private Timestamp timeLast;

	public Timestamp getTimeFirst() {
		return timeFirst;
	}

	public void setTimeFirst(Timestamp timeFirst) {
		this.timeFirst = timeFirst;
	}

	public Timestamp getTimeLast() {
		return timeLast;
	}

	public void setTimeLast(Timestamp timeLast) {
		this.timeLast = timeLast;
	}
}
