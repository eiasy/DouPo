package mmo.tools.net.extension.logic;


public abstract class ASessionParser implements ISessionParser {

	@Override
	public String toString() {
		return "AParser [protocol=" + protocol + "]";
	}

	protected int protocol;

	public ASessionParser() {

	}

	public int getProtocol() {
		return protocol;
	}
}