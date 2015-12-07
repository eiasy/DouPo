package mmo.common.bean.role.parser;

import mmo.common.bean.role.Role;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

public abstract class ARoleParser {
	protected final static Logger logger = Logger.getLogger(ARoleParser.class); // 日志
	protected int                 protocol;

	public ARoleParser() {

	}

	public int getProtocol() {
		return protocol;
	}

	public abstract void parse(Role role, IoBuffer packet);

	@Override
	public String toString() {
		return "AParser [protocol=" + protocol + "]";
	}

}