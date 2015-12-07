package com.huayi.doupo.logic.rmi.impl;

import io.netty.channel.Channel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.socket.User;
import com.huayi.doupo.logic.rmi.inter.IRmiLookSocketMap;
import com.huayi.doupo.logic.util.ChannelMapUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;
import com.huayi.doupo.logic.util.UserMapUtil;

public class RmiLookSocketMap extends UnicastRemoteObject implements IRmiLookSocketMap{

	private static final long serialVersionUID = 5182255108598145320L;

	public RmiLookSocketMap() throws RemoteException {

	}
	
	/**
	 * 查看User Map
	 */
	@Override
	public ConcurrentHashMap<Integer, User> getUserMap() {
		return UserMapUtil.getMap();
	}

	/**
	 * 查看Player Map
	 */
	@Override
	public ConcurrentHashMap<String, Player> getPlayerMap() {
		return PlayerMapUtil.getMap();
	}

	/**
	 * 查看Channel Map
	 */
	@Override
	public ConcurrentHashMap<String, Channel> getChannelMap() {
		return ChannelMapUtil.getMap();
	}

	/**
	 * 查看大对象map
	 */
	@Override
	public Object getPlayerBigObjMap(Integer userId) {
//		if(userId == 0){
//			return PlayerBigObjMap.getMap();
//		}else{
//			return PlayerBigObjMap.getMap().get(userId);
//		}
		return null;
	}

	/**
	 * 获取服务端字典数据
	 */
	@Override
	public DictMap getDictData() {
//		return GenerDictData.staticDictData;
		return null;
	}

	

	
	
	
}
