package com.hygame.dpcq.coon;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hygame.dpcq.action.ServerAttributeAction;
import com.hygame.dpcq.db.dao.model.ServerAttribute;
import com.hygame.dpcq.tools.Lock;


public class GameCoon {
	
	public static Map<String, Channel> channelMap = new HashMap<String, Channel>();
	public static Map<Integer, ServerAttribute> serverMap = new  LinkedHashMap<Integer, ServerAttribute>();
	
	/**
	 * 组织服务器列表
	 * @author mp
	 * @date 2015-4-2 下午12:02:09
	 * @Description
	 */
	public static void orgServerList(){
		
		//清空channel和服务器后重新从数据库加载
		channelMap.clear();
		
		//获得数据库的游戏服列表
		ServerAttributeAction.orgServerList();
	}
	
	/**
	 * 选择服务器时,创建连接对象[超过3秒,判定为未取得连接]
	 * @author hzw
	 * @date 2015-3-7上午10:58:59
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static Channel getChannel(int serverid){
		ServerAttribute sa = serverMap.get(serverid);
		Channel channel = sa.getChannel();
		if(channel == null){
			try {
				//确保有效返回channel 加个 线程锁
				Thread current = new Thread();
				try {
					Lock.threadMap.put("coon", current);
					synchronized (current) {
						 Socket client = new Socket(); 
						 InetSocketAddress address = new InetSocketAddress(sa.getIp(), sa.getPort()); 
						 client.connect(address, 3000); 
						 client.close();
						 new DiscardClient(sa.getIp(), sa.getPort()).run();	
						 current.wait();
					}
				} catch (Exception e) {
					channelMap.put("ctx", null);
				}
				
				channel = channelMap.get("ctx");
				sa.setChannel(channel);
				serverMap.put(sa.getId(), sa);
				return channel;
			
			} catch (Exception e) {
				e.printStackTrace();
				return channel;
			}
		}
		return channel;
	}
	
	public static void main(String[] args) {
		getChannel(2);
	}
	
}
