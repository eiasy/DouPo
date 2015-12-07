package com.huayi.doupo.logic.core.filter.mina;

import java.io.ByteArrayOutputStream;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Output;

public class Encoder implements ProtocolEncoder {

	private final SerializationContext context = new SerializationContext();
	 
	
	public Encoder() {
	}

	public void dispose(IoSession session) throws Exception {
	}

	public void encode(IoSession session, Object message,ProtocolEncoderOutput out) throws Exception 
	{
		
		if(message !=null)
		{
			Amf3Output amf3out = new Amf3Output(context);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			amf3out.setOutputStream(stream);
			amf3out.writeObject(message);

			byte bytes[] = stream.toByteArray();		
			IoBuffer buffer = IoBuffer.allocate(bytes.length).setAutoExpand(true);
			buffer.putInt(bytes.length);
			buffer.put(bytes);
			buffer.flip();
			out.write(buffer);
			buffer.free();
			
			amf3out.flush();
			
			if(amf3out!=null)
				amf3out.close();
			amf3out=null;
		}	
	}
}