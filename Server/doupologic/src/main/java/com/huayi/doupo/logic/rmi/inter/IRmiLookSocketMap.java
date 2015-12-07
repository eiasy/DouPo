package com.huayi.doupo.logic.rmi.inter;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.socket.User;

/**
 * 查看Socket Map 的接口
 * @author mp
 * @version 1.0, 2013-6-6 上午11:11:57
 */
public interface IRmiLookSocketMap {
	
	/**
	 * 查看UserMap
	 * @author mp
	 * @version 1.0, 2013-6-6 上午11:12:15
	 * @return
	 */
	public ConcurrentHashMap<Integer, User> getUserMap();
	
	/**
	 * 查看PlayerMap
	 * @author mp
	 * @version 1.0, 2013-6-6 上午11:12:23
	 * @return
	 */
	public ConcurrentHashMap<String, Player>  getPlayerMap();
	
	/**
	 * 查看ChannelMap
	 * @author mp
	 * @version 1.0, 2013-6-7 上午10:26:27
	 * @return
	 */
	public ConcurrentHashMap<String, Channel> getChannelMap();
	
	/**
	 * 获取大对象Map
	 * @author mp
	 * @version 1.0, 2013-6-18 上午11:23:16
	 * @return
	 */
	public Object getPlayerBigObjMap(Integer userId);
	
	/**
	 * 获取服务端字典数据
	 * @author mp
	 * @version 1.0, 2013-6-18 上午11:26:08
	 * @return
	 */
	public DictMap getDictData();
}
