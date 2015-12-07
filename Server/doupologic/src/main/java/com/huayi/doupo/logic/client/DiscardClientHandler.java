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
package com.huayi.doupo.logic.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import model.proto.Message;

import com.huayi.doupo.logic.util.MessageData;

/**
 * Handles a client-side channel.
 */
public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 在没有encode , decoder的时候,netty默认的数据传输格式是ByteBuf,其他格式都不合法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//    	for (;;) {
//    		ctx.channel().writeAndFlush("2");
//    		TimeUnit.SECONDS.sleep(1);
//    	}
//    	for (int i = 0; i < 10000; i++) {
//    		ctx.channel().writeAndFlush(i + "");
//		}
    	
    	
    	
    	
    	
    	
//     在线和充值
//  		Message.Msg.Builder msg1 = Message.Msg.newBuilder();
//		msg1.setHeader(1133);//1132 查看充值总额  1133：查看在线人数
//		msg1.setVersion(1);
//		
//		MessageData msgData1 = new MessageData();
//		msgData1.putStringItem("name", "幽城幻剑");
    	
/*    	Message.Msg.Builder msg1 = Message.Msg.newBuilder();
		msg1.setHeader(1140);
		msg1.setVersion(1);
		
		MessageData msgData1 = new MessageData();
		msgData1.putIntItem("type", 1);
		msgData1.putIntItem("pageNum", 1000);*/
    	
//    	sendRegistMsg(ctx);
    	// param[ playerId = 261, chipId = 41, instPlayerLootId = 16, type = 1 ]
    	Message.Msg.Builder msg1 = Message.Msg.newBuilder();
		msg1.setHeader(1071);//在线人数
//		msg1.setHeader(20018);//获取总值
//		msg1.setHeader(20017);//允许同时在线
//		msg1.setHeader(20026);//设置登录不验证状态 1-不验证

//		msg1.setHeader(20030);//查看游戏服中map大小
//		msg1.setHeader(20031);//查看游戏服中map容量
//		msg1.setHeader(20032);//查看游戏服中玩家map容量
//		msg1.setHeader(1183);//查看游戏服中玩家map容量
		msg1.setVersion(1);
		
		
//		msgData1.putIntItem("num", i);
		
		
		MessageData msgData1 = new MessageData();
		msgData1.putIntItem("playerId", 261);
		msgData1.putIntItem("chipId", 3);
		msgData1.putIntItem("instPlayerLootId", 16);
		msgData1.putIntItem("type", 2);
//		msgData1.putStringItem("name", "1_1");
		
		
//		msgData1.putIntItem("id", 7);
//		msgData1.putStringItem("startTime", "2014-12-11 11:10:00");
//		msgData1.putStringItem("endTime", "2014-12-11 11:25:00");
//		msgData1.putStringItem("content", "测试aaaaaaaaaaaa");
//		msgData1.putIntItem("inteval", 5);
		
//		msgData1.putIntItem("id", 6);
		
		
//		msgData1.putIntItem("count", 5);
		msg1.setMsgdata(msgData1.getMsgData());
//		for (int i = 0; i < 11; i++) {
			ctx.channel().writeAndFlush(msg1.build());
//		}
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
    	System.out.println("-----操作已返回--------" + msg);
    }

    public void channelInactive(ChannelHandlerContext ctx)throws Exception{
    	ctx.channel().close();
    }

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

	private void sendRegistMsg (ChannelHandlerContext ctx) {
    	Message.Msg.Builder msg1 = Message.Msg.newBuilder();
		msg1.setHeader(1001);
		msg1.setVersion(1);
		
		MessageData msgData1 = new MessageData();
		msgData1.putStringItem("userip", "1");
		msgData1.putStringItem("openId", "test007");
		
		msg1.setMsgdata(msgData1.getMsgData());
		ctx.channel().writeAndFlush(msg1.build());
	}
}
