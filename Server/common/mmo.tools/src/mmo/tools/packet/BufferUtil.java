package mmo.tools.packet;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;

public class BufferUtil {
	protected final static Charset CHARSET       = Charset.forName("UTF-16BE");
	protected final static int     PREFIX_LENGTH = 2;

	public static final void putString(IoBuffer buf, String string) {
		try {
			buf.putPrefixedString(string, PREFIX_LENGTH, CHARSET.newEncoder());
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
	}

	public static final String getString(IoBuffer buf) {
		try {
			return buf.getPrefixedString(PREFIX_LENGTH, CHARSET.newDecoder());
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
