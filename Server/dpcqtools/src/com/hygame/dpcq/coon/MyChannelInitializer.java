package com.hygame.dpcq.coon;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class MyChannelInitializer extends ChannelInitializer<Channel> {
     @Override
     public void initChannel(Channel channel) throws Exception{
//         channel.pipeline().addLast(new IdleStateHandler(10, 10, 0));
//         channel.pipeline().addLast(new MyHandler());
         channel.pipeline().addLast(new Encoder());
         channel.pipeline().addLast(new Decoder());
         channel.pipeline().addLast(new DiscardServerHandler());
         
     }
 }