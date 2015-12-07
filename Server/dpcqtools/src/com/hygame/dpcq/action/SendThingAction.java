package com.hygame.dpcq.action;

import io.netty.channel.Channel;
import model.proto.Message;
import com.hygame.dpcq.servlet.Boot;

import com.opensymphony.xwork2.ActionSupport;

public class SendThingAction extends ActionSupport{
	
	private static final long serialVersionUID = -5799354446134309082L;

	public String execute() throws Exception {
		
		//获取Channel
		Channel channel = Boot.channelMap.get("miao");
		
		//组织协议格式
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20000);
		msg.setVersion(1);
		channel.writeAndFlush(msg.build());
		
		
		return "success";
	}
	
}
