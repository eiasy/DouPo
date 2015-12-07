package com.hygame.dpcq.coon;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import model.proto.Message;

/**
 * 解码器
 * @author mp
 * @date 2014-9-11 上午11:07:00
 */
public class Decoder extends ByteToMessageDecoder {
	
	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
		protobuf(ctx, buf, out);
	}

	/**
	 * PROTOBUF方式
	 * @author mp
	 * @date 2014-9-11 下午4:23:12
	 * @param ctx
	 * @param buf
	 * @param out
	 * @Description
	 */
	private void protobuf(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) {
		
		try {
			if (buf.readableBytes() < 4) {
				return;
			}
			buf.markReaderIndex();
			int lenght = buf.readInt();
			if (buf.readableBytes() < lenght) {
				buf.resetReaderIndex();
				return;
			}
			byte[] datas = new byte[lenght];
			buf.readBytes(datas);
			Message.Msg msg = Message.Msg.parseFrom(datas);
//			Message.MsgData msgData = msg.getMsgdata();
			out.add(msg);
		} catch (Exception e) {
			System.out.println("---Decode Error---");
		}
	}

}
