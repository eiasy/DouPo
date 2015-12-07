package com.hygame.dpcq.tools.analysis;

import java.lang.Thread.State;
import java.util.Map.Entry;

import io.netty.channel.Channel;
import model.proto.Message;
import model.proto.Message.StringItem;

import com.hygame.dpcq.action.ServerAttributeAction;
import com.hygame.dpcq.config.ParamConfig;
import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.db.dao.model.ServerAttribute;
import com.hygame.dpcq.tools.Lock;

public class Analysis {

	public static void analysisTools(Message.Msg retMsg, Channel channel){
		
		int header = retMsg.getHeader();
		Message.MsgData messageData = retMsg.getMsgdata();
		
		if (header == 20000) {
			for (StringItem stringItem : messageData.getStringItemList()) {
				if("当前在线人数".equals(stringItem.getKey())) {
					Lock.threadMapReturnString.put("onlineNumber", stringItem.getValue());
				}
			}
			activity ();
		} else if (header == 20006) {
			for (StringItem stringItem : messageData.getStringItemList()) {
				if("跑马灯列表".equals(stringItem.getKey())) {	
					Lock.threadMapReturnString.put("pmdlist", stringItem.getValue());
				}
			}
			activity ();
		} else if (header == 20010) {
			for (StringItem stringItem : messageData.getStringItemList()) {
				if("活动列表".equals(stringItem.getKey())) {	
					Lock.threadMapReturnString.put("actlist", stringItem.getValue());
				}
			}
			activity ();
		} else if (header == 20013) {
			for (StringItem stringItem : messageData.getStringItemList()) {
				if("多服更新成功".equals(stringItem.getKey())) {
					ServerAttributeAction.recordFailResult(getServerIdByChannel(channel), ParamConfig.succ);
				} else {
					ServerAttributeAction.recordFailResult(getServerIdByChannel(channel), ParamConfig.fail);
				}
			}
			activity ();
		}  else if (header == 20015) {
			for (StringItem stringItem : messageData.getStringItemList()) {
				if("冻结账号列表".equals(stringItem.getKey())) {
					Lock.threadMapReturnString.put("frozenList", stringItem.getValue());
				}
			}
			activity ();
		}  else if (header == 20023) {
			for (StringItem stringItem : messageData.getStringItemList()) {
				if("禁言账号列表".equals(stringItem.getKey())) {
					Lock.threadMapReturnString.put("shutupList", stringItem.getValue());
				}
			}
			activity ();
		}  else if (header == 20036) {
			for (StringItem stringItem : messageData.getStringItemList()) {
				if("当前设置的最大注册人数".equals(stringItem.getKey())) {
					Lock.threadMapReturnString.put("currMaxRegeditNum", stringItem.getValue());
				}
			}
			activity ();
		} else if (header == 20004) {
			for (StringItem stringItem : messageData.getStringItemList()) {
				if("派发奖励结果".equals(stringItem.getKey())) {
					Lock.threadMapReturnString.put("sendThingRes", stringItem.getValue());
				}
			}
			activity ();
		} else {
			activity ();
		}
	}
	
	private static int getServerIdByChannel (Channel channel) {
		int serverId = 0;
		for (Entry<Integer, ServerAttribute> entry : GameCoon.serverMap.entrySet()) {
			ServerAttribute serverAttribute = entry.getValue();
			if (serverAttribute.getChannel() == channel) {
				serverId = entry.getKey();
				break;
			}
		}
		return serverId;
	}
	
	/**
	 * 激活线程
	 * @author mp
	 * @date 2015-4-7 下午3:35:39
	 * @Description
	 */
	private static void activity () {
		Thread thread = Lock.threadMap.get("callback");
		if (thread != null && thread.getState() == State.NEW) {
			thread.start();
		}
	}
}
