package com.huayi.doupo.logic.core.filter.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.logic.core.tcp.HeartBeatHandler;
import com.huayi.doupo.logic.core.tcp.GameServerHandler;

/**
 * Channel过滤装载器
 * @author mp
 * @date 2014-9-11 上午11:48:31
 */
public class ChannelFilterInitializer extends ChannelInitializer<Channel> {
     
	 @Override
     public void initChannel(Channel channel) throws Exception{
		
		 int readerIdle = Integer.valueOf(SysConfigUtil.getValue("heartBeat.readerIdle"));
		 int writerIdle = Integer.valueOf(SysConfigUtil.getValue("heartBeat.writerIdle"));
		 int allIdle = Integer.valueOf(SysConfigUtil.getValue("heartBeat.allIdle"));
		
         channel.pipeline().addLast(new IdleStateHandler(readerIdle, writerIdle, allIdle));
         channel.pipeline().addLast(new HeartBeatHandler());
         channel.pipeline().addLast(new Encoder());
         channel.pipeline().addLast(new Decoder());
         channel.pipeline().addLast(new GameServerHandler());
     }
 }