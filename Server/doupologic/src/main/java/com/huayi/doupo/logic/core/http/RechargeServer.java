package com.huayi.doupo.logic.core.http;
 
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty Http 服务器
 * @author mp
 * @date 2015-7-13 下午4:08:20
 */
public class RechargeServer {
 
    /**
     * 启动
     * @author mp
     * @date 2015-7-13 下午4:08:56
     * @throws Exception
     * @Description
     */
    public static void start() throws Exception {
    	int port = Integer.valueOf(SysConfigUtil.getValue("socketRecharge.port"));
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new RechargeServerInitializer());
        ChannelFuture f = b.bind(port);
        f.get();
        System.out.println("recharge server start at port " + port);
    }
    
}