package com.hygame.dpcq.tools;


public class Convert {
	
	/**
	 * 将float转化成int
	 * @author mp
	 * @version 1.0, 2013-8-7 下午1:14:15
	 * @param f
	 * @return
	 */
	public static int toInt(float f){
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
	public static int toInt(String str){
		return Integer.valueOf(str);
	}
	
	/**
	 * 将string转化成long
	 * @author mp
	 * @date 2013-11-7 上午10:19:29
	 * @param str
	 * @return
	 * @Description
	 */
	public static long toLong (String str){
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
	public static long toLong (double dou){
		return (long)dou;
	}
	
	public static void main(String[] args) {
	}
	
}
