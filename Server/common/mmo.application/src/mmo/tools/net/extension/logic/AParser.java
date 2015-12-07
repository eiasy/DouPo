package mmo.tools.net.extension.logic;

import mmo.tools.net.extension.session.NetRole;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

public abstract class AParser {
	protected final static Logger logger = Logger.getLogger(AParser.class); // 日志
	protected int                 protocol;

	public AParser() {

	}

	public int getProtocol() {
		return protocol;
	}

	public abstract void parse(NetRole userRole, IoBuffer packet);

	@Override
	public String toString() {
		return "AParser [protocol=" + protocol + "]";
	}

}