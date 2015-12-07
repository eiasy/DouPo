package com.huayi.doupo.logic.client;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf0Input;
import flex.messaging.io.amf.Amf3Input;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.model.socket.statics.StaticProtoType;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * 解码器
 * @author mp
 * @date 2014-9-11 上午11:07:00
 */
public class Decoder extends ByteToMessageDecoder {
	
	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
		
		String protoType = SysConfigUtil.getValue("protocol.type");

		switch (protoType) {
		case StaticProtoType.AMF3:
			amf(ctx, buf, out, "amf3");
			break;
		case StaticProtoType.AMF0:
			amf(ctx, buf, out, "amf0");
			break;
		case StaticProtoType.PROTOBUF:
			protobuf(ctx, buf, out);
			break;
		case StaticProtoType.JSON:
			json(ctx, buf, out);
			break;
		default:
			protobuf(ctx, buf, out);
			break;
		}
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
			
/*			buf.markReaderIndex();
			
			int a = buf.readableBytes();
			for (int i = 0; i < a; i++) {
				System.out.print(buf.readByte() + " ");
			}
			System.out.println();
			
			buf.resetReaderIndex();*/
			
			if (buf.readableBytes() < 4) {
				return;
			}
			
			buf.markReaderIndex();
			
			int lenght = buf.readInt();
			
//			System.out.println("decode----->" + lenght);
			
			if (buf.readableBytes() < lenght) {
				buf.resetReaderIndex();
				return;
			}
			
			byte[] datas = new byte[lenght];
			buf.readBytes(datas);
			
			Map<String, Object> msgMap = MessageUtil.parseFrom(datas);
			if (msgMap != null) {
				out.add(msgMap);
			}
		} catch (Exception e) {
			LogUtil.error("---Decode Error---", e);
		}
	}

	/**
	 * AMF方式
	 * @author mp
	 * @date 2014-9-11 下午4:33:55
	 * @param ctx
	 * @param buf
	 * @param out
	 * @param amfType
	 * @throws Exception
	 * @Description
	 */
	private void amf(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out, String amfType)  throws Exception {
		if (buf.readableBytes() < 4) {
			return;
		}
		int lenght = buf.readInt();
		if (buf.readableBytes() < lenght) {
			return;
		}
		byte[] datas = new byte[lenght];
		buf.readBytes(datas);
		
		Object message = null;
		SerializationContext serializationContext = new SerializationContext();
		
		if ("amf0".equals(amfType)) {
			Amf0Input amf0Input = new Amf0Input(serializationContext);
			amf0Input.setInputStream(new ByteArrayInputStream(datas));
			message = amf0Input.readObject();
		} else if ("amf3".equals(amfType)) {
			Amf3Input amf3Input = new Amf3Input(serializationContext);
			amf3Input.setInputStream(new ByteArrayInputStream(datas));
			message = amf3Input.readObject();
		}
		out.add(message);
	}
	
	/**
	 * JSON方式
	 * @author mp
	 * @date 2014-9-11 下午4:28:28
	 * @param ctx
	 * @param buf
	 * @param out
	 * @Description
	 */
	private void json(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) {

	}

}
