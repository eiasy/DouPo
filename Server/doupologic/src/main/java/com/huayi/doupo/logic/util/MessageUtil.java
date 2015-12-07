package com.huayi.doupo.logic.util;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import model.proto.Message;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.socket.statics.StaticConfig;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;

/**
 * 消息包装类
 * @author mp
 * @date 2013-9-22 下午3:31:00
 */
public class MessageUtil {
	
	/**
	 * 私有构造方法
	 */
	private MessageUtil() {

	}
	
	/**
	 * 获取Message
	 * @author mp
	 * @date 2013-9-22 下午3:31:45 
	 * @return
	 * @Description
	 */
	public static Message.Msg getMsg(int header, int version, Message.MsgData.Builder msgData){
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(header);
		msg.setVersion(version);
		msg.setMsgdata(msgData);
		return msg.build();
	}
	
	/**
	 * 获取Message
	 * @author mp
	 * @date 2013-9-23 下午4:55:55
	 * @param msgMap
	 * @param msgData
	 * @return
	 * @Description
	 */
	private static Message.Msg getMsg(Map<String, Object> msgMap, MessageData messageData){
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		if(msgMap != null){
			msg.setHeader((int)msgMap.get("header"));
			msg.setVersion((int)msgMap.get("version"));
		}
		if(messageData != null){
			msg.setMsgdata(messageData.getMsgData());
		}
		return msg.build();
	}
	
	/**
	 * 获取Message
	 * @author mp
	 * @date 2013-12-11 下午2:40:44
	 * @param messageData
	 * @return
	 * @Description
	 */
	private static Message.Msg getMsg(MessageData messageData){
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		if(messageData != null){
			msg.setMsgdata(messageData.getMsgData());
		}
		return msg.build();
	}
	
	/**
	 * 反序列化成Map格式，用于解析前端传来参数
	 * @author mp
	 * @date 2013-9-22 下午4:59:38
	 * @param msgBody
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static Map<String, Object> parseFrom(byte[] msgBody) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();

		Message.Msg msg = Message.Msg.parseFrom(msgBody);
		Message.MsgData msgData = msg.getMsgdata();
		
		if(msg.getHeader() != 0){
			map.put("header", msg.getHeader());
			map.put("version", msg.getVersion());
			map.put("messageData", new MessageData(Message.MsgData.newBuilder(msgData)));
		}
		
		for(Message.IntItem intItem : msgData.getIntItemList()){
			map.put(intItem.getKey(), intItem.getValue());
		}
		for(Message.StringItem stringItem : msgData.getStringItemList()){
			map.put(stringItem.getKey(), stringItem.getValue());
		}
		for(Message.LongItem longItem : msgData.getLongItemList()){
			map.put(longItem.getKey(), longItem.getValue());
		}
		for(Message.FloatItem floatItem : msgData.getFloatItemList()){
			map.put(floatItem.getKey(), floatItem.getValue());
		}
		for(Message.DoubleItem doubleItem : msgData.getDoubleItemList()){
			map.put(doubleItem.getKey(), doubleItem.getValue());
		}
		return map;
	}
	
	/**
	 * 写消息日志
	 * @author mp
	 * @date 2014-4-24 下午2:19:34
	 * @param channelId
	 * @param msgMap
	 * @param messageData
	 * @Description
	 */
	public static void writeMsgLog (String channelId, Map<String, Object> msgMap, MessageData messageData) {
		try {
			int header = (int)msgMap.get("header");
			if ((header >= 10000 && header < 20000) || (int)header == 1000) {
				//[10000-20000)之间的和心跳检测协议不做处理
			}else {
				String protyName = DictMap.sysMsgRuleMap.get(msgMap.get("header")+"").getName();
				String log = " instPlayerId = " + BaseHandler.getInstPlayerId(channelId) + ", header = " + header + ", " + protyName + " ";
				String params = " ";
				for (Entry<String, Object> entry : msgMap.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					if (!key.equals("header") && !key.equals("version") && !key.equals("messageData")) {
						params = params + key + " = " + value + ", ";
					}
				}
				if (params.length() > 2) {
					params = params.substring(0, params.length() - 2);
				}
				String ret = " ";
				for(Message.StringItem stringItem : messageData.getMsgData().getStringItemList()){
					if (stringItem.getKey().equals("retMsg")) {
						ret = ret + stringItem.getKey() + " = " + stringItem.getValue() + ", ";
					}
				}
				for (Message.IntItem intItem : messageData.getMsgData().getIntItemList()) {
					if (intItem.getKey().equals("isOk")) {
						continue;
					}
					ret = ret + intItem.getKey() + " = " + intItem.getValue() + ", ";
				}
				if (ret.length() > 2) {
					ret = ret.substring(0, ret.length() - 2);
				}
				if (header == 1001) {
					log = "info[" + log + "]" + "  param[" + params + " ]" + "  return[" + ret + " ]" + " " + channelId;
				} else {
					String clientIp = "";
					try {
						Channel nettyChannel = ChannelMapUtil.getByChannelId(channelId);
						if (nettyChannel != null) {
							InetSocketAddress insocket = (InetSocketAddress) nettyChannel.remoteAddress();
							clientIp = insocket.getAddress().getHostAddress();// 获取客户端IP地址
						}
					} catch (Exception e) {
					}
					log = "info[" + log + "]" + "  param[" + params + " ]" + "  return[" + ret + " ]" + " " + clientIp;
				}
				LogUtil.info(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送消息[组织Header/Version]
	 * @author mp
	 * @date 2013-9-23 下午5:12:54
	 * @param channelId
	 * @param msg
	 * @Description
	 */
	public static ChannelFuture sendMsg(String channelId, Map<String, Object> msgMap, MessageData messageData){
		if (msgMap != null) {
			writeMsgLog(channelId, msgMap, messageData);
		}
		ChannelFuture channelFuture = null;
		try {
			Message.Msg msg = getMsg(msgMap, messageData);
			Channel channel = ChannelMapUtil.getByChannelId(channelId);
			if (channel != null) {
				if(!channel.isActive()){
					Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
					if (player != null) {
						LogUtil.error("miaopeng : Failed to write any response because the channel is not connected any more. Maybe the client has closed the connection? " + "   channelId = " + channelId + "  playerId = " + player.getPlayerId() + " playerName = " + player.getPlayerName() + " header = " + (int)msgMap.get("header"));
					} else {
						LogUtil.error("miaopeng : Failed to write any response because the channel is not connected any more. Maybe the client has closed the connection? ");
					}
				}else{
					channelFuture = channel.writeAndFlush(msg);
				}
			}
		} catch (Exception e) {
			LogUtil.error(e);
			e.printStackTrace();
		}
		return channelFuture;
	}
	
	/**
	 * 发送消息[直接发送,不组织Header/Version,主要用于向客户端推送消息]
	 * @author mp
	 * @date 2013-11-25 下午2:30:40
	 * @param channelId
	 * @param msg
	 * @return
	 * @Description
	 */
	public static ChannelFuture sendMsg(String channelId, Message.Msg msg){
		ChannelFuture channelFuture = null;
		try {
			Channel channel = ChannelMapUtil.getByChannelId(channelId);
			if (channel != null) {
				if(!channel.isActive()){
					Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
					if (player != null) {
						LogUtil.error("miaopeng : Failed to write any response because the channel is not connected any more. Maybe the client has closed the connection? " + "   channelId = " + channelId + "  playerId = " + player.getPlayerId() + " playerName = " + player.getPlayerName());
					} else {
						LogUtil.error("miaopeng : Failed to write any response because the channel is not connected any more. Maybe the client has closed the connection? ");
					}
				}else{
					channelFuture = channel.writeAndFlush(msg);
				}
			}
		} catch (Exception e) {
			LogUtil.error(e);
			e.printStackTrace();
		}
		return channelFuture;
	}
	
	/**
	 * 发送GM消息
	 * @author mp
	 * @date 2013-12-11 下午2:41:33
	 * @param channelId
	 * @param messageData
	 * @return
	 * @Description
	 */
	public static ChannelFuture sendGMMsg(String channelId, HashMap<String, Object> msgMap,  MessageData messageData){
		writeMsgLog(channelId, msgMap, messageData);
		ChannelFuture channelFuture = null;
		try {
			Message.Msg msg = getMsg(msgMap, messageData);
			Channel channel = ChannelMapUtil.getByChannelId(channelId);
			if (channel != null) {
				if(!channel.isActive()){
					LogUtil.error("miaopeng : Failed to write any response because the channel is not connected any more. Maybe the client has closed the connection? ");
				}else{
					channelFuture = channel.writeAndFlush(msg);
				}
			} else {
				ChannelMapUtil.remove(channelId);
			}
		} catch (Exception e) {
			LogUtil.error(e);
			e.printStackTrace();
		}
		return channelFuture;
	}
	
	/**
	 * 发送消息给所有人
	 * @author mp
	 * @date 2013-11-25 下午2:30:53
	 * @param msg
	 * @return
	 * @Description
	 */
	public static void sendMsgToAllPlayer(Message.Msg msg){
		ConcurrentHashMap<String, Player> linkedPlayerMap = PlayerMapUtil.getMap();
		if(linkedPlayerMap != null){
			for(Entry<String, Player> map : linkedPlayerMap.entrySet()){
				String channelId = map.getKey();
				MessageUtil.sendMsg(channelId, msg);
			}
		}
	}
	
	/**
	 * 发送失败消息
	 * @author mp
	 * @date 2013-10-14 上午11:35:17
	 * @param channelId
	 * @param msgMap
	 * @param errMsg
	 * @Description
	 */
	public static ChannelFuture sendFailMsg(String channelId, Map<String, Object> msgMap, String errMsg){
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("retMsg", errMsg);
		retMsgData.putIntItem("isOk", StaticConfig.FAIL);
		return sendMsg(channelId, msgMap, retMsgData);
	}


	/**
	 * 发送消息失败，并返回错误提示
	 * @author 	cui
	 * @date    09/30/2015
	 * @param channelId
	 * @param msgMap
	 * @param errMsg
	 * @param retMsgData
	 * @return
	 */
	public static ChannelFuture sendFailMsg(String channelId, Map<String, Object> msgMap, String errMsg,MessageData retMsgData){
		retMsgData.putStringItem("retMsg", errMsg);
		retMsgData.putIntItem("isOk", StaticConfig.FAIL);
		return sendMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 发送成功消息[不带消息体]
	 * @author mp
	 * @date 2014-8-28 下午2:31:06
	 * @param channelId
	 * @param msgMap
	 * @Description
	 */
	public static void sendSuccMsg(String channelId, Map<String, Object> msgMap){
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("retMsg", "");
		retMsgData.putIntItem("isOk", StaticConfig.SUCC);
		sendMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 发送成功信息[带消息体]
	 * @author mp
	 * @date 2013-10-23 下午3:09:04
	 * @param channelId //通道Id
	 * @param msgMap //客户端发来的数据[服务器自己组装的map,主要用里边的header]
	 * @param retMsgData //给客户端组织的数据,没有写null
	 * @Description
	 */
	public static void sendSuccMsg(String channelId, Map<String, Object> msgMap, MessageData retMsgData){
		if (retMsgData == null) {
			retMsgData = new MessageData();
		} 
		retMsgData.putStringItem("retMsg", "");//成功情况下一般不返回提示语,有成功标识isOk客户端自行判断即可
		retMsgData.putIntItem("isOk", StaticConfig.SUCC);
		sendMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 发送同步数据信息
	 * @author mp
	 * @date 2013-10-21 下午1:56:02
	 * @param channelId
	 * @param retMsgData
	 * @Description
	 */
	public static void sendSyncMsg(String channelId, MessageData syncMsgData){
		Map<String, Object> newMsgMata = new HashMap<String, Object>();
		newMsgMata.put("header", 10000);
		newMsgMata.put("version", 1);
		MessageUtil.sendMsg(channelId, newMsgMata, syncMsgData);
	}
	
	/**
	 * 同步消息给指定玩家
	 * @author mp
	 * @date 2013-11-16 下午3:46:45
	 * @param player
	 * @param retMsgData
	 * @Description
	 */
	public static void sendSyncMsgToOne(String openId, MessageData otherSyncMsgData){
		Map<String, Object> newMsgMata = new HashMap<String, Object>();
		newMsgMata.put("header", 10000);
		newMsgMata.put("version", 1);
		
		Player player = PlayerMapUtil.getPlayerByOpenId(openId);
		if(player != null){
			MessageUtil.sendMsg(player.getChannelId(), newMsgMata, otherSyncMsgData);
		}
	}
	
	/**
	 * 同步消息给所有在线玩家
	 * @author mp
	 * @date 2013-11-16 下午3:51:55
	 * @param retMsgData
	 * @Description
	 */
	public static void sendSyncMsgToAll(MessageData retMsgData){
		Map<String, Object> newMsgMata = new HashMap<String, Object>();
		newMsgMata.put("header", 10000);
		newMsgMata.put("version", 1);
		
		ConcurrentHashMap<String, Player> linkedPlayerMap = PlayerMapUtil.getMap();
		if(linkedPlayerMap != null){
			for(Entry<String, Player> map : linkedPlayerMap.entrySet()){
				String channelId = map.getKey();
				MessageUtil.sendMsg(channelId, newMsgMata, retMsgData);
			}
		}
	}
	
	/**
	 * 推送数据[推送类型用header做区分]
	 * @author mp
	 * @date 2014-8-28 下午2:43:55
	 * @param channelId
	 * @param header
	 * @param version
	 * @param retMsgData
	 * @Description
	 */
	public static void pushMsg(String channelId, int header, MessageData retMsgData){
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(header);
		msg.setVersion(1);
		msg.setMsgdata(retMsgData.getMsgData());
		
		MessageUtil.sendMsg(channelId, msg.build());
	}
	
	/**
	 * 推送数据[推送类型用header做区分]
	 * @author mp
	 * @date 2014-8-28 下午4:15:27
	 * @param channelIdList
	 * @param header
	 * @param version
	 * @param retMsgData
	 * @Description
	 */
	public static void pushMsg (List<String> channelIdList, int header, MessageData retMsgData) {
		for (String channelId :channelIdList) {
			pushMsg(channelId, header, retMsgData);
		}
	}
	
}
