package mmo.common.message;

/**
 * 消息状态
 * 
 * @author 李天喜
 * 
 */
public interface MessageState {
	/** 未处理 */
	short UNHANDLE = 0;
	/** 处理 */
	short HANDLED  = 1;
}
