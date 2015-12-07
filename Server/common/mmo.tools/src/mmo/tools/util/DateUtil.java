/**
 * 类名：Tools 日期：2007-06-12 功能：该类提供了以下公共接口： 1.GetDate():获取当前日期，精确到秒。 2.DealQuoteMark(String)：处理字符串中的单引号和双引号。
 * 3.DealEnterSymbol(String)：以换行符为分界线进行截取字符串。 4.GetRand(int, int)：得到两个非负数范围内的随机值(包括这两个数,第二个数大于等于第一个数)。
 * 5.GetFileByteData(String)：将一个文件里的数据读入到一个字节数组中。 6.FileIsExist(String)：判断一个文件是否存在。 7.IsProbability(int,
 * int)：判断是否在一个几率范围内。 8.GetCurrentTime():得到当前时间 年-月-日 时:分:秒。 9.GetCurrentDate()：得到当前日期 年-月-日。 10.GetChuanMa()：得到串码(17位
 * 13位 unix 4位 随机。
 */

package mmo.tools.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mmo.tools.log.LoggerError;

/**
 * 工具类
 * 
 * 日期：2007-06-12 功能：提供获取格式化的日期 作者：李天喜
 */
public class DateUtil {

	/** 一天对应的毫秒值 */
	public static final long    ONE_DAY_M    = 24 * 60 * 60 * 1000;

	/** 一小时对应的毫秒值 */
	public static final long    ONE_HOUR_M   = 60 * 60 * 1000;
	/** 一分钟对应的毫秒值 */
	public static final long    ONE_MINUTE_M = 60 * 1000;

	public static final String  DATE_FORMAT  = "yyyy-MM-dd HH:mm:ss";
	private final static String HH_mm        = "HH:mm";
	private final static String HH_mm_ss     = "HH:mm:ss";
	private final static String yyyy_MM_dd   = "yyyy-MM-dd";

	/**
	 * 得到当前时间 年-月-日 时:分:秒
	 */
	public static String getCurrentDateTime() {
		Date _date = new Date();
		DateFormat _dateformat = new SimpleDateFormat(DATE_FORMAT);
		return _dateformat.format(_date);
	}

	public static String formatDatetime(Date date) {
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}

	/**
	 * 得到当前时间 年-月-日 时:分:秒
	 */
	public static String getCurrentHourTime() {
		Date _date = new Date();
		DateFormat _dateformat = new SimpleDateFormat(HH_mm);
		return _dateformat.format(_date);
	}

	/**
	 * 得到当前时间 时:分:秒
	 */
	public static String getCurrentDetailTime() {
		Date _date = new Date();
		DateFormat _dateformat = new SimpleDateFormat(HH_mm_ss);
		return _dateformat.format(_date);
	}

	/**
	 * 得到当前日期 年-月-日
	 */
	public static String getCurrentDate() {
		DateFormat _dateformat = new SimpleDateFormat(yyyy_MM_dd);
		return _dateformat.format(new Date());
	}

	/**
	 * 得到当前日期 年-月-日
	 */
	public static String getDateOfMonth(Date D_date) {
		DateFormat _dateformat = new SimpleDateFormat(yyyy_MM_dd);
		return _dateformat.format(D_date);
	}

	/**
	 * 得到当前日期 年-月-日
	 */
	public static String getyyyyMMdd(long datetime) {
		DateFormat _dateformat = new SimpleDateFormat(yyyy_MM_dd);
		return _dateformat.format(new Date(datetime));
	}

	/**
	 * 格式化浮点数 参数：要格式化的值 返回：格式化后的浮点值
	 */
	public static double formatDouble(double _double) {
		DecimalFormat DF_numberformat = new DecimalFormat("#.0");
		return Double.parseDouble(DF_numberformat.format(_double));
	}

	/**
	 * 获取当前时间在一天中所处的时间段
	 * 
	 * @return 1：24-6；2：6-12；3：12-18；4：18-24
	 */
	public static int CurrentClock() {
		Calendar clock = Calendar.getInstance();
		return (clock.get(Calendar.HOUR_OF_DAY) / 6) + 1;
	}

	/**
	 * 获取当前时间：小时
	 * 
	 * @return
	 */
	public static int getCurrentHour() {
		Calendar clock = Calendar.getInstance();
		return clock.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取指定时间的小时数
	 * 
	 * @param pointCurrTime
	 * @return
	 */
	public static int getPointHour(long pointCurrTime) {
		Calendar clock = Calendar.getInstance();
		clock.setTimeInMillis(pointCurrTime);
		return clock.get(Calendar.HOUR_OF_DAY);
	}

	public static String getJtime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(date);
	}

	/**
	 * 获取当前日期，精确到 秒
	 * 
	 * 返回：字符串型的时间
	 */
	public static String getDate() {
		Date _date = new Date();
		DateFormat _dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return _dateFormat.format(_date);
	}

	/**
	 * 获取当前日期，精确到 秒
	 * 
	 * 返回：字符串型的时间
	 */
	public static String getDate(String format) {
		Date _date = new Date();
		DateFormat _dateFormat = new SimpleDateFormat(format);
		return _dateFormat.format(_date);
	}

	public static final String formatDate(Date date, String format) {
		DateFormat _dateFormat = new SimpleDateFormat(format);
		return _dateFormat.format(date);
	}

	/**
	 * 格式化时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public final static String formatDate(Date date) {
		DateFormat _dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return _dateFormat.format(date);
	}

	public static Date stringToDate(String dateStr, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date stringToDate(String dateStr) {
		DateFormat dd = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			LoggerError.error(dateStr, e);
		}
		return date;
	}

	/**
	 * 今天0点的毫秒数
	 * 
	 * @param dateStr
	 * @return
	 */
	public static long getCurrentDateSec() {
		return getCurrentDateSec(new Date());
	}

	/**
	 * 今天0点的毫秒数
	 * 
	 * @param dateStr
	 * @return
	 */
	public static long getCurrentDateSec(Date dateSrc) {
		if (dateSrc == null) {
			dateSrc = new Date();
		}
		DateFormat dd = new SimpleDateFormat(yyyy_MM_dd);
		String todayString = dd.format(dateSrc);

		Date date = null;
		try {
			date = dd.parse(todayString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	/**
	 * 今天0点的分钟数
	 * 
	 * @param dateStr
	 * @return
	 */
	public static long getCurrentDateMin() {
		return getCurrentDateSec() / 1000 / 60;
	}

	/**
	 * 获取当前时间：月
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		Calendar clock = Calendar.getInstance();
		clock.setTime(new Date());
		return clock.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前月中的某天
	 * 
	 * @return
	 */
	public static int getDayOfMonth() {
		Calendar clock = Calendar.getInstance();
		return clock.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前年中的某天
	 * 
	 * @return
	 */
	public static int getDayOfYear() {
		Calendar clock = Calendar.getInstance();
		return clock.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取星期几
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取当前是本年的第几周
	 * 
	 * @return
	 */
	public static int getWeekOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份最大的天数
	 * 
	 * @return
	 */
	public static int getMaxDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 通过固定格式的时间获取毫秒数
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static long stringToDec(String dateStr, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 通过固定格式的时间获取毫秒数
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static long stringToDec(String dateStr) {
		return stringToDec(dateStr, DATE_FORMAT);
	}

	public static long getCurrDayMillisecond(int hour, int minute, int second) {
		hour = Math.abs(hour % 24);
		minute = Math.abs(minute % 60);
		second = Math.abs(second % 60);
		StringBuilder sb = new StringBuilder();
		sb.append(DateUtil.getCurrentDate());
		if (hour < 10) {
			sb.append(" 0").append(hour);
		} else {
			sb.append(" ").append(hour);
		}
		if (minute < 10) {
			sb.append(":0").append(minute);
		} else {
			sb.append(":").append(minute);
		}
		if (second < 10) {
			sb.append(":0").append(second);
		} else {
			sb.append(":").append(second);
		}
		return DateUtil.stringToDate(sb.toString()).getTime();
	}
}
