package com.huayi.doupo.logic.core.tcp;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import com.huayi.doupo.base.util.logic.system.LogUtil;

/**
 * 心跳处理Handler
 * @author mp
 * @date 2014-9-11 上午11:13:28
 */
public class HeartBeatHandler extends ChannelHandlerAdapter {
	
	int count = 0;
	
     @Override
     public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
         
    	 if (evt instanceof IdleStateEvent) {
             IdleStateEvent e = (IdleStateEvent) evt;
             if (e.state() == IdleState.READER_IDLE || e.state() == IdleState.WRITER_IDLE || e.state() == IdleState.ALL_IDLE) {
            	 count ++;
                 LogUtil.out("channelId "+ctx.channel().id().asLongText()+" 超时 " + count + " 次了");
             } 
             
     		//累计超时三次就算断线了
     		if (count >= 3) {
     			ctx.channel().close();
     			LogUtil.out("channelId " + ctx.channel().id().asLongText() + " 掉线了......");
     		}
         }
     }
     
 }