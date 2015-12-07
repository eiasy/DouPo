package com.huayi.doupo.logic.util;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Netty Channel 帮助类
 * @author mp
 * @version 1.0, 2013-4-4 上午11:24:34
 */
public class ChannelMapUtil { 
	
	private ChannelMapUtil() {
	
	}
	
	private static ConcurrentHashMap<String, Channel> channelMap = null;

	public static ConcurrentHashMap<String, Channel> getMap(){
		if (channelMap == null) {
			channelMap = new ConcurrentHashMap<String, Channel>();
		}
		return channelMap;
	}

	/**
	 * 清空channel Map
	 * @author mp
	 * @version 1.0, 2013-4-4 上午11:25:22
	 * @throws Exception
	 */
	public static void clearAll(){
		channelMap = null;
	}

	/**
	 * 得到Channel的集合map的大小
	 * @author mp
	 * @date 2014-9-11 下午2:18:26
	 * @return
	 * @Description
	 */
	public static int getSize(){
		return getMap().size();
	}

	/**
	 * 根据ChannelId删除指定Channel
	 * @author mp
	 * @date 2014-9-11 下午2:19:22
	 * @param channelId
	 * @Description
	 */
	public static void remove(String channelId){
		if (getMap().containsKey(channelId))
			getMap().remove(channelId);
	}

	/**
	 * 根据ChannelId获取Channel
	 * @author mp
	 * @date 2014-9-11 下午2:19:57
	 * @param channelId
	 * @return
	 * @Description
	 */
	public static Channel getByChannelId(String channelId) {
		Channel channel = null;
		if (getMap().containsKey(channelId))
			channel = getMap().get(channelId);
		return channel;
	}

	/**
	 * 添加Channel
	 * @author mp
	 * @date 2014-9-11 下午2:20:14
	 * @param channel
	 * @Description
	 */
	public static void add(Channel channel) {
		if (!getMap().containsKey(channel.id().asLongText())) {
			getMap().put(channel.id().asLongText(), channel);
		} 
	}

	/**
	 * 判断map是否包含指定ID的channel
	 * @author mp
	 * @date 2014-9-11 下午2:21:12
	 * @param channelId
	 * @return
	 * @Description
	 */
	public static boolean containsKey(String channelId){
		return getMap().containsKey(channelId);
	}

}
