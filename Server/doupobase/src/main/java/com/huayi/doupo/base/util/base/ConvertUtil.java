package com.huayi.doupo.base.util.base;

import java.text.DecimalFormat;

/**
 * java数值类型转换工具类
 * @author mp
 * @date 2014-5-6 上午11:25:48
 */
public class ConvertUtil {
	
	/**
	 * 将float转化成int-四舍五入
	 * @author mp
	 * @version 1.0, 2013-8-7 下午1:14:15
	 * @param f
	 * @return
	 */
	public static int toInt(float f) {
		return Math.round(f);
	}
	
	/**
	 * 将string转化成int
	 * @author mp
	 * @date 2013-10-17 下午3:13:02
	 * @param str
	 * @return
	 * @Description
	 */
	public static int toInt(String str) {
		return Integer.valueOf(str);
	}
	
	/**
	 * 将string转化成float
	 * @author mp
	 * @date 2014-6-25 上午11:38:17
	 * @param str
	 * @return
	 * @Description
	 */
	public static float toFloat (String str) {
		return Float.valueOf(str);
	}
	
	/**
	 * 将string转化成long
	 * @author mp
	 * @date 2013-11-7 上午10:19:29
	 * @param str
	 * @return
	 * @Description
	 */
	public static long toLong (String str) {
		return Long.valueOf(str);
	}
	
	/**
	 * 将double转化成long
	 * @author mp
	 * @date 2013-11-22 上午10:10:53
	 * @param dou
	 * @return
	 * @Description
	 */
	public static long toLong (double dou) {
		return (long)dou;
	}
	
	/**
	 * 将string转化成double
	 * @author mp
	 * @date 2014-6-25 上午11:39:57
	 * @param str
	 * @return
	 * @Description
	 */
	public static double toDouble (String str) {
		return Double.valueOf(str);
	}
	
	/**
	 * 保留两位小数
	 * @author mp
	 * @date 2014-5-16 上午10:55:46
	 * @param t
	 * @return
	 * @Description
	 */
	public static <T extends Number> Float twoDecimal (T t) {
		return Float.valueOf(new DecimalFormat("#.##").format(t));
	}
	
	/**
	 * 将数字格式化成%的格式
	 * @author mp
	 * @date 2014-5-16 上午10:43:58
	 * @param t
	 * @return
	 * @Description
	 */
	public static <T extends Number> String toPer(T t) {
		return new java.text.DecimalFormat("%").format(t);
	}
	
	
	public static void main(String[] args) {
		float a = 1.4553f;
		System.out.println(twoDecimal(a));
	}
	
}
