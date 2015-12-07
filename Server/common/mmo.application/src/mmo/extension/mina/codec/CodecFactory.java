package mmo.extension.mina.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CodecFactory implements ProtocolCodecFactory {

	protected GameEncoder encoder;
	protected GameDecoder decoder;
	protected String      type;
	protected String      desc = "";

	public CodecFactory(String type) {
		this(type, Charset.defaultCharset());

	}

	public CodecFactory(String type, Charset charset) {
		this.type = type;
		init(charset);
	}

	protected void init(Charset charset) {
		this.encoder = new GameEncoder(this);
		this.decoder = new GameDecoder(this);
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	public String getType() {
		return type;
	}

	public void appendDesc(String desc) {
		if (desc == null || desc.trim().length() < 1) {
			this.desc += "|" + desc;
		} else {
			this.desc = desc;
		}
	}

	public String getDesc() {
		return desc;
	}
}