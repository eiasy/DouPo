package mmo.module.gm.bean;

public class BeanPaoMaDeng extends UIData {
	private String message;

	private long   timeStart;
	private long   timeStop;
	private String hourStart;
	private String hourStop;
	private int    offsetSecond;
	private byte   status;

	private long   timeEffectStart;
	private long   timeEffectStop;
	private long   timeNext;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public long getTimeStop() {
		return timeStop;
	}

	public void setTimeStop(long timeStop) {
		this.timeStop = timeStop;
	}

	public String getHourStart() {
		return hourStart;
	}

	public void setHourStart(String hourStart) {
		this.hourStart = hourStart;
	}

	public String getHourStop() {
		return hourStop;
	}

	public void setHourStop(String hourStop) {
		this.hourStop = hourStop;
	}

	public int getOffsetSecond() {
		return offsetSecond;
	}

	public void setOffsetSecond(int offsetSecond) {
		this.offsetSecond = offsetSecond;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public long getTimeEffectStart() {
		return timeEffectStart;
	}

	public void setTimeEffectStart(long timeEffectStart) {
		this.timeEffectStart = timeEffectStart;
	}

	public long getTimeEffectStop() {
		return timeEffectStop;
	}

	public void setTimeEffectStop(long timeEffectStop) {
		this.timeEffectStop = timeEffectStop;
	}

	public long getTimeNext() {
		return timeNext;
	}

	public void setTimeNext(long timeNext) {
		this.timeNext = timeNext;
	}
}
