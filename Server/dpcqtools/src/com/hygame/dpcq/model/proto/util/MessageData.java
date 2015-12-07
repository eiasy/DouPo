package com.hygame.dpcq.model.proto.util;

import model.proto.Message;

/**
 * ProtoBuf 工具类
 * 
 * @author mp
 * @date 2013-9-22 上午11:26:35
 */
public class MessageData {

	private Message.MsgData.Builder msgData = null;
	
	public MessageData(){
		msgData = Message.MsgData.newBuilder();
	}
	
	public MessageData(Message.MsgData.Builder msgData){
		if(msgData == null){
			this.msgData = Message.MsgData.newBuilder();
		}else{
			this.msgData = msgData;
		}
	}
	
	/**
	 * 获取MsgData
	 * @author mp
	 * @date 2013-9-22 下午12:05:19
	 * @return
	 * @Description
	 */
	public Message.MsgData.Builder getMsgData(){
		return msgData;
	}
	
	/**
	 * 放入int类型字段
	 * @author mp
	 * @date 2013-9-22 上午11:32:03
	 * @param key
	 * @param value
	 * @param type
	 * @Description
	 */
	public void putIntItem(String key, int value) {
		Message.IntItem.Builder intItem = Message.IntItem.newBuilder();
		intItem.setKey(key);
		intItem.setValue(value);
		msgData.addIntItem(intItem);
	}

	/**
	 * 放入Long类型字段
	 * @author mp
	 * @date 2013-9-22 上午11:33:23
	 * @param key
	 * @param value
	 * @param type
	 * @Description
	 */
	public void putLongItem(String key, long value) {
		Message.LongItem.Builder longItem = Message.LongItem.newBuilder();
		longItem.setKey(key);
		longItem.setValue(value);
		msgData.addLongItem(longItem);
	}

	/**
	 * 放入Float类型字段
	 * @author mp
	 * @date 2013-9-22 上午11:33:49
	 * @param key
	 * @param value
	 * @param type
	 * @Description
	 */
	public void putFloatItem(String key, float value) {
		Message.FloatItem.Builder floatItem = Message.FloatItem.newBuilder();
		floatItem.setKey(key);
		floatItem.setValue(value);
		msgData.addFloatItem(floatItem);
	}

	/**
	 * 放入Double类型字段
	 * @author mp
	 * @date 2013-9-22 上午11:34:24
	 * @param key
	 * @param value
	 * @param type
	 * @Description
	 */
	public void putDoubleItem(String key, double value) {
		Message.DoubleItem.Builder doubleItem = Message.DoubleItem.newBuilder();
		doubleItem.setKey(key);
		doubleItem.setValue(value);
		msgData.addDoubleItem(doubleItem);
	}

	/**
	 * 放入String类型字段
	 * @author mp
	 * @date 2013-9-22 上午11:34:55
	 * @param key
	 * @param value
	 * @param type
	 * @Description
	 */
	public void putStringItem(String key, String value) {
		Message.StringItem.Builder stringItem = Message.StringItem.newBuilder();
		stringItem.setKey(key);
		if(value != null){
			stringItem.setValue(value);
		}else{
			stringItem.setValue("");
		}
		msgData.addStringItem(stringItem);
	}

	/**
	 * 放入MsgData类型字段
	 * @author mp
	 * @date 2013-9-22 上午11:37:10
	 * @param key
	 * @param value
	 * @param type
	 * @Description
	 */
	public void putMessageItem(String key, Message.MsgData.Builder value) {
	   Message.MessageItem.Builder messageItem = Message.MessageItem.newBuilder();
	   messageItem.setKey(key);
	   messageItem.setMsgdata(value);
	   msgData.addMessageItem(messageItem);
	}
}
