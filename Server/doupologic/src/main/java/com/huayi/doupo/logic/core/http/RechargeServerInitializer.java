package com.huayi.doupo.logic.core.http;
 
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
 
 
public class RechargeServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = ch.pipeline();
        /**
         * http-request解码器
         */
        pipeline.addLast("decoder", new HttpRequestDecoder());
        /**
         * http-response解码器
         * http服务器端对response编码
         */
        pipeline.addLast("encoder", new HttpResponseEncoder());
 
        /**
         * 压缩
         * Compresses an HttpMessage and an HttpContent in gzip or deflate encoding
         * while respecting the "Accept-Encoding" header.
         * If there is no matching encoding, no compression is done.
         */
//        pipeline.addLast("deflater", new HttpContentCompressor());
 
        pipeline.addLast("handler", new RechargeServerHandler());
//        pipeline.addLast("aggregator", new HttpContent);
//        pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));
        pipeline.addLast("aggregator",new HttpObjectAggregator(655360));
    }
}