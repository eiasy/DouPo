package mmo.common.config.exchange;

public interface IExchangeResult {
	byte OK        = 1;
	/** 二次兑换 */
	byte SECOND    = 2;
	/** 已经兑换 */
	byte EXCHANGED = 3;
	/** 绑定服务器 */
	byte TARGETAPP = 4;
	/** 过期 */
	byte OVERDATE  = 5;
}
