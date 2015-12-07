package mmo.common.protocol.game.sub;

public interface SubPro_1045_friend {
	/** 建立关系 */
	byte ADD                          = 0;

	/** 改操作已经被废弃 */
	byte DETAIL                       = 1;
	/** 解除关系 */
	byte DELETE                       = 2;
	/** 同意添加好友 */
	byte ADD_AGREE                    = 3;
	/** 不同意添加好友 */
	byte ADD_AGREE_NO                 = 4;
	/** 全部同意添加好友 */
	byte ADD_ALL_AGREE                = 5;
	/** 全部不同意添加好友 */
	byte ADD_ALL_AGREE_NO             = 6;
	/** 推荐好友 */
	byte ADD_FRIEND_RECOMMEND         = 7;
	/** 通过名称添加好友 */
	byte ADD_BY_NAME                  = 8;

	/** 推荐好友无窗口 */
	byte ADD_FRIEND_RECOMMEND_NO_WIN  = 9;
	/** 赠送好友亲密物品 */
	byte SEND_FRIEND_INTIMACY_ITEM    = 10;
	/** 名字查找 */
	byte SEARCH_FRIEND_BY_NAME        = 11;
	/** 推荐列表赠送 */
	byte INTIMACY_FROM_RECOMMEND_LIST = 12;
	/** 推荐好友 */
	byte RECOMMEND_FRIEND_13          = 13;
	/** 领取好友赠送的精力 */
	byte PRO_14_GET_JING_LI           = 14;
}
