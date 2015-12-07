package com.hygame.dpcq.coon;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import model.proto.Message;

/**
 * 编码器
 * @author mp
 * @date 2014-9-11 上午11:09:10
 */
public class Encoder extends MessageToByteEncoder<Object>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		protobuf(ctx, msg, out);
	}
	
	/**
	 * PROTOBUF方式
	 * @author mp
	 * @date 2014-9-11 下午4:39:22
	 * @param ctx
	 * @param msg
	 * @param out
	 * @Description
	 */
	private void protobuf (ChannelHandlerContext ctx, Object msg, ByteBuf out) {
		try {
			Message.Msg message = (Message.Msg)msg;
			byte[] datas = message.toByteArray();
			if (datas != null && datas.length > 0) {
				out.writeInt(datas.length).writeBytes(datas);
				ctx.flush(); 
			}
		} catch (Exception e) {
			System.out.println("----Encode Error----");
		}
	}
}
