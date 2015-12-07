package com.huayi.doupo.logic.core.filter.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;





public class CodeFactory implements ProtocolCodecFactory {
	
	private ProtocolEncoder encoder;
    private ProtocolDecoder decoder;

	public CodeFactory() {
		
		encoder = new Encoder();
		
        decoder = new Decoder();
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}
}
