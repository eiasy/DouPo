package mmo.common.bean.role;

import java.sql.Timestamp;

public class CombineFriend extends Friend {
	private Timestamp timeJoin;
	private Timestamp timeUpdate;
	private byte      fstate;

	public Timestamp getTimeJoin() {
		return timeJoin;
	}

	public void setTimeJoin(Timestamp timeJoin) {
		this.timeJoin = timeJoin;
	}

	public Timestamp getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(Timestamp timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	public byte getFstate() {
		return fstate;
	}

	public void setFstate(byte fstate) {
		this.fstate = fstate;
	}
}
