/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.hygame.dpcq.coon;

import java.util.Map.Entry;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import model.proto.Message;

import com.hygame.dpcq.db.dao.model.ServerAttribute;
import com.hygame.dpcq.tools.Lock;
import com.hygame.dpcq.tools.analysis.Analysis;

/**
 * Handles a client-side channel.
 */
public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 在没有encode , decoder的时候,netty默认的数据传输格式是ByteBuf,其他格式都不合法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    	GameCoon.channelMap.put("ctx", ctx.channel());
    	
    	//解锁
    	Lock.threadMap.get("coon").start();
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
    	//回调解锁
    	
    	Message.Msg retMsg = (Message.Msg)msg;
    	
    	/*for(Message.StringItem stringItem : messageData.getStringItemList()){
			System.out.println(stringItem.getKey() + " = " + stringItem.getValue() + ", ");
		}*/
    	Analysis.analysisTools(retMsg, ctx.channel());
    	//存入返回数据
    	
    /*	Lock.threadMapReturnObject = (HashMap<String, Object>)((MessageEvent)msg).getMessage();
    	Lock.threadMapReturnObject.put("key", msg);
    	System.out.println(msg.toString());*/
    }

//    @Override
//	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//    	System.out.println("--- client messageReceived ---");
//    	Message message = (Message) msg;
//    	System.out.println("message is " + message.getHeader());
//	}
    
    public void channelInactive(ChannelHandlerContext ctx)throws Exception{
    	System.out.println("---- Channel Inactive ----");
    	Channel channel = ctx.channel();
    	for(Entry<Integer, ServerAttribute> entry : GameCoon.serverMap.entrySet()){
    		ServerAttribute serverAttribute = entry.getValue();
    		if (serverAttribute.getChannel() != null && serverAttribute.getChannel() == channel) {
    			serverAttribute.setChannel(null);
			}
    	}
    	ctx.channel().close();
    }

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("---- Channel exceptionCaught ----");
		cause.printStackTrace();
		System.out.println(cause.toString());
		ctx.close();
		//解锁
    //	Lock.threadMap.get("coon").start();
	}

}
