package com.huayi.doupo.logic.core.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.logic.core.filter.netty.ChannelFilterInitializer;

/**
 * Tcp Server 服务器
 * @author mp
 * @date 2015-7-13 下午4:11:07
 */
public class GameServer {
	
	/**
	 * 启动
	 * @author mp
	 * @date 2015-7-13 下午4:13:06
	 * @throws Exception
	 * @Description
	 */
	public static void start() throws Exception {
		String ip = SysConfigUtil.getValue("socketGame.ip");
		int port = Integer.valueOf(SysConfigUtil.getValue("socketGame.port"));
		int gmPort = Integer.valueOf(SysConfigUtil.getValue("socketGm.port"));
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * ParamConfig.cpuMom);
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelFilterInitializer());
        ChannelFuture f = b.bind(ip, port);
        f = b.bind(ip, gmPort);
        f.get(); //Future的get方法取决于任务的状态， 如果任务已经完成，get会立即返回或者抛出异常，如果任务在规定时间内没有完成，get会阻塞当前线程直到它的完成，如果超出规定时间，那么会抛出异常。
        
        ParamConfig.startServerTime = DateUtil.getCurrMill();
		System.out.println("--game server start--->  ip:" + ip + "  port:" + port);
	}
	
}
