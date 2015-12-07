package mmo.extension.mina.codec;

import java.nio.charset.Charset;

public class Codec2Factory extends CodecFactory {

	public Codec2Factory(String type) {
		this(type, Charset.defaultCharset());
		this.type = type;
	}

	public Codec2Factory(String type, Charset charset) {
		super(type, charset);
	}

	protected void init(Charset charset) {
		this.encoder = new GameEncoder(this);
		this.decoder = new Decoder2(this);
	}

}