package mmo.common.system;

import java.util.Calendar;

public class GameSystem {
	private long              systemExitTime;

	/*****************************************************************/
	private static GameSystem instance   = new GameSystem();
	private Calendar          systemTime = Calendar.getInstance();
	public static final long  ONEDAYMS   = 24 * 60 * 60 * 1000;
	public static final long  ONE_HOUR   = 1000 * 60 * 60;

	public static final GameSystem getInstance() {
		if (instance == null) {
			instance = new GameSystem();
		}
		return instance;
	}

	private GameSystem() {

	}

	public final static long getCurrDayMillisecond() {
		return (System.currentTimeMillis() + ONE_HOUR * 8) % ONEDAYMS;
	}

	public final static long getTimeDayMillisecond(int milliseconOfDay) {
		long time = getCurrDayMillisecond();
		return System.currentTimeMillis() - (time % ONEDAYMS) + milliseconOfDay;
	}

	public final int getYear() {
		return systemTime.get(Calendar.YEAR);
	}

	public final int getMonth() {
		/**
		 * @alter
		 * @name：xiaoqiong
		 * @date：13-5-8
		 * @note：Calender月份从0开始，为了获取真实月份所以+1
		 */
		return systemTime.get(Calendar.MONTH) + 1;
	}

	public final int getDayOfMonth() {
		return systemTime.get(Calendar.DAY_OF_MONTH);
	}

	private final void refreshTime() {
		systemTime.setTimeInMillis(System.currentTimeMillis());
	}

	protected final void refreshTime(long currTime) {
		systemTime.setTimeInMillis(currTime);
	}

	public void setSystemTime(Calendar systemTime) {
		this.systemTime = systemTime;
	}

	public int getDayOfYear() {
		refreshTime();
		return systemTime.get(Calendar.DAY_OF_YEAR);
	}

	public int getHourOfDay() {
		refreshTime();
		return systemTime.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinuteOfHour() {
		refreshTime();
		return systemTime.get(Calendar.MINUTE);
	}

	public int getMinuteOfDay() {
		refreshTime();
		return systemTime.get(Calendar.HOUR_OF_DAY) * 60 + systemTime.get(Calendar.MINUTE);
	}

	public long getSystemExitTime() {
		return systemExitTime;
	}

	public void setSystemExitTime(long systemExitTime) {
		this.systemExitTime = systemExitTime;
	}

	public Calendar getSystemTime() {
		return systemTime;
	}

}
