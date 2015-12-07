package com.huayi.doupo.logic.util;

import model.proto.Message;
import model.proto.Message.DoubleItem;
import model.proto.Message.FloatItem;
import model.proto.Message.IntItem;
import model.proto.Message.LongItem;
import model.proto.Message.MessageItem;
import model.proto.Message.StringItem;

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
	 * 排重放入int类型字段
	 * @author mp
	 * @date 2015-2-7 下午8:48:11
	 * @param key
	 * @param value
	 * @param messageData
	 * @Description
	 */
	public void putIntItem(String key, int value, MessageData messageData) {
		int index = 0;
		int isSet = 0;
		Message.IntItem.Builder intItemBuilder = Message.IntItem.newBuilder();
		if (msgData.getIntItemList().size() > 0) {
			for (IntItem intItem : msgData.getIntItemList()) {
				if (intItem.getKey().equals(key)) {
					intItemBuilder.setValue(value).setKey(key);
					msgData.setIntItem(index, intItemBuilder);
					isSet = 1;
				}
				index ++;
			}
			if (isSet == 0) {
				intItemBuilder.setKey(key);
				intItemBuilder.setValue(value);
				msgData.addIntItem(intItemBuilder);
			}
		} else {
			intItemBuilder.setKey(key);
			intItemBuilder.setValue(value);
			msgData.addIntItem(intItemBuilder);
		}
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
	 * 排重放入Long类型字段
	 * @author mp
	 * @date 2015-2-7 下午8:52:34
	 * @param key
	 * @param value
	 * @param messageData
	 * @Description
	 */
	public void putLongItem(String key, long value, MessageData messageData) {
		int index = 0;
		int isSet = 0;
		Message.LongItem.Builder longItemBuilder = Message.LongItem.newBuilder();
		if (msgData.getLongItemList().size() > 0) {
			for (LongItem longItem : msgData.getLongItemList()) {
				if (longItem.getKey().equals(key)) {
					longItemBuilder.setValue(value).setKey(key);
					msgData.setLongItem(index, longItemBuilder);
					isSet = 1;
				}
				index ++;
			}
			if (isSet == 0) {
				longItemBuilder.setKey(key);
				longItemBuilder.setValue(value);
				msgData.addLongItem(longItemBuilder);
			}
		} else {
			longItemBuilder.setKey(key);
			longItemBuilder.setValue(value);
			msgData.addLongItem(longItemBuilder);
		}
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
	 * 排重放入Float类型字段
	 * @author mp
	 * @date 2015-2-7 下午8:56:20
	 * @param key
	 * @param value
	 * @param messageData
	 * @Description
	 */
	public void putFloatItem(String key, float value, MessageData messageData) {
		int index = 0;
		int isSet = 0;
		Message.FloatItem.Builder floatItemBuilder = Message.FloatItem.newBuilder();
		if (msgData.getFloatItemList().size() > 0) {
			for (FloatItem floatItem : msgData.getFloatItemList()) {
				if (floatItem.getKey().equals(key)) {
					floatItemBuilder.setValue(value).setKey(key);
					msgData.setFloatItem(index, floatItemBuilder);
					isSet = 1;
				}
				index ++;
			}
			if (isSet == 0) {
				floatItemBuilder.setKey(key);
				floatItemBuilder.setValue(value);
				msgData.addFloatItem(floatItemBuilder);
			}
		} else {
			floatItemBuilder.setKey(key);
			floatItemBuilder.setValue(value);
			msgData.addFloatItem(floatItemBuilder);
		}
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
	 * 排重放入Double类型字段
	 * @author mp
	 * @date 2015-2-7 下午9:01:37
	 * @param key
	 * @param value
	 * @param messageData
	 * @Description
	 */
	public void putDoubleItem(String key, double value, MessageData messageData) {
		int index = 0;
		int isSet = 0;
		Message.DoubleItem.Builder doubleItemBuilder = Message.DoubleItem.newBuilder();
		if (msgData.getDoubleItemList().size() > 0) {
			for (DoubleItem doubleItem : msgData.getDoubleItemList()) {
				if (doubleItem.getKey().equals(key)) {
					doubleItemBuilder.setValue(value).setKey(key);
					msgData.setDoubleItem(index, doubleItemBuilder);
					isSet = 1;
				}
				index ++;
			}
			if (isSet == 0) {
				doubleItemBuilder.setKey(key);
				doubleItemBuilder.setValue(value);
				msgData.addDoubleItem(doubleItemBuilder);
			}
		} else {
			doubleItemBuilder.setKey(key);
			doubleItemBuilder.setValue(value);
			msgData.addDoubleItem(doubleItemBuilder);
		}
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
	 * 排重放入String类型字段
	 * @author mp
	 * @date 2015-2-7 下午9:06:06
	 * @param key
	 * @param value
	 * @param messageData
	 * @Description
	 */
	public void putStringItem(String key, String value, MessageData messageData) {
		int index = 0;
		int isSet = 0;
		Message.StringItem.Builder stringItemBuilder = Message.StringItem.newBuilder();
		if (msgData.getStringItemList().size() > 0) {
			for (StringItem stringItem : msgData.getStringItemList()) {
				if (stringItem.getKey().equals(key)) {
					stringItemBuilder.setValue(value).setKey(key);
					msgData.setStringItem(index, stringItemBuilder);
					isSet = 1;
				}
				index ++;
			}
			if (isSet == 0) {
				stringItemBuilder.setKey(key);
				stringItemBuilder.setValue(value);
				msgData.addStringItem(stringItemBuilder);
			}
		} else {
			stringItemBuilder.setKey(key);
			stringItemBuilder.setValue(value);
			msgData.addStringItem(stringItemBuilder);
		}
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
	 * 暂不处理[感觉有风险,目前不处理没错误,有空再测]
	 * @author mp
	 * @date 2015-2-9 上午11:18:49
	 * @param key
	 * @param value
	 * @param messageData
	 * @Description
	 */
//	public void putMessageItem(String key, Message.MsgData.Builder value, MessageData messageData) {
//		int index = 0;
//		int isSet = 0;
//		Message.MessageItem.Builder messageItemBuilder = Message.MessageItem.newBuilder();
//		if (msgData.getMessageItemList().size() > 0) {
//			for (MessageItem MessageItem : msgData.getMessageItemList()) {
//				if (MessageItem.getKey().equals(key)) {
//					messageItemBuilder.setMsgdata(value).setKey(key);
//					msgData.setMessageItem(index, messageItemBuilder);
//					isSet = 1;
//				}
//				index ++;
//			}
//			if (isSet == 0) {
//				messageItemBuilder.setKey(key);
//				messageItemBuilder.setMsgdata(value);
//				msgData.addMessageItem(messageItemBuilder);
//			}
//		} else {
//			messageItemBuilder.setKey(key);
//			messageItemBuilder.setMsgdata(value);
//			msgData.addMessageItem(messageItemBuilder);
//		}
//	}
	
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
