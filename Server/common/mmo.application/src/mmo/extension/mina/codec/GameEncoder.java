package mmo.extension.mina.codec;

import mmo.tools.net.extension.session.UserSession;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class GameEncoder extends ProtocolEncoderAdapter {
	protected static final Logger logger = Logger.getLogger(UserSession.class);

	public CodecFactory getCodecFactory() {
		return codecFactory;
	}

	public void setCodecFactory(CodecFactory codecFactory) {
		this.codecFactory = codecFactory;
	}

	private CodecFactory codecFactory;

	public GameEncoder(CodecFactory codecFactor) {
		this.codecFactory = codecFactor;
	}

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		session.write(message);
	}
}
