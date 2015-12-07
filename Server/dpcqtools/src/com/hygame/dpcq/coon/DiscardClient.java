
package com.hygame.dpcq.coon;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DiscardClient {

    private final String host;
    private final int port;

    public DiscardClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                	 ch.pipeline().addLast(new Encoder());
                	 ch.pipeline().addLast(new Decoder());
                     ch.pipeline().addLast(new DiscardClientHandler());
                 }
             });

            ChannelFuture f = b.connect(host, port).sync();
            f.get();
    }

  /*  public static void main(String[] args) throws Exception {
    	System.out.println("请输入选项：输入1-刷新小胡服务器， 输入2-刷新苗朋服务器， 3-直接填写ip:port(如：192.168.1.42:7000)");
    	String command = new BufferedReader(new InputStreamReader(System.in)).readLine();
    	if (command.equals("1")) {
    		new DiscardClient("192.168.1.42", 20000).run();
		} else if (command.equals("2")) {
			new DiscardClient("192.168.1.43", 20000).run();
		} else {
			new DiscardClient(command.split(":")[0], Integer.valueOf(command.split(":")[1])).run();
		}
    }*/
}
