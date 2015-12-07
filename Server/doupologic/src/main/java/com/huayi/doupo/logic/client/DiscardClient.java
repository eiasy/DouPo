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

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DiscardClient {

	/******选择IP和端口*****119710****39760*/
	private int index = 0;
	
	
	private  String host = "";
	private  int port = 1;

    public void run() throws Exception {
    	
    	if (index == 0) {
   		 host = "192.168.1.36";
   		 port = 17000;
		} else if (index == 1) {
    		 host = "192.168.1.42";
    		 port = 17000;
		} else if (index == 2) {
	   		 host = "192.168.1.101";
	   		 port = 20000;
		} else if (index == 3) {
	   		 host = "192.168.1.101";
	   		 port = 20001;
		} else if (index == 4) {
	   		 host = "42.62.56.25";
	   		 port = 20001;
		} else if (index == 5) {
	   		 host = "119.29.66.195";
	   		 port = 10001;
		} else if (index == 6) {
	   		 host = "119.29.66.195";
	   		 port = 10002;
		} else if (index == 7) {
	   		 host = "123.59.64.44";
	   		 port = 20001;
		} else if (index == 8) {
	   		 host = "123.59.64.75";
	   		 port = 20002;
		}
    	
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                	 ch.pipeline().addLast(new Encoder());
                	 ch.pipeline().addLast(new Decoder());
                     ch.pipeline().addLast(new DiscardClientHandler());
                 }
             }); 

            ChannelFuture f = b.connect(host, port).sync();
            
            System.out.println("==== client start ====");
            f.channel().closeFuture().sync();
            
        } finally {
            group.shutdownGracefully();
        } 
    }

    public static void main(String[] args) throws Exception {
        new DiscardClient().run();
    }
}
