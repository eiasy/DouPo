package mmo.common.config.role;

/**
 * 游戏状态
 * 
 * @author 李天喜
 * 
 */
public final class RoleGameState {
	/** 登录前 */
	public static final byte BEFORE_LOGIN = 0;
	/** 正在登陆 */
	public static final byte LOGINING     = 1;
	/** 已经登陆 */
	public static final byte LOGINED      = 2;
	/** 离线 */
	public static final byte OFF_LINE     = 3;
	/** 创建角色 */
	public static final byte CREATE_ROLE  = 4;
	/** 切换角色 */
	public static final byte CHANGE_ROLE  = 5;
	/** 正在排队 */
	public static final byte WAIT_QUEUE   = 6;

	/** 离线状态 */
	public static final byte stateOffline = 0;
	/** 在线状态 */
	public static final byte stateOnline  = 1;
	
}
