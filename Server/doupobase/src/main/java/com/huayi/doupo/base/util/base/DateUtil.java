package com.huayi.doupo.base.util.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * 
 * @author mp
 * @date 2014-7-22 上午11:37:16
 */
public class DateUtil {

	private static String DEFDATETEPAT = "yyyy-MM-dd HH:mm:ss";

	// yyyy-MM-dd HH:mm:ss.SSS Z

	private static String DEFDAYPAT = "yyyy-MM-dd";

	public enum DateType {
		Year, Month, Day, DayOfWeek, HOUR_OF_DAY, Minute, Second
	}

	public enum DateFormat {
		YMD, YMDHMS
	}

	/**
	 * 获取年月日时分秒组合 eg:2015729171014
	 * 
	 * @author mp
	 * @date 2015-7-29 下午5:10:22
	 * @return
	 * @Description
	 */
	public static String getYmdHmsNum() {
		return getTimeInfo(DateType.Year) + "" + getTimeInfo(DateType.Month) + "" + getTimeInfo(DateType.Day) + "" + getTimeInfo(DateType.HOUR_OF_DAY) + "" + getTimeInfo(DateType.Minute) + "" + getTimeInfo(DateType.Second);
	}

	/**
	 * 获取当前时间相关信息 someDay格式为年月日格式
	 * 
	 * @author mp
	 * @date 2014-7-22 下午3:54:45
	 * @param dateType
	 * @return
	 * @Description
	 */
	public static int getTimeInfo(DateType dateType) {
		return getTimeInfo(dateType, Calendar.getInstance());
	}

	/**
	 * 获取某个时间相关信息 someDay格式为年月日格式
	 * 
	 * @author mp
	 * @date 2014-7-22 下午4:03:28
	 * @param someDay
	 * @param dateType
	 * @return
	 * @Description
	 */
	public static int getTimeInfo(String someDay, DateType dateType) {
		int result = 0;
		try {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new SimpleDateFormat(DEFDAYPAT).parse(someDay));
			result = getTimeInfo(dateType, calendar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取某个时间相关信息 someDay 为时分秒格式
	 * 
	 * @author hzw
	 * @date 2015-6-9上午11:47:39
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int getTimeInfoHms(String someDay, DateType dateType) {
		int result = 0;
		try {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new SimpleDateFormat(DEFDATETEPAT).parse(someDay));
			result = getTimeInfo(dateType, calendar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取时间信息 -私有方法
	 * 
	 * @author mp
	 * @date 2014-7-22 下午4:15:27
	 * @param dateType
	 * @param calendar
	 * @return
	 * @Description
	 */
	private static int getTimeInfo(DateType dateType, Calendar calendar) {
		int result = 0;
		switch (dateType) {
			case Year:
				result = calendar.get(Calendar.YEAR);
				break;
			case Month:
				result = calendar.get(Calendar.MONTH) + 1;
				break;
			case Day:
				result = calendar.get(Calendar.DAY_OF_MONTH);
				break;
			case DayOfWeek:
				result = (calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : (calendar.get(Calendar.DAY_OF_WEEK) - 1);
				break;
			case HOUR_OF_DAY:
				result = calendar.get(Calendar.HOUR_OF_DAY);
				break;
			case Minute:
				result = calendar.get(Calendar.MINUTE);
				break;
			case Second:
				result = calendar.get(Calendar.SECOND);
				break;
		}
		return result;
	}

	/**
	 * 计算两日期间相差天数
	 * 
	 * @author mp
	 * @date 2014-2-20 下午2:49:24
	 * @param nowDate
	 * @param beforeDate
	 * @return
	 * @Description
	 */
	public static int dayDiff(String oneDay, String twoDay) {
		return Math.abs(dayCompare(oneDay, twoDay));
	}

	/**
	 * 两日期比较 返回值=0 同一天 返回值>0 oneDay > twoDay 返回值<0 oneDay < twoDay
	 * 
	 * @author mp
	 * @date 2014-7-22 下午3:22:30
	 * @param nowDate
	 * @param beforeDate
	 * @return
	 * @Description
	 */
	public static int dayCompare(String oneDay, String twoDay) {
		long between_days = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFDAYPAT);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(twoDay));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(oneDay));
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (int) between_days;
	}

	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @author mp
	 * @date 2014-3-5 上午11:27:50
	 * @return
	 * @Description
	 */
	public static String getCurrTime() {
		return new java.text.SimpleDateFormat(DEFDATETEPAT).format(new Date());
	}

	/**
	 * 获取当前时间毫秒数
	 * 
	 * @author mp
	 * @date 2014-8-14 下午1:58:01
	 * @return
	 * @Description
	 */
	public static long getCurrMill() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取当前时间秒数
	 * 
	 * @author mp
	 * @date 2015-3-17 下午3:38:31
	 * @return
	 * @Description
	 */
	public static long getCurrSec() {
		return getCurrMill() / 1000;
	}

	/**
	 * 将毫秒数转换成时间 yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @author mp
	 * @date 2013-11-8 上午11:48:33
	 * @param mill
	 * @return
	 * @Description
	 */
	public static String getTimeByMill(long mill) {
		return new SimpleDateFormat(DEFDATETEPAT).format(mill);
	}

	/**
	 * 根据时间返回毫秒数
	 * 
	 * @author mp
	 * @date 2013-11-8 上午11:51:23
	 * @param formateDate
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static long getMillSecond(String formateDate) throws Exception {
		return new SimpleDateFormat(DEFDATETEPAT).parse(formateDate).getTime();
	}

	/**
	 * 根据时间返回毫秒数
	 * 
	 * @author mp
	 * @date 2013-11-8 上午11:51:23
	 * @param formateDate
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static long string2MillSecond(String formateDate) {
		try {
			return new SimpleDateFormat(DEFDATETEPAT).parse(formateDate).getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 根据日期获取年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static int getAge(String strbirthDay) {
		return 0;
	}

	/**
	 * 验证是否为同一天
	 * 
	 * @author mp
	 * @date 2013-11-7 上午10:12:16
	 * @param millOne
	 * @param millTwo
	 * @return
	 * @Description
	 */
	public static boolean isSameDay(long millOne, long millTwo) {
		return new SimpleDateFormat(DEFDAYPAT).format(millOne).equals(new SimpleDateFormat(DEFDAYPAT).format(millTwo));
	}

	/**
	 * 验证是否为同一天
	 * 
	 * @author hzw
	 * @date 2014-8-28 下午4:39:17
	 * @param oneDay
	 * @param twoDay
	 * @param dateFormat
	 * @return
	 * @Description
	 */
	public static boolean isSameDay(String oneDay, String twoDay, DateFormat dateFormat) {
		boolean isSameDay = true;
		switch (dateFormat) {
			case YMD:
				isSameDay = oneDay.equals(twoDay);
				break;
			case YMDHMS:
				isSameDay = getYmdDate(oneDay).equals(getYmdDate(twoDay));
				break;
		}
		return isSameDay;
	}

	/**
	 * 获取某一天的 年月日格式
	 * 
	 * @author mp
	 * @date 2013-11-8 下午7:08:23
	 * @param day
	 * @return
	 * @Description
	 */
	public static String getYmdDate(String someDay) {
		return someDay.substring(0, 10);
	}

	/**
	 * 获取某一天的 时分秒格式
	 * 
	 * @author mp
	 * @date 2013-11-8 下午7:08:23
	 * @param day
	 * @return
	 * @Description
	 */
	public static String getHmsDate(String someDay) {
		return someDay.substring(11);
	}

	/**
	 * 获取当前时间的 年月日格式
	 * 
	 * @author mp
	 * @date 2014-2-10 下午1:36:02
	 * @return
	 * @Description
	 */
	public static String getYmdDate() {
		return getTimeByMill(System.currentTimeMillis()).substring(0, 10);
	}

	/**
	 * 获取当前时间的 时分秒格式
	 * 
	 * @author mp
	 * @date 2014-7-22 下午3:28:12
	 * @return
	 * @Description
	 */
	public static String getHmsDate() {
		return getTimeByMill(System.currentTimeMillis()).substring(11);
	}

	/**
	 * 获取加/减 N天的日期，格式是yyyy-mm-dd
	 * 
	 * @author mp
	 * @date 2014-1-15 下午12:09:40
	 * @param num
	 * @return
	 * @Description
	 */
	public static String getNumDayDate(int num) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(DEFDAYPAT);
		return formatter.format(date);
	}

	/**
	 * 获取加/减 N天的日期，格式是yyyy-mm-dd
	 * 
	 * @author mp
	 * @date 2014-4-1 上午10:07:44
	 * @param someDay
	 * @param num
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static String getNumDayDate(String someDay, int num) throws Exception {
		Date date = new SimpleDateFormat(DEFDAYPAT).parse(someDay);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(DEFDAYPAT);
		return formatter.format(date);
	}

	public static void main(String[] args) {
		try {
			
			String a2 = "2015-08-24 16:46:30"; //1440405990000   21名         pId 318
			
			System.out.println(getMillSecond(a2));
			
			/*String startTime = "2014-08-25 17:03:12";  //开始时间
			String currTime = "2014-09-26 15:00:10";	//当前时间
			int jg = 2;  //间隔时间
			long jgm = jg * 60 * 60 * 1000;  //间隔毫秒数
			long startm = getMillSecond(startTime);
			long currm = getMillSecond(currTime);
			
			long intval = jgm - ((currm - startm) % jgm);
			long hh = intval / (60*60*1000);
			long mm = (intval - (hh*60*60*1000))  / (60*1000);
			long ss = ((intval - (hh*60*60*1000))  - (mm*60*1000)) / 1000;
			System.out.println(hh + ":" + mm + ":" + ss);
			System.out.println(DateUtil.dayDiff(startTime, currTime));*/
			/*
			 * String startTime = "2014-08-25 17:03:12"; //开始时间 String currTime = "2014-09-26 15:00:10"; //当前时间 int jg = 2; //间隔时间 long jgm = jg * 60 * 60 * 1000; //间隔毫秒数 long startm = getMillSecond(startTime); long currm = getMillSecond(currTime);
			 * 
			 * long intval = jgm - ((currm - startm) % jgm); long hh = intval / (60*60*1000); long mm = (intval - (hh*60*60*1000)) / (60*1000); long ss = ((intval - (hh*60*60*1000)) - (mm*60*1000)) / 1000; System.out.println(hh + ":" + mm + ":" + ss); System.out.println(DateUtil.dayDiff(startTime, currTime));
			 */

			// long a = 1430817778000L;
			// System.out.println(a);
			// System.out.println(getTimeByMill(a));

			// dayDiff
			// System.out.println(getNumDayDate("2015-02-23", 55));

			// System.out.println(getNumDayDate(-3));

			// System.out.println(getTimeInfo(DateType.DayOfWeek));
			// System.out.println(getYmdDate() + " 00:00:00");

			// System.out.println(DateUtil.getTimeInfo(DateUtil.getNumDayDate(DateUtil.getCurrTime(), -1),DateType.DayOfWeek));
			// System.out.println(DateUtil.dayCompare("2015-08-25 00:00:00", DateUtil.getCurrTime()));

			// System.out.println(DateUtil.dayDiff("2015-01-27 23:12:12", "2015-01-27 18:12:12"));

			// String oldDate = "2015-04-13 11:24:47";
			// System.out.println(getMillSecond(oldDate));
			//
			// String day = "2015-07-06";
			// System.out.println(getMillSecond(day + " 00:00:00"));

//			System.out.println(getTimeInfo(DateType.DayOfWeek));

			// String day2 = "2015-07-06";
			// System.out.println(dayCompare(day2, day));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
