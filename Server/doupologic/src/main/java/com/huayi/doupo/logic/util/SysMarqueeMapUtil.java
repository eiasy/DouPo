package com.huayi.doupo.logic.util;

import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.logic.handler.util.marquee.MarqueeThread;

/**
 * 平台GM跑马灯管理工具类,存入<SysMarqueeId, MarqueeThread>
 * @author mp
 * @date 2013-11-23 下午4:03:24
 */
public class SysMarqueeMapUtil {
	
	private SysMarqueeMapUtil() {
		
	}

	private static ConcurrentHashMap<Integer, MarqueeThread> linkedPlayerMap = null;
	
	public static ConcurrentHashMap<Integer, MarqueeThread> getMap() {
		if (linkedPlayerMap == null) {
			linkedPlayerMap = new ConcurrentHashMap<Integer, MarqueeThread>();
		}
		return linkedPlayerMap;
	}
	
	/**
	 * 返回map的大小
	 * @author mp
	 * @date 2013-11-23 下午4:16:32
	 * @return
	 * @Description
	 */
	public static int getSize() {
		return getMap().size();
	}

	/**
	 * 清空map
	 * @author mp
	 * @date 2013-11-23 下午4:16:26
	 * @Description
	 */
	public static void clearAll() {
		linkedPlayerMap = null;
	}
	
	/**
	 * 根据sysMarqueeId，删除MarqueeThread
	 * @author mp
	 * @date 2013-11-23 下午4:16:17
	 * @param channelId
	 * @Description
	 */
	public static void removeById(Integer sysMarqueeId) {
		if (getMap().containsKey(sysMarqueeId)) {
			getMap().remove(sysMarqueeId);
		}
	}

	/**
	 * 向map中加入MarqueeThread
	 * @author mp
	 * @date 2013-11-23 下午4:16:07
	 * @param sysMarqueeId
	 * @param MarqueeThread
	 * @throws Exception
	 * @Description
	 */
	public static void add(Integer sysMarqueeId, MarqueeThread marqueeThread) throws Exception{
		if(!containsSysMarqueeId(sysMarqueeId)){
			getMap().put(sysMarqueeId, marqueeThread);
		}
	}
	
	/**
	 * 判断map是否包含指定的sysMarqueeId
	 * @author mp
	 * @date 2013-12-21 下午2:44:25
	 * @param sysMarqueeId
	 * @return
	 * @Description
	 */
	public static boolean containsSysMarqueeId(Integer sysMarqueeId) {
		return getMap().containsKey(sysMarqueeId);
	}
	
	/**
	 * 根据SysMarqueeId获取MarqueeThread对象
	 * @author mp
	 * @date 2013-12-21 下午2:43:58
	 * @param sysMarqueeId
	 * @return
	 * @Description
	 */
	public static MarqueeThread getMarqueeThreadById(Integer sysMarqueeId) {
		if(getMap().containsKey(sysMarqueeId)){
			return getMap().get(sysMarqueeId);
		}
		return null;
	}
	
}
