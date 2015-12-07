package mmo.common.message;

/**
 * 消息类型
 * 
 * @author 李天喜
 * 
 */
public interface MessageType {
	/** 通知离线玩家被拒绝加入宗门 */
	short REFUSE_JOIN_SECT  = 1;
	/** 通知离线玩家加入宗门 */
	short AGREE_JOIN_SECT   = 2;
	/** 用户提交的BUG */
	short MSG_BUG           = 3;
	/** 用户提交的建议 */
	short MSG_SUGGEST       = 4;
	/** 系统公告 */
	short MSG_SYSTEM_NOTICE = 5;
	/** 跑马灯 */
	short MSG_PAO_MA_DENG   = 6;
	/** 客服回复的消息 */
	short MSG_CS_RESPONSE   = 7;
	/** 联系GM */
	short MSG_GM            = 9;
}
