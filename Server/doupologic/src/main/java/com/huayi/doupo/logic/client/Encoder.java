package com.huayi.doupo.logic.client;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf0Output;
import flex.messaging.io.amf.Amf3Output;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;

import model.proto.Message;

import com.huayi.doupo.base.model.socket.statics.StaticProtoType;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;

/**
 * 编码器
 * @author mp
 * @date 2014-9-11 上午11:09:10
 */
public class Encoder extends MessageToByteEncoder<Object>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		
		String protoType = SysConfigUtil.getValue("protocol.type");

		switch (protoType) {
			case StaticProtoType.AMF3:
				amf(ctx, msg, out, "amf3");
				break;
			case StaticProtoType.AMF0:
				amf(ctx, msg, out, "amf0");
				break;
			case StaticProtoType.PROTOBUF:
				protobuf(ctx, msg, out);
				break;
			case StaticProtoType.JSON:
				json(ctx, msg, out);
				break;
			default:
				protobuf(ctx, msg, out);
				break;
		}
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
//			System.out.println("encode--->" + datas.length);
			if (datas != null && datas.length > 0) {
				out.writeInt(datas.length).writeBytes(datas);
				ctx.flush(); 
			}
		} catch (Exception e) {
			LogUtil.error("----Encode Error----", e);
		}
	}
	
	/**
	 * AMF方式
	 * @author mp
	 * @date 2014-9-11 下午4:42:10
	 * @param ctx
	 * @param msg
	 * @param out
	 * @param amfType
	 * @throws Exception
	 * @Description
	 */
	private void amf (ChannelHandlerContext ctx, Object msg, ByteBuf out, String type) throws Exception{
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		SerializationContext serializationContext = new SerializationContext();
		if("amf0".equals(type)){
			Amf0Output amf0Output = new Amf0Output(serializationContext);
			amf0Output.setOutputStream(stream);
			amf0Output.writeObject(msg);
		}else if ("amf3".equals(type)){
			 Amf3Output amf3Output = new Amf3Output(serializationContext);
			 amf3Output.setOutputStream(stream);
			 amf3Output.writeObject(msg);
		}
		byte[] datas = stream.toByteArray();
		if (datas != null && datas.length > 0) {
			out.writeInt(datas.length);
			out.writeBytes(datas);
			ctx.flush(); 
		}
	}
	
	/**
	 * JSON方式
	 * @author mp
	 * @date 2014-9-11 下午4:44:56
	 * @param ctx
	 * @param msg
	 * @param out
	 * @Description
	 */
	private void json (ChannelHandlerContext ctx, Object msg, ByteBuf out) {
		
	}
}
