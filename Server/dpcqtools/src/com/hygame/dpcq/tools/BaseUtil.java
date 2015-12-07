package com.hygame.dpcq.tools;


public class BaseUtil {
	/**
	 * 
	 * 该方法判断是否为空
	 */
	public static boolean isNullAndEmpty(String obj) {
		if (obj == null || ("").equals(obj.trim()))
			return true;

		return false;
	}

}
