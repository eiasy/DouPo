package com.hygame.dpcq.tools;

import java.util.HashMap;
import java.util.Timer;

/**
 *   线程锁
 */
public class Lock {
	public static HashMap<String,Thread> threadMap = new HashMap<String,Thread>();
	public static HashMap<String,Timer> threadMapTimer = new HashMap<String,Timer>();
	public static HashMap<String,Object> threadMapReturnObject = new HashMap<String,Object>();
	public static HashMap<String,String> threadMapReturnString = new HashMap<String,String>();
}
