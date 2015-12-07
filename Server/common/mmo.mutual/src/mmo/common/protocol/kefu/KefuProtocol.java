package mmo.common.protocol.kefu;

public class KefuProtocol {
	/** 注册 */
	public static final short C2S_0x6275_REGISTER = 0x6275;
	/** 向客服服务器发送消息 */
	public static final short C2S_0x627e_MESSAGE  = 0x627e;
	/** 响应客户回复的消息 */
	public static final short C2S_0x6278_RESPONSE = 0x6278;

	/** 响应注册 */
	public static final short S2C_0x6276_REGISTER = 0x6276;
	/** 回复消息 */
	public static final short S2C_0x6277_RESPONSE = 0x6277;
}
