package com.hygame.dpcq.model.proto.util;

import java.util.HashMap;
import java.util.Map;

import model.proto.Message;

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
		
		/*map.put("header", msg.getHeader());
		map.put("version", msg.getVersion());
		map.put("messageData", new MessageData(Message.MsgData.newBuilder(msgData)));*/
		
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
}
