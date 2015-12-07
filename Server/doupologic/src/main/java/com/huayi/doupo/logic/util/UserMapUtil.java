package com.huayi.doupo.logic.util;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.huayi.doupo.base.model.socket.User;
import com.huayi.doupo.base.util.logic.system.LogUtil;

/**
 * 玩家管理Map,存入<userId,Player>
 * @author mp
 * @version 1.0, 2013-4-10 上午10:58:07
 */
public class UserMapUtil {
	
	private UserMapUtil() {
	}

	private static ConcurrentHashMap<Integer, User> linkedPlayerMap = null;//发消息时按玩家进入顺序进行发送
	
	public static ConcurrentHashMap<Integer, User> getMap() {
		if (linkedPlayerMap == null) {
			linkedPlayerMap = new ConcurrentHashMap<Integer, User>();
		}
		return linkedPlayerMap;
	}
	
	/**
	 * 返回map的大小
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:19:40
	 * @return
	 * @throws Exception
	 */
	public static int getSize() {
		return getMap().size();
	}

	/**
	 * 清空map
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:19:11
	 * @throws Exception
	 */
	public static void clearAll() {
		linkedPlayerMap = null;
	}
	
	/**
	 * 根据channelId，删除user
	 * @author mp
	 * @version 1.0, 2013-4-16 下午12:23:11
	 * @param channelId
	 */
	public static void removeByChannelId(Integer channelId) {
		User user = getUserByChannelId(channelId);
		if(user != null){
			int userId = user.getUserId();
			if (getMap().containsKey(userId)) {
				getMap().remove(userId);
			}
		}
	}

	/**
	 * 根据指定userId删除user
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:20:46
	 * @param userId
	 * @throws Exception
	 */
	public static void removeByUserId(Integer userId) {
		if (getMap().containsKey(userId)) {
			getMap().remove(userId);
		}
	}

	/**
	 * 根据指定player对象删除user
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:21:53
	 * @param player
	 * @throws Exception
	 */
	public static void removeByUser(User user) {
		long userId = user.getUserId();
		if (getMap().containsKey(userId)) {
			getMap().remove(userId);
		}
	}


	/**
	 * 向map中加入user
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:22:40
	 * @param userId
	 * @param player
	 * @throws Exception
	 */
	public static void add(Integer userId, User user) {
		if(getMap().containsKey(userId)){
			User newUser = getMap().get(userId);
			newUser.setChannelId(user.getChannelId());
			LogUtil.info("UserMapUtil  newUser  userId = "+newUser.getUserId()+" channelId = "+newUser.getChannelId());
		}else{
			getMap().put(userId, user);
			LogUtil.info("UserMapUtil  User  userId = "+user.getUserId()+" channelId = "+user.getChannelId());
		}
	}

	/**
	 * 判断map是否包含指定的userId
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:23:04
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static boolean containsUserId(Integer userId) {
		return getMap().containsKey(userId);
	}

	/**
	 * 判断map是否包含指定的user对象
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:24:37
	 * @param player
	 * @return
	 * @throws Exception
	 */
	public static boolean containsUser(User user) {
		boolean flag = false;
		if (user != null) {
			if (UserMapUtil.containsUserId(user.getUserId())) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 根据指定的channelId，获取user对象
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:24:58
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public static User getUserByChannelId(Integer channelId) {
		User user = null;
		Iterator<Integer> it = getMap().keySet().iterator();
		while (it.hasNext()) {
			Integer userId = it.next();
			User u = getMap().get(userId);
			if (u != null && u.getChannelId() == channelId) {
				user=u;
				break;
			}
		}
		return user;
	}
	
	/**
	 * 根据指定userId获取user
	 * @author mp
	 * @version 1.0, 2013-4-10 上午11:22:17
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static User getUserByUserId(Integer userId) {
		User user = null;
		if (getMap().containsKey(userId)) {
			user = getMap().get(userId);
		}
		return user;
	}

}
