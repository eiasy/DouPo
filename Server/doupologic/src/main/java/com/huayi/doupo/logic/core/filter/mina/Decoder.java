package com.huayi.doupo.logic.core.filter.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;


public class Decoder extends CumulativeProtocolDecoder {

	public Decoder() {
	}

	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		

		while (in.remaining() > 4)// 可用数据是否大于包头
		{
			in.mark();
			int size = in.getInt();// 读取包头 包体大小

			if (size > in.remaining()) // 可以用数据是否满足 包体大小
			{
				in.reset();// 重置 等待其实数据
			
				return false;
			} else {
				SerializationContext context = new SerializationContext();
				Amf3Input amf3in = new Amf3Input(context);
				amf3in.setInputStream(in.asInputStream());

				try {
					Object message = amf3in.readObject();// 读取包体(amf)对象
					out.write(message);
					in.free();
				} catch (Exception e) {
					in.reset();
//					Log.error("粘包 Exception size:" + size
//							+ " in.remaining():" + in.remaining());
					return false;
				} finally {
					if (amf3in != null)
						amf3in.close();
					amf3in = null;
				}
			}
		}

		if (in.remaining() == 0) {
			return true;// 解析完成
		} else {
			return false;
		}
	}
}
