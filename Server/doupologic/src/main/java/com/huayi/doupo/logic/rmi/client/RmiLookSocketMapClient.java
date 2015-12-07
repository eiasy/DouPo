package com.huayi.doupo.logic.rmi.client;

import io.netty.channel.Channel;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.socket.User;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.rmi.inter.IRmiLookSocketMap;

public class RmiLookSocketMapClient {
	
	private String ip = "192.168.0.114";
	
	private String port = "8888";
	
	private String serviceName = "RmiLookSocketMapService";
	
	/**
	 * 获取rmi factory
	 * @author mp
	 * @version 1.0, 2013-6-6 上午10:47:18
	 * @return
	 */
	private IRmiLookSocketMap getRmiClientInterface(){
		RmiProxyFactoryBean factory= new RmiProxyFactoryBean();
        factory.setServiceInterface(IRmiLookSocketMap.class );
        factory.setServiceUrl("rmi://"+ip+":"+port+"/"+serviceName);
        factory.setLookupStubOnStartup(false);
        factory.setRefreshStubOnConnectFailure(true);
        factory.afterPropertiesSet();
        IRmiLookSocketMap obj = (IRmiLookSocketMap)factory.getObject();
        return obj;
	}
	
	
	/**
	 * 查看ChannelMap
	 * @author mp
	 * @version 1.0, 2013-6-6 上午10:46:33
	 */
	@Test
	public void lookChannelMap(){
		IRmiLookSocketMap obj = getRmiClientInterface();
		ConcurrentHashMap<String, Channel> channelMap = obj.getChannelMap();
        if(channelMap == null){
        	LogUtil.info("channelMap is null");
        	return;
        }
        Iterator<String> iterator = channelMap.keySet().iterator();
        while(iterator.hasNext()){
        	String channelId = iterator.next();
        	Channel channel = channelMap.get(channelId);
        	LogUtil.info("channelId = "+channel.id().asLongText());
        	LogUtil.info("\n");
        	LogUtil.out("\n");
        }
	}
	
	
	/**
	 * 查看UserMap
	 * @author mp
	 * @version 1.0, 2013-6-6 上午10:46:33
	 */
	@Test
	public void lookUserMap(){
		IRmiLookSocketMap obj = getRmiClientInterface();
		ConcurrentHashMap<Integer, User> userMap = obj.getUserMap();
        if(userMap == null){
        	LogUtil.info("userMap is null");
        	return;
        }
        Iterator<Integer> iterator = userMap.keySet().iterator();
        while(iterator.hasNext()){
        	Integer userId = iterator.next();
        	User user = userMap.get(userId);
        	LogUtil.info("userId = "+user.getUserId());
        	LogUtil.out("userId = "+user.getUserId());
        	LogUtil.info("channelId = "+user.getChannelId());
        	LogUtil.out("channelId = "+user.getChannelId());
        	LogUtil.info("\n");
        	LogUtil.out("\n");
        }
	}
	
	/**
	 * 查看PlayerMap
	 * @author mp
	 * @version 1.0, 2013-6-6 上午10:46:57
	 */
	@Test
	public void lookPlayerMap(){
		IRmiLookSocketMap obj = getRmiClientInterface();
		ConcurrentHashMap<String, Player> playerMap = obj.getPlayerMap();
        if(playerMap == null){
        	LogUtil.info("playerMap is null");
        	return;
        }
        Iterator<String> iterator = playerMap.keySet().iterator();
        while(iterator.hasNext()){
        	String channelId = iterator.next();
        	Player player = playerMap.get(channelId);
        	LogUtil.info("playerId = "+player.getPlayerId());
        	LogUtil.out("playerId = "+player.getPlayerId());
        	LogUtil.info("channelId = "+player.getChannelId());
        	LogUtil.out("channelId = "+player.getChannelId());
        	LogUtil.info("playerName = "+player.getPlayerName());
        	LogUtil.out("playerName = "+player.getPlayerName());
        	LogUtil.info("\n");
        	LogUtil.out("\n");
        }
	}
	
	/**
	 * 查看大对象
	 * @author mp
	 * @version 1.0, 2013-6-18 下午12:19:13
	 */
	@Test
	public void lookPlayerBigObjMap(){
//		IRmiLookSocketMap obj = getRmiClientInterface();
//		PlayerBigObj playerBigObj = (PlayerBigObj)obj.getPlayerBigObjMap(11);
//		HashMap<Integer, PlayerBigObj> playerBigObjMap  = (HashMap<Integer, PlayerBigObj>)obj.getPlayerBigObjMap(0);
//		System.out.println();
		
	}
	
	/**
	 * 查看字典数据
	 * @author mp
	 * @version 1.0, 2013-6-25 上午11:20:19
	 */
	@Test
	public void lookDictData(){
//		IRmiLookSocketMap obj = getRmiClientInterface();
//		DictMap dictData = obj.getDictData();
//		System.out.println();
	}
	
}
