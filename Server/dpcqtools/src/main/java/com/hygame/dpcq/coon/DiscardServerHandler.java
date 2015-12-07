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

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	
    }
    
	@Override
    public void messageReceived(final ChannelHandlerContext ctx, final Object msg) throws Exception {
		
		System.out.println(Thread.currentThread().getName() + " --- " + msg);
		
/*		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.print(Thread.currentThread().getName() + " --- " + msg);
//					if (msg.equals("1")) {
//						System.out.println("   ---累了，睡会---");
//						TimeUnit.SECONDS.sleep(30);
//					} else {
//						System.out.println("   ---来吧，玩会---");
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();*/
		
	  
//    	Message message = (Message) msg;
//    	System.out.println("message is " + message.getHeader());
    }

    public void channelInactive(ChannelHandlerContext ctx)throws Exception{
    	System.out.println("---- Channel Inactive ----");
    	ctx.channel().close();
    }
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("----------Exception-----------");
    }
}
